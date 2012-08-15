package net.minecraft.server;

public final class ThreadShutdown extends Thread
{
	public ThreadShutdown(DedicatedServer paramDedicatedServer)
	{
	}

	public void run()
	{
		this.a.stop();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ThreadShutdown
 * JD-Core Version:		0.6.0
 */