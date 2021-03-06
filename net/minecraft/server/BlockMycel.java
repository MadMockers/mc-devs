package net.minecraft.server;

import java.util.Random;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.plugin.PluginManager;

public class BlockMycel extends Block
{
	protected BlockMycel(int i)
	{
		super(i, Material.GRASS);
		this.textureId = 77;
		b(true);
		a(CreativeModeTab.b);
	}

	public int a(int i, int j) {
		return i == 0 ? 2 : i == 1 ? 78 : 77;
	}

	public void b(World world, int i, int j, int k, Random random) {
		if (!world.isStatic)
			if ((world.getLightLevel(i, j + 1, k) < 4) && (Block.lightBlock[world.getTypeId(i, j + 1, k)] > 2))
			{
				org.bukkit.World bworld = world.getWorld();
				BlockState blockState = bworld.getBlockAt(i, j, k).getState();
				blockState.setTypeId(Block.DIRT.id);

				BlockFadeEvent event = new BlockFadeEvent(blockState.getBlock(), blockState);
				world.getServer().getPluginManager().callEvent(event);

				if (!event.isCancelled()) {
					blockState.update(true);
				}
			}
			else if (world.getLightLevel(i, j + 1, k) >= 9) {
				for (int l = 0; l < 4; l++) {
					int i1 = i + random.nextInt(3) - 1;
					int j1 = j + random.nextInt(5) - 3;
					int k1 = k + random.nextInt(3) - 1;
					int l1 = world.getTypeId(i1, j1 + 1, k1);

					if ((world.getTypeId(i1, j1, k1) != Block.DIRT.id) || (world.getLightLevel(i1, j1 + 1, k1) < 4) || (Block.lightBlock[l1] > 2))
						continue;
					org.bukkit.World bworld = world.getWorld();
					BlockState blockState = bworld.getBlockAt(i1, j1, k1).getState();
					blockState.setTypeId(this.id);

					BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), bworld.getBlockAt(i, j, k), blockState);
					world.getServer().getPluginManager().callEvent(event);

					if (!event.isCancelled())
						blockState.update(true);
				}
			}
	}

	public int getDropType(int i, Random random, int j)
	{
		return Block.DIRT.getDropType(0, random, j);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockMycel
 * JD-Core Version:		0.6.0
 */