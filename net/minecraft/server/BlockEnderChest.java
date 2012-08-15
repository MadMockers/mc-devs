package net.minecraft.server;

import java.util.Random;

public class BlockEnderChest extends BlockContainer
{
	protected BlockEnderChest(int paramInt)
	{
		super(paramInt, Material.STONE);
		this.textureId = 37;
		a(CreativeModeTab.c);
	}

	public boolean d()
	{
		return false;
	}

	public boolean c()
	{
		return false;
	}

	public int b()
	{
		return 22;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Block.OBSIDIAN.id;
	}

	public int a(Random paramRandom)
	{
		return 8;
	}

	protected boolean q_()
	{
		return true;
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = 0;
		int j = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;

		if (j == 0) i = 2;
		if (j == 1) i = 5;
		if (j == 2) i = 3;
		if (j == 3) i = 4;

		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		InventoryEnderChest localInventoryEnderChest = paramEntityHuman.getEnderChest();
		TileEntityEnderChest localTileEntityEnderChest = (TileEntityEnderChest)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if ((localInventoryEnderChest == null) || (localTileEntityEnderChest == null)) return true;

		if (paramWorld.s(paramInt1, paramInt2 + 1, paramInt3)) return true;

		if (paramWorld.isStatic) {
			return true;
		}

		localInventoryEnderChest.a(localTileEntityEnderChest);
		paramEntityHuman.openContainer(localInventoryEnderChest);

		return true;
	}

	public TileEntity a(World paramWorld)
	{
		return new TileEntityEnderChest();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockEnderChest
 * JD-Core Version:		0.6.0
 */