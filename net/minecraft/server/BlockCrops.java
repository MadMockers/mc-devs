/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ 
/*		 */ public class BlockCrops extends BlockFlower
/*		 */ {
/*		 */	 protected BlockCrops(int i, int j)
/*		 */	 {
/*	 8 */		 super(i, j);
/*	 9 */		 this.textureId = j;
/*	10 */		 b(true);
/*	11 */		 float f = 0.5F;
/*		 */ 
/*	13 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
/*	14 */		 a((CreativeModeTab)null);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean d_(int i) {
/*	18 */		 return i == Block.SOIL.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	22 */		 super.b(world, i, j, k, random);
/*	23 */		 if (world.getLightLevel(i, j + 1, k) >= 9) {
/*	24 */			 int l = world.getData(i, j, k);
/*		 */ 
/*	26 */			 if (l < 7) {
/*	27 */				 float f = l(world, i, j, k);
/*		 */ 
/*	29 */				 if (random.nextInt((int)(25.0F / f) + 1) == 0) {
/*	30 */					 l++; CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this.id, l);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void c_(World world, int i, int j, int k) {
/*	37 */		 world.setData(i, j, k, 7);
/*		 */	 }
/*		 */ 
/*		 */	 private float l(World world, int i, int j, int k) {
/*	41 */		 float f = 1.0F;
/*	42 */		 int l = world.getTypeId(i, j, k - 1);
/*	43 */		 int i1 = world.getTypeId(i, j, k + 1);
/*	44 */		 int j1 = world.getTypeId(i - 1, j, k);
/*	45 */		 int k1 = world.getTypeId(i + 1, j, k);
/*	46 */		 int l1 = world.getTypeId(i - 1, j, k - 1);
/*	47 */		 int i2 = world.getTypeId(i + 1, j, k - 1);
/*	48 */		 int j2 = world.getTypeId(i + 1, j, k + 1);
/*	49 */		 int k2 = world.getTypeId(i - 1, j, k + 1);
/*	50 */		 boolean flag = (j1 == this.id) || (k1 == this.id);
/*	51 */		 boolean flag1 = (l == this.id) || (i1 == this.id);
/*	52 */		 boolean flag2 = (l1 == this.id) || (i2 == this.id) || (j2 == this.id) || (k2 == this.id);
/*		 */ 
/*	54 */		 for (int l2 = i - 1; l2 <= i + 1; l2++) {
/*	55 */			 for (int i3 = k - 1; i3 <= k + 1; i3++) {
/*	56 */				 int j3 = world.getTypeId(l2, j - 1, i3);
/*	57 */				 float f1 = 0.0F;
/*		 */ 
/*	59 */				 if (j3 == Block.SOIL.id) {
/*	60 */					 f1 = 1.0F;
/*	61 */					 if (world.getData(l2, j - 1, i3) > 0) {
/*	62 */						 f1 = 3.0F;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	66 */				 if ((l2 != i) || (i3 != k)) {
/*	67 */					 f1 /= 4.0F;
/*		 */				 }
/*		 */ 
/*	70 */				 f += f1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	74 */		 if ((flag2) || ((flag) && (flag1))) {
/*	75 */			 f /= 2.0F;
/*		 */		 }
/*		 */ 
/*	78 */		 return f;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	82 */		 if (j < 0) {
/*	83 */			 j = 7;
/*		 */		 }
/*		 */ 
/*	86 */		 return this.textureId + j;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	90 */		 return 6;
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
/*	94 */		 super.dropNaturally(world, i, j, k, l, f, 0);
/*	95 */		 if (!world.isStatic) {
/*	96 */			 int j1 = 3 + i1;
/*		 */ 
/*	98 */			 for (int k1 = 0; k1 < j1; k1++)
/*	99 */				 if (world.random.nextInt(15) <= l) {
/* 100 */					 float f1 = 0.7F;
/* 101 */					 float f2 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
/* 102 */					 float f3 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
/* 103 */					 float f4 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
/* 104 */					 EntityItem entityitem = new EntityItem(world, i + f2, j + f3, k + f4, new ItemStack(Item.SEEDS));
/*		 */ 
/* 106 */					 entityitem.pickupDelay = 10;
/* 107 */					 world.addEntity(entityitem);
/*		 */				 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j)
/*		 */	 {
/* 114 */		 return i == 7 ? Item.WHEAT.id : -1;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/* 118 */		 return 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockCrops
 * JD-Core Version:		0.6.0
 */