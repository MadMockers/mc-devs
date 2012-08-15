/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.world.StructureGrowEvent;
/*		 */ import org.bukkit.material.MaterialData;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class WorldGenHugeMushroom extends WorldGenerator
/*		 */	 implements BlockSapling.TreeGenerator
/*		 */ {
/*	13 */	 private int a = -1;
/*		 */ 
/*		 */	 public WorldGenHugeMushroom(int i) {
/*	16 */		 super(true);
/*	17 */		 this.a = i;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldGenHugeMushroom() {
/*	21 */		 super(false);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World world, Random random, int i, int j, int k)
/*		 */	 {
/*	26 */		 return grow((BlockChangeDelegate)world, random, i, j, k, null, null, null);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean generate(BlockChangeDelegate world, Random random, int i, int j, int k) {
/*	30 */		 return grow(world, random, i, j, k, null, null, null);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean grow(BlockChangeDelegate world, Random random, int i, int j, int k, StructureGrowEvent event, ItemStack itemstack, CraftWorld bukkitWorld)
/*		 */	 {
/*	35 */		 int l = random.nextInt(2);
/*		 */ 
/*	37 */		 if (this.a >= 0) {
/*	38 */			 l = this.a;
/*		 */		 }
/*		 */ 
/*	41 */		 int i1 = random.nextInt(3) + 4;
/*	42 */		 boolean flag = true;
/*		 */ 
/*	44 */		 if ((j >= 1) && (j + i1 + 1 < 256))
/*		 */		 {
/*	50 */			 for (int j1 = j; j1 <= j + 1 + i1; j1++) {
/*	51 */				 byte b0 = 3;
/*		 */ 
/*	53 */				 if (j1 <= j + 3) {
/*	54 */					 b0 = 0;
/*		 */				 }
/*		 */ 
/*	57 */				 for (int k1 = i - b0; (k1 <= i + b0) && (flag); k1++) {
/*	58 */					 for (int l1 = k - b0; (l1 <= k + b0) && (flag); l1++) {
/*	59 */						 if ((j1 >= 0) && (j1 < 256)) {
/*	60 */							 int i2 = world.getTypeId(k1, j1, l1);
/*	61 */							 if ((i2 != 0) && (i2 != Block.LEAVES.id))
/*	62 */								 flag = false;
/*		 */						 }
/*		 */						 else {
/*	65 */							 flag = false;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	71 */			 if (!flag) {
/*	72 */				 return false;
/*		 */			 }
/*	74 */			 j1 = world.getTypeId(i, j - 1, k);
/*	75 */			 if ((j1 != Block.DIRT.id) && (j1 != Block.GRASS.id) && (j1 != Block.MYCEL.id)) {
/*	76 */				 return false;
/*		 */			 }
/*		 */ 
/*	79 */			 if (event == null) {
/*	80 */				 setTypeAndData(world, i, j - 1, k, Block.DIRT.id, 0);
/*		 */			 } else {
/*	82 */				 BlockState dirtState = bukkitWorld.getBlockAt(i, j - 1, k).getState();
/*	83 */				 dirtState.setTypeId(Block.DIRT.id);
/*	84 */				 event.getBlocks().add(dirtState);
/*		 */			 }
/*		 */ 
/*	87 */			 int j2 = j + i1;
/*		 */ 
/*	89 */			 if (l == 1) {
/*	90 */				 j2 = j + i1 - 3;
/*		 */			 }
/*		 */ 
/*	93 */			 for (int k1 = j2; k1 <= j + i1; k1++) {
/*	94 */				 int l1 = 1;
/*	95 */				 if (k1 < j + i1) {
/*	96 */					 l1++;
/*		 */				 }
/*		 */ 
/*	99 */				 if (l == 0) {
/* 100 */					 l1 = 3;
/*		 */				 }
/*		 */ 
/* 103 */				 for (int i2 = i - l1; i2 <= i + l1; i2++) {
/* 104 */					 for (int k2 = k - l1; k2 <= k + l1; k2++) {
/* 105 */						 int l2 = 5;
/*		 */ 
/* 107 */						 if (i2 == i - l1) {
/* 108 */							 l2--;
/*		 */						 }
/*		 */ 
/* 111 */						 if (i2 == i + l1) {
/* 112 */							 l2++;
/*		 */						 }
/*		 */ 
/* 115 */						 if (k2 == k - l1) {
/* 116 */							 l2 -= 3;
/*		 */						 }
/*		 */ 
/* 119 */						 if (k2 == k + l1) {
/* 120 */							 l2 += 3;
/*		 */						 }
/*		 */ 
/* 123 */						 if ((l == 0) || (k1 < j + i1)) {
/* 124 */							 if (((i2 == i - l1) || (i2 == i + l1)) && ((k2 == k - l1) || (k2 == k + l1)))
/*		 */							 {
/*		 */								 continue;
/*		 */							 }
/* 128 */							 if ((i2 == i - (l1 - 1)) && (k2 == k - l1)) {
/* 129 */								 l2 = 1;
/*		 */							 }
/*		 */ 
/* 132 */							 if ((i2 == i - l1) && (k2 == k - (l1 - 1))) {
/* 133 */								 l2 = 1;
/*		 */							 }
/*		 */ 
/* 136 */							 if ((i2 == i + (l1 - 1)) && (k2 == k - l1)) {
/* 137 */								 l2 = 3;
/*		 */							 }
/*		 */ 
/* 140 */							 if ((i2 == i + l1) && (k2 == k - (l1 - 1))) {
/* 141 */								 l2 = 3;
/*		 */							 }
/*		 */ 
/* 144 */							 if ((i2 == i - (l1 - 1)) && (k2 == k + l1)) {
/* 145 */								 l2 = 7;
/*		 */							 }
/*		 */ 
/* 148 */							 if ((i2 == i - l1) && (k2 == k + (l1 - 1))) {
/* 149 */								 l2 = 7;
/*		 */							 }
/*		 */ 
/* 152 */							 if ((i2 == i + (l1 - 1)) && (k2 == k + l1)) {
/* 153 */								 l2 = 9;
/*		 */							 }
/*		 */ 
/* 156 */							 if ((i2 == i + l1) && (k2 == k + (l1 - 1))) {
/* 157 */								 l2 = 9;
/*		 */							 }
/*		 */						 }
/*		 */ 
/* 161 */						 if ((l2 == 5) && (k1 < j + i1)) {
/* 162 */							 l2 = 0;
/*		 */						 }
/*		 */ 
/* 165 */						 if (((l2 == 0) && (j < j + i1 - 1)) || (Block.n[world.getTypeId(i2, k1, k2)] != 0))
/*		 */							 continue;
/* 167 */						 if (event == null) {
/* 168 */							 setTypeAndData(world, i2, k1, k2, Block.BIG_MUSHROOM_1.id + l, l2);
/*		 */						 } else {
/* 170 */							 BlockState state = bukkitWorld.getBlockAt(i2, k1, k2).getState();
/* 171 */							 state.setTypeId(Block.BIG_MUSHROOM_1.id + l);
/* 172 */							 state.setData(new MaterialData(Block.BIG_MUSHROOM_1.id + l, (byte)l2));
/* 173 */							 event.getBlocks().add(state);
/*		 */						 }
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 181 */			 for (k1 = 0; k1 < i1; k1++) {
/* 182 */				 int l1 = world.getTypeId(i, j + k1, k);
/* 183 */				 if (Block.n[l1] != 0)
/*		 */					 continue;
/* 185 */				 if (event == null) {
/* 186 */					 setTypeAndData(world, i, j + k1, k, Block.BIG_MUSHROOM_1.id + l, 10);
/*		 */				 } else {
/* 188 */					 BlockState state = bukkitWorld.getBlockAt(i, j + k1, k).getState();
/* 189 */					 state.setTypeId(Block.BIG_MUSHROOM_1.id + l);
/* 190 */					 state.setData(new MaterialData(Block.BIG_MUSHROOM_1.id + l, 10));
/* 191 */					 event.getBlocks().add(state);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 197 */			 if (event != null) {
/* 198 */				 Bukkit.getPluginManager().callEvent(event);
/* 199 */				 if (!event.isCancelled()) {
/* 200 */					 for (BlockState state : event.getBlocks()) {
/* 201 */						 state.update(true);
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 207 */			 return true;
/*		 */		 }
/*		 */ 
/* 211 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenHugeMushroom
 * JD-Core Version:		0.6.0
 */