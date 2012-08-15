/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenStrongholdPortalRoom extends WorldGenStrongholdPiece
/*			*/ {
/*			*/	 private boolean a;
/*			*/ 
/*			*/	 public WorldGenStrongholdPortalRoom(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1338 */		 super(paramInt1);
/*			*/ 
/* 1340 */		 this.f = paramInt2;
/* 1341 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1347 */		 if (paramStructurePiece != null)
/* 1348 */			 ((WorldGenStrongholdStart)paramStructurePiece).b = this;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenStrongholdPortalRoom a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1355 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -4, -1, 0, 11, 8, 16, paramInt4);
/*			*/ 
/* 1357 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1358 */			 return null;
/*			*/		 }
/*			*/ 
/* 1361 */		 return new WorldGenStrongholdPortalRoom(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1367 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 10, 7, 15, false, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1369 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.c, 4, 1, 0);
/*			*/ 
/* 1372 */		 int i = 6;
/* 1373 */		 a(paramWorld, paramStructureBoundingBox, 1, i, 1, 1, i, 14, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1374 */		 a(paramWorld, paramStructureBoundingBox, 9, i, 1, 9, i, 14, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1375 */		 a(paramWorld, paramStructureBoundingBox, 2, i, 1, 8, i, 2, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1376 */		 a(paramWorld, paramStructureBoundingBox, 2, i, 14, 8, i, 14, false, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1379 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 2, 1, 4, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1380 */		 a(paramWorld, paramStructureBoundingBox, 8, 1, 1, 9, 1, 4, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1381 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 1, 1, 3, Block.LAVA.id, Block.LAVA.id, false);
/* 1382 */		 a(paramWorld, paramStructureBoundingBox, 9, 1, 1, 9, 1, 3, Block.LAVA.id, Block.LAVA.id, false);
/*			*/ 
/* 1385 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 8, 7, 1, 12, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1386 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 9, 6, 1, 11, Block.LAVA.id, Block.LAVA.id, false);
/*			*/ 
/* 1389 */		 for (int j = 3; j < 14; j += 2) {
/* 1390 */			 a(paramWorld, paramStructureBoundingBox, 0, 3, j, 0, 4, j, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
/* 1391 */			 a(paramWorld, paramStructureBoundingBox, 10, 3, j, 10, 4, j, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
/*			*/		 }
/* 1393 */		 for (j = 2; j < 9; j += 2) {
/* 1394 */			 a(paramWorld, paramStructureBoundingBox, j, 3, 15, j, 4, 15, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
/*			*/		 }
/*			*/ 
/* 1398 */		 j = c(Block.STONE_STAIRS.id, 3);
/* 1399 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 5, 6, 1, 7, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1400 */		 a(paramWorld, paramStructureBoundingBox, 4, 2, 6, 6, 2, 7, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1401 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 7, 6, 3, 7, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1402 */		 for (int k = 4; k <= 6; k++) {
/* 1403 */			 a(paramWorld, Block.STONE_STAIRS.id, j, k, 1, 4, paramStructureBoundingBox);
/* 1404 */			 a(paramWorld, Block.STONE_STAIRS.id, j, k, 2, 5, paramStructureBoundingBox);
/* 1405 */			 a(paramWorld, Block.STONE_STAIRS.id, j, k, 3, 6, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1408 */		 k = 2;
/* 1409 */		 int m = 0;
/* 1410 */		 int n = 3;
/* 1411 */		 int i1 = 1;
/*			*/ 
/* 1413 */		 switch (this.f) {
/*			*/		 case 0:
/* 1415 */			 k = 0;
/* 1416 */			 m = 2;
/* 1417 */			 break;
/*			*/		 case 3:
/* 1419 */			 k = 3;
/* 1420 */			 m = 1;
/* 1421 */			 n = 0;
/* 1422 */			 i1 = 2;
/* 1423 */			 break;
/*			*/		 case 1:
/* 1425 */			 k = 1;
/* 1426 */			 m = 3;
/* 1427 */			 n = 0;
/* 1428 */			 i1 = 2;
/*			*/		 case 2:
/*			*/		 }
/*			*/ 
/* 1432 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, k + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 4, 3, 8, paramStructureBoundingBox);
/* 1433 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, k + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 5, 3, 8, paramStructureBoundingBox);
/* 1434 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, k + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 6, 3, 8, paramStructureBoundingBox);
/* 1435 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, m + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 4, 3, 12, paramStructureBoundingBox);
/* 1436 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, m + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 5, 3, 12, paramStructureBoundingBox);
/* 1437 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, m + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 6, 3, 12, paramStructureBoundingBox);
/* 1438 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, n + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 3, 3, 9, paramStructureBoundingBox);
/* 1439 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, n + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 3, 3, 10, paramStructureBoundingBox);
/* 1440 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, n + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 3, 3, 11, paramStructureBoundingBox);
/* 1441 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, i1 + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 7, 3, 9, paramStructureBoundingBox);
/* 1442 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, i1 + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 7, 3, 10, paramStructureBoundingBox);
/* 1443 */		 a(paramWorld, Block.ENDER_PORTAL_FRAME.id, i1 + (paramRandom.nextFloat() > 0.9F ? 4 : 0), 7, 3, 11, paramStructureBoundingBox);
/*			*/ 
/* 1445 */		 if (!this.a) {
/* 1446 */			 i = a(3);
/* 1447 */			 int i2 = a(5, 6); int i3 = b(5, 6);
/* 1448 */			 if (paramStructureBoundingBox.b(i2, i, i3)) {
/* 1449 */				 this.a = true;
/* 1450 */				 paramWorld.setTypeId(i2, i, i3, Block.MOB_SPAWNER.id);
/* 1451 */				 TileEntityMobSpawner localTileEntityMobSpawner = (TileEntityMobSpawner)paramWorld.getTileEntity(i2, i, i3);
/* 1452 */				 if (localTileEntityMobSpawner != null) localTileEntityMobSpawner.a("Silverfish");
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1456 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdPortalRoom
 * JD-Core Version:		0.6.0
 */