package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStairs extends WorldGenStrongholdPiece
{
	private final WorldGenStrongholdDoorType a;
	private final boolean b;
	private final boolean c;

	public WorldGenStrongholdStairs(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramInt1);

		this.f = paramInt2;
		this.a = a(paramRandom);
		this.e = paramStructureBoundingBox;

		this.b = (paramRandom.nextInt(2) == 0);
		this.c = (paramRandom.nextInt(2) == 0);
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		a((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
		if (this.b) b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 2);
		if (this.c) c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 2);
	}

	public static WorldGenStrongholdStairs a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, 7, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenStrongholdStairs(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (a(paramWorld, paramStructureBoundingBox)) {
			return false;
		}

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 6, true, paramRandom, WorldGenStrongholdPieces.b());

		a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);

		a(paramWorld, paramRandom, paramStructureBoundingBox, WorldGenStrongholdDoorType.a, 1, 1, 6);

		a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 1, 2, 1, Block.TORCH.id, 0);
		a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 3, 2, 1, Block.TORCH.id, 0);
		a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 1, 2, 5, Block.TORCH.id, 0);
		a(paramWorld, paramStructureBoundingBox, paramRandom, 0.1F, 3, 2, 5, Block.TORCH.id, 0);

		if (this.b) {
			a(paramWorld, paramStructureBoundingBox, 0, 1, 2, 0, 3, 4, 0, 0, false);
		}
		if (this.c) {
			a(paramWorld, paramStructureBoundingBox, 4, 1, 2, 4, 3, 4, 0, 0, false);
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdStairs
 * JD-Core Version:		0.6.0
 */