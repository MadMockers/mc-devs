package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece4 extends WorldGenNetherPiece
{
	public WorldGenNetherPiece4(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramInt1);

		this.f = paramInt2;
		this.e = paramStructureBoundingBox;
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 1, 0, true);
	}

	public static WorldGenNetherPiece4 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -7, 0, 5, 14, 10, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenNetherPiece4(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = c(Block.NETHER_BRICK_STAIRS.id, 2);
		for (int j = 0; j <= 9; j++) {
			int k = Math.max(1, 7 - j);
			int m = Math.min(Math.max(k + 5, 14 - j), 13);
			int n = j;

			a(paramWorld, paramStructureBoundingBox, 0, 0, n, 4, k, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

			a(paramWorld, paramStructureBoundingBox, 1, k + 1, n, 3, m - 1, n, 0, 0, false);
			if (j <= 6) {
				a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, 1, k + 1, n, paramStructureBoundingBox);
				a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, 2, k + 1, n, paramStructureBoundingBox);
				a(paramWorld, Block.NETHER_BRICK_STAIRS.id, i, 3, k + 1, n, paramStructureBoundingBox);
			}

			a(paramWorld, paramStructureBoundingBox, 0, m, n, 4, m, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

			a(paramWorld, paramStructureBoundingBox, 0, k + 1, n, 0, m - 1, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
			a(paramWorld, paramStructureBoundingBox, 4, k + 1, n, 4, m - 1, n, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
			if ((j & 0x1) == 0) {
				a(paramWorld, paramStructureBoundingBox, 0, k + 2, n, 0, k + 3, n, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
				a(paramWorld, paramStructureBoundingBox, 4, k + 2, n, 4, k + 3, n, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
			}

			for (int i1 = 0; i1 <= 4; i1++) {
				b(paramWorld, Block.NETHER_BRICK.id, 0, i1, -1, n, paramStructureBoundingBox);
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece4
 * JD-Core Version:		0.6.0
 */