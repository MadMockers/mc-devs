/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenMineshaftCorridor extends StructurePiece
/*		 */ {
/*		 */	 private final boolean a;
/*		 */	 private final boolean b;
/*		 */	 private boolean c;
/*		 */	 private int d;
/*		 */ 
/*		 */	 public WorldGenMineshaftCorridor(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 177 */		 super(paramInt1);
/* 178 */		 this.f = paramInt2;
/* 179 */		 this.e = paramStructureBoundingBox;
/* 180 */		 this.a = (paramRandom.nextInt(3) == 0);
/* 181 */		 this.b = ((!this.a) && (paramRandom.nextInt(23) == 0));
/*		 */ 
/* 183 */		 if ((this.f == 2) || (this.f == 0))
/* 184 */			 this.d = (paramStructureBoundingBox.d() / 5);
/*		 */		 else
/* 186 */			 this.d = (paramStructureBoundingBox.b() / 5);
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 192 */		 StructureBoundingBox localStructureBoundingBox = new StructureBoundingBox(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2 + 2, paramInt3);
/*		 */ 
/* 194 */		 int i = paramRandom.nextInt(3) + 2;
/* 195 */		 while (i > 0) {
/* 196 */			 int j = i * 5;
/*		 */ 
/* 198 */			 switch (paramInt4) {
/*		 */			 case 2:
/* 200 */				 localStructureBoundingBox.d = (paramInt1 + 2);
/* 201 */				 localStructureBoundingBox.c = (paramInt3 - (j - 1));
/* 202 */				 break;
/*		 */			 case 0:
/* 204 */				 localStructureBoundingBox.d = (paramInt1 + 2);
/* 205 */				 localStructureBoundingBox.f = (paramInt3 + (j - 1));
/* 206 */				 break;
/*		 */			 case 1:
/* 208 */				 localStructureBoundingBox.a = (paramInt1 - (j - 1));
/* 209 */				 localStructureBoundingBox.f = (paramInt3 + 2);
/* 210 */				 break;
/*		 */			 case 3:
/* 212 */				 localStructureBoundingBox.d = (paramInt1 + (j - 1));
/* 213 */				 localStructureBoundingBox.f = (paramInt3 + 2);
/*		 */			 }
/*		 */ 
/* 217 */			 if (StructurePiece.a(paramList, localStructureBoundingBox) == null) break;
/* 218 */			 i--;
/*		 */		 }
/*		 */ 
/* 224 */		 if (i > 0) {
/* 225 */			 return localStructureBoundingBox;
/*		 */		 }
/*		 */ 
/* 228 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 233 */		 int i = c();
/* 234 */		 int j = paramRandom.nextInt(4);
/* 235 */		 switch (this.f) {
/*		 */		 case 2:
/* 237 */			 if (j <= 1)
/* 238 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b - 1 + paramRandom.nextInt(3), this.e.c - 1, this.f, i);
/* 239 */			 else if (j == 2)
/* 240 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b - 1 + paramRandom.nextInt(3), this.e.c, 1, i);
/*		 */			 else {
/* 242 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b - 1 + paramRandom.nextInt(3), this.e.c, 3, i);
/*		 */			 }
/* 244 */			 break;
/*		 */		 case 0:
/* 246 */			 if (j <= 1)
/* 247 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b - 1 + paramRandom.nextInt(3), this.e.f + 1, this.f, i);
/* 248 */			 else if (j == 2)
/* 249 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b - 1 + paramRandom.nextInt(3), this.e.f - 3, 1, i);
/*		 */			 else {
/* 251 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b - 1 + paramRandom.nextInt(3), this.e.f - 3, 3, i);
/*		 */			 }
/* 253 */			 break;
/*		 */		 case 1:
/* 255 */			 if (j <= 1)
/* 256 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b - 1 + paramRandom.nextInt(3), this.e.c, this.f, i);
/* 257 */			 else if (j == 2)
/* 258 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b - 1 + paramRandom.nextInt(3), this.e.c - 1, 2, i);
/*		 */			 else {
/* 260 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b - 1 + paramRandom.nextInt(3), this.e.f + 1, 0, i);
/*		 */			 }
/* 262 */			 break;
/*		 */		 case 3:
/* 264 */			 if (j <= 1)
/* 265 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b - 1 + paramRandom.nextInt(3), this.e.c, this.f, i);
/* 266 */			 else if (j == 2)
/* 267 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d - 3, this.e.b - 1 + paramRandom.nextInt(3), this.e.c - 1, 2, i);
/*		 */			 else {
/* 269 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d - 3, this.e.b - 1 + paramRandom.nextInt(3), this.e.f + 1, 0, i);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 275 */		 if (i < 8)
/*		 */		 {
/*		 */			 int k;
/*		 */			 int m;
/* 276 */			 if ((this.f == 2) || (this.f == 0)) {
/* 277 */				 for (k = this.e.c + 3; k + 3 <= this.e.f; k += 5) {
/* 278 */					 m = paramRandom.nextInt(5);
/* 279 */					 if (m == 0)
/* 280 */						 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, k, 1, i + 1);
/* 281 */					 else if (m == 1)
/* 282 */						 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, k, 3, i + 1);
/*		 */				 }
/*		 */			 }
/*		 */			 else
/* 286 */				 for (k = this.e.a + 3; k + 3 <= this.e.d; k += 5) {
/* 287 */					 m = paramRandom.nextInt(5);
/* 288 */					 if (m == 0)
/* 289 */						 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, k, this.e.b, this.e.c - 1, 2, i + 1);
/* 290 */					 else if (m == 1)
/* 291 */						 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, k, this.e.b, this.e.f + 1, 0, i + 1);
/*		 */				 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 301 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 302 */			 return false;
/*		 */		 }
/*		 */ 
/* 309 */		 int i = this.d * 5 - 1;
/*		 */ 
/* 312 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 2, 1, i, 0, 0, false);
/* 313 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.8F, 0, 2, 0, 2, 2, i, 0, 0, false);
/*		 */ 
/* 315 */		 if (this.b)
/* 316 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.6F, 0, 0, 0, 2, 1, i, Block.WEB.id, 0, false);
/*		 */		 int k;
/*		 */		 int m;
/* 320 */		 for (int j = 0; j < this.d; j++)
/*		 */		 {
/* 322 */			 k = 2 + j * 5;
/*		 */ 
/* 324 */			 a(paramWorld, paramStructureBoundingBox, 0, 0, k, 0, 1, k, Block.FENCE.id, 0, false);
/* 325 */			 a(paramWorld, paramStructureBoundingBox, 2, 0, k, 2, 1, k, Block.FENCE.id, 0, false);
/* 326 */			 if (paramRandom.nextInt(4) == 0) {
/* 327 */				 a(paramWorld, paramStructureBoundingBox, 0, 2, k, 0, 2, k, Block.WOOD.id, 0, false);
/* 328 */				 a(paramWorld, paramStructureBoundingBox, 2, 2, k, 2, 2, k, Block.WOOD.id, 0, false);
/*		 */			 } else {
/* 330 */				 a(paramWorld, paramStructureBoundingBox, 0, 2, k, 2, 2, k, Block.WOOD.id, 0, false);
/*		 */			 }
/* 332 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 0, 2, k - 1, Block.WEB.id, 0);
/* 333 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 2, 2, k - 1, Block.WEB.id, 0);
/* 334 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 0, 2, k + 1, Block.WEB.id, 0);
/* 335 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 2, 2, k + 1, Block.WEB.id, 0);
/* 336 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.05F, 0, 2, k - 2, Block.WEB.id, 0);
/* 337 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.05F, 2, 2, k - 2, Block.WEB.id, 0);
/* 338 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.05F, 0, 2, k + 2, Block.WEB.id, 0);
/* 339 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.05F, 2, 2, k + 2, Block.WEB.id, 0);
/*		 */ 
/* 341 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.05F, 1, 2, k - 1, Block.TORCH.id, 0);
/* 342 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.05F, 1, 2, k + 1, Block.TORCH.id, 0);
/*		 */ 
/* 344 */			 if (paramRandom.nextInt(100) == 0) {
/* 345 */				 a(paramWorld, paramStructureBoundingBox, paramRandom, 2, 0, k - 1, WorldGenMineshaftPieces.a(), 3 + paramRandom.nextInt(4));
/*		 */			 }
/* 347 */			 if (paramRandom.nextInt(100) == 0) {
/* 348 */				 a(paramWorld, paramStructureBoundingBox, paramRandom, 0, 0, k + 1, WorldGenMineshaftPieces.a(), 3 + paramRandom.nextInt(4));
/*		 */			 }
/* 350 */			 if ((this.b) && (!this.c)) {
/* 351 */				 m = a(0); int n = k - 1 + paramRandom.nextInt(3);
/* 352 */				 int i1 = a(1, n);
/* 353 */				 n = b(1, n);
/* 354 */				 if (paramStructureBoundingBox.b(i1, m, n)) {
/* 355 */					 this.c = true;
/* 356 */					 paramWorld.setTypeId(i1, m, n, Block.MOB_SPAWNER.id);
/* 357 */					 TileEntityMobSpawner localTileEntityMobSpawner = (TileEntityMobSpawner)paramWorld.getTileEntity(i1, m, n);
/* 358 */					 if (localTileEntityMobSpawner == null) continue; localTileEntityMobSpawner.a("CaveSpider");
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 364 */		 for (j = 0; j <= 2; j++) {
/* 365 */			 for (k = 0; k <= i; k++) {
/* 366 */				 m = a(paramWorld, j, -1, k, paramStructureBoundingBox);
/* 367 */				 if (m == 0) {
/* 368 */					 a(paramWorld, Block.WOOD.id, 0, j, -1, k, paramStructureBoundingBox);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 373 */		 if (this.a) {
/* 374 */			 for (j = 0; j <= i; j++) {
/* 375 */				 k = a(paramWorld, 1, -1, j, paramStructureBoundingBox);
/* 376 */				 if ((k > 0) && (Block.n[k] != 0)) {
/* 377 */					 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.7F, 1, 0, j, Block.RAILS.id, c(Block.RAILS.id, 0));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 382 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaftCorridor
 * JD-Core Version:		0.6.0
 */