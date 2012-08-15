/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class BlockFence extends Block
/*		 */ {
/*		 */	 public BlockFence(int paramInt1, int paramInt2)
/*		 */	 {
/*	10 */		 super(paramInt1, paramInt2, Material.WOOD);
/*	11 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public BlockFence(int paramInt1, int paramInt2, Material paramMaterial) {
/*	15 */		 super(paramInt1, paramInt2, paramMaterial);
/*	16 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	21 */		 boolean bool1 = d(paramWorld, paramInt1, paramInt2, paramInt3 - 1);
/*	22 */		 boolean bool2 = d(paramWorld, paramInt1, paramInt2, paramInt3 + 1);
/*	23 */		 boolean bool3 = d(paramWorld, paramInt1 - 1, paramInt2, paramInt3);
/*	24 */		 boolean bool4 = d(paramWorld, paramInt1 + 1, paramInt2, paramInt3);
/*		 */ 
/*	26 */		 float f1 = 0.375F;
/*	27 */		 float f2 = 0.625F;
/*	28 */		 float f3 = 0.375F;
/*	29 */		 float f4 = 0.625F;
/*		 */ 
/*	31 */		 if (bool1) {
/*	32 */			 f3 = 0.0F;
/*		 */		 }
/*	34 */		 if (bool2) {
/*	35 */			 f4 = 1.0F;
/*		 */		 }
/*	37 */		 if (bool3) {
/*	38 */			 f1 = 0.0F;
/*		 */		 }
/*	40 */		 if (bool4) {
/*	41 */			 f2 = 1.0F;
/*		 */		 }
/*		 */ 
/*	44 */		 return AxisAlignedBB.a().a(paramInt1 + f1, paramInt2, paramInt3 + f3, paramInt1 + f2, paramInt2 + 1.5F, paramInt3 + f4);
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	49 */		 boolean bool1 = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 - 1);
/*	50 */		 boolean bool2 = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 + 1);
/*	51 */		 boolean bool3 = d(paramIBlockAccess, paramInt1 - 1, paramInt2, paramInt3);
/*	52 */		 boolean bool4 = d(paramIBlockAccess, paramInt1 + 1, paramInt2, paramInt3);
/*		 */ 
/*	54 */		 float f1 = 0.375F;
/*	55 */		 float f2 = 0.625F;
/*	56 */		 float f3 = 0.375F;
/*	57 */		 float f4 = 0.625F;
/*		 */ 
/*	59 */		 if (bool1) {
/*	60 */			 f3 = 0.0F;
/*		 */		 }
/*	62 */		 if (bool2) {
/*	63 */			 f4 = 1.0F;
/*		 */		 }
/*	65 */		 if (bool3) {
/*	66 */			 f1 = 0.0F;
/*		 */		 }
/*	68 */		 if (bool4) {
/*	69 */			 f2 = 1.0F;
/*		 */		 }
/*		 */ 
/*	72 */		 a(f1, 0.0F, f3, f2, 1.0F, f4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	81 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	86 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	91 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	96 */		 return 11;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
/* 100 */		 int i = paramIBlockAccess.getTypeId(paramInt1, paramInt2, paramInt3);
/* 101 */		 if ((i == this.id) || (i == Block.FENCE_GATE.id)) {
/* 102 */			 return true;
/*		 */		 }
/* 104 */		 Block localBlock = Block.byId[i];
/* 105 */		 if ((localBlock != null) && 
/* 106 */			 (localBlock.material.k()) && (localBlock.c())) {
/* 107 */			 return localBlock.material != Material.PUMPKIN;
/*		 */		 }
/*		 */ 
/* 110 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean c(int paramInt) {
/* 114 */		 return (paramInt == Block.FENCE.id) || (paramInt == Block.NETHER_FENCE.id);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockFence
 * JD-Core Version:		0.6.0
 */