package net.minecraft.server;

import java.util.Random;

public class BlockWoodStep extends BlockStepAbstract
{
	public static final String[] a = { "oak", "spruce", "birch", "jungle" };

	public BlockWoodStep(int paramInt, boolean paramBoolean)
	{
		super(paramInt, paramBoolean, Material.WOOD);
		a(CreativeModeTab.b);
	}

	public int a(int paramInt1, int paramInt2)
	{
		switch (paramInt2 & 0x7) {
		default:
			return 4;
		case 1:
			return 198;
		case 2:
			return 214;
		case 3:
		}return 199;
	}

	public int a(int paramInt)
	{
		return a(paramInt, 0);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Block.WOOD_STEP.id;
	}

	protected ItemStack c_(int paramInt)
	{
		return new ItemStack(Block.WOOD_STEP.id, 2, paramInt & 0x7);
	}

	public String d(int paramInt)
	{
		if ((paramInt < 0) || (paramInt >= a.length)) {
			paramInt = 0;
		}
		return super.a() + "." + a[paramInt];
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockWoodStep
 * JD-Core Version:		0.6.0
 */