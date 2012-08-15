/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class ChunkProviderHell
/*		 */	 implements IChunkProvider
/*		 */ {
/*		 */	 private Random i;
/*		 */	 private NoiseGeneratorOctaves j;
/*		 */	 private NoiseGeneratorOctaves k;
/*		 */	 private NoiseGeneratorOctaves l;
/*		 */	 private NoiseGeneratorOctaves m;
/*		 */	 private NoiseGeneratorOctaves n;
/*		 */	 public NoiseGeneratorOctaves a;
/*		 */	 public NoiseGeneratorOctaves b;
/*		 */	 private World o;
/*		 */	 private double[] p;
/*	53 */	 public WorldGenNether c = new WorldGenNether();
/*		 */ 
/* 121 */	 private double[] q = new double[256];
/* 122 */	 private double[] r = new double[256];
/* 123 */	 private double[] s = new double[256];
/*		 */ 
/* 184 */	 private WorldGenBase t = new WorldGenCavesHell();
/*		 */	 double[] d;
/*		 */	 double[] e;
/*		 */	 double[] f;
/*		 */	 double[] g;
/*		 */	 double[] h;
/*		 */ 
/*		 */	 public ChunkProviderHell(World paramWorld, long paramLong)
/*		 */	 {
/*	34 */		 this.o = paramWorld;
/*		 */ 
/*	36 */		 this.i = new Random(paramLong);
/*	37 */		 this.j = new NoiseGeneratorOctaves(this.i, 16);
/*	38 */		 this.k = new NoiseGeneratorOctaves(this.i, 16);
/*	39 */		 this.l = new NoiseGeneratorOctaves(this.i, 8);
/*	40 */		 this.m = new NoiseGeneratorOctaves(this.i, 4);
/*	41 */		 this.n = new NoiseGeneratorOctaves(this.i, 4);
/*		 */ 
/*	43 */		 this.a = new NoiseGeneratorOctaves(this.i, 10);
/*	44 */		 this.b = new NoiseGeneratorOctaves(this.i, 16);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
/*		 */	 {
/*	56 */		 int i1 = 4;
/*	57 */		 int i2 = 32;
/*		 */ 
/*	59 */		 int i3 = i1 + 1;
/*	60 */		 int i4 = 17;
/*	61 */		 int i5 = i1 + 1;
/*	62 */		 this.p = a(this.p, paramInt1 * i1, 0, paramInt2 * i1, i3, i4, i5);
/*		 */ 
/*	64 */		 for (int i6 = 0; i6 < i1; i6++)
/*	65 */			 for (int i7 = 0; i7 < i1; i7++)
/*	66 */				 for (int i8 = 0; i8 < 16; i8++) {
/*	67 */					 double d1 = 0.125D;
/*	68 */					 double d2 = this.p[(((i6 + 0) * i5 + (i7 + 0)) * i4 + (i8 + 0))];
/*	69 */					 double d3 = this.p[(((i6 + 0) * i5 + (i7 + 1)) * i4 + (i8 + 0))];
/*	70 */					 double d4 = this.p[(((i6 + 1) * i5 + (i7 + 0)) * i4 + (i8 + 0))];
/*	71 */					 double d5 = this.p[(((i6 + 1) * i5 + (i7 + 1)) * i4 + (i8 + 0))];
/*		 */ 
/*	73 */					 double d6 = (this.p[(((i6 + 0) * i5 + (i7 + 0)) * i4 + (i8 + 1))] - d2) * d1;
/*	74 */					 double d7 = (this.p[(((i6 + 0) * i5 + (i7 + 1)) * i4 + (i8 + 1))] - d3) * d1;
/*	75 */					 double d8 = (this.p[(((i6 + 1) * i5 + (i7 + 0)) * i4 + (i8 + 1))] - d4) * d1;
/*	76 */					 double d9 = (this.p[(((i6 + 1) * i5 + (i7 + 1)) * i4 + (i8 + 1))] - d5) * d1;
/*		 */ 
/*	78 */					 for (int i9 = 0; i9 < 8; i9++) {
/*	79 */						 double d10 = 0.25D;
/*		 */ 
/*	81 */						 double d11 = d2;
/*	82 */						 double d12 = d3;
/*	83 */						 double d13 = (d4 - d2) * d10;
/*	84 */						 double d14 = (d5 - d3) * d10;
/*		 */ 
/*	86 */						 for (int i10 = 0; i10 < 4; i10++) {
/*	87 */							 int i11 = i10 + i6 * 4 << 11 | 0 + i7 * 4 << 7 | i8 * 8 + i9;
/*	88 */							 int i12 = 128;
/*	89 */							 double d15 = 0.25D;
/*		 */ 
/*	91 */							 double d16 = d11;
/*	92 */							 double d17 = (d12 - d11) * d15;
/*	93 */							 for (int i13 = 0; i13 < 4; i13++) {
/*	94 */								 int i14 = 0;
/*	95 */								 if (i8 * 8 + i9 < i2) {
/*	96 */									 i14 = Block.STATIONARY_LAVA.id;
/*		 */								 }
/*	98 */								 if (d16 > 0.0D) {
/*	99 */									 i14 = Block.NETHERRACK.id;
/*		 */								 }
/*		 */ 
/* 103 */								 paramArrayOfByte[i11] = (byte)i14;
/* 104 */								 i11 += i12;
/* 105 */								 d16 += d17;
/*		 */							 }
/* 107 */							 d11 += d13;
/* 108 */							 d12 += d14;
/*		 */						 }
/*		 */ 
/* 111 */						 d2 += d6;
/* 112 */						 d3 += d7;
/* 113 */						 d4 += d8;
/* 114 */						 d5 += d9;
/*		 */					 }
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
/*		 */	 {
/* 126 */		 int i1 = 64;
/*		 */ 
/* 128 */		 double d1 = 0.03125D;
/* 129 */		 this.q = this.m.a(this.q, paramInt1 * 16, paramInt2 * 16, 0, 16, 16, 1, d1, d1, 1.0D);
/* 130 */		 this.r = this.m.a(this.r, paramInt1 * 16, 109, paramInt2 * 16, 16, 1, 16, d1, 1.0D, d1);
/* 131 */		 this.s = this.n.a(this.s, paramInt1 * 16, paramInt2 * 16, 0, 16, 16, 1, d1 * 2.0D, d1 * 2.0D, d1 * 2.0D);
/*		 */ 
/* 133 */		 for (int i2 = 0; i2 < 16; i2++)
/* 134 */			 for (int i3 = 0; i3 < 16; i3++) {
/* 135 */				 int i4 = this.q[(i2 + i3 * 16)] + this.i.nextDouble() * 0.2D > 0.0D ? 1 : 0;
/* 136 */				 int i5 = this.r[(i2 + i3 * 16)] + this.i.nextDouble() * 0.2D > 0.0D ? 1 : 0;
/* 137 */				 int i6 = (int)(this.s[(i2 + i3 * 16)] / 3.0D + 3.0D + this.i.nextDouble() * 0.25D);
/*		 */ 
/* 139 */				 int i7 = -1;
/*		 */ 
/* 141 */				 int i8 = (byte)Block.NETHERRACK.id;
/* 142 */				 int i9 = (byte)Block.NETHERRACK.id;
/*		 */ 
/* 144 */				 for (int i10 = 127; i10 >= 0; i10--) {
/* 145 */					 int i11 = (i3 * 16 + i2) * 128 + i10;
/*		 */ 
/* 147 */					 if ((i10 >= 127 - this.i.nextInt(5)) || (i10 <= 0 + this.i.nextInt(5))) {
/* 148 */						 paramArrayOfByte[i11] = (byte)Block.BEDROCK.id;
/*		 */					 } else {
/* 150 */						 int i12 = paramArrayOfByte[i11];
/*		 */ 
/* 152 */						 if (i12 == 0)
/* 153 */							 i7 = -1;
/* 154 */						 else if (i12 == Block.NETHERRACK.id)
/* 155 */							 if (i7 == -1) {
/* 156 */								 if (i6 <= 0) {
/* 157 */									 i8 = 0;
/* 158 */									 i9 = (byte)Block.NETHERRACK.id;
/* 159 */								 } else if ((i10 >= i1 - 4) && (i10 <= i1 + 1)) {
/* 160 */									 i8 = (byte)Block.NETHERRACK.id;
/* 161 */									 i9 = (byte)Block.NETHERRACK.id;
/* 162 */									 if (i5 != 0) i8 = (byte)Block.GRAVEL.id;
/* 163 */									 if (i5 != 0) i9 = (byte)Block.NETHERRACK.id;
/* 164 */									 if (i4 != 0) i8 = (byte)Block.SOUL_SAND.id;
/* 165 */									 if (i4 != 0) i9 = (byte)Block.SOUL_SAND.id;
/*		 */								 }
/*		 */ 
/* 168 */								 if ((i10 < i1) && (i8 == 0)) i8 = (byte)Block.STATIONARY_LAVA.id;
/*		 */ 
/* 170 */								 i7 = i6;
/* 171 */								 if (i10 >= i1 - 1) paramArrayOfByte[i11] = i8; else
/* 172 */									 paramArrayOfByte[i11] = i9;
/* 173 */							 } else if (i7 > 0) {
/* 174 */								 i7--;
/* 175 */								 paramArrayOfByte[i11] = i9;
/*		 */							 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getChunkAt(int paramInt1, int paramInt2)
/*		 */	 {
/* 187 */		 return getOrCreateChunk(paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/* 191 */		 this.i.setSeed(paramInt1 * 341873128712L + paramInt2 * 132897987541L);
/*		 */ 
/* 193 */		 byte[] arrayOfByte1 = new byte[32768];
/*		 */ 
/* 195 */		 a(paramInt1, paramInt2, arrayOfByte1);
/* 196 */		 b(paramInt1, paramInt2, arrayOfByte1);
/*		 */ 
/* 198 */		 this.t.a(this, this.o, paramInt1, paramInt2, arrayOfByte1);
/* 199 */		 this.c.a(this, this.o, paramInt1, paramInt2, arrayOfByte1);
/*		 */ 
/* 201 */		 Chunk localChunk = new Chunk(this.o, arrayOfByte1, paramInt1, paramInt2);
/* 202 */		 BiomeBase[] arrayOfBiomeBase = this.o.getWorldChunkManager().getBiomeBlock(null, paramInt1 * 16, paramInt2 * 16, 16, 16);
/* 203 */		 byte[] arrayOfByte2 = localChunk.m();
/*		 */ 
/* 205 */		 for (int i1 = 0; i1 < arrayOfByte2.length; i1++) {
/* 206 */			 arrayOfByte2[i1] = (byte)arrayOfBiomeBase[i1].id;
/*		 */		 }
/*		 */ 
/* 209 */		 localChunk.n();
/*		 */ 
/* 211 */		 return localChunk;
/*		 */	 }
/*		 */ 
/*		 */	 private double[] a(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*		 */	 {
/* 217 */		 if (paramArrayOfDouble == null) {
/* 218 */			 paramArrayOfDouble = new double[paramInt4 * paramInt5 * paramInt6];
/*		 */		 }
/*		 */ 
/* 221 */		 double d1 = 684.41200000000003D;
/* 222 */		 double d2 = 2053.2359999999999D;
/*		 */ 
/* 224 */		 this.g = this.a.a(this.g, paramInt1, paramInt2, paramInt3, paramInt4, 1, paramInt6, 1.0D, 0.0D, 1.0D);
/* 225 */		 this.h = this.b.a(this.h, paramInt1, paramInt2, paramInt3, paramInt4, 1, paramInt6, 100.0D, 0.0D, 100.0D);
/*		 */ 
/* 227 */		 this.d = this.l.a(this.d, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1 / 80.0D, d2 / 60.0D, d1 / 80.0D);
/* 228 */		 this.e = this.j.a(this.e, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
/* 229 */		 this.f = this.k.a(this.f, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
/*		 */ 
/* 231 */		 int i1 = 0;
/* 232 */		 int i2 = 0;
/* 233 */		 double[] arrayOfDouble = new double[paramInt5];
/* 234 */		 for (int i3 = 0; i3 < paramInt5; i3++) {
/* 235 */			 arrayOfDouble[i3] = (Math.cos(i3 * 3.141592653589793D * 6.0D / paramInt5) * 2.0D);
/*		 */ 
/* 237 */			 double d3 = i3;
/* 238 */			 if (i3 > paramInt5 / 2) {
/* 239 */				 d3 = paramInt5 - 1 - i3;
/*		 */			 }
/* 241 */			 if (d3 < 4.0D) {
/* 242 */				 d3 = 4.0D - d3;
/* 243 */				 arrayOfDouble[i3] -= d3 * d3 * d3 * 10.0D;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 247 */		 for (i3 = 0; i3 < paramInt4; i3++)
/*		 */		 {
/* 249 */			 for (int i4 = 0; i4 < paramInt6; i4++)
/*		 */			 {
/* 251 */				 double d4 = (this.g[i2] + 256.0D) / 512.0D;
/* 252 */				 if (d4 > 1.0D) d4 = 1.0D;
/*		 */ 
/* 254 */				 double d5 = 0.0D;
/*		 */ 
/* 256 */				 double d6 = this.h[i2] / 8000.0D;
/* 257 */				 if (d6 < 0.0D) d6 = -d6;
/* 258 */				 d6 = d6 * 3.0D - 3.0D;
/*		 */ 
/* 260 */				 if (d6 < 0.0D) {
/* 261 */					 d6 /= 2.0D;
/* 262 */					 if (d6 < -1.0D) d6 = -1.0D;
/* 263 */					 d6 /= 1.4D;
/* 264 */					 d6 /= 2.0D;
/* 265 */					 d4 = 0.0D;
/*		 */				 } else {
/* 267 */					 if (d6 > 1.0D) d6 = 1.0D;
/* 268 */					 d6 /= 6.0D;
/*		 */				 }
/* 270 */				 d4 += 0.5D;
/* 271 */				 d6 = d6 * paramInt5 / 16.0D;
/* 272 */				 i2++;
/*		 */ 
/* 274 */				 for (int i5 = 0; i5 < paramInt5; i5++) {
/* 275 */					 double d7 = 0.0D;
/*		 */ 
/* 277 */					 double d8 = arrayOfDouble[i5];
/*		 */ 
/* 279 */					 double d9 = this.e[i1] / 512.0D;
/* 280 */					 double d10 = this.f[i1] / 512.0D;
/*		 */ 
/* 282 */					 double d11 = (this.d[i1] / 10.0D + 1.0D) / 2.0D;
/* 283 */					 if (d11 < 0.0D) d7 = d9;
/* 284 */					 else if (d11 > 1.0D) d7 = d10; else
/* 285 */						 d7 = d9 + (d10 - d9) * d11;
/* 286 */					 d7 -= d8;
/*		 */					 double d12;
/* 288 */					 if (i5 > paramInt5 - 4) {
/* 289 */						 d12 = (i5 - (paramInt5 - 4)) / 3.0F;
/* 290 */						 d7 = d7 * (1.0D - d12) + -10.0D * d12;
/*		 */					 }
/*		 */ 
/* 293 */					 if (i5 < d5) {
/* 294 */						 d12 = (d5 - i5) / 4.0D;
/* 295 */						 if (d12 < 0.0D) d12 = 0.0D;
/* 296 */						 if (d12 > 1.0D) d12 = 1.0D;
/* 297 */						 d7 = d7 * (1.0D - d12) + -10.0D * d12;
/*		 */					 }
/*		 */ 
/* 300 */					 paramArrayOfDouble[i1] = d7;
/* 301 */					 i1++;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 305 */		 return paramArrayOfDouble;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isChunkLoaded(int paramInt1, int paramInt2) {
/* 309 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void getChunkAt(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2) {
/* 313 */		 BlockSand.instaFall = true;
/* 314 */		 int i1 = paramInt1 * 16;
/* 315 */		 int i2 = paramInt2 * 16;
/*		 */ 
/* 317 */		 this.c.a(this.o, this.i, paramInt1, paramInt2);
/*		 */		 int i5;
/*		 */		 int i6;
/* 319 */		 for (int i3 = 0; i3 < 8; i3++) {
/* 320 */			 i4 = i1 + this.i.nextInt(16) + 8;
/* 321 */			 i5 = this.i.nextInt(120) + 4;
/* 322 */			 i6 = i2 + this.i.nextInt(16) + 8;
/* 323 */			 new WorldGenHellLava(Block.LAVA.id).a(this.o, this.i, i4, i5, i6);
/*		 */		 }
/*		 */ 
/* 326 */		 i3 = this.i.nextInt(this.i.nextInt(10) + 1) + 1;
/*		 */		 int i7;
/* 328 */		 for (int i4 = 0; i4 < i3; i4++) {
/* 329 */			 i5 = i1 + this.i.nextInt(16) + 8;
/* 330 */			 i6 = this.i.nextInt(120) + 4;
/* 331 */			 i7 = i2 + this.i.nextInt(16) + 8;
/* 332 */			 new WorldGenFire().a(this.o, this.i, i5, i6, i7);
/*		 */		 }
/*		 */ 
/* 335 */		 i3 = this.i.nextInt(this.i.nextInt(10) + 1);
/* 336 */		 for (i4 = 0; i4 < i3; i4++) {
/* 337 */			 i5 = i1 + this.i.nextInt(16) + 8;
/* 338 */			 i6 = this.i.nextInt(120) + 4;
/* 339 */			 i7 = i2 + this.i.nextInt(16) + 8;
/* 340 */			 new WorldGenLightStone1().a(this.o, this.i, i5, i6, i7);
/*		 */		 }
/*		 */ 
/* 343 */		 for (i4 = 0; i4 < 10; i4++) {
/* 344 */			 i5 = i1 + this.i.nextInt(16) + 8;
/* 345 */			 i6 = this.i.nextInt(128);
/* 346 */			 i7 = i2 + this.i.nextInt(16) + 8;
/* 347 */			 new WorldGenLightStone2().a(this.o, this.i, i5, i6, i7);
/*		 */		 }
/*		 */ 
/* 350 */		 if (this.i.nextInt(1) == 0) {
/* 351 */			 i4 = i1 + this.i.nextInt(16) + 8;
/* 352 */			 i5 = this.i.nextInt(128);
/* 353 */			 i6 = i2 + this.i.nextInt(16) + 8;
/* 354 */			 new WorldGenFlowers(Block.BROWN_MUSHROOM.id).a(this.o, this.i, i4, i5, i6);
/*		 */		 }
/*		 */ 
/* 357 */		 if (this.i.nextInt(1) == 0) {
/* 358 */			 i4 = i1 + this.i.nextInt(16) + 8;
/* 359 */			 i5 = this.i.nextInt(128);
/* 360 */			 i6 = i2 + this.i.nextInt(16) + 8;
/* 361 */			 new WorldGenFlowers(Block.RED_MUSHROOM.id).a(this.o, this.i, i4, i5, i6);
/*		 */		 }
/*		 */ 
/* 364 */		 BlockSand.instaFall = false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate) {
/* 368 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean unloadChunks() {
/* 372 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSave() {
/* 376 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 380 */		 return "HellRandomLevelSource";
/*		 */	 }
/*		 */ 
/*		 */	 public List getMobsFor(EnumCreatureType paramEnumCreatureType, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 386 */		 if ((paramEnumCreatureType == EnumCreatureType.MONSTER) && (this.c.a(paramInt1, paramInt2, paramInt3))) {
/* 387 */			 return this.c.a();
/*		 */		 }
/*		 */ 
/* 390 */		 BiomeBase localBiomeBase = this.o.getBiome(paramInt1, paramInt3);
/* 391 */		 if (localBiomeBase == null) {
/* 392 */			 return null;
/*		 */		 }
/* 394 */		 return localBiomeBase.getMobs(paramEnumCreatureType);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition findNearestMapFeature(World paramWorld, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/* 398 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int getLoadedChunks() {
/* 402 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkProviderHell
 * JD-Core Version:		0.6.0
 */