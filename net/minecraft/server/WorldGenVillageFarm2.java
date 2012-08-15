/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageFarm2 extends WorldGenVillagePiece
/*			*/ {
/* 1620 */	 private int a = -1;
/*			*/ 
/*			*/	 public WorldGenVillageFarm2(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/* 1623 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1625 */		 this.f = paramInt2;
/* 1626 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageFarm2 a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1631 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 13, 4, 9, paramInt4);
/*			*/ 
/* 1633 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1634 */			 return null;
/*			*/		 }
/*			*/ 
/* 1637 */		 return new WorldGenVillageFarm2(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1643 */		 if (this.a < 0) {
/* 1644 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 1645 */			 if (this.a < 0) {
/* 1646 */				 return true;
/*			*/			 }
/* 1648 */			 this.e.a(0, this.a - this.e.e + 4 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1652 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 12, 4, 8, 0, 0, false);
/*			*/ 
/* 1655 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 2, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
/* 1656 */		 a(paramWorld, paramStructureBoundingBox, 4, 0, 1, 5, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
/* 1657 */		 a(paramWorld, paramStructureBoundingBox, 7, 0, 1, 8, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
/* 1658 */		 a(paramWorld, paramStructureBoundingBox, 10, 0, 1, 11, 0, 7, Block.SOIL.id, Block.SOIL.id, false);
/*			*/ 
/* 1660 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 0, 0, 8, Block.LOG.id, Block.LOG.id, false);
/* 1661 */		 a(paramWorld, paramStructureBoundingBox, 6, 0, 0, 6, 0, 8, Block.LOG.id, Block.LOG.id, false);
/* 1662 */		 a(paramWorld, paramStructureBoundingBox, 12, 0, 0, 12, 0, 8, Block.LOG.id, Block.LOG.id, false);
/* 1663 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 0, 11, 0, 0, Block.LOG.id, Block.LOG.id, false);
/* 1664 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 8, 11, 0, 8, Block.LOG.id, Block.LOG.id, false);
/*			*/ 
/* 1666 */		 a(paramWorld, paramStructureBoundingBox, 3, 0, 1, 3, 0, 7, Block.WATER.id, Block.WATER.id, false);
/* 1667 */		 a(paramWorld, paramStructureBoundingBox, 9, 0, 1, 9, 0, 7, Block.WATER.id, Block.WATER.id, false);
/*			*/ 
/* 1669 */		 for (int i = 1; i <= 7; i++) {
/* 1670 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 1, 1, i, paramStructureBoundingBox);
/* 1671 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 2, 1, i, paramStructureBoundingBox);
/* 1672 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 4, 1, i, paramStructureBoundingBox);
/* 1673 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 5, 1, i, paramStructureBoundingBox);
/* 1674 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 7, 1, i, paramStructureBoundingBox);
/* 1675 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 8, 1, i, paramStructureBoundingBox);
/* 1676 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 10, 1, i, paramStructureBoundingBox);
/* 1677 */			 a(paramWorld, Block.CROPS.id, MathHelper.a(paramRandom, 2, 7), 11, 1, i, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1680 */		 for (i = 0; i < 9; i++) {
/* 1681 */			 for (int j = 0; j < 13; j++) {
/* 1682 */				 b(paramWorld, j, 4, i, paramStructureBoundingBox);
/* 1683 */				 b(paramWorld, Block.DIRT.id, 0, j, -1, i, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1687 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageFarm2
 * JD-Core Version:		0.6.0
 */