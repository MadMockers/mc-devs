/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class AxisAlignedBB
/*		 */ {
/*	 9 */	 private static final ThreadLocal g = new AABBPoolThreadLocal();
/*		 */	 public double a;
/*		 */	 public double b;
/*		 */	 public double c;
/*		 */	 public double d;
/*		 */	 public double e;
/*		 */	 public double f;
/*		 */ 
/*		 */	 public static AxisAlignedBB a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
/*		 */	 {
/*	17 */		 return new AxisAlignedBB(paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramDouble5, paramDouble6);
/*		 */	 }
/*		 */ 
/*		 */	 public static AABBPool a() {
/*	21 */		 return (AABBPool)g.get();
/*		 */	 }
/*		 */ 
/*		 */	 protected AxisAlignedBB(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
/*		 */	 {
/*	28 */		 this.a = paramDouble1;
/*	29 */		 this.b = paramDouble2;
/*	30 */		 this.c = paramDouble3;
/*	31 */		 this.d = paramDouble4;
/*	32 */		 this.e = paramDouble5;
/*	33 */		 this.f = paramDouble6;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB b(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6) {
/*	37 */		 this.a = paramDouble1;
/*	38 */		 this.b = paramDouble2;
/*	39 */		 this.c = paramDouble3;
/*	40 */		 this.d = paramDouble4;
/*	41 */		 this.e = paramDouble5;
/*	42 */		 this.f = paramDouble6;
/*	43 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB a(double paramDouble1, double paramDouble2, double paramDouble3) {
/*	47 */		 double d1 = this.a;
/*	48 */		 double d2 = this.b;
/*	49 */		 double d3 = this.c;
/*	50 */		 double d4 = this.d;
/*	51 */		 double d5 = this.e;
/*	52 */		 double d6 = this.f;
/*		 */ 
/*	54 */		 if (paramDouble1 < 0.0D) d1 += paramDouble1;
/*	55 */		 if (paramDouble1 > 0.0D) d4 += paramDouble1;
/*		 */ 
/*	57 */		 if (paramDouble2 < 0.0D) d2 += paramDouble2;
/*	58 */		 if (paramDouble2 > 0.0D) d5 += paramDouble2;
/*		 */ 
/*	60 */		 if (paramDouble3 < 0.0D) d3 += paramDouble3;
/*	61 */		 if (paramDouble3 > 0.0D) d6 += paramDouble3;
/*		 */ 
/*	63 */		 return a().a(d1, d2, d3, d4, d5, d6);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB grow(double paramDouble1, double paramDouble2, double paramDouble3) {
/*	67 */		 double d1 = this.a - paramDouble1;
/*	68 */		 double d2 = this.b - paramDouble2;
/*	69 */		 double d3 = this.c - paramDouble3;
/*	70 */		 double d4 = this.d + paramDouble1;
/*	71 */		 double d5 = this.e + paramDouble2;
/*	72 */		 double d6 = this.f + paramDouble3;
/*		 */ 
/*	74 */		 return a().a(d1, d2, d3, d4, d5, d6);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB c(double paramDouble1, double paramDouble2, double paramDouble3) {
/*	78 */		 return a().a(this.a + paramDouble1, this.b + paramDouble2, this.c + paramDouble3, this.d + paramDouble1, this.e + paramDouble2, this.f + paramDouble3);
/*		 */	 }
/*		 */ 
/*		 */	 public double a(AxisAlignedBB paramAxisAlignedBB, double paramDouble) {
/*	82 */		 if ((paramAxisAlignedBB.e <= this.b) || (paramAxisAlignedBB.b >= this.e)) return paramDouble;
/*	83 */		 if ((paramAxisAlignedBB.f <= this.c) || (paramAxisAlignedBB.c >= this.f)) return paramDouble;
/*		 */		 double d1;
/*	85 */		 if ((paramDouble > 0.0D) && (paramAxisAlignedBB.d <= this.a)) {
/*	86 */			 d1 = this.a - paramAxisAlignedBB.d;
/*	87 */			 if (d1 < paramDouble) paramDouble = d1;
/*		 */		 }
/*	89 */		 if ((paramDouble < 0.0D) && (paramAxisAlignedBB.a >= this.d)) {
/*	90 */			 d1 = this.d - paramAxisAlignedBB.a;
/*	91 */			 if (d1 > paramDouble) paramDouble = d1;
/*		 */		 }
/*		 */ 
/*	94 */		 return paramDouble;
/*		 */	 }
/*		 */ 
/*		 */	 public double b(AxisAlignedBB paramAxisAlignedBB, double paramDouble) {
/*	98 */		 if ((paramAxisAlignedBB.d <= this.a) || (paramAxisAlignedBB.a >= this.d)) return paramDouble;
/*	99 */		 if ((paramAxisAlignedBB.f <= this.c) || (paramAxisAlignedBB.c >= this.f)) return paramDouble;
/*		 */		 double d1;
/* 101 */		 if ((paramDouble > 0.0D) && (paramAxisAlignedBB.e <= this.b)) {
/* 102 */			 d1 = this.b - paramAxisAlignedBB.e;
/* 103 */			 if (d1 < paramDouble) paramDouble = d1;
/*		 */		 }
/* 105 */		 if ((paramDouble < 0.0D) && (paramAxisAlignedBB.b >= this.e)) {
/* 106 */			 d1 = this.e - paramAxisAlignedBB.b;
/* 107 */			 if (d1 > paramDouble) paramDouble = d1;
/*		 */		 }
/*		 */ 
/* 110 */		 return paramDouble;
/*		 */	 }
/*		 */ 
/*		 */	 public double c(AxisAlignedBB paramAxisAlignedBB, double paramDouble) {
/* 114 */		 if ((paramAxisAlignedBB.d <= this.a) || (paramAxisAlignedBB.a >= this.d)) return paramDouble;
/* 115 */		 if ((paramAxisAlignedBB.e <= this.b) || (paramAxisAlignedBB.b >= this.e)) return paramDouble;
/*		 */		 double d1;
/* 117 */		 if ((paramDouble > 0.0D) && (paramAxisAlignedBB.f <= this.c)) {
/* 118 */			 d1 = this.c - paramAxisAlignedBB.f;
/* 119 */			 if (d1 < paramDouble) paramDouble = d1;
/*		 */		 }
/* 121 */		 if ((paramDouble < 0.0D) && (paramAxisAlignedBB.c >= this.f)) {
/* 122 */			 d1 = this.f - paramAxisAlignedBB.c;
/* 123 */			 if (d1 > paramDouble) paramDouble = d1;
/*		 */		 }
/*		 */ 
/* 126 */		 return paramDouble;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(AxisAlignedBB paramAxisAlignedBB) {
/* 130 */		 if ((paramAxisAlignedBB.d <= this.a) || (paramAxisAlignedBB.a >= this.d)) return false;
/* 131 */		 if ((paramAxisAlignedBB.e <= this.b) || (paramAxisAlignedBB.b >= this.e)) return false;
/* 132 */		 return (paramAxisAlignedBB.f > this.c) && (paramAxisAlignedBB.c < this.f);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB d(double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/* 144 */		 this.a += paramDouble1;
/* 145 */		 this.b += paramDouble2;
/* 146 */		 this.c += paramDouble3;
/* 147 */		 this.d += paramDouble1;
/* 148 */		 this.e += paramDouble2;
/* 149 */		 this.f += paramDouble3;
/* 150 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(Vec3D paramVec3D)
/*		 */	 {
/* 161 */		 if ((paramVec3D.a <= this.a) || (paramVec3D.a >= this.d)) return false;
/* 162 */		 if ((paramVec3D.b <= this.b) || (paramVec3D.b >= this.e)) return false;
/* 163 */		 return (paramVec3D.c > this.c) && (paramVec3D.c < this.f);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB shrink(double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/* 175 */		 double d1 = this.a + paramDouble1;
/* 176 */		 double d2 = this.b + paramDouble2;
/* 177 */		 double d3 = this.c + paramDouble3;
/* 178 */		 double d4 = this.d - paramDouble1;
/* 179 */		 double d5 = this.e - paramDouble2;
/* 180 */		 double d6 = this.f - paramDouble3;
/*		 */ 
/* 182 */		 return a().a(d1, d2, d3, d4, d5, d6);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB clone() {
/* 186 */		 return a().a(this.a, this.b, this.c, this.d, this.e, this.f);
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(Vec3D paramVec3D1, Vec3D paramVec3D2) {
/* 190 */		 Vec3D localVec3D1 = paramVec3D1.b(paramVec3D2, this.a);
/* 191 */		 Vec3D localVec3D2 = paramVec3D1.b(paramVec3D2, this.d);
/*		 */ 
/* 193 */		 Vec3D localVec3D3 = paramVec3D1.c(paramVec3D2, this.b);
/* 194 */		 Vec3D localVec3D4 = paramVec3D1.c(paramVec3D2, this.e);
/*		 */ 
/* 196 */		 Vec3D localVec3D5 = paramVec3D1.d(paramVec3D2, this.c);
/* 197 */		 Vec3D localVec3D6 = paramVec3D1.d(paramVec3D2, this.f);
/*		 */ 
/* 199 */		 if (!b(localVec3D1)) localVec3D1 = null;
/* 200 */		 if (!b(localVec3D2)) localVec3D2 = null;
/* 201 */		 if (!c(localVec3D3)) localVec3D3 = null;
/* 202 */		 if (!c(localVec3D4)) localVec3D4 = null;
/* 203 */		 if (!d(localVec3D5)) localVec3D5 = null;
/* 204 */		 if (!d(localVec3D6)) localVec3D6 = null;
/*		 */ 
/* 206 */		 Vec3D localVec3D7 = null;
/*		 */ 
/* 208 */		 if ((localVec3D1 != null) && ((localVec3D7 == null) || (paramVec3D1.distanceSquared(localVec3D1) < paramVec3D1.distanceSquared(localVec3D7)))) localVec3D7 = localVec3D1;
/* 209 */		 if ((localVec3D2 != null) && ((localVec3D7 == null) || (paramVec3D1.distanceSquared(localVec3D2) < paramVec3D1.distanceSquared(localVec3D7)))) localVec3D7 = localVec3D2;
/* 210 */		 if ((localVec3D3 != null) && ((localVec3D7 == null) || (paramVec3D1.distanceSquared(localVec3D3) < paramVec3D1.distanceSquared(localVec3D7)))) localVec3D7 = localVec3D3;
/* 211 */		 if ((localVec3D4 != null) && ((localVec3D7 == null) || (paramVec3D1.distanceSquared(localVec3D4) < paramVec3D1.distanceSquared(localVec3D7)))) localVec3D7 = localVec3D4;
/* 212 */		 if ((localVec3D5 != null) && ((localVec3D7 == null) || (paramVec3D1.distanceSquared(localVec3D5) < paramVec3D1.distanceSquared(localVec3D7)))) localVec3D7 = localVec3D5;
/* 213 */		 if ((localVec3D6 != null) && ((localVec3D7 == null) || (paramVec3D1.distanceSquared(localVec3D6) < paramVec3D1.distanceSquared(localVec3D7)))) localVec3D7 = localVec3D6;
/*		 */ 
/* 215 */		 if (localVec3D7 == null) return null;
/*		 */ 
/* 217 */		 int i = -1;
/*		 */ 
/* 219 */		 if (localVec3D7 == localVec3D1) i = 4;
/* 220 */		 if (localVec3D7 == localVec3D2) i = 5;
/* 221 */		 if (localVec3D7 == localVec3D3) i = 0;
/* 222 */		 if (localVec3D7 == localVec3D4) i = 1;
/* 223 */		 if (localVec3D7 == localVec3D5) i = 2;
/* 224 */		 if (localVec3D7 == localVec3D6) i = 3;
/*		 */ 
/* 226 */		 return new MovingObjectPosition(0, 0, 0, i, localVec3D7);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(Vec3D paramVec3D) {
/* 230 */		 if (paramVec3D == null) return false;
/* 231 */		 return (paramVec3D.b >= this.b) && (paramVec3D.b <= this.e) && (paramVec3D.c >= this.c) && (paramVec3D.c <= this.f);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean c(Vec3D paramVec3D) {
/* 235 */		 if (paramVec3D == null) return false;
/* 236 */		 return (paramVec3D.a >= this.a) && (paramVec3D.a <= this.d) && (paramVec3D.c >= this.c) && (paramVec3D.c <= this.f);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean d(Vec3D paramVec3D) {
/* 240 */		 if (paramVec3D == null) return false;
/* 241 */		 return (paramVec3D.a >= this.a) && (paramVec3D.a <= this.d) && (paramVec3D.b >= this.b) && (paramVec3D.b <= this.e);
/*		 */	 }
/*		 */ 
/*		 */	 public void c(AxisAlignedBB paramAxisAlignedBB) {
/* 245 */		 this.a = paramAxisAlignedBB.a;
/* 246 */		 this.b = paramAxisAlignedBB.b;
/* 247 */		 this.c = paramAxisAlignedBB.c;
/* 248 */		 this.d = paramAxisAlignedBB.d;
/* 249 */		 this.e = paramAxisAlignedBB.e;
/* 250 */		 this.f = paramAxisAlignedBB.f;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/* 254 */		 return "box[" + this.a + ", " + this.b + ", " + this.c + " -> " + this.d + ", " + this.e + ", " + this.f + "]";
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.AxisAlignedBB
 * JD-Core Version:		0.6.0
 */