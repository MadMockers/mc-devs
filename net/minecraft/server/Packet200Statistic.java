package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet200Statistic extends Packet
{
	public int a;
	public int b;

	public Packet200Statistic()
	{
	}

	public Packet200Statistic(int paramInt1, int paramInt2)
	{
		this.a = paramInt1;
		this.b = paramInt2;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.b);
	}

	public int a()
	{
		return 6;
	}

	public boolean a_()
	{
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet200Statistic
 * JD-Core Version:		0.6.0
 */