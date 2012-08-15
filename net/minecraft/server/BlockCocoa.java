package net.minecraft.server;

import java.util.Random;

public class BlockCocoa extends BlockDirectional
{
	public BlockCocoa(int paramInt)
	{
		super(paramInt, 168, Material.PLANT);
		b(true);
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
			c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
		} else if (paramWorld.random.nextInt(5) == 0) {
			int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
			int j = c(i);
			if (j < 2) {
				j++;
				paramWorld.setData(paramInt1, paramInt2, paramInt3, j << 2 | d(i));
			}
		}
	}

	public boolean d(World paramWorld, int paramInt1, int paramInt2, int paramInt3) {
		int i = d(paramWorld.getData(paramInt1, paramInt2, paramInt3));

		paramInt1 += Direction.a[i];
		paramInt3 += Direction.b[i];
		int j = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3);

		return (j == Block.LOG.id) && (BlockLog.e(paramWorld.getData(paramInt1, paramInt2, paramInt3)) == 3);
	}

	public int b()
	{
		return 28;
	}

	public boolean c()
	{
		return false;
	}

	public boolean d()
	{
		return false;
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		updateShape(paramWorld, paramInt1, paramInt2, paramInt3);
		return super.e(paramWorld, paramInt1, paramInt2, paramInt3);
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);
		int j = d(i);
		int k = c(i);

		int m = 4 + k * 2;
		int n = 5 + k * 2;

		float f = m / 2.0F;

		switch (j) {
		case 0:
			a((8.0F - f) / 16.0F, (12.0F - n) / 16.0F, (15.0F - m) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
			break;
		case 2:
			a((8.0F - f) / 16.0F, (12.0F - n) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + m) / 16.0F);
			break;
		case 1:
			a(0.0625F, (12.0F - n) / 16.0F, (8.0F - f) / 16.0F, (1.0F + m) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
			break;
		case 3:
			a((15.0F - m) / 16.0F, (12.0F - n) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
		}
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = ((MathHelper.floor(paramEntityLiving.yaw * 4.0F / 360.0F + 0.5D) & 0x3) + 0) % 4;
		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if ((paramInt4 == 1) || (paramInt4 == 0)) {
			paramInt4 = 2;
		}
		int i = Direction.e[Direction.d[paramInt4]];
		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if (!d(paramWorld, paramInt1, paramInt2, paramInt3)) {
			c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
		}
	}

	public static int c(int paramInt) {
		return (paramInt & 0xC) >> 2;
	}

	public void dropNaturally(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
	{
		int i = c(paramInt4);
		int j = 1;
		if (i >= 2) {
			j = 3;
		}
		for (int k = 0; k < j; k++)
			a(paramWorld, paramInt1, paramInt2, paramInt3, new ItemStack(Item.INK_SACK, 1, 3));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockCocoa
 * JD-Core Version:		0.6.0
 */