package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockEnderPortal extends BlockContainer
{
	public static boolean a = false;

	protected BlockEnderPortal(int paramInt, Material paramMaterial) {
		super(paramInt, 0, paramMaterial);
		a(1.0F);
	}

	public TileEntity a(World paramWorld)
	{
		return new TileEntityEnderPortal();
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		float f = 0.0625F;
		a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
	{
	}

	public boolean d()
	{
		return false;
	}

	public boolean c()
	{
		return false;
	}

	public int a(Random paramRandom)
	{
		return 0;
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity)
	{
		if ((paramEntity.vehicle == null) && (paramEntity.passenger == null) && 
			((paramEntity instanceof EntityHuman)) && 
			(!paramWorld.isStatic))
			((EntityHuman)paramEntity).c(1);
	}

	public int b()
	{
		return -1;
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (a) return;

		if (paramWorld.worldProvider.dimension != 0)
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.BlockEnderPortal
 * JD-Core Version:		0.6.0
 */