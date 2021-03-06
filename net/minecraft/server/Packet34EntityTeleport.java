package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet34EntityTeleport extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public byte e;
	public byte f;

	public Packet34EntityTeleport()
	{
	}

	public Packet34EntityTeleport(Entity paramEntity)
	{
		this.a = paramEntity.id;
		this.b = MathHelper.floor(paramEntity.locX * 32.0D);
		this.c = MathHelper.floor(paramEntity.locY * 32.0D);
		this.d = MathHelper.floor(paramEntity.locZ * 32.0D);
		this.e = (byte)(int)(paramEntity.yaw * 256.0F / 360.0F);
		this.f = (byte)(int)(paramEntity.pitch * 256.0F / 360.0F);
	}

	public Packet34EntityTeleport(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte paramByte1, byte paramByte2) {
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
		this.d = paramInt4;
		this.e = paramByte1;
		this.f = paramByte2;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = (byte)paramDataInputStream.read();
		this.f = (byte)paramDataInputStream.read();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.write(this.e);
		paramDataOutputStream.write(this.f);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 34;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		Packet34EntityTeleport localPacket34EntityTeleport = (Packet34EntityTeleport)paramPacket;
		return localPacket34EntityTeleport.a == this.a;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet34EntityTeleport
 * JD-Core Version:		0.6.0
 */