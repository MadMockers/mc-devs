package net.minecraft.server;

import java.net.SocketAddress;

public abstract interface INetworkManager
{
	public abstract void a(NetHandler paramNetHandler);

	public abstract void queue(Packet paramPacket);

	public abstract void a();

	public abstract void b();

	public abstract SocketAddress getSocketAddress();

	public abstract void d();

	public abstract int e();

	public abstract void a(String paramString, Object[] paramArrayOfObject);
}

/* 
 * Qualified Name:		 net.minecraft.server.INetworkManager
 * JD-Core Version:		0.6.0
 */