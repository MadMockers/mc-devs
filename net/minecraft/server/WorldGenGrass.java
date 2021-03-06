package net.minecraft.server;

import java.util.Random;

public class WorldGenGrass extends WorldGenerator
{
	private int a;
	private int b;

	public WorldGenGrass(int paramInt1, int paramInt2)
	{
		this.a = paramInt1;
		this.b = paramInt2;
	}

	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = 0;
		while ((((i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3)) == 0) || (i == Block.LEAVES.id)) && (paramInt2 > 0)) {
			paramInt2--;
		}
		for (int j = 0; j < 128; j++) {
			int k = paramInt1 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			int m = paramInt2 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
			int n = paramInt3 + paramRandom.nextInt(8) - paramRandom.nextInt(8);
			if ((!paramWorld.isEmpty(k, m, n)) || 
				(!Block.byId[this.a].d(paramWorld, k, m, n))) continue;
			paramWorld.setRawTypeIdAndData(k, m, n, this.a, this.b);
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenGrass
 * JD-Core Version:		0.6.0
 */