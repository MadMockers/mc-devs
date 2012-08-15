/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.ArrayList;
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenStrongholdPieces
/*			*/ {
/*	 56 */	 private static final WorldGenStrongholdPieceWeight[] b = { new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairs.class, 40, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdPrison.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdLeftTurn.class, 20, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdRightTurn.class, 20, 0), new WorldGenStrongholdPieceWeight(WorldGenStrongholdRoomCrossing.class, 10, 6), new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairsStraight.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdStairs2.class, 5, 5), new WorldGenStrongholdPieceWeight(WorldGenStrongholdCrossing.class, 5, 4), new WorldGenStrongholdPieceWeight(WorldGenStrongholdChestCorridor.class, 5, 4), new WorldGenStrongholdUnknown(WorldGenStrongholdLibrary.class, 10, 2), new WorldGenStrongholdPiece2(WorldGenStrongholdPortalRoom.class, 20, 1) };
/*			*/	 private static List c;
/*			*/	 private static Class d;
/*	 82 */	 static int a = 0;
/*			*/ 
/* 1486 */	 private static final WorldGenStrongholdStones e = new WorldGenStrongholdStones(null);
/*			*/ 
/*			*/	 public static void a()
/*			*/	 {
/*	 85 */		 c = new ArrayList();
/*	 86 */		 for (WorldGenStrongholdPieceWeight localWorldGenStrongholdPieceWeight : b) {
/*	 87 */			 localWorldGenStrongholdPieceWeight.c = 0;
/*	 88 */			 c.add(localWorldGenStrongholdPieceWeight);
/*			*/		 }
/*	 90 */		 d = null;
/*			*/	 }
/*			*/ 
/*			*/	 private static boolean c() {
/*	 94 */		 int i = 0;
/*	 95 */		 a = 0;
/*	 96 */		 for (WorldGenStrongholdPieceWeight localWorldGenStrongholdPieceWeight : c) {
/*	 97 */			 if ((localWorldGenStrongholdPieceWeight.d > 0) && (localWorldGenStrongholdPieceWeight.c < localWorldGenStrongholdPieceWeight.d)) {
/*	 98 */				 i = 1;
/*			*/			 }
/*	100 */			 a += localWorldGenStrongholdPieceWeight.b;
/*			*/		 }
/*	102 */		 return i;
/*			*/	 }
/*			*/ 
/*			*/	 private static WorldGenStrongholdPiece a(Class paramClass, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/*	108 */		 Object localObject = null;
/*			*/ 
/*	110 */		 if (paramClass == WorldGenStrongholdStairs.class)
/*	111 */			 localObject = WorldGenStrongholdStairs.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	112 */		 else if (paramClass == WorldGenStrongholdPrison.class)
/*	113 */			 localObject = WorldGenStrongholdPrison.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	114 */		 else if (paramClass == WorldGenStrongholdLeftTurn.class)
/*	115 */			 localObject = WorldGenStrongholdLeftTurn.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	116 */		 else if (paramClass == WorldGenStrongholdRightTurn.class)
/*	117 */			 localObject = WorldGenStrongholdRightTurn.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	118 */		 else if (paramClass == WorldGenStrongholdRoomCrossing.class)
/*	119 */			 localObject = WorldGenStrongholdRoomCrossing.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	120 */		 else if (paramClass == WorldGenStrongholdStairsStraight.class)
/*	121 */			 localObject = WorldGenStrongholdStairsStraight.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	122 */		 else if (paramClass == WorldGenStrongholdStairs2.class)
/*	123 */			 localObject = WorldGenStrongholdStairs2.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	124 */		 else if (paramClass == WorldGenStrongholdCrossing.class)
/*	125 */			 localObject = WorldGenStrongholdCrossing.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	126 */		 else if (paramClass == WorldGenStrongholdChestCorridor.class)
/*	127 */			 localObject = WorldGenStrongholdChestCorridor.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	128 */		 else if (paramClass == WorldGenStrongholdLibrary.class)
/*	129 */			 localObject = WorldGenStrongholdLibrary.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	130 */		 else if (paramClass == WorldGenStrongholdPortalRoom.class) {
/*	131 */			 localObject = WorldGenStrongholdPortalRoom.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*			*/		 }
/*			*/ 
/*	134 */		 return (WorldGenStrongholdPiece)localObject;
/*			*/	 }
/*			*/ 
/*			*/	 private static WorldGenStrongholdPiece b(WorldGenStrongholdStart paramWorldGenStrongholdStart, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/*	139 */		 if (!c()) {
/*	140 */			 return null;
/*			*/		 }
/*			*/ 
/*	143 */		 if (d != null)
/*			*/		 {
/*	145 */			 WorldGenStrongholdPiece localWorldGenStrongholdPiece1 = a(d, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	146 */			 d = null;
/*			*/ 
/*	148 */			 if (localWorldGenStrongholdPiece1 != null) {
/*	149 */				 return localWorldGenStrongholdPiece1;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	153 */		 int i = 0;
/*			*/		 int j;
/*	154 */		 while (i < 5) {
/*	155 */			 i++;
/*			*/ 
/*	157 */			 j = paramRandom.nextInt(a);
/*	158 */			 for (WorldGenStrongholdPieceWeight localWorldGenStrongholdPieceWeight : c) {
/*	159 */				 j -= localWorldGenStrongholdPieceWeight.b;
/*	160 */				 if (j < 0)
/*			*/				 {
/*	162 */					 if ((!localWorldGenStrongholdPieceWeight.a(paramInt5)) || (localWorldGenStrongholdPieceWeight == paramWorldGenStrongholdStart.a))
/*			*/					 {
/*			*/						 break;
/*			*/					 }
/*	166 */					 WorldGenStrongholdPiece localWorldGenStrongholdPiece2 = a(localWorldGenStrongholdPieceWeight.a, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	167 */					 if (localWorldGenStrongholdPiece2 != null) {
/*	168 */						 localWorldGenStrongholdPieceWeight.c += 1;
/*	169 */						 paramWorldGenStrongholdStart.a = localWorldGenStrongholdPieceWeight;
/*			*/ 
/*	171 */						 if (!localWorldGenStrongholdPieceWeight.a()) {
/*	172 */							 c.remove(localWorldGenStrongholdPieceWeight);
/*			*/						 }
/*	174 */						 return localWorldGenStrongholdPiece2;
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	180 */		 StructureBoundingBox localStructureBoundingBox = WorldGenStrongholdCorridor.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
/*	181 */		 if ((localStructureBoundingBox != null) && (localStructureBoundingBox.b > 1)) {
/*	182 */			 return new WorldGenStrongholdCorridor(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/		 }
/*			*/ 
/*	185 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 private static StructurePiece c(WorldGenStrongholdStart paramWorldGenStrongholdStart, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*	189 */		 if (paramInt5 > 50) {
/*	190 */			 return null;
/*			*/		 }
/*	192 */		 if ((Math.abs(paramInt1 - paramWorldGenStrongholdStart.b().a) > 112) || (Math.abs(paramInt3 - paramWorldGenStrongholdStart.b().c) > 112)) {
/*	193 */			 return null;
/*			*/		 }
/*			*/ 
/*	196 */		 WorldGenStrongholdPiece localWorldGenStrongholdPiece = b(paramWorldGenStrongholdStart, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5 + 1);
/*	197 */		 if (localWorldGenStrongholdPiece != null) {
/*	198 */			 paramList.add(localWorldGenStrongholdPiece);
/*	199 */			 paramWorldGenStrongholdStart.c.add(localWorldGenStrongholdPiece);
/*			*/		 }
/*	201 */		 return localWorldGenStrongholdPiece;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdPieces
 * JD-Core Version:		0.6.0
 */