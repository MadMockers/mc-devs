package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet31RelEntityMove extends Packet30Entity
{
	public Packet31RelEntityMove()
	{
	}

	public Packet31RelEntityMove(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3)
	{
		super(paramInt);
		this.b = paramByte1;
		this.c = paramByte2;
		this.d = paramByte3;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		super.a(paramDataInputStream);
		this.b = paramDataInputStream.readByte();
		this.c = paramDataInputStream.readByte();
		this.d = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		super.a(paramDataOutputStream);
		paramDataOutputStream.writeByte(this.b);
		paramDataOutputStream.writeByte(this.c);
		paramDataOutputStream.writeByte(this.d);
	}

	public int a()
	{
		return 7;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet31RelEntityMove
 * JD-Core Version:		0.6.0
 */