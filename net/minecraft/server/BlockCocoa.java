/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockCocoa extends BlockDirectional
/*		 */ {
/*		 */	 public BlockCocoa(int paramInt)
/*		 */	 {
/*	16 */		 super(paramInt, 168, Material.PLANT);
/*	17 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
/*		 */	 {
/*	23 */		 if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
/*	24 */			 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/*	25 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*	26 */		 } else if (paramWorld.random.nextInt(5) == 0) {
/*	27 */			 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	28 */			 int j = c(i);
/*	29 */			 if (j < 2) {
/*	30 */				 j++;
/*	31 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, j << 2 | d(i));
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/*	37 */		 int i = d(paramWorld.getData(paramInt1, paramInt2, paramInt3));
/*		 */ 
/*	39 */		 paramInt1 += Direction.a[i];
/*	40 */		 paramInt3 += Direction.b[i];
/*	41 */		 int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	43 */		 return (j == Block.LOG.id) && (BlockLog.e(paramWorld.getData(paramInt1, paramInt2, paramInt3)) == 3);
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	48 */		 return 28;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	53 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	58 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	63 */		 updateShape(paramWorld, paramInt1, paramInt2, paramInt3);
/*	64 */		 return super.e(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	75 */		 int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);
/*	76 */		 int j = d(i);
/*	77 */		 int k = c(i);
/*		 */ 
/*	79 */		 int m = 4 + k * 2;
/*	80 */		 int n = 5 + k * 2;
/*		 */ 
/*	82 */		 float f = m / 2.0F;
/*		 */ 
/*	84 */		 switch (j) {
/*		 */		 case 0:
/*	86 */			 a((8.0F - f) / 16.0F, (12.0F - n) / 16.0F, (15.0F - m) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
/*	87 */			 break;
/*		 */		 case 2:
/*	89 */			 a((8.0F - f) / 16.0F, (12.0F - n) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + m) / 16.0F);
/*	90 */			 break;
/*		 */		 case 1:
/*	92 */			 a(0.0625F, (12.0F - n) / 16.0F, (8.0F - f) / 16.0F, (1.0F + m) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
/*	93 */			 break;
/*		 */		 case 3:
/*	95 */			 a((15.0F - m) / 16.0F, (12.0F - n) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/* 102 */		 int i = ((MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3) + 0) % 4;
/* 103 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 108 */		 if ((paramInt4 == 1) || (paramInt4 == 0)) {
/* 109 */			 paramInt4 = 2;
/*		 */		 }
/* 111 */		 int i = Direction.e[Direction.d[paramInt4]];
/* 112 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 117 */		 if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
/* 118 */			 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/* 119 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static int c(int paramInt) {
/* 124 */		 return (paramInt & 0xC) >> 2;
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
/*		 */	 {
/* 129 */		 int i = c(paramInt4);
/* 130 */		 int j = 1;
/* 131 */		 if (i >= 2) {
/* 132 */			 j = 3;
/*		 */		 }
/* 134 */		 for (int k = 0; k < j; k++)
/* 135 */			 a(paramWorld, paramInt1, paramInt2, paramInt3, new ItemStack(Item.INK_SACK, 1, 3));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockCocoa
 * JD-Core Version:		0.6.0
 */