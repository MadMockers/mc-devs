package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet5EntityEquipment extends Packet
{
	public int a;
	public int b;
	private ItemStack c;

	public Packet5EntityEquipment()
	{
	}

	public Packet5EntityEquipment(int paramInt1, int paramInt2, ItemStack paramItemStack)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = (paramItemStack == null ? null : paramItemStack.cloneItemStack());
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readShort();
		this.c = c(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeShort(this.b);
		a(this.c, paramDataOutputStream);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 8;
	}

	public boolean e()
	{
		return true;
	}

	public boolean a(Packet paramPacket)
	{
		Packet5EntityEquipment localPacket5EntityEquipment = (Packet5EntityEquipment)paramPacket;
		return (localPacket5EntityEquipment.a == this.a) && (localPacket5EntityEquipment.b == this.b);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet5EntityEquipment
 * JD-Core Version:		0.6.0
 */