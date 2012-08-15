/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.entity.CraftEntity;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent;
/*		 */ import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
/*		 */ 
/*		 */ public abstract class EntityMonster extends EntityCreature
/*		 */	 implements IMonster
/*		 */ {
/*	 7 */	 protected int damage = 2;
/*		 */ 
/*		 */	 public EntityMonster(World world) {
/*	10 */		 super(world);
/*	11 */		 this.aV = 5;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	15 */		 float f = c(1.0F);
/*		 */ 
/*	17 */		 if (f > 0.5F) {
/*	18 */			 this.bq += 2;
/*		 */		 }
/*		 */ 
/*	21 */		 super.d();
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	25 */		 super.h_();
/*	26 */		 if ((!this.world.isStatic) && (this.world.difficulty == 0))
/*	27 */			 die();
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget()
/*		 */	 {
/*	32 */		 EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, 16.0D);
/*		 */ 
/*	34 */		 return (entityhuman != null) && (l(entityhuman)) ? entityhuman : null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	38 */		 if (super.damageEntity(damagesource, i)) {
/*	39 */			 Entity entity = damagesource.getEntity();
/*		 */ 
/*	41 */			 if ((this.passenger != entity) && (this.vehicle != entity)) {
/*	42 */				 if (entity != this)
/*		 */				 {
/*	44 */					 if ((entity != this.target) && (((this instanceof EntityBlaze)) || ((this instanceof EntityEnderman)) || ((this instanceof EntitySpider)) || ((this instanceof EntityGiantZombie)) || ((this instanceof EntitySilverfish)))) {
/*	45 */						 EntityTargetEvent event = CraftEventFactory.callEntityTargetEvent(this, entity, EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY);
/*		 */ 
/*	47 */						 if (!event.isCancelled())
/*	48 */							 if (event.getTarget() == null)
/*	49 */								 this.target = null;
/*		 */							 else
/*	51 */								 this.target = ((CraftEntity)event.getTarget()).getHandle();
/*		 */					 }
/*		 */					 else
/*		 */					 {
/*	55 */						 this.target = entity;
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/*	60 */				 return true;
/*		 */			 }
/*	62 */			 return true;
/*		 */		 }
/*		 */ 
/*	65 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k(Entity entity)
/*		 */	 {
/*	70 */		 int i = this.damage;
/*		 */ 
/*	72 */		 if (hasEffect(MobEffectList.INCREASE_DAMAGE)) {
/*	73 */			 i += (3 << getEffect(MobEffectList.INCREASE_DAMAGE).getAmplifier());
/*		 */		 }
/*		 */ 
/*	76 */		 if (hasEffect(MobEffectList.WEAKNESS)) {
/*	77 */			 i -= (2 << getEffect(MobEffectList.WEAKNESS).getAmplifier());
/*		 */		 }
/*		 */ 
/*	80 */		 return entity.damageEntity(DamageSource.mobAttack(this), i);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity entity, float f) {
/*	84 */		 if ((this.attackTicks <= 0) && (f < 2.0F) && (entity.boundingBox.e > this.boundingBox.b) && (entity.boundingBox.b < this.boundingBox.e)) {
/*	85 */			 this.attackTicks = 20;
/*	86 */			 k(entity);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public float a(int i, int j, int k) {
/*	91 */		 return 0.5F - this.world.o(i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean o() {
/*	95 */		 int i = MathHelper.floor(this.locX);
/*	96 */		 int j = MathHelper.floor(this.boundingBox.b);
/*	97 */		 int k = MathHelper.floor(this.locZ);
/*		 */ 
/*	99 */		 if (this.world.b(EnumSkyBlock.SKY, i, j, k) > this.random.nextInt(32)) {
/* 100 */			 return false;
/*		 */		 }
/* 102 */		 int l = this.world.getLightLevel(i, j, k);
/*		 */ 
/* 104 */		 if (this.world.I()) {
/* 105 */			 int i1 = this.world.k;
/*		 */ 
/* 107 */			 this.world.k = 10;
/* 108 */			 l = this.world.getLightLevel(i, j, k);
/* 109 */			 this.world.k = i1;
/*		 */		 }
/*		 */ 
/* 112 */		 return l <= this.random.nextInt(8);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn()
/*		 */	 {
/* 117 */		 return (o()) && (super.canSpawn());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityMonster
 * JD-Core Version:		0.6.0
 */