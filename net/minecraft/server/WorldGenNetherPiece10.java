/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenNetherPiece10 extends WorldGenNetherPiece
/*			*/ {
/*			*/	 public WorldGenNetherPiece10(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1157 */		 super(paramInt1);
/*			*/ 
/* 1159 */		 this.f = paramInt2;
/* 1160 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1167 */		 c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, 1, true);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenNetherPiece10 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1173 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, 0, 0, 5, 7, 5, paramInt4);
/*			*/ 
/* 1175 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1176 */			 return null;
/*			*/		 }
/*			*/ 
/* 1179 */		 return new WorldGenNetherPiece10(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1186 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1188 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
/*			*/ 
/* 1191 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 0, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1192 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 1, 0, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1193 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 3, 0, 4, 3, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/ 
/* 1195 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 0, 4, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1197 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 4, 4, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1198 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 4, 1, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_BRICK.id, false);
/* 1199 */		 a(paramWorld, paramStructureBoundingBox, 3, 3, 4, 3, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1202 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1205 */		 for (int i = 0; i <= 4; i++) {
/* 1206 */			 for (int j = 0; j <= 4; j++) {
/* 1207 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1211 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece10
 * JD-Core Version:		0.6.0
 */