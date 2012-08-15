package net.minecraft.server;

import java.io.PrintStream;
import java.util.Random;

public class WorldGenDungeons extends WorldGenerator
{
	public boolean a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
	{
		int i = 3;
		int j = paramRandom.nextInt(2) + 2;
		int k = paramRandom.nextInt(2) + 2;

		int m = 0;
		int i1;
		int i2;
		for (int n = paramInt1 - j - 1; n <= paramInt1 + j + 1; n++) {
			for (i1 = paramInt2 - 1; i1 <= paramInt2 + i + 1; i1++) {
				for (i2 = paramInt3 - k - 1; i2 <= paramInt3 + k + 1; i2++) {
					Material localMaterial = paramWorld.getMaterial(n, i1, i2);
					if ((i1 == paramInt2 - 1) && (!localMaterial.isBuildable())) return false;
					if ((i1 == paramInt2 + i + 1) && (!localMaterial.isBuildable())) return false;

					if (((n != paramInt1 - j - 1) && (n != paramInt1 + j + 1) && (i2 != paramInt3 - k - 1) && (i2 != paramInt3 + k + 1)) || 
						(i1 != paramInt2) || (!paramWorld.isEmpty(n, i1, i2)) || (!paramWorld.isEmpty(n, i1 + 1, i2))) continue;
					m++;
				}

			}

		}

		if ((m < 1) || (m > 5)) return false;

		for (n = paramInt1 - j - 1; n <= paramInt1 + j + 1; n++) {
			for (i1 = paramInt2 + i; i1 >= paramInt2 - 1; i1--) {
				for (i2 = paramInt3 - k - 1; i2 <= paramInt3 + k + 1; i2++)
				{
					if ((n == paramInt1 - j - 1) || (i1 == paramInt2 - 1) || (i2 == paramInt3 - k - 1) || (n == paramInt1 + j + 1) || (i1 == paramInt2 + i + 1) || (i2 == paramInt3 + k + 1)) {
						if ((i1 >= 0) && (!paramWorld.getMaterial(n, i1 - 1, i2).isBuildable()))
							paramWorld.setTypeId(n, i1, i2, 0);
						else if (paramWorld.getMaterial(n, i1, i2).isBuildable()) {
							if ((i1 == paramInt2 - 1) && (paramRandom.nextInt(4) != 0))
								paramWorld.setTypeId(n, i1, i2, Block.MOSSY_COBBLESTONE.id);
							else
								paramWorld.setTypeId(n, i1, i2, Block.COBBLESTONE.id);
						}
					}
					else {
						paramWorld.setTypeId(n, i1, i2, 0);
					}
				}
			}
		}

		for (n = 0; n < 2; n++) {
			for (i1 = 0; i1 < 3; i1++) {
				i2 = paramInt1 + paramRandom.nextInt(j * 2 + 1) - j;
				int i3 = paramInt2;
				int i4 = paramInt3 + paramRandom.nextInt(k * 2 + 1) - k;
				if (!paramWorld.isEmpty(i2, i3, i4))
					continue;
				int i5 = 0;
				if (paramWorld.getMaterial(i2 - 1, i3, i4).isBuildable()) i5++;
				if (paramWorld.getMaterial(i2 + 1, i3, i4).isBuildable()) i5++;
				if (paramWorld.getMaterial(i2, i3, i4 - 1).isBuildable()) i5++;
				if (paramWorld.getMaterial(i2, i3, i4 + 1).isBuildable()) i5++;

				if (i5 != 1)
					continue;
				paramWorld.setTypeId(i2, i3, i4, Block.CHEST.id);
				TileEntityChest localTileEntityChest = (TileEntityChest)paramWorld.getTileEntity(i2, i3, i4);
				if (localTileEntityChest == null) break;
				for (int i6 = 0; i6 < 8; i6++) {
					ItemStack localItemStack = a(paramRandom);
					if (localItemStack == null) continue; localTileEntityChest.setItem(paramRandom.nextInt(localTileEntityChest.getSize()), localItemStack);
				}
				break;
			}

		}

		paramWorld.setTypeId(paramInt1, paramInt2, paramInt3, Block.MOB_SPAWNER.id);
		TileEntityMobSpawner localTileEntityMobSpawner = (TileEntityMobSpawner)paramWorld.getTileEntity(paramInt1, paramInt2, paramInt3);
		if (localTileEntityMobSpawner != null)
			localTileEntityMobSpawner.a(b(paramRandom));
		else {
			System.err.println("Failed to fetch mob spawner entity at (" + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + ")");
		}

		return true;
	}

	private ItemStack a(Random paramRandom) {
		int i = paramRandom.nextInt(11);
		if (i == 0) return new ItemStack(Item.SADDLE);
		if (i == 1) return new ItemStack(Item.IRON_INGOT, paramRandom.nextInt(4) + 1);
		if (i == 2) return new ItemStack(Item.BREAD);
		if (i == 3) return new ItemStack(Item.WHEAT, paramRandom.nextInt(4) + 1);
		if (i == 4) return new ItemStack(Item.SULPHUR, paramRandom.nextInt(4) + 1);
		if (i == 5) return new ItemStack(Item.STRING, paramRandom.nextInt(4) + 1);
		if (i == 6) return new ItemStack(Item.BUCKET);
		if ((i == 7) && (paramRandom.nextInt(100) == 0)) return new ItemStack(Item.GOLDEN_APPLE);
		if ((i == 8) && (paramRandom.nextInt(2) == 0)) return new ItemStack(Item.REDSTONE, paramRandom.nextInt(4) + 1);
		if ((i == 9) && (paramRandom.nextInt(10) == 0))
			return new ItemStack(Item.byId[(Item.RECORD_1.id + paramRandom.nextInt(2))]);
		if (i == 10) return new ItemStack(Item.INK_SACK, 1, 3);

		return null;
	}

	private String b(Random paramRandom) {
		int i = paramRandom.nextInt(4);
		if (i == 0) return "Skeleton";
		if (i == 1) return "Zombie";
		if (i == 2) return "Zombie";
		if (i == 3) return "Spider";
		return "";
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldGenDungeons
 * JD-Core Version:		0.6.0
 */