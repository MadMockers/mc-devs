/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockTrapdoor extends Block
/*		 */ {
/*		 */	 protected BlockTrapdoor(int i, Material material)
/*		 */	 {
/*	 8 */		 super(i, material);
/*	 9 */		 this.textureId = 84;
/*	10 */		 if (material == Material.ORE) {
/*	11 */			 this.textureId += 1;
/*		 */		 }
/*		 */ 
/*	14 */		 float f = 0.5F;
/*	15 */		 float f1 = 1.0F;
/*		 */ 
/*	17 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
/*	18 */		 a(CreativeModeTab.d);
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
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
/*	30 */		 return !g(iblockaccess.getData(i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	34 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	38 */		 updateShape(world, i, j, k);
/*	39 */		 return super.e(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/*	43 */		 e(iblockaccess.getData(i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/*	47 */		 float f = 0.1875F;
/*		 */ 
/*	49 */		 a(0.0F, 0.5F - f / 2.0F, 0.0F, 1.0F, 0.5F + f / 2.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void e(int i) {
/*	53 */		 float f = 0.1875F;
/*		 */ 
/*	55 */		 a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
/*	56 */		 if (g(i)) {
/*	57 */			 if ((i & 0x3) == 0) {
/*	58 */				 a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*		 */			 }
/*		 */ 
/*	61 */			 if ((i & 0x3) == 1) {
/*	62 */				 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*		 */			 }
/*		 */ 
/*	65 */			 if ((i & 0x3) == 2) {
/*	66 */				 a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */			 }
/*		 */ 
/*	69 */			 if ((i & 0x3) == 3)
/*	70 */				 a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman)
/*		 */	 {
/*	76 */		 interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/*	80 */		 if (this.material == Material.ORE) {
/*	81 */			 return true;
/*		 */		 }
/*	83 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/*	85 */		 world.setData(i, j, k, i1 ^ 0x4);
/*	86 */		 world.a(entityhuman, 1003, i, j, k, 0);
/*	87 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void setOpen(World world, int i, int j, int k, boolean flag)
/*		 */	 {
/*	92 */		 int l = world.getData(i, j, k);
/*	93 */		 boolean flag1 = (l & 0x4) > 0;
/*		 */ 
/*	95 */		 if (flag1 != flag) {
/*	96 */			 world.setData(i, j, k, l ^ 0x4);
/*	97 */			 world.a((EntityHuman)null, 1003, i, j, k, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/* 102 */		 if (!world.isStatic) {
/* 103 */			 int i1 = world.getData(i, j, k);
/* 104 */			 int j1 = i;
/* 105 */			 int k1 = k;
/*		 */ 
/* 107 */			 if ((i1 & 0x3) == 0) {
/* 108 */				 k1 = k + 1;
/*		 */			 }
/*		 */ 
/* 111 */			 if ((i1 & 0x3) == 1) {
/* 112 */				 k1--;
/*		 */			 }
/*		 */ 
/* 115 */			 if ((i1 & 0x3) == 2) {
/* 116 */				 j1 = i + 1;
/*		 */			 }
/*		 */ 
/* 119 */			 if ((i1 & 0x3) == 3) {
/* 120 */				 j1--;
/*		 */			 }
/*		 */ 
/* 123 */			 if (!j(world.getTypeId(j1, j, k1))) {
/* 124 */				 world.setTypeId(i, j, k, 0);
/* 125 */				 c(world, i, j, k, i1, 0);
/*		 */			 }
/*		 */ 
/* 129 */			 if ((l == 0) || ((l > 0) && (Block.byId[l] != null) && (Block.byId[l].isPowerSource()))) {
/* 130 */				 org.bukkit.World bworld = world.getWorld();
/* 131 */				 org.bukkit.block.Block block = bworld.getBlockAt(i, j, k);
/*		 */ 
/* 133 */				 int power = block.getBlockPower();
/* 134 */				 int oldPower = (world.getData(i, j, k) & 0x4) > 0 ? 15 : 0;
/*		 */ 
/* 136 */				 if ((((oldPower == 0 ? 1 : 0) ^ (power == 0 ? 1 : 0)) != 0) || ((Block.byId[l] != null) && (Block.byId[l].isPowerSource()))) {
/* 137 */					 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, oldPower, power);
/* 138 */					 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */ 
/* 140 */					 setOpen(world, i, j, k, eventRedstone.getNewCurrent() > 0);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
/*		 */	 {
/* 148 */		 updateShape(world, i, j, k);
/* 149 */		 return super.a(world, i, j, k, vec3d, vec3d1);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
/* 153 */		 byte b0 = 0;
/*		 */ 
/* 155 */		 if (l == 2) {
/* 156 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/* 159 */		 if (l == 3) {
/* 160 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/* 163 */		 if (l == 4) {
/* 164 */			 b0 = 2;
/*		 */		 }
/*		 */ 
/* 167 */		 if (l == 5) {
/* 168 */			 b0 = 3;
/*		 */		 }
/*		 */ 
/* 171 */		 world.setData(i, j, k, b0);
/* 172 */		 doPhysics(world, i, j, k, Block.REDSTONE_WIRE.id);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k, int l) {
/* 176 */		 if (l == 0)
/* 177 */			 return false;
/* 178 */		 if (l == 1) {
/* 179 */			 return false;
/*		 */		 }
/* 181 */		 if (l == 2) {
/* 182 */			 k++;
/*		 */		 }
/*		 */ 
/* 185 */		 if (l == 3) {
/* 186 */			 k--;
/*		 */		 }
/*		 */ 
/* 189 */		 if (l == 4) {
/* 190 */			 i++;
/*		 */		 }
/*		 */ 
/* 193 */		 if (l == 5) {
/* 194 */			 i--;
/*		 */		 }
/*		 */ 
/* 197 */		 return j(world.getTypeId(i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean g(int i)
/*		 */	 {
/* 202 */		 return (i & 0x4) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 private static boolean j(int i) {
/* 206 */		 if (i <= 0) {
/* 207 */			 return false;
/*		 */		 }
/* 209 */		 Block block = Block.byId[i];
/*		 */ 
/* 211 */		 return ((block != null) && (block.material.k()) && (block.c())) || (block == Block.GLOWSTONE);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockTrapdoor
 * JD-Core Version:		0.6.0
 */