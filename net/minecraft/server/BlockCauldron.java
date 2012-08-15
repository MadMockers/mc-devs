/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockCauldron extends Block
/*		 */ {
/*		 */	 public BlockCauldron(int paramInt)
/*		 */	 {
/*	18 */		 super(paramInt, Material.ORE);
/*	19 */		 this.textureId = 154;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt1, int paramInt2)
/*		 */	 {
/*	24 */		 if (paramInt1 == 1) {
/*	25 */			 return 138;
/*		 */		 }
/*	27 */		 if (paramInt1 == 0) {
/*	28 */			 return 155;
/*		 */		 }
/*	30 */		 return 154;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
/*		 */	 {
/*	35 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
/*	36 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	37 */		 float f = 0.125F;
/*	38 */		 a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
/*	39 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	40 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
/*	41 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	42 */		 a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*	43 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*	44 */		 a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
/*	45 */		 super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
/*		 */ 
/*	47 */		 f();
/*		 */	 }
/*		 */ 
/*		 */	 public void f()
/*		 */	 {
/*	52 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	57 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	62 */		 return 24;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	67 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	73 */		 if (paramWorld.isStatic) {
/*	74 */			 return true;
/*		 */		 }
/*		 */ 
/*	77 */		 ItemStack localItemStack1 = paramEntityHuman.inventory.getItemInHand();
/*	78 */		 if (localItemStack1 == null) {
/*	79 */			 return true;
/*		 */		 }
/*		 */ 
/*	82 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	84 */		 if (localItemStack1.id == Item.WATER_BUCKET.id) {
/*	85 */			 if (i < 3) {
/*	86 */				 if (!paramEntityHuman.abilities.canInstantlyBuild) {
/*	87 */					 paramEntityHuman.inventory.setItem(paramEntityHuman.inventory.itemInHandIndex, new ItemStack(Item.BUCKET));
/*		 */				 }
/*		 */ 
/*	90 */				 paramWorld.setData(paramInt1, paramInt2, paramInt3, 3);
/*		 */			 }
/*	92 */			 return true;
/*	93 */		 }if ((localItemStack1.id == Item.GLASS_BOTTLE.id) && 
/*	94 */			 (i > 0)) {
/*	95 */			 ItemStack localItemStack2 = new ItemStack(Item.POTION, 1, 0);
/*	96 */			 if (!paramEntityHuman.inventory.pickup(localItemStack2))
/*	97 */				 paramWorld.addEntity(new EntityItem(paramWorld, paramInt1 + 0.5D, paramInt2 + 1.5D, paramInt3 + 0.5D, localItemStack2));
/*	98 */			 else if ((paramEntityHuman instanceof EntityPlayer)) {
/*	99 */				 ((EntityPlayer)paramEntityHuman).updateInventory(paramEntityHuman.defaultContainer);
/*		 */			 }
/*		 */ 
/* 102 */			 localItemStack1.count -= 1;
/* 103 */			 if (localItemStack1.count <= 0) {
/* 104 */				 paramEntityHuman.inventory.setItem(paramEntityHuman.inventory.itemInHandIndex, null);
/*		 */			 }
/* 106 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, i - 1);
/*		 */		 }
/*		 */ 
/* 110 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void f(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 115 */		 if (paramWorld.random.nextInt(20) != 1) return;
/*		 */ 
/* 117 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 119 */		 if (i < 3)
/* 120 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, i + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 126 */		 return Item.CAULDRON.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockCauldron
 * JD-Core Version:		0.6.0
 */