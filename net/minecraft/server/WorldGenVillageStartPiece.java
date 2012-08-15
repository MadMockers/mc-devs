/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenVillageStartPiece extends WorldGenVillageWell
/*		 */ {
/*		 */	 public final WorldChunkManager a;
/*		 */	 public final boolean b;
/*		 */	 public final int c;
/*		 */	 public WorldGenVillagePieceWeight d;
/*		 */	 public ArrayList h;
/* 469 */	 public ArrayList i = new ArrayList();
/* 470 */	 public ArrayList j = new ArrayList();
/*		 */ 
/*		 */	 public WorldGenVillageStartPiece(WorldChunkManager paramWorldChunkManager, int paramInt1, Random paramRandom, int paramInt2, int paramInt3, ArrayList paramArrayList, int paramInt4) {
/* 473 */		 super(null, 0, paramRandom, paramInt2, paramInt3);
/* 474 */		 this.a = paramWorldChunkManager;
/* 475 */		 this.h = paramArrayList;
/* 476 */		 this.c = paramInt4;
/*		 */ 
/* 478 */		 BiomeBase localBiomeBase = paramWorldChunkManager.getBiome(paramInt2, paramInt3);
/* 479 */		 this.b = ((localBiomeBase == BiomeBase.DESERT) || (localBiomeBase == BiomeBase.DESERT_HILLS));
/* 480 */		 this.k = this;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldChunkManager d() {
/* 484 */		 return this.a;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageStartPiece
 * JD-Core Version:		0.6.0
 */