package net.minecraft.server;

public abstract class WorldProvider
{
	public World a;
	public WorldType type;
	public WorldChunkManager c;
	public boolean d = false;
	public boolean e = false;
	public float[] f = new float[16];
	public int dimension = 0;

	private float[] h = new float[4];

	public final void a(World paramWorld)
	{
		this.a = paramWorld;
		this.type = paramWorld.getWorldData().getType();
		b();
		a();
	}

	protected void a() {
		float f1 = 0.0F;
		for (int i = 0; i <= 15; i++) {
			float f2 = 1.0F - i / 15.0F;
			this.f[i] = ((1.0F - f2) / (f2 * 3.0F + 1.0F) * (1.0F - f1) + f1);
		}
	}

	protected void b() {
		if (this.a.getWorldData().getType() == WorldType.FLAT)
			this.c = new WorldChunkManagerHell(BiomeBase.PLAINS, 0.5F, 0.5F);
		else
			this.c = new WorldChunkManager(this.a);
	}

	public IChunkProvider getChunkProvider()
	{
		if (this.type == WorldType.FLAT) {
			return new ChunkProviderFlat(this.a, this.a.getSeed(), this.a.getWorldData().shouldGenerateMapFeatures());
		}
		return new ChunkProviderGenerate(this.a, this.a.getSeed(), this.a.getWorldData().shouldGenerateMapFeatures());
	}

	public boolean canSpawn(int paramInt1, int paramInt2)
	{
		int i = this.a.b(paramInt1, paramInt2);

		return i == Block.GRASS.id;
	}

	public float a(long paramLong, float paramFloat)
	{
		int i = (int)(paramLong % 24000L);
		float f1 = (i + paramFloat) / 24000.0F - 0.25F;
		if (f1 < 0.0F) f1 += 1.0F;
		if (f1 > 1.0F) f1 -= 1.0F;
		float f2 = f1;
		f1 = 1.0F - (float)((Math.cos(f1 * 3.141592653589793D) + 1.0D) / 2.0D);
		f1 = f2 + (f1 - f2) / 3.0F;
		return f1;
	}

	public boolean d()
	{
		return true;
	}

	public boolean e()
	{
		return true;
	}

	public static WorldProvider byDimension(int paramInt) {
		if (paramInt == -1) return new WorldProviderHell();
		if (paramInt == 0) return new WorldProviderNormal();
		if (paramInt == 1) return new WorldProviderTheEnd();
		return null;
	}

	public ChunkCoordinates h()
	{
		return null;
	}

	public int getSeaLevel() {
		if (this.type == WorldType.FLAT) {
			return 4;
		}
		return 64;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldProvider
 * JD-Core Version:		0.6.0
 */