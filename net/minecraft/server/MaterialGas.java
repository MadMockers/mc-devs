package net.minecraft.server;

public class MaterialGas extends Material
{
	public MaterialGas(MaterialMapColor paramMaterialMapColor)
	{
		super(paramMaterialMapColor);
		i();
	}

	public boolean isBuildable()
	{
		return false;
	}

	public boolean blocksLight()
	{
		return false;
	}

	public boolean isSolid()
	{
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MaterialGas
 * JD-Core Version:		0.6.0
 */