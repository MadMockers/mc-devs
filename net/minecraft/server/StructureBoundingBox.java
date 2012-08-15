/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class StructureBoundingBox
/*		 */ {
/*		 */	 public int a;
/*		 */	 public int b;
/*		 */	 public int c;
/*		 */	 public int d;
/*		 */	 public int e;
/*		 */	 public int f;
/*		 */ 
/*		 */	 public StructureBoundingBox()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a()
/*		 */	 {
/*	14 */		 return new StructureBoundingBox(2147483647, 2147483647, 2147483647, -2147483648, -2147483648, -2147483648);
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10) {
/*	18 */		 switch (paramInt10) {
/*		 */		 default:
/*	20 */			 return new StructureBoundingBox(paramInt1 + paramInt4, paramInt2 + paramInt5, paramInt3 + paramInt6, paramInt1 + paramInt7 - 1 + paramInt4, paramInt2 + paramInt8 - 1 + paramInt5, paramInt3 + paramInt9 - 1 + paramInt6);
/*		 */		 case 2:
/*	23 */			 return new StructureBoundingBox(paramInt1 + paramInt4, paramInt2 + paramInt5, paramInt3 - paramInt9 + 1 + paramInt6, paramInt1 + paramInt7 - 1 + paramInt4, paramInt2 + paramInt8 - 1 + paramInt5, paramInt3 + paramInt6);
/*		 */		 case 0:
/*	26 */			 return new StructureBoundingBox(paramInt1 + paramInt4, paramInt2 + paramInt5, paramInt3 + paramInt6, paramInt1 + paramInt7 - 1 + paramInt4, paramInt2 + paramInt8 - 1 + paramInt5, paramInt3 + paramInt9 - 1 + paramInt6);
/*		 */		 case 1:
/*	29 */			 return new StructureBoundingBox(paramInt1 - paramInt9 + 1 + paramInt6, paramInt2 + paramInt5, paramInt3 + paramInt4, paramInt1 + paramInt6, paramInt2 + paramInt8 - 1 + paramInt5, paramInt3 + paramInt7 - 1 + paramInt4);
/*		 */		 case 3:
/*		 */		 }
/*	32 */		 return new StructureBoundingBox(paramInt1 + paramInt6, paramInt2 + paramInt5, paramInt3 + paramInt4, paramInt1 + paramInt9 - 1 + paramInt6, paramInt2 + paramInt8 - 1 + paramInt5, paramInt3 + paramInt7 - 1 + paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public StructureBoundingBox(StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/*	37 */		 this.a = paramStructureBoundingBox.a;
/*	38 */		 this.b = paramStructureBoundingBox.b;
/*	39 */		 this.c = paramStructureBoundingBox.c;
/*	40 */		 this.d = paramStructureBoundingBox.d;
/*	41 */		 this.e = paramStructureBoundingBox.e;
/*	42 */		 this.f = paramStructureBoundingBox.f;
/*		 */	 }
/*		 */ 
/*		 */	 public StructureBoundingBox(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*	46 */		 this.a = paramInt1;
/*	47 */		 this.b = paramInt2;
/*	48 */		 this.c = paramInt3;
/*	49 */		 this.d = paramInt4;
/*	50 */		 this.e = paramInt5;
/*	51 */		 this.f = paramInt6;
/*		 */	 }
/*		 */ 
/*		 */	 public StructureBoundingBox(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*	55 */		 this.a = paramInt1;
/*	56 */		 this.c = paramInt2;
/*	57 */		 this.d = paramInt3;
/*	58 */		 this.f = paramInt4;
/*		 */ 
/*	61 */		 this.b = 1;
/*	62 */		 this.e = 512;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(StructureBoundingBox paramStructureBoundingBox) {
/*	66 */		 return (this.d >= paramStructureBoundingBox.a) && (this.a <= paramStructureBoundingBox.d) && (this.f >= paramStructureBoundingBox.c) && (this.c <= paramStructureBoundingBox.f) && (this.e >= paramStructureBoundingBox.b) && (this.b <= paramStructureBoundingBox.e);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	74 */		 return (this.d >= paramInt1) && (this.a <= paramInt3) && (this.f >= paramInt2) && (this.c <= paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(StructureBoundingBox paramStructureBoundingBox) {
/*	78 */		 this.a = Math.min(this.a, paramStructureBoundingBox.a);
/*	79 */		 this.b = Math.min(this.b, paramStructureBoundingBox.b);
/*	80 */		 this.c = Math.min(this.c, paramStructureBoundingBox.c);
/*	81 */		 this.d = Math.max(this.d, paramStructureBoundingBox.d);
/*	82 */		 this.e = Math.max(this.e, paramStructureBoundingBox.e);
/*	83 */		 this.f = Math.max(this.f, paramStructureBoundingBox.f);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 102 */		 this.a += paramInt1;
/* 103 */		 this.b += paramInt2;
/* 104 */		 this.c += paramInt3;
/* 105 */		 this.d += paramInt1;
/* 106 */		 this.e += paramInt2;
/* 107 */		 this.f += paramInt3;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(int paramInt1, int paramInt2, int paramInt3) {
/* 111 */		 return (paramInt1 >= this.a) && (paramInt1 <= this.d) && (paramInt3 >= this.c) && (paramInt3 <= this.f) && (paramInt2 >= this.b) && (paramInt2 <= this.e);
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/* 115 */		 return this.d - this.a + 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int c() {
/* 119 */		 return this.e - this.b + 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int d() {
/* 123 */		 return this.f - this.c + 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 127 */		 return this.a + (this.d - this.a + 1) / 2;
/*		 */	 }
/*		 */ 
/*		 */	 public int f() {
/* 131 */		 return this.b + (this.e - this.b + 1) / 2;
/*		 */	 }
/*		 */ 
/*		 */	 public int g() {
/* 135 */		 return this.c + (this.f - this.c + 1) / 2;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/* 139 */		 return "(" + this.a + ", " + this.b + ", " + this.c + "; " + this.d + ", " + this.e + ", " + this.f + ")";
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.StructureBoundingBox
 * JD-Core Version:		0.6.0
 */