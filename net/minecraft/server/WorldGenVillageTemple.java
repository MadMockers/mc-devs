/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenVillageTemple extends WorldGenVillagePiece
/*		 */ {
/* 735 */	 private int a = -1;
/*		 */ 
/*		 */	 public WorldGenVillageTemple(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/* 738 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*		 */ 
/* 740 */		 this.f = paramInt2;
/* 741 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenVillageTemple a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 746 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 5, 12, 9, paramInt4);
/*		 */ 
/* 748 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 749 */			 return null;
/*		 */		 }
/*		 */ 
/* 752 */		 return new WorldGenVillageTemple(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 758 */		 if (this.a < 0) {
/* 759 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 760 */			 if (this.a < 0) {
/* 761 */				 return true;
/*		 */			 }
/* 763 */			 this.e.a(0, this.a - this.e.e + 12 - 1, 0);
/*		 */		 }
/*		 */ 
/* 767 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 3, 3, 7, 0, 0, false);
/* 768 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 1, 3, 9, 3, 0, 0, false);
/*		 */ 
/* 771 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 0, 3, 0, 8, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 774 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 0, 3, 10, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 776 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 10, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 778 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 10, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 780 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 4, 0, 4, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 782 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 4, 4, 4, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 784 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 8, 3, 4, 8, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 786 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 4, 3, 10, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 789 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 5, 3, 5, 7, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 791 */		 a(paramWorld, paramStructureBoundingBox, 0, 9, 0, 4, 9, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*		 */ 
/* 793 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 0, 4, 4, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 794 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 0, 11, 2, paramStructureBoundingBox);
/* 795 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 4, 11, 2, paramStructureBoundingBox);
/* 796 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 2, 11, 0, paramStructureBoundingBox);
/* 797 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 2, 11, 4, paramStructureBoundingBox);
/*		 */ 
/* 800 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 1, 1, 6, paramStructureBoundingBox);
/* 801 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 1, 1, 7, paramStructureBoundingBox);
/* 802 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 2, 1, 7, paramStructureBoundingBox);
/* 803 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 3, 1, 6, paramStructureBoundingBox);
/* 804 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 3, 1, 7, paramStructureBoundingBox);
/* 805 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 1, 5, paramStructureBoundingBox);
/* 806 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 1, 6, paramStructureBoundingBox);
/* 807 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 3, 1, 5, paramStructureBoundingBox);
/* 808 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 1), 1, 2, 7, paramStructureBoundingBox);
/* 809 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 0), 3, 2, 7, paramStructureBoundingBox);
/*		 */ 
/* 812 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/* 813 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 3, 2, paramStructureBoundingBox);
/* 814 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 2, 2, paramStructureBoundingBox);
/* 815 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 3, 2, paramStructureBoundingBox);
/* 816 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 6, 2, paramStructureBoundingBox);
/* 817 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 7, 2, paramStructureBoundingBox);
/* 818 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 6, 2, paramStructureBoundingBox);
/* 819 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 7, 2, paramStructureBoundingBox);
/* 820 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 6, 0, paramStructureBoundingBox);
/* 821 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 7, 0, paramStructureBoundingBox);
/* 822 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 6, 4, paramStructureBoundingBox);
/* 823 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 7, 4, paramStructureBoundingBox);
/* 824 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 3, 6, paramStructureBoundingBox);
/* 825 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 3, 6, paramStructureBoundingBox);
/* 826 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 3, 8, paramStructureBoundingBox);
/*		 */ 
/* 829 */		 a(paramWorld, Block.TORCH.id, 0, 2, 4, 7, paramStructureBoundingBox);
/* 830 */		 a(paramWorld, Block.TORCH.id, 0, 1, 4, 6, paramStructureBoundingBox);
/* 831 */		 a(paramWorld, Block.TORCH.id, 0, 3, 4, 6, paramStructureBoundingBox);
/* 832 */		 a(paramWorld, Block.TORCH.id, 0, 2, 4, 5, paramStructureBoundingBox);
/*		 */ 
/* 835 */		 int i = c(Block.LADDER.id, 4);
/* 836 */		 for (int j = 1; j <= 9; j++) {
/* 837 */			 a(paramWorld, Block.LADDER.id, i, 3, j, 3, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 841 */		 a(paramWorld, 0, 0, 2, 1, 0, paramStructureBoundingBox);
/* 842 */		 a(paramWorld, 0, 0, 2, 2, 0, paramStructureBoundingBox);
/* 843 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 2, 1, 0, c(Block.WOODEN_DOOR.id, 1));
/* 844 */		 if ((a(paramWorld, 2, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 2, -1, -1, paramStructureBoundingBox) != 0)) {
/* 845 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 848 */		 for (j = 0; j < 9; j++) {
/* 849 */			 for (int k = 0; k < 5; k++) {
/* 850 */				 b(paramWorld, k, 12, j, paramStructureBoundingBox);
/* 851 */				 b(paramWorld, Block.COBBLESTONE.id, 0, k, -1, j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 855 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 2, 1);
/*		 */ 
/* 857 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected int b(int paramInt)
/*		 */	 {
/* 862 */		 return 2;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageTemple
 * JD-Core Version:		0.6.0
 */