/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.io.FileInputStream;
/*		 */ import java.io.PrintStream;
/*		 */ 
/*		 */ public class WorldLoader
/*		 */	 implements Convertable
/*		 */ {
/*		 */	 protected final File a;
/*		 */ 
/*		 */	 public WorldLoader(File paramFile)
/*		 */	 {
/*	19 */		 if (!paramFile.exists()) paramFile.mkdirs();
/*	20 */		 this.a = paramFile;
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public WorldData c(String paramString)
/*		 */	 {
/*	48 */		 File localFile1 = new File(this.a, paramString);
/*	49 */		 if (!localFile1.exists()) return null;
/*		 */ 
/*	51 */		 File localFile2 = new File(localFile1, "level.dat");
/*		 */		 NBTTagCompound localNBTTagCompound3;
/*	52 */		 if (localFile2.exists()) {
/*		 */			 try {
/*	54 */				 NBTTagCompound localNBTTagCompound1 = NBTCompressedStreamTools.a(new FileInputStream(localFile2));
/*	55 */				 localNBTTagCompound3 = localNBTTagCompound1.getCompound("Data");
/*	56 */				 return new WorldData(localNBTTagCompound3);
/*		 */			 } catch (Exception localException1) {
/*	58 */				 localException1.printStackTrace();
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	62 */		 localFile2 = new File(localFile1, "level.dat_old");
/*	63 */		 if (localFile2.exists()) {
/*		 */			 try {
/*	65 */				 NBTTagCompound localNBTTagCompound2 = NBTCompressedStreamTools.a(new FileInputStream(localFile2));
/*	66 */				 localNBTTagCompound3 = localNBTTagCompound2.getCompound("Data");
/*	67 */				 return new WorldData(localNBTTagCompound3);
/*		 */			 } catch (Exception localException2) {
/*	69 */				 localException2.printStackTrace();
/*		 */			 }
/*		 */		 }
/*	72 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(String paramString)
/*		 */	 {
/* 112 */		 File localFile = new File(this.a, paramString);
/* 113 */		 if (!localFile.exists()) return;
/*		 */ 
/* 115 */		 a(localFile.listFiles());
/* 116 */		 localFile.delete();
/*		 */	 }
/*		 */ 
/*		 */	 protected static void a(File[] paramArrayOfFile) {
/* 120 */		 for (File localFile : paramArrayOfFile) {
/* 121 */			 if (localFile.isDirectory()) {
/* 122 */				 System.out.println("Deleting " + localFile);
/* 123 */				 a(localFile.listFiles());
/*		 */			 }
/* 125 */			 localFile.delete();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public IDataManager a(String paramString, boolean paramBoolean) {
/* 130 */		 return new WorldNBTStorage(this.a, paramString, paramBoolean);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isConvertable(String paramString)
/*		 */	 {
/* 138 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean convert(String paramString, IProgressUpdate paramIProgressUpdate) {
/* 142 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldLoader
 * JD-Core Version:		0.6.0
 */