/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public abstract class BlockFluids extends Block
/*		 */ {
/*		 */	 protected BlockFluids(int paramInt, Material paramMaterial)
/*		 */	 {
/*	13 */		 super(paramInt, (paramMaterial == Material.LAVA ? 14 : 12) * 16 + 13, paramMaterial);
/*	14 */		 float f1 = 0.0F;
/*	15 */		 float f2 = 0.0F;
/*		 */ 
/*	17 */		 a(0.0F + f2, 0.0F + f1, 0.0F + f2, 1.0F + f2, 1.0F + f1, 1.0F + f2);
/*	18 */		 b(true);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	23 */		 return this.material != Material.LAVA;
/*		 */	 }
/*		 */ 
/*		 */	 public static float d(int paramInt)
/*		 */	 {
/*	54 */		 if (paramInt >= 8) paramInt = 0;
/*	55 */		 return (paramInt + 1) / 9.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt)
/*		 */	 {
/*	60 */		 if ((paramInt == 0) || (paramInt == 1)) {
/*	61 */			 return this.textureId;
/*		 */		 }
/*	63 */		 return this.textureId + 1;
/*		 */	 }
/*		 */ 
/*		 */	 protected int f_(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	68 */		 if (paramWorld.getMaterial(paramInt1, paramInt2, paramInt3) == this.material) return paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*	69 */		 return -1;
/*		 */	 }
/*		 */ 
/*		 */	 protected int d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
/*	73 */		 if (paramIBlockAccess.getMaterial(paramInt1, paramInt2, paramInt3) != this.material) return -1;
/*	74 */		 int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);
/*	75 */		 if (i >= 8) i = 0;
/*	76 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	81 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	86 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int paramInt, boolean paramBoolean)
/*		 */	 {
/*	91 */		 return (paramBoolean) && (paramInt == 0);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/*	96 */		 Material localMaterial = paramIBlockAccess.getMaterial(paramInt1, paramInt2, paramInt3);
/*	97 */		 if (localMaterial == this.material) return false;
/*	98 */		 if (paramInt4 == 1) return true;
/*	99 */		 if (localMaterial == Material.ICE) return false;
/* 100 */		 return super.d(paramIBlockAccess, paramInt1, paramInt2, paramInt3, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 116 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/* 121 */		 return 4;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 126 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random paramRandom)
/*		 */	 {
/* 131 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 private Vec3D i(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
/* 135 */		 Vec3D localVec3D = Vec3D.a().create(0.0D, 0.0D, 0.0D);
/* 136 */		 int i = d(paramIBlockAccess, paramInt1, paramInt2, paramInt3);
/* 137 */		 for (int j = 0; j < 4; j++)
/*		 */		 {
/* 139 */			 int k = paramInt1;
/* 140 */			 int m = paramInt2;
/* 141 */			 int n = paramInt3;
/*		 */ 
/* 143 */			 if (j == 0) k--;
/* 144 */			 if (j == 1) n--;
/* 145 */			 if (j == 2) k++;
/* 146 */			 if (j == 3) n++;
/*		 */ 
/* 148 */			 int i1 = d(paramIBlockAccess, k, m, n);
/*		 */			 int i2;
/* 149 */			 if (i1 < 0) {
/* 150 */				 if (!paramIBlockAccess.getMaterial(k, m, n).isSolid()) {
/* 151 */					 i1 = d(paramIBlockAccess, k, m - 1, n);
/* 152 */					 if (i1 >= 0) {
/* 153 */						 i2 = i1 - (i - 8);
/* 154 */						 localVec3D = localVec3D.add((k - paramInt1) * i2, (m - paramInt2) * i2, (n - paramInt3) * i2);
/*		 */					 }
/*		 */				 }
/*		 */			 }
/* 158 */			 else if (i1 >= 0) {
/* 159 */				 i2 = i1 - i;
/* 160 */				 localVec3D = localVec3D.add((k - paramInt1) * i2, (m - paramInt2) * i2, (n - paramInt3) * i2);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 165 */		 if (paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3) >= 8) {
/* 166 */			 j = 0;
/* 167 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 - 1, 2))) j = 1;
/* 168 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2, paramInt3 + 1, 3))) j = 1;
/* 169 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1 - 1, paramInt2, paramInt3, 4))) j = 1;
/* 170 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1 + 1, paramInt2, paramInt3, 5))) j = 1;
/* 171 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2 + 1, paramInt3 - 1, 2))) j = 1;
/* 172 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1, paramInt2 + 1, paramInt3 + 1, 3))) j = 1;
/* 173 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1 - 1, paramInt2 + 1, paramInt3, 4))) j = 1;
/* 174 */			 if ((j != 0) || (d(paramIBlockAccess, paramInt1 + 1, paramInt2 + 1, paramInt3, 5))) j = 1;
/* 175 */			 if (j != 0) localVec3D = localVec3D.b().add(0.0D, -6.0D, 0.0D);
/*		 */		 }
/* 177 */		 localVec3D = localVec3D.b();
/* 178 */		 return localVec3D;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity, Vec3D paramVec3D)
/*		 */	 {
/* 183 */		 Vec3D localVec3D = i(paramWorld, paramInt1, paramInt2, paramInt3);
/* 184 */		 paramVec3D.a += localVec3D.a;
/* 185 */		 paramVec3D.b += localVec3D.b;
/* 186 */		 paramVec3D.c += localVec3D.c;
/*		 */	 }
/*		 */ 
/*		 */	 public int p_()
/*		 */	 {
/* 191 */		 if (this.material == Material.WATER) return 5;
/* 192 */		 if (this.material == Material.LAVA) return 30;
/* 193 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 304 */		 l(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 309 */		 l(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 private void l(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/* 313 */		 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != this.id) return;
/* 314 */		 if (this.material == Material.LAVA) {
/* 315 */			 int i = 0;
/* 316 */			 if ((i != 0) || (paramWorld.getMaterial(paramInt1, paramInt2, paramInt3 - 1) == Material.WATER)) i = 1;
/* 317 */			 if ((i != 0) || (paramWorld.getMaterial(paramInt1, paramInt2, paramInt3 + 1) == Material.WATER)) i = 1;
/* 318 */			 if ((i != 0) || (paramWorld.getMaterial(paramInt1 - 1, paramInt2, paramInt3) == Material.WATER)) i = 1;
/* 319 */			 if ((i != 0) || (paramWorld.getMaterial(paramInt1 + 1, paramInt2, paramInt3) == Material.WATER)) i = 1;
/* 320 */			 if ((i != 0) || (paramWorld.getMaterial(paramInt1, paramInt2 + 1, paramInt3) == Material.WATER)) i = 1;
/* 321 */			 if (i != 0) {
/* 322 */				 int j = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 323 */				 if (j == 0)
/* 324 */					 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.OBSIDIAN.id);
/* 325 */				 else if (j <= 4) {
/* 326 */					 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.COBBLESTONE.id);
/*		 */				 }
/* 328 */				 fizz(paramWorld, paramInt1, paramInt2, paramInt3);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void fizz(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
/* 334 */		 paramWorld.makeSound(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, "random.fizz", 0.5F, 2.6F + (paramWorld.random.nextFloat() - paramWorld.random.nextFloat()) * 0.8F);
/* 335 */		 for (int i = 0; i < 8; i++)
/* 336 */			 paramWorld.a("largesmoke", paramInt1 + Math.random(), paramInt2 + 1.2D, paramInt3 + Math.random(), 0.0D, 0.0D, 0.0D);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockFluids
 * JD-Core Version:		0.6.0
 */