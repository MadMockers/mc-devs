/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenDungeons extends WorldGenerator
/*		 */ {
/*		 */	 public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	15 */		 int i = 3;
/*	16 */		 int j = paramRandom.nextInt(2) + 2;
/*	17 */		 int k = paramRandom.nextInt(2) + 2;
/*		 */ 
/*	19 */		 int m = 0;
/*		 */		 int i1;
/*		 */		 int i2;
/*	20 */		 for (int n = paramInt1 - j - 1; n <= paramInt1 + j + 1; n++) {
/*	21 */			 for (i1 = paramInt2 - 1; i1 <= paramInt2 + i + 1; i1++) {
/*	22 */				 for (i2 = paramInt3 - k - 1; i2 <= paramInt3 + k + 1; i2++) {
/*	23 */					 Material localMaterial = paramWorld.getMaterial(n, i1, i2);
/*	24 */					 if ((i1 == paramInt2 - 1) && (!localMaterial.isBuildable())) return false;
/*	25 */					 if ((i1 == paramInt2 + i + 1) && (!localMaterial.isBuildable())) return false;
/*		 */ 
/*	27 */					 if (((n != paramInt1 - j - 1) && (n != paramInt1 + j + 1) && (i2 != paramInt3 - k - 1) && (i2 != paramInt3 + k + 1)) || 
/*	28 */						 (i1 != paramInt2) || (!paramWorld.isEmpty(n, i1, i2)) || (!paramWorld.isEmpty(n, i1 + 1, i2))) continue;
/*	29 */					 m++;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	37 */		 if ((m < 1) || (m > 5)) return false;
/*		 */ 
/*	39 */		 for (n = paramInt1 - j - 1; n <= paramInt1 + j + 1; n++) {
/*	40 */			 for (i1 = paramInt2 + i; i1 >= paramInt2 - 1; i1--) {
/*	41 */				 for (i2 = paramInt3 - k - 1; i2 <= paramInt3 + k + 1; i2++)
/*		 */				 {
/*	43 */					 if ((n == paramInt1 - j - 1) || (i1 == paramInt2 - 1) || (i2 == paramInt3 - k - 1) || (n == paramInt1 + j + 1) || (i1 == paramInt2 + i + 1) || (i2 == paramInt3 + k + 1)) {
/*	44 */						 if ((i1 >= 0) && (!paramWorld.getMaterial(n, i1 - 1, i2).isBuildable()))
/*	45 */							 paramWorld.setTypeId(n, i1, i2, 0);
/*	46 */						 else if (paramWorld.getMaterial(n, i1, i2).isBuildable()) {
/*	47 */							 if ((i1 == paramInt2 - 1) && (paramRandom.nextInt(4) != 0))
/*	48 */								 paramWorld.setTypeId(n, i1, i2, Block.MOSSY_COBBLESTONE.id);
/*		 */							 else
/*	50 */								 paramWorld.setTypeId(n, i1, i2, Block.COBBLESTONE.id);
/*		 */						 }
/*		 */					 }
/*		 */					 else {
/*	54 */						 paramWorld.setTypeId(n, i1, i2, 0);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	60 */		 for (n = 0; n < 2; n++) {
/*	61 */			 for (i1 = 0; i1 < 3; i1++) {
/*	62 */				 i2 = paramInt1 + paramRandom.nextInt(j * 2 + 1) - j;
/*	63 */				 int i3 = paramInt2;
/*	64 */				 int i4 = paramInt3 + paramRandom.nextInt(k * 2 + 1) - k;
/*	65 */				 if (!paramWorld.isEmpty(i2, i3, i4))
/*		 */					 continue;
/*	67 */				 int i5 = 0;
/*	68 */				 if (paramWorld.getMaterial(i2 - 1, i3, i4).isBuildable()) i5++;
/*	69 */				 if (paramWorld.getMaterial(i2 + 1, i3, i4).isBuildable()) i5++;
/*	70 */				 if (paramWorld.getMaterial(i2, i3, i4 - 1).isBuildable()) i5++;
/*	71 */				 if (paramWorld.getMaterial(i2, i3, i4 + 1).isBuildable()) i5++;
/*		 */ 
/*	73 */				 if (i5 != 1)
/*		 */					 continue;
/*	75 */				 paramWorld.setTypeId(i2, i3, i4, Block.CHEST.id);
/*	76 */				 TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(i2, i3, i4);
/*	77 */				 if (localTileEntityChest == null) break;
/*	78 */				 for (int i6 = 0; i6 < 8; i6++) {
/*	79 */					 ItemStack localItemStack = a(paramRandom);
/*	80 */					 if (localItemStack == null) continue; localTileEntityChest.setItem(paramRandom.nextInt(localTileEntityChest.getSize()), localItemStack);
/*		 */				 }
/*	78 */				 break;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	88 */		 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.MOB_SPAWNER.id);
/*	89 */		 TileEntityMobSpawner localTileEntityMobSpawner = (TileEntityMobSpawner)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/*	90 */		 if (localTileEntityMobSpawner != null)
/*	91 */			 localTileEntityMobSpawner.a(b(paramRandom));
/*		 */		 else {
/*	93 */			 System.err.println("Failed to fetch mob spawner entity at (" + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + ")");
/*		 */		 }
/*		 */ 
/*	96 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private ItemStack a(Random paramRandom) {
/* 100 */		 int i = paramRandom.nextInt(11);
/* 101 */		 if (i == 0) return new ItemStack(Item.SADDLE);
/* 102 */		 if (i == 1) return new ItemStack(Item.IRON_INGOT, paramRandom.nextInt(4) + 1);
/* 103 */		 if (i == 2) return new ItemStack(Item.BREAD);
/* 104 */		 if (i == 3) return new ItemStack(Item.WHEAT, paramRandom.nextInt(4) + 1);
/* 105 */		 if (i == 4) return new ItemStack(Item.SULPHUR, paramRandom.nextInt(4) + 1);
/* 106 */		 if (i == 5) return new ItemStack(Item.STRING, paramRandom.nextInt(4) + 1);
/* 107 */		 if (i == 6) return new ItemStack(Item.BUCKET);
/* 108 */		 if ((i == 7) && (paramRandom.nextInt(100) == 0)) return new ItemStack(Item.GOLDEN_APPLE);
/* 109 */		 if ((i == 8) && (paramRandom.nextInt(2) == 0)) return new ItemStack(Item.REDSTONE, paramRandom.nextInt(4) + 1);
/* 110 */		 if ((i == 9) && (paramRandom.nextInt(10) == 0))
/* 111 */			 return new ItemStack(Item.byId[(Item.RECORD_1.id + paramRandom.nextInt(2))]);
/* 112 */		 if (i == 10) return new ItemStack(Item.INK_SACK, 1, 3);
/*		 */ 
/* 114 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private String b(Random paramRandom) {
/* 118 */		 int i = paramRandom.nextInt(4);
/* 119 */		 if (i == 0) return "Skeleton";
/* 120 */		 if (i == 1) return "Zombie";
/* 121 */		 if (i == 2) return "Zombie";
/* 122 */		 if (i == 3) return "Spider";
/* 123 */		 return "";
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenDungeons
 * JD-Core Version:		0.6.0
 */