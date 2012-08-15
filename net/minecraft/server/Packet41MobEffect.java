package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet41MobEffect extends Packet
{
	public int a;
	public byte b;
	public byte c;
	public short d;

	public Packet41MobEffect()
	{
	}

	public Packet41MobEffect(int paramInt, MobEffect paramMobEffect)
	{
		this.a = paramInt;
		this.b = (byte)(paramMobEffect.getEffectId() & 0xFF);
		this.c = (byte)(paramMobEffect.getAmplifier() & 0xFF);
		this.d = (short)paramMobEffect.getDuration();
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readByte();
		this.c = paramDataInputStream.readByte();
		this.d = paramDataInputStream.readShort();
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeByte(this.b);
		paramDataOutputStream.writeByte(this.c);
		paramDataOutputStream.writeShort(this.d);
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
		Packet41MobEffect localPacket41MobEffect = (Packet41MobEffect)paramPacket;
		return (localPacket41MobEffect.a == this.a) && (localPacket41MobEffect.b == this.b);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet41MobEffect
 * JD-Core Version:		0.6.0
 */