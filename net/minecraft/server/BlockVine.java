package net.minecraft.server;

import java.util.Random;

public class BlockVine extends Block
{
	public BlockVine(int paramInt)
	{
		super(paramInt, 143, Material.REPLACEABLE_PLANT);
		b(true);
		a(CreativeModeTab.c);
	}

	public void f()
	{
		a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public int b()
	{
		return 20;
	}

	public boolean d()
	{
		return false;
	}

	public boolean c()
	{
		return false;
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = paramIBlockAccess.getData(paramInt1, paramInt2, paramInt3);

		float f1 = 1.0F;
		float f2 = 1.0F;
		float f3 = 1.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		int j = i > 0 ? 1 : 0;

		if ((i & 0x2) != 0) {
			f4 = Math.max(f4, 0.0625F);
			f1 = 0.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			j = 1;
		}
		if ((i & 0x8) != 0) {
			f1 = Math.min(f1, 0.9375F);
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			j = 1;
		}
		if ((i & 0x4) != 0) {
			f6 = Math.max(f6, 0.0625F);
			f3 = 0.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			j = 1;
		}
		if ((i & 0x1) != 0) {
			f3 = Math.min(f3, 0.9375F);
			f6 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			j = 1;
		}
		if ((j == 0) && (e(paramIBlockAccess.getTypeId(paramInt1, paramInt2 + 1, paramInt3)))) {
			f2 = Math.min(f2, 0.9375F);
			f5 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
		}
		a(f1, f2, f3, f4, f5, f6);
	}

	public AxisAlignedBB e(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		return null;
	}

	public boolean canPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		switch (paramInt4) {
		default:
			return false;
		case 1:
			return e(paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3));
		case 2:
			return e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 + 1));
		case 3:
			return e(paramWorld.getTypeId(paramInt1, paramInt2, paramInt3 - 1));
		case 5:
			return e(paramWorld.getTypeId(paramInt1 - 1, paramInt2, paramInt3));
		case 4:
		}return e(paramWorld.getTypeId(paramInt1 + 1, paramInt2, paramInt3));
	}

	private boolean e(int paramInt)
	{
		if (paramInt == 0) return false;
		Block localBlock = Block.byId[paramInt];
		return (localBlock.c()) && (localBlock.material.isSolid());
	}

	private boolean l(World paramWorld, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = i;

		if (j > 0) {
			for (int k = 0; k <= 3; k++) {
				int m = 1 << k;
				if (((i & m) == 0) || 
					(e(paramWorld.getTypeId(paramInt1 + Direction.a[k], paramInt2, paramInt3 + Direction.b[k])))) {
					continue;
				}
				if ((paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3) != this.id) || ((paramWorld.getData(paramInt1, paramInt2 + 1, paramInt3) & m) == 0)) {
					j &= (m ^ 0xFFFFFFFF);
				}

			}

		}

		if (j == 0)
		{
			if (!e(paramWorld.getTypeId(paramInt1, paramInt2 + 1, paramInt3))) {
				return false;
			}
		}
		if (j != i) {
			paramWorld.setData(paramInt1, paramInt2, paramInt3, j);
		}
		return true;
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((!paramWorld.isStatic) && (!l(paramWorld, paramInt1, paramInt2, paramInt3))) {
			c(paramWorld, paramInt1, paramInt2, paramInt3, paramWorld.getData(paramInt1, paramInt2, paramInt3), 0);
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
		}
	}

	public void b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
	{
		if ((!paramWorld.isStatic) && 
			(paramWorld.random.nextInt(4) == 0)) {
			int i = 4;
			int j = 5;
			int k = 0;

			for (int m = paramInt1 - i; m <= paramInt1 + i; m++) {
				for (n = paramInt3 - i; n <= paramInt3 + i; n++)
					for (i1 = paramInt2 - 1; i1 <= paramInt2 + 1; i1++) {
						if (paramWorld.getTypeId(m, i1, n) != this.id) continue; j--; if (j <= 0) {
							k = 1;
							break label121;
						}
					}
			}
			label121: m = paramWorld.getData(paramInt1, paramInt2, paramInt3);
			int n = paramWorld.random.nextInt(6);
			int i1 = Direction.d[n];
			int i2;
			int i3;
			if ((n == 1) && (paramInt2 < 255) && (paramWorld.isEmpty(paramInt1, paramInt2 + 1, paramInt3))) {
				if (k != 0) return;

				i2 = paramWorld.random.nextInt(16) & m;
				if (i2 > 0) {
					for (i3 = 0; i3 <= 3; i3++) {
						if (!e(paramWorld.getTypeId(paramInt1 + Direction.a[i3], paramInt2 + 1, paramInt3 + Direction.b[i3]))) {
							i2 &= (1 << i3 ^ 0xFFFFFFFF);
						}
					}
					if (i2 > 0)
						paramWorld.setTypeIdAndData(paramInt1, paramInt2 + 1, paramInt3, this.id, i2);
				}
			}
			else
			{
				int i4;
				if ((n >= 2) && (n <= 5) && ((m & 1 << i1) == 0)) {
					if (k != 0) return;

					i2 = paramWorld.getTypeId(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1]);

					if ((i2 == 0) || (Block.byId[i2] == null))
					{
						i3 = i1 + 1 & 0x3;
						i4 = i1 + 3 & 0x3;

						if (((m & 1 << i3) != 0) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i1] + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i3]))))
						{
							paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1], this.id, 1 << i3);
						} else if (((m & 1 << i4) != 0) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i1] + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i4]))))
						{
							paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1], this.id, 1 << i4);
						}
						else if (((m & 1 << i3) != 0) && (paramWorld.isEmpty(paramInt1 + Direction.a[i1] + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i3])) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i3]))))
						{
							paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1] + Direction.a[i3], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i3], this.id, 1 << (i1 + 2 & 0x3));
						}
						else if (((m & 1 << i4) != 0) && (paramWorld.isEmpty(paramInt1 + Direction.a[i1] + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i4])) && (e(paramWorld.getTypeId(paramInt1 + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i4]))))
						{
							paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1] + Direction.a[i4], paramInt2, paramInt3 + Direction.b[i1] + Direction.b[i4], this.id, 1 << (i1 + 2 & 0x3));
						}
						else if (e(paramWorld.getTypeId(paramInt1 + Direction.a[i1], paramInt2 + 1, paramInt3 + Direction.b[i1]))) {
							paramWorld.setTypeIdAndData(paramInt1 + Direction.a[i1], paramInt2, paramInt3 + Direction.b[i1], this.id, 0);
						}
					}
					else if ((Block.byId[i2].material.k()) && (Block.byId[i2].c()))
					{
						paramWorld.setData(paramInt1, paramInt2, paramInt3, m | 1 << i1);
					}

				}
				else if (paramInt2 > 1) {
					i2 = paramWorld.getTypeId(paramInt1, paramInt2 - 1, paramInt3);

					if (i2 == 0) {
						i3 = paramWorld.random.nextInt(16) & m;
						if (i3 > 0)
							paramWorld.setTypeIdAndData(paramInt1, paramInt2 - 1, paramInt3, this.id, i3);
					}
					else if (i2 == this.id) {
						i3 = paramWorld.random.nextInt(16) & m;
						i4 = paramWorld.getData(paramInt1, paramInt2 - 1, paramInt3);
						if (i4 != (i4 | i3))
							paramWorld.setData(paramInt1, paramInt2 - 1, paramInt3, i4 | i3);
					}
				}
			}
		}
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		int i = 0;
		switch (paramInt4) {
		case 2:
			i = 1;
			break;
		case 3:
			i = 4;
			break;
		case 4:
			i = 8;
			break;
		case 5:
			i = 2;
		}

		if (i != 0)
			paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return 0;
	}

	public int a(Random paramRandom)
	{
		return 0;
	}

	public void a(World paramWorld, EntityHuman paramEntityHuman, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((!paramWorld.isStatic) && (paramEntityHuman.bC() != null) && (paramEntityHuman.bC().id == Item.SHEARS.id)) {
			paramEntityHuman.a(StatisticList.C[this.id], 1);

			a(paramWorld, paramInt1, paramInt2, paramInt3, new ItemStack(Block.VINE, 1, 0));
		} else {
			super.a(paramWorld, paramEntityHuman, paramInt1, paramInt2, paramInt3, paramInt4);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockVine
 * JD-Core Version:		0.6.0
 */