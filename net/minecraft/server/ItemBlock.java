package net.minecraft.server;

import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockPlaceEvent;

public class ItemBlock extends Item
{
	private int id;

	public ItemBlock(int i)
	{
		super(i);
		this.id = (i + 256);
		c(Block.byId[(i + 256)].a(2));
	}

	public int f() {
		return this.id;
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		int clickedX = i; int clickedY = j; int clickedZ = k;
		int i1 = world.getTypeId(i, j, k);

		if (i1 == Block.SNOW.id) {
			l = 1;
		} else if ((i1 != Block.VINE.id) && (i1 != Block.LONG_GRASS.id) && (i1 != Block.DEAD_BUSH.id)) {
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
		}

		if (itemstack.count == 0)
			return false;
		if (!entityhuman.e(i, j, k))
			return false;
		if ((j == 255) && (Block.byId[this.id].material.isBuildable())) {
			return false;
		}

		int id = this.id;
		if ((l == -1) && ((itemstack.getItem() instanceof ItemStep))) {
			if (this.id == Block.STEP.id)
				id = Block.DOUBLE_STEP.id;
			else if (this.id == Block.WOOD_STEP.id) {
				id = Block.WOOD_DOUBLE_STEP.id;
			}
		}

		if ((id != this.id) || (world.mayPlace(this.id, i, j, k, false, l, entityhuman))) {
			Block block = Block.byId[id];

			CraftBlockState replacedBlockState = CraftBlockState.getBlockState(world, i, j, k);

			world.suppressPhysics = true;
			world.setTypeIdAndData(i, j, k, id, filterData(itemstack.getData()));
			BlockPlaceEvent event = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, replacedBlockState, clickedX, clickedY, clickedZ);
			id = world.getTypeId(i, j, k);
			int data = world.getData(i, j, k);
			replacedBlockState.update(true);
			world.suppressPhysics = false;

			if ((event.isCancelled()) || (!event.canBuild())) {
				return true;
			}
			if (world.setTypeIdAndData(i, j, k, id, data)) {
				if ((world.getTypeId(i, j, k) == id) && (Block.byId[id] != null)) {
					Block.byId[id].postPlace(world, i, j, k, l, f, f1, f2);
					Block.byId[id].postPlace(world, i, j, k, entityhuman);
				}

				world.makeSound(i + 0.5F, j + 0.5F, k + 0.5F, block.stepSound.getName(), (block.stepSound.getVolume1() + 1.0F) / 2.0F, block.stepSound.getVolume2() * 0.8F);
				itemstack.count -= 1;
			}

			return true;
		}
		return false;
	}

	public String c(ItemStack itemstack)
	{
		return Block.byId[this.id].a();
	}

	public String getName() {
		return Block.byId[this.id].a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemBlock
 * JD-Core Version:		0.6.0
 */