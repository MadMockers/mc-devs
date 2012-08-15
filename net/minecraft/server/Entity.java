/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.io.PrintStream;
/*			*/ import java.util.Iterator;
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ import java.util.UUID;
/*			*/ import org.bukkit.Bukkit;
/*			*/ import org.bukkit.Server;
/*			*/ import org.bukkit.block.BlockFace;
/*			*/ import org.bukkit.craftbukkit.CraftServer;
/*			*/ import org.bukkit.craftbukkit.CraftWorld;
/*			*/ import org.bukkit.craftbukkit.entity.CraftEntity;
/*			*/ import org.bukkit.craftbukkit.entity.CraftPlayer;
/*			*/ import org.bukkit.entity.LivingEntity;
/*			*/ import org.bukkit.entity.Painting;
/*			*/ import org.bukkit.entity.Vehicle;
/*			*/ import org.bukkit.event.entity.EntityCombustByBlockEvent;
/*			*/ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*			*/ import org.bukkit.event.entity.EntityCombustEvent;
/*			*/ import org.bukkit.event.entity.EntityDamageByBlockEvent;
/*			*/ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*			*/ import org.bukkit.event.entity.EntityDamageEvent;
/*			*/ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*			*/ import org.bukkit.event.painting.PaintingBreakByEntityEvent;
/*			*/ import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
/*			*/ import org.bukkit.event.vehicle.VehicleEnterEvent;
/*			*/ import org.bukkit.event.vehicle.VehicleExitEvent;
/*			*/ import org.bukkit.plugin.PluginManager;
/*			*/ 
/*			*/ public abstract class Entity
/*			*/ {
/*	 32 */	 private static int entityCount = 0;
/*			*/	 public int id;
/*			*/	 public double l;
/*			*/	 public boolean m;
/*			*/	 public Entity passenger;
/*			*/	 public Entity vehicle;
/*			*/	 public World world;
/*			*/	 public double lastX;
/*			*/	 public double lastY;
/*			*/	 public double lastZ;
/*			*/	 public double locX;
/*			*/	 public double locY;
/*			*/	 public double locZ;
/*			*/	 public double motX;
/*			*/	 public double motY;
/*			*/	 public double motZ;
/*			*/	 public float yaw;
/*			*/	 public float pitch;
/*			*/	 public float lastYaw;
/*			*/	 public float lastPitch;
/*			*/	 public final AxisAlignedBB boundingBox;
/*			*/	 public boolean onGround;
/*			*/	 public boolean positionChanged;
/*			*/	 public boolean G;
/*			*/	 public boolean H;
/*			*/	 public boolean velocityChanged;
/*			*/	 protected boolean J;
/*			*/	 public boolean K;
/*			*/	 public boolean dead;
/*			*/	 public float height;
/*			*/	 public float width;
/*			*/	 public float length;
/*			*/	 public float P;
/*			*/	 public float Q;
/*			*/	 public float fallDistance;
/*			*/	 private int b;
/*			*/	 public double S;
/*			*/	 public double T;
/*			*/	 public double U;
/*			*/	 public float V;
/*			*/	 public float W;
/*			*/	 public boolean X;
/*			*/	 public float Y;
/*			*/	 protected Random random;
/*			*/	 public int ticksLived;
/*			*/	 public int maxFireTicks;
/*			*/	 public int fireTicks;
/*			*/	 protected boolean ac;
/*			*/	 public int noDamageTicks;
/*			*/	 private boolean justCreated;
/*			*/	 protected boolean fireProof;
/*			*/	 protected DataWatcher datawatcher;
/*			*/	 private double e;
/*			*/	 private double f;
/*			*/	 public boolean ag;
/*			*/	 public int ah;
/*			*/	 public int ai;
/*			*/	 public int aj;
/*			*/	 public boolean ak;
/*			*/	 public boolean al;
/*			*/	 public EnumEntitySize am;
/*	 93 */	 public UUID uniqueId = UUID.randomUUID();
/*	 94 */	 public boolean valid = true;
/*			*/	 protected org.bukkit.entity.Entity bukkitEntity;
/*			*/ 
/*			*/	 public Entity(World world)
/*			*/	 {
/*	 97 */		 this.id = (entityCount++);
/*	 98 */		 this.l = 1.0D;
/*	 99 */		 this.m = false;
/*	100 */		 this.boundingBox = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*	101 */		 this.onGround = false;
/*	102 */		 this.H = false;
/*	103 */		 this.velocityChanged = false;
/*	104 */		 this.K = true;
/*	105 */		 this.dead = false;
/*	106 */		 this.height = 0.0F;
/*	107 */		 this.width = 0.6F;
/*	108 */		 this.length = 1.8F;
/*	109 */		 this.P = 0.0F;
/*	110 */		 this.Q = 0.0F;
/*	111 */		 this.fallDistance = 0.0F;
/*	112 */		 this.b = 1;
/*	113 */		 this.V = 0.0F;
/*	114 */		 this.W = 0.0F;
/*	115 */		 this.X = false;
/*	116 */		 this.Y = 0.0F;
/*	117 */		 this.random = new Random();
/*	118 */		 this.ticksLived = 0;
/*	119 */		 this.maxFireTicks = 1;
/*	120 */		 this.fireTicks = 0;
/*	121 */		 this.ac = false;
/*	122 */		 this.noDamageTicks = 0;
/*	123 */		 this.justCreated = true;
/*	124 */		 this.fireProof = false;
/*	125 */		 this.datawatcher = new DataWatcher();
/*	126 */		 this.ag = false;
/*	127 */		 this.am = EnumEntitySize.SIZE_2;
/*	128 */		 this.world = world;
/*	129 */		 setPosition(0.0D, 0.0D, 0.0D);
/*	130 */		 this.datawatcher.a(0, Byte.valueOf(0));
/*	131 */		 this.datawatcher.a(1, Short.valueOf(300));
/*	132 */		 a();
/*			*/	 }
/*			*/	 protected abstract void a();
/*			*/ 
/*			*/	 public DataWatcher getDataWatcher() {
/*	138 */		 return this.datawatcher;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean equals(Object object) {
/*	142 */		 return ((Entity)object).id == this.id;
/*			*/	 }
/*			*/ 
/*			*/	 public int hashCode() {
/*	146 */		 return this.id;
/*			*/	 }
/*			*/ 
/*			*/	 public void die() {
/*	150 */		 this.dead = true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(float f, float f1) {
/*	154 */		 this.width = f;
/*	155 */		 this.length = f1;
/*	156 */		 float f2 = f % 2.0F;
/*			*/ 
/*	158 */		 if (f2 < 0.375D)
/*	159 */			 this.am = EnumEntitySize.SIZE_1;
/*	160 */		 else if (f2 < 0.75D)
/*	161 */			 this.am = EnumEntitySize.SIZE_2;
/*	162 */		 else if (f2 < 1.0D)
/*	163 */			 this.am = EnumEntitySize.SIZE_3;
/*	164 */		 else if (f2 < 1.375D)
/*	165 */			 this.am = EnumEntitySize.SIZE_4;
/*	166 */		 else if (f2 < 1.75D)
/*	167 */			 this.am = EnumEntitySize.SIZE_5;
/*			*/		 else
/*	169 */			 this.am = EnumEntitySize.SIZE_6;
/*			*/	 }
/*			*/ 
/*			*/	 protected void b(float f, float f1)
/*			*/	 {
/*	175 */		 if (Float.isNaN(f)) {
/*	176 */			 f = 0.0F;
/*			*/		 }
/*			*/ 
/*	179 */		 if ((f == (1.0F / 1.0F)) || (f == (1.0F / -1.0F))) {
/*	180 */			 if ((this instanceof EntityPlayer)) {
/*	181 */				 System.err.println(((CraftPlayer)getBukkitEntity()).getName() + " was caught trying to crash the server with an invalid yaw");
/*	182 */				 ((CraftPlayer)getBukkitEntity()).kickPlayer("Nope");
/*			*/			 }
/*	184 */			 f = 0.0F;
/*			*/		 }
/*			*/ 
/*	188 */		 if (Float.isNaN(f1)) {
/*	189 */			 f1 = 0.0F;
/*			*/		 }
/*			*/ 
/*	192 */		 if ((f1 == (1.0F / 1.0F)) || (f1 == (1.0F / -1.0F))) {
/*	193 */			 if ((this instanceof EntityPlayer)) {
/*	194 */				 System.err.println(((CraftPlayer)getBukkitEntity()).getName() + " was caught trying to crash the server with an invalid pitch");
/*	195 */				 ((CraftPlayer)getBukkitEntity()).kickPlayer("Nope");
/*			*/			 }
/*	197 */			 f1 = 0.0F;
/*			*/		 }
/*			*/ 
/*	201 */		 this.yaw = (f % 360.0F);
/*	202 */		 this.pitch = (f1 % 360.0F);
/*			*/	 }
/*			*/ 
/*			*/	 public void setPosition(double d0, double d1, double d2) {
/*	206 */		 this.locX = d0;
/*	207 */		 this.locY = d1;
/*	208 */		 this.locZ = d2;
/*	209 */		 float f = this.width / 2.0F;
/*	210 */		 float f1 = this.length;
/*			*/ 
/*	212 */		 this.boundingBox.b(d0 - f, d1 - this.height + this.V, d2 - f, d0 + f, d1 - this.height + this.V + f1, d2 + f);
/*			*/	 }
/*			*/ 
/*			*/	 public void h_() {
/*	216 */		 z();
/*			*/	 }
/*			*/ 
/*			*/	 public void z()
/*			*/	 {
/*	221 */		 if ((this.vehicle != null) && (this.vehicle.dead)) {
/*	222 */			 this.vehicle = null;
/*			*/		 }
/*			*/ 
/*	225 */		 this.ticksLived += 1;
/*	226 */		 this.P = this.Q;
/*	227 */		 this.lastX = this.locX;
/*	228 */		 this.lastY = this.locY;
/*	229 */		 this.lastZ = this.locZ;
/*	230 */		 this.lastPitch = this.pitch;
/*	231 */		 this.lastYaw = this.yaw;
/*			*/ 
/*	234 */		 if ((isSprinting()) && (!H())) {
/*	235 */			 int j = MathHelper.floor(this.locX);
/*	236 */			 int k = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
/*			*/ 
/*	238 */			 int i = MathHelper.floor(this.locZ);
/*	239 */			 int l = this.world.getTypeId(j, k, i);
/*			*/ 
/*	241 */			 if (l > 0) {
/*	242 */				 this.world.a("tilecrack_" + l, this.locX + (this.random.nextFloat() - 0.5D) * this.width, this.boundingBox.b + 0.1D, this.locZ + (this.random.nextFloat() - 0.5D) * this.width, -this.motX * 4.0D, 1.5D, -this.motZ * 4.0D);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	246 */		 if (I()) {
/*	247 */			 if ((!this.ac) && (!this.justCreated)) {
/*	248 */				 float f = MathHelper.sqrt(this.motX * this.motX * 0.2000000029802322D + this.motY * this.motY + this.motZ * this.motZ * 0.2000000029802322D) * 0.2F;
/*			*/ 
/*	250 */				 if (f > 1.0F) {
/*	251 */					 f = 1.0F;
/*			*/				 }
/*			*/ 
/*	254 */				 this.world.makeSound(this, "random.splash", f, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/*	255 */				 float f1 = MathHelper.floor(this.boundingBox.b);
/*			*/ 
/*	260 */				 for (int i = 0; i < 1.0F + this.width * 20.0F; i++) {
/*	261 */					 float f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/*	262 */					 float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/*	263 */					 this.world.a("bubble", this.locX + f3, f1 + 1.0F, this.locZ + f2, this.motX, this.motY - this.random.nextFloat() * 0.2F, this.motZ);
/*			*/				 }
/*			*/ 
/*	266 */				 for (i = 0; i < 1.0F + this.width * 20.0F; i++) {
/*	267 */					 float f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/*	268 */					 float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/*	269 */					 this.world.a("splash", this.locX + f3, f1 + 1.0F, this.locZ + f2, this.motX, this.motY, this.motZ);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	273 */			 this.fallDistance = 0.0F;
/*	274 */			 this.ac = true;
/*	275 */			 this.fireTicks = 0;
/*			*/		 } else {
/*	277 */			 this.ac = false;
/*			*/		 }
/*			*/ 
/*	280 */		 if (this.world.isStatic)
/*	281 */			 this.fireTicks = 0;
/*	282 */		 else if (this.fireTicks > 0) {
/*	283 */			 if (this.fireProof) {
/*	284 */				 this.fireTicks -= 4;
/*	285 */				 if (this.fireTicks < 0)
/*	286 */					 this.fireTicks = 0;
/*			*/			 }
/*			*/			 else {
/*	289 */				 if (this.fireTicks % 20 == 0)
/*			*/				 {
/*	291 */					 if ((this instanceof EntityLiving)) {
/*	292 */						 EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.FIRE_TICK, 1);
/*	293 */						 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	295 */						 if (!event.isCancelled()) {
/*	296 */							 event.getEntity().setLastDamageCause(event);
/*	297 */							 damageEntity(DamageSource.BURN, event.getDamage());
/*			*/						 }
/*			*/					 } else {
/*	300 */						 damageEntity(DamageSource.BURN, 1);
/*			*/					 }
/*			*/ 
/*			*/				 }
/*			*/ 
/*	305 */				 this.fireTicks -= 1;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	309 */		 if (J()) {
/*	310 */			 A();
/*	311 */			 this.fallDistance *= 0.5F;
/*			*/		 }
/*			*/ 
/*	314 */		 if (this.locY < -64.0D) {
/*	315 */			 C();
/*			*/		 }
/*			*/ 
/*	318 */		 if (!this.world.isStatic) {
/*	319 */			 a(0, this.fireTicks > 0);
/*	320 */			 a(2, this.vehicle != null);
/*			*/		 }
/*			*/ 
/*	323 */		 this.justCreated = false;
/*			*/	 }
/*			*/ 
/*			*/	 protected void A()
/*			*/	 {
/*	328 */		 if (!this.fireProof)
/*			*/		 {
/*	330 */			 if ((this instanceof EntityLiving)) {
/*	331 */				 Server server = this.world.getServer();
/*			*/ 
/*	334 */				 org.bukkit.block.Block damager = null;
/*	335 */				 org.bukkit.entity.Entity damagee = getBukkitEntity();
/*			*/ 
/*	337 */				 EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(damager, damagee, EntityDamageEvent.DamageCause.LAVA, 4);
/*	338 */				 server.getPluginManager().callEvent(event);
/*			*/ 
/*	340 */				 if (!event.isCancelled()) {
/*	341 */					 damagee.setLastDamageCause(event);
/*	342 */					 damageEntity(DamageSource.LAVA, event.getDamage());
/*			*/				 }
/*			*/ 
/*	345 */				 if (this.fireTicks <= 0)
/*			*/				 {
/*	347 */					 EntityCombustEvent combustEvent = new EntityCombustByBlockEvent(damager, damagee, 15);
/*	348 */					 server.getPluginManager().callEvent(combustEvent);
/*			*/ 
/*	350 */					 if (!combustEvent.isCancelled())
/*	351 */						 setOnFire(combustEvent.getDuration());
/*			*/				 }
/*			*/				 else
/*			*/				 {
/*	355 */					 setOnFire(15);
/*			*/				 }
/*	357 */				 return;
/*			*/			 }
/*			*/ 
/*	361 */			 damageEntity(DamageSource.LAVA, 4);
/*	362 */			 setOnFire(15);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void setOnFire(int i) {
/*	367 */		 int j = i * 20;
/*			*/ 
/*	369 */		 if (this.fireTicks < j)
/*	370 */			 this.fireTicks = j;
/*			*/	 }
/*			*/ 
/*			*/	 public void extinguish()
/*			*/	 {
/*	375 */		 this.fireTicks = 0;
/*			*/	 }
/*			*/ 
/*			*/	 protected void C() {
/*	379 */		 die();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean c(double d0, double d1, double d2) {
/*	383 */		 AxisAlignedBB axisalignedbb = this.boundingBox.c(d0, d1, d2);
/*	384 */		 List list = this.world.getCubes(this, axisalignedbb);
/*			*/ 
/*	386 */		 return list.isEmpty();
/*			*/	 }
/*			*/ 
/*			*/	 public void move(double d0, double d1, double d2) {
/*	390 */		 if (this.X) {
/*	391 */			 this.boundingBox.d(d0, d1, d2);
/*	392 */			 this.locX = ((this.boundingBox.a + this.boundingBox.d) / 2.0D);
/*	393 */			 this.locY = (this.boundingBox.b + this.height - this.V);
/*	394 */			 this.locZ = ((this.boundingBox.c + this.boundingBox.f) / 2.0D);
/*			*/		 }
/*			*/		 else {
/*	397 */			 this.V *= 0.4F;
/*	398 */			 double d3 = this.locX;
/*	399 */			 double d4 = this.locZ;
/*			*/ 
/*	401 */			 if (this.J) {
/*	402 */				 this.J = false;
/*	403 */				 d0 *= 0.25D;
/*	404 */				 d1 *= 0.0500000007450581D;
/*	405 */				 d2 *= 0.25D;
/*	406 */				 this.motX = 0.0D;
/*	407 */				 this.motY = 0.0D;
/*	408 */				 this.motZ = 0.0D;
/*			*/			 }
/*			*/ 
/*	411 */			 double d5 = d0;
/*	412 */			 double d6 = d1;
/*	413 */			 double d7 = d2;
/*	414 */			 AxisAlignedBB axisalignedbb = this.boundingBox.clone();
/*	415 */			 boolean flag = (this.onGround) && (isSneaking()) && ((this instanceof EntityHuman));
/*			*/ 
/*	417 */			 if (flag)
/*			*/			 {
/*	420 */				 for (double d8 = 0.05D; (d0 != 0.0D) && (this.world.getCubes(this, this.boundingBox.c(d0, -1.0D, 0.0D)).isEmpty()); d5 = d0) {
/*	421 */					 if ((d0 < d8) && (d0 >= -d8))
/*	422 */						 d0 = 0.0D;
/*	423 */					 else if (d0 > 0.0D)
/*	424 */						 d0 -= d8;
/*			*/					 else {
/*	426 */						 d0 += d8;
/*			*/					 }
/*			*/				 }
/*			*/ 
/*	430 */				 for (; (d2 != 0.0D) && (this.world.getCubes(this, this.boundingBox.c(0.0D, -1.0D, d2)).isEmpty()); d7 = d2) {
/*	431 */					 if ((d2 < d8) && (d2 >= -d8))
/*	432 */						 d2 = 0.0D;
/*	433 */					 else if (d2 > 0.0D)
/*	434 */						 d2 -= d8;
/*			*/					 else {
/*	436 */						 d2 += d8;
/*			*/					 }
/*			*/				 }
/*			*/ 
/*	440 */				 while ((d0 != 0.0D) && (d2 != 0.0D) && (this.world.getCubes(this, this.boundingBox.c(d0, -1.0D, d2)).isEmpty())) {
/*	441 */					 if ((d0 < d8) && (d0 >= -d8))
/*	442 */						 d0 = 0.0D;
/*	443 */					 else if (d0 > 0.0D)
/*	444 */						 d0 -= d8;
/*			*/					 else {
/*	446 */						 d0 += d8;
/*			*/					 }
/*			*/ 
/*	449 */					 if ((d2 < d8) && (d2 >= -d8))
/*	450 */						 d2 = 0.0D;
/*	451 */					 else if (d2 > 0.0D)
/*	452 */						 d2 -= d8;
/*			*/					 else {
/*	454 */						 d2 += d8;
/*			*/					 }
/*			*/ 
/*	457 */					 d5 = d0;
/*	458 */					 d7 = d2;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	462 */			 List list = this.world.getCubes(this, this.boundingBox.a(d0, d1, d2));
/*			*/			 AxisAlignedBB axisalignedbb1;
/*	466 */			 for (Iterator iterator = list.iterator(); iterator.hasNext(); d1 = axisalignedbb1.b(this.boundingBox, d1)) {
/*	467 */				 axisalignedbb1 = (AxisAlignedBB)iterator.next();
/*			*/			 }
/*			*/ 
/*	470 */			 this.boundingBox.d(0.0D, d1, 0.0D);
/*	471 */			 if ((!this.K) && (d6 != d1)) {
/*	472 */				 d2 = 0.0D;
/*	473 */				 d1 = 0.0D;
/*	474 */				 d0 = 0.0D;
/*			*/			 }
/*			*/ 
/*	477 */			 boolean flag1 = (this.onGround) || ((d6 != d1) && (d6 < 0.0D));
/*			*/			 AxisAlignedBB axisalignedbb2;
/*	482 */			 for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); d0 = axisalignedbb2.a(this.boundingBox, d0)) {
/*	483 */				 axisalignedbb2 = (AxisAlignedBB)iterator1.next();
/*			*/			 }
/*			*/ 
/*	486 */			 this.boundingBox.d(d0, 0.0D, 0.0D);
/*	487 */			 if ((!this.K) && (d5 != d0)) {
/*	488 */				 d2 = 0.0D;
/*	489 */				 d1 = 0.0D;
/*	490 */				 d0 = 0.0D;
/*			*/			 }
/*			*/			 AxisAlignedBB axisalignedbb2;
/*	493 */			 for (iterator1 = list.iterator(); iterator1.hasNext(); d2 = axisalignedbb2.c(this.boundingBox, d2)) {
/*	494 */				 axisalignedbb2 = (AxisAlignedBB)iterator1.next();
/*			*/			 }
/*			*/ 
/*	497 */			 this.boundingBox.d(0.0D, 0.0D, d2);
/*	498 */			 if ((!this.K) && (d7 != d2)) {
/*	499 */				 d2 = 0.0D;
/*	500 */				 d1 = 0.0D;
/*	501 */				 d0 = 0.0D;
/*			*/			 }
/*			*/ 
/*	507 */			 if ((this.W > 0.0F) && (flag1) && ((flag) || (this.V < 0.05F)) && ((d5 != d0) || (d7 != d2))) {
/*	508 */				 double d9 = d0;
/*	509 */				 double d10 = d1;
/*	510 */				 double d11 = d2;
/*			*/ 
/*	512 */				 d0 = d5;
/*	513 */				 d1 = this.W;
/*	514 */				 d2 = d7;
/*	515 */				 AxisAlignedBB axisalignedbb3 = this.boundingBox.clone();
/*			*/ 
/*	517 */				 this.boundingBox.c(axisalignedbb);
/*	518 */				 list = this.world.getCubes(this, this.boundingBox.a(d5, d1, d7));
/*			*/				 AxisAlignedBB axisalignedbb4;
/*	523 */				 for (Iterator iterator2 = list.iterator(); iterator2.hasNext(); d1 = axisalignedbb4.b(this.boundingBox, d1)) {
/*	524 */					 axisalignedbb4 = (AxisAlignedBB)iterator2.next();
/*			*/				 }
/*			*/ 
/*	527 */				 this.boundingBox.d(0.0D, d1, 0.0D);
/*	528 */				 if ((!this.K) && (d6 != d1)) {
/*	529 */					 d2 = 0.0D;
/*	530 */					 d1 = 0.0D;
/*	531 */					 d0 = 0.0D;
/*			*/				 }
/*			*/				 AxisAlignedBB axisalignedbb4;
/*	534 */				 for (iterator2 = list.iterator(); iterator2.hasNext(); d0 = axisalignedbb4.a(this.boundingBox, d0)) {
/*	535 */					 axisalignedbb4 = (AxisAlignedBB)iterator2.next();
/*			*/				 }
/*			*/ 
/*	538 */				 this.boundingBox.d(d0, 0.0D, 0.0D);
/*	539 */				 if ((!this.K) && (d5 != d0)) {
/*	540 */					 d2 = 0.0D;
/*	541 */					 d1 = 0.0D;
/*	542 */					 d0 = 0.0D;
/*			*/				 }
/*			*/				 AxisAlignedBB axisalignedbb4;
/*	545 */				 for (iterator2 = list.iterator(); iterator2.hasNext(); d2 = axisalignedbb4.c(this.boundingBox, d2)) {
/*	546 */					 axisalignedbb4 = (AxisAlignedBB)iterator2.next();
/*			*/				 }
/*			*/ 
/*	549 */				 this.boundingBox.d(0.0D, 0.0D, d2);
/*	550 */				 if ((!this.K) && (d7 != d2)) {
/*	551 */					 d2 = 0.0D;
/*	552 */					 d1 = 0.0D;
/*	553 */					 d0 = 0.0D;
/*			*/				 }
/*			*/ 
/*	556 */				 if ((!this.K) && (d6 != d1)) {
/*	557 */					 d2 = 0.0D;
/*	558 */					 d1 = 0.0D;
/*	559 */					 d0 = 0.0D;
/*			*/				 } else {
/*	561 */					 d1 = -this.W;
/*			*/					 AxisAlignedBB axisalignedbb4;
/*	563 */					 for (iterator2 = list.iterator(); iterator2.hasNext(); d1 = axisalignedbb4.b(this.boundingBox, d1)) {
/*	564 */						 axisalignedbb4 = (AxisAlignedBB)iterator2.next();
/*			*/					 }
/*			*/ 
/*	567 */					 this.boundingBox.d(0.0D, d1, 0.0D);
/*			*/				 }
/*			*/ 
/*	570 */				 if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
/*	571 */					 d0 = d9;
/*	572 */					 d1 = d10;
/*	573 */					 d2 = d11;
/*	574 */					 this.boundingBox.c(axisalignedbb3);
/*			*/				 } else {
/*	576 */					 double d12 = this.boundingBox.b - (int)this.boundingBox.b;
/*			*/ 
/*	578 */					 if (d12 > 0.0D) {
/*	579 */						 this.V = (float)(this.V + d12 + 0.01D);
/*			*/					 }
/*			*/ 
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/*	586 */			 this.locX = ((this.boundingBox.a + this.boundingBox.d) / 2.0D);
/*	587 */			 this.locY = (this.boundingBox.b + this.height - this.V);
/*	588 */			 this.locZ = ((this.boundingBox.c + this.boundingBox.f) / 2.0D);
/*	589 */			 this.positionChanged = ((d5 != d0) || (d7 != d2));
/*	590 */			 this.G = (d6 != d1);
/*	591 */			 this.onGround = ((d6 != d1) && (d6 < 0.0D));
/*	592 */			 this.H = ((this.positionChanged) || (this.G));
/*	593 */			 a(d1, this.onGround);
/*	594 */			 if (d5 != d0) {
/*	595 */				 this.motX = 0.0D;
/*			*/			 }
/*			*/ 
/*	598 */			 if (d6 != d1) {
/*	599 */				 this.motY = 0.0D;
/*			*/			 }
/*			*/ 
/*	602 */			 if (d7 != d2) {
/*	603 */				 this.motZ = 0.0D;
/*			*/			 }
/*			*/ 
/*	606 */			 double d9 = this.locX - d3;
/*	607 */			 double d10 = this.locZ - d4;
/*			*/ 
/*	610 */			 if ((this.positionChanged) && ((getBukkitEntity() instanceof Vehicle))) {
/*	611 */				 Vehicle vehicle = (Vehicle)getBukkitEntity();
/*	612 */				 org.bukkit.block.Block block = this.world.getWorld().getBlockAt(MathHelper.floor(this.locX), MathHelper.floor(this.locY - this.height), MathHelper.floor(this.locZ));
/*			*/ 
/*	614 */				 if (d5 > d0)
/*	615 */					 block = block.getRelative(BlockFace.SOUTH);
/*	616 */				 else if (d5 < d0)
/*	617 */					 block = block.getRelative(BlockFace.NORTH);
/*	618 */				 else if (d7 > d2)
/*	619 */					 block = block.getRelative(BlockFace.WEST);
/*	620 */				 else if (d7 < d2) {
/*	621 */					 block = block.getRelative(BlockFace.EAST);
/*			*/				 }
/*			*/ 
/*	624 */				 VehicleBlockCollisionEvent event = new VehicleBlockCollisionEvent(vehicle, block);
/*	625 */				 this.world.getServer().getPluginManager().callEvent(event);
/*			*/			 }
/*			*/ 
/*	629 */			 if ((e_()) && (!flag) && (this.vehicle == null)) {
/*	630 */				 this.Q = (float)(this.Q + MathHelper.sqrt(d9 * d9 + d10 * d10) * 0.6D);
/*	631 */				 int i = MathHelper.floor(this.locX);
/*	632 */				 int j = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
/*	633 */				 int k = MathHelper.floor(this.locZ);
/*	634 */				 int l = this.world.getTypeId(i, j, k);
/*			*/ 
/*	636 */				 if ((l == 0) && (this.world.getTypeId(i, j - 1, k) == Block.FENCE.id)) {
/*	637 */					 l = this.world.getTypeId(i, j - 1, k);
/*			*/				 }
/*			*/ 
/*	640 */				 if ((this.Q > this.b) && (l > 0)) {
/*	641 */					 this.b = ((int)this.Q + 1);
/*	642 */					 a(i, j, k, l);
/*	643 */					 Block.byId[l].b(this.world, i, j, k, this);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	647 */			 D();
/*	648 */			 boolean flag2 = G();
/*			*/ 
/*	650 */			 if (this.world.e(this.boundingBox.shrink(0.001D, 0.001D, 0.001D))) {
/*	651 */				 burn(1);
/*	652 */				 if (!flag2) {
/*	653 */					 this.fireTicks += 1;
/*			*/ 
/*	655 */					 if (this.fireTicks <= 0) {
/*	656 */						 EntityCombustEvent event = new EntityCombustEvent(getBukkitEntity(), 8);
/*	657 */						 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	659 */						 if (!event.isCancelled())
/*	660 */							 setOnFire(event.getDuration());
/*			*/					 }
/*			*/					 else
/*			*/					 {
/*	664 */						 setOnFire(8);
/*			*/					 }
/*			*/				 }
/*	667 */			 } else if (this.fireTicks <= 0) {
/*	668 */				 this.fireTicks = (-this.maxFireTicks);
/*			*/			 }
/*			*/ 
/*	671 */			 if ((flag2) && (this.fireTicks > 0)) {
/*	672 */				 this.world.makeSound(this, "random.fizz", 0.7F, 1.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/*	673 */				 this.fireTicks = (-this.maxFireTicks);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void D()
/*			*/	 {
/*	681 */		 int i = MathHelper.floor(this.boundingBox.a + 0.001D);
/*	682 */		 int j = MathHelper.floor(this.boundingBox.b + 0.001D);
/*	683 */		 int k = MathHelper.floor(this.boundingBox.c + 0.001D);
/*	684 */		 int l = MathHelper.floor(this.boundingBox.d - 0.001D);
/*	685 */		 int i1 = MathHelper.floor(this.boundingBox.e - 0.001D);
/*	686 */		 int j1 = MathHelper.floor(this.boundingBox.f - 0.001D);
/*			*/ 
/*	688 */		 if (this.world.c(i, j, k, l, i1, j1))
/*	689 */			 for (int k1 = i; k1 <= l; k1++)
/*	690 */				 for (int l1 = j; l1 <= i1; l1++)
/*	691 */					 for (int i2 = k; i2 <= j1; i2++) {
/*	692 */						 int j2 = this.world.getTypeId(k1, l1, i2);
/*			*/ 
/*	694 */						 if (j2 > 0)
/*	695 */							 Block.byId[j2].a(this.world, k1, l1, i2, this);
/*			*/					 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(int i, int j, int k, int l)
/*			*/	 {
/*	704 */		 StepSound stepsound = Block.byId[l].stepSound;
/*			*/ 
/*	706 */		 if (this.world.getTypeId(i, j + 1, k) == Block.SNOW.id) {
/*	707 */			 stepsound = Block.SNOW.stepSound;
/*	708 */			 this.world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.15F, stepsound.getVolume2());
/*	709 */		 } else if (!Block.byId[l].material.isLiquid()) {
/*	710 */			 this.world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.15F, stepsound.getVolume2());
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean e_() {
/*	715 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(double d0, boolean flag) {
/*	719 */		 if (flag) {
/*	720 */			 if (this.fallDistance > 0.0F) {
/*	721 */				 if ((this instanceof EntityLiving)) {
/*	722 */					 int i = MathHelper.floor(this.locX);
/*	723 */					 int j = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
/*	724 */					 int k = MathHelper.floor(this.locZ);
/*	725 */					 int l = this.world.getTypeId(i, j, k);
/*			*/ 
/*	727 */					 if ((l == 0) && (this.world.getTypeId(i, j - 1, k) == Block.FENCE.id)) {
/*	728 */						 l = this.world.getTypeId(i, j - 1, k);
/*			*/					 }
/*			*/ 
/*	731 */					 if (l > 0) {
/*	732 */						 Block.byId[l].a(this.world, i, j, k, this, this.fallDistance);
/*			*/					 }
/*			*/				 }
/*			*/ 
/*	736 */				 a(this.fallDistance);
/*	737 */				 this.fallDistance = 0.0F;
/*			*/			 }
/*	739 */		 } else if (d0 < 0.0D)
/*	740 */			 this.fallDistance = (float)(this.fallDistance - d0);
/*			*/	 }
/*			*/ 
/*			*/	 public AxisAlignedBB E()
/*			*/	 {
/*	745 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 protected void burn(int i) {
/*	749 */		 if (!this.fireProof)
/*			*/		 {
/*	751 */			 if ((this instanceof EntityLiving)) {
/*	752 */				 EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.FIRE, i);
/*	753 */				 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	755 */				 if (event.isCancelled()) {
/*	756 */					 return;
/*			*/				 }
/*			*/ 
/*	759 */				 i = event.getDamage();
/*	760 */				 event.getEntity().setLastDamageCause(event);
/*			*/			 }
/*			*/ 
/*	764 */			 damageEntity(DamageSource.FIRE, i);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public final boolean isFireproof() {
/*	769 */		 return this.fireProof;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(float f) {
/*	773 */		 if (this.passenger != null)
/*	774 */			 this.passenger.a(f);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean G()
/*			*/	 {
/*	779 */		 return (this.ac) || (this.world.B(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean H() {
/*	783 */		 return this.ac;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean I() {
/*	787 */		 return this.world.a(this.boundingBox.grow(0.0D, -0.4000000059604645D, 0.0D).shrink(0.001D, 0.001D, 0.001D), Material.WATER, this);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(Material material) {
/*	791 */		 double d0 = this.locY + getHeadHeight();
/*	792 */		 int i = MathHelper.floor(this.locX);
/*	793 */		 int j = MathHelper.d(MathHelper.floor(d0));
/*	794 */		 int k = MathHelper.floor(this.locZ);
/*	795 */		 int l = this.world.getTypeId(i, j, k);
/*			*/ 
/*	797 */		 if ((l != 0) && (Block.byId[l].material == material)) {
/*	798 */			 float f = BlockFluids.d(this.world.getData(i, j, k)) - 0.1111111F;
/*	799 */			 float f1 = j + 1 - f;
/*			*/ 
/*	801 */			 return d0 < f1;
/*			*/		 }
/*	803 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public float getHeadHeight()
/*			*/	 {
/*	808 */		 return 0.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean J() {
/*	812 */		 return this.world.a(this.boundingBox.grow(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), Material.LAVA);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(float f, float f1, float f2) {
/*	816 */		 float f3 = f * f + f1 * f1;
/*			*/ 
/*	818 */		 if (f3 >= 1.0E-004F) {
/*	819 */			 f3 = MathHelper.c(f3);
/*	820 */			 if (f3 < 1.0F) {
/*	821 */				 f3 = 1.0F;
/*			*/			 }
/*			*/ 
/*	824 */			 f3 = f2 / f3;
/*	825 */			 f *= f3;
/*	826 */			 f1 *= f3;
/*	827 */			 float f4 = MathHelper.sin(this.yaw * 3.141593F / 180.0F);
/*	828 */			 float f5 = MathHelper.cos(this.yaw * 3.141593F / 180.0F);
/*			*/ 
/*	830 */			 this.motX += f * f5 - f1 * f4;
/*	831 */			 this.motZ += f1 * f5 + f * f4;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public float c(float f) {
/*	836 */		 int i = MathHelper.floor(this.locX);
/*	837 */		 int j = MathHelper.floor(this.locZ);
/*			*/ 
/*	839 */		 if (this.world.isLoaded(i, 0, j)) {
/*	840 */			 double d0 = (this.boundingBox.e - this.boundingBox.b) * 0.66D;
/*	841 */			 int k = MathHelper.floor(this.locY - this.height + d0);
/*			*/ 
/*	843 */			 return this.world.o(i, k, j);
/*			*/		 }
/*	845 */		 return 0.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public void spawnIn(World world)
/*			*/	 {
/*	851 */		 if (world == null) {
/*	852 */			 die();
/*	853 */			 this.world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
/*	854 */			 return;
/*			*/		 }
/*			*/ 
/*	858 */		 this.world = world;
/*			*/	 }
/*			*/ 
/*			*/	 public void setLocation(double d0, double d1, double d2, float f, float f1) {
/*	862 */		 this.lastX = (this.locX = d0);
/*	863 */		 this.lastY = (this.locY = d1);
/*	864 */		 this.lastZ = (this.locZ = d2);
/*	865 */		 this.lastYaw = (this.yaw = f);
/*	866 */		 this.lastPitch = (this.pitch = f1);
/*	867 */		 this.V = 0.0F;
/*	868 */		 double d3 = this.lastYaw - f;
/*			*/ 
/*	870 */		 if (d3 < -180.0D) {
/*	871 */			 this.lastYaw += 360.0F;
/*			*/		 }
/*			*/ 
/*	874 */		 if (d3 >= 180.0D) {
/*	875 */			 this.lastYaw -= 360.0F;
/*			*/		 }
/*			*/ 
/*	878 */		 setPosition(this.locX, this.locY, this.locZ);
/*	879 */		 b(f, f1);
/*			*/	 }
/*			*/ 
/*			*/	 public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
/*	883 */		 this.S = (this.lastX = this.locX = d0);
/*	884 */		 this.T = (this.lastY = this.locY = d1 + this.height);
/*	885 */		 this.U = (this.lastZ = this.locZ = d2);
/*	886 */		 this.yaw = f;
/*	887 */		 this.pitch = f1;
/*	888 */		 setPosition(this.locX, this.locY, this.locZ);
/*			*/	 }
/*			*/ 
/*			*/	 public float d(Entity entity) {
/*	892 */		 float f = (float)(this.locX - entity.locX);
/*	893 */		 float f1 = (float)(this.locY - entity.locY);
/*	894 */		 float f2 = (float)(this.locZ - entity.locZ);
/*			*/ 
/*	896 */		 return MathHelper.c(f * f + f1 * f1 + f2 * f2);
/*			*/	 }
/*			*/ 
/*			*/	 public double e(double d0, double d1, double d2) {
/*	900 */		 double d3 = this.locX - d0;
/*	901 */		 double d4 = this.locY - d1;
/*	902 */		 double d5 = this.locZ - d2;
/*			*/ 
/*	904 */		 return d3 * d3 + d4 * d4 + d5 * d5;
/*			*/	 }
/*			*/ 
/*			*/	 public double f(double d0, double d1, double d2) {
/*	908 */		 double d3 = this.locX - d0;
/*	909 */		 double d4 = this.locY - d1;
/*	910 */		 double d5 = this.locZ - d2;
/*			*/ 
/*	912 */		 return MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*			*/	 }
/*			*/ 
/*			*/	 public double e(Entity entity) {
/*	916 */		 double d0 = this.locX - entity.locX;
/*	917 */		 double d1 = this.locY - entity.locY;
/*	918 */		 double d2 = this.locZ - entity.locZ;
/*			*/ 
/*	920 */		 return d0 * d0 + d1 * d1 + d2 * d2;
/*			*/	 }
/*			*/	 public void b_(EntityHuman entityhuman) {
/*			*/	 }
/*			*/ 
/*			*/	 public void collide(Entity entity) {
/*	926 */		 if ((entity.passenger != this) && (entity.vehicle != this)) {
/*	927 */			 double d0 = entity.locX - this.locX;
/*	928 */			 double d1 = entity.locZ - this.locZ;
/*	929 */			 double d2 = MathHelper.a(d0, d1);
/*			*/ 
/*	931 */			 if (d2 >= 0.009999999776482582D) {
/*	932 */				 d2 = MathHelper.sqrt(d2);
/*	933 */				 d0 /= d2;
/*	934 */				 d1 /= d2;
/*	935 */				 double d3 = 1.0D / d2;
/*			*/ 
/*	937 */				 if (d3 > 1.0D) {
/*	938 */					 d3 = 1.0D;
/*			*/				 }
/*			*/ 
/*	941 */				 d0 *= d3;
/*	942 */				 d1 *= d3;
/*	943 */				 d0 *= 0.0500000007450581D;
/*	944 */				 d1 *= 0.0500000007450581D;
/*	945 */				 d0 *= (1.0F - this.Y);
/*	946 */				 d1 *= (1.0F - this.Y);
/*	947 */				 g(-d0, 0.0D, -d1);
/*	948 */				 entity.g(d0, 0.0D, d1);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void g(double d0, double d1, double d2) {
/*	954 */		 this.motX += d0;
/*	955 */		 this.motY += d1;
/*	956 */		 this.motZ += d2;
/*	957 */		 this.al = true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void K() {
/*	961 */		 this.velocityChanged = true;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	965 */		 K();
/*	966 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean L() {
/*	970 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean M() {
/*	974 */		 return false;
/*			*/	 }
/*			*/	 public void c(Entity entity, int i) {
/*			*/	 }
/*			*/ 
/*			*/	 public boolean c(NBTTagCompound nbttagcompound) {
/*	980 */		 String s = Q();
/*			*/ 
/*	982 */		 if ((!this.dead) && (s != null)) {
/*	983 */			 nbttagcompound.setString("id", s);
/*	984 */			 d(nbttagcompound);
/*	985 */			 return true;
/*			*/		 }
/*	987 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public void d(NBTTagCompound nbttagcompound)
/*			*/	 {
/*	992 */		 nbttagcompound.set("Pos", a(new double[] { this.locX, this.locY + this.V, this.locZ }));
/*	993 */		 nbttagcompound.set("Motion", a(new double[] { this.motX, this.motY, this.motZ }));
/*			*/ 
/*	997 */		 if (Float.isNaN(this.yaw)) {
/*	998 */			 this.yaw = 0.0F;
/*			*/		 }
/*			*/ 
/* 1001 */		 if (Float.isNaN(this.pitch)) {
/* 1002 */			 this.pitch = 0.0F;
/*			*/		 }
/*			*/ 
/* 1006 */		 nbttagcompound.set("Rotation", a(new float[] { this.yaw, this.pitch }));
/* 1007 */		 nbttagcompound.setFloat("FallDistance", this.fallDistance);
/* 1008 */		 nbttagcompound.setShort("Fire", (short)this.fireTicks);
/* 1009 */		 nbttagcompound.setShort("Air", (short)getAirTicks());
/* 1010 */		 nbttagcompound.setBoolean("OnGround", this.onGround);
/*			*/ 
/* 1012 */		 nbttagcompound.setLong("WorldUUIDLeast", this.world.getDataManager().getUUID().getLeastSignificantBits());
/* 1013 */		 nbttagcompound.setLong("WorldUUIDMost", this.world.getDataManager().getUUID().getMostSignificantBits());
/* 1014 */		 nbttagcompound.setLong("UUIDLeast", this.uniqueId.getLeastSignificantBits());
/* 1015 */		 nbttagcompound.setLong("UUIDMost", this.uniqueId.getMostSignificantBits());
/*			*/ 
/* 1017 */		 b(nbttagcompound);
/*			*/	 }
/*			*/ 
/*			*/	 public void e(NBTTagCompound nbttagcompound) {
/* 1021 */		 NBTTagList nbttaglist = nbttagcompound.getList("Pos");
/* 1022 */		 NBTTagList nbttaglist1 = nbttagcompound.getList("Motion");
/* 1023 */		 NBTTagList nbttaglist2 = nbttagcompound.getList("Rotation");
/*			*/ 
/* 1025 */		 this.motX = ((NBTTagDouble)nbttaglist1.get(0)).data;
/* 1026 */		 this.motY = ((NBTTagDouble)nbttaglist1.get(1)).data;
/* 1027 */		 this.motZ = ((NBTTagDouble)nbttaglist1.get(2)).data;
/*			*/ 
/* 1042 */		 this.lastX = (this.S = this.locX = ((NBTTagDouble)nbttaglist.get(0)).data);
/* 1043 */		 this.lastY = (this.T = this.locY = ((NBTTagDouble)nbttaglist.get(1)).data);
/* 1044 */		 this.lastZ = (this.U = this.locZ = ((NBTTagDouble)nbttaglist.get(2)).data);
/* 1045 */		 this.lastYaw = (this.yaw = ((NBTTagFloat)nbttaglist2.get(0)).data);
/* 1046 */		 this.lastPitch = (this.pitch = ((NBTTagFloat)nbttaglist2.get(1)).data);
/* 1047 */		 this.fallDistance = nbttagcompound.getFloat("FallDistance");
/* 1048 */		 this.fireTicks = nbttagcompound.getShort("Fire");
/* 1049 */		 setAirTicks(nbttagcompound.getShort("Air"));
/* 1050 */		 this.onGround = nbttagcompound.getBoolean("OnGround");
/* 1051 */		 setPosition(this.locX, this.locY, this.locZ);
/*			*/ 
/* 1054 */		 long least = nbttagcompound.getLong("UUIDLeast");
/* 1055 */		 long most = nbttagcompound.getLong("UUIDMost");
/*			*/ 
/* 1057 */		 if ((least != 0L) && (most != 0L)) {
/* 1058 */			 this.uniqueId = new UUID(most, least);
/*			*/		 }
/*			*/ 
/* 1062 */		 b(this.yaw, this.pitch);
/* 1063 */		 a(nbttagcompound);
/*			*/ 
/* 1066 */		 if (!(getBukkitEntity() instanceof Vehicle)) {
/* 1067 */			 if (Math.abs(this.motX) > 10.0D) {
/* 1068 */				 this.motX = 0.0D;
/*			*/			 }
/*			*/ 
/* 1071 */			 if (Math.abs(this.motY) > 10.0D) {
/* 1072 */				 this.motY = 0.0D;
/*			*/			 }
/*			*/ 
/* 1075 */			 if (Math.abs(this.motZ) > 10.0D) {
/* 1076 */				 this.motZ = 0.0D;
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1082 */		 if ((this instanceof EntityPlayer)) {
/* 1083 */			 Server server = Bukkit.getServer();
/* 1084 */			 org.bukkit.World bworld = null;
/*			*/ 
/* 1087 */			 String worldName = nbttagcompound.getString("World");
/*			*/ 
/* 1089 */			 if ((nbttagcompound.hasKey("WorldUUIDMost")) && (nbttagcompound.hasKey("WorldUUIDLeast"))) {
/* 1090 */				 UUID uid = new UUID(nbttagcompound.getLong("WorldUUIDMost"), nbttagcompound.getLong("WorldUUIDLeast"));
/* 1091 */				 bworld = server.getWorld(uid);
/*			*/			 } else {
/* 1093 */				 bworld = server.getWorld(worldName);
/*			*/			 }
/*			*/ 
/* 1096 */			 if (bworld == null) {
/* 1097 */				 EntityPlayer entityPlayer = (EntityPlayer)this;
/* 1098 */				 bworld = ((CraftServer)server).getServer().getWorldServer(entityPlayer.dimension).getWorld();
/*			*/			 }
/*			*/ 
/* 1101 */			 spawnIn(bworld == null ? null : ((CraftWorld)bworld).getHandle());
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected final String Q()
/*			*/	 {
/* 1107 */		 return EntityTypes.b(this);
/*			*/	 }
/*			*/	 protected abstract void a(NBTTagCompound paramNBTTagCompound);
/*			*/ 
/*			*/	 protected abstract void b(NBTTagCompound paramNBTTagCompound);
/*			*/ 
/* 1115 */	 protected NBTTagList a(double[] adouble) { NBTTagList nbttaglist = new NBTTagList();
/* 1116 */		 double[] adouble1 = adouble;
/* 1117 */		 int i = adouble.length;
/*			*/ 
/* 1119 */		 for (int j = 0; j < i; j++) {
/* 1120 */			 double d0 = adouble1[j];
/*			*/ 
/* 1122 */			 nbttaglist.add(new NBTTagDouble((String)null, d0));
/*			*/		 }
/*			*/ 
/* 1125 */		 return nbttaglist; }
/*			*/ 
/*			*/	 protected NBTTagList a(float[] afloat)
/*			*/	 {
/* 1129 */		 NBTTagList nbttaglist = new NBTTagList();
/* 1130 */		 float[] afloat1 = afloat;
/* 1131 */		 int i = afloat.length;
/*			*/ 
/* 1133 */		 for (int j = 0; j < i; j++) {
/* 1134 */			 float f = afloat1[j];
/*			*/ 
/* 1136 */			 nbttaglist.add(new NBTTagFloat((String)null, f));
/*			*/		 }
/*			*/ 
/* 1139 */		 return nbttaglist;
/*			*/	 }
/*			*/ 
/*			*/	 public EntityItem b(int i, int j) {
/* 1143 */		 return a(i, j, 0.0F);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityItem a(int i, int j, float f) {
/* 1147 */		 return a(new ItemStack(i, j, 0), f);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityItem a(ItemStack itemstack, float f) {
/* 1151 */		 EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY + f, this.locZ, itemstack);
/*			*/ 
/* 1153 */		 entityitem.pickupDelay = 10;
/* 1154 */		 this.world.addEntity(entityitem);
/* 1155 */		 return entityitem;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isAlive() {
/* 1159 */		 return !this.dead;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean inBlock() {
/* 1163 */		 for (int i = 0; i < 8; i++) {
/* 1164 */			 float f = ((i >> 0) % 2 - 0.5F) * this.width * 0.8F;
/* 1165 */			 float f1 = ((i >> 1) % 2 - 0.5F) * 0.1F;
/* 1166 */			 float f2 = ((i >> 2) % 2 - 0.5F) * this.width * 0.8F;
/* 1167 */			 int j = MathHelper.floor(this.locX + f);
/* 1168 */			 int k = MathHelper.floor(this.locY + getHeadHeight() + f1);
/* 1169 */			 int l = MathHelper.floor(this.locZ + f2);
/*			*/ 
/* 1171 */			 if (this.world.s(j, k, l)) {
/* 1172 */				 return true;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1176 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean c(EntityHuman entityhuman) {
/* 1180 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public AxisAlignedBB g(Entity entity) {
/* 1184 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 public void U() {
/* 1188 */		 if (this.vehicle.dead) {
/* 1189 */			 this.vehicle = null;
/*			*/		 } else {
/* 1191 */			 this.motX = 0.0D;
/* 1192 */			 this.motY = 0.0D;
/* 1193 */			 this.motZ = 0.0D;
/* 1194 */			 h_();
/* 1195 */			 if (this.vehicle != null) {
/* 1196 */				 this.vehicle.V();
/* 1197 */				 this.f += this.vehicle.yaw - this.vehicle.lastYaw;
/*			*/ 
/* 1199 */				 for (this.e += this.vehicle.pitch - this.vehicle.lastPitch; this.f >= 180.0D; this.f -= 360.0D);
/* 1203 */				 while (this.f < -180.0D) {
/* 1204 */					 this.f += 360.0D;
/*			*/				 }
/*			*/ 
/* 1207 */				 while (this.e >= 180.0D) {
/* 1208 */					 this.e -= 360.0D;
/*			*/				 }
/*			*/ 
/* 1211 */				 while (this.e < -180.0D) {
/* 1212 */					 this.e += 360.0D;
/*			*/				 }
/*			*/ 
/* 1215 */				 double d0 = this.f * 0.5D;
/* 1216 */				 double d1 = this.e * 0.5D;
/* 1217 */				 float f = 10.0F;
/*			*/ 
/* 1219 */				 if (d0 > f) {
/* 1220 */					 d0 = f;
/*			*/				 }
/*			*/ 
/* 1223 */				 if (d0 < -f) {
/* 1224 */					 d0 = -f;
/*			*/				 }
/*			*/ 
/* 1227 */				 if (d1 > f) {
/* 1228 */					 d1 = f;
/*			*/				 }
/*			*/ 
/* 1231 */				 if (d1 < -f) {
/* 1232 */					 d1 = -f;
/*			*/				 }
/*			*/ 
/* 1235 */				 this.f -= d0;
/* 1236 */				 this.e -= d1;
/* 1237 */				 this.yaw = (float)(this.yaw + d0);
/* 1238 */				 this.pitch = (float)(this.pitch + d1);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void V() {
/* 1244 */		 if ((!(this.passenger instanceof EntityHuman)) || (!((EntityHuman)this.passenger).bF())) {
/* 1245 */			 this.passenger.S = this.passenger.locX;
/* 1246 */			 this.passenger.T = this.passenger.locY;
/* 1247 */			 this.passenger.U = this.passenger.locZ;
/*			*/		 }
/*			*/ 
/* 1250 */		 this.passenger.setPosition(this.locX, this.locY + X() + this.passenger.W(), this.locZ);
/*			*/	 }
/*			*/ 
/*			*/	 public double W() {
/* 1254 */		 return this.height;
/*			*/	 }
/*			*/ 
/*			*/	 public double X() {
/* 1258 */		 return this.length * 0.75D;
/*			*/	 }
/*			*/ 
/*			*/	 public void mount(Entity entity)
/*			*/	 {
/* 1263 */		 setPassengerOf(entity);
/*			*/	 }
/*			*/ 
/*			*/	 public org.bukkit.entity.Entity getBukkitEntity()
/*			*/	 {
/* 1269 */		 if (this.bukkitEntity == null) {
/* 1270 */			 this.bukkitEntity = CraftEntity.getEntity(this.world.getServer(), this);
/*			*/		 }
/* 1272 */		 return this.bukkitEntity;
/*			*/	 }
/*			*/ 
/*			*/	 public void setPassengerOf(Entity entity)
/*			*/	 {
/* 1279 */		 PluginManager pluginManager = Bukkit.getPluginManager();
/* 1280 */		 getBukkitEntity();
/*			*/ 
/* 1282 */		 this.e = 0.0D;
/* 1283 */		 this.f = 0.0D;
/* 1284 */		 if (entity == null) {
/* 1285 */			 if (this.vehicle != null)
/*			*/			 {
/* 1287 */				 if (((this.bukkitEntity instanceof LivingEntity)) && ((this.vehicle.getBukkitEntity() instanceof Vehicle))) {
/* 1288 */					 VehicleExitEvent event = new VehicleExitEvent((Vehicle)this.vehicle.getBukkitEntity(), (LivingEntity)this.bukkitEntity);
/* 1289 */					 pluginManager.callEvent(event);
/*			*/				 }
/*			*/ 
/* 1293 */				 setPositionRotation(this.vehicle.locX, this.vehicle.boundingBox.b + this.vehicle.length, this.vehicle.locZ, this.yaw, this.pitch);
/* 1294 */				 this.vehicle.passenger = null;
/*			*/			 }
/*			*/ 
/* 1297 */			 this.vehicle = null;
/* 1298 */		 } else if (this.vehicle == entity)
/*			*/		 {
/* 1300 */			 if (((this.bukkitEntity instanceof LivingEntity)) && ((this.vehicle.getBukkitEntity() instanceof Vehicle))) {
/* 1301 */				 VehicleExitEvent event = new VehicleExitEvent((Vehicle)this.vehicle.getBukkitEntity(), (LivingEntity)this.bukkitEntity);
/* 1302 */				 pluginManager.callEvent(event);
/*			*/			 }
/*			*/ 
/* 1306 */			 h(entity);
/* 1307 */			 this.vehicle.passenger = null;
/* 1308 */			 this.vehicle = null;
/*			*/		 }
/*			*/		 else {
/* 1311 */			 if (((this.bukkitEntity instanceof LivingEntity)) && ((entity.getBukkitEntity() instanceof Vehicle))) {
/* 1312 */				 VehicleEnterEvent event = new VehicleEnterEvent((Vehicle)entity.getBukkitEntity(), this.bukkitEntity);
/* 1313 */				 pluginManager.callEvent(event);
/*			*/ 
/* 1315 */				 if (event.isCancelled()) {
/* 1316 */					 return;
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1321 */			 if (this.vehicle != null) {
/* 1322 */				 this.vehicle.passenger = null;
/*			*/			 }
/*			*/ 
/* 1325 */			 if (entity.passenger != null) {
/* 1326 */				 entity.passenger.vehicle = null;
/*			*/			 }
/*			*/ 
/* 1329 */			 this.vehicle = entity;
/* 1330 */			 entity.passenger = this;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void h(Entity entity) {
/* 1335 */		 double d0 = entity.locX;
/* 1336 */		 double d1 = entity.boundingBox.b + entity.length;
/* 1337 */		 double d2 = entity.locZ;
/*			*/ 
/* 1339 */		 for (double d3 = -1.5D; d3 < 2.0D; d3 += 1.0D) {
/* 1340 */			 for (double d4 = -1.5D; d4 < 2.0D; d4 += 1.0D) {
/* 1341 */				 if ((d3 != 0.0D) || (d4 != 0.0D)) {
/* 1342 */					 int i = (int)(this.locX + d3);
/* 1343 */					 int j = (int)(this.locZ + d4);
/* 1344 */					 AxisAlignedBB axisalignedbb = this.boundingBox.c(d3, 1.0D, d4);
/*			*/ 
/* 1346 */					 if (this.world.a(axisalignedbb).isEmpty()) {
/* 1347 */						 if (this.world.t(i, (int)this.locY, j)) {
/* 1348 */							 setPositionRotation(this.locX + d3, this.locY + 1.0D, this.locZ + d4, this.yaw, this.pitch);
/* 1349 */							 return;
/*			*/						 }
/*			*/ 
/* 1352 */						 if ((this.world.t(i, (int)this.locY - 1, j)) || (this.world.getMaterial(i, (int)this.locY - 1, j) == Material.WATER)) {
/* 1353 */							 d0 = this.locX + d3;
/* 1354 */							 d1 = this.locY + 1.0D;
/* 1355 */							 d2 = this.locZ + d4;
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1362 */		 setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*			*/	 }
/*			*/ 
/*			*/	 public float Y() {
/* 1366 */		 return 0.1F;
/*			*/	 }
/*			*/ 
/*			*/	 public Vec3D Z() {
/* 1370 */		 return null;
/*			*/	 }
/*			*/	 public void aa() {
/*			*/	 }
/*			*/ 
/*			*/	 public ItemStack[] getEquipment() {
/* 1376 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isBurning() {
/* 1380 */		 return (this.fireTicks > 0) || (f(0));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isSneaking() {
/* 1384 */		 return f(1);
/*			*/	 }
/*			*/ 
/*			*/	 public void setSneaking(boolean flag) {
/* 1388 */		 a(1, flag);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isSprinting() {
/* 1392 */		 return f(3);
/*			*/	 }
/*			*/ 
/*			*/	 public void setSprinting(boolean flag) {
/* 1396 */		 a(3, flag);
/*			*/	 }
/*			*/ 
/*			*/	 public void c(boolean flag) {
/* 1400 */		 a(4, flag);
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean f(int i) {
/* 1404 */		 return (this.datawatcher.getByte(0) & 1 << i) != 0;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(int i, boolean flag) {
/* 1408 */		 byte b0 = this.datawatcher.getByte(0);
/*			*/ 
/* 1410 */		 if (flag)
/* 1411 */			 this.datawatcher.watch(0, Byte.valueOf((byte)(b0 | 1 << i)));
/*			*/		 else
/* 1413 */			 this.datawatcher.watch(0, Byte.valueOf((byte)(b0 & (1 << i ^ 0xFFFFFFFF))));
/*			*/	 }
/*			*/ 
/*			*/	 public int getAirTicks()
/*			*/	 {
/* 1418 */		 return this.datawatcher.getShort(1);
/*			*/	 }
/*			*/ 
/*			*/	 public void setAirTicks(int i) {
/* 1422 */		 this.datawatcher.watch(1, Short.valueOf((short)i));
/*			*/	 }
/*			*/ 
/*			*/	 public void a(EntityLightning entitylightning)
/*			*/	 {
/* 1427 */		 org.bukkit.entity.Entity thisBukkitEntity = getBukkitEntity();
/* 1428 */		 org.bukkit.entity.Entity stormBukkitEntity = entitylightning.getBukkitEntity();
/* 1429 */		 PluginManager pluginManager = Bukkit.getPluginManager();
/*			*/ 
/* 1431 */		 if ((thisBukkitEntity instanceof Painting)) {
/* 1432 */			 PaintingBreakByEntityEvent event = new PaintingBreakByEntityEvent((Painting)thisBukkitEntity, stormBukkitEntity);
/* 1433 */			 pluginManager.callEvent(event);
/*			*/ 
/* 1435 */			 if (event.isCancelled()) {
/* 1436 */				 return;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1440 */		 EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(stormBukkitEntity, thisBukkitEntity, EntityDamageEvent.DamageCause.LIGHTNING, 5);
/* 1441 */		 pluginManager.callEvent(event);
/*			*/ 
/* 1443 */		 if (event.isCancelled()) {
/* 1444 */			 return;
/*			*/		 }
/*			*/ 
/* 1447 */		 thisBukkitEntity.setLastDamageCause(event);
/* 1448 */		 burn(event.getDamage());
/*			*/ 
/* 1451 */		 this.fireTicks += 1;
/* 1452 */		 if (this.fireTicks == 0)
/*			*/		 {
/* 1454 */			 EntityCombustByEntityEvent entityCombustEvent = new EntityCombustByEntityEvent(stormBukkitEntity, thisBukkitEntity, 8);
/* 1455 */			 pluginManager.callEvent(entityCombustEvent);
/* 1456 */			 if (!entityCombustEvent.isCancelled())
/* 1457 */				 setOnFire(entityCombustEvent.getDuration());
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(EntityLiving entityliving)
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean i(double d0, double d1, double d2) {
/* 1466 */		 int i = MathHelper.floor(d0);
/* 1467 */		 int j = MathHelper.floor(d1);
/* 1468 */		 int k = MathHelper.floor(d2);
/* 1469 */		 double d3 = d0 - i;
/* 1470 */		 double d4 = d1 - j;
/* 1471 */		 double d5 = d2 - k;
/*			*/ 
/* 1473 */		 if (this.world.s(i, j, k)) {
/* 1474 */			 boolean flag = !this.world.s(i - 1, j, k);
/* 1475 */			 boolean flag1 = !this.world.s(i + 1, j, k);
/* 1476 */			 boolean flag2 = !this.world.s(i, j - 1, k);
/* 1477 */			 boolean flag3 = !this.world.s(i, j + 1, k);
/* 1478 */			 boolean flag4 = !this.world.s(i, j, k - 1);
/* 1479 */			 boolean flag5 = !this.world.s(i, j, k + 1);
/* 1480 */			 byte b0 = -1;
/* 1481 */			 double d6 = 9999.0D;
/*			*/ 
/* 1483 */			 if ((flag) && (d3 < d6)) {
/* 1484 */				 d6 = d3;
/* 1485 */				 b0 = 0;
/*			*/			 }
/*			*/ 
/* 1488 */			 if ((flag1) && (1.0D - d3 < d6)) {
/* 1489 */				 d6 = 1.0D - d3;
/* 1490 */				 b0 = 1;
/*			*/			 }
/*			*/ 
/* 1493 */			 if ((flag2) && (d4 < d6)) {
/* 1494 */				 d6 = d4;
/* 1495 */				 b0 = 2;
/*			*/			 }
/*			*/ 
/* 1498 */			 if ((flag3) && (1.0D - d4 < d6)) {
/* 1499 */				 d6 = 1.0D - d4;
/* 1500 */				 b0 = 3;
/*			*/			 }
/*			*/ 
/* 1503 */			 if ((flag4) && (d5 < d6)) {
/* 1504 */				 d6 = d5;
/* 1505 */				 b0 = 4;
/*			*/			 }
/*			*/ 
/* 1508 */			 if ((flag5) && (1.0D - d5 < d6)) {
/* 1509 */				 d6 = 1.0D - d5;
/* 1510 */				 b0 = 5;
/*			*/			 }
/*			*/ 
/* 1513 */			 float f = this.random.nextFloat() * 0.2F + 0.1F;
/*			*/ 
/* 1515 */			 if (b0 == 0) {
/* 1516 */				 this.motX = (-f);
/*			*/			 }
/*			*/ 
/* 1519 */			 if (b0 == 1) {
/* 1520 */				 this.motX = f;
/*			*/			 }
/*			*/ 
/* 1523 */			 if (b0 == 2) {
/* 1524 */				 this.motY = (-f);
/*			*/			 }
/*			*/ 
/* 1527 */			 if (b0 == 3) {
/* 1528 */				 this.motY = f;
/*			*/			 }
/*			*/ 
/* 1531 */			 if (b0 == 4) {
/* 1532 */				 this.motZ = (-f);
/*			*/			 }
/*			*/ 
/* 1535 */			 if (b0 == 5) {
/* 1536 */				 this.motZ = f;
/*			*/			 }
/*			*/ 
/* 1539 */			 return true;
/*			*/		 }
/* 1541 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public void aj()
/*			*/	 {
/* 1546 */		 this.J = true;
/* 1547 */		 this.fallDistance = 0.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public String getLocalizedName() {
/* 1551 */		 String s = EntityTypes.b(this);
/*			*/ 
/* 1553 */		 if (s == null) {
/* 1554 */			 s = "generic";
/*			*/		 }
/*			*/ 
/* 1557 */		 return LocaleI18n.get("entity." + s + ".name");
/*			*/	 }
/*			*/ 
/*			*/	 public Entity[] al() {
/* 1561 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean i(Entity entity) {
/* 1565 */		 return this == entity;
/*			*/	 }
/*			*/ 
/*			*/	 public float am() {
/* 1569 */		 return 0.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean an() {
/* 1573 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public String toString() {
/* 1577 */		 return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", new Object[] { getClass().getSimpleName(), getLocalizedName(), Integer.valueOf(this.id), this.world == null ? "~NULL~" : this.world.getWorldData().getName(), Double.valueOf(this.locX), Double.valueOf(this.locY), Double.valueOf(this.locZ) });
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Entity
 * JD-Core Version:		0.6.0
 */