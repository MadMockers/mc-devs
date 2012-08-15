package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet70Bed extends Packet
{
	public static final String[] a = { "tile.bed.notValid", null, null, "gameMode.changed" };
	public int b;
	public int c;

	public Packet70Bed()
	{
	}

	public Packet70Bed(int paramInt1, int paramInt2)
	{
		this.b = paramInt1;
		this.c = paramInt2;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.b = paramDataInputStream.readByte();
		this.c = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.b);
		paramDataOutputStream.writeByte(this.c);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet70Bed
 * JD-Core Version:		0.6.0
 */