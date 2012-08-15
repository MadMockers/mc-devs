/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ 
/*		 */ public class WorldGenMegaTree extends WorldGenerator
/*		 */	 implements BlockSapling.TreeGenerator
/*		 */ {
/*		 */	 private final int a;
/*		 */	 private final int b;
/*		 */	 private final int c;
/*		 */ 
/*		 */	 public WorldGenMegaTree(boolean flag, int i, int j, int k)
/*		 */	 {
/*	14 */		 super(flag);
/*	15 */		 this.a = i;
/*	16 */		 this.b = j;
/*	17 */		 this.c = k;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World world, Random random, int i, int j, int k)
/*		 */	 {
/*	22 */		 return generate((BlockChangeDelegate)world, random, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k)
/*		 */	 {
/*	27 */		 int l = random.nextInt(3) + this.a;
/*	28 */		 boolean flag = true;
/*		 */ 
/*	30 */		 if ((j >= 1) && (j + l + 1 <= 256))
/*		 */		 {
/*	36 */			 for (int i1 = j; i1 <= j + 1 + l; i1++) {
/*	37 */				 byte b0 = 2;
/*		 */ 
/*	39 */				 if (i1 == j) {
/*	40 */					 b0 = 1;
/*		 */				 }
/*		 */ 
/*	43 */				 if (i1 >= j + 1 + l - 2) {
/*	44 */					 b0 = 2;
/*		 */				 }
/*		 */ 
/*	47 */				 for (int j1 = i - b0; (j1 <= i + b0) && (flag); j1++) {
/*	48 */					 for (int k1 = k - b0; (k1 <= k + b0) && (flag); k1++) {
/*	49 */						 if ((i1 >= 0) && (i1 < 256)) {
/*	50 */							 int l1 = world.getTypeId(j1, i1, k1);
/*	51 */							 if ((l1 != 0) && (l1 != Block.LEAVES.id) && (l1 != Block.GRASS.id) && (l1 != Block.DIRT.id) && (l1 != Block.LOG.id) && (l1 != Block.SAPLING.id))
/*	52 */								 flag = false;
/*		 */						 }
/*		 */						 else {
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
/*	65 */			 if (((i1 == Block.GRASS.id) || (i1 == Block.DIRT.id)) && (j < 256 - l - 1)) {
/*	66 */				 world.setRawTypeId(i, j - 1, k, Block.DIRT.id);
/*	67 */				 world.setRawTypeId(i + 1, j - 1, k, Block.DIRT.id);
/*	68 */				 world.setRawTypeId(i, j - 1, k + 1, Block.DIRT.id);
/*	69 */				 world.setRawTypeId(i + 1, j - 1, k + 1, Block.DIRT.id);
/*	70 */				 a(world, i, k, j + l, 2, random);
/*		 */ 
/*	72 */				 for (int i2 = j + l - 2 - random.nextInt(4); i2 > j + l / 2; i2 -= 2 + random.nextInt(4)) {
/*	73 */					 float f = random.nextFloat() * 3.141593F * 2.0F;
/*		 */ 
/*	75 */					 int k1 = i + (int)(0.5F + MathHelper.cos(f) * 4.0F);
/*	76 */					 int l1 = k + (int)(0.5F + MathHelper.sin(f) * 4.0F);
/*	77 */					 a(world, k1, l1, i2, 0, random);
/*		 */ 
/*	79 */					 for (int j2 = 0; j2 < 5; j2++) {
/*	80 */						 k1 = i + (int)(1.5F + MathHelper.cos(f) * j2);
/*	81 */						 l1 = k + (int)(1.5F + MathHelper.sin(f) * j2);
/*	82 */						 setTypeAndData(world, k1, i2 - 3 + j2 / 2, l1, Block.LOG.id, this.b);
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	86 */				 for (int j1 = 0; j1 < l; j1++) {
/*	87 */					 int k1 = world.getTypeId(i, j + j1, k);
/*	88 */					 if ((k1 == 0) || (k1 == Block.LEAVES.id)) {
/*	89 */						 setTypeAndData(world, i, j + j1, k, Block.LOG.id, this.b);
/*	90 */						 if (j1 > 0) {
/*	91 */							 if ((random.nextInt(3) > 0) && (world.isEmpty(i - 1, j + j1, k))) {
/*	92 */								 setTypeAndData(world, i - 1, j + j1, k, Block.VINE.id, 8);
/*		 */							 }
/*		 */ 
/*	95 */							 if ((random.nextInt(3) > 0) && (world.isEmpty(i, j + j1, k - 1))) {
/*	96 */								 setTypeAndData(world, i, j + j1, k - 1, Block.VINE.id, 1);
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 101 */					 if (j1 < l - 1) {
/* 102 */						 k1 = world.getTypeId(i + 1, j + j1, k);
/* 103 */						 if ((k1 == 0) || (k1 == Block.LEAVES.id)) {
/* 104 */							 setTypeAndData(world, i + 1, j + j1, k, Block.LOG.id, this.b);
/* 105 */							 if (j1 > 0) {
/* 106 */								 if ((random.nextInt(3) > 0) && (world.isEmpty(i + 2, j + j1, k))) {
/* 107 */									 setTypeAndData(world, i + 2, j + j1, k, Block.VINE.id, 2);
/*		 */								 }
/*		 */ 
/* 110 */								 if ((random.nextInt(3) > 0) && (world.isEmpty(i + 1, j + j1, k - 1))) {
/* 111 */									 setTypeAndData(world, i + 1, j + j1, k - 1, Block.VINE.id, 1);
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */ 
/* 116 */						 k1 = world.getTypeId(i + 1, j + j1, k + 1);
/* 117 */						 if ((k1 == 0) || (k1 == Block.LEAVES.id)) {
/* 118 */							 setTypeAndData(world, i + 1, j + j1, k + 1, Block.LOG.id, this.b);
/* 119 */							 if (j1 > 0) {
/* 120 */								 if ((random.nextInt(3) > 0) && (world.isEmpty(i + 2, j + j1, k + 1))) {
/* 121 */									 setTypeAndData(world, i + 2, j + j1, k + 1, Block.VINE.id, 2);
/*		 */								 }
/*		 */ 
/* 124 */								 if ((random.nextInt(3) > 0) && (world.isEmpty(i + 1, j + j1, k + 2))) {
/* 125 */									 setTypeAndData(world, i + 1, j + j1, k + 2, Block.VINE.id, 4);
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */ 
/* 130 */						 k1 = world.getTypeId(i, j + j1, k + 1);
/* 131 */						 if ((k1 == 0) || (k1 == Block.LEAVES.id)) {
/* 132 */							 setTypeAndData(world, i, j + j1, k + 1, Block.LOG.id, this.b);
/* 133 */							 if (j1 > 0) {
/* 134 */								 if ((random.nextInt(3) > 0) && (world.isEmpty(i - 1, j + j1, k + 1))) {
/* 135 */									 setTypeAndData(world, i - 1, j + j1, k + 1, Block.VINE.id, 8);
/*		 */								 }
/*		 */ 
/* 138 */								 if ((random.nextInt(3) > 0) && (world.isEmpty(i, j + j1, k + 2))) {
/* 139 */									 setTypeAndData(world, i, j + j1, k + 2, Block.VINE.id, 4);
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 146 */				 return true;
/*		 */			 }
/* 148 */			 return false;
/*		 */		 }
/*		 */ 
/* 152 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(BlockChangeDelegate world, int i, int j, int k, int l, Random random)
/*		 */	 {
/* 158 */		 byte b0 = 2;
/*		 */ 
/* 160 */		 for (int i1 = k - b0; i1 <= k; i1++) {
/* 161 */			 int j1 = i1 - k;
/* 162 */			 int k1 = l + 1 - j1;
/*		 */ 
/* 164 */			 for (int l1 = i - k1; l1 <= i + k1 + 1; l1++) {
/* 165 */				 int i2 = l1 - i;
/*		 */ 
/* 167 */				 for (int j2 = j - k1; j2 <= j + k1 + 1; j2++) {
/* 168 */					 int k2 = j2 - j;
/*		 */ 
/* 170 */					 if (((i2 >= 0) || (k2 >= 0) || (i2 * i2 + k2 * k2 <= k1 * k1)) && (((i2 <= 0) && (k2 <= 0)) || ((i2 * i2 + k2 * k2 <= (k1 + 1) * (k1 + 1)) && ((random.nextInt(4) != 0) || (i2 * i2 + k2 * k2 <= (k1 - 1) * (k1 - 1))) && (Block.n[world.getTypeId(l1, i1, j2)] == 0))))
/* 171 */						 setTypeAndData(world, l1, i1, j2, Block.LEAVES.id, this.c);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenMegaTree
 * JD-Core Version:		0.6.0
 */