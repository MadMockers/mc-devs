package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet202Abilities extends Packet
{
	private boolean a = false;
	private boolean b = false;
	private boolean c = false;
	private boolean d = false;
	private float e;
	private float f;

	public Packet202Abilities()
	{
	}

	public Packet202Abilities(PlayerAbilities paramPlayerAbilities)
	{
		a(paramPlayerAbilities.isInvulnerable);
		b(paramPlayerAbilities.isFlying);
		c(paramPlayerAbilities.canFly);
		d(paramPlayerAbilities.canInstantlyBuild);
		a(paramPlayerAbilities.a());
		b(paramPlayerAbilities.b());
	}

	public void a(DataInputStream paramDataInputStream)
	{
		int i = paramDataInputStream.readByte();

		a((i & 0x1) > 0);
		b((i & 0x2) > 0);
		c((i & 0x4) > 0);
		d((i & 0x8) > 0);
		a(paramDataInputStream.readByte() / 255.0F);
		b(paramDataInputStream.readByte() / 255.0F);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		int i = 0;

		if (d()) i = (byte)(i | 0x1);
		if (f()) i = (byte)(i | 0x2);
		if (g()) i = (byte)(i | 0x4);
		if (h()) i = (byte)(i | 0x8);

		paramDataOutputStream.writeByte(i);
		paramDataOutputStream.writeByte((int)(this.e * 255.0F));
		paramDataOutputStream.writeByte((int)(this.f * 255.0F));
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 2;
	}

	public boolean d()
	{
		return this.a;
	}

	public void a(boolean paramBoolean) {
		this.a = paramBoolean;
	}

	public boolean f() {
		return this.b;
	}

	public void b(boolean paramBoolean) {
		this.b = paramBoolean;
	}

	public boolean g() {
		return this.c;
	}

	public void c(boolean paramBoolean) {
		this.c = paramBoolean;
	}

	public boolean h() {
		return this.d;
	}

	public void d(boolean paramBoolean) {
		this.d = paramBoolean;
	}

	public void a(float paramFloat)
	{
		this.e = paramFloat;
	}

	public void b(float paramFloat)
	{
		this.f = paramFloat;
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

/* 
 * Qualified Name:		 net.minecraft.server.Packet202Abilities
 * JD-Core Version:		0.6.0
 */