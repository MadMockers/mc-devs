package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.PluginManager;

public class BlockBloodStone extends Block
{
	public BlockBloodStone(int i, int j)
	{
		super(i, j, Material.STONE);
		a(CreativeModeTab.b);
	}

	public void doPhysics(World world, int i, int j, int k, int l)
	{
		if ((Block.byId[l] != null) && (Block.byId[l].isPowerSource())) {
			org.bukkit.block.Block block = world.getWorld().getBlockAt(i, j, k);
			int power = block.getBlockPower();

			BlockRedstoneEvent event = new BlockRedstoneEvent(block, power, power);
			world.getServer().getPluginManager().callEvent(event);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockBloodStone
 * JD-Core Version:		0.6.0
 */