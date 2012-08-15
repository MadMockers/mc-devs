package net.minecraft.server;

import java.util.Random;

public class BlockStone extends Block
{
	public BlockStone(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.STONE);
		a(CreativeModeTab.b);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Block.COBBLESTONE.id;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockStone
 * JD-Core Version:		0.6.0
 */