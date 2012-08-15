package net.minecraft.server;

public class BlockHalfTransparant extends Block
{
	private boolean a;

	protected BlockHalfTransparant(int paramInt1, int paramInt2, Material paramMaterial, boolean paramBoolean)
	{
		super(paramInt1, paramInt2, paramMaterial);
		this.a = paramBoolean;
	}

	public boolean d()
	{
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockHalfTransparant
 * JD-Core Version:		0.6.0
 */