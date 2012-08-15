/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ abstract class WorldGenVillagePiece extends StructurePiece
/*		 */ {
/*		 */	 private int a;
/*		 */	 protected WorldGenVillageStartPiece k;
/*		 */ 
/*		 */	 protected WorldGenVillagePiece(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt)
/*		 */	 {
/* 211 */		 super(paramInt);
/* 212 */		 this.k = paramWorldGenVillageStartPiece;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2) {
/* 216 */		 switch (this.f) {
/*		 */		 case 2:
/* 218 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c());
/*		 */		 case 0:
/* 220 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c());
/*		 */		 case 1:
/* 222 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c());
/*		 */		 case 3:
/* 224 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c());
/*		 */		 }
/* 226 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece b(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2) {
/* 230 */		 switch (this.f) {
/*		 */		 case 2:
/* 232 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c());
/*		 */		 case 0:
/* 234 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c());
/*		 */		 case 1:
/* 236 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c());
/*		 */		 case 3:
/* 238 */			 return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c());
/*		 */		 }
/* 240 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected int b(World paramWorld, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 245 */		 int i = 0;
/* 246 */		 int j = 0;
/* 247 */		 for (int m = this.e.c; m <= this.e.f; m++) {
/* 248 */			 for (int n = this.e.a; n <= this.e.d; n++) {
/* 249 */				 if (paramStructureBoundingBox.b(n, 64, m)) {
/* 250 */					 i += Math.max(paramWorld.h(n, m), paramWorld.worldProvider.getSeaLevel());
/* 251 */					 j++;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 256 */		 if (j == 0) {
/* 257 */			 return -1;
/*		 */		 }
/* 259 */		 return i / j;
/*		 */	 }
/*		 */ 
/*		 */	 protected static boolean a(StructureBoundingBox paramStructureBoundingBox) {
/* 263 */		 return (paramStructureBoundingBox != null) && (paramStructureBoundingBox.b > 10);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 278 */		 if (this.a >= paramInt4) {
/* 279 */			 return;
/*		 */		 }
/*		 */ 
/* 282 */		 for (int i = this.a; i < paramInt4; i++) {
/* 283 */			 int j = a(paramInt1 + i, paramInt3);
/* 284 */			 int m = a(paramInt2);
/* 285 */			 int n = b(paramInt1 + i, paramInt3);
/*		 */ 
/* 287 */			 if (!paramStructureBoundingBox.b(j, m, n)) break;
/* 288 */			 this.a += 1;
/*		 */ 
/* 290 */			 EntityVillager localEntityVillager = new EntityVillager(paramWorld, b(i));
/* 291 */			 localEntityVillager.setPositionRotation(j + 0.5D, m, n + 0.5D, 0.0F, 0.0F);
/* 292 */			 paramWorld.addEntity(localEntityVillager);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected int b(int paramInt)
/*		 */	 {
/* 301 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 protected int d(int paramInt1, int paramInt2) {
/* 305 */		 if (this.k.b) {
/* 306 */			 if (paramInt1 == Block.LOG.id)
/* 307 */				 return Block.SANDSTONE.id;
/* 308 */			 if (paramInt1 == Block.COBBLESTONE.id)
/* 309 */				 return Block.SANDSTONE.id;
/* 310 */			 if (paramInt1 == Block.WOOD.id)
/* 311 */				 return Block.SANDSTONE.id;
/* 312 */			 if (paramInt1 == Block.WOOD_STAIRS.id)
/* 313 */				 return Block.SANDSTONE_STAIRS.id;
/* 314 */			 if (paramInt1 == Block.COBBLESTONE_STAIRS.id)
/* 315 */				 return Block.SANDSTONE_STAIRS.id;
/* 316 */			 if (paramInt1 == Block.GRAVEL.id) {
/* 317 */				 return Block.SANDSTONE.id;
/*		 */			 }
/*		 */		 }
/* 320 */		 return paramInt1;
/*		 */	 }
/*		 */ 
/*		 */	 protected int e(int paramInt1, int paramInt2) {
/* 324 */		 if (this.k.b) {
/* 325 */			 if (paramInt1 == Block.LOG.id)
/* 326 */				 return 0;
/* 327 */			 if (paramInt1 == Block.COBBLESTONE.id)
/* 328 */				 return 0;
/* 329 */			 if (paramInt1 == Block.WOOD.id) {
/* 330 */				 return 2;
/*		 */			 }
/*		 */		 }
/* 333 */		 return paramInt2;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 338 */		 int i = d(paramInt1, paramInt2);
/* 339 */		 int j = e(paramInt1, paramInt2);
/* 340 */		 super.a(paramWorld, i, j, paramInt3, paramInt4, paramInt5, paramStructureBoundingBox);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
/*		 */	 {
/* 345 */		 int i = d(paramInt7, 0);
/* 346 */		 int j = e(paramInt7, 0);
/* 347 */		 int m = d(paramInt8, 0);
/* 348 */		 int n = e(paramInt8, 0);
/* 349 */		 super.a(paramWorld, paramStructureBoundingBox, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, i, j, m, n, paramBoolean);
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 354 */		 int i = d(paramInt1, paramInt2);
/* 355 */		 int j = e(paramInt1, paramInt2);
/* 356 */		 super.b(paramWorld, i, j, paramInt3, paramInt4, paramInt5, paramStructureBoundingBox);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillagePiece
 * JD-Core Version:		0.6.0
 */