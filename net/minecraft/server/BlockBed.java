/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class BlockBed extends BlockDirectional
/*		 */ {
/*	17 */	 public static final int[][] a = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*		 */ 
/*		 */	 public BlockBed(int paramInt)
/*		 */	 {
/*	30 */		 super(paramInt, 134, Material.CLOTH);
/*		 */ 
/*	32 */		 n();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	37 */		 if (paramWorld.isStatic) return true;
/*		 */ 
/*	39 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */ 
/*	41 */		 if (!a_(i))
/*		 */		 {
/*	43 */			 int j = d(i);
/*	44 */			 paramInt1 += a[j][0];
/*	45 */			 paramInt3 += a[j][1];
/*	46 */			 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != this.id) {
/*	47 */				 return true;
/*		 */			 }
/*	49 */			 i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/*		 */		 }
/*		 */ 
/*	52 */		 if (!paramWorld.worldProvider.e()) {
/*	53 */			 double d1 = paramInt1 + 0.5D;
/*	54 */			 double d2 = paramInt2 + 0.5D;
/*	55 */			 double d3 = paramInt3 + 0.5D;
/*	56 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*	57 */			 int k = d(i);
/*	58 */			 paramInt1 += a[k][0];
/*	59 */			 paramInt3 += a[k][1];
/*	60 */			 if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) == this.id) {
/*	61 */				 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*	62 */				 d1 = (d1 + paramInt1 + 0.5D) / 2.0D;
/*	63 */				 d2 = (d2 + paramInt2 + 0.5D) / 2.0D;
/*	64 */				 d3 = (d3 + paramInt3 + 0.5D) / 2.0D;
/*		 */			 }
/*	66 */			 paramWorld.createExplosion(null, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, 5.0F, true);
/*	67 */			 return true;
/*		 */		 }
/*		 */ 
/*	70 */		 if (b_(i)) {
/*	71 */			 localObject = null;
/*	72 */			 for (EntityHuman localEntityHuman : paramWorld.players) {
/*	73 */				 if (localEntityHuman.isSleeping()) {
/*	74 */					 ChunkCoordinates localChunkCoordinates = localEntityHuman.bT;
/*	75 */					 if ((localChunkCoordinates.x == paramInt1) && (localChunkCoordinates.y == paramInt2) && (localChunkCoordinates.z == paramInt3)) {
/*	76 */						 localObject = localEntityHuman;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	81 */			 if (localObject == null) {
/*	82 */				 a(paramWorld, paramInt1, paramInt2, paramInt3, false);
/*		 */			 } else {
/*	84 */				 paramEntityHuman.c("tile.bed.occupied");
/*	85 */				 return true;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	89 */		 Object localObject = paramEntityHuman.a(paramInt1, paramInt2, paramInt3);
/*	90 */		 if (localObject == EnumBedResult.OK) {
/*	91 */			 a(paramWorld, paramInt1, paramInt2, paramInt3, true);
/*	92 */			 return true;
/*		 */		 }
/*		 */ 
/*	95 */		 if (localObject == EnumBedResult.NOT_POSSIBLE_NOW)
/*	96 */			 paramEntityHuman.c("tile.bed.noSleep");
/*	97 */		 else if (localObject == EnumBedResult.NOT_SAFE) {
/*	98 */			 paramEntityHuman.c("tile.bed.notSafe");
/*		 */		 }
/* 100 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int paramInt1, int paramInt2)
/*		 */	 {
/* 106 */		 if (paramInt1 == 0) {
/* 107 */			 return Block.WOOD.textureId;
/*		 */		 }
/*		 */ 
/* 110 */		 int i = d(paramInt2);
/* 111 */		 int j = Direction.h[i][paramInt1];
/*		 */ 
/* 113 */		 if (a_(paramInt2))
/*		 */		 {
/* 115 */			 if (j == 2) {
/* 116 */				 return this.textureId + 2 + 16;
/*		 */			 }
/* 118 */			 if ((j == 5) || (j == 4)) {
/* 119 */				 return this.textureId + 1 + 16;
/*		 */			 }
/* 121 */			 return this.textureId + 1;
/*		 */		 }
/* 123 */		 if (j == 3) {
/* 124 */			 return this.textureId - 1 + 16;
/*		 */		 }
/* 126 */		 if ((j == 5) || (j == 4)) {
/* 127 */			 return this.textureId + 16;
/*		 */		 }
/* 129 */		 return this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public int b()
/*		 */	 {
/* 135 */		 return 14;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/* 140 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/* 145 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 150 */		 n();
/*		 */	 }
/*		 */ 
/*		 */	 public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 155 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 156 */		 int j = d(i);
/*		 */ 
/* 158 */		 if (a_(i)) {
/* 159 */			 if (paramWorld.getTypeId(paramInt1 - a[j][0], paramInt2, paramInt3 - a[j][1]) != this.id) {
/* 160 */				 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/*		 */			 }
/*		 */		 }
/* 163 */		 else if (paramWorld.getTypeId(paramInt1 + a[j][0], paramInt2, paramInt3 + a[j][1]) != this.id) {
/* 164 */			 paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
/* 165 */			 if (!paramWorld.isStatic)
/* 166 */				 c(paramWorld, paramInt1, paramInt2, paramInt3, i, 0);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
/*		 */	 {
/* 174 */		 if (a_(paramInt1)) {
/* 175 */			 return 0;
/*		 */		 }
/* 177 */		 return Item.BED.id;
/*		 */	 }
/*		 */ 
/*		 */	 private void n() {
/* 181 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean a_(int paramInt) {
/* 185 */		 return (paramInt & 0x8) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean b_(int paramInt) {
/* 189 */		 return (paramInt & 0x4) != 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/* 193 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 194 */		 if (paramBoolean)
/* 195 */			 i |= 4;
/*		 */		 else {
/* 197 */			 i &= -5;
/*		 */		 }
/* 199 */		 paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
/*		 */	 }
/*		 */ 
/*		 */	 public static ChunkCoordinates b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 203 */		 int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
/* 204 */		 int j = BlockDirectional.d(i);
/*		 */ 
/* 207 */		 for (int k = 0; k <= 1; k++) {
/* 208 */			 int m = paramInt1 - a[j][0] * k - 1;
/* 209 */			 int n = paramInt3 - a[j][1] * k - 1;
/* 210 */			 int i1 = m + 2;
/* 211 */			 int i2 = n + 2;
/*		 */ 
/* 213 */			 for (int i3 = m; i3 <= i1; i3++) {
/* 214 */				 for (int i4 = n; i4 <= i2; i4++) {
/* 215 */					 if ((paramWorld.t(i3, paramInt2 - 1, i4)) && (paramWorld.isEmpty(i3, paramInt2, i4)) && (paramWorld.isEmpty(i3, paramInt2 + 1, i4))) {
/* 216 */						 if (paramInt4 > 0) {
/* 217 */							 paramInt4--;
/*		 */						 }
/*		 */						 else {
/* 220 */							 return new ChunkCoordinates(i3, paramInt2, i4);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 226 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
/*		 */	 {
/* 231 */		 if (!a_(paramInt4))
/* 232 */			 super.dropNaturally(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public int e()
/*		 */	 {
/* 238 */		 return 1;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockBed
 * JD-Core Version:		0.6.0
 */