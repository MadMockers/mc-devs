/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockCake extends Block
/*		 */ {
/*		 */	 protected BlockCake(int paramInt1, int paramInt2)
/*		 */	 {
/*	14 */		 super(paramInt1, paramInt2, Material.CAKE);
/*	15 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	20 */		 int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);
/*	21 */		 float f1 = 0.0625F;
/*	22 */		 float f2 = (1 + i * 2) / 16.0F;
/*	23 */		 float f3 = 0.5F;
/*	24 */		 a(f2, 0.0F, f1, 1.0F - f1, f3, 1.0F - f1);
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/*	29 */		 float f1 = 0.0625F;
/*	30 */		 float f2 = 0.5F;
/*	31 */		 a(f1, 0.0F, f1, 1.0F - f1, f2, 1.0F - f1);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	36 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	37 */		 float f1 = 0.0625F;
/*	38 */		 float f2 = (1 + i * 2) / 16.0F;
/*	39 */		 float f3 = 0.5F;
/*	40 */		 return AxisAlignedBB.a().a(paramInt1 + f2, paramInt2, paramInt3 + f1, paramInt1 + 1 - f1, paramInt2 + f3 - f1, paramInt3 + 1 - f1);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt1, int paramInt2)
/*		 */	 {
/*	54 */		 if (paramInt1 == 1) return this.textureId;
/*	55 */		 if (paramInt1 == 0) return this.textureId + 3;
/*	56 */		 if ((paramInt2 > 0) && (paramInt1 == 4)) return this.textureId + 2;
/*	57 */		 return this.textureId + 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt)
/*		 */	 {
/*	62 */		 if (paramInt == 1) return this.textureId;
/*	63 */		 if (paramInt == 0) return this.textureId + 3;
/*	64 */		 return this.textureId + 1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	69 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	74 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	79 */		 b(paramWorld, paramInt1, paramInt2, paramInt3, paramEntityHuman);
/*	80 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void attack(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman)
/*		 */	 {
/*	85 */		 b(paramWorld, paramInt1, paramInt2, paramInt3, paramEntityHuman);
/*		 */	 }
/*		 */ 
/*		 */	 private void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman) {
/*	89 */		 if (paramEntityHuman.e(false)) {
/*	90 */			 paramEntityHuman.getFoodData().eat(2, 0.1F);
/*		 */ 
/*	92 */			 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3) + 1;
/*	93 */			 if (i >= 6) {
/*	94 */				 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */			 } else {
/*	96 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*	97 */				 paramWorld.i(paramInt1, paramInt2, paramInt3);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 104 */		 if (!super.canPlace(paramWorld, paramInt1, paramInt2, paramInt3)) return false;
/*		 */ 
/* 106 */		 return d(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 111 */		 if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
/* 112 */			 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/* 113 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 119 */		 return paramWorld.getMaterial(paramInt1, paramInt2 - 1, paramInt3).isBuildable();
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random paramRandom)
/*		 */	 {
/* 124 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 129 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockCake
 * JD-Core Version:		0.6.0
 */