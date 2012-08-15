/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece12 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 private boolean a;
/*		 */ 
/*		 */	 public WorldGenNetherPiece12(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 678 */		 super(paramInt1);
/*		 */ 
/* 680 */		 this.f = paramInt2;
/* 681 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece12 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 687 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -2, 0, 0, 7, 8, 9, paramInt4);
/*		 */ 
/* 689 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 690 */			 return null;
/*		 */		 }
/*		 */ 
/* 693 */		 return new WorldGenNetherPiece12(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 700 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 6, 7, 7, 0, 0, false);
/*		 */ 
/* 703 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 0, 5, 1, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 704 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 1, 5, 2, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 705 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 2, 5, 3, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 706 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 3, 5, 4, 7, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 709 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 0, 1, 4, 2, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 710 */		 a(paramWorld, paramStructureBoundingBox, 5, 2, 0, 5, 4, 2, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 711 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 2, 1, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 712 */		 a(paramWorld, paramStructureBoundingBox, 5, 5, 2, 5, 5, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 713 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 3, 0, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 714 */		 a(paramWorld, paramStructureBoundingBox, 6, 5, 3, 6, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 715 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 8, 5, 5, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 717 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 1, 6, 3, paramStructureBoundingBox);
/* 718 */		 a(paramWorld, Block.NETHER_FENCE.id, 0, 5, 6, 3, paramStructureBoundingBox);
/* 719 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 3, 0, 6, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 720 */		 a(paramWorld, paramStructureBoundingBox, 6, 6, 3, 6, 6, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 721 */		 a(paramWorld, paramStructureBoundingBox, 1, 6, 8, 5, 7, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 722 */		 a(paramWorld, paramStructureBoundingBox, 2, 8, 8, 4, 8, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */		 int j;
/* 724 */		 if (!this.a) {
/* 725 */			 i = a(5); j = a(3, 5); int k = b(3, 5);
/* 726 */			 if (paramStructureBoundingBox.b(j, i, k)) {
/* 727 */				 this.a = true;
/* 728 */				 paramWorld.setTypeId(j, i, k, Block.MOB_SPAWNER.id);
/* 729 */				 TileEntityMobSpawner localTileEntityMobSpawner = (TileEntityMobSpawner)paramWorld.getTileEntity(j, i, k);
/* 730 */				 if (localTileEntityMobSpawner != null) localTileEntityMobSpawner.a("Blaze");
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 735 */		 for (int i = 0; i <= 6; i++) {
/* 736 */			 for (j = 0; j <= 6; j++) {
/* 737 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 741 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece12
 * JD-Core Version:		0.6.0
 */