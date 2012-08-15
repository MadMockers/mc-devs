/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.block.BlockFace;
/*		 */ import org.bukkit.event.block.BlockFromToEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class BlockFlowing extends BlockFluids
/*		 */ {
/*	12 */	 int a = 0;
/*	13 */	 boolean[] b = new boolean[4];
/*	14 */	 int[] c = new int[4];
/*		 */ 
/*		 */	 protected BlockFlowing(int i, Material material) {
/*	17 */		 super(i, material);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World world, int i, int j, int k) {
/*	21 */		 int l = world.getData(i, j, k);
/*		 */ 
/*	23 */		 world.setRawTypeIdAndData(i, j, k, this.id + 1, l);
/*	24 */		 world.d(i, j, k, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
/*	28 */		 return this.material != Material.LAVA;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World world, int i, int j, int k, Random random)
/*		 */	 {
/*	33 */		 org.bukkit.World bworld = world.getWorld();
/*	34 */		 Server server = world.getServer();
/*	35 */		 org.bukkit.block.Block source = bworld == null ? null : bworld.getBlockAt(i, j, k);
/*		 */ 
/*	38 */		 int l = f_(world, i, j, k);
/*	39 */		 byte b0 = 1;
/*		 */ 
/*	41 */		 if ((this.material == Material.LAVA) && (!world.worldProvider.d)) {
/*	42 */			 b0 = 2;
/*		 */		 }
/*		 */ 
/*	45 */		 boolean flag = true;
/*		 */ 
/*	48 */		 if (l > 0) {
/*	49 */			 byte b1 = -100;
/*		 */ 
/*	51 */			 this.a = 0;
/*	52 */			 int j1 = e(world, i - 1, j, k, b1);
/*		 */ 
/*	54 */			 j1 = e(world, i + 1, j, k, j1);
/*	55 */			 j1 = e(world, i, j, k - 1, j1);
/*	56 */			 j1 = e(world, i, j, k + 1, j1);
/*	57 */			 int i1 = j1 + b0;
/*	58 */			 if ((i1 >= 8) || (j1 < 0)) {
/*	59 */				 i1 = -1;
/*		 */			 }
/*		 */ 
/*	62 */			 if (f_(world, i, j + 1, k) >= 0) {
/*	63 */				 int k1 = f_(world, i, j + 1, k);
/*		 */ 
/*	65 */				 if (k1 >= 8)
/*	66 */					 i1 = k1;
/*		 */				 else {
/*	68 */					 i1 = k1 + 8;
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	72 */			 if ((this.a >= 2) && (this.material == Material.WATER)) {
/*	73 */				 if (world.getMaterial(i, j - 1, k).isBuildable())
/*	74 */					 i1 = 0;
/*	75 */				 else if ((world.getMaterial(i, j - 1, k) == this.material) && (world.getData(i, j, k) == 0)) {
/*	76 */					 i1 = 0;
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	80 */			 if ((this.material == Material.LAVA) && (l < 8) && (i1 < 8) && (i1 > l) && (random.nextInt(4) != 0)) {
/*	81 */				 i1 = l;
/*	82 */				 flag = false;
/*		 */			 }
/*		 */ 
/*	85 */			 if (i1 == l) {
/*	86 */				 if (flag)
/*	87 */					 l(world, i, j, k);
/*		 */			 }
/*		 */			 else {
/*	90 */				 l = i1;
/*	91 */				 if (i1 < 0) {
/*	92 */					 world.setTypeId(i, j, k, 0);
/*		 */				 } else {
/*	94 */					 world.setData(i, j, k, i1);
/*	95 */					 world.a(i, j, k, this.id, p_());
/*	96 */					 world.applyPhysics(i, j, k, this.id);
/*		 */				 }
/*		 */			 }
/*		 */		 } else {
/* 100 */			 l(world, i, j, k);
/*		 */		 }
/*		 */ 
/* 103 */		 if (p(world, i, j - 1, k))
/*		 */		 {
/* 105 */			 BlockFromToEvent event = new BlockFromToEvent(source, BlockFace.DOWN);
/* 106 */			 if (server != null) {
/* 107 */				 server.getPluginManager().callEvent(event);
/*		 */			 }
/*		 */ 
/* 110 */			 if (!event.isCancelled()) {
/* 111 */				 if ((this.material == Material.LAVA) && (world.getMaterial(i, j - 1, k) == Material.WATER)) {
/* 112 */					 world.setTypeId(i, j - 1, k, Block.STONE.id);
/* 113 */					 fizz(world, i, j - 1, k);
/* 114 */					 return;
/*		 */				 }
/*		 */ 
/* 117 */				 if (l >= 8)
/* 118 */					 flow(world, i, j - 1, k, l);
/*		 */				 else {
/* 120 */					 flow(world, i, j - 1, k, l + 8);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 124 */		 else if ((l >= 0) && ((l == 0) || (o(world, i, j - 1, k)))) {
/* 125 */			 boolean[] aboolean = n(world, i, j, k);
/*		 */ 
/* 127 */			 int i1 = l + b0;
/* 128 */			 if (l >= 8) {
/* 129 */				 i1 = 1;
/*		 */			 }
/*		 */ 
/* 132 */			 if (i1 >= 8) {
/* 133 */				 return;
/*		 */			 }
/*		 */ 
/* 137 */			 BlockFace[] faces = { BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST };
/* 138 */			 int index = 0;
/*		 */ 
/* 140 */			 for (BlockFace currentFace : faces) {
/* 141 */				 if (aboolean[index] != 0) {
/* 142 */					 BlockFromToEvent event = new BlockFromToEvent(source, currentFace);
/*		 */ 
/* 144 */					 if (server != null) {
/* 145 */						 server.getPluginManager().callEvent(event);
/*		 */					 }
/*		 */ 
/* 148 */					 if (!event.isCancelled()) {
/* 149 */						 flow(world, i + currentFace.getModX(), j, k + currentFace.getModZ(), i1);
/*		 */					 }
/*		 */				 }
/* 152 */				 index++;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void flow(World world, int i, int j, int k, int l)
/*		 */	 {
/* 159 */		 if (p(world, i, j, k)) {
/* 160 */			 int i1 = world.getTypeId(i, j, k);
/*		 */ 
/* 162 */			 if (i1 > 0) {
/* 163 */				 if (this.material == Material.LAVA)
/* 164 */					 fizz(world, i, j, k);
/*		 */				 else {
/* 166 */					 Block.byId[i1].c(world, i, j, k, world.getData(i, j, k), 0);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 170 */			 world.setTypeIdAndData(i, j, k, this.id, l);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private int d(World world, int i, int j, int k, int l, int i1) {
/* 175 */		 int j1 = 1000;
/*		 */ 
/* 177 */		 for (int k1 = 0; k1 < 4; k1++) {
/* 178 */			 if (((k1 != 0) || (i1 != 1)) && ((k1 != 1) || (i1 != 0)) && ((k1 != 2) || (i1 != 3)) && ((k1 != 3) || (i1 != 2))) {
/* 179 */				 int l1 = i;
/* 180 */				 int i2 = k;
/*		 */ 
/* 182 */				 if (k1 == 0) {
/* 183 */					 l1 = i - 1;
/*		 */				 }
/*		 */ 
/* 186 */				 if (k1 == 1) {
/* 187 */					 l1++;
/*		 */				 }
/*		 */ 
/* 190 */				 if (k1 == 2) {
/* 191 */					 i2 = k - 1;
/*		 */				 }
/*		 */ 
/* 194 */				 if (k1 == 3) {
/* 195 */					 i2++;
/*		 */				 }
/*		 */ 
/* 198 */				 if ((!o(world, l1, j, i2)) && ((world.getMaterial(l1, j, i2) != this.material) || (world.getData(l1, j, i2) != 0))) {
/* 199 */					 if (!o(world, l1, j - 1, i2)) {
/* 200 */						 return l;
/*		 */					 }
/*		 */ 
/* 203 */					 if (l < 4) {
/* 204 */						 int j2 = d(world, l1, j, i2, l + 1, k1);
/*		 */ 
/* 206 */						 if (j2 < j1) {
/* 207 */							 j1 = j2;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 214 */		 return j1;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean[] n(World world, int i, int j, int k)
/*		 */	 {
/* 221 */		 for (int l = 0; l < 4; l++) {
/* 222 */			 this.c[l] = 1000;
/* 223 */			 int i1 = i;
/* 224 */			 int j1 = k;
/*		 */ 
/* 226 */			 if (l == 0) {
/* 227 */				 i1 = i - 1;
/*		 */			 }
/*		 */ 
/* 230 */			 if (l == 1) {
/* 231 */				 i1++;
/*		 */			 }
/*		 */ 
/* 234 */			 if (l == 2) {
/* 235 */				 j1 = k - 1;
/*		 */			 }
/*		 */ 
/* 238 */			 if (l == 3) {
/* 239 */				 j1++;
/*		 */			 }
/*		 */ 
/* 242 */			 if ((!o(world, i1, j, j1)) && ((world.getMaterial(i1, j, j1) != this.material) || (world.getData(i1, j, j1) != 0))) {
/* 243 */				 if (o(world, i1, j - 1, j1))
/* 244 */					 this.c[l] = d(world, i1, j, j1, 1, l);
/*		 */				 else {
/* 246 */					 this.c[l] = 0;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 251 */		 l = this.c[0];
/*		 */ 
/* 253 */		 for (int i1 = 1; i1 < 4; i1++) {
/* 254 */			 if (this.c[i1] < l) {
/* 255 */				 l = this.c[i1];
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 259 */		 for (i1 = 0; i1 < 4; i1++) {
/* 260 */			 this.b[i1] = (this.c[i1] == l ? 1 : false);
/*		 */		 }
/*		 */ 
/* 263 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean o(World world, int i, int j, int k) {
/* 267 */		 int l = world.getTypeId(i, j, k);
/*		 */ 
/* 269 */		 if ((l != Block.WOODEN_DOOR.id) && (l != Block.IRON_DOOR_BLOCK.id) && (l != Block.SIGN_POST.id) && (l != Block.LADDER.id) && (l != Block.SUGAR_CANE_BLOCK.id)) {
/* 270 */			 if (l == 0) {
/* 271 */				 return false;
/*		 */			 }
/* 273 */			 Material material = Block.byId[l].material;
/*		 */ 
/* 275 */			 return material == Material.PORTAL ? true : material.isSolid();
/*		 */		 }
/*		 */ 
/* 278 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected int e(World world, int i, int j, int k, int l)
/*		 */	 {
/* 283 */		 int i1 = f_(world, i, j, k);
/*		 */ 
/* 285 */		 if (i1 < 0) {
/* 286 */			 return l;
/*		 */		 }
/* 288 */		 if (i1 == 0) {
/* 289 */			 this.a += 1;
/*		 */		 }
/*		 */ 
/* 292 */		 if (i1 >= 8) {
/* 293 */			 i1 = 0;
/*		 */		 }
/*		 */ 
/* 296 */		 return (l >= 0) && (i1 >= l) ? l : i1;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean p(World world, int i, int j, int k)
/*		 */	 {
/* 301 */		 Material material = world.getMaterial(i, j, k);
/*		 */ 
/* 303 */		 return material != this.material;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/* 307 */		 super.onPlace(world, i, j, k);
/* 308 */		 if (world.getTypeId(i, j, k) == this.id)
/* 309 */			 world.a(i, j, k, this.id, p_());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockFlowing
 * JD-Core Version:		0.6.0
 */