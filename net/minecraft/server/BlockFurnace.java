/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockFurnace extends BlockContainer
/*		 */ {
/*	20 */	 private Random a = new Random();
/*		 */	 private final boolean b;
/*	22 */	 private static boolean c = false;
/*		 */ 
/*		 */	 protected BlockFurnace(int paramInt, boolean paramBoolean) {
/*	25 */		 super(paramInt, Material.STONE);
/*	26 */		 this.b = paramBoolean;
/*	27 */		 this.textureId = 45;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/*	32 */		 return Block.FURNACE.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	37 */		 super.onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*	38 */		 l(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	43 */		 if (paramWorld.isStatic) {
/*	44 */			 return;
/*		 */		 }
/*		 */ 
/*	47 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
/*	48 */		 int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
/*	49 */		 int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
/*	50 */		 int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);
/*		 */ 
/*	52 */		 int n = 3;
/*	53 */		 if ((Block.n[i] != 0) && (Block.n[j] == 0)) n = 3;
/*	54 */		 if ((Block.n[j] != 0) && (Block.n[i] == 0)) n = 2;
/*	55 */		 if ((Block.n[k] != 0) && (Block.n[m] == 0)) n = 5;
/*	56 */		 if ((Block.n[m] != 0) && (Block.n[k] == 0)) n = 4;
/*	57 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt)
/*		 */	 {
/* 101 */		 if (paramInt == 1) return this.textureId + 17;
/* 102 */		 if (paramInt == 0) return this.textureId + 17;
/* 103 */		 if (paramInt == 3) return this.textureId - 1;
/* 104 */		 return this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 109 */		 if (paramWorld.isStatic) {
/* 110 */			 return true;
/*		 */		 }
/* 112 */		 TileEntityFurnace localTileEntityFurnace = (TileEntityFurnace)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/* 113 */		 if (localTileEntityFurnace != null) paramEntityHuman.openFurnace(localTileEntityFurnace);
/* 114 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(boolean paramBoolean, World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/* 118 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 119 */		 TileEntity localTileEntity = paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 121 */		 c = true;
/* 122 */		 if (paramBoolean) paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.BURNING_FURNACE.id); else
/* 123 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.FURNACE.id);
/* 124 */		 c = false;
/*		 */ 
/* 126 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */ 
/* 128 */		 if (localTileEntity != null) {
/* 129 */			 localTileEntity.q();
/* 130 */			 paramWorld.setTileEntity(paramInt1, paramInt2, paramInt3, localTileEntity);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity a(World paramWorld)
/*		 */	 {
/* 136 */		 return new TileEntityFurnace();
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/* 141 */		 int i = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
/*		 */ 
/* 143 */		 if (i == 0) paramWorld.setData(paramInt1, paramInt2, paramInt3, 2);
/* 144 */		 if (i == 1) paramWorld.setData(paramInt1, paramInt2, paramInt3, 5);
/* 145 */		 if (i == 2) paramWorld.setData(paramInt1, paramInt2, paramInt3, 3);
/* 146 */		 if (i == 3) paramWorld.setData(paramInt1, paramInt2, paramInt3, 4);
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 151 */		 if (!c) {
/* 152 */			 TileEntityFurnace localTileEntityFurnace = (TileEntityFurnace)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/* 153 */			 if (localTileEntityFurnace != null) {
/* 154 */				 for (int i = 0; i < localTileEntityFurnace.getSize(); i++) {
/* 155 */					 ItemStack localItemStack = localTileEntityFurnace.getItem(i);
/* 156 */					 if (localItemStack != null) {
/* 157 */						 float f1 = this.a.nextFloat() * 0.8F + 0.1F;
/* 158 */						 float f2 = this.a.nextFloat() * 0.8F + 0.1F;
/* 159 */						 float f3 = this.a.nextFloat() * 0.8F + 0.1F;
/*		 */ 
/* 161 */						 while (localItemStack.count > 0) {
/* 162 */							 int j = this.a.nextInt(21) + 10;
/* 163 */							 if (j > localItemStack.count) j = localItemStack.count;
/* 164 */							 localItemStack.count -= j;
/*		 */ 
/* 166 */							 EntityItem localEntityItem = new EntityItem(paramWorld, paramInt1 + f1, paramInt2 + f2, paramInt3 + f3, new ItemStack(localItemStack.id, j, localItemStack.getData()));
/*		 */ 
/* 168 */							 if (localItemStack.hasTag()) {
/* 169 */								 localEntityItem.itemStack.setTag((NBTTagCompound)localItemStack.getTag().clone());
/*		 */							 }
/*		 */ 
/* 172 */							 float f4 = 0.05F;
/* 173 */							 localEntityItem.motX = ((float)this.a.nextGaussian() * f4);
/* 174 */							 localEntityItem.motY = ((float)this.a.nextGaussian() * f4 + 0.2F);
/* 175 */							 localEntityItem.motZ = ((float)this.a.nextGaussian() * f4);
/* 176 */							 paramWorld.addEntity(localEntityItem);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 182 */		 super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockFurnace
 * JD-Core Version:		0.6.0
 */