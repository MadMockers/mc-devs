/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ 
/*		 */ public class WorldGenSwampTree extends WorldGenerator
/*		 */	 implements BlockSapling.TreeGenerator
/*		 */ {
/*		 */	 public boolean a(World world, Random random, int i, int j, int k)
/*		 */	 {
/*	13 */		 return generate((BlockChangeDelegate)world, random, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
/*		 */	 {
/*	20 */		 for (int l = random.nextInt(4) + 5; (world.getTypeId(i, j - 1, k) != 0) && (Block.byId[world.getTypeId(i, j - 1, k)].material == Material.WATER); j--);
/*	24 */		 boolean flag = true;
/*		 */ 
/*	26 */		 if ((j >= 1) && (j + l + 1 <= 128))
/*		 */		 {
/*	32 */			 for (int i1 = j; i1 <= j + 1 + l; i1++) {
/*	33 */				 byte b0 = 1;
/*		 */ 
/*	35 */				 if (i1 == j) {
/*	36 */					 b0 = 0;
/*		 */				 }
/*		 */ 
/*	39 */				 if (i1 >= j + 1 + l - 2) {
/*	40 */					 b0 = 3;
/*		 */				 }
/*		 */ 
/*	43 */				 for (int j1 = i - b0; (j1 <= i + b0) && (flag); j1++) {
/*	44 */					 for (int k1 = k - b0; (k1 <= k + b0) && (flag); k1++) {
/*	45 */						 if ((i1 >= 0) && (i1 < 128)) {
/*	46 */							 int l1 = world.getTypeId(j1, i1, k1);
/*	47 */							 if ((l1 != 0) && (l1 != Block.LEAVES.id))
/*	48 */								 if ((l1 != Block.STATIONARY_WATER.id) && (l1 != Block.WATER.id))
/*	49 */									 flag = false;
/*	50 */								 else if (i1 > j)
/*	51 */									 flag = false;
/*		 */						 }
/*		 */						 else
/*		 */						 {
/*	55 */							 flag = false;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	61 */			 if (!flag) {
/*	62 */				 return false;
/*		 */			 }
/*	64 */			 i1 = world.getTypeId(i, j - 1, k);
/*	65 */			 if (((i1 == Block.GRASS.id) || (i1 == Block.DIRT.id)) && (j < 128 - l - 1)) {
/*	66 */				 setType(world, i, j - 1, k, Block.DIRT.id);
/*		 */ 
/*	71 */				 for (int j2 = j - 3 + l; j2 <= j + l; j2++) {
/*	72 */					 int j1 = j2 - (j + l);
/*	73 */					 int k1 = 2 - j1 / 2;
/*		 */ 
/*	75 */					 for (int l1 = i - k1; l1 <= i + k1; l1++) {
/*	76 */						 int i2 = l1 - i;
/*		 */ 
/*	78 */						 for (int k2 = k - k1; k2 <= k + k1; k2++) {
/*	79 */							 int l2 = k2 - k;
/*		 */ 
/*	81 */							 if (((Math.abs(i2) != k1) || (Math.abs(l2) != k1) || ((random.nextInt(2) != 0) && (j1 != 0))) && (Block.n[world.getTypeId(l1, j2, k2)] == 0)) {
/*	82 */								 setType(world, l1, j2, k2, Block.LEAVES.id);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	88 */				 for (j2 = 0; j2 < l; j2++) {
/*	89 */					 int j1 = world.getTypeId(i, j + j2, k);
/*	90 */					 if ((j1 == 0) || (j1 == Block.LEAVES.id) || (j1 == Block.WATER.id) || (j1 == Block.STATIONARY_WATER.id)) {
/*	91 */						 setType(world, i, j + j2, k, Block.LOG.id);
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	95 */				 for (j2 = j - 3 + l; j2 <= j + l; j2++) {
/*	96 */					 int j1 = j2 - (j + l);
/*	97 */					 int k1 = 2 - j1 / 2;
/*		 */ 
/*	99 */					 for (int l1 = i - k1; l1 <= i + k1; l1++) {
/* 100 */						 for (int i2 = k - k1; i2 <= k + k1; i2++) {
/* 101 */							 if (world.getTypeId(l1, j2, i2) == Block.LEAVES.id) {
/* 102 */								 if ((random.nextInt(4) == 0) && (world.getTypeId(l1 - 1, j2, i2) == 0)) {
/* 103 */									 b(world, l1 - 1, j2, i2, 8);
/*		 */								 }
/*		 */ 
/* 106 */								 if ((random.nextInt(4) == 0) && (world.getTypeId(l1 + 1, j2, i2) == 0)) {
/* 107 */									 b(world, l1 + 1, j2, i2, 2);
/*		 */								 }
/*		 */ 
/* 110 */								 if ((random.nextInt(4) == 0) && (world.getTypeId(l1, j2, i2 - 1) == 0)) {
/* 111 */									 b(world, l1, j2, i2 - 1, 1);
/*		 */								 }
/*		 */ 
/* 114 */								 if ((random.nextInt(4) == 0) && (world.getTypeId(l1, j2, i2 + 1) == 0)) {
/* 115 */									 b(world, l1, j2, i2 + 1, 4);
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 122 */				 return true;
/*		 */			 }
/* 124 */			 return false;
/*		 */		 }
/*		 */ 
/* 128 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private void b(BlockChangeDelegate world, int i, int j, int k, int l)
/*		 */	 {
/* 134 */		 setTypeAndData(world, i, j, k, Block.VINE.id, l);
/* 135 */		 int i1 = 4;
/*		 */		 while (true)
/*		 */		 {
/* 138 */			 j--;
/* 139 */			 if ((world.getTypeId(i, j, k) != 0) || (i1 <= 0)) {
/* 140 */				 return;
/*		 */			 }
/*		 */ 
/* 143 */			 setTypeAndData(world, i, j, k, Block.VINE.id, l);
/* 144 */			 i1--;
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenSwampTree
 * JD-Core Version:		0.6.0
 */