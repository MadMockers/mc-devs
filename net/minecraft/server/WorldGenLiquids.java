package net.minecraft.server;

import java.util.Random;

public class WorldGenLiquids extends WorldGenerator
{
	private int a;

	public WorldGenLiquids(int paramInt)
	{
		this.a = paramInt;
	}

	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3) != Block.STONE.id) return false;
		if (paramWorld.getTypeId(paramInt1, paramInt2 - 1, paramInt3) != Block.STONE.id) return false;

		if ((paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != 0) && (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != Block.STONE.id)) return false;

		int i = 0;
		if (paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3) == Block.STONE.id) i++;
		if (paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3) == Block.STONE.id) i++;
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1) == Block.STONE.id) i++;
		if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1) == Block.STONE.id) i++;

		int j = 0;
		if (paramWorld.isEmpty(paramInt1 - 1, paramInt2, paramInt3)) j++;
		if (paramWorld.isEmpty(paramInt1 + 1, paramInt2, paramInt3)) j++;
		if (paramWorld.isEmpty(paramInt1, paramInt2, paramInt3 - 1)) j++;
		if (paramWorld.isEmpty(paramInt1, paramInt2, paramInt3 + 1)) j++;

		if ((i == 3) && (j == 1)) {
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, this.a);
			paramWorld.e = true;
			Block.byId[this.a].b(paramWorld, paramInt1, paramInt2, paramInt3, paramRandom);
			paramWorld.e = false;
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenLiquids
 * JD-Core Version:		0.6.0
 */