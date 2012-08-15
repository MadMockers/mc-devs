package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;

public class BlockReed extends Block
{
	protected BlockReed(int i, int j)
	{
		super(i, Material.PLANT);
		this.textureId = j;
		float f = 0.375F;

		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		b(true);
	}

	public void b(World world, int i, int j, int k, Random random) {
		if (world.isEmpty(i, j + 1, k))
		{
			for (int l = 1; world.getTypeId(i, j - l, k) == this.id; l++);
			if (l < 3) {
				int i1 = world.getData(i, j, k);

				if (i1 == 15) {
					CraftEventFactory.handleBlockGrowEvent(world, i, j + 1, k, this.id, 0);
					world.setData(i, j, k, 0);
				} else {
					world.setData(i, j, k, i1 + 1);
				}
			}
		}
	}

	public boolean canPlace(World world, int i, int j, int k) {
		int l = world.getTypeId(i, j - 1, k);

		return l == this.id;
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		k_(world, i, j, k);
	}

	protected final void k_(World world, int i, int j, int k) {
		if (!d(world, i, j, k)) {
			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
		}
	}

	public boolean d(World world, int i, int j, int k) {
		return canPlace(world, i, j, k);
	}

	public AxisAlignedBB e(World world, int i, int j, int k) {
		return null;
	}

	public int getDropType(int i, Random random, int j) {
		return Item.SUGAR_CANE.id;
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public int b() {
		return 1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockReed
 * JD-Core Version:		0.6.0
 */