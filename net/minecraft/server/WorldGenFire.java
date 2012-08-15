package net.minecraft.server;

import java.util.Random;

public class WorldGenFire extends WorldGenerator
{
	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		for (int i = 0; i < 64; i++) {
			int j = paramInt1 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			int k = paramInt2 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
			int m = paramInt3 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			if ((!paramWorld.isEmpty(j, k, m)) || 
				(paramWorld.getTypeId(j, k - 1, m) != Block.NETHERRACK.id)) continue;
			paramWorld.setTypeId(j, k, m, Block.FIRE.id);
		}

		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenFire
 * JD-Core Version:		0.6.0
 */