package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet106Transaction extends Packet
{
	public int a;
	public short b;
	public boolean c;

	public Packet106Transaction()
	{
	}

	public Packet106Transaction(int paramInt, short paramShort, boolean paramBoolean)
	{
		this.a = paramInt;
		this.b = paramShort;
		this.c = paramBoolean;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
		this.b = paramDataInputStream.readShort();
		this.c = (paramDataInputStream.readByte() != 0);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
		paramDataOutputStream.writeShort(this.b);
		paramDataOutputStream.writeByte(this.c ? 1 : 0);
	}

	public int a()
	{
		return 4;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet106Transaction
 * JD-Core Version:		0.6.0
 */