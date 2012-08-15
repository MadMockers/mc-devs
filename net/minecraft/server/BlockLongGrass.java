package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockFlower
{
	protected BlockLongGrass(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.REPLACEABLE_PLANT);
		float f = 0.4F;
		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	public int a(int paramInt1, int paramInt2)
	{
		if (paramInt2 == 1) return this.textureId;
		if (paramInt2 == 2) return this.textureId + 16 + 1;
		if (paramInt2 == 0) return this.textureId + 16;
		return this.textureId;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		if (paramRandom.nextInt(8) == 0) {
			return Item.SEEDS.id;
		}

		return -1;
	}

	public int getDropCount(int paramInt, Random paramRandom)
	{
		return 1 + paramRandom.nextInt(paramInt * 2 + 1);
	}

	public void a(World paramWorld, EntityHuman paramEntityHuman, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((!paramWorld.isStatic) && (paramEntityHuman.bC() != null) && (paramEntityHuman.bC().id == Item.SHEARS.id)) {
			paramEntityHuman.a(StatisticList.C[this.id], 1);

			a(paramWorld, paramInt1, paramInt2, paramInt3, new ItemStack(Block.LONG_GRASS, 1, paramInt4));
		} else {
			super.a(paramWorld, paramEntityHuman, paramInt1, paramInt2, paramInt3, paramInt4);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockLongGrass
 * JD-Core Version:		0.6.0
 */