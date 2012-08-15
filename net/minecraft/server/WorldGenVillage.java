package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenVillage extends StructureGenerator
{
	public static List e = Arrays.asList(new BiomeBase[] { BiomeBase.PLAINS, BiomeBase.DESERT });
	private final int f;

	public WorldGenVillage(int paramInt)
	{
		this.f = paramInt;
	}

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
		Random localRandom = this.c.D(n, i1, 10387312);
		n *= i;
		i1 *= i;
		n += localRandom.nextInt(i - j);
		i1 += localRandom.nextInt(i - j);
		paramInt1 = k;
		paramInt2 = m;

		if ((paramInt1 == n) && (paramInt2 == i1)) {
			boolean bool = this.c.getWorldChunkManager().a(paramInt1 * 16 + 8, paramInt2 * 16 + 8, 0, e);
			if (bool) {
				return true;
			}
		}

		return false;
	}

	protected StructureStart b(int paramInt1, int paramInt2)
	{
		return new WorldGenVillageStart(this.c, this.b, paramInt1, paramInt2, this.f);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenVillage
 * JD-Core Version:		0.6.0
 */