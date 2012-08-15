/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ 
/*		 */ public class EntityIronGolem extends EntityGolem
/*		 */ {
/*	 7 */	 private int e = 0;
/*	 8 */	 Village d = null;
/*		 */	 private int f;
/*		 */	 private int g;
/*		 */ 
/*		 */	 public EntityIronGolem(World world)
/*		 */	 {
/*	13 */		 super(world);
/*	14 */		 this.texture = "/mob/villager_golem.png";
/*	15 */		 a(1.4F, 2.9F);
/*	16 */		 getNavigation().a(true);
/*	17 */		 this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 0.25F, true));
/*	18 */		 this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.22F, 32.0F));
/*	19 */		 this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.16F, true));
/*	20 */		 this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 0.16F));
/*	21 */		 this.goalSelector.a(5, new PathfinderGoalOfferFlower(this));
/*	22 */		 this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.16F));
/*	23 */		 this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
/*	24 */		 this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*	25 */		 this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
/*	26 */		 this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
/*	27 */		 this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityMonster.class, 16.0F, 0, false, true));
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	31 */		 super.a();
/*	32 */		 this.datawatcher.a(16, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	36 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected void bd() {
/*	40 */		 if (--this.e <= 0) {
/*	41 */			 this.e = (70 + this.random.nextInt(50));
/*	42 */			 this.d = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
/*	43 */			 if (this.d == null) {
/*	44 */				 aE();
/*		 */			 } else {
/*	46 */				 ChunkCoordinates chunkcoordinates = this.d.getCenter();
/*		 */ 
/*	48 */				 b(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, this.d.getSize());
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	52 */		 super.bd();
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	56 */		 return 100;
/*		 */	 }
/*		 */ 
/*		 */	 protected int h(int i) {
/*	60 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	64 */		 super.d();
/*	65 */		 if (this.f > 0) {
/*	66 */			 this.f -= 1;
/*		 */		 }
/*		 */ 
/*	69 */		 if (this.g > 0) {
/*	70 */			 this.g -= 1;
/*		 */		 }
/*		 */ 
/*	73 */		 if ((this.motX * this.motX + this.motZ * this.motZ > 2.500000277905201E-007D) && (this.random.nextInt(5) == 0)) {
/*	74 */			 int i = MathHelper.floor(this.locX);
/*	75 */			 int j = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
/*	76 */			 int k = MathHelper.floor(this.locZ);
/*	77 */			 int l = this.world.getTypeId(i, j, k);
/*		 */ 
/*	79 */			 if (l > 0)
/*	80 */				 this.world.a("tilecrack_" + l, this.locX + (this.random.nextFloat() - 0.5D) * this.width, this.boundingBox.b + 0.1D, this.locZ + (this.random.nextFloat() - 0.5D) * this.width, 4.0D * (this.random.nextFloat() - 0.5D), 0.5D, (this.random.nextFloat() - 0.5D) * 4.0D);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(Class oclass)
/*		 */	 {
/*	86 */		 return (q()) && (EntityHuman.class.isAssignableFrom(oclass)) ? false : super.a(oclass);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k(Entity entity) {
/*	90 */		 this.f = 10;
/*	91 */		 this.world.broadcastEntityEffect(this, 4);
/*	92 */		 boolean flag = entity.damageEntity(DamageSource.mobAttack(this), 7 + this.random.nextInt(15));
/*		 */ 
/*	94 */		 if (flag) {
/*	95 */			 entity.motY += 0.4000000059604645D;
/*		 */		 }
/*		 */ 
/*	98 */		 this.world.makeSound(this, "mob.irongolem.throw", 1.0F, 1.0F);
/*	99 */		 return flag;
/*		 */	 }
/*		 */ 
/*		 */	 public Village n() {
/* 103 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(boolean flag) {
/* 107 */		 this.g = (flag ? 400 : 0);
/* 108 */		 this.world.broadcastEntityEffect(this, 11);
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/* 112 */		 return "none";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/* 116 */		 return "mob.irongolem.hit";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/* 120 */		 return "mob.irongolem.death";
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(int i, int j, int k, int l) {
/* 124 */		 this.world.makeSound(this, "mob.irongolem.walk", 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/* 129 */		 List loot = new ArrayList();
/* 130 */		 int j = this.random.nextInt(3);
/*		 */ 
/* 134 */		 if (j > 0) {
/* 135 */			 loot.add(new CraftItemStack(Block.RED_ROSE.id, j));
/*		 */		 }
/*		 */ 
/* 138 */		 int k = 3 + this.random.nextInt(3);
/*		 */ 
/* 140 */		 if (k > 0) {
/* 141 */			 loot.add(new CraftItemStack(Item.IRON_INGOT.id, k));
/*		 */		 }
/*		 */ 
/* 144 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public int p()
/*		 */	 {
/* 149 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean q() {
/* 153 */		 return (this.datawatcher.getByte(16) & 0x1) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void f(boolean flag) {
/* 157 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 159 */		 if (flag)
/* 160 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x1)));
/*		 */		 else
/* 162 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityIronGolem
 * JD-Core Version:		0.6.0
 */