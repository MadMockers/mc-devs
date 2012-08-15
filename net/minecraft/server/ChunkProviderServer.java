/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ import java.util.logging.Logger;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.util.LongHashset;
/*		 */ import org.bukkit.craftbukkit.util.LongHashtable;
/*		 */ import org.bukkit.event.world.ChunkLoadEvent;
/*		 */ import org.bukkit.event.world.ChunkPopulateEvent;
/*		 */ import org.bukkit.event.world.ChunkUnloadEvent;
/*		 */ import org.bukkit.generator.BlockPopulator;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class ChunkProviderServer
/*		 */	 implements IChunkProvider
/*		 */ {
/*	22 */	 public LongHashset unloadQueue = new LongHashset();
/*		 */	 public Chunk emptyChunk;
/*		 */	 public IChunkProvider chunkProvider;
/*		 */	 private IChunkLoader e;
/*	26 */	 public boolean forceChunkLoad = false;
/*	27 */	 public LongHashtable<Chunk> chunks = new LongHashtable();
/*	28 */	 public List chunkList = new ArrayList();
/*		 */	 public WorldServer world;
/*		 */ 
/*		 */	 public ChunkProviderServer(WorldServer worldserver, IChunkLoader ichunkloader, IChunkProvider ichunkprovider)
/*		 */	 {
/*	33 */		 this.emptyChunk = new EmptyChunk(worldserver, 0, 0);
/*	34 */		 this.world = worldserver;
/*	35 */		 this.e = ichunkloader;
/*	36 */		 this.chunkProvider = ichunkprovider;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isChunkLoaded(int i, int j) {
/*	40 */		 return (!this.unloadQueue.containsKey(i, j)) && (this.chunks.containsKey(i, j));
/*		 */	 }
/*		 */ 
/*		 */	 public void queueUnload(int i, int j) {
/*	44 */		 if (this.world.worldProvider.e()) {
/*	45 */			 ChunkCoordinates chunkcoordinates = this.world.getSpawn();
/*	46 */			 int k = i * 16 + 8 - chunkcoordinates.x;
/*	47 */			 int l = j * 16 + 8 - chunkcoordinates.z;
/*	48 */			 short short1 = 128;
/*		 */ 
/*	50 */			 if ((k < -short1) || (k > short1) || (l < -short1) || (l > short1) || (!this.world.keepSpawnInMemory))
/*	51 */				 this.unloadQueue.add(i, j);
/*		 */		 }
/*		 */		 else {
/*	54 */			 this.unloadQueue.add(i, j);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/*	59 */		 Iterator iterator = this.chunkList.iterator();
/*		 */ 
/*	61 */		 while (iterator.hasNext()) {
/*	62 */			 Chunk chunk = (Chunk)iterator.next();
/*		 */ 
/*	64 */			 queueUnload(chunk.x, chunk.z);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getChunkAt(int i, int j)
/*		 */	 {
/*	70 */		 this.unloadQueue.remove(i, j);
/*	71 */		 Chunk chunk = (Chunk)this.chunks.get(i, j);
/*	72 */		 boolean newChunk = false;
/*		 */ 
/*	75 */		 if (chunk == null) {
/*	76 */			 chunk = loadChunk(i, j);
/*	77 */			 if (chunk == null) {
/*	78 */				 if (this.chunkProvider == null)
/*	79 */					 chunk = this.emptyChunk;
/*		 */				 else {
/*	81 */					 chunk = this.chunkProvider.getOrCreateChunk(i, j);
/*		 */				 }
/*	83 */				 newChunk = true;
/*		 */			 }
/*		 */ 
/*	86 */			 this.chunks.put(i, j, chunk);
/*	87 */			 this.chunkList.add(chunk);
/*	88 */			 if (chunk != null) {
/*	89 */				 chunk.addEntities();
/*		 */			 }
/*		 */ 
/*	93 */			 Server server = this.world.getServer();
/*	94 */			 if (server != null)
/*		 */			 {
/* 100 */				 server.getPluginManager().callEvent(new ChunkLoadEvent(chunk.bukkitChunk, newChunk));
/*		 */			 }
/*		 */ 
/* 104 */			 chunk.a(this, this, i, j);
/*		 */		 }
/*		 */ 
/* 107 */		 return chunk;
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk getOrCreateChunk(int i, int j)
/*		 */	 {
/* 112 */		 Chunk chunk = (Chunk)this.chunks.get(i, j);
/*		 */ 
/* 114 */		 chunk = chunk == null ? getChunkAt(i, j) : (!this.world.isLoading) && (!this.forceChunkLoad) ? this.emptyChunk : chunk;
/* 115 */		 if (chunk == this.emptyChunk) return chunk;
/* 116 */		 if ((i != chunk.x) || (j != chunk.z)) {
/* 117 */			 MinecraftServer.log.severe("Chunk (" + chunk.x + ", " + chunk.z + ") stored at	(" + i + ", " + j + ") in world '" + this.world.getWorld().getName() + "'");
/* 118 */			 MinecraftServer.log.severe(chunk.getClass().getName());
/* 119 */			 Throwable ex = new Throwable();
/* 120 */			 ex.fillInStackTrace();
/* 121 */			 ex.printStackTrace();
/*		 */		 }
/* 123 */		 return chunk;
/*		 */	 }
/*		 */ 
/*		 */	 public Chunk loadChunk(int i, int j)
/*		 */	 {
/* 128 */		 if (this.e == null)
/* 129 */			 return null;
/*		 */		 try
/*		 */		 {
/* 132 */			 Chunk chunk = this.e.a(this.world, i, j);
/*		 */ 
/* 134 */			 if (chunk != null) {
/* 135 */				 chunk.n = this.world.getTime();
/*		 */			 }
/*		 */ 
/* 138 */			 return chunk;
/*		 */		 } catch (Exception exception) {
/* 140 */			 exception.printStackTrace();
/* 141 */		 }return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void saveChunkNOP(Chunk chunk)
/*		 */	 {
/* 147 */		 if (this.e != null)
/*		 */			 try {
/* 149 */				 this.e.b(this.world, chunk);
/*		 */			 } catch (Exception exception) {
/* 151 */				 exception.printStackTrace();
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public void saveChunk(Chunk chunk)
/*		 */	 {
/* 157 */		 if (this.e != null)
/*		 */			 try {
/* 159 */				 chunk.n = this.world.getTime();
/* 160 */				 this.e.a(this.world, chunk);
/*		 */			 } catch (Exception ioexception) {
/* 162 */				 ioexception.printStackTrace();
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 public void getChunkAt(IChunkProvider ichunkprovider, int i, int j)
/*		 */	 {
/* 173 */		 Chunk chunk = getOrCreateChunk(i, j);
/*		 */ 
/* 175 */		 if (!chunk.done) {
/* 176 */			 chunk.done = true;
/* 177 */			 if (this.chunkProvider != null) {
/* 178 */				 this.chunkProvider.getChunkAt(ichunkprovider, i, j);
/*		 */ 
/* 181 */				 BlockSand.instaFall = true;
/* 182 */				 Random random = new Random();
/* 183 */				 random.setSeed(this.world.getSeed());
/* 184 */				 long xRand = random.nextLong() / 2L * 2L + 1L;
/* 185 */				 long zRand = random.nextLong() / 2L * 2L + 1L;
/* 186 */				 random.setSeed(i * xRand + j * zRand ^ this.world.getSeed());
/*		 */ 
/* 188 */				 org.bukkit.World world = this.world.getWorld();
/* 189 */				 if (world != null) {
/* 190 */					 for (BlockPopulator populator : world.getPopulators()) {
/* 191 */						 populator.populate(world, random, chunk.bukkitChunk);
/*		 */					 }
/*		 */				 }
/* 194 */				 BlockSand.instaFall = false;
/* 195 */				 this.world.getServer().getPluginManager().callEvent(new ChunkPopulateEvent(chunk.bukkitChunk));
/*		 */ 
/* 198 */				 chunk.e();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
/* 204 */		 int i = 0;
/* 205 */		 Iterator iterator = this.chunkList.iterator();
/*		 */ 
/* 207 */		 while (iterator.hasNext()) {
/* 208 */			 Chunk chunk = (Chunk)iterator.next();
/*		 */ 
/* 210 */			 if (flag) {
/* 211 */				 saveChunkNOP(chunk);
/*		 */			 }
/*		 */ 
/* 214 */			 if (chunk.a(flag)) {
/* 215 */				 saveChunk(chunk);
/* 216 */				 chunk.l = false;
/* 217 */				 i++;
/* 218 */				 if ((i == 24) && (!flag)) {
/* 219 */					 return false;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 224 */		 if (flag) {
/* 225 */			 if (this.e == null) {
/* 226 */				 return true;
/*		 */			 }
/*		 */ 
/* 229 */			 this.e.b();
/*		 */		 }
/*		 */ 
/* 232 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean unloadChunks() {
/* 236 */		 if (!this.world.savingDisabled)
/*		 */		 {
/* 238 */			 Server server = this.world.getServer();
/* 239 */			 for (int i = 0; (i < 50) && (!this.unloadQueue.isEmpty()); i++) {
/* 240 */				 long chunkcoordinates = this.unloadQueue.popFirst();
/* 241 */				 Chunk chunk = (Chunk)this.chunks.get(chunkcoordinates);
/* 242 */				 if (chunk == null)
/*		 */					 continue;
/* 244 */				 ChunkUnloadEvent event = new ChunkUnloadEvent(chunk.bukkitChunk);
/* 245 */				 server.getPluginManager().callEvent(event);
/* 246 */				 if (!event.isCancelled()) {
/* 247 */					 chunk.removeEntities();
/* 248 */					 saveChunk(chunk);
/* 249 */					 saveChunkNOP(chunk);
/*		 */ 
/* 251 */					 this.chunks.remove(chunkcoordinates);
/* 252 */					 this.chunkList.remove(chunk);
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 257 */			 if (this.e != null) {
/* 258 */				 this.e.a();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 262 */		 return this.chunkProvider.unloadChunks();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canSave() {
/* 266 */		 return !this.world.savingDisabled;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 270 */		 return "ServerChunkCache: " + this.chunks.values().size() + " Drop: " + this.unloadQueue.size();
/*		 */	 }
/*		 */ 
/*		 */	 public List getMobsFor(EnumCreatureType enumcreaturetype, int i, int j, int k) {
/* 274 */		 return this.chunkProvider.getMobsFor(enumcreaturetype, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkPosition findNearestMapFeature(World world, String s, int i, int j, int k) {
/* 278 */		 return this.chunkProvider.findNearestMapFeature(world, s, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public int getLoadedChunks() {
/* 282 */		 return this.chunks.values().size();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ChunkProviderServer
 * JD-Core Version:		0.6.0
 */