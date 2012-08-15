/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.entity.Entity;
/*		 */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*		 */ 
/*		 */ public class EntityCow extends EntityAnimal
/*		 */ {
/*		 */	 public EntityCow(World world)
/*		 */	 {
/*	11 */		 super(world);
/*	12 */		 this.texture = "/mob/cow.png";
/*	13 */		 a(0.9F, 1.3F);
/*	14 */		 getNavigation().a(true);
/*	15 */		 this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*	16 */		 this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
/*	17 */		 this.goalSelector.a(2, new PathfinderGoalBreed(this, 0.2F));
/*	18 */		 this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
/*	19 */		 this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.25F));
/*	20 */		 this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.2F));
/*	21 */		 this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
/*	22 */		 this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	26 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	30 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	34 */		 return "mob.cow";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	38 */		 return "mob.cowhurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	42 */		 return "mob.cowhurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected float aP() {
/*	46 */		 return 0.4F;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	50 */		 return Item.LEATHER.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	55 */		 List loot = new ArrayList();
/*	56 */		 int j = this.random.nextInt(3) + this.random.nextInt(1 + i);
/*		 */ 
/*	60 */		 if (j > 0) {
/*	61 */			 loot.add(new org.bukkit.inventory.ItemStack(Item.LEATHER.id, j));
/*		 */		 }
/*		 */ 
/*	64 */		 j = this.random.nextInt(3) + 1 + this.random.nextInt(1 + i);
/*		 */ 
/*	66 */		 if (j > 0) {
/*	67 */			 loot.add(new org.bukkit.inventory.ItemStack(isBurning() ? Item.COOKED_BEEF.id : Item.RAW_BEEF.id, j));
/*		 */		 }
/*		 */ 
/*	70 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman)
/*		 */	 {
/*	75 */		 ItemStack itemstack = entityhuman.inventory.getItemInHand();
/*		 */ 
/*	77 */		 if ((itemstack != null) && (itemstack.id == Item.BUCKET.id))
/*		 */		 {
/*	79 */			 Location loc = getBukkitEntity().getLocation();
/*	80 */			 PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent(entityhuman, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), -1, itemstack, Item.MILK_BUCKET);
/*		 */ 
/*	82 */			 if (event.isCancelled()) {
/*	83 */				 return false;
/*		 */			 }
/*		 */ 
/*	86 */			 if (--itemstack.count <= 0)
/*	87 */				 entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, CraftItemStack.createNMSItemStack(event.getItemStack()));
/*	88 */			 else if (!entityhuman.inventory.pickup(new ItemStack(Item.MILK_BUCKET))) {
/*	89 */				 entityhuman.drop(CraftItemStack.createNMSItemStack(event.getItemStack()));
/*		 */			 }
/*		 */ 
/*	93 */			 return true;
/*		 */		 }
/*	95 */		 return super.c(entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityAnimal createChild(EntityAnimal entityanimal)
/*		 */	 {
/* 100 */		 return new EntityCow(this.world);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityCow
 * JD-Core Version:		0.6.0
 */