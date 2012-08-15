/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockLadder extends Block
/*		 */ {
/*		 */	 protected BlockLadder(int paramInt1, int paramInt2)
/*		 */	 {
/*	12 */		 super(paramInt1, paramInt2, Material.ORIENTABLE);
/*	13 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	18 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	19 */		 float f = 0.125F;
/*		 */ 
/*	21 */		 if (i == 2) a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*	22 */		 if (i == 3) a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*	23 */		 if (i == 4) a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*	24 */		 if (i == 5) a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*		 */ 
/*	26 */		 return super.e(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	48 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	53 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	58 */		 return 8;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	63 */		 if (paramWorld.s(paramInt1 - 1, paramInt2, paramInt3))
/*	64 */			 return true;
/*	65 */		 if (paramWorld.s(paramInt1 + 1, paramInt2, paramInt3))
/*	66 */			 return true;
/*	67 */		 if (paramWorld.s(paramInt1, paramInt2, paramInt3 - 1)) {
/*	68 */			 return true;
/*		 */		 }
/*	70 */		 return paramWorld.s(paramInt1, paramInt2, paramInt3 + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	77 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	79 */		 if (((i == 0) || (paramInt4 == 2)) && (paramWorld.s(paramInt1, paramInt2, paramInt3 + 1))) i = 2;
/*	80 */		 if (((i == 0) || (paramInt4 == 3)) && (paramWorld.s(paramInt1, paramInt2, paramInt3 - 1))) i = 3;
/*	81 */		 if (((i == 0) || (paramInt4 == 4)) && (paramWorld.s(paramInt1 + 1, paramInt2, paramInt3))) i = 4;
/*	82 */		 if (((i == 0) || (paramInt4 == 5)) && (paramWorld.s(paramInt1 - 1, paramInt2, paramInt3))) i = 5;
/*		 */ 
/*	84 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	89 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	90 */		 int j = 0;
/*		 */ 
/*	92 */		 if ((i == 2) && (paramWorld.s(paramInt1, paramInt2, paramInt3 + 1))) j = 1;
/*	93 */		 if ((i == 3) && (paramWorld.s(paramInt1, paramInt2, paramInt3 - 1))) j = 1;
/*	94 */		 if ((i == 4) && (paramWorld.s(paramInt1 + 1, paramInt2, paramInt3))) j = 1;
/*	95 */		 if ((i == 5) && (paramWorld.s(paramInt1 - 1, paramInt2, paramInt3))) j = 1;
/*	96 */		 if (j == 0) {
/*	97 */			 c(paramWorld, paramInt1, paramInt2, paramInt3, i, 0);
/*	98 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */		 }
/*		 */ 
/* 101 */		 super.doPhysics(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random paramRandom)
/*		 */	 {
/* 106 */		 return 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockLadder
 * JD-Core Version:		0.6.0
 */