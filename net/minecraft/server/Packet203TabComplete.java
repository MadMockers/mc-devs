package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet203TabComplete extends Packet
{
	private String a;

	public Packet203TabComplete()
	{
	}

	public Packet203TabComplete(String paramString)
	{
		this.a = paramString;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = a(paramDataInputStream, Packet3Chat.a);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.a, paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2 + this.a.length() * 2;
	}

	public String d() {
		return this.a;
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

/* 
 * Qualified Name:		 net.minecraft.server.Packet203TabComplete
 * JD-Core Version:		0.6.0
 */