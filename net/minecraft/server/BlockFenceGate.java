/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class BlockFenceGate extends BlockDirectional
/*		 */ {
/*		 */	 public BlockFenceGate(int paramInt1, int paramInt2)
/*		 */	 {
/*	18 */		 super(paramInt1, paramInt2, Material.WOOD);
/*	19 */		 a(CreativeModeTab.d);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	24 */		 if (!paramWorld.getMaterial(paramInt1, paramInt2 - 1, paramInt3).isBuildable()) return false;
/*	25 */		 return super.canPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	30 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	31 */		 if (c(i)) {
/*	32 */			 return null;
/*		 */		 }
/*	34 */		 if ((i == 2) || (i == 0)) {
/*	35 */			 return AxisAlignedBB.a().a(paramInt1, paramInt2, paramInt3 + 0.375F, paramInt1 + 1, paramInt2 + 1.5F, paramInt3 + 0.625F);
/*		 */		 }
/*	37 */		 return AxisAlignedBB.a().a(paramInt1 + 0.375F, paramInt2, paramInt3, paramInt1 + 0.625F, paramInt2 + 1.5F, paramInt3 + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	42 */		 int i = d(paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3));
/*	43 */		 if ((i == 2) || (i == 0))
/*	44 */			 a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
/*		 */		 else
/*	46 */			 a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	56 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	61 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	66 */		 return c(paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3));
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	71 */		 return 21;
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/*	76 */		 int i = (MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3) % 4;
/*	77 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	82 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	83 */		 if (c(i)) {
/*	84 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, i & 0xFFFFFFFB);
/*		 */		 }
/*		 */		 else {
/*	87 */			 int j = (MathHelper.floor(paramEntityHuman.yaw * 4.0F / 360.0F + 0.5D) & 0x3) % 4;
/*	88 */			 int k = d(i);
/*	89 */			 if (k == (j + 2) % 4) {
/*	90 */				 i = j;
/*		 */			 }
/*	92 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, i | 0x4);
/*		 */		 }
/*	94 */		 paramWorld.a(paramEntityHuman, 1003, paramInt1, paramInt2, paramInt3, 0);
/*	95 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 100 */		 if (paramWorld.isStatic) return;
/*		 */ 
/* 102 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 104 */		 boolean bool = paramWorld.isBlockIndirectlyPowered(paramInt1, paramInt2, paramInt3);
/* 105 */		 if ((bool) || ((paramInt4 > 0) && (Block.byId[paramInt4].isPowerSource())) || (paramInt4 == 0))
/* 106 */			 if ((bool) && (!c(i))) {
/* 107 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, i | 0x4);
/* 108 */				 paramWorld.a(null, 1003, paramInt1, paramInt2, paramInt3, 0);
/* 109 */			 } else if ((!bool) && (c(i))) {
/* 110 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, i & 0xFFFFFFFB);
/* 111 */				 paramWorld.a(null, 1003, paramInt1, paramInt2, paramInt3, 0);
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean c(int paramInt)
/*		 */	 {
/* 117 */		 return (paramInt & 0x4) != 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockFenceGate
 * JD-Core Version:		0.6.0
 */