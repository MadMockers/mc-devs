package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.plugin.PluginManager;

public class BlockTripwire extends Block
{
	public BlockTripwire(int i)
	{
		super(i, 173, Material.ORIENTABLE);
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
		b(true);
	}

	public int p_() {
		return 10;
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
		return 30;
	}

	public int getDropType(int i, Random random, int j) {
		return Item.STRING.id;
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		int i1 = world.getData(i, j, k);
		boolean flag = (i1 & 0x2) == 2;
		boolean flag1 = !world.t(i, j - 1, k);

		if (flag != flag1) {
			c(world, i, j, k, i1, 0);
			world.setTypeId(i, j, k, 0);
		}
	}

	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
		int l = iblockaccess.getData(i, j, k);
		boolean flag = (l & 0x4) == 4;
		boolean flag1 = (l & 0x2) == 2;

		if (!flag1)
			a(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
		else if (!flag)
			a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		else
			a(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
	}

	public void onPlace(World world, int i, int j, int k)
	{
		int l = world.t(i, j - 1, k) ? 0 : 2;

		world.setData(i, j, k, l);
		e(world, i, j, k, l);
	}

	public void remove(World world, int i, int j, int k, int l, int i1) {
		e(world, i, j, k, i1 | 0x1);
	}

	public void a(World world, int i, int j, int k, int l, EntityHuman entityhuman) {
		if ((!world.isStatic) && 
			(entityhuman.bC() != null) && (entityhuman.bC().id == Item.SHEARS.id))
			world.setData(i, j, k, l | 0x8);
	}

	private void e(World world, int i, int j, int k, int l)
	{
		int i1 = 0;

		while (i1 < 2) {
			int j1 = 1;

			while (j1 < 42) {
				int k1 = i + Direction.a[i1] * j1;
				int l1 = k + Direction.b[i1] * j1;
				int i2 = world.getTypeId(k1, j, l1);

				if (i2 == Block.TRIPWIRE_SOURCE.id) {
					int j2 = world.getData(k1, j, l1) & 0x3;

					if (j2 == Direction.e[i1]) {
						Block.TRIPWIRE_SOURCE.a(world, k1, j, l1, i2, world.getData(k1, j, l1), true, j1, l);
					}
					break; } if (i2 != Block.TRIPWIRE.id) break;
				j1++;
			}

			i1++;
		}
	}

	public void a(World world, int i, int j, int k, Entity entity)
	{
		if ((!world.isStatic) && 
			((world.getData(i, j, k) & 0x1) != 1))
			l(world, i, j, k);
	}

	public void b(World world, int i, int j, int k, Random random)
	{
		if ((!world.isStatic) && 
			((world.getData(i, j, k) & 0x1) == 1))
			l(world, i, j, k);
	}

	private void l(World world, int i, int j, int k)
	{
		int l = world.getData(i, j, k);
		boolean flag = (l & 0x1) == 1;
		boolean flag1 = false;
		List list = world.getEntities((Entity)null, AxisAlignedBB.a().a(i + this.minX, j + this.minY, k + this.minZ, i + this.maxX, j + this.maxY, k + this.maxZ));

		if (!list.isEmpty()) {
			flag1 = true;
		}

		org.bukkit.World bworld = world.getWorld();
		PluginManager manager = world.getServer().getPluginManager();
		Iterator i$;
		if ((flag != flag1) && 
			(flag1)) {
			for (i$ = list.iterator(); i$.hasNext(); ) { Object object = i$.next();
				if (object != null)
				{
					Cancellable cancellable;
					Cancellable cancellable;
					if ((object instanceof EntityHuman)) {
						cancellable = CraftEventFactory.callPlayerInteractEvent((EntityHuman)object, Action.PHYSICAL, i, j, k, -1, null);
					} else if ((object instanceof Entity)) {
						cancellable = new EntityInteractEvent(((Entity)object).getBukkitEntity(), bworld.getBlockAt(i, j, k));
						manager.callEvent((EntityInteractEvent)cancellable);
					}

					if (cancellable.isCancelled()) {
						return;
					}
				}

			}

		}

		if ((flag1) && (!flag)) {
			l |= 1;
		}

		if ((!flag1) && (flag)) {
			l &= -2;
		}

		if (flag1 != flag) {
			world.setData(i, j, k, l);
			e(world, i, j, k, l);
		}

		if (flag1)
			world.a(i, j, k, this.id, p_());
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockTripwire
 * JD-Core Version:		0.6.0
 */