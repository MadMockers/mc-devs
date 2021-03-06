package net.minecraft.server;

import java.util.List;

public abstract interface IChunkProvider
{
	public abstract boolean isChunkLoaded(int paramInt1, int paramInt2);

	public abstract Chunk getOrCreateChunk(int paramInt1, int paramInt2);

	public abstract Chunk getChunkAt(int paramInt1, int paramInt2);

	public abstract void getChunkAt(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2);

	public abstract boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate);

	public abstract boolean unloadChunks();

	public abstract boolean canSave();

	public abstract String getName();

	public abstract List getMobsFor(EnumCreatureType paramEnumCreatureType, int paramInt1, int paramInt2, int paramInt3);

	public abstract ChunkPosition findNearestMapFeature(World paramWorld, String paramString, int paramInt1, int paramInt2, int paramInt3);

	public abstract int getLoadedChunks();
}

/* 
 * Qualified Name:		 net.minecraft.server.IChunkProvider
 * JD-Core Version:		0.6.0
 */