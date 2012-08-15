/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockFromToEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockDragonEgg extends Block
/*		 */ {
/*		 */	 public BlockDragonEgg(int i, int j)
/*		 */	 {
/*	10 */		 super(i, j, Material.DRAGON_EGG);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/*	14 */		 world.a(i, j, k, this.id, p_());
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	18 */		 world.a(i, j, k, this.id, p_());
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	22 */		 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k) {
/*	26 */		 if ((BlockSand.canFall(world, i, j - 1, k)) && (j >= 0)) {
/*	27 */			 byte b0 = 32;
/*		 */ 
/*	29 */			 if ((!BlockSand.instaFall) && (world.c(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)))
/*		 */			 {
/*	31 */				 EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, i + 0.5F, j + 0.5F, k + 0.5F, this.id, world.getData(i, j, k));
/*		 */ 
/*	33 */				 world.addEntity(entityfallingblock);
/*		 */			 } else {
/*	35 */				 world.setTypeId(i, j, k, 0);
/*		 */ 
/*	37 */				 while ((BlockSand.canFall(world, i, j - 1, k)) && (j > 0)) {
/*	38 */					 j--;
/*		 */				 }
/*		 */ 
/*	41 */				 if (j > 0)
/*	42 */					 world.setTypeId(i, j, k, this.id);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2)
/*		 */	 {
/*	49 */		 n(world, i, j, k);
/*	50 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
/*	54 */		 n(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 private void n(World world, int i, int j, int k) {
/*	58 */		 if ((world.getTypeId(i, j, k) == this.id) && 
/*	59 */			 (!world.isStatic))
/*	60 */			 for (int l = 0; l < 1000; l++) {
/*	61 */				 int i1 = i + world.random.nextInt(16) - world.random.nextInt(16);
/*	62 */				 int j1 = j + world.random.nextInt(8) - world.random.nextInt(8);
/*	63 */				 int k1 = k + world.random.nextInt(16) - world.random.nextInt(16);
/*		 */ 
/*	65 */				 if (world.getTypeId(i1, j1, k1) != 0)
/*		 */					 continue;
/*	67 */				 org.bukkit.block.Block from = world.getWorld().getBlockAt(i, j, k);
/*	68 */				 org.bukkit.block.Block to = world.getWorld().getBlockAt(i1, j1, k1);
/*	69 */				 BlockFromToEvent event = new BlockFromToEvent(from, to);
/*	70 */				 Bukkit.getPluginManager().callEvent(event);
/*		 */ 
/*	72 */				 if (event.isCancelled()) {
/*	73 */					 return;
/*		 */				 }
/*		 */ 
/*	76 */				 i1 = event.getToBlock().getX();
/*	77 */				 j1 = event.getToBlock().getY();
/*	78 */				 k1 = event.getToBlock().getZ();
/*		 */ 
/*	81 */				 world.setTypeIdAndData(i1, j1, k1, this.id, world.getData(i, j, k));
/*	82 */				 world.setTypeId(i, j, k, 0);
/*	83 */				 short short1 = 128;
/*		 */ 
/*	85 */				 for (int l1 = 0; l1 < short1; l1++) {
/*	86 */					 double d0 = world.random.nextDouble();
/*	87 */					 float f = (world.random.nextFloat() - 0.5F) * 0.2F;
/*	88 */					 float f1 = (world.random.nextFloat() - 0.5F) * 0.2F;
/*	89 */					 float f2 = (world.random.nextFloat() - 0.5F) * 0.2F;
/*	90 */					 double d1 = i1 + (i - i1) * d0 + (world.random.nextDouble() - 0.5D) * 1.0D + 0.5D;
/*	91 */					 double d2 = j1 + (j - j1) * d0 + world.random.nextDouble() * 1.0D - 0.5D;
/*	92 */					 double d3 = k1 + (k - k1) * d0 + (world.random.nextDouble() - 0.5D) * 1.0D + 0.5D;
/*		 */ 
/*	94 */					 world.a("portal", d1, d2, d3, f, f1, f2);
/*		 */				 }
/*		 */ 
/*	97 */				 return;
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public int p_()
/*		 */	 {
/* 105 */		 return 3;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/* 109 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/* 113 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/* 117 */		 return 27;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockDragonEgg
 * JD-Core Version:		0.6.0
 */