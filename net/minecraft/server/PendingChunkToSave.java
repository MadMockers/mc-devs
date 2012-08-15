package net.minecraft.server;

class PendingChunkToSave
{
	public final ChunkCoordIntPair a;
	public final NBTTagCompound b;

	public PendingChunkToSave(ChunkCoordIntPair paramChunkCoordIntPair, NBTTagCompound paramNBTTagCompound)
	{
		this.a = paramChunkCoordIntPair;
		this.b = paramNBTTagCompound;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PendingChunkToSave
 * JD-Core Version:		0.6.0
 */