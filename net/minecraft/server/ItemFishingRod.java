package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.plugin.PluginManager;

public class ItemFishingRod extends Item
{
	public ItemFishingRod(int i)
	{
		super(i);
		setMaxDurability(64);
		d(1);
		a(CreativeModeTab.i);
	}

	public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
		if (entityhuman.hookedFish != null) {
			int i = entityhuman.hookedFish.d();

			itemstack.damage(i, entityhuman);
			entityhuman.i();
		}
		else {
			PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)entityhuman.getBukkitEntity(), null, PlayerFishEvent.State.FISHING);
			world.getServer().getPluginManager().callEvent(playerFishEvent);

			if (playerFishEvent.isCancelled()) {
				return itemstack;
			}

			world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
			if (!world.isStatic) {
				world.addEntity(new EntityFishingHook(world, entityhuman));
			}

			entityhuman.i();
		}

		return itemstack;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemFishingRod
 * JD-Core Version:		0.6.0
 */