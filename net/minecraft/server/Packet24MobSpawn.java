package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class Packet24MobSpawn extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public int e;
	public int f;
	public int g;
	public int h;
	public byte i;
	public byte j;
	public byte k;
	private DataWatcher s;
	private List t;

	public Packet24MobSpawn()
	{
	}

	public Packet24MobSpawn(EntityLiving paramEntityLiving)
	{
		this.a = paramEntityLiving.id;

		this.b = (byte)EntityTypes.a(paramEntityLiving);
		this.c = paramEntityLiving.am.a(paramEntityLiving.locX);
		this.d = MathHelper.floor(paramEntityLiving.locY * 32.0D);
		this.e = paramEntityLiving.am.a(paramEntityLiving.locZ);
		this.i = (byte)(int)(paramEntityLiving.yaw * 256.0F / 360.0F);
		this.j = (byte)(int)(paramEntityLiving.pitch * 256.0F / 360.0F);
		this.k = (byte)(int)(paramEntityLiving.as * 256.0F / 360.0F);

		double d1 = 3.9D;
		double d2 = paramEntityLiving.motX;
		double d3 = paramEntityLiving.motY;
		double d4 = paramEntityLiving.motZ;
		if (d2 < -d1) d2 = -d1;
		if (d3 < -d1) d3 = -d1;
		if (d4 < -d1) d4 = -d1;
		if (d2 > d1) d2 = d1;
		if (d3 > d1) d3 = d1;
		if (d4 > d1) d4 = d1;
		this.f = (int)(d2 * 8000.0D);
		this.g = (int)(d3 * 8000.0D);
		this.h = (int)(d4 * 8000.0D);

		this.s = paramEntityLiving.getDataWatcher();
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = (paramDataInputStream.readByte() & 0xFF);
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = paramDataInputStream.readInt();
		this.i = paramDataInputStream.readByte();
		this.j = paramDataInputStream.readByte();
		this.k = paramDataInputStream.readByte();
		this.f = paramDataInputStream.readShort();
		this.g = paramDataInputStream.readShort();
		this.h = paramDataInputStream.readShort();
		this.t = DataWatcher.a(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.b & 0xFF);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.writeInt(this.e);
		paramDataOutputStream.writeByte(this.i);
		paramDataOutputStream.writeByte(this.j);
		paramDataOutputStream.writeByte(this.k);
		paramDataOutputStream.writeShort(this.f);
		paramDataOutputStream.writeShort(this.g);
		paramDataOutputStream.writeShort(this.h);
		this.s.a(paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 26;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet24MobSpawn
 * JD-Core Version:		0.6.0
 */