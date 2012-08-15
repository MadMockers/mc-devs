package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet101CloseWindow extends Packet
{
	public int a;

	public Packet101CloseWindow()
	{
	}

	public Packet101CloseWindow(int paramInt)
	{
		this.a = paramInt;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.handleContainerClose(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
	}

	public int a()
	{
		return 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet101CloseWindow
 * JD-Core Version:		0.6.0
 */