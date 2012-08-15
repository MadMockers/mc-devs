/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class EntityEnderSignal extends Entity
/*		 */ {
/*	14 */	 public int a = 0;
/*		 */	 private double b;
/*		 */	 private double c;
/*		 */	 private double d;
/*		 */	 private int e;
/*		 */	 private boolean f;
/*		 */ 
/*		 */	 public EntityEnderSignal(World paramWorld)
/*		 */	 {
/*	20 */		 super(paramWorld);
/*	21 */		 a(0.25F, 0.25F);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public EntityEnderSignal(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/*	36 */		 super(paramWorld);
/*	37 */		 this.e = 0;
/*		 */ 
/*	39 */		 a(0.25F, 0.25F);
/*		 */ 
/*	41 */		 setPosition(paramDouble1, paramDouble2, paramDouble3);
/*	42 */		 this.height = 0.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(double paramDouble1, int paramInt, double paramDouble2)
/*		 */	 {
/*	47 */		 double d1 = paramDouble1 - this.locX; double d2 = paramDouble2 - this.locZ;
/*	48 */		 float f1 = MathHelper.sqrt(d1 * d1 + d2 * d2);
/*		 */ 
/*	50 */		 if (f1 > 12.0F) {
/*	51 */			 this.b = (this.locX + d1 / f1 * 12.0D);
/*	52 */			 this.d = (this.locZ + d2 / f1 * 12.0D);
/*	53 */			 this.c = (this.locY + 8.0D);
/*		 */		 } else {
/*	55 */			 this.b = paramDouble1;
/*	56 */			 this.c = paramInt;
/*	57 */			 this.d = paramDouble2;
/*		 */		 }
/*		 */ 
/*	60 */		 this.e = 0;
/*	61 */		 this.f = (this.random.nextInt(5) > 0);
/*		 */	 }
/*		 */ 
/*		 */	 public void h_()
/*		 */	 {
/*	78 */		 this.S = this.locX;
/*	79 */		 this.T = this.locY;
/*	80 */		 this.U = this.locZ;
/*	81 */		 super.h_();
/*		 */ 
/*	83 */		 this.locX += this.motX;
/*	84 */		 this.locY += this.motY;
/*	85 */		 this.locZ += this.motZ;
/*		 */ 
/*	87 */		 float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*	88 */		 this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D);
/*	89 */		 this.pitch = (float)(Math.atan2(this.motY, f1) * 180.0D / 3.141592741012573D);
/*		 */ 
/*	91 */		 while (this.pitch - this.lastPitch < -180.0F)
/*	92 */			 this.lastPitch -= 360.0F;
/*	93 */		 while (this.pitch - this.lastPitch >= 180.0F) {
/*	94 */			 this.lastPitch += 360.0F;
/*		 */		 }
/*	96 */		 while (this.yaw - this.lastYaw < -180.0F)
/*	97 */			 this.lastYaw -= 360.0F;
/*	98 */		 while (this.yaw - this.lastYaw >= 180.0F) {
/*	99 */			 this.lastYaw += 360.0F;
/*		 */		 }
/* 101 */		 this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
/* 102 */		 this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
/*		 */ 
/* 104 */		 if (!this.world.isStatic) {
/* 105 */			 double d1 = this.b - this.locX; double d2 = this.d - this.locZ;
/* 106 */			 float f2 = (float)Math.sqrt(d1 * d1 + d2 * d2);
/* 107 */			 float f3 = (float)Math.atan2(d2, d1);
/* 108 */			 double d3 = f1 + (f2 - f1) * 0.0025D;
/* 109 */			 if (f2 < 1.0F) {
/* 110 */				 d3 *= 0.8D;
/* 111 */				 this.motY *= 0.8D;
/*		 */			 }
/* 113 */			 this.motX = (Math.cos(f3) * d3);
/* 114 */			 this.motZ = (Math.sin(f3) * d3);
/*		 */ 
/* 116 */			 if (this.locY < this.c)
/* 117 */				 this.motY += (1.0D - this.motY) * 0.01499999966472387D;
/*		 */			 else {
/* 119 */				 this.motY += (-1.0D - this.motY) * 0.01499999966472387D;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 124 */		 float f4 = 0.25F;
/* 125 */		 if (H()) {
/* 126 */			 for (int i = 0; i < 4; i++) {
/* 127 */				 this.world.a("bubble", this.locX - this.motX * f4, this.locY - this.motY * f4, this.locZ - this.motZ * f4, this.motX, this.motY, this.motZ);
/*		 */			 }
/*		 */		 }
/*		 */		 else {
/* 131 */			 this.world.a("portal", this.locX - this.motX * f4 + this.random.nextDouble() * 0.6D - 0.3D, this.locY - this.motY * f4 - 0.5D, this.locZ - this.motZ * f4 + this.random.nextDouble() * 0.6D - 0.3D, this.motX, this.motY, this.motZ);
/*		 */		 }
/*		 */ 
/* 134 */		 if (!this.world.isStatic) {
/* 135 */			 setPosition(this.locX, this.locY, this.locZ);
/*		 */ 
/* 137 */			 this.e += 1;
/* 138 */			 if ((this.e > 80) && (!this.world.isStatic)) {
/* 139 */				 die();
/* 140 */				 if (this.f)
/* 141 */					 this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Item.EYE_OF_ENDER)));
/*		 */				 else
/* 143 */					 this.world.triggerEffect(2003, (int)Math.round(this.locX), (int)Math.round(this.locY), (int)Math.round(this.locZ), 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public float c(float paramFloat)
/*		 */	 {
/* 164 */		 return 1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean an()
/*		 */	 {
/* 174 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityEnderSignal
 * JD-Core Version:		0.6.0
 */