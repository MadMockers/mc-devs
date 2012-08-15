package net.minecraft.server;

public class ChunkCoordIntPair
{
	public final int x;
	public final int z;

	public ChunkCoordIntPair(int paramInt1, int paramInt2)
	{
		this.x = paramInt1;
		this.z = paramInt2;
	}

	public static long a(int paramInt1, int paramInt2) {
		return paramInt1 & 0xFFFFFFFF | (paramInt2 & 0xFFFFFFFF) << 32;
	}

	public int hashCode() {
		long l = a(this.x, this.z);
		int i = (int)l;
		int j = (int)(l >> 32);
		return i ^ j;
	}

	public boolean equals(Object paramObject) {
		ChunkCoordIntPair localChunkCoordIntPair = (ChunkCoordIntPair)paramObject;
		return (localChunkCoordIntPair.x == this.x) && (localChunkCoordIntPair.z == this.z);
	}

	public int a()
	{
		return (this.x << 4) + 8;
	}

	public int b() {
		return (this.z << 4) + 8;
	}

	public ChunkPosition a(int paramInt) {
		return new ChunkPosition(a(), paramInt, b());
	}

	public String toString() {
		return "[" + this.x + ", " + this.z + "]";
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkCoordIntPair
 * JD-Core Version:		0.6.0
 */