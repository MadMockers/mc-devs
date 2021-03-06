package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstoneLamp extends Block
{
	private final boolean a;

	public BlockRedstoneLamp(int i, boolean flag)
	{
		super(i, 211, Material.BUILDABLE_GLASS);
		this.a = flag;
		if (flag) {
			a(1.0F);
			this.textureId += 1;
		}
	}

	public void onPlace(World world, int i, int j, int k) {
		if (!world.isStatic)
			if ((this.a) && (!world.isBlockIndirectlyPowered(i, j, k))) {
				world.a(i, j, k, this.id, 4);
			} else if ((!this.a) && (world.isBlockIndirectlyPowered(i, j, k)))
			{
				if (CraftEventFactory.callRedstoneChange(world, i, j, k, 0, 15).getNewCurrent() != 15) {
					return;
				}

				world.setTypeId(i, j, k, Block.REDSTONE_LAMP_ON.id);
			}
	}

	public void doPhysics(World world, int i, int j, int k, int l)
	{
		if (!world.isStatic)
			if ((this.a) && (!world.isBlockIndirectlyPowered(i, j, k))) {
				world.a(i, j, k, this.id, 4);
			} else if ((!this.a) && (world.isBlockIndirectlyPowered(i, j, k)))
			{
				if (CraftEventFactory.callRedstoneChange(world, i, j, k, 0, 15).getNewCurrent() != 15) {
					return;
				}

				world.setTypeId(i, j, k, Block.REDSTONE_LAMP_ON.id);
			}
	}

	public void b(World world, int i, int j, int k, Random random)
	{
		if ((!world.isStatic) && (this.a) && (!world.isBlockIndirectlyPowered(i, j, k)))
		{
			if (CraftEventFactory.callRedstoneChange(world, i, j, k, 15, 0).getNewCurrent() != 0) {
				return;
			}

			world.setTypeId(i, j, k, Block.REDSTONE_LAMP_OFF.id);
		}
	}

	public int getDropType(int i, Random random, int j) {
		return Block.REDSTONE_LAMP_OFF.id;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockRedstoneLamp
 * JD-Core Version:		0.6.0
 */