/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdStart extends WorldGenStrongholdStairs2
/*		 */ {
/*		 */	 public WorldGenStrongholdPieceWeight a;
/*		 */	 public WorldGenStrongholdPortalRoom b;
/* 504 */	 public ArrayList c = new ArrayList();
/*		 */ 
/*		 */	 public WorldGenStrongholdStart(int paramInt1, Random paramRandom, int paramInt2, int paramInt3) {
/* 507 */		 super(0, paramRandom, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition a()
/*		 */	 {
/* 512 */		 if (this.b != null) {
/* 513 */			 return this.b.a();
/*		 */		 }
/* 515 */		 return super.a();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStart
 * JD-Core Version:		0.6.0
 */