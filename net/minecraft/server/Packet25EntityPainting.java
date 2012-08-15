package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet25EntityPainting extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public int e;
	public String f;

	public Packet25EntityPainting()
	{
	}

	public Packet25EntityPainting(EntityPainting paramEntityPainting)
	{
		this.a = paramEntityPainting.id;
		this.b = paramEntityPainting.x;
		this.c = paramEntityPainting.y;
		this.d = paramEntityPainting.z;
		this.e = paramEntityPainting.direction;
		this.f = paramEntityPainting.art.A;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.f = a(paramDataInputStream, EnumArt.z);
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		a(this.f, paramDataOutputStream);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.writeInt(this.e);
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
 * Qualified Name:		 net.minecraft.server.Packet25EntityPainting
 * JD-Core Version:		0.6.0
 */