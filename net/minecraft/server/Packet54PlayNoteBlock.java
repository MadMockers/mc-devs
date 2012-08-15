package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet54PlayNoteBlock extends Packet
{
	public int a;
	public int b;
	public int c;
	public int d;
	public int e;
	public int f;

	public Packet54PlayNoteBlock()
	{
	}

	public Packet54PlayNoteBlock(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
	{
		this.a = paramInt1;
		this.b = paramInt2;
		this.c = paramInt3;
		this.d = paramInt5;
		this.e = paramInt6;
		this.f = paramInt4;
	}

	public void a(DataInputStream paramDataInputStream)
	{
		this.a = paramDataInputStream.readInt();
		this.b = paramDataInputStream.readShort();
		this.c = paramDataInputStream.readInt();
		this.d = paramDataInputStream.read();
		this.e = paramDataInputStream.read();
		this.f = (paramDataInputStream.readShort() & 0xFFF);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeInt(this.a);
		paramDataOutputStream.writeShort(this.b);
		paramDataOutputStream.writeInt(this.c);
		paramDataOutputStream.write(this.d);
		paramDataOutputStream.write(this.e);
		paramDataOutputStream.writeShort(this.f & 0xFFF);
	}

	public void handle(NetHandler paramNetHandler)
	{
		paramNetHandler.a(this);
	}

	public int a()
	{
		return 14;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Packet54PlayNoteBlock
 * JD-Core Version:		0.6.0
 */