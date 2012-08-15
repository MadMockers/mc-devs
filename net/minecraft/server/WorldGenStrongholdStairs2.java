/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdStairs2 extends WorldGenStrongholdPiece
/*		 */ {
/*		 */	 private final boolean a;
/*		 */	 private final WorldGenStrongholdDoorType b;
/*		 */ 
/*		 */	 public WorldGenStrongholdStairs2(int paramInt1, Random paramRandom, int paramInt2, int paramInt3)
/*		 */	 {
/* 413 */		 super(paramInt1);
/*		 */ 
/* 415 */		 this.a = true;
/* 416 */		 this.f = paramRandom.nextInt(4);
/* 417 */		 this.b = WorldGenStrongholdDoorType.a;
/*		 */ 
/* 419 */		 switch (this.f) {
/*		 */		 case 0:
/*		 */		 case 2:
/* 422 */			 this.e = new StructureBoundingBox(paramInt2, 64, paramInt3, paramInt2 + 5 - 1, 74, paramInt3 + 5 - 1);
/* 423 */			 break;
/*		 */		 default:
/* 425 */			 this.e = new StructureBoundingBox(paramInt2, 64, paramInt3, paramInt2 + 5 - 1, 74, paramInt3 + 5 - 1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public WorldGenStrongholdStairs2(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 431 */		 super(paramInt1);
/*		 */ 
/* 433 */		 this.a = false;
/* 434 */		 this.f = paramInt2;
/* 435 */		 this.b = a(paramRandom);
/* 436 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 442 */		 if (this.a)
/*		 */		 {
/* 444 */			 WorldGenStrongholdPieces.a(WorldGenStrongholdCrossing.class);
/*		 */		 }
/* 446 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenStrongholdStairs2 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 451 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -7, 0, 5, 11, 5, paramInt4);
/*		 */ 
/* 453 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 454 */			 return null;
/*		 */		 }
/*		 */ 
/* 457 */		 return new WorldGenStrongholdStairs2(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 462 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 463 */			 return false;
/*		 */		 }
/*		 */ 
/* 467 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 10, 4, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 469 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.b, 1, 7, 0);
/*		 */ 
/* 471 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.a, 1, 1, 4);
/*		 */ 
/* 474 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 6, 1, paramStructureBoundingBox);
/* 475 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 5, 1, paramStructureBoundingBox);
/* 476 */		 a(paramWorld, Block.STEP.id, 0, 1, 6, 1, paramStructureBoundingBox);
/* 477 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 5, 2, paramStructureBoundingBox);
/* 478 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 4, 3, paramStructureBoundingBox);
/* 479 */		 a(paramWorld, Block.STEP.id, 0, 1, 5, 3, paramStructureBoundingBox);
/* 480 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 4, 3, paramStructureBoundingBox);
/* 481 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 3, 3, paramStructureBoundingBox);
/* 482 */		 a(paramWorld, Block.STEP.id, 0, 3, 4, 3, paramStructureBoundingBox);
/* 483 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 3, 2, paramStructureBoundingBox);
/* 484 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 2, 1, paramStructureBoundingBox);
/* 485 */		 a(paramWorld, Block.STEP.id, 0, 3, 3, 1, paramStructureBoundingBox);
/* 486 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 2, 1, paramStructureBoundingBox);
/* 487 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 1, 1, paramStructureBoundingBox);
/* 488 */		 a(paramWorld, Block.STEP.id, 0, 1, 2, 1, paramStructureBoundingBox);
/* 489 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 1, 2, paramStructureBoundingBox);
/* 490 */		 a(paramWorld, Block.STEP.id, 0, 1, 1, 3, paramStructureBoundingBox);
/*		 */ 
/* 492 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStairs2
 * JD-Core Version:		0.6.0
 */