package net.minecraft.server;

public abstract class GenLayer
{
	private long b;
	protected GenLayer a;
	private long c;
	private long d;

	public static GenLayer[] a(long paramLong, WorldType paramWorldType)
	{
		Object localObject1 = new LayerIsland(1L);
		localObject1 = new GenLayerZoomFuzzy(2000L, (GenLayer)localObject1);
		localObject1 = new GenLayerIsland(1L, (GenLayer)localObject1);
		localObject1 = new GenLayerZoom(2001L, (GenLayer)localObject1);
		localObject1 = new GenLayerIsland(2L, (GenLayer)localObject1);
		localObject1 = new GenLayerIcePlains(2L, (GenLayer)localObject1);
		localObject1 = new GenLayerZoom(2002L, (GenLayer)localObject1);
		localObject1 = new GenLayerIsland(3L, (GenLayer)localObject1);
		localObject1 = new GenLayerZoom(2003L, (GenLayer)localObject1);
		localObject1 = new GenLayerIsland(4L, (GenLayer)localObject1);
		localObject1 = new GenLayerMushroomIsland(5L, (GenLayer)localObject1);

		int i = 4;
		if (paramWorldType == WorldType.LARGE_BIOMES) {
			i = 6;
		}

		Object localObject2 = localObject1;
		localObject2 = GenLayerZoom.a(1000L, (GenLayer)localObject2, 0);
		localObject2 = new GenLayerRiverInit(100L, (GenLayer)localObject2);
		localObject2 = GenLayerZoom.a(1000L, (GenLayer)localObject2, i + 2);
		localObject2 = new GenLayerRiver(1L, (GenLayer)localObject2);
		localObject2 = new GenLayerSmooth(1000L, (GenLayer)localObject2);

		Object localObject3 = localObject1;
		localObject3 = GenLayerZoom.a(1000L, (GenLayer)localObject3, 0);
		localObject3 = new GenLayerBiome(200L, (GenLayer)localObject3, paramWorldType);
		localObject3 = GenLayerZoom.a(1000L, (GenLayer)localObject3, 2);
		localObject3 = new GenLayerRegionHills(1000L, (GenLayer)localObject3);

		for (int j = 0; j < i; j++) {
			localObject3 = new GenLayerZoom(1000 + j, (GenLayer)localObject3);
			if (j == 0) localObject3 = new GenLayerIsland(3L, (GenLayer)localObject3);

			if (j == 1) {
				localObject3 = new GenLayerMushroomShore(1000L, (GenLayer)localObject3);
			}
			if (j == 1) {
				localObject3 = new GenLayerSwampRivers(1000L, (GenLayer)localObject3);
			}
		}

		localObject3 = new GenLayerSmooth(1000L, (GenLayer)localObject3);

		localObject3 = new GenLayerRiverMix(100L, (GenLayer)localObject3, (GenLayer)localObject2);

		Object localObject4 = localObject3;
		GenLayerZoomVoronoi localGenLayerZoomVoronoi = new GenLayerZoomVoronoi(10L, (GenLayer)localObject3);

		((GenLayer)localObject3).a(paramLong);
		localGenLayerZoomVoronoi.a(paramLong);

		return (GenLayer)(GenLayer)(GenLayer)new GenLayer[] { localObject3, localGenLayerZoomVoronoi, localObject4 };
	}

	public GenLayer(long paramLong)
	{
		this.d = paramLong;
		this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
		this.d += paramLong;
		this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
		this.d += paramLong;
		this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
		this.d += paramLong;
	}

	public void a(long paramLong) {
		this.b = paramLong;
		if (this.a != null) this.a.a(paramLong);
		this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
		this.b += this.d;
		this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
		this.b += this.d;
		this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
		this.b += this.d;
	}

	public void a(long paramLong1, long paramLong2) {
		this.c = this.b;
		this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
		this.c += paramLong1;
		this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
		this.c += paramLong2;
		this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
		this.c += paramLong1;
		this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
		this.c += paramLong2;
	}

	protected int a(int paramInt) {
		int i = (int)((this.c >> 24) % paramInt);
		if (i < 0) i += paramInt;
		this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
		this.c += this.b;
		return i;
	}

	public abstract int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}

/* 
 * Qualified Name:		 net.minecraft.server.GenLayer
 * JD-Core Version:		0.6.0
 */