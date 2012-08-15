package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportChunkStats
	implements Callable
{
	CrashReportChunkStats(World paramWorld)
	{
	}

	public String a()
	{
		return this.a.chunkProvider.getName();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CrashReportChunkStats
 * JD-Core Version:		0.6.0
 */