/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.concurrent.Callable;
/*		 */ 
/*		 */ public class CrashReportPlayerCount
/*		 */	 implements Callable
/*		 */ {
/*		 */	 public CrashReportPlayerCount(MinecraftServer paramMinecraftServer)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public String a()
/*		 */	 {
/* 636 */		 return MinecraftServer.a(this.a).getPlayerCount() + " / " + MinecraftServer.a(this.a).getMaxPlayers() + "; " + MinecraftServer.a(this.a).players;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportPlayerCount
 * JD-Core Version:		0.6.0
 */