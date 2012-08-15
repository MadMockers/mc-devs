package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet16BlockItemSwitch extends Packet
{
	public int itemInHandIndex;

	public void a(DataInputStream paramDataInputStream)
	{
		this.itemInHandIndex = paramDataInputStream.readShort();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeShort(this.itemInHandIndex);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet16BlockItemSwitch
 * JD-Core Version:		0.6.0
 */