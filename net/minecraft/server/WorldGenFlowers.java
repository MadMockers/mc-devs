package net.minecraft.server;

import java.util.Random;

public class WorldGenFlowers extends WorldGenerator
{
	private int a;

	public WorldGenFlowers(int paramInt)
	{
		this.a = paramInt;
	}

	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		for (int i = 0; i < 64; i++) {
			int j = paramInt1 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			int k = paramInt2 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
			int m = paramInt3 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			if ((!paramWorld.isEmpty(j, k, m)) || 
				(!Block.byId[this.a].d(paramWorld, j, k, m))) continue;
			paramWorld.setRawTypeId(j, k, m, this.a);
		}

		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenFlowers
 * JD-Core Version:		0.6.0
 */