package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageLight extends WorldGenVillagePiece
{
	private int a = -1;

	public WorldGenVillageLight(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2) {
		super(paramWorldGenVillageStartPiece, paramInt1);

		this.f = paramInt2;
		this.e = paramStructureBoundingBox;
	}

	public static StructureBoundingBox a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 3, 4, 2, paramInt4);

		if (StructurePiece.a(paramList, localStructureBoundingBox) != null) {
			return null;
		}

		return localStructureBoundingBox;
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (this.a < 0) {
			this.a = b(paramWorld, paramStructureBoundingBox);
			if (this.a < 0) {
				return true;
			}
			this.e.a(0, this.a - this.e.e + 4 - 1, 0);
		}

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 2, 3, 1, 0, 0, false);

		a(paramWorld, Block.FENCE.id, 0, 1, 0, 0, paramStructureBoundingBox);
		a(paramWorld, Block.FENCE.id, 0, 1, 1, 0, paramStructureBoundingBox);
		a(paramWorld, Block.FENCE.id, 0, 1, 2, 0, paramStructureBoundingBox);

		a(paramWorld, Block.WOOL.id, 15, 1, 3, 0, paramStructureBoundingBox);

		a(paramWorld, Block.TORCH.id, 15, 0, 3, 0, paramStructureBoundingBox);
		a(paramWorld, Block.TORCH.id, 15, 1, 3, 1, paramStructureBoundingBox);
		a(paramWorld, Block.TORCH.id, 15, 2, 3, 0, paramStructureBoundingBox);
		a(paramWorld, Block.TORCH.id, 15, 1, 3, -1, paramStructureBoundingBox);

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillageLight
 * JD-Core Version:		0.6.0
 */