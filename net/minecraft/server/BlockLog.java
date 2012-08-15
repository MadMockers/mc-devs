/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockLog extends Block
/*		 */ {
/*	24 */	 public static final String[] a = { "oak", "spruce", "birch", "jungle" };
/*		 */ 
/*		 */	 protected BlockLog(int paramInt)
/*		 */	 {
/*	29 */		 super(paramInt, Material.WOOD);
/*	30 */		 this.textureId = 20;
/*	31 */		 a(CreativeModeTab.b);
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/*	36 */		 return 31;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random paramRandom)
/*		 */	 {
/*	41 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/*	46 */		 return Block.LOG.id;
/*		 */	 }
/*		 */ 
/*		 */	 public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/*	51 */		 int i = 4;
/*	52 */		 int j = i + 1;
/*		 */ 
/*	54 */		 if (paramWorld.c(paramInt1 - j, paramInt2 - j, paramInt3 - j, paramInt1 + j, paramInt2 + j, paramInt3 + j))
/*	55 */			 for (int k = -i; k <= i; k++)
/*	56 */				 for (int m = -i; m <= i; m++)
/*	57 */					 for (int n = -i; n <= i; n++) {
/*	58 */						 int i1 = paramWorld.getTypeId(paramInt1 + k, paramInt2 + m, paramInt3 + n);
/*	59 */						 if (i1 == Block.LEAVES.id) {
/*	60 */							 int i2 = paramWorld.getData(paramInt1 + k, paramInt2 + m, paramInt3 + n);
/*	61 */							 if ((i2 & 0x8) == 0)
/*	62 */								 paramWorld.setRawData(paramInt1 + k, paramInt2 + m, paramInt3 + n, i2 | 0x8);
/*		 */						 }
/*		 */					 }
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/*	71 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x3;
/*	72 */		 int j = BlockPiston.b(paramWorld, paramInt1, paramInt2, paramInt3, (EntityHuman)paramEntityLiving);
/*	73 */		 int k = 0;
/*		 */ 
/*	75 */		 switch (j) {
/*		 */		 case 2:
/*		 */		 case 3:
/*	78 */			 k = 8;
/*	79 */			 break;
/*		 */		 case 4:
/*		 */		 case 5:
/*	82 */			 k = 4;
/*	83 */			 break;
/*		 */		 case 0:
/*		 */		 case 1:
/*	86 */			 k = 0;
/*		 */		 }
/*		 */ 
/*	90 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i | k);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt1, int paramInt2)
/*		 */	 {
/*	95 */		 int i = paramInt2 & 0xC;
/*	96 */		 int j = paramInt2 & 0x3;
/*		 */ 
/*	98 */		 if ((i == 0) && ((paramInt1 == 1) || (paramInt1 == 0)))
/*	99 */			 return 21;
/* 100 */		 if ((i == 4) && ((paramInt1 == 5) || (paramInt1 == 4)))
/* 101 */			 return 21;
/* 102 */		 if ((i == 8) && ((paramInt1 == 2) || (paramInt1 == 3))) {
/* 103 */			 return 21;
/*		 */		 }
/*		 */ 
/* 106 */		 if (j == 1) return 116;
/* 107 */		 if (j == 2) return 117;
/* 108 */		 if (j == 3) return 153;
/*		 */ 
/* 110 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 protected int getDropData(int paramInt)
/*		 */	 {
/* 115 */		 return paramInt & 0x3;
/*		 */	 }
/*		 */ 
/*		 */	 public static int e(int paramInt) {
/* 119 */		 return paramInt & 0x3;
/*		 */	 }
/*		 */ 
/*		 */	 protected ItemStack c_(int paramInt)
/*		 */	 {
/* 133 */		 return new ItemStack(this.id, 1, e(paramInt));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockLog
 * JD-Core Version:		0.6.0
 */