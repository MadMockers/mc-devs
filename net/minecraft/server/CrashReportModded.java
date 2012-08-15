/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.concurrent.Callable;
/*		 */ 
/*		 */ public class CrashReportModded
/*		 */	 implements Callable
/*		 */ {
/*		 */	 public CrashReportModded(MinecraftServer paramMinecraftServer)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public String a()
/*		 */	 {
/* 621 */		 String str = this.a.getServerModName();
/* 622 */		 if (!str.equals("vanilla")) return "Definitely; '" + str + "'";
/* 623 */		 return "Unknown (can't tell)";
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportModded
 * JD-Core Version:		0.6.0
 */