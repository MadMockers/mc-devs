/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.Cancellable;
/*		 */ import org.bukkit.event.block.Action;
/*		 */ import org.bukkit.event.entity.EntityInteractEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockSoil extends Block
/*		 */ {
/*		 */	 protected BlockSoil(int i)
/*		 */	 {
/*	10 */		 super(i, Material.EARTH);
/*	11 */		 this.textureId = 87;
/*	12 */		 b(true);
/*	13 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
/*	14 */		 h(255);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	18 */		 return AxisAlignedBB.a().a(i + 0, j + 0, k + 0, i + 1, j + 1, k + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	22 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	26 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	30 */		 return i == 1 ? this.textureId : (i == 1) && (j > 0) ? this.textureId - 1 : 2;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*	34 */		 if ((!n(world, i, j, k)) && (!world.B(i, j + 1, k))) {
/*	35 */			 int l = world.getData(i, j, k);
/*		 */ 
/*	37 */			 if (l > 0)
/*	38 */				 world.setData(i, j, k, l - 1);
/*	39 */			 else if (!l(world, i, j, k))
/*	40 */				 world.setTypeId(i, j, k, Block.DIRT.id);
/*		 */		 }
/*		 */		 else {
/*	43 */			 world.setData(i, j, k, 7);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, Entity entity, float f) {
/*	48 */		 if ((!world.isStatic) && (world.random.nextFloat() < f - 0.5F))
/*		 */		 {
/*		 */			 Cancellable cancellable;
/*		 */			 Cancellable cancellable;
/*	51 */			 if ((entity instanceof EntityHuman)) {
/*	52 */				 cancellable = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, i, j, k, -1, null);
/*		 */			 } else {
/*	54 */				 cancellable = new EntityInteractEvent(entity.getBukkitEntity(), world.getWorld().getBlockAt(i, j, k));
/*	55 */				 world.getServer().getPluginManager().callEvent((EntityInteractEvent)cancellable);
/*		 */			 }
/*		 */ 
/*	58 */			 if (cancellable.isCancelled()) {
/*	59 */				 return;
/*		 */			 }
/*		 */ 
/*	63 */			 world.setTypeId(i, j, k, Block.DIRT.id);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World world, int i, int j, int k) {
/*	68 */		 byte b0 = 0;
/*		 */ 
/*	70 */		 for (int l = i - b0; l <= i + b0; l++) {
/*	71 */			 for (int i1 = k - b0; i1 <= k + b0; i1++) {
/*	72 */				 int j1 = world.getTypeId(l, j + 1, i1);
/*		 */ 
/*	74 */				 if ((j1 == Block.CROPS.id) || (j1 == Block.MELON_STEM.id) || (j1 == Block.PUMPKIN_STEM.id)) {
/*	75 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	80 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean n(World world, int i, int j, int k) {
/*	84 */		 for (int l = i - 4; l <= i + 4; l++) {
/*	85 */			 for (int i1 = j; i1 <= j + 1; i1++) {
/*	86 */				 for (int j1 = k - 4; j1 <= k + 4; j1++) {
/*	87 */					 if (world.getMaterial(l, i1, j1) == Material.WATER) {
/*	88 */						 return true;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	94 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	98 */		 super.doPhysics(world, i, j, k, l);
/*	99 */		 Material material = world.getMaterial(i, j + 1, k);
/*		 */ 
/* 101 */		 if (material.isBuildable())
/* 102 */			 world.setTypeId(i, j, k, Block.DIRT.id);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j)
/*		 */	 {
/* 107 */		 return Block.DIRT.getDropType(0, random, j);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockSoil
 * JD-Core Version:		0.6.0
 */