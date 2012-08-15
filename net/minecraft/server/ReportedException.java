package net.minecraft.server;

public class ReportedException extends RuntimeException
{
	private final CrashReport a;

	public ReportedException(CrashReport paramCrashReport)
	{
		this.a = paramCrashReport;
	}

	public CrashReport a() {
		return this.a;
	}

	public Throwable getCause()
	{
		return this.a.b();
	}

	public String getMessage()
	{
		return this.a.a();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ReportedException
 * JD-Core Version:		0.6.0
 */