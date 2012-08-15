package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportJavaVMVersion
	implements Callable
{
	CrashReportJavaVMVersion(CrashReport paramCrashReport)
	{
	}

	public String a()
	{
		return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportJavaVMVersion
 * JD-Core Version:		0.6.0
 */