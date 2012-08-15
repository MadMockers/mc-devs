/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenNetherPiece8 extends WorldGenNetherPiece
/*			*/ {
/*			*/	 public WorldGenNetherPiece8(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1223 */		 super(paramInt1);
/*			*/ 
/* 1225 */		 this.f = paramInt2;
/* 1226 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1233 */		 b((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, 1, true);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenNetherPiece8 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1239 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, 0, 0, 5, 7, 5, paramInt4);
/*			*/ 
/* 1241 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1242 */			 return null;
/*			*/		 }
/*			*/ 
/* 1245 */		 return new WorldGenNetherPiece8(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1252 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1254 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
/*			*/ 
/* 1257 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 0, 4, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1258 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 1, 4, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1259 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 3, 4, 4, 3, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/ 
/* 1261 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 0, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1263 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 4, 3, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1264 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 4, 1, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_BRICK.id, false);
/* 1265 */		 a(paramWorld, paramStructureBoundingBox, 3, 3, 4, 3, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1268 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1271 */		 for (int i = 0; i <= 4; i++) {
/* 1272 */			 for (int j = 0; j <= 4; j++) {
/* 1273 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1277 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece8
 * JD-Core Version:		0.6.0
 */