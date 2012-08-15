/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockButton extends Block
/*		 */ {
/*		 */	 protected BlockButton(int i, int j)
/*		 */	 {
/*	10 */		 super(i, j, Material.ORIENTABLE);
/*	11 */		 b(true);
/*	12 */		 a(CreativeModeTab.d);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	16 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/*	20 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	24 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	28 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k, int l) {
/*	32 */		 return (l == 2) && (world.s(i, j, k + 1));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	36 */		 return world.s(i, j, k - 1) ? true : world.s(i + 1, j, k) ? true : world.s(i - 1, j, k) ? true : world.s(i, j, k + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	40 */		 int i1 = world.getData(i, j, k);
/*	41 */		 int j1 = i1 & 0x8;
/*		 */ 
/*	43 */		 i1 &= 7;
/*	44 */		 if ((l == 2) && (world.s(i, j, k + 1)))
/*	45 */			 i1 = 4;
/*	46 */		 else if ((l == 3) && (world.s(i, j, k - 1)))
/*	47 */			 i1 = 3;
/*	48 */		 else if ((l == 4) && (world.s(i + 1, j, k)))
/*	49 */			 i1 = 2;
/*	50 */		 else if ((l == 5) && (world.s(i - 1, j, k)))
/*	51 */			 i1 = 1;
/*		 */		 else {
/*	53 */			 i1 = l(world, i, j, k);
/*		 */		 }
/*		 */ 
/*	56 */		 world.setData(i, j, k, i1 + j1);
/*		 */	 }
/*		 */ 
/*		 */	 private int l(World world, int i, int j, int k) {
/*	60 */		 return world.s(i, j, k + 1) ? 4 : world.s(i, j, k - 1) ? 3 : world.s(i + 1, j, k) ? 2 : world.s(i - 1, j, k) ? 1 : 1;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	64 */		 if (n(world, i, j, k)) {
/*	65 */			 int i1 = world.getData(i, j, k) & 0x7;
/*	66 */			 boolean flag = false;
/*		 */ 
/*	68 */			 if ((!world.s(i - 1, j, k)) && (i1 == 1)) {
/*	69 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	72 */			 if ((!world.s(i + 1, j, k)) && (i1 == 2)) {
/*	73 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	76 */			 if ((!world.s(i, j, k - 1)) && (i1 == 3)) {
/*	77 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	80 */			 if ((!world.s(i, j, k + 1)) && (i1 == 4)) {
/*	81 */				 flag = true;
/*		 */			 }
/*		 */ 
/*	84 */			 if (flag) {
/*	85 */				 c(world, i, j, k, world.getData(i, j, k), 0);
/*	86 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean n(World world, int i, int j, int k) {
/*	92 */		 if (!canPlace(world, i, j, k)) {
/*	93 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/*	94 */			 world.setTypeId(i, j, k, 0);
/*	95 */			 return false;
/*		 */		 }
/*	97 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
/*		 */	 {
/* 102 */		 int l = iblockaccess.getData(i, j, k);
/* 103 */		 int i1 = l & 0x7;
/* 104 */		 boolean flag = (l & 0x8) > 0;
/* 105 */		 float f = 0.375F;
/* 106 */		 float f1 = 0.625F;
/* 107 */		 float f2 = 0.1875F;
/* 108 */		 float f3 = 0.125F;
/*		 */ 
/* 110 */		 if (flag) {
/* 111 */			 f3 = 0.0625F;
/*		 */		 }
/*		 */ 
/* 114 */		 if (i1 == 1)
/* 115 */			 a(0.0F, f, 0.5F - f2, f3, f1, 0.5F + f2);
/* 116 */		 else if (i1 == 2)
/* 117 */			 a(1.0F - f3, f, 0.5F - f2, 1.0F, f1, 0.5F + f2);
/* 118 */		 else if (i1 == 3)
/* 119 */			 a(0.5F - f2, f, 0.0F, 0.5F + f2, f1, f3);
/* 120 */		 else if (i1 == 4)
/* 121 */			 a(0.5F - f2, f, 1.0F - f3, 0.5F + f2, f1, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman)
/*		 */	 {
/* 126 */		 interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/* 130 */		 int i1 = world.getData(i, j, k);
/* 131 */		 int j1 = i1 & 0x7;
/* 132 */		 int k1 = 8 - (i1 & 0x8);
/*		 */ 
/* 134 */		 if (k1 == 0) {
/* 135 */			 return true;
/*		 */		 }
/*		 */ 
/* 138 */		 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/* 139 */		 int old = k1 != 8 ? 1 : 0;
/* 140 */		 int current = k1 == 8 ? 1 : 0;
/*		 */ 
/* 142 */		 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
/* 143 */		 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */ 
/* 145 */		 if ((eventRedstone.getNewCurrent() > 0 ? 1 : 0) != (k1 == 8 ? 1 : 0)) {
/* 146 */			 return true;
/*		 */		 }
/*		 */ 
/* 150 */		 world.setData(i, j, k, j1 + k1);
/* 151 */		 world.d(i, j, k, i, j, k);
/* 152 */		 world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "random.click", 0.3F, 0.6F);
/* 153 */		 world.applyPhysics(i, j, k, this.id);
/* 154 */		 if (j1 == 1)
/* 155 */			 world.applyPhysics(i - 1, j, k, this.id);
/* 156 */		 else if (j1 == 2)
/* 157 */			 world.applyPhysics(i + 1, j, k, this.id);
/* 158 */		 else if (j1 == 3)
/* 159 */			 world.applyPhysics(i, j, k - 1, this.id);
/* 160 */		 else if (j1 == 4)
/* 161 */			 world.applyPhysics(i, j, k + 1, this.id);
/*		 */		 else {
/* 163 */			 world.applyPhysics(i, j - 1, k, this.id);
/*		 */		 }
/*		 */ 
/* 166 */		 world.a(i, j, k, this.id, p_());
/* 167 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1)
/*		 */	 {
/* 172 */		 if ((i1 & 0x8) > 0) {
/* 173 */			 world.applyPhysics(i, j, k, this.id);
/* 174 */			 int j1 = i1 & 0x7;
/*		 */ 
/* 176 */			 if (j1 == 1)
/* 177 */				 world.applyPhysics(i - 1, j, k, this.id);
/* 178 */			 else if (j1 == 2)
/* 179 */				 world.applyPhysics(i + 1, j, k, this.id);
/* 180 */			 else if (j1 == 3)
/* 181 */				 world.applyPhysics(i, j, k - 1, this.id);
/* 182 */			 else if (j1 == 4)
/* 183 */				 world.applyPhysics(i, j, k + 1, this.id);
/*		 */			 else {
/* 185 */				 world.applyPhysics(i, j - 1, k, this.id);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 189 */		 super.remove(world, i, j, k, l, i1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 193 */		 return (iblockaccess.getData(i, j, k) & 0x8) > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 197 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/* 199 */		 if ((i1 & 0x8) == 0) {
/* 200 */			 return false;
/*		 */		 }
/* 202 */		 int j1 = i1 & 0x7;
/*		 */ 
/* 204 */		 return (j1 == 5) && (l == 1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource()
/*		 */	 {
/* 209 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/* 213 */		 if (!world.isStatic) {
/* 214 */			 int l = world.getData(i, j, k);
/*		 */ 
/* 216 */			 if ((l & 0x8) != 0)
/*		 */			 {
/* 218 */				 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/*		 */ 
/* 220 */				 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 1, 0);
/* 221 */				 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */ 
/* 223 */				 if (eventRedstone.getNewCurrent() > 0) {
/* 224 */					 return;
/*		 */				 }
/*		 */ 
/* 228 */				 world.setData(i, j, k, l & 0x7);
/* 229 */				 world.applyPhysics(i, j, k, this.id);
/* 230 */				 int i1 = l & 0x7;
/*		 */ 
/* 232 */				 if (i1 == 1)
/* 233 */					 world.applyPhysics(i - 1, j, k, this.id);
/* 234 */				 else if (i1 == 2)
/* 235 */					 world.applyPhysics(i + 1, j, k, this.id);
/* 236 */				 else if (i1 == 3)
/* 237 */					 world.applyPhysics(i, j, k - 1, this.id);
/* 238 */				 else if (i1 == 4)
/* 239 */					 world.applyPhysics(i, j, k + 1, this.id);
/*		 */				 else {
/* 241 */					 world.applyPhysics(i, j - 1, k, this.id);
/*		 */				 }
/*		 */ 
/* 244 */				 world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "random.click", 0.3F, 0.5F);
/* 245 */				 world.d(i, j, k, i, j, k);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void f() {
/* 251 */		 float f = 0.1875F;
/* 252 */		 float f1 = 0.125F;
/* 253 */		 float f2 = 0.125F;
/*		 */ 
/* 255 */		 a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockButton
 * JD-Core Version:		0.6.0
 */