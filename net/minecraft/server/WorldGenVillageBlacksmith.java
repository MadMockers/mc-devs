/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageBlacksmith extends WorldGenVillagePiece
/*			*/ {
/* 1407 */	 private static final StructurePieceTreasure[] a = { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 1, 3, 5), new StructurePieceTreasure(Item.BREAD.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.APPLE.id, 0, 1, 3, 15), new StructurePieceTreasure(Item.IRON_PICKAXE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_SWORD.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_CHESTPLATE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_HELMET.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_LEGGINGS.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.IRON_BOOTS.id, 0, 1, 1, 5), new StructurePieceTreasure(Block.OBSIDIAN.id, 0, 3, 7, 5), new StructurePieceTreasure(Block.SAPLING.id, 0, 3, 7, 5) };
/*			*/ 
/* 1428 */	 private int b = -1;
/*			*/	 private boolean c;
/*			*/ 
/*			*/	 public WorldGenVillageBlacksmith(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1432 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1434 */		 this.f = paramInt2;
/* 1435 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageBlacksmith a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1440 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 10, 6, 7, paramInt4);
/*			*/ 
/* 1442 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1443 */			 return null;
/*			*/		 }
/*			*/ 
/* 1446 */		 return new WorldGenVillageBlacksmith(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1452 */		 if (this.b < 0) {
/* 1453 */			 this.b = b(paramWorld, paramStructureBoundingBox);
/* 1454 */			 if (this.b < 0) {
/* 1455 */				 return true;
/*			*/			 }
/* 1457 */			 this.e.a(0, this.b - this.e.e + 6 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1461 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 9, 4, 6, 0, 0, false);
/*			*/ 
/* 1464 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 9, 0, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*			*/ 
/* 1467 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 0, 9, 4, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1468 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 9, 5, 6, Block.STEP.id, Block.STEP.id, false);
/* 1469 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 1, 8, 5, 5, 0, 0, false);
/*			*/ 
/* 1472 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
/* 1473 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 0, 4, 0, Block.LOG.id, Block.LOG.id, false);
/* 1474 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 0, 3, 4, 0, Block.LOG.id, Block.LOG.id, false);
/* 1475 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 6, 0, 4, 6, Block.LOG.id, Block.LOG.id, false);
/* 1476 */		 a(paramWorld, Block.WOOD.id, 0, 3, 3, 1, paramStructureBoundingBox);
/* 1477 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 2, 3, 3, 2, Block.WOOD.id, Block.WOOD.id, false);
/* 1478 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 3, 5, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 1479 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
/* 1480 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 6, 5, 3, 6, Block.WOOD.id, Block.WOOD.id, false);
/*			*/ 
/* 1483 */		 a(paramWorld, paramStructureBoundingBox, 5, 1, 0, 5, 3, 0, Block.FENCE.id, Block.FENCE.id, false);
/* 1484 */		 a(paramWorld, paramStructureBoundingBox, 9, 1, 0, 9, 3, 0, Block.FENCE.id, Block.FENCE.id, false);
/*			*/ 
/* 1487 */		 a(paramWorld, paramStructureBoundingBox, 6, 1, 4, 9, 4, 6, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1488 */		 a(paramWorld, Block.LAVA.id, 0, 7, 1, 5, paramStructureBoundingBox);
/* 1489 */		 a(paramWorld, Block.LAVA.id, 0, 8, 1, 5, paramStructureBoundingBox);
/* 1490 */		 a(paramWorld, Block.IRON_FENCE.id, 0, 9, 2, 5, paramStructureBoundingBox);
/* 1491 */		 a(paramWorld, Block.IRON_FENCE.id, 0, 9, 2, 4, paramStructureBoundingBox);
/* 1492 */		 a(paramWorld, paramStructureBoundingBox, 7, 2, 4, 8, 2, 5, 0, 0, false);
/* 1493 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 6, 1, 3, paramStructureBoundingBox);
/* 1494 */		 a(paramWorld, Block.FURNACE.id, 0, 6, 2, 3, paramStructureBoundingBox);
/* 1495 */		 a(paramWorld, Block.FURNACE.id, 0, 6, 3, 3, paramStructureBoundingBox);
/* 1496 */		 a(paramWorld, Block.DOUBLE_STEP.id, 0, 8, 1, 1, paramStructureBoundingBox);
/*			*/ 
/* 1499 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/* 1500 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 4, paramStructureBoundingBox);
/* 1501 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 2, 6, paramStructureBoundingBox);
/* 1502 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 4, 2, 6, paramStructureBoundingBox);
/*			*/ 
/* 1505 */		 a(paramWorld, Block.FENCE.id, 0, 2, 1, 4, paramStructureBoundingBox);
/* 1506 */		 a(paramWorld, Block.WOOD_PLATE.id, 0, 2, 2, 4, paramStructureBoundingBox);
/* 1507 */		 a(paramWorld, Block.WOOD.id, 0, 1, 1, 5, paramStructureBoundingBox);
/* 1508 */		 a(paramWorld, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 3), 2, 1, 5, paramStructureBoundingBox);
/* 1509 */		 a(paramWorld, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 1), 1, 1, 4, paramStructureBoundingBox);
/*			*/		 int j;
/* 1511 */		 if (!this.c) {
/* 1512 */			 i = a(1);
/* 1513 */			 j = a(5, 5); int k = b(5, 5);
/* 1514 */			 if (paramStructureBoundingBox.b(j, i, k)) {
/* 1515 */				 this.c = true;
/* 1516 */				 a(paramWorld, paramStructureBoundingBox, paramRandom, 5, 1, 5, a, 3 + paramRandom.nextInt(6));
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1521 */		 for (int i = 6; i <= 8; i++) {
/* 1522 */			 if ((a(paramWorld, i, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, i, -1, -1, paramStructureBoundingBox) != 0)) {
/* 1523 */				 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), i, 0, -1, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1527 */		 for (i = 0; i < 7; i++) {
/* 1528 */			 for (j = 0; j < 10; j++) {
/* 1529 */				 b(paramWorld, j, 6, i, paramStructureBoundingBox);
/* 1530 */				 b(paramWorld, Block.COBBLESTONE.id, 0, j, -1, i, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1534 */		 a(paramWorld, paramStructureBoundingBox, 7, 1, 1, 1);
/*			*/ 
/* 1536 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 protected int b(int paramInt)
/*			*/	 {
/* 1541 */		 return 3;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageBlacksmith
 * JD-Core Version:		0.6.0
 */