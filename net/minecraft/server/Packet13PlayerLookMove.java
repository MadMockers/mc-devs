package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet13PlayerLookMove extends Packet10Flying
{
	public Packet13PlayerLookMove()
	{
		this.hasLook = true;
		this.hasPos = true;
	}

	public Packet13PlayerLookMove(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat1, float paramFloat2, boolean paramBoolean) {
		this.x = paramDouble1;
		this.y = paramDouble2;
		this.stance = paramDouble3;
		this.z = paramDouble4;
		this.yaw = paramFloat1;
		this.pitch = paramFloat2;
		this.g = paramBoolean;
		this.hasLook = true;
		this.hasPos = true;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.x = paramDataInputStream.readDouble();
		this.y = paramDataInputStream.readDouble();
		this.stance = paramDataInputStream.readDouble();
		this.z = paramDataInputStream.readDouble();
		this.yaw = paramDataInputStream.readFloat();
		this.pitch = paramDataInputStream.readFloat();
		super.a(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeDouble(this.x);
		paramDataOutputStream.writeDouble(this.y);
		paramDataOutputStream.writeDouble(this.stance);
		paramDataOutputStream.writeDouble(this.z);
		paramDataOutputStream.writeFloat(this.yaw);
		paramDataOutputStream.writeFloat(this.pitch);
		super.a(paramDataOutputStream);
	}

	public int a()
	{
		return 41;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet13PlayerLookMove
 * JD-Core Version:		0.6.0
 */