package net.minecraft.server;

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

public class ItemDoor extends Item
{
	private Material a;

	public ItemDoor(int i, Material material)
	{
		super(i);
		this.a = material;
		this.maxStackSize = 1;
		a(CreativeModeTab.d);
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		if (l != 1) {
			return false;
		}
		j++;
		Block block;
		Block block;
		if (this.a == Material.WOOD)
			block = Block.WOODEN_DOOR;
		else {
			block = Block.IRON_DOOR_BLOCK;
		}

		if ((entityhuman.e(i, j, k)) && (entityhuman.e(i, j + 1, k))) {
			if (!block.canPlace(world, i, j, k)) {
				return false;
			}
			int i1 = MathHelper.floor((entityhuman.yaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;

			if (!place(world, i, j, k, i1, block, entityhuman)) {
				return false;
			}

			itemstack.count -= 1;
			return true;
		}

		return false;
	}

	public static void place(World world, int i, int j, int k, int l, Block block)
	{
		place(world, i, j, k, l, block, null);
	}

	public static boolean place(World world, int i, int j, int k, int l, Block block, EntityHuman entityhuman)
	{
		byte b0 = 0;
		byte b1 = 0;

		if (l == 0) {
			b1 = 1;
		}

		if (l == 1) {
			b0 = -1;
		}

		if (l == 2) {
			b1 = -1;
		}

		if (l == 3) {
			b0 = 1;
		}

		int i1 = (world.s(i - b0, j, k - b1) ? 1 : 0) + (world.s(i - b0, j + 1, k - b1) ? 1 : 0);
		int j1 = (world.s(i + b0, j, k + b1) ? 1 : 0) + (world.s(i + b0, j + 1, k + b1) ? 1 : 0);
		boolean flag = (world.getTypeId(i - b0, j, k - b1) == block.id) || (world.getTypeId(i - b0, j + 1, k - b1) == block.id);
		boolean flag1 = (world.getTypeId(i + b0, j, k + b1) == block.id) || (world.getTypeId(i + b0, j + 1, k + b1) == block.id);
		boolean flag2 = false;

		if ((flag) && (!flag1))
			flag2 = true;
		else if (j1 > i1) {
			flag2 = true;
		}

		CraftBlockState blockState = CraftBlockState.getBlockState(world, i, j, k);
		world.suppressPhysics = true;
		world.setTypeIdAndData(i, j, k, block.id, l);

		if (entityhuman != null) {
			BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, blockState, i, j, k);

			if ((event.isCancelled()) || (!event.canBuild())) {
				event.getBlockPlaced().setTypeIdAndData(blockState.getTypeId(), blockState.getRawData(), false);
				return false;
			}
		}

		world.setTypeIdAndData(i, j + 1, k, block.id, 0x8 | (flag2 ? 1 : 0));
		world.suppressPhysics = false;
		world.applyPhysics(i, j, k, block.id);
		world.applyPhysics(i, j + 1, k, block.id);
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemDoor
 * JD-Core Version:		0.6.0
 */