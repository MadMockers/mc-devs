/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.LeavesDecayEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockLeaves extends BlockTransparant
/*		 */ {
/*		 */	 private int cr;
/*	10 */	 public static final String[] a = { "oak", "spruce", "birch", "jungle" };
/*		 */	 int[] b;
/*		 */ 
/*		 */	 protected BlockLeaves(int i, int j)
/*		 */	 {
/*	14 */		 super(i, j, Material.LEAVES, false);
/*	15 */		 this.cr = j;
/*	16 */		 b(true);
/*	17 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1) {
/*	21 */		 byte b0 = 1;
/*	22 */		 int j1 = b0 + 1;
/*		 */ 
/*	24 */		 if (world.c(i - j1, j - j1, k - j1, i + j1, j + j1, k + j1))
/*	25 */			 for (int k1 = -b0; k1 <= b0; k1++)
/*	26 */				 for (int l1 = -b0; l1 <= b0; l1++)
/*	27 */					 for (int i2 = -b0; i2 <= b0; i2++) {
/*	28 */						 int j2 = world.getTypeId(i + k1, j + l1, k + i2);
/*		 */ 
/*	30 */						 if (j2 == Block.LEAVES.id) {
/*	31 */							 int k2 = world.getData(i + k1, j + l1, k + i2);
/*		 */ 
/*	33 */							 world.setRawData(i + k1, j + l1, k + i2, k2 | 0x8);
/*		 */						 }
/*		 */					 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random)
/*		 */	 {
/*	42 */		 if (!world.isStatic) {
/*	43 */			 int l = world.getData(i, j, k);
/*		 */ 
/*	45 */			 if (((l & 0x8) != 0) && ((l & 0x4) == 0)) {
/*	46 */				 byte b0 = 4;
/*	47 */				 int i1 = b0 + 1;
/*	48 */				 byte b1 = 32;
/*	49 */				 int j1 = b1 * b1;
/*	50 */				 int k1 = b1 / 2;
/*		 */ 
/*	52 */				 if (this.b == null) {
/*	53 */					 this.b = new int[b1 * b1 * b1];
/*		 */				 }
/*		 */ 
/*	58 */				 if (world.c(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
/*		 */				 {
/*	63 */					 for (int l1 = -b0; l1 <= b0; l1++) {
/*	64 */						 for (int i2 = -b0; i2 <= b0; i2++) {
/*	65 */							 for (int j2 = -b0; j2 <= b0; j2++) {
/*	66 */								 int k2 = world.getTypeId(i + l1, j + i2, k + j2);
/*	67 */								 if (k2 == Block.LOG.id)
/*	68 */									 this.b[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = 0;
/*	69 */								 else if (k2 == Block.LEAVES.id)
/*	70 */									 this.b[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = -2;
/*		 */								 else {
/*	72 */									 this.b[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = -1;
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */ 
/*	78 */					 for (l1 = 1; l1 <= 4; l1++) {
/*	79 */						 for (int i2 = -b0; i2 <= b0; i2++) {
/*	80 */							 for (int j2 = -b0; j2 <= b0; j2++) {
/*	81 */								 for (int k2 = -b0; k2 <= b0; k2++) {
/*	82 */									 if (this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1)] == l1 - 1) {
/*	83 */										 if (this.b[((i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1)] == -2) {
/*	84 */											 this.b[((i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1)] = l1;
/*		 */										 }
/*		 */ 
/*	87 */										 if (this.b[((i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1)] == -2) {
/*	88 */											 this.b[((i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1)] = l1;
/*		 */										 }
/*		 */ 
/*	91 */										 if (this.b[((i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1)] == -2) {
/*	92 */											 this.b[((i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1)] = l1;
/*		 */										 }
/*		 */ 
/*	95 */										 if (this.b[((i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1)] == -2) {
/*	96 */											 this.b[((i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1)] = l1;
/*		 */										 }
/*		 */ 
/*	99 */										 if (this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1))] == -2) {
/* 100 */											 this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1))] = l1;
/*		 */										 }
/*		 */ 
/* 103 */										 if (this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1)] == -2) {
/* 104 */											 this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1)] = l1;
/*		 */										 }
/*		 */									 }
/*		 */								 }
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 113 */				 int l1 = this.b[(k1 * j1 + k1 * b1 + k1)];
/* 114 */				 if (l1 >= 0)
/* 115 */					 world.setRawData(i, j, k, l & 0xFFFFFFF7);
/*		 */				 else
/* 117 */					 l(world, i, j, k);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k)
/*		 */	 {
/* 125 */		 LeavesDecayEvent event = new LeavesDecayEvent(world.getWorld().getBlockAt(i, j, k));
/* 126 */		 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 128 */		 if (event.isCancelled()) {
/* 129 */			 return;
/*		 */		 }
/*		 */ 
/* 133 */		 c(world, i, j, k, world.getData(i, j, k), 0);
/* 134 */		 world.setTypeId(i, j, k, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/* 138 */		 return random.nextInt(20) == 0 ? 1 : 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j) {
/* 142 */		 return Block.SAPLING.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
/* 146 */		 if (!world.isStatic) {
/* 147 */			 byte b0 = 20;
/*		 */ 
/* 149 */			 if ((l & 0x3) == 3) {
/* 150 */				 b0 = 40;
/*		 */			 }
/*		 */ 
/* 153 */			 if (world.random.nextInt(b0) == 0) {
/* 154 */				 int j1 = getDropType(l, world.random, i1);
/*		 */ 
/* 156 */				 a(world, i, j, k, new ItemStack(j1, 1, getDropData(l)));
/*		 */			 }
/*		 */ 
/* 159 */			 if (((l & 0x3) == 0) && (world.random.nextInt(200) == 0))
/* 160 */				 a(world, i, j, k, new ItemStack(Item.APPLE, 1, 0));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
/*		 */	 {
/* 166 */		 if ((!world.isStatic) && (entityhuman.bC() != null) && (entityhuman.bC().id == Item.SHEARS.id)) {
/* 167 */			 entityhuman.a(StatisticList.C[this.id], 1);
/* 168 */			 a(world, i, j, k, new ItemStack(Block.LEAVES.id, 1, l & 0x3));
/*		 */		 } else {
/* 170 */			 super.a(world, entityhuman, i, j, k, l);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected int getDropData(int i) {
/* 175 */		 return i & 0x3;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/* 179 */		 return !this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/* 183 */		 return (j & 0x3) == 3 ? this.textureId + 144 : (j & 0x3) == 1 ? this.textureId + 80 : this.textureId;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockLeaves
 * JD-Core Version:		0.6.0
 */