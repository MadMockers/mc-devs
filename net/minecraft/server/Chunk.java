/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Arrays;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ import java.util.logging.Logger;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.craftbukkit.CraftChunk;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ 
/*		 */ public class Chunk
/*		 */ {
/*		 */	 public static boolean a;
/*		 */	 private ChunkSection[] sections;
/*		 */	 private byte[] r;
/*		 */	 public int[] b;
/*		 */	 public boolean[] c;
/*		 */	 public boolean d;
/*		 */	 public World world;
/*		 */	 public int[] heightMap;
/*		 */	 public final int x;
/*		 */	 public final int z;
/*		 */	 private boolean s;
/*		 */	 public Map tileEntities;
/*		 */	 public List[] entitySlices;
/*		 */	 public boolean done;
/*		 */	 public boolean l;
/*		 */	 public boolean m;
/*		 */	 public long n;
/*		 */	 public boolean seenByPlayer;
/*		 */	 private int t;
/*		 */	 boolean p;
/*		 */	 public org.bukkit.Chunk bukkitChunk;
/*		 */ 
/*		 */	 public Chunk(World world, int i, int j)
/*		 */	 {
/*	40 */		 this.sections = new ChunkSection[16];
/*	41 */		 this.r = new byte[256];
/*	42 */		 this.b = new int[256];
/*	43 */		 this.c = new boolean[256];
/*	44 */		 this.s = false;
/*	45 */		 this.tileEntities = new HashMap();
/*	46 */		 this.done = false;
/*	47 */		 this.l = false;
/*	48 */		 this.m = false;
/*	49 */		 this.n = 0L;
/*	50 */		 this.seenByPlayer = false;
/*	51 */		 this.t = 4096;
/*	52 */		 this.p = false;
/*	53 */		 this.entitySlices = new List[16];
/*	54 */		 this.world = world;
/*	55 */		 this.x = i;
/*	56 */		 this.z = j;
/*	57 */		 this.heightMap = new int[256];
/*		 */ 
/*	59 */		 for (int k = 0; k < this.entitySlices.length; k++) {
/*	60 */			 this.entitySlices[k] = new ArrayList();
/*		 */		 }
/*		 */ 
/*	63 */		 Arrays.fill(this.b, -999);
/*	64 */		 Arrays.fill(this.r, -1);
/*		 */ 
/*	67 */		 if (!(this instanceof EmptyChunk))
/*	68 */			 this.bukkitChunk = new CraftChunk(this);
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk(World world, byte[] abyte, int i, int j)
/*		 */	 {
/*	76 */		 this(world, i, j);
/*	77 */		 int k = abyte.length / 256;
/*		 */ 
/*	79 */		 for (int l = 0; l < 16; l++)
/*	80 */			 for (int i1 = 0; i1 < 16; i1++)
/*	81 */				 for (int j1 = 0; j1 < k; j1++) {
/*	82 */					 byte b0 = abyte[(l << 11 | i1 << 7 | j1)];
/*		 */ 
/*	84 */					 if (b0 != 0) {
/*	85 */						 int k1 = j1 >> 4;
/*		 */ 
/*	87 */						 if (this.sections[k1] == null) {
/*	88 */							 this.sections[k1] = new ChunkSection(k1 << 4);
/*		 */						 }
/*		 */ 
/*	91 */						 this.sections[k1].a(l, j1 & 0xF, i1, b0);
/*		 */					 }
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int i, int j)
/*		 */	 {
/*	99 */		 return (i == this.x) && (j == this.z);
/*		 */	 }
/*		 */ 
/*		 */	 public int b(int i, int j) {
/* 103 */		 return this.heightMap[(j << 4 | i)];
/*		 */	 }
/*		 */ 
/*		 */	 public int h() {
/* 107 */		 for (int i = this.sections.length - 1; i >= 0; i--) {
/* 108 */			 if (this.sections[i] != null) {
/* 109 */				 return this.sections[i].d();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 113 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkSection[] i() {
/* 117 */		 return this.sections;
/*		 */	 }
/*		 */ 
/*		 */	 public void initLighting() {
/* 121 */		 int i = h();
/*		 */ 
/* 126 */		 for (int j = 0; j < 16; j++) {
/* 127 */			 int k = 0;
/*		 */ 
/* 129 */			 while (k < 16) {
/* 130 */				 this.b[(j + (k << 4))] = -999;
/* 131 */				 int l = i + 16 - 1;
/*		 */ 
/* 134 */				 while (l > 0) {
/* 135 */					 if (b(j, l - 1, k) == 0) {
/* 136 */						 l--;
/* 137 */						 continue;
/*		 */					 }
/*		 */ 
/* 140 */					 this.heightMap[(k << 4 | j)] = l;
/*		 */				 }
/*		 */ 
/* 143 */				 if (!this.world.worldProvider.e) {
/* 144 */					 l = 15;
/* 145 */					 int i1 = i + 16 - 1;
/*		 */					 do
/*		 */					 {
/* 148 */						 l -= b(j, i1, k);
/* 149 */						 if (l > 0) {
/* 150 */							 ChunkSection chunksection = this.sections[(i1 >> 4)];
/*		 */ 
/* 152 */							 if (chunksection != null) {
/* 153 */								 chunksection.c(j, i1 & 0xF, k, l);
/* 154 */								 this.world.n((this.x << 4) + j, i1, (this.z << 4) + k);
/*		 */							 }
/*		 */						 }
/*		 */ 
/* 158 */						 i1--;
/* 159 */					 }while ((i1 > 0) && (l > 0));
/*		 */				 }
/*		 */ 
/* 162 */				 k++;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 168 */		 this.l = true;
/*		 */ 
/* 170 */		 for (j = 0; j < 16; j++)
/* 171 */			 for (int k = 0; k < 16; k++)
/* 172 */				 e(j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void e(int i, int j)
/*		 */	 {
/* 178 */		 this.c[(i + j * 16)] = true;
/* 179 */		 this.s = true;
/*		 */	 }
/*		 */ 
/*		 */	 private void q() {
/* 183 */		 this.world.methodProfiler.a("recheckGaps");
/* 184 */		 if (this.world.areChunksLoaded(this.x * 16 + 8, 0, this.z * 16 + 8, 16)) {
/* 185 */			 for (int i = 0; i < 16; i++) {
/* 186 */				 for (int j = 0; j < 16; j++) {
/* 187 */					 if (this.c[(i + j * 16)] != 0) {
/* 188 */						 this.c[(i + j * 16)] = false;
/* 189 */						 int k = b(i, j);
/* 190 */						 int l = this.x * 16 + i;
/* 191 */						 int i1 = this.z * 16 + j;
/* 192 */						 int j1 = this.world.getHighestBlockYAt(l - 1, i1);
/* 193 */						 int k1 = this.world.getHighestBlockYAt(l + 1, i1);
/* 194 */						 int l1 = this.world.getHighestBlockYAt(l, i1 - 1);
/* 195 */						 int i2 = this.world.getHighestBlockYAt(l, i1 + 1);
/*		 */ 
/* 197 */						 if (k1 < j1) {
/* 198 */							 j1 = k1;
/*		 */						 }
/*		 */ 
/* 201 */						 if (l1 < j1) {
/* 202 */							 j1 = l1;
/*		 */						 }
/*		 */ 
/* 205 */						 if (i2 < j1) {
/* 206 */							 j1 = i2;
/*		 */						 }
/*		 */ 
/* 209 */						 g(l, i1, j1);
/* 210 */						 g(l - 1, i1, k);
/* 211 */						 g(l + 1, i1, k);
/* 212 */						 g(l, i1 - 1, k);
/* 213 */						 g(l, i1 + 1, k);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 218 */			 this.s = false;
/*		 */		 }
/*		 */ 
/* 221 */		 this.world.methodProfiler.b();
/*		 */	 }
/*		 */ 
/*		 */	 private void g(int i, int j, int k) {
/* 225 */		 int l = this.world.getHighestBlockYAt(i, j);
/*		 */ 
/* 227 */		 if (l > k)
/* 228 */			 d(i, j, k, l + 1);
/* 229 */		 else if (l < k)
/* 230 */			 d(i, j, l, k + 1);
/*		 */	 }
/*		 */ 
/*		 */	 private void d(int i, int j, int k, int l)
/*		 */	 {
/* 235 */		 if ((l > k) && (this.world.areChunksLoaded(i, 0, j, 16))) {
/* 236 */			 for (int i1 = k; i1 < l; i1++) {
/* 237 */				 this.world.c(EnumSkyBlock.SKY, i, i1, j);
/*		 */			 }
/*		 */ 
/* 240 */			 this.l = true;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void h(int i, int j, int k) {
/* 245 */		 int l = this.heightMap[(k << 4 | i)] & 0xFF;
/* 246 */		 int i1 = l;
/*		 */ 
/* 248 */		 if (j > l) {
/* 249 */			 i1 = j;
/*		 */		 }
/*		 */ 
/* 252 */		 while ((i1 > 0) && (b(i, i1 - 1, k) == 0)) {
/* 253 */			 i1--;
/*		 */		 }
/*		 */ 
/* 256 */		 if (i1 != l) {
/* 257 */			 this.world.g(i, k, i1, l);
/* 258 */			 this.heightMap[(k << 4 | i)] = i1;
/* 259 */			 int j1 = this.x * 16 + i;
/* 260 */			 int k1 = this.z * 16 + k;
/*		 */ 
/* 264 */			 if (!this.world.worldProvider.e)
/*		 */			 {
/* 267 */				 if (i1 < l) {
/* 268 */					 for (int l1 = i1; l1 < l; l1++) {
/* 269 */						 ChunkSection chunksection = this.sections[(l1 >> 4)];
/* 270 */						 if (chunksection != null) {
/* 271 */							 chunksection.c(i, l1 & 0xF, k, 15);
/* 272 */							 this.world.n((this.x << 4) + i, l1, (this.z << 4) + k);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/* 276 */				 for (int l1 = l; l1 < i1; l1++) {
/* 277 */					 ChunkSection chunksection = this.sections[(l1 >> 4)];
/* 278 */					 if (chunksection != null) {
/* 279 */						 chunksection.c(i, l1 & 0xF, k, 0);
/* 280 */						 this.world.n((this.x << 4) + i, l1, (this.z << 4) + k);
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/* 285 */				 l1 = 15;
/*		 */ 
/* 287 */				 while ((i1 > 0) && (l1 > 0)) {
/* 288 */					 i1--;
/* 289 */					 int i2 = b(i, i1, k);
/* 290 */					 if (i2 == 0) {
/* 291 */						 i2 = 1;
/*		 */					 }
/*		 */ 
/* 294 */					 l1 -= i2;
/* 295 */					 if (l1 < 0) {
/* 296 */						 l1 = 0;
/*		 */					 }
/*		 */ 
/* 299 */					 ChunkSection chunksection1 = this.sections[(i1 >> 4)];
/*		 */ 
/* 301 */					 if (chunksection1 != null) {
/* 302 */						 chunksection1.c(i, i1 & 0xF, k, l1);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 307 */			 int l1 = this.heightMap[(k << 4 | i)];
/* 308 */			 int i2 = l;
/* 309 */			 int j2 = l1;
/*		 */ 
/* 311 */			 if (l1 < l) {
/* 312 */				 i2 = l1;
/* 313 */				 j2 = l;
/*		 */			 }
/*		 */ 
/* 316 */			 if (!this.world.worldProvider.e) {
/* 317 */				 d(j1 - 1, k1, i2, j2);
/* 318 */				 d(j1 + 1, k1, i2, j2);
/* 319 */				 d(j1, k1 - 1, i2, j2);
/* 320 */				 d(j1, k1 + 1, i2, j2);
/* 321 */				 d(j1, k1, i2, j2);
/*		 */			 }
/*		 */ 
/* 324 */			 this.l = true;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int b(int i, int j, int k) {
/* 329 */		 return Block.lightBlock[getTypeId(i, j, k)];
/*		 */	 }
/*		 */ 
/*		 */	 public int getTypeId(int i, int j, int k) {
/* 333 */		 if (j >> 4 >= this.sections.length) {
/* 334 */			 return 0;
/*		 */		 }
/* 336 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/*		 */ 
/* 338 */		 return chunksection != null ? chunksection.a(i, j & 0xF, k) : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int getData(int i, int j, int k)
/*		 */	 {
/* 343 */		 if (j >> 4 >= this.sections.length) {
/* 344 */			 return 0;
/*		 */		 }
/* 346 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/*		 */ 
/* 348 */		 return chunksection != null ? chunksection.b(i, j & 0xF, k) : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int i, int j, int k, int l)
/*		 */	 {
/* 353 */		 return a(i, j, k, l, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int i, int j, int k, int l, int i1) {
/* 357 */		 int j1 = k << 4 | i;
/*		 */ 
/* 359 */		 if (j >= this.b[j1] - 1) {
/* 360 */			 this.b[j1] = -999;
/*		 */		 }
/*		 */ 
/* 363 */		 int k1 = this.heightMap[j1];
/* 364 */		 int l1 = getTypeId(i, j, k);
/* 365 */		 int i2 = getData(i, j, k);
/*		 */ 
/* 367 */		 if ((l1 == l) && (i2 == i1)) {
/* 368 */			 return false;
/*		 */		 }
/* 370 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/* 371 */		 boolean flag = false;
/*		 */ 
/* 373 */		 if (chunksection == null) {
/* 374 */			 if (l == 0) {
/* 375 */				 return false;
/*		 */			 }
/*		 */ 
/* 378 */			 chunksection = this.sections[(j >> 4)] =	= new ChunkSection(j >> 4 << 4);
/* 379 */			 flag = j >= k1;
/*		 */		 }
/*		 */ 
/* 382 */		 int j2 = this.x * 16 + i;
/* 383 */		 int k2 = this.z * 16 + k;
/*		 */ 
/* 385 */		 if ((l1 != 0) && (!this.world.isStatic)) {
/* 386 */			 Block.byId[l1].h(this.world, j2, j, k2, i2);
/*		 */		 }
/*		 */ 
/* 389 */		 chunksection.a(i, j & 0xF, k, l);
/* 390 */		 if (l1 != 0) {
/* 391 */			 if (!this.world.isStatic)
/* 392 */				 Block.byId[l1].remove(this.world, j2, j, k2, l1, i2);
/* 393 */			 else if (((Block.byId[l1] instanceof BlockContainer)) && (l1 != l)) {
/* 394 */				 this.world.q(j2, j, k2);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 398 */		 if (chunksection.a(i, j & 0xF, k) != l) {
/* 399 */			 return false;
/*		 */		 }
/* 401 */		 chunksection.b(i, j & 0xF, k, i1);
/* 402 */		 if (flag) {
/* 403 */			 initLighting();
/*		 */		 } else {
/* 405 */			 if (Block.lightBlock[(l & 0xFFF)] > 0) {
/* 406 */				 if (j >= k1)
/* 407 */					 h(i, j + 1, k);
/*		 */			 }
/* 409 */			 else if (j == k1 - 1) {
/* 410 */				 h(i, j, k);
/*		 */			 }
/*		 */ 
/* 413 */			 e(i, k);
/*		 */		 }
/*		 */ 
/* 418 */		 if (l != 0) {
/* 419 */			 if (!this.world.isStatic) {
/* 420 */				 Block.byId[l].onPlace(this.world, j2, j, k2);
/*		 */			 }
/*		 */ 
/* 423 */			 if ((Block.byId[l] instanceof BlockContainer)) {
/* 424 */				 TileEntity tileentity = e(i, j, k);
/* 425 */				 if (tileentity == null) {
/* 426 */					 tileentity = ((BlockContainer)Block.byId[l]).a(this.world);
/* 427 */					 this.world.setTileEntity(j2, j, k2, tileentity);
/*		 */				 }
/*		 */ 
/* 430 */				 if (tileentity != null)
/* 431 */					 tileentity.h();
/*		 */			 }
/*		 */		 }
/* 434 */		 else if ((l1 > 0) && ((Block.byId[l1] instanceof BlockContainer))) {
/* 435 */			 TileEntity tileentity = e(i, j, k);
/* 436 */			 if (tileentity != null) {
/* 437 */				 tileentity.h();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 441 */		 this.l = true;
/* 442 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(int i, int j, int k, int l)
/*		 */	 {
/* 448 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/*		 */ 
/* 450 */		 if (chunksection == null) {
/* 451 */			 return false;
/*		 */		 }
/* 453 */		 int i1 = chunksection.b(i, j & 0xF, k);
/*		 */ 
/* 455 */		 if (i1 == l) {
/* 456 */			 return false;
/*		 */		 }
/* 458 */		 this.l = true;
/* 459 */		 chunksection.b(i, j & 0xF, k, l);
/* 460 */		 int j1 = chunksection.a(i, j & 0xF, k);
/*		 */ 
/* 462 */		 if ((j1 > 0) && ((Block.byId[j1] instanceof BlockContainer))) {
/* 463 */			 TileEntity tileentity = e(i, j, k);
/*		 */ 
/* 465 */			 if (tileentity != null) {
/* 466 */				 tileentity.h();
/* 467 */				 tileentity.p = l;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 471 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int getBrightness(EnumSkyBlock enumskyblock, int i, int j, int k)
/*		 */	 {
/* 477 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/*		 */ 
/* 479 */		 return enumskyblock == EnumSkyBlock.BLOCK ? chunksection.d(i, j & 0xF, k) : enumskyblock == EnumSkyBlock.SKY ? chunksection.c(i, j & 0xF, k) : chunksection == null ? 0 : d(i, j, k) ? enumskyblock.c : enumskyblock.c;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EnumSkyBlock enumskyblock, int i, int j, int k, int l) {
/* 483 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/*		 */ 
/* 485 */		 if (chunksection == null) {
/* 486 */			 chunksection = this.sections[(j >> 4)] =	= new ChunkSection(j >> 4 << 4);
/* 487 */			 initLighting();
/*		 */		 }
/*		 */ 
/* 490 */		 this.l = true;
/* 491 */		 if (enumskyblock == EnumSkyBlock.SKY) {
/* 492 */			 if (!this.world.worldProvider.e)
/* 493 */				 chunksection.c(i, j & 0xF, k, l);
/*		 */		 }
/* 495 */		 else if (enumskyblock == EnumSkyBlock.BLOCK)
/* 496 */			 chunksection.d(i, j & 0xF, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public int c(int i, int j, int k, int l)
/*		 */	 {
/* 501 */		 ChunkSection chunksection = this.sections[(j >> 4)];
/*		 */ 
/* 503 */		 if (chunksection == null) {
/* 504 */			 return (!this.world.worldProvider.e) && (l < EnumSkyBlock.SKY.c) ? EnumSkyBlock.SKY.c - l : 0;
/*		 */		 }
/* 506 */		 int i1 = this.world.worldProvider.e ? 0 : chunksection.c(i, j & 0xF, k);
/*		 */ 
/* 508 */		 if (i1 > 0) {
/* 509 */			 a = true;
/*		 */		 }
/*		 */ 
/* 512 */		 i1 -= l;
/* 513 */		 int j1 = chunksection.d(i, j & 0xF, k);
/*		 */ 
/* 515 */		 if (j1 > i1) {
/* 516 */			 i1 = j1;
/*		 */		 }
/*		 */ 
/* 519 */		 return i1;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity entity)
/*		 */	 {
/* 524 */		 this.m = true;
/* 525 */		 int i = MathHelper.floor(entity.locX / 16.0D);
/* 526 */		 int j = MathHelper.floor(entity.locZ / 16.0D);
/*		 */ 
/* 528 */		 if ((i != this.x) || (j != this.z))
/*		 */		 {
/* 530 */			 Bukkit.getLogger().warning("Wrong location for " + entity + " in world '" + this.world.getWorld().getName() + "'!");
/*		 */ 
/* 532 */			 Bukkit.getLogger().warning("Entity is at " + entity.locX + "," + entity.locZ + " (chunk " + i + "," + j + ") but was stored in chunk " + this.x + "," + this.z);
/*		 */		 }
/*		 */ 
/* 536 */		 int k = MathHelper.floor(entity.locY / 16.0D);
/*		 */ 
/* 538 */		 if (k < 0) {
/* 539 */			 k = 0;
/*		 */		 }
/*		 */ 
/* 542 */		 if (k >= this.entitySlices.length) {
/* 543 */			 k = this.entitySlices.length - 1;
/*		 */		 }
/*		 */ 
/* 546 */		 entity.ag = true;
/* 547 */		 entity.ah = this.x;
/* 548 */		 entity.ai = k;
/* 549 */		 entity.aj = this.z;
/* 550 */		 this.entitySlices[k].add(entity);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(Entity entity) {
/* 554 */		 a(entity, entity.ai);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity entity, int i) {
/* 558 */		 if (i < 0) {
/* 559 */			 i = 0;
/*		 */		 }
/*		 */ 
/* 562 */		 if (i >= this.entitySlices.length) {
/* 563 */			 i = this.entitySlices.length - 1;
/*		 */		 }
/*		 */ 
/* 566 */		 this.entitySlices[i].remove(entity);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(int i, int j, int k) {
/* 570 */		 return j >= this.heightMap[(k << 4 | i)];
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity e(int i, int j, int k) {
/* 574 */		 ChunkPosition chunkposition = new ChunkPosition(i, j, k);
/* 575 */		 TileEntity tileentity = (TileEntity)this.tileEntities.get(chunkposition);
/*		 */ 
/* 577 */		 if (tileentity == null) {
/* 578 */			 int l = getTypeId(i, j, k);
/*		 */ 
/* 580 */			 if ((l <= 0) || (!Block.byId[l].s())) {
/* 581 */				 return null;
/*		 */			 }
/*		 */ 
/* 584 */			 if (tileentity == null) {
/* 585 */				 tileentity = ((BlockContainer)Block.byId[l]).a(this.world);
/* 586 */				 this.world.setTileEntity(this.x * 16 + i, j, this.z * 16 + k, tileentity);
/*		 */			 }
/*		 */ 
/* 589 */			 tileentity = (TileEntity)this.tileEntities.get(chunkposition);
/*		 */		 }
/*		 */ 
/* 592 */		 if ((tileentity != null) && (tileentity.p())) {
/* 593 */			 this.tileEntities.remove(chunkposition);
/* 594 */			 return null;
/*		 */		 }
/* 596 */		 return tileentity;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(TileEntity tileentity)
/*		 */	 {
/* 601 */		 int i = tileentity.x - this.x * 16;
/* 602 */		 int j = tileentity.y;
/* 603 */		 int k = tileentity.z - this.z * 16;
/*		 */ 
/* 605 */		 a(i, j, k, tileentity);
/* 606 */		 if (this.d)
/* 607 */			 this.world.tileEntityList.add(tileentity);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i, int j, int k, TileEntity tileentity)
/*		 */	 {
/* 612 */		 ChunkPosition chunkposition = new ChunkPosition(i, j, k);
/*		 */ 
/* 614 */		 tileentity.a(this.world);
/* 615 */		 tileentity.x = (this.x * 16 + i);
/* 616 */		 tileentity.y = j;
/* 617 */		 tileentity.z = (this.z * 16 + k);
/* 618 */		 if ((getTypeId(i, j, k) != 0) && ((Block.byId[getTypeId(i, j, k)] instanceof BlockContainer))) {
/* 619 */			 tileentity.q();
/* 620 */			 this.tileEntities.put(chunkposition, tileentity);
/*		 */		 }
/*		 */		 else {
/* 623 */			 System.out.println("Attempted to place a tile entity (" + tileentity + ") at " + tileentity.x + "," + tileentity.y + "," + tileentity.z + " (" + org.bukkit.Material.getMaterial(getTypeId(i, j, k)) + ") where there was no entity tile!");
/*		 */ 
/* 625 */			 System.out.println("Chunk coordinates: " + this.x * 16 + "," + this.z * 16);
/* 626 */			 new Exception().printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void f(int i, int j, int k)
/*		 */	 {
/* 632 */		 ChunkPosition chunkposition = new ChunkPosition(i, j, k);
/*		 */ 
/* 634 */		 if (this.d) {
/* 635 */			 TileEntity tileentity = (TileEntity)this.tileEntities.remove(chunkposition);
/*		 */ 
/* 637 */			 if (tileentity != null)
/* 638 */				 tileentity.j();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void addEntities()
/*		 */	 {
/* 644 */		 this.d = true;
/* 645 */		 this.world.a(this.tileEntities.values());
/* 646 */		 List[] alist = this.entitySlices;
/* 647 */		 int i = alist.length;
/*		 */ 
/* 649 */		 for (int j = 0; j < i; j++) {
/* 650 */			 List list = alist[j];
/*		 */ 
/* 652 */			 this.world.a(list);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void removeEntities() {
/* 657 */		 this.d = false;
/* 658 */		 Iterator iterator = this.tileEntities.values().iterator();
/*		 */ 
/* 660 */		 while (iterator.hasNext()) {
/* 661 */			 TileEntity tileentity = (TileEntity)iterator.next();
/*		 */ 
/* 663 */			 this.world.a(tileentity);
/*		 */		 }
/*		 */ 
/* 666 */		 List[] alist = this.entitySlices;
/* 667 */		 int i = alist.length;
/*		 */ 
/* 669 */		 for (int j = 0; j < i; j++)
/*		 */		 {
/* 671 */			 Iterator iter = this.entitySlices[j].iterator();
/* 672 */			 while (iter.hasNext()) {
/* 673 */				 Entity entity = (Entity)iter.next();
/* 674 */				 int cx = Location.locToBlock(entity.locX) >> 4;
/* 675 */				 int cz = Location.locToBlock(entity.locZ) >> 4;
/*		 */ 
/* 679 */				 if (((entity instanceof EntityPlayer)) && ((cx != this.x) || (cz != this.z))) {
/* 680 */					 iter.remove();
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 685 */			 List list = alist[j];
/*		 */ 
/* 687 */			 this.world.b(list);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void e() {
/* 692 */		 this.l = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Entity entity, AxisAlignedBB axisalignedbb, List list) {
/* 696 */		 int i = MathHelper.floor((axisalignedbb.b - 2.0D) / 16.0D);
/* 697 */		 int j = MathHelper.floor((axisalignedbb.e + 2.0D) / 16.0D);
/*		 */ 
/* 699 */		 if (i < 0) {
/* 700 */			 i = 0;
/*		 */		 }
/*		 */ 
/* 703 */		 if (j >= this.entitySlices.length) {
/* 704 */			 j = this.entitySlices.length - 1;
/*		 */		 }
/*		 */ 
/* 707 */		 for (int k = i; k <= j; k++) {
/* 708 */			 List list1 = this.entitySlices[k];
/* 709 */			 Iterator iterator = list1.iterator();
/*		 */ 
/* 711 */			 while (iterator.hasNext()) {
/* 712 */				 Entity entity1 = (Entity)iterator.next();
/*		 */ 
/* 714 */				 if ((entity1 != entity) && (entity1.boundingBox.a(axisalignedbb))) {
/* 715 */					 list.add(entity1);
/* 716 */					 Entity[] aentity = entity1.al();
/*		 */ 
/* 718 */					 if (aentity != null)
/* 719 */						 for (int l = 0; l < aentity.length; l++) {
/* 720 */							 entity1 = aentity[l];
/* 721 */							 if ((entity1 != entity) && (entity1.boundingBox.a(axisalignedbb)))
/* 722 */								 list.add(entity1);
/*		 */						 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Class oclass, AxisAlignedBB axisalignedbb, List list)
/*		 */	 {
/* 732 */		 int i = MathHelper.floor((axisalignedbb.b - 2.0D) / 16.0D);
/* 733 */		 int j = MathHelper.floor((axisalignedbb.e + 2.0D) / 16.0D);
/*		 */ 
/* 735 */		 if (i < 0)
/* 736 */			 i = 0;
/* 737 */		 else if (i >= this.entitySlices.length) {
/* 738 */			 i = this.entitySlices.length - 1;
/*		 */		 }
/*		 */ 
/* 741 */		 if (j >= this.entitySlices.length)
/* 742 */			 j = this.entitySlices.length - 1;
/* 743 */		 else if (j < 0) {
/* 744 */			 j = 0;
/*		 */		 }
/*		 */ 
/* 747 */		 for (int k = i; k <= j; k++) {
/* 748 */			 List list1 = this.entitySlices[k];
/* 749 */			 Iterator iterator = list1.iterator();
/*		 */ 
/* 751 */			 while (iterator.hasNext()) {
/* 752 */				 Entity entity = (Entity)iterator.next();
/*		 */ 
/* 754 */				 if ((oclass.isAssignableFrom(entity.getClass())) && (entity.boundingBox.a(axisalignedbb)))
/* 755 */					 list.add(entity);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(boolean flag)
/*		 */	 {
/* 762 */		 if (flag) {
/* 763 */			 if ((this.m) && (this.world.getTime() != this.n))
/* 764 */				 return true;
/*		 */		 }
/* 766 */		 else if ((this.m) && (this.world.getTime() >= this.n + 600L)) {
/* 767 */			 return true;
/*		 */		 }
/*		 */ 
/* 770 */		 return this.l;
/*		 */	 }
/*		 */ 
/*		 */	 public Random a(long i) {
/* 774 */		 return new Random(this.world.getSeed() + this.x * this.x * 4987142 + this.x * 5947611 + this.z * this.z * 4392871L + this.z * 389711 ^ i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isEmpty() {
/* 778 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(IChunkProvider ichunkprovider, IChunkProvider ichunkprovider1, int i, int j) {
/* 782 */		 if ((!this.done) && (ichunkprovider.isChunkLoaded(i + 1, j + 1)) && (ichunkprovider.isChunkLoaded(i, j + 1)) && (ichunkprovider.isChunkLoaded(i + 1, j))) {
/* 783 */			 ichunkprovider.getChunkAt(ichunkprovider1, i, j);
/*		 */		 }
/*		 */ 
/* 786 */		 if ((ichunkprovider.isChunkLoaded(i - 1, j)) && (!ichunkprovider.getOrCreateChunk(i - 1, j).done) && (ichunkprovider.isChunkLoaded(i - 1, j + 1)) && (ichunkprovider.isChunkLoaded(i, j + 1)) && (ichunkprovider.isChunkLoaded(i - 1, j + 1))) {
/* 787 */			 ichunkprovider.getChunkAt(ichunkprovider1, i - 1, j);
/*		 */		 }
/*		 */ 
/* 790 */		 if ((ichunkprovider.isChunkLoaded(i, j - 1)) && (!ichunkprovider.getOrCreateChunk(i, j - 1).done) && (ichunkprovider.isChunkLoaded(i + 1, j - 1)) && (ichunkprovider.isChunkLoaded(i + 1, j - 1)) && (ichunkprovider.isChunkLoaded(i + 1, j))) {
/* 791 */			 ichunkprovider.getChunkAt(ichunkprovider1, i, j - 1);
/*		 */		 }
/*		 */ 
/* 794 */		 if ((ichunkprovider.isChunkLoaded(i - 1, j - 1)) && (!ichunkprovider.getOrCreateChunk(i - 1, j - 1).done) && (ichunkprovider.isChunkLoaded(i, j - 1)) && (ichunkprovider.isChunkLoaded(i - 1, j)))
/* 795 */			 ichunkprovider.getChunkAt(ichunkprovider1, i - 1, j - 1);
/*		 */	 }
/*		 */ 
/*		 */	 public int d(int i, int j)
/*		 */	 {
/* 800 */		 int k = i | j << 4;
/* 801 */		 int l = this.b[k];
/*		 */ 
/* 803 */		 if (l == -999) {
/* 804 */			 int i1 = h() + 15;
/*		 */ 
/* 806 */			 l = -1;
/*		 */ 
/* 808 */			 while ((i1 > 0) && (l == -1)) {
/* 809 */				 int j1 = getTypeId(i, i1, j);
/* 810 */				 Material material = j1 == 0 ? Material.AIR : Block.byId[j1].material;
/*		 */ 
/* 812 */				 if ((!material.isSolid()) && (!material.isLiquid()))
/* 813 */					 i1--;
/*		 */				 else {
/* 815 */					 l = i1 + 1;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 819 */			 this.b[k] = l;
/*		 */		 }
/*		 */ 
/* 822 */		 return l;
/*		 */	 }
/*		 */ 
/*		 */	 public void k() {
/* 826 */		 if ((this.s) && (!this.world.worldProvider.e))
/* 827 */			 q();
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkCoordIntPair l()
/*		 */	 {
/* 832 */		 return new ChunkCoordIntPair(this.x, this.z);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(int i, int j) {
/* 836 */		 if (i < 0) {
/* 837 */			 i = 0;
/*		 */		 }
/*		 */ 
/* 840 */		 if (j >= 256) {
/* 841 */			 j = 255;
/*		 */		 }
/*		 */ 
/* 844 */		 for (int k = i; k <= j; k += 16) {
/* 845 */			 ChunkSection chunksection = this.sections[(k >> 4)];
/*		 */ 
/* 847 */			 if ((chunksection != null) && (!chunksection.a())) {
/* 848 */				 return false;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 852 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(ChunkSection[] achunksection) {
/* 856 */		 this.sections = achunksection;
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeBase a(int i, int j, WorldChunkManager worldchunkmanager) {
/* 860 */		 int k = this.r[(j << 4 | i)] & 0xFF;
/*		 */ 
/* 862 */		 if (k == 255) {
/* 863 */			 BiomeBase biomebase = worldchunkmanager.getBiome((this.x << 4) + i, (this.z << 4) + j);
/*		 */ 
/* 865 */			 k = biomebase.id;
/* 866 */			 this.r[(j << 4 | i)] = (byte)(k & 0xFF);
/*		 */		 }
/*		 */ 
/* 869 */		 return BiomeBase.biomes[k] == null ? BiomeBase.PLAINS : BiomeBase.biomes[k];
/*		 */	 }
/*		 */ 
/*		 */	 public byte[] m() {
/* 873 */		 return this.r;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(byte[] abyte) {
/* 877 */		 this.r = abyte;
/*		 */	 }
/*		 */ 
/*		 */	 public void n() {
/* 881 */		 this.t = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void o() {
/* 885 */		 for (int i = 0; i < 8; i++) {
/* 886 */			 if (this.t >= 4096) {
/* 887 */				 return;
/*		 */			 }
/*		 */ 
/* 890 */			 int j = this.t % 16;
/* 891 */			 int k = this.t / 16 % 16;
/* 892 */			 int l = this.t / 256;
/*		 */ 
/* 894 */			 this.t += 1;
/* 895 */			 int i1 = (this.x << 4) + k;
/* 896 */			 int j1 = (this.z << 4) + l;
/*		 */ 
/* 898 */			 for (int k1 = 0; k1 < 16; k1++) {
/* 899 */				 int l1 = (j << 4) + k1;
/*		 */ 
/* 901 */				 if (((this.sections[j] == null) && ((k1 == 0) || (k1 == 15) || (k == 0) || (k == 15) || (l == 0) || (l == 15))) || ((this.sections[j] != null) && (this.sections[j].a(k, k1, l) == 0))) {
/* 902 */					 if (Block.lightEmission[this.world.getTypeId(i1, l1 - 1, j1)] > 0) {
/* 903 */						 this.world.x(i1, l1 - 1, j1);
/*		 */					 }
/*		 */ 
/* 906 */					 if (Block.lightEmission[this.world.getTypeId(i1, l1 + 1, j1)] > 0) {
/* 907 */						 this.world.x(i1, l1 + 1, j1);
/*		 */					 }
/*		 */ 
/* 910 */					 if (Block.lightEmission[this.world.getTypeId(i1 - 1, l1, j1)] > 0) {
/* 911 */						 this.world.x(i1 - 1, l1, j1);
/*		 */					 }
/*		 */ 
/* 914 */					 if (Block.lightEmission[this.world.getTypeId(i1 + 1, l1, j1)] > 0) {
/* 915 */						 this.world.x(i1 + 1, l1, j1);
/*		 */					 }
/*		 */ 
/* 918 */					 if (Block.lightEmission[this.world.getTypeId(i1, l1, j1 - 1)] > 0) {
/* 919 */						 this.world.x(i1, l1, j1 - 1);
/*		 */					 }
/*		 */ 
/* 922 */					 if (Block.lightEmission[this.world.getTypeId(i1, l1, j1 + 1)] > 0) {
/* 923 */						 this.world.x(i1, l1, j1 + 1);
/*		 */					 }
/*		 */ 
/* 926 */					 this.world.x(i1, l1, j1);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Chunk
 * JD-Core Version:		0.6.0
 */