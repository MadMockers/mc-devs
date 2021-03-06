package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece9 extends WorldGenNetherPiece
{
	public WorldGenNetherPiece9(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramInt1);

		this.f = paramInt2;
		this.e = paramStructureBoundingBox;
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		a((WorldGenNetherPiece15)paramStructurePiece, paramList, paramRandom, 1, 0, true);
	}

	public static WorldGenNetherPiece9 a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, 0, 0, 5, 7, 5, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenNetherPiece9(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);

		a(paramWorld, paramStructureBoundingBox, 0, 2, 0, 0, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 4, 2, 0, 4, 5, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);
		a(paramWorld, paramStructureBoundingBox, 0, 3, 1, 0, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 0, 3, 3, 0, 4, 3, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 4, 3, 1, 4, 4, 1, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 4, 3, 3, 4, 4, 3, Block.NETHER_FENCE.id, Block.NETHER_FENCE.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.NETHER_BRICK.id, Block.NETHER_BRICK.id, false);

		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				b(paramWorld, Block.NETHER_BRICK.id, 0, i, -1, j, paramStructureBoundingBox);
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenNetherPiece9
 * JD-Core Version:		0.6.0
 */