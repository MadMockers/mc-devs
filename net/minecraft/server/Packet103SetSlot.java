package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet103SetSlot extends Packet
{
	public int a;
	public int b;
	public ItemStack c;

	public Packet103SetSlot()
	{
	}

	public Packet103SetSlot(int paramInt1, int paramInt2, ItemStack paramItemStack)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = (paramItemStack == null ? paramItemStack : paramItemStack.cloneItemStack());
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
		this.b = paramDataInputStream.readShort();
		this.c = c(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
		paramDataOutputStream.writeShort(this.b);
		a(this.c, paramDataOutputStream);
	}

	public int a()
	{
		return 8;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet103SetSlot
 * JD-Core Version:		0.6.0
 */