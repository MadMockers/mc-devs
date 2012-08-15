/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.Painting;
/*		 */ import org.bukkit.event.painting.PaintingBreakByEntityEvent;
/*		 */ import org.bukkit.event.painting.PaintingBreakEvent;
/*		 */ import org.bukkit.event.painting.PaintingBreakEvent.RemoveCause;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityPainting extends Entity
/*		 */ {
/*		 */	 private int f;
/*		 */	 public int direction;
/*		 */	 public int x;
/*		 */	 public int y;
/*		 */	 public int z;
/*		 */	 public EnumArt art;
/*		 */ 
/*		 */	 public EntityPainting(World world)
/*		 */	 {
/*	23 */		 super(world);
/*	24 */		 this.f = 0;
/*	25 */		 this.direction = 0;
/*	26 */		 this.height = 0.0F;
/*	27 */		 a(0.5F, 0.5F);
/*	28 */		 this.art = EnumArt.values()[this.random.nextInt(EnumArt.values().length)];
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPainting(World world, int i, int j, int k, int l) {
/*	32 */		 this(world);
/*	33 */		 this.x = i;
/*	34 */		 this.y = j;
/*	35 */		 this.z = k;
/*	36 */		 ArrayList arraylist = new ArrayList();
/*	37 */		 EnumArt[] aenumart = EnumArt.values();
/*	38 */		 int i1 = aenumart.length;
/*		 */ 
/*	40 */		 for (int j1 = 0; j1 < i1; j1++) {
/*	41 */			 EnumArt enumart = aenumart[j1];
/*		 */ 
/*	43 */			 this.art = enumart;
/*	44 */			 setDirection(l);
/*	45 */			 if (survives()) {
/*	46 */				 arraylist.add(enumart);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	50 */		 if (!arraylist.isEmpty()) {
/*	51 */			 this.art = ((EnumArt)arraylist.get(this.random.nextInt(arraylist.size())));
/*		 */		 }
/*		 */ 
/*	54 */		 setDirection(l);
/*		 */	 }
/*		 */	 protected void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public void setDirection(int i) {
/*	60 */		 this.direction = i;
/*	61 */		 this.lastYaw = (this.yaw = i * 90);
/*	62 */		 float f = this.art.B;
/*	63 */		 float f1 = this.art.C;
/*	64 */		 float f2 = this.art.B;
/*		 */ 
/*	66 */		 if ((i != 0) && (i != 2))
/*	67 */			 f = 0.5F;
/*		 */		 else {
/*	69 */			 f2 = 0.5F;
/*		 */		 }
/*		 */ 
/*	72 */		 f /= 32.0F;
/*	73 */		 f1 /= 32.0F;
/*	74 */		 f2 /= 32.0F;
/*	75 */		 float f3 = this.x + 0.5F;
/*	76 */		 float f4 = this.y + 0.5F;
/*	77 */		 float f5 = this.z + 0.5F;
/*	78 */		 float f6 = 0.5625F;
/*		 */ 
/*	80 */		 if (i == 0) {
/*	81 */			 f5 -= f6;
/*		 */		 }
/*		 */ 
/*	84 */		 if (i == 1) {
/*	85 */			 f3 -= f6;
/*		 */		 }
/*		 */ 
/*	88 */		 if (i == 2) {
/*	89 */			 f5 += f6;
/*		 */		 }
/*		 */ 
/*	92 */		 if (i == 3) {
/*	93 */			 f3 += f6;
/*		 */		 }
/*		 */ 
/*	96 */		 if (i == 0) {
/*	97 */			 f3 -= b(this.art.B);
/*		 */		 }
/*		 */ 
/* 100 */		 if (i == 1) {
/* 101 */			 f5 += b(this.art.B);
/*		 */		 }
/*		 */ 
/* 104 */		 if (i == 2) {
/* 105 */			 f3 += b(this.art.B);
/*		 */		 }
/*		 */ 
/* 108 */		 if (i == 3) {
/* 109 */			 f5 -= b(this.art.B);
/*		 */		 }
/*		 */ 
/* 112 */		 f4 += b(this.art.C);
/* 113 */		 setPosition(f3, f4, f5);
/* 114 */		 float f7 = -0.00625F;
/*		 */ 
/* 116 */		 this.boundingBox.b(f3 - f - f7, f4 - f1 - f7, f5 - f2 - f7, f3 + f + f7, f4 + f1 + f7, f5 + f2 + f7);
/*		 */	 }
/*		 */ 
/*		 */	 private float b(int i) {
/* 120 */		 return i == 64 ? 0.5F : i == 32 ? 0.5F : 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/* 124 */		 if ((this.f++ == 100) && (!this.world.isStatic)) {
/* 125 */			 this.f = 0;
/* 126 */			 if ((!this.dead) && (!survives()))
/*		 */			 {
/* 128 */				 Material material = this.world.getMaterial((int)this.locX, (int)this.locY, (int)this.locZ);
/*		 */				 PaintingBreakEvent.RemoveCause cause;
/*		 */				 PaintingBreakEvent.RemoveCause cause;
/* 131 */				 if (material.equals(Material.WATER)) {
/* 132 */					 cause = PaintingBreakEvent.RemoveCause.WATER;
/*		 */				 }
/*		 */				 else
/*		 */				 {
/*		 */					 PaintingBreakEvent.RemoveCause cause;
/* 133 */					 if (!material.equals(Material.AIR))
/*		 */					 {
/* 135 */						 cause = PaintingBreakEvent.RemoveCause.OBSTRUCTION;
/*		 */					 }
/* 137 */					 else cause = PaintingBreakEvent.RemoveCause.PHYSICS;
/*		 */				 }
/*		 */ 
/* 140 */				 PaintingBreakEvent event = new PaintingBreakEvent((Painting)getBukkitEntity(), cause);
/* 141 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 143 */				 if ((event.isCancelled()) || (this.dead)) {
/* 144 */					 return;
/*		 */				 }
/*		 */ 
/* 148 */				 die();
/* 149 */				 this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Item.PAINTING)));
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean survives() {
/* 155 */		 if (!this.world.getCubes(this, this.boundingBox).isEmpty()) {
/* 156 */			 return false;
/*		 */		 }
/* 158 */		 int i = this.art.B / 16;
/* 159 */		 int j = this.art.C / 16;
/* 160 */		 int k = this.x;
/* 161 */		 int l = this.y;
/* 162 */		 int i1 = this.z;
/*		 */ 
/* 164 */		 if (this.direction == 0) {
/* 165 */			 k = MathHelper.floor(this.locX - this.art.B / 32.0F);
/*		 */		 }
/*		 */ 
/* 168 */		 if (this.direction == 1) {
/* 169 */			 i1 = MathHelper.floor(this.locZ - this.art.B / 32.0F);
/*		 */		 }
/*		 */ 
/* 172 */		 if (this.direction == 2) {
/* 173 */			 k = MathHelper.floor(this.locX - this.art.B / 32.0F);
/*		 */		 }
/*		 */ 
/* 176 */		 if (this.direction == 3) {
/* 177 */			 i1 = MathHelper.floor(this.locZ - this.art.B / 32.0F);
/*		 */		 }
/*		 */ 
/* 180 */		 l = MathHelper.floor(this.locY - this.art.C / 32.0F);
/*		 */ 
/* 182 */		 for (int j1 = 0; j1 < i; j1++) {
/* 183 */			 for (int k1 = 0; k1 < j; k1++)
/*		 */			 {
/*		 */				 Material material;
/*		 */				 Material material;
/* 186 */				 if ((this.direction != 0) && (this.direction != 2))
/* 187 */					 material = this.world.getMaterial(this.x, l + k1, i1 + j1);
/*		 */				 else {
/* 189 */					 material = this.world.getMaterial(k + j1, l + k1, this.z);
/*		 */				 }
/*		 */ 
/* 192 */				 if (!material.isBuildable()) {
/* 193 */					 return false;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 198 */		 List list = this.world.getEntities(this, this.boundingBox);
/* 199 */		 Iterator iterator = list.iterator();
/*		 */		 Entity entity;
/*		 */		 do {
/* 204 */			 if (!iterator.hasNext()) {
/* 205 */				 return true;
/*		 */			 }
/*		 */ 
/* 208 */			 entity = (Entity)iterator.next();
/* 209 */		 }while (!(entity instanceof EntityPainting));
/*		 */ 
/* 211 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean L()
/*		 */	 {
/* 216 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 220 */		 if ((!this.dead) && (!this.world.isStatic))
/*		 */		 {
/* 222 */			 PaintingBreakEvent event = null;
/* 223 */			 if (damagesource.getEntity() != null) {
/* 224 */				 event = new PaintingBreakByEntityEvent((Painting)getBukkitEntity(), damagesource.getEntity() == null ? null : damagesource.getEntity().getBukkitEntity());
/*		 */			 }
/* 226 */			 else if (damagesource == DamageSource.FIRE) {
/* 227 */				 event = new PaintingBreakEvent((Painting)getBukkitEntity(), PaintingBreakEvent.RemoveCause.FIRE);
/*		 */			 }
/*		 */ 
/* 232 */			 if (event != null) {
/* 233 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 235 */				 if (event.isCancelled()) {
/* 236 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 240 */			 if (this.dead) {
/* 241 */				 return true;
/*		 */			 }
/*		 */ 
/* 245 */			 die();
/* 246 */			 K();
/* 247 */			 EntityHuman entityhuman = null;
/*		 */ 
/* 249 */			 if ((damagesource.getEntity() instanceof EntityHuman)) {
/* 250 */				 entityhuman = (EntityHuman)damagesource.getEntity();
/*		 */			 }
/*		 */ 
/* 253 */			 if ((entityhuman != null) && (entityhuman.abilities.canInstantlyBuild)) {
/* 254 */				 return true;
/*		 */			 }
/*		 */ 
/* 257 */			 this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Item.PAINTING)));
/*		 */		 }
/*		 */ 
/* 260 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 264 */		 nbttagcompound.setByte("Dir", (byte)this.direction);
/* 265 */		 nbttagcompound.setString("Motive", this.art.A);
/* 266 */		 nbttagcompound.setInt("TileX", this.x);
/* 267 */		 nbttagcompound.setInt("TileY", this.y);
/* 268 */		 nbttagcompound.setInt("TileZ", this.z);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 272 */		 this.direction = nbttagcompound.getByte("Dir");
/* 273 */		 this.x = nbttagcompound.getInt("TileX");
/* 274 */		 this.y = nbttagcompound.getInt("TileY");
/* 275 */		 this.z = nbttagcompound.getInt("TileZ");
/* 276 */		 String s = nbttagcompound.getString("Motive");
/* 277 */		 EnumArt[] aenumart = EnumArt.values();
/* 278 */		 int i = aenumart.length;
/*		 */ 
/* 280 */		 for (int j = 0; j < i; j++) {
/* 281 */			 EnumArt enumart = aenumart[j];
/*		 */ 
/* 283 */			 if (enumart.A.equals(s)) {
/* 284 */				 this.art = enumart;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 288 */		 if (this.art == null) {
/* 289 */			 this.art = EnumArt.KEBAB;
/*		 */		 }
/*		 */ 
/* 292 */		 setDirection(this.direction);
/*		 */	 }
/*		 */ 
/*		 */	 public void move(double d0, double d1, double d2) {
/* 296 */		 if ((!this.world.isStatic) && (!this.dead) && (d0 * d0 + d1 * d1 + d2 * d2 > 0.0D)) {
/* 297 */			 if (this.dead) return;
/*		 */ 
/* 299 */			 die();
/* 300 */			 this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Item.PAINTING)));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void g(double d0, double d1, double d2)
/*		 */	 {
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityPainting
 * JD-Core Version:		0.6.0
 */