/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockChest extends BlockContainer
/*		 */ {
/*	23 */	 private Random a = new Random();
/*		 */ 
/*		 */	 protected BlockChest(int paramInt) {
/*	26 */		 super(paramInt, Material.WOOD);
/*	27 */		 this.textureId = 26;
/*	28 */		 a(CreativeModeTab.c);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	33 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	38 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	43 */		 return 22;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	48 */		 super.onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*	49 */		 b_(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	51 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
/*	52 */		 int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
/*	53 */		 int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
/*	54 */		 int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);
/*	55 */		 if (i == this.id) b_(paramWorld, paramInt1, paramInt2, paramInt3 - 1);
/*	56 */		 if (j == this.id) b_(paramWorld, paramInt1, paramInt2, paramInt3 + 1);
/*	57 */		 if (k == this.id) b_(paramWorld, paramInt1 - 1, paramInt2, paramInt3);
/*	58 */		 if (m == this.id) b_(paramWorld, paramInt1 + 1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/*	63 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
/*	64 */		 int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
/*	65 */		 int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
/*	66 */		 int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);
/*		 */ 
/*	68 */		 int n = 0;
/*	69 */		 int i1 = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
/*		 */ 
/*	71 */		 if (i1 == 0) n = 2;
/*	72 */		 if (i1 == 1) n = 5;
/*	73 */		 if (i1 == 2) n = 3;
/*	74 */		 if (i1 == 3) n = 4;
/*		 */ 
/*	76 */		 if ((i != this.id) && (j != this.id) && (k != this.id) && (m != this.id)) {
/*	77 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
/*		 */		 } else {
/*	79 */			 if (((i == this.id) || (j == this.id)) && ((n == 4) || (n == 5))) {
/*	80 */				 if (i == this.id) paramWorld.setData(paramInt1, paramInt2, paramInt3 - 1, n); else
/*	81 */					 paramWorld.setData(paramInt1, paramInt2, paramInt3 + 1, n);
/*	82 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
/*		 */			 }
/*	84 */			 if (((k == this.id) || (m == this.id)) && ((n == 2) || (n == 3))) {
/*	85 */				 if (k == this.id) paramWorld.setData(paramInt1 - 1, paramInt2, paramInt3, n); else
/*	86 */					 paramWorld.setData(paramInt1 + 1, paramInt2, paramInt3, n);
/*	87 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b_(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/*	93 */		 if (paramWorld.isStatic) {
/*	94 */			 return;
/*		 */		 }
/*		 */ 
/*	97 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1);
/*	98 */		 int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1);
/*	99 */		 int k = paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3);
/* 100 */		 int m = paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3);
/*		 */ 
/* 103 */		 int n = 4;
/*		 */		 int i1;
/*		 */		 int i2;
/*		 */		 int i3;
/* 104 */		 if ((i == this.id) || (j == this.id)) {
/* 105 */			 i1 = paramWorld.getTypeId(paramInt1 - 1, paramInt2, i == this.id ? paramInt3 - 1 : paramInt3 + 1);
/* 106 */			 i2 = paramWorld.getTypeId(paramInt1 + 1, paramInt2, i == this.id ? paramInt3 - 1 : paramInt3 + 1);
/*		 */ 
/* 108 */			 n = 5;
/*		 */ 
/* 110 */			 i3 = -1;
/* 111 */			 if (i == this.id) i3 = paramWorld.getData(paramInt1, paramInt2, paramInt3 - 1); else
/* 112 */				 i3 = paramWorld.getData(paramInt1, paramInt2, paramInt3 + 1);
/* 113 */			 if (i3 == 4) n = 4;
/*		 */ 
/* 115 */			 if (((Block.n[k] != 0) || (Block.n[i1] != 0)) && (Block.n[m] == 0) && (Block.n[i2] == 0)) n = 5;
/* 116 */			 if (((Block.n[m] != 0) || (Block.n[i2] != 0)) && (Block.n[k] == 0) && (Block.n[i1] == 0)) n = 4; 
/*		 */		 }
/* 117 */		 else if ((k == this.id) || (m == this.id)) {
/* 118 */			 i1 = paramWorld.getTypeId(k == this.id ? paramInt1 - 1 : paramInt1 + 1, paramInt2, paramInt3 - 1);
/* 119 */			 i2 = paramWorld.getTypeId(k == this.id ? paramInt1 - 1 : paramInt1 + 1, paramInt2, paramInt3 + 1);
/*		 */ 
/* 121 */			 n = 3;
/* 122 */			 i3 = -1;
/* 123 */			 if (k == this.id) i3 = paramWorld.getData(paramInt1 - 1, paramInt2, paramInt3); else
/* 124 */				 i3 = paramWorld.getData(paramInt1 + 1, paramInt2, paramInt3);
/* 125 */			 if (i3 == 2) n = 2;
/*		 */ 
/* 127 */			 if (((Block.n[i] != 0) || (Block.n[i1] != 0)) && (Block.n[j] == 0) && (Block.n[i2] == 0)) n = 3;
/* 128 */			 if (((Block.n[j] != 0) || (Block.n[i2] != 0)) && (Block.n[i] == 0) && (Block.n[i1] == 0)) n = 2; 
/*		 */		 }
/*		 */		 else {
/* 130 */			 n = 3;
/* 131 */			 if ((Block.n[i] != 0) && (Block.n[j] == 0)) n = 3;
/* 132 */			 if ((Block.n[j] != 0) && (Block.n[i] == 0)) n = 2;
/* 133 */			 if ((Block.n[k] != 0) && (Block.n[m] == 0)) n = 5;
/* 134 */			 if ((Block.n[m] != 0) && (Block.n[k] == 0)) n = 4;
/*		 */		 }
/*		 */ 
/* 137 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, n);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt)
/*		 */	 {
/* 196 */		 return 4;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 205 */		 int i = 0;
/*		 */ 
/* 207 */		 if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) i++;
/* 208 */		 if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) i++;
/* 209 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) i++;
/* 210 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id) i++;
/*		 */ 
/* 212 */		 if (i > 1) return false;
/*		 */ 
/* 214 */		 if (l(paramWorld, paramInt1 - 1, paramInt2, paramInt3)) return false;
/* 215 */		 if (l(paramWorld, paramInt1 + 1, paramInt2, paramInt3)) return false;
/* 216 */		 if (l(paramWorld, paramInt1, paramInt2, paramInt3 - 1)) return false;
/* 217 */		 return !l(paramWorld, paramInt1, paramInt2, paramInt3 + 1);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean l(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 222 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != this.id) return false;
/* 223 */		 if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) return true;
/* 224 */		 if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) return true;
/* 225 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) return true;
/* 226 */		 return paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 232 */		 super.doPhysics(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
/* 233 */		 TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/* 234 */		 if (localTileEntityChest != null) localTileEntityChest.h();
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 239 */		 TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/* 240 */		 if (localTileEntityChest != null) {
/* 241 */			 for (int i = 0; i < localTileEntityChest.getSize(); i++) {
/* 242 */				 ItemStack localItemStack = localTileEntityChest.getItem(i);
/* 243 */				 if (localItemStack != null) {
/* 244 */					 float f1 = this.a.nextFloat() * 0.8F + 0.1F;
/* 245 */					 float f2 = this.a.nextFloat() * 0.8F + 0.1F;
/* 246 */					 float f3 = this.a.nextFloat() * 0.8F + 0.1F;
/*		 */ 
/* 248 */					 while (localItemStack.count > 0) {
/* 249 */						 int j = this.a.nextInt(21) + 10;
/* 250 */						 if (j > localItemStack.count) j = localItemStack.count;
/* 251 */						 localItemStack.count -= j;
/*		 */ 
/* 253 */						 EntityItem localEntityItem = new EntityItem(paramWorld, paramInt1 + f1, paramInt2 + f2, paramInt3 + f3, new ItemStack(localItemStack.id, j, localItemStack.getData()));
/* 254 */						 float f4 = 0.05F;
/* 255 */						 localEntityItem.motX = ((float)this.a.nextGaussian() * f4);
/* 256 */						 localEntityItem.motY = ((float)this.a.nextGaussian() * f4 + 0.2F);
/* 257 */						 localEntityItem.motZ = ((float)this.a.nextGaussian() * f4);
/* 258 */						 if (localItemStack.hasTag()) {
/* 259 */							 localEntityItem.itemStack.setTag((NBTTagCompound)localItemStack.getTag().clone());
/*		 */						 }
/* 261 */						 paramWorld.addEntity(localEntityItem);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 266 */		 super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 271 */		 Object localObject = (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
/* 272 */		 if (localObject == null) return true;
/*		 */ 
/* 274 */		 if (paramWorld.s(paramInt1, paramInt2 + 1, paramInt3)) return true;
/* 275 */		 if (n(paramWorld, paramInt1, paramInt2, paramInt3)) return true;
/*		 */ 
/* 277 */		 if ((paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) && ((paramWorld.s(paramInt1 - 1, paramInt2 + 1, paramInt3)) || (n(paramWorld, paramInt1 - 1, paramInt2, paramInt3)))) return true;
/* 278 */		 if ((paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) && ((paramWorld.s(paramInt1 + 1, paramInt2 + 1, paramInt3)) || (n(paramWorld, paramInt1 + 1, paramInt2, paramInt3)))) return true;
/* 279 */		 if ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) && ((paramWorld.s(paramInt1, paramInt2 + 1, paramInt3 - 1)) || (n(paramWorld, paramInt1, paramInt2, paramInt3 - 1)))) return true;
/* 280 */		 if ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id) && ((paramWorld.s(paramInt1, paramInt2 + 1, paramInt3 + 1)) || (n(paramWorld, paramInt1, paramInt2, paramInt3 + 1)))) return true;
/*		 */ 
/* 282 */		 if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (TileEntityChest)paramWorld.getTileEntity(paramInt1 - 1, paramInt2, paramInt3), (IInventory)localObject);
/* 283 */		 if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (IInventory)localObject, (TileEntityChest)paramWorld.getTileEntity(paramInt1 + 1, paramInt2, paramInt3));
/* 284 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3 - 1), (IInventory)localObject);
/* 285 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == this.id) localObject = new InventoryLargeChest("container.chestDouble", (IInventory)localObject, (TileEntityChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3 + 1));
/*		 */ 
/* 287 */		 if (paramWorld.isStatic) {
/* 288 */			 return true;
/*		 */		 }
/*		 */ 
/* 291 */		 paramEntityHuman.openContainer((IInventory)localObject);
/*		 */ 
/* 293 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity a(World paramWorld)
/*		 */	 {
/* 298 */		 return new TileEntityChest();
/*		 */	 }
/*		 */ 
/*		 */	 private static boolean n(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/* 302 */		 for (EntityOcelot localEntityOcelot1 : paramWorld.a(EntityOcelot.class, AxisAlignedBB.a().a(paramInt1, paramInt2 + 1, paramInt3, paramInt1 + 1, paramInt2 + 2, paramInt3 + 1))) {
/* 303 */			 EntityOcelot localEntityOcelot2 = (EntityOcelot)localEntityOcelot1;
/* 304 */			 if (localEntityOcelot2.isSitting()) return true;
/*		 */		 }
/* 306 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockChest
 * JD-Core Version:		0.6.0
 */