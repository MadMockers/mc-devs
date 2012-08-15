/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.LinkedHashSet;
/*		 */ import java.util.Random;
/*		 */ import java.util.Set;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockRedstoneWire extends Block
/*		 */ {
/*	13 */	 private boolean a = true;
/*	14 */	 private Set b = new LinkedHashSet();
/*		 */ 
/*		 */	 public BlockRedstoneWire(int i, int j) {
/*	17 */		 super(i, j, Material.ORIENTABLE);
/*	18 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/*	22 */		 return this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	26 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	30 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	34 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	38 */		 return 5;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	42 */		 return (world.t(i, j - 1, k)) || (world.getTypeId(i, j - 1, k) == Block.GLOWSTONE.id);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k) {
/*	46 */		 a(world, i, j, k, i, j, k);
/*	47 */		 ArrayList arraylist = new ArrayList(this.b);
/*		 */ 
/*	49 */		 this.b.clear();
/*	50 */		 Iterator iterator = arraylist.iterator();
/*		 */ 
/*	52 */		 while (iterator.hasNext()) {
/*	53 */			 ChunkPosition chunkposition = (ChunkPosition)iterator.next();
/*		 */ 
/*	55 */			 world.applyPhysics(chunkposition.x, chunkposition.y, chunkposition.z, this.id);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(World world, int i, int j, int k, int l, int i1, int j1) {
/*	60 */		 int k1 = world.getData(i, j, k);
/*	61 */		 int l1 = 0;
/*		 */ 
/*	63 */		 this.a = false;
/*	64 */		 boolean flag = world.isBlockIndirectlyPowered(i, j, k);
/*		 */ 
/*	66 */		 this.a = true;
/*		 */ 
/*	71 */		 if (flag) {
/*	72 */			 l1 = 15;
/*		 */		 } else {
/*	74 */			 for (int i2 = 0; i2 < 4; i2++) {
/*	75 */				 int j2 = i;
/*	76 */				 int k2 = k;
/*	77 */				 if (i2 == 0) {
/*	78 */					 j2 = i - 1;
/*		 */				 }
/*		 */ 
/*	81 */				 if (i2 == 1) {
/*	82 */					 j2++;
/*		 */				 }
/*		 */ 
/*	85 */				 if (i2 == 2) {
/*	86 */					 k2 = k - 1;
/*		 */				 }
/*		 */ 
/*	89 */				 if (i2 == 3) {
/*	90 */					 k2++;
/*		 */				 }
/*		 */ 
/*	93 */				 if ((j2 != l) || (j != i1) || (k2 != j1)) {
/*	94 */					 l1 = getPower(world, j2, j, k2, l1);
/*		 */				 }
/*		 */ 
/*	97 */				 if ((world.s(j2, j, k2)) && (!world.s(i, j + 1, k))) {
/*	98 */					 if ((j2 != l) || (j + 1 != i1) || (k2 != j1))
/*	99 */						 l1 = getPower(world, j2, j + 1, k2, l1);
/*		 */				 }
/* 101 */				 else if ((!world.s(j2, j, k2)) && ((j2 != l) || (j - 1 != i1) || (k2 != j1))) {
/* 102 */					 l1 = getPower(world, j2, j - 1, k2, l1);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 106 */			 if (l1 > 0)
/* 107 */				 l1--;
/*		 */			 else {
/* 109 */				 l1 = 0;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 114 */		 if (k1 != l1) {
/* 115 */			 BlockRedstoneEvent event = new BlockRedstoneEvent(world.getWorld().getBlockAt(i, j, k), k1, l1);
/* 116 */			 world.getServer().getPluginManager().callEvent(event);
/*		 */ 
/* 118 */			 l1 = event.getNewCurrent();
/*		 */		 }
/*		 */ 
/* 122 */		 if (k1 != l1) {
/* 123 */			 world.suppressPhysics = true;
/* 124 */			 world.setData(i, j, k, l1);
/* 125 */			 world.d(i, j, k, i, j, k);
/* 126 */			 world.suppressPhysics = false;
/*		 */ 
/* 128 */			 for (int i2 = 0; i2 < 4; i2++) {
/* 129 */				 int j2 = i;
/* 130 */				 int k2 = k;
/* 131 */				 int l2 = j - 1;
/*		 */ 
/* 133 */				 if (i2 == 0) {
/* 134 */					 j2 = i - 1;
/*		 */				 }
/*		 */ 
/* 137 */				 if (i2 == 1) {
/* 138 */					 j2++;
/*		 */				 }
/*		 */ 
/* 141 */				 if (i2 == 2) {
/* 142 */					 k2 = k - 1;
/*		 */				 }
/*		 */ 
/* 145 */				 if (i2 == 3) {
/* 146 */					 k2++;
/*		 */				 }
/*		 */ 
/* 149 */				 if (world.s(j2, j, k2)) {
/* 150 */					 l2 += 2;
/*		 */				 }
/*		 */ 
/* 153 */				 boolean flag1 = false;
/* 154 */				 int i3 = getPower(world, j2, j, k2, -1);
/*		 */ 
/* 156 */				 l1 = world.getData(i, j, k);
/* 157 */				 if (l1 > 0) {
/* 158 */					 l1--;
/*		 */				 }
/*		 */ 
/* 161 */				 if ((i3 >= 0) && (i3 != l1)) {
/* 162 */					 a(world, j2, j, k2, i, j, k);
/*		 */				 }
/*		 */ 
/* 165 */				 i3 = getPower(world, j2, l2, k2, -1);
/* 166 */				 l1 = world.getData(i, j, k);
/* 167 */				 if (l1 > 0) {
/* 168 */					 l1--;
/*		 */				 }
/*		 */ 
/* 171 */				 if ((i3 >= 0) && (i3 != l1)) {
/* 172 */					 a(world, j2, l2, k2, i, j, k);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 176 */			 if ((k1 < l1) || (l1 == 0)) {
/* 177 */				 this.b.add(new ChunkPosition(i, j, k));
/* 178 */				 this.b.add(new ChunkPosition(i - 1, j, k));
/* 179 */				 this.b.add(new ChunkPosition(i + 1, j, k));
/* 180 */				 this.b.add(new ChunkPosition(i, j - 1, k));
/* 181 */				 this.b.add(new ChunkPosition(i, j + 1, k));
/* 182 */				 this.b.add(new ChunkPosition(i, j, k - 1));
/* 183 */				 this.b.add(new ChunkPosition(i, j, k + 1));
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void n(World world, int i, int j, int k) {
/* 189 */		 if (world.getTypeId(i, j, k) == this.id) {
/* 190 */			 world.applyPhysics(i, j, k, this.id);
/* 191 */			 world.applyPhysics(i - 1, j, k, this.id);
/* 192 */			 world.applyPhysics(i + 1, j, k, this.id);
/* 193 */			 world.applyPhysics(i, j, k - 1, this.id);
/* 194 */			 world.applyPhysics(i, j, k + 1, this.id);
/* 195 */			 world.applyPhysics(i, j - 1, k, this.id);
/* 196 */			 world.applyPhysics(i, j + 1, k, this.id);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/* 201 */		 if (world.suppressPhysics) return;
/* 202 */		 super.onPlace(world, i, j, k);
/* 203 */		 if (!world.isStatic) {
/* 204 */			 l(world, i, j, k);
/* 205 */			 world.applyPhysics(i, j + 1, k, this.id);
/* 206 */			 world.applyPhysics(i, j - 1, k, this.id);
/* 207 */			 n(world, i - 1, j, k);
/* 208 */			 n(world, i + 1, j, k);
/* 209 */			 n(world, i, j, k - 1);
/* 210 */			 n(world, i, j, k + 1);
/* 211 */			 if (world.s(i - 1, j, k))
/* 212 */				 n(world, i - 1, j + 1, k);
/*		 */			 else {
/* 214 */				 n(world, i - 1, j - 1, k);
/*		 */			 }
/*		 */ 
/* 217 */			 if (world.s(i + 1, j, k))
/* 218 */				 n(world, i + 1, j + 1, k);
/*		 */			 else {
/* 220 */				 n(world, i + 1, j - 1, k);
/*		 */			 }
/*		 */ 
/* 223 */			 if (world.s(i, j, k - 1))
/* 224 */				 n(world, i, j + 1, k - 1);
/*		 */			 else {
/* 226 */				 n(world, i, j - 1, k - 1);
/*		 */			 }
/*		 */ 
/* 229 */			 if (world.s(i, j, k + 1))
/* 230 */				 n(world, i, j + 1, k + 1);
/*		 */			 else
/* 232 */				 n(world, i, j - 1, k + 1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1)
/*		 */	 {
/* 238 */		 super.remove(world, i, j, k, l, i1);
/* 239 */		 if (!world.isStatic) {
/* 240 */			 world.applyPhysics(i, j + 1, k, this.id);
/* 241 */			 world.applyPhysics(i, j - 1, k, this.id);
/* 242 */			 world.applyPhysics(i + 1, j, k, this.id);
/* 243 */			 world.applyPhysics(i - 1, j, k, this.id);
/* 244 */			 world.applyPhysics(i, j, k + 1, this.id);
/* 245 */			 world.applyPhysics(i, j, k - 1, this.id);
/* 246 */			 l(world, i, j, k);
/* 247 */			 n(world, i - 1, j, k);
/* 248 */			 n(world, i + 1, j, k);
/* 249 */			 n(world, i, j, k - 1);
/* 250 */			 n(world, i, j, k + 1);
/* 251 */			 if (world.s(i - 1, j, k))
/* 252 */				 n(world, i - 1, j + 1, k);
/*		 */			 else {
/* 254 */				 n(world, i - 1, j - 1, k);
/*		 */			 }
/*		 */ 
/* 257 */			 if (world.s(i + 1, j, k))
/* 258 */				 n(world, i + 1, j + 1, k);
/*		 */			 else {
/* 260 */				 n(world, i + 1, j - 1, k);
/*		 */			 }
/*		 */ 
/* 263 */			 if (world.s(i, j, k - 1))
/* 264 */				 n(world, i, j + 1, k - 1);
/*		 */			 else {
/* 266 */				 n(world, i, j - 1, k - 1);
/*		 */			 }
/*		 */ 
/* 269 */			 if (world.s(i, j, k + 1))
/* 270 */				 n(world, i, j + 1, k + 1);
/*		 */			 else
/* 272 */				 n(world, i, j - 1, k + 1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getPower(World world, int i, int j, int k, int l)
/*		 */	 {
/* 279 */		 if (world.getTypeId(i, j, k) != this.id) {
/* 280 */			 return l;
/*		 */		 }
/* 282 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/* 284 */		 return i1 > l ? i1 : l;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l)
/*		 */	 {
/* 289 */		 if (!world.isStatic) {
/* 290 */			 int i1 = world.getData(i, j, k);
/* 291 */			 boolean flag = canPlace(world, i, j, k);
/*		 */ 
/* 293 */			 if (flag) {
/* 294 */				 l(world, i, j, k);
/*		 */			 } else {
/* 296 */				 c(world, i, j, k, i1, 0);
/* 297 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */ 
/* 300 */			 super.doPhysics(world, i, j, k, l);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j) {
/* 305 */		 return Item.REDSTONE.id;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 309 */		 return !this.a ? false : a(world, i, j, k, l);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 313 */		 if (!this.a)
/* 314 */			 return false;
/* 315 */		 if (iblockaccess.getData(i, j, k) == 0)
/* 316 */			 return false;
/* 317 */		 if (l == 1) {
/* 318 */			 return true;
/*		 */		 }
/* 320 */		 boolean flag = (f(iblockaccess, i - 1, j, k, 1)) || ((!iblockaccess.s(i - 1, j, k)) && (f(iblockaccess, i - 1, j - 1, k, -1)));
/* 321 */		 boolean flag1 = (f(iblockaccess, i + 1, j, k, 3)) || ((!iblockaccess.s(i + 1, j, k)) && (f(iblockaccess, i + 1, j - 1, k, -1)));
/* 322 */		 boolean flag2 = (f(iblockaccess, i, j, k - 1, 2)) || ((!iblockaccess.s(i, j, k - 1)) && (f(iblockaccess, i, j - 1, k - 1, -1)));
/* 323 */		 boolean flag3 = (f(iblockaccess, i, j, k + 1, 0)) || ((!iblockaccess.s(i, j, k + 1)) && (f(iblockaccess, i, j - 1, k + 1, -1)));
/*		 */ 
/* 325 */		 if (!iblockaccess.s(i, j + 1, k)) {
/* 326 */			 if ((iblockaccess.s(i - 1, j, k)) && (f(iblockaccess, i - 1, j + 1, k, -1))) {
/* 327 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 330 */			 if ((iblockaccess.s(i + 1, j, k)) && (f(iblockaccess, i + 1, j + 1, k, -1))) {
/* 331 */				 flag1 = true;
/*		 */			 }
/*		 */ 
/* 334 */			 if ((iblockaccess.s(i, j, k - 1)) && (f(iblockaccess, i, j + 1, k - 1, -1))) {
/* 335 */				 flag2 = true;
/*		 */			 }
/*		 */ 
/* 338 */			 if ((iblockaccess.s(i, j, k + 1)) && (f(iblockaccess, i, j + 1, k + 1, -1))) {
/* 339 */				 flag3 = true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 343 */		 return (!flag2) && (!flag1) && (!flag) && (!flag3) && (l >= 2) && (l <= 5);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource()
/*		 */	 {
/* 348 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean e(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 352 */		 int i1 = iblockaccess.getTypeId(i, j, k);
/*		 */ 
/* 354 */		 if (i1 == Block.REDSTONE_WIRE.id)
/* 355 */			 return true;
/* 356 */		 if (i1 == 0)
/* 357 */			 return false;
/* 358 */		 if ((i1 != Block.DIODE_OFF.id) && (i1 != Block.DIODE_ON.id)) {
/* 359 */			 return (Block.byId[i1].isPowerSource()) && (l != -1);
/*		 */		 }
/* 361 */		 int j1 = iblockaccess.getData(i, j, k);
/*		 */ 
/* 363 */		 return (l == (j1 & 0x3)) || (l == Direction.e[(j1 & 0x3)]);
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean f(IBlockAccess iblockaccess, int i, int j, int k, int l)
/*		 */	 {
/* 368 */		 if (e(iblockaccess, i, j, k, l)) {
/* 369 */			 return true;
/*		 */		 }
/* 371 */		 int i1 = iblockaccess.getTypeId(i, j, k);
/*		 */ 
/* 373 */		 if (i1 == Block.DIODE_ON.id) {
/* 374 */			 int j1 = iblockaccess.getData(i, j, k);
/*		 */ 
/* 376 */			 return l == (j1 & 0x3);
/*		 */		 }
/* 378 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockRedstoneWire
 * JD-Core Version:		0.6.0
 */