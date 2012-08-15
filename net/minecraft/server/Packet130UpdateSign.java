package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet130UpdateSign extends Packet
{
	public int x;
	public int y;
	public int z;
	public String[] lines;

	public Packet130UpdateSign()
	{
		this.lowPriority = true;
	}

	public Packet130UpdateSign(int paramInt1, int paramInt2, int paramInt3, String[] paramArrayOfString) {
		this.lowPriority = true;
		this.x = paramInt1;
		this.y = paramInt2;
		this.z = paramInt3;
		this.lines = new String[] { paramArrayOfString[0], paramArrayOfString[1], paramArrayOfString[2], paramArrayOfString[3] };
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.x = paramDataInputStream.readInt();
		this.y = paramDataInputStream.readShort();
		this.z = paramDataInputStream.readInt();
		this.lines = new String[4];
		for (int i = 0; i < 4; i++)
			this.lines[i] = a(paramDataInputStream, 15);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.x);
		paramDataOutputStream.writeShort(this.y);
		paramDataOutputStream.writeInt(this.z);
		for (int i = 0; i < 4; i++)
			a(this.lines[i], paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		int i = 0;
		for (int j = 0; j < 4; j++)
			i += this.lines[j].length();
		return i;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet130UpdateSign
 * JD-Core Version:		0.6.0
 */