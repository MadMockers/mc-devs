/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public abstract class EntityTameableAnimal extends EntityAnimal
/*		 */ {
/*	14 */	 protected PathfinderGoalSit d = new PathfinderGoalSit(this);
/*		 */ 
/*		 */	 public EntityTameableAnimal(World paramWorld) {
/*	17 */		 super(paramWorld);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/*	22 */		 super.a();
/*	23 */		 this.datawatcher.a(16, Byte.valueOf(0));
/*	24 */		 this.datawatcher.a(17, "");
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*	29 */		 super.b(paramNBTTagCompound);
/*	30 */		 if (getOwnerName() == null)
/*	31 */			 paramNBTTagCompound.setString("Owner", "");
/*		 */		 else {
/*	33 */			 paramNBTTagCompound.setString("Owner", getOwnerName());
/*		 */		 }
/*	35 */		 paramNBTTagCompound.setBoolean("Sitting", isSitting());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*	40 */		 super.a(paramNBTTagCompound);
/*	41 */		 String str = paramNBTTagCompound.getString("Owner");
/*	42 */		 if (str.length() > 0) {
/*	43 */			 setOwnerName(str);
/*	44 */			 setTamed(true);
/*		 */		 }
/*	46 */		 this.d.a(paramNBTTagCompound.getBoolean("Sitting"));
/*		 */	 }
/*		 */ 
/*		 */	 protected void e(boolean paramBoolean) {
/*	50 */		 String str = "heart";
/*	51 */		 if (!paramBoolean) {
/*	52 */			 str = "smoke";
/*		 */		 }
/*	54 */		 for (int i = 0; i < 7; i++) {
/*	55 */			 double d1 = this.random.nextGaussian() * 0.02D;
/*	56 */			 double d2 = this.random.nextGaussian() * 0.02D;
/*	57 */			 double d3 = this.random.nextGaussian() * 0.02D;
/*	58 */			 this.world.a(str, this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + 0.5D + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, d1, d2, d3);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isTamed()
/*		 */	 {
/*	74 */		 return (this.datawatcher.getByte(16) & 0x4) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void setTamed(boolean paramBoolean) {
/*	78 */		 int i = this.datawatcher.getByte(16);
/*	79 */		 if (paramBoolean)
/*	80 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(i | 0x4)));
/*		 */		 else
/*	82 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(i & 0xFFFFFFFB)));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isSitting()
/*		 */	 {
/*	87 */		 return (this.datawatcher.getByte(16) & 0x1) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void setSitting(boolean paramBoolean) {
/*	91 */		 int i = this.datawatcher.getByte(16);
/*	92 */		 if (paramBoolean)
/*	93 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(i | 0x1)));
/*		 */		 else
/*	95 */			 this.datawatcher.watch(16, Byte.valueOf((byte)(i & 0xFFFFFFFE)));
/*		 */	 }
/*		 */ 
/*		 */	 public String getOwnerName()
/*		 */	 {
/* 100 */		 return this.datawatcher.getString(17);
/*		 */	 }
/*		 */ 
/*		 */	 public void setOwnerName(String paramString) {
/* 104 */		 this.datawatcher.watch(17, paramString);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityLiving getOwner() {
/* 108 */		 return this.world.a(getOwnerName());
/*		 */	 }
/*		 */ 
/*		 */	 public PathfinderGoalSit r() {
/* 112 */		 return this.d;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityTameableAnimal
 * JD-Core Version:		0.6.0
 */