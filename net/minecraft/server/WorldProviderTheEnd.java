package net.minecraft.server;

public class WorldProviderTheEnd extends WorldProvider
{
	public void b()
	{
		this.c = new WorldChunkManagerHell(BiomeBase.SKY, 0.5F, 0.0F);
		this.dimension = 1;
		this.e = true;
	}

	public IChunkProvider getChunkProvider()
	{
		return new ChunkProviderTheEnd(this.a, this.a.getSeed());
	}

	public float a(long paramLong, float paramFloat)
	{
		return 0.0F;
	}

	public boolean e()
	{
		return false;
	}

	public boolean d()
	{
		return false;
	}

	public boolean canSpawn(int paramInt1, int paramInt2)
	{
		int i = this.a.b(paramInt1, paramInt2);

		if (i == 0) return false;

		return Block.byId[i].material.isSolid();
	}

	public ChunkCoordinates h()
	{
		return new ChunkCoordinates(100, 50, 0);
	}

	public int getSeaLevel()
	{
		return 50;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldProviderTheEnd
 * JD-Core Version:		0.6.0
 */