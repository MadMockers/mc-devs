package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

public class ItemSeeds extends Item
{
	private int id;
	private int b;

	public ItemSeeds(int i, int j, int k)
	{
		super(i);
		this.id = j;
		this.b = k;
		a(CreativeModeTab.l);
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		if (l != 1)
			return false;
		if ((entityhuman.e(i, j, k)) && (entityhuman.e(i, j + 1, k))) {
			int i1 = world.getTypeId(i, j, k);

			if ((i1 == this.b) && (world.isEmpty(i, j + 1, k))) {
				CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j + 1, k);

				world.setTypeId(i, j + 1, k, this.id);

				BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k);

				if ((event.isCancelled()) || (!event.canBuild())) {
					event.getBlockPlaced().setTypeId(0);
					return false;
				}

				itemstack.count -= 1;
				return true;
			}
			return false;
		}

		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemSeeds
 * JD-Core Version:		0.6.0
 */