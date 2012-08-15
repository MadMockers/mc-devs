/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockStairs extends Block
/*		 */ {
/*	15 */	 private static final int[][] a = { { 2, 6 }, { 3, 7 }, { 2, 3 }, { 6, 7 }, { 0, 4 }, { 1, 5 }, { 0, 1 }, { 4, 5 } };
/*		 */	 private final Block b;
/*		 */	 private final int c;
/*	30 */	 private boolean cr = false;
/*	31 */	 private int cs = 0;
/*		 */ 
/*		 */	 protected BlockStairs(int paramInt1, Block paramBlock, int paramInt2) {
/*	34 */		 super(paramInt1, paramBlock.textureId, paramBlock.material);
/*	35 */		 this.b = paramBlock;
/*	36 */		 this.c = paramInt2;
/*	37 */		 c(paramBlock.strength);
/*	38 */		 b(paramBlock.durability / 3.0F);
/*	39 */		 a(paramBlock.stepSound);
/*	40 */		 h(255);
/*	41 */		 a(CreativeModeTab.b);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	46 */		 if (this.cr)
/*	47 */			 a(0.5F * (this.cs % 2), 0.5F * (this.cs / 2 % 2), 0.5F * (this.cs / 4 % 2), 0.5F + 0.5F * (this.cs % 2), 0.5F + 0.5F * (this.cs / 2 % 2), 0.5F + 0.5F * (this.cs / 4 % 2));
/*		 */		 else
/*	49 */			 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	55 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	60 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	65 */		 return 10;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
/*		 */	 {
/*	71 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	72 */		 int j = i & 0x3;
/*	73 */		 float f1 = 0.0F;
/*	74 */		 float f2 = 0.5F;
/*	75 */		 float f3 = 0.5F;
/*	76 */		 float f4 = 1.0F;
/*		 */ 
/*	78 */		 if ((i & 0x4) != 0) {
/*	79 */			 f1 = 0.5F;
/*	80 */			 f2 = 1.0F;
/*	81 */			 f3 = 0.0F;
/*	82 */			 f4 = 0.5F;
/*		 */		 }
/*		 */ 
/*	85 */		 a(0.0F, f1, 0.0F, 1.0F, f2, 1.0F);
/*	86 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*		 */ 
/*	88 */		 if (j == 0) {
/*	89 */			 a(0.5F, f3, 0.0F, 1.0F, f4, 1.0F);
/*	90 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	91 */		 } else if (j == 1) {
/*	92 */			 a(0.0F, f3, 0.0F, 0.5F, f4, 1.0F);
/*	93 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	94 */		 } else if (j == 2) {
/*	95 */			 a(0.0F, f3, 0.5F, 1.0F, f4, 1.0F);
/*	96 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	97 */		 } else if (j == 3) {
/*	98 */			 a(0.0F, f3, 0.0F, 1.0F, f4, 0.5F);
/*	99 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*		 */		 }
/*		 */ 
/* 102 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman)
/*		 */	 {
/* 120 */		 this.b.attack(paramWorld, paramInt1, paramInt2, paramInt3, paramEntityHuman);
/*		 */	 }
/*		 */ 
/*		 */	 public void postBreak(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 125 */		 this.b.postBreak(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public float a(Entity paramEntity)
/*		 */	 {
/* 140 */		 return this.b.a(paramEntity);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt1, int paramInt2)
/*		 */	 {
/* 150 */		 return this.b.a(paramInt1, this.c);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt)
/*		 */	 {
/* 155 */		 return this.b.a(paramInt, this.c);
/*		 */	 }
/*		 */ 
/*		 */	 public int p_()
/*		 */	 {
/* 160 */		 return this.b.p_();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity, Vec3D paramVec3D)
/*		 */	 {
/* 170 */		 this.b.a(paramWorld, paramInt1, paramInt2, paramInt3, paramEntity, paramVec3D);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean l()
/*		 */	 {
/* 175 */		 return this.b.l();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt, boolean paramBoolean)
/*		 */	 {
/* 180 */		 return this.b.a(paramInt, paramBoolean);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 185 */		 return this.b.canPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 190 */		 doPhysics(paramWorld, paramInt1, paramInt2, paramInt3, 0);
/* 191 */		 this.b.onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 196 */		 this.b.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity)
/*		 */	 {
/* 206 */		 this.b.b(paramWorld, paramInt1, paramInt2, paramInt3, paramEntity);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
/*		 */	 {
/* 211 */		 this.b.b(paramWorld, paramInt1, paramInt2, paramInt3, paramRandom);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 216 */		 return this.b.interact(paramWorld, paramInt1, paramInt2, paramInt3, paramEntityHuman, 0, 0.0F, 0.0F, 0.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void wasExploded(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 221 */		 this.b.wasExploded(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/* 226 */		 int i = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
/* 227 */		 int j = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x4;
/*		 */ 
/* 229 */		 if (i == 0) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x2 | j);
/* 230 */		 if (i == 1) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x1 | j);
/* 231 */		 if (i == 2) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x3 | j);
/* 232 */		 if (i == 3) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x0 | j);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 237 */		 if ((paramInt4 == 0) || ((paramInt4 != 1) && (paramFloat2 > 0.5D))) {
/* 238 */			 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 239 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, i | 0x4);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Vec3D paramVec3D1, Vec3D paramVec3D2)
/*		 */	 {
/* 245 */		 MovingObjectPosition[] arrayOfMovingObjectPosition1 = new MovingObjectPosition[8];
/* 246 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 247 */		 int j = i & 0x3;
/* 248 */		 int k = (i & 0x4) == 4 ? 1 : 0;
/* 249 */		 int[] arrayOfInt1 = a[(j + 0)];
/*		 */ 
/* 251 */		 this.cr = true;
/*		 */		 int i3;
/* 252 */		 for (int m = 0; m < 8; m++) {
/* 253 */			 this.cs = m;
/*		 */ 
/* 255 */			 for (i3 : arrayOfInt1) {
/* 256 */				 if (i3 == m)
/*		 */					 continue;
/*		 */			 }
/* 259 */			 arrayOfMovingObjectPosition1[m] = super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramVec3D1, paramVec3D2);
/*		 */		 }
/*		 */ 
/* 262 */		 for (??? : arrayOfInt1) {
/* 263 */			 arrayOfMovingObjectPosition1[???] = null;
/*		 */		 }
/*		 */ 
/* 266 */		 ??? = null;
/* 267 */		 double d1 = 0.0D;
/*		 */ 
/* 269 */		 for (MovingObjectPosition localMovingObjectPosition : arrayOfMovingObjectPosition1) {
/* 270 */			 if (localMovingObjectPosition != null) {
/* 271 */				 double d2 = localMovingObjectPosition.pos.distanceSquared(paramVec3D2);
/*		 */ 
/* 273 */				 if (d2 > d1) {
/* 274 */					 ??? = localMovingObjectPosition;
/* 275 */					 d1 = d2;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 280 */		 return (MovingObjectPosition)???;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockStairs
 * JD-Core Version:		0.6.0
 */