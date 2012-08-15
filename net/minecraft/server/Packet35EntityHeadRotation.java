package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet35EntityHeadRotation extends Packet
{
	public int a;
	public byte b;

	public Packet35EntityHeadRotation()
	{
	}

	public Packet35EntityHeadRotation(int paramInt, byte paramByte)
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

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		Packet35EntityHeadRotation localPacket35EntityHeadRotation = (Packet35EntityHeadRotation)paramPacket;
		return localPacket35EntityHeadRotation.a == this.a;
	}

	public boolean a_()
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet35EntityHeadRotation
 * JD-Core Version:		0.6.0
 */