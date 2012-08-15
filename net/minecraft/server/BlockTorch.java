/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockTorch extends Block
/*		 */ {
/*		 */	 protected BlockTorch(int paramInt1, int paramInt2)
/*		 */	 {
/*	12 */		 super(paramInt1, paramInt2, Material.ORIENTABLE);
/*	13 */		 b(true);
/*	14 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	19 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	24 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	29 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	34 */		 return 2;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/*	38 */		 if (paramWorld.t(paramInt1, paramInt2, paramInt3)) {
/*	39 */			 return true;
/*		 */		 }
/*	41 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	43 */		 return (i == Block.FENCE.id) || (i == Block.NETHER_FENCE.id) || (i == Block.GLASS.id);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	50 */		 if (paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true))
/*	51 */			 return true;
/*	52 */		 if (paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true))
/*	53 */			 return true;
/*	54 */		 if (paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true))
/*	55 */			 return true;
/*	56 */		 if (paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true)) {
/*	57 */			 return true;
/*		 */		 }
/*	59 */		 return l(paramWorld, paramInt1, paramInt2 - 1, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	67 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	69 */		 if ((paramInt4 == 1) && (l(paramWorld, paramInt1, paramInt2 - 1, paramInt3))) i = 5;
/*	70 */		 if ((paramInt4 == 2) && (paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true))) i = 4;
/*	71 */		 if ((paramInt4 == 3) && (paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true))) i = 3;
/*	72 */		 if ((paramInt4 == 4) && (paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true))) i = 2;
/*	73 */		 if ((paramInt4 == 5) && (paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true))) i = 1;
/*		 */ 
/*	75 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
/*		 */	 {
/*	80 */		 super.b(paramWorld, paramInt1, paramInt2, paramInt3, paramRandom);
/*	81 */		 if (paramWorld.getData(paramInt1, paramInt2, paramInt3) == 0) onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	86 */		 if (paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true))
/*	87 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, 1);
/*	88 */		 else if (paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true))
/*	89 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, 2);
/*	90 */		 else if (paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true))
/*	91 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, 3);
/*	92 */		 else if (paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true))
/*	93 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, 4);
/*	94 */		 else if (l(paramWorld, paramInt1, paramInt2 - 1, paramInt3)) {
/*	95 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, 5);
/*		 */		 }
/*	97 */		 n(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 102 */		 if (n(paramWorld, paramInt1, paramInt2, paramInt3)) {
/* 103 */			 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 104 */			 int j = 0;
/*		 */ 
/* 106 */			 if ((!paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true)) && (i == 1)) j = 1;
/* 107 */			 if ((!paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true)) && (i == 2)) j = 1;
/* 108 */			 if ((!paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true)) && (i == 3)) j = 1;
/* 109 */			 if ((!paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true)) && (i == 4)) j = 1;
/* 110 */			 if ((!l(paramWorld, paramInt1, paramInt2 - 1, paramInt3)) && (i == 5)) j = 1;
/*		 */ 
/* 112 */			 if (j != 0) {
/* 113 */				 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/* 114 */				 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean n(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/* 120 */		 if (!canPlace(paramWorld, paramInt1, paramInt2, paramInt3)) {
/* 121 */			 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) == this.id) {
/* 122 */				 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/* 123 */				 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */			 }
/* 125 */			 return false;
/*		 */		 }
/* 127 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Vec3D paramVec3D1, Vec3D paramVec3D2)
/*		 */	 {
/* 132 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x7;
/*		 */ 
/* 134 */		 float f = 0.15F;
/* 135 */		 if (i == 1) {
/* 136 */			 a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
/* 137 */		 } else if (i == 2) {
/* 138 */			 a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
/* 139 */		 } else if (i == 3) {
/* 140 */			 a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
/* 141 */		 } else if (i == 4) {
/* 142 */			 a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
/*		 */		 } else {
/* 144 */			 f = 0.1F;
/* 145 */			 a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
/*		 */		 }
/*		 */ 
/* 148 */		 return super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramVec3D1, paramVec3D2);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockTorch
 * JD-Core Version:		0.6.0
 */