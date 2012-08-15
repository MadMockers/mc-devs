package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet0KeepAlive extends Packet
{
	public int a;

	public Packet0KeepAlive()
	{
	}

	public Packet0KeepAlive(int paramInt)
	{
		this.a = paramInt;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
	}

	public int a()
	{
		return 4;
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

/* 
 * Qualified Name:		 net.minecraft.server.Packet0KeepAlive
 * JD-Core Version:		0.6.0
 */