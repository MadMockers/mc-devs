package net.minecraft.server;

import java.util.Random;

public class BlockWeb extends Block
{
	public BlockWeb(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.WEB);
		a(CreativeModeTab.c);
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity)
	{
		paramEntity.aj();
	}

	public boolean d()
	{
		return false;
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		return null;
	}

	public int b()
	{
		return 1;
	}

	public boolean c()
	{
		return false;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Item.STRING.id;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockWeb
 * JD-Core Version:		0.6.0
 */