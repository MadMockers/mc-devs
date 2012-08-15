/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.concurrent.Callable;
/*			*/ 
/*			*/ class CrashReportPlayers
/*			*/	 implements Callable
/*			*/ {
/*			*/	 CrashReportPlayers(World paramWorld)
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public String a()
/*			*/	 {
/* 2431 */		 return this.a.players.size() + " total; " + this.a.players.toString();
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportPlayers
 * JD-Core Version:		0.6.0
 */