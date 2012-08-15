/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenMineshaftStairs extends StructurePiece
/*		 */ {
/*		 */	 public WorldGenMineshaftStairs(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 524 */		 super(paramInt1);
/* 525 */		 this.f = paramInt2;
/* 526 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 533 */		 StructureBoundingBox localStructureBoundingBox = new StructureBoundingBox(paramInt1, paramInt2 - 5, paramInt3, paramInt1, paramInt2 + 2, paramInt3);
/*		 */ 
/* 535 */		 switch (paramInt4) {
/*		 */		 case 2:
/* 537 */			 localStructureBoundingBox.d = (paramInt1 + 2);
/* 538 */			 localStructureBoundingBox.c = (paramInt3 - 8);
/* 539 */			 break;
/*		 */		 case 0:
/* 541 */			 localStructureBoundingBox.d = (paramInt1 + 2);
/* 542 */			 localStructureBoundingBox.f = (paramInt3 + 8);
/* 543 */			 break;
/*		 */		 case 1:
/* 545 */			 localStructureBoundingBox.a = (paramInt1 - 8);
/* 546 */			 localStructureBoundingBox.f = (paramInt3 + 2);
/* 547 */			 break;
/*		 */		 case 3:
/* 549 */			 localStructureBoundingBox.d = (paramInt1 + 8);
/* 550 */			 localStructureBoundingBox.f = (paramInt3 + 2);
/*		 */		 }
/*		 */ 
/* 554 */		 if (StructurePiece.a(paramList, localStructureBoundingBox) != null) {
/* 555 */			 return null;
/*		 */		 }
/*		 */ 
/* 558 */		 return localStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 563 */		 int i = c();
/*		 */ 
/* 566 */		 switch (this.f) {
/*		 */		 case 2:
/* 568 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b, this.e.c - 1, 2, i);
/* 569 */			 break;
/*		 */		 case 0:
/* 571 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b, this.e.f + 1, 0, i);
/* 572 */			 break;
/*		 */		 case 1:
/* 574 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.c, 1, i);
/* 575 */			 break;
/*		 */		 case 3:
/* 577 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.c, 3, i);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 586 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 587 */			 return false;
/*		 */		 }
/*		 */ 
/* 591 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 2, 7, 1, 0, 0, false);
/*		 */ 
/* 593 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 7, 2, 2, 8, 0, 0, false);
/*		 */ 
/* 595 */		 for (int i = 0; i < 5; i++) {
/* 596 */			 a(paramWorld, paramStructureBoundingBox, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, 0, 0, false);
/*		 */		 }
/*		 */ 
/* 599 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaftStairs
 * JD-Core Version:		0.6.0
 */