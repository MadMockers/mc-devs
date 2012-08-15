/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class WorldGenJungleTemple extends WorldGenScatteredPiece
/*		 */ {
/*		 */	 private boolean h;
/*		 */	 private boolean i;
/*		 */	 private boolean j;
/*		 */	 private boolean k;
/* 306 */	 private static final StructurePieceTreasure[] l = { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 2, 7, 15), new StructurePieceTreasure(Item.EMERALD.id, 0, 1, 3, 2), new StructurePieceTreasure(Item.BONE.id, 0, 4, 6, 20), new StructurePieceTreasure(Item.ROTTEN_FLESH.id, 0, 3, 7, 16) };
/*		 */ 
/* 314 */	 private static final StructurePieceTreasure[] m = { new StructurePieceTreasure(Item.ARROW.id, 0, 2, 7, 30) };
/*		 */ 
/* 534 */	 private static WorldGenJungleTemplePiece n = new WorldGenJungleTemplePiece(null);
/*		 */ 
/*		 */	 public WorldGenJungleTemple(Random paramRandom, int paramInt1, int paramInt2)
/*		 */	 {
/* 321 */		 super(paramRandom, paramInt1, 64, paramInt2, 12, 10, 15);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
/*		 */	 {
/* 327 */		 if (!a(paramWorld, paramStructureBoundingBox, 0)) {
/* 328 */			 return false;
/*		 */		 }
/*		 */ 
/* 331 */		 int i1 = c(Block.COBBLESTONE_STAIRS.id, 3);
/* 332 */		 int i2 = c(Block.COBBLESTONE_STAIRS.id, 2);
/* 333 */		 int i3 = c(Block.COBBLESTONE_STAIRS.id, 0);
/* 334 */		 int i4 = c(Block.COBBLESTONE_STAIRS.id, 1);
/*		 */ 
/* 337 */		 a(paramWorld, paramStructureBoundingBox, 0, -4, 0, this.a - 1, 0, this.c - 1, false, paramRandom, n);
/*		 */ 
/* 340 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 2, 9, 2, 2, false, paramRandom, n);
/* 341 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 12, 9, 2, 12, false, paramRandom, n);
/* 342 */		 a(paramWorld, paramStructureBoundingBox, 2, 1, 3, 2, 2, 11, false, paramRandom, n);
/* 343 */		 a(paramWorld, paramStructureBoundingBox, 9, 1, 3, 9, 2, 11, false, paramRandom, n);
/*		 */ 
/* 346 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 1, 10, 6, 1, false, paramRandom, n);
/* 347 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 13, 10, 6, 13, false, paramRandom, n);
/* 348 */		 a(paramWorld, paramStructureBoundingBox, 1, 3, 2, 1, 6, 12, false, paramRandom, n);
/* 349 */		 a(paramWorld, paramStructureBoundingBox, 10, 3, 2, 10, 6, 12, false, paramRandom, n);
/*		 */ 
/* 352 */		 a(paramWorld, paramStructureBoundingBox, 2, 3, 2, 9, 3, 12, false, paramRandom, n);
/* 353 */		 a(paramWorld, paramStructureBoundingBox, 2, 6, 2, 9, 6, 12, false, paramRandom, n);
/* 354 */		 a(paramWorld, paramStructureBoundingBox, 3, 7, 3, 8, 7, 11, false, paramRandom, n);
/* 355 */		 a(paramWorld, paramStructureBoundingBox, 4, 8, 4, 7, 8, 10, false, paramRandom, n);
/*		 */ 
/* 358 */		 a(paramWorld, paramStructureBoundingBox, 3, 1, 3, 8, 2, 11);
/* 359 */		 a(paramWorld, paramStructureBoundingBox, 4, 3, 6, 7, 3, 9);
/* 360 */		 a(paramWorld, paramStructureBoundingBox, 2, 4, 2, 9, 5, 12);
/* 361 */		 a(paramWorld, paramStructureBoundingBox, 4, 6, 5, 7, 6, 9);
/* 362 */		 a(paramWorld, paramStructureBoundingBox, 5, 7, 6, 6, 7, 8);
/*		 */ 
/* 365 */		 a(paramWorld, paramStructureBoundingBox, 5, 1, 2, 6, 2, 2);
/* 366 */		 a(paramWorld, paramStructureBoundingBox, 5, 2, 12, 6, 2, 12);
/* 367 */		 a(paramWorld, paramStructureBoundingBox, 5, 5, 1, 6, 5, 1);
/* 368 */		 a(paramWorld, paramStructureBoundingBox, 5, 5, 13, 6, 5, 13);
/* 369 */		 a(paramWorld, 0, 0, 1, 5, 5, paramStructureBoundingBox);
/* 370 */		 a(paramWorld, 0, 0, 10, 5, 5, paramStructureBoundingBox);
/* 371 */		 a(paramWorld, 0, 0, 1, 5, 9, paramStructureBoundingBox);
/* 372 */		 a(paramWorld, 0, 0, 10, 5, 9, paramStructureBoundingBox);
/*		 */ 
/* 375 */		 for (int i5 = 0; i5 <= 14; i5 += 14) {
/* 376 */			 a(paramWorld, paramStructureBoundingBox, 2, 4, i5, 2, 5, i5, false, paramRandom, n);
/* 377 */			 a(paramWorld, paramStructureBoundingBox, 4, 4, i5, 4, 5, i5, false, paramRandom, n);
/* 378 */			 a(paramWorld, paramStructureBoundingBox, 7, 4, i5, 7, 5, i5, false, paramRandom, n);
/* 379 */			 a(paramWorld, paramStructureBoundingBox, 9, 4, i5, 9, 5, i5, false, paramRandom, n);
/*		 */		 }
/* 381 */		 a(paramWorld, paramStructureBoundingBox, 5, 6, 0, 6, 6, 0, false, paramRandom, n);
/* 382 */		 for (i5 = 0; i5 <= 11; i5 += 11) {
/* 383 */			 for (int i6 = 2; i6 <= 12; i6 += 2) {
/* 384 */				 a(paramWorld, paramStructureBoundingBox, i5, 4, i6, i5, 5, i6, false, paramRandom, n);
/*		 */			 }
/* 386 */			 a(paramWorld, paramStructureBoundingBox, i5, 6, 5, i5, 6, 5, false, paramRandom, n);
/* 387 */			 a(paramWorld, paramStructureBoundingBox, i5, 6, 9, i5, 6, 9, false, paramRandom, n);
/*		 */		 }
/* 389 */		 a(paramWorld, paramStructureBoundingBox, 2, 7, 2, 2, 9, 2, false, paramRandom, n);
/* 390 */		 a(paramWorld, paramStructureBoundingBox, 9, 7, 2, 9, 9, 2, false, paramRandom, n);
/* 391 */		 a(paramWorld, paramStructureBoundingBox, 2, 7, 12, 2, 9, 12, false, paramRandom, n);
/* 392 */		 a(paramWorld, paramStructureBoundingBox, 9, 7, 12, 9, 9, 12, false, paramRandom, n);
/* 393 */		 a(paramWorld, paramStructureBoundingBox, 4, 9, 4, 4, 9, 4, false, paramRandom, n);
/* 394 */		 a(paramWorld, paramStructureBoundingBox, 7, 9, 4, 7, 9, 4, false, paramRandom, n);
/* 395 */		 a(paramWorld, paramStructureBoundingBox, 4, 9, 10, 4, 9, 10, false, paramRandom, n);
/* 396 */		 a(paramWorld, paramStructureBoundingBox, 7, 9, 10, 7, 9, 10, false, paramRandom, n);
/* 397 */		 a(paramWorld, paramStructureBoundingBox, 5, 9, 7, 6, 9, 7, false, paramRandom, n);
/* 398 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 5, 9, 6, paramStructureBoundingBox);
/* 399 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 6, 9, 6, paramStructureBoundingBox);
/* 400 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 5, 9, 8, paramStructureBoundingBox);
/* 401 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 6, 9, 8, paramStructureBoundingBox);
/*		 */ 
/* 404 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 0, 0, paramStructureBoundingBox);
/* 405 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 5, 0, 0, paramStructureBoundingBox);
/* 406 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 6, 0, 0, paramStructureBoundingBox);
/* 407 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 0, 0, paramStructureBoundingBox);
/*		 */ 
/* 410 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 1, 8, paramStructureBoundingBox);
/* 411 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 2, 9, paramStructureBoundingBox);
/* 412 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 3, 10, paramStructureBoundingBox);
/* 413 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 1, 8, paramStructureBoundingBox);
/* 414 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 2, 9, paramStructureBoundingBox);
/* 415 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 3, 10, paramStructureBoundingBox);
/* 416 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 9, 4, 1, 9, false, paramRandom, n);
/* 417 */		 a(paramWorld, paramStructureBoundingBox, 7, 1, 9, 7, 1, 9, false, paramRandom, n);
/* 418 */		 a(paramWorld, paramStructureBoundingBox, 4, 1, 10, 7, 2, 10, false, paramRandom, n);
/*		 */ 
/* 421 */		 a(paramWorld, paramStructureBoundingBox, 5, 4, 5, 6, 4, 5, false, paramRandom, n);
/* 422 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i3, 4, 4, 5, paramStructureBoundingBox);
/* 423 */		 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i4, 7, 4, 5, paramStructureBoundingBox);
/*		 */ 
/* 426 */		 for (i5 = 0; i5 < 4; i5++) {
/* 427 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 5, 0 - i5, 6 + i5, paramStructureBoundingBox);
/* 428 */			 a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 6, 0 - i5, 6 + i5, paramStructureBoundingBox);
/* 429 */			 a(paramWorld, paramStructureBoundingBox, 5, 0 - i5, 7 + i5, 6, 0 - i5, 9 + i5);
/*		 */		 }
/*		 */ 
/* 433 */		 a(paramWorld, paramStructureBoundingBox, 1, -3, 12, 10, -1, 13);
/* 434 */		 a(paramWorld, paramStructureBoundingBox, 1, -3, 1, 3, -1, 13);
/* 435 */		 a(paramWorld, paramStructureBoundingBox, 1, -3, 1, 9, -1, 5);
/* 436 */		 for (i5 = 1; i5 <= 13; i5 += 2) {
/* 437 */			 a(paramWorld, paramStructureBoundingBox, 1, -3, i5, 1, -2, i5, false, paramRandom, n);
/*		 */		 }
/* 439 */		 for (i5 = 2; i5 <= 12; i5 += 2) {
/* 440 */			 a(paramWorld, paramStructureBoundingBox, 1, -1, i5, 3, -1, i5, false, paramRandom, n);
/*		 */		 }
/* 442 */		 a(paramWorld, paramStructureBoundingBox, 2, -2, 1, 5, -2, 1, false, paramRandom, n);
/* 443 */		 a(paramWorld, paramStructureBoundingBox, 7, -2, 1, 9, -2, 1, false, paramRandom, n);
/* 444 */		 a(paramWorld, paramStructureBoundingBox, 6, -3, 1, 6, -3, 1, false, paramRandom, n);
/* 445 */		 a(paramWorld, paramStructureBoundingBox, 6, -1, 1, 6, -1, 1, false, paramRandom, n);
/*		 */ 
/* 448 */		 a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 3) | 0x4, 1, -3, 8, paramStructureBoundingBox);
/* 449 */		 a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 1) | 0x4, 4, -3, 8, paramStructureBoundingBox);
/* 450 */		 a(paramWorld, Block.TRIPWIRE.id, 4, 2, -3, 8, paramStructureBoundingBox);
/* 451 */		 a(paramWorld, Block.TRIPWIRE.id, 4, 3, -3, 8, paramStructureBoundingBox);
/* 452 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 7, paramStructureBoundingBox);
/* 453 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 6, paramStructureBoundingBox);
/* 454 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 5, paramStructureBoundingBox);
/* 455 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 4, paramStructureBoundingBox);
/* 456 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 3, paramStructureBoundingBox);
/* 457 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 2, paramStructureBoundingBox);
/* 458 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 1, paramStructureBoundingBox);
/* 459 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 4, -3, 1, paramStructureBoundingBox);
/* 460 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 3, -3, 1, paramStructureBoundingBox);
/* 461 */		 if (!this.j) {
/* 462 */			 this.j = a(paramWorld, paramStructureBoundingBox, paramRandom, 3, -2, 1, 2, m, 2);
/*		 */		 }
/* 464 */		 a(paramWorld, Block.VINE.id, 15, 3, -2, 2, paramStructureBoundingBox);
/*		 */ 
/* 467 */		 a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 2) | 0x4, 7, -3, 1, paramStructureBoundingBox);
/* 468 */		 a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 0) | 0x4, 7, -3, 5, paramStructureBoundingBox);
/* 469 */		 a(paramWorld, Block.TRIPWIRE.id, 4, 7, -3, 2, paramStructureBoundingBox);
/* 470 */		 a(paramWorld, Block.TRIPWIRE.id, 4, 7, -3, 3, paramStructureBoundingBox);
/* 471 */		 a(paramWorld, Block.TRIPWIRE.id, 4, 7, -3, 4, paramStructureBoundingBox);
/* 472 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 8, -3, 6, paramStructureBoundingBox);
/* 473 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 9, -3, 6, paramStructureBoundingBox);
/* 474 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 9, -3, 5, paramStructureBoundingBox);
/* 475 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 9, -3, 4, paramStructureBoundingBox);
/* 476 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 9, -2, 4, paramStructureBoundingBox);
/* 477 */		 if (!this.k) {
/* 478 */			 this.k = a(paramWorld, paramStructureBoundingBox, paramRandom, 9, -2, 3, 4, m, 2);
/*		 */		 }
/* 480 */		 a(paramWorld, Block.VINE.id, 15, 8, -1, 3, paramStructureBoundingBox);
/* 481 */		 a(paramWorld, Block.VINE.id, 15, 8, -2, 3, paramStructureBoundingBox);
/* 482 */		 if (!this.h) {
/* 483 */			 this.h = a(paramWorld, paramStructureBoundingBox, paramRandom, 8, -3, 3, l, 2 + paramRandom.nextInt(5));
/*		 */		 }
/* 485 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 9, -3, 2, paramStructureBoundingBox);
/* 486 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 8, -3, 1, paramStructureBoundingBox);
/* 487 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 4, -3, 5, paramStructureBoundingBox);
/* 488 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 5, -2, 5, paramStructureBoundingBox);
/* 489 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 5, -1, 5, paramStructureBoundingBox);
/* 490 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 6, -3, 5, paramStructureBoundingBox);
/* 491 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 7, -2, 5, paramStructureBoundingBox);
/* 492 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 7, -1, 5, paramStructureBoundingBox);
/* 493 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 8, -3, 5, paramStructureBoundingBox);
/* 494 */		 a(paramWorld, paramStructureBoundingBox, 9, -1, 1, 9, -1, 5, false, paramRandom, n);
/*		 */ 
/* 497 */		 a(paramWorld, paramStructureBoundingBox, 8, -3, 8, 10, -1, 10);
/* 498 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 3, 8, -2, 11, paramStructureBoundingBox);
/* 499 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 3, 9, -2, 11, paramStructureBoundingBox);
/* 500 */		 a(paramWorld, Block.SMOOTH_BRICK.id, 3, 10, -2, 11, paramStructureBoundingBox);
/* 501 */		 a(paramWorld, Block.LEVER.id, BlockLever.d(c(Block.LEVER.id, 2)), 8, -2, 12, paramStructureBoundingBox);
/* 502 */		 a(paramWorld, Block.LEVER.id, BlockLever.d(c(Block.LEVER.id, 2)), 9, -2, 12, paramStructureBoundingBox);
/* 503 */		 a(paramWorld, Block.LEVER.id, BlockLever.d(c(Block.LEVER.id, 2)), 10, -2, 12, paramStructureBoundingBox);
/* 504 */		 a(paramWorld, paramStructureBoundingBox, 8, -3, 8, 8, -3, 10, false, paramRandom, n);
/* 505 */		 a(paramWorld, paramStructureBoundingBox, 10, -3, 8, 10, -3, 10, false, paramRandom, n);
/* 506 */		 a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 10, -2, 9, paramStructureBoundingBox);
/* 507 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 8, -2, 9, paramStructureBoundingBox);
/* 508 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 8, -2, 10, paramStructureBoundingBox);
/* 509 */		 a(paramWorld, Block.REDSTONE_WIRE.id, 0, 10, -1, 9, paramStructureBoundingBox);
/* 510 */		 a(paramWorld, Block.PISTON_STICKY.id, 1, 9, -2, 8, paramStructureBoundingBox);
/* 511 */		 a(paramWorld, Block.PISTON_STICKY.id, c(Block.PISTON_STICKY.id, 4), 10, -2, 8, paramStructureBoundingBox);
/* 512 */		 a(paramWorld, Block.PISTON_STICKY.id, c(Block.PISTON_STICKY.id, 4), 10, -1, 8, paramStructureBoundingBox);
/* 513 */		 a(paramWorld, Block.DIODE_OFF.id, c(Block.DIODE_OFF.id, 2), 10, -2, 10, paramStructureBoundingBox);
/* 514 */		 if (!this.i) {
/* 515 */			 this.i = a(paramWorld, paramStructureBoundingBox, paramRandom, 9, -3, 10, l, 2 + paramRandom.nextInt(5));
/*		 */		 }
/*		 */ 
/* 519 */		 return true;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenJungleTemple
 * JD-Core Version:		0.6.0
 */