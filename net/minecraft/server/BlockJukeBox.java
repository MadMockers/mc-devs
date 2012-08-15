package net.minecraft.server;

import java.util.Random;

public class BlockJukeBox extends BlockContainer
{
	protected BlockJukeBox(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.WOOD);
		a(CreativeModeTab.c);
	}

	public int a(int paramInt)
	{
		return this.textureId + (paramInt == 1 ? 1 : 0);
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if (paramWorld.getData(paramInt1, paramInt2, paramInt3) == 0) return false;
		dropRecord(paramWorld, paramInt1, paramInt2, paramInt3);
		return true;
	}

	public void e(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramWorld.isStatic) return;

		TileEntityRecordPlayer localTileEntityRecordPlayer = (TileEntityRecordPlayer)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localTileEntityRecordPlayer == null) return;

		localTileEntityRecordPlayer.record = paramInt4;
		localTileEntityRecordPlayer.update();

		paramWorld.setData(paramInt1, paramInt2, paramInt3, 1);
	}

	public void dropRecord(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		if (paramWorld.isStatic) return;

		TileEntityRecordPlayer localTileEntityRecordPlayer = (TileEntityRecordPlayer)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localTileEntityRecordPlayer == null) return;

		int i = localTileEntityRecordPlayer.record;
		if (i == 0) return;

		paramWorld.triggerEffect(1005, paramInt1, paramInt2, paramInt3, 0);
		paramWorld.a(null, paramInt1, paramInt2, paramInt3);
		localTileEntityRecordPlayer.record = 0;
		localTileEntityRecordPlayer.update();
		paramWorld.setData(paramInt1, paramInt2, paramInt3, 0);

		float f = 0.7F;
		double d1 = paramWorld.random.nextFloat() * f + (1.0F - f) * 0.5D;
		double d2 = paramWorld.random.nextFloat() * f + (1.0F - f) * 0.2D + 0.6D;
		double d3 = paramWorld.random.nextFloat() * f + (1.0F - f) * 0.5D;
		EntityItem localEntityItem = new EntityItem(paramWorld, paramInt1 + d1, paramInt2 + d2, paramInt3 + d3, new ItemStack(i, 1, 0));
		localEntityItem.pickupDelay = 10;
		paramWorld.addEntity(localEntityItem);
	}

	public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		dropRecord(paramWorld, paramInt1, paramInt2, paramInt3);
		super.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
	}

	public void dropNaturally(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
	{
		if (paramWorld.isStatic) return;
		super.dropNaturally(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat, 0);
	}

	public TileEntity a(World paramWorld)
	{
		return new TileEntityRecordPlayer();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockJukeBox
 * JD-Core Version:		0.6.0
 */