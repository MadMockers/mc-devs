/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ abstract class WorldGenStrongholdPiece extends StructurePiece
/*		 */ {
/*		 */	 protected WorldGenStrongholdPiece(int paramInt)
/*		 */	 {
/* 207 */		 super(paramInt);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, WorldGenStrongholdDoorType paramWorldGenStrongholdDoorType, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 219 */		 switch (WorldGenStrongholdPieceWeight3.a[paramWorldGenStrongholdDoorType.ordinal()]) {
/*		 */		 case 1:
/*		 */		 default:
/* 222 */			 a(paramWorld, paramStructureBoundingBox, paramInt1, paramInt2, paramInt3, paramInt1 + 3 - 1, paramInt2 + 3 - 1, paramInt3, 0, 0, false);
/* 223 */			 break;
/*		 */		 case 2:
/* 225 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1, paramInt2, paramInt3, paramStructureBoundingBox);
/* 226 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 227 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 228 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 1, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 229 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 2, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 230 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 2, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 231 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 2, paramInt2, paramInt3, paramStructureBoundingBox);
/* 232 */			 a(paramWorld, Block.WOODEN_DOOR.id, 0, paramInt1 + 1, paramInt2, paramInt3, paramStructureBoundingBox);
/* 233 */			 a(paramWorld, Block.WOODEN_DOOR.id, 8, paramInt1 + 1, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 234 */			 break;
/*		 */		 case 3:
/* 236 */			 a(paramWorld, 0, 0, paramInt1 + 1, paramInt2, paramInt3, paramStructureBoundingBox);
/* 237 */			 a(paramWorld, 0, 0, paramInt1 + 1, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 238 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1, paramInt2, paramInt3, paramStructureBoundingBox);
/* 239 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 240 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 241 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1 + 1, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 242 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1 + 2, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 243 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1 + 2, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 244 */			 a(paramWorld, Block.IRON_FENCE.id, 0, paramInt1 + 2, paramInt2, paramInt3, paramStructureBoundingBox);
/* 245 */			 break;
/*		 */		 case 4:
/* 247 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1, paramInt2, paramInt3, paramStructureBoundingBox);
/* 248 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 249 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 250 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 1, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 251 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 2, paramInt2 + 2, paramInt3, paramStructureBoundingBox);
/* 252 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 2, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 253 */			 a(paramWorld, Block.SMOOTH_BRICK.id, 0, paramInt1 + 2, paramInt2, paramInt3, paramStructureBoundingBox);
/* 254 */			 a(paramWorld, Block.IRON_DOOR_BLOCK.id, 0, paramInt1 + 1, paramInt2, paramInt3, paramStructureBoundingBox);
/* 255 */			 a(paramWorld, Block.IRON_DOOR_BLOCK.id, 8, paramInt1 + 1, paramInt2 + 1, paramInt3, paramStructureBoundingBox);
/* 256 */			 a(paramWorld, Block.STONE_BUTTON.id, c(Block.STONE_BUTTON.id, 4), paramInt1 + 2, paramInt2 + 1, paramInt3 + 1, paramStructureBoundingBox);
/* 257 */			 a(paramWorld, Block.STONE_BUTTON.id, c(Block.STONE_BUTTON.id, 3), paramInt1 + 2, paramInt2 + 1, paramInt3 - 1, paramStructureBoundingBox);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected WorldGenStrongholdDoorType a(Random paramRandom)
/*		 */	 {
/* 263 */		 int i = paramRandom.nextInt(5);
/* 264 */		 switch (i) {
/*		 */		 case 0:
/*		 */		 case 1:
/*		 */		 default:
/* 268 */			 return WorldGenStrongholdDoorType.a;
/*		 */		 case 2:
/* 270 */			 return WorldGenStrongholdDoorType.b;
/*		 */		 case 3:
/* 272 */			 return WorldGenStrongholdDoorType.c;
/*		 */		 case 4:
/* 274 */		 }return WorldGenStrongholdDoorType.d;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece a(WorldGenStrongholdStart paramWorldGenStrongholdStart, List paramList, Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/* 279 */		 switch (this.f) {
/*		 */		 case 2:
/* 281 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a + paramInt1, this.e.b + paramInt2, this.e.c - 1, this.f, c());
/*		 */		 case 0:
/* 283 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a + paramInt1, this.e.b + paramInt2, this.e.f + 1, this.f, c());
/*		 */		 case 1:
/* 285 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt2, this.e.c + paramInt1, this.f, c());
/*		 */		 case 3:
/* 287 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt2, this.e.c + paramInt1, this.f, c());
/*		 */		 }
/* 289 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece b(WorldGenStrongholdStart paramWorldGenStrongholdStart, List paramList, Random paramRandom, int paramInt1, int paramInt2) {
/* 293 */		 switch (this.f) {
/*		 */		 case 2:
/* 295 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c());
/*		 */		 case 0:
/* 297 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c());
/*		 */		 case 1:
/* 299 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c());
/*		 */		 case 3:
/* 301 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c());
/*		 */		 }
/* 303 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected StructurePiece c(WorldGenStrongholdStart paramWorldGenStrongholdStart, List paramList, Random paramRandom, int paramInt1, int paramInt2) {
/* 307 */		 switch (this.f) {
/*		 */		 case 2:
/* 309 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c());
/*		 */		 case 0:
/* 311 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c());
/*		 */		 case 1:
/* 313 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c());
/*		 */		 case 3:
/* 315 */			 return WorldGenStrongholdPieces.a(paramWorldGenStrongholdStart, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c());
/*		 */		 }
/* 317 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 protected static boolean a(StructureBoundingBox paramStructureBoundingBox) {
/* 321 */		 return (paramStructureBoundingBox != null) && (paramStructureBoundingBox.b > 10);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdPiece
 * JD-Core Version:		0.6.0
 */