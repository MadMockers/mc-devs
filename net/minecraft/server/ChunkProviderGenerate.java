/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class ChunkProviderGenerate
/*		 */	 implements IChunkProvider
/*		 */ {
/*		 */	 private Random k;
/*		 */	 private NoiseGeneratorOctaves l;
/*		 */	 private NoiseGeneratorOctaves m;
/*		 */	 private NoiseGeneratorOctaves n;
/*		 */	 private NoiseGeneratorOctaves o;
/*		 */	 public NoiseGeneratorOctaves a;
/*		 */	 public NoiseGeneratorOctaves b;
/*		 */	 public NoiseGeneratorOctaves c;
/*		 */	 private World p;
/*		 */	 private final boolean q;
/*		 */	 private double[] r;
/* 130 */	 private double[] s = new double[256];
/*		 */ 
/* 195 */	 private WorldGenBase t = new WorldGenCaves();
/* 196 */	 private WorldGenStronghold u = new WorldGenStronghold();
/* 197 */	 private WorldGenVillage v = new WorldGenVillage(0);
/* 198 */	 private WorldGenMineshaft w = new WorldGenMineshaft();
/* 199 */	 private WorldGenLargeFeature x = new WorldGenLargeFeature();
/* 200 */	 private WorldGenBase y = new WorldGenCanyon();
/*		 */	 private BiomeBase[] z;
/*		 */	 double[] d;
/*		 */	 double[] e;
/*		 */	 double[] f;
/*		 */	 double[] g;
/*		 */	 double[] h;
/*		 */	 float[] i;
/* 358 */	 int[][] j = new int[32][32];
/*		 */ 
/*		 */	 public ChunkProviderGenerate(World paramWorld, long paramLong, boolean paramBoolean)
/*		 */	 {
/*	42 */		 this.p = paramWorld;
/*	43 */		 this.q = paramBoolean;
/*		 */ 
/*	45 */		 this.k = new Random(paramLong);
/*	46 */		 this.l = new NoiseGeneratorOctaves(this.k, 16);
/*	47 */		 this.m = new NoiseGeneratorOctaves(this.k, 16);
/*	48 */		 this.n = new NoiseGeneratorOctaves(this.k, 8);
/*	49 */		 this.o = new NoiseGeneratorOctaves(this.k, 4);
/*		 */ 
/*	51 */		 this.a = new NoiseGeneratorOctaves(this.k, 10);
/*	52 */		 this.b = new NoiseGeneratorOctaves(this.k, 16);
/*		 */ 
/*	59 */		 this.c = new NoiseGeneratorOctaves(this.k, 8);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
/*		 */	 {
/*	65 */		 int i1 = 4;
/*	66 */		 int i2 = 16;
/*	67 */		 int i3 = 63;
/*		 */ 
/*	69 */		 int i4 = i1 + 1;
/*	70 */		 int i5 = 17;
/*	71 */		 int i6 = i1 + 1;
/*		 */ 
/*	73 */		 this.z = this.p.getWorldChunkManager().getBiomes(this.z, paramInt1 * 4 - 2, paramInt2 * 4 - 2, i4 + 5, i6 + 5);
/*	74 */		 this.r = a(this.r, paramInt1 * i1, 0, paramInt2 * i1, i4, i5, i6);
/*		 */ 
/*	76 */		 for (int i7 = 0; i7 < i1; i7++)
/*	77 */			 for (int i8 = 0; i8 < i1; i8++)
/*	78 */				 for (int i9 = 0; i9 < i2; i9++) {
/*	79 */					 double d1 = 0.125D;
/*	80 */					 double d2 = this.r[(((i7 + 0) * i6 + (i8 + 0)) * i5 + (i9 + 0))];
/*	81 */					 double d3 = this.r[(((i7 + 0) * i6 + (i8 + 1)) * i5 + (i9 + 0))];
/*	82 */					 double d4 = this.r[(((i7 + 1) * i6 + (i8 + 0)) * i5 + (i9 + 0))];
/*	83 */					 double d5 = this.r[(((i7 + 1) * i6 + (i8 + 1)) * i5 + (i9 + 0))];
/*		 */ 
/*	85 */					 double d6 = (this.r[(((i7 + 0) * i6 + (i8 + 0)) * i5 + (i9 + 1))] - d2) * d1;
/*	86 */					 double d7 = (this.r[(((i7 + 0) * i6 + (i8 + 1)) * i5 + (i9 + 1))] - d3) * d1;
/*	87 */					 double d8 = (this.r[(((i7 + 1) * i6 + (i8 + 0)) * i5 + (i9 + 1))] - d4) * d1;
/*	88 */					 double d9 = (this.r[(((i7 + 1) * i6 + (i8 + 1)) * i5 + (i9 + 1))] - d5) * d1;
/*		 */ 
/*	90 */					 for (int i10 = 0; i10 < 8; i10++) {
/*	91 */						 double d10 = 0.25D;
/*		 */ 
/*	93 */						 double d11 = d2;
/*	94 */						 double d12 = d3;
/*	95 */						 double d13 = (d4 - d2) * d10;
/*	96 */						 double d14 = (d5 - d3) * d10;
/*		 */ 
/*	98 */						 for (int i11 = 0; i11 < 4; i11++) {
/*	99 */							 int i12 = i11 + i7 * 4 << 11 | 0 + i8 * 4 << 7 | i9 * 8 + i10;
/* 100 */							 int i13 = 128;
/* 101 */							 i12 -= i13;
/* 102 */							 double d15 = 0.25D;
/*		 */ 
/* 104 */							 double d16 = d11;
/* 105 */							 double d17 = (d12 - d11) * d15;
/* 106 */							 d16 -= d17;
/* 107 */							 for (int i14 = 0; i14 < 4; i14++) {
/* 108 */								 if (d16 += d17 > 0.0D)
/*		 */								 {
/*		 */									 int tmp514_513 = (i12 + i13); i12 = tmp514_513; paramArrayOfByte[tmp514_513] = (byte)Block.STONE.id;
/* 110 */								 } else if (i9 * 8 + i10 < i3)
/*		 */								 {
/*		 */									 int tmp547_546 = (i12 + i13); i12 = tmp547_546; paramArrayOfByte[tmp547_546] = (byte)Block.STATIONARY_WATER.id;
/*		 */								 }
/*		 */								 else
/*		 */								 {
/*		 */									 int tmp567_566 = (i12 + i13); i12 = tmp567_566; paramArrayOfByte[tmp567_566] = 0;
/*		 */								 }
/*		 */							 }
/* 116 */							 d11 += d13;
/* 117 */							 tmp547_546 += d14;
/*		 */						 }
/*		 */ 
/* 120 */						 d2 += d6;
/* 121 */						 d3 += d7;
/* 122 */						 d4 += d8;
/* 123 */						 d5 += d9;
/*		 */					 }
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte, BiomeBase[] paramArrayOfBiomeBase)
/*		 */	 {
/* 133 */		 int i1 = 63;
/*		 */ 
/* 135 */		 double d1 = 0.03125D;
/* 136 */		 this.s = this.o.a(this.s, paramInt1 * 16, paramInt2 * 16, 0, 16, 16, 1, d1 * 2.0D, d1 * 2.0D, d1 * 2.0D);
/*		 */ 
/* 138 */		 for (int i2 = 0; i2 < 16; i2++)
/* 139 */			 for (int i3 = 0; i3 < 16; i3++) {
/* 140 */				 BiomeBase localBiomeBase = paramArrayOfBiomeBase[(i3 + i2 * 16)];
/* 141 */				 float f1 = localBiomeBase.j();
/* 142 */				 int i4 = (int)(this.s[(i2 + i3 * 16)] / 3.0D + 3.0D + this.k.nextDouble() * 0.25D);
/*		 */ 
/* 144 */				 int i5 = -1;
/*		 */ 
/* 146 */				 int i6 = localBiomeBase.A;
/* 147 */				 int i7 = localBiomeBase.B;
/*		 */ 
/* 149 */				 for (int i8 = 127; i8 >= 0; i8--) {
/* 150 */					 int i9 = (i3 * 16 + i2) * 128 + i8;
/*		 */ 
/* 152 */					 if (i8 <= 0 + this.k.nextInt(5)) {
/* 153 */						 paramArrayOfByte[i9] = (byte)Block.BEDROCK.id;
/*		 */					 } else {
/* 155 */						 int i10 = paramArrayOfByte[i9];
/*		 */ 
/* 157 */						 if (i10 == 0)
/* 158 */							 i5 = -1;
/* 159 */						 else if (i10 == Block.STONE.id)
/* 160 */							 if (i5 == -1) {
/* 161 */								 if (i4 <= 0) {
/* 162 */									 i6 = 0;
/* 163 */									 i7 = (byte)Block.STONE.id;
/* 164 */								 } else if ((i8 >= i1 - 4) && (i8 <= i1 + 1)) {
/* 165 */									 i6 = localBiomeBase.A;
/* 166 */									 i7 = localBiomeBase.B;
/*		 */								 }
/*		 */ 
/* 169 */								 if ((i8 < i1) && (i6 == 0)) {
/* 170 */									 if (f1 < 0.15F) i6 = (byte)Block.ICE.id; else {
/* 171 */										 i6 = (byte)Block.STATIONARY_WATER.id;
/*		 */									 }
/*		 */								 }
/* 174 */								 i5 = i4;
/* 175 */								 if (i8 >= i1 - 1) paramArrayOfByte[i9] = i6; else
/* 176 */									 paramArrayOfByte[i9] = i7;
/* 177 */							 } else if (i5 > 0) {
/* 178 */								 i5--;
/* 179 */								 paramArrayOfByte[i9] = i7;
/*		 */ 
/* 183 */								 if ((i5 == 0) && (i7 == Block.SAND.id)) {
/* 184 */									 i5 = this.k.nextInt(4);
/* 185 */									 i7 = (byte)Block.SANDSTONE.id;
/*		 */								 }
/*		 */							 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getChunkAt(int paramInt1, int paramInt2)
/*		 */	 {
/* 205 */		 return getOrCreateChunk(paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getOrCreateChunk(int paramInt1, int paramInt2) {
/* 209 */		 this.k.setSeed(paramInt1 * 341873128712L + paramInt2 * 132897987541L);
/*		 */ 
/* 211 */		 byte[] arrayOfByte1 = new byte[32768];
/*		 */ 
/* 213 */		 a(paramInt1, paramInt2, arrayOfByte1);
/* 214 */		 this.z = this.p.getWorldChunkManager().getBiomeBlock(this.z, paramInt1 * 16, paramInt2 * 16, 16, 16);
/* 215 */		 a(paramInt1, paramInt2, arrayOfByte1, this.z);
/*		 */ 
/* 217 */		 this.t.a(this, this.p, paramInt1, paramInt2, arrayOfByte1);
/* 218 */		 this.y.a(this, this.p, paramInt1, paramInt2, arrayOfByte1);
/* 219 */		 if (this.q) {
/* 220 */			 this.w.a(this, this.p, paramInt1, paramInt2, arrayOfByte1);
/* 221 */			 this.v.a(this, this.p, paramInt1, paramInt2, arrayOfByte1);
/* 222 */			 this.u.a(this, this.p, paramInt1, paramInt2, arrayOfByte1);
/* 223 */			 this.x.a(this, this.p, paramInt1, paramInt2, arrayOfByte1);
/*		 */		 }
/*		 */ 
/* 226 */		 Chunk localChunk = new Chunk(this.p, arrayOfByte1, paramInt1, paramInt2);
/* 227 */		 byte[] arrayOfByte2 = localChunk.m();
/*		 */ 
/* 229 */		 for (int i1 = 0; i1 < arrayOfByte2.length; i1++) {
/* 230 */			 arrayOfByte2[i1] = (byte)this.z[i1].id;
/*		 */		 }
/*		 */ 
/* 233 */		 localChunk.initLighting();
/*		 */ 
/* 235 */		 return localChunk;
/*		 */	 }
/*		 */ 
/*		 */	 private double[] a(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*		 */	 {
/* 242 */		 if (paramArrayOfDouble == null) {
/* 243 */			 paramArrayOfDouble = new double[paramInt4 * paramInt5 * paramInt6];
/*		 */		 }
/* 245 */		 if (this.i == null) {
/* 246 */			 this.i = new float[25];
/* 247 */			 for (int i1 = -2; i1 <= 2; i1++) {
/* 248 */				 for (int i2 = -2; i2 <= 2; i2++) {
/* 249 */					 float f1 = 10.0F / MathHelper.c(i1 * i1 + i2 * i2 + 0.2F);
/* 250 */					 this.i[(i1 + 2 + (i2 + 2) * 5)] = f1;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 255 */		 double d1 = 684.41200000000003D;
/* 256 */		 double d2 = 684.41200000000003D;
/*		 */ 
/* 263 */		 this.g = this.a.a(this.g, paramInt1, paramInt3, paramInt4, paramInt6, 1.121D, 1.121D, 0.5D);
/* 264 */		 this.h = this.b.a(this.h, paramInt1, paramInt3, paramInt4, paramInt6, 200.0D, 200.0D, 0.5D);
/*		 */ 
/* 266 */		 this.d = this.n.a(this.d, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1 / 80.0D, d2 / 160.0D, d1 / 80.0D);
/* 267 */		 this.e = this.l.a(this.e, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
/* 268 */		 this.f = this.m.a(this.f, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, d1, d2, d1);
/* 269 */		 paramInt1 = paramInt3 = 0;
/*		 */ 
/* 271 */		 int i3 = 0;
/* 272 */		 int i4 = 0;
/*		 */ 
/* 274 */		 for (int i5 = 0; i5 < paramInt4; i5++) {
/* 275 */			 for (int i6 = 0; i6 < paramInt6; i6++) {
/* 276 */				 float f2 = 0.0F;
/* 277 */				 float f3 = 0.0F;
/* 278 */				 float f4 = 0.0F;
/*		 */ 
/* 280 */				 int i7 = 2;
/*		 */ 
/* 282 */				 BiomeBase localBiomeBase1 = this.z[(i5 + 2 + (i6 + 2) * (paramInt4 + 5))];
/* 283 */				 for (int i8 = -i7; i8 <= i7; i8++) {
/* 284 */					 for (int i9 = -i7; i9 <= i7; i9++) {
/* 285 */						 BiomeBase localBiomeBase2 = this.z[(i5 + i8 + 2 + (i6 + i9 + 2) * (paramInt4 + 5))];
/* 286 */						 float f5 = this.i[(i8 + 2 + (i9 + 2) * 5)] / (localBiomeBase2.D + 2.0F);
/* 287 */						 if (localBiomeBase2.D > localBiomeBase1.D) {
/* 288 */							 f5 /= 2.0F;
/*		 */						 }
/* 290 */						 f2 += localBiomeBase2.E * f5;
/* 291 */						 f3 += localBiomeBase2.D * f5;
/* 292 */						 f4 += f5;
/*		 */					 }
/*		 */				 }
/* 295 */				 f2 /= f4;
/* 296 */				 f3 /= f4;
/*		 */ 
/* 298 */				 f2 = f2 * 0.9F + 0.1F;
/* 299 */				 f3 = (f3 * 4.0F - 1.0F) / 8.0F;
/*		 */ 
/* 301 */				 double d3 = this.h[i4] / 8000.0D;
/* 302 */				 if (d3 < 0.0D) d3 = -d3 * 0.3D;
/* 303 */				 d3 = d3 * 3.0D - 2.0D;
/*		 */ 
/* 305 */				 if (d3 < 0.0D) {
/* 306 */					 d3 /= 2.0D;
/* 307 */					 if (d3 < -1.0D) d3 = -1.0D;
/* 308 */					 d3 /= 1.4D;
/* 309 */					 d3 /= 2.0D;
/*		 */				 } else {
/* 311 */					 if (d3 > 1.0D) d3 = 1.0D;
/* 312 */					 d3 /= 8.0D;
/*		 */				 }
/*		 */ 
/* 315 */				 i4++;
/*		 */ 
/* 317 */				 for (int i10 = 0; i10 < paramInt5; i10++) {
/* 318 */					 double d4 = f3;
/* 319 */					 double d5 = f2;
/*		 */ 
/* 321 */					 d4 += d3 * 0.2D;
/* 322 */					 d4 = d4 * paramInt5 / 16.0D;
/*		 */ 
/* 324 */					 double d6 = paramInt5 / 2.0D + d4 * 4.0D;
/*		 */ 
/* 326 */					 double d7 = 0.0D;
/*		 */ 
/* 328 */					 double d8 = (i10 - d6) * 12.0D * 128.0D / 128.0D / d5;
/*		 */ 
/* 330 */					 if (d8 < 0.0D) d8 *= 4.0D;
/*		 */ 
/* 332 */					 double d9 = this.e[i3] / 512.0D;
/* 333 */					 double d10 = this.f[i3] / 512.0D;
/*		 */ 
/* 335 */					 double d11 = (this.d[i3] / 10.0D + 1.0D) / 2.0D;
/* 336 */					 if (d11 < 0.0D) d7 = d9;
/* 337 */					 else if (d11 > 1.0D) d7 = d10; else
/* 338 */						 d7 = d9 + (d10 - d9) * d11;
/* 339 */					 d7 -= d8;
/*		 */ 
/* 341 */					 if (i10 > paramInt5 - 4) {
/* 342 */						 double d12 = (i10 - (paramInt5 - 4)) / 3.0F;
/* 343 */						 d7 = d7 * (1.0D - d12) + -10.0D * d12;
/*		 */					 }
/*		 */ 
/* 346 */					 paramArrayOfDouble[i3] = d7;
/* 347 */					 i3++;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 351 */		 return paramArrayOfDouble;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isChunkLoaded(int paramInt1, int paramInt2) {
/* 355 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void getChunkAt(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2)
/*		 */	 {
/* 406 */		 BlockSand.instaFall = true;
/* 407 */		 int i1 = paramInt1 * 16;
/* 408 */		 int i2 = paramInt2 * 16;
/*		 */ 
/* 410 */		 BiomeBase localBiomeBase = this.p.getBiome(i1 + 16, i2 + 16);
/*		 */ 
/* 416 */		 this.k.setSeed(this.p.getSeed());
/* 417 */		 long l1 = this.k.nextLong() / 2L * 2L + 1L;
/* 418 */		 long l2 = this.k.nextLong() / 2L * 2L + 1L;
/* 419 */		 this.k.setSeed(paramInt1 * l1 + paramInt2 * l2 ^ this.p.getSeed());
/*		 */ 
/* 421 */		 boolean bool = false;
/*		 */ 
/* 423 */		 if (this.q) {
/* 424 */			 this.w.a(this.p, this.k, paramInt1, paramInt2);
/* 425 */			 bool = this.v.a(this.p, this.k, paramInt1, paramInt2);
/* 426 */			 this.u.a(this.p, this.k, paramInt1, paramInt2);
/* 427 */			 this.x.a(this.p, this.k, paramInt1, paramInt2);
/*		 */		 }
/*		 */		 int i4;
/*		 */		 int i5;
/* 430 */		 if ((!bool) && (this.k.nextInt(4) == 0)) {
/* 431 */			 i3 = i1 + this.k.nextInt(16) + 8;
/* 432 */			 i4 = this.k.nextInt(128);
/* 433 */			 i5 = i2 + this.k.nextInt(16) + 8;
/* 434 */			 new WorldGenLakes(Block.STATIONARY_WATER.id).a(this.p, this.k, i3, i4, i5);
/*		 */		 }
/*		 */ 
/* 437 */		 if ((!bool) && (this.k.nextInt(8) == 0)) {
/* 438 */			 i3 = i1 + this.k.nextInt(16) + 8;
/* 439 */			 i4 = this.k.nextInt(this.k.nextInt(120) + 8);
/* 440 */			 i5 = i2 + this.k.nextInt(16) + 8;
/* 441 */			 if ((i4 < 63) || (this.k.nextInt(10) == 0)) new WorldGenLakes(Block.STATIONARY_LAVA.id).a(this.p, this.k, i3, i4, i5);
/*		 */		 }
/*		 */ 
/* 444 */		 for (int i3 = 0; i3 < 8; i3++) {
/* 445 */			 i4 = i1 + this.k.nextInt(16) + 8;
/* 446 */			 i5 = this.k.nextInt(128);
/* 447 */			 int i6 = i2 + this.k.nextInt(16) + 8;
/* 448 */			 if (!new WorldGenDungeons().a(this.p, this.k, i4, i5, i6)) {
/*		 */				 continue;
/*		 */			 }
/*		 */		 }
/* 452 */		 localBiomeBase.a(this.p, this.k, i1, i2);
/*		 */ 
/* 454 */		 SpawnerCreature.a(this.p, localBiomeBase, i1 + 8, i2 + 8, 16, 16, this.k);
/*		 */ 
/* 456 */		 i1 += 8;
/* 457 */		 i2 += 8;
/* 458 */		 for (i3 = 0; i3 < 16; i3++) {
/* 459 */			 for (i4 = 0; i4 < 16; i4++) {
/* 460 */				 i5 = this.p.g(i1 + i3, i2 + i4);
/*		 */ 
/* 462 */				 if (this.p.u(i3 + i1, i5 - 1, i4 + i2)) {
/* 463 */					 this.p.setTypeId(i3 + i1, i5 - 1, i4 + i2, Block.ICE.id);
/*		 */				 }
/* 465 */				 if (this.p.w(i3 + i1, i5, i4 + i2)) {
/* 466 */					 this.p.setTypeId(i3 + i1, i5, i4 + i2, Block.SNOW.id);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 471 */		 BlockSand.instaFall = false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate) {
/* 475 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean unloadChunks() {
/* 479 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSave() {
/* 483 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 487 */		 return "RandomLevelSource";
/*		 */	 }
/*		 */ 
/*		 */	 public List getMobsFor(EnumCreatureType paramEnumCreatureType, int paramInt1, int paramInt2, int paramInt3) {
/* 491 */		 BiomeBase localBiomeBase = this.p.getBiome(paramInt1, paramInt3);
/* 492 */		 if (localBiomeBase == null) {
/* 493 */			 return null;
/*		 */		 }
/* 495 */		 return localBiomeBase.getMobs(paramEnumCreatureType);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition findNearestMapFeature(World paramWorld, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/* 499 */		 if (("Stronghold".equals(paramString)) && (this.u != null)) {
/* 500 */			 return this.u.getNearestGeneratedFeature(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */		 }
/* 502 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int getLoadedChunks() {
/* 506 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkProviderGenerate
 * JD-Core Version:		0.6.0
 */