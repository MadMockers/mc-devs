package net.minecraft.server;

import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemMinecart extends Item
{
	public int a;

	public ItemMinecart(int i, int j)
	{
		super(i);
		this.maxStackSize = 1;
		this.a = j;
		a(CreativeModeTab.e);
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int i1 = world.getTypeId(i, j, k);

		if (BlockMinecartTrack.d(i1)) {
			if (!world.isStatic)
			{
				PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, i, j, k, l, itemstack);

				if (event.isCancelled()) {
					return false;
				}

				world.addEntity(new EntityMinecart(world, i + 0.5F, j + 0.5F, k + 0.5F, this.a));
			}

			itemstack.count -= 1;
			return true;
		}
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemMinecart
 * JD-Core Version:		0.6.0
 */