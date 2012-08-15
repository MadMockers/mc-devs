package net.minecraft.server;

import java.util.Random;

public class BlockBed extends BlockDirectional
{
	public static final int[][] a = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

	public BlockBed(int paramInt)
	{
		super(paramInt, 134, Material.CLOTH);

		n();
	}

	public boolean interact(World paramWorld, int paramInt1, int paramInt2, int paramInt3, EntityHuman paramEntityHuman, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		if (paramWorld.isStatic) return true;

		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);

		if (!a_(i))
		{
			int j = d(i);
			paramInt1 += a[j][0];
			paramInt3 += a[j][1];
			if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) != this.id) {
				return true;
			}
			i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		}

		if (!paramWorld.worldProvider.e()) {
			double d1 = paramInt1 + 0.5D;
			double d2 = paramInt2 + 0.5D;
			double d3 = paramInt3 + 0.5D;
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
			int k = d(i);
			paramInt1 += a[k][0];
			paramInt3 += a[k][1];
			if (paramWorld.getTypeId(paramInt1, paramInt2, paramInt3) == this.id) {
				paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
				d1 = (d1 + paramInt1 + 0.5D) / 2.0D;
				d2 = (d2 + paramInt2 + 0.5D) / 2.0D;
				d3 = (d3 + paramInt3 + 0.5D) / 2.0D;
			}
			paramWorld.createExplosion(null, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, 5.0F, true);
			return true;
		}

		if (b_(i)) {
			localObject = null;
			for (EntityHuman localEntityHuman : paramWorld.players) {
				if (localEntityHuman.isSleeping()) {
					ChunkCoordinates localChunkCoordinates = localEntityHuman.bT;
					if ((localChunkCoordinates.x == paramInt1) && (localChunkCoordinates.y == paramInt2) && (localChunkCoordinates.z == paramInt3)) {
						localObject = localEntityHuman;
					}
				}
			}

			if (localObject == null) {
				a(paramWorld, paramInt1, paramInt2, paramInt3, false);
			} else {
				paramEntityHuman.c("tile.bed.occupied");
				return true;
			}
		}

		Object localObject = paramEntityHuman.a(paramInt1, paramInt2, paramInt3);
		if (localObject == EnumBedResult.OK) {
			a(paramWorld, paramInt1, paramInt2, paramInt3, true);
			return true;
		}

		if (localObject == EnumBedResult.NOT_POSSIBLE_NOW)
			paramEntityHuman.c("tile.bed.noSleep");
		else if (localObject == EnumBedResult.NOT_SAFE) {
			paramEntityHuman.c("tile.bed.notSafe");
		}
		return true;
	}

	public int a(int paramInt1, int paramInt2)
	{
		if (paramInt1 == 0) {
			return Block.WOOD.textureId;
		}

		int i = d(paramInt2);
		int j = Direction.h[i][paramInt1];

		if (a_(paramInt2))
		{
			if (j == 2) {
				return this.textureId + 2 + 16;
			}
			if ((j == 5) || (j == 4)) {
				return this.textureId + 1 + 16;
			}
			return this.textureId + 1;
		}
		if (j == 3) {
			return this.textureId - 1 + 16;
		}
		if ((j == 5) || (j == 4)) {
			return this.textureId + 16;
		}
		return this.textureId;
	}

	public int b()
	{
		return 14;
	}

	public boolean c()
	{
		return false;
	}

	public boolean d()
	{
		return false;
	}

	public void updateShape(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3)
	{
		n();
	}

	public void doPhysics(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = d(i);

		if (a_(i)) {
			if (paramWorld.getTypeId(paramInt1 - a[j][0], paramInt2, paramInt3 - a[j][1]) != this.id) {
				paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
			}
		}
		else if (paramWorld.getTypeId(paramInt1 + a[j][0], paramInt2, paramInt3 + a[j][1]) != this.id) {
			paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, 0);
			if (!paramWorld.isStatic)
				c(paramWorld, paramInt1, paramInt2, paramInt3, i, 0);
		}
	}

	public int getDropType(int paramInt1, Random paramRandom, int paramInt2)
	{
		if (a_(paramInt1)) {
			return 0;
		}
		return Item.BED.id;
	}

	private void n() {
		a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
	}

	public static boolean a_(int paramInt) {
		return (paramInt & 0x8) != 0;
	}

	public static boolean b_(int paramInt) {
		return (paramInt & 0x4) != 0;
	}

	public static void a(World paramWorld, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		if (paramBoolean)
			i |= 4;
		else {
			i &= -5;
		}
		paramWorld.setData(paramInt1, paramInt2, paramInt3, i);
	}

	public static ChunkCoordinates b(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		int i = paramWorld.getData(paramInt1, paramInt2, paramInt3);
		int j = BlockDirectional.d(i);

		for (int k = 0; k <= 1; k++) {
			int m = paramInt1 - a[j][0] * k - 1;
			int n = paramInt3 - a[j][1] * k - 1;
			int i1 = m + 2;
			int i2 = n + 2;

			for (int i3 = m; i3 <= i1; i3++) {
				for (int i4 = n; i4 <= i2; i4++) {
					if ((paramWorld.t(i3, paramInt2 - 1, i4)) && (paramWorld.isEmpty(i3, paramInt2, i4)) && (paramWorld.isEmpty(i3, paramInt2 + 1, i4))) {
						if (paramInt4 > 0) {
							paramInt4--;
						}
						else {
							return new ChunkCoordinates(i3, paramInt2, i4);
						}
					}
				}
			}
		}
		return null;
	}

	public void dropNaturally(World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
	{
		if (!a_(paramInt4))
			super.dropNaturally(paramWorld, paramInt1, paramInt2, paramInt3, paramInt4, paramFloat, 0);
	}

	public int e()
	{
		return 1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BlockBed
 * JD-Core Version:		0.6.0
 */