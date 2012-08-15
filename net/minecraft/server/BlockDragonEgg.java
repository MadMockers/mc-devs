package net.minecraft.server;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.PluginManager;

public class BlockDragonEgg extends Block
{
	public BlockDragonEgg(int i, int j)
	{
		super(i, j, Material.DRAGON_EGG);
	}

	public void onPlace(World world, int i, int j, int k) {
		world.a(i, j, k, this.id, p_());
	}

	public void doPhysics(World world, int i, int j, int k, int l) {
		world.a(i, j, k, this.id, p_());
	}

	public void b(World world, int i, int j, int k, Random random) {
		l(world, i, j, k);
	}

	private void l(World world, int i, int j, int k) {
		if ((BlockSand.canFall(world, i, j - 1, k)) && (j >= 0)) {
			byte b0 = 32;

			if ((!BlockSand.instaFall) && (world.c(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)))
			{
				EntityFallingBlock entityfallingblock = new EntityFallingBlock(world, i + 0.5F, j + 0.5F, k + 0.5F, this.id, world.getData(i, j, k));

				world.addEntity(entityfallingblock);
			} else {
				world.setTypeId(i, j, k, 0);

				while ((BlockSand.canFall(world, i, j - 1, k)) && (j > 0)) {
					j--;
				}

				if (j > 0)
					world.setTypeId(i, j, k, this.id);
			}
		}
	}

	public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2)
	{
		n(world, i, j, k);
		return true;
	}

	public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
		n(world, i, j, k);
	}

	private void n(World world, int i, int j, int k) {
		if ((world.getTypeId(i, j, k) == this.id) && 
			(!world.isStatic))
			for (int l = 0; l < 1000; l++) {
				int i1 = i + world.random.nextInt(16) - world.random.nextInt(16);
				int j1 = j + world.random.nextInt(8) - world.random.nextInt(8);
				int k1 = k + world.random.nextInt(16) - world.random.nextInt(16);

				if (world.getTypeId(i1, j1, k1) != 0)
					continue;
				org.bukkit.block.Block from = world.getWorld().getBlockAt(i, j, k);
				org.bukkit.block.Block to = world.getWorld().getBlockAt(i1, j1, k1);
				BlockFromToEvent event = new BlockFromToEvent(from, to);
				Bukkit.getPluginManager().callEvent(event);

				if (event.isCancelled()) {
					return;
				}

				i1 = event.getToBlock().getX();
				j1 = event.getToBlock().getY();
				k1 = event.getToBlock().getZ();

				world.setTypeIdAndData(i1, j1, k1, this.id, world.getData(i, j, k));
				world.setTypeId(i, j, k, 0);
				short short1 = 128;

				for (int l1 = 0; l1 < short1; l1++) {
					double d0 = world.random.nextDouble();
					float f = (world.random.nextFloat() - 0.5F) * 0.2F;
					float f1 = (world.random.nextFloat() - 0.5F) * 0.2F;
					float f2 = (world.random.nextFloat() - 0.5F) * 0.2F;
					double d1 = i1 + (i - i1) * d0 + (world.random.nextDouble() - 0.5D) * 1.0D + 0.5D;
					double d2 = j1 + (j - j1) * d0 + world.random.nextDouble() * 1.0D - 0.5D;
					double d3 = k1 + (k - k1) * d0 + (world.random.nextDouble() - 0.5D) * 1.0D + 0.5D;

					world.a("portal", d1, d2, d3, f, f1, f2);
				}

				return;
			}
	}

	public int p_()
	{
		return 3;
	}

	public boolean d() {
		return false;
	}

	public boolean c() {
		return false;
	}

	public int b() {
		return 27;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockDragonEgg
 * JD-Core Version:		0.6.0
 */