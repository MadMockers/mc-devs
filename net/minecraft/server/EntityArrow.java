/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.entity.CraftItem;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.entity.Projectile;
/*		 */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*		 */ import org.bukkit.event.entity.ProjectileHitEvent;
/*		 */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityArrow extends Entity
/*		 */ {
/*	15 */	 private int d = -1;
/*	16 */	 private int e = -1;
/*	17 */	 private int f = -1;
/*	18 */	 private int g = 0;
/*	19 */	 private int h = 0;
/*	20 */	 private boolean inGround = false;
/*	21 */	 public int fromPlayer = 0;
/*	22 */	 public int shake = 0;
/*		 */	 public Entity shooter;
/*		 */	 private int j;
/*	25 */	 private int an = 0;
/*	26 */	 private double damage = 2.0D;
/*		 */	 private int ap;
/*		 */ 
/*		 */	 public EntityArrow(World world)
/*		 */	 {
/*	30 */		 super(world);
/*	31 */		 a(0.5F, 0.5F);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityArrow(World world, double d0, double d1, double d2) {
/*	35 */		 super(world);
/*	36 */		 a(0.5F, 0.5F);
/*	37 */		 setPosition(d0, d1, d2);
/*	38 */		 this.height = 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityArrow(World world, EntityLiving entityliving, EntityLiving entityliving1, float f, float f1) {
/*	42 */		 super(world);
/*	43 */		 this.shooter = entityliving;
/*	44 */		 if ((entityliving instanceof EntityHuman)) {
/*	45 */			 this.fromPlayer = 1;
/*		 */		 }
/*		 */ 
/*	48 */		 this.locY = (entityliving.locY + entityliving.getHeadHeight() - 0.1000000014901161D);
/*	49 */		 double d0 = entityliving1.locX - entityliving.locX;
/*	50 */		 double d1 = entityliving1.locY + entityliving1.getHeadHeight() - 0.699999988079071D - this.locY;
/*	51 */		 double d2 = entityliving1.locZ - entityliving.locZ;
/*	52 */		 double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*		 */ 
/*	54 */		 if (d3 >= 1.0E-007D) {
/*	55 */			 float f2 = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592741012573D) - 90.0F;
/*	56 */			 float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / 3.141592741012573D));
/*	57 */			 double d4 = d0 / d3;
/*	58 */			 double d5 = d2 / d3;
/*		 */ 
/*	60 */			 setPositionRotation(entityliving.locX + d4, this.locY, entityliving.locZ + d5, f2, f3);
/*	61 */			 this.height = 0.0F;
/*	62 */			 float f4 = (float)d3 * 0.2F;
/*		 */ 
/*	64 */			 shoot(d0, d1 + f4, d2, f, f1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public EntityArrow(World world, EntityLiving entityliving, float f) {
/*	69 */		 super(world);
/*	70 */		 this.shooter = entityliving;
/*	71 */		 if ((entityliving instanceof EntityHuman)) {
/*	72 */			 this.fromPlayer = 1;
/*		 */		 }
/*		 */ 
/*	75 */		 a(0.5F, 0.5F);
/*	76 */		 setPositionRotation(entityliving.locX, entityliving.locY + entityliving.getHeadHeight(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
/*	77 */		 this.locX -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
/*	78 */		 this.locY -= 0.1000000014901161D;
/*	79 */		 this.locZ -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
/*	80 */		 setPosition(this.locX, this.locY, this.locZ);
/*	81 */		 this.height = 0.0F;
/*	82 */		 this.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
/*	83 */		 this.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
/*	84 */		 this.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F));
/*	85 */		 shoot(this.motX, this.motY, this.motZ, f * 1.5F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	89 */		 this.datawatcher.a(16, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void shoot(double d0, double d1, double d2, float f, float f1) {
/*	93 */		 float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*		 */ 
/*	95 */		 d0 /= f2;
/*	96 */		 d1 /= f2;
/*	97 */		 d2 /= f2;
/*	98 */		 d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/*	99 */		 d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 100 */		 d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
/* 101 */		 d0 *= f;
/* 102 */		 d1 *= f;
/* 103 */		 d2 *= f;
/* 104 */		 this.motX = d0;
/* 105 */		 this.motY = d1;
/* 106 */		 this.motZ = d2;
/* 107 */		 float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*		 */ 
/* 109 */		 this.lastYaw = (this.yaw = (float)(Math.atan2(d0, d2) * 180.0D / 3.141592741012573D));
/* 110 */		 this.lastPitch = (this.pitch = (float)(Math.atan2(d1, f3) * 180.0D / 3.141592741012573D));
/* 111 */		 this.j = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/* 115 */		 super.h_();
/* 116 */		 if ((this.lastPitch == 0.0F) && (this.lastYaw == 0.0F)) {
/* 117 */			 float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 119 */			 this.lastYaw = (this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D));
/* 120 */			 this.lastPitch = (this.pitch = (float)(Math.atan2(this.motY, f) * 180.0D / 3.141592741012573D));
/*		 */		 }
/*		 */ 
/* 123 */		 int i = this.world.getTypeId(this.d, this.e, this.f);
/*		 */ 
/* 125 */		 if (i > 0) {
/* 126 */			 Block.byId[i].updateShape(this.world, this.d, this.e, this.f);
/* 127 */			 AxisAlignedBB axisalignedbb = Block.byId[i].e(this.world, this.d, this.e, this.f);
/*		 */ 
/* 129 */			 if ((axisalignedbb != null) && (axisalignedbb.a(Vec3D.a().create(this.locX, this.locY, this.locZ)))) {
/* 130 */				 this.inGround = true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 134 */		 if (this.shake > 0) {
/* 135 */			 this.shake -= 1;
/*		 */		 }
/*		 */ 
/* 138 */		 if (this.inGround) {
/* 139 */			 int j = this.world.getTypeId(this.d, this.e, this.f);
/* 140 */			 int k = this.world.getData(this.d, this.e, this.f);
/*		 */ 
/* 142 */			 if ((j == this.g) && (k == this.h)) {
/* 143 */				 this.j += 1;
/* 144 */				 if (this.j == 1200)
/* 145 */					 die();
/*		 */			 }
/*		 */			 else {
/* 148 */				 this.inGround = false;
/* 149 */				 this.motX *= this.random.nextFloat() * 0.2F;
/* 150 */				 this.motY *= this.random.nextFloat() * 0.2F;
/* 151 */				 this.motZ *= this.random.nextFloat() * 0.2F;
/* 152 */				 this.j = 0;
/* 153 */				 this.an = 0;
/*		 */			 }
/*		 */		 } else {
/* 156 */			 this.an += 1;
/* 157 */			 Vec3D vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 158 */			 Vec3D vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 159 */			 MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1, false, true);
/*		 */ 
/* 161 */			 vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
/* 162 */			 vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 163 */			 if (movingobjectposition != null) {
/* 164 */				 vec3d1 = Vec3D.a().create(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
/*		 */			 }
/*		 */ 
/* 167 */			 Entity entity = null;
/* 168 */			 List list = this.world.getEntities(this, this.boundingBox.a(this.motX, this.motY, this.motZ).grow(1.0D, 1.0D, 1.0D));
/* 169 */			 double d0 = 0.0D;
/* 170 */			 Iterator iterator = list.iterator();
/*		 */ 
/* 174 */			 while (iterator.hasNext()) {
/* 175 */				 Entity entity1 = (Entity)iterator.next();
/*		 */ 
/* 177 */				 if ((entity1.L()) && ((entity1 != this.shooter) || (this.an >= 5))) {
/* 178 */					 float f1 = 0.3F;
/* 179 */					 AxisAlignedBB axisalignedbb1 = entity1.boundingBox.grow(f1, f1, f1);
/* 180 */					 MovingObjectPosition movingobjectposition1 = axisalignedbb1.a(vec3d, vec3d1);
/*		 */ 
/* 182 */					 if (movingobjectposition1 != null) {
/* 183 */						 double d1 = vec3d.distanceSquared(movingobjectposition1.pos);
/*		 */ 
/* 185 */						 if ((d1 < d0) || (d0 == 0.0D)) {
/* 186 */							 entity = entity1;
/* 187 */							 d0 = d1;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 193 */			 if (entity != null) {
/* 194 */				 movingobjectposition = new MovingObjectPosition(entity);
/*		 */			 }
/*		 */ 
/* 199 */			 if (movingobjectposition != null)
/*		 */			 {
/* 201 */				 Projectile projectile = (Projectile)getBukkitEntity();
/* 202 */				 ProjectileHitEvent phe = new ProjectileHitEvent(projectile);
/* 203 */				 this.world.getServer().getPluginManager().callEvent(phe);
/*		 */ 
/* 205 */				 if (movingobjectposition.entity != null) {
/* 206 */					 float f2 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
/* 207 */					 int l = MathHelper.f(f2 * this.damage);
/*		 */ 
/* 209 */					 if (g()) {
/* 210 */						 l += this.random.nextInt(l / 2 + 2);
/*		 */					 }
/*		 */ 
/* 213 */					 DamageSource damagesource = null;
/*		 */ 
/* 215 */					 if (this.shooter == null)
/* 216 */						 damagesource = DamageSource.arrow(this, this);
/*		 */					 else {
/* 218 */						 damagesource = DamageSource.arrow(this, this.shooter);
/*		 */					 }
/*		 */ 
/* 222 */					 if (movingobjectposition.entity.damageEntity(damagesource, l)) {
/* 223 */						 if ((isBurning()) && ((!(movingobjectposition.entity instanceof EntityPlayer)) || (this.world.pvpMode))) {
/* 224 */							 EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(getBukkitEntity(), entity.getBukkitEntity(), 5);
/* 225 */							 Bukkit.getPluginManager().callEvent(combustEvent);
/*		 */ 
/* 227 */							 if (!combustEvent.isCancelled()) {
/* 228 */								 movingobjectposition.entity.setOnFire(combustEvent.getDuration());
/*		 */							 }
/*		 */ 
/*		 */						 }
/*		 */ 
/* 234 */						 if ((movingobjectposition.entity instanceof EntityLiving)) {
/* 235 */							 ((EntityLiving)movingobjectposition.entity).bd += 1;
/* 236 */							 if (this.ap > 0) {
/* 237 */								 float f3 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 239 */								 if (f3 > 0.0F) {
/* 240 */									 movingobjectposition.entity.g(this.motX * this.ap * 0.6000000238418579D / f3, 0.1D, this.motZ * this.ap * 0.6000000238418579D / f3);
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */ 
/* 245 */						 this.world.makeSound(this, "random.bowhit", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
/* 246 */						 die();
/*		 */					 } else {
/* 248 */						 this.motX *= -0.1000000014901161D;
/* 249 */						 this.motY *= -0.1000000014901161D;
/* 250 */						 this.motZ *= -0.1000000014901161D;
/* 251 */						 this.yaw += 180.0F;
/* 252 */						 this.lastYaw += 180.0F;
/* 253 */						 this.an = 0;
/*		 */					 }
/*		 */				 } else {
/* 256 */					 this.d = movingobjectposition.b;
/* 257 */					 this.e = movingobjectposition.c;
/* 258 */					 this.f = movingobjectposition.d;
/* 259 */					 this.g = this.world.getTypeId(this.d, this.e, this.f);
/* 260 */					 this.h = this.world.getData(this.d, this.e, this.f);
/* 261 */					 this.motX = (float)(movingobjectposition.pos.a - this.locX);
/* 262 */					 this.motY = (float)(movingobjectposition.pos.b - this.locY);
/* 263 */					 this.motZ = (float)(movingobjectposition.pos.c - this.locZ);
/* 264 */					 float f2 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
/* 265 */					 this.locX -= this.motX / f2 * 0.0500000007450581D;
/* 266 */					 this.locY -= this.motY / f2 * 0.0500000007450581D;
/* 267 */					 this.locZ -= this.motZ / f2 * 0.0500000007450581D;
/* 268 */					 this.world.makeSound(this, "random.bowhit", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
/* 269 */					 this.inGround = true;
/* 270 */					 this.shake = 7;
/* 271 */					 d(false);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 275 */			 if (g()) {
/* 276 */				 for (int i1 = 0; i1 < 4; i1++) {
/* 277 */					 this.world.a("crit", this.locX + this.motX * i1 / 4.0D, this.locY + this.motY * i1 / 4.0D, this.locZ + this.motZ * i1 / 4.0D, -this.motX, -this.motY + 0.2D, -this.motZ);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 281 */			 this.locX += this.motX;
/* 282 */			 this.locY += this.motY;
/* 283 */			 this.locZ += this.motZ;
/* 284 */			 float f2 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 285 */			 this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D);
/*		 */ 
/* 287 */			 for (this.pitch = (float)(Math.atan2(this.motY, f2) * 180.0D / 3.141592741012573D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/* 291 */			 while (this.pitch - this.lastPitch >= 180.0F) {
/* 292 */				 this.lastPitch += 360.0F;
/*		 */			 }
/*		 */ 
/* 295 */			 while (this.yaw - this.lastYaw < -180.0F) {
/* 296 */				 this.lastYaw -= 360.0F;
/*		 */			 }
/*		 */ 
/* 299 */			 while (this.yaw - this.lastYaw >= 180.0F) {
/* 300 */				 this.lastYaw += 360.0F;
/*		 */			 }
/*		 */ 
/* 303 */			 this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
/* 304 */			 this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
/* 305 */			 float f4 = 0.99F;
/*		 */ 
/* 307 */			 float f1 = 0.05F;
/* 308 */			 if (H()) {
/* 309 */				 for (int j1 = 0; j1 < 4; j1++) {
/* 310 */					 float f5 = 0.25F;
/*		 */ 
/* 312 */					 this.world.a("bubble", this.locX - this.motX * f5, this.locY - this.motY * f5, this.locZ - this.motZ * f5, this.motX, this.motY, this.motZ);
/*		 */				 }
/*		 */ 
/* 315 */				 f4 = 0.8F;
/*		 */			 }
/*		 */ 
/* 318 */			 this.motX *= f4;
/* 319 */			 this.motY *= f4;
/* 320 */			 this.motZ *= f4;
/* 321 */			 this.motY -= f1;
/* 322 */			 setPosition(this.locX, this.locY, this.locZ);
/* 323 */			 D();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/* 328 */		 nbttagcompound.setShort("xTile", (short)this.d);
/* 329 */		 nbttagcompound.setShort("yTile", (short)this.e);
/* 330 */		 nbttagcompound.setShort("zTile", (short)this.f);
/* 331 */		 nbttagcompound.setByte("inTile", (byte)this.g);
/* 332 */		 nbttagcompound.setByte("inData", (byte)this.h);
/* 333 */		 nbttagcompound.setByte("shake", (byte)this.shake);
/* 334 */		 nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/* 335 */		 nbttagcompound.setByte("pickup", (byte)this.fromPlayer);
/* 336 */		 nbttagcompound.setDouble("damage", this.damage);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/* 340 */		 this.d = nbttagcompound.getShort("xTile");
/* 341 */		 this.e = nbttagcompound.getShort("yTile");
/* 342 */		 this.f = nbttagcompound.getShort("zTile");
/* 343 */		 this.g = (nbttagcompound.getByte("inTile") & 0xFF);
/* 344 */		 this.h = (nbttagcompound.getByte("inData") & 0xFF);
/* 345 */		 this.shake = (nbttagcompound.getByte("shake") & 0xFF);
/* 346 */		 this.inGround = (nbttagcompound.getByte("inGround") == 1);
/* 347 */		 if (nbttagcompound.hasKey("damage")) {
/* 348 */			 this.damage = nbttagcompound.getDouble("damage");
/*		 */		 }
/*		 */ 
/* 351 */		 if (nbttagcompound.hasKey("pickup"))
/* 352 */			 this.fromPlayer = nbttagcompound.getByte("pickup");
/* 353 */		 else if (nbttagcompound.hasKey("player"))
/* 354 */			 this.fromPlayer = (nbttagcompound.getBoolean("player") ? 1 : 0);
/*		 */	 }
/*		 */ 
/*		 */	 public void b_(EntityHuman entityhuman)
/*		 */	 {
/* 359 */		 if ((!this.world.isStatic) && (this.inGround) && (this.shake <= 0))
/*		 */		 {
/* 361 */			 ItemStack itemstack = new ItemStack(Item.ARROW);
/* 362 */			 if ((this.inGround) && (this.fromPlayer == 1) && (this.shake <= 0) && (entityhuman.inventory.canHold(itemstack) > 0)) {
/* 363 */				 EntityItem item = new EntityItem(this.world, this.locX, this.locY, this.locZ, itemstack);
/*		 */ 
/* 365 */				 PlayerPickupItemEvent event = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), new CraftItem(this.world.getServer(), this, item), 0);
/* 366 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 368 */				 if (event.isCancelled()) {
/* 369 */					 return;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 374 */			 boolean flag = (this.fromPlayer == 1) || ((this.fromPlayer == 2) && (entityhuman.abilities.canInstantlyBuild));
/*		 */ 
/* 376 */			 if ((this.fromPlayer == 1) && (!entityhuman.inventory.pickup(new ItemStack(Item.ARROW, 1)))) {
/* 377 */				 flag = false;
/*		 */			 }
/*		 */ 
/* 380 */			 if (flag) {
/* 381 */				 this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 382 */				 entityhuman.receive(this, 1);
/* 383 */				 die();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(double d0) {
/* 389 */		 this.damage = d0;
/*		 */	 }
/*		 */ 
/*		 */	 public double d() {
/* 393 */		 return this.damage;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i) {
/* 397 */		 this.ap = i;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean an() {
/* 401 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void d(boolean flag) {
/* 405 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 407 */		 if (flag)
/* 408 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x1)));
/*		 */		 else
/* 410 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean g()
/*		 */	 {
/* 415 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 417 */		 return (b0 & 0x1) != 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityArrow
 * JD-Core Version:		0.6.0
 */