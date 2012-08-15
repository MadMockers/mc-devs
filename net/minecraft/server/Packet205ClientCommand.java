package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet205ClientCommand extends Packet
{
	public int a;

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a & 0xFF);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet205ClientCommand
 * JD-Core Version:		0.6.0
 */