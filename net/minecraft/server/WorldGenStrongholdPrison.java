package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPrison extends WorldGenStrongholdPiece
{
	protected final WorldGenStrongholdDoorType a;

	public WorldGenStrongholdPrison(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
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

	public static WorldGenStrongholdPrison a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 9, 5, 11, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenStrongholdPrison(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (a(paramWorld, paramStructureBoundingBox)) {
			return false;
		}

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 8, 4, 10, true, paramRandom, WorldGenStrongholdPieces.b());

		a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);

		a(paramWorld, paramStructureBoundingBox, 1, 1, 10, 3, 3, 10, 0, 0, false);

		a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 1, false, paramRandom, WorldGenStrongholdPieces.b());
		a(paramWorld, paramStructureBoundingBox, 4, 1, 3, 4, 3, 3, false, paramRandom, WorldGenStrongholdPieces.b());
		a(paramWorld, paramStructureBoundingBox, 4, 1, 7, 4, 3, 7, false, paramRandom, WorldGenStrongholdPieces.b());
		a(paramWorld, paramStructureBoundingBox, 4, 1, 9, 4, 3, 9, false, paramRandom, WorldGenStrongholdPieces.b());

		a(paramWorld, paramStructureBoundingBox, 4, 1, 4, 4, 3, 6, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);
		a(paramWorld, paramStructureBoundingBox, 5, 1, 5, 7, 3, 5, Block.IRON_FENCE.id, Block.IRON_FENCE.id, false);

		a(paramWorld, Block.IRON_FENCE.id, 0, 4, 3, 2, paramStructureBoundingBox);
		a(paramWorld, Block.IRON_FENCE.id, 0, 4, 3, 8, paramStructureBoundingBox);
		a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 2, paramStructureBoundingBox);
		a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 2, paramStructureBoundingBox);
		a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3), 4, 1, 8, paramStructureBoundingBox);
		a(paramWorld, Block.IRON_DOOR_BLOCK.id, c(Block.IRON_DOOR_BLOCK.id, 3) + 8, 4, 2, 8, paramStructureBoundingBox);

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdPrison
 * JD-Core Version:		0.6.0
 */