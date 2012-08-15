/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenNetherPiece11 extends WorldGenNetherPiece
/*			*/ {
/*			*/	 public WorldGenNetherPiece11(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/*	877 */		 super(paramInt1);
/*			*/ 
/*	879 */		 this.f = paramInt2;
/*	880 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/*	887 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 5, 3, true);
/*	888 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 5, 11, true);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenNetherPiece11 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/*	894 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -5, -3, 0, 13, 14, 13, paramInt4);
/*			*/ 
/*	896 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/*	897 */			 return null;
/*			*/		 }
/*			*/ 
/*	900 */		 return new WorldGenNetherPiece11(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/*	907 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 0, 12, 4, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/*	909 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 12, 13, 12, 0, 0, false);
/*			*/ 
/*	912 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 1, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	913 */		 a(paramWorld, paramStructureBoundingBox, 11, 5, 0, 12, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	914 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 11, 4, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	915 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 11, 10, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	916 */		 a(paramWorld, paramStructureBoundingBox, 5, 9, 11, 7, 12, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	917 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 0, 4, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	918 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 0, 10, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	919 */		 a(paramWorld, paramStructureBoundingBox, 5, 9, 0, 7, 12, 1, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/*	922 */		 a(paramWorld, paramStructureBoundingBox, 2, 11, 2, 10, 12, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/*	925 */		 for (int i = 1; i <= 11; i += 2) {
/*	926 */			 a(paramWorld, paramStructureBoundingBox, i, 10, 0, i, 11, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	927 */			 a(paramWorld, paramStructureBoundingBox, i, 10, 12, i, 11, 12, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	928 */			 a(paramWorld, paramStructureBoundingBox, 0, 10, i, 0, 11, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	929 */			 a(paramWorld, paramStructureBoundingBox, 12, 10, i, 12, 11, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	930 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, i, 13, 0, paramStructureBoundingBox);
/*	931 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, i, 13, 12, paramStructureBoundingBox);
/*	932 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, 0, 13, i, paramStructureBoundingBox);
/*	933 */			 a(paramWorld, Block.NETHER_BRICK.id, 0, 12, 13, i, paramStructureBoundingBox);
/*	934 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, i + 1, 13, 0, paramStructureBoundingBox);
/*	935 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, i + 1, 13, 12, paramStructureBoundingBox);
/*	936 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, i + 1, paramStructureBoundingBox);
/*	937 */			 a(paramWorld, Block.NETHER_FENCE.id, 0, 12, 13, i + 1, paramStructureBoundingBox);
/*			*/		 }
/*	939 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, 0, paramStructureBoundingBox);
/*	940 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, 12, paramStructureBoundingBox);
/*	941 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 0, 13, 0, paramStructureBoundingBox);
/*	942 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 12, 13, 0, paramStructureBoundingBox);
/*			*/ 
/*	945 */		 for (i = 3; i <= 9; i += 2) {
/*	946 */			 a(paramWorld, paramStructureBoundingBox, 1, 7, i, 1, 8, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	947 */			 a(paramWorld, paramStructureBoundingBox, 11, 7, i, 11, 8, i, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/		 }
/*			*/ 
/*	951 */		 i = c(Block.NETHER_BRICK_STAIRS.id, 3);
/*	952 */		 for (int j = 0; j <= 6; j++) {
/*	953 */			 k = j + 4;
/*	954 */			 for (m = 5; m <= 7; m++) {
/*	955 */				 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, m, 5 + j, k, paramStructureBoundingBox);
/*			*/			 }
/*	957 */			 if ((k >= 5) && (k <= 8))
/*	958 */				 a(paramWorld, paramStructureBoundingBox, 5, 5, k, 7, j + 4, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	959 */			 else if ((k >= 9) && (k <= 10)) {
/*	960 */				 a(paramWorld, paramStructureBoundingBox, 5, 8, k, 7, j + 4, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/			 }
/*	962 */			 if (j >= 1) {
/*	963 */				 a(paramWorld, paramStructureBoundingBox, 5, 6 + j, k, 7, 9 + j, k, 0, 0, false);
/*			*/			 }
/*			*/		 }
/*	966 */		 for (j = 5; j <= 7; j++) {
/*	967 */			 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, j, 12, 11, paramStructureBoundingBox);
/*			*/		 }
/*	969 */		 a(paramWorld, paramStructureBoundingBox, 5, 6, 7, 5, 7, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	970 */		 a(paramWorld, paramStructureBoundingBox, 7, 6, 7, 7, 7, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*	971 */		 a(paramWorld, paramStructureBoundingBox, 5, 13, 12, 7, 13, 12, 0, 0, false);
/*			*/ 
/*	974 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 2, 3, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	975 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 9, 3, 5, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	976 */		 a(paramWorld, paramStructureBoundingBox, 2, 5, 4, 2, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	977 */		 a(paramWorld, paramStructureBoundingBox, 9, 5, 2, 10, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	978 */		 a(paramWorld, paramStructureBoundingBox, 9, 5, 9, 10, 5, 10, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	979 */		 a(paramWorld, paramStructureBoundingBox, 10, 5, 4, 10, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	980 */		 j = c(Block.NETHER_BRICK_STAIRS.id, 0);
/*	981 */		 int k = c(Block.NETHER_BRICK_STAIRS.id, 1);
/*	982 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 2, paramStructureBoundingBox);
/*	983 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 3, paramStructureBoundingBox);
/*	984 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 9, paramStructureBoundingBox);
/*	985 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, k, 4, 5, 10, paramStructureBoundingBox);
/*	986 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 2, paramStructureBoundingBox);
/*	987 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 3, paramStructureBoundingBox);
/*	988 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 9, paramStructureBoundingBox);
/*	989 */		 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, j, 8, 5, 10, paramStructureBoundingBox);
/*			*/ 
/*	992 */		 a(paramWorld, paramStructureBoundingBox, 3, 4, 4, 4, 4, 8, Block.SOUL_SAND.id, Block.SOUL_SAND.id, false);
/*	993 */		 a(paramWorld, paramStructureBoundingBox, 8, 4, 4, 9, 4, 8, Block.SOUL_SAND.id, Block.SOUL_SAND.id, false);
/*	994 */		 a(paramWorld, paramStructureBoundingBox, 3, 5, 4, 4, 5, 8, Block.NETHER_WART.id, Block.NETHER_WART.id, false);
/*	995 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 4, 9, 5, 8, Block.NETHER_WART.id, Block.NETHER_WART.id, false);
/*			*/ 
/*	998 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 0, 8, 2, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*	999 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 4, 12, 2, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1001 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 0, 8, 1, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1002 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 9, 8, 1, 12, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1003 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 4, 3, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1004 */		 a(paramWorld, paramStructureBoundingBox, 9, 0, 4, 12, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/		 int n;
/* 1006 */		 for (int m = 4; m <= 8; m++) {
/* 1007 */			 for (n = 0; n <= 2; n++) {
/* 1008 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, m, -1, n, paramStructureBoundingBox);
/* 1009 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, m, -1, 12 - n, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/* 1012 */		 for (m = 0; m <= 2; m++) {
/* 1013 */			 for (n = 4; n <= 8; n++) {
/* 1014 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, m, -1, n, paramStructureBoundingBox);
/* 1015 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, 12 - m, -1, n, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1019 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece11
 * JD-Core Version:		0.6.0
 */