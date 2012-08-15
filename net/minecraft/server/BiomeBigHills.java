package net.minecraft.server;

import java.util.Random;

public class BiomeBigHills extends BiomeBase
{
	protected BiomeBigHills(int paramInt)
	{
		super(paramInt);
	}

	public void a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2)
	{
		super.a(paramWorld, paramRandom, paramInt1, paramInt2);

		int i = 3 + paramRandom.nextInt(6);
		for (int j = 0; j < i; j++) {
			int k = paramInt1 + paramRandom.nextInt(16);
			int m = paramRandom.nextInt(28) + 4;
			int n = paramInt2 + paramRandom.nextInt(16);
			int i1 = paramWorld.getTypeId(k, m, n);
			if (i1 == Block.STONE.id)
				paramWorld.setRawTypeId(k, m, n, Block.EMERALD_ORE.id);
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BiomeBigHills
 * JD-Core Version:		0.6.0
 */