/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public abstract class WorldProvider
/*		 */ {
/*		 */	 public World a;
/*		 */	 public WorldType type;
/*		 */	 public WorldChunkManager c;
/*	16 */	 public boolean d = false;
/*	17 */	 public boolean e = false;
/*	18 */	 public float[] f = new float[16];
/*	19 */	 public int dimension = 0;
/*		 */ 
/*	81 */	 private float[] h = new float[4];
/*		 */ 
/*		 */	 public final void a(World paramWorld)
/*		 */	 {
/*	22 */		 this.a = paramWorld;
/*	23 */		 this.type = paramWorld.getWorldData().getType();
/*	24 */		 b();
/*	25 */		 a();
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() {
/*	29 */		 float f1 = 0.0F;
/*	30 */		 for (int i = 0; i <= 15; i++) {
/*	31 */			 float f2 = 1.0F - i / 15.0F;
/*	32 */			 this.f[i] = ((1.0F - f2) / (f2 * 3.0F + 1.0F) * (1.0F - f1) + f1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b() {
/*	37 */		 if (this.a.getWorldData().getType() == WorldType.FLAT)
/*	38 */			 this.c = new WorldChunkManagerHell(BiomeBase.PLAINS, 0.5F, 0.5F);
/*		 */		 else
/*	40 */			 this.c = new WorldChunkManager(this.a);
/*		 */	 }
/*		 */ 
/*		 */	 public IChunkProvider getChunkProvider()
/*		 */	 {
/*	45 */		 if (this.type == WorldType.FLAT) {
/*	46 */			 return new ChunkProviderFlat(this.a, this.a.getSeed(), this.a.getWorldData().shouldGenerateMapFeatures());
/*		 */		 }
/*	48 */		 return new ChunkProviderGenerate(this.a, this.a.getSeed(), this.a.getWorldData().shouldGenerateMapFeatures());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSpawn(int paramInt1, int paramInt2)
/*		 */	 {
/*	53 */		 int i = this.a.b(paramInt1, paramInt2);
/*		 */ 
/*	55 */		 return i == Block.GRASS.id;
/*		 */	 }
/*		 */ 
/*		 */	 public float a(long paramLong, float paramFloat)
/*		 */	 {
/*	61 */		 int i = (int)(paramLong % 24000L);
/*	62 */		 float f1 = (i + paramFloat) / 24000.0F - 0.25F;
/*	63 */		 if (f1 < 0.0F) f1 += 1.0F;
/*	64 */		 if (f1 > 1.0F) f1 -= 1.0F;
/*	65 */		 float f2 = f1;
/*	66 */		 f1 = 1.0F - (float)((Math.cos(f1 * 3.141592653589793D) + 1.0D) / 2.0D);
/*	67 */		 f1 = f2 + (f1 - f2) / 3.0F;
/*	68 */		 return f1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/*	76 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean e()
/*		 */	 {
/* 118 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public static WorldProvider byDimension(int paramInt) {
/* 122 */		 if (paramInt == -1) return new WorldProviderHell();
/* 123 */		 if (paramInt == 0) return new WorldProviderNormal();
/* 124 */		 if (paramInt == 1) return new WorldProviderTheEnd();
/* 125 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkCoordinates h()
/*		 */	 {
/* 137 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int getSeaLevel() {
/* 141 */		 if (this.type == WorldType.FLAT) {
/* 142 */			 return 4;
/*		 */		 }
/* 144 */		 return 64;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldProvider
 * JD-Core Version:		0.6.0
 */