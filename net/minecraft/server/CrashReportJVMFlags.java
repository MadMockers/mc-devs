package net.minecraft.server;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.Callable;

class CrashReportJVMFlags
	implements Callable
{
	CrashReportJVMFlags(CrashReport paramCrashReport)
	{
	}

	public String a()
	{
		RuntimeMXBean localRuntimeMXBean = ManagementFactory.getRuntimeMXBean();
		List localList = localRuntimeMXBean.getInputArguments();
		int i = 0;
		StringBuilder localStringBuilder = new StringBuilder();

		for (String str : localList) {
			if (str.startsWith("-X")) {
				if (i++ > 0) {
					localStringBuilder.append(" ");
				}

				localStringBuilder.append(str);
			}
		}

		return String.format("%d total; %s", new Object[] { Integer.valueOf(i), localStringBuilder.toString() });
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CrashReportJVMFlags
 * JD-Core Version:		0.6.0
 */