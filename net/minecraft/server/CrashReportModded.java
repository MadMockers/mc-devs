package net.minecraft.server;

import java.util.concurrent.Callable;

public class CrashReportModded
	implements Callable
{
	public CrashReportModded(MinecraftServer paramMinecraftServer)
	{
	}

	public String a()
	{
		String str = this.a.getServerModName();
		if (!str.equals("vanilla")) return "Definitely; '" + str + "'";
		return "Unknown (can't tell)";
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CrashReportModded
 * JD-Core Version:		0.6.0
 */