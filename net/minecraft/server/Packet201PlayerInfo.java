package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet201PlayerInfo extends Packet
{
	public String a;
	public boolean b;
	public int c;

	public Packet201PlayerInfo()
	{
	}

	public Packet201PlayerInfo(String paramString, boolean paramBoolean, int paramInt)
	{
		this.a = paramString;
		this.b = paramBoolean;
		this.c = paramInt;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = a(paramDataInputStream, 16);
		this.b = (paramDataInputStream.readByte() != 0);
		this.c = paramDataInputStream.readShort();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.a, paramDataOutputStream);
		paramDataOutputStream.writeByte(this.b ? 1 : 0);
		paramDataOutputStream.writeShort(this.c);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return this.a.length() + 2 + 1 + 2;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet201PlayerInfo
 * JD-Core Version:		0.6.0
 */