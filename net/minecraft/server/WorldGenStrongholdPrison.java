/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenStrongholdPrison extends WorldGenStrongholdPiece
/*			*/ {
/*			*/	 protected final WorldGenStrongholdDoorType a;
/*			*/ 
/*			*/	 public WorldGenStrongholdPrison(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1002 */		 super(paramInt1);
/*			*/ 
/* 1004 */		 this.f = paramInt2;
/* 1005 */		 this.a = a(paramRandom);
/* 1006 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1012 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenStrongholdPrison a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1018 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 9, 5, 11, paramInt4);
/*			*/ 
/* 1020 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1021 */			 return null;
/*			*/		 }
/*			*/ 
/* 1024 */		 return new WorldGenStrongholdPrison(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1029 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 1030 */			 return false;
/*			*/		 }
/*			*/ 
/* 1034 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 8, 4, 10, true, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1036 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);
/*			*/ 
/* 1038 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 10, 3, 3, 10, 0, 0, false);
/*			*/ 
/* 1041 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 1, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1042 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 3, 4, 3, 3, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1043 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 7, 4, 3, 7, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1044 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 9, 4, 3, 9, false, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1047 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 4, 4, 3, 6, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
/* 1048 */		 a(paramWorld, paramStructureBoundingBox, 5, 1, 5, 7, 3, 5, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
/*			*/ 
/* 1051 */		 a(paramWorld, Block.IRON_FENCE.id, 0, 4, 3, 2, paramStructureBoundingBox);
/* 1052 */		 a(paramWorld, Block.IRON_FENCE.id, 0, 4, 3, 8, paramStructureBoundingBox);
/* 1053 */		 a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 2, paramStructureBoundingBox);
/* 1054 */		 a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 2, paramStructureBoundingBox);
/* 1055 */		 a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 8, paramStructureBoundingBox);
/* 1056 */		 a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 8, paramStructureBoundingBox);
/*			*/ 
/* 1058 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdPrison
 * JD-Core Version:		0.6.0
 */