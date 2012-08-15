/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece3 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 public WorldGenNetherPiece3(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 288 */		 super(paramInt1);
/*		 */ 
/* 290 */		 this.f = paramInt2;
/* 291 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 298 */		 a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 1, 3, false);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece3 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 304 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -3, 0, 5, 10, 19, paramInt4);
/*		 */ 
/* 306 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 307 */			 return null;
/*		 */		 }
/*		 */ 
/* 310 */		 return new WorldGenNetherPiece3(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 317 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 0, 4, 4, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 319 */		 a(paramWorld, paramStructureBoundingBox, 1, 5, 0, 3, 7, 18, 0, 0, false);
/*		 */ 
/* 322 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 0, 5, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 323 */		 a(paramWorld, paramStructureBoundingBox, 4, 5, 0, 4, 5, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 326 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 4, 2, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 327 */		 a(paramWorld, paramStructureBoundingBox, 0, 2, 13, 4, 2, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 328 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 1, 3, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/* 329 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 15, 4, 1, 18, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 331 */		 for (int i = 0; i <= 4; i++) {
/* 332 */			 for (int j = 0; j <= 2; j++) {
/* 333 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
/* 334 */				 b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, 18 - j, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 338 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 339 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 4, 0, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 340 */		 a(paramWorld, paramStructureBoundingBox, 0, 3, 14, 0, 4, 14, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 341 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 17, 0, 4, 17, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 342 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 343 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 4, 4, 4, 4, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 344 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 14, 4, 4, 14, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/* 345 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 17, 4, 4, 17, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
/*		 */ 
/* 347 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece3
 * JD-Core Version:		0.6.0
 */