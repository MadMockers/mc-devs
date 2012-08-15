package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet8UpdateHealth extends Packet
{
	public int a;
	public int b;
	public float c;

	public Packet8UpdateHealth()
	{
	}

	public Packet8UpdateHealth(int paramInt1, int paramInt2, float paramFloat)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramFloat;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readShort();
		this.b = paramDataInputStream.readShort();
		this.c = paramDataInputStream.readFloat();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeShort(this.a);
		paramDataOutputStream.writeShort(this.b);
		paramDataOutputStream.writeFloat(this.c);
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
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet8UpdateHealth
 * JD-Core Version:		0.6.0
 */