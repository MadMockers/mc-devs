/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenCanyon extends WorldGenBase
/*		 */ {
/*	10 */	 private float[] d = new float[1024];
/*		 */ 
/*		 */	 protected void a(long paramLong, int paramInt1, int paramInt2, byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt3, int paramInt4, double paramDouble4) {
/*	13 */		 Random localRandom = new Random(paramLong);
/*		 */ 
/*	15 */		 double d1 = paramInt1 * 16 + 8;
/*	16 */		 double d2 = paramInt2 * 16 + 8;
/*		 */ 
/*	18 */		 float f1 = 0.0F;
/*	19 */		 float f2 = 0.0F;
/*		 */ 
/*	21 */		 if (paramInt4 <= 0) {
/*	22 */			 i = this.a * 16 - 16;
/*	23 */			 paramInt4 = i - localRandom.nextInt(i / 4);
/*		 */		 }
/*	25 */		 int i = 0;
/*		 */ 
/*	27 */		 if (paramInt3 == -1) {
/*	28 */			 paramInt3 = paramInt4 / 2;
/*	29 */			 i = 1;
/*		 */		 }
/*		 */ 
/*	32 */		 float f3 = 1.0F;
/*	33 */		 for (int j = 0; j < 128; j++) {
/*	34 */			 if ((j == 0) || (localRandom.nextInt(3) == 0)) {
/*	35 */				 f3 = 1.0F + localRandom.nextFloat() * localRandom.nextFloat() * 1.0F;
/*		 */			 }
/*	37 */			 this.d[j] = (f3 * f3);
/*		 */		 }
/*		 */ 
/*	40 */		 for (; paramInt3 < paramInt4; paramInt3++) {
/*	41 */			 double d3 = 1.5D + MathHelper.sin(paramInt3 * 3.141593F / paramInt4) * paramFloat1 * 1.0F;
/*	42 */			 double d4 = d3 * paramDouble4;
/*		 */ 
/*	44 */			 d3 *= (localRandom.nextFloat() * 0.25D + 0.75D);
/*	45 */			 d4 *= (localRandom.nextFloat() * 0.25D + 0.75D);
/*		 */ 
/*	47 */			 float f4 = MathHelper.cos(paramFloat3);
/*	48 */			 float f5 = MathHelper.sin(paramFloat3);
/*	49 */			 paramDouble1 += MathHelper.cos(paramFloat2) * f4;
/*	50 */			 paramDouble2 += f5;
/*	51 */			 paramDouble3 += MathHelper.sin(paramFloat2) * f4;
/*		 */ 
/*	53 */			 paramFloat3 *= 0.7F;
/*		 */ 
/*	55 */			 paramFloat3 += f2 * 0.05F;
/*	56 */			 paramFloat2 += f1 * 0.05F;
/*		 */ 
/*	58 */			 f2 *= 0.8F;
/*	59 */			 f1 *= 0.5F;
/*	60 */			 f2 += (localRandom.nextFloat() - localRandom.nextFloat()) * localRandom.nextFloat() * 2.0F;
/*	61 */			 f1 += (localRandom.nextFloat() - localRandom.nextFloat()) * localRandom.nextFloat() * 4.0F;
/*		 */ 
/*	63 */			 if ((i == 0) && (localRandom.nextInt(4) == 0)) {
/*		 */				 continue;
/*		 */			 }
/*	66 */			 double d5 = paramDouble1 - d1;
/*	67 */			 double d6 = paramDouble3 - d2;
/*	68 */			 double d7 = paramInt4 - paramInt3;
/*	69 */			 double d8 = paramFloat1 + 2.0F + 16.0F;
/*	70 */			 if (d5 * d5 + d6 * d6 - d7 * d7 > d8 * d8) {
/*	71 */				 return;
/*		 */			 }
/*		 */ 
/*	75 */			 if ((paramDouble1 < d1 - 16.0D - d3 * 2.0D) || (paramDouble3 < d2 - 16.0D - d3 * 2.0D) || (paramDouble1 > d1 + 16.0D + d3 * 2.0D) || (paramDouble3 > d2 + 16.0D + d3 * 2.0D)) {
/*		 */				 continue;
/*		 */			 }
/*	78 */			 int k = MathHelper.floor(paramDouble1 - d3) - paramInt1 * 16 - 1;
/*	79 */			 int m = MathHelper.floor(paramDouble1 + d3) - paramInt1 * 16 + 1;
/*		 */ 
/*	81 */			 int n = MathHelper.floor(paramDouble2 - d4) - 1;
/*	82 */			 int i1 = MathHelper.floor(paramDouble2 + d4) + 1;
/*		 */ 
/*	84 */			 int i2 = MathHelper.floor(paramDouble3 - d3) - paramInt2 * 16 - 1;
/*	85 */			 int i3 = MathHelper.floor(paramDouble3 + d3) - paramInt2 * 16 + 1;
/*		 */ 
/*	87 */			 if (k < 0) k = 0;
/*	88 */			 if (m > 16) m = 16;
/*		 */ 
/*	90 */			 if (n < 1) n = 1;
/*	91 */			 if (i1 > 120) i1 = 120;
/*		 */ 
/*	93 */			 if (i2 < 0) i2 = 0;
/*	94 */			 if (i3 > 16) i3 = 16;
/*		 */ 
/*	96 */			 int i4 = 0;
/*		 */			 int i8;
/*	97 */			 for (int i5 = k; (i4 == 0) && (i5 < m); i5++) {
/*	98 */				 for (int i6 = i2; (i4 == 0) && (i6 < i3); i6++) {
/*	99 */					 for (int i7 = i1 + 1; (i4 == 0) && (i7 >= n - 1); i7--) {
/* 100 */						 i8 = (i5 * 16 + i6) * 128 + i7;
/* 101 */						 if ((i7 >= 0) && (i7 < 128)) {
/* 102 */							 if ((paramArrayOfByte[i8] == Block.WATER.id) || (paramArrayOfByte[i8] == Block.STATIONARY_WATER.id)) {
/* 103 */								 i4 = 1;
/*		 */							 }
/* 105 */							 if ((i7 != n - 1) && (i5 != k) && (i5 != m - 1) && (i6 != i2) && (i6 != i3 - 1))
/* 106 */								 i7 = n;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 111 */			 if (i4 != 0)
/*		 */				 continue;
/* 113 */			 for (i5 = k; i5 < m; i5++) {
/* 114 */				 double d9 = (i5 + paramInt1 * 16 + 0.5D - paramDouble1) / d3;
/* 115 */				 for (i8 = i2; i8 < i3; i8++) {
/* 116 */					 double d10 = (i8 + paramInt2 * 16 + 0.5D - paramDouble3) / d3;
/* 117 */					 int i9 = (i5 * 16 + i8) * 128 + i1;
/* 118 */					 int i10 = 0;
/* 119 */					 if (d9 * d9 + d10 * d10 < 1.0D) {
/* 120 */						 for (int i11 = i1 - 1; i11 >= n; i11--) {
/* 121 */							 double d11 = (i11 + 0.5D - paramDouble2) / d4;
/* 122 */							 if ((d9 * d9 + d10 * d10) * this.d[i11] + d11 * d11 / 6.0D < 1.0D) {
/* 123 */								 int i12 = paramArrayOfByte[i9];
/* 124 */								 if (i12 == Block.GRASS.id) i10 = 1;
/* 125 */								 if ((i12 == Block.STONE.id) || (i12 == Block.DIRT.id) || (i12 == Block.GRASS.id)) {
/* 126 */									 if (i11 < 10) {
/* 127 */										 paramArrayOfByte[i9] = (byte)Block.LAVA.id;
/*		 */									 } else {
/* 129 */										 paramArrayOfByte[i9] = 0;
/* 130 */										 if ((i10 != 0) && (paramArrayOfByte[(i9 - 1)] == Block.DIRT.id))
/* 131 */											 paramArrayOfByte[(i9 - 1)] = this.c.getBiome(i5 + paramInt1 * 16, i8 + paramInt2 * 16).A;
/*		 */									 }
/*		 */								 }
/*		 */							 }
/* 135 */							 i9--;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 140 */			 if (i != 0)
/*		 */				 break;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte) {
/* 146 */		 if (this.b.nextInt(50) != 0) return;
/* 147 */		 double d1 = paramInt1 * 16 + this.b.nextInt(16);
/* 148 */		 double d2 = this.b.nextInt(this.b.nextInt(40) + 8) + 20;
/* 149 */		 double d3 = paramInt2 * 16 + this.b.nextInt(16);
/*		 */ 
/* 151 */		 int i = 1;
/*		 */ 
/* 153 */		 for (int j = 0; j < i; j++)
/*		 */		 {
/* 155 */			 float f1 = this.b.nextFloat() * 3.141593F * 2.0F;
/* 156 */			 float f2 = (this.b.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 157 */			 float f3 = (this.b.nextFloat() * 2.0F + this.b.nextFloat()) * 2.0F;
/*		 */ 
/* 159 */			 a(this.b.nextLong(), paramInt3, paramInt4, paramArrayOfByte, d1, d2, d3, f3, f1, f2, 0, 0, 3.0D);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenCanyon
 * JD-Core Version:		0.6.0
 */