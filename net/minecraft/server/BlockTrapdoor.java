package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

public class BlockTrapdoor extends Block
{
	protected BlockTrapdoor(int i, Material material)
	{
		super(i, material);
		this.textureId = 84;
		if (material == Material.ORE) {
			this.textureId += 1;
		}

		float f = 0.5F;
		float f1 = 1.0F;

		a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		a(CreativeModeTab.d);
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
		return !g(iblockaccess.getData(i, j, k));
	}

	public int b() {
		return 0;
	}

	public AxisAlignedBB e(World world, int i, int j, int k) {
		updateShape(world, i, j, k);
		return super.e(world, i, j, k);
	}

	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
		e(iblockaccess.getData(i, j, k));
	}

	public void f() {
		float f = 0.1875F;

		a(0.0F, 0.5F - f / 2.0F, 0.0F, 1.0F, 0.5F + f / 2.0F, 1.0F);
	}

	public void e(int i) {
		float f = 0.1875F;

		a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		if (g(i)) {
			if ((i & 0x3) == 0) {
				a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			}

			if ((i & 0x3) == 1) {
				a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			}

			if ((i & 0x3) == 2) {
				a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}

			if ((i & 0x3) == 3)
				a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		}
	}

	public void attack(World world, int i, int j, int k, EntityHuman entityhuman)
	{
		interact(world, i, j, k, entityhuman, 0, 0.0F, 0.0F, 0.0F);
	}

	public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
		if (this.material == Material.ORE) {
			return true;
		}
		int i1 = world.getData(i, j, k);

		world.setData(i, j, k, i1 ^ 0x4);
		world.a(entityhuman, 1003, i, j, k, 0);
		return true;
	}

	public void setOpen(World world, int i, int j, int k, boolean flag)
	{
		int l = world.getData(i, j, k);
		boolean flag1 = (l & 0x4) > 0;

		if (flag1 != flag) {
			world.setData(i, j, k, l ^ 0x4);
			world.a((EntityHuman)null, 1003, i, j, k, 0);
		}
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		if (!world.isStatic) {
			int i1 = world.getData(i, j, k);
			int j1 = i;
			int k1 = k;

			if ((i1 & 0x3) == 0) {
				k1 = k + 1;
			}

			if ((i1 & 0x3) == 1) {
				k1--;
			}

			if ((i1 & 0x3) == 2) {
				j1 = i + 1;
			}

			if ((i1 & 0x3) == 3) {
				j1--;
			}

			if (!j(world.getTypeId(j1, j, k1))) {
				world.setTypeId(i, j, k, 0);
				c(world, i, j, k, i1, 0);
			}

			if ((l == 0) || ((l > 0) && (Block.byId[l] != null) && (Block.byId[l].isPowerSource()))) {
				org.bukkit.World bworld = world.getWorld();
				org.bukkit.block.Block block = bworld.getBlockAt(i, j, k);

				int power = block.getBlockPower();
				int oldPower = (world.getData(i, j, k) & 0x4) > 0 ? 15 : 0;

				if ((((oldPower == 0 ? 1 : 0) ^ (power == 0 ? 1 : 0)) != 0) || ((Block.byId[l] != null) && (Block.byId[l].isPowerSource()))) {
					BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, oldPower, power);
					world.getServer().getPluginManager().callEvent(eventRedstone);

					setOpen(world, i, j, k, eventRedstone.getNewCurrent() > 0);
				}
			}
		}
	}

	public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
	{
		updateShape(world, i, j, k);
		return super.a(world, i, j, k, vec3d, vec3d1);
	}

	public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
		byte b0 = 0;

		if (l == 2) {
			b0 = 0;
		}

		if (l == 3) {
			b0 = 1;
		}

		if (l == 4) {
			b0 = 2;
		}

		if (l == 5) {
			b0 = 3;
		}

		world.setData(i, j, k, b0);
		doPhysics(world, i, j, k, Block.REDSTONE_WIRE.id);
	}

	public boolean canPlace(World world, int i, int j, int k, int l) {
		if (l == 0)
			return false;
		if (l == 1) {
			return false;
		}
		if (l == 2) {
			k++;
		}

		if (l == 3) {
			k--;
		}

		if (l == 4) {
			i++;
		}

		if (l == 5) {
			i--;
		}

		return j(world.getTypeId(i, j, k));
	}

	public static boolean g(int i)
	{
		return (i & 0x4) != 0;
	}

	private static boolean j(int i) {
		if (i <= 0) {
			return false;
		}
		Block block = Block.byId[i];

		return ((block != null) && (block.material.k()) && (block.c())) || (block == Block.GLOWSTONE);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockTrapdoor
 * JD-Core Version:		0.6.0
 */