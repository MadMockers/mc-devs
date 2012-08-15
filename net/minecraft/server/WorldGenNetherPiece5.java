/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenNetherPiece5 extends WorldGenNetherPiece
/*			*/ {
/*			*/	 public WorldGenNetherPiece5(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1361 */		 super(paramInt1);
/*			*/ 
/* 1363 */		 this.f = paramInt2;
/* 1364 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1371 */		 int i = 1;
/*			*/ 
/* 1373 */		 if ((this.f == 1) || (this.f == 2)) {
/* 1374 */			 i = 5;
/*			*/		 }
/*			*/ 
/* 1377 */		 b((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, i, paramRandom.nextInt(8) > 0);
/* 1378 */		 c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, i, paramRandom.nextInt(8) > 0);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenNetherPiece5 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1384 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -3, 0, 0, 9, 7, 9, paramInt4);
/*			*/ 
/* 1386 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1387 */			 return null;
/*			*/		 }
/*			*/ 
/* 1390 */		 return new WorldGenNetherPiece5(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1397 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 8, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1399 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 8, 5, 8, 0, 0, false);
/*			*/ 
/* 1401 */		 a(paramWorld, paramStructureBoundingBox, 0, 6, 0, 8, 6, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1404 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 2, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1405 */		 a(paramWorld, paramStructureBoundingBox, 6, 2, 0, 8, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1406 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 0, 1, 4, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1407 */		 a(paramWorld, paramStructureBoundingBox, 7, 3, 0, 7, 4, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/ 
/* 1410 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 4, 8, 2, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1411 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 4, 2, 2, 4, 0, 0, false);
/* 1412 */		 a(paramWorld, paramStructureBoundingBox, 6, 1, 4, 7, 2, 4, 0, 0, false);
/*			*/ 
/* 1415 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 8, 8, 3, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1416 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 6, 0, 3, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1417 */		 a(paramWorld, paramStructureBoundingBox, 8, 3, 6, 8, 3, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/ 
/* 1420 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 4, 0, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1421 */		 a(paramWorld, paramStructureBoundingBox, 8, 3, 4, 8, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1422 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 5, 2, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1423 */		 a(paramWorld, paramStructureBoundingBox, 6, 3, 5, 7, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1424 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 5, 1, 5, 5, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1425 */		 a(paramWorld, paramStructureBoundingBox, 7, 4, 5, 7, 5, 5, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/ 
/* 1428 */		 for (int i = 0; i <= 5; i++) {
/* 1429 */			 for (int j = 0; j <= 8; j++) {
/* 1430 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, j, -1, i, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1434 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece5
 * JD-Core Version:		0.6.0
 */