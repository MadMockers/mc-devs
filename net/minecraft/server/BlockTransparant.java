package net.minecraft.server;

public class BlockTransparant extends Block
{
	protected boolean c;

	protected BlockTransparant(int paramInt1, int paramInt2, Material paramMaterial, boolean paramBoolean)
	{
		super(paramInt1, paramInt2, paramMaterial);
		this.c = paramBoolean;
	}

	public boolean d()
	{
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockTransparant
 * JD-Core Version:		0.6.0
 */