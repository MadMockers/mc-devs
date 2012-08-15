/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*		 */ 
/*		 */ public class EntitySilverfish extends EntityMonster
/*		 */ {
/*		 */	 private int d;
/*		 */ 
/*		 */	 public EntitySilverfish(World world)
/*		 */	 {
/*	10 */		 super(world);
/*	11 */		 this.texture = "/mob/silverfish.png";
/*	12 */		 a(0.3F, 0.7F);
/*	13 */		 this.bw = 0.6F;
/*	14 */		 this.damage = 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	18 */		 return 8;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_() {
/*	22 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected Entity findTarget() {
/*	26 */		 double d0 = 8.0D;
/*		 */ 
/*	28 */		 return this.world.findNearbyVulnerablePlayer(this, d0);
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	32 */		 return "mob.silverfish.say";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	36 */		 return "mob.silverfish.hit";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	40 */		 return "mob.silverfish.kill";
/*		 */	 }
/*		 */ 
/*		 */	 public boolean damageEntity(DamageSource damagesource, int i) {
/*	44 */		 if ((this.d <= 0) && (((damagesource instanceof EntityDamageSource)) || (damagesource == DamageSource.MAGIC))) {
/*	45 */			 this.d = 20;
/*		 */		 }
/*		 */ 
/*	48 */		 return super.damageEntity(damagesource, i);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity entity, float f) {
/*	52 */		 if ((this.attackTicks <= 0) && (f < 1.2F) && (entity.boundingBox.e > this.boundingBox.b) && (entity.boundingBox.b < this.boundingBox.e)) {
/*	53 */			 this.attackTicks = 20;
/*	54 */			 entity.damageEntity(DamageSource.mobAttack(this), this.damage);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(int i, int j, int k, int l) {
/*	59 */		 this.world.makeSound(this, "mob.silverfish.step", 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	63 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_() {
/*	67 */		 this.aq = this.yaw;
/*	68 */		 super.h_();
/*		 */	 }
/*		 */ 
/*		 */	 protected void be() {
/*	72 */		 super.be();
/*	73 */		 if (!this.world.isStatic)
/*		 */		 {
/*	79 */			 if (this.d > 0) {
/*	80 */				 this.d -= 1;
/*	81 */				 if (this.d == 0) {
/*	82 */					 int i = MathHelper.floor(this.locX);
/*	83 */					 int j = MathHelper.floor(this.locY);
/*	84 */					 int k = MathHelper.floor(this.locZ);
/*	85 */					 boolean flag = false;
/*		 */ 
/*	87 */					 for (int l = 0; (!flag) && (l <= 5) && (l >= -5); l = l <= 0 ? 1 - l : 0 - l) {
/*	88 */						 for (int i1 = 0; (!flag) && (i1 <= 10) && (i1 >= -10); i1 = i1 <= 0 ? 1 - i1 : 0 - i1) {
/*	89 */							 for (int j1 = 0; (!flag) && (j1 <= 10) && (j1 >= -10); j1 = j1 <= 0 ? 1 - j1 : 0 - j1) {
/*	90 */								 int k1 = this.world.getTypeId(i + i1, j + l, k + j1);
/*		 */ 
/*	92 */								 if (k1 != Block.MONSTER_EGGS.id)
/*		 */									 continue;
/*	94 */								 if (CraftEventFactory.callEntityChangeBlockEvent(this, i + i1, j + l, k + j1, 0).isCancelled())
/*		 */								 {
/*		 */									 continue;
/*		 */								 }
/*		 */ 
/*	99 */								 this.world.triggerEffect(2001, i + i1, j + l, k + j1, Block.MONSTER_EGGS.id + (this.world.getData(i + i1, j + l, k + j1) << 12));
/* 100 */								 this.world.setTypeId(i + i1, j + l, k + j1, 0);
/* 101 */								 Block.MONSTER_EGGS.postBreak(this.world, i + i1, j + l, k + j1, 0);
/* 102 */								 if (this.random.nextBoolean()) {
/* 103 */									 flag = true;
/* 104 */									 break;
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 113 */			 if ((this.target == null) && (!l())) {
/* 114 */				 int i = MathHelper.floor(this.locX);
/* 115 */				 int j = MathHelper.floor(this.locY + 0.5D);
/* 116 */				 int k = MathHelper.floor(this.locZ);
/* 117 */				 int l1 = this.random.nextInt(6);
/*		 */ 
/* 119 */				 int l = this.world.getTypeId(i + Facing.b[l1], j + Facing.c[l1], k + Facing.d[l1]);
/* 120 */				 if (BlockMonsterEggs.e(l))
/*		 */				 {
/* 122 */					 if (CraftEventFactory.callEntityChangeBlockEvent(this, i + Facing.b[l1], j + Facing.c[l1], k + Facing.d[l1], Block.MONSTER_EGGS.id).isCancelled()) {
/* 123 */						 return;
/*		 */					 }
/*		 */ 
/* 127 */					 this.world.setTypeIdAndData(i + Facing.b[l1], j + Facing.c[l1], k + Facing.d[l1], Block.MONSTER_EGGS.id, BlockMonsterEggs.f(l));
/* 128 */					 aK();
/* 129 */					 die();
/*		 */				 } else {
/* 131 */					 j();
/*		 */				 }
/* 133 */			 } else if ((this.target != null) && (!l())) {
/* 134 */				 this.target = null;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public float a(int i, int j, int k) {
/* 140 */		 return this.world.getTypeId(i, j - 1, k) == Block.STONE.id ? 10.0F : super.a(i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean o() {
/* 144 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/* 148 */		 if (super.canSpawn()) {
/* 149 */			 EntityHuman entityhuman = this.world.findNearbyPlayer(this, 5.0D);
/*		 */ 
/* 151 */			 return entityhuman == null;
/*		 */		 }
/* 153 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public EnumMonsterType getMonsterType()
/*		 */	 {
/* 158 */		 return EnumMonsterType.ARTHROPOD;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySilverfish
 * JD-Core Version:		0.6.0
 */