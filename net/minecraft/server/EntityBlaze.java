/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ 
/*		 */ public class EntityBlaze extends EntityMonster
/*		 */ {
/*	 5 */	 private float d = 0.5F;
/*		 */	 private int e;
/*		 */	 private int g;
/*		 */ 
/*		 */	 public EntityBlaze(World world)
/*		 */	 {
/*	10 */		 super(world);
/*	11 */		 this.texture = "/mob/fire.png";
/*	12 */		 this.fireProof = true;
/*	13 */		 this.damage = 6;
/*	14 */		 this.aV = 10;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	18 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	22 */		 super.a();
/*	23 */		 this.datawatcher.a(16, new Byte(0));
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	27 */		 return "mob.blaze.breathe";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	31 */		 return "mob.blaze.hit";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	35 */		 return "mob.blaze.death";
/*		 */	 }
/*		 */ 
/*		 */	 public float c(float f) {
/*	39 */		 return 1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	43 */		 if (!this.world.isStatic) {
/*	44 */			 if (G()) {
/*	45 */				 damageEntity(DamageSource.DROWN, 1);
/*		 */			 }
/*		 */ 
/*	48 */			 this.e -= 1;
/*	49 */			 if (this.e <= 0) {
/*	50 */				 this.e = 100;
/*	51 */				 this.d = (0.5F + (float)this.random.nextGaussian() * 3.0F);
/*		 */			 }
/*		 */ 
/*	54 */			 if ((m() != null) && (m().locY + m().getHeadHeight() > this.locY + getHeadHeight() + this.d)) {
/*	55 */				 this.motY += (0.300000011920929D - this.motY) * 0.300000011920929D;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	59 */		 if (this.random.nextInt(24) == 0) {
/*	60 */			 this.world.makeSound(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, "fire.fire", 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F);
/*		 */		 }
/*		 */ 
/*	63 */		 if ((!this.onGround) && (this.motY < 0.0D)) {
/*	64 */			 this.motY *= 0.6D;
/*		 */		 }
/*		 */ 
/*	67 */		 for (int i = 0; i < 2; i++) {
/*	68 */			 this.world.a("largesmoke", this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*		 */ 
/*	71 */		 super.d();
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity entity, float f) {
/*	75 */		 if ((this.attackTicks <= 0) && (f < 2.0F) && (entity.boundingBox.e > this.boundingBox.b) && (entity.boundingBox.b < this.boundingBox.e)) {
/*	76 */			 this.attackTicks = 20;
/*	77 */			 k(entity);
/*	78 */		 } else if (f < 30.0F) {
/*	79 */			 double d0 = entity.locX - this.locX;
/*	80 */			 double d1 = entity.boundingBox.b + entity.length / 2.0F - (this.locY + this.length / 2.0F);
/*	81 */			 double d2 = entity.locZ - this.locZ;
/*		 */ 
/*	83 */			 if (this.attackTicks == 0) {
/*	84 */				 this.g += 1;
/*	85 */				 if (this.g == 1) {
/*	86 */					 this.attackTicks = 60;
/*	87 */					 e(true);
/*	88 */				 } else if (this.g <= 4) {
/*	89 */					 this.attackTicks = 6;
/*		 */				 } else {
/*	91 */					 this.attackTicks = 100;
/*	92 */					 this.g = 0;
/*	93 */					 e(false);
/*		 */				 }
/*		 */ 
/*	96 */				 if (this.g > 1) {
/*	97 */					 float f1 = MathHelper.c(f) * 0.5F;
/*		 */ 
/*	99 */					 this.world.a((EntityHuman)null, 1009, (int)this.locX, (int)this.locY, (int)this.locZ, 0);
/*		 */ 
/* 101 */					 for (int i = 0; i < 1; i++) {
/* 102 */						 EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.world, this, d0 + this.random.nextGaussian() * f1, d1, d2 + this.random.nextGaussian() * f1);
/*		 */ 
/* 104 */						 entitysmallfireball.locY = (this.locY + this.length / 2.0F + 0.5D);
/* 105 */						 this.world.addEntity(entitysmallfireball);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 110 */			 this.yaw = ((float)(Math.atan2(d2, d0) * 180.0D / 3.141592741012573D) - 90.0F);
/* 111 */			 this.b = true;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(float f) {
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/* 118 */		 return Item.BLAZE_ROD.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isBurning() {
/* 122 */		 return n();
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i) {
/* 126 */		 if (flag)
/*		 */		 {
/* 128 */			 List loot = new ArrayList();
/* 129 */			 int j = this.random.nextInt(2 + i);
/*		 */ 
/* 131 */			 if (j > 0) {
/* 132 */				 loot.add(new ItemStack(Item.BLAZE_ROD.id, j));
/*		 */			 }
/*		 */ 
/* 135 */			 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean n()
/*		 */	 {
/* 141 */		 return (this.datawatcher.getByte(16) & 0x1) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(boolean flag) {
/* 145 */		 byte b0 = this.datawatcher.getByte(16);
/*		 */ 
/* 147 */		 if (flag)
/* 148 */			 b0 = (byte)(b0 | 0x1);
/*		 */		 else {
/* 150 */			 b0 = (byte)(b0 & 0xFFFFFFFE);
/*		 */		 }
/*		 */ 
/* 153 */		 this.datawatcher.watch(16, Byte.valueOf(b0));
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean o() {
/* 157 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityBlaze
 * JD-Core Version:		0.6.0
 */