package net.minecraft.server;

public class BlockWood extends Block
{
	public static final String[] a = { "oak", "spruce", "birch", "jungle" };

	public BlockWood(int paramInt)
	{
		super(paramInt, 4, Material.WOOD);
		a(CreativeModeTab.b);
	}

	public int a(int paramInt1, int paramInt2)
	{
		switch (paramInt2) {
		default:
			return 4;
		case 1:
			return 198;
		case 2:
			return 214;
		case 3:
		}return 199;
	}

	protected int getDropData(int paramInt)
	{
		return paramInt;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockWood
 * JD-Core Version:		0.6.0
 */