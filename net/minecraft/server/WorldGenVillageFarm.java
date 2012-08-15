/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageFarm extends WorldGenVillagePiece
/*			*/ {
/* 1551 */	 private int a = -1;
/*			*/ 
/*			*/	 public WorldGenVillageFarm(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/* 1554 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1556 */		 this.f = paramInt2;
/* 1557 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageFarm a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1562 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 7, 4, 9, paramInt4);
/*			*/ 
/* 1564 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1565 */			 return null;
/*			*/		 }
/*			*/ 
/* 1568 */		 return new WorldGenVillageFarm(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1574 */		 if (this.a < 0) {
/* 1575 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 1576 */			 if (this.a < 0) {
/* 1577 */				 return true;
/*			*/			 }
/* 1579 */			 this.e.a(0, this.a - this.e.e + 4 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1583 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 6, 4, 8, 0, 0, false);
/*			*/ 
/* 1586 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 2, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
/* 1587 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 1, 5, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
/*			*/ 
/* 1589 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 0, 0, 8, Block.LOG.id, Block.LOG.id, false);
/* 1590 */		 a(paramWorld, paramStructureBoundingBox, 6, 0, 0, 6, 0, 8, Block.LOG.id, Block.LOG.id, false);
/* 1591 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 0, 5, 0, 0, Block.LOG.id, Block.LOG.id, false);
/* 1592 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 8, 5, 0, 8, Block.LOG.id, Block.LOG.id, false);
/*			*/ 
/* 1594 */		 a(paramWorld, paramStructureBoundingBox, 3, 0, 1, 3, 0, 7, Block.WATER.id, Block.WATER.id, false);
/*			*/ 
/* 1596 */		 for (int i = 1; i <= 7; i++) {
/* 1597 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 1, 1, i, paramStructureBoundingBox);
/* 1598 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 2, 1, i, paramStructureBoundingBox);
/* 1599 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 4, 1, i, paramStructureBoundingBox);
/* 1600 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 5, 1, i, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1603 */		 for (i = 0; i < 9; i++) {
/* 1604 */			 for (int j = 0; j < 7; j++) {
/* 1605 */				 b(paramWorld, j, 4, i, paramStructureBoundingBox);
/* 1606 */				 b(paramWorld, Block.DIRT.id, 0, j, -1, i, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1610 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageFarm
 * JD-Core Version:		0.6.0
 */