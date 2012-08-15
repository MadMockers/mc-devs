/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenNetherPiece4 extends WorldGenNetherPiece
/*			*/ {
/*			*/	 public WorldGenNetherPiece4(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1289 */		 super(paramInt1);
/*			*/ 
/* 1291 */		 this.f = paramInt2;
/* 1292 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1299 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 1, 0, true);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenNetherPiece4 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1305 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -7, 0, 5, 14, 10, paramInt4);
/*			*/ 
/* 1307 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1308 */			 return null;
/*			*/		 }
/*			*/ 
/* 1311 */		 return new WorldGenNetherPiece4(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1318 */		 int i = c(Block.NETHER_BRICK_STAIRS.id, 2);
/* 1319 */		 for (int j = 0; j <= 9; j++) {
/* 1320 */			 int k = Math.max(1, 7 - j);
/* 1321 */			 int m = Math.min(Math.max(k + 5, 14 - j), 13);
/* 1322 */			 int n = j;
/*			*/ 
/* 1325 */			 a(paramWorld, paramStructureBoundingBox, 0, 0, n, 4, k, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1327 */			 a(paramWorld, paramStructureBoundingBox, 1, k + 1, n, 3, m - 1, n, 0, 0, false);
/* 1328 */			 if (j <= 6) {
/* 1329 */				 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, 1, k + 1, n, paramStructureBoundingBox);
/* 1330 */				 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, 2, k + 1, n, paramStructureBoundingBox);
/* 1331 */				 a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, 3, k + 1, n, paramStructureBoundingBox);
/*			*/			 }
/*			*/ 
/* 1334 */			 a(paramWorld, paramStructureBoundingBox, 0, m, n, 4, m, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*			*/ 
/* 1336 */			 a(paramWorld, paramStructureBoundingBox, 0, k + 1, n, 0, m - 1, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1337 */			 a(paramWorld, paramStructureBoundingBox, 4, k + 1, n, 4, m - 1, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 1338 */			 if ((j & 0x1) == 0) {
/* 1339 */				 a(paramWorld, paramStructureBoundingBox, 0, k + 2, n, 0, k + 3, n, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 1340 */				 a(paramWorld, paramStructureBoundingBox, 4, k + 2, n, 4, k + 3, n, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*			*/			 }
/*			*/ 
/* 1344 */			 for (int i1 = 0; i1 <= 4; i1++) {
/* 1345 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i1, -1, n, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1349 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece4
 * JD-Core Version:		0.6.0
 */