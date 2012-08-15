/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.block.BlockBurnEvent;
/*		 */ import org.bukkit.event.block.BlockFadeEvent;
/*		 */ import org.bukkit.event.block.BlockIgniteEvent;
/*		 */ import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
/*		 */ import org.bukkit.event.block.BlockSpreadEvent;
/*		 */ import org.bukkit.material.MaterialData;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockFire extends Block
/*		 */ {
/*	13 */	 private int[] a = new int[256];
/*	14 */	 private int[] b = new int[256];
/*		 */ 
/*		 */	 protected BlockFire(int i, int j) {
/*	17 */		 super(i, j, Material.FIRE);
/*	18 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void r_() {
/*	22 */		 a(Block.WOOD.id, 5, 20);
/*	23 */		 a(Block.WOOD_DOUBLE_STEP.id, 5, 20);
/*	24 */		 a(Block.WOOD_STEP.id, 5, 20);
/*	25 */		 a(Block.FENCE.id, 5, 20);
/*	26 */		 a(Block.WOOD_STAIRS.id, 5, 20);
/*	27 */		 a(Block.BIRCH_WOOD_STAIRS.id, 5, 20);
/*	28 */		 a(Block.SPRUCE_WOOD_STAIRS.id, 5, 20);
/*	29 */		 a(Block.JUNGLE_WOOD_STAIRS.id, 5, 20);
/*	30 */		 a(Block.LOG.id, 5, 5);
/*	31 */		 a(Block.LEAVES.id, 30, 60);
/*	32 */		 a(Block.BOOKSHELF.id, 30, 20);
/*	33 */		 a(Block.TNT.id, 15, 100);
/*	34 */		 a(Block.LONG_GRASS.id, 60, 100);
/*	35 */		 a(Block.WOOL.id, 30, 60);
/*	36 */		 a(Block.VINE.id, 15, 100);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int i, int j, int k) {
/*	40 */		 this.a[i] = j;
/*	41 */		 this.b[i] = k;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	45 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	49 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	53 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	57 */		 return 3;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/*	61 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	65 */		 return 30;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	69 */		 boolean flag = world.getTypeId(i, j - 1, k) == Block.NETHERRACK.id;
/*		 */ 
/*	71 */		 if (((world.worldProvider instanceof WorldProviderTheEnd)) && (world.getTypeId(i, j - 1, k) == Block.BEDROCK.id)) {
/*	72 */			 flag = true;
/*		 */		 }
/*		 */ 
/*	75 */		 if (!canPlace(world, i, j, k)) {
/*	76 */			 fireExtinguished(world, i, j, k);
/*		 */		 }
/*		 */ 
/*	79 */		 if ((!flag) && (world.J()) && ((world.B(i, j, k)) || (world.B(i - 1, j, k)) || (world.B(i + 1, j, k)) || (world.B(i, j, k - 1)) || (world.B(i, j, k + 1)))) {
/*	80 */			 fireExtinguished(world, i, j, k);
/*		 */		 } else {
/*	82 */			 int l = world.getData(i, j, k);
/*		 */ 
/*	84 */			 if (l < 15) {
/*	85 */				 world.setRawData(i, j, k, l + random.nextInt(3) / 2);
/*		 */			 }
/*		 */ 
/*	88 */			 world.a(i, j, k, this.id, p_() + random.nextInt(10));
/*	89 */			 if ((!flag) && (!l(world, i, j, k))) {
/*	90 */				 if ((!world.t(i, j - 1, k)) || (l > 3))
/*	91 */					 fireExtinguished(world, i, j, k);
/*		 */			 }
/*	93 */			 else if ((!flag) && (!d(world, i, j - 1, k)) && (l == 15) && (random.nextInt(4) == 0)) {
/*	94 */				 fireExtinguished(world, i, j, k);
/*		 */			 } else {
/*	96 */				 boolean flag1 = world.C(i, j, k);
/*	97 */				 byte b0 = 0;
/*		 */ 
/*	99 */				 if (flag1) {
/* 100 */					 b0 = -50;
/*		 */				 }
/*		 */ 
/* 103 */				 a(world, i + 1, j, k, 300 + b0, random, l);
/* 104 */				 a(world, i - 1, j, k, 300 + b0, random, l);
/* 105 */				 a(world, i, j - 1, k, 250 + b0, random, l);
/* 106 */				 a(world, i, j + 1, k, 250 + b0, random, l);
/* 107 */				 a(world, i, j, k - 1, 300 + b0, random, l);
/* 108 */				 a(world, i, j, k + 1, 300 + b0, random, l);
/*		 */ 
/* 111 */				 Server server = world.getServer();
/* 112 */				 org.bukkit.World bworld = world.getWorld();
/*		 */ 
/* 114 */				 BlockIgniteEvent.IgniteCause igniteCause = BlockIgniteEvent.IgniteCause.SPREAD;
/* 115 */				 org.bukkit.block.Block fromBlock = bworld.getBlockAt(i, j, k);
/*		 */ 
/* 118 */				 for (int i1 = i - 1; i1 <= i + 1; i1++)
/* 119 */					 for (int j1 = k - 1; j1 <= k + 1; j1++)
/* 120 */						 for (int k1 = j - 1; k1 <= j + 4; k1++)
/* 121 */							 if ((i1 != i) || (k1 != j) || (j1 != k)) {
/* 122 */								 int l1 = 100;
/*		 */ 
/* 124 */								 if (k1 > j + 1) {
/* 125 */									 l1 += (k1 - (j + 1)) * 100;
/*		 */								 }
/*		 */ 
/* 128 */								 int i2 = n(world, i1, k1, j1);
/*		 */ 
/* 130 */								 if (i2 > 0) {
/* 131 */									 int j2 = (i2 + 40) / (l + 30);
/*		 */ 
/* 133 */									 if (flag1) {
/* 134 */										 j2 /= 2;
/*		 */									 }
/*		 */ 
/* 137 */									 if ((j2 > 0) && (random.nextInt(l1) <= j2) && ((!world.J()) || (!world.B(i1, k1, j1))) && (!world.B(i1 - 1, k1, k)) && (!world.B(i1 + 1, k1, j1)) && (!world.B(i1, k1, j1 - 1)) && (!world.B(i1, k1, j1 + 1))) {
/* 138 */										 int k2 = l + random.nextInt(5) / 4;
/*		 */ 
/* 140 */										 if (k2 > 15) {
/* 141 */											 k2 = 15;
/*		 */										 }
/*		 */ 
/* 145 */										 org.bukkit.block.Block block = bworld.getBlockAt(i1, k1, j1);
/*		 */ 
/* 147 */										 if (block.getTypeId() != Block.FIRE.id) {
/* 148 */											 BlockIgniteEvent event = new BlockIgniteEvent(block, igniteCause, null);
/* 149 */											 server.getPluginManager().callEvent(event);
/*		 */ 
/* 151 */											 if (event.isCancelled())
/*		 */											 {
/*		 */												 continue;
/*		 */											 }
/* 155 */											 BlockState blockState = bworld.getBlockAt(i1, k1, j1).getState();
/* 156 */											 blockState.setTypeId(this.id);
/* 157 */											 blockState.setData(new MaterialData(this.id, (byte)k2));
/*		 */ 
/* 159 */											 BlockSpreadEvent spreadEvent = new BlockSpreadEvent(blockState.getBlock(), fromBlock, blockState);
/* 160 */											 server.getPluginManager().callEvent(spreadEvent);
/*		 */ 
/* 162 */											 if (!spreadEvent.isCancelled())
/* 163 */												 blockState.update(true);
/*		 */										 }
/*		 */									 }
/*		 */								 }
/*		 */							 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(World world, int i, int j, int k, int l, Random random, int i1)
/*		 */	 {
/* 178 */		 int j1 = this.b[world.getTypeId(i, j, k)];
/*		 */ 
/* 180 */		 if (random.nextInt(l) < j1) {
/* 181 */			 boolean flag = world.getTypeId(i, j, k) == Block.TNT.id;
/*		 */ 
/* 184 */			 org.bukkit.block.Block theBlock = world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/* 186 */			 BlockBurnEvent event = new BlockBurnEvent(theBlock);
/* 187 */			 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 189 */			 if (event.isCancelled()) {
/* 190 */				 return;
/*		 */			 }
/*		 */ 
/* 194 */			 if ((random.nextInt(i1 + 10) < 5) && (!world.B(i, j, k))) {
/* 195 */				 int k1 = i1 + random.nextInt(5) / 4;
/*		 */ 
/* 197 */				 if (k1 > 15) {
/* 198 */					 k1 = 15;
/*		 */				 }
/*		 */ 
/* 201 */				 world.setTypeIdAndData(i, j, k, this.id, k1);
/*		 */			 } else {
/* 203 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */ 
/* 206 */			 if (flag)
/* 207 */				 Block.TNT.postBreak(world, i, j, k, 1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World world, int i, int j, int k)
/*		 */	 {
/* 213 */		 return d(world, i, j, k - 1) ? true : d(world, i, j + 1, k) ? true : d(world, i, j - 1, k) ? true : d(world, i - 1, j, k) ? true : d(world, i + 1, j, k) ? true : d(world, i, j, k + 1);
/*		 */	 }
/*		 */ 
/*		 */	 private int n(World world, int i, int j, int k) {
/* 217 */		 byte b0 = 0;
/*		 */ 
/* 219 */		 if (!world.isEmpty(i, j, k)) {
/* 220 */			 return 0;
/*		 */		 }
/* 222 */		 int l = e(world, i + 1, j, k, b0);
/*		 */ 
/* 224 */		 l = e(world, i - 1, j, k, l);
/* 225 */		 l = e(world, i, j - 1, k, l);
/* 226 */		 l = e(world, i, j + 1, k, l);
/* 227 */		 l = e(world, i, j, k - 1, l);
/* 228 */		 l = e(world, i, j, k + 1, l);
/* 229 */		 return l;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean l()
/*		 */	 {
/* 234 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(IBlockAccess iblockaccess, int i, int j, int k) {
/* 238 */		 return this.a[iblockaccess.getTypeId(i, j, k)] > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int e(World world, int i, int j, int k, int l) {
/* 242 */		 int i1 = this.a[world.getTypeId(i, j, k)];
/*		 */ 
/* 244 */		 return i1 > l ? i1 : l;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/* 248 */		 return (world.t(i, j - 1, k)) || (l(world, i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/* 252 */		 if ((!world.t(i, j - 1, k)) && (!l(world, i, j, k)))
/* 253 */			 fireExtinguished(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k)
/*		 */	 {
/* 258 */		 if ((world.worldProvider.dimension > 0) || (world.getTypeId(i, j - 1, k) != Block.OBSIDIAN.id) || (!Block.PORTAL.i_(world, i, j, k)))
/* 259 */			 if ((!world.t(i, j - 1, k)) && (!l(world, i, j, k)))
/* 260 */				 fireExtinguished(world, i, j, k);
/*		 */			 else
/* 262 */				 world.a(i, j, k, this.id, p_() + world.random.nextInt(10));
/*		 */	 }
/*		 */ 
/*		 */	 private void fireExtinguished(World world, int x, int y, int z)
/*		 */	 {
/* 269 */		 if (!CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(x, y, z), 0).isCancelled())
/* 270 */			 world.setTypeId(x, y, z, 0);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockFire
 * JD-Core Version:		0.6.0
 */