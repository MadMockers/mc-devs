package net.minecraft.server;

import java.io.File;
import java.util.UUID;

public abstract interface IDataManager
{
	public abstract WorldData getWorldData();

	public abstract void checkSession()
		throws ExceptionWorldConflict;

	public abstract IChunkLoader createChunkLoader(WorldProvider paramWorldProvider);

	public abstract void saveWorldData(WorldData paramWorldData, NBTTagCompound paramNBTTagCompound);

	public abstract void saveWorldData(WorldData paramWorldData);

	public abstract PlayerFileData getPlayerFileData();

	public abstract void a();

	public abstract File getDataFile(String paramString);

	public abstract String g();

	public abstract UUID getUUID();
}

/* 
 * Qualified Name:		 net.minecraft.server.IDataManager
 * JD-Core Version:		0.6.0
 */