/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.entity.CraftHumanEntity;
/*		 */ import org.bukkit.entity.HumanEntity;
/*		 */ import org.bukkit.entity.Vehicle;
/*		 */ import org.bukkit.event.vehicle.VehicleCreateEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleDamageEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*		 */ import org.bukkit.event.vehicle.VehicleUpdateEvent;
/*		 */ import org.bukkit.inventory.InventoryHolder;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ import org.bukkit.util.Vector;
/*		 */ 
/*		 */ public class EntityMinecart extends Entity
/*		 */	 implements IInventory
/*		 */ {
/*		 */	 private ItemStack[] items;
/*		 */	 private int e;
/*		 */	 private boolean f;
/*		 */	 public int type;
/*		 */	 public double b;
/*		 */	 public double c;
/*	25 */	 private static final int[][][] matrix = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
/*		 */	 private int h;
/*		 */	 private double i;
/*		 */	 private double j;
/*		 */	 private double an;
/*		 */	 private double ao;
/*		 */	 private double ap;
/*	34 */	 public boolean slowWhenEmpty = true;
/*	35 */	 private double derailedX = 0.5D;
/*	36 */	 private double derailedY = 0.5D;
/*	37 */	 private double derailedZ = 0.5D;
/*	38 */	 private double flyingX = 0.95D;
/*	39 */	 private double flyingY = 0.95D;
/*	40 */	 private double flyingZ = 0.95D;
/*	41 */	 public double maxSpeed = 0.4D;
/*	42 */	 public List<HumanEntity> transaction = new ArrayList();
/*	43 */	 private int maxStack = 64;
/*		 */ 
/*		 */	 public ItemStack[] getContents() {
/*	46 */		 return this.items;
/*		 */	 }
/*		 */ 
/*		 */	 public void onOpen(CraftHumanEntity who) {
/*	50 */		 this.transaction.add(who);
/*		 */	 }
/*		 */ 
/*		 */	 public void onClose(CraftHumanEntity who) {
/*	54 */		 this.transaction.remove(who);
/*		 */	 }
/*		 */ 
/*		 */	 public List<HumanEntity> getViewers() {
/*	58 */		 return this.transaction;
/*		 */	 }
/*		 */ 
/*		 */	 public InventoryHolder getOwner() {
/*	62 */		 org.bukkit.entity.Entity cart = getBukkitEntity();
/*	63 */		 if ((cart instanceof InventoryHolder)) return (InventoryHolder)cart;
/*	64 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setMaxStackSize(int size) {
/*	68 */		 this.maxStack = size;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityMinecart(World world)
/*		 */	 {
/*	73 */		 super(world);
/*	74 */		 this.items = new ItemStack[27];
/*	75 */		 this.e = 0;
/*	76 */		 this.f = false;
/*	77 */		 this.m = true;
/*	78 */		 a(0.98F, 0.7F);
/*	79 */		 this.height = (this.length / 2.0F);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	83 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	87 */		 this.datawatcher.a(16, new Byte(0));
/*	88 */		 this.datawatcher.a(17, new Integer(0));
/*	89 */		 this.datawatcher.a(18, new Integer(1));
/*	90 */		 this.datawatcher.a(19, new Integer(0));
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB g(Entity entity) {
/*	94 */		 return entity.boundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB E() {
/*	98 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean M() {
/* 102 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityMinecart(World world, double d0, double d1, double d2, int i) {
/* 106 */		 this(world);
/* 107 */		 setPosition(d0, d1 + this.height, d2);
/* 108 */		 this.motX = 0.0D;
/* 109 */		 this.motY = 0.0D;
/* 110 */		 this.motZ = 0.0D;
/* 111 */		 this.lastX = d0;
/* 112 */		 this.lastY = d1;
/* 113 */		 this.lastZ = d2;
/* 114 */		 this.type = i;
/*		 */ 
/* 116 */		 this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle)getBukkitEntity()));
/*		 */	 }
/*		 */ 
/*		 */	 public double X() {
/* 120 */		 return this.length * 0.0D - 0.300000011920929D;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/* 124 */		 if ((!this.world.isStatic) && (!this.dead))
/*		 */		 {
/* 126 */			 Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 127 */			 org.bukkit.entity.Entity passenger = damagesource.getEntity() == null ? null : damagesource.getEntity().getBukkitEntity();
/*		 */ 
/* 129 */			 VehicleDamageEvent event = new VehicleDamageEvent(vehicle, passenger, i);
/* 130 */			 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 132 */			 if (event.isCancelled()) {
/* 133 */				 return true;
/*		 */			 }
/*		 */ 
/* 136 */			 i = event.getDamage();
/*		 */ 
/* 139 */			 i(-k());
/* 140 */			 h(10);
/* 141 */			 K();
/* 142 */			 setDamage(getDamage() + i * 10);
/* 143 */			 if (((damagesource.getEntity() instanceof EntityHuman)) && (((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild)) {
/* 144 */				 setDamage(100);
/*		 */			 }
/*		 */ 
/* 147 */			 if (getDamage() > 40) {
/* 148 */				 if (this.passenger != null) {
/* 149 */					 this.passenger.mount(this);
/*		 */				 }
/*		 */ 
/* 153 */				 VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, passenger);
/* 154 */				 this.world.getServer().getPluginManager().callEvent(destroyEvent);
/*		 */ 
/* 156 */				 if (destroyEvent.isCancelled()) {
/* 157 */					 setDamage(40);
/* 158 */					 return true;
/*		 */				 }
/*		 */ 
/* 162 */				 die();
/* 163 */				 a(Item.MINECART.id, 1, 0.0F);
/* 164 */				 if (this.type == 1) {
/* 165 */					 EntityMinecart entityminecart = this;
/*		 */ 
/* 167 */					 for (int j = 0; j < entityminecart.getSize(); j++) {
/* 168 */						 ItemStack itemstack = entityminecart.getItem(j);
/*		 */ 
/* 170 */						 if (itemstack != null) {
/* 171 */							 float f = this.random.nextFloat() * 0.8F + 0.1F;
/* 172 */							 float f1 = this.random.nextFloat() * 0.8F + 0.1F;
/* 173 */							 float f2 = this.random.nextFloat() * 0.8F + 0.1F;
/*		 */ 
/* 175 */							 while (itemstack.count > 0) {
/* 176 */								 int k = this.random.nextInt(21) + 10;
/*		 */ 
/* 178 */								 if (k > itemstack.count) {
/* 179 */									 k = itemstack.count;
/*		 */								 }
/*		 */ 
/* 182 */								 itemstack.count -= k;
/*		 */ 
/* 184 */								 EntityItem entityitem = new EntityItem(this.world, this.locX + f, this.locY + f1, this.locZ + f2, new ItemStack(itemstack.id, k, itemstack.getData(), itemstack.getEnchantments()));
/* 185 */								 float f3 = 0.05F;
/*		 */ 
/* 187 */								 entityitem.motX = ((float)this.random.nextGaussian() * f3);
/* 188 */								 entityitem.motY = ((float)this.random.nextGaussian() * f3 + 0.2F);
/* 189 */								 entityitem.motZ = ((float)this.random.nextGaussian() * f3);
/* 190 */								 this.world.addEntity(entityitem);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 195 */					 a(Block.CHEST.id, 1, 0.0F);
/* 196 */				 } else if (this.type == 2) {
/* 197 */					 a(Block.FURNACE.id, 1, 0.0F);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 201 */			 return true;
/*		 */		 }
/* 203 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean L()
/*		 */	 {
/* 208 */		 return !this.dead;
/*		 */	 }
/*		 */ 
/*		 */	 public void die() {
/* 212 */		 for (int i = 0; i < getSize(); i++) {
/* 213 */			 ItemStack itemstack = getItem(i);
/*		 */ 
/* 215 */			 if (itemstack != null) {
/* 216 */				 float f = this.random.nextFloat() * 0.8F + 0.1F;
/* 217 */				 float f1 = this.random.nextFloat() * 0.8F + 0.1F;
/* 218 */				 float f2 = this.random.nextFloat() * 0.8F + 0.1F;
/*		 */ 
/* 220 */				 while (itemstack.count > 0) {
/* 221 */					 int j = this.random.nextInt(21) + 10;
/*		 */ 
/* 223 */					 if (j > itemstack.count) {
/* 224 */						 j = itemstack.count;
/*		 */					 }
/*		 */ 
/* 227 */					 itemstack.count -= j;
/* 228 */					 EntityItem entityitem = new EntityItem(this.world, this.locX + f, this.locY + f1, this.locZ + f2, new ItemStack(itemstack.id, j, itemstack.getData()));
/*		 */ 
/* 230 */					 if (itemstack.hasTag()) {
/* 231 */						 entityitem.itemStack.setTag((NBTTagCompound)itemstack.getTag().clone());
/*		 */					 }
/*		 */ 
/* 234 */					 float f3 = 0.05F;
/*		 */ 
/* 236 */					 entityitem.motX = ((float)this.random.nextGaussian() * f3);
/* 237 */					 entityitem.motY = ((float)this.random.nextGaussian() * f3 + 0.2F);
/* 238 */					 entityitem.motZ = ((float)this.random.nextGaussian() * f3);
/* 239 */					 this.world.addEntity(entityitem);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 244 */		 super.die();
/*		 */	 }
/*		 */ 
/*		 */	 public void h_()
/*		 */	 {
/* 249 */		 double prevX = this.locX;
/* 250 */		 double prevY = this.locY;
/* 251 */		 double prevZ = this.locZ;
/* 252 */		 float prevYaw = this.yaw;
/* 253 */		 float prevPitch = this.pitch;
/*		 */ 
/* 256 */		 if (j() > 0) {
/* 257 */			 h(j() - 1);
/*		 */		 }
/*		 */ 
/* 260 */		 if (getDamage() > 0) {
/* 261 */			 setDamage(getDamage() - 1);
/*		 */		 }
/*		 */ 
/* 264 */		 if (this.locY < -64.0D) {
/* 265 */			 C();
/*		 */		 }
/*		 */ 
/* 268 */		 if ((h()) && (this.random.nextInt(4) == 0)) {
/* 269 */			 this.world.a("largesmoke", this.locX, this.locY + 0.8D, this.locZ, 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*		 */ 
/* 272 */		 if (this.world.isStatic) {
/* 273 */			 if (this.h > 0) {
/* 274 */				 double d0 = this.locX + (this.i - this.locX) / this.h;
/* 275 */				 double d1 = this.locY + (this.j - this.locY) / this.h;
/* 276 */				 double d2 = this.locZ + (this.an - this.locZ) / this.h;
/* 277 */				 double d3 = MathHelper.g(this.ao - this.yaw);
/*		 */ 
/* 279 */				 this.yaw = (float)(this.yaw + d3 / this.h);
/* 280 */				 this.pitch = (float)(this.pitch + (this.ap - this.pitch) / this.h);
/* 281 */				 this.h -= 1;
/* 282 */				 setPosition(d0, d1, d2);
/* 283 */				 b(this.yaw, this.pitch);
/*		 */			 } else {
/* 285 */				 setPosition(this.locX, this.locY, this.locZ);
/* 286 */				 b(this.yaw, this.pitch);
/*		 */			 }
/*		 */		 } else {
/* 289 */			 this.lastX = this.locX;
/* 290 */			 this.lastY = this.locY;
/* 291 */			 this.lastZ = this.locZ;
/* 292 */			 this.motY -= 0.03999999910593033D;
/* 293 */			 int i = MathHelper.floor(this.locX);
/* 294 */			 int j = MathHelper.floor(this.locY);
/* 295 */			 int k = MathHelper.floor(this.locZ);
/*		 */ 
/* 297 */			 if (BlockMinecartTrack.d_(this.world, i, j - 1, k)) {
/* 298 */				 j--;
/*		 */			 }
/*		 */ 
/* 302 */			 double d4 = this.maxSpeed;
/* 303 */			 double d5 = 0.0078125D;
/* 304 */			 int l = this.world.getTypeId(i, j, k);
/*		 */ 
/* 306 */			 if (BlockMinecartTrack.d(l)) {
/* 307 */				 Vec3D vec3d = a(this.locX, this.locY, this.locZ);
/* 308 */				 int i1 = this.world.getData(i, j, k);
/*		 */ 
/* 310 */				 this.locY = j;
/* 311 */				 boolean flag = false;
/* 312 */				 boolean flag1 = false;
/*		 */ 
/* 314 */				 if (l == Block.GOLDEN_RAIL.id) {
/* 315 */					 flag = (i1 & 0x8) != 0;
/* 316 */					 flag1 = !flag;
/*		 */				 }
/*		 */ 
/* 319 */				 if (((BlockMinecartTrack)Block.byId[l]).n()) {
/* 320 */					 i1 &= 7;
/*		 */				 }
/*		 */ 
/* 323 */				 if ((i1 >= 2) && (i1 <= 5)) {
/* 324 */					 this.locY = (j + 1);
/*		 */				 }
/*		 */ 
/* 327 */				 if (i1 == 2) {
/* 328 */					 this.motX -= d5;
/*		 */				 }
/*		 */ 
/* 331 */				 if (i1 == 3) {
/* 332 */					 this.motX += d5;
/*		 */				 }
/*		 */ 
/* 335 */				 if (i1 == 4) {
/* 336 */					 this.motZ += d5;
/*		 */				 }
/*		 */ 
/* 339 */				 if (i1 == 5) {
/* 340 */					 this.motZ -= d5;
/*		 */				 }
/*		 */ 
/* 343 */				 int[][] aint = matrix[i1];
/* 344 */				 double d6 = aint[1][0] - aint[0][0];
/* 345 */				 double d7 = aint[1][2] - aint[0][2];
/* 346 */				 double d8 = Math.sqrt(d6 * d6 + d7 * d7);
/* 347 */				 double d9 = this.motX * d6 + this.motZ * d7;
/*		 */ 
/* 349 */				 if (d9 < 0.0D) {
/* 350 */					 d6 = -d6;
/* 351 */					 d7 = -d7;
/*		 */				 }
/*		 */ 
/* 354 */				 double d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*		 */ 
/* 356 */				 this.motX = (d10 * d6 / d8);
/* 357 */				 this.motZ = (d10 * d7 / d8);
/*		 */ 
/* 361 */				 if (this.passenger != null) {
/* 362 */					 double d12 = this.passenger.motX * this.passenger.motX + this.passenger.motZ * this.passenger.motZ;
/* 363 */					 double d11 = this.motX * this.motX + this.motZ * this.motZ;
/* 364 */					 if ((d12 > 0.0001D) && (d11 < 0.01D)) {
/* 365 */						 this.motX += this.passenger.motX * 0.1D;
/* 366 */						 this.motZ += this.passenger.motZ * 0.1D;
/* 367 */						 flag1 = false;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 371 */				 if (flag1) {
/* 372 */					 double d12 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 373 */					 if (d12 < 0.03D) {
/* 374 */						 this.motX *= 0.0D;
/* 375 */						 this.motY *= 0.0D;
/* 376 */						 this.motZ *= 0.0D;
/*		 */					 } else {
/* 378 */						 this.motX *= 0.5D;
/* 379 */						 this.motY *= 0.0D;
/* 380 */						 this.motZ *= 0.5D;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 384 */				 double d12 = 0.0D;
/* 385 */				 double d11 = i + 0.5D + aint[0][0] * 0.5D;
/* 386 */				 double d13 = k + 0.5D + aint[0][2] * 0.5D;
/* 387 */				 double d14 = i + 0.5D + aint[1][0] * 0.5D;
/* 388 */				 double d15 = k + 0.5D + aint[1][2] * 0.5D;
/*		 */ 
/* 390 */				 d6 = d14 - d11;
/* 391 */				 d7 = d15 - d13;
/*		 */ 
/* 395 */				 if (d6 == 0.0D) {
/* 396 */					 this.locX = (i + 0.5D);
/* 397 */					 d12 = this.locZ - k;
/* 398 */				 } else if (d7 == 0.0D) {
/* 399 */					 this.locZ = (k + 0.5D);
/* 400 */					 d12 = this.locX - i;
/*		 */				 } else {
/* 402 */					 double d16 = this.locX - d11;
/* 403 */					 double d17 = this.locZ - d13;
/* 404 */					 d12 = (d16 * d6 + d17 * d7) * 2.0D;
/*		 */				 }
/*		 */ 
/* 407 */				 this.locX = (d11 + d6 * d12);
/* 408 */				 this.locZ = (d13 + d7 * d12);
/* 409 */				 setPosition(this.locX, this.locY + this.height, this.locZ);
/* 410 */				 double d16 = this.motX;
/* 411 */				 double d17 = this.motZ;
/* 412 */				 if (this.passenger != null) {
/* 413 */					 d16 *= 0.75D;
/* 414 */					 d17 *= 0.75D;
/*		 */				 }
/*		 */ 
/* 417 */				 if (d16 < -d4) {
/* 418 */					 d16 = -d4;
/*		 */				 }
/*		 */ 
/* 421 */				 if (d16 > d4) {
/* 422 */					 d16 = d4;
/*		 */				 }
/*		 */ 
/* 425 */				 if (d17 < -d4) {
/* 426 */					 d17 = -d4;
/*		 */				 }
/*		 */ 
/* 429 */				 if (d17 > d4) {
/* 430 */					 d17 = d4;
/*		 */				 }
/*		 */ 
/* 433 */				 move(d16, 0.0D, d17);
/* 434 */				 if ((aint[0][1] != 0) && (MathHelper.floor(this.locX) - i == aint[0][0]) && (MathHelper.floor(this.locZ) - k == aint[0][2]))
/* 435 */					 setPosition(this.locX, this.locY + aint[0][1], this.locZ);
/* 436 */				 else if ((aint[1][1] != 0) && (MathHelper.floor(this.locX) - i == aint[1][0]) && (MathHelper.floor(this.locZ) - k == aint[1][2])) {
/* 437 */					 setPosition(this.locX, this.locY + aint[1][1], this.locZ);
/*		 */				 }
/*		 */ 
/* 441 */				 if ((this.passenger != null) || (!this.slowWhenEmpty)) {
/* 442 */					 this.motX *= 0.996999979019165D;
/* 443 */					 this.motY *= 0.0D;
/* 444 */					 this.motZ *= 0.996999979019165D;
/*		 */				 } else {
/* 446 */					 if (this.type == 2) {
/* 447 */						 double d18 = this.b * this.b + this.c * this.c;
/*		 */ 
/* 449 */						 if (d18 > 0.0001D) {
/* 450 */							 d18 = MathHelper.sqrt(d18);
/* 451 */							 this.b /= d18;
/* 452 */							 this.c /= d18;
/* 453 */							 double d19 = 0.04D;
/*		 */ 
/* 455 */							 this.motX *= 0.800000011920929D;
/* 456 */							 this.motY *= 0.0D;
/* 457 */							 this.motZ *= 0.800000011920929D;
/* 458 */							 this.motX += this.b * d19;
/* 459 */							 this.motZ += this.c * d19;
/*		 */						 } else {
/* 461 */							 this.motX *= 0.8999999761581421D;
/* 462 */							 this.motY *= 0.0D;
/* 463 */							 this.motZ *= 0.8999999761581421D;
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 467 */					 this.motX *= 0.9599999785423279D;
/* 468 */					 this.motY *= 0.0D;
/* 469 */					 this.motZ *= 0.9599999785423279D;
/*		 */				 }
/*		 */ 
/* 472 */				 Vec3D vec3d1 = a(this.locX, this.locY, this.locZ);
/*		 */ 
/* 474 */				 if ((vec3d1 != null) && (vec3d != null)) {
/* 475 */					 double d20 = (vec3d.b - vec3d1.b) * 0.05D;
/*		 */ 
/* 477 */					 d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 478 */					 if (d10 > 0.0D) {
/* 479 */						 this.motX = (this.motX / d10 * (d10 + d20));
/* 480 */						 this.motZ = (this.motZ / d10 * (d10 + d20));
/*		 */					 }
/*		 */ 
/* 483 */					 setPosition(this.locX, vec3d1.b, this.locZ);
/*		 */				 }
/*		 */ 
/* 486 */				 int j1 = MathHelper.floor(this.locX);
/* 487 */				 int k1 = MathHelper.floor(this.locZ);
/*		 */ 
/* 489 */				 if ((j1 != i) || (k1 != k)) {
/* 490 */					 d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 491 */					 this.motX = (d10 * (j1 - i));
/* 492 */					 this.motZ = (d10 * (k1 - k));
/*		 */				 }
/*		 */ 
/* 497 */				 if (this.type == 2) {
/* 498 */					 double d21 = this.b * this.b + this.c * this.c;
/* 499 */					 if ((d21 > 0.0001D) && (this.motX * this.motX + this.motZ * this.motZ > 0.001D)) {
/* 500 */						 d21 = MathHelper.sqrt(d21);
/* 501 */						 this.b /= d21;
/* 502 */						 this.c /= d21;
/* 503 */						 if (this.b * this.motX + this.c * this.motZ < 0.0D) {
/* 504 */							 this.b = 0.0D;
/* 505 */							 this.c = 0.0D;
/*		 */						 } else {
/* 507 */							 this.b = this.motX;
/* 508 */							 this.c = this.motZ;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 513 */				 if (flag) {
/* 514 */					 double d21 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 515 */					 if (d21 > 0.01D) {
/* 516 */						 double d22 = 0.06D;
/*		 */ 
/* 518 */						 this.motX += this.motX / d21 * d22;
/* 519 */						 this.motZ += this.motZ / d21 * d22;
/* 520 */					 } else if (i1 == 1) {
/* 521 */						 if (this.world.s(i - 1, j, k))
/* 522 */							 this.motX = 0.02D;
/* 523 */						 else if (this.world.s(i + 1, j, k))
/* 524 */							 this.motX = -0.02D;
/*		 */					 }
/* 526 */					 else if (i1 == 0) {
/* 527 */						 if (this.world.s(i, j, k - 1))
/* 528 */							 this.motZ = 0.02D;
/* 529 */						 else if (this.world.s(i, j, k + 1)) {
/* 530 */							 this.motZ = -0.02D;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 535 */				 D();
/*		 */			 } else {
/* 537 */				 if (this.motX < -d4) {
/* 538 */					 this.motX = (-d4);
/*		 */				 }
/*		 */ 
/* 541 */				 if (this.motX > d4) {
/* 542 */					 this.motX = d4;
/*		 */				 }
/*		 */ 
/* 545 */				 if (this.motZ < -d4) {
/* 546 */					 this.motZ = (-d4);
/*		 */				 }
/*		 */ 
/* 549 */				 if (this.motZ > d4) {
/* 550 */					 this.motZ = d4;
/*		 */				 }
/*		 */ 
/* 553 */				 if (this.onGround)
/*		 */				 {
/* 555 */					 this.motX *= this.derailedX;
/* 556 */					 this.motY *= this.derailedY;
/* 557 */					 this.motZ *= this.derailedZ;
/*		 */				 }
/*		 */ 
/* 561 */				 move(this.motX, this.motY, this.motZ);
/* 562 */				 if (!this.onGround)
/*		 */				 {
/* 564 */					 this.motX *= this.flyingX;
/* 565 */					 this.motY *= this.flyingY;
/* 566 */					 this.motZ *= this.flyingZ;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 571 */			 this.pitch = 0.0F;
/* 572 */			 double d23 = this.lastX - this.locX;
/* 573 */			 double d24 = this.lastZ - this.locZ;
/*		 */ 
/* 575 */			 if (d23 * d23 + d24 * d24 > 0.001D) {
/* 576 */				 this.yaw = (float)(Math.atan2(d24, d23) * 180.0D / 3.141592653589793D);
/* 577 */				 if (this.f) {
/* 578 */					 this.yaw += 180.0F;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 582 */			 double d25 = MathHelper.g(this.yaw - this.lastYaw);
/*		 */ 
/* 584 */			 if ((d25 < -170.0D) || (d25 >= 170.0D)) {
/* 585 */				 this.yaw += 180.0F;
/* 586 */				 this.f = (!this.f);
/*		 */			 }
/*		 */ 
/* 589 */			 b(this.yaw, this.pitch);
/*		 */ 
/* 592 */			 org.bukkit.World bworld = this.world.getWorld();
/* 593 */			 Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
/* 594 */			 Location to = new Location(bworld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 595 */			 Vehicle vehicle = (Vehicle)getBukkitEntity();
/*		 */ 
/* 597 */			 this.world.getServer().getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));
/*		 */ 
/* 599 */			 if (!from.equals(to)) {
/* 600 */				 this.world.getServer().getPluginManager().callEvent(new VehicleMoveEvent(vehicle, from, to));
/*		 */			 }
/*		 */ 
/* 604 */			 List list = this.world.getEntities(this, this.boundingBox.grow(0.2000000029802322D, 0.0D, 0.2000000029802322D));
/*		 */ 
/* 606 */			 if ((list != null) && (!list.isEmpty())) {
/* 607 */				 for (int l1 = 0; l1 < list.size(); l1++) {
/* 608 */					 Entity entity = (Entity)list.get(l1);
/*		 */ 
/* 610 */					 if ((entity != this.passenger) && (entity.M()) && ((entity instanceof EntityMinecart))) {
/* 611 */						 entity.collide(this);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 616 */			 if ((this.passenger != null) && (this.passenger.dead)) {
/* 617 */				 if (this.passenger.vehicle == this) {
/* 618 */					 this.passenger.vehicle = null;
/*		 */				 }
/*		 */ 
/* 621 */				 this.passenger = null;
/*		 */			 }
/*		 */ 
/* 624 */			 if (this.e > 0) {
/* 625 */				 this.e -= 1;
/*		 */			 }
/*		 */ 
/* 628 */			 if (this.e <= 0) {
/* 629 */				 this.b = (this.c = 0.0D);
/*		 */			 }
/*		 */ 
/* 632 */			 d(this.e > 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Vec3D a(double d0, double d1, double d2) {
/* 637 */		 int i = MathHelper.floor(d0);
/* 638 */		 int j = MathHelper.floor(d1);
/* 639 */		 int k = MathHelper.floor(d2);
/*		 */ 
/* 641 */		 if (BlockMinecartTrack.d_(this.world, i, j - 1, k)) {
/* 642 */			 j--;
/*		 */		 }
/*		 */ 
/* 645 */		 int l = this.world.getTypeId(i, j, k);
/*		 */ 
/* 647 */		 if (BlockMinecartTrack.d(l)) {
/* 648 */			 int i1 = this.world.getData(i, j, k);
/*		 */ 
/* 650 */			 d1 = j;
/* 651 */			 if (((BlockMinecartTrack)Block.byId[l]).n()) {
/* 652 */				 i1 &= 7;
/*		 */			 }
/*		 */ 
/* 655 */			 if ((i1 >= 2) && (i1 <= 5)) {
/* 656 */				 d1 = j + 1;
/*		 */			 }
/*		 */ 
/* 659 */			 int[][] aint = matrix[i1];
/* 660 */			 double d3 = 0.0D;
/* 661 */			 double d4 = i + 0.5D + aint[0][0] * 0.5D;
/* 662 */			 double d5 = j + 0.5D + aint[0][1] * 0.5D;
/* 663 */			 double d6 = k + 0.5D + aint[0][2] * 0.5D;
/* 664 */			 double d7 = i + 0.5D + aint[1][0] * 0.5D;
/* 665 */			 double d8 = j + 0.5D + aint[1][1] * 0.5D;
/* 666 */			 double d9 = k + 0.5D + aint[1][2] * 0.5D;
/* 667 */			 double d10 = d7 - d4;
/* 668 */			 double d11 = (d8 - d5) * 2.0D;
/* 669 */			 double d12 = d9 - d6;
/*		 */ 
/* 671 */			 if (d10 == 0.0D) {
/* 672 */				 d0 = i + 0.5D;
/* 673 */				 d3 = d2 - k;
/* 674 */			 } else if (d12 == 0.0D) {
/* 675 */				 d2 = k + 0.5D;
/* 676 */				 d3 = d0 - i;
/*		 */			 } else {
/* 678 */				 double d13 = d0 - d4;
/* 679 */				 double d14 = d2 - d6;
/*		 */ 
/* 681 */				 d3 = (d13 * d10 + d14 * d12) * 2.0D;
/*		 */			 }
/*		 */ 
/* 684 */			 d0 = d4 + d10 * d3;
/* 685 */			 d1 = d5 + d11 * d3;
/* 686 */			 d2 = d6 + d12 * d3;
/* 687 */			 if (d11 < 0.0D) {
/* 688 */				 d1 += 1.0D;
/*		 */			 }
/*		 */ 
/* 691 */			 if (d11 > 0.0D) {
/* 692 */				 d1 += 0.5D;
/*		 */			 }
/*		 */ 
/* 695 */			 return Vec3D.a().create(d0, d1, d2);
/*		 */		 }
/* 697 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(NBTTagCompound nbttagcompound)
/*		 */	 {
/* 702 */		 nbttagcompound.setInt("Type", this.type);
/* 703 */		 if (this.type == 2) {
/* 704 */			 nbttagcompound.setDouble("PushX", this.b);
/* 705 */			 nbttagcompound.setDouble("PushZ", this.c);
/* 706 */			 nbttagcompound.setShort("Fuel", (short)this.e);
/* 707 */		 } else if (this.type == 1) {
/* 708 */			 NBTTagList nbttaglist = new NBTTagList();
/*		 */ 
/* 710 */			 for (int i = 0; i < this.items.length; i++) {
/* 711 */				 if (this.items[i] != null) {
/* 712 */					 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*		 */ 
/* 714 */					 nbttagcompound1.setByte("Slot", (byte)i);
/* 715 */					 this.items[i].save(nbttagcompound1);
/* 716 */					 nbttaglist.add(nbttagcompound1);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 720 */			 nbttagcompound.set("Items", nbttaglist);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(NBTTagCompound nbttagcompound) {
/* 725 */		 this.type = nbttagcompound.getInt("Type");
/* 726 */		 if (this.type == 2) {
/* 727 */			 this.b = nbttagcompound.getDouble("PushX");
/* 728 */			 this.c = nbttagcompound.getDouble("PushZ");
/* 729 */			 this.e = nbttagcompound.getShort("Fuel");
/* 730 */		 } else if (this.type == 1) {
/* 731 */			 NBTTagList nbttaglist = nbttagcompound.getList("Items");
/*		 */ 
/* 733 */			 this.items = new ItemStack[getSize()];
/*		 */ 
/* 735 */			 for (int i = 0; i < nbttaglist.size(); i++) {
/* 736 */				 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
/* 737 */				 int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*		 */ 
/* 739 */				 if ((j >= 0) && (j < this.items.length))
/* 740 */					 this.items[j] = ItemStack.a(nbttagcompound1);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void collide(Entity entity)
/*		 */	 {
/* 747 */		 if ((!this.world.isStatic) && 
/* 748 */			 (entity != this.passenger))
/*		 */		 {
/* 750 */			 Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 751 */			 org.bukkit.entity.Entity hitEntity = entity == null ? null : entity.getBukkitEntity();
/*		 */ 
/* 753 */			 VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, hitEntity);
/* 754 */			 this.world.getServer().getPluginManager().callEvent(collisionEvent);
/*		 */ 
/* 756 */			 if (collisionEvent.isCancelled()) {
/* 757 */				 return;
/*		 */			 }
/*		 */ 
/* 761 */			 if (((entity instanceof EntityLiving)) && (!(entity instanceof EntityHuman)) && (!(entity instanceof EntityIronGolem)) && (this.type == 0) && (this.motX * this.motX + this.motZ * this.motZ > 0.01D) && (this.passenger == null) && (entity.vehicle == null)) {
/* 762 */				 entity.mount(this);
/*		 */			 }
/*		 */ 
/* 765 */			 double d0 = entity.locX - this.locX;
/* 766 */			 double d1 = entity.locZ - this.locZ;
/* 767 */			 double d2 = d0 * d0 + d1 * d1;
/*		 */ 
/* 770 */			 if ((d2 >= 9.999999747378752E-005D) && (!collisionEvent.isCollisionCancelled())) {
/* 771 */				 d2 = MathHelper.sqrt(d2);
/* 772 */				 d0 /= d2;
/* 773 */				 d1 /= d2;
/* 774 */				 double d3 = 1.0D / d2;
/*		 */ 
/* 776 */				 if (d3 > 1.0D) {
/* 777 */					 d3 = 1.0D;
/*		 */				 }
/*		 */ 
/* 780 */				 d0 *= d3;
/* 781 */				 d1 *= d3;
/* 782 */				 d0 *= 0.1000000014901161D;
/* 783 */				 d1 *= 0.1000000014901161D;
/* 784 */				 d0 *= (1.0F - this.Y);
/* 785 */				 d1 *= (1.0F - this.Y);
/* 786 */				 d0 *= 0.5D;
/* 787 */				 d1 *= 0.5D;
/* 788 */				 if ((entity instanceof EntityMinecart)) {
/* 789 */					 double d4 = entity.locX - this.locX;
/* 790 */					 double d5 = entity.locZ - this.locZ;
/* 791 */					 Vec3D vec3d = Vec3D.a().create(d4, 0.0D, d5).b();
/* 792 */					 Vec3D vec3d1 = Vec3D.a().create(MathHelper.cos(this.yaw * 3.141593F / 180.0F), 0.0D, MathHelper.sin(this.yaw * 3.141593F / 180.0F)).b();
/* 793 */					 double d6 = Math.abs(vec3d.b(vec3d1));
/*		 */ 
/* 795 */					 if (d6 < 0.800000011920929D) {
/* 796 */						 return;
/*		 */					 }
/*		 */ 
/* 799 */					 double d7 = entity.motX + this.motX;
/* 800 */					 double d8 = entity.motZ + this.motZ;
/*		 */ 
/* 802 */					 if ((((EntityMinecart)entity).type == 2) && (this.type != 2)) {
/* 803 */						 this.motX *= 0.2000000029802322D;
/* 804 */						 this.motZ *= 0.2000000029802322D;
/* 805 */						 g(entity.motX - d0, 0.0D, entity.motZ - d1);
/* 806 */						 entity.motX *= 0.949999988079071D;
/* 807 */						 entity.motZ *= 0.949999988079071D;
/* 808 */					 } else if ((((EntityMinecart)entity).type != 2) && (this.type == 2)) {
/* 809 */						 entity.motX *= 0.2000000029802322D;
/* 810 */						 entity.motZ *= 0.2000000029802322D;
/* 811 */						 entity.g(this.motX + d0, 0.0D, this.motZ + d1);
/* 812 */						 this.motX *= 0.949999988079071D;
/* 813 */						 this.motZ *= 0.949999988079071D;
/*		 */					 } else {
/* 815 */						 d7 /= 2.0D;
/* 816 */						 d8 /= 2.0D;
/* 817 */						 this.motX *= 0.2000000029802322D;
/* 818 */						 this.motZ *= 0.2000000029802322D;
/* 819 */						 g(d7 - d0, 0.0D, d8 - d1);
/* 820 */						 entity.motX *= 0.2000000029802322D;
/* 821 */						 entity.motZ *= 0.2000000029802322D;
/* 822 */						 entity.g(d7 + d0, 0.0D, d8 + d1);
/*		 */					 }
/*		 */				 } else {
/* 825 */					 g(-d0, 0.0D, -d1);
/* 826 */					 entity.g(d0 / 4.0D, 0.0D, d1 / 4.0D);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getSize()
/*		 */	 {
/* 834 */		 return 27;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack getItem(int i) {
/* 838 */		 return this.items[i];
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitStack(int i, int j) {
/* 842 */		 if (this.items[i] != null)
/*		 */		 {
/* 845 */			 if (this.items[i].count <= j) {
/* 846 */				 ItemStack itemstack = this.items[i];
/* 847 */				 this.items[i] = null;
/* 848 */				 return itemstack;
/*		 */			 }
/* 850 */			 ItemStack itemstack = this.items[i].a(j);
/* 851 */			 if (this.items[i].count == 0) {
/* 852 */				 this.items[i] = null;
/*		 */			 }
/*		 */ 
/* 855 */			 return itemstack;
/*		 */		 }
/*		 */ 
/* 858 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack splitWithoutUpdate(int i)
/*		 */	 {
/* 863 */		 if (this.items[i] != null) {
/* 864 */			 ItemStack itemstack = this.items[i];
/*		 */ 
/* 866 */			 this.items[i] = null;
/* 867 */			 return itemstack;
/*		 */		 }
/* 869 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void setItem(int i, ItemStack itemstack)
/*		 */	 {
/* 874 */		 this.items[i] = itemstack;
/* 875 */		 if ((itemstack != null) && (itemstack.count > getMaxStackSize()))
/* 876 */			 itemstack.count = getMaxStackSize();
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/* 881 */		 return "container.minecart";
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 885 */		 return this.maxStack;
/*		 */	 }
/*		 */	 public void update() {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/* 891 */		 if (this.type == 0) {
/* 892 */			 if ((this.passenger != null) && ((this.passenger instanceof EntityHuman)) && (this.passenger != entityhuman)) {
/* 893 */				 return true;
/*		 */			 }
/*		 */ 
/* 896 */			 if (!this.world.isStatic)
/* 897 */				 entityhuman.mount(this);
/*		 */		 }
/* 899 */		 else if (this.type == 1) {
/* 900 */			 if (!this.world.isStatic)
/* 901 */				 entityhuman.openContainer(this);
/*		 */		 }
/* 903 */		 else if (this.type == 2) {
/* 904 */			 ItemStack itemstack = entityhuman.inventory.getItemInHand();
/*		 */ 
/* 906 */			 if ((itemstack != null) && (itemstack.id == Item.COAL.id)) {
/* 907 */				 if (--itemstack.count == 0) {
/* 908 */					 entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
/*		 */				 }
/*		 */ 
/* 911 */				 this.e += 3600;
/*		 */			 }
/*		 */ 
/* 914 */			 this.b = (this.locX - entityhuman.locX);
/* 915 */			 this.c = (this.locZ - entityhuman.locZ);
/*		 */		 }
/*		 */ 
/* 918 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman) {
/* 922 */		 return !this.dead;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean h() {
/* 926 */		 return (this.datawatcher.getByte(16) & 0x1) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 protected void d(boolean flag) {
/* 930 */		 if (flag)
/* 931 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(this.datawatcher.getByte(16) | 0x1)));
/*		 */		 else
/* 933 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(this.datawatcher.getByte(16) & 0xFFFFFFFE))); 
/*		 */	 }
/*		 */ 
/*		 */	 public void startOpen() {
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/*		 */	 }
/*		 */ 
/*		 */	 public void setDamage(int i) {
/* 942 */		 this.datawatcher.watch(19, Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public int getDamage() {
/* 946 */		 return this.datawatcher.getInt(19);
/*		 */	 }
/*		 */ 
/*		 */	 public void h(int i) {
/* 950 */		 this.datawatcher.watch(17, Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public int j() {
/* 954 */		 return this.datawatcher.getInt(17);
/*		 */	 }
/*		 */ 
/*		 */	 public void i(int i) {
/* 958 */		 this.datawatcher.watch(18, Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public int k() {
/* 962 */		 return this.datawatcher.getInt(18);
/*		 */	 }
/*		 */ 
/*		 */	 public Vector getFlyingVelocityMod()
/*		 */	 {
/* 967 */		 return new Vector(this.flyingX, this.flyingY, this.flyingZ);
/*		 */	 }
/*		 */ 
/*		 */	 public void setFlyingVelocityMod(Vector flying) {
/* 971 */		 this.flyingX = flying.getX();
/* 972 */		 this.flyingY = flying.getY();
/* 973 */		 this.flyingZ = flying.getZ();
/*		 */	 }
/*		 */ 
/*		 */	 public Vector getDerailedVelocityMod() {
/* 977 */		 return new Vector(this.derailedX, this.derailedY, this.derailedZ);
/*		 */	 }
/*		 */ 
/*		 */	 public void setDerailedVelocityMod(Vector derailed) {
/* 981 */		 this.derailedX = derailed.getX();
/* 982 */		 this.derailedY = derailed.getY();
/* 983 */		 this.derailedZ = derailed.getZ();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityMinecart
 * JD-Core Version:		0.6.0
 */