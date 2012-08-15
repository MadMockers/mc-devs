/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockRedstoneTorch extends BlockTorch
/*		 */ {
/*	14 */	 private boolean isOn = false;
/*	15 */	 private static Map b = new HashMap();
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	18 */		 return i == 1 ? Block.REDSTONE_WIRE.a(i, j) : super.a(i, j);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(World world, int i, int j, int k, boolean flag) {
/*	22 */		 if (!b.containsKey(world)) {
/*	23 */			 b.put(world, new ArrayList());
/*		 */		 }
/*		 */ 
/*	26 */		 if (flag) {
/*	27 */			 ((List)b.get(world)).add(new RedstoneUpdateInfo(i, j, k, world.getTime()));
/*		 */		 }
/*		 */ 
/*	30 */		 int l = 0;
/*	31 */		 Iterator iterator = ((List)b.get(world)).iterator();
/*		 */ 
/*	33 */		 while (iterator.hasNext()) {
/*	34 */			 RedstoneUpdateInfo redstoneupdateinfo = (RedstoneUpdateInfo)iterator.next();
/*		 */ 
/*	36 */			 if ((redstoneupdateinfo.a == i) && (redstoneupdateinfo.b == j) && (redstoneupdateinfo.c == k)) {
/*	37 */				 l++;
/*	38 */				 if (l >= 8) {
/*	39 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	44 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected BlockRedstoneTorch(int i, int j, boolean flag) {
/*	48 */		 super(i, j);
/*	49 */		 this.isOn = flag;
/*	50 */		 b(true);
/*	51 */		 a((CreativeModeTab)null);
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	55 */		 return 2;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/*	59 */		 if (world.getData(i, j, k) == 0) {
/*	60 */			 super.onPlace(world, i, j, k);
/*		 */		 }
/*		 */ 
/*	63 */		 if (this.isOn) {
/*	64 */			 world.applyPhysics(i, j - 1, k, this.id);
/*	65 */			 world.applyPhysics(i, j + 1, k, this.id);
/*	66 */			 world.applyPhysics(i - 1, j, k, this.id);
/*	67 */			 world.applyPhysics(i + 1, j, k, this.id);
/*	68 */			 world.applyPhysics(i, j, k - 1, this.id);
/*	69 */			 world.applyPhysics(i, j, k + 1, this.id);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1) {
/*	74 */		 if (this.isOn) {
/*	75 */			 world.applyPhysics(i, j - 1, k, this.id);
/*	76 */			 world.applyPhysics(i, j + 1, k, this.id);
/*	77 */			 world.applyPhysics(i - 1, j, k, this.id);
/*	78 */			 world.applyPhysics(i + 1, j, k, this.id);
/*	79 */			 world.applyPhysics(i, j, k - 1, this.id);
/*	80 */			 world.applyPhysics(i, j, k + 1, this.id);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/*	85 */		 if (!this.isOn) {
/*	86 */			 return false;
/*		 */		 }
/*	88 */		 int i1 = iblockaccess.getData(i, j, k);
/*		 */ 
/*	90 */		 return (i1 != 5) || (l != 1);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World world, int i, int j, int k)
/*		 */	 {
/*	95 */		 int l = world.getData(i, j, k);
/*		 */ 
/*	97 */		 return (l == 5) && (world.isBlockFaceIndirectlyPowered(i, j - 1, k, 0));
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/* 101 */		 boolean flag = l(world, i, j, k);
/* 102 */		 List list = (List)b.get(world);
/*		 */ 
/* 104 */		 while ((list != null) && (!list.isEmpty()) && (world.getTime() - ((RedstoneUpdateInfo)list.get(0)).d > 60L)) {
/* 105 */			 list.remove(0);
/*		 */		 }
/*		 */ 
/* 109 */		 PluginManager manager = world.getServer().getPluginManager();
/* 110 */		 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/* 111 */		 int oldCurrent = this.isOn ? 15 : 0;
/*		 */ 
/* 113 */		 BlockRedstoneEvent event = new BlockRedstoneEvent(block, oldCurrent, oldCurrent);
/*		 */ 
/* 116 */		 if (this.isOn) {
/* 117 */			 if (flag)
/*		 */			 {
/* 119 */				 if (oldCurrent != 0) {
/* 120 */					 event.setNewCurrent(0);
/* 121 */					 manager.callEvent(event);
/* 122 */					 if (event.getNewCurrent() != 0) {
/* 123 */						 return;
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/* 128 */				 world.setTypeIdAndData(i, j, k, Block.REDSTONE_TORCH_OFF.id, world.getData(i, j, k));
/* 129 */				 if (a(world, i, j, k, true)) {
/* 130 */					 world.makeSound(i + 0.5F, j + 0.5F, k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
/*		 */ 
/* 132 */					 for (int l = 0; l < 5; l++) {
/* 133 */						 double d0 = i + random.nextDouble() * 0.6D + 0.2D;
/* 134 */						 double d1 = j + random.nextDouble() * 0.6D + 0.2D;
/* 135 */						 double d2 = k + random.nextDouble() * 0.6D + 0.2D;
/*		 */ 
/* 137 */						 world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 141 */		 } else if ((!flag) && (!a(world, i, j, k, false)))
/*		 */		 {
/* 143 */			 if (oldCurrent != 15) {
/* 144 */				 event.setNewCurrent(15);
/* 145 */				 manager.callEvent(event);
/* 146 */				 if (event.getNewCurrent() != 15) {
/* 147 */					 return;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 152 */			 world.setTypeIdAndData(i, j, k, Block.REDSTONE_TORCH_ON.id, world.getData(i, j, k));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/* 157 */		 super.doPhysics(world, i, j, k, l);
/* 158 */		 world.a(i, j, k, this.id, p_());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 162 */		 return l == 0 ? a(world, i, j, k, l) : false;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j) {
/* 166 */		 return Block.REDSTONE_TORCH_ON.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource() {
/* 170 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, long i, long j) {
/* 174 */		 List list = (List)b.get(world);
/*		 */ 
/* 177 */		 if (list != null)
/*		 */		 {
/*		 */			 RedstoneUpdateInfo redstoneupdateinfo;
/* 178 */			 for (Iterator iterator = list.iterator(); iterator.hasNext(); redstoneupdateinfo.d += i)
/* 179 */				 redstoneupdateinfo = (RedstoneUpdateInfo)iterator.next();
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockRedstoneTorch
 * JD-Core Version:		0.6.0
 */