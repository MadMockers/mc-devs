/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece13 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 public WorldGenNetherPiece13(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 524 */		 super(paramInt1);
/*		 */ 
/* 526 */		 this.f = paramInt2;
/* 527 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 534 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 2, 0, false);
/* 535 */		 b((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, 2, false);
/* 536 */		 c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, 2, false);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece13 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 542 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -2, 0, 0, 7, 9, 7, paramInt4);
/*		 */ 
/* 544 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 545 */			 return null;
/*		 */		 }
/*		 */ 
/* 548 */		 return new WorldGenNetherPiece13(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 555 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 6, 1, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 557 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 6, 7, 6, 0, 0, false);
/*		 */ 
/* 560 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 1, 6, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 561 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 6, 1, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 562 */		 a(paramWorld, paramStructureBoundingBox, 5, 2, 0, 6, 6, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 563 */		 a(paramWorld, paramStructureBoundingBox, 5, 2, 6, 6, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 564 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 0, 6, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 565 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 5, 0, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 566 */		 a(paramWorld, paramStructureBoundingBox, 6, 2, 0, 6, 6, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 567 */		 a(paramWorld, paramStructureBoundingBox, 6, 2, 5, 6, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 570 */		 a(paramWorld, paramStructureBoundingBox, 2, 6, 0, 4, 6, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 571 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 0, 4, 5, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 572 */		 a(paramWorld, paramStructureBoundingBox, 2, 6, 6, 4, 6, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 573 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 6, 4, 5, 6, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 574 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 2, 0, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 575 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 2, 0, 5, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 576 */		 a(paramWorld, paramStructureBoundingBox, 6, 6, 2, 6, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 577 */		 a(paramWorld, paramStructureBoundingBox, 6, 5, 2, 6, 5, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */ 
/* 580 */		 for (int i = 0; i <= 6; i++) {
/* 581 */			 for (int j = 0; j <= 6; j++) {
/* 582 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 586 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece13
 * JD-Core Version:		0.6.0
 */