/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageLight extends WorldGenVillagePiece
/*			*/ {
/* 1697 */	 private int a = -1;
/*			*/ 
/*			*/	 public WorldGenVillageLight(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/* 1700 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1702 */		 this.f = paramInt2;
/* 1703 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static StructureBoundingBox a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*			*/	 {
/* 1708 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 3, 4, 2, paramInt4);
/*			*/ 
/* 1710 */		 if (StructurePiece.a(paramList, localStructureBoundingBox) != null) {
/* 1711 */			 return null;
/*			*/		 }
/*			*/ 
/* 1714 */		 return localStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1720 */		 if (this.a < 0) {
/* 1721 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 1722 */			 if (this.a < 0) {
/* 1723 */				 return true;
/*			*/			 }
/* 1725 */			 this.e.a(0, this.a - this.e.e + 4 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1729 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 2, 3, 1, 0, 0, false);
/*			*/ 
/* 1732 */		 a(paramWorld, Block.FENCE.id, 0, 1, 0, 0, paramStructureBoundingBox);
/* 1733 */		 a(paramWorld, Block.FENCE.id, 0, 1, 1, 0, paramStructureBoundingBox);
/* 1734 */		 a(paramWorld, Block.FENCE.id, 0, 1, 2, 0, paramStructureBoundingBox);
/*			*/ 
/* 1737 */		 a(paramWorld, Block.WOOL.id, 15, 1, 3, 0, paramStructureBoundingBox);
/*			*/ 
/* 1740 */		 a(paramWorld, Block.TORCH.id, 15, 0, 3, 0, paramStructureBoundingBox);
/* 1741 */		 a(paramWorld, Block.TORCH.id, 15, 1, 3, 1, paramStructureBoundingBox);
/* 1742 */		 a(paramWorld, Block.TORCH.id, 15, 2, 3, 0, paramStructureBoundingBox);
/* 1743 */		 a(paramWorld, Block.TORCH.id, 15, 1, 3, -1, paramStructureBoundingBox);
/*			*/ 
/* 1745 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageLight
 * JD-Core Version:		0.6.0
 */