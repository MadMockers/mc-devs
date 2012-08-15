package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet1Login extends Packet
{
	public int a = 0;
	public WorldType b;
	public boolean c;
	public EnumGamemode d;
	public int e;
	public byte f;
	public byte g;
	public byte h;

	public Packet1Login()
	{
	}

	public Packet1Login(int paramInt1, WorldType paramWorldType, EnumGamemode paramEnumGamemode, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		this.a = paramInt1;
		this.b = paramWorldType;
		this.e = paramInt2;
		this.f = (byte)paramInt3;
		this.d = paramEnumGamemode;
		this.g = (byte)paramInt4;
		this.h = (byte)paramInt5;
		this.c = paramBoolean;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		String str = a(paramDataInputStream, 16);
		this.b = WorldType.getType(str);

		if (this.b == null) {
			this.b = WorldType.NORMAL;
		}

		int i = paramDataInputStream.readByte();
		this.c = ((i & 0x8) == 8);
		i &= -9;
		this.d = EnumGamemode.a(i);

		this.e = paramDataInputStream.readByte();
		this.f = paramDataInputStream.readByte();
		this.g = paramDataInputStream.readByte();
		this.h = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		a(this.b == null ? "" : this.b.name(), paramDataOutputStream);
		int i = this.d.a();
		if (this.c) i |= 8;
		paramDataOutputStream.writeByte(i);
		paramDataOutputStream.writeByte(this.e);
		paramDataOutputStream.writeByte(this.f);
		paramDataOutputStream.writeByte(this.g);
		paramDataOutputStream.writeByte(this.h);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		int i = 0;
		if (this.b != null) {
			i = this.b.name().length();
		}
		return 6 + 2 * i + 4 + 4 + 1 + 1 + 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet1Login
 * JD-Core Version:		0.6.0
 */