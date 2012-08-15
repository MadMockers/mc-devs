/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.event.block.BlockRedstoneEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockLever extends Block
/*		 */ {
/*		 */	 protected BlockLever(int i, int j)
/*		 */	 {
/*	 8 */		 super(i, j, Material.ORIENTABLE);
/*	 9 */		 a(CreativeModeTab.d);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	13 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	17 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	21 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	25 */		 return 12;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k, int l) {
/*	29 */		 return (l == 0) && (world.s(i, j + 1, k));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	33 */		 return world.t(i, j - 1, k) ? true : world.s(i, j, k + 1) ? true : world.s(i, j, k - 1) ? true : world.s(i + 1, j, k) ? true : world.s(i - 1, j, k) ? true : world.s(i, j + 1, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*	37 */		 int i1 = world.getData(i, j, k);
/*	38 */		 int j1 = i1 & 0x8;
/*		 */ 
/*	40 */		 i1 &= 7;
/*	41 */		 i1 = -1;
/*	42 */		 if ((l == 0) && (world.s(i, j + 1, k))) {
/*	43 */			 i1 = world.random.nextBoolean() ? 0 : 7;
/*		 */		 }
/*		 */ 
/*	46 */		 if ((l == 1) && (world.t(i, j - 1, k))) {
/*	47 */			 i1 = 5 + world.random.nextInt(2);
/*		 */		 }
/*		 */ 
/*	50 */		 if ((l == 2) && (world.s(i, j, k + 1))) {
/*	51 */			 i1 = 4;
/*		 */		 }
/*		 */ 
/*	54 */		 if ((l == 3) && (world.s(i, j, k - 1))) {
/*	55 */			 i1 = 3;
/*		 */		 }
/*		 */ 
/*	58 */		 if ((l == 4) && (world.s(i + 1, j, k))) {
/*	59 */			 i1 = 2;
/*		 */		 }
/*		 */ 
/*	62 */		 if ((l == 5) && (world.s(i - 1, j, k))) {
/*	63 */			 i1 = 1;
/*		 */		 }
/*		 */ 
/*	66 */		 if (i1 == -1) {
/*	67 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/*	68 */			 world.setTypeId(i, j, k, 0);
/*		 */		 } else {
/*	70 */			 world.setData(i, j, k, i1 + j1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static int d(int i) {
/*	75 */		 switch (i) {
/*		 */		 case 0:
/*	77 */			 return 0;
/*		 */		 case 1:
/*	80 */			 return 5;
/*		 */		 case 2:
/*	83 */			 return 4;
/*		 */		 case 3:
/*	86 */			 return 3;
/*		 */		 case 4:
/*	89 */			 return 2;
/*		 */		 case 5:
/*	92 */			 return 1;
/*		 */		 }
/*		 */ 
/*	95 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l)
/*		 */	 {
/* 100 */		 if (l(world, i, j, k)) {
/* 101 */			 int i1 = world.getData(i, j, k) & 0x7;
/* 102 */			 boolean flag = false;
/*		 */ 
/* 104 */			 if ((!world.s(i - 1, j, k)) && (i1 == 1)) {
/* 105 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 108 */			 if ((!world.s(i + 1, j, k)) && (i1 == 2)) {
/* 109 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 112 */			 if ((!world.s(i, j, k - 1)) && (i1 == 3)) {
/* 113 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 116 */			 if ((!world.s(i, j, k + 1)) && (i1 == 4)) {
/* 117 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 120 */			 if ((!world.t(i, j - 1, k)) && (i1 == 5)) {
/* 121 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 124 */			 if ((!world.t(i, j - 1, k)) && (i1 == 6)) {
/* 125 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 128 */			 if ((!world.s(i, j + 1, k)) && (i1 == 0)) {
/* 129 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 132 */			 if ((!world.s(i, j + 1, k)) && (i1 == 7)) {
/* 133 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 136 */			 if (flag) {
/* 137 */				 c(world, i, j, k, world.getData(i, j, k), 0);
/* 138 */				 world.setTypeId(i, j, k, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World world, int i, int j, int k) {
/* 144 */		 if (!canPlace(world, i, j, k)) {
/* 145 */			 c(world, i, j, k, world.getData(i, j, k), 0);
/* 146 */			 world.setTypeId(i, j, k, 0);
/* 147 */			 return false;
/*		 */		 }
/* 149 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
/*		 */	 {
/* 154 */		 int l = iblockaccess.getData(i, j, k) & 0x7;
/* 155 */		 float f = 0.1875F;
/*		 */ 
/* 157 */		 if (l == 1) {
/* 158 */			 a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
/* 159 */		 } else if (l == 2) {
/* 160 */			 a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
/* 161 */		 } else if (l == 3) {
/* 162 */			 a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
/* 163 */		 } else if (l == 4) {
/* 164 */			 a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
/* 165 */		 } else if ((l != 5) && (l != 6)) {
/* 166 */			 if ((l == 0) || (l == 7)) {
/* 167 */				 f = 0.25F;
/* 168 */				 a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
/*		 */			 }
/*		 */		 } else {
/* 171 */			 f = 0.25F;
/* 172 */			 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
/* 177 */		 interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/* 181 */		 if (world.isStatic) {
/* 182 */			 return true;
/*		 */		 }
/* 184 */		 int i1 = world.getData(i, j, k);
/* 185 */		 int j1 = i1 & 0x7;
/* 186 */		 int k1 = 8 - (i1 & 0x8);
/*		 */ 
/* 189 */		 org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
/* 190 */		 int old = k1 != 8 ? 1 : 0;
/* 191 */		 int current = k1 == 8 ? 1 : 0;
/*		 */ 
/* 193 */		 BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
/* 194 */		 world.getServer().getPluginManager().callEvent(eventRedstone);
/*		 */ 
/* 196 */		 if ((eventRedstone.getNewCurrent() > 0 ? 1 : 0) != (k1 == 8 ? 1 : 0)) {
/* 197 */			 return true;
/*		 */		 }
/*		 */ 
/* 201 */		 world.setData(i, j, k, j1 + k1);
/* 202 */		 world.d(i, j, k, i, j, k);
/* 203 */		 world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
/* 204 */		 world.applyPhysics(i, j, k, this.id);
/* 205 */		 if (j1 == 1)
/* 206 */			 world.applyPhysics(i - 1, j, k, this.id);
/* 207 */		 else if (j1 == 2)
/* 208 */			 world.applyPhysics(i + 1, j, k, this.id);
/* 209 */		 else if (j1 == 3)
/* 210 */			 world.applyPhysics(i, j, k - 1, this.id);
/* 211 */		 else if (j1 == 4)
/* 212 */			 world.applyPhysics(i, j, k + 1, this.id);
/* 213 */		 else if ((j1 != 5) && (j1 != 6)) {
/* 214 */			 if ((j1 == 0) || (j1 == 7))
/* 215 */				 world.applyPhysics(i, j + 1, k, this.id);
/*		 */		 }
/*		 */		 else {
/* 218 */			 world.applyPhysics(i, j - 1, k, this.id);
/*		 */		 }
/*		 */ 
/* 221 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1)
/*		 */	 {
/* 226 */		 if ((i1 & 0x8) > 0) {
/* 227 */			 world.applyPhysics(i, j, k, this.id);
/* 228 */			 int j1 = i1 & 0x7;
/*		 */ 
/* 230 */			 if (j1 == 1)
/* 231 */				 world.applyPhysics(i - 1, j, k, this.id);
/* 232 */			 else if (j1 == 2)
/* 233 */				 world.applyPhysics(i + 1, j, k, this.id);
/* 234 */			 else if (j1 == 3)
/* 235 */				 world.applyPhysics(i, j, k - 1, this.id);
/* 236 */			 else if (j1 == 4)
/* 237 */				 world.applyPhysics(i, j, k + 1, this.id);
/* 238 */			 else if ((j1 != 5) && (j1 != 6)) {
/* 239 */				 if ((j1 == 0) || (j1 == 7))
/* 240 */					 world.applyPhysics(i, j + 1, k, this.id);
/*		 */			 }
/*		 */			 else {
/* 243 */				 world.applyPhysics(i, j - 1, k, this.id);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 247 */		 super.remove(world, i, j, k, l, i1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 251 */		 return (iblockaccess.getData(i, j, k) & 0x8) > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 255 */		 int i1 = world.getData(i, j, k);
/*		 */ 
/* 257 */		 if ((i1 & 0x8) == 0) {
/* 258 */			 return false;
/*		 */		 }
/* 260 */		 int j1 = i1 & 0x7;
/*		 */ 
/* 262 */		 return (j1 == 0) && (l == 0);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource()
/*		 */	 {
/* 267 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockLever
 * JD-Core Version:		0.6.0
 */