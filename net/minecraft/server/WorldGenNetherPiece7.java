/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenNetherPiece7 extends WorldGenNetherPiece
/*			*/ {
/*			*/	 public WorldGenNetherPiece7(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1094 */		 super(paramInt1);
/*			*/ 
/* 1096 */		 this.f = paramInt2;
/* 1097 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1104 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 1, 0, true);
/* 1105 */		 b((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, 1, true);
/* 1106 */		 c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, 1, true);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenNetherPiece7 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1112 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, 0, 0, 5, 7, 5, paramInt4);
/*			*/ 
/* 1114 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1115 */			 return null;
/*			*/		 }
/*			*/ 
/* 1118 */		 return new WorldGenNetherPiece7(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1125 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1127 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
/*			*/ 
/* 1130 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 0, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1131 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 0, 4, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1132 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 4, 0, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1133 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 4, 4, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1136 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1139 */		 for (int i = 0; i <= 4; i++) {
/* 1140 */			 for (int j = 0; j <= 4; j++) {
/* 1141 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1145 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece7
 * JD-Core Version:		0.6.0
 */