/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockDoor extends Block
/*		 */ {
/*		 */	 protected BlockDoor(int i, Material material)
/*		 */	 {
/*	10 */		 super(i, material);
/*	11 */		 this.textureId = 97;
/*	12 */		 if (material == Material.ORE) {
/*	13 */			 this.textureId += 1;
/*		 */		 }
/*		 */ 
/*	16 */		 float f = 0.5F;
/*	17 */		 float f1 = 1.0F;
/*		 */ 
/*	19 */		 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	23 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
/*	27 */		 int l = b_(iblockaccess, i, j, k);
/*		 */ 
/*	29 */		 return (l & 0x4) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	33 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	37 */		 return 7;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	41 */		 updateShape(world, i, j, k);
/*	42 */		 return super.e(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/*	46 */		 e(b_(iblockaccess, i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 public int d(IBlockAccess iblockaccess, int i, int j, int k) {
/*	50 */		 return b_(iblockaccess, i, j, k) & 0x3;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a_(IBlockAccess iblockaccess, int i, int j, int k) {
/*	54 */		 return (b_(iblockaccess, i, j, k) & 0x4) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 private void e(int i) {
/*	58 */		 float f = 0.1875F;
/*		 */ 
/*	60 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/*	61 */		 int j = i & 0x3;
/*	62 */		 boolean flag = (i & 0x4) != 0;
/*	63 */		 boolean flag1 = (i & 0x10) != 0;
/*		 */ 
/*	65 */		 if (j == 0) {
/*	66 */			 if (flag) {
/*	67 */				 if (!flag1)
/*	68 */					 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*		 */				 else
/*	70 */					 a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*		 */			 }
/*		 */			 else
/*	73 */				 a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*		 */		 }
/*	75 */		 else if (j == 1) {
/*	76 */			 if (flag) {
/*	77 */				 if (!flag1)
/*	78 */					 a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */				 else
/*	80 */					 a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*		 */			 }
/*		 */			 else
/*	83 */				 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*		 */		 }
/*	85 */		 else if (j == 2) {
/*	86 */			 if (flag) {
/*	87 */				 if (!flag1)
/*	88 */					 a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*		 */				 else
/*	90 */					 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*		 */			 }
/*		 */			 else
/*	93 */				 a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */		 }
/*	95 */		 else if (j == 3)
/*	96 */			 if (flag) {
/*	97 */				 if (!flag1)
/*	98 */					 a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*		 */				 else
/* 100 */					 a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */			 }
/*		 */			 else
/* 103 */				 a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman)
/*		 */	 {
/* 109 */		 interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/* 113 */		 if (this.material == Material.ORE) {
/* 114 */			 return true;
/*		 */		 }
/* 116 */		 int i1 = b_(world, i, j, k);
/* 117 */		 int j1 = i1 & 0x7;
/*		 */ 
/* 119 */		 j1 ^= 4;
/* 120 */		 if ((i1 & 0x8) == 0) {
/* 121 */			 world.setData(i, j, k, j1);
/* 122 */			 world.d(i, j, k, i, j, k);
/*		 */		 } else {
/* 124 */			 world.setData(i, j - 1, k, j1);
/* 125 */			 world.d(i, j - 1, k, i, j, k);
/*		 */		 }
/*		 */ 
/* 128 */		 world.a(entityhuman, 1003, i, j, k, 0);
/* 129 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void setDoor(World world, int i, int j, int k, boolean flag)
/*		 */	 {
/* 134 */		 int l = b_(world, i, j, k);
/* 135 */		 boolean flag1 = (l & 0x4) != 0;
/*		 */ 
/* 137 */		 if (flag1 != flag) {
/* 138 */			 int i1 = l & 0x7;
/*		 */ 
/* 140 */			 i1 ^= 4;
/* 141 */			 if ((l & 0x8) == 0) {
/* 142 */				 world.setData(i, j, k, i1);
/* 143 */				 world.d(i, j, k, i, j, k);
/*		 */			 } else {
/* 145 */				 world.setData(i, j - 1, k, i1);
/* 146 */				 world.d(i, j - 1, k, i, j, k);
/*		 */			 }
/*		 */ 
/* 149 */			 world.a((EntityHuman)null, 1003, i, j, k, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/* 154 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/* 156 */		 if ((i1 & 0x8) == 0) {
/* 157 */			 boolean flag = false;
/*		 */ 
/* 159 */			 if (world.getTypeId(i, j + 1, k) != this.id) {
/* 160 */				 world.setTypeId(i, j, k, 0);
/* 161 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 164 */			 if (!world.t(i, j - 1, k)) {
/* 165 */				 world.setTypeId(i, j, k, 0);
/* 166 */				 flag = true;
/* 167 */				 if (world.getTypeId(i, j + 1, k) == this.id) {
/* 168 */					 world.setTypeId(i, j + 1, k, 0);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 172 */			 if (flag) {
/* 173 */				 if (!world.isStatic) {
/* 174 */					 c(world, i, j, k, i1, 0);
/*		 */				 }
/*		 */			 }
/* 177 */			 else if ((l > 0) && (Block.byId[l].isPowerSource())) {
/* 178 */				 org.bukkit.World bworld = world.getWorld();
/* 179 */				 org.bukkit.block.Block block = bworld.getBlockAt(i, j, k);
/* 180 */				 org.bukkit.block.Block blockTop = bworld.getBlockAt(i, j + 1, k);
/*		 */ 
/* 182 */				 int power = block.getBlockPower();
/* 183 */				 int powerTop = blockTop.getBlockPower();
/* 184 */				 if (powerTop > power) power = powerTop;
/* 185 */				 int oldPower = (world.getData(i, j, k) & 0x4) > 0 ? 15 : 0;
/*		 */ 
/* 187 */				 if (((oldPower == 0 ? 1 : 0) ^ (power == 0 ? 1 : 0)) != 0) {
/* 188 */					 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, oldPower, power);
/* 189 */					 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */ 
/* 191 */					 setDoor(world, i, j, k, eventRedstone.getNewCurrent() > 0);
/*		 */				 }
/*		 */			 }
/*		 */ 
/*		 */		 }
/* 196 */		 else if (world.getTypeId(i, j - 1, k) != this.id) {
/* 197 */			 world.setTypeId(i, j, k, 0);
/*		 */		 }
/* 199 */		 else if ((l > 0) && (l != this.id)) {
/* 200 */			 doPhysics(world, i, j - 1, k, l);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j)
/*		 */	 {
/* 206 */		 return this.material == Material.ORE ? Item.IRON_DOOR.id : (i & 0x8) != 0 ? 0 : Item.WOOD_DOOR.id;
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
/* 210 */		 updateShape(world, i, j, k);
/* 211 */		 return super.a(world, i, j, k, vec3d, vec3d1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/* 215 */		 return j < 255;
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 219 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int b_(IBlockAccess iblockaccess, int i, int j, int k) {
/* 223 */		 int l = iblockaccess.getData(i, j, k);
/* 224 */		 boolean flag = (l & 0x8) != 0;
/*		 */		 int j1;
/*		 */		 int i1;
/*		 */		 int j1;
/* 228 */		 if (flag) {
/* 229 */			 int i1 = iblockaccess.getData(i, j - 1, k);
/* 230 */			 j1 = l;
/*		 */		 } else {
/* 232 */			 i1 = l;
/* 233 */			 j1 = iblockaccess.getData(i, j + 1, k);
/*		 */		 }
/*		 */ 
/* 236 */		 boolean flag1 = (j1 & 0x1) != 0;
/*		 */ 
/* 238 */		 return i1 & 0x7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockDoor
 * JD-Core Version:		0.6.0
 */