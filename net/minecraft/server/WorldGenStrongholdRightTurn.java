/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenStrongholdRightTurn extends WorldGenStrongholdLeftTurn
/*		 */ {
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*		 */	 {
/* 818 */		 if ((this.f == 2) || (this.f == 3))
/* 819 */			 c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */		 else
/* 821 */			 b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 827 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 828 */			 return false;
/*		 */		 }
/*		 */ 
/* 832 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 4, true, paramRandom, WorldGenStrongholdPieces.b());
/*		 */ 
/* 834 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);
/*		 */ 
/* 836 */		 if ((this.f == 2) || (this.f == 3))
/* 837 */			 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 3, 0, 0, false);
/*		 */		 else {
/* 839 */			 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, 0, 0, false);
/*		 */		 }
/*		 */ 
/* 842 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdRightTurn
 * JD-Core Version:		0.6.0
 */