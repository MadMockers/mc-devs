package net.minecraft.server;

class NetworkMonitorThread extends Thread
{
	NetworkMonitorThread(NetworkManager paramNetworkManager)
	{
	}

	public void run()
	{
		try
		{
			Thread.sleep(2000L);
			if (NetworkManager.a(this.a)) {
				NetworkManager.h(this.a).interrupt();
				this.a.a("disconnect.closed", new Object[0]);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NetworkMonitorThread
 * JD-Core Version:		0.6.0
 */