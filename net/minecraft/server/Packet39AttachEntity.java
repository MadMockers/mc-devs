package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet39AttachEntity extends Packet
{
	public int a;
	public int b;

	public Packet39AttachEntity()
	{
	}

	public Packet39AttachEntity(Entity paramEntity1, Entity paramEntity2)
	{
		this.a = paramEntity1.id;
		this.b = (paramEntity2 != null ? paramEntity2.id : -1);
	}

	public int a()
	{
		return 8;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.b);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		Packet39AttachEntity localPacket39AttachEntity = (Packet39AttachEntity)paramPacket;
		return localPacket39AttachEntity.a == this.a;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet39AttachEntity
 * JD-Core Version:		0.6.0
 */