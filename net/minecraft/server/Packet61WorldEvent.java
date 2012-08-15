package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet61WorldEvent extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public int e;

	public Packet61WorldEvent()
	{
	}

	public Packet61WorldEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		this.a = paramInt1;
		this.c = paramInt2;
		this.d = paramInt3;
		this.e = paramInt4;
		this.b = paramInt5;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = (paramDataInputStream.readByte() & 0xFF);
		this.e = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeByte(this.d & 0xFF);
		paramDataOutputStream.writeInt(this.e);
		paramDataOutputStream.writeInt(this.b);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 20;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet61WorldEvent
 * JD-Core Version:		0.6.0
 */