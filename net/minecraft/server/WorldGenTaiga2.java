/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ 
/*		 */ public class WorldGenTaiga2 extends WorldGenerator
/*		 */	 implements BlockSapling.TreeGenerator
/*		 */ {
/*		 */	 public WorldGenTaiga2(boolean flag)
/*		 */	 {
/*	10 */		 super(flag);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World world, Random random, int i, int j, int k)
/*		 */	 {
/*	15 */		 return generate((BlockChangeDelegate)world, random, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
/*		 */	 {
/*	20 */		 int l = random.nextInt(4) + 6;
/*	21 */		 int i1 = 1 + random.nextInt(2);
/*	22 */		 int j1 = l - i1;
/*	23 */		 int k1 = 2 + random.nextInt(2);
/*	24 */		 boolean flag = true;
/*		 */ 
/*	26 */		 if ((j >= 1) && (j + l + 1 <= 256))
/*		 */		 {
/*	32 */			 for (int l1 = j; (l1 <= j + 1 + l) && (flag); l1++) {
/*	33 */				 boolean flag1 = true;
/*		 */				 int k2;
/*		 */				 int k2;
/*	35 */				 if (l1 - j < i1)
/*	36 */					 k2 = 0;
/*		 */				 else {
/*	38 */					 k2 = k1;
/*		 */				 }
/*		 */ 
/*	41 */				 for (int i2 = i - k2; (i2 <= i + k2) && (flag); i2++) {
/*	42 */					 for (int l2 = k - k2; (l2 <= k + k2) && (flag); l2++) {
/*	43 */						 if ((l1 >= 0) && (l1 < 256)) {
/*	44 */							 int j2 = world.getTypeId(i2, l1, l2);
/*	45 */							 if ((j2 != 0) && (j2 != Block.LEAVES.id))
/*	46 */								 flag = false;
/*		 */						 }
/*		 */						 else {
/*	49 */							 flag = false;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	55 */			 if (!flag) {
/*	56 */				 return false;
/*		 */			 }
/*	58 */			 l1 = world.getTypeId(i, j - 1, k);
/*	59 */			 if (((l1 == Block.GRASS.id) || (l1 == Block.DIRT.id)) && (j < 256 - l - 1)) {
/*	60 */				 setType(world, i, j - 1, k, Block.DIRT.id);
/*	61 */				 int k2 = random.nextInt(2);
/*	62 */				 int i2 = 1;
/*	63 */				 byte b0 = 0;
/*		 */ 
/*	68 */				 for (int j2 = 0; j2 <= j1; j2++) {
/*	69 */					 int j3 = j + l - j2;
/*		 */ 
/*	71 */					 for (int i3 = i - k2; i3 <= i + k2; i3++) {
/*	72 */						 int k3 = i3 - i;
/*		 */ 
/*	74 */						 for (int l3 = k - k2; l3 <= k + k2; l3++) {
/*	75 */							 int i4 = l3 - k;
/*		 */ 
/*	77 */							 if (((Math.abs(k3) != k2) || (Math.abs(i4) != k2) || (k2 <= 0)) && (Block.n[world.getTypeId(i3, j3, l3)] == 0)) {
/*	78 */								 setTypeAndData(world, i3, j3, l3, Block.LEAVES.id, 1);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/*	83 */					 if (k2 >= i2) {
/*	84 */						 k2 = b0;
/*	85 */						 b0 = 1;
/*	86 */						 i2++;
/*	87 */						 if (i2 > k1)
/*	88 */							 i2 = k1;
/*		 */					 }
/*		 */					 else {
/*	91 */						 k2++;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	95 */				 j2 = random.nextInt(3);
/*		 */ 
/*	97 */				 for (int j3 = 0; j3 < l - j2; j3++) {
/*	98 */					 int i3 = world.getTypeId(i, j + j3, k);
/*	99 */					 if ((i3 == 0) || (i3 == Block.LEAVES.id)) {
/* 100 */						 setTypeAndData(world, i, j + j3, k, Block.LOG.id, 1);
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 104 */				 return true;
/*		 */			 }
/* 106 */			 return false;
/*		 */		 }
/*		 */ 
/* 110 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenTaiga2
 * JD-Core Version:		0.6.0
 */