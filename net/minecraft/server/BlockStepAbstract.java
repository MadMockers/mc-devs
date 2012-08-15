package net.minecraft.server;

import java.util.List;
import java.util.Random;

public abstract class BlockStepAbstract extends Block
{
	private final boolean a;

	public BlockStepAbstract(int paramInt, boolean paramBoolean, Material paramMaterial)
	{
		super(paramInt, 6, paramMaterial);
		this.a = paramBoolean;

		if (paramBoolean)
			n[paramInt] = true;
		else {
			a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
		h(255);
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		if (this.a) {
			a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			int i = (paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3) & 0x8) != 0 ? 1 : 0;
			if (i != 0)
				a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
			else
				a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
	}

	public void f()
	{
		if (this.a)
			a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		else
			a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
	{
		updateShape(paramWorld, paramInt1, paramInt2, paramInt3);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
	}

	public int a(int paramInt)
	{
		return a(paramInt, 0);
	}

	public boolean d()
	{
		return this.a;
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if (this.a) return;

		if ((paramInt4 == 0) || ((paramInt4 != 1) && (paramFloat2 > 0.5D))) {
			int i = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x7;
			paramWorld.setData(paramInt1, paramInt2, paramInt3, i | 0x8);
		}
	}

	public int a(Random paramRandom)
	{
		if (this.a) {
			return 2;
		}
		return 1;
	}

	protected int getDropData(int paramInt)
	{
		return paramInt & 0x7;
	}

	public boolean c()
	{
		return this.a;
	}

	public abstract String d(int paramInt);
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockStepAbstract
 * JD-Core Version:		0.6.0
 */