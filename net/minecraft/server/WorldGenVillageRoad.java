package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageRoad extends WorldGenVillageRoadPiece
{
	private int a;

	public WorldGenVillageRoad(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramWorldGenVillageStartPiece, paramInt1);

		this.f = paramInt2;
		this.e = paramStructureBoundingBox;
		this.a = Math.max(paramStructureBoundingBox.b(), paramStructureBoundingBox.d());
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		int i = 0;

		int j = paramRandom.nextInt(5);
		StructurePiece localStructurePiece;
		while (j < this.a - 8) {
			localStructurePiece = a((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, 0, j);
			if (localStructurePiece != null) {
				j += Math.max(localStructurePiece.e.b(), localStructurePiece.e.d());
				i = 1;
			}
			j += 2 + paramRandom.nextInt(5);
		}

		j = paramRandom.nextInt(5);
		while (j < this.a - 8) {
			localStructurePiece = b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, 0, j);
			if (localStructurePiece != null) {
				j += Math.max(localStructurePiece.e.b(), localStructurePiece.e.d());
				i = 1;
			}
			j += 2 + paramRandom.nextInt(5);
		}

		if ((i != 0) && (paramRandom.nextInt(3) > 0)) {
			switch (this.f) {
			case 2:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.c, 1, c());
				break;
			case 0:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a - 1, this.e.b, this.e.f - 2, 1, c());
				break;
			case 3:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d - 2, this.e.b, this.e.c - 1, 2, c());
				break;
			case 1:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b, this.e.c - 1, 2, c());
			}
		}

		if ((i != 0) && (paramRandom.nextInt(3) > 0))
			switch (this.f) {
			case 2:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.c, 3, c());
				break;
			case 0:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d + 1, this.e.b, this.e.f - 2, 3, c());
				break;
			case 3:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.d - 2, this.e.b, this.e.f + 1, 0, c());
				break;
			case 1:
				WorldGenVillagePieces.b((WorldGenVillageStartPiece)paramStructurePiece, paramList, paramRandom, this.e.a, this.e.b, this.e.f + 1, 0, c());
			}
	}

	public static StructureBoundingBox a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = 7 * MathHelper.a(paramRandom, 3, 5);

		while (i >= 7) {
			StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 3, 3, i, paramInt4);

			if (StructurePiece.a(paramList, localStructureBoundingBox) == null) {
				return localStructureBoundingBox;
			}
			i -= 7;
		}

		return null;
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = d(Block.GRAVEL.id, 0);
		for (int j = this.e.a; j <= this.e.d; j++) {
			for (int k = this.e.c; k <= this.e.f; k++) {
				if (paramStructureBoundingBox.b(j, 64, k)) {
					int m = paramWorld.h(j, k) - 1;
					paramWorld.setRawTypeId(j, m, k, i);
				}
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillageRoad
 * JD-Core Version:		0.6.0
 */