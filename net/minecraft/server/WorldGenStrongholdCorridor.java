/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdCorridor extends WorldGenStrongholdPiece
/*		 */ {
/*		 */	 private final int a;
/*		 */ 
/*		 */	 public WorldGenStrongholdCorridor(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 334 */		 super(paramInt1);
/*		 */ 
/* 336 */		 this.f = paramInt2;
/* 337 */		 this.e = paramStructureBoundingBox;
/* 338 */		 this.a = ((paramInt2 == 2) || (paramInt2 == 0) ? paramStructureBoundingBox.d() : paramStructureBoundingBox.b());
/*		 */	 }
/*		 */ 
/*		 */	 public static StructureBoundingBox a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 345 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, 4, paramInt4);
/*		 */ 
/* 347 */		 StructurePiece localStructurePiece = StructurePiece.a(paramList, localStructureBoundingBox);
/* 348 */		 if (localStructurePiece == null)
/*		 */		 {
/* 350 */			 return null;
/*		 */		 }
/*		 */ 
/* 353 */		 if (localStructurePiece.b().b == localStructureBoundingBox.b)
/*		 */		 {
/* 355 */			 for (int i = 3; i >= 1; i--) {
/* 356 */				 localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, i - 1, paramInt4);
/* 357 */				 if (!localStructurePiece.b().a(localStructureBoundingBox))
/*		 */				 {
/* 361 */					 return StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, i, paramInt4);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 366 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 371 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 372 */			 return false;
/*		 */		 }
/*		 */ 
/* 376 */		 for (int i = 0; i < this.a; i++)
/*		 */		 {
/* 378 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 0, 0, i, paramStructureBoundingBox);
/* 379 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 0, i, paramStructureBoundingBox);
/* 380 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 0, i, paramStructureBoundingBox);
/* 381 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 0, i, paramStructureBoundingBox);
/* 382 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 4, 0, i, paramStructureBoundingBox);
/*		 */ 
/* 384 */			 for (int j = 1; j <= 3; j++) {
/* 385 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 0, j, i, paramStructureBoundingBox);
/* 386 */				 a(paramWorld, 0, 0, 1, j, i, paramStructureBoundingBox);
/* 387 */				 a(paramWorld, 0, 0, 2, j, i, paramStructureBoundingBox);
/* 388 */				 a(paramWorld, 0, 0, 3, j, i, paramStructureBoundingBox);
/* 389 */				 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 4, j, i, paramStructureBoundingBox);
/*		 */			 }
/*		 */ 
/* 392 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 0, 4, i, paramStructureBoundingBox);
/* 393 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 4, i, paramStructureBoundingBox);
/* 394 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 4, i, paramStructureBoundingBox);
/* 395 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 4, i, paramStructureBoundingBox);
/* 396 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 4, 4, i, paramStructureBoundingBox);
/*		 */		 }
/*		 */ 
/* 399 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdCorridor
 * JD-Core Version:		0.6.0
 */