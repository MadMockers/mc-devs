/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenPyramidPiece extends WorldGenScatteredPiece
/*		 */ {
/*	72 */	 private boolean[] h = new boolean[4];
/*		 */ 
/*	75 */	 private static final StructurePieceTreasure[] i = { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 2, 7, 15), new StructurePieceTreasure(Item.EMERALD.id, 0, 1, 3, 2), new StructurePieceTreasure(Item.BONE.id, 0, 4, 6, 20), new StructurePieceTreasure(Item.ROTTEN_FLESH.id, 0, 3, 7, 16) };
/*		 */ 
/*		 */	 public WorldGenPyramidPiece(Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/*	86 */		 super(paramRandom, paramInt1, 64, paramInt2, 21, 15, 21);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/*	93 */		 a(paramWorld, paramStructureBoundingBox, 0, -4, 0, this.a - 1, 0, this.c - 1, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/*	94 */		 for (int j = 1; j <= 9; j++) {
/*	95 */			 a(paramWorld, paramStructureBoundingBox, j, j, j, this.a - 1 - j, j, this.c - 1 - j, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/*	96 */			 a(paramWorld, paramStructureBoundingBox, j + 1, j, j + 1, this.a - 2 - j, j, this.c - 2 - j, 0, 0, false);
/*		 */		 }
/*	98 */		 for (j = 0; j < this.a; j++) {
/*	99 */			 for (k = 0; k < this.c; k++) {
/* 100 */				 b(paramWorld, Block.SANDSTONE.id, 0, j, -5, k, paramStructureBoundingBox);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 104 */		 j = c(Block.SANDSTONE_STAIRS.id, 3);
/* 105 */		 int k = c(Block.SANDSTONE_STAIRS.id, 2);
/* 106 */		 int m = c(Block.SANDSTONE_STAIRS.id, 0);
/* 107 */		 int n = c(Block.SANDSTONE_STAIRS.id, 1);
/* 108 */		 int i1 = 1;
/* 109 */		 int i2 = 11;
/*		 */ 
/* 112 */		 a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 9, 4, Block.SANDSTONE.id, 0, false);
/* 113 */		 a(paramWorld, paramStructureBoundingBox, 1, 10, 1, 3, 10, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 114 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, j, 2, 10, 0, paramStructureBoundingBox);
/* 115 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, k, 2, 10, 4, paramStructureBoundingBox);
/* 116 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, m, 0, 10, 2, paramStructureBoundingBox);
/* 117 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, n, 4, 10, 2, paramStructureBoundingBox);
/* 118 */		 a(paramWorld, paramStructureBoundingBox, this.a - 5, 0, 0, this.a - 1, 9, 4, Block.SANDSTONE.id, 0, false);
/* 119 */		 a(paramWorld, paramStructureBoundingBox, this.a - 4, 10, 1, this.a - 2, 10, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 120 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 10, 0, paramStructureBoundingBox);
/* 121 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, k, this.a - 3, 10, 4, paramStructureBoundingBox);
/* 122 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, m, this.a - 5, 10, 2, paramStructureBoundingBox);
/* 123 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, n, this.a - 1, 10, 2, paramStructureBoundingBox);
/*		 */ 
/* 126 */		 a(paramWorld, paramStructureBoundingBox, 8, 0, 0, 12, 4, 4, Block.SANDSTONE.id, 0, false);
/* 127 */		 a(paramWorld, paramStructureBoundingBox, 9, 1, 0, 11, 3, 4, 0, 0, false);
/* 128 */		 a(paramWorld, Block.SANDSTONE.id, 2, 9, 1, 1, paramStructureBoundingBox);
/* 129 */		 a(paramWorld, Block.SANDSTONE.id, 2, 9, 2, 1, paramStructureBoundingBox);
/* 130 */		 a(paramWorld, Block.SANDSTONE.id, 2, 9, 3, 1, paramStructureBoundingBox);
/* 131 */		 a(paramWorld, Block.SANDSTONE.id, 2, 10, 3, 1, paramStructureBoundingBox);
/* 132 */		 a(paramWorld, Block.SANDSTONE.id, 2, 11, 3, 1, paramStructureBoundingBox);
/* 133 */		 a(paramWorld, Block.SANDSTONE.id, 2, 11, 2, 1, paramStructureBoundingBox);
/* 134 */		 a(paramWorld, Block.SANDSTONE.id, 2, 11, 1, 1, paramStructureBoundingBox);
/*		 */ 
/* 137 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 8, 3, 3, Block.SANDSTONE.id, 0, false);
/* 138 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 2, 8, 2, 2, 0, 0, false);
/* 139 */		 a(paramWorld, paramStructureBoundingBox, 12, 1, 1, 16, 3, 3, Block.SANDSTONE.id, 0, false);
/* 140 */		 a(paramWorld, paramStructureBoundingBox, 12, 1, 2, 16, 2, 2, 0, 0, false);
/*		 */ 
/* 143 */		 a(paramWorld, paramStructureBoundingBox, 5, 4, 5, this.a - 6, 4, this.c - 6, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 144 */		 a(paramWorld, paramStructureBoundingBox, 9, 4, 9, 11, 4, 11, 0, 0, false);
/* 145 */		 a(paramWorld, paramStructureBoundingBox, 8, 1, 8, 8, 3, 8, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 146 */		 a(paramWorld, paramStructureBoundingBox, 12, 1, 8, 12, 3, 8, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 147 */		 a(paramWorld, paramStructureBoundingBox, 8, 1, 12, 8, 3, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 148 */		 a(paramWorld, paramStructureBoundingBox, 12, 1, 12, 12, 3, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/*		 */ 
/* 151 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 5, 4, 4, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 152 */		 a(paramWorld, paramStructureBoundingBox, this.a - 5, 1, 5, this.a - 2, 4, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 153 */		 a(paramWorld, paramStructureBoundingBox, 6, 7, 9, 6, 7, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 154 */		 a(paramWorld, paramStructureBoundingBox, this.a - 7, 7, 9, this.a - 7, 7, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 155 */		 a(paramWorld, paramStructureBoundingBox, 5, 5, 9, 5, 7, 11, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 156 */		 a(paramWorld, paramStructureBoundingBox, this.a - 6, 5, 9, this.a - 6, 7, 11, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 157 */		 a(paramWorld, 0, 0, 5, 5, 10, paramStructureBoundingBox);
/* 158 */		 a(paramWorld, 0, 0, 5, 6, 10, paramStructureBoundingBox);
/* 159 */		 a(paramWorld, 0, 0, 6, 6, 10, paramStructureBoundingBox);
/* 160 */		 a(paramWorld, 0, 0, this.a - 6, 5, 10, paramStructureBoundingBox);
/* 161 */		 a(paramWorld, 0, 0, this.a - 6, 6, 10, paramStructureBoundingBox);
/* 162 */		 a(paramWorld, 0, 0, this.a - 7, 6, 10, paramStructureBoundingBox);
/*		 */ 
/* 165 */		 a(paramWorld, paramStructureBoundingBox, 2, 4, 4, 2, 6, 4, 0, 0, false);
/* 166 */		 a(paramWorld, paramStructureBoundingBox, this.a - 3, 4, 4, this.a - 3, 6, 4, 0, 0, false);
/* 167 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, j, 2, 4, 5, paramStructureBoundingBox);
/* 168 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, j, 2, 3, 4, paramStructureBoundingBox);
/* 169 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 4, 5, paramStructureBoundingBox);
/* 170 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 3, 4, paramStructureBoundingBox);
/* 171 */		 a(paramWorld, paramStructureBoundingBox, 1, 1, 3, 2, 2, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 172 */		 a(paramWorld, paramStructureBoundingBox, this.a - 3, 1, 3, this.a - 2, 2, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 173 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, 0, 1, 1, 2, paramStructureBoundingBox);
/* 174 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, 0, this.a - 2, 1, 2, paramStructureBoundingBox);
/* 175 */		 a(paramWorld, Block.STEP.id, 1, 1, 2, 2, paramStructureBoundingBox);
/* 176 */		 a(paramWorld, Block.STEP.id, 1, this.a - 2, 2, 2, paramStructureBoundingBox);
/* 177 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, n, 2, 1, 2, paramStructureBoundingBox);
/* 178 */		 a(paramWorld, Block.SANDSTONE_STAIRS.id, m, this.a - 3, 1, 2, paramStructureBoundingBox);
/*		 */ 
/* 181 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 5, 4, 3, 18, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 182 */		 a(paramWorld, paramStructureBoundingBox, this.a - 5, 3, 5, this.a - 5, 3, 17, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 183 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 5, 4, 2, 16, 0, 0, false);
/* 184 */		 a(paramWorld, paramStructureBoundingBox, this.a - 6, 1, 5, this.a - 5, 2, 16, 0, 0, false);
/* 185 */		 for (int i3 = 5; i3 <= 17; i3 += 2) {
/* 186 */			 a(paramWorld, Block.SANDSTONE.id, 2, 4, 1, i3, paramStructureBoundingBox);
/* 187 */			 a(paramWorld, Block.SANDSTONE.id, 1, 4, 2, i3, paramStructureBoundingBox);
/* 188 */			 a(paramWorld, Block.SANDSTONE.id, 2, this.a - 5, 1, i3, paramStructureBoundingBox);
/* 189 */			 a(paramWorld, Block.SANDSTONE.id, 1, this.a - 5, 2, i3, paramStructureBoundingBox);
/*		 */		 }
/* 191 */		 a(paramWorld, Block.WOOL.id, i1, 10, 0, 7, paramStructureBoundingBox);
/* 192 */		 a(paramWorld, Block.WOOL.id, i1, 10, 0, 8, paramStructureBoundingBox);
/* 193 */		 a(paramWorld, Block.WOOL.id, i1, 9, 0, 9, paramStructureBoundingBox);
/* 194 */		 a(paramWorld, Block.WOOL.id, i1, 11, 0, 9, paramStructureBoundingBox);
/* 195 */		 a(paramWorld, Block.WOOL.id, i1, 8, 0, 10, paramStructureBoundingBox);
/* 196 */		 a(paramWorld, Block.WOOL.id, i1, 12, 0, 10, paramStructureBoundingBox);
/* 197 */		 a(paramWorld, Block.WOOL.id, i1, 7, 0, 10, paramStructureBoundingBox);
/* 198 */		 a(paramWorld, Block.WOOL.id, i1, 13, 0, 10, paramStructureBoundingBox);
/* 199 */		 a(paramWorld, Block.WOOL.id, i1, 9, 0, 11, paramStructureBoundingBox);
/* 200 */		 a(paramWorld, Block.WOOL.id, i1, 11, 0, 11, paramStructureBoundingBox);
/* 201 */		 a(paramWorld, Block.WOOL.id, i1, 10, 0, 12, paramStructureBoundingBox);
/* 202 */		 a(paramWorld, Block.WOOL.id, i1, 10, 0, 13, paramStructureBoundingBox);
/* 203 */		 a(paramWorld, Block.WOOL.id, i2, 10, 0, 10, paramStructureBoundingBox);
/*		 */ 
/* 206 */		 for (i3 = 0; i3 <= this.a - 1; i3 += this.a - 1) {
/* 207 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 2, 1, paramStructureBoundingBox);
/* 208 */			 a(paramWorld, Block.WOOL.id, i1, i3, 2, 2, paramStructureBoundingBox);
/* 209 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 2, 3, paramStructureBoundingBox);
/* 210 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 3, 1, paramStructureBoundingBox);
/* 211 */			 a(paramWorld, Block.WOOL.id, i1, i3, 3, 2, paramStructureBoundingBox);
/* 212 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 3, 3, paramStructureBoundingBox);
/* 213 */			 a(paramWorld, Block.WOOL.id, i1, i3, 4, 1, paramStructureBoundingBox);
/* 214 */			 a(paramWorld, Block.SANDSTONE.id, 1, i3, 4, 2, paramStructureBoundingBox);
/* 215 */			 a(paramWorld, Block.WOOL.id, i1, i3, 4, 3, paramStructureBoundingBox);
/* 216 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 5, 1, paramStructureBoundingBox);
/* 217 */			 a(paramWorld, Block.WOOL.id, i1, i3, 5, 2, paramStructureBoundingBox);
/* 218 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 5, 3, paramStructureBoundingBox);
/* 219 */			 a(paramWorld, Block.WOOL.id, i1, i3, 6, 1, paramStructureBoundingBox);
/* 220 */			 a(paramWorld, Block.SANDSTONE.id, 1, i3, 6, 2, paramStructureBoundingBox);
/* 221 */			 a(paramWorld, Block.WOOL.id, i1, i3, 6, 3, paramStructureBoundingBox);
/* 222 */			 a(paramWorld, Block.WOOL.id, i1, i3, 7, 1, paramStructureBoundingBox);
/* 223 */			 a(paramWorld, Block.WOOL.id, i1, i3, 7, 2, paramStructureBoundingBox);
/* 224 */			 a(paramWorld, Block.WOOL.id, i1, i3, 7, 3, paramStructureBoundingBox);
/* 225 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 1, paramStructureBoundingBox);
/* 226 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 2, paramStructureBoundingBox);
/* 227 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 3, paramStructureBoundingBox);
/*		 */		 }
/* 229 */		 for (i3 = 2; i3 <= this.a - 3; i3 += this.a - 3 - 2) {
/* 230 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 2, 0, paramStructureBoundingBox);
/* 231 */			 a(paramWorld, Block.WOOL.id, i1, i3, 2, 0, paramStructureBoundingBox);
/* 232 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 2, 0, paramStructureBoundingBox);
/* 233 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 3, 0, paramStructureBoundingBox);
/* 234 */			 a(paramWorld, Block.WOOL.id, i1, i3, 3, 0, paramStructureBoundingBox);
/* 235 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 3, 0, paramStructureBoundingBox);
/* 236 */			 a(paramWorld, Block.WOOL.id, i1, i3 - 1, 4, 0, paramStructureBoundingBox);
/* 237 */			 a(paramWorld, Block.SANDSTONE.id, 1, i3, 4, 0, paramStructureBoundingBox);
/* 238 */			 a(paramWorld, Block.WOOL.id, i1, i3 + 1, 4, 0, paramStructureBoundingBox);
/* 239 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 5, 0, paramStructureBoundingBox);
/* 240 */			 a(paramWorld, Block.WOOL.id, i1, i3, 5, 0, paramStructureBoundingBox);
/* 241 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 5, 0, paramStructureBoundingBox);
/* 242 */			 a(paramWorld, Block.WOOL.id, i1, i3 - 1, 6, 0, paramStructureBoundingBox);
/* 243 */			 a(paramWorld, Block.SANDSTONE.id, 1, i3, 6, 0, paramStructureBoundingBox);
/* 244 */			 a(paramWorld, Block.WOOL.id, i1, i3 + 1, 6, 0, paramStructureBoundingBox);
/* 245 */			 a(paramWorld, Block.WOOL.id, i1, i3 - 1, 7, 0, paramStructureBoundingBox);
/* 246 */			 a(paramWorld, Block.WOOL.id, i1, i3, 7, 0, paramStructureBoundingBox);
/* 247 */			 a(paramWorld, Block.WOOL.id, i1, i3 + 1, 7, 0, paramStructureBoundingBox);
/* 248 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 8, 0, paramStructureBoundingBox);
/* 249 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 0, paramStructureBoundingBox);
/* 250 */			 a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 8, 0, paramStructureBoundingBox);
/*		 */		 }
/* 252 */		 a(paramWorld, paramStructureBoundingBox, 8, 4, 0, 12, 6, 0, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 253 */		 a(paramWorld, 0, 0, 8, 6, 0, paramStructureBoundingBox);
/* 254 */		 a(paramWorld, 0, 0, 12, 6, 0, paramStructureBoundingBox);
/* 255 */		 a(paramWorld, Block.WOOL.id, i1, 9, 5, 0, paramStructureBoundingBox);
/* 256 */		 a(paramWorld, Block.SANDSTONE.id, 1, 10, 5, 0, paramStructureBoundingBox);
/* 257 */		 a(paramWorld, Block.WOOL.id, i1, 11, 5, 0, paramStructureBoundingBox);
/*		 */ 
/* 260 */		 a(paramWorld, paramStructureBoundingBox, 8, -14, 8, 12, -11, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 261 */		 a(paramWorld, paramStructureBoundingBox, 8, -10, 8, 12, -10, 12, Block.SANDSTONE.id, 1, Block.SANDSTONE.id, 1, false);
/* 262 */		 a(paramWorld, paramStructureBoundingBox, 8, -9, 8, 12, -9, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
/* 263 */		 a(paramWorld, paramStructureBoundingBox, 8, -8, 8, 12, -1, 12, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
/* 264 */		 a(paramWorld, paramStructureBoundingBox, 9, -11, 9, 11, -1, 11, 0, 0, false);
/* 265 */		 a(paramWorld, Block.STONE_PLATE.id, 0, 10, -11, 10, paramStructureBoundingBox);
/* 266 */		 a(paramWorld, paramStructureBoundingBox, 9, -13, 9, 11, -13, 11, Block.TNT.id, 0, false);
/* 267 */		 a(paramWorld, 0, 0, 8, -11, 10, paramStructureBoundingBox);
/* 268 */		 a(paramWorld, 0, 0, 8, -10, 10, paramStructureBoundingBox);
/* 269 */		 a(paramWorld, Block.SANDSTONE.id, 1, 7, -10, 10, paramStructureBoundingBox);
/* 270 */		 a(paramWorld, Block.SANDSTONE.id, 2, 7, -11, 10, paramStructureBoundingBox);
/* 271 */		 a(paramWorld, 0, 0, 12, -11, 10, paramStructureBoundingBox);
/* 272 */		 a(paramWorld, 0, 0, 12, -10, 10, paramStructureBoundingBox);
/* 273 */		 a(paramWorld, Block.SANDSTONE.id, 1, 13, -10, 10, paramStructureBoundingBox);
/* 274 */		 a(paramWorld, Block.SANDSTONE.id, 2, 13, -11, 10, paramStructureBoundingBox);
/* 275 */		 a(paramWorld, 0, 0, 10, -11, 8, paramStructureBoundingBox);
/* 276 */		 a(paramWorld, 0, 0, 10, -10, 8, paramStructureBoundingBox);
/* 277 */		 a(paramWorld, Block.SANDSTONE.id, 1, 10, -10, 7, paramStructureBoundingBox);
/* 278 */		 a(paramWorld, Block.SANDSTONE.id, 2, 10, -11, 7, paramStructureBoundingBox);
/* 279 */		 a(paramWorld, 0, 0, 10, -11, 12, paramStructureBoundingBox);
/* 280 */		 a(paramWorld, 0, 0, 10, -10, 12, paramStructureBoundingBox);
/* 281 */		 a(paramWorld, Block.SANDSTONE.id, 1, 10, -10, 13, paramStructureBoundingBox);
/* 282 */		 a(paramWorld, Block.SANDSTONE.id, 2, 10, -11, 13, paramStructureBoundingBox);
/*		 */ 
/* 285 */		 for (i3 = 0; i3 < 4; i3++) {
/* 286 */			 if (this.h[i3] == 0) {
/* 287 */				 int i4 = Direction.a[i3] * 2;
/* 288 */				 int i5 = Direction.b[i3] * 2;
/* 289 */				 this.h[i3] = a(paramWorld, paramStructureBoundingBox, paramRandom, 10 + i4, -11, 10 + i5, i, 2 + paramRandom.nextInt(5));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 293 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenPyramidPiece
 * JD-Core Version:		0.6.0
 */