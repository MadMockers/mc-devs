package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet55BlockBreakAnimation extends Packet
{
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;

	public Packet55BlockBreakAnimation()
	{
	}

	public Packet55BlockBreakAnimation(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
		this.d = paramInt4;
		this.e = paramInt5;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readInt();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readInt();
		this.e = paramDataInputStream.read();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeInt(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeInt(this.d);
		paramDataOutputStream.write(this.e);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 13;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		Packet55BlockBreakAnimation localPacket55BlockBreakAnimation = (Packet55BlockBreakAnimation)paramPacket;
		return localPacket55BlockBreakAnimation.a == this.a;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet55BlockBreakAnimation
 * JD-Core Version:		0.6.0
 */