/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class Vec3D
/*		 */ {
/*	 7 */	 private static final ThreadLocal d = new Vec3DPoolThreadLocal();
/*		 */	 public double a;
/*		 */	 public double b;
/*		 */	 public double c;
/*		 */ 
/*		 */	 public static Vec3D a(double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/*	15 */		 return new Vec3D(paramDouble1, paramDouble2, paramDouble3);
/*		 */	 }
/*		 */ 
/*		 */	 public static Vec3DPool a() {
/*	19 */		 return (Vec3DPool)d.get();
/*		 */	 }
/*		 */ 
/*		 */	 protected Vec3D(double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/*	25 */		 if (paramDouble1 == -0.0D) paramDouble1 = 0.0D;
/*	26 */		 if (paramDouble2 == -0.0D) paramDouble2 = 0.0D;
/*	27 */		 if (paramDouble3 == -0.0D) paramDouble3 = 0.0D;
/*	28 */		 this.a = paramDouble1;
/*	29 */		 this.b = paramDouble2;
/*	30 */		 this.c = paramDouble3;
/*		 */	 }
/*		 */ 
/*		 */	 protected Vec3D b(double paramDouble1, double paramDouble2, double paramDouble3) {
/*	34 */		 this.a = paramDouble1;
/*	35 */		 this.b = paramDouble2;
/*	36 */		 this.c = paramDouble3;
/*	37 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public Vec3D b()
/*		 */	 {
/*	58 */		 double d1 = MathHelper.sqrt(this.a * this.a + this.b * this.b + this.c * this.c);
/*	59 */		 if (d1 < 0.0001D) return a().create(0.0D, 0.0D, 0.0D);
/*	60 */		 return a().create(this.a / d1, this.b / d1, this.c / d1);
/*		 */	 }
/*		 */ 
/*		 */	 public double b(Vec3D paramVec3D) {
/*	64 */		 return this.a * paramVec3D.a + this.b * paramVec3D.b + this.c * paramVec3D.c;
/*		 */	 }
/*		 */ 
/*		 */	 public Vec3D add(double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/*	72 */		 return a().create(this.a + paramDouble1, this.b + paramDouble2, this.c + paramDouble3);
/*		 */	 }
/*		 */ 
/*		 */	 public double d(Vec3D paramVec3D) {
/*	76 */		 double d1 = paramVec3D.a - this.a;
/*	77 */		 double d2 = paramVec3D.b - this.b;
/*	78 */		 double d3 = paramVec3D.c - this.c;
/*	79 */		 return MathHelper.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/*		 */	 }
/*		 */ 
/*		 */	 public double distanceSquared(Vec3D paramVec3D) {
/*	83 */		 double d1 = paramVec3D.a - this.a;
/*	84 */		 double d2 = paramVec3D.b - this.b;
/*	85 */		 double d3 = paramVec3D.c - this.c;
/*	86 */		 return d1 * d1 + d2 * d2 + d3 * d3;
/*		 */	 }
/*		 */ 
/*		 */	 public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
/*	90 */		 double d1 = paramDouble1 - this.a;
/*	91 */		 double d2 = paramDouble2 - this.b;
/*	92 */		 double d3 = paramDouble3 - this.c;
/*	93 */		 return d1 * d1 + d2 * d2 + d3 * d3;
/*		 */	 }
/*		 */ 
/*		 */	 public double c()
/*		 */	 {
/* 101 */		 return MathHelper.sqrt(this.a * this.a + this.b * this.b + this.c * this.c);
/*		 */	 }
/*		 */ 
/*		 */	 public Vec3D b(Vec3D paramVec3D, double paramDouble) {
/* 105 */		 double d1 = paramVec3D.a - this.a;
/* 106 */		 double d2 = paramVec3D.b - this.b;
/* 107 */		 double d3 = paramVec3D.c - this.c;
/*		 */ 
/* 109 */		 if (d1 * d1 < 1.000000011686097E-007D) return null;
/*		 */ 
/* 111 */		 double d4 = (paramDouble - this.a) / d1;
/* 112 */		 if ((d4 < 0.0D) || (d4 > 1.0D)) return null;
/* 113 */		 return a().create(this.a + d1 * d4, this.b + d2 * d4, this.c + d3 * d4);
/*		 */	 }
/*		 */ 
/*		 */	 public Vec3D c(Vec3D paramVec3D, double paramDouble) {
/* 117 */		 double d1 = paramVec3D.a - this.a;
/* 118 */		 double d2 = paramVec3D.b - this.b;
/* 119 */		 double d3 = paramVec3D.c - this.c;
/*		 */ 
/* 121 */		 if (d2 * d2 < 1.000000011686097E-007D) return null;
/*		 */ 
/* 123 */		 double d4 = (paramDouble - this.b) / d2;
/* 124 */		 if ((d4 < 0.0D) || (d4 > 1.0D)) return null;
/* 125 */		 return a().create(this.a + d1 * d4, this.b + d2 * d4, this.c + d3 * d4);
/*		 */	 }
/*		 */ 
/*		 */	 public Vec3D d(Vec3D paramVec3D, double paramDouble) {
/* 129 */		 double d1 = paramVec3D.a - this.a;
/* 130 */		 double d2 = paramVec3D.b - this.b;
/* 131 */		 double d3 = paramVec3D.c - this.c;
/*		 */ 
/* 133 */		 if (d3 * d3 < 1.000000011686097E-007D) return null;
/*		 */ 
/* 135 */		 double d4 = (paramDouble - this.c) / d3;
/* 136 */		 if ((d4 < 0.0D) || (d4 > 1.0D)) return null;
/* 137 */		 return a().create(this.a + d1 * d4, this.b + d2 * d4, this.c + d3 * d4);
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/* 141 */		 return "(" + this.a + ", " + this.b + ", " + this.c + ")";
/*		 */	 }
/*		 */ 
/*		 */	 public void a(float paramFloat)
/*		 */	 {
/* 149 */		 float f1 = MathHelper.cos(paramFloat);
/* 150 */		 float f2 = MathHelper.sin(paramFloat);
/*		 */ 
/* 152 */		 double d1 = this.a;
/* 153 */		 double d2 = this.b * f1 + this.c * f2;
/* 154 */		 double d3 = this.c * f1 - this.b * f2;
/*		 */ 
/* 156 */		 this.a = d1;
/* 157 */		 this.b = d2;
/* 158 */		 this.c = d3;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(float paramFloat) {
/* 162 */		 float f1 = MathHelper.cos(paramFloat);
/* 163 */		 float f2 = MathHelper.sin(paramFloat);
/*		 */ 
/* 165 */		 double d1 = this.a * f1 + this.c * f2;
/* 166 */		 double d2 = this.b;
/* 167 */		 double d3 = this.c * f1 - this.a * f2;
/*		 */ 
/* 169 */		 this.a = d1;
/* 170 */		 this.b = d2;
/* 171 */		 this.c = d3;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Vec3D
 * JD-Core Version:		0.6.0
 */