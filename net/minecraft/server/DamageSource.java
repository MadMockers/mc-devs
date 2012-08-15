/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class DamageSource
/*		 */ {
/*	10 */	 public static DamageSource FIRE = new DamageSource("inFire").j();
/*	11 */	 public static DamageSource BURN = new DamageSource("onFire").h().j();
/*	12 */	 public static DamageSource LAVA = new DamageSource("lava").j();
/*	13 */	 public static DamageSource STUCK = new DamageSource("inWall").h();
/*	14 */	 public static DamageSource DROWN = new DamageSource("drown").h();
/*	15 */	 public static DamageSource STARVE = new DamageSource("starve").h();
/*	16 */	 public static DamageSource CACTUS = new DamageSource("cactus");
/*	17 */	 public static DamageSource FALL = new DamageSource("fall").h();
/*	18 */	 public static DamageSource OUT_OF_WORLD = new DamageSource("outOfWorld").h().i();
/*	19 */	 public static DamageSource GENERIC = new DamageSource("generic").h();
/*	20 */	 public static DamageSource EXPLOSION = new DamageSource("explosion").m();
/*	21 */	 public static DamageSource EXPLOSION2 = new DamageSource("explosion");
/*	22 */	 public static DamageSource MAGIC = new DamageSource("magic").h();
/*		 */ 
/*	51 */	 private boolean o = false;
/*	52 */	 private boolean p = false;
/*		 */ 
/*	54 */	 private float q = 0.3F;
/*		 */	 private boolean r;
/*		 */	 private boolean s;
/*		 */	 private boolean t;
/*		 */	 public String translationIndex;
/*		 */ 
/*		 */	 public static DamageSource mobAttack(EntityLiving paramEntityLiving)
/*		 */	 {
/*	25 */		 return new EntityDamageSource("mob", paramEntityLiving);
/*		 */	 }
/*		 */ 
/*		 */	 public static DamageSource playerAttack(EntityHuman paramEntityHuman) {
/*	29 */		 return new EntityDamageSource("player", paramEntityHuman);
/*		 */	 }
/*		 */ 
/*		 */	 public static DamageSource arrow(EntityArrow paramEntityArrow, Entity paramEntity) {
/*	33 */		 return new EntityDamageSourceIndirect("arrow", paramEntityArrow, paramEntity).b();
/*		 */	 }
/*		 */ 
/*		 */	 public static DamageSource fireball(EntityFireball paramEntityFireball, Entity paramEntity) {
/*	37 */		 if (paramEntity == null) {
/*	38 */			 return new EntityDamageSourceIndirect("onFire", paramEntityFireball, paramEntityFireball).j().b();
/*		 */		 }
/*	40 */		 return new EntityDamageSourceIndirect("fireball", paramEntityFireball, paramEntity).j().b();
/*		 */	 }
/*		 */ 
/*		 */	 public static DamageSource projectile(Entity paramEntity1, Entity paramEntity2) {
/*	44 */		 return new EntityDamageSourceIndirect("thrown", paramEntity1, paramEntity2).b();
/*		 */	 }
/*		 */ 
/*		 */	 public static DamageSource b(Entity paramEntity1, Entity paramEntity2) {
/*	48 */		 return new EntityDamageSourceIndirect("indirectMagic", paramEntity1, paramEntity2).h();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a()
/*		 */	 {
/*	60 */		 return this.s;
/*		 */	 }
/*		 */ 
/*		 */	 public DamageSource b() {
/*	64 */		 this.s = true;
/*	65 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean ignoresArmor() {
/*	69 */		 return this.o;
/*		 */	 }
/*		 */ 
/*		 */	 public float d() {
/*	73 */		 return this.q;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean ignoresInvulnerability() {
/*	77 */		 return this.p;
/*		 */	 }
/*		 */ 
/*		 */	 protected DamageSource(String paramString)
/*		 */	 {
/*	83 */		 this.translationIndex = paramString;
/*		 */	 }
/*		 */ 
/*		 */	 public Entity f() {
/*	87 */		 return getEntity();
/*		 */	 }
/*		 */ 
/*		 */	 public Entity getEntity() {
/*	91 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected DamageSource h() {
/*	95 */		 this.o = true;
/*		 */ 
/*	97 */		 this.q = 0.0F;
/*	98 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected DamageSource i() {
/* 102 */		 this.p = true;
/* 103 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected DamageSource j() {
/* 107 */		 this.r = true;
/* 108 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String getLocalizedDeathMessage(EntityHuman paramEntityHuman) {
/* 112 */		 return LocaleI18n.get("death." + this.translationIndex, new Object[] { paramEntityHuman.name });
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k() {
/* 116 */		 return this.r;
/*		 */	 }
/*		 */ 
/*		 */	 public String l() {
/* 120 */		 return this.translationIndex;
/*		 */	 }
/*		 */ 
/*		 */	 public DamageSource m() {
/* 124 */		 this.t = true;
/* 125 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean n() {
/* 129 */		 return this.t;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.DamageSource
 * JD-Core Version:		0.6.0
 */