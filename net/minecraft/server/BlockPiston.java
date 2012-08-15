/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.block.CraftBlock;
/*		 */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*		 */ import org.bukkit.event.block.BlockPistonRetractEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockPiston extends Block
/*		 */ {
/*		 */	 private boolean a;
/*		 */ 
/*		 */	 public BlockPiston(int i, int j, boolean flag)
/*		 */	 {
/*	16 */		 super(i, j, Material.PISTON);
/*	17 */		 this.a = flag;
/*	18 */		 a(h);
/*	19 */		 c(0.5F);
/*	20 */		 a(CreativeModeTab.d);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	24 */		 int k = e(j);
/*		 */ 
/*	26 */		 return i == Facing.OPPOSITE_FACING[k] ? 109 : i == k ? 110 : (!f(j)) && (this.minX <= 0.0D) && (this.minY <= 0.0D) && (this.minZ <= 0.0D) && (this.maxX >= 1.0D) && (this.maxY >= 1.0D) && (this.maxZ >= 1.0D) ? this.textureId : k > 5 ? this.textureId : 108;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	30 */		 return 16;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	34 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/*	38 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
/*	42 */		 int l = b(world, i, j, k, (EntityHuman)entityliving);
/*		 */ 
/*	44 */		 world.setData(i, j, k, l);
/*	45 */		 if (!world.isStatic)
/*	46 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l)
/*		 */	 {
/*	51 */		 if (!world.isStatic)
/*	52 */			 l(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k)
/*		 */	 {
/*	57 */		 if ((!world.isStatic) && (world.getTileEntity(i, j, k) == null));
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k)
/*		 */	 {
/*	63 */		 int l = world.getData(i, j, k);
/*	64 */		 int i1 = e(l);
/*		 */ 
/*	66 */		 if (i1 != 7) {
/*	67 */			 boolean flag = e(world, i, j, k, i1);
/*		 */ 
/*	69 */			 if ((flag) && (!f(l)))
/*		 */			 {
/*	71 */				 int length = i(world, i, j, k, i1);
/*	72 */				 if (length >= 0) {
/*	73 */					 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/*	75 */					 BlockPistonExtendEvent event = new BlockPistonExtendEvent(block, length, CraftBlock.notchToBlockFace(i1));
/*	76 */					 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	78 */					 if (event.isCancelled()) {
/*	79 */						 return;
/*		 */					 }
/*		 */ 
/*	83 */					 world.playNote(i, j, k, this.id, 0, i1);
/*		 */				 }
/*	85 */			 } else if ((!flag) && (f(l)))
/*		 */			 {
/*	87 */				 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/*	89 */				 BlockPistonRetractEvent event = new BlockPistonRetractEvent(block, CraftBlock.notchToBlockFace(i1));
/*	90 */				 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/*	92 */				 if (event.isCancelled()) {
/*	93 */					 return;
/*		 */				 }
/*		 */ 
/*	97 */				 world.playNote(i, j, k, this.id, 1, i1);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean e(World world, int i, int j, int k, int l) {
/* 103 */		 return world.isBlockFaceIndirectlyPowered(i - 1, j + 1, k, 4) ? true : world.isBlockFaceIndirectlyPowered(i, j + 1, k + 1, 3) ? true : world.isBlockFaceIndirectlyPowered(i, j + 1, k - 1, 2) ? true : world.isBlockFaceIndirectlyPowered(i, j + 2, k, 1) ? true : world.isBlockFaceIndirectlyPowered(i, j, k, 0) ? true : (l != 4) && (world.isBlockFaceIndirectlyPowered(i - 1, j, k, 4)) ? true : (l != 5) && (world.isBlockFaceIndirectlyPowered(i + 1, j, k, 5)) ? true : (l != 3) && (world.isBlockFaceIndirectlyPowered(i, j, k + 1, 3)) ? true : (l != 2) && (world.isBlockFaceIndirectlyPowered(i, j, k - 1, 2)) ? true : (l != 1) && (world.isBlockFaceIndirectlyPowered(i, j + 1, k, 1)) ? true : (l != 0) && (world.isBlockFaceIndirectlyPowered(i, j - 1, k, 0)) ? true : world.isBlockFaceIndirectlyPowered(i + 1, j + 1, k, 5);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, int l, int i1) {
/* 107 */		 if (l == 0)
/* 108 */			 world.setRawData(i, j, k, i1 | 0x8);
/*		 */		 else {
/* 110 */			 world.setRawData(i, j, k, i1);
/*		 */		 }
/*		 */ 
/* 113 */		 if (l == 0) {
/* 114 */			 if (j(world, i, j, k, i1)) {
/* 115 */				 world.setData(i, j, k, i1 | 0x8);
/* 116 */				 world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "tile.piston.out", 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
/*		 */			 } else {
/* 118 */				 world.setRawData(i, j, k, i1);
/*		 */			 }
/* 120 */		 } else if (l == 1) {
/* 121 */			 TileEntity tileentity = world.getTileEntity(i + Facing.b[i1], j + Facing.c[i1], k + Facing.d[i1]);
/*		 */ 
/* 123 */			 if ((tileentity instanceof TileEntityPiston)) {
/* 124 */				 ((TileEntityPiston)tileentity).i();
/*		 */			 }
/*		 */ 
/* 127 */			 world.setRawTypeIdAndData(i, j, k, Block.PISTON_MOVING.id, i1);
/* 128 */			 world.setTileEntity(i, j, k, BlockPistonMoving.a(this.id, i1, i1, false, true));
/* 129 */			 if (this.a) {
/* 130 */				 int j1 = i + Facing.b[i1] * 2;
/* 131 */				 int k1 = j + Facing.c[i1] * 2;
/* 132 */				 int l1 = k + Facing.d[i1] * 2;
/* 133 */				 int i2 = world.getTypeId(j1, k1, l1);
/* 134 */				 int j2 = world.getData(j1, k1, l1);
/* 135 */				 boolean flag = false;
/*		 */ 
/* 137 */				 if (i2 == Block.PISTON_MOVING.id) {
/* 138 */					 TileEntity tileentity1 = world.getTileEntity(j1, k1, l1);
/*		 */ 
/* 140 */					 if ((tileentity1 instanceof TileEntityPiston)) {
/* 141 */						 TileEntityPiston tileentitypiston = (TileEntityPiston)tileentity1;
/*		 */ 
/* 143 */						 if ((tileentitypiston.c() == i1) && (tileentitypiston.b())) {
/* 144 */							 tileentitypiston.i();
/* 145 */							 i2 = tileentitypiston.a();
/* 146 */							 j2 = tileentitypiston.n();
/* 147 */							 flag = true;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 152 */				 if ((!flag) && (i2 > 0) && (a(i2, world, j1, k1, l1, false)) && ((Block.byId[i2].e() == 0) || (i2 == Block.PISTON.id) || (i2 == Block.PISTON_STICKY.id))) {
/* 153 */					 i += Facing.b[i1];
/* 154 */					 j += Facing.c[i1];
/* 155 */					 k += Facing.d[i1];
/* 156 */					 world.setRawTypeIdAndData(i, j, k, Block.PISTON_MOVING.id, j2);
/* 157 */					 world.setTileEntity(i, j, k, BlockPistonMoving.a(i2, j2, i1, false, false));
/* 158 */					 world.setTypeId(j1, k1, l1, 0);
/* 159 */				 } else if (!flag) {
/* 160 */					 world.setTypeId(i + Facing.b[i1], j + Facing.c[i1], k + Facing.d[i1], 0);
/*		 */				 }
/*		 */			 } else {
/* 163 */				 world.setTypeId(i + Facing.b[i1], j + Facing.c[i1], k + Facing.d[i1], 0);
/*		 */			 }
/*		 */ 
/* 166 */			 world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "tile.piston.in", 0.5F, world.random.nextFloat() * 0.15F + 0.6F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/* 171 */		 int l = iblockaccess.getData(i, j, k);
/*		 */ 
/* 173 */		 if (f(l))
/* 174 */			 switch (e(l)) {
/*		 */			 case 0:
/* 176 */				 a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 177 */				 break;
/*		 */			 case 1:
/* 180 */				 a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
/* 181 */				 break;
/*		 */			 case 2:
/* 184 */				 a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
/* 185 */				 break;
/*		 */			 case 3:
/* 188 */				 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
/* 189 */				 break;
/*		 */			 case 4:
/* 192 */				 a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 193 */				 break;
/*		 */			 case 5:
/* 196 */				 a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
/*		 */			 }
/*		 */		 else
/* 199 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/* 204 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
/* 208 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 209 */		 super.a(world, i, j, k, axisalignedbb, list, entity);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/* 213 */		 updateShape(world, i, j, k);
/* 214 */		 return super.e(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/* 218 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public static int e(int i) {
/* 222 */		 if ((i & 0x7) >= Facing.OPPOSITE_FACING.length) return 0;
/* 223 */		 return i & 0x7;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean f(int i) {
/* 227 */		 return (i & 0x8) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static int b(World world, int i, int j, int k, EntityHuman entityhuman) {
/* 231 */		 if ((MathHelper.abs((float)entityhuman.locX - i) < 2.0F) && (MathHelper.abs((float)entityhuman.locZ - k) < 2.0F)) {
/* 232 */			 double d0 = entityhuman.locY + 1.82D - entityhuman.height;
/*		 */ 
/* 234 */			 if (d0 - j > 2.0D) {
/* 235 */				 return 1;
/*		 */			 }
/*		 */ 
/* 238 */			 if (j - d0 > 0.0D) {
/* 239 */				 return 0;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 243 */		 int l = MathHelper.floor(entityhuman.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
/*		 */ 
/* 245 */		 return l == 3 ? 4 : l == 2 ? 3 : l == 1 ? 5 : l == 0 ? 2 : 0;
/*		 */	 }
/*		 */ 
/*		 */	 private static boolean a(int i, World world, int j, int k, int l, boolean flag) {
/* 249 */		 if (i == Block.OBSIDIAN.id) {
/* 250 */			 return false;
/*		 */		 }
/* 252 */		 if ((i != Block.PISTON.id) && (i != Block.PISTON_STICKY.id)) {
/* 253 */			 if (Block.byId[i].m(world, j, k, l) == -1.0F) {
/* 254 */				 return false;
/*		 */			 }
/*		 */ 
/* 257 */			 if (Block.byId[i].e() == 2) {
/* 258 */				 return false;
/*		 */			 }
/*		 */ 
/* 261 */			 if ((!flag) && (Block.byId[i].e() == 1))
/* 262 */				 return false;
/*		 */		 }
/* 264 */		 else if (f(world.getData(j, k, l))) {
/* 265 */			 return false;
/*		 */		 }
/*		 */ 
/* 268 */		 return !(Block.byId[i] instanceof BlockContainer);
/*		 */	 }
/*		 */ 
/*		 */	 private static int i(World world, int i, int j, int k, int l)
/*		 */	 {
/* 274 */		 int i1 = i + Facing.b[l];
/* 275 */		 int j1 = j + Facing.c[l];
/* 276 */		 int k1 = k + Facing.d[l];
/* 277 */		 int l1 = 0;
/*		 */ 
/* 280 */		 while (l1 < 13) {
/* 281 */			 if ((j1 <= 0) || (j1 >= 255)) {
/* 282 */				 return -1;
/*		 */			 }
/*		 */ 
/* 285 */			 int i2 = world.getTypeId(i1, j1, k1);
/*		 */ 
/* 287 */			 if (i2 == 0) break;
/* 288 */			 if (!a(i2, world, i1, j1, k1, true)) {
/* 289 */				 return -1;
/*		 */			 }
/*		 */ 
/* 292 */			 if (Block.byId[i2].e() == 1) break;
/* 293 */			 if (l1 == 12) {
/* 294 */				 return -1;
/*		 */			 }
/*		 */ 
/* 297 */			 i1 += Facing.b[l];
/* 298 */			 j1 += Facing.c[l];
/* 299 */			 k1 += Facing.d[l];
/* 300 */			 l1++;
/*		 */		 }
/*		 */ 
/* 306 */		 return l1;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean j(World world, int i, int j, int k, int l)
/*		 */	 {
/* 311 */		 int i1 = i + Facing.b[l];
/* 312 */		 int j1 = j + Facing.c[l];
/* 313 */		 int k1 = k + Facing.d[l];
/* 314 */		 int l1 = 0;
/*		 */ 
/* 319 */		 while (l1 < 13) {
/* 320 */			 if ((j1 <= 0) || (j1 >= 255)) {
/* 321 */				 return false;
/*		 */			 }
/*		 */ 
/* 324 */			 int i2 = world.getTypeId(i1, j1, k1);
/* 325 */			 if (i2 == 0) break;
/* 326 */			 if (!a(i2, world, i1, j1, k1, true)) {
/* 327 */				 return false;
/*		 */			 }
/*		 */ 
/* 330 */			 if (Block.byId[i2].e() != 1) {
/* 331 */				 if (l1 == 12) {
/* 332 */					 return false;
/*		 */				 }
/*		 */ 
/* 335 */				 i1 += Facing.b[l];
/* 336 */				 j1 += Facing.c[l];
/* 337 */				 k1 += Facing.d[l];
/* 338 */				 l1++;
/* 339 */				 continue;
/*		 */			 }
/*		 */ 
/* 342 */			 Block.byId[i2].c(world, i1, j1, k1, world.getData(i1, j1, k1), 0);
/* 343 */			 world.setTypeId(i1, j1, k1, 0);
/*		 */		 }
/*		 */ 
/* 347 */		 while ((i1 != i) || (j1 != j) || (k1 != k)) {
/* 348 */			 l1 = i1 - Facing.b[l];
/* 349 */			 int i2 = j1 - Facing.c[l];
/* 350 */			 int j2 = k1 - Facing.d[l];
/* 351 */			 int k2 = world.getTypeId(l1, i2, j2);
/* 352 */			 int l2 = world.getData(l1, i2, j2);
/*		 */ 
/* 354 */			 if ((k2 == this.id) && (l1 == i) && (i2 == j) && (j2 == k)) {
/* 355 */				 world.setRawTypeIdAndData(i1, j1, k1, Block.PISTON_MOVING.id, l | (this.a ? 8 : 0), false);
/* 356 */				 world.setTileEntity(i1, j1, k1, BlockPistonMoving.a(Block.PISTON_EXTENSION.id, l | (this.a ? 8 : 0), l, true, false));
/*		 */			 } else {
/* 358 */				 world.setRawTypeIdAndData(i1, j1, k1, Block.PISTON_MOVING.id, l2, false);
/* 359 */				 world.setTileEntity(i1, j1, k1, BlockPistonMoving.a(k2, l2, l, true, false));
/*		 */			 }
/*		 */ 
/* 362 */			 i1 = l1;
/* 363 */			 j1 = i2;
/* 364 */			 k1 = j2;
/*		 */		 }
/*		 */ 
/* 367 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockPiston
 * JD-Core Version:		0.6.0
 */