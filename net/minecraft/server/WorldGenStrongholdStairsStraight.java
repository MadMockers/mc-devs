package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStairsStraight extends WorldGenStrongholdPiece
{
	private final WorldGenStrongholdDoorType a;

	public WorldGenStrongholdStairsStraight(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramInt1);

		this.f = paramInt2;
		this.a = a(paramRandom);
		this.e = paramStructureBoundingBox;
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
	}

	public static WorldGenStrongholdStairsStraight a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -7, 0, 5, 11, 8, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenStrongholdStairsStraight(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (a(paramWorld, paramStructureBoundingBox)) {
			return false;
		}

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 10, 7, true, paramRandom, WorldGenStrongholdPieces.b());

		a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 7, 0);

		a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.a, 1, 1, 7);

		int i = c(Block.COBBLESTONE_STAIRS.id, 2);
		for (int j = 0; j < 6; j++) {
			a(paramWorld, Block.COBBLESTONE_STAIRS.id, i, 1, 6 - j, 1 + j, paramStructureBoundingBox);
			a(paramWorld, Block.COBBLESTONE_STAIRS.id, i, 2, 6 - j, 1 + j, paramStructureBoundingBox);
			a(paramWorld, Block.COBBLESTONE_STAIRS.id, i, 3, 6 - j, 1 + j, paramStructureBoundingBox);
			if (j < 5) {
				a(paramWorld, Block.SMOOTH_BRICK.id, 0, 1, 5 - j, 1 + j, paramStructureBoundingBox);
				a(paramWorld, Block.SMOOTH_BRICK.id, 0, 2, 5 - j, 1 + j, paramStructureBoundingBox);
				a(paramWorld, Block.SMOOTH_BRICK.id, 0, 3, 5 - j, 1 + j, paramStructureBoundingBox);
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStairsStraight
 * JD-Core Version:		0.6.0
 */