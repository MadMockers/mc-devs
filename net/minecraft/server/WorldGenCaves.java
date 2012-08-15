/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenCaves extends WorldGenBase
/*		 */ {
/*		 */	 protected void a(long paramLong, int paramInt1, int paramInt2, byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, double paramDouble3)
/*		 */	 {
/*	12 */		 a(paramLong, paramInt1, paramInt2, paramArrayOfByte, paramDouble1, paramDouble2, paramDouble3, 1.0F + this.b.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(long paramLong, int paramInt1, int paramInt2, byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt3, int paramInt4, double paramDouble4) {
/*	16 */		 double d1 = paramInt1 * 16 + 8;
/*	17 */		 double d2 = paramInt2 * 16 + 8;
/*		 */ 
/*	19 */		 float f1 = 0.0F;
/*	20 */		 float f2 = 0.0F;
/*	21 */		 Random localRandom = new Random(paramLong);
/*		 */ 
/*	23 */		 if (paramInt4 <= 0) {
/*	24 */			 i = this.a * 16 - 16;
/*	25 */			 paramInt4 = i - localRandom.nextInt(i / 4);
/*		 */		 }
/*	27 */		 int i = 0;
/*		 */ 
/*	29 */		 if (paramInt3 == -1) {
/*	30 */			 paramInt3 = paramInt4 / 2;
/*	31 */			 i = 1;
/*		 */		 }
/*		 */ 
/*	34 */		 int j = localRandom.nextInt(paramInt4 / 2) + paramInt4 / 4;
/*	35 */		 int k = localRandom.nextInt(6) == 0 ? 1 : 0;
/*		 */ 
/*	37 */		 for (; paramInt3 < paramInt4; paramInt3++) {
/*	38 */			 double d3 = 1.5D + MathHelper.sin(paramInt3 * 3.141593F / paramInt4) * paramFloat1 * 1.0F;
/*	39 */			 double d4 = d3 * paramDouble4;
/*		 */ 
/*	41 */			 float f3 = MathHelper.cos(paramFloat3);
/*	42 */			 float f4 = MathHelper.sin(paramFloat3);
/*	43 */			 paramDouble1 += MathHelper.cos(paramFloat2) * f3;
/*	44 */			 paramDouble2 += f4;
/*	45 */			 paramDouble3 += MathHelper.sin(paramFloat2) * f3;
/*		 */ 
/*	47 */			 if (k != 0)
/*	48 */				 paramFloat3 *= 0.92F;
/*		 */			 else {
/*	50 */				 paramFloat3 *= 0.7F;
/*		 */			 }
/*	52 */			 paramFloat3 += f2 * 0.1F;
/*	53 */			 paramFloat2 += f1 * 0.1F;
/*		 */ 
/*	55 */			 f2 *= 0.9F;
/*	56 */			 f1 *= 0.75F;
/*	57 */			 f2 += (localRandom.nextFloat() - localRandom.nextFloat()) * localRandom.nextFloat() * 2.0F;
/*	58 */			 f1 += (localRandom.nextFloat() - localRandom.nextFloat()) * localRandom.nextFloat() * 4.0F;
/*		 */ 
/*	60 */			 if ((i == 0) && (paramInt3 == j) && (paramFloat1 > 1.0F) && (paramInt4 > 0)) {
/*	61 */				 a(localRandom.nextLong(), paramInt1, paramInt2, paramArrayOfByte, paramDouble1, paramDouble2, paramDouble3, localRandom.nextFloat() * 0.5F + 0.5F, paramFloat2 - 1.570796F, paramFloat3 / 3.0F, paramInt3, paramInt4, 1.0D);
/*	62 */				 a(localRandom.nextLong(), paramInt1, paramInt2, paramArrayOfByte, paramDouble1, paramDouble2, paramDouble3, localRandom.nextFloat() * 0.5F + 0.5F, paramFloat2 + 1.570796F, paramFloat3 / 3.0F, paramInt3, paramInt4, 1.0D);
/*	63 */				 return;
/*		 */			 }
/*	65 */			 if ((i == 0) && (localRandom.nextInt(4) == 0)) {
/*		 */				 continue;
/*		 */			 }
/*	68 */			 double d5 = paramDouble1 - d1;
/*	69 */			 double d6 = paramDouble3 - d2;
/*	70 */			 double d7 = paramInt4 - paramInt3;
/*	71 */			 double d8 = paramFloat1 + 2.0F + 16.0F;
/*	72 */			 if (d5 * d5 + d6 * d6 - d7 * d7 > d8 * d8) {
/*	73 */				 return;
/*		 */			 }
/*		 */ 
/*	77 */			 if ((paramDouble1 < d1 - 16.0D - d3 * 2.0D) || (paramDouble3 < d2 - 16.0D - d3 * 2.0D) || (paramDouble1 > d1 + 16.0D + d3 * 2.0D) || (paramDouble3 > d2 + 16.0D + d3 * 2.0D)) {
/*		 */				 continue;
/*		 */			 }
/*	80 */			 int m = MathHelper.floor(paramDouble1 - d3) - paramInt1 * 16 - 1;
/*	81 */			 int n = MathHelper.floor(paramDouble1 + d3) - paramInt1 * 16 + 1;
/*		 */ 
/*	83 */			 int i1 = MathHelper.floor(paramDouble2 - d4) - 1;
/*	84 */			 int i2 = MathHelper.floor(paramDouble2 + d4) + 1;
/*		 */ 
/*	86 */			 int i3 = MathHelper.floor(paramDouble3 - d3) - paramInt2 * 16 - 1;
/*	87 */			 int i4 = MathHelper.floor(paramDouble3 + d3) - paramInt2 * 16 + 1;
/*		 */ 
/*	89 */			 if (m < 0) m = 0;
/*	90 */			 if (n > 16) n = 16;
/*		 */ 
/*	92 */			 if (i1 < 1) i1 = 1;
/*	93 */			 if (i2 > 120) i2 = 120;
/*		 */ 
/*	95 */			 if (i3 < 0) i3 = 0;
/*	96 */			 if (i4 > 16) i4 = 16;
/*		 */ 
/*	98 */			 int i5 = 0;
/*		 */			 int i9;
/*	99 */			 for (int i6 = m; (i5 == 0) && (i6 < n); i6++) {
/* 100 */				 for (int i7 = i3; (i5 == 0) && (i7 < i4); i7++) {
/* 101 */					 for (int i8 = i2 + 1; (i5 == 0) && (i8 >= i1 - 1); i8--) {
/* 102 */						 i9 = (i6 * 16 + i7) * 128 + i8;
/* 103 */						 if ((i8 >= 0) && (i8 < 128)) {
/* 104 */							 if ((paramArrayOfByte[i9] == Block.WATER.id) || (paramArrayOfByte[i9] == Block.STATIONARY_WATER.id)) {
/* 105 */								 i5 = 1;
/*		 */							 }
/* 107 */							 if ((i8 != i1 - 1) && (i6 != m) && (i6 != n - 1) && (i7 != i3) && (i7 != i4 - 1))
/* 108 */								 i8 = i1;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 113 */			 if (i5 != 0)
/*		 */				 continue;
/* 115 */			 for (i6 = m; i6 < n; i6++) {
/* 116 */				 double d9 = (i6 + paramInt1 * 16 + 0.5D - paramDouble1) / d3;
/* 117 */				 for (i9 = i3; i9 < i4; i9++) {
/* 118 */					 double d10 = (i9 + paramInt2 * 16 + 0.5D - paramDouble3) / d3;
/* 119 */					 int i10 = (i6 * 16 + i9) * 128 + i2;
/* 120 */					 int i11 = 0;
/* 121 */					 if (d9 * d9 + d10 * d10 < 1.0D) {
/* 122 */						 for (int i12 = i2 - 1; i12 >= i1; i12--) {
/* 123 */							 double d11 = (i12 + 0.5D - paramDouble2) / d4;
/* 124 */							 if ((d11 > -0.7D) && (d9 * d9 + d11 * d11 + d10 * d10 < 1.0D)) {
/* 125 */								 int i13 = paramArrayOfByte[i10];
/* 126 */								 if (i13 == Block.GRASS.id) i11 = 1;
/* 127 */								 if ((i13 == Block.STONE.id) || (i13 == Block.DIRT.id) || (i13 == Block.GRASS.id)) {
/* 128 */									 if (i12 < 10) {
/* 129 */										 paramArrayOfByte[i10] = (byte)Block.LAVA.id;
/*		 */									 } else {
/* 131 */										 paramArrayOfByte[i10] = 0;
/* 132 */										 if ((i11 != 0) && (paramArrayOfByte[(i10 - 1)] == Block.DIRT.id))
/* 133 */											 paramArrayOfByte[(i10 - 1)] = this.c.getBiome(i6 + paramInt1 * 16, i9 + paramInt2 * 16).A;
/*		 */									 }
/*		 */								 }
/*		 */							 }
/* 137 */							 i10--;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 142 */			 if (i != 0)
/*		 */				 break;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte) {
/* 148 */		 int i = this.b.nextInt(this.b.nextInt(this.b.nextInt(40) + 1) + 1);
/* 149 */		 if (this.b.nextInt(15) != 0) i = 0;
/*		 */ 
/* 151 */		 for (int j = 0; j < i; j++) {
/* 152 */			 double d1 = paramInt1 * 16 + this.b.nextInt(16);
/* 153 */			 double d2 = this.b.nextInt(this.b.nextInt(120) + 8);
/* 154 */			 double d3 = paramInt2 * 16 + this.b.nextInt(16);
/*		 */ 
/* 156 */			 int k = 1;
/* 157 */			 if (this.b.nextInt(4) == 0) {
/* 158 */				 a(this.b.nextLong(), paramInt3, paramInt4, paramArrayOfByte, d1, d2, d3);
/* 159 */				 k += this.b.nextInt(4);
/*		 */			 }
/*		 */ 
/* 162 */			 for (int m = 0; m < k; m++)
/*		 */			 {
/* 164 */				 float f1 = this.b.nextFloat() * 3.141593F * 2.0F;
/* 165 */				 float f2 = (this.b.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 166 */				 float f3 = this.b.nextFloat() * 2.0F + this.b.nextFloat();
/* 167 */				 if (this.b.nextInt(10) == 0) f3 *= (this.b.nextFloat() * this.b.nextFloat() * 3.0F + 1.0F);
/*		 */ 
/* 169 */				 a(this.b.nextLong(), paramInt3, paramInt4, paramArrayOfByte, d1, d2, d3, f3, f1, f2, 0, 0, 1.0D);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenCaves
 * JD-Core Version:		0.6.0
 */