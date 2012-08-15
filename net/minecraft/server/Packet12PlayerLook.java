package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet12PlayerLook extends Packet10Flying
{
	public Packet12PlayerLook()
	{
		this.hasLook = true;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.yaw = paramDataInputStream.readFloat();
		this.pitch = paramDataInputStream.readFloat();
		super.a(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeFloat(this.yaw);
		paramDataOutputStream.writeFloat(this.pitch);
		super.a(paramDataOutputStream);
	}

	public int a()
	{
		return 9;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet12PlayerLook
 * JD-Core Version:		0.6.0
 */