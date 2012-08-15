package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet14BlockDig extends Packet
{
	public int a;
	public int b;
	public int c;
	public int face;
	public int e;

	public void a(DataInputStream paramDataInputStream)
	{
		this.e = paramDataInputStream.read();
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.read();
		this.c = paramDataInputStream.readInt();
		this.face = paramDataInputStream.read();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.write(this.e);
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.write(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.write(this.face);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 11;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet14BlockDig
 * JD-Core Version:		0.6.0
 */