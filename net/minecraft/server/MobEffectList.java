/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.potion.CraftPotionEffectType;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent;
/*		 */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*		 */ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*		 */ import org.bukkit.potion.PotionEffectType;
/*		 */ 
/*		 */ public class MobEffectList
/*		 */ {
/*	11 */	 public static final MobEffectList[] byId = new MobEffectList[32];
/*	12 */	 public static final MobEffectList b = null;
/*	13 */	 public static final MobEffectList FASTER_MOVEMENT = new MobEffectList(1, false, 8171462).b("potion.moveSpeed").b(0, 0);
/*	14 */	 public static final MobEffectList SLOWER_MOVEMENT = new MobEffectList(2, true, 5926017).b("potion.moveSlowdown").b(1, 0);
/*	15 */	 public static final MobEffectList FASTER_DIG = new MobEffectList(3, false, 14270531).b("potion.digSpeed").b(2, 0).a(1.5D);
/*	16 */	 public static final MobEffectList SLOWER_DIG = new MobEffectList(4, true, 4866583).b("potion.digSlowDown").b(3, 0);
/*	17 */	 public static final MobEffectList INCREASE_DAMAGE = new MobEffectList(5, false, 9643043).b("potion.damageBoost").b(4, 0);
/*	18 */	 public static final MobEffectList HEAL = new InstantMobEffect(6, false, 16262179).b("potion.heal");
/*	19 */	 public static final MobEffectList HARM = new InstantMobEffect(7, true, 4393481).b("potion.harm");
/*	20 */	 public static final MobEffectList JUMP = new MobEffectList(8, false, 7889559).b("potion.jump").b(2, 1);
/*	21 */	 public static final MobEffectList CONFUSION = new MobEffectList(9, true, 5578058).b("potion.confusion").b(3, 1).a(0.25D);
/*	22 */	 public static final MobEffectList REGENERATION = new MobEffectList(10, false, 13458603).b("potion.regeneration").b(7, 0).a(0.25D);
/*	23 */	 public static final MobEffectList RESISTANCE = new MobEffectList(11, false, 10044730).b("potion.resistance").b(6, 1);
/*	24 */	 public static final MobEffectList FIRE_RESISTANCE = new MobEffectList(12, false, 14981690).b("potion.fireResistance").b(7, 1);
/*	25 */	 public static final MobEffectList WATER_BREATHING = new MobEffectList(13, false, 3035801).b("potion.waterBreathing").b(0, 2);
/*	26 */	 public static final MobEffectList INVISIBILITY = new MobEffectList(14, false, 8356754).b("potion.invisibility").b(0, 1).h();
/*	27 */	 public static final MobEffectList BLINDNESS = new MobEffectList(15, true, 2039587).b("potion.blindness").b(5, 1).a(0.25D);
/*	28 */	 public static final MobEffectList NIGHT_VISION = new MobEffectList(16, false, 2039713).b("potion.nightVision").b(4, 1).h();
/*	29 */	 public static final MobEffectList HUNGER = new MobEffectList(17, true, 5797459).b("potion.hunger").b(1, 1);
/*	30 */	 public static final MobEffectList WEAKNESS = new MobEffectList(18, true, 4738376).b("potion.weakness").b(5, 0);
/*	31 */	 public static final MobEffectList POISON = new MobEffectList(19, true, 5149489).b("potion.poison").b(6, 0).a(0.25D);
/*	32 */	 public static final MobEffectList v = null;
/*	33 */	 public static final MobEffectList w = null;
/*	34 */	 public static final MobEffectList x = null;
/*	35 */	 public static final MobEffectList y = null;
/*	36 */	 public static final MobEffectList z = null;
/*	37 */	 public static final MobEffectList A = null;
/*	38 */	 public static final MobEffectList B = null;
/*	39 */	 public static final MobEffectList C = null;
/*	40 */	 public static final MobEffectList D = null;
/*	41 */	 public static final MobEffectList E = null;
/*	42 */	 public static final MobEffectList F = null;
/*	43 */	 public static final MobEffectList G = null;
/*		 */	 public final int id;
/*	45 */	 private String I = "";
/*	46 */	 private int J = -1;
/*		 */	 private final boolean K;
/*		 */	 private double L;
/*		 */	 private boolean M;
/*		 */	 private final int N;
/*		 */ 
/*		 */	 protected MobEffectList(int i, boolean flag, int j)
/*		 */	 {
/*	53 */		 this.id = i;
/*	54 */		 byId[i] = this;
/*	55 */		 this.K = flag;
/*	56 */		 if (flag)
/*	57 */			 this.L = 0.5D;
/*		 */		 else {
/*	59 */			 this.L = 1.0D;
/*		 */		 }
/*		 */ 
/*	62 */		 this.N = j;
/*		 */ 
/*	64 */		 PotionEffectType.registerPotionEffectType(new CraftPotionEffectType(this));
/*		 */	 }
/*		 */ 
/*		 */	 protected MobEffectList b(int i, int j) {
/*	68 */		 this.J = (i + j * 8);
/*	69 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public int getId() {
/*	73 */		 return this.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void tick(EntityLiving entityliving, int i) {
/*	77 */		 if (this.id == REGENERATION.id) {
/*	78 */			 if (entityliving.getHealth() < entityliving.getMaxHealth())
/*	79 */				 entityliving.heal(1, EntityRegainHealthEvent.RegainReason.MAGIC_REGEN);
/*		 */		 }
/*	81 */		 else if (this.id == POISON.id) {
/*	82 */			 if (entityliving.getHealth() > 1)
/*		 */			 {
/*	84 */				 EntityDamageEvent event = CraftEventFactory.callEntityDamageEvent(null, entityliving, EntityDamageEvent.DamageCause.POISON, 1);
/*		 */ 
/*	86 */				 if ((!event.isCancelled()) && (event.getDamage() > 0)) {
/*	87 */					 entityliving.damageEntity(DamageSource.MAGIC, event.getDamage());
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*	91 */		 else if ((this.id == HUNGER.id) && ((entityliving instanceof EntityHuman)))
/*	92 */			 ((EntityHuman)entityliving).j(0.025F * (i + 1));
/*	93 */		 else if (((this.id != HEAL.id) || (entityliving.br())) && ((this.id != HARM.id) || (!entityliving.br()))) {
/*	94 */			 if (((this.id == HARM.id) && (!entityliving.br())) || ((this.id == HEAL.id) && (entityliving.br())))
/*		 */			 {
/*	96 */				 EntityDamageEvent event = CraftEventFactory.callEntityDamageEvent(null, entityliving, EntityDamageEvent.DamageCause.MAGIC, 6 << i);
/*		 */ 
/*	98 */				 if ((!event.isCancelled()) && (event.getDamage() > 0)) {
/*	99 */					 entityliving.damageEntity(DamageSource.MAGIC, event.getDamage());
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */		 else
/* 104 */			 entityliving.heal(6 << i, EntityRegainHealthEvent.RegainReason.MAGIC);
/*		 */	 }
/*		 */ 
/*		 */	 public void applyInstantEffect(EntityLiving entityliving, EntityLiving entityliving1, int i, double d0)
/*		 */	 {
/* 110 */		 applyInstantEffect(entityliving, entityliving1, i, d0, null);
/*		 */	 }
/*		 */ 
/*		 */	 public void applyInstantEffect(EntityLiving entityliving, EntityLiving entityliving1, int i, double d0, EntityPotion potion)
/*		 */	 {
/* 117 */		 if (((this.id != HEAL.id) || (entityliving1.br())) && ((this.id != HARM.id) || (!entityliving1.br()))) {
/* 118 */			 if (((this.id == HARM.id) && (!entityliving1.br())) || ((this.id == HEAL.id) && (entityliving1.br()))) {
/* 119 */				 int j = (int)(d0 * (6 << i) + 0.5D);
/* 120 */				 if (entityliving == null) {
/* 121 */					 entityliving1.damageEntity(DamageSource.MAGIC, j);
/*		 */				 }
/*		 */				 else
/* 124 */					 entityliving1.damageEntity(DamageSource.b(potion != null ? potion : entityliving1, entityliving), j);
/*		 */			 }
/*		 */		 }
/*		 */		 else {
/* 128 */			 int j = (int)(d0 * (6 << i) + 0.5D);
/* 129 */			 entityliving1.heal(j, EntityRegainHealthEvent.RegainReason.MAGIC);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isInstant() {
/* 134 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int i, int j) {
/* 138 */		 if ((this.id != REGENERATION.id) && (this.id != POISON.id)) {
/* 139 */			 return this.id == HUNGER.id;
/*		 */		 }
/* 141 */		 int k = 25 >> j;
/*		 */ 
/* 143 */		 return i % k == 0;
/*		 */	 }
/*		 */ 
/*		 */	 public MobEffectList b(String s)
/*		 */	 {
/* 148 */		 this.I = s;
/* 149 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String a() {
/* 153 */		 return this.I;
/*		 */	 }
/*		 */ 
/*		 */	 protected MobEffectList a(double d0) {
/* 157 */		 this.L = d0;
/* 158 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public double getDurationModifier() {
/* 162 */		 return this.L;
/*		 */	 }
/*		 */ 
/*		 */	 public MobEffectList h() {
/* 166 */		 this.M = true;
/* 167 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean i() {
/* 171 */		 return this.M;
/*		 */	 }
/*		 */ 
/*		 */	 public int j() {
/* 175 */		 return this.N;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MobEffectList
 * JD-Core Version:		0.6.0
 */