package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockFadeEvent;

public class BlockIce extends BlockHalfTransparant
{
	public BlockIce(int i, int j)
	{
		super(i, j, Material.ICE, false);
		this.frictionFactor = 0.98F;
		b(true);
		a(CreativeModeTab.b);
	}

	public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
		entityhuman.a(StatisticList.C[this.id], 1);
		entityhuman.j(0.025F);
		if ((q_()) && (EnchantmentManager.hasSilkTouchEnchantment(entityhuman.inventory))) {
			ItemStack itemstack = c_(l);

			if (itemstack != null)
				a(world, i, j, k, itemstack);
		}
		else {
			if (world.worldProvider.d) {
				world.setTypeId(i, j, k, 0);
				return;
			}

			int i1 = EnchantmentManager.getBonusBlockLootEnchantmentLevel(entityhuman.inventory);

			c(world, i, j, k, l, i1);
			Material material = world.getMaterial(i, j - 1, k);

			if ((material.isSolid()) || (material.isLiquid()))
				world.setTypeId(i, j, k, Block.WATER.id);
		}
	}

	public int a(Random random)
	{
		return 0;
	}

	public void b(World world, int i, int j, int k, Random random) {
		if (world.b(EnumSkyBlock.BLOCK, i, j, k) > 11 - Block.lightBlock[this.id])
		{
			if (CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(i, j, k), Block.STATIONARY_WATER.id).isCancelled()) {
				return;
			}

			if (world.worldProvider.d) {
				world.setTypeId(i, j, k, 0);
				return;
			}

			c(world, i, j, k, world.getData(i, j, k), 0);
			world.setTypeId(i, j, k, Block.STATIONARY_WATER.id);
		}
	}

	public int e() {
		return 0;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockIce
 * JD-Core Version:		0.6.0
 */