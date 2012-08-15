package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet105CraftProgressBar extends Packet
{
	public int a;
	public int b;
	public int c;

	public Packet105CraftProgressBar()
	{
	}

	public Packet105CraftProgressBar(int paramInt1, int paramInt2, int paramInt3)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
		this.b = paramDataInputStream.readShort();
		this.c = paramDataInputStream.readShort();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
		paramDataOutputStream.writeShort(this.b);
		paramDataOutputStream.writeShort(this.c);
	}

	public int a()
	{
		return 5;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet105CraftProgressBar
 * JD-Core Version:		0.6.0
 */