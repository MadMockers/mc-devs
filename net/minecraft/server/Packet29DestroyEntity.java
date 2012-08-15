package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet29DestroyEntity extends Packet
{
	public int[] a;

	public Packet29DestroyEntity()
	{
	}

	public Packet29DestroyEntity(int[] paramArrayOfInt)
	{
		this.a = paramArrayOfInt;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = new int[paramDataInputStream.readByte()];

		for (int i = 0; i < this.a.length; i++)
			this.a[i] = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a.length);

		for (int i = 0; i < this.a.length; i++)
			paramDataOutputStream.writeInt(this.a[i]);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 1 + this.a.length * 4;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet29DestroyEntity
 * JD-Core Version:		0.6.0
 */