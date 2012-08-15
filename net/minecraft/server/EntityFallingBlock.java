/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class EntityFallingBlock extends Entity
/*		 */ {
/*		 */	 public int id;
/*		 */	 public int data;
/*	15 */	 public int c = 0;
/*	16 */	 public boolean dropItem = true;
/*		 */ 
/*		 */	 public EntityFallingBlock(World paramWorld) {
/*	19 */		 super(paramWorld);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityFallingBlock(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
/*	23 */		 this(paramWorld, paramDouble1, paramDouble2, paramDouble3, paramInt, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityFallingBlock(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt1, int paramInt2) {
/*	27 */		 super(paramWorld);
/*	28 */		 this.id = paramInt1;
/*	29 */		 this.data = paramInt2;
/*	30 */		 this.m = true;
/*	31 */		 a(0.98F, 0.98F);
/*	32 */		 this.height = (this.length / 2.0F);
/*	33 */		 setPosition(paramDouble1, paramDouble2, paramDouble3);
/*		 */ 
/*	35 */		 this.motX = 0.0D;
/*	36 */		 this.motY = 0.0D;
/*	37 */		 this.motZ = 0.0D;
/*		 */ 
/*	39 */		 this.lastX = paramDouble1;
/*	40 */		 this.lastY = paramDouble2;
/*	41 */		 this.lastZ = paramDouble3;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean e_()
/*		 */	 {
/*	47 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean L()
/*		 */	 {
/*	56 */		 return !this.dead;
/*		 */	 }
/*		 */ 
/*		 */	 public void h_()
/*		 */	 {
/*	61 */		 if (this.id == 0) {
/*	62 */			 die();
/*	63 */			 return;
/*		 */		 }
/*		 */ 
/*	66 */		 this.lastX = this.locX;
/*	67 */		 this.lastY = this.locY;
/*	68 */		 this.lastZ = this.locZ;
/*	69 */		 this.c += 1;
/*		 */ 
/*	71 */		 this.motY -= 0.03999999910593033D;
/*	72 */		 move(this.motX, this.motY, this.motZ);
/*	73 */		 this.motX *= 0.9800000190734863D;
/*	74 */		 this.motY *= 0.9800000190734863D;
/*	75 */		 this.motZ *= 0.9800000190734863D;
/*		 */ 
/*	77 */		 if (!this.world.isStatic) {
/*	78 */			 int i = MathHelper.floor(this.locX);
/*	79 */			 int j = MathHelper.floor(this.locY);
/*	80 */			 int k = MathHelper.floor(this.locZ);
/*		 */ 
/*	82 */			 if (this.c == 1) {
/*	83 */				 if ((this.c == 1) && (this.world.getTypeId(i, j, k) == this.id))
/*	84 */					 this.world.setTypeId(i, j, k, 0);
/*		 */				 else {
/*	86 */					 die();
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	90 */			 if (this.onGround) {
/*	91 */				 this.motX *= 0.699999988079071D;
/*	92 */				 this.motZ *= 0.699999988079071D;
/*	93 */				 this.motY *= -0.5D;
/*		 */ 
/*	95 */				 if (this.world.getTypeId(i, j, k) != Block.PISTON_MOVING.id) {
/*	96 */					 die();
/*	97 */					 if (((!this.world.mayPlace(this.id, i, j, k, true, 1, null)) || (BlockSand.canFall(this.world, i, j - 1, k)) || (!this.world.setTypeIdAndData(i, j, k, this.id, this.data))) && 
/*	98 */						 (!this.world.isStatic) && 
/*	99 */						 (this.dropItem)) a(new ItemStack(this.id, 1, this.data), 0.0F);
/*		 */				 }
/*		 */			 }
/* 102 */			 else if (((this.c > 100) && (!this.world.isStatic) && ((j < 1) || (j > 256))) || (this.c > 600)) {
/* 103 */				 if (this.dropItem) b(this.id, 1);
/* 104 */				 die();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 112 */		 paramNBTTagCompound.setByte("Tile", (byte)this.id);
/* 113 */		 paramNBTTagCompound.setByte("Data", (byte)this.data);
/* 114 */		 paramNBTTagCompound.setByte("Time", (byte)this.c);
/* 115 */		 paramNBTTagCompound.setBoolean("DropItem", this.dropItem);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 120 */		 this.id = (paramNBTTagCompound.getByte("Tile") & 0xFF);
/* 121 */		 this.data = (paramNBTTagCompound.getByte("Data") & 0xFF);
/* 122 */		 this.c = (paramNBTTagCompound.getByte("Time") & 0xFF);
/*		 */ 
/* 124 */		 if (paramNBTTagCompound.hasKey("DropItem")) {
/* 125 */			 this.dropItem = paramNBTTagCompound.getBoolean("DropItem");
/*		 */		 }
/*		 */ 
/* 128 */		 if (this.id == 0)
/* 129 */			 this.id = Block.SAND.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityFallingBlock
 * JD-Core Version:		0.6.0
 */