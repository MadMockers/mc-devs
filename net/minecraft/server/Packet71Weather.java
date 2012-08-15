package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet71Weather extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public int e;

	public Packet71Weather()
	{
	}

	public Packet71Weather(Entity paramEntity)
	{
		this.a = paramEntity.id;
		this.b = MathHelper.floor(paramEntity.locX * 32.0D);
		this.c = MathHelper.floor(paramEntity.locY * 32.0D);
		this.d = MathHelper.floor(paramEntity.locZ * 32.0D);
		if ((paramEntity instanceof EntityLightning))
			this.e = 1;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.e = paramDataInputStream.readByte();
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.e);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 17;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet71Weather
 * JD-Core Version:		0.6.0
 */