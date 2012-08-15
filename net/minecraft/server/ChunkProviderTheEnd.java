/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class ChunkProviderTheEnd
/*		 */	 implements IChunkProvider
/*		 */ {
/*		 */	 private Random i;
/*		 */	 private NoiseGeneratorOctaves j;
/*		 */	 private NoiseGeneratorOctaves k;
/*		 */	 private NoiseGeneratorOctaves l;
/*		 */	 public NoiseGeneratorOctaves a;
/*		 */	 public NoiseGeneratorOctaves b;
/*		 */	 private World m;
/*		 */	 private double[] n;
/*		 */	 private BiomeBase[] o;
/*		 */	 double[] c;
/*		 */	 double[] d;
/*		 */	 double[] e;
/*		 */	 double[] f;
/*		 */	 double[] g;
/* 264 */	 int[][] h = new int[32][32];
/*		 */ 
/*		 */	 public ChunkProviderTheEnd(World paramWorld, long paramLong)
/*		 */	 {
/*	35 */		 this.m = paramWorld;
/*		 */ 
/*	37 */		 this.i = new Random(paramLong);
/*	38 */		 this.j = new NoiseGeneratorOctaves(this.i, 16);
/*	39 */		 this.k = new NoiseGeneratorOctaves(this.i, 16);
/*	40 */		 this.l = new NoiseGeneratorOctaves(this.i, 8);
/*		 */ 
/*	42 */		 this.a = new NoiseGeneratorOctaves(this.i, 10);
/*	43 */		 this.b = new NoiseGeneratorOctaves(this.i, 16);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte, BiomeBase[] paramArrayOfBiomeBase)
/*		 */	 {
/*	49 */		 int i1 = 2;
/*		 */ 
/*	51 */		 int i2 = i1 + 1;
/*	52 */		 int i3 = 33;
/*	53 */		 int i4 = i1 + 1;
/*	54 */		 this.n = a(this.n, paramInt1 * i1, 0, paramInt2 * i1, i2, i3, i4);
/*		 */ 
/*	56 */		 for (int i5 = 0; i5 < i1; i5++)
/*	57 */			 for (int i6 = 0; i6 < i1; i6++)
/*	58 */				 for (int i7 = 0; i7 < 32; i7++) {
/*	59 */					 double d1 = 0.25D;
/*	60 */					 double d2 = this.n[(((i5 + 0) * i4 + (i6 + 0)) * i3 + (i7 + 0))];
/*	61 */					 double d3 = this.n[(((i5 + 0) * i4 + (i6 + 1)) * i3 + (i7 + 0))];
/*	62 */					 double d4 = this.n[(((i5 + 1) * i4 + (i6 + 0)) * i3 + (i7 + 0))];
/*	63 */					 double d5 = this.n[(((i5 + 1) * i4 + (i6 + 1)) * i3 + (i7 + 0))];
/*		 */ 
/*	65 */					 double d6 = (this.n[(((i5 + 0) * i4 + (i6 + 0)) * i3 + (i7 + 1))] - d2) * d1;
/*	66 */					 double d7 = (this.n[(((i5 + 0) * i4 + (i6 + 1)) * i3 + (i7 + 1))] - d3) * d1;
/*	67 */					 double d8 = (this.n[(((i5 + 1) * i4 + (i6 + 0)) * i3 + (i7 + 1))] - d4) * d1;
/*	68 */					 double d9 = (this.n[(((i5 + 1) * i4 + (i6 + 1)) * i3 + (i7 + 1))] - d5) * d1;
/*		 */ 
/*	70 */					 for (int i8 = 0; i8 < 4; i8++) {
/*	71 */						 double d10 = 0.125D;
/*		 */ 
/*	73 */						 double d11 = d2;
/*	74 */						 double d12 = d3;
/*	75 */						 double d13 = (d4 - d2) * d10;
/*	76 */						 double d14 = (d5 - d3) * d10;
/*		 */ 
/*	78 */						 for (int i9 = 0; i9 < 8; i9++) {
/*	79 */							 int i10 = i9 + i5 * 8 << 11 | 0 + i6 * 8 << 7 | i7 * 4 + i8;
/*	80 */							 int i11 = 128;
/*	81 */							 double d15 = 0.125D;
/*		 */ 
/*	83 */							 double d16 = d11;
/*	84 */							 double d17 = (d12 - d11) * d15;
/*	85 */							 for (int i12 = 0; i12 < 8; i12++) {
/*	86 */								 int i13 = 0;
/*	87 */								 if (d16 > 0.0D) {
/*	88 */									 i13 = Block.WHITESTONE.id;
/*		 */								 }
/*		 */ 
/*	92 */								 paramArrayOfByte[i10] = (byte)i13;
/*	93 */								 i10 += i11;
/*	94 */								 d16 += d17;
/*		 */							 }
/*	96 */							 d11 += d13;
/*	97 */							 d12 += d14;
/*		 */						 }
/*		 */ 
/* 100 */						 d2 += d6;
/* 101 */						 d3 += d7;
/* 102 */						 d4 += d8;
/* 103 */						 d5 += d9;
/*		 */					 }
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int paramInt1, int paramInt2, byte[] paramArrayOfByte, BiomeBase[] paramArrayOfBiomeBase)
/*		 */	 {
/* 111 */		 for (int i1 = 0; i1 < 16; i1++)
/* 112 */			 for (int i2 = 0; i2 < 16; i2++) {
/* 113 */				 int i3 = 1;
/* 114 */				 int i4 = -1;
/*		 */ 
/* 116 */				 int i5 = (byte)Block.WHITESTONE.id;
/* 117 */				 int i6 = (byte)Block.WHITESTONE.id;
/*		 */ 
/* 119 */				 for (int i7 = 127; i7 >= 0; i7--) {
/* 120 */					 int i8 = (i2 * 16 + i1) * 128 + i7;
/*		 */ 
/* 122 */					 int i9 = paramArrayOfByte[i8];
/*		 */ 
/* 124 */					 if (i9 == 0)
/* 125 */						 i4 = -1;
/* 126 */					 else if (i9 == Block.STONE.id)
/* 127 */						 if (i4 == -1) {
/* 128 */							 if (i3 <= 0) {
/* 129 */								 i5 = 0;
/* 130 */								 i6 = (byte)Block.WHITESTONE.id;
/*		 */							 }
/*		 */ 
/* 133 */							 i4 = i3;
/* 134 */							 if (i7 >= 0) paramArrayOfByte[i8] = i5; else
/* 135 */								 paramArrayOfByte[i8] = i6;
/* 136 */						 } else if (i4 > 0) {
/* 137 */							 i4--;
/* 138 */							 paramArrayOfByte[i8] = i6;
/*		 */						 }
/*		 */				 }
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getChunkAt(int paramInt1, int paramInt2)
/*		 */	 {
/* 149 */		 return getOrCreateChunk(paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/* 153 */		 this.i.setSeed(paramInt1 * 341873128712L + paramInt2 * 132897987541L);
/*		 */ 
/* 155 */		 byte[] arrayOfByte1 = new byte[32768];
/* 156 */		 this.o = this.m.getWorldChunkManager().getBiomeBlock(this.o, paramInt1 * 16, paramInt2 * 16, 16, 16);
/*		 */ 
/* 158 */		 a(paramInt1, paramInt2, arrayOfByte1, this.o);
/* 159 */		 b(paramInt1, paramInt2, arrayOfByte1, this.o);
/*		 */ 
/* 161 */		 Chunk localChunk = new Chunk(this.m, arrayOfByte1, paramInt1, paramInt2);
/* 162 */		 byte[] arrayOfByte2 = localChunk.m();
/*		 */ 
/* 164 */		 for (int i1 = 0; i1 < arrayOfByte2.length; i1++) {
/* 165 */			 arrayOfByte2[i1] = (byte)this.o[i1].id;
/*		 */		 }
/*		 */ 
/* 168 */		 localChunk.initLighting();
/*		 */ 
/* 170 */		 return localChunk;
/*		 */	 }
/*		 */ 
/*		 */	 private double[] a(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*		 */	 {
/* 176 */		 if (paramArrayOfDouble == null) {
/* 177 */			 paramArrayOfDouble = new double[paramInt4 * paramInt5 * paramInt6];
/*		 */		 }
/*		 */ 
/* 180 */		 double d1 = 684.41200000000003D;
/* 181 */		 double d2 = 684.41200000000003D;
/*		 */ 
/* 183 */		 this.f = this.a.a(this.f, paramInt1, paramInt3, paramInt4, paramInt6, 1.121D, 1.121D, 0.5D);
/* 184 */		 this.g = this.b.a(this.g, paramInt1, paramInt3, paramInt4, paramInt6, 200.0D, 200.0D, 0.5D);
/*		 */ 
/* 186 */		 d1 *= 2.0D;
/*		 */ 
/* 188 */		 this.c = this.l.a(this.c, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1 / 80.0D, d2 / 160.0D, d1 / 80.0D);
/* 189 */		 this.d = this.j.a(this.d, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
/* 190 */		 this.e = this.k.a(this.e, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
/*		 */ 
/* 192 */		 int i1 = 0;
/* 193 */		 int i2 = 0;
/*		 */ 
/* 195 */		 for (int i3 = 0; i3 < paramInt4; i3++) {
/* 196 */			 for (int i4 = 0; i4 < paramInt6; i4++) {
/* 197 */				 double d3 = (this.f[i2] + 256.0D) / 512.0D;
/* 198 */				 if (d3 > 1.0D) d3 = 1.0D;
/*		 */ 
/* 200 */				 double d4 = this.g[i2] / 8000.0D;
/* 201 */				 if (d4 < 0.0D) d4 = -d4 * 0.3D;
/* 202 */				 d4 = d4 * 3.0D - 2.0D;
/*		 */ 
/* 204 */				 float f1 = (i3 + paramInt1 - 0) / 1.0F;
/* 205 */				 float f2 = (i4 + paramInt3 - 0) / 1.0F;
/* 206 */				 float f3 = 100.0F - MathHelper.c(f1 * f1 + f2 * f2) * 8.0F;
/* 207 */				 if (f3 > 80.0F) f3 = 80.0F;
/* 208 */				 if (f3 < -100.0F) f3 = -100.0F;
/* 209 */				 if (d4 > 1.0D) d4 = 1.0D;
/* 210 */				 d4 /= 8.0D;
/* 211 */				 d4 = 0.0D;
/*		 */ 
/* 213 */				 if (d3 < 0.0D) d3 = 0.0D;
/* 214 */				 d3 += 0.5D;
/* 215 */				 d4 = d4 * paramInt5 / 16.0D;
/*		 */ 
/* 217 */				 i2++;
/*		 */ 
/* 219 */				 double d5 = paramInt5 / 2.0D;
/*		 */ 
/* 221 */				 for (int i5 = 0; i5 < paramInt5; i5++) {
/* 222 */					 double d6 = 0.0D;
/*		 */ 
/* 224 */					 double d7 = (i5 - d5) * 8.0D / d3;
/*		 */ 
/* 226 */					 if (d7 < 0.0D) d7 *= -1.0D;
/*		 */ 
/* 228 */					 double d8 = this.d[i1] / 512.0D;
/* 229 */					 double d9 = this.e[i1] / 512.0D;
/*		 */ 
/* 231 */					 double d10 = (this.c[i1] / 10.0D + 1.0D) / 2.0D;
/* 232 */					 if (d10 < 0.0D) d6 = d8;
/* 233 */					 else if (d10 > 1.0D) d6 = d9; else
/* 234 */						 d6 = d8 + (d9 - d8) * d10;
/* 235 */					 d6 -= 8.0D;
/*		 */ 
/* 237 */					 d6 += f3;
/*		 */ 
/* 239 */					 int i6 = 2;
/*		 */					 double d11;
/* 240 */					 if (i5 > paramInt5 / 2 - i6) {
/* 241 */						 d11 = (i5 - (paramInt5 / 2 - i6)) / 64.0F;
/* 242 */						 if (d11 < 0.0D) d11 = 0.0D;
/* 243 */						 if (d11 > 1.0D) d11 = 1.0D;
/* 244 */						 d6 = d6 * (1.0D - d11) + -3000.0D * d11;
/*		 */					 }
/* 246 */					 i6 = 8;
/* 247 */					 if (i5 < i6) {
/* 248 */						 d11 = (i6 - i5) / (i6 - 1.0F);
/* 249 */						 d6 = d6 * (1.0D - d11) + -30.0D * d11;
/*		 */					 }
/*		 */ 
/* 252 */					 paramArrayOfDouble[i1] = d6;
/* 253 */					 i1++;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 257 */		 return paramArrayOfDouble;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isChunkLoaded(int paramInt1, int paramInt2) {
/* 261 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void getChunkAt(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2)
/*		 */	 {
/* 317 */		 BlockSand.instaFall = true;
/* 318 */		 int i1 = paramInt1 * 16;
/* 319 */		 int i2 = paramInt2 * 16;
/*		 */ 
/* 321 */		 BiomeBase localBiomeBase = this.m.getBiome(i1 + 16, i2 + 16);
/* 322 */		 localBiomeBase.a(this.m, this.m.random, i1, i2);
/*		 */ 
/* 324 */		 BlockSand.instaFall = false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate) {
/* 328 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean unloadChunks() {
/* 332 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSave() {
/* 336 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 340 */		 return "RandomLevelSource";
/*		 */	 }
/*		 */ 
/*		 */	 public List getMobsFor(EnumCreatureType paramEnumCreatureType, int paramInt1, int paramInt2, int paramInt3) {
/* 344 */		 BiomeBase localBiomeBase = this.m.getBiome(paramInt1, paramInt3);
/* 345 */		 if (localBiomeBase == null) {
/* 346 */			 return null;
/*		 */		 }
/* 348 */		 return localBiomeBase.getMobs(paramEnumCreatureType);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition findNearestMapFeature(World paramWorld, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/* 352 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int getLoadedChunks() {
/* 356 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkProviderTheEnd
 * JD-Core Version:		0.6.0
 */