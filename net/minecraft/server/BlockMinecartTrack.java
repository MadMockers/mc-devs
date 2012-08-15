/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockMinecartTrack extends Block
/*		 */ {
/*		 */	 private final boolean a;
/*		 */ 
/*		 */	 public static final boolean d_(World world, int i, int j, int k)
/*		 */	 {
/*	10 */		 int l = world.getTypeId(i, j, k);
/*		 */ 
/*	12 */		 return (l == Block.RAILS.id) || (l == Block.GOLDEN_RAIL.id) || (l == Block.DETECTOR_RAIL.id);
/*		 */	 }
/*		 */ 
/*		 */	 public static final boolean d(int i) {
/*	16 */		 return (i == Block.RAILS.id) || (i == Block.GOLDEN_RAIL.id) || (i == Block.DETECTOR_RAIL.id);
/*		 */	 }
/*		 */ 
/*		 */	 protected BlockMinecartTrack(int i, int j, boolean flag) {
/*	20 */		 super(i, j, Material.ORIENTABLE);
/*	21 */		 this.a = flag;
/*	22 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*	23 */		 a(CreativeModeTab.e);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean n() {
/*	27 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k) {
/*	31 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/*	35 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
/*	39 */		 updateShape(world, i, j, k);
/*	40 */		 return super.a(world, i, j, k, vec3d, vec3d1);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/*	44 */		 int l = iblockaccess.getData(i, j, k);
/*		 */ 
/*	46 */		 if ((l >= 2) && (l <= 5))
/*	47 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
/*		 */		 else
/*	49 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j)
/*		 */	 {
/*	54 */		 if (this.a) {
/*	55 */			 if ((this.id == Block.GOLDEN_RAIL.id) && ((j & 0x8) == 0))
/*	56 */				 return this.textureId - 16;
/*		 */		 }
/*	58 */		 else if (j >= 6) {
/*	59 */			 return this.textureId - 16;
/*		 */		 }
/*		 */ 
/*	62 */		 return this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/*	66 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/*	70 */		 return 9;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/*	74 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/*	78 */		 return world.t(i, j - 1, k);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/*	82 */		 if (!world.isStatic) {
/*	83 */			 a(world, i, j, k, true);
/*	84 */			 if (this.id != Block.GOLDEN_RAIL.id);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*	91 */		 if (!world.isStatic) {
/*	92 */			 int i1 = world.getData(i, j, k);
/*	93 */			 int j1 = i1;
/*		 */ 
/*	95 */			 if (this.a) {
/*	96 */				 j1 = i1 & 0x7;
/*		 */			 }
/*		 */ 
/*	99 */			 boolean flag = false;
/*		 */ 
/* 101 */			 if (!world.t(i, j - 1, k)) {
/* 102 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 105 */			 if ((j1 == 2) && (!world.t(i + 1, j, k))) {
/* 106 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 109 */			 if ((j1 == 3) && (!world.t(i - 1, j, k))) {
/* 110 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 113 */			 if ((j1 == 4) && (!world.t(i, j, k - 1))) {
/* 114 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 117 */			 if ((j1 == 5) && (!world.t(i, j, k + 1))) {
/* 118 */				 flag = true;
/*		 */			 }
/*		 */ 
/* 121 */			 if (flag) {
/* 122 */				 c(world, i, j, k, world.getData(i, j, k), 0);
/* 123 */				 world.setTypeId(i, j, k, 0);
/* 124 */			 } else if (this.id == Block.GOLDEN_RAIL.id) {
/* 125 */				 boolean flag1 = world.isBlockIndirectlyPowered(i, j, k);
/*		 */ 
/* 127 */				 flag1 = (flag1) || (a(world, i, j, k, i1, true, 0)) || (a(world, i, j, k, i1, false, 0));
/* 128 */				 boolean flag2 = false;
/*		 */ 
/* 130 */				 if ((flag1) && ((i1 & 0x8) == 0)) {
/* 131 */					 world.setData(i, j, k, j1 | 0x8);
/* 132 */					 flag2 = true;
/* 133 */				 } else if ((!flag1) && ((i1 & 0x8) != 0)) {
/* 134 */					 world.setData(i, j, k, j1);
/* 135 */					 flag2 = true;
/*		 */				 }
/*		 */ 
/* 138 */				 if (flag2) {
/* 139 */					 world.applyPhysics(i, j - 1, k, this.id);
/* 140 */					 if ((j1 == 2) || (j1 == 3) || (j1 == 4) || (j1 == 5))
/* 141 */						 world.applyPhysics(i, j + 1, k, this.id);
/*		 */				 }
/*		 */			 }
/* 144 */			 else if ((l > 0) && (Block.byId[l].isPowerSource()) && (!this.a) && (MinecartTrackLogic.a(new MinecartTrackLogic(this, world, i, j, k)) == 3)) {
/* 145 */				 a(world, i, j, k, false);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(World world, int i, int j, int k, boolean flag) {
/* 151 */		 if (!world.isStatic)
/* 152 */			 new MinecartTrackLogic(this, world, i, j, k).a(world.isBlockIndirectlyPowered(i, j, k), flag);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(World world, int i, int j, int k, int l, boolean flag, int i1)
/*		 */	 {
/* 157 */		 if (i1 >= 8) {
/* 158 */			 return false;
/*		 */		 }
/* 160 */		 int j1 = l & 0x7;
/* 161 */		 boolean flag1 = true;
/*		 */ 
/* 163 */		 switch (j1) {
/*		 */		 case 0:
/* 165 */			 if (flag)
/* 166 */				 k++;
/*		 */			 else {
/* 168 */				 k--;
/*		 */			 }
/* 170 */			 break;
/*		 */		 case 1:
/* 173 */			 if (flag)
/* 174 */				 i--;
/*		 */			 else {
/* 176 */				 i++;
/*		 */			 }
/* 178 */			 break;
/*		 */		 case 2:
/* 181 */			 if (flag) {
/* 182 */				 i--;
/*		 */			 } else {
/* 184 */				 i++;
/* 185 */				 j++;
/* 186 */				 flag1 = false;
/*		 */			 }
/*		 */ 
/* 189 */			 j1 = 1;
/* 190 */			 break;
/*		 */		 case 3:
/* 193 */			 if (flag) {
/* 194 */				 i--;
/* 195 */				 j++;
/* 196 */				 flag1 = false;
/*		 */			 } else {
/* 198 */				 i++;
/*		 */			 }
/*		 */ 
/* 201 */			 j1 = 1;
/* 202 */			 break;
/*		 */		 case 4:
/* 205 */			 if (flag) {
/* 206 */				 k++;
/*		 */			 } else {
/* 208 */				 k--;
/* 209 */				 j++;
/* 210 */				 flag1 = false;
/*		 */			 }
/*		 */ 
/* 213 */			 j1 = 0;
/* 214 */			 break;
/*		 */		 case 5:
/* 217 */			 if (flag) {
/* 218 */				 k++;
/* 219 */				 j++;
/* 220 */				 flag1 = false;
/*		 */			 } else {
/* 222 */				 k--;
/*		 */			 }
/*		 */ 
/* 225 */			 j1 = 0;
/*		 */		 }
/*		 */ 
/* 228 */		 return a(world, i, j, k, flag, i1, j1);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(World world, int i, int j, int k, boolean flag, int l, int i1)
/*		 */	 {
/* 233 */		 int j1 = world.getTypeId(i, j, k);
/*		 */ 
/* 235 */		 if (j1 == Block.GOLDEN_RAIL.id) {
/* 236 */			 int k1 = world.getData(i, j, k);
/* 237 */			 int l1 = k1 & 0x7;
/*		 */ 
/* 239 */			 if ((i1 == 1) && ((l1 == 0) || (l1 == 4) || (l1 == 5))) {
/* 240 */				 return false;
/*		 */			 }
/*		 */ 
/* 243 */			 if ((i1 == 0) && ((l1 == 1) || (l1 == 2) || (l1 == 3))) {
/* 244 */				 return false;
/*		 */			 }
/*		 */ 
/* 247 */			 if ((k1 & 0x8) != 0) {
/* 248 */				 if (world.isBlockIndirectlyPowered(i, j, k)) {
/* 249 */					 return true;
/*		 */				 }
/*		 */ 
/* 252 */				 return a(world, i, j, k, k1, flag, l + 1);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 256 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 260 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 static boolean a(BlockMinecartTrack blockminecarttrack) {
/* 264 */		 return blockminecarttrack.a;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockMinecartTrack
 * JD-Core Version:		0.6.0
 */