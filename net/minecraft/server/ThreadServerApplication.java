package net.minecraft.server;

public class ThreadServerApplication extends Thread
{
	public ThreadServerApplication(MinecraftServer paramMinecraftServer, String paramString)
	{
		super(paramString);
	}
	public void run() {
		this.a.run();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ThreadServerApplication
 * JD-Core Version:		0.6.0
 */