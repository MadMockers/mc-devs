package net.minecraft.server;

public abstract interface IBlockAccess
{
	public abstract int getTypeId(int paramInt1, int paramInt2, int paramInt3);

	public abstract TileEntity getTileEntity(int paramInt1, int paramInt2, int paramInt3);

	public abstract int getData(int paramInt1, int paramInt2, int paramInt3);

	public abstract Material getMaterial(int paramInt1, int paramInt2, int paramInt3);

	public abstract boolean s(int paramInt1, int paramInt2, int paramInt3);
}

/* 
 * Qualified Name:		 net.minecraft.server.IBlockAccess
 * JD-Core Version:		0.6.0
 */