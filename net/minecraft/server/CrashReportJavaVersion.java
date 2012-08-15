package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportJavaVersion
	implements Callable
{
	CrashReportJavaVersion(CrashReport paramCrashReport)
	{
	}

	public String a()
	{
		return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportJavaVersion
 * JD-Core Version:		0.6.0
 */