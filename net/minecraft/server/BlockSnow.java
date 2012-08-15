package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockFadeEvent;

public class BlockSnow extends Block
{
	protected BlockSnow(int i, int j)
	{
		super(i, j, Material.SNOW_LAYER);
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		b(true);
		a(CreativeModeTab.c);
	}

	public AxisAlignedBB e(World world, int i, int j, int k) {
		int l = world.getData(i, j, k) & 0x7;

		return l >= 3 ? AxisAlignedBB.a().a(i + this.minX, j + this.minY, k + this.minZ, i + this.maxX, j + 0.5F, k + this.maxZ) : null;
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
		int l = iblockaccess.getData(i, j, k) & 0x7;
		float f = 2 * (1 + l) / 16.0F;

		a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}

	public boolean canPlace(World world, int i, int j, int k) {
		int l = world.getTypeId(i, j - 1, k);

		return (l != 0) && ((l == Block.LEAVES.id) || (Block.byId[l].d())) ? world.getMaterial(i, j - 1, k).isSolid() : false;
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		n(world, i, j, k);
	}

	private boolean n(World world, int i, int j, int k) {
		if (!canPlace(world, i, j, k)) {
			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setRawTypeId(i, j, k, 0);
			world.notify(i, j, k);
			return false;
		}
		return true;
	}

	public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l)
	{
		int i1 = Item.SNOW_BALL.id;
		float f = 0.7F;
		double d0 = world.random.nextFloat() * f + (1.0F - f) * 0.5D;
		double d1 = world.random.nextFloat() * f + (1.0F - f) * 0.5D;
		double d2 = world.random.nextFloat() * f + (1.0F - f) * 0.5D;
		EntityItem entityitem = new EntityItem(world, i + d0, j + d1, k + d2, new ItemStack(i1, 1, 0));

		entityitem.pickupDelay = 10;
		world.addEntity(entityitem);
		world.setTypeId(i, j, k, 0);
		entityhuman.a(StatisticList.C[this.id], 1);
	}

	public int getDropType(int i, Random random, int j) {
		return Item.SNOW_BALL.id;
	}

	public int a(Random random) {
		return 0;
	}

	public void b(World world, int i, int j, int k, Random random) {
		if (world.b(EnumSkyBlock.BLOCK, i, j, k) > 11)
		{
			if (CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(i, j, k), 0).isCancelled()) {
				return;
			}

			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, 0);
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockSnow
 * JD-Core Version:		0.6.0
 */