package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet19EntityAction extends Packet
{
	public int a;
	public int animation;

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.animation = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.animation);
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
 * Qualified Name:		 net.minecraft.server.Packet19EntityAction
 * JD-Core Version:		0.6.0
 */