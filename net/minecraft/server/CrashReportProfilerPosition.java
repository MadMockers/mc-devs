/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.concurrent.Callable;
/*		 */ 
/*		 */ public class CrashReportProfilerPosition
/*		 */	 implements Callable
/*		 */ {
/*		 */	 public CrashReportProfilerPosition(MinecraftServer paramMinecraftServer)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public String a()
/*		 */	 {
/* 629 */		 return this.a.methodProfiler.a ? this.a.methodProfiler.c() : "N/A (disabled)";
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportProfilerPosition
 * JD-Core Version:		0.6.0
 */