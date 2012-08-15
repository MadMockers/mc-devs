package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

public class BlockLever extends Block
{
	protected BlockLever(int i, int j)
	{
		super(i, j, Material.ORIENTABLE);
		a(CreativeModeTab.d);
	}

	public AxisAlignedBB e(World world, int i, int j, int k) {
		return null;
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public int b() {
		return 12;
	}

	public boolean canPlace(World world, int i, int j, int k, int l) {
		return (l == 0) && (world.s(i, j + 1, k));
	}

	public boolean canPlace(World world, int i, int j, int k) {
		return world.t(i, j - 1, k) ? true : world.s(i, j, k + 1) ? true : world.s(i, j, k - 1) ? true : world.s(i + 1, j, k) ? true : world.s(i - 1, j, k) ? true : world.s(i, j + 1, k);
	}

	public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int i1 = world.getData(i, j, k);
		int j1 = i1 & 0x8;

		i1 &= 7;
		i1 = -1;
		if ((l == 0) && (world.s(i, j + 1, k))) {
			i1 = world.random.nextBoolean() ? 0 : 7;
		}

		if ((l == 1) && (world.t(i, j - 1, k))) {
			i1 = 5 + world.random.nextInt(2);
		}

		if ((l == 2) && (world.s(i, j, k + 1))) {
			i1 = 4;
		}

		if ((l == 3) && (world.s(i, j, k - 1))) {
			i1 = 3;
		}

		if ((l == 4) && (world.s(i + 1, j, k))) {
			i1 = 2;
		}

		if ((l == 5) && (world.s(i - 1, j, k))) {
			i1 = 1;
		}

		if (i1 == -1) {
			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
		} else {
			world.setData(i, j, k, i1 + j1);
		}
	}

	public static int d(int i) {
		switch (i) {
		case 0:
			return 0;
		case 1:
			return 5;
		case 2:
			return 4;
		case 3:
			return 3;
		case 4:
			return 2;
		case 5:
			return 1;
		}

		return -1;
	}

	public void doPhysics(World world, int i, int j, int k, int l)
	{
		if (l(world, i, j, k)) {
			int i1 = world.getData(i, j, k) & 0x7;
			boolean flag = false;

			if ((!world.s(i - 1, j, k)) && (i1 == 1)) {
				flag = true;
			}

			if ((!world.s(i + 1, j, k)) && (i1 == 2)) {
				flag = true;
			}

			if ((!world.s(i, j, k - 1)) && (i1 == 3)) {
				flag = true;
			}

			if ((!world.s(i, j, k + 1)) && (i1 == 4)) {
				flag = true;
			}

			if ((!world.t(i, j - 1, k)) && (i1 == 5)) {
				flag = true;
			}

			if ((!world.t(i, j - 1, k)) && (i1 == 6)) {
				flag = true;
			}

			if ((!world.s(i, j + 1, k)) && (i1 == 0)) {
				flag = true;
			}

			if ((!world.s(i, j + 1, k)) && (i1 == 7)) {
				flag = true;
			}

			if (flag) {
				c(world, i, j, k, world.getData(i, j, k), 0);
				world.setTypeId(i, j, k, 0);
			}
		}
	}

	private boolean l(World world, int i, int j, int k) {
		if (!canPlace(world, i, j, k)) {
			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
			return false;
		}
		return true;
	}

	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
	{
		int l = iblockaccess.getData(i, j, k) & 0x7;
		float f = 0.1875F;

		if (l == 1) {
			a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
		} else if (l == 2) {
			a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
		} else if (l == 3) {
			a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
		} else if (l == 4) {
			a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
		} else if ((l != 5) && (l != 6)) {
			if ((l == 0) || (l == 7)) {
				f = 0.25F;
				a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
			}
		} else {
			f = 0.25F;
			a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
		}
	}

	public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
		interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
	}

	public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
		if (world.isStatic) {
			return true;
		}
		int i1 = world.getData(i, j, k);
		int j1 = i1 & 0x7;
		int k1 = 8 - (i1 & 0x8);

		org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
		int old = k1 != 8 ? 1 : 0;
		int current = k1 == 8 ? 1 : 0;

		BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
		world.getServer().getPluginManager().callEvent(eventRedstone);

		if ((eventRedstone.getNewCurrent() > 0 ? 1 : 0) != (k1 == 8 ? 1 : 0)) {
			return true;
		}

		world.setData(i, j, k, j1 + k1);
		world.d(i, j, k, i, j, k);
		world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
		world.applyPhysics(i, j, k, this.id);
		if (j1 == 1)
			world.applyPhysics(i - 1, j, k, this.id);
		else if (j1 == 2)
			world.applyPhysics(i + 1, j, k, this.id);
		else if (j1 == 3)
			world.applyPhysics(i, j, k - 1, this.id);
		else if (j1 == 4)
			world.applyPhysics(i, j, k + 1, this.id);
		else if ((j1 != 5) && (j1 != 6)) {
			if ((j1 == 0) || (j1 == 7))
				world.applyPhysics(i, j + 1, k, this.id);
		}
		else {
			world.applyPhysics(i, j - 1, k, this.id);
		}

		return true;
	}

	public void remove(World world, int i, int j, int k, int l, int i1)
	{
		if ((i1 & 0x8) > 0) {
			world.applyPhysics(i, j, k, this.id);
			int j1 = i1 & 0x7;

			if (j1 == 1)
				world.applyPhysics(i - 1, j, k, this.id);
			else if (j1 == 2)
				world.applyPhysics(i + 1, j, k, this.id);
			else if (j1 == 3)
				world.applyPhysics(i, j, k - 1, this.id);
			else if (j1 == 4)
				world.applyPhysics(i, j, k + 1, this.id);
			else if ((j1 != 5) && (j1 != 6)) {
				if ((j1 == 0) || (j1 == 7))
					world.applyPhysics(i, j + 1, k, this.id);
			}
			else {
				world.applyPhysics(i, j - 1, k, this.id);
			}
		}

		super.remove(world, i, j, k, l, i1);
	}

	public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return (iblockaccess.getData(i, j, k) & 0x8) > 0;
	}

	public boolean c(World world, int i, int j, int k, int l) {
		int i1 = world.getData(i, j, k);

		if ((i1 & 0x8) == 0) {
			return false;
		}
		int j1 = i1 & 0x7;

		return (j1 == 0) && (l == 0);
	}

	public boolean isPowerSource()
	{
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockLever
 * JD-Core Version:		0.6.0
 */