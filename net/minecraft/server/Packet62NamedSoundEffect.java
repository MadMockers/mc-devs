package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet62NamedSoundEffect extends Packet
{
	private String a;
	private int b;
	private int c = 2147483647;
	private int d;
	private float e;
	private int f;

	public Packet62NamedSoundEffect()
	{
	}

	public Packet62NamedSoundEffect(String paramString, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2)
	{
		this.a = paramString;
		this.b = (int)(paramDouble1 * 8.0D);
		this.c = (int)(paramDouble2 * 8.0D);
		this.d = (int)(paramDouble3 * 8.0D);
		this.e = paramFloat1;
		this.f = (int)(paramFloat2 * 63.0F);

		if (this.f < 0) this.f = 0;
		if (this.f > 255) this.f = 255;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = a(paramDataInputStream, 32);
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = paramDataInputStream.readFloat();
		this.f = paramDataInputStream.readUnsignedByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		a(this.a, paramDataOutputStream);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.writeFloat(this.e);
		paramDataOutputStream.writeByte(this.f);
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

/* 
 * Qualified Name:		 net.minecraft.server.Packet62NamedSoundEffect
 * JD-Core Version:		0.6.0
 */