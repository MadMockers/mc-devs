package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet4UpdateTime extends Packet
{
	public long a;

	public Packet4UpdateTime()
	{
	}

	public Packet4UpdateTime(long paramLong)
	{
		this.a = paramLong;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readLong();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeLong(this.a);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 8;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		return true;
	}

	public boolean a_()
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet4UpdateTime
 * JD-Core Version:		0.6.0
 */