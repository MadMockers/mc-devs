package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdRightTurn extends WorldGenStrongholdLeftTurn
{
	public void a(StructurePiece paramStructurePiece, List paramList, Random paramRandom)
	{
		if ((this.f == 2) || (this.f == 3))
			c((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
		else
			b((WorldGenStrongholdStart)paramStructurePiece, paramList, paramRandom, 1, 1);
	}

	public boolean a(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox)
	{
		if (a(paramWorld, paramStructureBoundingBox)) {
			return false;
		}

		a(paramWorld, paramStructureBoundingBox, 0, 0, 0, 4, 4, 4, true, paramRandom, WorldGenStrongholdPieces.b());

		a(paramWorld, paramRandom, paramStructureBoundingBox, this.a, 1, 1, 0);

		if ((this.f == 2) || (this.f == 3))
			a(paramWorld, paramStructureBoundingBox, 4, 1, 1, 4, 3, 3, 0, 0, false);
		else {
			a(paramWorld, paramStructureBoundingBox, 0, 1, 1, 0, 3, 3, 0, 0, false);
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdRightTurn
 * JD-Core Version:		0.6.0
 */