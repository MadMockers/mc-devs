package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet38EntityStatus extends Packet
{
	public int a;
	public byte b;

	public Packet38EntityStatus()
	{
	}

	public Packet38EntityStatus(int paramInt, byte paramByte)
	{
		this.a = paramInt;
		this.b = paramByte;
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

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 5;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet38EntityStatus
 * JD-Core Version:		0.6.0
 */