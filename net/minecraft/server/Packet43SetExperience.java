package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet43SetExperience extends Packet
{
	public float a;
	public int b;
	public int c;

	public Packet43SetExperience()
	{
	}

	public Packet43SetExperience(float paramFloat, int paramInt1, int paramInt2)
	{
		this.a = paramFloat;
		this.b = paramInt1;
		this.c = paramInt2;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readFloat();
		this.c = paramDataInputStream.readShort();
		this.b = paramDataInputStream.readShort();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeFloat(this.a);
		paramDataOutputStream.writeShort(this.c);
		paramDataOutputStream.writeShort(this.b);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
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
 * Qualified Name:		 net.minecraft.server.Packet43SetExperience
 * JD-Core Version:		0.6.0
 */