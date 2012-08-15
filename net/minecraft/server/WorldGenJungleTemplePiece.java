/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ class WorldGenJungleTemplePiece extends StructurePieceBlockSelector
/*		 */ {
/*		 */	 public void a(Random paramRandom, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
/*		 */	 {
/* 526 */		 if (paramRandom.nextFloat() < 0.4F)
/* 527 */			 this.a = Block.COBBLESTONE.id;
/*		 */		 else
/* 529 */			 this.a = Block.MOSSY_COBBLESTONE.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenJungleTemplePiece
 * JD-Core Version:		0.6.0
 */