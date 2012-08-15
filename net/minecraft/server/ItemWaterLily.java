package net.minecraft.server;

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

public class ItemWaterLily extends ItemWithAuxData
{
	public ItemWaterLily(int i)
	{
		super(i, false);
	}

	public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
		MovingObjectPosition movingobjectposition = a(world, entityhuman, true);

		if (movingobjectposition == null) {
			return itemstack;
		}
		if (movingobjectposition.type == EnumMovingObjectType.TILE) {
			int i = movingobjectposition.b;
			int j = movingobjectposition.c;
			int k = movingobjectposition.d;

			if (!world.a(entityhuman, i, j, k)) {
				return itemstack;
			}

			if (!entityhuman.e(i, j, k)) {
				return itemstack;
			}

			if ((world.getMaterial(i, j, k) == Material.WATER) && (world.getData(i, j, k) == 0) && (world.isEmpty(i, j + 1, k))) {
				CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j + 1, k);

				world.setTypeId(i, j + 1, k, Block.WATER_LILY.id);

				BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k);

				if ((event.isCancelled()) || (!event.canBuild())) {
					event.getBlockPlaced().setTypeId(0);
					return itemstack;
				}

				if (!entityhuman.abilities.canInstantlyBuild) {
					itemstack.count -= 1;
				}
			}
		}

		return itemstack;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemWaterLily
 * JD-Core Version:		0.6.0
 */