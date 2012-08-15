/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public abstract class GenLayer
/*		 */ {
/*		 */	 private long b;
/*		 */	 protected GenLayer a;
/*		 */	 private long c;
/*		 */	 private long d;
/*		 */ 
/*		 */	 public static GenLayer[] a(long paramLong, WorldType paramWorldType)
/*		 */	 {
/*	13 */		 Object localObject1 = new LayerIsland(1L);
/*	14 */		 localObject1 = new GenLayerZoomFuzzy(2000L, (GenLayer)localObject1);
/*	15 */		 localObject1 = new GenLayerIsland(1L, (GenLayer)localObject1);
/*	16 */		 localObject1 = new GenLayerZoom(2001L, (GenLayer)localObject1);
/*	17 */		 localObject1 = new GenLayerIsland(2L, (GenLayer)localObject1);
/*	18 */		 localObject1 = new GenLayerIcePlains(2L, (GenLayer)localObject1);
/*	19 */		 localObject1 = new GenLayerZoom(2002L, (GenLayer)localObject1);
/*	20 */		 localObject1 = new GenLayerIsland(3L, (GenLayer)localObject1);
/*	21 */		 localObject1 = new GenLayerZoom(2003L, (GenLayer)localObject1);
/*	22 */		 localObject1 = new GenLayerIsland(4L, (GenLayer)localObject1);
/*	23 */		 localObject1 = new GenLayerMushroomIsland(5L, (GenLayer)localObject1);
/*		 */ 
/*	25 */		 int i = 4;
/*	26 */		 if (paramWorldType == WorldType.LARGE_BIOMES) {
/*	27 */			 i = 6;
/*		 */		 }
/*		 */ 
/*	30 */		 Object localObject2 = localObject1;
/*	31 */		 localObject2 = GenLayerZoom.a(1000L, (GenLayer)localObject2, 0);
/*	32 */		 localObject2 = new GenLayerRiverInit(100L, (GenLayer)localObject2);
/*	33 */		 localObject2 = GenLayerZoom.a(1000L, (GenLayer)localObject2, i + 2);
/*	34 */		 localObject2 = new GenLayerRiver(1L, (GenLayer)localObject2);
/*	35 */		 localObject2 = new GenLayerSmooth(1000L, (GenLayer)localObject2);
/*		 */ 
/*	37 */		 Object localObject3 = localObject1;
/*	38 */		 localObject3 = GenLayerZoom.a(1000L, (GenLayer)localObject3, 0);
/*	39 */		 localObject3 = new GenLayerBiome(200L, (GenLayer)localObject3, paramWorldType);
/*	40 */		 localObject3 = GenLayerZoom.a(1000L, (GenLayer)localObject3, 2);
/*	41 */		 localObject3 = new GenLayerRegionHills(1000L, (GenLayer)localObject3);
/*		 */ 
/*	43 */		 for (int j = 0; j < i; j++) {
/*	44 */			 localObject3 = new GenLayerZoom(1000 + j, (GenLayer)localObject3);
/*	45 */			 if (j == 0) localObject3 = new GenLayerIsland(3L, (GenLayer)localObject3);
/*		 */ 
/*	47 */			 if (j == 1) {
/*	48 */				 localObject3 = new GenLayerMushroomShore(1000L, (GenLayer)localObject3);
/*		 */			 }
/*	50 */			 if (j == 1) {
/*	51 */				 localObject3 = new GenLayerSwampRivers(1000L, (GenLayer)localObject3);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	55 */		 localObject3 = new GenLayerSmooth(1000L, (GenLayer)localObject3);
/*		 */ 
/*	57 */		 localObject3 = new GenLayerRiverMix(100L, (GenLayer)localObject3, (GenLayer)localObject2);
/*		 */ 
/*	59 */		 Object localObject4 = localObject3;
/*	60 */		 GenLayerZoomVoronoi localGenLayerZoomVoronoi = new GenLayerZoomVoronoi(10L, (GenLayer)localObject3);
/*		 */ 
/*	62 */		 ((GenLayer)localObject3).a(paramLong);
/*	63 */		 localGenLayerZoomVoronoi.a(paramLong);
/*		 */ 
/*	65 */		 return (GenLayer)(GenLayer)(GenLayer)new GenLayer[] { localObject3, localGenLayerZoomVoronoi, localObject4 };
/*		 */	 }
/*		 */ 
/*		 */	 public GenLayer(long paramLong)
/*		 */	 {
/*	71 */		 this.d = paramLong;
/*	72 */		 this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
/*	73 */		 this.d += paramLong;
/*	74 */		 this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
/*	75 */		 this.d += paramLong;
/*	76 */		 this.d *= (this.d * 6364136223846793005L + 1442695040888963407L);
/*	77 */		 this.d += paramLong;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(long paramLong) {
/*	81 */		 this.b = paramLong;
/*	82 */		 if (this.a != null) this.a.a(paramLong);
/*	83 */		 this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
/*	84 */		 this.b += this.d;
/*	85 */		 this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
/*	86 */		 this.b += this.d;
/*	87 */		 this.b *= (this.b * 6364136223846793005L + 1442695040888963407L);
/*	88 */		 this.b += this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(long paramLong1, long paramLong2) {
/*	92 */		 this.c = this.b;
/*	93 */		 this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
/*	94 */		 this.c += paramLong1;
/*	95 */		 this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
/*	96 */		 this.c += paramLong2;
/*	97 */		 this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
/*	98 */		 this.c += paramLong1;
/*	99 */		 this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
/* 100 */		 this.c += paramLong2;
/*		 */	 }
/*		 */ 
/*		 */	 protected int a(int paramInt) {
/* 104 */		 int i = (int)((this.c >> 24) % paramInt);
/* 105 */		 if (i < 0) i += paramInt;
/* 106 */		 this.c *= (this.c * 6364136223846793005L + 1442695040888963407L);
/* 107 */		 this.c += this.b;
/* 108 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 public abstract int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.GenLayer
 * JD-Core Version:		0.6.0
 */