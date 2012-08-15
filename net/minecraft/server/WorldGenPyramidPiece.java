package net.minecraft.server;

import java.util.Random;

public class WorldGenPyramidPiece extends WorldGenScatteredPiece
{
	private boolean[] h = new boolean[4];

	private static final StructurePieceTreasure[] i = { new StructurePieceTreasure(Item.DIAMOND.id, 0, 1, 3, 3), new StructurePieceTreasure(Item.IRON_INGOT.id, 0, 1, 5, 10), new StructurePieceTreasure(Item.GOLD_INGOT.id, 0, 2, 7, 15), new StructurePieceTreasure(Item.EMERALD.id, 0, 1, 3, 2), new StructurePieceTreasure(Item.BONE.id, 0, 4, 6, 20), new StructurePieceTreasure(Item.ROTTEN_FLESH.id, 0, 3, 7, 16) };

	public WorldGenPyramidPiece(Random paramRandom, int paramInt1, int paramInt2)
	{
		super(paramRandom, paramInt1, 64, paramInt2, 21, 15, 21);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		a(paramWorld, paramStructureBoundingBox, 0, -4, 0, this.a - 1, 0, this.c - 1, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		for (int j = 1; j <= 9; j++) {
			a(paramWorld, paramStructureBoundingBox, j, j, j, this.a - 1 - j, j, this.c - 1 - j, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
			a(paramWorld, paramStructureBoundingBox, j + 1, j, j + 1, this.a - 2 - j, j, this.c - 2 - j, 0, 0, false);
		}
		for (j = 0; j < this.a; j++) {
			for (k = 0; k < this.c; k++) {
				b(paramWorld, Block.SANDSTONE.id, 0, j, -5, k, paramStructureBoundingBox);
			}
		}

		j = c(Block.SANDSTONE_STAIRS.id, 3);
		int k = c(Block.SANDSTONE_STAIRS.id, 2);
		int m = c(Block.SANDSTONE_STAIRS.id, 0);
		int n = c(Block.SANDSTONE_STAIRS.id, 1);
		int i1 = 1;
		int i2 = 11;

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 9, 4, Block.SANDSTONE.id, 0, false);
		a(paramWorld, paramStructureBoundingBox, 1, 10, 1, 3, 10, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, j, 2, 10, 0, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, k, 2, 10, 4, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, m, 0, 10, 2, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, n, 4, 10, 2, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, this.a - 5, 0, 0, this.a - 1, 9, 4, Block.SANDSTONE.id, 0, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 4, 10, 1, this.a - 2, 10, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 10, 0, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, k, this.a - 3, 10, 4, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, m, this.a - 5, 10, 2, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, n, this.a - 1, 10, 2, paramStructureBoundingBox);

		a(paramWorld, paramStructureBoundingBox, 8, 0, 0, 12, 4, 4, Block.SANDSTONE.id, 0, false);
		a(paramWorld, paramStructureBoundingBox, 9, 1, 0, 11, 3, 4, 0, 0, false);
		a(paramWorld, Block.SANDSTONE.id, 2, 9, 1, 1, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 9, 2, 1, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 9, 3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 10, 3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 11, 3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 11, 2, 1, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 11, 1, 1, paramStructureBoundingBox);

		a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 8, 3, 3, Block.SANDSTONE.id, 0, false);
		a(paramWorld, paramStructureBoundingBox, 4, 1, 2, 8, 2, 2, 0, 0, false);
		a(paramWorld, paramStructureBoundingBox, 12, 1, 1, 16, 3, 3, Block.SANDSTONE.id, 0, false);
		a(paramWorld, paramStructureBoundingBox, 12, 1, 2, 16, 2, 2, 0, 0, false);

		a(paramWorld, paramStructureBoundingBox, 5, 4, 5, this.a - 6, 4, this.c - 6, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, 9, 4, 9, 11, 4, 11, 0, 0, false);
		a(paramWorld, paramStructureBoundingBox, 8, 1, 8, 8, 3, 8, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, paramStructureBoundingBox, 12, 1, 8, 12, 3, 8, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, paramStructureBoundingBox, 8, 1, 12, 8, 3, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, paramStructureBoundingBox, 12, 1, 12, 12, 3, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);

		a(paramWorld, paramStructureBoundingBox, 1, 1, 5, 4, 4, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 5, 1, 5, this.a - 2, 4, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, 6, 7, 9, 6, 7, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 7, 7, 9, this.a - 7, 7, 11, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, 5, 5, 9, 5, 7, 11, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 6, 5, 9, this.a - 6, 7, 11, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, 0, 0, 5, 5, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 5, 6, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 6, 6, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, this.a - 6, 5, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, this.a - 6, 6, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, this.a - 7, 6, 10, paramStructureBoundingBox);

		a(paramWorld, paramStructureBoundingBox, 2, 4, 4, 2, 6, 4, 0, 0, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 3, 4, 4, this.a - 3, 6, 4, 0, 0, false);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, j, 2, 4, 5, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, j, 2, 3, 4, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 4, 5, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, j, this.a - 3, 3, 4, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, 1, 1, 3, 2, 2, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 3, 1, 3, this.a - 2, 2, 3, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, 0, 1, 1, 2, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, 0, this.a - 2, 1, 2, paramStructureBoundingBox);
		a(paramWorld, Block.STEP.id, 1, 1, 2, 2, paramStructureBoundingBox);
		a(paramWorld, Block.STEP.id, 1, this.a - 2, 2, 2, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, n, 2, 1, 2, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE_STAIRS.id, m, this.a - 3, 1, 2, paramStructureBoundingBox);

		a(paramWorld, paramStructureBoundingBox, 4, 3, 5, 4, 3, 18, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 5, 3, 5, this.a - 5, 3, 17, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, 3, 1, 5, 4, 2, 16, 0, 0, false);
		a(paramWorld, paramStructureBoundingBox, this.a - 6, 1, 5, this.a - 5, 2, 16, 0, 0, false);
		for (int i3 = 5; i3 <= 17; i3 += 2) {
			a(paramWorld, Block.SANDSTONE.id, 2, 4, 1, i3, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 1, 4, 2, i3, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, this.a - 5, 1, i3, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 1, this.a - 5, 2, i3, paramStructureBoundingBox);
		}
		a(paramWorld, Block.WOOL.id, i1, 10, 0, 7, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 10, 0, 8, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 9, 0, 9, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 11, 0, 9, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 8, 0, 10, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 12, 0, 10, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 7, 0, 10, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 13, 0, 10, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 9, 0, 11, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 11, 0, 11, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 10, 0, 12, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 10, 0, 13, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i2, 10, 0, 10, paramStructureBoundingBox);

		for (i3 = 0; i3 <= this.a - 1; i3 += this.a - 1) {
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 2, 1, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 2, 2, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 2, 3, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 3, 1, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 3, 2, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 3, 3, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 4, 1, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 1, i3, 4, 2, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 4, 3, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 5, 1, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 5, 2, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 5, 3, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 6, 1, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 1, i3, 6, 2, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 6, 3, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 7, 1, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 7, 2, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 7, 3, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 1, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 2, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 3, paramStructureBoundingBox);
		}
		for (i3 = 2; i3 <= this.a - 3; i3 += this.a - 3 - 2) {
			a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 2, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 2, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 2, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 3, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 3, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 3, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3 - 1, 4, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 1, i3, 4, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3 + 1, 4, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 5, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 5, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 5, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3 - 1, 6, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 1, i3, 6, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3 + 1, 6, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3 - 1, 7, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3, 7, 0, paramStructureBoundingBox);
			a(paramWorld, Block.WOOL.id, i1, i3 + 1, 7, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 - 1, 8, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3, 8, 0, paramStructureBoundingBox);
			a(paramWorld, Block.SANDSTONE.id, 2, i3 + 1, 8, 0, paramStructureBoundingBox);
		}
		a(paramWorld, paramStructureBoundingBox, 8, 4, 0, 12, 6, 0, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, 0, 0, 8, 6, 0, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 12, 6, 0, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 9, 5, 0, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 1, 10, 5, 0, paramStructureBoundingBox);
		a(paramWorld, Block.WOOL.id, i1, 11, 5, 0, paramStructureBoundingBox);

		a(paramWorld, paramStructureBoundingBox, 8, -14, 8, 12, -11, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, paramStructureBoundingBox, 8, -10, 8, 12, -10, 12, Block.SANDSTONE.id, 1, Block.SANDSTONE.id, 1, false);
		a(paramWorld, paramStructureBoundingBox, 8, -9, 8, 12, -9, 12, Block.SANDSTONE.id, 2, Block.SANDSTONE.id, 2, false);
		a(paramWorld, paramStructureBoundingBox, 8, -8, 8, 12, -1, 12, Block.SANDSTONE.id, Block.SANDSTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, 9, -11, 9, 11, -1, 11, 0, 0, false);
		a(paramWorld, Block.STONE_PLATE.id, 0, 10, -11, 10, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, 9, -13, 9, 11, -13, 11, Block.TNT.id, 0, false);
		a(paramWorld, 0, 0, 8, -11, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 8, -10, 10, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 1, 7, -10, 10, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 7, -11, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 12, -11, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 12, -10, 10, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 1, 13, -10, 10, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 13, -11, 10, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 10, -11, 8, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 10, -10, 8, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 1, 10, -10, 7, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 10, -11, 7, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 10, -11, 12, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 10, -10, 12, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 1, 10, -10, 13, paramStructureBoundingBox);
		a(paramWorld, Block.SANDSTONE.id, 2, 10, -11, 13, paramStructureBoundingBox);

		for (i3 = 0; i3 < 4; i3++) {
			if (this.h[i3] == 0) {
				int i4 = Direction.a[i3] * 2;
				int i5 = Direction.b[i3] * 2;
				this.h[i3] = a(paramWorld, paramStructureBoundingBox, paramRandom, 10 + i4, -11, 10 + i5, i, 2 + paramRandom.nextInt(5));
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenPyramidPiece
 * JD-Core Version:		0.6.0
 */