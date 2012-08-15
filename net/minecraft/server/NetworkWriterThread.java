package net.minecraft.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

class NetworkWriterThread extends Thread
{
	final NetworkManager a;

	NetworkWriterThread(NetworkManager networkmanager, String s)
	{
		super(s);
		this.a = networkmanager;
	}

	public void run() {
		NetworkManager.b.getAndIncrement();
		try
		{
			while (NetworkManager.a(this.a))
			{
				for (boolean flag = false; NetworkManager.d(this.a); flag = true);
				try
				{
					if ((flag) && (NetworkManager.e(this.a) != null))
						NetworkManager.e(this.a).flush();
				}
				catch (IOException ioexception) {
					if (!NetworkManager.f(this.a)) {
						NetworkManager.a(this.a, ioexception);
					}

				}

				try
				{
					sleep(2L);
				} catch (InterruptedException interruptedexception) {
				}
			}
		}
		finally {
			NetworkManager.b.getAndDecrement();
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetworkWriterThread
 * JD-Core Version:		0.6.0
 */