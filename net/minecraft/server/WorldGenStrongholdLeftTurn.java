package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdLeftTurn extends WorldGenStrongholdPiece
{
	protected final WorldGenStrongholdDoorType a;

	public WorldGenStrongholdLeftTurn(int paramInt1, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, int paramInt2)
	{
		super(paramInt1);

		this.f = paramInt2;
		this.a = a(paramRandom);
		this.e = paramStructureBoundingBox;
	}

	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		if ((this.f == 2) || (this.f == 3))
			b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
		else
			c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
	}

	public static WorldGenStrongholdLeftTurn a(List paramList, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		StructureBoundingBox localStructureBoundingBox = StructureBoundingBox.a(paramInt1, paramInt2, paramInt3, -1, -1, 0, 5, 5, 5, paramInt4);

		if ((!a(localStructureBoundingBox)) || (StructurePiece.a(paramList, localStructureBoundingBox) != null)) {
			return null;
		}

		return new WorldGenStrongholdLeftTurn(paramInt5, paramRandom, localStructureBoundingBox, paramInt4);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (a(paramWorld, paramStructureBoundingBox)) {
			return false;
		}

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 4, true, paramRandom, WorldGenStrongholdPieces.b());

		a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);

		if ((this.f == 2) || (this.f == 3))
			a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, 0, 0, false);
		else {
			a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 3, 0, 0, false);
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdLeftTurn
 * JD-Core Version:		0.6.0
 */