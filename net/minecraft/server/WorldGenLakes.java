/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenLakes extends WorldGenerator
/*		 */ {
/*		 */	 private int a;
/*		 */ 
/*		 */	 public WorldGenLakes(int paramInt)
/*		 */	 {
/*	14 */		 this.a = paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	19 */		 paramInt1 -= 8;
/*	20 */		 paramInt3 -= 8;
/*	21 */		 while ((paramInt2 > 5) && (paramWorld.isEmpty(paramInt1, paramInt2, paramInt3)))
/*	22 */			 paramInt2--;
/*	23 */		 if (paramInt2 <= 4) {
/*	24 */			 return false;
/*		 */		 }
/*		 */ 
/*	27 */		 paramInt2 -= 4;
/*		 */ 
/*	29 */		 boolean[] arrayOfBoolean = new boolean[2048];
/*		 */ 
/*	31 */		 int i = paramRandom.nextInt(4) + 4;
/*	32 */		 for (int j = 0; j < i; j++) {
/*	33 */			 double d1 = paramRandom.nextDouble() * 6.0D + 3.0D;
/*	34 */			 double d2 = paramRandom.nextDouble() * 4.0D + 2.0D;
/*	35 */			 double d3 = paramRandom.nextDouble() * 6.0D + 3.0D;
/*		 */ 
/*	37 */			 double d4 = paramRandom.nextDouble() * (16.0D - d1 - 2.0D) + 1.0D + d1 / 2.0D;
/*	38 */			 double d5 = paramRandom.nextDouble() * (8.0D - d2 - 4.0D) + 2.0D + d2 / 2.0D;
/*	39 */			 double d6 = paramRandom.nextDouble() * (16.0D - d3 - 2.0D) + 1.0D + d3 / 2.0D;
/*		 */ 
/*	41 */			 for (int k = 1; k < 15; k++)
/*	42 */				 for (int m = 1; m < 15; m++)
/*	43 */					 for (int n = 1; n < 7; n++) {
/*	44 */						 double d7 = (k - d4) / (d1 / 2.0D);
/*	45 */						 double d8 = (n - d5) / (d2 / 2.0D);
/*	46 */						 double d9 = (m - d6) / (d3 / 2.0D);
/*	47 */						 double d10 = d7 * d7 + d8 * d8 + d9 * d9;
/*	48 */						 if (d10 >= 1.0D) continue; arrayOfBoolean[((k * 16 + m) * 8 + n)] = true;
/*		 */					 }
/*		 */		 }
/*		 */		 int i1;
/*		 */		 int i2;
/*	54 */		 for (j = 0; j < 16; j++) {
/*	55 */			 for (i1 = 0; i1 < 16; i1++) {
/*	56 */				 for (i2 = 0; i2 < 8; i2++) {
/*	57 */					 int i3 = (arrayOfBoolean[((j * 16 + i1) * 8 + i2)] == 0) && (((j < 15) && (arrayOfBoolean[(((j + 1) * 16 + i1) * 8 + i2)] != 0)) || ((j > 0) && (arrayOfBoolean[(((j - 1) * 16 + i1) * 8 + i2)] != 0)) || ((i1 < 15) && (arrayOfBoolean[((j * 16 + (i1 + 1)) * 8 + i2)] != 0)) || ((i1 > 0) && (arrayOfBoolean[((j * 16 + (i1 - 1)) * 8 + i2)] != 0)) || ((i2 < 7) && (arrayOfBoolean[((j * 16 + i1) * 8 + (i2 + 1))] != 0)) || ((i2 > 0) && (arrayOfBoolean[((j * 16 + i1) * 8 + (i2 - 1))] != 0))) ? 1 : 0;
/*		 */ 
/*	65 */					 if (i3 != 0) {
/*	66 */						 Material localMaterial = paramWorld.getMaterial(paramInt1 + j, paramInt2 + i2, paramInt3 + i1);
/*	67 */						 if ((i2 >= 4) && (localMaterial.isLiquid())) return false;
/*	68 */						 if ((i2 < 4) && (!localMaterial.isBuildable()) && (paramWorld.getTypeId(paramInt1 + j, paramInt2 + i2, paramInt3 + i1) != this.a)) return false;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	75 */		 for (j = 0; j < 16; j++) {
/*	76 */			 for (i1 = 0; i1 < 16; i1++) {
/*	77 */				 for (i2 = 0; i2 < 8; i2++) {
/*	78 */					 if (arrayOfBoolean[((j * 16 + i1) * 8 + i2)] != 0) {
/*	79 */						 paramWorld.setRawTypeId(paramInt1 + j, paramInt2 + i2, paramInt3 + i1, i2 >= 4 ? 0 : this.a);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	85 */		 for (j = 0; j < 16; j++) {
/*	86 */			 for (i1 = 0; i1 < 16; i1++) {
/*	87 */				 for (i2 = 4; i2 < 8; i2++) {
/*	88 */					 if ((arrayOfBoolean[((j * 16 + i1) * 8 + i2)] == 0) || 
/*	89 */						 (paramWorld.getTypeId(paramInt1 + j, paramInt2 + i2 - 1, paramInt3 + i1) != Block.DIRT.id) || (paramWorld.b(EnumSkyBlock.SKY, paramInt1 + j, paramInt2 + i2, paramInt3 + i1) <= 0)) continue;
/*	90 */					 BiomeBase localBiomeBase = paramWorld.getBiome(paramInt1 + j, paramInt3 + i1);
/*	91 */					 if (localBiomeBase.A == Block.MYCEL.id)
/*	92 */						 paramWorld.setRawTypeId(paramInt1 + j, paramInt2 + i2 - 1, paramInt3 + i1, Block.MYCEL.id);
/*	93 */					 else paramWorld.setRawTypeId(paramInt1 + j, paramInt2 + i2 - 1, paramInt3 + i1, Block.GRASS.id);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 100 */		 if (Block.byId[this.a].material == Material.LAVA) {
/* 101 */			 for (j = 0; j < 16; j++) {
/* 102 */				 for (i1 = 0; i1 < 16; i1++) {
/* 103 */					 for (i2 = 0; i2 < 8; i2++) {
/* 104 */						 int i4 = (arrayOfBoolean[((j * 16 + i1) * 8 + i2)] == 0) && (((j < 15) && (arrayOfBoolean[(((j + 1) * 16 + i1) * 8 + i2)] != 0)) || ((j > 0) && (arrayOfBoolean[(((j - 1) * 16 + i1) * 8 + i2)] != 0)) || ((i1 < 15) && (arrayOfBoolean[((j * 16 + (i1 + 1)) * 8 + i2)] != 0)) || ((i1 > 0) && (arrayOfBoolean[((j * 16 + (i1 - 1)) * 8 + i2)] != 0)) || ((i2 < 7) && (arrayOfBoolean[((j * 16 + i1) * 8 + (i2 + 1))] != 0)) || ((i2 > 0) && (arrayOfBoolean[((j * 16 + i1) * 8 + (i2 - 1))] != 0))) ? 1 : 0;
/*		 */ 
/* 112 */						 if ((i4 == 0) || 
/* 113 */							 ((i2 >= 4) && (paramRandom.nextInt(2) == 0)) || (!paramWorld.getMaterial(paramInt1 + j, paramInt2 + i2, paramInt3 + i1).isBuildable())) continue;
/* 114 */						 paramWorld.setRawTypeId(paramInt1 + j, paramInt2 + i2, paramInt3 + i1, Block.STONE.id);
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 122 */		 if (Block.byId[this.a].material == Material.WATER) {
/* 123 */			 for (j = 0; j < 16; j++) {
/* 124 */				 for (i1 = 0; i1 < 16; i1++) {
/* 125 */					 i2 = 4;
/* 126 */					 if (paramWorld.u(paramInt1 + j, paramInt2 + i2, paramInt3 + i1)) {
/* 127 */						 paramWorld.setRawTypeId(paramInt1 + j, paramInt2 + i2, paramInt3 + i1, Block.ICE.id);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 132 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenLakes
 * JD-Core Version:		0.6.0
 */