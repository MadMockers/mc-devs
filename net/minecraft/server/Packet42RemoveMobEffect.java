package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet42RemoveMobEffect extends Packet
{
	public int a;
	public byte b;

	public Packet42RemoveMobEffect()
	{
	}

	public Packet42RemoveMobEffect(int paramInt, MobEffect paramMobEffect)
	{
		this.a = paramInt;
		this.b = (byte)(paramMobEffect.getEffectId() & 0xFF);
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readByte();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.b);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 5;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet42RemoveMobEffect
 * JD-Core Version:		0.6.0
 */