/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockVine extends Block
/*		 */ {
/*		 */	 public BlockVine(int paramInt)
/*		 */	 {
/*	21 */		 super(paramInt, 143, Material.REPLACEABLE_PLANT);
/*	22 */		 b(true);
/*	23 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/*	28 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	33 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	38 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	43 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	51 */		 int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	53 */		 float f1 = 1.0F;
/*	54 */		 float f2 = 1.0F;
/*	55 */		 float f3 = 1.0F;
/*	56 */		 float f4 = 0.0F;
/*	57 */		 float f5 = 0.0F;
/*	58 */		 float f6 = 0.0F;
/*	59 */		 int j = i > 0 ? 1 : 0;
/*		 */ 
/*	61 */		 if ((i & 0x2) != 0) {
/*	62 */			 f4 = Math.max(f4, 0.0625F);
/*	63 */			 f1 = 0.0F;
/*	64 */			 f2 = 0.0F;
/*	65 */			 f5 = 1.0F;
/*	66 */			 f3 = 0.0F;
/*	67 */			 f6 = 1.0F;
/*	68 */			 j = 1;
/*		 */		 }
/*	70 */		 if ((i & 0x8) != 0) {
/*	71 */			 f1 = Math.min(f1, 0.9375F);
/*	72 */			 f4 = 1.0F;
/*	73 */			 f2 = 0.0F;
/*	74 */			 f5 = 1.0F;
/*	75 */			 f3 = 0.0F;
/*	76 */			 f6 = 1.0F;
/*	77 */			 j = 1;
/*		 */		 }
/*	79 */		 if ((i & 0x4) != 0) {
/*	80 */			 f6 = Math.max(f6, 0.0625F);
/*	81 */			 f3 = 0.0F;
/*	82 */			 f1 = 0.0F;
/*	83 */			 f4 = 1.0F;
/*	84 */			 f2 = 0.0F;
/*	85 */			 f5 = 1.0F;
/*	86 */			 j = 1;
/*		 */		 }
/*	88 */		 if ((i & 0x1) != 0) {
/*	89 */			 f3 = Math.min(f3, 0.9375F);
/*	90 */			 f6 = 1.0F;
/*	91 */			 f1 = 0.0F;
/*	92 */			 f4 = 1.0F;
/*	93 */			 f2 = 0.0F;
/*	94 */			 f5 = 1.0F;
/*	95 */			 j = 1;
/*		 */		 }
/*	97 */		 if ((j == 0) && (e(paramIBlockAccess.getTypeId(paramInt1, paramInt2 + 1, paramInt3)))) {
/*	98 */			 f2 = Math.min(f2, 0.9375F);
/*	99 */			 f5 = 1.0F;
/* 100 */			 f1 = 0.0F;
/* 101 */			 f4 = 1.0F;
/* 102 */			 f3 = 0.0F;
/* 103 */			 f6 = 1.0F;
/*		 */		 }
/* 105 */		 a(f1, f2, f3, f4, f5, f6);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 110 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 115 */		 switch (paramInt4) {
/*		 */		 default:
/* 117 */			 return false;
/*		 */		 case 1:
/* 119 */			 return e(paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3));
/*		 */		 case 2:
/* 121 */			 return e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1));
/*		 */		 case 3:
/* 123 */			 return e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1));
/*		 */		 case 5:
/* 125 */			 return e(paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3));
/*		 */		 case 4:
/* 127 */		 }return e(paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3));
/*		 */	 }
/*		 */ 
/*		 */	 private boolean e(int paramInt)
/*		 */	 {
/* 132 */		 if (paramInt == 0) return false;
/* 133 */		 Block localBlock = Block.byId[paramInt];
/* 134 */		 return (localBlock.c()) && (localBlock.material.isSolid());
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 140 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 141 */		 int j = i;
/*		 */ 
/* 143 */		 if (j > 0) {
/* 144 */			 for (int k = 0; k <= 3; k++) {
/* 145 */				 int m = 1 << k;
/* 146 */				 if (((i & m) == 0) || 
/* 147 */					 (e(paramWorld.getTypeId(paramInt1 + Direction.a[k], paramInt2, paramInt3 + Direction.b[k])))) {
/*		 */					 continue;
/*		 */				 }
/* 150 */				 if ((paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3) != this.id) || ((paramWorld.getData(paramInt1, paramInt2 + 1, paramInt3) & m) == 0)) {
/* 151 */					 j &= (m ^ 0xFFFFFFFF);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 158 */		 if (j == 0)
/*		 */		 {
/* 160 */			 if (!e(paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3))) {
/* 161 */				 return false;
/*		 */			 }
/*		 */		 }
/* 164 */		 if (j != i) {
/* 165 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, j);
/*		 */		 }
/* 167 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 188 */		 if ((!paramWorld.isStatic) && (!l(paramWorld, paramInt1, paramInt2, paramInt3))) {
/* 189 */			 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/* 190 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
/*		 */	 {
/* 196 */		 if ((!paramWorld.isStatic) && 
/* 197 */			 (paramWorld.random.nextInt(4) == 0)) {
/* 198 */			 int i = 4;
/* 199 */			 int j = 5;
/* 200 */			 int k = 0;
/*		 */ 
/* 202 */			 for (int m = paramInt1 - i; m <= paramInt1 + i; m++) {
/* 203 */				 for (n = paramInt3 - i; n <= paramInt3 + i; n++)
/* 204 */					 for (i1 = paramInt2 - 1; i1 <= paramInt2 + 1; i1++) {
/* 205 */						 if (paramWorld.getTypeId(m, i1, n) != this.id) continue; j--; if (j <= 0) {
/* 206 */							 k = 1;
/* 207 */							 break label121;
/*		 */						 }
/*		 */					 }
/*		 */			 }
/* 211 */			 label121: m = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 212 */			 int n = paramWorld.random.nextInt(6);
/* 213 */			 int i1 = Direction.d[n];
/*		 */			 int i2;
/*		 */			 int i3;
/* 215 */			 if ((n == 1) && (paramInt2 < 255) && (paramWorld.isEmpty(paramInt1, paramInt2 + 1, paramInt3))) {
/* 216 */				 if (k != 0) return;
/*		 */ 
/* 218 */				 i2 = paramWorld.random.nextInt(16) & m;
/* 219 */				 if (i2 > 0) {
/* 220 */					 for (i3 = 0; i3 <= 3; i3++) {
/* 221 */						 if (!e(paramWorld.getTypeId(paramInt1 + Direction.a[i3], paramInt2 + 1, paramInt3 + Direction.b[i3]))) {
/* 222 */							 i2 &= (1 << i3 ^ 0xFFFFFFFF);
/*		 */						 }
/*		 */					 }
/* 225 */					 if (i2 > 0)
/* 226 */						 paramWorld.setTypeIdAndData(paramInt1, paramInt2 + 1, paramInt3, this.id, i2);
/*		 */				 }
/*		 */			 }
/*		 */			 else
/*		 */			 {
/*		 */				 int i4;
/* 229 */				 if ((n >= 2) && (n <= 5) && ((m & 1 << i1) == 0)) {
/* 230 */					 if (k != 0) return;
/*		 */ 
/* 232 */					 i2 = paramWorld.getTypeId(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1]);
/*		 */ 
/* 234 */					 if ((i2 == 0) || (Block.byId[i2] == null))
/*		 */					 {
/* 236 */						 i3 = i1 + 1 & 0x3;
/* 237 */						 i4 = i1 + 3 & 0x3;
/*		 */ 
/* 240 */						 if (((m & 1 << i3) != 0) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i1] + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i3]))))
/*		 */						 {
/* 242 */							 paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1], this.id, 1 << i3);
/* 243 */						 } else if (((m & 1 << i4) != 0) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i1] + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i4]))))
/*		 */						 {
/* 245 */							 paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1], this.id, 1 << i4);
/*		 */						 }
/* 248 */						 else if (((m & 1 << i3) != 0) && (paramWorld.isEmpty(paramInt1 + Direction.a[i1] + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i3])) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i3]))))
/*		 */						 {
/* 251 */							 paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1] + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i3], this.id, 1 << (i1 + 2 & 0x3));
/*		 */						 }
/* 253 */						 else if (((m & 1 << i4) != 0) && (paramWorld.isEmpty(paramInt1 + Direction.a[i1] + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i4])) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i4]))))
/*		 */						 {
/* 256 */							 paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1] + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i4], this.id, 1 << (i1 + 2 & 0x3));
/*		 */						 }
/* 260 */						 else if (e(paramWorld.getTypeId(paramInt1 + Direction.a[i1], paramInt2 + 1, paramInt3 + Direction.b[i1]))) {
/* 261 */							 paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1], this.id, 0);
/*		 */						 }
/*		 */					 }
/* 264 */					 else if ((Block.byId[i2].material.k()) && (Block.byId[i2].c()))
/*		 */					 {
/* 266 */						 paramWorld.setData(paramInt1, paramInt2, paramInt3, m | 1 << i1);
/*		 */					 }
/*		 */ 
/*		 */				 }
/* 270 */				 else if (paramInt2 > 1) {
/* 271 */					 i2 = paramWorld.getTypeId(paramInt1, paramInt2 - 1, paramInt3);
/*		 */ 
/* 273 */					 if (i2 == 0) {
/* 274 */						 i3 = paramWorld.random.nextInt(16) & m;
/* 275 */						 if (i3 > 0)
/* 276 */							 paramWorld.setTypeIdAndData(paramInt1, paramInt2 - 1, paramInt3, this.id, i3);
/*		 */					 }
/* 278 */					 else if (i2 == this.id) {
/* 279 */						 i3 = paramWorld.random.nextInt(16) & m;
/* 280 */						 i4 = paramWorld.getData(paramInt1, paramInt2 - 1, paramInt3);
/* 281 */						 if (i4 != (i4 | i3))
/* 282 */							 paramWorld.setData(paramInt1, paramInt2 - 1, paramInt3, i4 | i3);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 293 */		 int i = 0;
/* 294 */		 switch (paramInt4) {
/*		 */		 case 2:
/* 296 */			 i = 1;
/* 297 */			 break;
/*		 */		 case 3:
/* 299 */			 i = 4;
/* 300 */			 break;
/*		 */		 case 4:
/* 302 */			 i = 8;
/* 303 */			 break;
/*		 */		 case 5:
/* 305 */			 i = 2;
/*		 */		 }
/*		 */ 
/* 308 */		 if (i != 0)
/* 309 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 316 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random paramRandom)
/*		 */	 {
/* 321 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, EntityHuman paramEntityHuman, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 326 */		 if ((!paramWorld.isStatic) && (paramEntityHuman.bC() != null) && (paramEntityHuman.bC().id == Item.SHEARS.id)) {
/* 327 */			 paramEntityHuman.a(StatisticList.C[this.id], 1);
/*		 */ 
/* 330 */			 a(paramWorld, paramInt1, paramInt2, paramInt3, new ItemStack(Block.VINE, 1, 0));
/*		 */		 } else {
/* 332 */			 super.a(paramWorld, paramEntityHuman, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockVine
 * JD-Core Version:		0.6.0
 */