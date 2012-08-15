package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;

public class Packet104WindowItems extends Packet
{
	public int a;
	public ItemStack[] b;

	public Packet104WindowItems()
	{
	}

	public Packet104WindowItems(int paramInt, List paramList)
	{
		this.a = paramInt;
		this.b = new ItemStack[paramList.size()];
		for (int i = 0; i < this.b.length; i++) {
			ItemStack localItemStack = (ItemStack)paramList.get(i);
			this.b[i] = (localItemStack == null ? null : localItemStack.cloneItemStack());
		}
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
		int i = paramDataInputStream.readShort();
		this.b = new ItemStack[i];
		for (int j = 0; j < i; j++)
			this.b[j] = c(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
		paramDataOutputStream.writeShort(this.b.length);
		for (ItemStack localItemStack : this.b)
			a(localItemStack, paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 3 + this.b.length * 5;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet104WindowItems
 * JD-Core Version:		0.6.0
 */