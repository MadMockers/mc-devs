package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet7UseEntity extends Packet
{
	public int a;
	public int target;
	public int action;

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.target = paramDataInputStream.readInt();
		this.action = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.target);
		paramDataOutputStream.writeByte(this.action);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 9;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet7UseEntity
 * JD-Core Version:		0.6.0
 */