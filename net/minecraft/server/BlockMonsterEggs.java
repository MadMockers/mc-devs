package net.minecraft.server;

import java.util.Random;

public class BlockMonsterEggs extends Block
{
	public static final String[] a = { "stone", "cobble", "brick" };

	public BlockMonsterEggs(int paramInt)
	{
		super(paramInt, 1, Material.CLAY);

		c(0.0F);
		a(CreativeModeTab.c);
	}

	public int a(int paramInt1, int paramInt2)
	{
		if (paramInt2 == 1) {
			return Block.COBBLESTONE.textureId;
		}
		if (paramInt2 == 2) {
			return Block.SMOOTH_BRICK.textureId;
		}
		return Block.STONE.textureId;
	}

	public void postBreak(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (!paramWorld.isStatic) {
			EntitySilverfish localEntitySilverfish = new EntitySilverfish(paramWorld);
			localEntitySilverfish.setPositionRotation(paramInt1 + 0.5D, paramInt2, paramInt3 + 0.5D, 0.0F, 0.0F);
			paramWorld.addEntity(localEntitySilverfish);

			localEntitySilverfish.aK();
		}
		super.postBreak(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public int a(Random paramRandom)
	{
		return 0;
	}

	public static boolean e(int paramInt) {
		return (paramInt == Block.STONE.id) || (paramInt == Block.COBBLESTONE.id) || (paramInt == Block.SMOOTH_BRICK.id);
	}

	public static int f(int paramInt) {
		if (paramInt == Block.COBBLESTONE.id) {
			return 1;
		}
		if (paramInt == Block.SMOOTH_BRICK.id) {
			return 2;
		}
		return 0;
	}

	protected ItemStack c_(int paramInt)
	{
		Block localBlock = Block.STONE;
		if (paramInt == 1) {
			localBlock = Block.COBBLESTONE;
		}
		if (paramInt == 2) {
			localBlock = Block.SMOOTH_BRICK;
		}
		return new ItemStack(localBlock);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockMonsterEggs
 * JD-Core Version:		0.6.0
 */