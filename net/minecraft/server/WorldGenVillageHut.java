/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageHut extends WorldGenVillagePiece
/*			*/ {
/* 1010 */	 private int a = -1;
/*			*/	 private final boolean b;
/*			*/	 private final int c;
/*			*/ 
/*			*/	 public WorldGenVillageHut(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1015 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1017 */		 this.f = paramInt2;
/* 1018 */		 this.e = paramStructureBoundingBox;
/* 1019 */		 this.b = paramRandom.nextBoolean();
/* 1020 */		 this.c = paramRandom.nextInt(3);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageHut a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1025 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 4, 6, 5, paramInt4);
/*			*/ 
/* 1027 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1028 */			 return null;
/*			*/		 }
/*			*/ 
/* 1031 */		 return new WorldGenVillageHut(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1037 */		 if (this.a < 0) {
/* 1038 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 1039 */			 if (this.a < 0) {
/* 1040 */				 return true;
/*			*/			 }
/* 1042 */			 this.e.a(0, this.a - this.e.e + 6 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1046 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 3, 5, 4, 0, 0, false);
/*			*/ 
/* 1049 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 3, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1050 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 2, 0, 3, Block.DIRT.id, Block.DIRT.id, false);
/*			*/ 
/* 1052 */		 if (this.b)
/* 1053 */			 a(paramWorld, paramStructureBoundingBox, 1, 4, 1, 2, 4, 3, Block.LOG.id, Block.LOG.id, false);
/*			*/		 else {
/* 1055 */			 a(paramWorld, paramStructureBoundingBox, 1, 5, 1, 2, 5, 3, Block.LOG.id, Block.LOG.id, false);
/*			*/		 }
/* 1057 */		 a(paramWorld, Block.LOG.id, 0, 1, 4, 0, paramStructureBoundingBox);
/* 1058 */		 a(paramWorld, Block.LOG.id, 0, 2, 4, 0, paramStructureBoundingBox);
/* 1059 */		 a(paramWorld, Block.LOG.id, 0, 1, 4, 4, paramStructureBoundingBox);
/* 1060 */		 a(paramWorld, Block.LOG.id, 0, 2, 4, 4, paramStructureBoundingBox);
/* 1061 */		 a(paramWorld, Block.LOG.id, 0, 0, 4, 1, paramStructureBoundingBox);
/* 1062 */		 a(paramWorld, Block.LOG.id, 0, 0, 4, 2, paramStructureBoundingBox);
/* 1063 */		 a(paramWorld, Block.LOG.id, 0, 0, 4, 3, paramStructureBoundingBox);
/* 1064 */		 a(paramWorld, Block.LOG.id, 0, 3, 4, 1, paramStructureBoundingBox);
/* 1065 */		 a(paramWorld, Block.LOG.id, 0, 3, 4, 2, paramStructureBoundingBox);
/* 1066 */		 a(paramWorld, Block.LOG.id, 0, 3, 4, 3, paramStructureBoundingBox);
/*			*/ 
/* 1069 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 0, 3, 0, Block.LOG.id, Block.LOG.id, false);
/* 1070 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 0, 3, 3, 0, Block.LOG.id, Block.LOG.id, false);
/* 1071 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 4, 0, 3, 4, Block.LOG.id, Block.LOG.id, false);
/* 1072 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 4, 3, 3, 4, Block.LOG.id, Block.LOG.id, false);
/*			*/ 
/* 1075 */		 a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 1076 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 1, 3, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 1077 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
/* 1078 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 4, 2, 3, 4, Block.WOOD.id, Block.WOOD.id, false);
/*			*/ 
/* 1081 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/* 1082 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 3, 2, 2, paramStructureBoundingBox);
/*			*/ 
/* 1085 */		 if (this.c > 0) {
/* 1086 */			 a(paramWorld, Block.FENCE.id, 0, this.c, 1, 3, paramStructureBoundingBox);
/* 1087 */			 a(paramWorld, Block.WOOD_PLATE.id, 0, this.c, 2, 3, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1091 */		 a(paramWorld, 0, 0, 1, 1, 0, paramStructureBoundingBox);
/* 1092 */		 a(paramWorld, 0, 0, 1, 2, 0, paramStructureBoundingBox);
/* 1093 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 1, 1, 0, c(Block.WOODEN_DOOR.id, 1));
/* 1094 */		 if ((a(paramWorld, 1, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 1, -1, -1, paramStructureBoundingBox) != 0)) {
/* 1095 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1098 */		 for (int i = 0; i < 5; i++) {
/* 1099 */			 for (int j = 0; j < 4; j++) {
/* 1100 */				 b(paramWorld, j, 6, i, paramStructureBoundingBox);
/* 1101 */				 b(paramWorld, Block.COBBLESTONE.id, 0, j, -1, i, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1105 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 2, 1);
/*			*/ 
/* 1107 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageHut
 * JD-Core Version:		0.6.0
 */