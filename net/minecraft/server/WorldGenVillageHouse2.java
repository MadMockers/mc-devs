/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageHouse2 extends WorldGenVillagePiece
/*			*/ {
/* 1255 */	 private int a = -1;
/*			*/ 
/*			*/	 public WorldGenVillageHouse2(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/* 1258 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1260 */		 this.f = paramInt2;
/* 1261 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageHouse2 a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1266 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 9, 7, 12, paramInt4);
/*			*/ 
/* 1268 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1269 */			 return null;
/*			*/		 }
/*			*/ 
/* 1272 */		 return new WorldGenVillageHouse2(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1278 */		 if (this.a < 0) {
/* 1279 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 1280 */			 if (this.a < 0) {
/* 1281 */				 return true;
/*			*/			 }
/* 1283 */			 this.e.a(0, this.a - this.e.e + 7 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1287 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 7, 4, 4, 0, 0, false);
/* 1288 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 6, 8, 4, 10, 0, 0, false);
/*			*/ 
/* 1291 */		 a(paramWorld, paramStructureBoundingBox, 2, 0, 5, 8, 0, 10, Block.WOOD.id, Block.WOOD.id, false);
/* 1292 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 7, 0, 4, Block.WOOD.id, Block.WOOD.id, false);
/* 1293 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 0, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1294 */		 a(paramWorld, paramStructureBoundingBox, 8, 0, 0, 8, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1295 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 0, 7, 2, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1296 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 5, 2, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1297 */		 a(paramWorld, paramStructureBoundingBox, 2, 0, 6, 2, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1298 */		 a(paramWorld, paramStructureBoundingBox, 3, 0, 10, 7, 3, 10, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*			*/ 
/* 1301 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 0, 7, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
/* 1302 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 5, 2, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
/* 1303 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 1, 8, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
/* 1304 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 4, 3, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
/* 1305 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 2, 8, 5, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 1306 */		 a(paramWorld, Block.WOOD.id, 0, 0, 4, 2, paramStructureBoundingBox);
/* 1307 */		 a(paramWorld, Block.WOOD.id, 0, 0, 4, 3, paramStructureBoundingBox);
/* 1308 */		 a(paramWorld, Block.WOOD.id, 0, 8, 4, 2, paramStructureBoundingBox);
/* 1309 */		 a(paramWorld, Block.WOOD.id, 0, 8, 4, 3, paramStructureBoundingBox);
/* 1310 */		 a(paramWorld, Block.WOOD.id, 0, 8, 4, 4, paramStructureBoundingBox);
/*			*/ 
/* 1312 */		 int i = c(Block.WOOD_STAIRS.id, 3);
/* 1313 */		 int j = c(Block.WOOD_STAIRS.id, 2);
/* 1314 */		 for (int k = -1; k <= 2; k++) {
/* 1315 */			 for (m = 0; m <= 8; m++) {
/* 1316 */				 a(paramWorld, Block.WOOD_STAIRS.id, i, m, 4 + k, k, paramStructureBoundingBox);
/* 1317 */				 if (((k > -1) || (m <= 1)) && ((k > 0) || (m <= 3)) && ((k > 1) || (m <= 4) || (m >= 6))) {
/* 1318 */					 a(paramWorld, Block.WOOD_STAIRS.id, j, m, 4 + k, 5 - k, paramStructureBoundingBox);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1324 */		 a(paramWorld, paramStructureBoundingBox, 3, 4, 5, 3, 4, 10, Block.WOOD.id, Block.WOOD.id, false);
/* 1325 */		 a(paramWorld, paramStructureBoundingBox, 7, 4, 2, 7, 4, 10, Block.WOOD.id, Block.WOOD.id, false);
/* 1326 */		 a(paramWorld, paramStructureBoundingBox, 4, 5, 4, 4, 5, 10, Block.WOOD.id, Block.WOOD.id, false);
/* 1327 */		 a(paramWorld, paramStructureBoundingBox, 6, 5, 4, 6, 5, 10, Block.WOOD.id, Block.WOOD.id, false);
/* 1328 */		 a(paramWorld, paramStructureBoundingBox, 5, 6, 3, 5, 6, 10, Block.WOOD.id, Block.WOOD.id, false);
/* 1329 */		 k = c(Block.WOOD_STAIRS.id, 0);
/* 1330 */		 for (int m = 4; m >= 1; m--) {
/* 1331 */			 a(paramWorld, Block.WOOD.id, 0, m, 2 + m, 7 - m, paramStructureBoundingBox);
/* 1332 */			 for (n = 8 - m; n <= 10; n++) {
/* 1333 */				 a(paramWorld, Block.WOOD_STAIRS.id, k, m, 2 + m, n, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/* 1336 */		 m = c(Block.WOOD_STAIRS.id, 1);
/* 1337 */		 a(paramWorld, Block.WOOD.id, 0, 6, 6, 3, paramStructureBoundingBox);
/* 1338 */		 a(paramWorld, Block.WOOD.id, 0, 7, 5, 4, paramStructureBoundingBox);
/* 1339 */		 a(paramWorld, Block.WOOD_STAIRS.id, m, 6, 6, 4, paramStructureBoundingBox);
/*			*/		 int i1;
/* 1340 */		 for (int n = 6; n <= 8; n++) {
/* 1341 */			 for (i1 = 5; i1 <= 10; i1++) {
/* 1342 */				 a(paramWorld, Block.WOOD_STAIRS.id, m, n, 12 - n, i1, paramStructureBoundingBox);
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1347 */		 a(paramWorld, Block.LOG.id, 0, 0, 2, 1, paramStructureBoundingBox);
/* 1348 */		 a(paramWorld, Block.LOG.id, 0, 0, 2, 4, paramStructureBoundingBox);
/* 1349 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/* 1350 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 3, paramStructureBoundingBox);
/*			*/ 
/* 1352 */		 a(paramWorld, Block.LOG.id, 0, 4, 2, 0, paramStructureBoundingBox);
/* 1353 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 5, 2, 0, paramStructureBoundingBox);
/* 1354 */		 a(paramWorld, Block.LOG.id, 0, 6, 2, 0, paramStructureBoundingBox);
/*			*/ 
/* 1356 */		 a(paramWorld, Block.LOG.id, 0, 8, 2, 1, paramStructureBoundingBox);
/* 1357 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 2, paramStructureBoundingBox);
/* 1358 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 3, paramStructureBoundingBox);
/* 1359 */		 a(paramWorld, Block.LOG.id, 0, 8, 2, 4, paramStructureBoundingBox);
/* 1360 */		 a(paramWorld, Block.WOOD.id, 0, 8, 2, 5, paramStructureBoundingBox);
/* 1361 */		 a(paramWorld, Block.LOG.id, 0, 8, 2, 6, paramStructureBoundingBox);
/* 1362 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 7, paramStructureBoundingBox);
/* 1363 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 8, paramStructureBoundingBox);
/* 1364 */		 a(paramWorld, Block.LOG.id, 0, 8, 2, 9, paramStructureBoundingBox);
/* 1365 */		 a(paramWorld, Block.LOG.id, 0, 2, 2, 6, paramStructureBoundingBox);
/* 1366 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 2, 7, paramStructureBoundingBox);
/* 1367 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 2, 8, paramStructureBoundingBox);
/* 1368 */		 a(paramWorld, Block.LOG.id, 0, 2, 2, 9, paramStructureBoundingBox);
/*			*/ 
/* 1370 */		 a(paramWorld, Block.LOG.id, 0, 4, 4, 10, paramStructureBoundingBox);
/* 1371 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 5, 4, 10, paramStructureBoundingBox);
/* 1372 */		 a(paramWorld, Block.LOG.id, 0, 6, 4, 10, paramStructureBoundingBox);
/* 1373 */		 a(paramWorld, Block.WOOD.id, 0, 5, 5, 10, paramStructureBoundingBox);
/*			*/ 
/* 1376 */		 a(paramWorld, 0, 0, 2, 1, 0, paramStructureBoundingBox);
/* 1377 */		 a(paramWorld, 0, 0, 2, 2, 0, paramStructureBoundingBox);
/* 1378 */		 a(paramWorld, Block.TORCH.id, 0, 2, 3, 1, paramStructureBoundingBox);
/* 1379 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 2, 1, 0, c(Block.WOODEN_DOOR.id, 1));
/* 1380 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, -1, 3, 2, -1, 0, 0, false);
/* 1381 */		 if ((a(paramWorld, 2, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 2, -1, -1, paramStructureBoundingBox) != 0)) {
/* 1382 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1385 */		 for (n = 0; n < 5; n++) {
/* 1386 */			 for (i1 = 0; i1 < 9; i1++) {
/* 1387 */				 b(paramWorld, i1, 7, n, paramStructureBoundingBox);
/* 1388 */				 b(paramWorld, Block.COBBLESTONE.id, 0, i1, -1, n, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/* 1391 */		 for (n = 5; n < 11; n++) {
/* 1392 */			 for (i1 = 2; i1 < 9; i1++) {
/* 1393 */				 b(paramWorld, i1, 7, n, paramStructureBoundingBox);
/* 1394 */				 b(paramWorld, Block.COBBLESTONE.id, 0, i1, -1, n, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1398 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 2, 2);
/*			*/ 
/* 1400 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageHouse2
 * JD-Core Version:		0.6.0
 */