/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.io.FilenameFilter;
/*		 */ 
/*		 */ class ChunkFilenameFilter
/*		 */	 implements FilenameFilter
/*		 */ {
/*		 */	 ChunkFilenameFilter(WorldLoaderServer paramWorldLoaderServer)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean accept(File paramFile, String paramString)
/*		 */	 {
/* 241 */		 return paramString.endsWith(".mcr");
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkFilenameFilter
 * JD-Core Version:		0.6.0
 */