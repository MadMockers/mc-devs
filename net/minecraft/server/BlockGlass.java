package net.minecraft.server;

import java.util.Random;

public class BlockGlass extends BlockHalfTransparant
{
	public BlockGlass(int paramInt1, int paramInt2, Material paramMaterial, boolean paramBoolean)
	{
		super(paramInt1, paramInt2, paramMaterial, paramBoolean);
		a(CreativeModeTab.b);
	}

	public int a(Random paramRandom)
	{
		return 0;
	}

	public boolean d()
	{
		return false;
	}

	public boolean c()
	{
		return false;
	}

	protected boolean q_()
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockGlass
 * JD-Core Version:		0.6.0
 */