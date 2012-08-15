/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.Material;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.TNTPrimed;
/*		 */ import org.bukkit.event.entity.EntityDamageByBlockEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*		 */ import org.bukkit.event.entity.EntityExplodeEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class Explosion
/*		 */ {
/*	22 */	 public boolean a = false;
/*	23 */	 private int h = 16;
/*	24 */	 private Random i = new Random();
/*		 */	 private World world;
/*		 */	 public double posX;
/*		 */	 public double posY;
/*		 */	 public double posZ;
/*		 */	 public Entity source;
/*		 */	 public float size;
/*	31 */	 public List blocks = new ArrayList();
/*	32 */	 private Map k = new HashMap();
/*	33 */	 public boolean wasCanceled = false;
/*		 */ 
/*		 */	 public Explosion(World world, Entity entity, double d0, double d1, double d2, float f) {
/*	36 */		 this.world = world;
/*	37 */		 this.source = entity;
/*	38 */		 this.size = (float)Math.max(f, 0.0D);
/*	39 */		 this.posX = d0;
/*	40 */		 this.posY = d1;
/*	41 */		 this.posZ = d2;
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/*	46 */		 if (this.size < 0.1F) {
/*	47 */			 return;
/*		 */		 }
/*		 */ 
/*	51 */		 float f = this.size;
/*	52 */		 HashSet hashset = new HashSet();
/*		 */ 
/*	61 */		 for (int i = 0; i < this.h; i++) {
/*	62 */			 for (int j = 0; j < this.h; j++) {
/*	63 */				 for (int k = 0; k < this.h; k++) {
/*	64 */					 if ((i == 0) || (i == this.h - 1) || (j == 0) || (j == this.h - 1) || (k == 0) || (k == this.h - 1)) {
/*	65 */						 double d3 = i / (this.h - 1.0F) * 2.0F - 1.0F;
/*	66 */						 double d4 = j / (this.h - 1.0F) * 2.0F - 1.0F;
/*	67 */						 double d5 = k / (this.h - 1.0F) * 2.0F - 1.0F;
/*	68 */						 double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*		 */ 
/*	70 */						 d3 /= d6;
/*	71 */						 d4 /= d6;
/*	72 */						 d5 /= d6;
/*	73 */						 float f1 = this.size * (0.7F + this.world.random.nextFloat() * 0.6F);
/*		 */ 
/*	75 */						 double d0 = this.posX;
/*	76 */						 double d1 = this.posY;
/*	77 */						 double d2 = this.posZ;
/*		 */ 
/*	79 */						 for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
/*	80 */							 int l = MathHelper.floor(d0);
/*	81 */							 int i1 = MathHelper.floor(d1);
/*	82 */							 int j1 = MathHelper.floor(d2);
/*	83 */							 int k1 = this.world.getTypeId(l, i1, j1);
/*		 */ 
/*	85 */							 if (k1 > 0) {
/*	86 */								 f1 -= (Block.byId[k1].a(this.source) + 0.3F) * f2;
/*		 */							 }
/*		 */ 
/*	89 */							 if ((f1 > 0.0F) && (i1 < 256) && (i1 >= 0)) {
/*	90 */								 hashset.add(new ChunkPosition(l, i1, j1));
/*		 */							 }
/*		 */ 
/*	93 */							 d0 += d3 * f2;
/*	94 */							 d1 += d4 * f2;
/*	95 */							 d2 += d5 * f2;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 102 */		 this.blocks.addAll(hashset);
/* 103 */		 this.size *= 2.0F;
/* 104 */		 i = MathHelper.floor(this.posX - this.size - 1.0D);
/* 105 */		 int j = MathHelper.floor(this.posX + this.size + 1.0D);
/* 106 */		 int k = MathHelper.floor(this.posY - this.size - 1.0D);
/* 107 */		 int l1 = MathHelper.floor(this.posY + this.size + 1.0D);
/* 108 */		 int i2 = MathHelper.floor(this.posZ - this.size - 1.0D);
/* 109 */		 int j2 = MathHelper.floor(this.posZ + this.size + 1.0D);
/* 110 */		 List list = this.world.getEntities(this.source, AxisAlignedBB.a().a(i, k, i2, j, l1, j2));
/* 111 */		 Vec3D vec3d = Vec3D.a().create(this.posX, this.posY, this.posZ);
/*		 */ 
/* 113 */		 for (int k2 = 0; k2 < list.size(); k2++) {
/* 114 */			 Entity entity = (Entity)list.get(k2);
/* 115 */			 double d7 = entity.f(this.posX, this.posY, this.posZ) / this.size;
/*		 */ 
/* 117 */			 if (d7 <= 1.0D) {
/* 118 */				 double d0 = entity.locX - this.posX;
/* 119 */				 double d1 = entity.locY + entity.getHeadHeight() - this.posY;
/* 120 */				 double d2 = entity.locZ - this.posZ;
/* 121 */				 double d8 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*		 */ 
/* 123 */				 if (d8 != 0.0D) {
/* 124 */					 d0 /= d8;
/* 125 */					 d1 /= d8;
/* 126 */					 d2 /= d8;
/* 127 */					 double d9 = this.world.a(vec3d, entity.boundingBox);
/* 128 */					 double d10 = (1.0D - d7) * d9;
/*		 */ 
/* 131 */					 org.bukkit.entity.Entity damagee = entity == null ? null : entity.getBukkitEntity();
/* 132 */					 int damageDone = (int)((d10 * d10 + d10) / 2.0D * 8.0D * this.size + 1.0D);
/*		 */ 
/* 134 */					 if (damagee == null)
/*		 */						 continue;
/* 136 */					 if (this.source == null) {
/* 137 */						 EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(null, damagee, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, damageDone);
/* 138 */						 Bukkit.getPluginManager().callEvent(event);
/*		 */ 
/* 140 */						 if (!event.isCancelled()) {
/* 141 */							 damagee.setLastDamageCause(event);
/* 142 */							 entity.damageEntity(DamageSource.EXPLOSION, event.getDamage());
/*		 */ 
/* 144 */							 entity.motX += d0 * d10;
/* 145 */							 entity.motY += d1 * d10;
/* 146 */							 entity.motZ += d2 * d10;
/* 147 */							 if ((entity instanceof EntityHuman))
/* 148 */								 this.k.put((EntityHuman)entity, Vec3D.a().create(d0 * d10, d1 * d10, d2 * d10));
/*		 */						 }
/*		 */					 }
/*		 */					 else {
/* 152 */						 org.bukkit.entity.Entity damager = this.source.getBukkitEntity();
/*		 */						 EntityDamageEvent.DamageCause damageCause;
/*		 */						 EntityDamageEvent.DamageCause damageCause;
/* 155 */						 if ((damager instanceof TNTPrimed))
/* 156 */							 damageCause = EntityDamageEvent.DamageCause.BLOCK_EXPLOSION;
/*		 */						 else {
/* 158 */							 damageCause = EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
/*		 */						 }
/*		 */ 
/* 161 */						 EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, damagee, damageCause, damageDone);
/* 162 */						 Bukkit.getPluginManager().callEvent(event);
/*		 */ 
/* 164 */						 if (!event.isCancelled()) {
/* 165 */							 entity.getBukkitEntity().setLastDamageCause(event);
/* 166 */							 entity.damageEntity(DamageSource.EXPLOSION, event.getDamage());
/*		 */ 
/* 168 */							 entity.motX += d0 * d10;
/* 169 */							 entity.motY += d1 * d10;
/* 170 */							 entity.motZ += d2 * d10;
/* 171 */							 if ((entity instanceof EntityHuman)) {
/* 172 */								 this.k.put((EntityHuman)entity, Vec3D.a().create(d0 * d10, d1 * d10, d2 * d10));
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 181 */		 this.size = f;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(boolean flag) {
/* 185 */		 this.world.makeSound(this.posX, this.posY, this.posZ, "random.explode", 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
/* 186 */		 this.world.a("hugeexplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
/*		 */ 
/* 189 */		 org.bukkit.World bworld = this.world.getWorld();
/* 190 */		 org.bukkit.entity.Entity explode = this.source == null ? null : this.source.getBukkitEntity();
/* 191 */		 Location location = new Location(bworld, this.posX, this.posY, this.posZ);
/*		 */ 
/* 193 */		 List blockList = new ArrayList();
/* 194 */		 for (int j = this.blocks.size() - 1; j >= 0; j--) {
/* 195 */			 ChunkPosition cpos = (ChunkPosition)this.blocks.get(j);
/* 196 */			 org.bukkit.block.Block block = bworld.getBlockAt(cpos.x, cpos.y, cpos.z);
/* 197 */			 if (block.getType() != Material.AIR) {
/* 198 */				 blockList.add(block);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 202 */		 EntityExplodeEvent event = new EntityExplodeEvent(explode, location, blockList, 0.3F);
/* 203 */		 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 205 */		 this.blocks.clear();
/*		 */ 
/* 207 */		 for (org.bukkit.block.Block block : event.blockList()) {
/* 208 */			 ChunkPosition coords = new ChunkPosition(block.getX(), block.getY(), block.getZ());
/* 209 */			 this.blocks.add(coords);
/*		 */		 }
/*		 */ 
/* 212 */		 if (event.isCancelled()) {
/* 213 */			 this.wasCanceled = true;
/* 214 */			 return;
/*		 */		 }
/*		 */ 
/* 217 */		 Iterator iterator = this.blocks.iterator();
/*		 */ 
/* 226 */		 while (iterator.hasNext()) {
/* 227 */			 ChunkPosition chunkposition = (ChunkPosition)iterator.next();
/* 228 */			 int i = chunkposition.x;
/* 229 */			 int j = chunkposition.y;
/* 230 */			 int k = chunkposition.z;
/* 231 */			 int l = this.world.getTypeId(i, j, k);
/* 232 */			 if (flag) {
/* 233 */				 double d0 = i + this.world.random.nextFloat();
/* 234 */				 double d1 = j + this.world.random.nextFloat();
/* 235 */				 double d2 = k + this.world.random.nextFloat();
/* 236 */				 double d3 = d0 - this.posX;
/* 237 */				 double d4 = d1 - this.posY;
/* 238 */				 double d5 = d2 - this.posZ;
/* 239 */				 double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*		 */ 
/* 241 */				 d3 /= d6;
/* 242 */				 d4 /= d6;
/* 243 */				 d5 /= d6;
/* 244 */				 double d7 = 0.5D / (d6 / this.size + 0.1D);
/*		 */ 
/* 246 */				 d7 *= (this.world.random.nextFloat() * this.world.random.nextFloat() + 0.3F);
/* 247 */				 d3 *= d7;
/* 248 */				 d4 *= d7;
/* 249 */				 d5 *= d7;
/* 250 */				 this.world.a("explode", (d0 + this.posX * 1.0D) / 2.0D, (d1 + this.posY * 1.0D) / 2.0D, (d2 + this.posZ * 1.0D) / 2.0D, d3, d4, d5);
/* 251 */				 this.world.a("smoke", d0, d1, d2, d3, d4, d5);
/*		 */			 }
/*		 */ 
/* 255 */			 if ((l <= 0) || (l == Block.FIRE.id))
/*		 */				 continue;
/* 257 */			 Block.byId[l].dropNaturally(this.world, i, j, k, this.world.getData(i, j, k), event.getYield(), 0);
/* 258 */			 if (this.world.setRawTypeIdAndData(i, j, k, 0, 0, this.world.isStatic)) {
/* 259 */				 this.world.applyPhysics(i, j, k, 0);
/*		 */			 }
/*		 */ 
/* 262 */			 Block.byId[l].wasExploded(this.world, i, j, k);
/*		 */		 }
/*		 */ 
/* 266 */		 if (this.a) {
/* 267 */			 iterator = this.blocks.iterator();
/*		 */ 
/* 269 */			 while (iterator.hasNext()) {
/* 270 */				 ChunkPosition chunkposition = (ChunkPosition)iterator.next();
/* 271 */				 int i = chunkposition.x;
/* 272 */				 int j = chunkposition.y;
/* 273 */				 int k = chunkposition.z;
/* 274 */				 int l = this.world.getTypeId(i, j, k);
/* 275 */				 int i1 = this.world.getTypeId(i, j - 1, k);
/*		 */ 
/* 277 */				 if ((l == 0) && (Block.n[i1] != 0) && (this.i.nextInt(3) == 0))
/* 278 */					 this.world.setTypeId(i, j, k, Block.FIRE.id);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Map b()
/*		 */	 {
/* 285 */		 return this.k;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Explosion
 * JD-Core Version:		0.6.0
 */