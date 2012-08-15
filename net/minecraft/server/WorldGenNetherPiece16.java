/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece16 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 public WorldGenNetherPiece16(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 598 */		 super(paramInt1);
/*		 */ 
/* 600 */		 this.f = paramInt2;
/* 601 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 608 */		 c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 6, 2, false);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece16 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 614 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -2, 0, 0, 7, 11, 7, paramInt4);
/*		 */ 
/* 616 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 617 */			 return null;
/*		 */		 }
/*		 */ 
/* 620 */		 return new WorldGenNetherPiece16(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 627 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 6, 1, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 629 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 6, 10, 6, 0, 0, false);
/*		 */ 
/* 632 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 1, 8, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 633 */		 a(paramWorld, paramStructureBoundingBox, 5, 2, 0, 6, 8, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 634 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 1, 0, 8, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 635 */		 a(paramWorld, paramStructureBoundingBox, 6, 2, 1, 6, 8, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 636 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 6, 5, 8, 6, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 639 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 2, 0, 5, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 640 */		 a(paramWorld, paramStructureBoundingBox, 6, 3, 2, 6, 5, 2, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 641 */		 a(paramWorld, paramStructureBoundingBox, 6, 3, 4, 6, 5, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */ 
/* 644 */		 a(paramWorld, Block.NETHER_BRICK.id, 0, 5, 2, 5, paramStructureBoundingBox);
/* 645 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 5, 4, 3, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 646 */		 a(paramWorld, paramStructureBoundingBox, 3, 2, 5, 3, 4, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 647 */		 a(paramWorld, paramStructureBoundingBox, 2, 2, 5, 2, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 648 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 5, 1, 6, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 651 */		 a(paramWorld, paramStructureBoundingBox, 1, 7, 1, 5, 7, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 652 */		 a(paramWorld, paramStructureBoundingBox, 6, 8, 2, 6, 8, 4, 0, 0, false);
/*		 */ 
/* 655 */		 a(paramWorld, paramStructureBoundingBox, 2, 6, 0, 4, 8, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 656 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 0, 4, 5, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */ 
/* 658 */		 for (int i = 0; i <= 6; i++) {
/* 659 */			 for (int j = 0; j <= 6; j++) {
/* 660 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 664 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece16
 * JD-Core Version:		0.6.0
 */