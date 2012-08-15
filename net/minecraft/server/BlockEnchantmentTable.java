package net.minecraft.server;

public class BlockEnchantmentTable extends BlockContainer
{
	protected BlockEnchantmentTable(int paramInt)
	{
		super(paramInt, 166, Material.STONE);
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		h(0);
		a(CreativeModeTab.c);
	}

	public boolean c()
	{
		return false;
	}

	public boolean d()
	{
		return false;
	}

	public int a(int paramInt1, int paramInt2)
	{
		return a(paramInt1);
	}

	public int a(int paramInt)
	{
		if (paramInt == 0) return this.textureId + 17;
		if (paramInt == 1) return this.textureId;
		return this.textureId + 16;
	}

	public TileEntity a(World paramWorld)
	{
		return new TileEntityEnchantTable();
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if (paramWorld.isStatic) {
			return true;
		}
		paramEntityHuman.startEnchanting(paramInt1, paramInt2, paramInt3);
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockEnchantmentTable
 * JD-Core Version:		0.6.0
 */