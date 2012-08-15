package net.minecraft.server;

import java.util.Random;

public class BlockSand extends Block
{
	public static boolean instaFall = false;

	public BlockSand(int i, int j) {
		super(i, j, Material.SAND);
		a(CreativeModeTab.b);
	}

	public void onPlace(World world, int i, int j, int k) {
		world.a(i, j, k, this.id, p_());
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		world.a(i, j, k, this.id, p_());
	}

	public void b(World world, int i, int j, int k, Random random) {
		if (!world.isStatic)
			l(world, i, j, k);
	}

	private void l(World world, int i, int j, int k)
	{
		if ((canFall(world, i, j - 1, k)) && (j >= 0)) {
			byte b0 = 32;

			if ((!instaFall) && (world.c(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0))) {
				if (!world.isStatic)
				{
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, i + 0.5F, j + 0.5F, k + 0.5F, this.id, world.getData(i, j, k));

					world.addEntity(entityfallingblock);
				}
			} else {
				world.setTypeId(i, j, k, 0);

				while ((canFall(world, i, j - 1, k)) && (j > 0)) {
					j--;
				}

				if (j > 0)
					world.setTypeId(i, j, k, this.id);
			}
		}
	}

	public int p_()
	{
		return 3;
	}

	public static boolean canFall(World world, int i, int j, int k) {
		int l = world.getTypeId(i, j, k);

		if (l == 0)
			return true;
		if (l == Block.FIRE.id) {
			return true;
		}
		Material material = Block.byId[l].material;

		return material == Material.WATER;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockSand
 * JD-Core Version:		0.6.0
 */