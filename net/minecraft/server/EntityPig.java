/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*		 */ import org.bukkit.event.entity.PigZapEvent;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ 
/*		 */ public class EntityPig extends EntityAnimal
/*		 */ {
/*		 */	 public EntityPig(World world)
/*		 */	 {
/*	 6 */		 super(world);
/*	 7 */		 this.texture = "/mob/pig.png";
/*	 8 */		 a(0.9F, 0.9F);
/*	 9 */		 getNavigation().a(true);
/*	10 */		 float f = 0.25F;
/*		 */ 
/*	12 */		 this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*	13 */		 this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
/*	14 */		 this.goalSelector.a(2, new PathfinderGoalBreed(this, f));
/*	15 */		 this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
/*	16 */		 this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.28F));
/*	17 */		 this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, f));
/*	18 */		 this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
/*	19 */		 this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	23 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	27 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	31 */		 super.a();
/*	32 */		 this.datawatcher.a(16, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound nbttagcompound) {
/*	36 */		 super.b(nbttagcompound);
/*	37 */		 nbttagcompound.setBoolean("Saddle", hasSaddle());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound nbttagcompound) {
/*	41 */		 super.a(nbttagcompound);
/*	42 */		 setSaddle(nbttagcompound.getBoolean("Saddle"));
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	46 */		 return "mob.pig";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	50 */		 return "mob.pig";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	54 */		 return "mob.pigdeath";
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman entityhuman) {
/*	58 */		 if (super.c(entityhuman))
/*	59 */			 return true;
/*	60 */		 if ((hasSaddle()) && (!this.world.isStatic) && ((this.passenger == null) || (this.passenger == entityhuman))) {
/*	61 */			 entityhuman.mount(this);
/*	62 */			 return true;
/*		 */		 }
/*	64 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId()
/*		 */	 {
/*	69 */		 return isBurning() ? Item.GRILLED_PORK.id : Item.PORK.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	74 */		 List loot = new ArrayList();
/*	75 */		 int j = this.random.nextInt(3) + 1 + this.random.nextInt(1 + i);
/*		 */ 
/*	77 */		 if (j > 0) {
/*	78 */			 if (isBurning())
/*	79 */				 loot.add(new ItemStack(Item.GRILLED_PORK.id, j));
/*		 */			 else {
/*	81 */				 loot.add(new ItemStack(Item.PORK.id, j));
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	85 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasSaddle()
/*		 */	 {
/*	90 */		 return (this.datawatcher.getByte(16) & 0x1) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void setSaddle(boolean flag) {
/*	94 */		 if (flag)
/*	95 */			 this.datawatcher.watch(16, Byte.valueOf(1));
/*		 */		 else
/*	97 */			 this.datawatcher.watch(16, Byte.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityLightning entitylightning)
/*		 */	 {
/* 102 */		 if (!this.world.isStatic) {
/* 103 */			 EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
/*		 */ 
/* 106 */			 if (CraftEventFactory.callPigZapEvent(this, entitylightning, entitypigzombie).isCancelled()) {
/* 107 */				 return;
/*		 */			 }
/*		 */ 
/* 111 */			 entitypigzombie.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/*		 */ 
/* 113 */			 this.world.addEntity(entitypigzombie, CreatureSpawnEvent.SpawnReason.LIGHTNING);
/* 114 */			 die();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(float f) {
/* 119 */		 super.a(f);
/* 120 */		 if ((f > 5.0F) && ((this.passenger instanceof EntityHuman)))
/* 121 */			 ((EntityHuman)this.passenger).a(AchievementList.u);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityAnimal createChild(EntityAnimal entityanimal)
/*		 */	 {
/* 126 */		 return new EntityPig(this.world);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityPig
 * JD-Core Version:		0.6.0
 */