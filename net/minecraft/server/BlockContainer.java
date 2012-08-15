package net.minecraft.server;

public abstract class BlockContainer extends Block
{
	protected BlockContainer(int paramInt, Material paramMaterial)
	{
		super(paramInt, paramMaterial);
		this.isTileEntity = true;
	}

	protected BlockContainer(int paramInt1, int paramInt2, Material paramMaterial) {
		super(paramInt1, paramInt2, paramMaterial);
		this.isTileEntity = true;
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		super.onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
		paramWorld.setTileEntity(paramInt1, paramInt2, paramInt3, a(paramWorld));
	}

	public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		paramWorld.q(paramInt1, paramInt2, paramInt3);
	}

	public abstract TileEntity a(World paramWorld);

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
		super.b(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
		TileEntity localTileEntity = paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localTileEntity != null)
			localTileEntity.b(paramInt4, paramInt5);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockContainer
 * JD-Core Version:		0.6.0
 */