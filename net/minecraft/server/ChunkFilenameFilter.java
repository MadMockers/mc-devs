package net.minecraft.server;

import java.io.File;
import java.io.FilenameFilter;

class ChunkFilenameFilter
	implements FilenameFilter
{
	ChunkFilenameFilter(WorldLoaderServer paramWorldLoaderServer)
	{
	}

	public boolean accept(File paramFile, String paramString)
	{
		return paramString.endsWith(".mcr");
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ChunkFilenameFilter
 * JD-Core Version:		0.6.0
 */