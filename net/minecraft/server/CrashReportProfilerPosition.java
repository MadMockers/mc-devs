package net.minecraft.server;

import java.util.concurrent.Callable;

public class CrashReportProfilerPosition
	implements Callable
{
	public CrashReportProfilerPosition(MinecraftServer paramMinecraftServer)
	{
	}

	public String a()
	{
		return this.a.methodProfiler.a ? this.a.methodProfiler.c() : "N/A (disabled)";
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CrashReportProfilerPosition
 * JD-Core Version:		0.6.0
 */