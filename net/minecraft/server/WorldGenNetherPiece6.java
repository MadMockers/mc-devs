/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece6 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 public WorldGenNetherPiece6(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 753 */		 super(paramInt1);
/*		 */ 
/* 755 */		 this.f = paramInt2;
/* 756 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 763 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 5, 3, true);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece6 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 769 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -5, -3, 0, 13, 14, 13, paramInt4);
/*		 */ 
/* 771 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 772 */			 return null;
/*		 */		 }
/*		 */ 
/* 775 */		 return new WorldGenNetherPiece6(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 782 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 0, 12, 4, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 784 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 12, 13, 12, 0, 0, false);
/*		 */ 
/* 787 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 1, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 788 */		 a(paramWorld, paramStructureBoundingBox, 11, 5, 0, 12, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 789 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 11, 4, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 790 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 11, 10, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 791 */		 a(paramWorld, paramStructureBoundingBox, 5, 9, 11, 7, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 792 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 0, 4, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 793 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 0, 10, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 794 */		 a(paramWorld, paramStructureBoundingBox, 5, 9, 0, 7, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 797 */		 a(paramWorld, paramStructureBoundingBox, 2, 11, 2, 10, 12, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 800 */		 a(paramWorld, paramStructureBoundingBox, 5, 8, 0, 7, 8, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */ 
/* 803 */		 for (int i = 1; i <= 11; i += 2) {
/* 804 */			 a(paramWorld, paramStructureBoundingBox, i, 10, 0, i, 11, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 805 */			 a(paramWorld, paramStructureBoundingBox, i, 10, 12, i, 11, 12, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 806 */			 a(paramWorld, paramStructureBoundingBox, 0, 10, i, 0, 11, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 807 */			 a(paramWorld, paramStructureBoundingBox, 12, 10, i, 12, 11, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 808 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, i, 13, 0, paramStructureBoundingBox);
/* 809 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, i, 13, 12, paramStructureBoundingBox);
/* 810 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, 0, 13, i, paramStructureBoundingBox);
/* 811 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, 12, 13, i, paramStructureBoundingBox);
/* 812 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, i + 1, 13, 0, paramStructureBoundingBox);
/* 813 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, i + 1, 13, 12, paramStructureBoundingBox);
/* 814 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, i + 1, paramStructureBoundingBox);
/* 815 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, 12, 13, i + 1, paramStructureBoundingBox);
/*		 */		 }
/* 817 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, 0, paramStructureBoundingBox);
/* 818 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, 12, paramStructureBoundingBox);
/* 819 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, 0, paramStructureBoundingBox);
/* 820 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 12, 13, 0, paramStructureBoundingBox);
/*		 */ 
/* 823 */		 for (i = 3; i <= 9; i += 2) {
/* 824 */			 a(paramWorld, paramStructureBoundingBox, 1, 7, i, 1, 8, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 825 */			 a(paramWorld, paramStructureBoundingBox, 11, 7, i, 11, 8, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */		 }
/*		 */ 
/* 829 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 0, 8, 2, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 830 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 4, 12, 2, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 832 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 0, 8, 1, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 833 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 9, 8, 1, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 834 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 4, 3, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 835 */		 a(paramWorld, paramStructureBoundingBox, 9, 0, 4, 12, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 837 */		 for (i = 4; i <= 8; i++) {
/* 838 */			 for (j = 0; j <= 2; j++) {
/* 839 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/* 840 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, 12 - j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/* 843 */		 for (i = 0; i <= 2; i++) {
/* 844 */			 for (j = 4; j <= 8; j++) {
/* 845 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/* 846 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, 12 - i, -1, j, paramStructureBoundingBox);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 851 */		 a(paramWorld, paramStructureBoundingBox, 5, 5, 5, 7, 5, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 852 */		 a(paramWorld, paramStructureBoundingBox, 6, 1, 6, 6, 4, 6, 0, 0, false);
/* 853 */		 a(paramWorld, Block.NETHER_BRICK.id, 0, 6, 0, 6, paramStructureBoundingBox);
/* 854 */		 a(paramWorld, Block.LAVA.id, 0, 6, 5, 6, paramStructureBoundingBox);
/*		 */ 
/* 856 */		 i = a(6, 6);
/* 857 */		 int j = a(5);
/* 858 */		 int k = b(6, 6);
/* 859 */		 if (paramStructureBoundingBox.b(i, j, k)) {
/* 860 */			 paramWorld.e = true;
/* 861 */			 Block.byId[Block.LAVA.id].b(paramWorld, i, j, k, paramRandom);
/* 862 */			 paramWorld.e = false;
/*		 */		 }
/*		 */ 
/* 865 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece6
 * JD-Core Version:		0.6.0
 */