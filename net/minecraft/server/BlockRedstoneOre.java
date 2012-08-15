/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.block.Action;
/*		 */ import org.bukkit.event.entity.EntityInteractEvent;
/*		 */ import org.bukkit.event.player.PlayerInteractEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockRedstoneOre extends Block
/*		 */ {
/*		 */	 private boolean a;
/*		 */ 
/*		 */	 public BlockRedstoneOre(int i, int j, boolean flag)
/*		 */	 {
/*	12 */		 super(i, j, Material.STONE);
/*	13 */		 if (flag) {
/*	14 */			 b(true);
/*		 */		 }
/*		 */ 
/*	17 */		 this.a = flag;
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	21 */		 return 30;
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
/*	25 */		 l(world, i, j, k);
/*	26 */		 super.attack(world, i, j, k, entityhuman);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Entity entity)
/*		 */	 {
/*	31 */		 if ((entity instanceof EntityHuman)) {
/*	32 */			 PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, i, j, k, -1, null);
/*	33 */			 if (!event.isCancelled()) {
/*	34 */				 l(world, i, j, k);
/*	35 */				 super.b(world, i, j, k, entity);
/*		 */			 }
/*		 */		 } else {
/*	38 */			 EntityInteractEvent event = new EntityInteractEvent(entity.getBukkitEntity(), world.getWorld().getBlockAt(i, j, k));
/*	39 */			 world.getServer().getPluginManager().callEvent(event);
/*	40 */			 if (!event.isCancelled()) {
/*	41 */				 l(world, i, j, k);
/*	42 */				 super.b(world, i, j, k, entity);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2)
/*		 */	 {
/*	49 */		 l(world, i, j, k);
/*	50 */		 return super.interact(world, i, j, k, entityhuman, l, f, f1, f2);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k) {
/*	54 */		 n(world, i, j, k);
/*	55 */		 if (this.id == Block.REDSTONE_ORE.id)
/*	56 */			 world.setTypeId(i, j, k, Block.GLOWING_REDSTONE_ORE.id);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random)
/*		 */	 {
/*	61 */		 if (this.id == Block.GLOWING_REDSTONE_ORE.id)
/*	62 */			 world.setTypeId(i, j, k, Block.REDSTONE_ORE.id);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j)
/*		 */	 {
/*	67 */		 return Item.REDSTONE.id;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropCount(int i, Random random) {
/*	71 */		 return a(random) + random.nextInt(i + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/*	75 */		 return 4 + random.nextInt(2);
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
/*	79 */		 super.dropNaturally(world, i, j, k, l, f, i1);
/*		 */	 }
/*		 */ 
/*		 */	 public int getExpDrop(World world, int l, int i1)
/*		 */	 {
/*	89 */		 if (getDropType(l, world.random, i1) != this.id) {
/*	90 */			 int j1 = 1 + world.random.nextInt(5);
/*		 */ 
/*	92 */			 return j1;
/*		 */		 }
/*		 */ 
/*	95 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 private void n(World world, int i, int j, int k)
/*		 */	 {
/* 100 */		 Random random = world.random;
/* 101 */		 double d0 = 0.0625D;
/*		 */ 
/* 103 */		 for (int l = 0; l < 6; l++) {
/* 104 */			 double d1 = i + random.nextFloat();
/* 105 */			 double d2 = j + random.nextFloat();
/* 106 */			 double d3 = k + random.nextFloat();
/*		 */ 
/* 108 */			 if ((l == 0) && (!world.r(i, j + 1, k))) {
/* 109 */				 d2 = j + 1 + d0;
/*		 */			 }
/*		 */ 
/* 112 */			 if ((l == 1) && (!world.r(i, j - 1, k))) {
/* 113 */				 d2 = j + 0 - d0;
/*		 */			 }
/*		 */ 
/* 116 */			 if ((l == 2) && (!world.r(i, j, k + 1))) {
/* 117 */				 d3 = k + 1 + d0;
/*		 */			 }
/*		 */ 
/* 120 */			 if ((l == 3) && (!world.r(i, j, k - 1))) {
/* 121 */				 d3 = k + 0 - d0;
/*		 */			 }
/*		 */ 
/* 124 */			 if ((l == 4) && (!world.r(i + 1, j, k))) {
/* 125 */				 d1 = i + 1 + d0;
/*		 */			 }
/*		 */ 
/* 128 */			 if ((l == 5) && (!world.r(i - 1, j, k))) {
/* 129 */				 d1 = i + 0 - d0;
/*		 */			 }
/*		 */ 
/* 132 */			 if ((d1 < i) || (d1 > i + 1) || (d2 < 0.0D) || (d2 > j + 1) || (d3 < k) || (d3 > k + 1))
/* 133 */				 world.a("reddust", d1, d2, d3, 0.0D, 0.0D, 0.0D);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected ItemStack c_(int i)
/*		 */	 {
/* 139 */		 return new ItemStack(Block.REDSTONE_ORE);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockRedstoneOre
 * JD-Core Version:		0.6.0
 */