/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Set;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.entity.CraftPlayer;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.player.PlayerVelocityEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ import org.bukkit.util.Vector;
/*		 */ 
/*		 */ public class EntityTrackerEntry
/*		 */ {
/*		 */	 public Entity tracker;
/*		 */	 public int b;
/*		 */	 public int c;
/*		 */	 public int xLoc;
/*		 */	 public int yLoc;
/*		 */	 public int zLoc;
/*		 */	 public int yRot;
/*		 */	 public int xRot;
/*		 */	 public int i;
/*		 */	 public double j;
/*		 */	 public double k;
/*		 */	 public double l;
/*	27 */	 public int m = 0;
/*		 */	 private double p;
/*		 */	 private double q;
/*		 */	 private double r;
/*	31 */	 private boolean s = false;
/*		 */	 private boolean isMoving;
/*	33 */	 private int u = 0;
/*		 */	 private Entity v;
/*	35 */	 public boolean n = false;
/*	36 */	 public Set trackedPlayers = new HashSet();
/*		 */ 
/*		 */	 public EntityTrackerEntry(Entity entity, int i, int j, boolean flag) {
/*	39 */		 this.tracker = entity;
/*	40 */		 this.b = i;
/*	41 */		 this.c = j;
/*	42 */		 this.isMoving = flag;
/*	43 */		 this.xLoc = MathHelper.floor(entity.locX * 32.0D);
/*	44 */		 this.yLoc = MathHelper.floor(entity.locY * 32.0D);
/*	45 */		 this.zLoc = MathHelper.floor(entity.locZ * 32.0D);
/*	46 */		 this.yRot = MathHelper.d(entity.yaw * 256.0F / 360.0F);
/*	47 */		 this.xRot = MathHelper.d(entity.pitch * 256.0F / 360.0F);
/*	48 */		 this.i = MathHelper.d(entity.am() * 256.0F / 360.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean equals(Object object) {
/*	52 */		 return ((EntityTrackerEntry)object).tracker.id == this.tracker.id;
/*		 */	 }
/*		 */ 
/*		 */	 public int hashCode() {
/*	56 */		 return this.tracker.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void track(List list) {
/*	60 */		 this.n = false;
/*	61 */		 if ((!this.s) || (this.tracker.e(this.p, this.q, this.r) > 16.0D)) {
/*	62 */			 this.p = this.tracker.locX;
/*	63 */			 this.q = this.tracker.locY;
/*	64 */			 this.r = this.tracker.locZ;
/*	65 */			 this.s = true;
/*	66 */			 this.n = true;
/*	67 */			 scanPlayers(list);
/*		 */		 }
/*		 */ 
/*	70 */		 if (this.v != this.tracker.vehicle) {
/*	71 */			 this.v = this.tracker.vehicle;
/*	72 */			 broadcast(new Packet39AttachEntity(this.tracker, this.tracker.vehicle));
/*		 */		 }
/*		 */ 
/*	75 */		 if (this.tracker.vehicle == null) {
/*	76 */			 this.u += 1;
/*	77 */			 if ((this.m++ % this.c == 0) || (this.tracker.al)) {
/*	78 */				 int i = this.tracker.am.a(this.tracker.locX);
/*	79 */				 int j = MathHelper.floor(this.tracker.locY * 32.0D);
/*	80 */				 int k = this.tracker.am.a(this.tracker.locZ);
/*	81 */				 int l = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
/*	82 */				 int i1 = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
/*	83 */				 int j1 = i - this.xLoc;
/*	84 */				 int k1 = j - this.yLoc;
/*	85 */				 int l1 = k - this.zLoc;
/*	86 */				 Object object = null;
/*	87 */				 boolean flag = (Math.abs(j1) >= 4) || (Math.abs(k1) >= 4) || (Math.abs(l1) >= 4);
/*	88 */				 boolean flag1 = (Math.abs(l - this.yRot) >= 4) || (Math.abs(i1 - this.xRot) >= 4);
/*		 */ 
/*	91 */				 if (flag) {
/*	92 */					 this.xLoc = i;
/*	93 */					 this.yLoc = j;
/*	94 */					 this.zLoc = k;
/*		 */				 }
/*		 */ 
/*	97 */				 if (flag1) {
/*	98 */					 this.yRot = l;
/*	99 */					 this.xRot = i1;
/*		 */				 }
/*		 */ 
/* 103 */				 if ((j1 >= -128) && (j1 < 128) && (k1 >= -128) && (k1 < 128) && (l1 >= -128) && (l1 < 128) && (this.u <= 400)) {
/* 104 */					 if ((flag) && (flag1))
/* 105 */						 object = new Packet33RelEntityMoveLook(this.tracker.id, (byte)j1, (byte)k1, (byte)l1, (byte)l, (byte)i1);
/* 106 */					 else if (flag)
/* 107 */						 object = new Packet31RelEntityMove(this.tracker.id, (byte)j1, (byte)k1, (byte)l1);
/* 108 */					 else if (flag1)
/* 109 */						 object = new Packet32EntityLook(this.tracker.id, (byte)l, (byte)i1);
/*		 */				 }
/*		 */				 else {
/* 112 */					 this.u = 0;
/*		 */ 
/* 114 */					 if ((this.tracker instanceof EntityPlayer)) {
/* 115 */						 scanPlayers(new ArrayList(this.trackedPlayers));
/*		 */					 }
/*		 */ 
/* 118 */					 object = new Packet34EntityTeleport(this.tracker.id, i, j, k, (byte)l, (byte)i1);
/*		 */				 }
/*		 */ 
/* 121 */				 if (this.isMoving) {
/* 122 */					 double d0 = this.tracker.motX - this.j;
/* 123 */					 double d1 = this.tracker.motY - this.k;
/* 124 */					 double d2 = this.tracker.motZ - this.l;
/* 125 */					 double d3 = 0.02D;
/* 126 */					 double d4 = d0 * d0 + d1 * d1 + d2 * d2;
/*		 */ 
/* 128 */					 if ((d4 > d3 * d3) || ((d4 > 0.0D) && (this.tracker.motX == 0.0D) && (this.tracker.motY == 0.0D) && (this.tracker.motZ == 0.0D))) {
/* 129 */						 this.j = this.tracker.motX;
/* 130 */						 this.k = this.tracker.motY;
/* 131 */						 this.l = this.tracker.motZ;
/* 132 */						 broadcast(new Packet28EntityVelocity(this.tracker.id, this.j, this.k, this.l));
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 136 */				 if (object != null) {
/* 137 */					 broadcast((Packet)object);
/*		 */				 }
/*		 */ 
/* 140 */				 DataWatcher datawatcher = this.tracker.getDataWatcher();
/*		 */ 
/* 142 */				 if (datawatcher.a()) {
/* 143 */					 broadcastIncludingSelf(new Packet40EntityMetadata(this.tracker.id, datawatcher));
/*		 */				 }
/*		 */ 
/* 146 */				 int i2 = MathHelper.d(this.tracker.am() * 256.0F / 360.0F);
/*		 */ 
/* 148 */				 if (Math.abs(i2 - this.i) >= 4) {
/* 149 */					 broadcast(new Packet35EntityHeadRotation(this.tracker.id, (byte)i2));
/* 150 */					 this.i = i2;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 167 */			 this.tracker.al = false;
/*		 */		 }
/*		 */ 
/* 170 */		 if (this.tracker.velocityChanged)
/*		 */		 {
/* 172 */			 boolean cancelled = false;
/*		 */ 
/* 174 */			 if ((this.tracker instanceof EntityPlayer)) {
/* 175 */				 Player player = (Player)this.tracker.getBukkitEntity();
/* 176 */				 Vector velocity = player.getVelocity();
/*		 */ 
/* 178 */				 PlayerVelocityEvent event = new PlayerVelocityEvent(player, velocity);
/* 179 */				 this.tracker.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 181 */				 if (event.isCancelled())
/* 182 */					 cancelled = true;
/* 183 */				 else if (!velocity.equals(event.getVelocity())) {
/* 184 */					 player.setVelocity(velocity);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 188 */			 if (!cancelled) {
/* 189 */				 broadcastIncludingSelf(new Packet28EntityVelocity(this.tracker));
/*		 */			 }
/*		 */ 
/* 192 */			 this.tracker.velocityChanged = false;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void broadcast(Packet packet) {
/* 197 */		 Iterator iterator = this.trackedPlayers.iterator();
/*		 */ 
/* 199 */		 while (iterator.hasNext()) {
/* 200 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 202 */			 entityplayer.netServerHandler.sendPacket(packet);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void broadcastIncludingSelf(Packet packet) {
/* 207 */		 broadcast(packet);
/* 208 */		 if ((this.tracker instanceof EntityPlayer))
/* 209 */			 ((EntityPlayer)this.tracker).netServerHandler.sendPacket(packet);
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/* 214 */		 Iterator iterator = this.trackedPlayers.iterator();
/*		 */ 
/* 216 */		 while (iterator.hasNext()) {
/* 217 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 219 */			 entityplayer.g.add(Integer.valueOf(this.tracker.id));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityPlayer entityplayer) {
/* 224 */		 if (this.trackedPlayers.contains(entityplayer))
/* 225 */			 this.trackedPlayers.remove(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 public void updatePlayer(EntityPlayer entityplayer)
/*		 */	 {
/* 230 */		 if (entityplayer != this.tracker) {
/* 231 */			 double d0 = entityplayer.locX - this.xLoc / 32;
/* 232 */			 double d1 = entityplayer.locZ - this.zLoc / 32;
/*		 */ 
/* 234 */			 if ((d0 >= -this.b) && (d0 <= this.b) && (d1 >= -this.b) && (d1 <= this.b)) {
/* 235 */				 if ((!this.trackedPlayers.contains(entityplayer)) && (d(entityplayer)))
/*		 */				 {
/* 237 */					 if ((this.tracker instanceof EntityPlayer)) {
/* 238 */						 Player player = ((EntityPlayer)this.tracker).getBukkitEntity();
/* 239 */						 if (!entityplayer.getBukkitEntity().canSee(player)) {
/* 240 */							 return;
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 244 */					 this.trackedPlayers.add(entityplayer);
/* 245 */					 Packet packet = b();
/*		 */ 
/* 247 */					 entityplayer.netServerHandler.sendPacket(packet);
/* 248 */					 this.j = this.tracker.motX;
/* 249 */					 this.k = this.tracker.motY;
/* 250 */					 this.l = this.tracker.motZ;
/* 251 */					 if ((this.isMoving) && (!(packet instanceof Packet24MobSpawn))) {
/* 252 */						 entityplayer.netServerHandler.sendPacket(new Packet28EntityVelocity(this.tracker.id, this.tracker.motX, this.tracker.motY, this.tracker.motZ));
/*		 */					 }
/*		 */ 
/* 255 */					 if (this.tracker.vehicle != null) {
/* 256 */						 entityplayer.netServerHandler.sendPacket(new Packet39AttachEntity(this.tracker, this.tracker.vehicle));
/*		 */					 }
/*		 */ 
/* 259 */					 ItemStack[] aitemstack = this.tracker.getEquipment();
/*		 */ 
/* 261 */					 if (aitemstack != null) {
/* 262 */						 for (int i = 0; i < aitemstack.length; i++) {
/* 263 */							 entityplayer.netServerHandler.sendPacket(new Packet5EntityEquipment(this.tracker.id, i, aitemstack[i]));
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 267 */					 if ((this.tracker instanceof EntityHuman)) {
/* 268 */						 EntityHuman entityhuman = (EntityHuman)this.tracker;
/*		 */ 
/* 270 */						 if (entityhuman.isSleeping()) {
/* 271 */							 entityplayer.netServerHandler.sendPacket(new Packet17EntityLocationAction(this.tracker, 0, MathHelper.floor(this.tracker.locX), MathHelper.floor(this.tracker.locY), MathHelper.floor(this.tracker.locZ)));
/*		 */						 }
/*		 */ 
/*		 */					 }
/*		 */ 
/* 276 */					 this.i = MathHelper.d(this.tracker.am() * 256.0F / 360.0F);
/* 277 */					 broadcast(new Packet35EntityHeadRotation(this.tracker.id, (byte)this.i));
/*		 */ 
/* 280 */					 if ((this.tracker instanceof EntityLiving)) {
/* 281 */						 EntityLiving entityliving = (EntityLiving)this.tracker;
/* 282 */						 Iterator iterator = entityliving.getEffects().iterator();
/*		 */ 
/* 284 */						 while (iterator.hasNext()) {
/* 285 */							 MobEffect mobeffect = (MobEffect)iterator.next();
/*		 */ 
/* 287 */							 entityplayer.netServerHandler.sendPacket(new Packet41MobEffect(this.tracker.id, mobeffect));
/*		 */						 }
/*		 */					 }
/*		 */				 }
/* 291 */			 } else if (this.trackedPlayers.contains(entityplayer)) {
/* 292 */				 this.trackedPlayers.remove(entityplayer);
/* 293 */				 entityplayer.g.add(Integer.valueOf(this.tracker.id));
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(EntityPlayer entityplayer) {
/* 299 */		 return entityplayer.q().getPlayerManager().a(entityplayer, this.tracker.ah, this.tracker.aj);
/*		 */	 }
/*		 */ 
/*		 */	 public void scanPlayers(List list) {
/* 303 */		 Iterator iterator = list.iterator();
/*		 */ 
/* 305 */		 while (iterator.hasNext()) {
/* 306 */			 EntityHuman entityhuman = (EntityHuman)iterator.next();
/*		 */ 
/* 308 */			 updatePlayer((EntityPlayer)entityhuman);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private Packet b() {
/* 313 */		 if (this.tracker.dead)
/*		 */		 {
/* 316 */			 return null;
/*		 */		 }
/*		 */ 
/* 320 */		 if ((this.tracker instanceof EntityItem)) {
/* 321 */			 EntityItem entityitem = (EntityItem)this.tracker;
/* 322 */			 Packet21PickupSpawn packet21pickupspawn = new Packet21PickupSpawn(entityitem);
/*		 */ 
/* 324 */			 entityitem.locX = (packet21pickupspawn.b / 32.0D);
/* 325 */			 entityitem.locY = (packet21pickupspawn.c / 32.0D);
/* 326 */			 entityitem.locZ = (packet21pickupspawn.d / 32.0D);
/* 327 */			 return packet21pickupspawn;
/* 328 */		 }if ((this.tracker instanceof EntityPlayer)) {
/* 329 */			 return new Packet20NamedEntitySpawn((EntityHuman)this.tracker);
/*		 */		 }
/* 331 */		 if ((this.tracker instanceof EntityMinecart)) {
/* 332 */			 EntityMinecart entityminecart = (EntityMinecart)this.tracker;
/*		 */ 
/* 334 */			 if (entityminecart.type == 0) {
/* 335 */				 return new Packet23VehicleSpawn(this.tracker, 10);
/*		 */			 }
/*		 */ 
/* 338 */			 if (entityminecart.type == 1) {
/* 339 */				 return new Packet23VehicleSpawn(this.tracker, 11);
/*		 */			 }
/*		 */ 
/* 342 */			 if (entityminecart.type == 2) {
/* 343 */				 return new Packet23VehicleSpawn(this.tracker, 12);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 347 */		 if ((this.tracker instanceof EntityBoat))
/* 348 */			 return new Packet23VehicleSpawn(this.tracker, 1);
/* 349 */		 if ((!(this.tracker instanceof IAnimal)) && (!(this.tracker instanceof EntityEnderDragon))) {
/* 350 */			 if ((this.tracker instanceof EntityFishingHook)) {
/* 351 */				 EntityHuman entityhuman = ((EntityFishingHook)this.tracker).owner;
/*		 */ 
/* 353 */				 return new Packet23VehicleSpawn(this.tracker, 90, entityhuman != null ? entityhuman.id : this.tracker.id);
/* 354 */			 }if ((this.tracker instanceof EntityArrow)) {
/* 355 */				 Entity entity = ((EntityArrow)this.tracker).shooter;
/*		 */ 
/* 357 */				 return new Packet23VehicleSpawn(this.tracker, 60, entity != null ? entity.id : this.tracker.id);
/* 358 */			 }if ((this.tracker instanceof EntitySnowball))
/* 359 */				 return new Packet23VehicleSpawn(this.tracker, 61);
/* 360 */			 if ((this.tracker instanceof EntityPotion))
/* 361 */				 return new Packet23VehicleSpawn(this.tracker, 73, ((EntityPotion)this.tracker).getPotionValue());
/* 362 */			 if ((this.tracker instanceof EntityThrownExpBottle))
/* 363 */				 return new Packet23VehicleSpawn(this.tracker, 75);
/* 364 */			 if ((this.tracker instanceof EntityEnderPearl))
/* 365 */				 return new Packet23VehicleSpawn(this.tracker, 65);
/* 366 */			 if ((this.tracker instanceof EntityEnderSignal)) {
/* 367 */				 return new Packet23VehicleSpawn(this.tracker, 72);
/*		 */			 }
/*		 */ 
/* 371 */			 if ((this.tracker instanceof EntitySmallFireball)) {
/* 372 */				 EntitySmallFireball entitysmallfireball = (EntitySmallFireball)this.tracker;
/*		 */ 
/* 374 */				 Packet23VehicleSpawn packet23vehiclespawn = null;
/* 375 */				 if (entitysmallfireball.shooter != null)
/* 376 */					 packet23vehiclespawn = new Packet23VehicleSpawn(this.tracker, 64, entitysmallfireball.shooter.id);
/*		 */				 else {
/* 378 */					 packet23vehiclespawn = new Packet23VehicleSpawn(this.tracker, 64, 0);
/*		 */				 }
/*		 */ 
/* 381 */				 packet23vehiclespawn.e = (int)(entitysmallfireball.dirX * 8000.0D);
/* 382 */				 packet23vehiclespawn.f = (int)(entitysmallfireball.dirY * 8000.0D);
/* 383 */				 packet23vehiclespawn.g = (int)(entitysmallfireball.dirZ * 8000.0D);
/* 384 */				 return packet23vehiclespawn;
/* 385 */			 }if ((this.tracker instanceof EntityFireball)) {
/* 386 */				 EntityFireball entityfireball = (EntityFireball)this.tracker;
/*		 */ 
/* 388 */				 Packet23VehicleSpawn packet23vehiclespawn = null;
/* 389 */				 if (entityfireball.shooter != null)
/* 390 */					 packet23vehiclespawn = new Packet23VehicleSpawn(this.tracker, 63, ((EntityFireball)this.tracker).shooter.id);
/*		 */				 else {
/* 392 */					 packet23vehiclespawn = new Packet23VehicleSpawn(this.tracker, 63, 0);
/*		 */				 }
/*		 */ 
/* 395 */				 packet23vehiclespawn.e = (int)(entityfireball.dirX * 8000.0D);
/* 396 */				 packet23vehiclespawn.f = (int)(entityfireball.dirY * 8000.0D);
/* 397 */				 packet23vehiclespawn.g = (int)(entityfireball.dirZ * 8000.0D);
/* 398 */				 return packet23vehiclespawn;
/* 399 */			 }if ((this.tracker instanceof EntityEgg))
/* 400 */				 return new Packet23VehicleSpawn(this.tracker, 62);
/* 401 */			 if ((this.tracker instanceof EntityTNTPrimed))
/* 402 */				 return new Packet23VehicleSpawn(this.tracker, 50);
/* 403 */			 if ((this.tracker instanceof EntityEnderCrystal))
/* 404 */				 return new Packet23VehicleSpawn(this.tracker, 51);
/* 405 */			 if ((this.tracker instanceof EntityFallingBlock)) {
/* 406 */				 EntityFallingBlock entityfallingblock = (EntityFallingBlock)this.tracker;
/*		 */ 
/* 408 */				 return new Packet23VehicleSpawn(this.tracker, 70, entityfallingblock.id | entityfallingblock.data << 16);
/* 409 */			 }if ((this.tracker instanceof EntityPainting))
/* 410 */				 return new Packet25EntityPainting((EntityPainting)this.tracker);
/* 411 */			 if ((this.tracker instanceof EntityExperienceOrb)) {
/* 412 */				 return new Packet26AddExpOrb((EntityExperienceOrb)this.tracker);
/*		 */			 }
/* 414 */			 throw new IllegalArgumentException("Don't know how to add " + this.tracker.getClass() + "!");
/*		 */		 }
/*		 */ 
/* 418 */		 this.i = MathHelper.d(this.tracker.am() * 256.0F / 360.0F);
/* 419 */		 return new Packet24MobSpawn((EntityLiving)this.tracker);
/*		 */	 }
/*		 */ 
/*		 */	 public void clear(EntityPlayer entityplayer)
/*		 */	 {
/* 425 */		 if (this.trackedPlayers.contains(entityplayer)) {
/* 426 */			 this.trackedPlayers.remove(entityplayer);
/* 427 */			 entityplayer.g.add(Integer.valueOf(this.tracker.id));
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityTrackerEntry
 * JD-Core Version:		0.6.0
 */