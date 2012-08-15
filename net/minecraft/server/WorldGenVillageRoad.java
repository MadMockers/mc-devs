/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenVillageRoad extends WorldGenVillageRoadPiece
/*		 */ {
/*		 */	 private int a;
/*		 */ 
/*		 */	 public WorldGenVillageRoad(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 502 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*		 */ 
/* 504 */		 this.f = paramInt2;
/* 505 */		 this.e = paramStructureBoundingBox;
/* 506 */		 this.a = Math.max(paramStructureBoundingBox.b(), paramStructureBoundingBox.d());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 512 */		 int i = 0;
/*		 */ 
/* 515 */		 int j = paramRandom.nextInt(5);
/*		 */		 StructurePiece localStructurePiece;
/* 516 */		 while (j < this.a - 8) {
/* 517 */			 localStructurePiece = a((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, 0, j);
/* 518 */			 if (localStructurePiece != null) {
/* 519 */				 j += Math.max(localStructurePiece.e.b(), localStructurePiece.e.d());
/* 520 */				 i = 1;
/*		 */			 }
/* 522 */			 j += 2 + paramRandom.nextInt(5);
/*		 */		 }
/*		 */ 
/* 526 */		 j = paramRandom.nextInt(5);
/* 527 */		 while (j < this.a - 8) {
/* 528 */			 localStructurePiece = b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, 0, j);
/* 529 */			 if (localStructurePiece != null) {
/* 530 */				 j += Math.max(localStructurePiece.e.b(), localStructurePiece.e.d());
/* 531 */				 i = 1;
/*		 */			 }
/* 533 */			 j += 2 + paramRandom.nextInt(5);
/*		 */		 }
/*		 */ 
/* 536 */		 if ((i != 0) && (paramRandom.nextInt(3) > 0)) {
/* 537 */			 switch (this.f) {
/*		 */			 case 2:
/* 539 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.c, 1, c());
/* 540 */				 break;
/*		 */			 case 0:
/* 542 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.f - 2, 1, c());
/* 543 */				 break;
/*		 */			 case 3:
/* 545 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d - 2, this.e.b, this.e.c - 1, 2, c());
/* 546 */				 break;
/*		 */			 case 1:
/* 548 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b, this.e.c - 1, 2, c());
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 552 */		 if ((i != 0) && (paramRandom.nextInt(3) > 0))
/* 553 */			 switch (this.f) {
/*		 */			 case 2:
/* 555 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.c, 3, c());
/* 556 */				 break;
/*		 */			 case 0:
/* 558 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.f - 2, 3, c());
/* 559 */				 break;
/*		 */			 case 3:
/* 561 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d - 2, this.e.b, this.e.f + 1, 0, c());
/* 562 */				 break;
/*		 */			 case 1:
/* 564 */				 WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b, this.e.f + 1, 0, c());
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 573 */		 int i = 7 * MathHelper.a(paramRandom, 3, 5);
/*		 */ 
/* 575 */		 while (i >= 7) {
/* 576 */			 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 3, 3, i, paramInt4);
/*		 */ 
/* 578 */			 if (StructurePiece.a(paramList, localStructureBoundingBox) == null) {
/* 579 */				 return localStructureBoundingBox;
/*		 */			 }
/* 581 */			 i -= 7;
/*		 */		 }
/*		 */ 
/* 584 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 590 */		 int i = d(Block.GRAVEL.id, 0);
/* 591 */		 for (int j = this.e.a; j <= this.e.d; j++) {
/* 592 */			 for (int k = this.e.c; k <= this.e.f; k++) {
/* 593 */				 if (paramStructureBoundingBox.b(j, 64, k)) {
/* 594 */					 int m = paramWorld.h(j, k) - 1;
/* 595 */					 paramWorld.setRawTypeId(j, m, k, i);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 600 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageRoad
 * JD-Core Version:		0.6.0
 */