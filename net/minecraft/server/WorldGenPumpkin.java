package net.minecraft.server;

import java.util.Random;

public class WorldGenPumpkin extends WorldGenerator
{
	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		for (int i = 0; i < 64; i++) {
			int j = paramInt1 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			int k = paramInt2 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
			int m = paramInt3 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			if ((!paramWorld.isEmpty(j, k, m)) || (paramWorld.getTypeId(j, k - 1, m) != Block.GRASS.id) || 
				(!Block.PUMPKIN.canPlace(paramWorld, j, k, m))) continue;
			paramWorld.setRawTypeIdAndData(j, k, m, Block.PUMPKIN.id, paramRandom.nextInt(4));
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenPumpkin
 * JD-Core Version:		0.6.0
 */