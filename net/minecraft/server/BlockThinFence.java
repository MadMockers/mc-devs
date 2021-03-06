package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockThinFence extends Block
{
	private int a;
	private final boolean b;

	protected BlockThinFence(int paramInt1, int paramInt2, int paramInt3, Material paramMaterial, boolean paramBoolean)
	{
		super(paramInt1, paramInt2, paramMaterial);
		this.a = paramInt3;
		this.b = paramBoolean;
		a(CreativeModeTab.c);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		if (!this.b) {
			return 0;
		}
		return super.getDropType(paramInt1, paramRandom, paramInt2);
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
		return 18;
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
	{
		boolean bool1 = e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1));
		boolean bool2 = e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1));
		boolean bool3 = e(paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3));
		boolean bool4 = e(paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3));

		if (((bool3) && (bool4)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
			a(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if ((bool3) && (!bool4)) {
			a(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if ((!bool3) && (bool4)) {
			a(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		}
		if (((bool1) && (bool2)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
			a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if ((bool1) && (!bool2)) {
			a(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if ((!bool1) && (bool2)) {
			a(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		}
	}

	public void f()
	{
		a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		float f1 = 0.4375F;
		float f2 = 0.5625F;
		float f3 = 0.4375F;
		float f4 = 0.5625F;

		boolean bool1 = e(paramIBlockAccess.getTypeId(paramInt1, paramInt2, paramInt3 - 1));
		boolean bool2 = e(paramIBlockAccess.getTypeId(paramInt1, paramInt2, paramInt3 + 1));
		boolean bool3 = e(paramIBlockAccess.getTypeId(paramInt1 - 1, paramInt2, paramInt3));
		boolean bool4 = e(paramIBlockAccess.getTypeId(paramInt1 + 1, paramInt2, paramInt3));

		if (((bool3) && (bool4)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
			f1 = 0.0F;
			f2 = 1.0F;
		} else if ((bool3) && (!bool4)) {
			f1 = 0.0F;
		} else if ((!bool3) && (bool4)) {
			f2 = 1.0F;
		}
		if (((bool1) && (bool2)) || ((!bool3) && (!bool4) && (!bool1) && (!bool2))) {
			f3 = 0.0F;
			f4 = 1.0F;
		} else if ((bool1) && (!bool2)) {
			f3 = 0.0F;
		} else if ((!bool1) && (bool2)) {
			f4 = 1.0F;
		}

		a(f1, 0.0F, f3, f2, 1.0F, f4);
	}

	public final boolean e(int paramInt)
	{
		return (Block.n[paramInt] != 0) || (paramInt == this.id) || (paramInt == Block.GLASS.id);
	}

	protected boolean q_()
	{
		return true;
	}

	protected ItemStack c_(int paramInt)
	{
		return new ItemStack(this.id, 1, paramInt);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockThinFence
 * JD-Core Version:		0.6.0
 */