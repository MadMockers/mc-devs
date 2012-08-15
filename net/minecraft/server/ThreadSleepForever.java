package net.minecraft.server;

class ThreadSleepForever extends Thread
{
	ThreadSleepForever(DedicatedServer paramDedicatedServer)
	{
		setDaemon(true);
		start();
	}

	public void run()
	{
		while (true)
			try {
				Thread.sleep(2147483647L);

				continue;
			}
			catch (InterruptedException localInterruptedException)
			{
			}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ThreadSleepForever
 * JD-Core Version:		0.6.0
 */