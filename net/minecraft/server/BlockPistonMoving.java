/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockPistonMoving extends BlockContainer
/*		 */ {
/*		 */	 public BlockPistonMoving(int paramInt)
/*		 */	 {
/*	16 */		 super(paramInt, Material.PISTON);
/*	17 */		 c(-1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity a(World paramWorld)
/*		 */	 {
/*	22 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/*	31 */		 TileEntity localTileEntity = paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/*	32 */		 if ((localTileEntity instanceof TileEntityPiston))
/*	33 */			 ((TileEntityPiston)localTileEntity).i();
/*		 */		 else
/*	35 */			 super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	41 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	46 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	51 */		 return -1;
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
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	68 */		 if ((!paramWorld.isStatic) && (paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3) == null))
/*		 */		 {
/*	70 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*	71 */			 return true;
/*		 */		 }
/*	73 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/*	78 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
/*		 */	 {
/*	83 */		 if (paramWorld.isStatic) return;
/*		 */ 
/*	85 */		 TileEntityPiston localTileEntityPiston = d(paramWorld, paramInt1, paramInt2, paramInt3);
/*	86 */		 if (localTileEntityPiston == null) {
/*	87 */			 return;
/*		 */		 }
/*		 */ 
/*	90 */		 Block.byId[localTileEntityPiston.a()].c(paramWorld, paramInt1, paramInt2, paramInt3, localTileEntityPiston.n(), 0);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	95 */		 if ((!paramWorld.isStatic) && (paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3) == null));
/*		 */	 }
/*		 */ 
/*		 */	 public static TileEntity a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2) {
/* 100 */		 return new TileEntityPiston(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 105 */		 TileEntityPiston localTileEntityPiston = d(paramWorld, paramInt1, paramInt2, paramInt3);
/* 106 */		 if (localTileEntityPiston == null) {
/* 107 */			 return null;
/*		 */		 }
/*		 */ 
/* 111 */		 float f = localTileEntityPiston.a(0.0F);
/* 112 */		 if (localTileEntityPiston.b()) {
/* 113 */			 f = 1.0F - f;
/*		 */		 }
/* 115 */		 return b(paramWorld, paramInt1, paramInt2, paramInt3, localTileEntityPiston.a(), f, localTileEntityPiston.c());
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 120 */		 TileEntityPiston localTileEntityPiston = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3);
/* 121 */		 if (localTileEntityPiston != null) {
/* 122 */			 Block localBlock = Block.byId[localTileEntityPiston.a()];
/* 123 */			 if ((localBlock == null) || (localBlock == this)) {
/* 124 */				 return;
/*		 */			 }
/* 126 */			 localBlock.updateShape(paramIBlockAccess, paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 128 */			 float f = localTileEntityPiston.a(0.0F);
/* 129 */			 if (localTileEntityPiston.b()) {
/* 130 */				 f = 1.0F - f;
/*		 */			 }
/* 132 */			 int i = localTileEntityPiston.c();
/* 133 */			 this.minX = (localBlock.minX - Facing.b[i] * f);
/* 134 */			 this.minY = (localBlock.minY - Facing.c[i] * f);
/* 135 */			 this.minZ = (localBlock.minZ - Facing.d[i] * f);
/* 136 */			 this.maxX = (localBlock.maxX - Facing.b[i] * f);
/* 137 */			 this.maxY = (localBlock.maxY - Facing.c[i] * f);
/* 138 */			 this.maxZ = (localBlock.maxZ - Facing.d[i] * f);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
/*		 */	 {
/* 144 */		 if ((paramInt4 == 0) || (paramInt4 == this.id)) {
/* 145 */			 return null;
/*		 */		 }
/* 147 */		 AxisAlignedBB localAxisAlignedBB = Block.byId[paramInt4].e(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 149 */		 if (localAxisAlignedBB == null) {
/* 150 */			 return null;
/*		 */		 }
/*		 */ 
/* 154 */		 if (Facing.b[paramInt5] < 0)
/* 155 */			 localAxisAlignedBB.a -= Facing.b[paramInt5] * paramFloat;
/*		 */		 else {
/* 157 */			 localAxisAlignedBB.d -= Facing.b[paramInt5] * paramFloat;
/*		 */		 }
/* 159 */		 if (Facing.c[paramInt5] < 0)
/* 160 */			 localAxisAlignedBB.b -= Facing.c[paramInt5] * paramFloat;
/*		 */		 else {
/* 162 */			 localAxisAlignedBB.e -= Facing.c[paramInt5] * paramFloat;
/*		 */		 }
/* 164 */		 if (Facing.d[paramInt5] < 0)
/* 165 */			 localAxisAlignedBB.c -= Facing.d[paramInt5] * paramFloat;
/*		 */		 else {
/* 167 */			 localAxisAlignedBB.f -= Facing.d[paramInt5] * paramFloat;
/*		 */		 }
/* 169 */		 return localAxisAlignedBB;
/*		 */	 }
/*		 */ 
/*		 */	 private TileEntityPiston d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
/* 173 */		 TileEntity localTileEntity = paramIBlockAccess.getTileEntity(paramInt1, paramInt2, paramInt3);
/* 174 */		 if ((localTileEntity instanceof TileEntityPiston)) {
/* 175 */			 return (TileEntityPiston)localTileEntity;
/*		 */		 }
/* 177 */		 return null;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockPistonMoving
 * JD-Core Version:		0.6.0
 */