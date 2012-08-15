/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*		 */ import org.bukkit.event.entity.EntityTeleportEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityEnderman extends EntityMonster
/*		 */ {
/*	11 */	 private static boolean[] d = new boolean[256];
/*	12 */	 private int e = 0;
/*	13 */	 private int g = 0;
/*		 */ 
/*		 */	 public EntityEnderman(World world) {
/*	16 */		 super(world);
/*	17 */		 this.texture = "/mob/enderman.png";
/*	18 */		 this.bw = 0.2F;
/*	19 */		 this.damage = 7;
/*	20 */		 a(0.6F, 2.9F);
/*	21 */		 this.W = 1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	25 */		 return 40;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	29 */		 super.a();
/*	30 */		 this.datawatcher.a(16, new Byte(0));
/*	31 */		 this.datawatcher.a(17, new Byte(0));
/*	32 */		 this.datawatcher.a(18, new Byte(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	36 */		 super.b(nbttagcompound);
/*	37 */		 nbttagcompound.setShort("carried", (short)getCarriedId());
/*	38 */		 nbttagcompound.setShort("carriedData", (short)getCarriedData());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	42 */		 super.a(nbttagcompound);
/*	43 */		 setCarriedId(nbttagcompound.getShort("carried"));
/*	44 */		 setCarriedData(nbttagcompound.getShort("carriedData"));
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget() {
/*	48 */		 EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, 64.0D);
/*		 */ 
/*	50 */		 if (entityhuman != null) {
/*	51 */			 if (d(entityhuman)) {
/*	52 */				 if (this.g++ == 5) {
/*	53 */					 this.g = 0;
/*	54 */					 e(true);
/*	55 */					 return entityhuman;
/*		 */				 }
/*		 */			 }
/*	58 */			 else this.g = 0;
/*		 */ 
/*		 */		 }
/*		 */ 
/*	62 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(EntityHuman entityhuman) {
/*	66 */		 ItemStack itemstack = entityhuman.inventory.armor[3];
/*		 */ 
/*	68 */		 if ((itemstack != null) && (itemstack.id == Block.PUMPKIN.id)) {
/*	69 */			 return false;
/*		 */		 }
/*	71 */		 Vec3D vec3d = entityhuman.i(1.0F).b();
/*	72 */		 Vec3D vec3d1 = Vec3D.a().create(this.locX - entityhuman.locX, this.boundingBox.b + this.length / 2.0F - (entityhuman.locY + entityhuman.getHeadHeight()), this.locZ - entityhuman.locZ);
/*	73 */		 double d0 = vec3d1.c();
/*		 */ 
/*	75 */		 vec3d1 = vec3d1.b();
/*	76 */		 double d1 = vec3d.b(vec3d1);
/*		 */ 
/*	78 */		 return d1 > 1.0D - 0.025D / d0 ? entityhuman.l(this) : false;
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/*	83 */		 if (G()) {
/*	84 */			 damageEntity(DamageSource.DROWN, 1);
/*		 */		 }
/*		 */ 
/*	87 */		 this.bw = (this.target != null ? 6.5F : 0.3F);
/*		 */ 
/*	90 */		 if (!this.world.isStatic)
/*		 */		 {
/*	95 */			 if (getCarriedId() == 0) {
/*	96 */				 if (this.random.nextInt(20) == 0) {
/*	97 */					 int i = MathHelper.floor(this.locX - 2.0D + this.random.nextDouble() * 4.0D);
/*	98 */					 int j = MathHelper.floor(this.locY + this.random.nextDouble() * 3.0D);
/*	99 */					 int k = MathHelper.floor(this.locZ - 2.0D + this.random.nextDouble() * 4.0D);
/* 100 */					 int l = this.world.getTypeId(i, j, k);
/* 101 */					 if (d[l] != 0)
/*		 */					 {
/* 103 */						 if (!CraftEventFactory.callEntityChangeBlockEvent(this, this.world.getWorld().getBlockAt(i, j, k), org.bukkit.Material.AIR).isCancelled()) {
/* 104 */							 setCarriedId(this.world.getTypeId(i, j, k));
/* 105 */							 setCarriedData(this.world.getData(i, j, k));
/* 106 */							 this.world.setTypeId(i, j, k, 0);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 111 */			 else if (this.random.nextInt(2000) == 0) {
/* 112 */				 int i = MathHelper.floor(this.locX - 1.0D + this.random.nextDouble() * 2.0D);
/* 113 */				 int j = MathHelper.floor(this.locY + this.random.nextDouble() * 2.0D);
/* 114 */				 int k = MathHelper.floor(this.locZ - 1.0D + this.random.nextDouble() * 2.0D);
/* 115 */				 int l = this.world.getTypeId(i, j, k);
/* 116 */				 int i1 = this.world.getTypeId(i, j - 1, k);
/*		 */ 
/* 118 */				 if ((l == 0) && (i1 > 0) && (Block.byId[i1].c()))
/*		 */				 {
/* 120 */					 org.bukkit.block.Block bblock = this.world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/* 122 */					 if (!CraftEventFactory.callEntityChangeBlockEvent(this, bblock, bblock.getType()).isCancelled()) {
/* 123 */						 this.world.setTypeIdAndData(i, j, k, getCarriedId(), getCarriedData());
/* 124 */						 setCarriedId(0);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 131 */		 for (int i = 0; i < 2; i++) {
/* 132 */			 this.world.a("portal", this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length - 0.25D, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
/*		 */		 }
/*		 */ 
/* 135 */		 if ((this.world.r()) && (!this.world.isStatic)) {
/* 136 */			 float f = c(1.0F);
/*		 */ 
/* 138 */			 if ((f > 0.5F) && (this.world.j(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ))) && (this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)) {
/* 139 */				 this.target = null;
/* 140 */				 e(false);
/* 141 */				 n();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 145 */		 if (G()) {
/* 146 */			 this.target = null;
/* 147 */			 e(false);
/* 148 */			 n();
/*		 */		 }
/*		 */ 
/* 151 */		 this.bu = false;
/* 152 */		 if (this.target != null) {
/* 153 */			 a(this.target, 100.0F, 100.0F);
/*		 */		 }
/*		 */ 
/* 156 */		 if ((!this.world.isStatic) && (isAlive())) {
/* 157 */			 if (this.target != null) {
/* 158 */				 if (((this.target instanceof EntityHuman)) && (d((EntityHuman)this.target))) {
/* 159 */					 this.br = (this.bs = 0.0F);
/* 160 */					 this.bw = 0.0F;
/* 161 */					 if (this.target.e(this) < 16.0D) {
/* 162 */						 n();
/*		 */					 }
/*		 */ 
/* 165 */					 this.e = 0;
/* 166 */				 } else if ((this.target.e(this) > 256.0D) && (this.e++ >= 30) && (c(this.target))) {
/* 167 */					 this.e = 0;
/*		 */				 }
/*		 */			 } else {
/* 170 */				 e(false);
/* 171 */				 this.e = 0;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 175 */		 super.d();
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean n() {
/* 179 */		 double d0 = this.locX + (this.random.nextDouble() - 0.5D) * 64.0D;
/* 180 */		 double d1 = this.locY + (this.random.nextInt(64) - 32);
/* 181 */		 double d2 = this.locZ + (this.random.nextDouble() - 0.5D) * 64.0D;
/*		 */ 
/* 183 */		 return j(d0, d1, d2);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean c(Entity entity) {
/* 187 */		 Vec3D vec3d = Vec3D.a().create(this.locX - entity.locX, this.boundingBox.b + this.length / 2.0F - entity.locY + entity.getHeadHeight(), this.locZ - entity.locZ);
/*		 */ 
/* 189 */		 vec3d = vec3d.b();
/* 190 */		 double d0 = 16.0D;
/* 191 */		 double d1 = this.locX + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.a * d0;
/* 192 */		 double d2 = this.locY + (this.random.nextInt(16) - 8) - vec3d.b * d0;
/* 193 */		 double d3 = this.locZ + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.c * d0;
/*		 */ 
/* 195 */		 return j(d1, d2, d3);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean j(double d0, double d1, double d2) {
/* 199 */		 double d3 = this.locX;
/* 200 */		 double d4 = this.locY;
/* 201 */		 double d5 = this.locZ;
/*		 */ 
/* 203 */		 this.locX = d0;
/* 204 */		 this.locY = d1;
/* 205 */		 this.locZ = d2;
/* 206 */		 boolean flag = false;
/* 207 */		 int i = MathHelper.floor(this.locX);
/* 208 */		 int j = MathHelper.floor(this.locY);
/* 209 */		 int k = MathHelper.floor(this.locZ);
/*		 */ 
/* 212 */		 if (this.world.isLoaded(i, j, k)) {
/* 213 */			 boolean flag1 = false;
/*		 */ 
/* 215 */			 while ((!flag1) && (j > 0)) {
/* 216 */				 int l = this.world.getTypeId(i, j - 1, k);
/* 217 */				 if ((l != 0) && (Block.byId[l].material.isSolid())) {
/* 218 */					 flag1 = true; continue;
/*		 */				 }
/* 220 */				 this.locY -= 1.0D;
/* 221 */				 j--;
/*		 */			 }
/*		 */ 
/* 225 */			 if (flag1)
/*		 */			 {
/* 227 */				 EntityTeleportEvent teleport = new EntityTeleportEvent(getBukkitEntity(), new Location(this.world.getWorld(), d3, d4, d5), new Location(this.world.getWorld(), this.locX, this.locY, this.locZ));
/* 228 */				 this.world.getServer().getPluginManager().callEvent(teleport);
/* 229 */				 if (teleport.isCancelled()) {
/* 230 */					 return false;
/*		 */				 }
/*		 */ 
/* 233 */				 Location to = teleport.getTo();
/* 234 */				 setPosition(to.getX(), to.getY(), to.getZ());
/*		 */ 
/* 237 */				 if ((this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox))) {
/* 238 */					 flag = true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 243 */		 if (!flag) {
/* 244 */			 setPosition(d3, d4, d5);
/* 245 */			 return false;
/*		 */		 }
/* 247 */		 short short1 = 128;
/*		 */ 
/* 249 */		 for (int l = 0; l < short1; l++) {
/* 250 */			 double d6 = l / (short1 - 1.0D);
/* 251 */			 float f = (this.random.nextFloat() - 0.5F) * 0.2F;
/* 252 */			 float f1 = (this.random.nextFloat() - 0.5F) * 0.2F;
/* 253 */			 float f2 = (this.random.nextFloat() - 0.5F) * 0.2F;
/* 254 */			 double d7 = d3 + (this.locX - d3) * d6 + (this.random.nextDouble() - 0.5D) * this.width * 2.0D;
/* 255 */			 double d8 = d4 + (this.locY - d4) * d6 + this.random.nextDouble() * this.length;
/* 256 */			 double d9 = d5 + (this.locZ - d5) * d6 + (this.random.nextDouble() - 0.5D) * this.width * 2.0D;
/*		 */ 
/* 258 */			 this.world.a("portal", d7, d8, d9, f, f1, f2);
/*		 */		 }
/*		 */ 
/* 261 */		 this.world.makeSound(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
/* 262 */		 this.world.makeSound(this, "mob.endermen.portal", 1.0F, 1.0F);
/* 263 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ()
/*		 */	 {
/* 268 */		 return "mob.endermen.idle";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/* 272 */		 return "mob.endermen.hit";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/* 276 */		 return "mob.endermen.death";
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/* 280 */		 return Item.ENDER_PEARL.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i) {
/* 284 */		 int j = getLootId();
/*		 */ 
/* 286 */		 if (j > 0)
/*		 */		 {
/* 288 */			 List loot = new ArrayList();
/* 289 */			 int count = this.random.nextInt(2 + i);
/*		 */ 
/* 291 */			 if ((j > 0) && (count > 0)) {
/* 292 */				 loot.add(new org.bukkit.inventory.ItemStack(j, count));
/*		 */			 }
/*		 */ 
/* 295 */			 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void setCarriedId(int i)
/*		 */	 {
/* 301 */		 this.datawatcher.watch(16, Byte.valueOf((byte)(i & 0xFF)));
/*		 */	 }
/*		 */ 
/*		 */	 public int getCarriedId() {
/* 305 */		 return this.datawatcher.getByte(16);
/*		 */	 }
/*		 */ 
/*		 */	 public void setCarriedData(int i) {
/* 309 */		 this.datawatcher.watch(17, Byte.valueOf((byte)(i & 0xFF)));
/*		 */	 }
/*		 */ 
/*		 */	 public int getCarriedData() {
/* 313 */		 return this.datawatcher.getByte(17);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 317 */		 if ((damagesource instanceof EntityDamageSourceIndirect)) {
/* 318 */			 for (int j = 0; j < 64; j++) {
/* 319 */				 if (n()) {
/* 320 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 324 */			 return false;
/*		 */		 }
/* 326 */		 if ((damagesource.getEntity() instanceof EntityHuman)) {
/* 327 */			 e(true);
/*		 */		 }
/*		 */ 
/* 330 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 public void e(boolean flag)
/*		 */	 {
/* 335 */		 this.datawatcher.watch(18, Byte.valueOf((byte)(flag ? 1 : 0)));
/*		 */	 }
/*		 */ 
/*		 */	 static {
/* 339 */		 d[Block.GRASS.id] = true;
/* 340 */		 d[Block.DIRT.id] = true;
/* 341 */		 d[Block.SAND.id] = true;
/* 342 */		 d[Block.GRAVEL.id] = true;
/* 343 */		 d[Block.YELLOW_FLOWER.id] = true;
/* 344 */		 d[Block.RED_ROSE.id] = true;
/* 345 */		 d[Block.BROWN_MUSHROOM.id] = true;
/* 346 */		 d[Block.RED_MUSHROOM.id] = true;
/* 347 */		 d[Block.TNT.id] = true;
/* 348 */		 d[Block.CACTUS.id] = true;
/* 349 */		 d[Block.CLAY.id] = true;
/* 350 */		 d[Block.PUMPKIN.id] = true;
/* 351 */		 d[Block.MELON.id] = true;
/* 352 */		 d[Block.MYCEL.id] = true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityEnderman
 * JD-Core Version:		0.6.0
 */