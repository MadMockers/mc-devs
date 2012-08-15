package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet102WindowClick extends Packet
{
	public int a;
	public int slot;
	public int button;
	public short d;
	public ItemStack item;
	public boolean shift;

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readByte();
		this.slot = paramDataInputStream.readShort();
		this.button = paramDataInputStream.readByte();
		this.d = paramDataInputStream.readShort();
		this.shift = paramDataInputStream.readBoolean();

		this.item = c(paramDataInputStream);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte(this.a);
		paramDataOutputStream.writeShort(this.slot);
		paramDataOutputStream.writeByte(this.button);
		paramDataOutputStream.writeShort(this.d);
		paramDataOutputStream.writeBoolean(this.shift);

		a(this.item, paramDataOutputStream);
	}

	public int a()
	{
		return 11;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet102WindowClick
 * JD-Core Version:		0.6.0
 */