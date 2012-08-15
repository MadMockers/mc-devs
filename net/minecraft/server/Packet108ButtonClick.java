package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet108ButtonClick extends Packet
{
	public int a;
	public int b;

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
		this.b = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
		paramDataOutputStream.writeByte(this.b);
	}

	public int a()
	{
		return 2;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet108ButtonClick
 * JD-Core Version:		0.6.0
 */