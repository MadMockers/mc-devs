package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet22Collect extends Packet
{
	public int a;
	public int b;

	public Packet22Collect()
	{
	}

	public Packet22Collect(int paramInt1, int paramInt2)
	{
		this.a = paramInt1;
		this.b = paramInt2;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.b);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 8;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet22Collect
 * JD-Core Version:		0.6.0
 */