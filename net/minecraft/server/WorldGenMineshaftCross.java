/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenMineshaftCross extends StructurePiece
/*		 */ {
/*		 */	 private final int a;
/*		 */	 private final boolean b;
/*		 */ 
/*		 */	 public WorldGenMineshaftCross(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 394 */		 super(paramInt1);
/* 395 */		 this.a = paramInt2;
/* 396 */		 this.e = paramStructureBoundingBox;
/* 397 */		 this.b = (paramStructureBoundingBox.c() > 3);
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 402 */		 StructureBoundingBox localStructureBoundingBox = new StructureBoundingBox(paramInt1, paramInt2, paramInt3, paramInt1, paramInt2 + 2, paramInt3);
/*		 */ 
/* 404 */		 if (paramRandom.nextInt(4) == 0) {
/* 405 */			 localStructureBoundingBox.e += 4;
/*		 */		 }
/*		 */ 
/* 408 */		 switch (paramInt4) {
/*		 */		 case 2:
/* 410 */			 localStructureBoundingBox.a = (paramInt1 - 1);
/* 411 */			 localStructureBoundingBox.d = (paramInt1 + 3);
/* 412 */			 localStructureBoundingBox.c = (paramInt3 - 4);
/* 413 */			 break;
/*		 */		 case 0:
/* 415 */			 localStructureBoundingBox.a = (paramInt1 - 1);
/* 416 */			 localStructureBoundingBox.d = (paramInt1 + 3);
/* 417 */			 localStructureBoundingBox.f = (paramInt3 + 4);
/* 418 */			 break;
/*		 */		 case 1:
/* 420 */			 localStructureBoundingBox.a = (paramInt1 - 4);
/* 421 */			 localStructureBoundingBox.c = (paramInt3 - 1);
/* 422 */			 localStructureBoundingBox.f = (paramInt3 + 3);
/* 423 */			 break;
/*		 */		 case 3:
/* 425 */			 localStructureBoundingBox.d = (paramInt1 + 4);
/* 426 */			 localStructureBoundingBox.c = (paramInt3 - 1);
/* 427 */			 localStructureBoundingBox.f = (paramInt3 + 3);
/*		 */		 }
/*		 */ 
/* 431 */		 if (StructurePiece.a(paramList, localStructureBoundingBox) != null) {
/* 432 */			 return null;
/*		 */		 }
/*		 */ 
/* 435 */		 return localStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 441 */		 int i = c();
/*		 */ 
/* 444 */		 switch (this.a) {
/*		 */		 case 2:
/* 446 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b, this.e.c - 1, 2, i);
/* 447 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.c + 1, 1, i);
/* 448 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.c + 1, 3, i);
/* 449 */			 break;
/*		 */		 case 0:
/* 451 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b, this.e.f + 1, 0, i);
/* 452 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.c + 1, 1, i);
/* 453 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.c + 1, 3, i);
/* 454 */			 break;
/*		 */		 case 1:
/* 456 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b, this.e.c - 1, 2, i);
/* 457 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b, this.e.f + 1, 0, i);
/* 458 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.c + 1, 1, i);
/* 459 */			 break;
/*		 */		 case 3:
/* 461 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b, this.e.c - 1, 2, i);
/* 462 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b, this.e.f + 1, 0, i);
/* 463 */			 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.c + 1, 3, i);
/*		 */		 }
/*		 */ 
/* 467 */		 if (this.b) {
/* 468 */			 if (paramRandom.nextBoolean())
/* 469 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b + 3 + 1, this.e.c - 1, 2, i);
/* 470 */			 if (paramRandom.nextBoolean())
/* 471 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b + 3 + 1, this.e.c + 1, 1, i);
/* 472 */			 if (paramRandom.nextBoolean())
/* 473 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b + 3 + 1, this.e.c + 1, 3, i);
/* 474 */			 if (paramRandom.nextBoolean())
/* 475 */				 WorldGenMineshaftPieces.a(paramStructurePiece, paramList, paramRandom, this.e.a + 1, this.e.b + 3 + 1, this.e.f + 1, 0, i);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 482 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 483 */			 return false;
/*		 */		 }
/*		 */ 
/* 487 */		 if (this.b) {
/* 488 */			 a(paramWorld, paramStructureBoundingBox, this.e.a + 1, this.e.b, this.e.c, this.e.d - 1, this.e.b + 3 - 1, this.e.f, 0, 0, false);
/* 489 */			 a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b, this.e.c + 1, this.e.d, this.e.b + 3 - 1, this.e.f - 1, 0, 0, false);
/* 490 */			 a(paramWorld, paramStructureBoundingBox, this.e.a + 1, this.e.e - 2, this.e.c, this.e.d - 1, this.e.e, this.e.f, 0, 0, false);
/* 491 */			 a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.e - 2, this.e.c + 1, this.e.d, this.e.e, this.e.f - 1, 0, 0, false);
/* 492 */			 a(paramWorld, paramStructureBoundingBox, this.e.a + 1, this.e.b + 3, this.e.c + 1, this.e.d - 1, this.e.b + 3, this.e.f - 1, 0, 0, false);
/*		 */		 }
/*		 */		 else {
/* 495 */			 a(paramWorld, paramStructureBoundingBox, this.e.a + 1, this.e.b, this.e.c, this.e.d - 1, this.e.e, this.e.f, 0, 0, false);
/* 496 */			 a(paramWorld, paramStructureBoundingBox, this.e.a, this.e.b, this.e.c + 1, this.e.d, this.e.e, this.e.f - 1, 0, 0, false);
/*		 */		 }
/*		 */ 
/* 500 */		 a(paramWorld, paramStructureBoundingBox, this.e.a + 1, this.e.b, this.e.c + 1, this.e.a + 1, this.e.e, this.e.c + 1, Block.WOOD.id, 0, false);
/* 501 */		 a(paramWorld, paramStructureBoundingBox, this.e.a + 1, this.e.b, this.e.f - 1, this.e.a + 1, this.e.e, this.e.f - 1, Block.WOOD.id, 0, false);
/* 502 */		 a(paramWorld, paramStructureBoundingBox, this.e.d - 1, this.e.b, this.e.c + 1, this.e.d - 1, this.e.e, this.e.c + 1, Block.WOOD.id, 0, false);
/* 503 */		 a(paramWorld, paramStructureBoundingBox, this.e.d - 1, this.e.b, this.e.f - 1, this.e.d - 1, this.e.e, this.e.f - 1, Block.WOOD.id, 0, false);
/*		 */ 
/* 507 */		 for (int i = this.e.a; i <= this.e.d; i++) {
/* 508 */			 for (int j = this.e.c; j <= this.e.f; j++) {
/* 509 */				 int k = a(paramWorld, i, this.e.b - 1, j, paramStructureBoundingBox);
/* 510 */				 if (k == 0) {
/* 511 */					 a(paramWorld, Block.WOOD.id, 0, i, this.e.b - 1, j, paramStructureBoundingBox);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 516 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaftCross
 * JD-Core Version:		0.6.0
 */