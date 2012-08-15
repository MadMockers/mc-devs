/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageLibrary extends WorldGenVillagePiece
/*			*/ {
/*	872 */	 private int a = -1;
/*			*/ 
/*			*/	 public WorldGenVillageLibrary(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/*	875 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/*	877 */		 this.f = paramInt2;
/*	878 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageLibrary a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/*	883 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 9, 9, 6, paramInt4);
/*			*/ 
/*	885 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/*	886 */			 return null;
/*			*/		 }
/*			*/ 
/*	889 */		 return new WorldGenVillageLibrary(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/*	895 */		 if (this.a < 0) {
/*	896 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/*	897 */			 if (this.a < 0) {
/*	898 */				 return true;
/*			*/			 }
/*	900 */			 this.e.a(0, this.a - this.e.e + 9 - 1, 0);
/*			*/		 }
/*			*/ 
/*	904 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 7, 5, 4, 0, 0, false);
/*			*/ 
/*	907 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 8, 0, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*			*/ 
/*	909 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 8, 5, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	910 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 1, 8, 6, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	911 */		 a(paramWorld, paramStructureBoundingBox, 0, 7, 2, 8, 7, 3, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	912 */		 int i = c(Block.WOOD_STAIRS.id, 3);
/*	913 */		 int j = c(Block.WOOD_STAIRS.id, 2);
/*	914 */		 for (int k = -1; k <= 2; k++) {
/*	915 */			 for (m = 0; m <= 8; m++) {
/*	916 */				 a(paramWorld, Block.WOOD_STAIRS.id, i, m, 6 + k, k, paramStructureBoundingBox);
/*	917 */				 a(paramWorld, Block.WOOD_STAIRS.id, j, m, 6 + k, 5 - k, paramStructureBoundingBox);
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	922 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 0, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	923 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 5, 8, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	924 */		 a(paramWorld, paramStructureBoundingBox, 8, 1, 0, 8, 1, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	925 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 0, 7, 1, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	926 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 0, 4, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	927 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 5, 0, 4, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	928 */		 a(paramWorld, paramStructureBoundingBox, 8, 2, 5, 8, 4, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*	929 */		 a(paramWorld, paramStructureBoundingBox, 8, 2, 0, 8, 4, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*			*/ 
/*	932 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 1, 0, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
/*	933 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 5, 7, 4, 5, Block.WOOD.id, Block.WOOD.id, false);
/*	934 */		 a(paramWorld, paramStructureBoundingBox, 8, 2, 1, 8, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
/*	935 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 0, 7, 4, 0, Block.WOOD.id, Block.WOOD.id, false);
/*			*/ 
/*	938 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 2, 0, paramStructureBoundingBox);
/*	939 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 5, 2, 0, paramStructureBoundingBox);
/*	940 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 6, 2, 0, paramStructureBoundingBox);
/*	941 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 3, 0, paramStructureBoundingBox);
/*	942 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 5, 3, 0, paramStructureBoundingBox);
/*	943 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 6, 3, 0, paramStructureBoundingBox);
/*	944 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/*	945 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 3, paramStructureBoundingBox);
/*	946 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 3, 2, paramStructureBoundingBox);
/*	947 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 3, 3, paramStructureBoundingBox);
/*	948 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 2, paramStructureBoundingBox);
/*	949 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 3, paramStructureBoundingBox);
/*	950 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 3, 2, paramStructureBoundingBox);
/*	951 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 3, 3, paramStructureBoundingBox);
/*	952 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 2, 5, paramStructureBoundingBox);
/*	953 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 3, 2, 5, paramStructureBoundingBox);
/*	954 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 5, 2, 5, paramStructureBoundingBox);
/*	955 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 6, 2, 5, paramStructureBoundingBox);
/*			*/ 
/*	958 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 1, 7, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
/*	959 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 4, 7, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
/*	960 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 4, 7, 3, 4, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/*			*/ 
/*	963 */		 a(paramWorld, Block.WOOD.id, 0, 7, 1, 4, paramStructureBoundingBox);
/*	964 */		 a(paramWorld, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 0), 7, 1, 3, paramStructureBoundingBox);
/*	965 */		 k = c(Block.WOOD_STAIRS.id, 3);
/*	966 */		 a(paramWorld, Block.WOOD_STAIRS.id, k, 6, 1, 4, paramStructureBoundingBox);
/*	967 */		 a(paramWorld, Block.WOOD_STAIRS.id, k, 5, 1, 4, paramStructureBoundingBox);
/*	968 */		 a(paramWorld, Block.WOOD_STAIRS.id, k, 4, 1, 4, paramStructureBoundingBox);
/*	969 */		 a(paramWorld, Block.WOOD_STAIRS.id, k, 3, 1, 4, paramStructureBoundingBox);
/*			*/ 
/*	972 */		 a(paramWorld, Block.FENCE.id, 0, 6, 1, 3, paramStructureBoundingBox);
/*	973 */		 a(paramWorld, Block.WOOD_PLATE.id, 0, 6, 2, 3, paramStructureBoundingBox);
/*	974 */		 a(paramWorld, Block.FENCE.id, 0, 4, 1, 3, paramStructureBoundingBox);
/*	975 */		 a(paramWorld, Block.WOOD_PLATE.id, 0, 4, 2, 3, paramStructureBoundingBox);
/*	976 */		 a(paramWorld, Block.WORKBENCH.id, 0, 7, 1, 1, paramStructureBoundingBox);
/*			*/ 
/*	979 */		 a(paramWorld, 0, 0, 1, 1, 0, paramStructureBoundingBox);
/*	980 */		 a(paramWorld, 0, 0, 1, 2, 0, paramStructureBoundingBox);
/*	981 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 1, 1, 0, c(Block.WOODEN_DOOR.id, 1));
/*	982 */		 if ((a(paramWorld, 1, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 1, -1, -1, paramStructureBoundingBox) != 0)) {
/*	983 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/*	986 */		 for (int m = 0; m < 6; m++) {
/*	987 */			 for (int n = 0; n < 9; n++) {
/*	988 */				 b(paramWorld, n, 9, m, paramStructureBoundingBox);
/*	989 */				 b(paramWorld, Block.COBBLESTONE.id, 0, n, -1, m, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	993 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 2, 1);
/*			*/ 
/*	995 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 protected int b(int paramInt)
/*			*/	 {
/* 1000 */		 return 1;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageLibrary
 * JD-Core Version:		0.6.0
 */