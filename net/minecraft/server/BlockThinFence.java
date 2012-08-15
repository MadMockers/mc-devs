/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockThinFence extends Block
/*		 */ {
/*		 */	 private int a;
/*		 */	 private final boolean b;
/*		 */ 
/*		 */	 protected BlockThinFence(int paramInt1, int paramInt2, int paramInt3, Material paramMaterial, boolean paramBoolean)
/*		 */	 {
/*	20 */		 super(paramInt1, paramInt2, paramMaterial);
/*	21 */		 this.a = paramInt3;
/*	22 */		 this.b = paramBoolean;
/*	23 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/*	28 */		 if (!this.b) {
/*	29 */			 return 0;
/*		 */		 }
/*	31 */		 return super.getDropType(paramInt1, paramRandom, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	36 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	41 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	46 */		 return 18;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
/*		 */	 {
/*	58 */		 boolean bool1 = e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1));
/*	59 */		 boolean bool2 = e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1));
/*	60 */		 boolean bool3 = e(paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3));
/*	61 */		 boolean bool4 = e(paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3));
/*		 */ 
/*	63 */		 if (((bool3) && (bool4)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
/*	64 */			 a(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
/*	65 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	66 */		 } else if ((bool3) && (!bool4)) {
/*	67 */			 a(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
/*	68 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	69 */		 } else if ((!bool3) && (bool4)) {
/*	70 */			 a(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
/*	71 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*		 */		 }
/*	73 */		 if (((bool1) && (bool2)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
/*	74 */			 a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
/*	75 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	76 */		 } else if ((bool1) && (!bool2)) {
/*	77 */			 a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
/*	78 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	79 */		 } else if ((!bool1) && (bool2)) {
/*	80 */			 a(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
/*	81 */			 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/*	87 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	92 */		 float f1 = 0.4375F;
/*	93 */		 float f2 = 0.5625F;
/*	94 */		 float f3 = 0.4375F;
/*	95 */		 float f4 = 0.5625F;
/*		 */ 
/*	97 */		 boolean bool1 = e(paramIBlockAccess.getTypeId(paramInt1, paramInt2, paramInt3 - 1));
/*	98 */		 boolean bool2 = e(paramIBlockAccess.getTypeId(paramInt1, paramInt2, paramInt3 + 1));
/*	99 */		 boolean bool3 = e(paramIBlockAccess.getTypeId(paramInt1 - 1, paramInt2, paramInt3));
/* 100 */		 boolean bool4 = e(paramIBlockAccess.getTypeId(paramInt1 + 1, paramInt2, paramInt3));
/*		 */ 
/* 102 */		 if (((bool3) && (bool4)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
/* 103 */			 f1 = 0.0F;
/* 104 */			 f2 = 1.0F;
/* 105 */		 } else if ((bool3) && (!bool4)) {
/* 106 */			 f1 = 0.0F;
/* 107 */		 } else if ((!bool3) && (bool4)) {
/* 108 */			 f2 = 1.0F;
/*		 */		 }
/* 110 */		 if (((bool1) && (bool2)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
/* 111 */			 f3 = 0.0F;
/* 112 */			 f4 = 1.0F;
/* 113 */		 } else if ((bool1) && (!bool2)) {
/* 114 */			 f3 = 0.0F;
/* 115 */		 } else if ((!bool1) && (bool2)) {
/* 116 */			 f4 = 1.0F;
/*		 */		 }
/*		 */ 
/* 119 */		 a(f1, 0.0F, f3, f2, 1.0F, f4);
/*		 */	 }
/*		 */ 
/*		 */	 public final boolean e(int paramInt)
/*		 */	 {
/* 127 */		 return (Block.n[paramInt] != 0) || (paramInt == this.id) || (paramInt == Block.GLASS.id);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean q_()
/*		 */	 {
/* 132 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected ItemStack c_(int paramInt)
/*		 */	 {
/* 137 */		 return new ItemStack(this.id, 1, paramInt);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockThinFence
 * JD-Core Version:		0.6.0
 */