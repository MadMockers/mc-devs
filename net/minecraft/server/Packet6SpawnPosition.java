package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet6SpawnPosition extends Packet
{
	public int x;
	public int y;
	public int z;

	public Packet6SpawnPosition()
	{
	}

	public Packet6SpawnPosition(int paramInt1, int paramInt2, int paramInt3)
	{
		this.x = paramInt1;
		this.y = paramInt2;
		this.z = paramInt3;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.x = paramDataInputStream.readInt();
		this.y = paramDataInputStream.readInt();
		this.z = paramDataInputStream.readInt();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.x);
		paramDataOutputStream.writeInt(this.y);
		paramDataOutputStream.writeInt(this.z);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 12;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		return true;
	}

	public boolean a_()
	{
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet6SpawnPosition
 * JD-Core Version:		0.6.0
 */