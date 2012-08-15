/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.TreeType;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.block.BlockSpreadEvent;
/*		 */ import org.bukkit.event.world.StructureGrowEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockMushroom extends BlockFlower
/*		 */ {
/*		 */	 protected BlockMushroom(int i, int j)
/*		 */	 {
/*	18 */		 super(i, j);
/*	19 */		 float f = 0.2F;
/*		 */ 
/*	21 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
/*	22 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	26 */		 if (random.nextInt(25) == 0) {
/*	27 */			 byte b0 = 4;
/*	28 */			 int l = 5;
/*		 */ 
/*	34 */			 for (int i1 = i - b0; i1 <= i + b0; i1++) {
/*	35 */				 for (int j1 = k - b0; j1 <= k + b0; j1++) {
/*	36 */					 for (int k1 = j - 1; k1 <= j + 1; k1++) {
/*	37 */						 if (world.getTypeId(i1, k1, j1) == this.id) {
/*	38 */							 l--;
/*	39 */							 if (l <= 0) {
/*	40 */								 return;
/*		 */							 }
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	47 */			 i1 = i + random.nextInt(3) - 1;
/*	48 */			 int j1 = j + random.nextInt(2) - random.nextInt(2);
/*	49 */			 int k1 = k + random.nextInt(3) - 1;
/*		 */ 
/*	51 */			 for (int l1 = 0; l1 < 4; l1++) {
/*	52 */				 if ((world.isEmpty(i1, j1, k1)) && (d(world, i1, j1, k1))) {
/*	53 */					 i = i1;
/*	54 */					 j = j1;
/*	55 */					 k = k1;
/*		 */				 }
/*		 */ 
/*	58 */				 i1 = i + random.nextInt(3) - 1;
/*	59 */				 j1 = j + random.nextInt(2) - random.nextInt(2);
/*	60 */				 k1 = k + random.nextInt(3) - 1;
/*		 */			 }
/*		 */ 
/*	63 */			 if ((world.isEmpty(i1, j1, k1)) && (d(world, i1, j1, k1)))
/*		 */			 {
/*	65 */				 org.bukkit.World bworld = world.getWorld();
/*	66 */				 BlockState blockState = bworld.getBlockAt(i1, j1, k1).getState();
/*	67 */				 blockState.setTypeId(this.id);
/*		 */ 
/*	69 */				 BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), bworld.getBlockAt(i, j, k), blockState);
/*	70 */				 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	72 */				 if (!event.isCancelled())
/*	73 */					 blockState.update(true);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k)
/*		 */	 {
/*	81 */		 return (super.canPlace(world, i, j, k)) && (d(world, i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean d_(int i) {
/*	85 */		 return Block.n[i];
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(World world, int i, int j, int k) {
/*	89 */		 if ((j >= 0) && (j < 256)) {
/*	90 */			 int l = world.getTypeId(i, j - 1, k);
/*		 */ 
/*	92 */			 return (l == Block.MYCEL.id) || ((world.k(i, j, k) < 13) && (d_(l)));
/*		 */		 }
/*	94 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean grow(World world, int i, int j, int k, Random random, boolean bonemeal, Player player, ItemStack itemstack)
/*		 */	 {
/* 100 */		 int l = world.getData(i, j, k);
/*		 */ 
/* 102 */		 world.setRawTypeId(i, j, k, 0);
/*		 */ 
/* 104 */		 boolean grown = false;
/* 105 */		 StructureGrowEvent event = null;
/* 106 */		 Location location = new Location(world.getWorld(), i, j, k);
/* 107 */		 WorldGenHugeMushroom worldgenhugemushroom = null;
/*		 */ 
/* 109 */		 if (this.id == Block.BROWN_MUSHROOM.id) {
/* 110 */			 event = new StructureGrowEvent(location, TreeType.BROWN_MUSHROOM, bonemeal, player, new ArrayList());
/* 111 */			 worldgenhugemushroom = new WorldGenHugeMushroom(0);
/* 112 */		 } else if (this.id == Block.RED_MUSHROOM.id) {
/* 113 */			 event = new StructureGrowEvent(location, TreeType.RED_MUSHROOM, bonemeal, player, new ArrayList());
/* 114 */			 worldgenhugemushroom = new WorldGenHugeMushroom(1);
/*		 */		 }
/*		 */ 
/* 117 */		 if ((worldgenhugemushroom != null) && (event != null)) {
/* 118 */			 grown = worldgenhugemushroom.grow((BlockChangeDelegate)world, random, i, j, k, event, itemstack, world.getWorld());
/* 119 */			 if ((event.isFromBonemeal()) && (itemstack != null)) {
/* 120 */				 itemstack.count -= 1;
/*		 */			 }
/*		 */		 }
/* 123 */		 if ((!grown) || (event.isCancelled())) {
/* 124 */			 world.setRawTypeIdAndData(i, j, k, this.id, l);
/* 125 */			 return false;
/*		 */		 }
/* 127 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockMushroom
 * JD-Core Version:		0.6.0
 */