/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.inventory.ItemStack;
/*		 */ 
/*		 */ public class EntitySquid extends EntityWaterAnimal
/*		 */ {
/*	 5 */	 public float d = 0.0F;
/*	 6 */	 public float e = 0.0F;
/*	 7 */	 public float f = 0.0F;
/*	 8 */	 public float g = 0.0F;
/*	 9 */	 public float h = 0.0F;
/*	10 */	 public float i = 0.0F;
/*	11 */	 public float j = 0.0F;
/*	12 */	 public float by = 0.0F;
/*	13 */	 private float bz = 0.0F;
/*	14 */	 private float bA = 0.0F;
/*	15 */	 private float bB = 0.0F;
/*	16 */	 private float bC = 0.0F;
/*	17 */	 private float bD = 0.0F;
/*	18 */	 private float bE = 0.0F;
/*		 */ 
/*		 */	 public EntitySquid(World world) {
/*	21 */		 super(world);
/*	22 */		 this.texture = "/mob/squid.png";
/*	23 */		 a(0.95F, 0.95F);
/*	24 */		 this.bA = (1.0F / (this.random.nextFloat() + 1.0F) * 0.2F);
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth() {
/*	28 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ() {
/*	32 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR() {
/*	36 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS() {
/*	40 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected float aP() {
/*	44 */		 return 0.4F;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getLootId() {
/*	48 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 protected void dropDeathLoot(boolean flag, int i)
/*		 */	 {
/*	53 */		 List loot = new ArrayList();
/*		 */ 
/*	55 */		 int count = this.random.nextInt(3 + i) + 1;
/*	56 */		 if (count > 0) {
/*	57 */			 loot.add(new ItemStack(org.bukkit.Material.INK_SACK, count));
/*		 */		 }
/*		 */ 
/*	60 */		 CraftEventFactory.callEntityDeathEvent(this, loot);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean H()
/*		 */	 {
/*	65 */		 return this.world.a(this.boundingBox.grow(0.0D, -0.6000000238418579D, 0.0D), Material.WATER, this);
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/*	69 */		 super.d();
/*	70 */		 this.e = this.d;
/*	71 */		 this.g = this.f;
/*	72 */		 this.i = this.h;
/*	73 */		 this.by = this.j;
/*	74 */		 this.h += this.bA;
/*	75 */		 if (this.h > 6.283186F) {
/*	76 */			 this.h -= 6.283186F;
/*	77 */			 if (this.random.nextInt(10) == 0) {
/*	78 */				 this.bA = (1.0F / (this.random.nextFloat() + 1.0F) * 0.2F);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	82 */		 if (H())
/*		 */		 {
/*	85 */			 if (this.h < 3.141593F) {
/*	86 */				 float f = this.h / 3.141593F;
/*	87 */				 this.j = (MathHelper.sin(f * f * 3.141593F) * 3.141593F * 0.25F);
/*	88 */				 if (f > 0.75D) {
/*	89 */					 this.bz = 1.0F;
/*	90 */					 this.bB = 1.0F;
/*		 */				 } else {
/*	92 */					 this.bB *= 0.8F;
/*		 */				 }
/*		 */			 } else {
/*	95 */				 this.j = 0.0F;
/*	96 */				 this.bz *= 0.9F;
/*	97 */				 this.bB *= 0.99F;
/*		 */			 }
/*		 */ 
/* 100 */			 if (!this.world.isStatic) {
/* 101 */				 this.motX = (this.bC * this.bz);
/* 102 */				 this.motY = (this.bD * this.bz);
/* 103 */				 this.motZ = (this.bE * this.bz);
/*		 */			 }
/*		 */ 
/* 106 */			 float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 107 */			 this.aq += (-(float)Math.atan2(this.motX, this.motZ) * 180.0F / 3.141593F - this.aq) * 0.1F;
/* 108 */			 this.yaw = this.aq;
/* 109 */			 this.f += 3.141593F * this.bB * 1.5F;
/* 110 */			 this.d += (-(float)Math.atan2(f, this.motY) * 180.0F / 3.141593F - this.d) * 0.1F;
/*		 */		 } else {
/* 112 */			 this.j = (MathHelper.abs(MathHelper.sin(this.h)) * 3.141593F * 0.25F);
/* 113 */			 if (!this.world.isStatic) {
/* 114 */				 this.motX = 0.0D;
/* 115 */				 this.motY -= 0.08D;
/* 116 */				 this.motY *= 0.9800000190734863D;
/* 117 */				 this.motZ = 0.0D;
/*		 */			 }
/*		 */ 
/* 120 */			 this.d = (float)(this.d + (-90.0F - this.d) * 0.02D);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void e(float f, float f1) {
/* 125 */		 move(this.motX, this.motY, this.motZ);
/*		 */	 }
/*		 */ 
/*		 */	 protected void be() {
/* 129 */		 this.bq += 1;
/* 130 */		 if (this.bq > 100) {
/* 131 */			 this.bC = (this.bD = this.bE = 0.0F);
/* 132 */		 } else if ((this.random.nextInt(50) == 0) || (!this.ac) || ((this.bC == 0.0F) && (this.bD == 0.0F) && (this.bE == 0.0F))) {
/* 133 */			 float f = this.random.nextFloat() * 3.141593F * 2.0F;
/*		 */ 
/* 135 */			 this.bC = (MathHelper.cos(f) * 0.2F);
/* 136 */			 this.bD = (-0.1F + this.random.nextFloat() * 0.2F);
/* 137 */			 this.bE = (MathHelper.sin(f) * 0.2F);
/*		 */		 }
/*		 */ 
/* 140 */		 bb();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn() {
/* 144 */		 return (this.locY > 45.0D) && (this.locY < 63.0D) && (super.canSpawn());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySquid
 * JD-Core Version:		0.6.0
 */