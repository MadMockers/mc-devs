/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.player.PlayerFishEvent;
/*		 */ import org.bukkit.event.player.PlayerFishEvent.State;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityFishingHook extends Entity
/*		 */ {
/*	13 */	 private int d = -1;
/*	14 */	 private int e = -1;
/*	15 */	 private int f = -1;
/*	16 */	 private int g = 0;
/*	17 */	 private boolean h = false;
/*	18 */	 public int a = 0;
/*		 */	 public EntityHuman owner;
/*		 */	 private int i;
/*	21 */	 private int j = 0;
/*	22 */	 private int an = 0;
/*	23 */	 public Entity hooked = null;
/*		 */	 private int ao;
/*		 */	 private double ap;
/*		 */	 private double aq;
/*		 */	 private double ar;
/*		 */	 private double as;
/*		 */	 private double at;
/*		 */ 
/*		 */	 public EntityFishingHook(World world)
/*		 */	 {
/*	32 */		 super(world);
/*	33 */		 a(0.25F, 0.25F);
/*	34 */		 this.ak = true;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityFishingHook(World world, EntityHuman entityhuman) {
/*	38 */		 super(world);
/*	39 */		 this.ak = true;
/*	40 */		 this.owner = entityhuman;
/*	41 */		 this.owner.hookedFish = this;
/*	42 */		 a(0.25F, 0.25F);
/*	43 */		 setPositionRotation(entityhuman.locX, entityhuman.locY + 1.62D - entityhuman.height, entityhuman.locZ, entityhuman.yaw, entityhuman.pitch);
/*	44 */		 this.locX -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
/*	45 */		 this.locY -= 0.1000000014901161D;
/*	46 */		 this.locZ -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
/*	47 */		 setPosition(this.locX, this.locY, this.locZ);
/*	48 */		 this.height = 0.0F;
/*	49 */		 float f = 0.4F;
/*		 */ 
/*	51 */		 this.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
/*	52 */		 this.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
/*	53 */		 this.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F) * f);
/*	54 */		 c(this.motX, this.motY, this.motZ, 1.5F, 1.0F);
/*		 */	 }
/*		 */	 protected void a() {
/*		 */	 }
/*		 */ 
/*		 */	 public void c(double d0, double d1, double d2, float f, float f1) {
/*	60 */		 float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*		 */ 
/*	62 */		 d0 /= f2;
/*	63 */		 d1 /= f2;
/*	64 */		 d2 /= f2;
/*	65 */		 d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	66 */		 d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	67 */		 d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	68 */		 d0 *= f;
/*	69 */		 d1 *= f;
/*	70 */		 d2 *= f;
/*	71 */		 this.motX = d0;
/*	72 */		 this.motY = d1;
/*	73 */		 this.motZ = d2;
/*	74 */		 float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*		 */ 
/*	76 */		 this.lastYaw = (this.yaw = (float)(Math.atan2(d0, d2) * 180.0D / 3.141592741012573D));
/*	77 */		 this.lastPitch = (this.pitch = (float)(Math.atan2(d1, f3) * 180.0D / 3.141592741012573D));
/*	78 */		 this.i = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	82 */		 super.h_();
/*	83 */		 if (this.ao > 0) {
/*	84 */			 double d0 = this.locX + (this.ap - this.locX) / this.ao;
/*	85 */			 double d1 = this.locY + (this.aq - this.locY) / this.ao;
/*	86 */			 double d2 = this.locZ + (this.ar - this.locZ) / this.ao;
/*	87 */			 double d3 = MathHelper.g(this.as - this.yaw);
/*		 */ 
/*	89 */			 this.yaw = (float)(this.yaw + d3 / this.ao);
/*	90 */			 this.pitch = (float)(this.pitch + (this.at - this.pitch) / this.ao);
/*	91 */			 this.ao -= 1;
/*	92 */			 setPosition(d0, d1, d2);
/*	93 */			 b(this.yaw, this.pitch);
/*		 */		 } else {
/*	95 */			 if (!this.world.isStatic) {
/*	96 */				 ItemStack itemstack = this.owner.bC();
/*		 */ 
/*	98 */				 if ((this.owner.dead) || (!this.owner.isAlive()) || (itemstack == null) || (itemstack.getItem() != Item.FISHING_ROD) || (e(this.owner) > 1024.0D)) {
/*	99 */					 die();
/* 100 */					 this.owner.hookedFish = null;
/* 101 */					 return;
/*		 */				 }
/*		 */ 
/* 104 */				 if (this.hooked != null) {
/* 105 */					 if (!this.hooked.dead) {
/* 106 */						 this.locX = this.hooked.locX;
/* 107 */						 this.locY = (this.hooked.boundingBox.b + this.hooked.length * 0.8D);
/* 108 */						 this.locZ = this.hooked.locZ;
/* 109 */						 return;
/*		 */					 }
/*		 */ 
/* 112 */					 this.hooked = null;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 116 */			 if (this.a > 0) {
/* 117 */				 this.a -= 1;
/*		 */			 }
/*		 */ 
/* 120 */			 if (this.h) {
/* 121 */				 int i = this.world.getTypeId(this.d, this.e, this.f);
/*		 */ 
/* 123 */				 if (i == this.g) {
/* 124 */					 this.i += 1;
/* 125 */					 if (this.i == 1200) {
/* 126 */						 die();
/*		 */					 }
/*		 */ 
/* 129 */					 return;
/*		 */				 }
/*		 */ 
/* 132 */				 this.h = false;
/* 133 */				 this.motX *= this.random.nextFloat() * 0.2F;
/* 134 */				 this.motY *= this.random.nextFloat() * 0.2F;
/* 135 */				 this.motZ *= this.random.nextFloat() * 0.2F;
/* 136 */				 this.i = 0;
/* 137 */				 this.j = 0;
/*		 */			 } else {
/* 139 */				 this.j += 1;
/*		 */			 }
/*		 */ 
/* 142 */			 Vec3D vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 143 */			 Vec3D vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 144 */			 MovingObjectPosition movingobjectposition = this.world.a(vec3d, vec3d1);
/*		 */ 
/* 146 */			 vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 147 */			 vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 148 */			 if (movingobjectposition != null) {
/* 149 */				 vec3d1 = Vec3D.a().create(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
/*		 */			 }
/*		 */ 
/* 152 */			 Entity entity = null;
/* 153 */			 List list = this.world.getEntities(this, this.boundingBox.a(this.motX, this.motY, this.motZ).grow(1.0D, 1.0D, 1.0D));
/* 154 */			 double d4 = 0.0D;
/* 155 */			 Iterator iterator = list.iterator();
/*		 */ 
/* 159 */			 while (iterator.hasNext()) {
/* 160 */				 Entity entity1 = (Entity)iterator.next();
/*		 */ 
/* 162 */				 if ((entity1.L()) && ((entity1 != this.owner) || (this.j >= 5))) {
/* 163 */					 float f = 0.3F;
/* 164 */					 AxisAlignedBB axisalignedbb = entity1.boundingBox.grow(f, f, f);
/* 165 */					 MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);
/*		 */ 
/* 167 */					 if (movingobjectposition1 != null) {
/* 168 */						 double d5 = vec3d.distanceSquared(movingobjectposition1.pos);
/* 169 */						 if ((d5 < d4) || (d4 == 0.0D)) {
/* 170 */							 entity = entity1;
/* 171 */							 d4 = d5;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 177 */			 if (entity != null) {
/* 178 */				 movingobjectposition = new MovingObjectPosition(entity);
/*		 */			 }
/*		 */ 
/* 181 */			 if (movingobjectposition != null) {
/* 182 */				 if (movingobjectposition.entity != null) {
/* 183 */					 if (movingobjectposition.entity.damageEntity(DamageSource.projectile(this, this.owner), 0))
/* 184 */						 this.hooked = movingobjectposition.entity;
/*		 */				 }
/*		 */				 else {
/* 187 */					 this.h = true;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 191 */			 if (!this.h) {
/* 192 */				 move(this.motX, this.motY, this.motZ);
/* 193 */				 float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 195 */				 this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D);
/*		 */ 
/* 197 */				 for (this.pitch = (float)(Math.atan2(this.motY, f1) * 180.0D / 3.141592741012573D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/* 201 */				 while (this.pitch - this.lastPitch >= 180.0F) {
/* 202 */					 this.lastPitch += 360.0F;
/*		 */				 }
/*		 */ 
/* 205 */				 while (this.yaw - this.lastYaw < -180.0F) {
/* 206 */					 this.lastYaw -= 360.0F;
/*		 */				 }
/*		 */ 
/* 209 */				 while (this.yaw - this.lastYaw >= 180.0F) {
/* 210 */					 this.lastYaw += 360.0F;
/*		 */				 }
/*		 */ 
/* 213 */				 this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
/* 214 */				 this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
/* 215 */				 float f2 = 0.92F;
/*		 */ 
/* 217 */				 if ((this.onGround) || (this.positionChanged)) {
/* 218 */					 f2 = 0.5F;
/*		 */				 }
/*		 */ 
/* 221 */				 byte b0 = 5;
/* 222 */				 double d6 = 0.0D;
/*		 */ 
/* 224 */				 for (int j = 0; j < b0; j++) {
/* 225 */					 double d7 = this.boundingBox.b + (this.boundingBox.e - this.boundingBox.b) * (j + 0) / b0 - 0.125D + 0.125D;
/* 226 */					 double d8 = this.boundingBox.b + (this.boundingBox.e - this.boundingBox.b) * (j + 1) / b0 - 0.125D + 0.125D;
/* 227 */					 AxisAlignedBB axisalignedbb1 = AxisAlignedBB.a().a(this.boundingBox.a, d7, this.boundingBox.c, this.boundingBox.d, d8, this.boundingBox.f);
/*		 */ 
/* 229 */					 if (this.world.b(axisalignedbb1, Material.WATER)) {
/* 230 */						 d6 += 1.0D / b0;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 234 */				 if (d6 > 0.0D) {
/* 235 */					 if (this.an > 0) {
/* 236 */						 this.an -= 1;
/*		 */					 } else {
/* 238 */						 short short1 = 500;
/*		 */ 
/* 240 */						 if (this.world.B(MathHelper.floor(this.locX), MathHelper.floor(this.locY) + 1, MathHelper.floor(this.locZ))) {
/* 241 */							 short1 = 300;
/*		 */						 }
/*		 */ 
/* 244 */						 if (this.random.nextInt(short1) == 0) {
/* 245 */							 this.an = (this.random.nextInt(30) + 10);
/* 246 */							 this.motY -= 0.2000000029802322D;
/* 247 */							 this.world.makeSound(this, "random.splash", 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/* 248 */							 float f3 = MathHelper.floor(this.boundingBox.b);
/*		 */ 
/* 254 */							 for (int k = 0; k < 1.0F + this.width * 20.0F; k++) {
/* 255 */								 float f5 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 256 */								 float f4 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 257 */								 this.world.a("bubble", this.locX + f5, f3 + 1.0F, this.locZ + f4, this.motX, this.motY - this.random.nextFloat() * 0.2F, this.motZ);
/*		 */							 }
/*		 */ 
/* 260 */							 for (k = 0; k < 1.0F + this.width * 20.0F; k++) {
/* 261 */								 float f5 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 262 */								 float f4 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 263 */								 this.world.a("splash", this.locX + f5, f3 + 1.0F, this.locZ + f4, this.motX, this.motY, this.motZ);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 269 */				 if (this.an > 0) {
/* 270 */					 this.motY -= this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat() * 0.2D;
/*		 */				 }
/*		 */ 
/* 273 */				 double d5 = d6 * 2.0D - 1.0D;
/* 274 */				 this.motY += 0.03999999910593033D * d5;
/* 275 */				 if (d6 > 0.0D) {
/* 276 */					 f2 = (float)(f2 * 0.9D);
/* 277 */					 this.motY *= 0.8D;
/*		 */				 }
/*		 */ 
/* 280 */				 this.motX *= f2;
/* 281 */				 this.motY *= f2;
/* 282 */				 this.motZ *= f2;
/* 283 */				 setPosition(this.locX, this.locY, this.locZ);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 289 */		 nbttagcompound.setShort("xTile", (short)this.d);
/* 290 */		 nbttagcompound.setShort("yTile", (short)this.e);
/* 291 */		 nbttagcompound.setShort("zTile", (short)this.f);
/* 292 */		 nbttagcompound.setByte("inTile", (byte)this.g);
/* 293 */		 nbttagcompound.setByte("shake", (byte)this.a);
/* 294 */		 nbttagcompound.setByte("inGround", (byte)(this.h ? 1 : 0));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 298 */		 this.d = nbttagcompound.getShort("xTile");
/* 299 */		 this.e = nbttagcompound.getShort("yTile");
/* 300 */		 this.f = nbttagcompound.getShort("zTile");
/* 301 */		 this.g = (nbttagcompound.getByte("inTile") & 0xFF);
/* 302 */		 this.a = (nbttagcompound.getByte("shake") & 0xFF);
/* 303 */		 this.h = (nbttagcompound.getByte("inGround") == 1);
/*		 */	 }
/*		 */ 
/*		 */	 public int d() {
/* 307 */		 if (this.world.isStatic) {
/* 308 */			 return 0;
/*		 */		 }
/* 310 */		 byte b0 = 0;
/*		 */ 
/* 312 */		 if (this.hooked != null)
/*		 */		 {
/* 314 */			 PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), this.hooked.getBukkitEntity(), PlayerFishEvent.State.CAUGHT_ENTITY);
/* 315 */			 this.world.getServer().getPluginManager().callEvent(playerFishEvent);
/*		 */ 
/* 317 */			 if (playerFishEvent.isCancelled()) {
/* 318 */				 die();
/* 319 */				 this.owner.hookedFish = null;
/* 320 */				 return 0;
/*		 */			 }
/*		 */ 
/* 324 */			 double d0 = this.owner.locX - this.locX;
/* 325 */			 double d1 = this.owner.locY - this.locY;
/* 326 */			 double d2 = this.owner.locZ - this.locZ;
/* 327 */			 double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/* 328 */			 double d4 = 0.1D;
/*		 */ 
/* 330 */			 this.hooked.motX += d0 * d4;
/* 331 */			 this.hooked.motY += d1 * d4 + MathHelper.sqrt(d3) * 0.08D;
/* 332 */			 this.hooked.motZ += d2 * d4;
/* 333 */			 b0 = 3;
/* 334 */		 } else if (this.an > 0) {
/* 335 */			 EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Item.RAW_FISH));
/*		 */ 
/* 337 */			 PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), entityitem.getBukkitEntity(), PlayerFishEvent.State.CAUGHT_FISH);
/* 338 */			 this.world.getServer().getPluginManager().callEvent(playerFishEvent);
/*		 */ 
/* 340 */			 if (playerFishEvent.isCancelled()) {
/* 341 */				 die();
/* 342 */				 this.owner.hookedFish = null;
/* 343 */				 return 0;
/*		 */			 }
/*		 */ 
/* 347 */			 double d5 = this.owner.locX - this.locX;
/* 348 */			 double d6 = this.owner.locY - this.locY;
/* 349 */			 double d7 = this.owner.locZ - this.locZ;
/* 350 */			 double d8 = MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);
/* 351 */			 double d9 = 0.1D;
/*		 */ 
/* 353 */			 entityitem.motX = (d5 * d9);
/* 354 */			 entityitem.motY = (d6 * d9 + MathHelper.sqrt(d8) * 0.08D);
/* 355 */			 entityitem.motZ = (d7 * d9);
/* 356 */			 this.world.addEntity(entityitem);
/* 357 */			 this.owner.a(StatisticList.B, 1);
/* 358 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/* 361 */		 if (this.h)
/*		 */		 {
/* 363 */			 PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), null, PlayerFishEvent.State.IN_GROUND);
/* 364 */			 this.world.getServer().getPluginManager().callEvent(playerFishEvent);
/*		 */ 
/* 366 */			 if (playerFishEvent.isCancelled()) {
/* 367 */				 die();
/* 368 */				 this.owner.hookedFish = null;
/* 369 */				 return 0;
/*		 */			 }
/*		 */ 
/* 373 */			 b0 = 2;
/*		 */		 }
/*		 */ 
/* 377 */		 if (b0 == 0) {
/* 378 */			 PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), null, PlayerFishEvent.State.FAILED_ATTEMPT);
/* 379 */			 this.world.getServer().getPluginManager().callEvent(playerFishEvent);
/*		 */		 }
/*		 */ 
/* 383 */		 die();
/* 384 */		 this.owner.hookedFish = null;
/* 385 */		 return b0;
/*		 */	 }
/*		 */ 
/*		 */	 public void die()
/*		 */	 {
/* 390 */		 super.die();
/* 391 */		 if (this.owner != null)
/* 392 */			 this.owner.hookedFish = null;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityFishingHook
 * JD-Core Version:		0.6.0
 */