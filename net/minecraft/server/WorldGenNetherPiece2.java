/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenNetherPiece2 extends WorldGenNetherPiece
/*		 */ {
/*		 */	 private int a;
/*		 */ 
/*		 */	 public WorldGenNetherPiece2(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 361 */		 super(paramInt1);
/*		 */ 
/* 363 */		 this.f = paramInt2;
/* 364 */		 this.e = paramStructureBoundingBox;
/* 365 */		 this.a = paramRandom.nextInt();
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenNetherPiece2 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 371 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -3, 0, 5, 10, 8, paramInt4);
/*		 */ 
/* 373 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 374 */			 return null;
/*		 */		 }
/*		 */ 
/* 377 */		 return new WorldGenNetherPiece2(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 383 */		 Random localRandom = new Random(this.a);
/*		 */		 int j;
/*		 */		 int k;
/* 386 */		 for (int i = 0; i <= 4; i++) {
/* 387 */			 for (j = 3; j <= 4; j++) {
/* 388 */				 k = localRandom.nextInt(8);
/* 389 */				 a(paramWorld, paramStructureBoundingBox, i, j, 0, i, j, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 395 */		 i = localRandom.nextInt(8);
/* 396 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 0, 0, 5, i, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 399 */		 i = localRandom.nextInt(8);
/* 400 */		 a(paramWorld, paramStructureBoundingBox, 4, 5, 0, 4, 5, i, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */ 
/* 404 */		 for (i = 0; i <= 4; i++) {
/* 405 */			 j = localRandom.nextInt(5);
/* 406 */			 a(paramWorld, paramStructureBoundingBox, i, 2, 0, i, 2, j, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */		 }
/* 408 */		 for (i = 0; i <= 4; i++) {
/* 409 */			 for (j = 0; j <= 1; j++) {
/* 410 */				 k = localRandom.nextInt(3);
/* 411 */				 a(paramWorld, paramStructureBoundingBox, i, j, 0, i, j, k, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 415 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece2
 * JD-Core Version:		0.6.0
 */