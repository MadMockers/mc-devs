package net.minecraft.server;

import java.util.Random;

public class BlockTorch extends Block
{
	protected BlockTorch(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, Material.ORIENTABLE);
		b(true);
		a(CreativeModeTab.c);
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		return null;
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
		return 2;
	}

	private boolean l(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		if (paramWorld.t(paramInt1, paramInt2, paramInt3)) {
			return true;
		}
		int i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);

		return (i == Block.FENCE.id) || (i == Block.NETHER_FENCE.id) || (i == Block.GLASS.id);
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true))
			return true;
		if (paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true))
			return true;
		if (paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true))
			return true;
		if (paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true)) {
			return true;
		}
		return l(paramWorld, paramInt1, paramInt2 - 1, paramInt3);
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);

		if ((paramInt4 == 1) && (l(paramWorld, paramInt1, paramInt2 - 1, paramInt3))) i = 5;
		if ((paramInt4 == 2) && (paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true))) i = 4;
		if ((paramInt4 == 3) && (paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true))) i = 3;
		if ((paramInt4 == 4) && (paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true))) i = 2;
		if ((paramInt4 == 5) && (paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true))) i = 1;

		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		super.b(paramWorld, paramInt1, paramInt2, paramInt3, paramRandom);
		if (paramWorld.getData(paramInt1, paramInt2, paramInt3) == 0) onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		if (paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true))
			paramWorld.setData(paramInt1, paramInt2, paramInt3, 1);
		else if (paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true))
			paramWorld.setData(paramInt1, paramInt2, paramInt3, 2);
		else if (paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true))
			paramWorld.setData(paramInt1, paramInt2, paramInt3, 3);
		else if (paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true))
			paramWorld.setData(paramInt1, paramInt2, paramInt3, 4);
		else if (l(paramWorld, paramInt1, paramInt2 - 1, paramInt3)) {
			paramWorld.setData(paramInt1, paramInt2, paramInt3, 5);
		}
		n(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (n(paramWorld, paramInt1, paramInt2, paramInt3)) {
			int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
			int j = 0;

			if ((!paramWorld.b(paramInt1 - 1, paramInt2, paramInt3, true)) && (i == 1)) j = 1;
			if ((!paramWorld.b(paramInt1 + 1, paramInt2, paramInt3, true)) && (i == 2)) j = 1;
			if ((!paramWorld.b(paramInt1, paramInt2, paramInt3 - 1, true)) && (i == 3)) j = 1;
			if ((!paramWorld.b(paramInt1, paramInt2, paramInt3 + 1, true)) && (i == 4)) j = 1;
			if ((!l(paramWorld, paramInt1, paramInt2 - 1, paramInt3)) && (i == 5)) j = 1;

			if (j != 0) {
				c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
				paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
			}
		}
	}

	private boolean n(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		if (!canPlace(paramWorld, paramInt1, paramInt2, paramInt3)) {
			if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) == this.id) {
				c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
				paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
			}
			return false;
		}
		return true;
	}

	public MovingObjectPosition a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Vec3D paramVec3D1, Vec3D paramVec3D2)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x7;

		float f = 0.15F;
		if (i == 1) {
			a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
		} else if (i == 2) {
			a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
		} else if (i == 3) {
			a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
		} else if (i == 4) {
			a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
		} else {
			f = 0.1F;
			a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
		}

		return super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramVec3D1, paramVec3D2);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockTorch
 * JD-Core Version:		0.6.0
 */