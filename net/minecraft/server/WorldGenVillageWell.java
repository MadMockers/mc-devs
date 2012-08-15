/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenVillageWell extends WorldGenVillagePiece
/*		 */ {
/*		 */	 private final boolean a;
/* 368 */	 private int b = -1;
/*		 */ 
/*		 */	 public WorldGenVillageWell(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, int paramInt2, int paramInt3) {
/* 371 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*		 */ 
/* 373 */		 this.a = true;
/* 374 */		 this.f = paramRandom.nextInt(4);
/*		 */ 
/* 376 */		 switch (this.f) {
/*		 */		 case 0:
/*		 */		 case 2:
/* 379 */			 this.e = new StructureBoundingBox(paramInt2, 64, paramInt3, paramInt2 + 6 - 1, 78, paramInt3 + 6 - 1);
/* 380 */			 break;
/*		 */		 default:
/* 382 */			 this.e = new StructureBoundingBox(paramInt2, 64, paramInt3, paramInt2 + 6 - 1, 78, paramInt3 + 6 - 1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 398 */		 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.e - 4, this.e.c + 1, 1, c());
/* 399 */		 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.e - 4, this.e.c + 1, 3, c());
/* 400 */		 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.e - 4, this.e.c - 1, 2, c());
/* 401 */		 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.e - 4, this.e.f + 1, 0, c());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 419 */		 if (this.b < 0) {
/* 420 */			 this.b = b(paramWorld, paramStructureBoundingBox);
/* 421 */			 if (this.b < 0) {
/* 422 */				 return true;
/*		 */			 }
/* 424 */			 this.e.a(0, this.b - this.e.e + 3, 0);
/*		 */		 }
/*		 */ 
/* 427 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 4, 12, 4, Block.COBBLESTONE.id, Block.WATER.id, false);
/* 428 */		 a(paramWorld, 0, 0, 2, 12, 2, paramStructureBoundingBox);
/* 429 */		 a(paramWorld, 0, 0, 3, 12, 2, paramStructureBoundingBox);
/* 430 */		 a(paramWorld, 0, 0, 2, 12, 3, paramStructureBoundingBox);
/* 431 */		 a(paramWorld, 0, 0, 3, 12, 3, paramStructureBoundingBox);
/*		 */ 
/* 433 */		 a(paramWorld, Block.FENCE.id, 0, 1, 13, 1, paramStructureBoundingBox);
/* 434 */		 a(paramWorld, Block.FENCE.id, 0, 1, 14, 1, paramStructureBoundingBox);
/* 435 */		 a(paramWorld, Block.FENCE.id, 0, 4, 13, 1, paramStructureBoundingBox);
/* 436 */		 a(paramWorld, Block.FENCE.id, 0, 4, 14, 1, paramStructureBoundingBox);
/* 437 */		 a(paramWorld, Block.FENCE.id, 0, 1, 13, 4, paramStructureBoundingBox);
/* 438 */		 a(paramWorld, Block.FENCE.id, 0, 1, 14, 4, paramStructureBoundingBox);
/* 439 */		 a(paramWorld, Block.FENCE.id, 0, 4, 13, 4, paramStructureBoundingBox);
/* 440 */		 a(paramWorld, Block.FENCE.id, 0, 4, 14, 4, paramStructureBoundingBox);
/* 441 */		 a(paramWorld, paramStructureBoundingBox, 1, 15, 1, 4, 15, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 443 */		 for (int i = 0; i <= 5; i++) {
/* 444 */			 for (int j = 0; j <= 5; j++)
/*		 */			 {
/* 446 */				 if ((j != 0) && (j != 5) && (i != 0) && (i != 5)) {
/*		 */					 continue;
/*		 */				 }
/* 449 */				 a(paramWorld, Block.GRAVEL.id, 0, j, 11, i, paramStructureBoundingBox);
/* 450 */				 b(paramWorld, j, 12, i, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 454 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageWell
 * JD-Core Version:		0.6.0
 */