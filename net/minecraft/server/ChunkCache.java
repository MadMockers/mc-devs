/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class ChunkCache
/*		 */	 implements IBlockAccess
/*		 */ {
/*		 */	 private int a;
/*		 */	 private int b;
/*		 */	 private Chunk[][] c;
/*		 */	 private boolean d;
/*		 */	 private World e;
/*		 */ 
/*		 */	 public ChunkCache(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*		 */	 {
/*	20 */		 this.e = paramWorld;
/*		 */ 
/*	22 */		 this.a = (paramInt1 >> 4);
/*	23 */		 this.b = (paramInt3 >> 4);
/*	24 */		 int i = paramInt4 >> 4;
/*	25 */		 int j = paramInt6 >> 4;
/*		 */ 
/*	27 */		 this.c = new Chunk[i - this.a + 1][j - this.b + 1];
/*		 */ 
/*	29 */		 this.d = true;
/*	30 */		 for (int k = this.a; k <= i; k++)
/*	31 */			 for (int m = this.b; m <= j; m++) {
/*	32 */				 Chunk localChunk = paramWorld.getChunkAt(k, m);
/*	33 */				 if (localChunk != null) {
/*	34 */					 this.c[(k - this.a)][(m - this.b)] = localChunk;
/*		 */ 
/*	36 */					 if (!localChunk.c(paramInt2, paramInt5))
/*	37 */						 this.d = false;
/*		 */				 }
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public int getTypeId(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/*	49 */		 if (paramInt2 < 0) return 0;
/*	50 */		 if (paramInt2 >= 256) return 0;
/*		 */ 
/*	52 */		 int i = (paramInt1 >> 4) - this.a;
/*	53 */		 int j = (paramInt3 >> 4) - this.b;
/*		 */ 
/*	55 */		 if ((i < 0) || (i >= this.c.length) || (j < 0) || (j >= this.c[i].length)) {
/*	56 */			 return 0;
/*		 */		 }
/*		 */ 
/*	59 */		 Chunk localChunk = this.c[i][j];
/*	60 */		 if (localChunk == null) return 0;
/*		 */ 
/*	62 */		 return localChunk.getTypeId(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity getTileEntity(int paramInt1, int paramInt2, int paramInt3) {
/*	66 */		 int i = (paramInt1 >> 4) - this.a;
/*	67 */		 int j = (paramInt3 >> 4) - this.b;
/*		 */ 
/*	69 */		 return this.c[i][j].e(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
/*		 */	 }
/*		 */ 
/*		 */	 public int getData(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 128 */		 if (paramInt2 < 0) return 0;
/* 129 */		 if (paramInt2 >= 256) return 0;
/* 130 */		 int i = (paramInt1 >> 4) - this.a;
/* 131 */		 int j = (paramInt3 >> 4) - this.b;
/*		 */ 
/* 133 */		 return this.c[i][j].getData(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
/*		 */	 }
/*		 */ 
/*		 */	 public Material getMaterial(int paramInt1, int paramInt2, int paramInt3) {
/* 137 */		 int i = getTypeId(paramInt1, paramInt2, paramInt3);
/* 138 */		 if (i == 0) return Material.AIR;
/* 139 */		 return Block.byId[i].material;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean s(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 157 */		 Block localBlock = Block.byId[getTypeId(paramInt1, paramInt2, paramInt3)];
/* 158 */		 if (localBlock == null) return false;
/* 159 */		 return (localBlock.material.isSolid()) && (localBlock.c());
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkCache
 * JD-Core Version:		0.6.0
 */