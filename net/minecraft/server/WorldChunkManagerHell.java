package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerHell extends WorldChunkManager
{
	private BiomeBase d;
	private float e;
	private float f;

	public WorldChunkManagerHell(BiomeBase paramBiomeBase, float paramFloat1, float paramFloat2)
	{
		this.d = paramBiomeBase;
		this.e = paramFloat1;
		this.f = paramFloat2;
	}

	public BiomeBase getBiome(int paramInt1, int paramInt2)
	{
		return this.d;
	}

	public BiomeBase[] getBiomes(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
			paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
		}

		Arrays.fill(paramArrayOfBiomeBase, 0, paramInt3 * paramInt4, this.d);

		return paramArrayOfBiomeBase;
	}

	public float[] getWetness(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
			paramArrayOfFloat = new float[paramInt3 * paramInt4];
		}

		Arrays.fill(paramArrayOfFloat, 0, paramInt3 * paramInt4, this.e);
		return paramArrayOfFloat;
	}

	public float[] getTemperatures(float[] paramArrayOfFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length < paramInt3 * paramInt4)) {
			paramArrayOfFloat = new float[paramInt3 * paramInt4];
		}
		Arrays.fill(paramArrayOfFloat, 0, paramInt3 * paramInt4, this.f);

		return paramArrayOfFloat;
	}

	public BiomeBase[] getBiomeBlock(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		if ((paramArrayOfBiomeBase == null) || (paramArrayOfBiomeBase.length < paramInt3 * paramInt4)) {
			paramArrayOfBiomeBase = new BiomeBase[paramInt3 * paramInt4];
		}

		Arrays.fill(paramArrayOfBiomeBase, 0, paramInt3 * paramInt4, this.d);

		return paramArrayOfBiomeBase;
	}

	public BiomeBase[] a(BiomeBase[] paramArrayOfBiomeBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
	{
		return getBiomeBlock(paramArrayOfBiomeBase, paramInt1, paramInt2, paramInt3, paramInt4);
	}

	public ChunkPosition a(int paramInt1, int paramInt2, int paramInt3, List paramList, Random paramRandom)
	{
		if (paramList.contains(this.d)) {
			return new ChunkPosition(paramInt1 - paramInt3 + paramRandom.nextInt(paramInt3 * 2 + 1), 0, paramInt2 - paramInt3 + paramRandom.nextInt(paramInt3 * 2 + 1));
		}

		return null;
	}

	public boolean a(int paramInt1, int paramInt2, int paramInt3, List paramList)
	{
		return paramList.contains(this.d);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldChunkManagerHell
 * JD-Core Version:		0.6.0
 */