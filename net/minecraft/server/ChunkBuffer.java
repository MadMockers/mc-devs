package net.minecraft.server;

import java.io.ByteArrayOutputStream;

class ChunkBuffer extends ByteArrayOutputStream
{
	private int b;
	private int c;

	public ChunkBuffer(RegionFile paramRegionFile, int paramInt1, int paramInt2)
	{
		super(8096);
		this.b = paramInt1;
		this.c = paramInt2;
	}

	public void close()
	{
		this.a.a(this.b, this.c, this.buf, this.count);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ChunkBuffer
 * JD-Core Version:		0.6.0
 */