package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

public class BlockButton extends Block
{
	protected BlockButton(int i, int j)
	{
		super(i, j, Material.ORIENTABLE);
		b(true);
		a(CreativeModeTab.d);
	}

	public AxisAlignedBB e(World world, int i, int j, int k) {
		return null;
	}

	public int p_() {
		return 20;
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public boolean canPlace(World world, int i, int j, int k, int l) {
		return (l == 2) && (world.s(i, j, k + 1));
	}

	public boolean canPlace(World world, int i, int j, int k) {
		return world.s(i, j, k - 1) ? true : world.s(i + 1, j, k) ? true : world.s(i - 1, j, k) ? true : world.s(i, j, k + 1);
	}

	public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int i1 = world.getData(i, j, k);
		int j1 = i1 & 0x8;

		i1 &= 7;
		if ((l == 2) && (world.s(i, j, k + 1)))
			i1 = 4;
		else if ((l == 3) && (world.s(i, j, k - 1)))
			i1 = 3;
		else if ((l == 4) && (world.s(i + 1, j, k)))
			i1 = 2;
		else if ((l == 5) && (world.s(i - 1, j, k)))
			i1 = 1;
		else {
			i1 = l(world, i, j, k);
		}

		world.setData(i, j, k, i1 + j1);
	}

	private int l(World world, int i, int j, int k) {
		return world.s(i, j, k + 1) ? 4 : world.s(i, j, k - 1) ? 3 : world.s(i + 1, j, k) ? 2 : world.s(i - 1, j, k) ? 1 : 1;
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		if (n(world, i, j, k)) {
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

			if (flag) {
				c(world, i, j, k, world.getData(i, j, k), 0);
				world.setTypeId(i, j, k, 0);
			}
		}
	}

	private boolean n(World world, int i, int j, int k) {
		if (!canPlace(world, i, j, k)) {
			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
			return false;
		}
		return true;
	}

	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
	{
		int l = iblockaccess.getData(i, j, k);
		int i1 = l & 0x7;
		boolean flag = (l & 0x8) > 0;
		float f = 0.375F;
		float f1 = 0.625F;
		float f2 = 0.1875F;
		float f3 = 0.125F;

		if (flag) {
			f3 = 0.0625F;
		}

		if (i1 == 1)
			a(0.0F, f, 0.5F - f2, f3, f1, 0.5F + f2);
		else if (i1 == 2)
			a(1.0F - f3, f, 0.5F - f2, 1.0F, f1, 0.5F + f2);
		else if (i1 == 3)
			a(0.5F - f2, f, 0.0F, 0.5F + f2, f1, f3);
		else if (i1 == 4)
			a(0.5F - f2, f, 1.0F - f3, 0.5F + f2, f1, 1.0F);
	}

	public void attack(World world, int i, int j, int k, EntityHuman entityhuman)
	{
		interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
	}

	public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
		int i1 = world.getData(i, j, k);
		int j1 = i1 & 0x7;
		int k1 = 8 - (i1 & 0x8);

		if (k1 == 0) {
			return true;
		}

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
		world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "random.click", 0.3F, 0.6F);
		world.applyPhysics(i, j, k, this.id);
		if (j1 == 1)
			world.applyPhysics(i - 1, j, k, this.id);
		else if (j1 == 2)
			world.applyPhysics(i + 1, j, k, this.id);
		else if (j1 == 3)
			world.applyPhysics(i, j, k - 1, this.id);
		else if (j1 == 4)
			world.applyPhysics(i, j, k + 1, this.id);
		else {
			world.applyPhysics(i, j - 1, k, this.id);
		}

		world.a(i, j, k, this.id, p_());
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

		return (j1 == 5) && (l == 1);
	}

	public boolean isPowerSource()
	{
		return true;
	}

	public void b(World world, int i, int j, int k, Random random) {
		if (!world.isStatic) {
			int l = world.getData(i, j, k);

			if ((l & 0x8) != 0)
			{
				org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);

				BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 1, 0);
				world.getServer().getPluginManager().callEvent(eventRedstone);

				if (eventRedstone.getNewCurrent() > 0) {
					return;
				}

				world.setData(i, j, k, l & 0x7);
				world.applyPhysics(i, j, k, this.id);
				int i1 = l & 0x7;

				if (i1 == 1)
					world.applyPhysics(i - 1, j, k, this.id);
				else if (i1 == 2)
					world.applyPhysics(i + 1, j, k, this.id);
				else if (i1 == 3)
					world.applyPhysics(i, j, k - 1, this.id);
				else if (i1 == 4)
					world.applyPhysics(i, j, k + 1, this.id);
				else {
					world.applyPhysics(i, j - 1, k, this.id);
				}

				world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "random.click", 0.3F, 0.5F);
				world.d(i, j, k, i, j, k);
			}
		}
	}

	public void f() {
		float f = 0.1875F;
		float f1 = 0.125F;
		float f2 = 0.125F;

		a(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockButton
 * JD-Core Version:		0.6.0
 */