/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ abstract class WorldGenNetherPiece extends StructurePiece
/*		 */ {
/*		 */	 protected WorldGenNetherPiece(int paramInt)
/*		 */	 {
/* 101 */		 super(paramInt);
/*		 */	 }
/*		 */ 
/*		 */	 private int a(List paramList) {
/* 105 */		 int i = 0;
/* 106 */		 int j = 0;
/* 107 */		 for (WorldGenNetherPieceWeight localWorldGenNetherPieceWeight : paramList) {
/* 108 */			 if ((localWorldGenNetherPieceWeight.d > 0) && (localWorldGenNetherPieceWeight.c < localWorldGenNetherPieceWeight.d)) {
/* 109 */				 i = 1;
/*		 */			 }
/* 111 */			 j += localWorldGenNetherPieceWeight.b;
/*		 */		 }
/* 113 */		 return i != 0 ? j : -1;
/*		 */	 }
/*		 */ 
/*		 */	 private WorldGenNetherPiece a(WorldGenNetherPiece15 paramWorldGenNetherPiece15, List paramList1, List paramList2, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 119 */		 int i = a(paramList1);
/* 120 */		 int j = (i > 0) && (paramInt5 <= 30) ? 1 : 0;
/*		 */ 
/* 122 */		 int k = 0;
/*		 */		 int m;
/* 123 */		 while ((k < 5) && (j != 0)) {
/* 124 */			 k++;
/*		 */ 
/* 126 */			 m = paramRandom.nextInt(i);
/* 127 */			 for (WorldGenNetherPieceWeight localWorldGenNetherPieceWeight : paramList1) {
/* 128 */				 m -= localWorldGenNetherPieceWeight.b;
/* 129 */				 if (m < 0)
/*		 */				 {
/* 131 */					 if ((!localWorldGenNetherPieceWeight.a(paramInt5)) || ((localWorldGenNetherPieceWeight == paramWorldGenNetherPiece15.a) && (!localWorldGenNetherPieceWeight.e)))
/*		 */					 {
/*		 */						 break;
/*		 */					 }
/* 135 */					 WorldGenNetherPiece localWorldGenNetherPiece = WorldGenNetherPieces.a(localWorldGenNetherPieceWeight, paramList2, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/* 136 */					 if (localWorldGenNetherPiece != null) {
/* 137 */						 localWorldGenNetherPieceWeight.c += 1;
/* 138 */						 paramWorldGenNetherPiece15.a = localWorldGenNetherPieceWeight;
/*		 */ 
/* 140 */						 if (!localWorldGenNetherPieceWeight.a()) {
/* 141 */							 paramList1.remove(localWorldGenNetherPieceWeight);
/*		 */						 }
/* 143 */						 return localWorldGenNetherPiece;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 148 */		 return WorldGenNetherPiece2.a(paramList2, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */	 }
/*		 */ 
/*		 */	 private StructurePiece a(WorldGenNetherPiece15 paramWorldGenNetherPiece15, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean) {
/* 152 */		 if ((Math.abs(paramInt1 - paramWorldGenNetherPiece15.b().a) > 112) || (Math.abs(paramInt3 - paramWorldGenNetherPiece15.b().c) > 112)) {
/* 153 */			 return WorldGenNetherPiece2.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */		 }
/* 155 */		 List localList = paramWorldGenNetherPiece15.b;
/* 156 */		 if (paramBoolean) {
/* 157 */			 localList = paramWorldGenNetherPiece15.c;
/*		 */		 }
/* 159 */		 WorldGenNetherPiece localWorldGenNetherPiece = a(paramWorldGenNetherPiece15, localList, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5 + 1);
/* 160 */		 if (localWorldGenNetherPiece != null) {
/* 161 */			 paramList.add(localWorldGenNetherPiece);
/* 162 */			 paramWorldGenNetherPiece15.d.add(localWorldGenNetherPiece);
/*		 */		 }
/* 164 */		 return localWorldGenNetherPiece;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece a(WorldGenNetherPiece15 paramWorldGenNetherPiece15, List paramList, Random paramRandom, int paramInt1, int paramInt2, boolean paramBoolean) {
/* 168 */		 switch (this.f) {
/*		 */		 case 2:
/* 170 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a + paramInt1, this.e.b + paramInt2, this.e.c - 1, this.f, c(), paramBoolean);
/*		 */		 case 0:
/* 172 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a + paramInt1, this.e.b + paramInt2, this.e.f + 1, this.f, c(), paramBoolean);
/*		 */		 case 1:
/* 174 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt2, this.e.c + paramInt1, this.f, c(), paramBoolean);
/*		 */		 case 3:
/* 176 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt2, this.e.c + paramInt1, this.f, c(), paramBoolean);
/*		 */		 }
/* 178 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece b(WorldGenNetherPiece15 paramWorldGenNetherPiece15, List paramList, Random paramRandom, int paramInt1, int paramInt2, boolean paramBoolean) {
/* 182 */		 switch (this.f) {
/*		 */		 case 2:
/* 184 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c(), paramBoolean);
/*		 */		 case 0:
/* 186 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c(), paramBoolean);
/*		 */		 case 1:
/* 188 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c(), paramBoolean);
/*		 */		 case 3:
/* 190 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c(), paramBoolean);
/*		 */		 }
/* 192 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece c(WorldGenNetherPiece15 paramWorldGenNetherPiece15, List paramList, Random paramRandom, int paramInt1, int paramInt2, boolean paramBoolean) {
/* 196 */		 switch (this.f) {
/*		 */		 case 2:
/* 198 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c(), paramBoolean);
/*		 */		 case 0:
/* 200 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c(), paramBoolean);
/*		 */		 case 1:
/* 202 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c(), paramBoolean);
/*		 */		 case 3:
/* 204 */			 return a(paramWorldGenNetherPiece15, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c(), paramBoolean);
/*		 */		 }
/* 206 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected static boolean a(StructureBoundingBox paramStructureBoundingBox) {
/* 210 */		 return (paramStructureBoundingBox != null) && (paramStructureBoundingBox.b > 10);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece
 * JD-Core Version:		0.6.0
 */