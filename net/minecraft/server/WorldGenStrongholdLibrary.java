/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ 
/*			*/ public class WorldGenStrongholdLibrary extends WorldGenStrongholdPiece
/*			*/ {
/* 1066 */	 private static final StructurePieceTreasure[] b = { new StructurePieceTreasure(Item.BOOK.id, 0, 1, 3, 20), new StructurePieceTreasure(Item.PAPER.id, 0, 2, 7, 20), new StructurePieceTreasure(Item.MAP.id, 0, 1, 1, 1), new StructurePieceTreasure(Item.COMPASS.id, 0, 1, 1, 1) };
/*			*/	 protected final WorldGenStrongholdDoorType a;
/*			*/	 private final boolean c;
/*			*/ 
/*			*/	 public WorldGenStrongholdLibrary(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
/*			*/	 {
/* 1083 */		 super(paramInt1);
/*			*/ 
/* 1085 */		 this.f = paramInt2;
/* 1086 */		 this.a = a(paramRandom);
/* 1087 */		 this.e = paramStructureBoundingBox;
/* 1088 */		 this.c = (paramStructureBoundingBox.c() > 6);
/*			*/	 }
/*			*/ 
/*			*/	 public static WorldGenStrongholdLibrary a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*			*/	 {
/* 1094 */		 StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -4, -1, 0, 14, 11, 15, paramInt4);
/*			*/ 
/* 1096 */		 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null))
/*			*/		 {
/* 1098 */			 localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -4, -1, 0, 14, 6, 15, paramInt4);
/*			*/ 
/* 1100 */			 if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
/* 1101 */				 return null;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1105 */		 return new WorldGenStrongholdLibrary(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*			*/	 {
/* 1110 */		 if (a(paramWorld, paramStructureBoundingBox)) {
/* 1111 */			 return false;
/*			*/		 }
/*			*/ 
/* 1114 */		 int i = 11;
/* 1115 */		 if (!this.c) {
/* 1116 */			 i = 6;
/*			*/		 }
/*			*/ 
/* 1120 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 13, i - 1, 14, true, paramRandom, WorldGenStrongholdPieces.b());
/*			*/ 
/* 1122 */		 a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 4, 1, 0);
/*			*/ 
/* 1125 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 0.07F, 2, 1, 1, 11, 4, 13, Block.WEB.id, Block.WEB.id, false);
/*			*/ 
/* 1131 */		 for (int j = 1; j <= 13; j++) {
/* 1132 */			 if ((j - 1) % 4 == 0) {
/* 1133 */				 a(paramWorld, paramStructureBoundingBox, 1, 1, j, 1, 4, j, Block.WOOD.id, Block.WOOD.id, false);
/* 1134 */				 a(paramWorld, paramStructureBoundingBox, 12, 1, j, 12, 4, j, Block.WOOD.id, Block.WOOD.id, false);
/*			*/ 
/* 1136 */				 a(paramWorld, Block.TORCH.id, 0, 2, 3, j, paramStructureBoundingBox);
/* 1137 */				 a(paramWorld, Block.TORCH.id, 0, 11, 3, j, paramStructureBoundingBox);
/*			*/ 
/* 1139 */				 if (this.c) {
/* 1140 */					 a(paramWorld, paramStructureBoundingBox, 1, 6, j, 1, 9, j, Block.WOOD.id, Block.WOOD.id, false);
/* 1141 */					 a(paramWorld, paramStructureBoundingBox, 12, 6, j, 12, 9, j, Block.WOOD.id, Block.WOOD.id, false);
/*			*/				 }
/*			*/			 }
/*			*/			 else {
/* 1145 */				 a(paramWorld, paramStructureBoundingBox, 1, 1, j, 1, 4, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/* 1146 */				 a(paramWorld, paramStructureBoundingBox, 12, 1, j, 12, 4, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/*			*/ 
/* 1148 */				 if (this.c) {
/* 1149 */					 a(paramWorld, paramStructureBoundingBox, 1, 6, j, 1, 9, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/* 1150 */					 a(paramWorld, paramStructureBoundingBox, 12, 6, j, 12, 9, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1156 */		 for (j = 3; j < 12; j += 2) {
/* 1157 */			 a(paramWorld, paramStructureBoundingBox, 3, 1, j, 4, 3, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/* 1158 */			 a(paramWorld, paramStructureBoundingBox, 6, 1, j, 7, 3, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/* 1159 */			 a(paramWorld, paramStructureBoundingBox, 9, 1, j, 10, 3, j, Block.BOOKSHELF.id, Block.BOOKSHELF.id, false);
/*			*/		 }
/*			*/ 
/* 1162 */		 if (this.c)
/*			*/		 {
/* 1164 */			 a(paramWorld, paramStructureBoundingBox, 1, 5, 1, 3, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
/* 1165 */			 a(paramWorld, paramStructureBoundingBox, 10, 5, 1, 12, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
/* 1166 */			 a(paramWorld, paramStructureBoundingBox, 4, 5, 1, 9, 5, 2, Block.WOOD.id, Block.WOOD.id, false);
/* 1167 */			 a(paramWorld, paramStructureBoundingBox, 4, 5, 12, 9, 5, 13, Block.WOOD.id, Block.WOOD.id, false);
/*			*/ 
/* 1169 */			 a(paramWorld, Block.WOOD.id, 0, 9, 5, 11, paramStructureBoundingBox);
/* 1170 */			 a(paramWorld, Block.WOOD.id, 0, 8, 5, 11, paramStructureBoundingBox);
/* 1171 */			 a(paramWorld, Block.WOOD.id, 0, 9, 5, 10, paramStructureBoundingBox);
/*			*/ 
/* 1174 */			 a(paramWorld, paramStructureBoundingBox, 3, 6, 2, 3, 6, 12, Block.FENCE.id, Block.FENCE.id, false);
/* 1175 */			 a(paramWorld, paramStructureBoundingBox, 10, 6, 2, 10, 6, 10, Block.FENCE.id, Block.FENCE.id, false);
/* 1176 */			 a(paramWorld, paramStructureBoundingBox, 4, 6, 2, 9, 6, 2, Block.FENCE.id, Block.FENCE.id, false);
/* 1177 */			 a(paramWorld, paramStructureBoundingBox, 4, 6, 12, 8, 6, 12, Block.FENCE.id, Block.FENCE.id, false);
/* 1178 */			 a(paramWorld, Block.FENCE.id, 0, 9, 6, 11, paramStructureBoundingBox);
/* 1179 */			 a(paramWorld, Block.FENCE.id, 0, 8, 6, 11, paramStructureBoundingBox);
/* 1180 */			 a(paramWorld, Block.FENCE.id, 0, 9, 6, 10, paramStructureBoundingBox);
/*			*/ 
/* 1183 */			 j = c(Block.LADDER.id, 3);
/* 1184 */			 a(paramWorld, Block.LADDER.id, j, 10, 1, 13, paramStructureBoundingBox);
/* 1185 */			 a(paramWorld, Block.LADDER.id, j, 10, 2, 13, paramStructureBoundingBox);
/* 1186 */			 a(paramWorld, Block.LADDER.id, j, 10, 3, 13, paramStructureBoundingBox);
/* 1187 */			 a(paramWorld, Block.LADDER.id, j, 10, 4, 13, paramStructureBoundingBox);
/* 1188 */			 a(paramWorld, Block.LADDER.id, j, 10, 5, 13, paramStructureBoundingBox);
/* 1189 */			 a(paramWorld, Block.LADDER.id, j, 10, 6, 13, paramStructureBoundingBox);
/* 1190 */			 a(paramWorld, Block.LADDER.id, j, 10, 7, 13, paramStructureBoundingBox);
/*			*/ 
/* 1193 */			 int k = 7;
/* 1194 */			 int m = 7;
/* 1195 */			 a(paramWorld, Block.FENCE.id, 0, k - 1, 9, m, paramStructureBoundingBox);
/* 1196 */			 a(paramWorld, Block.FENCE.id, 0, k, 9, m, paramStructureBoundingBox);
/* 1197 */			 a(paramWorld, Block.FENCE.id, 0, k - 1, 8, m, paramStructureBoundingBox);
/* 1198 */			 a(paramWorld, Block.FENCE.id, 0, k, 8, m, paramStructureBoundingBox);
/* 1199 */			 a(paramWorld, Block.FENCE.id, 0, k - 1, 7, m, paramStructureBoundingBox);
/* 1200 */			 a(paramWorld, Block.FENCE.id, 0, k, 7, m, paramStructureBoundingBox);
/*			*/ 
/* 1202 */			 a(paramWorld, Block.FENCE.id, 0, k - 2, 7, m, paramStructureBoundingBox);
/* 1203 */			 a(paramWorld, Block.FENCE.id, 0, k + 1, 7, m, paramStructureBoundingBox);
/* 1204 */			 a(paramWorld, Block.FENCE.id, 0, k - 1, 7, m - 1, paramStructureBoundingBox);
/* 1205 */			 a(paramWorld, Block.FENCE.id, 0, k - 1, 7, m + 1, paramStructureBoundingBox);
/* 1206 */			 a(paramWorld, Block.FENCE.id, 0, k, 7, m - 1, paramStructureBoundingBox);
/* 1207 */			 a(paramWorld, Block.FENCE.id, 0, k, 7, m + 1, paramStructureBoundingBox);
/*			*/ 
/* 1209 */			 a(paramWorld, Block.TORCH.id, 0, k - 2, 8, m, paramStructureBoundingBox);
/* 1210 */			 a(paramWorld, Block.TORCH.id, 0, k + 1, 8, m, paramStructureBoundingBox);
/* 1211 */			 a(paramWorld, Block.TORCH.id, 0, k - 1, 8, m - 1, paramStructureBoundingBox);
/* 1212 */			 a(paramWorld, Block.TORCH.id, 0, k - 1, 8, m + 1, paramStructureBoundingBox);
/* 1213 */			 a(paramWorld, Block.TORCH.id, 0, k, 8, m - 1, paramStructureBoundingBox);
/* 1214 */			 a(paramWorld, Block.TORCH.id, 0, k, 8, m + 1, paramStructureBoundingBox);
/*			*/		 }
/*			*/ 
/* 1218 */		 a(paramWorld, paramStructureBoundingBox, paramRandom, 3, 3, 5, b, 1 + paramRandom.nextInt(4));
/* 1219 */		 if (this.c) {
/* 1220 */			 a(paramWorld, 0, 0, 12, 9, 1, paramStructureBoundingBox);
/* 1221 */			 a(paramWorld, paramStructureBoundingBox, paramRandom, 12, 8, 1, b, 1 + paramRandom.nextInt(4));
/*			*/		 }
/*			*/ 
/* 1224 */		 return true;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdLibrary
 * JD-Core Version:		0.6.0
 */