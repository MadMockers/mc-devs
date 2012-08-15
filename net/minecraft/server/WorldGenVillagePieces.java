/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenVillagePieces
/*		 */ {
/*		 */	 public static ArrayList a(Random paramRandom, int paramInt)
/*		 */	 {
/*	47 */		 ArrayList localArrayList = new ArrayList();
/*		 */ 
/*	49 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse.class, 4, MathHelper.a(paramRandom, 2 + paramInt, 4 + paramInt * 2)));
/*	50 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageTemple.class, 20, MathHelper.a(paramRandom, 0 + paramInt, 1 + paramInt)));
/*	51 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageLibrary.class, 20, MathHelper.a(paramRandom, 0 + paramInt, 2 + paramInt)));
/*	52 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageHut.class, 3, MathHelper.a(paramRandom, 2 + paramInt, 5 + paramInt * 3)));
/*	53 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageButcher.class, 15, MathHelper.a(paramRandom, 0 + paramInt, 2 + paramInt)));
/*	54 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm2.class, 3, MathHelper.a(paramRandom, 1 + paramInt, 4 + paramInt)));
/*	55 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageFarm.class, 3, MathHelper.a(paramRandom, 2 + paramInt, 4 + paramInt * 2)));
/*	56 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageBlacksmith.class, 15, MathHelper.a(paramRandom, 0, 1 + paramInt)));
/*	57 */		 localArrayList.add(new WorldGenVillagePieceWeight(WorldGenVillageHouse2.class, 8, MathHelper.a(paramRandom, 0 + paramInt, 3 + paramInt * 2)));
/*		 */ 
/*	60 */		 Iterator localIterator = localArrayList.iterator();
/*	61 */		 while (localIterator.hasNext()) {
/*	62 */			 if (((WorldGenVillagePieceWeight)localIterator.next()).d == 0) {
/*	63 */				 localIterator.remove();
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	67 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(List paramList) {
/*	71 */		 int i = 0;
/*	72 */		 int j = 0;
/*	73 */		 for (WorldGenVillagePieceWeight localWorldGenVillagePieceWeight : paramList) {
/*	74 */			 if ((localWorldGenVillagePieceWeight.d > 0) && (localWorldGenVillagePieceWeight.c < localWorldGenVillagePieceWeight.d)) {
/*	75 */				 i = 1;
/*		 */			 }
/*	77 */			 j += localWorldGenVillagePieceWeight.b;
/*		 */		 }
/*	79 */		 return i != 0 ? j : -1;
/*		 */	 }
/*		 */ 
/*		 */	 private static WorldGenVillagePiece a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, WorldGenVillagePieceWeight paramWorldGenVillagePieceWeight, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/*	85 */		 Class localClass = paramWorldGenVillagePieceWeight.a;
/*	86 */		 Object localObject = null;
/*		 */ 
/*	88 */		 if (localClass == WorldGenVillageHouse.class)
/*	89 */			 localObject = WorldGenVillageHouse.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	90 */		 else if (localClass == WorldGenVillageTemple.class)
/*	91 */			 localObject = WorldGenVillageTemple.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	92 */		 else if (localClass == WorldGenVillageLibrary.class)
/*	93 */			 localObject = WorldGenVillageLibrary.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	94 */		 else if (localClass == WorldGenVillageHut.class)
/*	95 */			 localObject = WorldGenVillageHut.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	96 */		 else if (localClass == WorldGenVillageButcher.class)
/*	97 */			 localObject = WorldGenVillageButcher.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*	98 */		 else if (localClass == WorldGenVillageFarm2.class)
/*	99 */			 localObject = WorldGenVillageFarm2.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/* 100 */		 else if (localClass == WorldGenVillageFarm.class)
/* 101 */			 localObject = WorldGenVillageFarm.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/* 102 */		 else if (localClass == WorldGenVillageBlacksmith.class)
/* 103 */			 localObject = WorldGenVillageBlacksmith.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/* 104 */		 else if (localClass == WorldGenVillageHouse2.class) {
/* 105 */			 localObject = WorldGenVillageHouse2.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */		 }
/*		 */ 
/* 108 */		 return (WorldGenVillagePiece)localObject;
/*		 */	 }
/*		 */ 
/*		 */	 private static WorldGenVillagePiece c(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 113 */		 int i = a(paramWorldGenVillageStartPiece.h);
/* 114 */		 if (i <= 0) {
/* 115 */			 return null;
/*		 */		 }
/*		 */ 
/* 118 */		 int j = 0;
/*		 */		 int k;
/* 119 */		 while (j < 5) {
/* 120 */			 j++;
/*		 */ 
/* 122 */			 k = paramRandom.nextInt(i);
/* 123 */			 for (WorldGenVillagePieceWeight localWorldGenVillagePieceWeight : paramWorldGenVillageStartPiece.h) {
/* 124 */				 k -= localWorldGenVillagePieceWeight.b;
/* 125 */				 if (k < 0)
/*		 */				 {
/* 127 */					 if ((!localWorldGenVillagePieceWeight.a(paramInt5)) || ((localWorldGenVillagePieceWeight == paramWorldGenVillageStartPiece.d) && (paramWorldGenVillageStartPiece.h.size() > 1)))
/*		 */					 {
/*		 */						 break;
/*		 */					 }
/* 131 */					 WorldGenVillagePiece localWorldGenVillagePiece = a(paramWorldGenVillageStartPiece, localWorldGenVillagePieceWeight, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/* 132 */					 if (localWorldGenVillagePiece != null) {
/* 133 */						 localWorldGenVillagePieceWeight.c += 1;
/* 134 */						 paramWorldGenVillageStartPiece.d = localWorldGenVillagePieceWeight;
/*		 */ 
/* 136 */						 if (!localWorldGenVillagePieceWeight.a()) {
/* 137 */							 paramWorldGenVillageStartPiece.h.remove(localWorldGenVillagePieceWeight);
/*		 */						 }
/* 139 */						 return localWorldGenVillagePiece;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 147 */		 StructureBoundingBox localStructureBoundingBox = WorldGenVillageLight.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
/* 148 */		 if (localStructureBoundingBox != null) {
/* 149 */			 return new WorldGenVillageLight(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */		 }
/*		 */ 
/* 152 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private static StructurePiece d(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 156 */		 if (paramInt5 > 50) {
/* 157 */			 return null;
/*		 */		 }
/* 159 */		 if ((Math.abs(paramInt1 - paramWorldGenVillageStartPiece.b().a) > 112) || (Math.abs(paramInt3 - paramWorldGenVillageStartPiece.b().c) > 112)) {
/* 160 */			 return null;
/*		 */		 }
/*		 */ 
/* 163 */		 WorldGenVillagePiece localWorldGenVillagePiece = c(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5 + 1);
/* 164 */		 if (localWorldGenVillagePiece != null) {
/* 165 */			 int i = (localWorldGenVillagePiece.e.a + localWorldGenVillagePiece.e.d) / 2;
/* 166 */			 int j = (localWorldGenVillagePiece.e.c + localWorldGenVillagePiece.e.f) / 2;
/* 167 */			 int k = localWorldGenVillagePiece.e.d - localWorldGenVillagePiece.e.a;
/* 168 */			 int m = localWorldGenVillagePiece.e.f - localWorldGenVillagePiece.e.c;
/* 169 */			 int n = k > m ? k : m;
/* 170 */			 if (paramWorldGenVillageStartPiece.d().a(i, j, n / 2 + 4, WorldGenVillage.e)) {
/* 171 */				 paramList.add(localWorldGenVillagePiece);
/* 172 */				 paramWorldGenVillageStartPiece.i.add(localWorldGenVillagePiece);
/* 173 */				 return localWorldGenVillagePiece;
/*		 */			 }
/*		 */		 }
/* 176 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private static StructurePiece e(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/* 180 */		 if (paramInt5 > 3 + paramWorldGenVillageStartPiece.c) {
/* 181 */			 return null;
/*		 */		 }
/* 183 */		 if ((Math.abs(paramInt1 - paramWorldGenVillageStartPiece.b().a) > 112) || (Math.abs(paramInt3 - paramWorldGenVillageStartPiece.b().c) > 112)) {
/* 184 */			 return null;
/*		 */		 }
/*		 */ 
/* 187 */		 StructureBoundingBox localStructureBoundingBox = WorldGenVillageRoad.a(paramWorldGenVillageStartPiece, paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
/* 188 */		 if ((localStructureBoundingBox != null) && (localStructureBoundingBox.b > 10)) {
/* 189 */			 WorldGenVillageRoad localWorldGenVillageRoad = new WorldGenVillageRoad(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/* 190 */			 int i = (localWorldGenVillageRoad.e.a + localWorldGenVillageRoad.e.d) / 2;
/* 191 */			 int j = (localWorldGenVillageRoad.e.c + localWorldGenVillageRoad.e.f) / 2;
/* 192 */			 int k = localWorldGenVillageRoad.e.d - localWorldGenVillageRoad.e.a;
/* 193 */			 int m = localWorldGenVillageRoad.e.f - localWorldGenVillageRoad.e.c;
/* 194 */			 int n = k > m ? k : m;
/* 195 */			 if (paramWorldGenVillageStartPiece.d().a(i, j, n / 2 + 4, WorldGenVillage.e)) {
/* 196 */				 paramList.add(localWorldGenVillageRoad);
/* 197 */				 paramWorldGenVillageStartPiece.j.add(localWorldGenVillageRoad);
/* 198 */				 return localWorldGenVillageRoad;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 202 */		 return null;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillagePieces
 * JD-Core Version:		0.6.0
 */