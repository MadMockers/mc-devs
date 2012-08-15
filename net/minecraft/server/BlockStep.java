package net.minecraft.server;

import java.util.Random;

public class BlockStep extends BlockStepAbstract
{
	public static final String[] a = { "stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick" };

	public BlockStep(int paramInt, boolean paramBoolean)
	{
		super(paramInt, paramBoolean, Material.STONE);
		a(CreativeModeTab.b);
	}

	public int a(int paramInt1, int paramInt2)
	{
		int i = paramInt2 & 0x7;
		if (i == 0) {
			if (paramInt1 <= 1) return 6;
			return 5;
		}if (i == 1) {
			if (paramInt1 == 0) return 208;
			if (paramInt1 == 1) return 176;
			return 192;
		}if (i == 2)
			return 4;
		if (i == 3)
			return 16;
		if (i == 4)
			return Block.BRICK.textureId;
		if (i == 5) {
			return Block.SMOOTH_BRICK.textureId;
		}
		return 6;
	}

	public int a(int paramInt)
	{
		return a(paramInt, 0);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Block.STEP.id;
	}

	protected ItemStack c_(int paramInt)
	{
		return new ItemStack(Block.STEP.id, 2, paramInt & 0x7);
	}

	public String d(int paramInt)
	{
		if ((paramInt < 0) || (paramInt >= a.length)) {
			paramInt = 0;
		}
		return super.a() + "." + a[paramInt];
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockStep
 * JD-Core Version:		0.6.0
 */