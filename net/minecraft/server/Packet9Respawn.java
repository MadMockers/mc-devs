package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet9Respawn extends Packet
{
	public int a;
	public int b;
	public int c;
	public EnumGamemode d;
	public WorldType e;

	public Packet9Respawn()
	{
	}

	public Packet9Respawn(int paramInt1, byte paramByte, WorldType paramWorldType, int paramInt2, EnumGamemode paramEnumGamemode)
	{
		this.a = paramInt1;
		this.b = paramByte;
		this.c = paramInt2;
		this.d = paramEnumGamemode;
		this.e = paramWorldType;
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readByte();
		this.d = EnumGamemode.a(paramDataInputStream.readByte());
		this.c = paramDataInputStream.readShort();
		String str = a(paramDataInputStream, 16);
		this.e = WorldType.getType(str);
		if (this.e == null)
			this.e = WorldType.NORMAL;
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.b);
		paramDataOutputStream.writeByte(this.d.a());
		paramDataOutputStream.writeShort(this.c);
		a(this.e.name(), paramDataOutputStream);
	}

	public int a()
	{
		return 8 + (this.e == null ? 0 : this.e.name().length());
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet9Respawn
 * JD-Core Version:		0.6.0
 */