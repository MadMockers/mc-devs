/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class ItemEnderEye extends Item
/*		 */ {
/*		 */	 public ItemEnderEye(int paramInt)
/*		 */	 {
/*	13 */		 super(paramInt);
/*	14 */		 a(CreativeModeTab.f);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	20 */		 int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);
/*	21 */		 int j = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	23 */		 if ((paramEntityHuman.e(paramInt1, paramInt2, paramInt3)) && (i == Block.ENDER_PORTAL_FRAME.id) && (!BlockEnderPortalFrame.e(j))) {
/*	24 */			 if (paramWorld.isStatic) return true;
/*	25 */			 paramWorld.setData(paramInt1, paramInt2, paramInt3, j + 4);
/*	26 */			 paramItemStack.count -= 1;
/*		 */ 
/*	28 */			 for (int k = 0; k < 16; k++) {
/*	29 */				 double d1 = paramInt1 + (5.0F + d.nextFloat() * 6.0F) / 16.0F;
/*	30 */				 double d2 = paramInt2 + 0.8125F;
/*	31 */				 double d3 = paramInt3 + (5.0F + d.nextFloat() * 6.0F) / 16.0F;
/*	32 */				 double d4 = 0.0D;
/*	33 */				 double d5 = 0.0D;
/*	34 */				 double d6 = 0.0D;
/*		 */ 
/*	36 */				 paramWorld.a("smoke", d1, d2, d3, d4, d5, d6);
/*		 */			 }
/*		 */ 
/*	40 */			 k = j & 0x3;
/*		 */ 
/*	43 */			 int m = 0;
/*	44 */			 int n = 0;
/*	45 */			 int i1 = 0;
/*	46 */			 int i2 = 1;
/*	47 */			 int i3 = Direction.f[k];
/*		 */			 int i5;
/*		 */			 int i6;
/*		 */			 int i7;
/*		 */			 int i8;
/*	48 */			 for (int i4 = -2; i4 <= 2; i4++) {
/*	49 */				 i5 = paramInt1 + Direction.a[i3] * i4;
/*	50 */				 i6 = paramInt3 + Direction.b[i3] * i4;
/*		 */ 
/*	52 */				 i7 = paramWorld.getTypeId(i5, paramInt2, i6);
/*	53 */				 if (i7 == Block.ENDER_PORTAL_FRAME.id) {
/*	54 */					 i8 = paramWorld.getData(i5, paramInt2, i6);
/*	55 */					 if (!BlockEnderPortalFrame.e(i8)) {
/*	56 */						 i2 = 0;
/*	57 */						 break;
/*		 */					 }
/*	59 */					 n = i4;
/*	60 */					 if (i1 == 0) {
/*	61 */						 m = i4;
/*	62 */						 i1 = 1;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*	68 */			 if ((i2 != 0) && (n == m + 2))
/*		 */			 {
/*	71 */				 for (i4 = m; i4 <= n; i4++) {
/*	72 */					 i5 = paramInt1 + Direction.a[i3] * i4;
/*	73 */					 i6 = paramInt3 + Direction.b[i3] * i4;
/*	74 */					 i5 += Direction.a[k] * 4;
/*	75 */					 i6 += Direction.b[k] * 4;
/*		 */ 
/*	77 */					 i7 = paramWorld.getTypeId(i5, paramInt2, i6);
/*	78 */					 i8 = paramWorld.getData(i5, paramInt2, i6);
/*	79 */					 if ((i7 != Block.ENDER_PORTAL_FRAME.id) || (!BlockEnderPortalFrame.e(i8))) {
/*	80 */						 i2 = 0;
/*	81 */						 break;
/*		 */					 }
/*		 */				 }
/*		 */ 
/*	85 */				 for (i4 = m - 1; i4 <= n + 1; i4 += 4) {
/*	86 */					 for (i5 = 1; i5 <= 3; i5++) {
/*	87 */						 i6 = paramInt1 + Direction.a[i3] * i4;
/*	88 */						 i7 = paramInt3 + Direction.b[i3] * i4;
/*	89 */						 i6 += Direction.a[k] * i5;
/*	90 */						 i7 += Direction.b[k] * i5;
/*		 */ 
/*	92 */						 i8 = paramWorld.getTypeId(i6, paramInt2, i7);
/*	93 */						 int i9 = paramWorld.getData(i6, paramInt2, i7);
/*	94 */						 if ((i8 != Block.ENDER_PORTAL_FRAME.id) || (!BlockEnderPortalFrame.e(i9))) {
/*	95 */							 i2 = 0;
/*	96 */							 break;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/* 100 */				 if (i2 != 0)
/*		 */				 {
/* 102 */					 for (i4 = m; i4 <= n; i4++) {
/* 103 */						 for (i5 = 1; i5 <= 3; i5++) {
/* 104 */							 i6 = paramInt1 + Direction.a[i3] * i4;
/* 105 */							 i7 = paramInt3 + Direction.b[i3] * i4;
/* 106 */							 i6 += Direction.a[k] * i5;
/* 107 */							 i7 += Direction.b[k] * i5;
/*		 */ 
/* 109 */							 paramWorld.setTypeId(i6, paramInt2, i7, Block.ENDER_PORTAL.id);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 115 */			 return true;
/*		 */		 }
/* 117 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
/*		 */	 {
/* 123 */		 MovingObjectPosition localMovingObjectPosition = a(paramWorld, paramEntityHuman, false);
/* 124 */		 if ((localMovingObjectPosition != null) && (localMovingObjectPosition.type == EnumMovingObjectType.TILE)) {
/* 125 */			 int i = paramWorld.getTypeId(localMovingObjectPosition.b, localMovingObjectPosition.c, localMovingObjectPosition.d);
/* 126 */			 if (i == Block.ENDER_PORTAL_FRAME.id) {
/* 127 */				 return paramItemStack;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 131 */		 if (!paramWorld.isStatic) {
/* 132 */			 ChunkPosition localChunkPosition = paramWorld.b("Stronghold", (int)paramEntityHuman.locX, (int)paramEntityHuman.locY, (int)paramEntityHuman.locZ);
/* 133 */			 if (localChunkPosition != null) {
/* 134 */				 EntityEnderSignal localEntityEnderSignal = new EntityEnderSignal(paramWorld, paramEntityHuman.locX, paramEntityHuman.locY + 1.62D - paramEntityHuman.height, paramEntityHuman.locZ);
/* 135 */				 localEntityEnderSignal.a(localChunkPosition.x, localChunkPosition.y, localChunkPosition.z);
/* 136 */				 paramWorld.addEntity(localEntityEnderSignal);
/*		 */ 
/* 138 */				 paramWorld.makeSound(paramEntityHuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
/* 139 */				 paramWorld.a(null, 1002, (int)paramEntityHuman.locX, (int)paramEntityHuman.locY, (int)paramEntityHuman.locZ, 0);
/* 140 */				 if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 141 */					 paramItemStack.count -= 1;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 145 */		 return paramItemStack;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemEnderEye
 * JD-Core Version:		0.6.0
 */