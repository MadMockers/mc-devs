/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenVillageButcher extends WorldGenVillagePiece
/*			*/ {
/* 1117 */	 private int a = -1;
/*			*/ 
/*			*/	 public WorldGenVillageButcher(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
/* 1120 */		 super(paramWorldGenVillageStartPiece, paramInt1);
/*			*/ 
/* 1122 */		 this.f = paramInt2;
/* 1123 */		 this.e = paramStructureBoundingBox;
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenVillageButcher a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1128 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 9, 7, 11, paramInt4);
/*			*/ 
/* 1130 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1131 */			 return null;
/*			*/		 }
/*			*/ 
/* 1134 */		 return new WorldGenVillageButcher(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1140 */		 if (this.a < 0) {
/* 1141 */			 this.a = b(paramWorld, paramStructureBoundingBox);
/* 1142 */			 if (this.a < 0) {
/* 1143 */				 return true;
/*			*/			 }
/* 1145 */			 this.e.a(0, this.a - this.e.e + 7 - 1, 0);
/*			*/		 }
/*			*/ 
/* 1149 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 7, 4, 4, 0, 0, false);
/* 1150 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 6, 8, 4, 10, 0, 0, false);
/*			*/ 
/* 1153 */		 a(paramWorld, paramStructureBoundingBox, 2, 0, 6, 8, 0, 10, Block.DIRT.id, Block.DIRT.id, false);
/* 1154 */		 a(paramWorld, Block.COBBLESTONE.id, 0, 6, 0, 6, paramStructureBoundingBox);
/*			*/ 
/* 1156 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 6, 2, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
/* 1157 */		 a(paramWorld, paramStructureBoundingBox, 8, 1, 6, 8, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
/* 1158 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 10, 7, 1, 10, Block.FENCE.id, Block.FENCE.id, false);
/*			*/ 
/* 1161 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 7, 0, 4, Block.WOOD.id, Block.WOOD.id, false);
/* 1162 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 0, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1163 */		 a(paramWorld, paramStructureBoundingBox, 8, 0, 0, 8, 3, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1164 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 0, 7, 1, 0, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/* 1165 */		 a(paramWorld, paramStructureBoundingBox, 1, 0, 5, 7, 1, 5, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
/*			*/ 
/* 1168 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 0, 7, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
/* 1169 */		 a(paramWorld, paramStructureBoundingBox, 1, 2, 5, 7, 3, 5, Block.WOOD.id, Block.WOOD.id, false);
/* 1170 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 1, 8, 4, 1, Block.WOOD.id, Block.WOOD.id, false);
/* 1171 */		 a(paramWorld, paramStructureBoundingBox, 0, 4, 4, 8, 4, 4, Block.WOOD.id, Block.WOOD.id, false);
/* 1172 */		 a(paramWorld, paramStructureBoundingBox, 0, 5, 2, 8, 5, 3, Block.WOOD.id, Block.WOOD.id, false);
/* 1173 */		 a(paramWorld, Block.WOOD.id, 0, 0, 4, 2, paramStructureBoundingBox);
/* 1174 */		 a(paramWorld, Block.WOOD.id, 0, 0, 4, 3, paramStructureBoundingBox);
/* 1175 */		 a(paramWorld, Block.WOOD.id, 0, 8, 4, 2, paramStructureBoundingBox);
/* 1176 */		 a(paramWorld, Block.WOOD.id, 0, 8, 4, 3, paramStructureBoundingBox);
/*			*/ 
/* 1178 */		 int i = c(Block.WOOD_STAIRS.id, 3);
/* 1179 */		 int j = c(Block.WOOD_STAIRS.id, 2);
/*			*/		 int m;
/* 1180 */		 for (int k = -1; k <= 2; k++) {
/* 1181 */			 for (m = 0; m <= 8; m++) {
/* 1182 */				 a(paramWorld, Block.WOOD_STAIRS.id, i, m, 4 + k, k, paramStructureBoundingBox);
/* 1183 */				 a(paramWorld, Block.WOOD_STAIRS.id, j, m, 4 + k, 5 - k, paramStructureBoundingBox);
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1188 */		 a(paramWorld, Block.LOG.id, 0, 0, 2, 1, paramStructureBoundingBox);
/* 1189 */		 a(paramWorld, Block.LOG.id, 0, 0, 2, 4, paramStructureBoundingBox);
/* 1190 */		 a(paramWorld, Block.LOG.id, 0, 8, 2, 1, paramStructureBoundingBox);
/* 1191 */		 a(paramWorld, Block.LOG.id, 0, 8, 2, 4, paramStructureBoundingBox);
/* 1192 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
/* 1193 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 3, paramStructureBoundingBox);
/* 1194 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 2, paramStructureBoundingBox);
/* 1195 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 8, 2, 3, paramStructureBoundingBox);
/* 1196 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 2, 2, 5, paramStructureBoundingBox);
/* 1197 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 3, 2, 5, paramStructureBoundingBox);
/* 1198 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 5, 2, 0, paramStructureBoundingBox);
/* 1199 */		 a(paramWorld, Block.THIN_GLASS.id, 0, 6, 2, 5, paramStructureBoundingBox);
/*			*/ 
/* 1202 */		 a(paramWorld, Block.FENCE.id, 0, 2, 1, 3, paramStructureBoundingBox);
/* 1203 */		 a(paramWorld, Block.WOOD_PLATE.id, 0, 2, 2, 3, paramStructureBoundingBox);
/* 1204 */		 a(paramWorld, Block.WOOD.id, 0, 1, 1, 4, paramStructureBoundingBox);
/* 1205 */		 a(paramWorld, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 3), 2, 1, 4, paramStructureBoundingBox);
/* 1206 */		 a(paramWorld, Block.WOOD_STAIRS.id, c(Block.WOOD_STAIRS.id, 1), 1, 1, 3, paramStructureBoundingBox);
/*			*/ 
/* 1209 */		 a(paramWorld, paramStructureBoundingBox, 5, 0, 1, 7, 0, 3, Block.DOUBLE_STEP.id, Block.DOUBLE_STEP.id, false);
/* 1210 */		 a(paramWorld, Block.DOUBLE_STEP.id, 0, 6, 1, 1, paramStructureBoundingBox);
/* 1211 */		 a(paramWorld, Block.DOUBLE_STEP.id, 0, 6, 1, 2, paramStructureBoundingBox);
/*			*/ 
/* 1214 */		 a(paramWorld, 0, 0, 2, 1, 0, paramStructureBoundingBox);
/* 1215 */		 a(paramWorld, 0, 0, 2, 2, 0, paramStructureBoundingBox);
/* 1216 */		 a(paramWorld, Block.TORCH.id, 0, 2, 3, 1, paramStructureBoundingBox);
/* 1217 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 2, 1, 0, c(Block.WOODEN_DOOR.id, 1));
/* 1218 */		 if ((a(paramWorld, 2, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 2, -1, -1, paramStructureBoundingBox) != 0)) {
/* 1219 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 2, 0, -1, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1223 */		 a(paramWorld, 0, 0, 6, 1, 5, paramStructureBoundingBox);
/* 1224 */		 a(paramWorld, 0, 0, 6, 2, 5, paramStructureBoundingBox);
/* 1225 */		 a(paramWorld, Block.TORCH.id, 0, 6, 3, 4, paramStructureBoundingBox);
/* 1226 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 6, 1, 5, c(Block.WOODEN_DOOR.id, 1));
/*			*/ 
/* 1228 */		 for (k = 0; k < 5; k++) {
/* 1229 */			 for (m = 0; m < 9; m++) {
/* 1230 */				 b(paramWorld, m, 7, k, paramStructureBoundingBox);
/* 1231 */				 b(paramWorld, Block.COBBLESTONE.id, 0, m, -1, k, paramStructureBoundingBox);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1235 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 2, 2);
/*			*/ 
/* 1237 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 protected int b(int paramInt)
/*			*/	 {
/* 1242 */		 if (paramInt == 0) {
/* 1243 */			 return 4;
/*			*/		 }
/* 1245 */		 return 0;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenVillageButcher
 * JD-Core Version:		0.6.0
 */