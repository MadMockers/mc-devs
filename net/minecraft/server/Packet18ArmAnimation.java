package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet18ArmAnimation extends Packet
{
	public int a;
	public int b;

	public Packet18ArmAnimation()
	{
	}

	public Packet18ArmAnimation(Entity paramEntity, int paramInt)
	{
		this.a = paramEntity.id;
		this.b = paramInt;
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

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet18ArmAnimation
 * JD-Core Version:		0.6.0
 */