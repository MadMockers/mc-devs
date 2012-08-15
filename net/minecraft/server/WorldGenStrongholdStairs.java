/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdStairs extends WorldGenStrongholdPiece
/*		 */ {
/*		 */	 private final WorldGenStrongholdDoorType a;
/*		 */	 private final boolean b;
/*		 */	 private final boolean c;
/*		 */ 
/*		 */	 public WorldGenStrongholdStairs(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 531 */		 super(paramInt1);
/*		 */ 
/* 533 */		 this.f = paramInt2;
/* 534 */		 this.a = a(paramRandom);
/* 535 */		 this.e = paramStructureBoundingBox;
/*		 */ 
/* 537 */		 this.b = (paramRandom.nextInt(2) == 0);
/* 538 */		 this.c = (paramRandom.nextInt(2) == 0);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 544 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/* 545 */		 if (this.b) b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 2);
/* 546 */		 if (this.c) c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 2);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenStrongholdStairs a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 552 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, 7, paramInt4);
/*		 */ 
/* 554 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 555 */			 return null;
/*		 */		 }
/*		 */ 
/* 558 */		 return new WorldGenStrongholdStairs(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 563 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 564 */			 return false;
/*		 */		 }
/*		 */ 
/* 568 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 6, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 570 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);
/*		 */ 
/* 572 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.a, 1, 1, 6);
/*		 */ 
/* 574 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 1, 2, 1, Block.TORCH.id, 0);
/* 575 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 3, 2, 1, Block.TORCH.id, 0);
/* 576 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 1, 2, 5, Block.TORCH.id, 0);
/* 577 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 3, 2, 5, Block.TORCH.id, 0);
/*		 */ 
/* 579 */		 if (this.b) {
/* 580 */			 a(paramWorld, paramStructureBoundingBox, 0, 1, 2, 0, 3, 4, 0, 0, false);
/*		 */		 }
/* 582 */		 if (this.c) {
/* 583 */			 a(paramWorld, paramStructureBoundingBox, 4, 1, 2, 4, 3, 4, 0, 0, false);
/*		 */		 }
/*		 */ 
/* 586 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStairs
 * JD-Core Version:		0.6.0
 */