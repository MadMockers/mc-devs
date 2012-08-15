package net.minecraft.server;

public class BlockSandStone extends Block
{
	public static final String[] a = { "default", "chiseled", "smooth" };

	public BlockSandStone(int paramInt)
	{
		super(paramInt, 192, Material.STONE);
		a(CreativeModeTab.b);
	}

	public int a(int paramInt1, int paramInt2)
	{
		if ((paramInt1 == 1) || ((paramInt1 == 0) && ((paramInt2 == 1) || (paramInt2 == 2)))) {
			return 176;
		}
		if (paramInt1 == 0) {
			return 208;
		}
		if (paramInt2 == 1) {
			return 229;
		}
		if (paramInt2 == 2) {
			return 230;
		}
		return 192;
	}

	public int a(int paramInt)
	{
		if (paramInt == 1) {
			return this.textureId - 16;
		}
		if (paramInt == 0) {
			return this.textureId + 16;
		}
		return this.textureId;
	}

	protected int getDropData(int paramInt)
	{
		return paramInt;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockSandStone
 * JD-Core Version:		0.6.0
 */