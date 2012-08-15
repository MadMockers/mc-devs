/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenMineshaftPieces
/*		 */ {
/* 605 */	 private static final StructurePieceTreasure[] a = { new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.INK_SACK.id, 4, 4, 9, 5), new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 2, 3), new StructurePieceTreasure(Item.COAL.id, 0, 3, 8, 10), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 1), new StructurePieceTreasure(Block.RAILS.id, 0, 4, 8, 1), new StructurePieceTreasure(Item.MELON_SEEDS.id, 0, 2, 4, 10), new StructurePieceTreasure(Item.PUMPKIN_SEEDS.id, 0, 2, 4, 10) };
/*		 */ 
/*		 */	 private static StructurePiece a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/*	27 */		 int i = paramRandom.nextInt(100);
/*		 */		 StructureBoundingBox localStructureBoundingBox;
/*	28 */		 if (i >= 80) {
/*	29 */			 localStructureBoundingBox = WorldGenMineshaftCross.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
/*	30 */			 if (localStructureBoundingBox != null)
/*	31 */				 return new WorldGenMineshaftCross(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */		 }
/*	33 */		 else if (i >= 70) {
/*	34 */			 localStructureBoundingBox = WorldGenMineshaftStairs.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
/*	35 */			 if (localStructureBoundingBox != null)
/*	36 */				 return new WorldGenMineshaftStairs(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */		 }
/*		 */		 else {
/*	39 */			 localStructureBoundingBox = WorldGenMineshaftCorridor.a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4);
/*	40 */			 if (localStructureBoundingBox != null) {
/*	41 */				 return new WorldGenMineshaftCorridor(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	45 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private static StructurePiece b(StructurePiece paramStructurePiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*	49 */		 if (paramInt5 > 8) {
/*	50 */			 return null;
/*		 */		 }
/*	52 */		 if ((Math.abs(paramInt1 - paramStructurePiece.b().a) > 80) || (Math.abs(paramInt3 - paramStructurePiece.b().c) > 80)) {
/*	53 */			 return null;
/*		 */		 }
/*		 */ 
/*	56 */		 StructurePiece localStructurePiece = a(paramList, paramRandom, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5 + 1);
/*	57 */		 if (localStructurePiece != null) {
/*	58 */			 paramList.add(localStructurePiece);
/*	59 */			 localStructurePiece.a(paramStructurePiece, paramList, paramRandom);
/*		 */		 }
/*	61 */		 return localStructurePiece;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMineshaftPieces
 * JD-Core Version:		0.6.0
 */