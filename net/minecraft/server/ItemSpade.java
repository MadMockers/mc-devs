package net.minecraft.server;

public class ItemSpade extends ItemTool
{
	private static Block[] c = { Block.GRASS, Block.DIRT, Block.SAND, Block.GRAVEL, Block.SNOW, Block.SNOW_BLOCK, Block.CLAY, Block.SOIL, Block.SOUL_SAND, Block.MYCEL };

	public ItemSpade(int paramInt, EnumToolMaterial paramEnumToolMaterial)
	{
		super(paramInt, 1, paramEnumToolMaterial, c);
	}

	public boolean canDestroySpecialBlock(Block paramBlock)
	{
		if (paramBlock == Block.SNOW) return true;
		return paramBlock == Block.SNOW_BLOCK;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemSpade
 * JD-Core Version:		0.6.0
 */