package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHut extends WorldGenVillagePiece
{
	private int a = -1;
	private final boolean b;
	private final int c;

	public WorldGenVillageHut(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramWorldGenVillageStartPiece, paramInt1);

		this.f = paramInt2;
		this.e = paramStructureBoundingBox;
		this.b = paramRandom.nextBoolean();
		this.c = paramRandom.nextInt(3);
	}

	public static WorldGenVillageHut a(WorldGenVillageStartPiece paramWorldGenVillageStartPiece, List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, 0, 0, 0, 4, 6, 5, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenVillageHut(paramWorldGenVillageStartPiece, paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (this.a < 0) {
			this.a = b(paramWorld, paramStructureBoundingBox);
			if (this.a < 0) {
				return true;
			}
			this.e.a(0, this.a - this.e.e + 6 - 1, 0);
		}

		a(paramWorld, paramStructureBoundingBox, 1, 1, 1, 3, 5, 4, 0, 0, false);

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 3, 0, 4, Block.COBBLESTONE.id, Block.COBBLESTONE.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 0, 1, 2, 0, 3, Block.DIRT.id, Block.DIRT.id, false);

		if (this.b)
			a(paramWorld, paramStructureBoundingBox, 1, 4, 1, 2, 4, 3, Block.LOG.id, Block.LOG.id, false);
		else {
			a(paramWorld, paramStructureBoundingBox, 1, 5, 1, 2, 5, 3, Block.LOG.id, Block.LOG.id, false);
		}
		a(paramWorld, Block.LOG.id, 0, 1, 4, 0, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 2, 4, 0, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 1, 4, 4, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 2, 4, 4, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 0, 4, 1, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 0, 4, 2, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 0, 4, 3, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 3, 4, 1, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 3, 4, 2, paramStructureBoundingBox);
		a(paramWorld, Block.LOG.id, 0, 3, 4, 3, paramStructureBoundingBox);

		a(paramWorld, paramStructureBoundingBox, 0, 1, 0, 0, 3, 0, Block.LOG.id, Block.LOG.id, false);
		a(paramWorld, paramStructureBoundingBox, 3, 1, 0, 3, 3, 0, Block.LOG.id, Block.LOG.id, false);
		a(paramWorld, paramStructureBoundingBox, 0, 1, 4, 0, 3, 4, Block.LOG.id, Block.LOG.id, false);
		a(paramWorld, paramStructureBoundingBox, 3, 1, 4, 3, 3, 4, Block.LOG.id, Block.LOG.id, false);

		a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
		a(paramWorld, paramStructureBoundingBox, 3, 1, 1, 3, 3, 3, Block.WOOD.id, Block.WOOD.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 1, 0, 2, 3, 0, Block.WOOD.id, Block.WOOD.id, false);
		a(paramWorld, paramStructureBoundingBox, 1, 1, 4, 2, 3, 4, Block.WOOD.id, Block.WOOD.id, false);

		a(paramWorld, Block.THIN_GLASS.id, 0, 0, 2, 2, paramStructureBoundingBox);
		a(paramWorld, Block.THIN_GLASS.id, 0, 3, 2, 2, paramStructureBoundingBox);

		if (this.c > 0) {
			a(paramWorld, Block.FENCE.id, 0, this.c, 1, 3, paramStructureBoundingBox);
			a(paramWorld, Block.WOOD_PLATE.id, 0, this.c, 2, 3, paramStructureBoundingBox);
		}

		a(paramWorld, 0, 0, 1, 1, 0, paramStructureBoundingBox);
		a(paramWorld, 0, 0, 1, 2, 0, paramStructureBoundingBox);
		a(paramWorld, paramStructureBoundingBox, paramRandom, 1, 1, 0, c(Block.WOODEN_DOOR.id, 1));
		if ((a(paramWorld, 1, 0, -1, paramStructureBoundingBox) == 0) && (a(paramWorld, 1, -1, -1, paramStructureBoundingBox) != 0)) {
			a(paramWorld, Block.COBBLESTONE_STAIRS.id, c(Block.COBBLESTONE_STAIRS.id, 3), 1, 0, -1, paramStructureBoundingBox);
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				b(paramWorld, j, 6, i, paramStructureBoundingBox);
				b(paramWorld, Block.COBBLESTONE.id, 0, j, -1, i, paramStructureBoundingBox);
			}
		}

		a(paramWorld, paramStructureBoundingBox, 1, 1, 2, 1);

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillageHut
 * JD-Core Version:		0.6.0
 */