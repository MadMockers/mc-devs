/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public abstract class StructurePiece
/*		 */ {
/*		 */	 protected StructureBoundingBox e;
/*		 */	 protected int f;
/*		 */	 protected int g;
/*		 */ 
/*		 */	 protected StructurePiece(int paramInt)
/*		 */	 {
/*	46 */		 this.g = paramInt;
/*	47 */		 this.f = -1;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom) {
/*		 */	 }
/*		 */ 
/*		 */	 public abstract boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox);
/*		 */ 
/*		 */	 public StructureBoundingBox b() {
/*	57 */		 return this.e;
/*		 */	 }
/*		 */ 
/*		 */	 public int c() {
/*	61 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public static StructurePiece a(List paramList, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/*	72 */		 for (StructurePiece localStructurePiece : paramList) {
/*	73 */			 if ((localStructurePiece.b() != null) && (localStructurePiece.b().a(paramStructureBoundingBox))) {
/*	74 */				 return localStructurePiece;
/*		 */			 }
/*		 */		 }
/*	77 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition a() {
/*	81 */		 return new ChunkPosition(this.e.e(), this.e.f(), this.e.g());
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/*	86 */		 int i = Math.max(this.e.a - 1, paramStructureBoundingBox.a);
/*	87 */		 int j = Math.max(this.e.b - 1, paramStructureBoundingBox.b);
/*	88 */		 int k = Math.max(this.e.c - 1, paramStructureBoundingBox.c);
/*	89 */		 int m = Math.min(this.e.d + 1, paramStructureBoundingBox.d);
/*	90 */		 int n = Math.min(this.e.e + 1, paramStructureBoundingBox.e);
/*	91 */		 int i1 = Math.min(this.e.f + 1, paramStructureBoundingBox.f);
/*		 */		 int i3;
/*		 */		 int i4;
/*	94 */		 for (int i2 = i; i2 <= m; i2++) {
/*	95 */			 for (i3 = k; i3 <= i1; i3++) {
/*	96 */				 i4 = paramWorld.getTypeId(i2, j, i3);
/*	97 */				 if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
/*	98 */					 return true;
/*		 */				 }
/* 100 */				 i4 = paramWorld.getTypeId(i2, n, i3);
/* 101 */				 if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
/* 102 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 107 */		 for (i2 = i; i2 <= m; i2++) {
/* 108 */			 for (i3 = j; i3 <= n; i3++) {
/* 109 */				 i4 = paramWorld.getTypeId(i2, i3, k);
/* 110 */				 if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
/* 111 */					 return true;
/*		 */				 }
/* 113 */				 i4 = paramWorld.getTypeId(i2, i3, i1);
/* 114 */				 if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
/* 115 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 120 */		 for (i2 = k; i2 <= i1; i2++) {
/* 121 */			 for (i3 = j; i3 <= n; i3++) {
/* 122 */				 i4 = paramWorld.getTypeId(i, i3, i2);
/* 123 */				 if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
/* 124 */					 return true;
/*		 */				 }
/* 126 */				 i4 = paramWorld.getTypeId(m, i3, i2);
/* 127 */				 if ((i4 > 0) && (Block.byId[i4].material.isLiquid())) {
/* 128 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 132 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected int a(int paramInt1, int paramInt2) {
/* 136 */		 switch (this.f) {
/*		 */		 case 0:
/*		 */		 case 2:
/* 139 */			 return this.e.a + paramInt1;
/*		 */		 case 1:
/* 141 */			 return this.e.d - paramInt2;
/*		 */		 case 3:
/* 143 */			 return this.e.a + paramInt2;
/*		 */		 }
/* 145 */		 return paramInt1;
/*		 */	 }
/*		 */ 
/*		 */	 protected int a(int paramInt)
/*		 */	 {
/* 150 */		 if (this.f == -1) {
/* 151 */			 return paramInt;
/*		 */		 }
/* 153 */		 return paramInt + this.e.b;
/*		 */	 }
/*		 */ 
/*		 */	 protected int b(int paramInt1, int paramInt2) {
/* 157 */		 switch (this.f) {
/*		 */		 case 2:
/* 159 */			 return this.e.f - paramInt2;
/*		 */		 case 0:
/* 161 */			 return this.e.c + paramInt2;
/*		 */		 case 1:
/*		 */		 case 3:
/* 164 */			 return this.e.c + paramInt1;
/*		 */		 }
/* 166 */		 return paramInt2;
/*		 */	 }
/*		 */ 
/*		 */	 protected int c(int paramInt1, int paramInt2)
/*		 */	 {
/* 171 */		 if (paramInt1 == Block.RAILS.id) {
/* 172 */			 if ((this.f == 1) || (this.f == 3)) {
/* 173 */				 if (paramInt2 == 1) {
/* 174 */					 return 0;
/*		 */				 }
/* 176 */				 return 1;
/*		 */			 }
/*		 */		 }
/* 179 */		 else if ((paramInt1 == Block.WOODEN_DOOR.id) || (paramInt1 == Block.IRON_DOOR_BLOCK.id)) {
/* 180 */			 if (this.f == 0) {
/* 181 */				 if (paramInt2 == 0) {
/* 182 */					 return 2;
/*		 */				 }
/* 184 */				 if (paramInt2 == 2)
/* 185 */					 return 0;
/*		 */			 } else {
/* 187 */				 if (this.f == 1)
/*		 */				 {
/* 192 */					 return paramInt2 + 1 & 0x3;
/* 193 */				 }if (this.f == 3)
/*		 */				 {
/* 198 */					 return paramInt2 + 3 & 0x3;
/*		 */				 }
/*		 */			 }
/* 200 */		 } else if ((paramInt1 == Block.COBBLESTONE_STAIRS.id) || (paramInt1 == Block.WOOD_STAIRS.id) || (paramInt1 == Block.NETHER_BRICK_STAIRS.id) || (paramInt1 == Block.STONE_STAIRS.id) || (paramInt1 == Block.SANDSTONE_STAIRS.id)) {
/* 201 */			 if (this.f == 0) {
/* 202 */				 if (paramInt2 == 2) {
/* 203 */					 return 3;
/*		 */				 }
/* 205 */				 if (paramInt2 == 3)
/* 206 */					 return 2;
/*		 */			 }
/* 208 */			 else if (this.f == 1) {
/* 209 */				 if (paramInt2 == 0) {
/* 210 */					 return 2;
/*		 */				 }
/* 212 */				 if (paramInt2 == 1) {
/* 213 */					 return 3;
/*		 */				 }
/* 215 */				 if (paramInt2 == 2) {
/* 216 */					 return 0;
/*		 */				 }
/* 218 */				 if (paramInt2 == 3)
/* 219 */					 return 1;
/*		 */			 }
/* 221 */			 else if (this.f == 3) {
/* 222 */				 if (paramInt2 == 0) {
/* 223 */					 return 2;
/*		 */				 }
/* 225 */				 if (paramInt2 == 1) {
/* 226 */					 return 3;
/*		 */				 }
/* 228 */				 if (paramInt2 == 2) {
/* 229 */					 return 1;
/*		 */				 }
/* 231 */				 if (paramInt2 == 3)
/* 232 */					 return 0;
/*		 */			 }
/*		 */		 }
/* 235 */		 else if (paramInt1 == Block.LADDER.id) {
/* 236 */			 if (this.f == 0) {
/* 237 */				 if (paramInt2 == 2) {
/* 238 */					 return 3;
/*		 */				 }
/* 240 */				 if (paramInt2 == 3)
/* 241 */					 return 2;
/*		 */			 }
/* 243 */			 else if (this.f == 1) {
/* 244 */				 if (paramInt2 == 2) {
/* 245 */					 return 4;
/*		 */				 }
/* 247 */				 if (paramInt2 == 3) {
/* 248 */					 return 5;
/*		 */				 }
/* 250 */				 if (paramInt2 == 4) {
/* 251 */					 return 2;
/*		 */				 }
/* 253 */				 if (paramInt2 == 5)
/* 254 */					 return 3;
/*		 */			 }
/* 256 */			 else if (this.f == 3) {
/* 257 */				 if (paramInt2 == 2) {
/* 258 */					 return 5;
/*		 */				 }
/* 260 */				 if (paramInt2 == 3) {
/* 261 */					 return 4;
/*		 */				 }
/* 263 */				 if (paramInt2 == 4) {
/* 264 */					 return 2;
/*		 */				 }
/* 266 */				 if (paramInt2 == 5) {
/* 267 */					 return 3;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 271 */		 else if (paramInt1 == Block.STONE_BUTTON.id) {
/* 272 */			 if (this.f == 0) {
/* 273 */				 if (paramInt2 == 3) {
/* 274 */					 return 4;
/*		 */				 }
/* 276 */				 if (paramInt2 == 4)
/* 277 */					 return 3;
/*		 */			 }
/* 279 */			 else if (this.f == 1) {
/* 280 */				 if (paramInt2 == 3) {
/* 281 */					 return 1;
/*		 */				 }
/* 283 */				 if (paramInt2 == 4) {
/* 284 */					 return 2;
/*		 */				 }
/* 286 */				 if (paramInt2 == 2) {
/* 287 */					 return 3;
/*		 */				 }
/* 289 */				 if (paramInt2 == 1)
/* 290 */					 return 4;
/*		 */			 }
/* 292 */			 else if (this.f == 3) {
/* 293 */				 if (paramInt2 == 3) {
/* 294 */					 return 2;
/*		 */				 }
/* 296 */				 if (paramInt2 == 4) {
/* 297 */					 return 1;
/*		 */				 }
/* 299 */				 if (paramInt2 == 2) {
/* 300 */					 return 3;
/*		 */				 }
/* 302 */				 if (paramInt2 == 1)
/* 303 */					 return 4;
/*		 */			 }
/*		 */		 }
/* 306 */		 else if ((paramInt1 == Block.TRIPWIRE_SOURCE.id) || ((Block.byId[paramInt1] != null) && ((Block.byId[paramInt1] instanceof BlockDirectional)))) {
/* 307 */			 if (this.f == 0) {
/* 308 */				 if ((paramInt2 == 0) || (paramInt2 == 2))
/* 309 */					 return Direction.e[paramInt2];
/*		 */			 }
/* 311 */			 else if (this.f == 1) {
/* 312 */				 if (paramInt2 == 2) {
/* 313 */					 return 1;
/*		 */				 }
/* 315 */				 if (paramInt2 == 0) {
/* 316 */					 return 3;
/*		 */				 }
/* 318 */				 if (paramInt2 == 1) {
/* 319 */					 return 2;
/*		 */				 }
/* 321 */				 if (paramInt2 == 3)
/* 322 */					 return 0;
/*		 */			 }
/* 324 */			 else if (this.f == 3) {
/* 325 */				 if (paramInt2 == 2) {
/* 326 */					 return 3;
/*		 */				 }
/* 328 */				 if (paramInt2 == 0) {
/* 329 */					 return 1;
/*		 */				 }
/* 331 */				 if (paramInt2 == 1) {
/* 332 */					 return 2;
/*		 */				 }
/* 334 */				 if (paramInt2 == 3)
/* 335 */					 return 0;
/*		 */			 }
/*		 */		 }
/* 338 */		 else if ((paramInt1 == Block.PISTON.id) || (paramInt1 == Block.PISTON_STICKY.id) || (paramInt1 == Block.LEVER.id) || (paramInt1 == Block.DISPENSER.id)) {
/* 339 */			 if (this.f == 0) {
/* 340 */				 if ((paramInt2 == 2) || (paramInt2 == 3))
/* 341 */					 return Facing.OPPOSITE_FACING[paramInt2];
/*		 */			 }
/* 343 */			 else if (this.f == 1) {
/* 344 */				 if (paramInt2 == 2) {
/* 345 */					 return 4;
/*		 */				 }
/* 347 */				 if (paramInt2 == 3) {
/* 348 */					 return 5;
/*		 */				 }
/* 350 */				 if (paramInt2 == 4) {
/* 351 */					 return 2;
/*		 */				 }
/* 353 */				 if (paramInt2 == 5)
/* 354 */					 return 3;
/*		 */			 }
/* 356 */			 else if (this.f == 3) {
/* 357 */				 if (paramInt2 == 2) {
/* 358 */					 return 5;
/*		 */				 }
/* 360 */				 if (paramInt2 == 3) {
/* 361 */					 return 4;
/*		 */				 }
/* 363 */				 if (paramInt2 == 4) {
/* 364 */					 return 2;
/*		 */				 }
/* 366 */				 if (paramInt2 == 5) {
/* 367 */					 return 3;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 371 */		 return paramInt2;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 376 */		 int i = a(paramInt3, paramInt5);
/* 377 */		 int j = a(paramInt4);
/* 378 */		 int k = b(paramInt3, paramInt5);
/*		 */ 
/* 380 */		 if (!paramStructureBoundingBox.b(i, j, k)) {
/* 381 */			 return;
/*		 */		 }
/*		 */ 
/* 384 */		 paramWorld.setRawTypeIdAndData(i, j, k, paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 protected int a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 401 */		 int i = a(paramInt1, paramInt3);
/* 402 */		 int j = a(paramInt2);
/* 403 */		 int k = b(paramInt1, paramInt3);
/*		 */ 
/* 405 */		 if (!paramStructureBoundingBox.b(i, j, k)) {
/* 406 */			 return 0;
/*		 */		 }
/*		 */ 
/* 409 */		 return paramWorld.getTypeId(i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/* 413 */		 for (int i = paramInt2; i <= paramInt5; i++)
/* 414 */			 for (int j = paramInt1; j <= paramInt4; j++)
/* 415 */				 for (int k = paramInt3; k <= paramInt6; k++)
/* 416 */					 a(paramWorld, 0, 0, j, i, k, paramStructureBoundingBox);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
/*		 */	 {
/* 424 */		 for (int i = paramInt2; i <= paramInt5; i++)
/* 425 */			 for (int j = paramInt1; j <= paramInt4; j++)
/* 426 */				 for (int k = paramInt3; k <= paramInt6; k++)
/*		 */				 {
/* 428 */					 if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
/*		 */						 continue;
/*		 */					 }
/* 431 */					 if ((i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6))
/* 432 */						 a(paramWorld, paramInt7, 0, j, i, k, paramStructureBoundingBox);
/*		 */					 else
/* 434 */						 a(paramWorld, paramInt8, 0, j, i, k, paramStructureBoundingBox);
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, boolean paramBoolean)
/*		 */	 {
/* 444 */		 for (int i = paramInt2; i <= paramInt5; i++)
/* 445 */			 for (int j = paramInt1; j <= paramInt4; j++)
/* 446 */				 for (int k = paramInt3; k <= paramInt6; k++)
/*		 */				 {
/* 448 */					 if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
/*		 */						 continue;
/*		 */					 }
/* 451 */					 if ((i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6))
/* 452 */						 a(paramWorld, paramInt7, paramInt8, j, i, k, paramStructureBoundingBox);
/*		 */					 else
/* 454 */						 a(paramWorld, paramInt9, paramInt10, j, i, k, paramStructureBoundingBox);
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, Random paramRandom, StructurePieceBlockSelector paramStructurePieceBlockSelector)
/*		 */	 {
/* 468 */		 for (int i = paramInt2; i <= paramInt5; i++)
/* 469 */			 for (int j = paramInt1; j <= paramInt4; j++)
/* 470 */				 for (int k = paramInt3; k <= paramInt6; k++)
/*		 */				 {
/* 472 */					 if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
/*		 */						 continue;
/*		 */					 }
/* 475 */					 paramStructurePieceBlockSelector.a(paramRandom, j, i, k, (i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6));
/* 476 */					 a(paramWorld, paramStructurePieceBlockSelector.a(), paramStructurePieceBlockSelector.b(), j, i, k, paramStructureBoundingBox);
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
/*		 */	 {
/* 489 */		 for (int i = paramInt2; i <= paramInt5; i++)
/* 490 */			 for (int j = paramInt1; j <= paramInt4; j++)
/* 491 */				 for (int k = paramInt3; k <= paramInt6; k++)
/*		 */				 {
/* 493 */					 if (paramRandom.nextFloat() > paramFloat) {
/*		 */						 continue;
/*		 */					 }
/* 496 */					 if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0)) {
/*		 */						 continue;
/*		 */					 }
/* 499 */					 if ((i == paramInt2) || (i == paramInt5) || (j == paramInt1) || (j == paramInt4) || (k == paramInt3) || (k == paramInt6))
/* 500 */						 a(paramWorld, paramInt7, 0, j, i, k, paramStructureBoundingBox);
/*		 */					 else
/* 502 */						 a(paramWorld, paramInt8, 0, j, i, k, paramStructureBoundingBox);
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*		 */	 {
/* 512 */		 if (paramRandom.nextFloat() < paramFloat)
/* 513 */			 a(paramWorld, paramInt4, paramInt5, paramInt1, paramInt2, paramInt3, paramStructureBoundingBox);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean)
/*		 */	 {
/* 520 */		 float f1 = paramInt4 - paramInt1 + 1;
/* 521 */		 float f2 = paramInt5 - paramInt2 + 1;
/* 522 */		 float f3 = paramInt6 - paramInt3 + 1;
/* 523 */		 float f4 = paramInt1 + f1 / 2.0F;
/* 524 */		 float f5 = paramInt3 + f3 / 2.0F;
/*		 */ 
/* 526 */		 for (int i = paramInt2; i <= paramInt5; i++) {
/* 527 */			 float f6 = (i - paramInt2) / f2;
/*		 */ 
/* 529 */			 for (int j = paramInt1; j <= paramInt4; j++) {
/* 530 */				 float f7 = (j - f4) / (f1 * 0.5F);
/*		 */ 
/* 532 */				 for (int k = paramInt3; k <= paramInt6; k++) {
/* 533 */					 float f8 = (k - f5) / (f3 * 0.5F);
/*		 */ 
/* 535 */					 if ((paramBoolean) && (a(paramWorld, j, i, k, paramStructureBoundingBox) == 0))
/*		 */					 {
/*		 */						 continue;
/*		 */					 }
/* 539 */					 float f9 = f7 * f7 + f6 * f6 + f8 * f8;
/*		 */ 
/* 541 */					 if (f9 <= 1.05F)
/* 542 */						 a(paramWorld, paramInt7, 0, j, i, k, paramStructureBoundingBox);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 552 */		 int i = a(paramInt1, paramInt3);
/* 553 */		 int j = a(paramInt2);
/* 554 */		 int k = b(paramInt1, paramInt3);
/*		 */ 
/* 556 */		 if (!paramStructureBoundingBox.b(i, j, k)) {
/* 557 */			 return;
/*		 */		 }
/*		 */ 
/* 560 */		 while ((!paramWorld.isEmpty(i, j, k)) && (j < 255)) {
/* 561 */			 paramWorld.setRawTypeIdAndData(i, j, k, 0, 0);
/* 562 */			 j++;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 568 */		 int i = a(paramInt3, paramInt5);
/* 569 */		 int j = a(paramInt4);
/* 570 */		 int k = b(paramInt3, paramInt5);
/*		 */ 
/* 572 */		 if (!paramStructureBoundingBox.b(i, j, k)) {
/* 573 */			 return;
/*		 */		 }
/*		 */ 
/* 576 */		 while (((paramWorld.isEmpty(i, j, k)) || (paramWorld.getMaterial(i, j, k).isLiquid())) && (j > 1)) {
/* 577 */			 paramWorld.setRawTypeIdAndData(i, j, k, paramInt1, paramInt2);
/* 578 */			 j--;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, int paramInt4)
/*		 */	 {
/* 584 */		 int i = a(paramInt1, paramInt3);
/* 585 */		 int j = a(paramInt2);
/* 586 */		 int k = b(paramInt1, paramInt3);
/*		 */ 
/* 588 */		 if ((paramStructureBoundingBox.b(i, j, k)) && 
/* 589 */			 (paramWorld.getTypeId(i, j, k) != Block.CHEST.id)) {
/* 590 */			 paramWorld.setTypeId(i, j, k, Block.CHEST.id);
/* 591 */			 TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(i, j, k);
/* 592 */			 if (localTileEntityChest != null) StructurePieceTreasure.a(paramRandom, paramArrayOfStructurePieceTreasure, localTileEntityChest, paramInt4);
/* 593 */			 return true;
/*		 */		 }
/*		 */ 
/* 596 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, int paramInt5)
/*		 */	 {
/* 601 */		 int i = a(paramInt1, paramInt3);
/* 602 */		 int j = a(paramInt2);
/* 603 */		 int k = b(paramInt1, paramInt3);
/*		 */ 
/* 605 */		 if ((paramStructureBoundingBox.b(i, j, k)) && 
/* 606 */			 (paramWorld.getTypeId(i, j, k) != Block.DISPENSER.id)) {
/* 607 */			 paramWorld.setTypeIdAndData(i, j, k, Block.DISPENSER.id, c(Block.DISPENSER.id, paramInt4));
/* 608 */			 TileEntityDispenser localTileEntityDispenser = (TileEntityDispenser)paramWorld.getTileEntity(i, j, k);
/* 609 */			 if (localTileEntityDispenser != null) StructurePieceTreasure.a(paramRandom, paramArrayOfStructurePieceTreasure, localTileEntityDispenser, paramInt5);
/* 610 */			 return true;
/*		 */		 }
/*		 */ 
/* 613 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*		 */	 {
/* 618 */		 int i = a(paramInt1, paramInt3);
/* 619 */		 int j = a(paramInt2);
/* 620 */		 int k = b(paramInt1, paramInt3);
/*		 */ 
/* 622 */		 if (paramStructureBoundingBox.b(i, j, k))
/* 623 */			 ItemDoor.place(paramWorld, i, j, k, paramInt4, Block.WOODEN_DOOR);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.StructurePiece
 * JD-Core Version:		0.6.0
 */