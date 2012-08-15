/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.File;
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collection;
/*		 */ import java.util.Collections;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public class WorldLoaderServer extends WorldLoader
/*		 */ {
/*		 */	 public WorldLoaderServer(File paramFile)
/*		 */	 {
/*	31 */		 super(paramFile);
/*		 */	 }
/*		 */ 
/*		 */	 protected int c()
/*		 */	 {
/*	68 */		 return 19133;
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/*	73 */		 RegionFileCache.a();
/*		 */	 }
/*		 */ 
/*		 */	 public IDataManager a(String paramString, boolean paramBoolean)
/*		 */	 {
/*	78 */		 return new ServerNBTManager(this.a, paramString, paramBoolean);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isConvertable(String paramString)
/*		 */	 {
/*	95 */		 WorldData localWorldData = c(paramString);
/*		 */ 
/*	97 */		 return (localWorldData != null) && (localWorldData.k() != c());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean convert(String paramString, IProgressUpdate paramIProgressUpdate)
/*		 */	 {
/* 105 */		 paramIProgressUpdate.a(0);
/*		 */ 
/* 107 */		 ArrayList localArrayList1 = new ArrayList();
/* 108 */		 ArrayList localArrayList2 = new ArrayList();
/* 109 */		 ArrayList localArrayList3 = new ArrayList();
/* 110 */		 File localFile1 = new File(this.a, paramString);
/* 111 */		 File localFile2 = new File(localFile1, "DIM-1");
/* 112 */		 File localFile3 = new File(localFile1, "DIM1");
/*		 */ 
/* 114 */		 System.out.println("Scanning folders...");
/*		 */ 
/* 117 */		 a(localFile1, localArrayList1);
/*		 */ 
/* 120 */		 if (localFile2.exists()) {
/* 121 */			 a(localFile2, localArrayList2);
/*		 */		 }
/* 123 */		 if (localFile3.exists()) {
/* 124 */			 a(localFile3, localArrayList3);
/*		 */		 }
/*		 */ 
/* 127 */		 int i = localArrayList1.size() + localArrayList2.size() + localArrayList3.size();
/* 128 */		 System.out.println("Total conversion count is " + i);
/*		 */ 
/* 130 */		 WorldData localWorldData = c(paramString);
/*		 */ 
/* 132 */		 Object localObject = null;
/* 133 */		 if (localWorldData.getType() == WorldType.FLAT)
/* 134 */			 localObject = new WorldChunkManagerHell(BiomeBase.PLAINS, 0.5F, 0.5F);
/*		 */		 else {
/* 136 */			 localObject = new WorldChunkManager(localWorldData.getSeed(), localWorldData.getType());
/*		 */		 }
/*		 */ 
/* 140 */		 a(new File(localFile1, "region"), localArrayList1, (WorldChunkManager)localObject, 0, i, paramIProgressUpdate);
/*		 */ 
/* 142 */		 a(new File(localFile2, "region"), localArrayList2, new WorldChunkManagerHell(BiomeBase.HELL, 1.0F, 0.0F), localArrayList1.size(), i, paramIProgressUpdate);
/*		 */ 
/* 144 */		 a(new File(localFile3, "region"), localArrayList3, new WorldChunkManagerHell(BiomeBase.SKY, 0.5F, 0.0F), localArrayList1.size() + localArrayList2.size(), i, paramIProgressUpdate);
/*		 */ 
/* 146 */		 localWorldData.e(19133);
/* 147 */		 if (localWorldData.getType() == WorldType.NORMAL_1_1) {
/* 148 */			 localWorldData.setType(WorldType.NORMAL);
/*		 */		 }
/*		 */ 
/* 151 */		 f(paramString);
/*		 */ 
/* 153 */		 IDataManager localIDataManager = a(paramString, false);
/* 154 */		 localIDataManager.saveWorldData(localWorldData);
/*		 */ 
/* 156 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private void f(String paramString) {
/* 160 */		 File localFile1 = new File(this.a, paramString);
/* 161 */		 if (!localFile1.exists()) {
/* 162 */			 System.out.println("Warning: Unable to create level.dat_mcr backup");
/* 163 */			 return;
/*		 */		 }
/*		 */ 
/* 166 */		 File localFile2 = new File(localFile1, "level.dat");
/* 167 */		 if (!localFile2.exists()) {
/* 168 */			 System.out.println("Warning: Unable to create level.dat_mcr backup");
/* 169 */			 return;
/*		 */		 }
/*		 */ 
/* 172 */		 File localFile3 = new File(localFile1, "level.dat_mcr");
/* 173 */		 if (!localFile2.renameTo(localFile3))
/* 174 */			 System.out.println("Warning: Unable to create level.dat_mcr backup");
/*		 */	 }
/*		 */ 
/*		 */	 private void a(File paramFile, Iterable paramIterable, WorldChunkManager paramWorldChunkManager, int paramInt1, int paramInt2, IProgressUpdate paramIProgressUpdate)
/*		 */	 {
/* 180 */		 for (File localFile : paramIterable) {
/* 181 */			 a(paramFile, localFile, paramWorldChunkManager, paramInt1, paramInt2, paramIProgressUpdate);
/*		 */ 
/* 183 */			 paramInt1++;
/* 184 */			 int i = (int)Math.round(100.0D * paramInt1 / paramInt2);
/* 185 */			 paramIProgressUpdate.a(i);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(File paramFile1, File paramFile2, WorldChunkManager paramWorldChunkManager, int paramInt1, int paramInt2, IProgressUpdate paramIProgressUpdate)
/*		 */	 {
/*		 */		 try
/*		 */		 {
/* 193 */			 String str = paramFile2.getName();
/*		 */ 
/* 195 */			 RegionFile localRegionFile1 = new RegionFile(paramFile2);
/* 196 */			 RegionFile localRegionFile2 = new RegionFile(new File(paramFile1, str.substring(0, str.length() - ".mcr".length()) + ".mca"));
/*		 */ 
/* 198 */			 for (int i = 0; i < 32; i++) {
/* 199 */				 for (int j = 0; j < 32; j++)
/* 200 */					 if ((localRegionFile1.c(i, j)) && (!localRegionFile2.c(i, j))) {
/* 201 */						 DataInputStream localDataInputStream = localRegionFile1.a(i, j);
/* 202 */						 if (localDataInputStream == null) {
/* 203 */							 System.out.println("Failed to fetch input stream");
/*		 */						 }
/*		 */						 else {
/* 206 */							 NBTTagCompound localNBTTagCompound1 = NBTCompressedStreamTools.a(localDataInputStream);
/* 207 */							 localDataInputStream.close();
/*		 */ 
/* 209 */							 NBTTagCompound localNBTTagCompound2 = localNBTTagCompound1.getCompound("Level");
/* 210 */							 OldChunk localOldChunk = OldChunkLoader.a(localNBTTagCompound2);
/*		 */ 
/* 212 */							 NBTTagCompound localNBTTagCompound3 = new NBTTagCompound();
/* 213 */							 NBTTagCompound localNBTTagCompound4 = new NBTTagCompound();
/* 214 */							 localNBTTagCompound3.set("Level", localNBTTagCompound4);
/* 215 */							 OldChunkLoader.a(localOldChunk, localNBTTagCompound4, paramWorldChunkManager);
/*		 */ 
/* 217 */							 DataOutputStream localDataOutputStream = localRegionFile2.b(i, j);
/* 218 */							 NBTCompressedStreamTools.a(localNBTTagCompound3, localDataOutputStream);
/* 219 */							 localDataOutputStream.close();
/*		 */						 }
/*		 */					 }
/* 222 */				 j = (int)Math.round(100.0D * (paramInt1 * 1024) / (paramInt2 * 1024));
/* 223 */				 int k = (int)Math.round(100.0D * ((i + 1) * 32 + paramInt1 * 1024) / (paramInt2 * 1024));
/* 224 */				 if (k > j) {
/* 225 */					 paramIProgressUpdate.a(k);
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 229 */			 localRegionFile1.c();
/* 230 */			 localRegionFile2.c();
/*		 */		 } catch (IOException localIOException) {
/* 232 */			 localIOException.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void a(File paramFile, Collection paramCollection)
/*		 */	 {
/* 238 */		 File localFile = new File(paramFile, "region");
/* 239 */		 File[] arrayOfFile = localFile.listFiles(new ChunkFilenameFilter(this));
/*		 */ 
/* 245 */		 if (arrayOfFile != null)
/* 246 */			 Collections.addAll(paramCollection, arrayOfFile);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldLoaderServer
 * JD-Core Version:		0.6.0
 */