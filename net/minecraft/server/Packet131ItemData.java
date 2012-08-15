package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet131ItemData extends Packet
{
	public short a;
	public short b;
	public byte[] c;

	public Packet131ItemData()
	{
		this.lowPriority = true;
	}

	public Packet131ItemData(short paramShort1, short paramShort2, byte[] paramArrayOfByte) {
		this.lowPriority = true;
		this.a = paramShort1;
		this.b = paramShort2;
		this.c = paramArrayOfByte;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readShort();
		this.b = paramDataInputStream.readShort();
		this.c = new byte[paramDataInputStream.readByte() & 0xFF];
		paramDataInputStream.readFully(this.c);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeShort(this.a);
		paramDataOutputStream.writeShort(this.b);
		paramDataOutputStream.writeByte(this.c.length);
		paramDataOutputStream.write(this.c);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 4 + this.c.length;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet131ItemData
 * JD-Core Version:		0.6.0
 */