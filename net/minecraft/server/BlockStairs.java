package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockStairs extends Block
{
	private static final int[][] a = { { 2, 6 }, { 3, 7 }, { 2, 3 }, { 6, 7 }, { 0, 4 }, { 1, 5 }, { 0, 1 }, { 4, 5 } };
	private final Block b;
	private final int c;
	private boolean cr = false;
	private int cs = 0;

	protected BlockStairs(int paramInt1, Block paramBlock, int paramInt2) {
		super(paramInt1, paramBlock.textureId, paramBlock.material);
		this.b = paramBlock;
		this.c = paramInt2;
		c(paramBlock.strength);
		b(paramBlock.durability / 3.0F);
		a(paramBlock.stepSound);
		h(255);
		a(CreativeModeTab.b);
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		if (this.cr)
			a(0.5F * (this.cs % 2), 0.5F * (this.cs / 2 % 2), 0.5F * (this.cs / 4 % 2), 0.5F + 0.5F * (this.cs % 2), 0.5F + 0.5F * (this.cs / 2 % 2), 0.5F + 0.5F * (this.cs / 4 % 2));
		else
			a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
		return 10;
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, AxisAlignedBB paramAxisAlignedBB, List paramList, Entity paramEntity)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = i & 0x3;
		float f1 = 0.0F;
		float f2 = 0.5F;
		float f3 = 0.5F;
		float f4 = 1.0F;

		if ((i & 0x4) != 0) {
			f1 = 0.5F;
			f2 = 1.0F;
			f3 = 0.0F;
			f4 = 0.5F;
		}

		a(0.0F, f1, 0.0F, 1.0F, f2, 1.0F);
		super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);

		if (j == 0) {
			a(0.5F, f3, 0.0F, 1.0F, f4, 1.0F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if (j == 1) {
			a(0.0F, f3, 0.0F, 0.5F, f4, 1.0F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if (j == 2) {
			a(0.0F, f3, 0.5F, 1.0F, f4, 1.0F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		} else if (j == 3) {
			a(0.0F, f3, 0.0F, 1.0F, f4, 0.5F);
			super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramAxisAlignedBB, paramList, paramEntity);
		}

		a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void attack(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman)
	{
		this.b.attack(paramWorld, paramInt1, paramInt2, paramInt3, paramEntityHuman);
	}

	public void postBreak(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		this.b.postBreak(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public float a(Entity paramEntity)
	{
		return this.b.a(paramEntity);
	}

	public int a(int paramInt1, int paramInt2)
	{
		return this.b.a(paramInt1, this.c);
	}

	public int a(int paramInt)
	{
		return this.b.a(paramInt, this.c);
	}

	public int p_()
	{
		return this.b.p_();
	}

	public void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity, Vec3D paramVec3D)
	{
		this.b.a(paramWorld, paramInt1, paramInt2, paramInt3, paramEntity, paramVec3D);
	}

	public boolean l()
	{
		return this.b.l();
	}

	public boolean a(int paramInt, boolean paramBoolean)
	{
		return this.b.a(paramInt, paramBoolean);
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		return this.b.canPlace(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void onPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		doPhysics(paramWorld, paramInt1, paramInt2, paramInt3, 0);
		this.b.onPlace(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		this.b.remove(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Entity paramEntity)
	{
		this.b.b(paramWorld, paramInt1, paramInt2, paramInt3, paramEntity);
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		this.b.b(paramWorld, paramInt1, paramInt2, paramInt3, paramRandom);
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		return this.b.interact(paramWorld, paramInt1, paramInt2, paramInt3, paramEntityHuman, 0, 0.0F, 0.0F, 0.0F);
	}

	public void wasExploded(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		this.b.wasExploded(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3;
		int j = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x4;

		if (i == 0) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x2 | j);
		if (i == 1) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x1 | j);
		if (i == 2) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x3 | j);
		if (i == 3) paramWorld.setData(paramInt1, paramInt2, paramInt3, 0x0 | j);
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if ((paramInt4 == 0) || ((paramInt4 != 1) && (paramFloat2 > 0.5D))) {
			int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
			paramWorld.setData(paramInt1, paramInt2, paramInt3, i | 0x4);
		}
	}

	public MovingObjectPosition a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Vec3D paramVec3D1, Vec3D paramVec3D2)
	{
		MovingObjectPosition[] arrayOfMovingObjectPosition1 = new MovingObjectPosition[8];
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = i & 0x3;
		int k = (i & 0x4) == 4 ? 1 : 0;
		int[] arrayOfInt1 = a[(j + 0)];

		this.cr = true;
		int i3;
		for (int m = 0; m < 8; m++) {
			this.cs = m;

			for (i3 : arrayOfInt1) {
				if (i3 == m)
					continue;
			}
			arrayOfMovingObjectPosition1[m] = super.a(paramWorld, paramInt1, paramInt2, paramInt3, paramVec3D1, paramVec3D2);
		}

		for (??? : arrayOfInt1) {
			arrayOfMovingObjectPosition1[???] = null;
		}

		??? = null;
		double d1 = 0.0D;

		for (MovingObjectPosition localMovingObjectPosition : arrayOfMovingObjectPosition1) {
			if (localMovingObjectPosition != null) {
				double d2 = localMovingObjectPosition.pos.distanceSquared(paramVec3D2);

				if (d2 > d1) {
					??? = localMovingObjectPosition;
					d1 = d2;
				}
			}
		}

		return (MovingObjectPosition)???;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockStairs
 * JD-Core Version:		0.6.0
 */