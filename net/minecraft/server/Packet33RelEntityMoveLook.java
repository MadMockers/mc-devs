package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet33RelEntityMoveLook extends Packet30Entity
{
	public Packet33RelEntityMoveLook()
	{
		this.g = true;
	}

	public Packet33RelEntityMoveLook(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5) {
		super(paramInt);
		this.b = paramByte1;
		this.c = paramByte2;
		this.d = paramByte3;
		this.e = paramByte4;
		this.f = paramByte5;
		this.g = true;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		super.a(paramDataInputStream);
		this.b = paramDataInputStream.readByte();
		this.c = paramDataInputStream.readByte();
		this.d = paramDataInputStream.readByte();
		this.e = paramDataInputStream.readByte();
		this.f = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		super.a(paramDataOutputStream);
		paramDataOutputStream.writeByte(this.b);
		paramDataOutputStream.writeByte(this.c);
		paramDataOutputStream.writeByte(this.d);
		paramDataOutputStream.writeByte(this.e);
		paramDataOutputStream.writeByte(this.f);
	}

	public int a()
	{
		return 9;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet33RelEntityMoveLook
 * JD-Core Version:		0.6.0
 */