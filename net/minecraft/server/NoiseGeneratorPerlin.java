/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class NoiseGeneratorPerlin extends NoiseGenerator
/*		 */ {
/*	 6 */	 private int[] d = new int[512];
/*		 */	 public double a;
/*		 */	 public double b;
/*		 */	 public double c;
/*		 */ 
/*		 */	 public NoiseGeneratorPerlin()
/*		 */	 {
/*	12 */		 this(new Random());
/*		 */	 }
/*		 */ 
/*		 */	 public NoiseGeneratorPerlin(Random paramRandom) {
/*	16 */		 this.a = (paramRandom.nextDouble() * 256.0D);
/*	17 */		 this.b = (paramRandom.nextDouble() * 256.0D);
/*	18 */		 this.c = (paramRandom.nextDouble() * 256.0D);
/*	19 */		 for (int i = 0; i < 256; i++) {
/*	20 */			 this.d[i] = i;
/*		 */		 }
/*		 */ 
/*	23 */		 for (i = 0; i < 256; i++) {
/*	24 */			 int j = paramRandom.nextInt(256 - i) + i;
/*	25 */			 int k = this.d[i];
/*	26 */			 this.d[i] = this.d[j];
/*	27 */			 this.d[j] = k;
/*		 */ 
/*	29 */			 this.d[(i + 256)] = this.d[i];
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public final double b(double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/*	71 */		 return paramDouble2 + paramDouble1 * (paramDouble3 - paramDouble2);
/*		 */	 }
/*		 */ 
/*		 */	 public final double a(int paramInt, double paramDouble1, double paramDouble2) {
/*	75 */		 int i = paramInt & 0xF;
/*		 */ 
/*	77 */		 double d1 = (1 - ((i & 0x8) >> 3)) * paramDouble1;
/*	78 */		 double d2 = (i == 12) || (i == 14) ? paramDouble1 : i < 4 ? 0.0D : paramDouble2;
/*		 */ 
/*	80 */		 return ((i & 0x1) == 0 ? d1 : -d1) + ((i & 0x2) == 0 ? d2 : -d2);
/*		 */	 }
/*		 */ 
/*		 */	 public final double a(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
/*	84 */		 int i = paramInt & 0xF;
/*		 */ 
/*	86 */		 double d1 = i < 8 ? paramDouble1 : paramDouble2;
/*	87 */		 double d2 = (i == 12) || (i == 14) ? paramDouble1 : i < 4 ? paramDouble2 : paramDouble3;
/*		 */ 
/*	89 */		 return ((i & 0x1) == 0 ? d1 : -d1) + ((i & 0x2) == 0 ? d2 : -d2);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(double[] paramArrayOfDouble, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt1, int paramInt2, int paramInt3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7)
/*		 */	 {
/*		 */		 double d6;
/*		 */		 int i5;
/*		 */		 int i6;
/*		 */		 double d7;
/* 102 */		 if (paramInt2 == 1) {
/* 103 */			 i = 0; int j = 0; int k = 0; m = 0;
/* 104 */			 double d1 = 0.0D; double d2 = 0.0D;
/* 105 */			 n = 0;
/* 106 */			 double d3 = 1.0D / paramDouble7;
/* 107 */			 for (int i1 = 0; i1 < paramInt1; i1++) {
/* 108 */				 d4 = paramDouble1 + i1 * paramDouble4 + this.a;
/* 109 */				 int i2 = (int)d4;
/* 110 */				 if (d4 < i2) i2--;
/* 111 */				 int i3 = i2 & 0xFF;
/* 112 */				 d4 -= i2;
/* 113 */				 d5 = d4 * d4 * d4 * (d4 * (d4 * 6.0D - 15.0D) + 10.0D);
/*		 */ 
/* 115 */				 for (i4 = 0; i4 < paramInt3; i4++) {
/* 116 */					 d6 = paramDouble3 + i4 * paramDouble6 + this.c;
/* 117 */					 i5 = (int)d6;
/* 118 */					 if (d6 < i5) i5--;
/* 119 */					 i6 = i5 & 0xFF;
/* 120 */					 d6 -= i5;
/* 121 */					 d7 = d6 * d6 * d6 * (d6 * (d6 * 6.0D - 15.0D) + 10.0D);
/*		 */ 
/* 123 */					 i = this.d[i3] + 0;
/* 124 */					 j = this.d[i] + i6;
/* 125 */					 k = this.d[(i3 + 1)] + 0;
/* 126 */					 m = this.d[k] + i6;
/* 127 */					 d1 = b(d5, a(this.d[j], d4, d6), a(this.d[m], d4 - 1.0D, 0.0D, d6));
/* 128 */					 d2 = b(d5, a(this.d[(j + 1)], d4, 0.0D, d6 - 1.0D), a(this.d[(m + 1)], d4 - 1.0D, 0.0D, d6 - 1.0D));
/*		 */ 
/* 130 */					 double d8 = b(d7, d1, d2);
/*		 */ 
/* 132 */					 paramArrayOfDouble[(n++)] += d8 * d3;
/*		 */				 }
/*		 */			 }
/* 135 */			 return;
/*		 */		 }
/* 137 */		 int i = 0;
/* 138 */		 double d9 = 1.0D / paramDouble7;
/* 139 */		 int m = -1;
/* 140 */		 int i7 = 0; int i8 = 0; int i9 = 0; int i10 = 0; int n = 0; int i11 = 0;
/* 141 */		 double d10 = 0.0D; double d4 = 0.0D; double d11 = 0.0D; double d5 = 0.0D;
/*		 */ 
/* 143 */		 for (int i4 = 0; i4 < paramInt1; i4++) {
/* 144 */			 d6 = paramDouble1 + i4 * paramDouble4 + this.a;
/* 145 */			 i5 = (int)d6;
/* 146 */			 if (d6 < i5) i5--;
/* 147 */			 i6 = i5 & 0xFF;
/* 148 */			 d6 -= i5;
/* 149 */			 d7 = d6 * d6 * d6 * (d6 * (d6 * 6.0D - 15.0D) + 10.0D);
/*		 */ 
/* 151 */			 for (int i12 = 0; i12 < paramInt3; i12++) {
/* 152 */				 double d12 = paramDouble3 + i12 * paramDouble6 + this.c;
/* 153 */				 int i13 = (int)d12;
/* 154 */				 if (d12 < i13) i13--;
/* 155 */				 int i14 = i13 & 0xFF;
/* 156 */				 d12 -= i13;
/* 157 */				 double d13 = d12 * d12 * d12 * (d12 * (d12 * 6.0D - 15.0D) + 10.0D);
/*		 */ 
/* 159 */				 for (int i15 = 0; i15 < paramInt2; i15++) {
/* 160 */					 double d14 = paramDouble2 + i15 * paramDouble5 + this.b;
/* 161 */					 int i16 = (int)d14;
/* 162 */					 if (d14 < i16) i16--;
/* 163 */					 int i17 = i16 & 0xFF;
/* 164 */					 d14 -= i16;
/* 165 */					 double d15 = d14 * d14 * d14 * (d14 * (d14 * 6.0D - 15.0D) + 10.0D);
/*		 */ 
/* 167 */					 if ((i15 == 0) || (i17 != m)) {
/* 168 */						 m = i17;
/* 169 */						 i7 = this.d[i6] + i17;
/* 170 */						 i8 = this.d[i7] + i14;
/* 171 */						 i9 = this.d[(i7 + 1)] + i14;
/* 172 */						 i10 = this.d[(i6 + 1)] + i17;
/* 173 */						 n = this.d[i10] + i14;
/* 174 */						 i11 = this.d[(i10 + 1)] + i14;
/* 175 */						 d10 = b(d7, a(this.d[i8], d6, d14, d12), a(this.d[n], d6 - 1.0D, d14, d12));
/* 176 */						 d4 = b(d7, a(this.d[i9], d6, d14 - 1.0D, d12), a(this.d[i11], d6 - 1.0D, d14 - 1.0D, d12));
/* 177 */						 d11 = b(d7, a(this.d[(i8 + 1)], d6, d14, d12 - 1.0D), a(this.d[(n + 1)], d6 - 1.0D, d14, d12 - 1.0D));
/* 178 */						 d5 = b(d7, a(this.d[(i9 + 1)], d6, d14 - 1.0D, d12 - 1.0D), a(this.d[(i11 + 1)], d6 - 1.0D, d14 - 1.0D, d12 - 1.0D));
/*		 */					 }
/*		 */ 
/* 181 */					 double d16 = b(d15, d10, d4);
/* 182 */					 double d17 = b(d15, d11, d5);
/* 183 */					 double d18 = b(d13, d16, d17);
/*		 */ 
/* 185 */					 paramArrayOfDouble[(i++)] += d18 * d9;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NoiseGeneratorPerlin
 * JD-Core Version:		0.6.0
 */