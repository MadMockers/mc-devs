/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ 
/*		 */ public class WorldGenTrees extends WorldGenerator
/*		 */	 implements BlockSapling.TreeGenerator
/*		 */ {
/*		 */	 private final int a;
/*		 */	 private final boolean b;
/*		 */	 private final int c;
/*		 */	 private final int d;
/*		 */ 
/*		 */	 public WorldGenTrees(boolean flag)
/*		 */	 {
/*	15 */		 this(flag, 4, 0, 0, false);
/*		 */	 }
/*		 */ 
/*		 */	 public WorldGenTrees(boolean flag, int i, int j, int k, boolean flag1) {
/*	19 */		 super(flag);
/*	20 */		 this.a = i;
/*	21 */		 this.c = j;
/*	22 */		 this.d = k;
/*	23 */		 this.b = flag1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World world, Random random, int i, int j, int k)
/*		 */	 {
/*	28 */		 return generate((BlockChangeDelegate)world, random, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
/*		 */	 {
/*	33 */		 int l = random.nextInt(3) + this.a;
/*	34 */		 boolean flag = true;
/*		 */ 
/*	36 */		 if ((j >= 1) && (j + l + 1 <= 256))
/*		 */		 {
/*	42 */			 for (int i1 = j; i1 <= j + 1 + l; i1++) {
/*	43 */				 byte b0 = 1;
/*	44 */				 if (i1 == j) {
/*	45 */					 b0 = 0;
/*		 */				 }
/*		 */ 
/*	48 */				 if (i1 >= j + 1 + l - 2) {
/*	49 */					 b0 = 2;
/*		 */				 }
/*		 */ 
/*	52 */				 for (int l1 = i - b0; (l1 <= i + b0) && (flag); l1++) {
/*	53 */					 for (int j1 = k - b0; (j1 <= k + b0) && (flag); j1++) {
/*	54 */						 if ((i1 >= 0) && (i1 < 256)) {
/*	55 */							 int k1 = world.getTypeId(l1, i1, j1);
/*	56 */							 if ((k1 != 0) && (k1 != Block.LEAVES.id) && (k1 != Block.GRASS.id) && (k1 != Block.DIRT.id) && (k1 != Block.LOG.id))
/*	57 */								 flag = false;
/*		 */						 }
/*		 */						 else {
/*	60 */							 flag = false;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	66 */			 if (!flag) {
/*	67 */				 return false;
/*		 */			 }
/*	69 */			 i1 = world.getTypeId(i, j - 1, k);
/*	70 */			 if (((i1 == Block.GRASS.id) || (i1 == Block.DIRT.id)) && (j < 256 - l - 1)) {
/*	71 */				 setType(world, i, j - 1, k, Block.DIRT.id);
/*	72 */				 byte b0 = 3;
/*	73 */				 byte b1 = 0;
/*		 */ 
/*	79 */				 for (int j1 = j - b0 + l; j1 <= j + l; j1++) {
/*	80 */					 int k1 = j1 - (j + l);
/*	81 */					 int i2 = b1 + 1 - k1 / 2;
/*		 */ 
/*	83 */					 for (int j2 = i - i2; j2 <= i + i2; j2++) {
/*	84 */						 int k2 = j2 - i;
/*		 */ 
/*	86 */						 for (int l2 = k - i2; l2 <= k + i2; l2++) {
/*	87 */							 int i3 = l2 - k;
/*		 */ 
/*	89 */							 if (((Math.abs(k2) != i2) || (Math.abs(i3) != i2) || ((random.nextInt(2) != 0) && (k1 != 0))) && (Block.n[world.getTypeId(j2, j1, l2)] == 0)) {
/*	90 */								 setTypeAndData(world, j2, j1, l2, Block.LEAVES.id, this.d);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	96 */				 for (j1 = 0; j1 < l; j1++) {
/*	97 */					 int k1 = world.getTypeId(i, j + j1, k);
/*	98 */					 if ((k1 == 0) || (k1 == Block.LEAVES.id)) {
/*	99 */						 setTypeAndData(world, i, j + j1, k, Block.LOG.id, this.c);
/* 100 */						 if ((this.b) && (j1 > 0)) {
/* 101 */							 if ((random.nextInt(3) > 0) && (world.isEmpty(i - 1, j + j1, k))) {
/* 102 */								 setTypeAndData(world, i - 1, j + j1, k, Block.VINE.id, 8);
/*		 */							 }
/*		 */ 
/* 105 */							 if ((random.nextInt(3) > 0) && (world.isEmpty(i + 1, j + j1, k))) {
/* 106 */								 setTypeAndData(world, i + 1, j + j1, k, Block.VINE.id, 2);
/*		 */							 }
/*		 */ 
/* 109 */							 if ((random.nextInt(3) > 0) && (world.isEmpty(i, j + j1, k - 1))) {
/* 110 */								 setTypeAndData(world, i, j + j1, k - 1, Block.VINE.id, 1);
/*		 */							 }
/*		 */ 
/* 113 */							 if ((random.nextInt(3) > 0) && (world.isEmpty(i, j + j1, k + 1))) {
/* 114 */								 setTypeAndData(world, i, j + j1, k + 1, Block.VINE.id, 4);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 120 */				 if (this.b) {
/* 121 */					 for (j1 = j - 3 + l; j1 <= j + l; j1++) {
/* 122 */						 int k1 = j1 - (j + l);
/* 123 */						 int i2 = 2 - k1 / 2;
/*		 */ 
/* 125 */						 for (int j2 = i - i2; j2 <= i + i2; j2++) {
/* 126 */							 for (int k2 = k - i2; k2 <= k + i2; k2++) {
/* 127 */								 if (world.getTypeId(j2, j1, k2) == Block.LEAVES.id) {
/* 128 */									 if ((random.nextInt(4) == 0) && (world.getTypeId(j2 - 1, j1, k2) == 0)) {
/* 129 */										 b(world, j2 - 1, j1, k2, 8);
/*		 */									 }
/*		 */ 
/* 132 */									 if ((random.nextInt(4) == 0) && (world.getTypeId(j2 + 1, j1, k2) == 0)) {
/* 133 */										 b(world, j2 + 1, j1, k2, 2);
/*		 */									 }
/*		 */ 
/* 136 */									 if ((random.nextInt(4) == 0) && (world.getTypeId(j2, j1, k2 - 1) == 0)) {
/* 137 */										 b(world, j2, j1, k2 - 1, 1);
/*		 */									 }
/*		 */ 
/* 140 */									 if ((random.nextInt(4) == 0) && (world.getTypeId(j2, j1, k2 + 1) == 0)) {
/* 141 */										 b(world, j2, j1, k2 + 1, 4);
/*		 */									 }
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 148 */					 if ((random.nextInt(5) == 0) && (l > 5)) {
/* 149 */						 for (j1 = 0; j1 < 2; j1++) {
/* 150 */							 for (int k1 = 0; k1 < 4; k1++) {
/* 151 */								 if (random.nextInt(4 - j1) == 0) {
/* 152 */									 int i2 = random.nextInt(3);
/* 153 */									 setTypeAndData(world, i + Direction.a[Direction.e[k1]], j + l - 5 + j1, k + Direction.b[Direction.e[k1]], Block.COCOA.id, i2 << 2 | k1);
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 160 */				 return true;
/*		 */			 }
/* 162 */			 return false;
/*		 */		 }
/*		 */ 
/* 166 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private void b(BlockChangeDelegate world, int i, int j, int k, int l)
/*		 */	 {
/* 172 */		 setTypeAndData(world, i, j, k, Block.VINE.id, l);
/* 173 */		 int i1 = 4;
/*		 */		 while (true)
/*		 */		 {
/* 176 */			 j--;
/* 177 */			 if ((world.getTypeId(i, j, k) != 0) || (i1 <= 0)) {
/* 178 */				 return;
/*		 */			 }
/*		 */ 
/* 181 */			 setTypeAndData(world, i, j, k, Block.VINE.id, l);
/* 182 */			 i1--;
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenTrees
 * JD-Core Version:		0.6.0
 */