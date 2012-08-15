package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet132TileEntityData extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public NBTTagCompound e;

	public Packet132TileEntityData()
	{
		this.lowPriority = true;
	}

	public Packet132TileEntityData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, NBTTagCompound paramNBTTagCompound) {
		this.lowPriority = true;
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
		this.d = paramInt4;
		this.e = paramNBTTagCompound;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readShort();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.readByte();
		this.e = d(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeShort(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.writeByte((byte)this.d);
		a(this.e, paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 25;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet132TileEntityData
 * JD-Core Version:		0.6.0
 */