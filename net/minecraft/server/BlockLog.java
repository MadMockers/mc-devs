package net.minecraft.server;

import java.util.Random;

public class BlockLog extends Block
{
	public static final String[] a = { "oak", "spruce", "birch", "jungle" };

	protected BlockLog(int paramInt)
	{
		super(paramInt, Material.WOOD);
		this.textureId = 20;
		a(CreativeModeTab.b);
	}

	public int b()
	{
		return 31;
	}

	public int a(Random paramRandom)
	{
		return 1;
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		return Block.LOG.id;
	}

	public void remove(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		int i = 4;
		int j = i + 1;

		if (paramWorld.c(paramInt1 - j, paramInt2 - j, paramInt3 - j, paramInt1 + j, paramInt2 + j, paramInt3 + j))
			for (int k = -i; k <= i; k++)
				for (int m = -i; m <= i; m++)
					for (int n = -i; n <= i; n++) {
						int i1 = paramWorld.getTypeId(paramInt1 + k, paramInt2 + m, paramInt3 + n);
						if (i1 == Block.LEAVES.id) {
							int i2 = paramWorld.getData(paramInt1 + k, paramInt2 + m, paramInt3 + n);
							if ((i2 & 0x8) == 0)
								paramWorld.setRawData(paramInt1 + k, paramInt2 + m, paramInt3 + n, i2 | 0x8);
						}
					}
	}

	public void postPlace(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3) & 0x3;
		int j = BlockPiston.b(paramWorld, paramInt1, paramInt2, paramInt3, (EntityHuman)paramEntityLiving);
		int k = 0;

		switch (j) {
		case 2:
		case 3:
			k = 8;
			break;
		case 4:
		case 5:
			k = 4;
			break;
		case 0:
		case 1:
			k = 0;
		}

		paramWorld.setData(paramInt1, paramInt2, paramInt3, i | k);
	}

	public int a(int paramInt1, int paramInt2)
	{
		int i = paramInt2 & 0xC;
		int j = paramInt2 & 0x3;

		if ((i == 0) && ((paramInt1 == 1) || (paramInt1 == 0)))
			return 21;
		if ((i == 4) && ((paramInt1 == 5) || (paramInt1 == 4)))
			return 21;
		if ((i == 8) && ((paramInt1 == 2) || (paramInt1 == 3))) {
			return 21;
		}

		if (j == 1) return 116;
		if (j == 2) return 117;
		if (j == 3) return 153;

		return 20;
	}

	protected int getDropData(int paramInt)
	{
		return paramInt & 0x3;
	}

	public static int e(int paramInt) {
		return paramInt & 0x3;
	}

	protected ItemStack c_(int paramInt)
	{
		return new ItemStack(this.id, 1, e(paramInt));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockLog
 * JD-Core Version:		0.6.0
 */