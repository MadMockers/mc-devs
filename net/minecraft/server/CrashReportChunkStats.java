/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.concurrent.Callable;
/*			*/ 
/*			*/ class CrashReportChunkStats
/*			*/	 implements Callable
/*			*/ {
/*			*/	 CrashReportChunkStats(World paramWorld)
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public String a()
/*			*/	 {
/* 2437 */		 return this.a.chunkProvider.getName();
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportChunkStats
 * JD-Core Version:		0.6.0
 */