/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockDiode extends BlockDirectional
/*		 */ {
/*	18 */	 public static final double[] a = { -0.0625D, 0.0625D, 0.1875D, 0.3125D };
/*		 */ 
/*	22 */	 private static final int[] b = { 1, 2, 3, 4 };
/*		 */	 private final boolean c;
/*		 */ 
/*		 */	 protected BlockDiode(int paramInt, boolean paramBoolean)
/*		 */	 {
/*	29 */		 super(paramInt, 6, Material.ORIENTABLE);
/*	30 */		 this.c = paramBoolean;
/*	31 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	36 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	41 */		 if (!paramWorld.t(paramInt1, paramInt2 - 1, paramInt3)) {
/*	42 */			 return false;
/*		 */		 }
/*	44 */		 return super.canPlace(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	49 */		 if (!paramWorld.t(paramInt1, paramInt2 - 1, paramInt3)) {
/*	50 */			 return false;
/*		 */		 }
/*	52 */		 return super.d(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
/*		 */	 {
/*	58 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	59 */		 boolean bool = e(paramWorld, paramInt1, paramInt2, paramInt3, i);
/*	60 */		 if ((this.c) && (!bool)) {
/*	61 */			 paramWorld.setTypeIdAndData(paramInt1, paramInt2, paramInt3, Block.DIODE_OFF.id, i);
/*	62 */		 } else if (!this.c)
/*		 */		 {
/*	65 */			 paramWorld.setTypeIdAndData(paramInt1, paramInt2, paramInt3, Block.DIODE_ON.id, i);
/*	66 */			 if (!bool) {
/*	67 */				 int j = (i & 0xC) >> 2;
/*	68 */				 paramWorld.a(paramInt1, paramInt2, paramInt3, Block.DIODE_ON.id, b[j] * 2);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt1, int paramInt2)
/*		 */	 {
/*	76 */		 if (paramInt1 == 0) {
/*	77 */			 if (this.c) {
/*	78 */				 return 99;
/*		 */			 }
/*	80 */			 return 115;
/*		 */		 }
/*	82 */		 if (paramInt1 == 1) {
/*	83 */			 if (this.c) {
/*	84 */				 return 147;
/*		 */			 }
/*	86 */			 return 131;
/*		 */		 }
/*		 */ 
/*	89 */		 return 5;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/* 103 */		 return 15;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt)
/*		 */	 {
/* 108 */		 return a(paramInt, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 113 */		 return a(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 118 */		 if (!this.c) {
/* 119 */			 return false;
/*		 */		 }
/*		 */ 
/* 122 */		 int i = d(paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3));
/*		 */ 
/* 124 */		 if ((i == 0) && (paramInt4 == 3)) return true;
/* 125 */		 if ((i == 1) && (paramInt4 == 4)) return true;
/* 126 */		 if ((i == 2) && (paramInt4 == 2)) return true;
/* 127 */		 return (i == 3) && (paramInt4 == 5);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 135 */		 if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
/* 136 */			 c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
/* 137 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/* 138 */			 paramWorld.applyPhysics(paramInt1 + 1, paramInt2, paramInt3, this.id);
/* 139 */			 paramWorld.applyPhysics(paramInt1 - 1, paramInt2, paramInt3, this.id);
/* 140 */			 paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 + 1, this.id);
/* 141 */			 paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 - 1, this.id);
/* 142 */			 paramWorld.applyPhysics(paramInt1, paramInt2 - 1, paramInt3, this.id);
/* 143 */			 paramWorld.applyPhysics(paramInt1, paramInt2 + 1, paramInt3, this.id);
/* 144 */			 return;
/*		 */		 }
/*		 */ 
/* 147 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/* 149 */		 boolean bool = e(paramWorld, paramInt1, paramInt2, paramInt3, i);
/* 150 */		 int j = (i & 0xC) >> 2;
/* 151 */		 if (((this.c) && (!bool)) || ((!this.c) && (bool)))
/* 152 */			 paramWorld.a(paramInt1, paramInt2, paramInt3, this.id, b[j] * 2);
/*		 */	 }
/*		 */ 
/*		 */	 private boolean e(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 157 */		 int i = d(paramInt4);
/* 158 */		 switch (i) {
/*		 */		 case 0:
/* 160 */			 return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1, paramInt2, paramInt3 + 1, 3)) || ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1, paramInt2, paramInt3 + 1) > 0));
/*		 */		 case 2:
/* 162 */			 return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1, paramInt2, paramInt3 - 1, 2)) || ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1, paramInt2, paramInt3 - 1) > 0));
/*		 */		 case 3:
/* 164 */			 return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1 + 1, paramInt2, paramInt3, 5)) || ((paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1 + 1, paramInt2, paramInt3) > 0));
/*		 */		 case 1:
/* 166 */			 return (paramWorld.isBlockFaceIndirectlyPowered(paramInt1 - 1, paramInt2, paramInt3, 4)) || ((paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == Block.REDSTONE_WIRE.id) && (paramWorld.getData(paramInt1 - 1, paramInt2, paramInt3) > 0));
/*		 */		 }
/* 168 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 174 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 175 */		 int j = (i & 0xC) >> 2;
/* 176 */		 j = j + 1 << 2 & 0xC;
/*		 */ 
/* 178 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, j | i & 0x3);
/* 179 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource()
/*		 */	 {
/* 184 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
/*		 */	 {
/* 189 */		 int i = ((MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3) + 2) % 4;
/* 190 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */ 
/* 192 */		 boolean bool = e(paramWorld, paramInt1, paramInt2, paramInt3, i);
/* 193 */		 if (bool)
/* 194 */			 paramWorld.a(paramInt1, paramInt2, paramInt3, this.id, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 206 */		 paramWorld.applyPhysics(paramInt1 + 1, paramInt2, paramInt3, this.id);
/* 207 */		 paramWorld.applyPhysics(paramInt1 - 1, paramInt2, paramInt3, this.id);
/* 208 */		 paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 + 1, this.id);
/* 209 */		 paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 - 1, this.id);
/* 210 */		 paramWorld.applyPhysics(paramInt1, paramInt2 - 1, paramInt3, this.id);
/* 211 */		 paramWorld.applyPhysics(paramInt1, paramInt2 + 1, paramInt3, this.id);
/*		 */	 }
/*		 */ 
/*		 */	 public void postBreak(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 216 */		 if (this.c) {
/* 217 */			 paramWorld.applyPhysics(paramInt1 + 1, paramInt2, paramInt3, this.id);
/* 218 */			 paramWorld.applyPhysics(paramInt1 - 1, paramInt2, paramInt3, this.id);
/* 219 */			 paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 + 1, this.id);
/* 220 */			 paramWorld.applyPhysics(paramInt1, paramInt2, paramInt3 - 1, this.id);
/* 221 */			 paramWorld.applyPhysics(paramInt1, paramInt2 - 1, paramInt3, this.id);
/* 222 */			 paramWorld.applyPhysics(paramInt1, paramInt2 + 1, paramInt3, this.id);
/*		 */		 }
/* 224 */		 super.postBreak(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/* 229 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 234 */		 return Item.DIODE.id;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockDiode
 * JD-Core Version:		0.6.0
 */