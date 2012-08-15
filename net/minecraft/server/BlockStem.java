/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ 
/*		 */ public class BlockStem extends BlockFlower
/*		 */ {
/*		 */	 private Block blockFruit;
/*		 */ 
/*		 */	 protected BlockStem(int i, Block block)
/*		 */	 {
/*	12 */		 super(i, 111);
/*	13 */		 this.blockFruit = block;
/*	14 */		 b(true);
/*	15 */		 float f = 0.125F;
/*		 */ 
/*	17 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
/*	18 */		 a((CreativeModeTab)null);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean d_(int i) {
/*	22 */		 return i == Block.SOIL.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	26 */		 super.b(world, i, j, k, random);
/*	27 */		 if (world.getLightLevel(i, j + 1, k) >= 9) {
/*	28 */			 float f = n(world, i, j, k);
/*		 */ 
/*	30 */			 if (random.nextInt((int)(25.0F / f) + 1) == 0) {
/*	31 */				 int l = world.getData(i, j, k);
/*		 */ 
/*	33 */				 if (l < 7) {
/*	34 */					 l++;
/*	35 */					 CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this.id, l);
/*		 */				 } else {
/*	37 */					 if (world.getTypeId(i - 1, j, k) == this.blockFruit.id) {
/*	38 */						 return;
/*		 */					 }
/*		 */ 
/*	41 */					 if (world.getTypeId(i + 1, j, k) == this.blockFruit.id) {
/*	42 */						 return;
/*		 */					 }
/*		 */ 
/*	45 */					 if (world.getTypeId(i, j, k - 1) == this.blockFruit.id) {
/*	46 */						 return;
/*		 */					 }
/*		 */ 
/*	49 */					 if (world.getTypeId(i, j, k + 1) == this.blockFruit.id) {
/*	50 */						 return;
/*		 */					 }
/*		 */ 
/*	53 */					 int i1 = random.nextInt(4);
/*	54 */					 int j1 = i;
/*	55 */					 int k1 = k;
/*		 */ 
/*	57 */					 if (i1 == 0) {
/*	58 */						 j1 = i - 1;
/*		 */					 }
/*		 */ 
/*	61 */					 if (i1 == 1) {
/*	62 */						 j1++;
/*		 */					 }
/*		 */ 
/*	65 */					 if (i1 == 2) {
/*	66 */						 k1 = k - 1;
/*		 */					 }
/*		 */ 
/*	69 */					 if (i1 == 3) {
/*	70 */						 k1++;
/*		 */					 }
/*		 */ 
/*	73 */					 int l1 = world.getTypeId(j1, j - 1, k1);
/*		 */ 
/*	75 */					 if ((world.getTypeId(j1, j, k1) == 0) && ((l1 == Block.SOIL.id) || (l1 == Block.DIRT.id) || (l1 == Block.GRASS.id)))
/*	76 */						 CraftEventFactory.handleBlockGrowEvent(world, j1, j, k1, this.blockFruit.id, 0);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void l(World world, int i, int j, int k)
/*		 */	 {
/*	84 */		 world.setData(i, j, k, 7);
/*		 */	 }
/*		 */ 
/*		 */	 private float n(World world, int i, int j, int k) {
/*	88 */		 float f = 1.0F;
/*	89 */		 int l = world.getTypeId(i, j, k - 1);
/*	90 */		 int i1 = world.getTypeId(i, j, k + 1);
/*	91 */		 int j1 = world.getTypeId(i - 1, j, k);
/*	92 */		 int k1 = world.getTypeId(i + 1, j, k);
/*	93 */		 int l1 = world.getTypeId(i - 1, j, k - 1);
/*	94 */		 int i2 = world.getTypeId(i + 1, j, k - 1);
/*	95 */		 int j2 = world.getTypeId(i + 1, j, k + 1);
/*	96 */		 int k2 = world.getTypeId(i - 1, j, k + 1);
/*	97 */		 boolean flag = (j1 == this.id) || (k1 == this.id);
/*	98 */		 boolean flag1 = (l == this.id) || (i1 == this.id);
/*	99 */		 boolean flag2 = (l1 == this.id) || (i2 == this.id) || (j2 == this.id) || (k2 == this.id);
/*		 */ 
/* 101 */		 for (int l2 = i - 1; l2 <= i + 1; l2++) {
/* 102 */			 for (int i3 = k - 1; i3 <= k + 1; i3++) {
/* 103 */				 int j3 = world.getTypeId(l2, j - 1, i3);
/* 104 */				 float f1 = 0.0F;
/*		 */ 
/* 106 */				 if (j3 == Block.SOIL.id) {
/* 107 */					 f1 = 1.0F;
/* 108 */					 if (world.getData(l2, j - 1, i3) > 0) {
/* 109 */						 f1 = 3.0F;
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 113 */				 if ((l2 != i) || (i3 != k)) {
/* 114 */					 f1 /= 4.0F;
/*		 */				 }
/*		 */ 
/* 117 */				 f += f1;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 121 */		 if ((flag2) || ((flag) && (flag1))) {
/* 122 */			 f /= 2.0F;
/*		 */		 }
/*		 */ 
/* 125 */		 return f;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/* 129 */		 return this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/* 133 */		 float f = 0.125F;
/*		 */ 
/* 135 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/* 139 */		 this.maxY = ((iblockaccess.getData(i, j, k) * 2 + 2) / 16.0F);
/* 140 */		 float f = 0.125F;
/*		 */ 
/* 142 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.maxY, 0.5F + f);
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/* 146 */		 return 19;
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
/* 150 */		 super.dropNaturally(world, i, j, k, l, f, i1);
/* 151 */		 if (!world.isStatic) {
/* 152 */			 Item item = null;
/*		 */ 
/* 154 */			 if (this.blockFruit == Block.PUMPKIN) {
/* 155 */				 item = Item.PUMPKIN_SEEDS;
/*		 */			 }
/*		 */ 
/* 158 */			 if (this.blockFruit == Block.MELON) {
/* 159 */				 item = Item.MELON_SEEDS;
/*		 */			 }
/*		 */ 
/* 162 */			 for (int j1 = 0; j1 < 3; j1++)
/* 163 */				 if (world.random.nextInt(15) <= l) {
/* 164 */					 float f1 = 0.7F;
/* 165 */					 float f2 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
/* 166 */					 float f3 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
/* 167 */					 float f4 = world.random.nextFloat() * f1 + (1.0F - f1) * 0.5F;
/* 168 */					 EntityItem entityitem = new EntityItem(world, i + f2, j + f3, k + f4, new ItemStack(item));
/*		 */ 
/* 170 */					 entityitem.pickupDelay = 10;
/* 171 */					 world.addEntity(entityitem);
/*		 */				 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j)
/*		 */	 {
/* 178 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/* 182 */		 return 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockStem
 * JD-Core Version:		0.6.0
 */