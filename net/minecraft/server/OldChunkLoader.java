/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class OldChunkLoader
/*		 */ {
/*		 */	 public static OldChunk a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*	14 */		 int i = paramNBTTagCompound.getInt("xPos");
/*	15 */		 int j = paramNBTTagCompound.getInt("zPos");
/*		 */ 
/*	17 */		 OldChunk localOldChunk = new OldChunk(i, j);
/*	18 */		 localOldChunk.g = paramNBTTagCompound.getByteArray("Blocks");
/*	19 */		 localOldChunk.f = new OldNibbleArray(paramNBTTagCompound.getByteArray("Data"), 7);
/*	20 */		 localOldChunk.e = new OldNibbleArray(paramNBTTagCompound.getByteArray("SkyLight"), 7);
/*	21 */		 localOldChunk.d = new OldNibbleArray(paramNBTTagCompound.getByteArray("BlockLight"), 7);
/*	22 */		 localOldChunk.c = paramNBTTagCompound.getByteArray("HeightMap");
/*	23 */		 localOldChunk.b = paramNBTTagCompound.getBoolean("TerrainPopulated");
/*	24 */		 localOldChunk.h = paramNBTTagCompound.getList("Entities");
/*	25 */		 localOldChunk.i = paramNBTTagCompound.getList("TileEntities");
/*	26 */		 localOldChunk.j = paramNBTTagCompound.getList("TileTicks");
/*		 */		 try
/*		 */		 {
/*	30 */			 localOldChunk.a = paramNBTTagCompound.getLong("LastUpdate");
/*		 */		 } catch (ClassCastException localClassCastException) {
/*	32 */			 localOldChunk.a = paramNBTTagCompound.getInt("LastUpdate");
/*		 */		 }
/*		 */ 
/*	35 */		 return localOldChunk;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(OldChunk paramOldChunk, NBTTagCompound paramNBTTagCompound, WorldChunkManager paramWorldChunkManager)
/*		 */	 {
/*	40 */		 paramNBTTagCompound.setInt("xPos", paramOldChunk.k);
/*	41 */		 paramNBTTagCompound.setInt("zPos", paramOldChunk.l);
/*	42 */		 paramNBTTagCompound.setLong("LastUpdate", paramOldChunk.a);
/*	43 */		 int[] arrayOfInt = new int[paramOldChunk.c.length];
/*	44 */		 for (int i = 0; i < paramOldChunk.c.length; i++) {
/*	45 */			 arrayOfInt[i] = paramOldChunk.c[i];
/*		 */		 }
/*	47 */		 paramNBTTagCompound.setIntArray("HeightMap", arrayOfInt);
/*	48 */		 paramNBTTagCompound.setBoolean("TerrainPopulated", paramOldChunk.b);
/*		 */ 
/*	50 */		 NBTTagList localNBTTagList = new NBTTagList("Sections");
/*	51 */		 for (int j = 0; j < 8; j++)
/*		 */		 {
/*	54 */			 k = 1;
/*	55 */			 for (int m = 0; (m < 16) && (k != 0); m++) {
/*	56 */				 for (int i1 = 0; (i1 < 16) && (k != 0); i1++) {
/*	57 */					 for (int i2 = 0; i2 < 16; i2++) {
/*	58 */						 int i3 = m << 11 | i2 << 7 | i1 + (j << 4);
/*	59 */						 i4 = paramOldChunk.g[i3];
/*	60 */						 if (i4 != 0) {
/*	61 */							 k = 0;
/*	62 */							 break;
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	68 */			 if (k != 0)
/*		 */			 {
/*		 */				 continue;
/*		 */			 }
/*		 */ 
/*	73 */			 byte[] arrayOfByte2 = new byte[4096];
/*	74 */			 NibbleArray localNibbleArray1 = new NibbleArray(arrayOfByte2.length, 4);
/*	75 */			 NibbleArray localNibbleArray2 = new NibbleArray(arrayOfByte2.length, 4);
/*	76 */			 NibbleArray localNibbleArray3 = new NibbleArray(arrayOfByte2.length, 4);
/*		 */ 
/*	78 */			 for (int i4 = 0; i4 < 16; i4++) {
/*	79 */				 for (int i5 = 0; i5 < 16; i5++) {
/*	80 */					 for (int i6 = 0; i6 < 16; i6++) {
/*	81 */						 int i7 = i4 << 11 | i6 << 7 | i5 + (j << 4);
/*	82 */						 int i8 = paramOldChunk.g[i7];
/*		 */ 
/*	84 */						 arrayOfByte2[(i5 << 8 | i6 << 4 | i4)] = (byte)(i8 & 0xFF);
/*	85 */						 localNibbleArray1.a(i4, i5, i6, paramOldChunk.f.a(i4, i5 + (j << 4), i6));
/*	86 */						 localNibbleArray2.a(i4, i5, i6, paramOldChunk.e.a(i4, i5 + (j << 4), i6));
/*	87 */						 localNibbleArray3.a(i4, i5, i6, paramOldChunk.d.a(i4, i5 + (j << 4), i6));
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */ 
/*	92 */			 NBTTagCompound localNBTTagCompound = new NBTTagCompound();
/*		 */ 
/*	94 */			 localNBTTagCompound.setByte("Y", (byte)(j & 0xFF));
/*	95 */			 localNBTTagCompound.setByteArray("Blocks", arrayOfByte2);
/*	96 */			 localNBTTagCompound.setByteArray("Data", localNibbleArray1.a);
/*	97 */			 localNBTTagCompound.setByteArray("SkyLight", localNibbleArray2.a);
/*	98 */			 localNBTTagCompound.setByteArray("BlockLight", localNibbleArray3.a);
/*		 */ 
/* 100 */			 localNBTTagList.add(localNBTTagCompound);
/*		 */		 }
/* 102 */		 paramNBTTagCompound.set("Sections", localNBTTagList);
/*		 */ 
/* 105 */		 byte[] arrayOfByte1 = new byte[256];
/* 106 */		 for (int k = 0; k < 16; k++) {
/* 107 */			 for (int n = 0; n < 16; n++) {
/* 108 */				 arrayOfByte1[(n << 4 | k)] = (byte)(paramWorldChunkManager.getBiome(paramOldChunk.k << 4 | k, paramOldChunk.l << 4 | n).id & 0xFF);
/*		 */			 }
/*		 */		 }
/* 111 */		 paramNBTTagCompound.setByteArray("Biomes", arrayOfByte1);
/*		 */ 
/* 113 */		 paramNBTTagCompound.set("Entities", paramOldChunk.h);
/*		 */ 
/* 115 */		 paramNBTTagCompound.set("TileEntities", paramOldChunk.i);
/*		 */ 
/* 117 */		 if (paramOldChunk.j != null)
/* 118 */			 paramNBTTagCompound.set("TileTicks", paramOldChunk.j);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.OldChunkLoader
 * JD-Core Version:		0.6.0
 */