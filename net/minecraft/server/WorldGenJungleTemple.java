package net.minecraft.server;

import java.util.Random;

public class WorldGenJungleTemple extends WorldGenScatteredPiece
{
	private boolean h;
	private boolean i;
	private boolean j;
	private boolean k;
	private static final StructurePieceTreasure[] l = { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 2, 7, 15), new StructurePieceTreasure(Item.EMERALD.id, 0, 1, 3, 2), new StructurePieceTreasure(Item.BONE.id, 0, 4, 6, 20), new StructurePieceTreasure(Item.ROTTEN_FLESH.id, 0, 3, 7, 16) };

	private static final StructurePieceTreasure[] m = { new StructurePieceTreasure(Item.ARROW.id, 0, 2, 7, 30) };

	private static WorldGenJungleTemplePiece n = new WorldGenJungleTemplePiece(null);

	public WorldGenJungleTemple(Random paramRandom, int paramInt1, int paramInt2)
	{
		super(paramRandom, paramInt1, 64, paramInt2, 12, 10, 15);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (!a(paramWorld, paramStructureBoundingBox, 0)) {
			return false;
		}

		int i1 = c(Block.COBBLESTONE_STAIRS.id, 3);
		int i2 = c(Block.COBBLESTONE_STAIRS.id, 2);
		int i3 = c(Block.COBBLESTONE_STAIRS.id, 0);
		int i4 = c(Block.COBBLESTONE_STAIRS.id, 1);

		a(paramWorld, paramStructureBoundingBox, 0, -4, 0, this.a - 1, 0, this.c - 1, false, paramRandom, n);

		a(paramWorld, paramStructureBoundingBox, 2, 1, 2, 9, 2, 2, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 2, 1, 12, 9, 2, 12, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 2, 1, 3, 2, 2, 11, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 9, 1, 3, 9, 2, 11, false, paramRandom, n);

		a(paramWorld, paramStructureBoundingBox, 1, 3, 1, 10, 6, 1, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 1, 3, 13, 10, 6, 13, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 1, 3, 2, 1, 6, 12, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 10, 3, 2, 10, 6, 12, false, paramRandom, n);

		a(paramWorld, paramStructureBoundingBox, 2, 3, 2, 9, 3, 12, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 2, 6, 2, 9, 6, 12, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 3, 7, 3, 8, 7, 11, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 4, 8, 4, 7, 8, 10, false, paramRandom, n);

		a(paramWorld, paramStructureBoundingBox, 3, 1, 3, 8, 2, 11);
		a(paramWorld, paramStructureBoundingBox, 4, 3, 6, 7, 3, 9);
		a(paramWorld, paramStructureBoundingBox, 2, 4, 2, 9, 5, 12);
		a(paramWorld, paramStructureBoundingBox, 4, 6, 5, 7, 6, 9);
		a(paramWorld, paramStructureBoundingBox, 5, 7, 6, 6, 7, 8);

		a(paramWorld, paramStructureBoundingBox, 5, 1, 2, 6, 2, 2);
		a(paramWorld, paramStructureBoundingBox, 5, 2, 12, 6, 2, 12);
		a(paramWorld, paramStructureBoundingBox, 5, 5, 1, 6, 5, 1);
		a(paramWorld, paramStructureBoundingBox, 5, 5, 13, 6, 5, 13);
		a(paramWorld, 0, 0, 1, 5, 5, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 10, 5, 5, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 1, 5, 9, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 10, 5, 9, paramStructureBoundingBox);

		for (int i5 = 0; i5 <= 14; i5 += 14) {
			a(paramWorld, paramStructureBoundingBox, 2, 4, i5, 2, 5, i5, false, paramRandom, n);
			a(paramWorld, paramStructureBoundingBox, 4, 4, i5, 4, 5, i5, false, paramRandom, n);
			a(paramWorld, paramStructureBoundingBox, 7, 4, i5, 7, 5, i5, false, paramRandom, n);
			a(paramWorld, paramStructureBoundingBox, 9, 4, i5, 9, 5, i5, false, paramRandom, n);
		}
		a(paramWorld, paramStructureBoundingBox, 5, 6, 0, 6, 6, 0, false, paramRandom, n);
		for (i5 = 0; i5 <= 11; i5 += 11) {
			for (int i6 = 2; i6 <= 12; i6 += 2) {
				a(paramWorld, paramStructureBoundingBox, i5, 4, i6, i5, 5, i6, false, paramRandom, n);
			}
			a(paramWorld, paramStructureBoundingBox, i5, 6, 5, i5, 6, 5, false, paramRandom, n);
			a(paramWorld, paramStructureBoundingBox, i5, 6, 9, i5, 6, 9, false, paramRandom, n);
		}
		a(paramWorld, paramStructureBoundingBox, 2, 7, 2, 2, 9, 2, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 9, 7, 2, 9, 9, 2, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 2, 7, 12, 2, 9, 12, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 9, 7, 12, 9, 9, 12, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 4, 9, 4, 4, 9, 4, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 7, 9, 4, 7, 9, 4, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 4, 9, 10, 4, 9, 10, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 7, 9, 10, 7, 9, 10, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 5, 9, 7, 6, 9, 7, false, paramRandom, n);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 5, 9, 6, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 6, 9, 6, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 5, 9, 8, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 6, 9, 8, paramStructureBoundingBox);

		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 0, 0, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 5, 0, 0, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 6, 0, 0, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 0, 0, paramStructureBoundingBox);

		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 1, 8, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 2, 9, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 4, 3, 10, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 1, 8, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 2, 9, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i1, 7, 3, 10, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, 4, 1, 9, 4, 1, 9, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 7, 1, 9, 7, 1, 9, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 4, 1, 10, 7, 2, 10, false, paramRandom, n);

		a(paramWorld, paramStructureBoundingBox, 5, 4, 5, 6, 4, 5, false, paramRandom, n);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i3, 4, 4, 5, paramStructureBoundingBox);
		a(paramWorld, Block.COBBLESTONE_STAIRS.id, i4, 7, 4, 5, paramStructureBoundingBox);

		for (i5 = 0; i5 < 4; i5++) {
			a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 5, 0 - i5, 6 + i5, paramStructureBoundingBox);
			a(paramWorld, Block.COBBLESTONE_STAIRS.id, i2, 6, 0 - i5, 6 + i5, paramStructureBoundingBox);
			a(paramWorld, paramStructureBoundingBox, 5, 0 - i5, 7 + i5, 6, 0 - i5, 9 + i5);
		}

		a(paramWorld, paramStructureBoundingBox, 1, -3, 12, 10, -1, 13);
		a(paramWorld, paramStructureBoundingBox, 1, -3, 1, 3, -1, 13);
		a(paramWorld, paramStructureBoundingBox, 1, -3, 1, 9, -1, 5);
		for (i5 = 1; i5 <= 13; i5 += 2) {
			a(paramWorld, paramStructureBoundingBox, 1, -3, i5, 1, -2, i5, false, paramRandom, n);
		}
		for (i5 = 2; i5 <= 12; i5 += 2) {
			a(paramWorld, paramStructureBoundingBox, 1, -1, i5, 3, -1, i5, false, paramRandom, n);
		}
		a(paramWorld, paramStructureBoundingBox, 2, -2, 1, 5, -2, 1, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 7, -2, 1, 9, -2, 1, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 6, -3, 1, 6, -3, 1, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 6, -1, 1, 6, -1, 1, false, paramRandom, n);

		a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 3) | 0x4, 1, -3, 8, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 1) | 0x4, 4, -3, 8, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE.id, 4, 2, -3, 8, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE.id, 4, 3, -3, 8, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 7, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 6, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 5, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 4, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 3, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 2, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 5, -3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 4, -3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 3, -3, 1, paramStructureBoundingBox);
		if (!this.j) {
			this.j = a(paramWorld, paramStructureBoundingBox, paramRandom, 3, -2, 1, 2, m, 2);
		}
		a(paramWorld, Block.VINE.id, 15, 3, -2, 2, paramStructureBoundingBox);

		a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 2) | 0x4, 7, -3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE_SOURCE.id, c(Block.TRIPWIRE_SOURCE.id, 0) | 0x4, 7, -3, 5, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE.id, 4, 7, -3, 2, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE.id, 4, 7, -3, 3, paramStructureBoundingBox);
		a(paramWorld, Block.TRIPWIRE.id, 4, 7, -3, 4, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 8, -3, 6, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 9, -3, 6, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 9, -3, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 9, -3, 4, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 9, -2, 4, paramStructureBoundingBox);
		if (!this.k) {
			this.k = a(paramWorld, paramStructureBoundingBox, paramRandom, 9, -2, 3, 4, m, 2);
		}
		a(paramWorld, Block.VINE.id, 15, 8, -1, 3, paramStructureBoundingBox);
		a(paramWorld, Block.VINE.id, 15, 8, -2, 3, paramStructureBoundingBox);
		if (!this.h) {
			this.h = a(paramWorld, paramStructureBoundingBox, paramRandom, 8, -3, 3, l, 2 + paramRandom.nextInt(5));
		}
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 9, -3, 2, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 8, -3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 4, -3, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 5, -2, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 5, -1, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 6, -3, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 7, -2, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 7, -1, 5, paramStructureBoundingBox);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 8, -3, 5, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, 9, -1, 1, 9, -1, 5, false, paramRandom, n);

		a(paramWorld, paramStructureBoundingBox, 8, -3, 8, 10, -1, 10);
		a(paramWorld, Block.SMOOTH_BRICK.id, 3, 8, -2, 11, paramStructureBoundingBox);
		a(paramWorld, Block.SMOOTH_BRICK.id, 3, 9, -2, 11, paramStructureBoundingBox);
		a(paramWorld, Block.SMOOTH_BRICK.id, 3, 10, -2, 11, paramStructureBoundingBox);
		a(paramWorld, Block.LEVER.id, BlockLever.d(c(Block.LEVER.id, 2)), 8, -2, 12, paramStructureBoundingBox);
		a(paramWorld, Block.LEVER.id, BlockLever.d(c(Block.LEVER.id, 2)), 9, -2, 12, paramStructureBoundingBox);
		a(paramWorld, Block.LEVER.id, BlockLever.d(c(Block.LEVER.id, 2)), 10, -2, 12, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, 8, -3, 8, 8, -3, 10, false, paramRandom, n);
		a(paramWorld, paramStructureBoundingBox, 10, -3, 8, 10, -3, 10, false, paramRandom, n);
		a(paramWorld, Block.MOSSY_COBBLESTONE.id, 0, 10, -2, 9, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 8, -2, 9, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 8, -2, 10, paramStructureBoundingBox);
		a(paramWorld, Block.REDSTONE_WIRE.id, 0, 10, -1, 9, paramStructureBoundingBox);
		a(paramWorld, Block.PISTON_STICKY.id, 1, 9, -2, 8, paramStructureBoundingBox);
		a(paramWorld, Block.PISTON_STICKY.id, c(Block.PISTON_STICKY.id, 4), 10, -2, 8, paramStructureBoundingBox);
		a(paramWorld, Block.PISTON_STICKY.id, c(Block.PISTON_STICKY.id, 4), 10, -1, 8, paramStructureBoundingBox);
		a(paramWorld, Block.DIODE_OFF.id, c(Block.DIODE_OFF.id, 2), 10, -2, 10, paramStructureBoundingBox);
		if (!this.i) {
			this.i = a(paramWorld, paramStructureBoundingBox, paramRandom, 9, -3, 10, l, 2 + paramRandom.nextInt(5));
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenJungleTemple
 * JD-Core Version:		0.6.0
 */