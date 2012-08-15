package net.minecraft.server;

import java.util.Random;
import org.bukkit.BlockChangeDelegate;

public abstract class WorldGenerator
{
	private final boolean a;

	public WorldGenerator()
	{
		this.a = false;
	}

	public WorldGenerator(boolean flag) {
		this.a = flag;
	}
	public abstract boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3);

	public void a(double d0, double d1, double d2) {
	}

	protected void setType(BlockChangeDelegate world, int i, int j, int k, int l) {
		setTypeAndData(world, i, j, k, l, 0);
	}

	protected void setTypeAndData(BlockChangeDelegate world, int i, int j, int k, int l, int i1)
	{
		if (this.a)
			world.setTypeIdAndData(i, j, k, l, i1);
		else
			world.setRawTypeIdAndData(i, j, k, l, i1);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenerator
 * JD-Core Version:		0.6.0
 */