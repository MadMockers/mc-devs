package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece5 extends WorldGenNetherPiece
{
	public WorldGenNetherPiece5(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramInt1);

		this.f = paramInt2;
		this.e = paramStructureBoundingBox;
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		int i = 1;

		if ((this.f == 1) || (this.f == 2)) {
			i = 5;
		}

		b((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, i, paramRandom.nextInt(8) > 0);
		c((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 0, i, paramRandom.nextInt(8) > 0);
	}

	public static WorldGenNetherPiece5 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -3, 0, 0, 9, 7, 9, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenNetherPiece5(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 8, 1, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 8, 5, 8, 0, 0, false);

		a(paramWorld, paramStructureBoundingBox, 0, 6, 0, 8, 6, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 2, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 6, 2, 0, 8, 5, 0, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 3, 0, 1, 4, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 7, 3, 0, 7, 4, 0, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 2, 4, 8, 2, 8, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 1, 4, 2, 2, 4, 0, 0, false);
		a(paramWorld, paramStructureBoundingBox, 6, 1, 4, 7, 2, 4, 0, 0, false);

		a(paramWorld, paramStructureBoundingBox, 0, 3, 8, 8, 3, 8, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 0, 3, 6, 0, 3, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 8, 3, 6, 8, 3, 7, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 3, 4, 0, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 8, 3, 4, 8, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 3, 5, 2, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 6, 3, 5, 7, 5, 5, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 4, 5, 1, 5, 5, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 7, 4, 5, 7, 5, 5, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);

		for (int i = 0; i <= 5; i++) {
			for (int j = 0; j <= 8; j++) {
				b(paramWorld, Block.NETHER_BRICK.id, 0, j, -1, i, paramStructureBoundingBox);
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece5
 * JD-Core Version:		0.6.0
 */