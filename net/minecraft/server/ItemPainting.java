package net.minecraft.server;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.plugin.PluginManager;

public class ItemPainting extends Item
{
	public ItemPainting(int i)
	{
		super(i);
		a(CreativeModeTab.c);
	}

	public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
		if (l == 0)
			return false;
		if (l == 1) {
			return false;
		}
		byte b0 = 0;

		if (l == 4) {
			b0 = 1;
		}

		if (l == 3) {
			b0 = 2;
		}

		if (l == 5) {
			b0 = 3;
		}

		if (!entityhuman.e(i, j, k)) {
			return false;
		}
		EntityPainting entitypainting = new EntityPainting(world, i, j, k, b0);

		if (entitypainting.survives()) {
			if (!world.isStatic)
			{
				Player who = entityhuman == null ? null : (Player)entityhuman.getBukkitEntity();

				Block blockClicked = world.getWorld().getBlockAt(i, j, k);
				BlockFace blockFace = CraftBlock.notchToBlockFace(l);

				PaintingPlaceEvent event = new PaintingPlaceEvent((Painting)entitypainting.getBukkitEntity(), who, blockClicked, blockFace);
				world.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) {
					return false;
				}

				world.addEntity(entitypainting);
			}

			itemstack.count -= 1;
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemPainting
 * JD-Core Version:		0.6.0
 */