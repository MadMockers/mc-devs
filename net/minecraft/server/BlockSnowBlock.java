package net.minecraft.server;

import java.util.Random;

public class BlockSnowBlock extends Block
{
	protected BlockSnowBlock(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.SNOW_BLOCK);
		b(true);
		a(CreativeModeTab.b);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Item.SNOW_BALL.id;
	}

	public int a(Random paramRandom)
	{
		return 4;
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		if (paramWorld.b(EnumSkyBlock.BLOCK, paramInt1, paramInt2, paramInt3) > 11) {
			c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockSnowBlock
 * JD-Core Version:		0.6.0
 */