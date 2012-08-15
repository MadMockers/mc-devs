package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet107SetCreativeSlot extends Packet
{
	public int slot;
	public ItemStack b;

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.slot = paramDataInputStream.readShort();
		this.b = c(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeShort(this.slot);
		a(this.b, paramDataOutputStream);
	}

	public int a()
	{
		return 8;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet107SetCreativeSlot
 * JD-Core Version:		0.6.0
 */