/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class ChunkProviderFlat
/*		 */	 implements IChunkProvider
/*		 */ {
/*		 */	 private World a;
/*		 */	 private Random b;
/*		 */	 private final boolean c;
/*	24 */	 private WorldGenVillage d = new WorldGenVillage(1);
/*		 */ 
/*		 */	 public ChunkProviderFlat(World paramWorld, long paramLong, boolean paramBoolean) {
/*	27 */		 this.a = paramWorld;
/*	28 */		 this.c = paramBoolean;
/*	29 */		 this.b = new Random(paramLong);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(byte[] paramArrayOfByte) {
/*	33 */		 int i = paramArrayOfByte.length / 256;
/*		 */ 
/*	35 */		 for (int j = 0; j < 16; j++)
/*	36 */			 for (int k = 0; k < 16; k++)
/*	37 */				 for (int m = 0; m < i; m++) {
/*	38 */					 int n = 0;
/*	39 */					 if (m == 0)
/*	40 */						 n = Block.BEDROCK.id;
/*	41 */					 else if (m <= 2)
/*	42 */						 n = Block.DIRT.id;
/*	43 */					 else if (m == 3) {
/*	44 */						 n = Block.GRASS.id;
/*		 */					 }
/*	46 */					 paramArrayOfByte[(j << 11 | k << 7 | m)] = (byte)n;
/*		 */				 }
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getChunkAt(int paramInt1, int paramInt2)
/*		 */	 {
/*	53 */		 return getOrCreateChunk(paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getOrCreateChunk(int paramInt1, int paramInt2)
/*		 */	 {
/*	58 */		 byte[] arrayOfByte1 = new byte[32768];
/*	59 */		 a(arrayOfByte1);
/*		 */ 
/*	61 */		 Chunk localChunk = new Chunk(this.a, arrayOfByte1, paramInt1, paramInt2);
/*		 */ 
/*	63 */		 if (this.c) {
/*	64 */			 this.d.a(this, this.a, paramInt1, paramInt2, arrayOfByte1);
/*		 */		 }
/*		 */ 
/*	67 */		 BiomeBase[] arrayOfBiomeBase = this.a.getWorldChunkManager().getBiomeBlock(null, paramInt1 * 16, paramInt2 * 16, 16, 16);
/*	68 */		 byte[] arrayOfByte2 = localChunk.m();
/*		 */ 
/*	70 */		 for (int i = 0; i < arrayOfByte2.length; i++) {
/*	71 */			 arrayOfByte2[i] = (byte)arrayOfBiomeBase[i].id;
/*		 */		 }
/*		 */ 
/*	74 */		 localChunk.initLighting();
/*		 */ 
/*	76 */		 return localChunk;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isChunkLoaded(int paramInt1, int paramInt2) {
/*	80 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public void getChunkAt(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2)
/*		 */	 {
/*	85 */		 this.b.setSeed(this.a.getSeed());
/*	86 */		 long l1 = this.b.nextLong() / 2L * 2L + 1L;
/*	87 */		 long l2 = this.b.nextLong() / 2L * 2L + 1L;
/*	88 */		 this.b.setSeed(paramInt1 * l1 + paramInt2 * l2 ^ this.a.getSeed());
/*		 */ 
/*	90 */		 if (this.c)
/*	91 */			 this.d.a(this.a, this.b, paramInt1, paramInt2);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate)
/*		 */	 {
/*	96 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean unloadChunks() {
/* 100 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSave() {
/* 104 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 108 */		 return "FlatLevelSource";
/*		 */	 }
/*		 */ 
/*		 */	 public List getMobsFor(EnumCreatureType paramEnumCreatureType, int paramInt1, int paramInt2, int paramInt3) {
/* 112 */		 BiomeBase localBiomeBase = this.a.getBiome(paramInt1, paramInt3);
/* 113 */		 if (localBiomeBase == null) {
/* 114 */			 return null;
/*		 */		 }
/* 116 */		 return localBiomeBase.getMobs(paramEnumCreatureType);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition findNearestMapFeature(World paramWorld, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/* 120 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public int getLoadedChunks() {
/* 124 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkProviderFlat
 * JD-Core Version:		0.6.0
 */