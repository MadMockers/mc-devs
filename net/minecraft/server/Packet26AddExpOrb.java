package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet26AddExpOrb extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public int e;

	public Packet26AddExpOrb()
	{
	}

	public Packet26AddExpOrb(EntityExperienceOrb paramEntityExperienceOrb)
	{
		this.a = paramEntityExperienceOrb.id;
		this.b = MathHelper.floor(paramEntityExperienceOrb.locX * 32.0D);
		this.c = MathHelper.floor(paramEntityExperienceOrb.locY * 32.0D);
		this.d = MathHelper.floor(paramEntityExperienceOrb.locZ * 32.0D);
		this.e = paramEntityExperienceOrb.d();
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = paramDataInputStream.readShort();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.writeShort(this.e);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 18;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet26AddExpOrb
 * JD-Core Version:		0.6.0
 */