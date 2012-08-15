/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Arrays;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldChunkManagerHell extends WorldChunkManager
/*		 */ {
/*		 */	 private BiomeBase d;
/*		 */	 private float e;
/*		 */	 private float f;
/*		 */ 
/*		 */	 public WorldChunkManagerHell(BiomeBase paramBiomeBase, float paramFloat1, float paramFloat2)
/*		 */	 {
/*	12 */		 this.d = paramBiomeBase;
/*	13 */		 this.e = paramFloat1;
/*	14 */		 this.f = paramFloat2;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase getBiome(int paramInt1, int paramInt2)
/*		 */	 {
/*	24 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase[] getBiomes(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	45 */		 if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
/*	46 */			 paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/*	49 */		 Arrays.fill(paramArrayOfBiomeBase, 0, paramInt3 * paramInt4, this.d);
/*		 */ 
/*	51 */		 return paramArrayOfBiomeBase;
/*		 */	 }
/*		 */ 
/*		 */	 public float[] getWetness(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	56 */		 if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
/*	57 */			 paramArrayOfFloat = new float[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/*	60 */		 Arrays.fill(paramArrayOfFloat, 0, paramInt3 * paramInt4, this.e);
/*	61 */		 return paramArrayOfFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public float[] getTemperatures(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	80 */		 if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
/*	81 */			 paramArrayOfFloat = new float[paramInt3 * paramInt4];
/*		 */		 }
/*	83 */		 Arrays.fill(paramArrayOfFloat, 0, paramInt3 * paramInt4, this.f);
/*		 */ 
/*	85 */		 return paramArrayOfFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase[] getBiomeBlock(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 109 */		 if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
/* 110 */			 paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/* 113 */		 Arrays.fill(paramArrayOfBiomeBase, 0, paramInt3 * paramInt4, this.d);
/*		 */ 
/* 115 */		 return paramArrayOfBiomeBase;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase[] a(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
/*		 */	 {
/* 120 */		 return getBiomeBlock(paramArrayOfBiomeBase, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition a(int paramInt1, int paramInt2, int paramInt3, List paramList, Random paramRandom)
/*		 */	 {
/* 135 */		 if (paramList.contains(this.d)) {
/* 136 */			 return new ChunkPosition(paramInt1 - paramInt3 + paramRandom.nextInt(paramInt3 * 2 + 1), 0, paramInt2 - paramInt3 + paramRandom.nextInt(paramInt3 * 2 + 1));
/*		 */		 }
/*		 */ 
/* 139 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2, int paramInt3, List paramList)
/*		 */	 {
/* 149 */		 return paramList.contains(this.d);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldChunkManagerHell
 * JD-Core Version:		0.6.0
 */