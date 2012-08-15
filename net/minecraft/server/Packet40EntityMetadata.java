package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class Packet40EntityMetadata extends Packet
{
	public int a;
	private List b;

	public Packet40EntityMetadata()
	{
	}

	public Packet40EntityMetadata(int paramInt, DataWatcher paramDataWatcher)
	{
		this.a = paramInt;
		this.b = paramDataWatcher.b();
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = DataWatcher.a(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		DataWatcher.a(this.b, paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 5;
	}

	public boolean a_()
	{
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet40EntityMetadata
 * JD-Core Version:		0.6.0
 */