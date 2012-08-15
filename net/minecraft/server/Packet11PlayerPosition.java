package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet11PlayerPosition extends Packet10Flying
{
	public Packet11PlayerPosition()
	{
		this.hasPos = true;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.x = paramDataInputStream.readDouble();
		this.y = paramDataInputStream.readDouble();
		this.stance = paramDataInputStream.readDouble();
		this.z = paramDataInputStream.readDouble();
		super.a(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeDouble(this.x);
		paramDataOutputStream.writeDouble(this.y);
		paramDataOutputStream.writeDouble(this.stance);
		paramDataOutputStream.writeDouble(this.z);
		super.a(paramDataOutputStream);
	}

	public int a()
	{
		return 33;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet11PlayerPosition
 * JD-Core Version:		0.6.0
 */