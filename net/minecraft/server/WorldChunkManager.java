/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldChunkManager
/*		 */ {
/*		 */	 private GenLayer d;
/*		 */	 private GenLayer e;
/*	13 */	 private BiomeCache f = new BiomeCache(this);
/*		 */	 private List g;
/*		 */ 
/*		 */	 protected WorldChunkManager()
/*		 */	 {
/*	19 */		 this.g = new ArrayList();
/*	20 */		 this.g.add(BiomeBase.FOREST);
/*	21 */		 this.g.add(BiomeBase.PLAINS);
/*	22 */		 this.g.add(BiomeBase.TAIGA);
/*	23 */		 this.g.add(BiomeBase.TAIGA_HILLS);
/*	24 */		 this.g.add(BiomeBase.FOREST_HILLS);
/*	25 */		 this.g.add(BiomeBase.JUNGLE);
/*	26 */		 this.g.add(BiomeBase.JUNGLE_HILLS);
/*		 */	 }
/*		 */ 
/*		 */	 public WorldChunkManager(long paramLong, WorldType paramWorldType) {
/*	30 */		 this();
/*		 */ 
/*	32 */		 GenLayer[] arrayOfGenLayer = GenLayer.a(paramLong, paramWorldType);
/*	33 */		 this.d = arrayOfGenLayer[0];
/*	34 */		 this.e = arrayOfGenLayer[1];
/*		 */	 }
/*		 */ 
/*		 */	 public WorldChunkManager(World paramWorld) {
/*	38 */		 this(paramWorld.getSeed(), paramWorld.getWorldData().getType());
/*		 */	 }
/*		 */ 
/*		 */	 public List a()
/*		 */	 {
/*	47 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase getBiome(int paramInt1, int paramInt2)
/*		 */	 {
/*	55 */		 return this.f.b(paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public float[] getWetness(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	68 */		 IntCache.a();
/*	69 */		 if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
/*	70 */			 paramArrayOfFloat = new float[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/*	73 */		 int[] arrayOfInt = this.e.a(paramInt1, paramInt2, paramInt3, paramInt4);
/*	74 */		 for (int i = 0; i < paramInt3 * paramInt4; i++) {
/*	75 */			 float f1 = BiomeBase.biomes[arrayOfInt[i]].g() / 65536.0F;
/*	76 */			 if (f1 > 1.0F) f1 = 1.0F;
/*	77 */			 paramArrayOfFloat[i] = f1;
/*		 */		 }
/*		 */ 
/*	80 */		 return paramArrayOfFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public float[] getTemperatures(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 101 */		 IntCache.a();
/* 102 */		 if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
/* 103 */			 paramArrayOfFloat = new float[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/* 106 */		 int[] arrayOfInt = this.e.a(paramInt1, paramInt2, paramInt3, paramInt4);
/* 107 */		 for (int i = 0; i < paramInt3 * paramInt4; i++) {
/* 108 */			 float f1 = BiomeBase.biomes[arrayOfInt[i]].h() / 65536.0F;
/* 109 */			 if (f1 > 1.0F) f1 = 1.0F;
/* 110 */			 paramArrayOfFloat[i] = f1;
/*		 */		 }
/*		 */ 
/* 113 */		 return paramArrayOfFloat;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase[] getBiomes(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 122 */		 IntCache.a();
/* 123 */		 if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
/* 124 */			 paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/* 127 */		 int[] arrayOfInt = this.d.a(paramInt1, paramInt2, paramInt3, paramInt4);
/* 128 */		 for (int i = 0; i < paramInt3 * paramInt4; i++) {
/* 129 */			 paramArrayOfBiomeBase[i] = BiomeBase.biomes[arrayOfInt[i]];
/*		 */		 }
/*		 */ 
/* 132 */		 return paramArrayOfBiomeBase;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase[] getBiomeBlock(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 145 */		 return a(paramArrayOfBiomeBase, paramInt1, paramInt2, paramInt3, paramInt4, true);
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase[] a(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
/* 149 */		 IntCache.a();
/* 150 */		 if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
/* 151 */			 paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
/*		 */		 }
/*		 */ 
/* 154 */		 if ((paramBoolean) && (paramInt3 == 16) && (paramInt4 == 16) && ((paramInt1 & 0xF) == 0) && ((paramInt2 & 0xF) == 0)) {
/* 155 */			 localObject = this.f.e(paramInt1, paramInt2);
/* 156 */			 System.arraycopy(localObject, 0, paramArrayOfBiomeBase, 0, paramInt3 * paramInt4);
/* 157 */			 return paramArrayOfBiomeBase;
/*		 */		 }
/*		 */ 
/* 160 */		 Object localObject = this.e.a(paramInt1, paramInt2, paramInt3, paramInt4);
/* 161 */		 for (int i = 0; i < paramInt3 * paramInt4; i++) {
/* 162 */			 paramArrayOfBiomeBase[i] = BiomeBase.biomes[localObject[i]];
/*		 */		 }
/*		 */ 
/* 165 */		 return (BiomeBase)paramArrayOfBiomeBase;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt1, int paramInt2, int paramInt3, List paramList)
/*		 */	 {
/* 176 */		 int i = paramInt1 - paramInt3 >> 2;
/* 177 */		 int j = paramInt2 - paramInt3 >> 2;
/* 178 */		 int k = paramInt1 + paramInt3 >> 2;
/* 179 */		 int m = paramInt2 + paramInt3 >> 2;
/*		 */ 
/* 181 */		 int n = k - i + 1;
/* 182 */		 int i1 = m - j + 1;
/*		 */ 
/* 184 */		 int[] arrayOfInt = this.d.a(i, j, n, i1);
/* 185 */		 for (int i2 = 0; i2 < n * i1; i2++) {
/* 186 */			 BiomeBase localBiomeBase = BiomeBase.biomes[arrayOfInt[i2]];
/* 187 */			 if (!paramList.contains(localBiomeBase)) return false;
/*		 */		 }
/*		 */ 
/* 190 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition a(int paramInt1, int paramInt2, int paramInt3, List paramList, Random paramRandom)
/*		 */	 {
/* 254 */		 int i = paramInt1 - paramInt3 >> 2;
/* 255 */		 int j = paramInt2 - paramInt3 >> 2;
/* 256 */		 int k = paramInt1 + paramInt3 >> 2;
/* 257 */		 int m = paramInt2 + paramInt3 >> 2;
/*		 */ 
/* 259 */		 int n = k - i + 1;
/* 260 */		 int i1 = m - j + 1;
/* 261 */		 int[] arrayOfInt = this.d.a(i, j, n, i1);
/* 262 */		 ChunkPosition localChunkPosition = null;
/* 263 */		 int i2 = 0;
/* 264 */		 for (int i3 = 0; i3 < arrayOfInt.length; i3++) {
/* 265 */			 int i4 = i + i3 % n << 2;
/* 266 */			 int i5 = j + i3 / n << 2;
/* 267 */			 BiomeBase localBiomeBase = BiomeBase.biomes[arrayOfInt[i3]];
/* 268 */			 if ((!paramList.contains(localBiomeBase)) || (
/* 269 */				 (localChunkPosition != null) && (paramRandom.nextInt(i2 + 1) != 0))) continue;
/* 270 */			 localChunkPosition = new ChunkPosition(i4, 0, i5);
/* 271 */			 i2++;
/*		 */		 }
/*		 */ 
/* 276 */		 return localChunkPosition;
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/* 280 */		 this.f.a();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldChunkManager
 * JD-Core Version:		0.6.0
 */