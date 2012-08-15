/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockBrewingStand extends BlockContainer
/*		 */ {
/*	19 */	 private Random a = new Random();
/*		 */ 
/*		 */	 public BlockBrewingStand(int paramInt) {
/*	22 */		 super(paramInt, Material.ORE);
/*	23 */		 this.textureId = 157;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	28 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	33 */		 return 25;
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity a(World paramWorld)
/*		 */	 {
/*	38 */		 return new TileEntityBrewingStand();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	43 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
/*		 */	 {
/*	48 */		 a(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
/*	49 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	50 */		 f();
/*	51 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/*	56 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	62 */		 if (paramWorld.isStatic) {
/*	63 */			 return true;
/*		 */		 }
/*	65 */		 TileEntityBrewingStand localTileEntityBrewingStand = (TileEntityBrewingStand)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/*	66 */		 if (localTileEntityBrewingStand != null) paramEntityHuman.openBrewingStand(localTileEntityBrewingStand);
/*		 */ 
/*	68 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/*	82 */		 TileEntity localTileEntity = paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/*	83 */		 if ((localTileEntity instanceof TileEntityBrewingStand)) {
/*	84 */			 TileEntityBrewingStand localTileEntityBrewingStand = (TileEntityBrewingStand)localTileEntity;
/*	85 */			 for (int i = 0; i < localTileEntityBrewingStand.getSize(); i++) {
/*	86 */				 ItemStack localItemStack = localTileEntityBrewingStand.getItem(i);
/*	87 */				 if (localItemStack != null) {
/*	88 */					 float f1 = this.a.nextFloat() * 0.8F + 0.1F;
/*	89 */					 float f2 = this.a.nextFloat() * 0.8F + 0.1F;
/*	90 */					 float f3 = this.a.nextFloat() * 0.8F + 0.1F;
/*		 */ 
/*	92 */					 while (localItemStack.count > 0) {
/*	93 */						 int j = this.a.nextInt(21) + 10;
/*	94 */						 if (j > localItemStack.count) j = localItemStack.count;
/*	95 */						 localItemStack.count -= j;
/*		 */ 
/*	97 */						 EntityItem localEntityItem = new EntityItem(paramWorld, paramInt1 + f1, paramInt2 + f2, paramInt3 + f3, new ItemStack(localItemStack.id, j, localItemStack.getData()));
/*	98 */						 float f4 = 0.05F;
/*	99 */						 localEntityItem.motX = ((float)this.a.nextGaussian() * f4);
/* 100 */						 localEntityItem.motY = ((float)this.a.nextGaussian() * f4 + 0.2F);
/* 101 */						 localEntityItem.motZ = ((float)this.a.nextGaussian() * f4);
/* 102 */						 paramWorld.addEntity(localEntityItem);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 107 */		 super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 112 */		 return Item.BREWING_STAND.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockBrewingStand
 * JD-Core Version:		0.6.0
 */