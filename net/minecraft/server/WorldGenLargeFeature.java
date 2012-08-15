package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenLargeFeature extends StructureGenerator
{
	private static List e = Arrays.asList(new BiomeBase[] { BiomeBase.DESERT, BiomeBase.DESERT_HILLS, BiomeBase.JUNGLE });

	protected boolean a(int paramInt1, int paramInt2)
	{
		int i = 32;
		int j = 8;

		int k = paramInt1;
		int m = paramInt2;
		if (paramInt1 < 0) paramInt1 -= i - 1;
		if (paramInt2 < 0) paramInt2 -= i - 1;

		int n = paramInt1 / i;
		int i1 = paramInt2 / i;
		Random localRandom = this.c.D(n, i1, 14357617);
		n *= i;
		i1 *= i;
		n += localRandom.nextInt(i - j);
		i1 += localRandom.nextInt(i - j);
		paramInt1 = k;
		paramInt2 = m;

		if ((paramInt1 == n) && (paramInt2 == i1)) {
			boolean bool = this.c.getWorldChunkManager().a(paramInt1 * 16 + 8, paramInt2 * 16 + 8, 0, e);
			if (bool)
			{
				return true;
			}
		}

		return false;
	}

	protected StructureStart b(int paramInt1, int paramInt2)
	{
		return new WorldGenLargeFeatureStart(this.c, this.b, paramInt1, paramInt2);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenLargeFeature
 * JD-Core Version:		0.6.0
 */