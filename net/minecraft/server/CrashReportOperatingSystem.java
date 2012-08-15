package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportOperatingSystem
	implements Callable
{
	CrashReportOperatingSystem(CrashReport paramCrashReport)
	{
	}

	public String a()
	{
		return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportOperatingSystem
 * JD-Core Version:		0.6.0
 */