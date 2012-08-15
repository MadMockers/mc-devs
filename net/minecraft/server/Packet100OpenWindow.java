package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet100OpenWindow extends Packet
{
	public int a;
	public int b;
	public String c;
	public int d;

	public Packet100OpenWindow()
	{
	}

	public Packet100OpenWindow(int paramInt1, int paramInt2, String paramString, int paramInt3)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramString;
		this.d = paramInt3;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = (paramDataInputStream.readByte() & 0xFF);
		this.b = (paramDataInputStream.readByte() & 0xFF);
		this.c = a(paramDataInputStream, 32);
		this.d = (paramDataInputStream.readByte() & 0xFF);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a & 0xFF);
		paramDataOutputStream.writeByte(this.b & 0xFF);
		a(this.c, paramDataOutputStream);
		paramDataOutputStream.writeByte(this.d & 0xFF);
	}

	public int a()
	{
		return 3 + this.c.length();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet100OpenWindow
 * JD-Core Version:		0.6.0
 */