/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ 
/*		 */ public class EntityChicken extends EntityAnimal
/*		 */ {
/*	 5 */	 public boolean d = false;
/*	 6 */	 public float e = 0.0F;
/*	 7 */	 public float f = 0.0F;
/*		 */	 public float g;
/*		 */	 public float h;
/*	10 */	 public float i = 1.0F;
/*		 */	 public int j;
/*		 */ 
/*		 */	 public EntityChicken(World world)
/*		 */	 {
/*	14 */		 super(world);
/*	15 */		 this.texture = "/mob/chicken.png";
/*	16 */		 a(0.3F, 0.7F);
/*	17 */		 this.j = (this.random.nextInt(6000) + 6000);
/*	18 */		 float f = 0.25F;
/*		 */ 
/*	20 */		 this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*	21 */		 this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
/*	22 */		 this.goalSelector.a(2, new PathfinderGoalBreed(this, f));
/*	23 */		 this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
/*	24 */		 this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.28F));
/*	25 */		 this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, f));
/*	26 */		 this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
/*	27 */		 this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV() {
/*	31 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	35 */		 return 4;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	39 */		 super.d();
/*	40 */		 this.h = this.e;
/*	41 */		 this.g = this.f;
/*	42 */		 this.f = (float)(this.f + (this.onGround ? -1 : 4) * 0.3D);
/*	43 */		 if (this.f < 0.0F) {
/*	44 */			 this.f = 0.0F;
/*		 */		 }
/*		 */ 
/*	47 */		 if (this.f > 1.0F) {
/*	48 */			 this.f = 1.0F;
/*		 */		 }
/*		 */ 
/*	51 */		 if ((!this.onGround) && (this.i < 1.0F)) {
/*	52 */			 this.i = 1.0F;
/*		 */		 }
/*		 */ 
/*	55 */		 this.i = (float)(this.i * 0.9D);
/*	56 */		 if ((!this.onGround) && (this.motY < 0.0D)) {
/*	57 */			 this.motY *= 0.6D;
/*		 */		 }
/*		 */ 
/*	60 */		 this.e += this.i * 2.0F;
/*	61 */		 if ((!isBaby()) && (!this.world.isStatic) && (--this.j <= 0)) {
/*	62 */			 this.world.makeSound(this, "mob.chickenplop", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*	63 */			 b(Item.EGG.id, 1);
/*	64 */			 this.j = (this.random.nextInt(6000) + 6000);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(float f) {
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	71 */		 return "mob.chicken";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	75 */		 return "mob.chickenhurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	79 */		 return "mob.chickenhurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	83 */		 return Item.FEATHER.id;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	88 */		 List loot = new ArrayList();
/*	89 */		 int j = this.random.nextInt(3) + this.random.nextInt(1 + i);
/*		 */ 
/*	91 */		 if (j > 0) {
/*	92 */			 loot.add(new ItemStack(Item.FEATHER.id, j));
/*		 */		 }
/*		 */ 
/*	95 */		 if (isBurning())
/*	96 */			 loot.add(new ItemStack(Item.COOKED_CHICKEN.id, 1));
/*		 */		 else {
/*	98 */			 loot.add(new ItemStack(Item.RAW_CHICKEN.id, 1));
/*		 */		 }
/*		 */ 
/* 101 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityAnimal createChild(EntityAnimal entityanimal)
/*		 */	 {
/* 106 */		 return new EntityChicken(this.world);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityChicken
 * JD-Core Version:		0.6.0
 */