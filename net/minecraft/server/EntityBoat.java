/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.Vehicle;
/*		 */ import org.bukkit.event.vehicle.VehicleCreateEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleDamageEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleUpdateEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntityBoat extends Entity
/*		 */ {
/*		 */	 private boolean a;
/*		 */	 private double b;
/*		 */	 private int c;
/*		 */	 private double d;
/*		 */	 private double e;
/*		 */	 private double f;
/*		 */	 private double g;
/*		 */	 private double h;
/*	27 */	 public double maxSpeed = 0.4D;
/*	28 */	 public double occupiedDeceleration = 0.2D;
/*	29 */	 public double unoccupiedDeceleration = -1.0D;
/*	30 */	 public boolean landBoats = false;
/*		 */ 
/*		 */	 public void collide(Entity entity)
/*		 */	 {
/*	34 */		 org.bukkit.entity.Entity hitEntity = entity == null ? null : entity.getBukkitEntity();
/*		 */ 
/*	36 */		 VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), hitEntity);
/*	37 */		 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	39 */		 if (event.isCancelled()) {
/*	40 */			 return;
/*		 */		 }
/*		 */ 
/*	43 */		 super.collide(entity);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityBoat(World world)
/*		 */	 {
/*	48 */		 super(world);
/*	49 */		 this.a = true;
/*	50 */		 this.b = 0.07000000000000001D;
/*	51 */		 this.m = true;
/*	52 */		 a(1.5F, 0.6F);
/*	53 */		 this.height = (this.length / 2.0F);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	57 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	61 */		 this.datawatcher.a(17, new Integer(0));
/*	62 */		 this.datawatcher.a(18, new Integer(1));
/*	63 */		 this.datawatcher.a(19, new Integer(0));
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB g(Entity entity) {
/*	67 */		 return entity.boundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB E() {
/*	71 */		 return this.boundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean M() {
/*	75 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityBoat(World world, double d0, double d1, double d2) {
/*	79 */		 this(world);
/*	80 */		 setPosition(d0, d1 + this.height, d2);
/*	81 */		 this.motX = 0.0D;
/*	82 */		 this.motY = 0.0D;
/*	83 */		 this.motZ = 0.0D;
/*	84 */		 this.lastX = d0;
/*	85 */		 this.lastY = d1;
/*	86 */		 this.lastZ = d2;
/*		 */ 
/*	88 */		 this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle)getBukkitEntity()));
/*		 */	 }
/*		 */ 
/*		 */	 public double X() {
/*	92 */		 return this.length * 0.0D - 0.300000011920929D;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	96 */		 if ((!this.world.isStatic) && (!this.dead))
/*		 */		 {
/*	98 */			 Vehicle vehicle = (Vehicle)getBukkitEntity();
/*	99 */			 org.bukkit.entity.Entity attacker = damagesource.getEntity() == null ? null : damagesource.getEntity().getBukkitEntity();
/*		 */ 
/* 101 */			 VehicleDamageEvent event = new VehicleDamageEvent(vehicle, attacker, i);
/* 102 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 104 */			 if (event.isCancelled()) {
/* 105 */				 return true;
/*		 */			 }
/*		 */ 
/* 110 */			 c(-i());
/* 111 */			 b(10);
/* 112 */			 setDamage(getDamage() + i * 10);
/* 113 */			 K();
/* 114 */			 if (((damagesource.getEntity() instanceof EntityHuman)) && (((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild)) {
/* 115 */				 setDamage(100);
/*		 */			 }
/*		 */ 
/* 118 */			 if (getDamage() > 40)
/*		 */			 {
/* 120 */				 VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, attacker);
/* 121 */				 this.world.getServer().getPluginManager().callEvent(destroyEvent);
/*		 */ 
/* 123 */				 if (destroyEvent.isCancelled()) {
/* 124 */					 setDamage(40);
/* 125 */					 return true;
/*		 */				 }
/*		 */ 
/* 129 */				 if (this.passenger != null) {
/* 130 */					 this.passenger.mount(this);
/*		 */				 }
/*		 */ 
/* 133 */				 a(Item.BOAT.id, 1, 0.0F);
/* 134 */				 die();
/*		 */			 }
/*		 */ 
/* 137 */			 return true;
/*		 */		 }
/* 139 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean L()
/*		 */	 {
/* 144 */		 return !this.dead;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_()
/*		 */	 {
/* 149 */		 double prevX = this.locX;
/* 150 */		 double prevY = this.locY;
/* 151 */		 double prevZ = this.locZ;
/* 152 */		 float prevYaw = this.yaw;
/* 153 */		 float prevPitch = this.pitch;
/*		 */ 
/* 156 */		 super.h_();
/* 157 */		 if (h() > 0) {
/* 158 */			 b(h() - 1);
/*		 */		 }
/*		 */ 
/* 161 */		 if (getDamage() > 0) {
/* 162 */			 setDamage(getDamage() - 1);
/*		 */		 }
/*		 */ 
/* 165 */		 this.lastX = this.locX;
/* 166 */		 this.lastY = this.locY;
/* 167 */		 this.lastZ = this.locZ;
/* 168 */		 byte b0 = 5;
/* 169 */		 double d0 = 0.0D;
/*		 */ 
/* 171 */		 for (int i = 0; i < b0; i++) {
/* 172 */			 double d1 = this.boundingBox.b + (this.boundingBox.e - this.boundingBox.b) * (i + 0) / b0 - 0.125D;
/* 173 */			 double d2 = this.boundingBox.b + (this.boundingBox.e - this.boundingBox.b) * (i + 1) / b0 - 0.125D;
/* 174 */			 AxisAlignedBB axisalignedbb = AxisAlignedBB.a().a(this.boundingBox.a, d1, this.boundingBox.c, this.boundingBox.d, d2, this.boundingBox.f);
/*		 */ 
/* 176 */			 if (this.world.b(axisalignedbb, Material.WATER)) {
/* 177 */				 d0 += 1.0D / b0;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 181 */		 double d3 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 185 */		 if (d3 > 0.2625D) {
/* 186 */			 double d4 = Math.cos(this.yaw * 3.141592653589793D / 180.0D);
/* 187 */			 double d5 = Math.sin(this.yaw * 3.141592653589793D / 180.0D);
/*		 */ 
/* 189 */			 for (int j = 0; j < 1.0D + d3 * 60.0D; j++) {
/* 190 */				 double d6 = this.random.nextFloat() * 2.0F - 1.0F;
/* 191 */				 double d7 = (this.random.nextInt(2) * 2 - 1) * 0.7D;
/*		 */ 
/* 195 */				 if (this.random.nextBoolean()) {
/* 196 */					 double d8 = this.locX - d4 * d6 * 0.8D + d5 * d7;
/* 197 */					 double d9 = this.locZ - d5 * d6 * 0.8D - d4 * d7;
/* 198 */					 this.world.a("splash", d8, this.locY - 0.125D, d9, this.motX, this.motY, this.motZ);
/*		 */				 } else {
/* 200 */					 double d8 = this.locX + d4 + d5 * d6 * 0.7D;
/* 201 */					 double d9 = this.locZ + d5 - d4 * d6 * 0.7D;
/* 202 */					 this.world.a("splash", d8, this.locY - 0.125D, d9, this.motX, this.motY, this.motZ);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 210 */		 if ((this.world.isStatic) && (this.a)) {
/* 211 */			 if (this.c > 0) {
/* 212 */				 double d4 = this.locX + (this.d - this.locX) / this.c;
/* 213 */				 double d5 = this.locY + (this.e - this.locY) / this.c;
/* 214 */				 double d10 = this.locZ + (this.f - this.locZ) / this.c;
/* 215 */				 double d11 = MathHelper.g(this.g - this.yaw);
/* 216 */				 this.yaw = (float)(this.yaw + d11 / this.c);
/* 217 */				 this.pitch = (float)(this.pitch + (this.h - this.pitch) / this.c);
/* 218 */				 this.c -= 1;
/* 219 */				 setPosition(d4, d5, d10);
/* 220 */				 b(this.yaw, this.pitch);
/*		 */			 } else {
/* 222 */				 double d4 = this.locX + this.motX;
/* 223 */				 double d5 = this.locY + this.motY;
/* 224 */				 double d10 = this.locZ + this.motZ;
/* 225 */				 setPosition(d4, d5, d10);
/* 226 */				 if (this.onGround) {
/* 227 */					 this.motX *= 0.5D;
/* 228 */					 this.motY *= 0.5D;
/* 229 */					 this.motZ *= 0.5D;
/*		 */				 }
/*		 */ 
/* 232 */				 this.motX *= 0.9900000095367432D;
/* 233 */				 this.motY *= 0.949999988079071D;
/* 234 */				 this.motZ *= 0.9900000095367432D;
/*		 */			 }
/*		 */		 } else {
/* 237 */			 if (d0 < 1.0D) {
/* 238 */				 double d4 = d0 * 2.0D - 1.0D;
/* 239 */				 this.motY += 0.03999999910593033D * d4;
/*		 */			 } else {
/* 241 */				 if (this.motY < 0.0D) {
/* 242 */					 this.motY /= 2.0D;
/*		 */				 }
/*		 */ 
/* 245 */				 this.motY += 0.007000000216066837D;
/*		 */			 }
/*		 */ 
/* 248 */			 if (this.passenger != null) {
/* 249 */				 this.motX += this.passenger.motX * this.b;
/* 250 */				 this.motZ += this.passenger.motZ * this.b;
/*		 */			 }
/* 253 */			 else if (this.unoccupiedDeceleration >= 0.0D) {
/* 254 */				 this.motX *= this.unoccupiedDeceleration;
/* 255 */				 this.motZ *= this.unoccupiedDeceleration;
/*		 */ 
/* 257 */				 if (this.motX <= 1.E-005D) {
/* 258 */					 this.motX = 0.0D;
/*		 */				 }
/* 260 */				 if (this.motZ <= 1.E-005D) {
/* 261 */					 this.motZ = 0.0D;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 266 */			 double d4 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 267 */			 if (d4 > 0.35D) {
/* 268 */				 double d5 = 0.35D / d4;
/* 269 */				 this.motX *= d5;
/* 270 */				 this.motZ *= d5;
/* 271 */				 d4 = 0.35D;
/*		 */			 }
/*		 */ 
/* 274 */			 if ((d4 > d3) && (this.b < 0.35D)) {
/* 275 */				 this.b += (0.35D - this.b) / 35.0D;
/* 276 */				 if (this.b > 0.35D)
/* 277 */					 this.b = 0.35D;
/*		 */			 }
/*		 */			 else {
/* 280 */				 this.b -= (this.b - 0.07000000000000001D) / 35.0D;
/* 281 */				 if (this.b < 0.07000000000000001D) {
/* 282 */					 this.b = 0.07000000000000001D;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 286 */			 if ((this.onGround) && (!this.landBoats)) {
/* 287 */				 this.motX *= 0.5D;
/* 288 */				 this.motY *= 0.5D;
/* 289 */				 this.motZ *= 0.5D;
/*		 */			 }
/*		 */ 
/* 292 */			 move(this.motX, this.motY, this.motZ);
/* 293 */			 if ((this.positionChanged) && (d3 > 0.2D)) {
/* 294 */				 if (!this.world.isStatic)
/*		 */				 {
/* 296 */					 Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 297 */					 VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, null);
/* 298 */					 this.world.getServer().getPluginManager().callEvent(destroyEvent);
/* 299 */					 if (!destroyEvent.isCancelled()) {
/* 300 */						 die();
/*		 */ 
/* 304 */						 for (int k = 0; k < 3; k++) {
/* 305 */							 a(Block.WOOD.id, 1, 0.0F);
/*		 */						 }
/*		 */ 
/* 308 */						 for (k = 0; k < 2; k++)
/* 309 */							 a(Item.STICK.id, 1, 0.0F);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */			 else
/*		 */			 {
/* 315 */				 this.motX *= 0.9900000095367432D;
/* 316 */				 this.motY *= 0.949999988079071D;
/* 317 */				 this.motZ *= 0.9900000095367432D;
/*		 */			 }
/*		 */ 
/* 320 */			 this.pitch = 0.0F;
/* 321 */			 double d5 = this.yaw;
/* 322 */			 double d10 = this.lastX - this.locX;
/* 323 */			 double d11 = this.lastZ - this.locZ;
/* 324 */			 if (d10 * d10 + d11 * d11 > 0.001D) {
/* 325 */				 d5 = (float)(Math.atan2(d11, d10) * 180.0D / 3.141592653589793D);
/*		 */			 }
/*		 */ 
/* 328 */			 double d12 = MathHelper.g(d5 - this.yaw);
/*		 */ 
/* 330 */			 if (d12 > 20.0D) {
/* 331 */				 d12 = 20.0D;
/*		 */			 }
/*		 */ 
/* 334 */			 if (d12 < -20.0D) {
/* 335 */				 d12 = -20.0D;
/*		 */			 }
/*		 */ 
/* 338 */			 this.yaw = (float)(this.yaw + d12);
/* 339 */			 b(this.yaw, this.pitch);
/*		 */ 
/* 342 */			 Server server = this.world.getServer();
/* 343 */			 org.bukkit.World bworld = this.world.getWorld();
/*		 */ 
/* 345 */			 Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
/* 346 */			 Location to = new Location(bworld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 347 */			 Vehicle vehicle = (Vehicle)getBukkitEntity();
/*		 */ 
/* 349 */			 server.getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));
/*		 */ 
/* 351 */			 if (!from.equals(to)) {
/* 352 */				 VehicleMoveEvent event = new VehicleMoveEvent(vehicle, from, to);
/* 353 */				 server.getPluginManager().callEvent(event);
/*		 */			 }
/*		 */ 
/* 357 */			 if (!this.world.isStatic) {
/* 358 */				 List list = this.world.getEntities(this, this.boundingBox.grow(0.2000000029802322D, 0.0D, 0.2000000029802322D));
/*		 */ 
/* 360 */				 if ((list != null) && (!list.isEmpty())) {
/* 361 */					 Iterator iterator = list.iterator();
/*		 */ 
/* 363 */					 while (iterator.hasNext()) {
/* 364 */						 Entity entity = (Entity)iterator.next();
/*		 */ 
/* 366 */						 if ((entity != this.passenger) && (entity.M()) && ((entity instanceof EntityBoat))) {
/* 367 */							 entity.collide(this);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 372 */				 for (int l = 0; l < 4; l++) {
/* 373 */					 int i1 = MathHelper.floor(this.locX + (l % 2 - 0.5D) * 0.8D);
/* 374 */					 int j1 = MathHelper.floor(this.locZ + (l / 2 - 0.5D) * 0.8D);
/*		 */ 
/* 376 */					 for (int k1 = 0; k1 < 2; k1++) {
/* 377 */						 int l1 = MathHelper.floor(this.locY) + k1;
/* 378 */						 int i2 = this.world.getTypeId(i1, l1, j1);
/* 379 */						 int j2 = this.world.getData(i1, l1, j1);
/*		 */ 
/* 381 */						 if (i2 == Block.SNOW.id) {
/* 382 */							 this.world.setTypeId(i1, l1, j1, 0);
/* 383 */						 } else if (i2 == Block.WATER_LILY.id) {
/* 384 */							 Block.WATER_LILY.dropNaturally(this.world, i1, l1, j1, j2, 0.3F, 0);
/* 385 */							 this.world.setTypeId(i1, l1, j1, 0);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 390 */				 if ((this.passenger != null) && (this.passenger.dead)) {
/* 391 */					 this.passenger.vehicle = null;
/* 392 */					 this.passenger = null;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void V() {
/* 399 */		 if (this.passenger != null) {
/* 400 */			 double d0 = Math.cos(this.yaw * 3.141592653589793D / 180.0D) * 0.4D;
/* 401 */			 double d1 = Math.sin(this.yaw * 3.141592653589793D / 180.0D) * 0.4D;
/*		 */ 
/* 403 */			 this.passenger.setPosition(this.locX + d0, this.locY + X() + this.passenger.W(), this.locZ + d1);
/*		 */		 }
/*		 */	 }
/*		 */	 protected void b(NBTTagCompound nbttagcompound) {
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(NBTTagCompound nbttagcompound) {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/* 412 */		 if ((this.passenger != null) && ((this.passenger instanceof EntityHuman)) && (this.passenger != entityhuman)) {
/* 413 */			 return true;
/*		 */		 }
/* 415 */		 if (!this.world.isStatic) {
/* 416 */			 entityhuman.mount(this);
/*		 */		 }
/*		 */ 
/* 419 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void setDamage(int i)
/*		 */	 {
/* 424 */		 this.datawatcher.watch(19, Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public int getDamage() {
/* 428 */		 return this.datawatcher.getInt(19);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int i) {
/* 432 */		 this.datawatcher.watch(17, Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public int h() {
/* 436 */		 return this.datawatcher.getInt(17);
/*		 */	 }
/*		 */ 
/*		 */	 public void c(int i) {
/* 440 */		 this.datawatcher.watch(18, Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public int i() {
/* 444 */		 return this.datawatcher.getInt(18);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityBoat
 * JD-Core Version:		0.6.0
 */