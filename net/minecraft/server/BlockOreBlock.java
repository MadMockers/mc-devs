package net.minecraft.server;

public class BlockOreBlock extends Block
{
	public BlockOreBlock(int paramInt1, int paramInt2)
	{
		super(paramInt1, Material.ORE);
		this.textureId = paramInt2;
		a(CreativeModeTab.b);
	}

	public int a(int paramInt)
	{
		return this.textureId;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockOreBlock
 * JD-Core Version:		0.6.0
 */