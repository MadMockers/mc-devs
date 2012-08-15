package net.minecraft.server;

public class BlockSmoothBrick extends Block
{
	public static final String[] a = { "default", "mossy", "cracked", "chiseled" };

	public BlockSmoothBrick(int paramInt)
	{
		super(paramInt, 54, Material.STONE);
		a(CreativeModeTab.b);
	}

	public int a(int paramInt1, int paramInt2)
	{
		switch (paramInt2) {
		default:
			return 54;
		case 1:
			return 100;
		case 2:
			return 101;
		case 3:
		}return 213;
	}

	protected int getDropData(int paramInt)
	{
		return paramInt;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockSmoothBrick
 * JD-Core Version:		0.6.0
 */