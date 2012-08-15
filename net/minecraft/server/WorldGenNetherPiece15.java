/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece15 extends WorldGenNetherPiece1
/*		 */ {
/*		 */	 public WorldGenNetherPieceWeight a;
/* 266 */	 public List b = new ArrayList();
/*		 */	 public List c;
/* 261 */	 public ArrayList d = new ArrayList();
/*		 */ 
/*		 */	 public WorldGenNetherPiece15(Random paramRandom, int paramInt1, int paramInt2) {
/* 264 */		 super(paramRandom, paramInt1, paramInt2);
/*		 */		 WorldGenNetherPieceWeight localWorldGenNetherPieceWeight;
/* 267 */		 for (localWorldGenNetherPieceWeight : WorldGenNetherPieces.a()) {
/* 268 */			 localWorldGenNetherPieceWeight.c = 0;
/* 269 */			 this.b.add(localWorldGenNetherPieceWeight);
/*		 */		 }
/*		 */ 
/* 272 */		 this.c = new ArrayList();
/* 273 */		 for (localWorldGenNetherPieceWeight : WorldGenNetherPieces.b()) {
/* 274 */			 localWorldGenNetherPieceWeight.c = 0;
/* 275 */			 this.c.add(localWorldGenNetherPieceWeight);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece15
 * JD-Core Version:		0.6.0
 */