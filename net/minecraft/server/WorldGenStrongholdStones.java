/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ class WorldGenStrongholdStones extends StructurePieceBlockSelector
/*			*/ {
/*			*/	 public void a(Random paramRandom, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
/*			*/	 {
/* 1465 */		 if (paramBoolean) {
/* 1466 */			 this.a = Block.SMOOTH_BRICK.id;
/*			*/ 
/* 1468 */			 float f = paramRandom.nextFloat();
/* 1469 */			 if (f < 0.2F) {
/* 1470 */				 this.b = 2;
/* 1471 */			 } else if (f < 0.5F) {
/* 1472 */				 this.b = 1;
/* 1473 */			 } else if (f < 0.55F) {
/* 1474 */				 this.a = Block.MONSTER_EGGS.id;
/* 1475 */				 this.b = 2;
/*			*/			 } else {
/* 1477 */				 this.b = 0;
/*			*/			 }
/*			*/		 } else {
/* 1480 */			 this.a = 0;
/* 1481 */			 this.b = 0;
/*			*/		 }
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStones
 * JD-Core Version:		0.6.0
 */