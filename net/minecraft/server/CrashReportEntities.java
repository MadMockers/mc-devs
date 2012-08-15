package net.minecraft.server;

import java.util.List;
import java.util.concurrent.Callable;

class CrashReportEntities
	implements Callable
{
	CrashReportEntities(World paramWorld)
	{
	}

	public String a()
	{
		return this.a.entityList.size() + " total; " + this.a.entityList.toString();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CrashReportEntities
 * JD-Core Version:		0.6.0
 */