package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.plugin.PluginManager;

public class BlockLeaves extends BlockTransparant
{
	private int cr;
	public static final String[] a = { "oak", "spruce", "birch", "jungle" };
	int[] b;

	protected BlockLeaves(int i, int j)
	{
		super(i, j, Material.LEAVES, false);
		this.cr = j;
		b(true);
		a(CreativeModeTab.c);
	}

	public void remove(World world, int i, int j, int k, int l, int i1) {
		byte b0 = 1;
		int j1 = b0 + 1;

		if (world.c(i - j1, j - j1, k - j1, i + j1, j + j1, k + j1))
			for (int k1 = -b0; k1 <= b0; k1++)
				for (int l1 = -b0; l1 <= b0; l1++)
					for (int i2 = -b0; i2 <= b0; i2++) {
						int j2 = world.getTypeId(i + k1, j + l1, k + i2);

						if (j2 == Block.LEAVES.id) {
							int k2 = world.getData(i + k1, j + l1, k + i2);

							world.setRawData(i + k1, j + l1, k + i2, k2 | 0x8);
						}
					}
	}

	public void b(World world, int i, int j, int k, Random random)
	{
		if (!world.isStatic) {
			int l = world.getData(i, j, k);

			if (((l & 0x8) != 0) && ((l & 0x4) == 0)) {
				byte b0 = 4;
				int i1 = b0 + 1;
				byte b1 = 32;
				int j1 = b1 * b1;
				int k1 = b1 / 2;

				if (this.b == null) {
					this.b = new int[b1 * b1 * b1];
				}

				if (world.c(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
				{
					for (int l1 = -b0; l1 <= b0; l1++) {
						for (int i2 = -b0; i2 <= b0; i2++) {
							for (int j2 = -b0; j2 <= b0; j2++) {
								int k2 = world.getTypeId(i + l1, j + i2, k + j2);
								if (k2 == Block.LOG.id)
									this.b[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = 0;
								else if (k2 == Block.LEAVES.id)
									this.b[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = -2;
								else {
									this.b[((l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1)] = -1;
								}
							}
						}
					}

					for (l1 = 1; l1 <= 4; l1++) {
						for (int i2 = -b0; i2 <= b0; i2++) {
							for (int j2 = -b0; j2 <= b0; j2++) {
								for (int k2 = -b0; k2 <= b0; k2++) {
									if (this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1)] == l1 - 1) {
										if (this.b[((i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1)] == -2) {
											this.b[((i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1)] = l1;
										}

										if (this.b[((i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1)] == -2) {
											this.b[((i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1)] = l1;
										}

										if (this.b[((i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1)] == -2) {
											this.b[((i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1)] = l1;
										}

										if (this.b[((i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1)] == -2) {
											this.b[((i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1)] = l1;
										}

										if (this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1))] == -2) {
											this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1))] = l1;
										}

										if (this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1)] == -2) {
											this.b[((i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1)] = l1;
										}
									}
								}
							}
						}
					}
				}

				int l1 = this.b[(k1 * j1 + k1 * b1 + k1)];
				if (l1 >= 0)
					world.setRawData(i, j, k, l & 0xFFFFFFF7);
				else
					l(world, i, j, k);
			}
		}
	}

	private void l(World world, int i, int j, int k)
	{
		LeavesDecayEvent event = new LeavesDecayEvent(world.getWorld().getBlockAt(i, j, k));
		world.getServer().getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}

		c(world, i, j, k, world.getData(i, j, k), 0);
		world.setTypeId(i, j, k, 0);
	}

	public int a(Random random) {
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	public int getDropType(int i, Random random, int j) {
		return Block.SAPLING.id;
	}

	public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
		if (!world.isStatic) {
			byte b0 = 20;

			if ((l & 0x3) == 3) {
				b0 = 40;
			}

			if (world.random.nextInt(b0) == 0) {
				int j1 = getDropType(l, world.random, i1);

				a(world, i, j, k, new ItemStack(j1, 1, getDropData(l)));
			}

			if (((l & 0x3) == 0) && (world.random.nextInt(200) == 0))
				a(world, i, j, k, new ItemStack(Item.APPLE, 1, 0));
		}
	}

	public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
	{
		if ((!world.isStatic) && (entityhuman.bC() != null) && (entityhuman.bC().id == Item.SHEARS.id)) {
			entityhuman.a(StatisticList.C[this.id], 1);
			a(world, i, j, k, new ItemStack(Block.LEAVES.id, 1, l & 0x3));
		} else {
			super.a(world, entityhuman, i, j, k, l);
		}
	}

	protected int getDropData(int i) {
		return i & 0x3;
	}

	public boolean d() {
		return !this.c;
	}

	public int a(int i, int j) {
		return (j & 0x3) == 3 ? this.textureId + 144 : (j & 0x3) == 1 ? this.textureId + 80 : this.textureId;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockLeaves
 * JD-Core Version:		0.6.0
 */