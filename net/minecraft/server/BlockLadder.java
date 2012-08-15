package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block
{
	protected BlockLadder(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.ORIENTABLE);
		a(CreativeModeTab.c);
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		float f = 0.125F;

		if (i == 2) a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		if (i == 3) a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		if (i == 4) a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		if (i == 5) a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);

		return super.e(paramWorld, paramInt1, paramInt2, paramInt3);
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
		return 8;
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.s(paramInt1 - 1, paramInt2, paramInt3))
			return true;
		if (paramWorld.s(paramInt1 + 1, paramInt2, paramInt3))
			return true;
		if (paramWorld.s(paramInt1, paramInt2, paramInt3 - 1)) {
			return true;
		}
		return paramWorld.s(paramInt1, paramInt2, paramInt3 + 1);
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);

		if (((i == 0) || (paramInt4 == 2)) && (paramWorld.s(paramInt1, paramInt2, paramInt3 + 1))) i = 2;
		if (((i == 0) || (paramInt4 == 3)) && (paramWorld.s(paramInt1, paramInt2, paramInt3 - 1))) i = 3;
		if (((i == 0) || (paramInt4 == 4)) && (paramWorld.s(paramInt1 + 1, paramInt2, paramInt3))) i = 4;
		if (((i == 0) || (paramInt4 == 5)) && (paramWorld.s(paramInt1 - 1, paramInt2, paramInt3))) i = 5;

		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = 0;

		if ((i == 2) && (paramWorld.s(paramInt1, paramInt2, paramInt3 + 1))) j = 1;
		if ((i == 3) && (paramWorld.s(paramInt1, paramInt2, paramInt3 - 1))) j = 1;
		if ((i == 4) && (paramWorld.s(paramInt1 + 1, paramInt2, paramInt3))) j = 1;
		if ((i == 5) && (paramWorld.s(paramInt1 - 1, paramInt2, paramInt3))) j = 1;
		if (j == 0) {
			c(paramWorld, paramInt1, paramInt2, paramInt3, i, 0);
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
		}

		super.doPhysics(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public int a(Random paramRandom)
	{
		return 1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockLadder
 * JD-Core Version:		0.6.0
 */