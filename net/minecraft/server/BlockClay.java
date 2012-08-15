package net.minecraft.server;

import java.util.Random;

public class BlockClay extends Block
{
	public BlockClay(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.CLAY);
		a(CreativeModeTab.b);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Item.CLAY_BALL.id;
	}

	public int a(Random paramRandom)
	{
		return 4;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockClay
 * JD-Core Version:		0.6.0
 */