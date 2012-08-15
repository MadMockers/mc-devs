package net.minecraft.server;

import java.util.List;
import java.util.concurrent.Callable;

class CrashReportPlayers
	implements Callable
{
	CrashReportPlayers(World paramWorld)
	{
	}

	public String a()
	{
		return this.a.players.size() + " total; " + this.a.players.toString();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CrashReportPlayers
 * JD-Core Version:		0.6.0
 */