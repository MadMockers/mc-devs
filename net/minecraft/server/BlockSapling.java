/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.TreeType;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.util.StructureGrowDelegate;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.world.StructureGrowEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockSapling extends BlockFlower
/*		 */ {
/*	14 */	 public static final String[] a = { "oak", "spruce", "birch", "jungle" };
/*		 */ 
/*		 */	 protected BlockSapling(int i, int j) {
/*	17 */		 super(i, j);
/*	18 */		 float f = 0.4F;
/*		 */ 
/*	20 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
/*	21 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	25 */		 if (!world.isStatic) {
/*	26 */			 super.b(world, i, j, k, random);
/*	27 */			 if ((world.getLightLevel(i, j + 1, k) >= 9) && (random.nextInt(7) == 0)) {
/*	28 */				 int l = world.getData(i, j, k);
/*		 */ 
/*	30 */				 if ((l & 0x8) == 0)
/*	31 */					 world.setData(i, j, k, l | 0x8);
/*		 */				 else
/*	33 */					 grow(world, i, j, k, random, false, null, null);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j)
/*		 */	 {
/*	40 */		 j &= 3;
/*	41 */		 return j == 3 ? 30 : j == 2 ? 79 : j == 1 ? 63 : super.a(i, j);
/*		 */	 }
/*		 */ 
/*		 */	 public void grow(World world, int i, int j, int k, Random random, boolean bonemeal, Player player, ItemStack itemstack)
/*		 */	 {
/*	46 */		 int l = world.getData(i, j, k) & 0x3;
/*	47 */		 int i1 = 0;
/*	48 */		 int j1 = 0;
/*	49 */		 boolean flag = false;
/*		 */ 
/*	51 */		 StructureGrowDelegate delegate = new StructureGrowDelegate(world);
/*	52 */		 TreeType treeType = null;
/*	53 */		 TreeGenerator gen = null;
/*	54 */		 boolean grownTree = false;
/*		 */ 
/*	56 */		 if (l == 1) {
/*	57 */			 treeType = TreeType.REDWOOD;
/*	58 */			 gen = new WorldGenTaiga2(false);
/*	59 */		 } else if (l == 2) {
/*	60 */			 treeType = TreeType.BIRCH;
/*	61 */			 gen = new WorldGenForest(false);
/*	62 */		 } else if (l == 3) {
/*	63 */			 for (i1 = 0; i1 >= -1; i1--) {
/*	64 */				 for (j1 = 0; j1 >= -1; j1--) {
/*	65 */					 if ((e(world, i + i1, j, k + j1, 3)) && (e(world, i + i1 + 1, j, k + j1, 3)) && (e(world, i + i1, j, k + j1 + 1, 3)) && (e(world, i + i1 + 1, j, k + j1 + 1, 3))) {
/*	66 */						 treeType = TreeType.JUNGLE;
/*	67 */						 gen = new WorldGenMegaTree(false, 10 + random.nextInt(20), 3, 3);
/*	68 */						 flag = true;
/*	69 */						 break;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	73 */				 if (gen != null)
/*		 */				 {
/*		 */					 break;
/*		 */				 }
/*		 */			 }
/*	78 */			 if (gen == null) {
/*	79 */				 j1 = 0;
/*	80 */				 i1 = 0;
/*	81 */				 treeType = TreeType.SMALL_JUNGLE;
/*	82 */				 gen = new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, false);
/*		 */			 }
/*		 */		 } else {
/*	85 */			 treeType = TreeType.TREE;
/*	86 */			 gen = new WorldGenTrees(false);
/*	87 */			 if (random.nextInt(10) == 0) {
/*	88 */				 treeType = TreeType.BIG_TREE;
/*	89 */				 gen = new WorldGenBigTree(false);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	93 */		 if (flag) {
/*	94 */			 world.setRawTypeId(i + i1, j, k + j1, 0);
/*	95 */			 world.setRawTypeId(i + i1 + 1, j, k + j1, 0);
/*	96 */			 world.setRawTypeId(i + i1, j, k + j1 + 1, 0);
/*	97 */			 world.setRawTypeId(i + i1 + 1, j, k + j1 + 1, 0);
/*		 */		 } else {
/*	99 */			 world.setRawTypeId(i, j, k, 0);
/*		 */		 }
/*		 */ 
/* 102 */		 grownTree = gen.generate(delegate, random, i + i1, j, k + j1);
/* 103 */		 if (grownTree) {
/* 104 */			 Location location = new Location(world.getWorld(), i, j, k);
/* 105 */			 StructureGrowEvent event = new StructureGrowEvent(location, treeType, bonemeal, player, delegate.getBlocks());
/* 106 */			 Bukkit.getPluginManager().callEvent(event);
/* 107 */			 if (event.isCancelled()) {
/* 108 */				 grownTree = false;
/*		 */			 } else {
/* 110 */				 for (BlockState state : event.getBlocks()) {
/* 111 */					 state.update(true);
/*		 */				 }
/* 113 */				 if ((event.isFromBonemeal()) && (itemstack != null)) {
/* 114 */					 itemstack.count -= 1;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 118 */		 if (!grownTree)
/* 119 */			 if (flag) {
/* 120 */				 world.setRawTypeIdAndData(i + i1, j, k + j1, this.id, l);
/* 121 */				 world.setRawTypeIdAndData(i + i1 + 1, j, k + j1, this.id, l);
/* 122 */				 world.setRawTypeIdAndData(i + i1, j, k + j1 + 1, this.id, l);
/* 123 */				 world.setRawTypeIdAndData(i + i1 + 1, j, k + j1 + 1, this.id, l);
/*		 */			 } else {
/* 125 */				 world.setRawTypeIdAndData(i, j, k, this.id, l);
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e(World world, int i, int j, int k, int l)
/*		 */	 {
/* 132 */		 return (world.getTypeId(i, j, k) == this.id) && ((world.getData(i, j, k) & 0x3) == l);
/*		 */	 }
/*		 */ 
/*		 */	 protected int getDropData(int i) {
/* 136 */		 return i & 0x3;
/*		 */	 }
/*		 */ 
/*		 */	 public static abstract interface TreeGenerator
/*		 */	 {
/*		 */		 public abstract boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3);
/*		 */ 
/*		 */		 public abstract boolean generate(BlockChangeDelegate paramBlockChangeDelegate, Random paramRandom, int paramInt1, int paramInt2, int paramInt3);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockSapling
 * JD-Core Version:		0.6.0
 */