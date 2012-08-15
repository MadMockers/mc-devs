/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockTripwireHook extends Block
/*		 */ {
/*		 */	 public BlockTripwireHook(int i)
/*		 */	 {
/*	10 */		 super(i, 172, Material.ORIENTABLE);
/*	11 */		 a(CreativeModeTab.d);
/*	12 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	16 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	20 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	24 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	28 */		 return 29;
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	32 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k, int l) {
/*	36 */		 return (l == 2) && (world.s(i, j, k + 1));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	40 */		 return world.s(i, j, k - 1) ? true : world.s(i + 1, j, k) ? true : world.s(i - 1, j, k) ? true : world.s(i, j, k + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	44 */		 byte b0 = 0;
/*		 */ 
/*	46 */		 if ((l == 2) && (world.b(i, j, k + 1, true))) {
/*	47 */			 b0 = 2;
/*		 */		 }
/*		 */ 
/*	50 */		 if ((l == 3) && (world.b(i, j, k - 1, true))) {
/*	51 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/*	54 */		 if ((l == 4) && (world.b(i + 1, j, k, true))) {
/*	55 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/*	58 */		 if ((l == 5) && (world.b(i - 1, j, k, true))) {
/*	59 */			 b0 = 3;
/*		 */		 }
/*		 */ 
/*	62 */		 a(world, i, j, k, this.id, b0, false, -1, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	66 */		 if ((l != this.id) && 
/*	67 */			 (l(world, i, j, k))) {
/*	68 */			 int i1 = world.getData(i, j, k);
/*	69 */			 int j1 = i1 & 0x3;
/*	70 */			 boolean flag = false;
/*		 */ 
/*	72 */			 if ((!world.s(i - 1, j, k)) && (j1 == 3)) {
/*	73 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	76 */			 if ((!world.s(i + 1, j, k)) && (j1 == 1)) {
/*	77 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	80 */			 if ((!world.s(i, j, k - 1)) && (j1 == 0)) {
/*	81 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	84 */			 if ((!world.s(i, j, k + 1)) && (j1 == 2)) {
/*	85 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	88 */			 if (flag) {
/*	89 */				 c(world, i, j, k, i1, 0);
/*	90 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, int l, int i1, boolean flag, int j1, int k1)
/*		 */	 {
/*	97 */		 int l1 = i1 & 0x3;
/*	98 */		 boolean flag1 = (i1 & 0x4) == 4;
/*	99 */		 boolean flag2 = (i1 & 0x8) == 8;
/* 100 */		 boolean flag3 = l == Block.TRIPWIRE_SOURCE.id;
/* 101 */		 boolean flag4 = false;
/* 102 */		 boolean flag5 = !world.t(i, j - 1, k);
/* 103 */		 int i2 = Direction.a[l1];
/* 104 */		 int j2 = Direction.b[l1];
/* 105 */		 int k2 = 0;
/* 106 */		 int[] aint = new int[42];
/*		 */ 
/* 114 */		 for (int i3 = 1; i3 < 42; i3++) {
/* 115 */			 int l2 = i + i2 * i3;
/* 116 */			 int k3 = k + j2 * i3;
/* 117 */			 int j3 = world.getTypeId(l2, j, k3);
/* 118 */			 if (j3 == Block.TRIPWIRE_SOURCE.id) {
/* 119 */				 int l3 = world.getData(l2, j, k3);
/* 120 */				 if ((l3 & 0x3) != Direction.e[l1]) break;
/* 121 */				 k2 = i3; break;
/*		 */			 }
/*		 */ 
/* 126 */			 if ((j3 != Block.TRIPWIRE.id) && (i3 != j1)) {
/* 127 */				 aint[i3] = -1;
/* 128 */				 flag3 = false;
/*		 */			 } else {
/* 130 */				 int l3 = i3 == j1 ? k1 : world.getData(l2, j, k3);
/* 131 */				 boolean flag6 = (l3 & 0x8) != 8;
/* 132 */				 boolean flag7 = (l3 & 0x1) == 1;
/* 133 */				 boolean flag8 = (l3 & 0x2) == 2;
/*		 */ 
/* 135 */				 flag3 &= flag8 == flag5;
/* 136 */				 flag4 |= ((flag6) && (flag7));
/* 137 */				 aint[i3] = l3;
/* 138 */				 if (i3 == j1) {
/* 139 */					 world.a(i, j, k, l, p_());
/* 140 */					 flag3 &= flag6;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 145 */		 flag3 &= k2 > 1;
/* 146 */		 flag4 &= flag3;
/* 147 */		 i3 = (flag3 ? 4 : 0) | (flag4 ? 8 : 0);
/* 148 */		 i1 = l1 | i3;
/* 149 */		 if (k2 > 0) {
/* 150 */			 int l2 = i + i2 * k2;
/* 151 */			 int k3 = k + j2 * k2;
/* 152 */			 int j3 = Direction.e[l1];
/* 153 */			 world.setData(l2, j, k3, j3 | i3);
/* 154 */			 e(world, l2, j, k3, j3);
/* 155 */			 a(world, l2, j, k3, flag3, flag4, flag1, flag2);
/*		 */		 }
/*		 */ 
/* 159 */		 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/* 161 */		 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 1, 0);
/* 162 */		 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */ 
/* 164 */		 if (eventRedstone.getNewCurrent() > 0) {
/* 165 */			 return;
/*		 */		 }
/*		 */ 
/* 169 */		 a(world, i, j, k, flag3, flag4, flag1, flag2);
/* 170 */		 if (l > 0) {
/* 171 */			 world.setData(i, j, k, i1);
/* 172 */			 if (flag) {
/* 173 */				 e(world, i, j, k, l1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 177 */		 if (flag1 != flag3)
/* 178 */			 for (int l2 = 1; l2 < k2; l2++) {
/* 179 */				 int k3 = i + i2 * l2;
/* 180 */				 int j3 = k + j2 * l2;
/* 181 */				 int l3 = aint[l2];
/* 182 */				 if (l3 >= 0) {
/* 183 */					 if (flag3)
/* 184 */						 l3 |= 4;
/*		 */					 else {
/* 186 */						 l3 &= -5;
/*		 */					 }
/*		 */ 
/* 189 */					 world.setData(k3, j, j3, l3);
/*		 */				 }
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random)
/*		 */	 {
/* 196 */		 a(world, i, j, k, this.id, world.getData(i, j, k), true, -1, 0);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(World world, int i, int j, int k, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
/* 200 */		 if ((flag1) && (!flag3))
/* 201 */			 world.makeSound(i + 0.5D, j + 0.1D, k + 0.5D, "random.click", 0.4F, 0.6F);
/* 202 */		 else if ((!flag1) && (flag3))
/* 203 */			 world.makeSound(i + 0.5D, j + 0.1D, k + 0.5D, "random.click", 0.4F, 0.5F);
/* 204 */		 else if ((flag) && (!flag2))
/* 205 */			 world.makeSound(i + 0.5D, j + 0.1D, k + 0.5D, "random.click", 0.4F, 0.7F);
/* 206 */		 else if ((!flag) && (flag2))
/* 207 */			 world.makeSound(i + 0.5D, j + 0.1D, k + 0.5D, "random.bowhit", 0.4F, 1.2F / (world.random.nextFloat() * 0.2F + 0.9F));
/*		 */	 }
/*		 */ 
/*		 */	 private void e(World world, int i, int j, int k, int l)
/*		 */	 {
/* 212 */		 world.applyPhysics(i, j, k, this.id);
/* 213 */		 if (l == 3)
/* 214 */			 world.applyPhysics(i - 1, j, k, this.id);
/* 215 */		 else if (l == 1)
/* 216 */			 world.applyPhysics(i + 1, j, k, this.id);
/* 217 */		 else if (l == 0)
/* 218 */			 world.applyPhysics(i, j, k - 1, this.id);
/* 219 */		 else if (l == 2)
/* 220 */			 world.applyPhysics(i, j, k + 1, this.id);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World world, int i, int j, int k)
/*		 */	 {
/* 225 */		 if (!canPlace(world, i, j, k)) {
/* 226 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/* 227 */			 world.setTypeId(i, j, k, 0);
/* 228 */			 return false;
/*		 */		 }
/* 230 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
/*		 */	 {
/* 235 */		 int l = iblockaccess.getData(i, j, k) & 0x3;
/* 236 */		 float f = 0.1875F;
/*		 */ 
/* 238 */		 if (l == 3)
/* 239 */			 a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
/* 240 */		 else if (l == 1)
/* 241 */			 a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
/* 242 */		 else if (l == 0)
/* 243 */			 a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
/* 244 */		 else if (l == 2)
/* 245 */			 a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1)
/*		 */	 {
/* 250 */		 boolean flag = (i1 & 0x4) == 4;
/* 251 */		 boolean flag1 = (i1 & 0x8) == 8;
/*		 */ 
/* 253 */		 if ((flag) || (flag1)) {
/* 254 */			 a(world, i, j, k, 0, i1, false, -1, 0);
/*		 */		 }
/*		 */ 
/* 257 */		 if (flag1) {
/* 258 */			 world.applyPhysics(i, j, k, this.id);
/* 259 */			 int j1 = i1 & 0x3;
/*		 */ 
/* 261 */			 if (j1 == 3)
/* 262 */				 world.applyPhysics(i - 1, j, k, this.id);
/* 263 */			 else if (j1 == 1)
/* 264 */				 world.applyPhysics(i + 1, j, k, this.id);
/* 265 */			 else if (j1 == 0)
/* 266 */				 world.applyPhysics(i, j, k - 1, this.id);
/* 267 */			 else if (j1 == 2) {
/* 268 */				 world.applyPhysics(i, j, k + 1, this.id);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 272 */		 super.remove(world, i, j, k, l, i1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 276 */		 return (iblockaccess.getData(i, j, k) & 0x8) == 8;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 280 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/* 282 */		 if ((i1 & 0x8) != 8) {
/* 283 */			 return false;
/*		 */		 }
/* 285 */		 int j1 = i1 & 0x3;
/*		 */ 
/* 287 */		 return (j1 == 2) && (l == 2);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource()
/*		 */	 {
/* 292 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockTripwireHook
 * JD-Core Version:		0.6.0
 */