package net.minecraft.server;

import java.util.concurrent.atomic.AtomicInteger;

class NetworkReaderThread extends Thread
{
	NetworkReaderThread(NetworkManager paramNetworkManager, String paramString)
	{
		super(paramString);
	}
	public void run() {
		NetworkManager.a.getAndIncrement();
		try {
			while ((NetworkManager.a(this.a)) && (!NetworkManager.b(this.a))) {
				while (NetworkManager.c(this.a));
				try
				{
					sleep(2L);
				} catch (InterruptedException localInterruptedException) {
				}
			}
		} finally {
			NetworkManager.a.getAndDecrement();
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NetworkReaderThread
 * JD-Core Version:		0.6.0
 */