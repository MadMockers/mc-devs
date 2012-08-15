/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
/*		 */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*		 */ 
/*		 */ public abstract class PathfinderGoalTarget extends PathfinderGoal
/*		 */ {
/*		 */	 protected EntityLiving d;
/*		 */	 protected float e;
/*		 */	 protected boolean f;
/*		 */	 private boolean a;
/*		 */	 private int b;
/*		 */	 private int c;
/*		 */	 private int g;
/*		 */ 
/*		 */	 public PathfinderGoalTarget(EntityLiving entityliving, float f, boolean flag)
/*		 */	 {
/*	16 */		 this(entityliving, f, flag, false);
/*		 */	 }
/*		 */ 
/*		 */	 public PathfinderGoalTarget(EntityLiving entityliving, float f, boolean flag, boolean flag1) {
/*	20 */		 this.b = 0;
/*	21 */		 this.c = 0;
/*	22 */		 this.g = 0;
/*	23 */		 this.d = entityliving;
/*	24 */		 this.e = f;
/*	25 */		 this.f = flag;
/*	26 */		 this.a = flag1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b() {
/*	30 */		 EntityLiving entityliving = this.d.az();
/*		 */ 
/*	32 */		 if (entityliving == null)
/*	33 */			 return false;
/*	34 */		 if (!entityliving.isAlive())
/*	35 */			 return false;
/*	36 */		 if (this.d.e(entityliving) > this.e * this.e) {
/*	37 */			 return false;
/*		 */		 }
/*	39 */		 if (this.f) {
/*	40 */			 if (this.d.at().canSee(entityliving))
/*	41 */				 this.g = 0;
/*	42 */			 else if (++this.g > 60) {
/*	43 */				 return false;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	47 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void e()
/*		 */	 {
/*	52 */		 this.b = 0;
/*	53 */		 this.c = 0;
/*	54 */		 this.g = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void c() {
/*	58 */		 this.d.b((EntityLiving)null);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean a(EntityLiving entityliving, boolean flag) {
/*	62 */		 if (entityliving == null)
/*	63 */			 return false;
/*	64 */		 if (entityliving == this.d)
/*	65 */			 return false;
/*	66 */		 if (!entityliving.isAlive())
/*	67 */			 return false;
/*	68 */		 if ((entityliving.boundingBox.e > this.d.boundingBox.b) && (entityliving.boundingBox.b < this.d.boundingBox.e)) {
/*	69 */			 if (!this.d.a(entityliving.getClass())) {
/*	70 */				 return false;
/*		 */			 }
/*	72 */			 if (((this.d instanceof EntityTameableAnimal)) && (((EntityTameableAnimal)this.d).isTamed())) {
/*	73 */				 if (((entityliving instanceof EntityTameableAnimal)) && (((EntityTameableAnimal)entityliving).isTamed())) {
/*	74 */					 return false;
/*		 */				 }
/*		 */ 
/*	77 */				 if (entityliving == ((EntityTameableAnimal)this.d).getOwner())
/*	78 */					 return false;
/*		 */			 }
/*	80 */			 else if (((entityliving instanceof EntityHuman)) && (!flag) && (((EntityHuman)entityliving).abilities.isInvulnerable)) {
/*	81 */				 return false;
/*		 */			 }
/*		 */ 
/*	84 */			 if (!this.d.d(MathHelper.floor(entityliving.locX), MathHelper.floor(entityliving.locY), MathHelper.floor(entityliving.locZ)))
/*	85 */				 return false;
/*	86 */			 if ((this.f) && (!this.d.at().canSee(entityliving))) {
/*	87 */				 return false;
/*		 */			 }
/*	89 */			 if (this.a) {
/*	90 */				 if (--this.c <= 0) {
/*	91 */					 this.b = 0;
/*		 */				 }
/*		 */ 
/*	94 */				 if (this.b == 0) {
/*	95 */					 this.b = (a(entityliving) ? 1 : 2);
/*		 */				 }
/*		 */ 
/*	98 */				 if (this.b == 2) {
/*	99 */					 return false;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 104 */			 EntityTargetEvent.TargetReason reason = EntityTargetEvent.TargetReason.RANDOM_TARGET;
/*		 */ 
/* 106 */			 if ((this instanceof PathfinderGoalDefendVillage))
/* 107 */				 reason = EntityTargetEvent.TargetReason.DEFEND_VILLAGE;
/* 108 */			 else if ((this instanceof PathfinderGoalHurtByTarget))
/* 109 */				 reason = EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY;
/* 110 */			 else if ((this instanceof PathfinderGoalNearestAttackableTarget)) {
/* 111 */				 if ((entityliving instanceof EntityHuman))
/* 112 */					 reason = EntityTargetEvent.TargetReason.CLOSEST_PLAYER;
/*		 */			 }
/* 114 */			 else if ((this instanceof PathfinderGoalOwnerHurtByTarget))
/* 115 */				 reason = EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER;
/* 116 */			 else if ((this instanceof PathfinderGoalOwnerHurtTarget)) {
/* 117 */				 reason = EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET;
/*		 */			 }
/*		 */ 
/* 120 */			 EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent(this.d, entityliving, reason);
/* 121 */			 if ((event.isCancelled()) || (event.getTarget() == null))
/* 122 */				 return false;
/* 123 */			 if (entityliving.getBukkitEntity() != event.getTarget()) {
/* 124 */				 this.d.b((EntityLiving)((CraftEntity)event.getTarget()).getHandle());
/*		 */			 }
/*		 */ 
/* 128 */			 return true;
/*		 */		 }
/*		 */ 
/* 132 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(EntityLiving entityliving)
/*		 */	 {
/* 137 */		 this.c = (10 + this.d.au().nextInt(5));
/* 138 */		 PathEntity pathentity = this.d.getNavigation().a(entityliving);
/*		 */ 
/* 140 */		 if (pathentity == null) {
/* 141 */			 return false;
/*		 */		 }
/* 143 */		 PathPoint pathpoint = pathentity.c();
/*		 */ 
/* 145 */		 if (pathpoint == null) {
/* 146 */			 return false;
/*		 */		 }
/* 148 */		 int i = pathpoint.a - MathHelper.floor(entityliving.locX);
/* 149 */		 int j = pathpoint.c - MathHelper.floor(entityliving.locZ);
/*		 */ 
/* 151 */		 return i * i + j * j <= 2.25D;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalTarget
 * JD-Core Version:		0.6.0
 */