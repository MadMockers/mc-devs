package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet204LocaleAndViewDistance extends Packet
{
	private String a;
	private int b;
	private int c;
	private boolean d;
	private int e;

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = a(paramDataInputStream, 7);
		this.b = paramDataInputStream.readByte();

		int i = paramDataInputStream.readByte();
		this.c = (i & 0x7);
		this.d = ((i & 0x8) == 8);

		this.e = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.a, paramDataOutputStream);
		paramDataOutputStream.writeByte(this.b);
		paramDataOutputStream.writeByte(this.c | (this.d ? 1 : 0) << 3);
		paramDataOutputStream.writeByte(this.e);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 0;
	}

	public String d() {
		return this.a;
	}

	public int f() {
		return this.b;
	}

	public int g() {
		return this.c;
	}

	public boolean h() {
		return this.d;
	}

	public int i() {
		return this.e;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		return true;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet204LocaleAndViewDistance
 * JD-Core Version:		0.6.0
 */