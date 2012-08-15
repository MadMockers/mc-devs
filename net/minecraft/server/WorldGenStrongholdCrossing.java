/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenStrongholdCrossing extends WorldGenStrongholdPiece
/*			*/ {
/*			*/	 protected final WorldGenStrongholdDoorType a;
/*			*/	 private boolean b;
/*			*/	 private boolean c;
/*			*/	 private boolean d;
/*			*/	 private boolean h;
/*			*/ 
/*			*/	 public WorldGenStrongholdCrossing(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1240 */		 super(paramInt1);
/*			*/ 
/* 1242 */		 this.f = paramInt2;
/* 1243 */		 this.a = a(paramRandom);
/* 1244 */		 this.e = paramStructureBoundingBox;
/*			*/ 
/* 1246 */		 this.b = paramRandom.nextBoolean();
/* 1247 */		 this.c = paramRandom.nextBoolean();
/* 1248 */		 this.d = paramRandom.nextBoolean();
/* 1249 */		 this.h = (paramRandom.nextInt(3) > 0);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
/*			*/	 {
/* 1255 */		 int i = 3;
/* 1256 */		 int j = 5;
/*			*/ 
/* 1258 */		 if ((this.f == 1) || (this.f == 2)) {
/* 1259 */			 i = 8 - i;
/* 1260 */			 j = 8 - j;
/*			*/		 }
/*			*/ 
/* 1263 */		 a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 5, 1);
/* 1264 */		 if (this.b) b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, i, 1);
/* 1265 */		 if (this.c) b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, j, 7);
/* 1266 */		 if (this.d) c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, i, 1);
/* 1267 */		 if (this.h) c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, j, 7);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenStrongholdCrossing a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1273 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -4, -3, 0, 10, 9, 11, paramInt4);
/*			*/ 
/* 1275 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1276 */			 return null;
/*			*/		 }
/*			*/ 
/* 1279 */		 return new WorldGenStrongholdCrossing(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1284 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 1285 */			 return false;
/*			*/		 }
/*			*/ 
/* 1289 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 9, 8, 10, true, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1291 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 4, 3, 0);
/*			*/ 
/* 1294 */		 if (this.b) a(paramWorld, paramStructureBoundingBox, 0, 3, 1, 0, 5, 3, 0, 0, false);
/* 1295 */		 if (this.d) a(paramWorld, paramStructureBoundingBox, 9, 3, 1, 9, 5, 3, 0, 0, false);
/* 1296 */		 if (this.c) a(paramWorld, paramStructureBoundingBox, 0, 5, 7, 0, 7, 9, 0, 0, false);
/* 1297 */		 if (this.h) a(paramWorld, paramStructureBoundingBox, 9, 5, 7, 9, 7, 9, 0, 0, false);
/* 1298 */		 a(paramWorld, paramStructureBoundingBox, 5, 1, 10, 7, 3, 10, 0, 0, false);
/*			*/ 
/* 1301 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 1, 8, 2, 6, false, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1303 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 5, 4, 4, 9, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1304 */		 a(paramWorld, paramStructureBoundingBox, 8, 1, 5, 8, 4, 9, false, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1306 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 7, 3, 4, 9, false, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1309 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 5, 3, 3, 6, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1310 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 4, 3, 3, 4, Block.STEP.id, Block.STEP.id, false);
/* 1311 */		 a(paramWorld, paramStructureBoundingBox, 1, 4, 6, 3, 4, 6, Block.STEP.id, Block.STEP.id, false);
/*			*/ 
/* 1314 */		 a(paramWorld, paramStructureBoundingBox, 5, 1, 7, 7, 1, 8, false, paramRandom, WorldGenStrongholdPieces.b());
/* 1315 */		 a(paramWorld, paramStructureBoundingBox, 5, 1, 9, 7, 1, 9, Block.STEP.id, Block.STEP.id, false);
/* 1316 */		 a(paramWorld, paramStructureBoundingBox, 5, 2, 7, 7, 2, 7, Block.STEP.id, Block.STEP.id, false);
/*			*/ 
/* 1319 */		 a(paramWorld, paramStructureBoundingBox, 4, 5, 7, 4, 5, 9, Block.STEP.id, Block.STEP.id, false);
/* 1320 */		 a(paramWorld, paramStructureBoundingBox, 8, 5, 7, 8, 5, 9, Block.STEP.id, Block.STEP.id, false);
/* 1321 */		 a(paramWorld, paramStructureBoundingBox, 5, 5, 7, 7, 5, 9, Block.DOUBLE_STEP.id, Block.DOUBLE_STEP.id, false);
/* 1322 */		 a(paramWorld, Block.TORCH.id, 0, 6, 5, 6, paramStructureBoundingBox);
/*			*/ 
/* 1324 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdCrossing
 * JD-Core Version:		0.6.0
 */