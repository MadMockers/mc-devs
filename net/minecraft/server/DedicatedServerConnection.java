package net.minecraft.server;

import java.net.InetAddress;

public class DedicatedServerConnection extends ServerConnection
{
	private final DedicatedServerConnectionThread c;

	public DedicatedServerConnection(MinecraftServer paramMinecraftServer, InetAddress paramInetAddress, int paramInt)
	{
		super(paramMinecraftServer);

		this.c = new DedicatedServerConnectionThread(this, paramInetAddress, paramInt);
		this.c.start();
	}

	public void a()
	{
		super.a();
		this.c.b();
		this.c.interrupt();
	}

	public void b()
	{
		this.c.a();
		super.b();
	}

	public DedicatedServer c()
	{
		return (DedicatedServer)super.d();
	}

	public void a(InetAddress paramInetAddress) {
		this.c.a(paramInetAddress);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.DedicatedServerConnection
 * JD-Core Version:		0.6.0
 */