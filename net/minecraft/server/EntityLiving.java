/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.ArrayList;
/*			*/ import java.util.Collection;
/*			*/ import java.util.HashMap;
/*			*/ import java.util.Iterator;
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ import java.util.Set;
/*			*/ import org.bukkit.craftbukkit.CraftServer;
/*			*/ import org.bukkit.craftbukkit.TrigMath;
/*			*/ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*			*/ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*			*/ import org.bukkit.event.entity.EntityDamageByBlockEvent;
/*			*/ import org.bukkit.event.entity.EntityDamageEvent;
/*			*/ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*			*/ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*			*/ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*			*/ import org.bukkit.plugin.PluginManager;
/*			*/ 
/*			*/ public abstract class EntityLiving extends Entity
/*			*/ {
/*	 18 */	 public int maxNoDamageTicks = 20;
/*			*/	 public float ao;
/*			*/	 public float ap;
/*	 21 */	 public float aq = 0.0F;
/*	 22 */	 public float ar = 0.0F;
/*	 23 */	 public float as = 0.0F;
/*	 24 */	 public float at = 0.0F;
/*			*/	 protected float au;
/*			*/	 protected float av;
/*			*/	 protected float aw;
/*			*/	 protected float ax;
/*	 29 */	 protected boolean ay = true;
/*	 30 */	 protected String texture = "/mob/char.png";
/*	 31 */	 protected boolean aA = true;
/*	 32 */	 protected float aB = 0.0F;
/*	 33 */	 protected String aC = null;
/*	 34 */	 protected float aD = 1.0F;
/*	 35 */	 protected int aE = 0;
/*	 36 */	 protected float aF = 0.0F;
/*	 37 */	 public float aG = 0.1F;
/*	 38 */	 public float aH = 0.02F;
/*			*/	 public float aI;
/*			*/	 public float aJ;
/*	 41 */	 protected int health = getMaxHealth();
/*			*/	 public int aL;
/*			*/	 protected int aM;
/*			*/	 private int a;
/*			*/	 public int hurtTicks;
/*			*/	 public int aO;
/*	 47 */	 public float aP = 0.0F;
/*	 48 */	 public int deathTicks = 0;
/*	 49 */	 public int attackTicks = 0;
/*			*/	 public float aS;
/*			*/	 public float aT;
/*	 52 */	 protected boolean aU = false;
/*			*/	 protected int aV;
/*	 54 */	 public int aW = -1;
/*	 55 */	 public float aX = (float)(Math.random() * 0.8999999761581421D + 0.1000000014901161D);
/*			*/	 public float aY;
/*			*/	 public float aZ;
/*			*/	 public float ba;
/*	 59 */	 public EntityHuman killer = null;
/*	 60 */	 protected int lastDamageByPlayerTime = 0;
/*	 61 */	 public EntityLiving lastDamager = null;
/*	 62 */	 private int c = 0;
/*	 63 */	 private EntityLiving d = null;
/*	 64 */	 public int bd = 0;
/*	 65 */	 public int be = 0;
/*	 66 */	 public HashMap effects = new HashMap();
/*	 67 */	 public boolean updateEffects = true;
/*			*/	 private int f;
/*			*/	 private ControllerLook lookController;
/*			*/	 private ControllerMove moveController;
/*			*/	 private ControllerJump jumpController;
/*			*/	 private EntityAIBodyControl senses;
/*			*/	 private Navigation navigation;
/*			*/	 protected final PathfinderGoalSelector goalSelector;
/*			*/	 protected final PathfinderGoalSelector targetSelector;
/*			*/	 private EntityLiving bz;
/*			*/	 private EntitySenses bA;
/*			*/	 private float bB;
/*	 79 */	 private ChunkCoordinates bC = new ChunkCoordinates(0, 0, 0);
/*	 80 */	 private float bD = -1.0F;
/*			*/	 protected int bi;
/*			*/	 protected double bj;
/*			*/	 protected double bk;
/*			*/	 protected double bl;
/*			*/	 protected double bm;
/*			*/	 protected double bn;
/*	 87 */	 float bo = 0.0F;
/*	 88 */	 public int lastDamage = 0;
/*	 89 */	 protected int bq = 0;
/*			*/	 protected float br;
/*			*/	 protected float bs;
/*			*/	 protected float bt;
/*	 93 */	 protected boolean bu = false;
/*	 94 */	 protected float bv = 0.0F;
/*	 95 */	 protected float bw = 0.7F;
/*	 96 */	 private int bE = 0;
/*			*/	 private Entity bF;
/*	 98 */	 protected int bx = 0;
/*	 99 */	 public int expToDrop = 0;
/*	100 */	 public int maxAirTicks = 300;
/*			*/ 
/*			*/	 public EntityLiving(World world) {
/*	103 */		 super(world);
/*	104 */		 this.m = true;
/*	105 */		 this.goalSelector = new PathfinderGoalSelector((world != null) && (world.methodProfiler != null) ? world.methodProfiler : null);
/*	106 */		 this.targetSelector = new PathfinderGoalSelector((world != null) && (world.methodProfiler != null) ? world.methodProfiler : null);
/*	107 */		 this.lookController = new ControllerLook(this);
/*	108 */		 this.moveController = new ControllerMove(this);
/*	109 */		 this.jumpController = new ControllerJump(this);
/*	110 */		 this.senses = new EntityAIBodyControl(this);
/*	111 */		 this.navigation = new Navigation(this, world, 16.0F);
/*	112 */		 this.bA = new EntitySenses(this);
/*	113 */		 this.ap = ((float)(Math.random() + 1.0D) * 0.01F);
/*	114 */		 setPosition(this.locX, this.locY, this.locZ);
/*	115 */		 this.ao = ((float)Math.random() * 12398.0F);
/*	116 */		 this.yaw = (float)(Math.random() * 3.141592741012573D * 2.0D);
/*	117 */		 this.as = this.yaw;
/*	118 */		 this.W = 0.5F;
/*			*/	 }
/*			*/ 
/*			*/	 public ControllerLook getControllerLook() {
/*	122 */		 return this.lookController;
/*			*/	 }
/*			*/ 
/*			*/	 public ControllerMove getControllerMove() {
/*	126 */		 return this.moveController;
/*			*/	 }
/*			*/ 
/*			*/	 public ControllerJump getControllerJump() {
/*	130 */		 return this.jumpController;
/*			*/	 }
/*			*/ 
/*			*/	 public Navigation getNavigation() {
/*	134 */		 return this.navigation;
/*			*/	 }
/*			*/ 
/*			*/	 public EntitySenses at() {
/*	138 */		 return this.bA;
/*			*/	 }
/*			*/ 
/*			*/	 public Random au() {
/*	142 */		 return this.random;
/*			*/	 }
/*			*/ 
/*			*/	 public EntityLiving av() {
/*	146 */		 return this.lastDamager;
/*			*/	 }
/*			*/ 
/*			*/	 public EntityLiving aw() {
/*	150 */		 return this.d;
/*			*/	 }
/*			*/ 
/*			*/	 public void j(Entity entity) {
/*	154 */		 if ((entity instanceof EntityLiving))
/*	155 */			 this.d = ((EntityLiving)entity);
/*			*/	 }
/*			*/ 
/*			*/	 public int ax()
/*			*/	 {
/*	160 */		 return this.bq;
/*			*/	 }
/*			*/ 
/*			*/	 public float am() {
/*	164 */		 return this.as;
/*			*/	 }
/*			*/ 
/*			*/	 public float ay() {
/*	168 */		 return this.bB;
/*			*/	 }
/*			*/ 
/*			*/	 public void e(float f) {
/*	172 */		 this.bB = f;
/*	173 */		 f(f);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean k(Entity entity) {
/*	177 */		 j(entity);
/*	178 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public EntityLiving az() {
/*	182 */		 return this.bz;
/*			*/	 }
/*			*/ 
/*			*/	 public void b(EntityLiving entityliving) {
/*	186 */		 this.bz = entityliving;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(Class oclass) {
/*	190 */		 return (EntityCreeper.class != oclass) && (EntityGhast.class != oclass);
/*			*/	 }
/*			*/	 public void aA() {
/*			*/	 }
/*			*/ 
/*			*/	 public boolean aB() {
/*	196 */		 return d(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean d(int i, int j, int k) {
/*	200 */		 return this.bD == -1.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public void b(int i, int j, int k, int l) {
/*	204 */		 this.bC.b(i, j, k);
/*	205 */		 this.bD = l;
/*			*/	 }
/*			*/ 
/*			*/	 public ChunkCoordinates aC() {
/*	209 */		 return this.bC;
/*			*/	 }
/*			*/ 
/*			*/	 public float aD() {
/*	213 */		 return this.bD;
/*			*/	 }
/*			*/ 
/*			*/	 public void aE() {
/*	217 */		 this.bD = -1.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean aF() {
/*	221 */		 return this.bD != -1.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public void c(EntityLiving entityliving) {
/*	225 */		 this.lastDamager = entityliving;
/*	226 */		 this.c = (this.lastDamager != null ? 60 : 0);
/*			*/	 }
/*			*/ 
/*			*/	 protected void a() {
/*	230 */		 this.datawatcher.a(8, Integer.valueOf(this.f));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean l(Entity entity) {
/*	234 */		 return this.world.a(Vec3D.a().create(this.locX, this.locY + getHeadHeight(), this.locZ), Vec3D.a().create(entity.locX, entity.locY + entity.getHeadHeight(), entity.locZ)) == null;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean L() {
/*	238 */		 return !this.dead;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean M() {
/*	242 */		 return !this.dead;
/*			*/	 }
/*			*/ 
/*			*/	 public float getHeadHeight() {
/*	246 */		 return this.length * 0.85F;
/*			*/	 }
/*			*/ 
/*			*/	 public int aG() {
/*	250 */		 return 80;
/*			*/	 }
/*			*/ 
/*			*/	 public void aH() {
/*	254 */		 String s = aQ();
/*			*/ 
/*	256 */		 if (s != null)
/*	257 */			 this.world.makeSound(this, s, aP(), i());
/*			*/	 }
/*			*/ 
/*			*/	 public void z()
/*			*/	 {
/*	262 */		 this.aI = this.aJ;
/*	263 */		 super.z();
/*			*/ 
/*	265 */		 if ((isAlive()) && (this.random.nextInt(1000) < this.a++)) {
/*	266 */			 this.a = (-aG());
/*	267 */			 aH();
/*			*/		 }
/*			*/ 
/*	271 */		 if ((isAlive()) && (inBlock()) && (!(this instanceof EntityEnderDragon))) {
/*	272 */			 EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.SUFFOCATION, 1);
/*	273 */			 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	275 */			 if (!event.isCancelled()) {
/*	276 */				 event.getEntity().setLastDamageCause(event);
/*	277 */				 damageEntity(DamageSource.STUCK, event.getDamage());
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	282 */		 if ((isFireproof()) || (this.world.isStatic)) {
/*	283 */			 extinguish();
/*			*/		 }
/*			*/ 
/*	286 */		 if ((isAlive()) && (a(Material.WATER)) && (!aU()) && (!this.effects.containsKey(Integer.valueOf(MobEffectList.WATER_BREATHING.id)))) {
/*	287 */			 setAirTicks(h(getAirTicks()));
/*	288 */			 if (getAirTicks() == -20) {
/*	289 */				 setAirTicks(0);
/*			*/ 
/*	291 */				 for (int i = 0; i < 8; i++) {
/*	292 */					 float f = this.random.nextFloat() - this.random.nextFloat();
/*	293 */					 float f1 = this.random.nextFloat() - this.random.nextFloat();
/*	294 */					 float f2 = this.random.nextFloat() - this.random.nextFloat();
/*			*/ 
/*	296 */					 this.world.a("bubble", this.locX + f, this.locY + f1, this.locZ + f2, this.motX, this.motY, this.motZ);
/*			*/				 }
/*			*/ 
/*	300 */				 EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.DROWNING, 2);
/*	301 */				 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	303 */				 if ((!event.isCancelled()) && (event.getDamage() != 0)) {
/*	304 */					 event.getEntity().setLastDamageCause(event);
/*	305 */					 damageEntity(DamageSource.DROWN, event.getDamage());
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/*	310 */			 extinguish();
/*			*/		 }
/*	313 */		 else if (getAirTicks() != 300) {
/*	314 */			 setAirTicks(this.maxAirTicks);
/*			*/		 }
/*			*/ 
/*	319 */		 this.aS = this.aT;
/*	320 */		 if (this.attackTicks > 0) {
/*	321 */			 this.attackTicks -= 1;
/*			*/		 }
/*			*/ 
/*	324 */		 if (this.hurtTicks > 0) {
/*	325 */			 this.hurtTicks -= 1;
/*			*/		 }
/*			*/ 
/*	328 */		 if (this.noDamageTicks > 0) {
/*	329 */			 this.noDamageTicks -= 1;
/*			*/		 }
/*			*/ 
/*	332 */		 if (this.health <= 0) {
/*	333 */			 aI();
/*			*/		 }
/*			*/ 
/*	336 */		 if (this.lastDamageByPlayerTime > 0)
/*	337 */			 this.lastDamageByPlayerTime -= 1;
/*			*/		 else {
/*	339 */			 this.killer = null;
/*			*/		 }
/*			*/ 
/*	342 */		 if ((this.d != null) && (!this.d.isAlive())) {
/*	343 */			 this.d = null;
/*			*/		 }
/*			*/ 
/*	346 */		 if (this.lastDamager != null) {
/*	347 */			 if (!this.lastDamager.isAlive())
/*	348 */				 c((EntityLiving)null);
/*	349 */			 else if (this.c > 0)
/*	350 */				 this.c -= 1;
/*			*/			 else {
/*	352 */				 c((EntityLiving)null);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	356 */		 bo();
/*	357 */		 this.ax = this.aw;
/*	358 */		 this.ar = this.aq;
/*	359 */		 this.at = this.as;
/*	360 */		 this.lastYaw = this.yaw;
/*	361 */		 this.lastPitch = this.pitch;
/*			*/	 }
/*			*/ 
/*			*/	 public int getExpReward()
/*			*/	 {
/*	367 */		 int exp = getExpValue(this.killer);
/*			*/ 
/*	369 */		 if ((!this.world.isStatic) && ((this.lastDamageByPlayerTime > 0) || (alwaysGivesExp())) && (!isBaby())) {
/*	370 */			 return exp;
/*			*/		 }
/*	372 */		 return 0;
/*			*/	 }
/*			*/ 
/*			*/	 protected void aI()
/*			*/	 {
/*	378 */		 this.deathTicks += 1;
/*	379 */		 if ((this.deathTicks >= 20) && (!this.dead))
/*			*/		 {
/*	383 */			 int i = this.expToDrop;
/*	384 */			 while (i > 0) {
/*	385 */				 int j = EntityExperienceOrb.getOrbValue(i);
/*			*/ 
/*	387 */				 i -= j;
/*	388 */				 this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
/*			*/			 }
/*			*/ 
/*	392 */			 die();
/*			*/ 
/*	394 */			 for (i = 0; i < 20; i++) {
/*	395 */				 double d0 = this.random.nextGaussian() * 0.02D;
/*	396 */				 double d1 = this.random.nextGaussian() * 0.02D;
/*	397 */				 double d2 = this.random.nextGaussian() * 0.02D;
/*			*/ 
/*	399 */				 this.world.a("explode", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected int h(int i) {
/*	405 */		 return i - 1;
/*			*/	 }
/*			*/ 
/*			*/	 protected int getExpValue(EntityHuman entityhuman) {
/*	409 */		 return this.aV;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean alwaysGivesExp() {
/*	413 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public void aK() {
/*	417 */		 for (int i = 0; i < 20; i++) {
/*	418 */			 double d0 = this.random.nextGaussian() * 0.02D;
/*	419 */			 double d1 = this.random.nextGaussian() * 0.02D;
/*	420 */			 double d2 = this.random.nextGaussian() * 0.02D;
/*	421 */			 double d3 = 10.0D;
/*			*/ 
/*	423 */			 this.world.a("explode", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width - d0 * d3, this.locY + this.random.nextFloat() * this.length - d1 * d3, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width - d2 * d3, d0, d1, d2);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void U() {
/*	428 */		 super.U();
/*	429 */		 this.au = this.av;
/*	430 */		 this.av = 0.0F;
/*	431 */		 this.fallDistance = 0.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public void h_() {
/*	435 */		 super.h_();
/*	436 */		 if (this.bd > 0) {
/*	437 */			 if (this.be <= 0) {
/*	438 */				 this.be = 60;
/*			*/			 }
/*			*/ 
/*	441 */			 this.be -= 1;
/*	442 */			 if (this.be <= 0) {
/*	443 */				 this.bd -= 1;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	447 */		 d();
/*	448 */		 double d0 = this.locX - this.lastX;
/*	449 */		 double d1 = this.locZ - this.lastZ;
/*	450 */		 float f = (float)(d0 * d0 + d1 * d1);
/*	451 */		 float f1 = this.aq;
/*	452 */		 float f2 = 0.0F;
/*			*/ 
/*	454 */		 this.au = this.av;
/*	455 */		 float f3 = 0.0F;
/*			*/ 
/*	457 */		 if (f > 0.0025F) {
/*	458 */			 f3 = 1.0F;
/*	459 */			 f2 = (float)Math.sqrt(f) * 3.0F;
/*			*/ 
/*	461 */			 f1 = (float)TrigMath.atan2(d1, d0) * 180.0F / 3.141593F - 90.0F;
/*			*/		 }
/*			*/ 
/*	464 */		 if (this.aJ > 0.0F) {
/*	465 */			 f1 = this.yaw;
/*			*/		 }
/*			*/ 
/*	468 */		 if (!this.onGround) {
/*	469 */			 f3 = 0.0F;
/*			*/		 }
/*			*/ 
/*	472 */		 this.av += (f3 - this.av) * 0.3F;
/*	473 */		 this.world.methodProfiler.a("headTurn");
/*	474 */		 if (aV()) {
/*	475 */			 this.senses.a();
/*			*/		 } else {
/*	477 */			 float f4 = MathHelper.g(f1 - this.aq);
/*			*/ 
/*	479 */			 this.aq += f4 * 0.3F;
/*	480 */			 float f5 = MathHelper.g(this.yaw - this.aq);
/*	481 */			 boolean flag = (f5 < -90.0F) || (f5 >= 90.0F);
/*			*/ 
/*	483 */			 if (f5 < -75.0F) {
/*	484 */				 f5 = -75.0F;
/*			*/			 }
/*			*/ 
/*	487 */			 if (f5 >= 75.0F) {
/*	488 */				 f5 = 75.0F;
/*			*/			 }
/*			*/ 
/*	491 */			 this.aq = (this.yaw - f5);
/*	492 */			 if (f5 * f5 > 2500.0F) {
/*	493 */				 this.aq += f5 * 0.2F;
/*			*/			 }
/*			*/ 
/*	496 */			 if (flag) {
/*	497 */				 f2 *= -1.0F;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	501 */		 this.world.methodProfiler.b();
/*	502 */		 this.world.methodProfiler.a("rangeChecks");
/*			*/ 
/*	504 */		 while (this.yaw - this.lastYaw < -180.0F) {
/*	505 */			 this.lastYaw -= 360.0F;
/*			*/		 }
/*			*/ 
/*	508 */		 while (this.yaw - this.lastYaw >= 180.0F) {
/*	509 */			 this.lastYaw += 360.0F;
/*			*/		 }
/*			*/ 
/*	512 */		 while (this.aq - this.ar < -180.0F) {
/*	513 */			 this.ar -= 360.0F;
/*			*/		 }
/*			*/ 
/*	516 */		 while (this.aq - this.ar >= 180.0F) {
/*	517 */			 this.ar += 360.0F;
/*			*/		 }
/*			*/ 
/*	520 */		 while (this.pitch - this.lastPitch < -180.0F) {
/*	521 */			 this.lastPitch -= 360.0F;
/*			*/		 }
/*			*/ 
/*	524 */		 while (this.pitch - this.lastPitch >= 180.0F) {
/*	525 */			 this.lastPitch += 360.0F;
/*			*/		 }
/*			*/ 
/*	528 */		 while (this.as - this.at < -180.0F) {
/*	529 */			 this.at -= 360.0F;
/*			*/		 }
/*			*/ 
/*	532 */		 while (this.as - this.at >= 180.0F) {
/*	533 */			 this.at += 360.0F;
/*			*/		 }
/*			*/ 
/*	536 */		 this.world.methodProfiler.b();
/*	537 */		 this.aw += f2;
/*			*/	 }
/*			*/ 
/*			*/	 public void heal(int i)
/*			*/	 {
/*	542 */		 heal(i, EntityRegainHealthEvent.RegainReason.CUSTOM);
/*			*/	 }
/*			*/ 
/*			*/	 public void heal(int i, EntityRegainHealthEvent.RegainReason regainReason) {
/*	546 */		 if (this.health > 0) {
/*	547 */			 EntityRegainHealthEvent event = new EntityRegainHealthEvent(getBukkitEntity(), i, regainReason);
/*	548 */			 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	550 */			 if (!event.isCancelled()) {
/*	551 */				 this.health += event.getAmount();
/*			*/			 }
/*			*/ 
/*	555 */			 if (this.health > getMaxHealth()) {
/*	556 */				 this.health = getMaxHealth();
/*			*/			 }
/*			*/ 
/*	559 */			 this.noDamageTicks = (this.maxNoDamageTicks / 2);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public abstract int getMaxHealth();
/*			*/ 
/*			*/	 public int getHealth() {
/*	566 */		 return this.health;
/*			*/	 }
/*			*/ 
/*			*/	 public void setHealth(int i) {
/*	570 */		 this.health = i;
/*	571 */		 if (i > getMaxHealth())
/*	572 */			 i = getMaxHealth();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean damageEntity(DamageSource damagesource, int i)
/*			*/	 {
/*	577 */		 if (this.world.isStatic) {
/*	578 */			 return false;
/*			*/		 }
/*	580 */		 this.bq = 0;
/*	581 */		 if (this.health <= 0)
/*	582 */			 return false;
/*	583 */		 if ((damagesource.k()) && (hasEffect(MobEffectList.FIRE_RESISTANCE))) {
/*	584 */			 return false;
/*			*/		 }
/*	586 */		 this.aZ = 1.5F;
/*	587 */		 boolean flag = true;
/*			*/ 
/*	590 */		 if ((damagesource instanceof EntityDamageSource)) {
/*	591 */			 EntityDamageEvent event = CraftEventFactory.handleEntityDamageEvent(this, damagesource, i);
/*	592 */			 if (event.isCancelled()) {
/*	593 */				 return false;
/*			*/			 }
/*	595 */			 i = event.getDamage();
/*			*/		 }
/*			*/ 
/*	599 */		 if (this.noDamageTicks > this.maxNoDamageTicks / 2.0F) {
/*	600 */			 if (i <= this.lastDamage) {
/*	601 */				 return false;
/*			*/			 }
/*			*/ 
/*	604 */			 d(damagesource, i - this.lastDamage);
/*	605 */			 this.lastDamage = i;
/*	606 */			 flag = false;
/*			*/		 } else {
/*	608 */			 this.lastDamage = i;
/*	609 */			 this.aL = this.health;
/*	610 */			 this.noDamageTicks = this.maxNoDamageTicks;
/*	611 */			 d(damagesource, i);
/*	612 */			 this.hurtTicks = (this.aO = 10);
/*			*/		 }
/*			*/ 
/*	615 */		 this.aP = 0.0F;
/*	616 */		 Entity entity = damagesource.getEntity();
/*			*/ 
/*	618 */		 if (entity != null) {
/*	619 */			 if ((entity instanceof EntityLiving)) {
/*	620 */				 c((EntityLiving)entity);
/*			*/			 }
/*			*/ 
/*	623 */			 if ((entity instanceof EntityHuman)) {
/*	624 */				 this.lastDamageByPlayerTime = 60;
/*	625 */				 this.killer = ((EntityHuman)entity);
/*	626 */			 } else if ((entity instanceof EntityWolf)) {
/*	627 */				 EntityWolf entitywolf = (EntityWolf)entity;
/*			*/ 
/*	629 */				 if (entitywolf.isTamed()) {
/*	630 */					 this.lastDamageByPlayerTime = 60;
/*	631 */					 this.killer = null;
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	636 */		 if (flag) {
/*	637 */			 this.world.broadcastEntityEffect(this, 2);
/*	638 */			 if ((damagesource != DamageSource.DROWN) && (damagesource != DamageSource.EXPLOSION2)) {
/*	639 */				 K();
/*			*/			 }
/*			*/ 
/*	642 */			 if (entity != null) {
/*	643 */				 double d0 = entity.locX - this.locX;
/*			*/ 
/*	647 */				 for (double d1 = entity.locZ - this.locZ; d0 * d0 + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D) {
/*	648 */					 d0 = (Math.random() - Math.random()) * 0.01D;
/*			*/				 }
/*			*/ 
/*	651 */				 this.aP = ((float)(Math.atan2(d1, d0) * 180.0D / 3.141592741012573D) - this.yaw);
/*	652 */				 a(entity, i, d0, d1);
/*			*/			 } else {
/*	654 */				 this.aP = ((int)(Math.random() * 2.0D) * 180);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	658 */		 if (this.health <= 0) {
/*	659 */			 if (flag) {
/*	660 */				 this.world.makeSound(this, aS(), aP(), i());
/*			*/			 }
/*			*/ 
/*	663 */			 die(damagesource);
/*	664 */		 } else if (flag) {
/*	665 */			 this.world.makeSound(this, aR(), aP(), i());
/*			*/		 }
/*			*/ 
/*	668 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 private float i()
/*			*/	 {
/*	674 */		 return isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
/*			*/	 }
/*			*/ 
/*			*/	 public int aO() {
/*	678 */		 return 0;
/*			*/	 }
/*			*/	 protected void k(int i) {
/*			*/	 }
/*			*/ 
/*			*/	 protected int b(DamageSource damagesource, int i) {
/*	684 */		 if (!damagesource.ignoresArmor()) {
/*	685 */			 int j = 25 - aO();
/*	686 */			 int k = i * j + this.aM;
/*			*/ 
/*	688 */			 k(i);
/*	689 */			 i = k / 25;
/*	690 */			 this.aM = (k % 25);
/*			*/		 }
/*			*/ 
/*	693 */		 return i;
/*			*/	 }
/*			*/ 
/*			*/	 protected int c(DamageSource damagesource, int i) {
/*	697 */		 if (hasEffect(MobEffectList.RESISTANCE)) {
/*	698 */			 int j = (getEffect(MobEffectList.RESISTANCE).getAmplifier() + 1) * 5;
/*	699 */			 int k = 25 - j;
/*	700 */			 int l = i * k + this.aM;
/*			*/ 
/*	702 */			 i = l / 25;
/*	703 */			 this.aM = (l % 25);
/*			*/		 }
/*			*/ 
/*	706 */		 return i;
/*			*/	 }
/*			*/ 
/*			*/	 protected void d(DamageSource damagesource, int i) {
/*	710 */		 i = b(damagesource, i);
/*	711 */		 i = c(damagesource, i);
/*	712 */		 this.health -= i;
/*			*/	 }
/*			*/ 
/*			*/	 protected float aP() {
/*	716 */		 return 1.0F;
/*			*/	 }
/*			*/ 
/*			*/	 protected String aQ() {
/*	720 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 protected String aR() {
/*	724 */		 return "damage.hurtflesh";
/*			*/	 }
/*			*/ 
/*			*/	 protected String aS() {
/*	728 */		 return "damage.hurtflesh";
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Entity entity, int i, double d0, double d1) {
/*	732 */		 this.al = true;
/*	733 */		 float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
/*	734 */		 float f1 = 0.4F;
/*			*/ 
/*	736 */		 this.motX /= 2.0D;
/*	737 */		 this.motY /= 2.0D;
/*	738 */		 this.motZ /= 2.0D;
/*	739 */		 this.motX -= d0 / f * f1;
/*	740 */		 this.motY += f1;
/*	741 */		 this.motZ -= d1 / f * f1;
/*	742 */		 if (this.motY > 0.4000000059604645D)
/*	743 */			 this.motY = 0.4000000059604645D;
/*			*/	 }
/*			*/ 
/*			*/	 public void die(DamageSource damagesource)
/*			*/	 {
/*	748 */		 Entity entity = damagesource.getEntity();
/*			*/ 
/*	750 */		 if ((this.aE >= 0) && (entity != null)) {
/*	751 */			 entity.c(this, this.aE);
/*			*/		 }
/*			*/ 
/*	754 */		 if (entity != null) {
/*	755 */			 entity.a(this);
/*			*/		 }
/*			*/ 
/*	758 */		 this.aU = true;
/*	759 */		 if (!this.world.isStatic) {
/*	760 */			 int i = 0;
/*			*/ 
/*	762 */			 if ((entity instanceof EntityHuman)) {
/*	763 */				 i = EnchantmentManager.getBonusMonsterLootEnchantmentLevel(((EntityHuman)entity).inventory);
/*			*/			 }
/*			*/ 
/*	766 */			 if (!isBaby()) {
/*	767 */				 dropDeathLoot(this.lastDamageByPlayerTime > 0, i);
/*			*/			 }
/*			*/			 else
/*			*/			 {
/*	776 */				 CraftEventFactory.callEntityDeathEvent(this);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	780 */		 this.world.broadcastEntityEffect(this, 3);
/*			*/	 }
/*			*/ 
/*			*/	 protected ItemStack l(int i)
/*			*/	 {
/*	785 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 protected void dropDeathLoot(boolean flag, int i)
/*			*/	 {
/*	791 */		 List loot = new ArrayList();
/*	792 */		 int j = getLootId();
/*			*/ 
/*	794 */		 if (j > 0) {
/*	795 */			 int k = this.random.nextInt(3);
/*			*/ 
/*	797 */			 if (i > 0) {
/*	798 */				 k += this.random.nextInt(i + 1);
/*			*/			 }
/*			*/ 
/*	801 */			 if (k > 0) {
/*	802 */				 loot.add(new org.bukkit.inventory.ItemStack(j, k));
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	807 */		 if (this.lastDamageByPlayerTime > 0) {
/*	808 */			 int k = this.random.nextInt(200) - i;
/*			*/ 
/*	810 */			 if (k < 5) {
/*	811 */				 ItemStack itemstack = l(k <= 0 ? 1 : 0);
/*	812 */				 if (itemstack != null) {
/*	813 */					 loot.add(new CraftItemStack(itemstack));
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	818 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*			*/	 }
/*			*/ 
/*			*/	 protected int getLootId()
/*			*/	 {
/*	823 */		 return 0;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(float f) {
/*	827 */		 super.a(f);
/*	828 */		 int i = MathHelper.f(f - 3.0F);
/*			*/ 
/*	830 */		 if (i > 0)
/*			*/		 {
/*	832 */			 EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.FALL, i);
/*	833 */			 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	835 */			 if ((!event.isCancelled()) && (event.getDamage() != 0)) {
/*	836 */				 i = event.getDamage();
/*			*/ 
/*	838 */				 if (i > 4)
/*	839 */					 this.world.makeSound(this, "damage.fallbig", 1.0F, 1.0F);
/*			*/				 else {
/*	841 */					 this.world.makeSound(this, "damage.fallsmall", 1.0F, 1.0F);
/*			*/				 }
/*			*/ 
/*	844 */				 getBukkitEntity().setLastDamageCause(event);
/*	845 */				 damageEntity(DamageSource.FALL, i);
/*			*/			 }
/*			*/ 
/*	849 */			 int j = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.locY - 0.2000000029802322D - this.height), MathHelper.floor(this.locZ));
/*			*/ 
/*	851 */			 if (j > 0) {
/*	852 */				 StepSound stepsound = Block.byId[j].stepSound;
/*			*/ 
/*	854 */				 this.world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.5F, stepsound.getVolume2() * 0.75F);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void e(float f, float f1)
/*			*/	 {
/*	862 */		 if ((H()) && ((!(this instanceof EntityHuman)) || (!((EntityHuman)this).abilities.isFlying))) {
/*	863 */			 double d0 = this.locY;
/*	864 */			 a(f, f1, aV() ? 0.04F : 0.02F);
/*	865 */			 move(this.motX, this.motY, this.motZ);
/*	866 */			 this.motX *= 0.800000011920929D;
/*	867 */			 this.motY *= 0.800000011920929D;
/*	868 */			 this.motZ *= 0.800000011920929D;
/*	869 */			 this.motY -= 0.02D;
/*	870 */			 if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d0, this.motZ)))
/*	871 */				 this.motY = 0.300000011920929D;
/*			*/		 }
/*	873 */		 else if ((J()) && ((!(this instanceof EntityHuman)) || (!((EntityHuman)this).abilities.isFlying))) {
/*	874 */			 double d0 = this.locY;
/*	875 */			 a(f, f1, 0.02F);
/*	876 */			 move(this.motX, this.motY, this.motZ);
/*	877 */			 this.motX *= 0.5D;
/*	878 */			 this.motY *= 0.5D;
/*	879 */			 this.motZ *= 0.5D;
/*	880 */			 this.motY -= 0.02D;
/*	881 */			 if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d0, this.motZ)))
/*	882 */				 this.motY = 0.300000011920929D;
/*			*/		 }
/*			*/		 else {
/*	885 */			 float f2 = 0.91F;
/*			*/ 
/*	887 */			 if (this.onGround) {
/*	888 */				 f2 = 0.5460001F;
/*	889 */				 int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));
/*			*/ 
/*	891 */				 if (i > 0) {
/*	892 */					 f2 = Block.byId[i].frictionFactor * 0.91F;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	896 */			 float f3 = 0.1627714F / (f2 * f2 * f2);
/*			*/			 float f4;
/*	899 */			 if (this.onGround)
/*			*/			 {
/*			*/				 float f4;
/*			*/				 float f4;
/*	900 */				 if (aV())
/*	901 */					 f4 = ay();
/*			*/				 else {
/*	903 */					 f4 = this.aG;
/*			*/				 }
/*			*/ 
/*	906 */				 f4 *= f3;
/*			*/			 } else {
/*	908 */				 f4 = this.aH;
/*			*/			 }
/*			*/ 
/*	911 */			 a(f, f1, f4);
/*	912 */			 f2 = 0.91F;
/*	913 */			 if (this.onGround) {
/*	914 */				 f2 = 0.5460001F;
/*	915 */				 int j = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));
/*			*/ 
/*	917 */				 if (j > 0) {
/*	918 */					 f2 = Block.byId[j].frictionFactor * 0.91F;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	922 */			 if (f_()) {
/*	923 */				 float f5 = 0.15F;
/*			*/ 
/*	925 */				 if (this.motX < -f5) {
/*	926 */					 this.motX = (-f5);
/*			*/				 }
/*			*/ 
/*	929 */				 if (this.motX > f5) {
/*	930 */					 this.motX = f5;
/*			*/				 }
/*			*/ 
/*	933 */				 if (this.motZ < -f5) {
/*	934 */					 this.motZ = (-f5);
/*			*/				 }
/*			*/ 
/*	937 */				 if (this.motZ > f5) {
/*	938 */					 this.motZ = f5;
/*			*/				 }
/*			*/ 
/*	941 */				 this.fallDistance = 0.0F;
/*	942 */				 if (this.motY < -0.15D) {
/*	943 */					 this.motY = -0.15D;
/*			*/				 }
/*			*/ 
/*	946 */				 boolean flag = (isSneaking()) && ((this instanceof EntityHuman));
/*			*/ 
/*	948 */				 if ((flag) && (this.motY < 0.0D)) {
/*	949 */					 this.motY = 0.0D;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	953 */			 move(this.motX, this.motY, this.motZ);
/*	954 */			 if ((this.positionChanged) && (f_())) {
/*	955 */				 this.motY = 0.2D;
/*			*/			 }
/*			*/ 
/*	958 */			 this.motY -= 0.08D;
/*	959 */			 this.motY *= 0.9800000190734863D;
/*	960 */			 this.motX *= f2;
/*	961 */			 this.motZ *= f2;
/*			*/		 }
/*			*/ 
/*	964 */		 this.aY = this.aZ;
/*	965 */		 double d0 = this.locX - this.lastX;
/*	966 */		 double d1 = this.locZ - this.lastZ;
/*	967 */		 float f6 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
/*			*/ 
/*	969 */		 if (f6 > 1.0F) {
/*	970 */			 f6 = 1.0F;
/*			*/		 }
/*			*/ 
/*	973 */		 this.aZ += (f6 - this.aZ) * 0.4F;
/*	974 */		 this.ba += this.aZ;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean f_() {
/*	978 */		 int i = MathHelper.floor(this.locX);
/*	979 */		 int j = MathHelper.floor(this.boundingBox.b);
/*	980 */		 int k = MathHelper.floor(this.locZ);
/*	981 */		 int l = this.world.getTypeId(i, j, k);
/*			*/ 
/*	983 */		 return (l == Block.LADDER.id) || (l == Block.VINE.id);
/*			*/	 }
/*			*/ 
/*			*/	 public void b(NBTTagCompound nbttagcompound) {
/*	987 */		 nbttagcompound.setShort("Health", (short)this.health);
/*	988 */		 nbttagcompound.setShort("HurtTime", (short)this.hurtTicks);
/*	989 */		 nbttagcompound.setShort("DeathTime", (short)this.deathTicks);
/*	990 */		 nbttagcompound.setShort("AttackTime", (short)this.attackTicks);
/*	991 */		 if (!this.effects.isEmpty()) {
/*	992 */			 NBTTagList nbttaglist = new NBTTagList();
/*	993 */			 Iterator iterator = this.effects.values().iterator();
/*			*/ 
/*	995 */			 while (iterator.hasNext()) {
/*	996 */				 MobEffect mobeffect = (MobEffect)iterator.next();
/*	997 */				 NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*			*/ 
/*	999 */				 nbttagcompound1.setByte("Id", (byte)mobeffect.getEffectId());
/* 1000 */				 nbttagcompound1.setByte("Amplifier", (byte)mobeffect.getAmplifier());
/* 1001 */				 nbttagcompound1.setInt("Duration", mobeffect.getDuration());
/* 1002 */				 nbttaglist.add(nbttagcompound1);
/*			*/			 }
/*			*/ 
/* 1005 */			 nbttagcompound.set("ActiveEffects", nbttaglist);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(NBTTagCompound nbttagcompound) {
/* 1010 */		 if (this.health < -32768) {
/* 1011 */			 this.health = -32768;
/*			*/		 }
/*			*/ 
/* 1014 */		 this.health = nbttagcompound.getShort("Health");
/* 1015 */		 if (!nbttagcompound.hasKey("Health")) {
/* 1016 */			 this.health = getMaxHealth();
/*			*/		 }
/*			*/ 
/* 1019 */		 this.hurtTicks = nbttagcompound.getShort("HurtTime");
/* 1020 */		 this.deathTicks = nbttagcompound.getShort("DeathTime");
/* 1021 */		 this.attackTicks = nbttagcompound.getShort("AttackTime");
/* 1022 */		 if (nbttagcompound.hasKey("ActiveEffects")) {
/* 1023 */			 NBTTagList nbttaglist = nbttagcompound.getList("ActiveEffects");
/*			*/ 
/* 1025 */			 for (int i = 0; i < nbttaglist.size(); i++) {
/* 1026 */				 NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
/* 1027 */				 byte b0 = nbttagcompound1.getByte("Id");
/* 1028 */				 byte b1 = nbttagcompound1.getByte("Amplifier");
/* 1029 */				 int j = nbttagcompound1.getInt("Duration");
/*			*/ 
/* 1031 */				 this.effects.put(Integer.valueOf(b0), new MobEffect(b0, j, b1));
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isAlive() {
/* 1037 */		 return (!this.dead) && (this.health > 0);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean aU() {
/* 1041 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public void f(float f) {
/* 1045 */		 this.bs = f;
/*			*/	 }
/*			*/ 
/*			*/	 public void d(boolean flag) {
/* 1049 */		 this.bu = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public void d() {
/* 1053 */		 if (this.bE > 0) {
/* 1054 */			 this.bE -= 1;
/*			*/		 }
/*			*/ 
/* 1057 */		 if (this.bi > 0) {
/* 1058 */			 double d0 = this.locX + (this.bj - this.locX) / this.bi;
/* 1059 */			 double d1 = this.locY + (this.bk - this.locY) / this.bi;
/* 1060 */			 double d2 = this.locZ + (this.bl - this.locZ) / this.bi;
/* 1061 */			 double d3 = MathHelper.g(this.bm - this.yaw);
/*			*/ 
/* 1063 */			 this.yaw = (float)(this.yaw + d3 / this.bi);
/* 1064 */			 this.pitch = (float)(this.pitch + (this.bn - this.pitch) / this.bi);
/* 1065 */			 this.bi -= 1;
/* 1066 */			 setPosition(d0, d1, d2);
/* 1067 */			 b(this.yaw, this.pitch);
/*			*/		 }
/*			*/ 
/* 1070 */		 if (Math.abs(this.motX) < 0.005D) {
/* 1071 */			 this.motX = 0.0D;
/*			*/		 }
/*			*/ 
/* 1074 */		 if (Math.abs(this.motY) < 0.005D) {
/* 1075 */			 this.motY = 0.0D;
/*			*/		 }
/*			*/ 
/* 1078 */		 if (Math.abs(this.motZ) < 0.005D) {
/* 1079 */			 this.motZ = 0.0D;
/*			*/		 }
/*			*/ 
/* 1083 */		 if (aX()) {
/* 1084 */			 this.bu = false;
/* 1085 */			 this.br = 0.0F;
/* 1086 */			 this.bs = 0.0F;
/* 1087 */			 this.bt = 0.0F;
/* 1088 */		 } else if (aW()) {
/* 1089 */			 if (aV())
/*			*/			 {
/* 1091 */				 bc();
/*			*/			 }
/*			*/			 else
/*			*/			 {
/* 1095 */				 be();
/*			*/ 
/* 1097 */				 this.as = this.yaw;
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1103 */		 if (this.bu) {
/* 1104 */			 if ((!H()) && (!J())) {
/* 1105 */				 if ((this.onGround) && (this.bE == 0)) {
/* 1106 */					 aZ();
/* 1107 */					 this.bE = 10;
/*			*/				 }
/*			*/			 }
/* 1110 */			 else this.motY += 0.03999999910593033D;
/*			*/		 }
/*			*/		 else {
/* 1113 */			 this.bE = 0;
/*			*/		 }
/*			*/ 
/* 1118 */		 this.br *= 0.98F;
/* 1119 */		 this.bs *= 0.98F;
/* 1120 */		 this.bt *= 0.9F;
/* 1121 */		 float f = this.aG;
/*			*/ 
/* 1123 */		 this.aG *= bs();
/* 1124 */		 e(this.br, this.bs);
/* 1125 */		 this.aG = f;
/*			*/ 
/* 1128 */		 if (!this.world.isStatic) {
/* 1129 */			 List list = this.world.getEntities(this, this.boundingBox.grow(0.2000000029802322D, 0.0D, 0.2000000029802322D));
/*			*/ 
/* 1131 */			 if ((list != null) && (!list.isEmpty())) {
/* 1132 */				 Iterator iterator = list.iterator();
/*			*/ 
/* 1134 */				 while (iterator.hasNext()) {
/* 1135 */					 Entity entity = (Entity)iterator.next();
/*			*/ 
/* 1137 */					 if (entity.M())
/* 1138 */						 entity.collide(this);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean aV()
/*			*/	 {
/* 1148 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean aW() {
/* 1152 */		 return !this.world.isStatic;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean aX() {
/* 1156 */		 return this.health <= 0;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean aY() {
/* 1160 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 protected void aZ() {
/* 1164 */		 this.motY = 0.4199999868869782D;
/* 1165 */		 if (hasEffect(MobEffectList.JUMP)) {
/* 1166 */			 this.motY += (getEffect(MobEffectList.JUMP).getAmplifier() + 1) * 0.1F;
/*			*/		 }
/*			*/ 
/* 1169 */		 if (isSprinting()) {
/* 1170 */			 float f = this.yaw * 0.01745329F;
/*			*/ 
/* 1172 */			 this.motX -= MathHelper.sin(f) * 0.2F;
/* 1173 */			 this.motZ += MathHelper.cos(f) * 0.2F;
/*			*/		 }
/*			*/ 
/* 1176 */		 this.al = true;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean ba() {
/* 1180 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void bb() {
/* 1184 */		 EntityHuman entityhuman = this.world.findNearbyPlayer(this, -1.0D);
/*			*/ 
/* 1186 */		 if (entityhuman != null) {
/* 1187 */			 double d0 = entityhuman.locX - this.locX;
/* 1188 */			 double d1 = entityhuman.locY - this.locY;
/* 1189 */			 double d2 = entityhuman.locZ - this.locZ;
/* 1190 */			 double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*			*/ 
/* 1192 */			 if ((ba()) && (d3 > 16384.0D)) {
/* 1193 */				 die();
/*			*/			 }
/*			*/ 
/* 1196 */			 if ((this.bq > 600) && (this.random.nextInt(800) == 0) && (d3 > 1024.0D) && (ba()))
/* 1197 */				 die();
/* 1198 */			 else if (d3 < 1024.0D)
/* 1199 */				 this.bq = 0;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void bc()
/*			*/	 {
/* 1205 */		 this.bq += 1;
/*			*/ 
/* 1207 */		 bb();
/*			*/ 
/* 1210 */		 this.bA.a();
/*			*/ 
/* 1213 */		 this.targetSelector.a();
/*			*/ 
/* 1216 */		 this.goalSelector.a();
/*			*/ 
/* 1219 */		 this.navigation.e();
/*			*/ 
/* 1222 */		 bd();
/*			*/ 
/* 1226 */		 this.moveController.c();
/*			*/ 
/* 1228 */		 this.lookController.a();
/*			*/ 
/* 1230 */		 this.jumpController.b();
/*			*/	 }
/*			*/ 
/*			*/	 protected void bd()
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 protected void be() {
/* 1238 */		 this.bq += 1;
/* 1239 */		 bb();
/* 1240 */		 this.br = 0.0F;
/* 1241 */		 this.bs = 0.0F;
/* 1242 */		 float f = 8.0F;
/*			*/ 
/* 1244 */		 if (this.random.nextFloat() < 0.02F) {
/* 1245 */			 EntityHuman entityhuman = this.world.findNearbyPlayer(this, f);
/*			*/ 
/* 1247 */			 if (entityhuman != null) {
/* 1248 */				 this.bF = entityhuman;
/* 1249 */				 this.bx = (10 + this.random.nextInt(20));
/*			*/			 } else {
/* 1251 */				 this.bt = ((this.random.nextFloat() - 0.5F) * 20.0F);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1255 */		 if (this.bF != null) {
/* 1256 */			 a(this.bF, 10.0F, bf());
/* 1257 */			 if ((this.bx-- <= 0) || (this.bF.dead) || (this.bF.e(this) > f * f))
/* 1258 */				 this.bF = null;
/*			*/		 }
/*			*/		 else {
/* 1261 */			 if (this.random.nextFloat() < 0.05F) {
/* 1262 */				 this.bt = ((this.random.nextFloat() - 0.5F) * 20.0F);
/*			*/			 }
/*			*/ 
/* 1265 */			 this.yaw += this.bt;
/* 1266 */			 this.pitch = this.bv;
/*			*/		 }
/*			*/ 
/* 1269 */		 boolean flag = H();
/* 1270 */		 boolean flag1 = J();
/*			*/ 
/* 1272 */		 if ((flag) || (flag1))
/* 1273 */			 this.bu = (this.random.nextFloat() < 0.8F);
/*			*/	 }
/*			*/ 
/*			*/	 public int bf()
/*			*/	 {
/* 1278 */		 return 40;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Entity entity, float f, float f1) {
/* 1282 */		 double d0 = entity.locX - this.locX;
/* 1283 */		 double d1 = entity.locZ - this.locZ;
/*			*/		 double d2;
/*			*/		 double d2;
/* 1286 */		 if ((entity instanceof EntityLiving)) {
/* 1287 */			 EntityLiving entityliving = (EntityLiving)entity;
/*			*/ 
/* 1289 */			 d2 = this.locY + getHeadHeight() - (entityliving.locY + entityliving.getHeadHeight());
/*			*/		 } else {
/* 1291 */			 d2 = (entity.boundingBox.b + entity.boundingBox.e) / 2.0D - (this.locY + getHeadHeight());
/*			*/		 }
/*			*/ 
/* 1294 */		 double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1);
/* 1295 */		 float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592741012573D) - 90.0F;
/* 1296 */		 float f3 = (float)(-(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D));
/*			*/ 
/* 1298 */		 this.pitch = (-b(this.pitch, f3, f1));
/* 1299 */		 this.yaw = b(this.yaw, f2, f);
/*			*/	 }
/*			*/ 
/*			*/	 private float b(float f, float f1, float f2) {
/* 1303 */		 float f3 = MathHelper.g(f1 - f);
/*			*/ 
/* 1305 */		 if (f3 > f2) {
/* 1306 */			 f3 = f2;
/*			*/		 }
/*			*/ 
/* 1309 */		 if (f3 < -f2) {
/* 1310 */			 f3 = -f2;
/*			*/		 }
/*			*/ 
/* 1313 */		 return f + f3;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean canSpawn() {
/* 1317 */		 return (this.world.b(this.boundingBox)) && (this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox));
/*			*/	 }
/*			*/ 
/*			*/	 protected void C()
/*			*/	 {
/* 1322 */		 EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(null, getBukkitEntity(), EntityDamageEvent.DamageCause.VOID, 4);
/* 1323 */		 this.world.getServer().getPluginManager().callEvent(event);
/*			*/ 
/* 1325 */		 if ((event.isCancelled()) || (event.getDamage() == 0)) {
/* 1326 */			 return;
/*			*/		 }
/*			*/ 
/* 1329 */		 event.getEntity().setLastDamageCause(event);
/* 1330 */		 damageEntity(DamageSource.OUT_OF_WORLD, event.getDamage());
/*			*/	 }
/*			*/ 
/*			*/	 public Vec3D Z()
/*			*/	 {
/* 1335 */		 return i(1.0F);
/*			*/	 }
/*			*/ 
/*			*/	 public Vec3D i(float f)
/*			*/	 {
/* 1344 */		 if (f == 1.0F) {
/* 1345 */			 float f1 = MathHelper.cos(-this.yaw * 0.01745329F - 3.141593F);
/* 1346 */			 float f2 = MathHelper.sin(-this.yaw * 0.01745329F - 3.141593F);
/* 1347 */			 float f3 = -MathHelper.cos(-this.pitch * 0.01745329F);
/* 1348 */			 float f4 = MathHelper.sin(-this.pitch * 0.01745329F);
/* 1349 */			 return Vec3D.a().create(f2 * f3, f4, f1 * f3);
/*			*/		 }
/* 1351 */		 float f1 = this.lastPitch + (this.pitch - this.lastPitch) * f;
/* 1352 */		 float f2 = this.lastYaw + (this.yaw - this.lastYaw) * f;
/* 1353 */		 float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
/* 1354 */		 float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
/* 1355 */		 float f5 = -MathHelper.cos(-f1 * 0.01745329F);
/* 1356 */		 float f6 = MathHelper.sin(-f1 * 0.01745329F);
/*			*/ 
/* 1358 */		 return Vec3D.a().create(f4 * f5, f6, f3 * f5);
/*			*/	 }
/*			*/ 
/*			*/	 public int bl()
/*			*/	 {
/* 1363 */		 return 4;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isSleeping() {
/* 1367 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 protected void bo() {
/* 1371 */		 Iterator iterator = this.effects.keySet().iterator();
/*			*/ 
/* 1373 */		 while (iterator.hasNext()) {
/* 1374 */			 Integer integer = (Integer)iterator.next();
/* 1375 */			 MobEffect mobeffect = (MobEffect)this.effects.get(integer);
/*			*/ 
/* 1377 */			 if ((!mobeffect.tick(this)) && (!this.world.isStatic)) {
/* 1378 */				 iterator.remove();
/* 1379 */				 c(mobeffect);
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1385 */		 if (this.updateEffects) {
/* 1386 */			 if (!this.world.isStatic) {
/* 1387 */				 if (this.effects.isEmpty()) {
/* 1388 */					 this.datawatcher.watch(8, Integer.valueOf(0));
/*			*/				 } else {
/* 1390 */					 int i = PotionBrewer.a(this.effects.values());
/* 1391 */					 this.datawatcher.watch(8, Integer.valueOf(i));
/*			*/				 }
/*			*/			 }
/*			*/ 
/* 1395 */			 this.updateEffects = false;
/*			*/		 }
/*			*/ 
/* 1398 */		 if (this.random.nextBoolean()) {
/* 1399 */			 int i = this.datawatcher.getInt(8);
/* 1400 */			 if (i > 0) {
/* 1401 */				 double d0 = (i >> 16 & 0xFF) / 255.0D;
/* 1402 */				 double d1 = (i >> 8 & 0xFF) / 255.0D;
/* 1403 */				 double d2 = (i >> 0 & 0xFF) / 255.0D;
/*			*/ 
/* 1405 */				 this.world.a("mobSpell", this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length - this.height, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, d0, d1, d2);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void bp() {
/* 1411 */		 Iterator iterator = this.effects.keySet().iterator();
/*			*/ 
/* 1413 */		 while (iterator.hasNext()) {
/* 1414 */			 Integer integer = (Integer)iterator.next();
/* 1415 */			 MobEffect mobeffect = (MobEffect)this.effects.get(integer);
/*			*/ 
/* 1417 */			 if (!this.world.isStatic) {
/* 1418 */				 iterator.remove();
/* 1419 */				 c(mobeffect);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public Collection getEffects() {
/* 1425 */		 return this.effects.values();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean hasEffect(MobEffectList mobeffectlist) {
/* 1429 */		 return this.effects.containsKey(Integer.valueOf(mobeffectlist.id));
/*			*/	 }
/*			*/ 
/*			*/	 public MobEffect getEffect(MobEffectList mobeffectlist) {
/* 1433 */		 return (MobEffect)this.effects.get(Integer.valueOf(mobeffectlist.id));
/*			*/	 }
/*			*/ 
/*			*/	 public void addEffect(MobEffect mobeffect) {
/* 1437 */		 if (e(mobeffect))
/* 1438 */			 if (this.effects.containsKey(Integer.valueOf(mobeffect.getEffectId()))) {
/* 1439 */				 ((MobEffect)this.effects.get(Integer.valueOf(mobeffect.getEffectId()))).a(mobeffect);
/* 1440 */				 b((MobEffect)this.effects.get(Integer.valueOf(mobeffect.getEffectId())));
/*			*/			 } else {
/* 1442 */				 this.effects.put(Integer.valueOf(mobeffect.getEffectId()), mobeffect);
/* 1443 */				 a(mobeffect);
/*			*/			 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean e(MobEffect mobeffect)
/*			*/	 {
/* 1449 */		 if (getMonsterType() == EnumMonsterType.UNDEAD) {
/* 1450 */			 int i = mobeffect.getEffectId();
/*			*/ 
/* 1452 */			 if ((i == MobEffectList.REGENERATION.id) || (i == MobEffectList.POISON.id)) {
/* 1453 */				 return false;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1457 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean br() {
/* 1461 */		 return getMonsterType() == EnumMonsterType.UNDEAD;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(MobEffect mobeffect) {
/* 1465 */		 this.updateEffects = true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void b(MobEffect mobeffect) {
/* 1469 */		 this.updateEffects = true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void c(MobEffect mobeffect) {
/* 1473 */		 this.updateEffects = true;
/*			*/	 }
/*			*/ 
/*			*/	 protected float bs() {
/* 1477 */		 float f = 1.0F;
/*			*/ 
/* 1479 */		 if (hasEffect(MobEffectList.FASTER_MOVEMENT)) {
/* 1480 */			 f *= (1.0F + 0.2F * (getEffect(MobEffectList.FASTER_MOVEMENT).getAmplifier() + 1));
/*			*/		 }
/*			*/ 
/* 1483 */		 if (hasEffect(MobEffectList.SLOWER_MOVEMENT)) {
/* 1484 */			 f *= (1.0F - 0.15F * (getEffect(MobEffectList.SLOWER_MOVEMENT).getAmplifier() + 1));
/*			*/		 }
/*			*/ 
/* 1487 */		 return f;
/*			*/	 }
/*			*/ 
/*			*/	 public void enderTeleportTo(double d0, double d1, double d2) {
/* 1491 */		 setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isBaby() {
/* 1495 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public EnumMonsterType getMonsterType() {
/* 1499 */		 return EnumMonsterType.UNDEFINED;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(ItemStack itemstack) {
/* 1503 */		 this.world.makeSound(this, "random.break", 0.8F, 0.8F + this.world.random.nextFloat() * 0.4F);
/*			*/ 
/* 1505 */		 for (int i = 0; i < 5; i++) {
/* 1506 */			 Vec3D vec3d = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/*			*/ 
/* 1508 */			 vec3d.a(-this.pitch * 3.141593F / 180.0F);
/* 1509 */			 vec3d.b(-this.yaw * 3.141593F / 180.0F);
/* 1510 */			 Vec3D vec3d1 = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.3D, -this.random.nextFloat() * 0.6D - 0.3D, 0.6D);
/*			*/ 
/* 1512 */			 vec3d1.a(-this.pitch * 3.141593F / 180.0F);
/* 1513 */			 vec3d1.b(-this.yaw * 3.141593F / 180.0F);
/* 1514 */			 vec3d1 = vec3d1.add(this.locX, this.locY + getHeadHeight(), this.locZ);
/* 1515 */			 this.world.a("iconcrack_" + itemstack.getItem().id, vec3d1.a, vec3d1.b, vec3d1.c, vec3d.a, vec3d.b + 0.05D, vec3d.c);
/*			*/		 }
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityLiving
 * JD-Core Version:		0.6.0
 */