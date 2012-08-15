package net.minecraft.server;

import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;

public class SecondaryWorldServer extends WorldServer
{
	public SecondaryWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, WorldServer worldserver, MethodProfiler methodprofiler, World.Environment env, ChunkGenerator gen)
	{
		super(minecraftserver, idatamanager, s, i, worldsettings, methodprofiler, env, gen);

		this.worldMaps = worldserver.worldMaps;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.SecondaryWorldServer
 * JD-Core Version:		0.6.0
 */