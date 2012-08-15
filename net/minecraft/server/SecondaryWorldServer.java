/*	 */ package net.minecraft.server;
/*	 */ 
/*	 */ import org.bukkit.World.Environment;
/*	 */ import org.bukkit.generator.ChunkGenerator;
/*	 */ 
/*	 */ public class SecondaryWorldServer extends WorldServer
/*	 */ {
/*	 */	 public SecondaryWorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, WorldServer worldserver, MethodProfiler methodprofiler, World.Environment env, ChunkGenerator gen)
/*	 */	 {
/* 6 */		 super(minecraftserver, idatamanager, s, i, worldsettings, methodprofiler, env, gen);
/*	 */ 
/* 8 */		 this.worldMaps = worldserver.worldMaps;
/*	 */	 }
/*	 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.SecondaryWorldServer
 * JD-Core Version:		0.6.0
 */