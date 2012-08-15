/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.LinkedList;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ class WorldGenStronghold2Start extends StructureStart
/*		 */ {
/*		 */	 public WorldGenStronghold2Start(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/*	88 */		 WorldGenStrongholdPieces.a();
/*		 */ 
/*	90 */		 WorldGenStrongholdStart localWorldGenStrongholdStart = new WorldGenStrongholdStart(0, paramRandom, (paramInt1 << 4) + 2, (paramInt2 << 4) + 2);
/*	91 */		 this.a.add(localWorldGenStrongholdStart);
/*	92 */		 localWorldGenStrongholdStart.a(localWorldGenStrongholdStart, this.a, paramRandom);
/*		 */ 
/*	94 */		 ArrayList localArrayList = localWorldGenStrongholdStart.c;
/*	95 */		 while (!localArrayList.isEmpty()) {
/*	96 */			 int i = paramRandom.nextInt(localArrayList.size());
/*	97 */			 StructurePiece localStructurePiece = (StructurePiece)localArrayList.remove(i);
/*	98 */			 localStructurePiece.a(localWorldGenStrongholdStart, this.a, paramRandom);
/*		 */		 }
/*		 */ 
/* 101 */		 c();
/* 102 */		 a(paramWorld, paramRandom, 10);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStronghold2Start
 * JD-Core Version:		0.6.0
 */