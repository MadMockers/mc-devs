package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;

public class BlockNetherWart extends BlockFlower
{
	protected BlockNetherWart(int i)
	{
		super(i, 226);
		b(true);
		float f = 0.5F;

		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		a((CreativeModeTab)null);
	}

	protected boolean d_(int i) {
		return i == Block.SOUL_SAND.id;
	}

	public boolean d(World world, int i, int j, int k) {
		return d_(world.getTypeId(i, j - 1, k));
	}

	public void b(World world, int i, int j, int k, Random random) {
		int l = world.getData(i, j, k);

		if ((l < 3) && (random.nextInt(10) == 0)) {
			l++; CraftEventFactory.handleBlockGrowEvent(world, i, j, k, this.id, l);
		}

		super.b(world, i, j, k, random);
	}

	public int a(int i, int j) {
		return j > 0 ? this.textureId + 1 : j >= 3 ? this.textureId + 2 : this.textureId;
	}

	public int b() {
		return 6;
	}

	public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
		if (!world.isStatic) {
			int j1 = 1;

			if (l >= 3) {
				j1 = 2 + world.random.nextInt(3);
				if (i1 > 0) {
					j1 += world.random.nextInt(i1 + 1);
				}
			}

			for (int k1 = 0; k1 < j1; k1++)
				a(world, i, j, k, new ItemStack(Item.NETHER_STALK));
		}
	}

	public int getDropType(int i, Random random, int j)
	{
		return 0;
	}

	public int a(Random random) {
		return 0;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockNetherWart
 * JD-Core Version:		0.6.0
 */