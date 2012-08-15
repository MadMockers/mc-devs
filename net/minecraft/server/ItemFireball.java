package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.plugin.PluginManager;

public class ItemFireball extends Item
{
	public ItemFireball(int i)
	{
		super(i);
		a(CreativeModeTab.f);
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		if (world.isStatic) {
			return true;
		}
		if (l == 0) {
			j--;
		}

		if (l == 1) {
			j++;
		}

		if (l == 2) {
			k--;
		}

		if (l == 3) {
			k++;
		}

		if (l == 4) {
			i--;
		}

		if (l == 5) {
			i++;
		}

		if (!entityhuman.e(i, j, k)) {
			return false;
		}
		int i1 = world.getTypeId(i, j, k);

		if (i1 == 0)
		{
			org.bukkit.block.Block blockClicked = world.getWorld().getBlockAt(i, j, k);
			Player thePlayer = (Player)entityhuman.getBukkitEntity();

			BlockIgniteEvent eventIgnite = new BlockIgniteEvent(blockClicked, BlockIgniteEvent.IgniteCause.FIREBALL, thePlayer);
			world.getServer().getPluginManager().callEvent(eventIgnite);

			if (eventIgnite.isCancelled()) {
				if (!entityhuman.abilities.canInstantlyBuild) {
					itemstack.count -= 1;
				}
				return false;
			}

			world.makeSound(i + 0.5D, j + 0.5D, k + 0.5D, "fire.ignite", 1.0F, d.nextFloat() * 0.4F + 0.8F);
			world.setTypeId(i, j, k, Block.FIRE.id);
		}

		if (!entityhuman.abilities.canInstantlyBuild) {
			itemstack.count -= 1;
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemFireball
 * JD-Core Version:		0.6.0
 */