package net.minecraft.server;

import java.io.File;

public class ServerNBTManager extends WorldNBTStorage
{
	public ServerNBTManager(File paramFile, String paramString, boolean paramBoolean)
	{
		super(paramFile, paramString, paramBoolean);
	}

	public IChunkLoader createChunkLoader(WorldProvider paramWorldProvider)
	{
		File localFile1 = getDirectory();
		File localFile2;
		if ((paramWorldProvider instanceof WorldProviderHell)) {
			localFile2 = new File(localFile1, "DIM-1");
			localFile2.mkdirs();
			return new ChunkRegionLoader(localFile2);
		}
		if ((paramWorldProvider instanceof WorldProviderTheEnd)) {
			localFile2 = new File(localFile1, "DIM1");
			localFile2.mkdirs();
			return new ChunkRegionLoader(localFile2);
		}

		return new ChunkRegionLoader(localFile1);
	}

	public void saveWorldData(WorldData paramWorldData, NBTTagCompound paramNBTTagCompound)
	{
		paramWorldData.e(19133);
		super.saveWorldData(paramWorldData, paramNBTTagCompound);
	}

	public void a()
	{
		try {
			FileIOThread.a.a();
		} catch (InterruptedException localInterruptedException) {
			localInterruptedException.printStackTrace();
		}

		RegionFileCache.a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ServerNBTManager
 * JD-Core Version:		0.6.0
 */