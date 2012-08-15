package net.minecraft.server;

public class GenLayerBiome extends GenLayer
{
	private BiomeBase[] b = { BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND, BiomeBase.PLAINS, BiomeBase.TAIGA, BiomeBase.JUNGLE };

	public GenLayerBiome(long paramLong, GenLayer paramGenLayer, WorldType paramWorldType)
	{
		super(paramLong);
		this.a = paramGenLayer;

		if (paramWorldType == WorldType.NORMAL_1_1)
			this.b = new BiomeBase[] { BiomeBase.DESERT, BiomeBase.FOREST, BiomeBase.EXTREME_HILLS, BiomeBase.SWAMPLAND, BiomeBase.PLAINS, BiomeBase.TAIGA };
	}

	public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int[] arrayOfInt1 = this.a.a(paramInt1, paramInt2, paramInt3, paramInt4);

		int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
		for (int i = 0; i < paramInt4; i++) {
			for (int j = 0; j < paramInt3; j++) {
				a(j + paramInt1, i + paramInt2);
				int k = arrayOfInt1[(j + i * paramInt3)];
				if (k == 0) {
					arrayOfInt2[(j + i * paramInt3)] = 0;
				} else if (k == BiomeBase.MUSHROOM_ISLAND.id) {
					arrayOfInt2[(j + i * paramInt3)] = k;
				} else if (k == 1) {
					arrayOfInt2[(j + i * paramInt3)] = this.b[a(this.b.length)].id;
				} else {
					int m = this.b[a(this.b.length)].id;
					if (m == BiomeBase.TAIGA.id)
						arrayOfInt2[(j + i * paramInt3)] = m;
					else {
						arrayOfInt2[(j + i * paramInt3)] = BiomeBase.ICE_PLAINS.id;
					}
				}
			}
		}

		return arrayOfInt2;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.GenLayerBiome
 * JD-Core Version:		0.6.0
 */