package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet32EntityLook extends Packet30Entity
{
	public Packet32EntityLook()
	{
		this.g = true;
	}

	public Packet32EntityLook(int paramInt, byte paramByte1, byte paramByte2) {
		super(paramInt);
		this.e = paramByte1;
		this.f = paramByte2;
		this.g = true;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		super.a(paramDataInputStream);
		this.e = paramDataInputStream.readByte();
		this.f = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		super.a(paramDataOutputStream);
		paramDataOutputStream.writeByte(this.e);
		paramDataOutputStream.writeByte(this.f);
	}

	public int a()
	{
		return 6;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet32EntityLook
 * JD-Core Version:		0.6.0
 */