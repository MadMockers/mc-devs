/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockSign extends BlockContainer
/*		 */ {
/*		 */	 private Class a;
/*		 */	 private boolean b;
/*		 */ 
/*		 */	 protected BlockSign(int i, Class oclass, boolean flag)
/*		 */	 {
/*	13 */		 super(i, Material.WOOD);
/*	14 */		 this.b = flag;
/*	15 */		 this.textureId = 4;
/*	16 */		 this.a = oclass;
/*	17 */		 float f = 0.25F;
/*	18 */		 float f1 = 1.0F;
/*		 */ 
/*	20 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	24 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/*	28 */		 if (!this.b) {
/*	29 */			 int l = iblockaccess.getData(i, j, k);
/*	30 */			 float f = 0.28125F;
/*	31 */			 float f1 = 0.78125F;
/*	32 */			 float f2 = 0.0F;
/*	33 */			 float f3 = 1.0F;
/*	34 */			 float f4 = 0.125F;
/*		 */ 
/*	36 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*	37 */			 if (l == 2) {
/*	38 */				 a(f2, f, 1.0F - f4, f3, f1, 1.0F);
/*		 */			 }
/*		 */ 
/*	41 */			 if (l == 3) {
/*	42 */				 a(f2, f, 0.0F, f3, f1, f4);
/*		 */			 }
/*		 */ 
/*	45 */			 if (l == 4) {
/*	46 */				 a(1.0F - f4, f, f2, 1.0F, f1, f3);
/*		 */			 }
/*		 */ 
/*	49 */			 if (l == 5)
/*	50 */				 a(0.0F, f, f2, f4, f1, f3);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	56 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	60 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
/*	64 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	68 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity a(World world) {
/*		 */		 try {
/*	73 */			 return (TileEntity)this.a.newInstance(); } catch (Exception exception) {
/*		 */		 }
/*	75 */		 throw new RuntimeException(exception);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j)
/*		 */	 {
/*	80 */		 return Item.SIGN.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	84 */		 boolean flag = false;
/*		 */ 
/*	86 */		 if (this.b) {
/*	87 */			 if (!world.getMaterial(i, j - 1, k).isBuildable())
/*	88 */				 flag = true;
/*		 */		 }
/*		 */		 else {
/*	91 */			 int i1 = world.getData(i, j, k);
/*		 */ 
/*	93 */			 flag = true;
/*	94 */			 if ((i1 == 2) && (world.getMaterial(i, j, k + 1).isBuildable())) {
/*	95 */				 flag = false;
/*		 */			 }
/*		 */ 
/*	98 */			 if ((i1 == 3) && (world.getMaterial(i, j, k - 1).isBuildable())) {
/*	99 */				 flag = false;
/*		 */			 }
/*		 */ 
/* 102 */			 if ((i1 == 4) && (world.getMaterial(i + 1, j, k).isBuildable())) {
/* 103 */				 flag = false;
/*		 */			 }
/*		 */ 
/* 106 */			 if ((i1 == 5) && (world.getMaterial(i - 1, j, k).isBuildable())) {
/* 107 */				 flag = false;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 111 */		 if (flag) {
/* 112 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/* 113 */			 world.setTypeId(i, j, k, 0);
/*		 */		 }
/*		 */ 
/* 116 */		 super.doPhysics(world, i, j, k, l);
/*		 */ 
/* 119 */		 if ((Block.byId[l] != null) && (Block.byId[l].isPowerSource())) {
/* 120 */			 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/* 121 */			 int power = block.getBlockPower();
/*		 */ 
/* 123 */			 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, power, power);
/* 124 */			 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockSign
 * JD-Core Version:		0.6.0
 */