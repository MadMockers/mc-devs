/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdStairsStraight extends WorldGenStrongholdPiece
/*		 */ {
/*		 */	 private final WorldGenStrongholdDoorType a;
/*		 */ 
/*		 */	 public WorldGenStrongholdStairsStraight(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 692 */		 super(paramInt1);
/*		 */ 
/* 694 */		 this.f = paramInt2;
/* 695 */		 this.a = a(paramRandom);
/* 696 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 703 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenStrongholdStairsStraight a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 709 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -7, 0, 5, 11, 8, paramInt4);
/*		 */ 
/* 711 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 712 */			 return null;
/*		 */		 }
/*		 */ 
/* 715 */		 return new WorldGenStrongholdStairsStraight(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 720 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 721 */			 return false;
/*		 */		 }
/*		 */ 
/* 725 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 10, 7, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 727 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 7, 0);
/*		 */ 
/* 729 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.a, 1, 1, 7);
/*		 */ 
/* 732 */		 int i = c(Block.COBBLESTONE_STAIRS.id, 2);
/* 733 */		 for (int j = 0; j < 6; j++) {
/* 734 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i, 1, 6 - j, 1 + j, paramStructureBoundingBox);
/* 735 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i, 2, 6 - j, 1 + j, paramStructureBoundingBox);
/* 736 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i, 3, 6 - j, 1 + j, paramStructureBoundingBox);
/* 737 */			 if (j < 5) {
/* 738 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 5 - j, 1 + j, paramStructureBoundingBox);
/* 739 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 5 - j, 1 + j, paramStructureBoundingBox);
/* 740 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 5 - j, 1 + j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 744 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStairsStraight
 * JD-Core Version:		0.6.0
 */