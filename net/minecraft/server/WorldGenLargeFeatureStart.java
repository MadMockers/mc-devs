package net.minecraft.server;

import java.util.LinkedList;
import java.util.Random;

public class WorldGenLargeFeatureStart extends StructureStart
{
	public WorldGenLargeFeatureStart(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
	{
		Object localObject;
		if (paramWorld.getBiome(paramInt1 * 16 + 8, paramInt2 * 16 + 8) == BiomeBase.JUNGLE) {
			localObject = new WorldGenJungleTemple(paramRandom, paramInt1 * 16, paramInt2 * 16);
			this.a.add(localObject);
		}
		else {
			localObject = new WorldGenPyramidPiece(paramRandom, paramInt1 * 16, paramInt2 * 16);
			this.a.add(localObject);
		}

		c();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenLargeFeatureStart
 * JD-Core Version:		0.6.0
 */