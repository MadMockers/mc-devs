/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdLeftTurn extends WorldGenStrongholdPiece
/*		 */ {
/*		 */	 protected final WorldGenStrongholdDoorType a;
/*		 */ 
/*		 */	 public WorldGenStrongholdLeftTurn(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*		 */	 {
/* 758 */		 super(paramInt1);
/*		 */ 
/* 760 */		 this.f = paramInt2;
/* 761 */		 this.a = a(paramRandom);
/* 762 */		 this.e = paramStructureBoundingBox;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 768 */		 if ((this.f == 2) || (this.f == 3))
/* 769 */			 b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */		 else
/* 771 */			 c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldGenStrongholdLeftTurn a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 778 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, 5, paramInt4);
/*		 */ 
/* 780 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 781 */			 return null;
/*		 */		 }
/*		 */ 
/* 784 */		 return new WorldGenStrongholdLeftTurn(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 789 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 790 */			 return false;
/*		 */		 }
/*		 */ 
/* 794 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 4, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 796 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);
/*		 */ 
/* 798 */		 if ((this.f == 2) || (this.f == 3))
/* 799 */			 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, 0, 0, false);
/*		 */		 else {
/* 801 */			 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 3, 0, 0, false);
/*		 */		 }
/*		 */ 
/* 804 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdLeftTurn
 * JD-Core Version:		0.6.0
 */