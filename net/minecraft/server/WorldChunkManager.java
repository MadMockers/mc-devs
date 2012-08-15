package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManager
{
	private GenLayer d;
	private GenLayer e;
	private BiomeCache f = new BiomeCache(this);
	private List g;

	protected WorldChunkManager()
	{
		this.g = new ArrayList();
		this.g.add(BiomeBase.FOREST);
		this.g.add(BiomeBase.PLAINS);
		this.g.add(BiomeBase.TAIGA);
		this.g.add(BiomeBase.TAIGA_HILLS);
		this.g.add(BiomeBase.FOREST_HILLS);
		this.g.add(BiomeBase.JUNGLE);
		this.g.add(BiomeBase.JUNGLE_HILLS);
	}

	public WorldChunkManager(long paramLong, WorldType paramWorldType) {
		this();

		GenLayer[] arrayOfGenLayer = GenLayer.a(paramLong, paramWorldType);
		this.d = arrayOfGenLayer[0];
		this.e = arrayOfGenLayer[1];
	}

	public WorldChunkManager(World paramWorld) {
		this(paramWorld.getSeed(), paramWorld.getWorldData().getType());
	}

	public List a()
	{
		return this.g;
	}

	public BiomeBase getBiome(int paramInt1, int paramInt2)
	{
		return this.f.b(paramInt1, paramInt2);
	}

	public float[] getWetness(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		IntCache.a();
		if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
			paramArrayOfFloat = new float[paramInt3 * paramInt4];
		}

		int[] arrayOfInt = this.e.a(paramInt1, paramInt2, paramInt3, paramInt4);
		for (int i = 0; i < paramInt3 * paramInt4; i++) {
			float f1 = BiomeBase.biomes[arrayOfInt[i]].g() / 65536.0F;
			if (f1 > 1.0F) f1 = 1.0F;
			paramArrayOfFloat[i] = f1;
		}

		return paramArrayOfFloat;
	}

	public float[] getTemperatures(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		IntCache.a();
		if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
			paramArrayOfFloat = new float[paramInt3 * paramInt4];
		}

		int[] arrayOfInt = this.e.a(paramInt1, paramInt2, paramInt3, paramInt4);
		for (int i = 0; i < paramInt3 * paramInt4; i++) {
			float f1 = BiomeBase.biomes[arrayOfInt[i]].h() / 65536.0F;
			if (f1 > 1.0F) f1 = 1.0F;
			paramArrayOfFloat[i] = f1;
		}

		return paramArrayOfFloat;
	}

	public BiomeBase[] getBiomes(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		IntCache.a();
		if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
			paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
		}

		int[] arrayOfInt = this.d.a(paramInt1, paramInt2, paramInt3, paramInt4);
		for (int i = 0; i < paramInt3 * paramInt4; i++) {
			paramArrayOfBiomeBase[i] = BiomeBase.biomes[arrayOfInt[i]];
		}

		return paramArrayOfBiomeBase;
	}

	public BiomeBase[] getBiomeBlock(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		return a(paramArrayOfBiomeBase, paramInt1, paramInt2, paramInt3, paramInt4, true);
	}

	public BiomeBase[] a(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
		IntCache.a();
		if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
			paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
		}

		if ((paramBoolean) && (paramInt3 == 16) && (paramInt4 == 16) && ((paramInt1 & 0xF) == 0) && ((paramInt2 & 0xF) == 0)) {
			localObject = this.f.e(paramInt1, paramInt2);
			System.arraycopy(localObject, 0, paramArrayOfBiomeBase, 0, paramInt3 * paramInt4);
			return paramArrayOfBiomeBase;
		}

		Object localObject = this.e.a(paramInt1, paramInt2, paramInt3, paramInt4);
		for (int i = 0; i < paramInt3 * paramInt4; i++) {
			paramArrayOfBiomeBase[i] = BiomeBase.biomes[localObject[i]];
		}

		return (BiomeBase)paramArrayOfBiomeBase;
	}

	public boolean a(int paramInt1, int paramInt2, int paramInt3, List paramList)
	{
		int i = paramInt1 - paramInt3 >> 2;
		int j = paramInt2 - paramInt3 >> 2;
		int k = paramInt1 + paramInt3 >> 2;
		int m = paramInt2 + paramInt3 >> 2;

		int n = k - i + 1;
		int i1 = m - j + 1;

		int[] arrayOfInt = this.d.a(i, j, n, i1);
		for (int i2 = 0; i2 < n * i1; i2++) {
			BiomeBase localBiomeBase = BiomeBase.biomes[arrayOfInt[i2]];
			if (!paramList.contains(localBiomeBase)) return false;
		}

		return true;
	}

	public ChunkPosition a(int paramInt1, int paramInt2, int paramInt3, List paramList, Random paramRandom)
	{
		int i = paramInt1 - paramInt3 >> 2;
		int j = paramInt2 - paramInt3 >> 2;
		int k = paramInt1 + paramInt3 >> 2;
		int m = paramInt2 + paramInt3 >> 2;

		int n = k - i + 1;
		int i1 = m - j + 1;
		int[] arrayOfInt = this.d.a(i, j, n, i1);
		ChunkPosition localChunkPosition = null;
		int i2 = 0;
		for (int i3 = 0; i3 < arrayOfInt.length; i3++) {
			int i4 = i + i3 % n << 2;
			int i5 = j + i3 / n << 2;
			BiomeBase localBiomeBase = BiomeBase.biomes[arrayOfInt[i3]];
			if ((!paramList.contains(localBiomeBase)) || (
				(localChunkPosition != null) && (paramRandom.nextInt(i2 + 1) != 0))) continue;
			localChunkPosition = new ChunkPosition(i4, 0, i5);
			i2++;
		}

		return localChunkPosition;
	}

	public void b() {
		this.f.a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldChunkManager
 * JD-Core Version:		0.6.0
 */