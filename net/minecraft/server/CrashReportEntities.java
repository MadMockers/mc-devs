/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.List;
/*			*/ import java.util.concurrent.Callable;
/*			*/ 
/*			*/ class CrashReportEntities
/*			*/	 implements Callable
/*			*/ {
/*			*/	 CrashReportEntities(World paramWorld)
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public String a()
/*			*/	 {
/* 2425 */		 return this.a.entityList.size() + " total; " + this.a.entityList.toString();
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportEntities
 * JD-Core Version:		0.6.0
 */