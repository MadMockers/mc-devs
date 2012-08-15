package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet21PickupSpawn extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public byte e;
	public byte f;
	public byte g;
	public int h;
	public int i;
	public int j;

	public Packet21PickupSpawn()
	{
	}

	public Packet21PickupSpawn(EntityItem paramEntityItem)
	{
		this.a = paramEntityItem.id;
		this.h = paramEntityItem.itemStack.id;
		this.i = paramEntityItem.itemStack.count;
		this.j = paramEntityItem.itemStack.getData();
		this.b = MathHelper.floor(paramEntityItem.locX * 32.0D);
		this.c = MathHelper.floor(paramEntityItem.locY * 32.0D);
		this.d = MathHelper.floor(paramEntityItem.locZ * 32.0D);
		this.e = (byte)(int)(paramEntityItem.motX * 128.0D);
		this.f = (byte)(int)(paramEntityItem.motY * 128.0D);
		this.g = (byte)(int)(paramEntityItem.motZ * 128.0D);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.h = paramDataInputStream.readShort();
		this.i = paramDataInputStream.readByte();
		this.j = paramDataInputStream.readShort();
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = paramDataInputStream.readByte();
		this.f = paramDataInputStream.readByte();
		this.g = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeShort(this.h);
		paramDataOutputStream.writeByte(this.i);
		paramDataOutputStream.writeShort(this.j);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.writeByte(this.e);
		paramDataOutputStream.writeByte(this.f);
		paramDataOutputStream.writeByte(this.g);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 24;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet21PickupSpawn
 * JD-Core Version:		0.6.0
 */