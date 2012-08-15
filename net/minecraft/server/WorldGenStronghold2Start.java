package net.minecraft.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

class WorldGenStronghold2Start extends StructureStart
{
	public WorldGenStronghold2Start(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
	{
		WorldGenStrongholdPieces.a();

		WorldGenStrongholdStart localWorldGenStrongholdStart = new WorldGenStrongholdStart(0, paramRandom, (paramInt1 << 4) + 2, (paramInt2 << 4) + 2);
		this.a.add(localWorldGenStrongholdStart);
		localWorldGenStrongholdStart.a(localWorldGenStrongholdStart, this.a, paramRandom);

		ArrayList localArrayList = localWorldGenStrongholdStart.c;
		while (!localArrayList.isEmpty()) {
			int i = paramRandom.nextInt(localArrayList.size());
			StructurePiece localStructurePiece = (StructurePiece)localArrayList.remove(i);
			localStructurePiece.a(localWorldGenStrongholdStart, this.a, paramRandom);
		}

		c();
		a(paramWorld, paramRandom, 10);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenStronghold2Start
 * JD-Core Version:		0.6.0
 */