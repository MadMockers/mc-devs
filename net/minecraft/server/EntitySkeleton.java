/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Material;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.event.entity.EntityCombustEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class EntitySkeleton extends EntityMonster
/*		 */ {
/*	 7 */	 private static final ItemStack d = new ItemStack(Item.BOW, 1);
/*		 */ 
/*		 */	 public EntitySkeleton(World world) {
/*	10 */		 super(world);
/*	11 */		 this.texture = "/mob/skeleton.png";
/*	12 */		 this.bw = 0.25F;
/*	13 */		 this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*	14 */		 this.goalSelector.a(2, new PathfinderGoalRestrictSun(this));
/*	15 */		 this.goalSelector.a(3, new PathfinderGoalFleeSun(this, this.bw));
/*	16 */		 this.goalSelector.a(4, new PathfinderGoalArrowAttack(this, this.bw, 1, 60));
/*	17 */		 this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, this.bw));
/*	18 */		 this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
/*	19 */		 this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*	20 */		 this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
/*	21 */		 this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, true));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	25 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	29 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	33 */		 return "mob.skeleton";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	37 */		 return "mob.skeletonhurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	41 */		 return "mob.skeletonhurt";
/*		 */	 }
/*		 */ 
/*		 */	 public EnumMonsterType getMonsterType() {
/*	45 */		 return EnumMonsterType.UNDEAD;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	49 */		 if ((this.world.r()) && (!this.world.isStatic)) {
/*	50 */			 float f = c(1.0F);
/*		 */ 
/*	52 */			 if ((f > 0.5F) && (this.world.j(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ))) && (this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F))
/*		 */			 {
/*	54 */				 EntityCombustEvent event = new EntityCombustEvent(getBukkitEntity(), 8);
/*	55 */				 this.world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	57 */				 if (!event.isCancelled()) {
/*	58 */					 setOnFire(event.getDuration());
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	64 */		 super.d();
/*		 */	 }
/*		 */ 
/*		 */	 public void die(DamageSource damagesource) {
/*	68 */		 super.die(damagesource);
/*	69 */		 if (((damagesource.f() instanceof EntityArrow)) && ((damagesource.getEntity() instanceof EntityHuman))) {
/*	70 */			 EntityHuman entityhuman = (EntityHuman)damagesource.getEntity();
/*	71 */			 double d0 = entityhuman.locX - this.locX;
/*	72 */			 double d1 = entityhuman.locZ - this.locZ;
/*		 */ 
/*	74 */			 if (d0 * d0 + d1 * d1 >= 2500.0D)
/*	75 */				 entityhuman.a(AchievementList.v);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId()
/*		 */	 {
/*	81 */		 return Item.ARROW.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	86 */		 List loot = new ArrayList();
/*	87 */		 int j = this.random.nextInt(3 + i);
/*		 */ 
/*	89 */		 int count = this.random.nextInt(3 + i);
/*	90 */		 if (count > 0) {
/*	91 */			 loot.add(new org.bukkit.inventory.ItemStack(Material.ARROW, count));
/*		 */		 }
/*		 */ 
/*	94 */		 count = this.random.nextInt(3 + i);
/*	95 */		 if (count > 0) {
/*	96 */			 loot.add(new org.bukkit.inventory.ItemStack(Material.BONE, count));
/*		 */		 }
/*		 */ 
/* 100 */		 if (this.lastDamageByPlayerTime > 0) {
/* 101 */			 int k = this.random.nextInt(200) - i;
/*		 */ 
/* 103 */			 if (k < 5) {
/* 104 */				 ItemStack itemstack = l(k <= 0 ? 1 : 0);
/* 105 */				 if (itemstack != null) {
/* 106 */					 loot.add(new CraftItemStack(itemstack));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 111 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 protected ItemStack l(int i)
/*		 */	 {
/* 117 */		 if (i > 0) {
/* 118 */			 ItemStack itemstack = new ItemStack(Item.BOW);
/*		 */ 
/* 120 */			 EnchantmentManager.a(this.random, itemstack, 5);
/* 121 */			 return itemstack;
/*		 */		 }
/* 123 */		 return new ItemStack(Item.BOW.id, 1, 0);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySkeleton
 * JD-Core Version:		0.6.0
 */