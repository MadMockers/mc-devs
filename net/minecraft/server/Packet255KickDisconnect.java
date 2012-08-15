package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet255KickDisconnect extends Packet
{
	public String a;

	public Packet255KickDisconnect()
	{
	}

	public Packet255KickDisconnect(String paramString)
	{
		this.a = paramString;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = a(paramDataInputStream, 256);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.a, paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return this.a.length();
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet255KickDisconnect
 * JD-Core Version:		0.6.0
 */