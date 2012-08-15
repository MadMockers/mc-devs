package net.minecraft.server;

public class WorldProviderHell extends WorldProvider
{
	public void b()
	{
		this.c = new WorldChunkManagerHell(BiomeBase.HELL, 1.0F, 0.0F);
		this.d = true;
		this.e = true;
		this.dimension = -1;
	}

	protected void a()
	{
		float f1 = 0.1F;
		for (int i = 0; i <= 15; i++) {
			float f2 = 1.0F - i / 15.0F;
			this.f[i] = ((1.0F - f2) / (f2 * 3.0F + 1.0F) * (1.0F - f1) + f1);
		}
	}

	public IChunkProvider getChunkProvider()
	{
		return new ChunkProviderHell(this.a, this.a.getSeed());
	}

	public boolean d()
	{
		return false;
	}

	public boolean canSpawn(int paramInt1, int paramInt2)
	{
		return false;
	}

	public float a(long paramLong, float paramFloat)
	{
		return 0.5F;
	}

	public boolean e()
	{
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldProviderHell
 * JD-Core Version:		0.6.0
 */