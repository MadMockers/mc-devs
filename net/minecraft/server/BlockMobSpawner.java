package net.minecraft.server;

import java.util.Random;

public class BlockMobSpawner extends BlockContainer
{
	protected BlockMobSpawner(int i, int j)
	{
		super(i, j, Material.STONE);
	}

	public TileEntity a(World world) {
		return new TileEntityMobSpawner();
	}

	public int getDropType(int i, Random random, int j) {
		return 0;
	}

	public int a(Random random) {
		return 0;
	}

	public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
		super.dropNaturally(world, i, j, k, l, f, i1);
	}

	public int getExpDrop(World world, int data, int enchantmentLevel)
	{
		int j1 = 15 + world.random.nextInt(15) + world.random.nextInt(15);

		return j1;
	}

	public boolean d()
	{
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockMobSpawner
 * JD-Core Version:		0.6.0
 */