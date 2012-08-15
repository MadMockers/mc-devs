/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdChestCorridor extends WorldGenStrongholdPiece
/*		 */ {
/* 594 */	 private static final StructurePieceTreasure[] a = { new StructurePieceTreasure(Item.ENDER_PEARL.id, 0, 1, 1, 10), new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.REDSTONE.id, 0, 4, 9, 5), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.APPLE.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_SWORD.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_CHESTPLATE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_HELMET.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_LEGGINGS.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_BOOTS.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.GOLDEN_APPLE.id, 0, 1, 1, 1) };
/*		 */	 private final WorldGenStrongholdDoorType b;
/*		 */	 private boolean c;
/*		 */ 
/*		 */	 public WorldGenStrongholdChestCorridor(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 620 */		 super(paramInt1);
/*		 */ 
/* 622 */		 this.f = paramInt2;
/* 623 */		 this.b = a(paramRandom);
/* 624 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 631 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenStrongholdChestCorridor a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 637 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, 7, paramInt4);
/*		 */ 
/* 639 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 640 */			 return null;
/*		 */		 }
/*		 */ 
/* 643 */		 return new WorldGenStrongholdChestCorridor(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 648 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 649 */			 return false;
/*		 */		 }
/*		 */ 
/* 653 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 6, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 655 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.b, 1, 1, 0);
/*		 */ 
/* 657 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.a, 1, 1, 6);
/*		 */ 
/* 660 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 2, 3, 1, 4, Block.SMOOTH_BRICK.id, Block.SMOOTH_BRICK.id, false);
/* 661 */		 a(paramWorld, Block.STEP.id, 5, 3, 1, 1, paramStructureBoundingBox);
/* 662 */		 a(paramWorld, Block.STEP.id, 5, 3, 1, 5, paramStructureBoundingBox);
/* 663 */		 a(paramWorld, Block.STEP.id, 5, 3, 2, 2, paramStructureBoundingBox);
/* 664 */		 a(paramWorld, Block.STEP.id, 5, 3, 2, 4, paramStructureBoundingBox);
/* 665 */		 for (int i = 2; i <= 4; i++) {
/* 666 */			 a(paramWorld, Block.STEP.id, 5, 2, 1, i, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 669 */		 if (!this.c) {
/* 670 */			 i = a(2);
/* 671 */			 int j = a(3, 3); int k = b(3, 3);
/* 672 */			 if (paramStructureBoundingBox.b(j, i, k)) {
/* 673 */				 this.c = true;
/* 674 */				 a(paramWorld, paramStructureBoundingBox, paramRandom, 3, 2, 3, a, 2 + paramRandom.nextInt(2));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 678 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdChestCorridor
 * JD-Core Version:		0.6.0
 */