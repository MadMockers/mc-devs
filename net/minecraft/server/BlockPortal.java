package net.minecraft.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.PortalCreateEvent.CreateReason;
import org.bukkit.plugin.PluginManager;

public class BlockPortal extends BlockHalfTransparant
{
	public BlockPortal(int i, int j)
	{
		super(i, j, Material.PORTAL, false);
		b(true);
	}

	public void b(World world, int i, int j, int k, Random random) {
		super.b(world, i, j, k, random);
		if ((world.worldProvider.d()) && (random.nextInt(2000) < world.difficulty))
		{
			for (int l = j; (!world.t(i, l, k)) && (l > 0); l--);
			if ((l > 0) && (!world.s(i, l + 1, k)))
				ItemMonsterEgg.a(world, 57, i + 0.5D, l + 1.1D, k + 0.5D);
		}
	}

	public AxisAlignedBB e(World world, int i, int j, int k)
	{
		return null;
	}

	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k)
	{
		if ((iblockaccess.getTypeId(i - 1, j, k) != this.id) && (iblockaccess.getTypeId(i + 1, j, k) != this.id)) {
			float f = 0.125F;
			float f1 = 0.5F;
			a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		} else {
			float f = 0.5F;
			float f1 = 0.125F;
			a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
		}
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public boolean i_(World world, int i, int j, int k) {
		byte b0 = 0;
		byte b1 = 0;

		if ((world.getTypeId(i - 1, j, k) == Block.OBSIDIAN.id) || (world.getTypeId(i + 1, j, k) == Block.OBSIDIAN.id)) {
			b0 = 1;
		}

		if ((world.getTypeId(i, j, k - 1) == Block.OBSIDIAN.id) || (world.getTypeId(i, j, k + 1) == Block.OBSIDIAN.id)) {
			b1 = 1;
		}

		if (b0 == b1) {
			return false;
		}

		Collection blocks = new HashSet();
		org.bukkit.World bworld = world.getWorld();

		if (world.getTypeId(i - b0, j, k - b1) == 0) {
			i -= b0;
			k -= b1;
		}

		for (int l = -1; l <= 2; l++) {
			for (int i1 = -1; i1 <= 3; i1++) {
				boolean flag = (l == -1) || (l == 2) || (i1 == -1) || (i1 == 3);

				if (((l != -1) && (l != 2)) || ((i1 != -1) && (i1 != 3))) {
					int j1 = world.getTypeId(i + b0 * l, j + i1, k + b1 * l);

					if (flag) {
						if (j1 != Block.OBSIDIAN.id) {
							return false;
						}
						blocks.add(bworld.getBlockAt(i + b0 * l, j + i1, k + b1 * l));
					}
					else if ((j1 != 0) && (j1 != Block.FIRE.id)) {
						return false;
					}
				}
			}

		}

		for (l = 0; l < 2; l++) {
			for (int i1 = 0; i1 < 3; i1++) {
				blocks.add(bworld.getBlockAt(i + b0 * l, j + i1, k + b1 * l));
			}
		}

		PortalCreateEvent event = new PortalCreateEvent(blocks, bworld, PortalCreateEvent.CreateReason.FIRE);
		world.getServer().getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return false;
		}

		world.suppressPhysics = true;

		for (l = 0; l < 2; l++) {
			for (int i1 = 0; i1 < 3; i1++) {
				world.setTypeId(i + b0 * l, j + i1, k + b1 * l, Block.PORTAL.id);
			}
		}

		world.suppressPhysics = false;
		return true;
	}

	public void doPhysics(World world, int i, int j, int k, int l)
	{
		byte b0 = 0;
		byte b1 = 1;

		if ((world.getTypeId(i - 1, j, k) == this.id) || (world.getTypeId(i + 1, j, k) == this.id)) {
			b0 = 1;
			b1 = 0;
		}

		for (int i1 = j; world.getTypeId(i, i1 - 1, k) == this.id; i1--);
		if (world.getTypeId(i, i1 - 1, k) != Block.OBSIDIAN.id) {
			world.setTypeId(i, j, k, 0);
		}
		else
		{
			for (int j1 = 1; (j1 < 4) && (world.getTypeId(i, i1 + j1, k) == this.id); j1++);
			if ((j1 == 3) && (world.getTypeId(i, i1 + j1, k) == Block.OBSIDIAN.id)) {
				boolean flag = (world.getTypeId(i - 1, j, k) == this.id) || (world.getTypeId(i + 1, j, k) == this.id);
				boolean flag1 = (world.getTypeId(i, j, k - 1) == this.id) || (world.getTypeId(i, j, k + 1) == this.id);

				if ((flag) && (flag1)) {
					world.setTypeId(i, j, k, 0);
				}
				else if (((world.getTypeId(i + b0, j, k + b1) != Block.OBSIDIAN.id) || (world.getTypeId(i - b0, j, k - b1) != this.id)) && ((world.getTypeId(i - b0, j, k - b1) != Block.OBSIDIAN.id) || (world.getTypeId(i + b0, j, k + b1) != this.id)))
					world.setTypeId(i, j, k, 0);
			}
			else
			{
				world.setTypeId(i, j, k, 0);
			}
		}
	}

	public int a(Random random) {
		return 0;
	}

	public void a(World world, int i, int j, int k, Entity entity) {
		if ((entity.vehicle == null) && (entity.passenger == null))
		{
			EntityPortalEnterEvent event = new EntityPortalEnterEvent(entity.getBukkitEntity(), new Location(world.getWorld(), i, j, k));
			world.getServer().getPluginManager().callEvent(event);

			entity.aa();
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockPortal
 * JD-Core Version:		0.6.0
 */