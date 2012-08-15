package net.minecraft.server;

import java.util.Random;

public class WorldGenBonusChest extends WorldGenerator
{
	private final StructurePieceTreasure[] a;
	private final int b;

	public WorldGenBonusChest(StructurePieceTreasure[] paramArrayOfStructurePieceTreasure, int paramInt)
	{
		this.a = paramArrayOfStructurePieceTreasure;
		this.b = paramInt;
	}

	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = 0;
		while ((((i = paramWorld.getTypeId(paramInt1, paramInt2, paramInt3)) == 0) || (i == Block.LEAVES.id)) && (paramInt2 > 1)) {
			paramInt2--;
		}
		if (paramInt2 < 1) {
			return false;
		}
		paramInt2++;

		for (int j = 0; j < 4; j++) {
			int k = paramInt1 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
			int m = paramInt2 + paramRandom.nextInt(3) - paramRandom.nextInt(3);
			int n = paramInt3 + paramRandom.nextInt(4) - paramRandom.nextInt(4);
			if ((paramWorld.isEmpty(k, m, n)) && (paramWorld.t(k, m - 1, n))) {
				paramWorld.setTypeId(k, m, n, Block.CHEST.id);
				TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(k, m, n);
				if ((localTileEntityChest != null) && 
					(localTileEntityChest != null)) StructurePieceTreasure.a(paramRandom, this.a, localTileEntityChest, this.b);

				if ((paramWorld.isEmpty(k - 1, m, n)) && (paramWorld.t(k - 1, m - 1, n))) {
					paramWorld.setTypeId(k - 1, m, n, Block.TORCH.id);
				}
				if ((paramWorld.isEmpty(k + 1, m, n)) && (paramWorld.t(k - 1, m - 1, n))) {
					paramWorld.setTypeId(k + 1, m, n, Block.TORCH.id);
				}
				if ((paramWorld.isEmpty(k, m, n - 1)) && (paramWorld.t(k - 1, m - 1, n))) {
					paramWorld.setTypeId(k, m, n - 1, Block.TORCH.id);
				}
				if ((paramWorld.isEmpty(k, m, n + 1)) && (paramWorld.t(k - 1, m - 1, n))) {
					paramWorld.setTypeId(k, m, n + 1, Block.TORCH.id);
				}
				return true;
			}
		}

		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenBonusChest
 * JD-Core Version:		0.6.0
 */