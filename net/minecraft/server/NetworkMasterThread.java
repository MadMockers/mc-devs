package net.minecraft.server;

class NetworkMasterThread extends Thread
{
	NetworkMasterThread(NetworkManager paramNetworkManager)
	{
	}

	public void run()
	{
		try
		{
			Thread.sleep(5000L);
			if (NetworkManager.g(this.a).isAlive())
				try {
					NetworkManager.g(this.a).stop();
				}
				catch (Throwable localThrowable1) {
				}
			if (NetworkManager.h(this.a).isAlive())
				try {
					NetworkManager.h(this.a).stop();
				} catch (Throwable localThrowable2) {
				}
		}
		catch (InterruptedException localInterruptedException) {
			localInterruptedException.printStackTrace();
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NetworkMasterThread
 * JD-Core Version:		0.6.0
 */