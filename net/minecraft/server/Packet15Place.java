package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet15Place extends Packet
{
	private int a;
	private int b;
	private int c;
	private int d;
	private ItemStack e;
	private float f;
	private float g;
	private float h;

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.read();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.read();
		this.e = c(paramDataInputStream);
		this.f = (paramDataInputStream.read() / 16.0F);
		this.g = (paramDataInputStream.read() / 16.0F);
		this.h = (paramDataInputStream.read() / 16.0F);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.write(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.write(this.d);
		a(this.e, paramDataOutputStream);
		paramDataOutputStream.write((int)(this.f * 16.0F));
		paramDataOutputStream.write((int)(this.g * 16.0F));
		paramDataOutputStream.write((int)(this.h * 16.0F));
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 19;
	}

	public int d() {
		return this.a;
	}

	public int f() {
		return this.b;
	}

	public int g() {
		return this.c;
	}

	public int getFace() {
		return this.d;
	}

	public ItemStack getItemStack() {
		return this.e;
	}

	public float j() {
		return this.f;
	}

	public float l() {
		return this.g;
	}

	public float m() {
		return this.h;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet15Place
 * JD-Core Version:		0.6.0
 */