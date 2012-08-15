package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenVillagePiece extends StructurePiece
{
	private int a;
	protected WorldGenVillageStartPiece k;

	protected WorldGenVillagePiece(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt)
	{
		super(paramInt);
		this.k = paramWorldGenVillageStartPiece;
	}

	protected StructurePiece a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2) {
		switch (this.f) {
		case 2:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c());
		case 0:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a - 1, this.e.b + paramInt1, this.e.c + paramInt2, 1, c());
		case 1:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c());
		case 3:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.c - 1, 2, c());
		}
		return null;
	}

	protected StructurePiece b(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2) {
		switch (this.f) {
		case 2:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c());
		case 0:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.d + 1, this.e.b + paramInt1, this.e.c + paramInt2, 3, c());
		case 1:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c());
		case 3:
			return WorldGenVillagePieces.a(paramWorldGenVillageStartPiece, paramList, paramRandom, this.e.a + paramInt2, this.e.b + paramInt1, this.e.f + 1, 0, c());
		}
		return null;
	}

	protected int b(World paramWorld, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = 0;
		int j = 0;
		for (int m = this.e.c; m <= this.e.f; m++) {
			for (int n = this.e.a; n <= this.e.d; n++) {
				if (paramStructureBoundingBox.b(n, 64, m)) {
					i += Math.max(paramWorld.h(n, m), paramWorld.worldProvider.getSeaLevel());
					j++;
				}
			}
		}

		if (j == 0) {
			return -1;
		}
		return i / j;
	}

	protected static boolean a(StructureBoundingBox paramStructureBoundingBox) {
		return (paramStructureBoundingBox != null) && (paramStructureBoundingBox.b > 10);
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (this.a >= paramInt4) {
			return;
		}

		for (int i = this.a; i < paramInt4; i++) {
			int j = a(paramInt1 + i, paramInt3);
			int m = a(paramInt2);
			int n = b(paramInt1 + i, paramInt3);

			if (!paramStructureBoundingBox.b(j, m, n)) break;
			this.a += 1;

			EntityVillager localEntityVillager = new EntityVillager(paramWorld, b(i));
			localEntityVillager.setPositionRotation(j + 0.5D, m, n + 0.5D, 0.0F, 0.0F);
			paramWorld.addEntity(localEntityVillager);
		}
	}

	protected int b(int paramInt)
	{
		return 0;
	}

	protected int d(int paramInt1, int paramInt2) {
		if (this.k.b) {
			if (paramInt1 == Block.LOG.id)
				return Block.SANDSTONE.id;
			if (paramInt1 == Block.COBBLESTONE.id)
				return Block.SANDSTONE.id;
			if (paramInt1 == Block.WOOD.id)
				return Block.SANDSTONE.id;
			if (paramInt1 == Block.WOOD_STAIRS.id)
				return Block.SANDSTONE_STAIRS.id;
			if (paramInt1 == Block.COBBLESTONE_STAIRS.id)
				return Block.SANDSTONE_STAIRS.id;
			if (paramInt1 == Block.GRAVEL.id) {
				return Block.SANDSTONE.id;
			}
		}
		return paramInt1;
	}

	protected int e(int paramInt1, int paramInt2) {
		if (this.k.b) {
			if (paramInt1 == Block.LOG.id)
				return 0;
			if (paramInt1 == Block.COBBLESTONE.id)
				return 0;
			if (paramInt1 == Block.WOOD.id) {
				return 2;
			}
		}
		return paramInt2;
	}

	protected void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = d(paramInt1, paramInt2);
		int j = e(paramInt1, paramInt2);
		super.a(paramWorld, i, j, paramInt3, paramInt4, paramInt5, paramStructureBoundingBox);
	}

	protected void a(World paramWorld, StructureBoundingBox paramStructureBoundingBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
	{
		int i = d(paramInt7, 0);
		int j = e(paramInt7, 0);
		int m = d(paramInt8, 0);
		int n = e(paramInt8, 0);
		super.a(paramWorld, paramStructureBoundingBox, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, i, j, m, n, paramBoolean);
	}

	protected void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, StructureBoundingBox paramStructureBoundingBox)
	{
		int i = d(paramInt1, paramInt2);
		int j = e(paramInt1, paramInt2);
		super.b(paramWorld, i, j, paramInt3, paramInt4, paramInt5, paramStructureBoundingBox);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillagePiece
 * JD-Core Version:		0.6.0
 */