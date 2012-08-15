/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece1 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 public WorldGenNetherPiece1(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 427 */		 super(paramInt1);
/*		 */ 
/* 429 */		 this.f = paramInt2;
/* 430 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 protected WorldGenNetherPiece1(Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/* 435 */		 super(0);
/*		 */ 
/* 437 */		 this.f = paramRandom.nextInt(4);
/*		 */ 
/* 439 */		 switch (this.f) {
/*		 */		 case 0:
/*		 */		 case 2:
/* 442 */			 this.e = new StructureBoundingBox(paramInt1, 64, paramInt2, paramInt1 + 19 - 1, 73, paramInt2 + 19 - 1);
/* 443 */			 break;
/*		 */		 default:
/* 445 */			 this.e = new StructureBoundingBox(paramInt1, 64, paramInt2, paramInt1 + 19 - 1, 73, paramInt2 + 19 - 1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 453 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 8, 3, false);
/* 454 */		 b((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 3, 8, false);
/* 455 */		 c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 3, 8, false);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece1 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 461 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -8, -3, 0, 19, 10, 19, paramInt4);
/*		 */ 
/* 463 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 464 */			 return null;
/*		 */		 }
/*		 */ 
/* 467 */		 return new WorldGenNetherPiece1(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 474 */		 a(paramWorld, paramStructureBoundingBox, 7, 3, 0, 11, 4, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 475 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 7, 18, 4, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 477 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 0, 10, 7, 18, 0, 0, false);
/* 478 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 8, 18, 7, 10, 0, 0, false);
/*		 */ 
/* 480 */		 a(paramWorld, paramStructureBoundingBox, 7, 5, 0, 7, 5, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 481 */		 a(paramWorld, paramStructureBoundingBox, 7, 5, 11, 7, 5, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 482 */		 a(paramWorld, paramStructureBoundingBox, 11, 5, 0, 11, 5, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 483 */		 a(paramWorld, paramStructureBoundingBox, 11, 5, 11, 11, 5, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 484 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 7, 7, 5, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 485 */		 a(paramWorld, paramStructureBoundingBox, 11, 5, 7, 18, 5, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 486 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 11, 7, 5, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 487 */		 a(paramWorld, paramStructureBoundingBox, 11, 5, 11, 18, 5, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 490 */		 a(paramWorld, paramStructureBoundingBox, 7, 2, 0, 11, 2, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 491 */		 a(paramWorld, paramStructureBoundingBox, 7, 2, 13, 11, 2, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 492 */		 a(paramWorld, paramStructureBoundingBox, 7, 0, 0, 11, 1, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 493 */		 a(paramWorld, paramStructureBoundingBox, 7, 0, 15, 11, 1, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */		 int j;
/* 494 */		 for (int i = 7; i <= 11; i++) {
/* 495 */			 for (j = 0; j <= 2; j++) {
/* 496 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/* 497 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, 18 - j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 501 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 7, 5, 2, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 502 */		 a(paramWorld, paramStructureBoundingBox, 13, 2, 7, 18, 2, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 503 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 7, 3, 1, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 504 */		 a(paramWorld, paramStructureBoundingBox, 15, 0, 7, 18, 1, 11, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 505 */		 for (i = 0; i <= 2; i++) {
/* 506 */			 for (j = 7; j <= 11; j++) {
/* 507 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/* 508 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, 18 - i, -1, j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 512 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece1
 * JD-Core Version:		0.6.0
 */