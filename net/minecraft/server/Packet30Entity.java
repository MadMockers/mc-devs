package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet30Entity extends Packet
{
	public int a;
	public byte b;
	public byte c;
	public byte d;
	public byte e;
	public byte f;
	public boolean g = false;

	public Packet30Entity() {
	}

	public Packet30Entity(int paramInt) {
		this.a = paramInt;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 4;
	}

	public String toString()
	{
		return "Entity_" + super.toString();
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		Packet30Entity localPacket30Entity = (Packet30Entity)paramPacket;
		return localPacket30Entity.a == this.a;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet30Entity
 * JD-Core Version:		0.6.0
 */