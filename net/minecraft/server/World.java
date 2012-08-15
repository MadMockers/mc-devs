/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.util.ArrayList;
/*			*/ import java.util.Collection;
/*			*/ import java.util.Iterator;
/*			*/ import java.util.List;
/*			*/ import java.util.Random;
/*			*/ import org.bukkit.Bukkit;
/*			*/ import org.bukkit.World.Environment;
/*			*/ import org.bukkit.craftbukkit.CraftServer;
/*			*/ import org.bukkit.craftbukkit.CraftWorld;
/*			*/ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*			*/ import org.bukkit.craftbukkit.util.LongHash;
/*			*/ import org.bukkit.craftbukkit.util.LongHashset;
/*			*/ import org.bukkit.entity.Projectile;
/*			*/ import org.bukkit.event.Cancellable;
/*			*/ import org.bukkit.event.block.BlockCanBuildEvent;
/*			*/ import org.bukkit.event.block.BlockPhysicsEvent;
/*			*/ import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
/*			*/ import org.bukkit.event.weather.ThunderChangeEvent;
/*			*/ import org.bukkit.event.weather.WeatherChangeEvent;
/*			*/ import org.bukkit.generator.ChunkGenerator;
/*			*/ import org.bukkit.plugin.PluginManager;
/*			*/ 
/*			*/ public abstract class World
/*			*/	 implements IBlockAccess
/*			*/ {
/*	 26 */	 public boolean e = false;
/*	 27 */	 public List entityList = new ArrayList();
/*	 28 */	 protected List g = new ArrayList();
/*	 29 */	 public List tileEntityList = new ArrayList();
/*	 30 */	 private List a = new ArrayList();
/*	 31 */	 private List b = new ArrayList();
/*	 32 */	 public List players = new ArrayList();
/*	 33 */	 public List j = new ArrayList();
/*	 34 */	 private long c = 16777215L;
/*	 35 */	 public int k = 0;
/*	 36 */	 protected int l = new Random().nextInt();
/*	 37 */	 protected final int m = 1013904223;
/*			*/	 protected float n;
/*			*/	 protected float o;
/*			*/	 protected float p;
/*			*/	 protected float q;
/*	 42 */	 protected int r = 0;
/*	 43 */	 public int s = 0;
/*	 44 */	 public boolean suppressPhysics = false;
/*			*/	 public int difficulty;
/*	 46 */	 public Random random = new Random();
/*			*/	 public WorldProvider worldProvider;
/*	 48 */	 protected List x = new ArrayList();
/*			*/	 public IChunkProvider chunkProvider;
/*			*/	 protected final IDataManager dataManager;
/*			*/	 public WorldData worldData;
/*			*/	 public boolean isLoading;
/*			*/	 public WorldMapCollection worldMaps;
/*	 54 */	 public final VillageCollection villages = new VillageCollection(this);
/*	 55 */	 protected final VillageSiege siegeManager = new VillageSiege(this);
/*			*/	 public final MethodProfiler methodProfiler;
/*	 57 */	 private ArrayList d = new ArrayList();
/*			*/	 private boolean L;
/*	 60 */	 public boolean allowMonsters = true;
/*	 61 */	 public boolean allowAnimals = true;
/*	 62 */	 protected LongHashset chunkTickList = new LongHashset();
/*			*/	 public long ticksPerAnimalSpawns;
/*			*/	 public long ticksPerMonsterSpawns;
/*			*/	 private int M;
/*			*/	 int[] J;
/*			*/	 private List N;
/*			*/	 public boolean isStatic;
/*			*/	 private final CraftWorld world;
/*			*/	 public boolean pvpMode;
/*	 90 */	 public boolean keepSpawnInMemory = true;
/*			*/	 public ChunkGenerator generator;
/*			*/	 Chunk lastChunkAccessed;
/*	 93 */	 int lastXAccessed = -2147483648;
/*	 94 */	 int lastZAccessed = -2147483648;
/*	 95 */	 final Object chunkLock = new Object();
/*			*/ 
/*			*/	 public BiomeBase getBiome(int i, int j)
/*			*/	 {
/*	 72 */		 if (isLoaded(i, 0, j)) {
/*	 73 */			 Chunk chunk = getChunkAtWorldCoords(i, j);
/*			*/ 
/*	 75 */			 if (chunk != null) {
/*	 76 */				 return chunk.a(i & 0xF, j & 0xF, this.worldProvider.c);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	 80 */		 return this.worldProvider.c.getBiome(i, j);
/*			*/	 }
/*			*/ 
/*			*/	 public WorldChunkManager getWorldChunkManager() {
/*	 84 */		 return this.worldProvider.c;
/*			*/	 }
/*			*/ 
/*			*/	 public CraftWorld getWorld()
/*			*/	 {
/*	 98 */		 return this.world;
/*			*/	 }
/*			*/ 
/*			*/	 public CraftServer getServer() {
/*	102 */		 return (CraftServer)Bukkit.getServer();
/*			*/	 }
/*			*/ 
/*			*/	 public World(IDataManager idatamanager, String s, WorldSettings worldsettings, WorldProvider worldprovider, MethodProfiler methodprofiler, ChunkGenerator gen, World.Environment env)
/*			*/	 {
/*	107 */		 this.generator = gen;
/*	108 */		 this.world = new CraftWorld((WorldServer)this, gen, env);
/*	109 */		 this.ticksPerAnimalSpawns = getServer().getTicksPerAnimalSpawns();
/*	110 */		 this.ticksPerMonsterSpawns = getServer().getTicksPerMonsterSpawns();
/*			*/ 
/*	113 */		 this.M = this.random.nextInt(12000);
/*	114 */		 this.J = new int[32768];
/*	115 */		 this.N = new ArrayList();
/*	116 */		 this.isStatic = false;
/*	117 */		 this.dataManager = idatamanager;
/*	118 */		 this.methodProfiler = methodprofiler;
/*	119 */		 this.worldMaps = new WorldMapCollection(idatamanager);
/*	120 */		 this.worldData = idatamanager.getWorldData();
/*	121 */		 if (worldprovider != null)
/*	122 */			 this.worldProvider = worldprovider;
/*	123 */		 else if ((this.worldData != null) && (this.worldData.i() != 0))
/*	124 */			 this.worldProvider = WorldProvider.byDimension(this.worldData.i());
/*			*/		 else {
/*	126 */			 this.worldProvider = WorldProvider.byDimension(0);
/*			*/		 }
/*			*/ 
/*	129 */		 if (this.worldData == null)
/*	130 */			 this.worldData = new WorldData(worldsettings, s);
/*			*/		 else {
/*	132 */			 this.worldData.setName(s);
/*			*/		 }
/*			*/ 
/*	135 */		 this.worldProvider.a(this);
/*	136 */		 this.chunkProvider = h();
/*	137 */		 if (!this.worldData.isInitialized()) {
/*	138 */			 a(worldsettings);
/*	139 */			 this.worldData.d(true);
/*			*/		 }
/*			*/ 
/*	142 */		 v();
/*	143 */		 a();
/*			*/ 
/*	145 */		 getServer().addWorld(this.world);
/*			*/	 }
/*			*/	 protected abstract IChunkProvider h();
/*			*/ 
/*			*/	 protected void a(WorldSettings worldsettings) {
/*	151 */		 this.worldData.d(true);
/*			*/	 }
/*			*/ 
/*			*/	 public int b(int i, int j)
/*			*/	 {
/*	157 */		 for (int k = 63; !isEmpty(i, k + 1, j); k++);
/*	161 */		 return getTypeId(i, k, j);
/*			*/	 }
/*			*/ 
/*			*/	 public int getTypeId(int i, int j, int k) {
/*	165 */		 return (i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000) ? getChunkAt(i >> 4, k >> 4).getTypeId(i & 0xF, j, k & 0xF) : j >= 256 ? 0 : j < 0 ? 0 : 0;
/*			*/	 }
/*			*/ 
/*			*/	 public int b(int i, int j, int k) {
/*	169 */		 return (i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000) ? getChunkAt(i >> 4, k >> 4).b(i & 0xF, j, k & 0xF) : j >= 256 ? 0 : j < 0 ? 0 : 0;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isEmpty(int i, int j, int k) {
/*	173 */		 return getTypeId(i, j, k) == 0;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isTileEntity(int i, int j, int k) {
/*	177 */		 int l = getTypeId(i, j, k);
/*			*/ 
/*	179 */		 return (Block.byId[l] != null) && (Block.byId[l].s());
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isLoaded(int i, int j, int k) {
/*	183 */		 return (j >= 0) && (j < 256) ? isChunkLoaded(i >> 4, k >> 4) : false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean areChunksLoaded(int i, int j, int k, int l) {
/*	187 */		 return c(i - l, j - l, k - l, i + l, j + l, k + l);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean c(int i, int j, int k, int l, int i1, int j1) {
/*	191 */		 if ((i1 >= 0) && (j < 256)) {
/*	192 */			 i >>= 4;
/*	193 */			 k >>= 4;
/*	194 */			 l >>= 4;
/*	195 */			 j1 >>= 4;
/*			*/ 
/*	197 */			 for (int k1 = i; k1 <= l; k1++) {
/*	198 */				 for (int l1 = k; l1 <= j1; l1++) {
/*	199 */					 if (!isChunkLoaded(k1, l1)) {
/*	200 */						 return false;
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	205 */			 return true;
/*			*/		 }
/*	207 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean isChunkLoaded(int i, int j)
/*			*/	 {
/*	212 */		 return this.chunkProvider.isChunkLoaded(i, j);
/*			*/	 }
/*			*/ 
/*			*/	 public Chunk getChunkAtWorldCoords(int i, int j) {
/*	216 */		 return getChunkAt(i >> 4, j >> 4);
/*			*/	 }
/*			*/ 
/*			*/	 public Chunk getChunkAt(int i, int j)
/*			*/	 {
/*	221 */		 Chunk result = null;
/*	222 */		 synchronized (this.chunkLock) {
/*	223 */			 if ((this.lastChunkAccessed == null) || (this.lastXAccessed != i) || (this.lastZAccessed != j)) {
/*	224 */				 this.lastXAccessed = i;
/*	225 */				 this.lastZAccessed = j;
/*	226 */				 this.lastChunkAccessed = this.chunkProvider.getOrCreateChunk(i, j);
/*			*/			 }
/*	228 */			 result = this.lastChunkAccessed;
/*			*/		 }
/*	230 */		 return result;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean setRawTypeIdAndData(int i, int j, int k, int l, int i1)
/*			*/	 {
/*	235 */		 return setRawTypeIdAndData(i, j, k, l, i1, true);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean setRawTypeIdAndData(int i, int j, int k, int l, int i1, boolean flag) {
/*	239 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/*	240 */			 if (j < 0)
/*	241 */				 return false;
/*	242 */			 if (j >= 256) {
/*	243 */				 return false;
/*			*/			 }
/*	245 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*	246 */			 boolean flag1 = chunk.a(i & 0xF, j, k & 0xF, l, i1);
/*			*/ 
/*	249 */			 x(i, j, k);
/*			*/ 
/*	251 */			 if ((flag) && (flag1) && ((this.isStatic) || (chunk.seenByPlayer))) {
/*	252 */				 notify(i, j, k);
/*			*/			 }
/*			*/ 
/*	255 */			 return flag1;
/*			*/		 }
/*			*/ 
/*	258 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean setRawTypeId(int i, int j, int k, int l)
/*			*/	 {
/*	263 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/*	264 */			 if (j < 0)
/*	265 */				 return false;
/*	266 */			 if (j >= 256) {
/*	267 */				 return false;
/*			*/			 }
/*	269 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*	270 */			 boolean flag = chunk.a(i & 0xF, j, k & 0xF, l);
/*			*/ 
/*	273 */			 x(i, j, k);
/*			*/ 
/*	275 */			 if ((flag) && ((this.isStatic) || (chunk.seenByPlayer))) {
/*	276 */				 notify(i, j, k);
/*			*/			 }
/*			*/ 
/*	279 */			 return flag;
/*			*/		 }
/*			*/ 
/*	282 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public Material getMaterial(int i, int j, int k)
/*			*/	 {
/*	287 */		 int l = getTypeId(i, j, k);
/*			*/ 
/*	289 */		 return l == 0 ? Material.AIR : Block.byId[l].material;
/*			*/	 }
/*			*/ 
/*			*/	 public int getData(int i, int j, int k) {
/*	293 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/*	294 */			 if (j < 0)
/*	295 */				 return 0;
/*	296 */			 if (j >= 256) {
/*	297 */				 return 0;
/*			*/			 }
/*	299 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*			*/ 
/*	301 */			 i &= 15;
/*	302 */			 k &= 15;
/*	303 */			 return chunk.getData(i, j, k);
/*			*/		 }
/*			*/ 
/*	306 */		 return 0;
/*			*/	 }
/*			*/ 
/*			*/	 public void setData(int i, int j, int k, int l)
/*			*/	 {
/*	311 */		 if (setRawData(i, j, k, l))
/*	312 */			 update(i, j, k, getTypeId(i, j, k));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean setRawData(int i, int j, int k, int l)
/*			*/	 {
/*	317 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/*	318 */			 if (j < 0)
/*	319 */				 return false;
/*	320 */			 if (j >= 256) {
/*	321 */				 return false;
/*			*/			 }
/*	323 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*	324 */			 int i1 = i & 0xF;
/*	325 */			 int j1 = k & 0xF;
/*	326 */			 boolean flag = chunk.b(i1, j, j1, l);
/*			*/ 
/*	328 */			 if ((flag) && ((this.isStatic) || ((chunk.seenByPlayer) && (Block.r[(chunk.getTypeId(i1, j, j1) & 0xFFF)] != 0)))) {
/*	329 */				 notify(i, j, k);
/*			*/			 }
/*			*/ 
/*	332 */			 return flag;
/*			*/		 }
/*			*/ 
/*	335 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean setTypeId(int i, int j, int k, int l)
/*			*/	 {
/*	341 */		 int old = getTypeId(i, j, k);
/*	342 */		 if (setRawTypeId(i, j, k, l)) {
/*	343 */			 update(i, j, k, l == 0 ? old : l);
/*			*/ 
/*	345 */			 return true;
/*			*/		 }
/*	347 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean setTypeIdAndData(int i, int j, int k, int l, int i1)
/*			*/	 {
/*	352 */		 if (setRawTypeIdAndData(i, j, k, l, i1)) {
/*	353 */			 update(i, j, k, l);
/*	354 */			 return true;
/*			*/		 }
/*	356 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public void notify(int i, int j, int k)
/*			*/	 {
/*	361 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	363 */		 while (iterator.hasNext()) {
/*	364 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	366 */			 iworldaccess.a(i, j, k);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void update(int i, int j, int k, int l) {
/*	371 */		 applyPhysics(i, j, k, l);
/*			*/	 }
/*			*/ 
/*			*/	 public void g(int i, int j, int k, int l)
/*			*/	 {
/*	377 */		 if (k > l) {
/*	378 */			 int i1 = l;
/*	379 */			 l = k;
/*	380 */			 k = i1;
/*			*/		 }
/*			*/ 
/*	383 */		 if (!this.worldProvider.e) {
/*	384 */			 for (int i1 = k; i1 <= l; i1++) {
/*	385 */				 c(EnumSkyBlock.SKY, i, i1, j);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	389 */		 d(i, k, j, i, l, j);
/*			*/	 }
/*			*/ 
/*			*/	 public void i(int i, int j, int k) {
/*	393 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	395 */		 while (iterator.hasNext()) {
/*	396 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	398 */			 iworldaccess.a(i, j, k, i, j, k);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void d(int i, int j, int k, int l, int i1, int j1) {
/*	403 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	405 */		 while (iterator.hasNext()) {
/*	406 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	408 */			 iworldaccess.a(i, j, k, l, i1, j1);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void applyPhysics(int i, int j, int k, int l) {
/*	413 */		 m(i - 1, j, k, l);
/*	414 */		 m(i + 1, j, k, l);
/*	415 */		 m(i, j - 1, k, l);
/*	416 */		 m(i, j + 1, k, l);
/*	417 */		 m(i, j, k - 1, l);
/*	418 */		 m(i, j, k + 1, l);
/*			*/	 }
/*			*/ 
/*			*/	 private void m(int i, int j, int k, int l) {
/*	422 */		 if ((!this.suppressPhysics) && (!this.isStatic)) {
/*	423 */			 Block block = Block.byId[getTypeId(i, j, k)];
/*			*/ 
/*	425 */			 if (block != null)
/*			*/			 {
/*	427 */				 CraftWorld world = ((WorldServer)this).getWorld();
/*	428 */				 if (world != null) {
/*	429 */					 BlockPhysicsEvent event = new BlockPhysicsEvent(world.getBlockAt(i, j, k), l);
/*	430 */					 getServer().getPluginManager().callEvent(event);
/*			*/ 
/*	432 */					 if (event.isCancelled()) {
/*	433 */						 return;
/*			*/					 }
/*			*/ 
/*			*/				 }
/*			*/ 
/*	438 */				 block.doPhysics(this, i, j, k, l);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean j(int i, int j, int k) {
/*	444 */		 return getChunkAt(i >> 4, k >> 4).d(i & 0xF, j, k & 0xF);
/*			*/	 }
/*			*/ 
/*			*/	 public int k(int i, int j, int k) {
/*	448 */		 if (j < 0) {
/*	449 */			 return 0;
/*			*/		 }
/*	451 */		 if (j >= 256) {
/*	452 */			 j = 255;
/*			*/		 }
/*			*/ 
/*	455 */		 return getChunkAt(i >> 4, k >> 4).c(i & 0xF, j, k & 0xF, 0);
/*			*/	 }
/*			*/ 
/*			*/	 public int getLightLevel(int i, int j, int k)
/*			*/	 {
/*	460 */		 return a(i, j, k, true);
/*			*/	 }
/*			*/ 
/*			*/	 public int a(int i, int j, int k, boolean flag) {
/*	464 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/*	465 */			 if (flag) {
/*	466 */				 int l = getTypeId(i, j, k);
/*			*/ 
/*	468 */				 if ((l == Block.STEP.id) || (l == Block.WOOD_STEP.id) || (l == Block.SOIL.id) || (l == Block.COBBLESTONE_STAIRS.id) || (l == Block.WOOD_STAIRS.id)) {
/*	469 */					 int i1 = a(i, j + 1, k, false);
/*	470 */					 int j1 = a(i + 1, j, k, false);
/*	471 */					 int k1 = a(i - 1, j, k, false);
/*	472 */					 int l1 = a(i, j, k + 1, false);
/*	473 */					 int i2 = a(i, j, k - 1, false);
/*			*/ 
/*	475 */					 if (j1 > i1) {
/*	476 */						 i1 = j1;
/*			*/					 }
/*			*/ 
/*	479 */					 if (k1 > i1) {
/*	480 */						 i1 = k1;
/*			*/					 }
/*			*/ 
/*	483 */					 if (l1 > i1) {
/*	484 */						 i1 = l1;
/*			*/					 }
/*			*/ 
/*	487 */					 if (i2 > i1) {
/*	488 */						 i1 = i2;
/*			*/					 }
/*			*/ 
/*	491 */					 return i1;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	495 */			 if (j < 0) {
/*	496 */				 return 0;
/*			*/			 }
/*	498 */			 if (j >= 256) {
/*	499 */				 j = 255;
/*			*/			 }
/*			*/ 
/*	502 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*			*/ 
/*	504 */			 i &= 15;
/*	505 */			 k &= 15;
/*	506 */			 return chunk.c(i, j, k, this.k);
/*			*/		 }
/*			*/ 
/*	509 */		 return 15;
/*			*/	 }
/*			*/ 
/*			*/	 public int getHighestBlockYAt(int i, int j)
/*			*/	 {
/*	514 */		 if ((i >= -30000000) && (j >= -30000000) && (i < 30000000) && (j < 30000000)) {
/*	515 */			 if (!isChunkLoaded(i >> 4, j >> 4)) {
/*	516 */				 return 0;
/*			*/			 }
/*	518 */			 Chunk chunk = getChunkAt(i >> 4, j >> 4);
/*			*/ 
/*	520 */			 return chunk.b(i & 0xF, j & 0xF);
/*			*/		 }
/*			*/ 
/*	523 */		 return 0;
/*			*/	 }
/*			*/ 
/*			*/	 public int b(EnumSkyBlock enumskyblock, int i, int j, int k)
/*			*/	 {
/*	528 */		 if (j < 0) {
/*	529 */			 j = 0;
/*			*/		 }
/*			*/ 
/*	532 */		 if (j >= 256) {
/*	533 */			 j = 255;
/*			*/		 }
/*			*/ 
/*	536 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/*	537 */			 int l = i >> 4;
/*	538 */			 int i1 = k >> 4;
/*			*/ 
/*	540 */			 if (!isChunkLoaded(l, i1)) {
/*	541 */				 return enumskyblock.c;
/*			*/			 }
/*	543 */			 Chunk chunk = getChunkAt(l, i1);
/*			*/ 
/*	545 */			 return chunk.getBrightness(enumskyblock, i & 0xF, j, k & 0xF);
/*			*/		 }
/*			*/ 
/*	548 */		 return enumskyblock.c;
/*			*/	 }
/*			*/ 
/*			*/	 public void b(EnumSkyBlock enumskyblock, int i, int j, int k, int l)
/*			*/	 {
/*	553 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000) && 
/*	554 */			 (j >= 0) && 
/*	555 */			 (j < 256) && 
/*	556 */			 (isChunkLoaded(i >> 4, k >> 4))) {
/*	557 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*			*/ 
/*	559 */			 chunk.a(enumskyblock, i & 0xF, j, k & 0xF, l);
/*	560 */			 Iterator iterator = this.x.iterator();
/*			*/ 
/*	562 */			 while (iterator.hasNext()) {
/*	563 */				 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	565 */				 iworldaccess.b(i, j, k);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void n(int i, int j, int k)
/*			*/	 {
/*	574 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	576 */		 while (iterator.hasNext()) {
/*	577 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	579 */			 iworldaccess.b(i, j, k);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public float o(int i, int j, int k) {
/*	584 */		 return this.worldProvider.f[getLightLevel(i, j, k)];
/*			*/	 }
/*			*/ 
/*			*/	 public boolean r() {
/*	588 */		 return this.k < 4;
/*			*/	 }
/*			*/ 
/*			*/	 public MovingObjectPosition a(Vec3D vec3d, Vec3D vec3d1) {
/*	592 */		 return rayTrace(vec3d, vec3d1, false, false);
/*			*/	 }
/*			*/ 
/*			*/	 public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag) {
/*	596 */		 return rayTrace(vec3d, vec3d1, flag, false);
/*			*/	 }
/*			*/ 
/*			*/	 public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag, boolean flag1) {
/*	600 */		 if ((!Double.isNaN(vec3d.a)) && (!Double.isNaN(vec3d.b)) && (!Double.isNaN(vec3d.c)))
/*	601 */			 if ((!Double.isNaN(vec3d1.a)) && (!Double.isNaN(vec3d1.b)) && (!Double.isNaN(vec3d1.c))) {
/*	602 */				 int i = MathHelper.floor(vec3d1.a);
/*	603 */				 int j = MathHelper.floor(vec3d1.b);
/*	604 */				 int k = MathHelper.floor(vec3d1.c);
/*	605 */				 int l = MathHelper.floor(vec3d.a);
/*	606 */				 int i1 = MathHelper.floor(vec3d.b);
/*	607 */				 int j1 = MathHelper.floor(vec3d.c);
/*	608 */				 int k1 = getTypeId(l, i1, j1);
/*	609 */				 int l1 = getData(l, i1, j1);
/*	610 */				 Block block = Block.byId[k1];
/*			*/ 
/*	612 */				 if (((!flag1) || (block == null) || (block.e(this, l, i1, j1) != null)) && (k1 > 0) && (block.a(l1, flag))) {
/*	613 */					 MovingObjectPosition movingobjectposition = block.a(this, l, i1, j1, vec3d, vec3d1);
/*			*/ 
/*	615 */					 if (movingobjectposition != null) {
/*	616 */						 return movingobjectposition;
/*			*/					 }
/*			*/				 }
/*			*/ 
/*	620 */				 k1 = 200;
/*			*/ 
/*	622 */				 if (k1-- >= 0) {
/*	623 */					 if ((Double.isNaN(vec3d.a)) || (Double.isNaN(vec3d.b)) || (Double.isNaN(vec3d.c))) {
/*	624 */						 return null;
/*			*/					 }
/*			*/ 
/*	627 */					 if ((l == i) && (i1 == j) && (j1 == k)) {
/*	628 */						 return null;
/*			*/					 }
/*			*/ 
/*	631 */					 boolean flag2 = true;
/*	632 */					 boolean flag3 = true;
/*	633 */					 boolean flag4 = true;
/*	634 */					 double d0 = 999.0D;
/*	635 */					 double d1 = 999.0D;
/*	636 */					 double d2 = 999.0D;
/*			*/ 
/*	638 */					 if (i > l)
/*	639 */						 d0 = l + 1.0D;
/*	640 */					 else if (i < l)
/*	641 */						 d0 = l + 0.0D;
/*			*/					 else {
/*	643 */						 flag2 = false;
/*			*/					 }
/*			*/ 
/*	646 */					 if (j > i1)
/*	647 */						 d1 = i1 + 1.0D;
/*	648 */					 else if (j < i1)
/*	649 */						 d1 = i1 + 0.0D;
/*			*/					 else {
/*	651 */						 flag3 = false;
/*			*/					 }
/*			*/ 
/*	654 */					 if (k > j1)
/*	655 */						 d2 = j1 + 1.0D;
/*	656 */					 else if (k < j1)
/*	657 */						 d2 = j1 + 0.0D;
/*			*/					 else {
/*	659 */						 flag4 = false;
/*			*/					 }
/*			*/ 
/*	662 */					 double d3 = 999.0D;
/*	663 */					 double d4 = 999.0D;
/*	664 */					 double d5 = 999.0D;
/*	665 */					 double d6 = vec3d1.a - vec3d.a;
/*	666 */					 double d7 = vec3d1.b - vec3d.b;
/*	667 */					 double d8 = vec3d1.c - vec3d.c;
/*			*/ 
/*	669 */					 if (flag2) {
/*	670 */						 d3 = (d0 - vec3d.a) / d6;
/*			*/					 }
/*			*/ 
/*	673 */					 if (flag3) {
/*	674 */						 d4 = (d1 - vec3d.b) / d7;
/*			*/					 }
/*			*/ 
/*	677 */					 if (flag4) {
/*	678 */						 d5 = (d2 - vec3d.c) / d8;
/*			*/					 }
/*			*/ 
/*	681 */					 boolean flag5 = false;
/*			*/ 
/*	684 */					 if ((d3 < d4) && (d3 < d5))
/*			*/					 {
/*			*/						 byte b0;
/*			*/						 byte b0;
/*	685 */						 if (i > l)
/*	686 */							 b0 = 4;
/*			*/						 else {
/*	688 */							 b0 = 5;
/*			*/						 }
/*			*/ 
/*	691 */						 vec3d.a = d0;
/*	692 */						 vec3d.b += d7 * d3;
/*	693 */						 vec3d.c += d8 * d3;
/*	694 */					 } else if (d4 < d5)
/*			*/					 {
/*			*/						 byte b0;
/*			*/						 byte b0;
/*	695 */						 if (j > i1)
/*	696 */							 b0 = 0;
/*			*/						 else {
/*	698 */							 b0 = 1;
/*			*/						 }
/*			*/ 
/*	701 */						 vec3d.a += d6 * d4;
/*	702 */						 vec3d.b = d1;
/*	703 */						 vec3d.c += d8 * d4;
/*			*/					 }
/*			*/					 else
/*			*/					 {
/*			*/						 byte b0;
/*			*/						 byte b0;
/*	705 */						 if (k > j1)
/*	706 */							 b0 = 2;
/*			*/						 else {
/*	708 */							 b0 = 3;
/*			*/						 }
/*			*/ 
/*	711 */						 vec3d.a += d6 * d5;
/*	712 */						 vec3d.b += d7 * d5;
/*	713 */						 vec3d.c = d2;
/*			*/					 }
/*			*/ 
/*	716 */					 vec3d2 = Vec3D.a().create(vec3d.a, vec3d.b, vec3d.c);
/*			*/				 }
/*			*/			 }
/*			*/	 }
/*			*/ 
/*			*/	 public void makeSound(Entity entity, String s, float f, float f1)
/*			*/	 {
/*	759 */		 if ((entity != null) && (s != null)) {
/*	760 */			 Iterator iterator = this.x.iterator();
/*			*/ 
/*	762 */			 while (iterator.hasNext()) {
/*	763 */				 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	765 */				 iworldaccess.a(s, entity.locX, entity.locY - entity.height, entity.locZ, f, f1);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void makeSound(double d0, double d1, double d2, String s, float f, float f1) {
/*	771 */		 if (s != null) {
/*	772 */			 Iterator iterator = this.x.iterator();
/*			*/ 
/*	774 */			 while (iterator.hasNext()) {
/*	775 */				 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	777 */				 iworldaccess.a(s, d0, d1, d2, f, f1);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(String s, int i, int j, int k) {
/*	783 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	785 */		 while (iterator.hasNext()) {
/*	786 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	788 */			 iworldaccess.a(s, i, j, k);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {
/*	793 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	795 */		 while (iterator.hasNext()) {
/*	796 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	798 */			 iworldaccess.a(s, d0, d1, d2, d3, d4, d5);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean strikeLightning(Entity entity) {
/*	803 */		 this.j.add(entity);
/*	804 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean addEntity(Entity entity)
/*			*/	 {
/*	809 */		 return addEntity(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean addEntity(Entity entity, CreatureSpawnEvent.SpawnReason spawnReason) {
/*	813 */		 if (entity == null) return false;
/*			*/ 
/*	816 */		 int i = MathHelper.floor(entity.locX / 16.0D);
/*	817 */		 int j = MathHelper.floor(entity.locZ / 16.0D);
/*	818 */		 boolean flag = false;
/*			*/ 
/*	820 */		 if ((entity instanceof EntityHuman)) {
/*	821 */			 flag = true;
/*			*/		 }
/*			*/ 
/*	825 */		 Cancellable event = null;
/*	826 */		 if (((entity instanceof EntityLiving)) && (!(entity instanceof EntityPlayer))) {
/*	827 */			 boolean isAnimal = ((entity instanceof EntityAnimal)) || ((entity instanceof EntityWaterAnimal)) || ((entity instanceof EntityGolem));
/*	828 */			 boolean isMonster = ((entity instanceof EntityMonster)) || ((entity instanceof EntityGhast)) || ((entity instanceof EntitySlime));
/*			*/ 
/*	830 */			 if ((spawnReason != CreatureSpawnEvent.SpawnReason.CUSTOM) && (
/*	831 */				 ((isAnimal) && (!this.allowAnimals)) || ((isMonster) && (!this.allowMonsters)))) {
/*	832 */				 entity.dead = true;
/*	833 */				 return false;
/*			*/			 }
/*			*/ 
/*	837 */			 event = CraftEventFactory.callCreatureSpawnEvent((EntityLiving)entity, spawnReason);
/*	838 */		 } else if ((entity instanceof EntityItem)) {
/*	839 */			 event = CraftEventFactory.callItemSpawnEvent((EntityItem)entity);
/*	840 */		 } else if ((entity.getBukkitEntity() instanceof Projectile))
/*			*/		 {
/*	842 */			 event = CraftEventFactory.callProjectileLaunchEvent(entity);
/*			*/		 }
/*			*/ 
/*	845 */		 if ((event != null) && ((event.isCancelled()) || (entity.dead))) {
/*	846 */			 entity.dead = true;
/*	847 */			 return false;
/*			*/		 }
/*			*/ 
/*	851 */		 if ((!flag) && (!isChunkLoaded(i, j))) {
/*	852 */			 entity.dead = true;
/*	853 */			 return false;
/*			*/		 }
/*	855 */		 if ((entity instanceof EntityHuman)) {
/*	856 */			 EntityHuman entityhuman = (EntityHuman)entity;
/*			*/ 
/*	858 */			 this.players.add(entityhuman);
/*	859 */			 everyoneSleeping();
/*			*/		 }
/*			*/ 
/*	862 */		 getChunkAt(i, j).a(entity);
/*	863 */		 this.entityList.add(entity);
/*	864 */		 a(entity);
/*	865 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(Entity entity)
/*			*/	 {
/*	870 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	872 */		 while (iterator.hasNext()) {
/*	873 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	875 */			 iworldaccess.a(entity);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void b(Entity entity) {
/*	880 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/*	882 */		 while (iterator.hasNext()) {
/*	883 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/*	885 */			 iworldaccess.b(entity);
/*			*/		 }
/*			*/ 
/*	888 */		 entity.valid = false;
/*			*/	 }
/*			*/ 
/*			*/	 public void kill(Entity entity) {
/*	892 */		 if (entity.passenger != null) {
/*	893 */			 entity.passenger.mount((Entity)null);
/*			*/		 }
/*			*/ 
/*	896 */		 if (entity.vehicle != null) {
/*	897 */			 entity.mount((Entity)null);
/*			*/		 }
/*			*/ 
/*	900 */		 entity.die();
/*	901 */		 if ((entity instanceof EntityHuman)) {
/*	902 */			 this.players.remove(entity);
/*	903 */			 everyoneSleeping();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void removeEntity(Entity entity) {
/*	908 */		 entity.die();
/*	909 */		 if ((entity instanceof EntityHuman)) {
/*	910 */			 this.players.remove(entity);
/*	911 */			 everyoneSleeping();
/*			*/		 }
/*			*/ 
/*	914 */		 int i = entity.ah;
/*	915 */		 int j = entity.aj;
/*			*/ 
/*	917 */		 if ((entity.ag) && (isChunkLoaded(i, j))) {
/*	918 */			 getChunkAt(i, j).b(entity);
/*			*/		 }
/*			*/ 
/*	921 */		 this.entityList.remove(entity);
/*	922 */		 b(entity);
/*			*/	 }
/*			*/ 
/*			*/	 public void addIWorldAccess(IWorldAccess iworldaccess) {
/*	926 */		 this.x.add(iworldaccess);
/*			*/	 }
/*			*/ 
/*			*/	 public List getCubes(Entity entity, AxisAlignedBB axisalignedbb) {
/*	930 */		 this.d.clear();
/*	931 */		 int i = MathHelper.floor(axisalignedbb.a);
/*	932 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/*	933 */		 int k = MathHelper.floor(axisalignedbb.b);
/*	934 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/*	935 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/*	936 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/*	938 */		 for (int k1 = i; k1 < j; k1++) {
/*	939 */			 for (int l1 = i1; l1 < j1; l1++) {
/*	940 */				 if (isLoaded(k1, 64, l1)) {
/*	941 */					 for (int i2 = k - 1; i2 < l; i2++) {
/*	942 */						 Block block = Block.byId[getTypeId(k1, i2, l1)];
/*			*/ 
/*	944 */						 if (block != null) {
/*	945 */							 block.a(this, k1, i2, l1, axisalignedbb, this.d, entity);
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	952 */		 double d0 = 0.25D;
/*	953 */		 List list = getEntities(entity, axisalignedbb.grow(d0, d0, d0));
/*	954 */		 Iterator iterator = list.iterator();
/*			*/ 
/*	956 */		 while (iterator.hasNext()) {
/*	957 */			 Entity entity1 = (Entity)iterator.next();
/*	958 */			 AxisAlignedBB axisalignedbb1 = entity1.E();
/*			*/ 
/*	960 */			 if ((axisalignedbb1 != null) && (axisalignedbb1.a(axisalignedbb))) {
/*	961 */				 this.d.add(axisalignedbb1);
/*			*/			 }
/*			*/ 
/*	964 */			 axisalignedbb1 = entity.g(entity1);
/*	965 */			 if ((axisalignedbb1 != null) && (axisalignedbb1.a(axisalignedbb))) {
/*	966 */				 this.d.add(axisalignedbb1);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	970 */		 return this.d;
/*			*/	 }
/*			*/ 
/*			*/	 public List a(AxisAlignedBB axisalignedbb) {
/*	974 */		 this.d.clear();
/*	975 */		 int i = MathHelper.floor(axisalignedbb.a);
/*	976 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/*	977 */		 int k = MathHelper.floor(axisalignedbb.b);
/*	978 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/*	979 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/*	980 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/*	982 */		 for (int k1 = i; k1 < j; k1++) {
/*	983 */			 for (int l1 = i1; l1 < j1; l1++) {
/*	984 */				 if (isLoaded(k1, 64, l1)) {
/*	985 */					 for (int i2 = k - 1; i2 < l; i2++) {
/*	986 */						 Block block = Block.byId[getTypeId(k1, i2, l1)];
/*			*/ 
/*	988 */						 if (block != null) {
/*	989 */							 block.a(this, k1, i2, l1, axisalignedbb, this.d, (Entity)null);
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	996 */		 return this.d;
/*			*/	 }
/*			*/ 
/*			*/	 public int a(float f) {
/* 1000 */		 float f1 = c(f);
/* 1001 */		 float f2 = 1.0F - (MathHelper.cos(f1 * 3.141593F * 2.0F) * 2.0F + 0.5F);
/*			*/ 
/* 1003 */		 if (f2 < 0.0F) {
/* 1004 */			 f2 = 0.0F;
/*			*/		 }
/*			*/ 
/* 1007 */		 if (f2 > 1.0F) {
/* 1008 */			 f2 = 1.0F;
/*			*/		 }
/*			*/ 
/* 1011 */		 f2 = 1.0F - f2;
/* 1012 */		 f2 = (float)(f2 * (1.0D - j(f) * 5.0F / 16.0D));
/* 1013 */		 f2 = (float)(f2 * (1.0D - i(f) * 5.0F / 16.0D));
/* 1014 */		 f2 = 1.0F - f2;
/* 1015 */		 return (int)(f2 * 11.0F);
/*			*/	 }
/*			*/ 
/*			*/	 public float c(float f) {
/* 1019 */		 return this.worldProvider.a(this.worldData.getTime(), f);
/*			*/	 }
/*			*/ 
/*			*/	 public int g(int i, int j) {
/* 1023 */		 return getChunkAtWorldCoords(i, j).d(i & 0xF, j & 0xF);
/*			*/	 }
/*			*/ 
/*			*/	 public int h(int i, int j) {
/* 1027 */		 Chunk chunk = getChunkAtWorldCoords(i, j);
/* 1028 */		 int k = chunk.h() + 15;
/*			*/ 
/* 1030 */		 i &= 15;
/*			*/ 
/* 1032 */		 for (j &= 15; k > 0; k--) {
/* 1033 */			 int l = chunk.getTypeId(i, k, j);
/*			*/ 
/* 1035 */			 if ((l != 0) && (Block.byId[l].material.isSolid()) && (Block.byId[l].material != Material.LEAVES)) {
/* 1036 */				 return k + 1;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1040 */		 return -1;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(int i, int j, int k, int l, int i1)
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public void b(int i, int j, int k, int l, int i1)
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public void tickEntities()
/*			*/	 {
/* 1054 */		 for (int i = 0; i < this.j.size(); i++) {
/* 1055 */			 Entity entity = (Entity)this.j.get(i);
/*			*/ 
/* 1057 */			 ChunkProviderServer chunkProviderServer = ((WorldServer)entity.world).chunkProviderServer;
/* 1058 */			 if (chunkProviderServer.unloadQueue.containsKey(MathHelper.floor(entity.locX) >> 4, MathHelper.floor(entity.locZ) >> 4))
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/* 1062 */			 if (entity == null)
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/* 1066 */			 entity.h_();
/* 1067 */			 if (entity.dead) {
/* 1068 */				 this.j.remove(i--);
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1073 */		 this.entityList.removeAll(this.g);
/* 1074 */		 Iterator iterator = this.g.iterator();
/*			*/ 
/* 1079 */		 while (iterator.hasNext()) {
/* 1080 */			 Entity entity = (Entity)iterator.next();
/* 1081 */			 int j = entity.ah;
/* 1082 */			 int k = entity.aj;
/* 1083 */			 if ((entity.ag) && (isChunkLoaded(j, k))) {
/* 1084 */				 getChunkAt(j, k).b(entity);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1088 */		 iterator = this.g.iterator();
/*			*/ 
/* 1090 */		 while (iterator.hasNext()) {
/* 1091 */			 Entity entity = (Entity)iterator.next();
/* 1092 */			 b(entity);
/*			*/		 }
/*			*/ 
/* 1095 */		 this.g.clear();
/*			*/ 
/* 1098 */		 for (i = 0; i < this.entityList.size(); i++) {
/* 1099 */			 Entity entity = (Entity)this.entityList.get(i);
/*			*/ 
/* 1102 */			 ChunkProviderServer chunkProviderServer = ((WorldServer)entity.world).chunkProviderServer;
/* 1103 */			 if (chunkProviderServer.unloadQueue.containsKey(MathHelper.floor(entity.locX) >> 4, MathHelper.floor(entity.locZ) >> 4))
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/*			*/ 
/* 1108 */			 if (entity.vehicle != null) {
/* 1109 */				 if ((!entity.vehicle.dead) && (entity.vehicle.passenger == entity))
/*			*/				 {
/*			*/					 continue;
/*			*/				 }
/* 1113 */				 entity.vehicle.passenger = null;
/* 1114 */				 entity.vehicle = null;
/*			*/			 }
/*			*/ 
/* 1118 */			 if (!entity.dead) {
/* 1119 */				 playerJoinedWorld(entity);
/*			*/			 }
/*			*/ 
/* 1124 */			 if (entity.dead) {
/* 1125 */				 int j = entity.ah;
/* 1126 */				 int k = entity.aj;
/* 1127 */				 if ((entity.ag) && (isChunkLoaded(j, k))) {
/* 1128 */					 getChunkAt(j, k).b(entity);
/*			*/				 }
/*			*/ 
/* 1131 */				 this.entityList.remove(i--);
/* 1132 */				 b(entity);
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1139 */		 this.L = true;
/* 1140 */		 iterator = this.tileEntityList.iterator();
/*			*/ 
/* 1142 */		 while (iterator.hasNext()) {
/* 1143 */			 TileEntity tileentity = (TileEntity)iterator.next();
/*			*/ 
/* 1146 */			 ChunkProviderServer chunkProviderServer = ((WorldServer)tileentity.world).chunkProviderServer;
/* 1147 */			 if (chunkProviderServer.unloadQueue.containsKey(tileentity.x >> 4, tileentity.z >> 4))
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/*			*/ 
/* 1152 */			 if ((!tileentity.p()) && (tileentity.m()) && (isLoaded(tileentity.x, tileentity.y, tileentity.z))) {
/* 1153 */				 tileentity.g();
/*			*/			 }
/*			*/ 
/* 1156 */			 if (tileentity.p()) {
/* 1157 */				 iterator.remove();
/* 1158 */				 if (isChunkLoaded(tileentity.x >> 4, tileentity.z >> 4)) {
/* 1159 */					 Chunk chunk = getChunkAt(tileentity.x >> 4, tileentity.z >> 4);
/*			*/ 
/* 1161 */					 if (chunk != null) {
/* 1162 */						 chunk.f(tileentity.x & 0xF, tileentity.y, tileentity.z & 0xF);
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1168 */		 this.L = false;
/* 1169 */		 if (!this.b.isEmpty()) {
/* 1170 */			 this.tileEntityList.removeAll(this.b);
/* 1171 */			 this.b.clear();
/*			*/		 }
/*			*/ 
/* 1175 */		 if (!this.a.isEmpty()) {
/* 1176 */			 Iterator iterator1 = this.a.iterator();
/*			*/ 
/* 1178 */			 while (iterator1.hasNext()) {
/* 1179 */				 TileEntity tileentity1 = (TileEntity)iterator1.next();
/*			*/ 
/* 1181 */				 if (!tileentity1.p())
/*			*/				 {
/* 1188 */					 if (isChunkLoaded(tileentity1.x >> 4, tileentity1.z >> 4)) {
/* 1189 */						 Chunk chunk1 = getChunkAt(tileentity1.x >> 4, tileentity1.z >> 4);
/*			*/ 
/* 1191 */						 if (chunk1 != null) {
/* 1192 */							 chunk1.a(tileentity1.x & 0xF, tileentity1.y, tileentity1.z & 0xF, tileentity1);
/*			*/ 
/* 1194 */							 if (!this.tileEntityList.contains(tileentity1)) {
/* 1195 */								 this.tileEntityList.add(tileentity1);
/*			*/							 }
/*			*/						 }
/*			*/ 
/*			*/					 }
/*			*/ 
/* 1201 */					 notify(tileentity1.x, tileentity1.y, tileentity1.z);
/*			*/				 }
/*			*/			 }
/*			*/ 
/* 1205 */			 this.a.clear();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Collection collection)
/*			*/	 {
/* 1213 */		 if (this.L)
/* 1214 */			 this.a.addAll(collection);
/*			*/		 else
/* 1216 */			 this.tileEntityList.addAll(collection);
/*			*/	 }
/*			*/ 
/*			*/	 public void playerJoinedWorld(Entity entity)
/*			*/	 {
/* 1221 */		 entityJoinedWorld(entity, true);
/*			*/	 }
/*			*/ 
/*			*/	 public void entityJoinedWorld(Entity entity, boolean flag) {
/* 1225 */		 int i = MathHelper.floor(entity.locX);
/* 1226 */		 int j = MathHelper.floor(entity.locZ);
/* 1227 */		 byte b0 = 32;
/*			*/ 
/* 1229 */		 if ((!flag) || (c(i - b0, 0, j - b0, i + b0, 0, j + b0))) {
/* 1230 */			 entity.S = entity.locX;
/* 1231 */			 entity.T = entity.locY;
/* 1232 */			 entity.U = entity.locZ;
/* 1233 */			 entity.lastYaw = entity.yaw;
/* 1234 */			 entity.lastPitch = entity.pitch;
/* 1235 */			 if ((flag) && (entity.ag)) {
/* 1236 */				 if (entity.vehicle != null)
/* 1237 */					 entity.U();
/*			*/				 else {
/* 1239 */					 entity.h_();
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1244 */			 if ((Double.isNaN(entity.locX)) || (Double.isInfinite(entity.locX))) {
/* 1245 */				 entity.locX = entity.S;
/*			*/			 }
/*			*/ 
/* 1248 */			 if ((Double.isNaN(entity.locY)) || (Double.isInfinite(entity.locY))) {
/* 1249 */				 entity.locY = entity.T;
/*			*/			 }
/*			*/ 
/* 1252 */			 if ((Double.isNaN(entity.locZ)) || (Double.isInfinite(entity.locZ))) {
/* 1253 */				 entity.locZ = entity.U;
/*			*/			 }
/*			*/ 
/* 1256 */			 if ((Double.isNaN(entity.pitch)) || (Double.isInfinite(entity.pitch))) {
/* 1257 */				 entity.pitch = entity.lastPitch;
/*			*/			 }
/*			*/ 
/* 1260 */			 if ((Double.isNaN(entity.yaw)) || (Double.isInfinite(entity.yaw))) {
/* 1261 */				 entity.yaw = entity.lastYaw;
/*			*/			 }
/*			*/ 
/* 1264 */			 int k = MathHelper.floor(entity.locX / 16.0D);
/* 1265 */			 int l = MathHelper.floor(entity.locY / 16.0D);
/* 1266 */			 int i1 = MathHelper.floor(entity.locZ / 16.0D);
/*			*/ 
/* 1268 */			 if ((!entity.ag) || (entity.ah != k) || (entity.ai != l) || (entity.aj != i1)) {
/* 1269 */				 if ((entity.ag) && (isChunkLoaded(entity.ah, entity.aj))) {
/* 1270 */					 getChunkAt(entity.ah, entity.aj).a(entity, entity.ai);
/*			*/				 }
/*			*/ 
/* 1273 */				 if (isChunkLoaded(k, i1)) {
/* 1274 */					 entity.ag = true;
/* 1275 */					 getChunkAt(k, i1).a(entity);
/*			*/				 } else {
/* 1277 */					 entity.ag = false;
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1282 */			 if ((flag) && (entity.ag) && (entity.passenger != null))
/* 1283 */				 if ((!entity.passenger.dead) && (entity.passenger.vehicle == entity)) {
/* 1284 */					 playerJoinedWorld(entity.passenger);
/*			*/				 } else {
/* 1286 */					 entity.passenger.vehicle = null;
/* 1287 */					 entity.passenger = null;
/*			*/				 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean b(AxisAlignedBB axisalignedbb)
/*			*/	 {
/* 1294 */		 return a(axisalignedbb, (Entity)null);
/*			*/	 }
/*			*/ 
/* 1298 */	 public boolean a(AxisAlignedBB axisalignedbb, Entity entity) { List list = getEntities((Entity)null, axisalignedbb);
/* 1299 */		 Iterator iterator = list.iterator();
/*			*/		 Entity entity1;
/*			*/		 do {
/* 1304 */			 if (!iterator.hasNext()) {
/* 1305 */				 return true;
/*			*/			 }
/*			*/ 
/* 1308 */			 entity1 = (Entity)iterator.next();
/* 1309 */		 }while ((entity1.dead) || (!entity1.m) || (entity1 == entity));
/*			*/ 
/* 1311 */		 return false; }
/*			*/ 
/*			*/	 public boolean c(AxisAlignedBB axisalignedbb)
/*			*/	 {
/* 1315 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 1316 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/* 1317 */		 int k = MathHelper.floor(axisalignedbb.b);
/* 1318 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/* 1319 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/* 1320 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/* 1322 */		 if (axisalignedbb.a < 0.0D) {
/* 1323 */			 i--;
/*			*/		 }
/*			*/ 
/* 1326 */		 if (axisalignedbb.b < 0.0D) {
/* 1327 */			 k--;
/*			*/		 }
/*			*/ 
/* 1330 */		 if (axisalignedbb.c < 0.0D) {
/* 1331 */			 i1--;
/*			*/		 }
/*			*/ 
/* 1334 */		 for (int k1 = i; k1 < j; k1++) {
/* 1335 */			 for (int l1 = k; l1 < l; l1++) {
/* 1336 */				 for (int i2 = i1; i2 < j1; i2++) {
/* 1337 */					 Block block = Block.byId[getTypeId(k1, l1, i2)];
/*			*/ 
/* 1339 */					 if (block != null) {
/* 1340 */						 return true;
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1346 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean containsLiquid(AxisAlignedBB axisalignedbb) {
/* 1350 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 1351 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/* 1352 */		 int k = MathHelper.floor(axisalignedbb.b);
/* 1353 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/* 1354 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/* 1355 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/* 1357 */		 if (axisalignedbb.a < 0.0D) {
/* 1358 */			 i--;
/*			*/		 }
/*			*/ 
/* 1361 */		 if (axisalignedbb.b < 0.0D) {
/* 1362 */			 k--;
/*			*/		 }
/*			*/ 
/* 1365 */		 if (axisalignedbb.c < 0.0D) {
/* 1366 */			 i1--;
/*			*/		 }
/*			*/ 
/* 1369 */		 for (int k1 = i; k1 < j; k1++) {
/* 1370 */			 for (int l1 = k; l1 < l; l1++) {
/* 1371 */				 for (int i2 = i1; i2 < j1; i2++) {
/* 1372 */					 Block block = Block.byId[getTypeId(k1, l1, i2)];
/*			*/ 
/* 1374 */					 if ((block != null) && (block.material.isLiquid())) {
/* 1375 */						 return true;
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1381 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean e(AxisAlignedBB axisalignedbb) {
/* 1385 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 1386 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/* 1387 */		 int k = MathHelper.floor(axisalignedbb.b);
/* 1388 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/* 1389 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/* 1390 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/* 1392 */		 if (c(i, k, i1, j, l, j1)) {
/* 1393 */			 for (int k1 = i; k1 < j; k1++) {
/* 1394 */				 for (int l1 = k; l1 < l; l1++) {
/* 1395 */					 for (int i2 = i1; i2 < j1; i2++) {
/* 1396 */						 int j2 = getTypeId(k1, l1, i2);
/*			*/ 
/* 1398 */						 if ((j2 == Block.FIRE.id) || (j2 == Block.LAVA.id) || (j2 == Block.STATIONARY_LAVA.id)) {
/* 1399 */							 return true;
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1406 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(AxisAlignedBB axisalignedbb, Material material, Entity entity) {
/* 1410 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 1411 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/* 1412 */		 int k = MathHelper.floor(axisalignedbb.b);
/* 1413 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/* 1414 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/* 1415 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/* 1417 */		 if (!c(i, k, i1, j, l, j1)) {
/* 1418 */			 return false;
/*			*/		 }
/* 1420 */		 boolean flag = false;
/* 1421 */		 Vec3D vec3d = Vec3D.a().create(0.0D, 0.0D, 0.0D);
/*			*/ 
/* 1423 */		 for (int k1 = i; k1 < j; k1++) {
/* 1424 */			 for (int l1 = k; l1 < l; l1++) {
/* 1425 */				 for (int i2 = i1; i2 < j1; i2++) {
/* 1426 */					 Block block = Block.byId[getTypeId(k1, l1, i2)];
/*			*/ 
/* 1428 */					 if ((block != null) && (block.material == material)) {
/* 1429 */						 double d0 = l1 + 1 - BlockFluids.d(getData(k1, l1, i2));
/*			*/ 
/* 1431 */						 if (l >= d0) {
/* 1432 */							 flag = true;
/* 1433 */							 block.a(this, k1, l1, i2, entity, vec3d);
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1440 */		 if (vec3d.c() > 0.0D) {
/* 1441 */			 vec3d = vec3d.b();
/* 1442 */			 double d1 = 0.014D;
/*			*/ 
/* 1444 */			 entity.motX += vec3d.a * d1;
/* 1445 */			 entity.motY += vec3d.b * d1;
/* 1446 */			 entity.motZ += vec3d.c * d1;
/*			*/		 }
/*			*/ 
/* 1449 */		 return flag;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(AxisAlignedBB axisalignedbb, Material material)
/*			*/	 {
/* 1454 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 1455 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/* 1456 */		 int k = MathHelper.floor(axisalignedbb.b);
/* 1457 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/* 1458 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/* 1459 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/* 1461 */		 for (int k1 = i; k1 < j; k1++) {
/* 1462 */			 for (int l1 = k; l1 < l; l1++) {
/* 1463 */				 for (int i2 = i1; i2 < j1; i2++) {
/* 1464 */					 Block block = Block.byId[getTypeId(k1, l1, i2)];
/*			*/ 
/* 1466 */					 if ((block != null) && (block.material == material)) {
/* 1467 */						 return true;
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1473 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean b(AxisAlignedBB axisalignedbb, Material material) {
/* 1477 */		 int i = MathHelper.floor(axisalignedbb.a);
/* 1478 */		 int j = MathHelper.floor(axisalignedbb.d + 1.0D);
/* 1479 */		 int k = MathHelper.floor(axisalignedbb.b);
/* 1480 */		 int l = MathHelper.floor(axisalignedbb.e + 1.0D);
/* 1481 */		 int i1 = MathHelper.floor(axisalignedbb.c);
/* 1482 */		 int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);
/*			*/ 
/* 1484 */		 for (int k1 = i; k1 < j; k1++) {
/* 1485 */			 for (int l1 = k; l1 < l; l1++) {
/* 1486 */				 for (int i2 = i1; i2 < j1; i2++) {
/* 1487 */					 Block block = Block.byId[getTypeId(k1, l1, i2)];
/*			*/ 
/* 1489 */					 if ((block != null) && (block.material == material)) {
/* 1490 */						 int j2 = getData(k1, l1, i2);
/* 1491 */						 double d0 = l1 + 1;
/*			*/ 
/* 1493 */						 if (j2 < 8) {
/* 1494 */							 d0 = l1 + 1 - j2 / 8.0D;
/*			*/						 }
/*			*/ 
/* 1497 */						 if (d0 >= axisalignedbb.b) {
/* 1498 */							 return true;
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1505 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public Explosion explode(Entity entity, double d0, double d1, double d2, float f) {
/* 1509 */		 return createExplosion(entity, d0, d1, d2, f, false);
/*			*/	 }
/*			*/ 
/*			*/	 public Explosion createExplosion(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
/* 1513 */		 Explosion explosion = new Explosion(this, entity, d0, d1, d2, f);
/*			*/ 
/* 1515 */		 explosion.a = flag;
/* 1516 */		 explosion.a();
/* 1517 */		 explosion.a(true);
/* 1518 */		 return explosion;
/*			*/	 }
/*			*/ 
/*			*/	 public float a(Vec3D vec3d, AxisAlignedBB axisalignedbb) {
/* 1522 */		 double d0 = 1.0D / ((axisalignedbb.d - axisalignedbb.a) * 2.0D + 1.0D);
/* 1523 */		 double d1 = 1.0D / ((axisalignedbb.e - axisalignedbb.b) * 2.0D + 1.0D);
/* 1524 */		 double d2 = 1.0D / ((axisalignedbb.f - axisalignedbb.c) * 2.0D + 1.0D);
/* 1525 */		 int i = 0;
/* 1526 */		 int j = 0;
/*			*/ 
/* 1528 */		 for (float f = 0.0F; f <= 1.0F; f = (float)(f + d0)) {
/* 1529 */			 for (float f1 = 0.0F; f1 <= 1.0F; f1 = (float)(f1 + d1)) {
/* 1530 */				 for (float f2 = 0.0F; f2 <= 1.0F; f2 = (float)(f2 + d2)) {
/* 1531 */					 double d3 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * f;
/* 1532 */					 double d4 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * f1;
/* 1533 */					 double d5 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * f2;
/*			*/ 
/* 1535 */					 if (a(Vec3D.a().create(d3, d4, d5), vec3d) == null) {
/* 1536 */						 i++;
/*			*/					 }
/*			*/ 
/* 1539 */					 j++;
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1544 */		 return i / j;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean douseFire(EntityHuman entityhuman, int i, int j, int k, int l) {
/* 1548 */		 if (l == 0) {
/* 1549 */			 j--;
/*			*/		 }
/*			*/ 
/* 1552 */		 if (l == 1) {
/* 1553 */			 j++;
/*			*/		 }
/*			*/ 
/* 1556 */		 if (l == 2) {
/* 1557 */			 k--;
/*			*/		 }
/*			*/ 
/* 1560 */		 if (l == 3) {
/* 1561 */			 k++;
/*			*/		 }
/*			*/ 
/* 1564 */		 if (l == 4) {
/* 1565 */			 i--;
/*			*/		 }
/*			*/ 
/* 1568 */		 if (l == 5) {
/* 1569 */			 i++;
/*			*/		 }
/*			*/ 
/* 1572 */		 if (getTypeId(i, j, k) == Block.FIRE.id) {
/* 1573 */			 a(entityhuman, 1004, i, j, k, 0);
/* 1574 */			 setTypeId(i, j, k, 0);
/* 1575 */			 return true;
/*			*/		 }
/* 1577 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public TileEntity getTileEntity(int i, int j, int k)
/*			*/	 {
/* 1582 */		 if (j >= 256) {
/* 1583 */			 return null;
/*			*/		 }
/* 1585 */		 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*			*/ 
/* 1587 */		 if (chunk == null) {
/* 1588 */			 return null;
/*			*/		 }
/* 1590 */		 TileEntity tileentity = chunk.e(i & 0xF, j, k & 0xF);
/*			*/ 
/* 1592 */		 if (tileentity == null) {
/* 1593 */			 Iterator iterator = this.a.iterator();
/*			*/ 
/* 1595 */			 while (iterator.hasNext()) {
/* 1596 */				 TileEntity tileentity1 = (TileEntity)iterator.next();
/*			*/ 
/* 1598 */				 if ((!tileentity1.p()) && (tileentity1.x == i) && (tileentity1.y == j) && (tileentity1.z == k)) {
/* 1599 */					 tileentity = tileentity1;
/* 1600 */					 break;
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1605 */		 return tileentity;
/*			*/	 }
/*			*/ 
/*			*/	 public void setTileEntity(int i, int j, int k, TileEntity tileentity)
/*			*/	 {
/* 1611 */		 if ((tileentity != null) && (!tileentity.p()))
/* 1612 */			 if (this.L) {
/* 1613 */				 tileentity.x = i;
/* 1614 */				 tileentity.y = j;
/* 1615 */				 tileentity.z = k;
/* 1616 */				 this.a.add(tileentity);
/*			*/			 } else {
/* 1618 */				 this.tileEntityList.add(tileentity);
/* 1619 */				 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*			*/ 
/* 1621 */				 if (chunk != null)
/* 1622 */					 chunk.a(i & 0xF, j, k & 0xF, tileentity);
/*			*/			 }
/*			*/	 }
/*			*/ 
/*			*/	 public void q(int i, int j, int k)
/*			*/	 {
/* 1629 */		 TileEntity tileentity = getTileEntity(i, j, k);
/*			*/ 
/* 1631 */		 if ((tileentity != null) && (this.L)) {
/* 1632 */			 tileentity.j();
/* 1633 */			 this.a.remove(tileentity);
/*			*/		 } else {
/* 1635 */			 if (tileentity != null) {
/* 1636 */				 this.a.remove(tileentity);
/* 1637 */				 this.tileEntityList.remove(tileentity);
/*			*/			 }
/*			*/ 
/* 1640 */			 Chunk chunk = getChunkAt(i >> 4, k >> 4);
/*			*/ 
/* 1642 */			 if (chunk != null)
/* 1643 */				 chunk.f(i & 0xF, j, k & 0xF);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(TileEntity tileentity)
/*			*/	 {
/* 1649 */		 this.b.add(tileentity);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean r(int i, int j, int k) {
/* 1653 */		 Block block = Block.byId[getTypeId(i, j, k)];
/*			*/ 
/* 1655 */		 return block == null ? false : block.d();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean s(int i, int j, int k) {
/* 1659 */		 return Block.i(getTypeId(i, j, k));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean t(int i, int j, int k) {
/* 1663 */		 Block block = Block.byId[getTypeId(i, j, k)];
/*			*/ 
/* 1665 */		 return block != null;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean b(int i, int j, int k, boolean flag) {
/* 1669 */		 if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
/* 1670 */			 Chunk chunk = this.chunkProvider.getOrCreateChunk(i >> 4, k >> 4);
/*			*/ 
/* 1672 */			 if ((chunk != null) && (!chunk.isEmpty())) {
/* 1673 */				 Block block = Block.byId[getTypeId(i, j, k)];
/*			*/ 
/* 1675 */				 return block != null;
/*			*/			 }
/* 1677 */			 return flag;
/*			*/		 }
/*			*/ 
/* 1680 */		 return flag;
/*			*/	 }
/*			*/ 
/*			*/	 public void v()
/*			*/	 {
/* 1685 */		 int i = a(1.0F);
/*			*/ 
/* 1687 */		 if (i != this.k)
/* 1688 */			 this.k = i;
/*			*/	 }
/*			*/ 
/*			*/	 public void setSpawnFlags(boolean flag, boolean flag1)
/*			*/	 {
/* 1693 */		 this.allowMonsters = flag;
/* 1694 */		 this.allowAnimals = flag1;
/*			*/	 }
/*			*/ 
/*			*/	 public void doTick() {
/* 1698 */		 l();
/*			*/	 }
/*			*/ 
/*			*/	 private void a() {
/* 1702 */		 if (this.worldData.hasStorm()) {
/* 1703 */			 this.o = 1.0F;
/* 1704 */			 if (this.worldData.isThundering())
/* 1705 */				 this.q = 1.0F;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void l()
/*			*/	 {
/* 1711 */		 if (!this.worldProvider.e) {
/* 1712 */			 if (this.r > 0) {
/* 1713 */				 this.r -= 1;
/*			*/			 }
/*			*/ 
/* 1716 */			 int i = this.worldData.getThunderDuration();
/*			*/ 
/* 1718 */			 if (i <= 0) {
/* 1719 */				 if (this.worldData.isThundering())
/* 1720 */					 this.worldData.setThunderDuration(this.random.nextInt(12000) + 3600);
/*			*/				 else
/* 1722 */					 this.worldData.setThunderDuration(this.random.nextInt(168000) + 12000);
/*			*/			 }
/*			*/			 else {
/* 1725 */				 i--;
/* 1726 */				 this.worldData.setThunderDuration(i);
/* 1727 */				 if (i <= 0)
/*			*/				 {
/* 1729 */					 ThunderChangeEvent thunder = new ThunderChangeEvent(getWorld(), !this.worldData.isThundering());
/* 1730 */					 getServer().getPluginManager().callEvent(thunder);
/* 1731 */					 if (!thunder.isCancelled()) {
/* 1732 */						 this.worldData.setThundering(!this.worldData.isThundering());
/*			*/					 }
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1738 */			 int j = this.worldData.getWeatherDuration();
/*			*/ 
/* 1740 */			 if (j <= 0) {
/* 1741 */				 if (this.worldData.hasStorm())
/* 1742 */					 this.worldData.setWeatherDuration(this.random.nextInt(12000) + 12000);
/*			*/				 else
/* 1744 */					 this.worldData.setWeatherDuration(this.random.nextInt(168000) + 12000);
/*			*/			 }
/*			*/			 else {
/* 1747 */				 j--;
/* 1748 */				 this.worldData.setWeatherDuration(j);
/* 1749 */				 if (j <= 0)
/*			*/				 {
/* 1751 */					 WeatherChangeEvent weather = new WeatherChangeEvent(getWorld(), !this.worldData.hasStorm());
/* 1752 */					 getServer().getPluginManager().callEvent(weather);
/*			*/ 
/* 1754 */					 if (!weather.isCancelled()) {
/* 1755 */						 this.worldData.setStorm(!this.worldData.hasStorm());
/*			*/					 }
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1761 */			 this.n = this.o;
/* 1762 */			 if (this.worldData.hasStorm())
/* 1763 */				 this.o = (float)(this.o + 0.01D);
/*			*/			 else {
/* 1765 */				 this.o = (float)(this.o - 0.01D);
/*			*/			 }
/*			*/ 
/* 1768 */			 if (this.o < 0.0F) {
/* 1769 */				 this.o = 0.0F;
/*			*/			 }
/*			*/ 
/* 1772 */			 if (this.o > 1.0F) {
/* 1773 */				 this.o = 1.0F;
/*			*/			 }
/*			*/ 
/* 1776 */			 this.p = this.q;
/* 1777 */			 if (this.worldData.isThundering())
/* 1778 */				 this.q = (float)(this.q + 0.01D);
/*			*/			 else {
/* 1780 */				 this.q = (float)(this.q - 0.01D);
/*			*/			 }
/*			*/ 
/* 1783 */			 if (this.q < 0.0F) {
/* 1784 */				 this.q = 0.0F;
/*			*/			 }
/*			*/ 
/* 1787 */			 if (this.q > 1.0F)
/* 1788 */				 this.q = 1.0F;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void w()
/*			*/	 {
/* 1794 */		 this.worldData.setWeatherDuration(1);
/*			*/	 }
/*			*/ 
/*			*/	 protected void x()
/*			*/	 {
/* 1806 */		 for (int i = 0; i < this.players.size(); i++) {
/* 1807 */			 EntityHuman entityhuman = (EntityHuman)this.players.get(i);
/* 1808 */			 int j = MathHelper.floor(entityhuman.locX / 16.0D);
/* 1809 */			 int k = MathHelper.floor(entityhuman.locZ / 16.0D);
/* 1810 */			 byte b0 = 7;
/*			*/ 
/* 1812 */			 for (int l = -b0; l <= b0; l++) {
/* 1813 */				 for (int i1 = -b0; i1 <= b0; i1++)
/*			*/				 {
/* 1815 */					 ChunkProviderServer chunkProviderServer = ((WorldServer)entityhuman.world).chunkProviderServer;
/* 1816 */					 if (chunkProviderServer.unloadQueue.containsKey(l + j, i1 + k))
/*			*/					 {
/*			*/						 continue;
/*			*/					 }
/*			*/ 
/* 1821 */					 this.chunkTickList.add(LongHash.toLong(l + j, i1 + k));
/*			*/				 }
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1827 */		 if (this.M > 0) {
/* 1828 */			 this.M -= 1;
/*			*/		 }
/*			*/ 
/* 1832 */		 if (!this.players.isEmpty()) {
/* 1833 */			 i = this.random.nextInt(this.players.size());
/* 1834 */			 EntityHuman entityhuman = (EntityHuman)this.players.get(i);
/* 1835 */			 int j = MathHelper.floor(entityhuman.locX) + this.random.nextInt(11) - 5;
/* 1836 */			 int k = MathHelper.floor(entityhuman.locY) + this.random.nextInt(11) - 5;
/* 1837 */			 int j1 = MathHelper.floor(entityhuman.locZ) + this.random.nextInt(11) - 5;
/*			*/ 
/* 1839 */			 x(j, k, j1);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(int i, int j, Chunk chunk)
/*			*/	 {
/* 1847 */		 if (this.M == 0) {
/* 1848 */			 this.l = (this.l * 3 + 1013904223);
/* 1849 */			 int k = this.l >> 2;
/* 1850 */			 int l = k & 0xF;
/* 1851 */			 int i1 = k >> 8 & 0xF;
/* 1852 */			 int j1 = k >> 16 & 0xFF;
/* 1853 */			 int k1 = chunk.getTypeId(l, j1, i1);
/*			*/ 
/* 1855 */			 l += i;
/* 1856 */			 i1 += j;
/* 1857 */			 if ((k1 == 0) && (k(l, j1, i1) <= this.random.nextInt(8)) && (b(EnumSkyBlock.SKY, l, j1, i1) <= 0)) {
/* 1858 */				 EntityHuman entityhuman = findNearbyPlayer(l + 0.5D, j1 + 0.5D, i1 + 0.5D, 8.0D);
/*			*/ 
/* 1860 */				 if ((entityhuman != null) && (entityhuman.e(l + 0.5D, j1 + 0.5D, i1 + 0.5D) > 4.0D)) {
/* 1861 */					 makeSound(l + 0.5D, j1 + 0.5D, i1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.random.nextFloat() * 0.2F);
/* 1862 */					 this.M = (this.random.nextInt(12000) + 6000);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/* 1868 */		 chunk.o();
/*			*/	 }
/*			*/ 
/*			*/	 protected void g() {
/* 1872 */		 x();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean u(int i, int j, int k) {
/* 1876 */		 return c(i, j, k, false);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean v(int i, int j, int k) {
/* 1880 */		 return c(i, j, k, true);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean c(int i, int j, int k, boolean flag) {
/* 1884 */		 BiomeBase biomebase = getBiome(i, k);
/* 1885 */		 float f = biomebase.j();
/*			*/ 
/* 1887 */		 if (f > 0.15F) {
/* 1888 */			 return false;
/*			*/		 }
/* 1890 */		 if ((j >= 0) && (j < 256) && (b(EnumSkyBlock.BLOCK, i, j, k) < 10)) {
/* 1891 */			 int l = getTypeId(i, j, k);
/*			*/ 
/* 1893 */			 if (((l == Block.STATIONARY_WATER.id) || (l == Block.WATER.id)) && (getData(i, j, k) == 0)) {
/* 1894 */				 if (!flag) {
/* 1895 */					 return true;
/*			*/				 }
/*			*/ 
/* 1898 */				 boolean flag1 = true;
/*			*/ 
/* 1900 */				 if ((flag1) && (getMaterial(i - 1, j, k) != Material.WATER)) {
/* 1901 */					 flag1 = false;
/*			*/				 }
/*			*/ 
/* 1904 */				 if ((flag1) && (getMaterial(i + 1, j, k) != Material.WATER)) {
/* 1905 */					 flag1 = false;
/*			*/				 }
/*			*/ 
/* 1908 */				 if ((flag1) && (getMaterial(i, j, k - 1) != Material.WATER)) {
/* 1909 */					 flag1 = false;
/*			*/				 }
/*			*/ 
/* 1912 */				 if ((flag1) && (getMaterial(i, j, k + 1) != Material.WATER)) {
/* 1913 */					 flag1 = false;
/*			*/				 }
/*			*/ 
/* 1916 */				 if (!flag1) {
/* 1917 */					 return true;
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1922 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean w(int i, int j, int k)
/*			*/	 {
/* 1927 */		 BiomeBase biomebase = getBiome(i, k);
/* 1928 */		 float f = biomebase.j();
/*			*/ 
/* 1930 */		 if (f > 0.15F) {
/* 1931 */			 return false;
/*			*/		 }
/* 1933 */		 if ((j >= 0) && (j < 256) && (b(EnumSkyBlock.BLOCK, i, j, k) < 10)) {
/* 1934 */			 int l = getTypeId(i, j - 1, k);
/* 1935 */			 int i1 = getTypeId(i, j, k);
/*			*/ 
/* 1937 */			 if ((i1 == 0) && (Block.SNOW.canPlace(this, i, j, k)) && (l != 0) && (l != Block.ICE.id) && (Block.byId[l].material.isSolid())) {
/* 1938 */				 return true;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1942 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public void x(int i, int j, int k)
/*			*/	 {
/* 1947 */		 if (!this.worldProvider.e) {
/* 1948 */			 c(EnumSkyBlock.SKY, i, j, k);
/*			*/		 }
/*			*/ 
/* 1951 */		 c(EnumSkyBlock.BLOCK, i, j, k);
/*			*/	 }
/*			*/ 
/*			*/	 private int a(int i, int j, int k, int l, int i1, int j1) {
/* 1955 */		 int k1 = 0;
/*			*/ 
/* 1957 */		 if (j(j, k, l)) {
/* 1958 */			 k1 = 15;
/*			*/		 } else {
/* 1960 */			 if (j1 == 0) {
/* 1961 */				 j1 = 1;
/*			*/			 }
/*			*/ 
/* 1964 */			 int l1 = b(EnumSkyBlock.SKY, j - 1, k, l) - j1;
/* 1965 */			 int i2 = b(EnumSkyBlock.SKY, j + 1, k, l) - j1;
/* 1966 */			 int j2 = b(EnumSkyBlock.SKY, j, k - 1, l) - j1;
/* 1967 */			 int k2 = b(EnumSkyBlock.SKY, j, k + 1, l) - j1;
/* 1968 */			 int l2 = b(EnumSkyBlock.SKY, j, k, l - 1) - j1;
/* 1969 */			 int i3 = b(EnumSkyBlock.SKY, j, k, l + 1) - j1;
/*			*/ 
/* 1971 */			 if (l1 > k1) {
/* 1972 */				 k1 = l1;
/*			*/			 }
/*			*/ 
/* 1975 */			 if (i2 > k1) {
/* 1976 */				 k1 = i2;
/*			*/			 }
/*			*/ 
/* 1979 */			 if (j2 > k1) {
/* 1980 */				 k1 = j2;
/*			*/			 }
/*			*/ 
/* 1983 */			 if (k2 > k1) {
/* 1984 */				 k1 = k2;
/*			*/			 }
/*			*/ 
/* 1987 */			 if (l2 > k1) {
/* 1988 */				 k1 = l2;
/*			*/			 }
/*			*/ 
/* 1991 */			 if (i3 > k1) {
/* 1992 */				 k1 = i3;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1996 */		 return k1;
/*			*/	 }
/*			*/ 
/*			*/	 private int f(int i, int j, int k, int l, int i1, int j1) {
/* 2000 */		 int k1 = Block.lightEmission[i1];
/* 2001 */		 int l1 = b(EnumSkyBlock.BLOCK, j - 1, k, l) - j1;
/* 2002 */		 int i2 = b(EnumSkyBlock.BLOCK, j + 1, k, l) - j1;
/* 2003 */		 int j2 = b(EnumSkyBlock.BLOCK, j, k - 1, l) - j1;
/* 2004 */		 int k2 = b(EnumSkyBlock.BLOCK, j, k + 1, l) - j1;
/* 2005 */		 int l2 = b(EnumSkyBlock.BLOCK, j, k, l - 1) - j1;
/* 2006 */		 int i3 = b(EnumSkyBlock.BLOCK, j, k, l + 1) - j1;
/*			*/ 
/* 2008 */		 if (l1 > k1) {
/* 2009 */			 k1 = l1;
/*			*/		 }
/*			*/ 
/* 2012 */		 if (i2 > k1) {
/* 2013 */			 k1 = i2;
/*			*/		 }
/*			*/ 
/* 2016 */		 if (j2 > k1) {
/* 2017 */			 k1 = j2;
/*			*/		 }
/*			*/ 
/* 2020 */		 if (k2 > k1) {
/* 2021 */			 k1 = k2;
/*			*/		 }
/*			*/ 
/* 2024 */		 if (l2 > k1) {
/* 2025 */			 k1 = l2;
/*			*/		 }
/*			*/ 
/* 2028 */		 if (i3 > k1) {
/* 2029 */			 k1 = i3;
/*			*/		 }
/*			*/ 
/* 2032 */		 return k1;
/*			*/	 }
/*			*/ 
/*			*/	 public void c(EnumSkyBlock enumskyblock, int i, int j, int k) {
/* 2036 */		 if (areChunksLoaded(i, j, k, 17)) {
/* 2037 */			 int l = 0;
/* 2038 */			 int i1 = 0;
/*			*/ 
/* 2041 */			 int j1 = b(enumskyblock, i, j, k);
/* 2042 */			 boolean flag = false;
/* 2043 */			 int k1 = getTypeId(i, j, k);
/* 2044 */			 int l1 = b(i, j, k);
/*			*/ 
/* 2046 */			 if (l1 == 0) {
/* 2047 */				 l1 = 1;
/*			*/			 }
/*			*/ 
/* 2050 */			 boolean flag1 = false;
/*			*/			 int i2;
/*			*/			 int i2;
/* 2053 */			 if (enumskyblock == EnumSkyBlock.SKY)
/* 2054 */				 i2 = a(j1, i, j, k, k1, l1);
/*			*/			 else {
/* 2056 */				 i2 = f(j1, i, j, k, k1, l1);
/*			*/			 }
/*			*/ 
/* 2068 */			 if (i2 > j1) {
/* 2069 */				 this.J[(i1++)] = 133152;
/* 2070 */			 } else if (i2 < j1) {
/* 2071 */				 if (enumskyblock != EnumSkyBlock.BLOCK);
/* 2075 */				 this.J[(i1++)] = (133152 + (j1 << 18));
/*			*/ 
/* 2077 */				 while (l < i1) {
/* 2078 */					 k1 = this.J[(l++)];
/* 2079 */					 l1 = (k1 & 0x3F) - 32 + i;
/* 2080 */					 i2 = (k1 >> 6 & 0x3F) - 32 + j;
/* 2081 */					 int j2 = (k1 >> 12 & 0x3F) - 32 + k;
/* 2082 */					 int k2 = k1 >> 18 & 0xF;
/* 2083 */					 int l2 = b(enumskyblock, l1, i2, j2);
/* 2084 */					 if (l2 == k2) {
/* 2085 */						 b(enumskyblock, l1, i2, j2, 0);
/* 2086 */						 if (k2 > 0) {
/* 2087 */							 int i3 = l1 - i;
/* 2088 */							 int k3 = i2 - j;
/* 2089 */							 int j3 = j2 - k;
/* 2090 */							 if (i3 < 0) {
/* 2091 */								 i3 = -i3;
/*			*/							 }
/*			*/ 
/* 2094 */							 if (k3 < 0) {
/* 2095 */								 k3 = -k3;
/*			*/							 }
/*			*/ 
/* 2098 */							 if (j3 < 0) {
/* 2099 */								 j3 = -j3;
/*			*/							 }
/*			*/ 
/* 2102 */							 if (i3 + k3 + j3 < 17) {
/* 2103 */								 for (int i4 = 0; i4 < 6; i4++) {
/* 2104 */									 int l3 = i4 % 2 * 2 - 1;
/* 2105 */									 int j4 = l1 + i4 / 2 % 3 / 2 * l3;
/* 2106 */									 int k4 = i2 + (i4 / 2 + 1) % 3 / 2 * l3;
/* 2107 */									 int l4 = j2 + (i4 / 2 + 2) % 3 / 2 * l3;
/*			*/ 
/* 2109 */									 l2 = b(enumskyblock, j4, k4, l4);
/* 2110 */									 int i5 = Block.lightBlock[getTypeId(j4, k4, l4)];
/*			*/ 
/* 2112 */									 if (i5 == 0) {
/* 2113 */										 i5 = 1;
/*			*/									 }
/*			*/ 
/* 2116 */									 if ((l2 == k2 - i5) && (i1 < this.J.length)) {
/* 2117 */										 this.J[(i1++)] = (j4 - i + 32 + (k4 - j + 32 << 6) + (l4 - k + 32 << 12) + (k2 - i5 << 18));
/*			*/									 }
/*			*/								 }
/*			*/							 }
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/ 
/* 2125 */				 l = 0;
/*			*/			 }
/*			*/ 
/* 2131 */			 while (l < i1) {
/* 2132 */				 k1 = this.J[(l++)];
/* 2133 */				 l1 = (k1 & 0x3F) - 32 + i;
/* 2134 */				 i2 = (k1 >> 6 & 0x3F) - 32 + j;
/* 2135 */				 int j2 = (k1 >> 12 & 0x3F) - 32 + k;
/* 2136 */				 int k2 = b(enumskyblock, l1, i2, j2);
/* 2137 */				 int l2 = getTypeId(l1, i2, j2);
/* 2138 */				 int i3 = Block.lightBlock[l2];
/* 2139 */				 if (i3 == 0) {
/* 2140 */					 i3 = 1;
/*			*/				 }
/*			*/ 
/* 2143 */				 boolean flag2 = false;
/*			*/				 int k3;
/*			*/				 int k3;
/* 2145 */				 if (enumskyblock == EnumSkyBlock.SKY)
/* 2146 */					 k3 = a(k2, l1, i2, j2, l2, i3);
/*			*/				 else {
/* 2148 */					 k3 = f(k2, l1, i2, j2, l2, i3);
/*			*/				 }
/*			*/ 
/* 2151 */				 if (k3 != k2) {
/* 2152 */					 b(enumskyblock, l1, i2, j2, k3);
/* 2153 */					 if (k3 > k2) {
/* 2154 */						 int j3 = l1 - i;
/* 2155 */						 int i4 = i2 - j;
/* 2156 */						 int l3 = j2 - k;
/* 2157 */						 if (j3 < 0) {
/* 2158 */							 j3 = -j3;
/*			*/						 }
/*			*/ 
/* 2161 */						 if (i4 < 0) {
/* 2162 */							 i4 = -i4;
/*			*/						 }
/*			*/ 
/* 2165 */						 if (l3 < 0) {
/* 2166 */							 l3 = -l3;
/*			*/						 }
/*			*/ 
/* 2169 */						 if ((j3 + i4 + l3 < 17) && (i1 < this.J.length - 6)) {
/* 2170 */							 if (b(enumskyblock, l1 - 1, i2, j2) < k3) {
/* 2171 */								 this.J[(i1++)] = (l1 - 1 - i + 32 + (i2 - j + 32 << 6) + (j2 - k + 32 << 12));
/*			*/							 }
/*			*/ 
/* 2174 */							 if (b(enumskyblock, l1 + 1, i2, j2) < k3) {
/* 2175 */								 this.J[(i1++)] = (l1 + 1 - i + 32 + (i2 - j + 32 << 6) + (j2 - k + 32 << 12));
/*			*/							 }
/*			*/ 
/* 2178 */							 if (b(enumskyblock, l1, i2 - 1, j2) < k3) {
/* 2179 */								 this.J[(i1++)] = (l1 - i + 32 + (i2 - 1 - j + 32 << 6) + (j2 - k + 32 << 12));
/*			*/							 }
/*			*/ 
/* 2182 */							 if (b(enumskyblock, l1, i2 + 1, j2) < k3) {
/* 2183 */								 this.J[(i1++)] = (l1 - i + 32 + (i2 + 1 - j + 32 << 6) + (j2 - k + 32 << 12));
/*			*/							 }
/*			*/ 
/* 2186 */							 if (b(enumskyblock, l1, i2, j2 - 1) < k3) {
/* 2187 */								 this.J[(i1++)] = (l1 - i + 32 + (i2 - j + 32 << 6) + (j2 - 1 - k + 32 << 12));
/*			*/							 }
/*			*/ 
/* 2190 */							 if (b(enumskyblock, l1, i2, j2 + 1) < k3)
/* 2191 */								 this.J[(i1++)] = (l1 - i + 32 + (i2 - j + 32 << 6) + (j2 + 1 - k + 32 << 12));
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(boolean flag)
/*			*/	 {
/* 2203 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public List a(Chunk chunk, boolean flag) {
/* 2207 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 public List getEntities(Entity entity, AxisAlignedBB axisalignedbb) {
/* 2211 */		 this.N.clear();
/* 2212 */		 int i = MathHelper.floor((axisalignedbb.a - 2.0D) / 16.0D);
/* 2213 */		 int j = MathHelper.floor((axisalignedbb.d + 2.0D) / 16.0D);
/* 2214 */		 int k = MathHelper.floor((axisalignedbb.c - 2.0D) / 16.0D);
/* 2215 */		 int l = MathHelper.floor((axisalignedbb.f + 2.0D) / 16.0D);
/*			*/ 
/* 2217 */		 for (int i1 = i; i1 <= j; i1++) {
/* 2218 */			 for (int j1 = k; j1 <= l; j1++) {
/* 2219 */				 if (isChunkLoaded(i1, j1)) {
/* 2220 */					 getChunkAt(i1, j1).a(entity, axisalignedbb, this.N);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2225 */		 return this.N;
/*			*/	 }
/*			*/ 
/*			*/	 public List a(Class oclass, AxisAlignedBB axisalignedbb) {
/* 2229 */		 int i = MathHelper.floor((axisalignedbb.a - 2.0D) / 16.0D);
/* 2230 */		 int j = MathHelper.floor((axisalignedbb.d + 2.0D) / 16.0D);
/* 2231 */		 int k = MathHelper.floor((axisalignedbb.c - 2.0D) / 16.0D);
/* 2232 */		 int l = MathHelper.floor((axisalignedbb.f + 2.0D) / 16.0D);
/* 2233 */		 ArrayList arraylist = new ArrayList();
/*			*/ 
/* 2235 */		 for (int i1 = i; i1 <= j; i1++) {
/* 2236 */			 for (int j1 = k; j1 <= l; j1++) {
/* 2237 */				 if (isChunkLoaded(i1, j1)) {
/* 2238 */					 getChunkAt(i1, j1).a(oclass, axisalignedbb, arraylist);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2243 */		 return arraylist;
/*			*/	 }
/*			*/ 
/*			*/	 public Entity a(Class oclass, AxisAlignedBB axisalignedbb, Entity entity) {
/* 2247 */		 List list = a(oclass, axisalignedbb);
/* 2248 */		 Entity entity1 = null;
/* 2249 */		 double d0 = 1.7976931348623157E+308D;
/* 2250 */		 Iterator iterator = list.iterator();
/*			*/ 
/* 2252 */		 while (iterator.hasNext()) {
/* 2253 */			 Entity entity2 = (Entity)iterator.next();
/*			*/ 
/* 2255 */			 if (entity2 != entity) {
/* 2256 */				 double d1 = entity.e(entity2);
/*			*/ 
/* 2258 */				 if (d1 <= d0) {
/* 2259 */					 entity1 = entity2;
/* 2260 */					 d0 = d1;
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2265 */		 return entity1;
/*			*/	 }
/*			*/ 
/*			*/	 public void b(int i, int j, int k, TileEntity tileentity) {
/* 2269 */		 if (isLoaded(i, j, k))
/* 2270 */			 getChunkAtWorldCoords(i, k).e();
/*			*/	 }
/*			*/ 
/*			*/	 public int a(Class oclass)
/*			*/	 {
/* 2275 */		 int i = 0;
/*			*/ 
/* 2277 */		 for (int j = 0; j < this.entityList.size(); j++) {
/* 2278 */			 Entity entity = (Entity)this.entityList.get(j);
/*			*/ 
/* 2280 */			 if (oclass.isAssignableFrom(entity.getClass())) {
/* 2281 */				 i++;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2285 */		 return i;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(List list)
/*			*/	 {
/* 2290 */		 Entity entity = null;
/* 2291 */		 for (int i = 0; i < list.size(); i++) {
/* 2292 */			 entity = (Entity)list.get(i);
/* 2293 */			 if (entity == null) {
/*			*/				 continue;
/*			*/			 }
/* 2296 */			 this.entityList.add(entity);
/*			*/ 
/* 2298 */			 a((Entity)list.get(i));
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void b(List list) {
/* 2303 */		 this.g.addAll(list);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean mayPlace(int i, int j, int k, int l, boolean flag, int i1, Entity entity) {
/* 2307 */		 int j1 = getTypeId(j, k, l);
/* 2308 */		 Block block = Block.byId[j1];
/* 2309 */		 Block block1 = Block.byId[i];
/* 2310 */		 AxisAlignedBB axisalignedbb = block1.e(this, j, k, l);
/*			*/ 
/* 2312 */		 if (flag)
/* 2313 */			 axisalignedbb = null;
/*			*/		 boolean defaultReturn;
/*			*/		 boolean defaultReturn;
/* 2318 */		 if ((axisalignedbb != null) && (!a(axisalignedbb, entity))) {
/* 2319 */			 defaultReturn = false;
/*			*/		 } else {
/* 2321 */			 if ((block != null) && ((block == Block.WATER) || (block == Block.STATIONARY_WATER) || (block == Block.LAVA) || (block == Block.STATIONARY_LAVA) || (block == Block.FIRE) || (block.material.isReplaceable()))) {
/* 2322 */				 block = null;
/*			*/			 }
/*			*/ 
/* 2325 */			 defaultReturn = (i > 0) && (block == null) && (block1.canPlace(this, j, k, l, i1));
/*			*/		 }
/*			*/ 
/* 2329 */		 BlockCanBuildEvent event = new BlockCanBuildEvent(getWorld().getBlockAt(j, k, l), i, defaultReturn);
/* 2330 */		 getServer().getPluginManager().callEvent(event);
/*			*/ 
/* 2332 */		 return event.isBuildable();
/*			*/	 }
/*			*/ 
/*			*/	 public PathEntity findPath(Entity entity, Entity entity1, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3)
/*			*/	 {
/* 2338 */		 int i = MathHelper.floor(entity.locX);
/* 2339 */		 int j = MathHelper.floor(entity.locY + 1.0D);
/* 2340 */		 int k = MathHelper.floor(entity.locZ);
/* 2341 */		 int l = (int)(f + 16.0F);
/* 2342 */		 int i1 = i - l;
/* 2343 */		 int j1 = j - l;
/* 2344 */		 int k1 = k - l;
/* 2345 */		 int l1 = i + l;
/* 2346 */		 int i2 = j + l;
/* 2347 */		 int j2 = k + l;
/* 2348 */		 ChunkCache chunkcache = new ChunkCache(this, i1, j1, k1, l1, i2, j2);
/* 2349 */		 PathEntity pathentity = new Pathfinder(chunkcache, flag, flag1, flag2, flag3).a(entity, entity1, f);
/*			*/ 
/* 2352 */		 return pathentity;
/*			*/	 }
/*			*/ 
/*			*/	 public PathEntity a(Entity entity, int i, int j, int k, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3)
/*			*/	 {
/* 2357 */		 int l = MathHelper.floor(entity.locX);
/* 2358 */		 int i1 = MathHelper.floor(entity.locY);
/* 2359 */		 int j1 = MathHelper.floor(entity.locZ);
/* 2360 */		 int k1 = (int)(f + 8.0F);
/* 2361 */		 int l1 = l - k1;
/* 2362 */		 int i2 = i1 - k1;
/* 2363 */		 int j2 = j1 - k1;
/* 2364 */		 int k2 = l + k1;
/* 2365 */		 int l2 = i1 + k1;
/* 2366 */		 int i3 = j1 + k1;
/* 2367 */		 ChunkCache chunkcache = new ChunkCache(this, l1, i2, j2, k2, l2, i3);
/* 2368 */		 PathEntity pathentity = new Pathfinder(chunkcache, flag, flag1, flag2, flag3).a(entity, i, j, k, f);
/*			*/ 
/* 2371 */		 return pathentity;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isBlockFacePowered(int i, int j, int k, int l) {
/* 2375 */		 int i1 = getTypeId(i, j, k);
/*			*/ 
/* 2377 */		 return i1 == 0 ? false : Block.byId[i1].c(this, i, j, k, l);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isBlockPowered(int i, int j, int k) {
/* 2381 */		 return isBlockFacePowered(i - 1, j, k, 4) ? true : isBlockFacePowered(i, j, k + 1, 3) ? true : isBlockFacePowered(i, j, k - 1, 2) ? true : isBlockFacePowered(i, j + 1, k, 1) ? true : isBlockFacePowered(i, j - 1, k, 0) ? true : isBlockFacePowered(i + 1, j, k, 5);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isBlockFaceIndirectlyPowered(int i, int j, int k, int l) {
/* 2385 */		 if (s(i, j, k)) {
/* 2386 */			 return isBlockPowered(i, j, k);
/*			*/		 }
/* 2388 */		 int i1 = getTypeId(i, j, k);
/*			*/ 
/* 2390 */		 return i1 == 0 ? false : Block.byId[i1].a(this, i, j, k, l);
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isBlockIndirectlyPowered(int i, int j, int k)
/*			*/	 {
/* 2395 */		 return isBlockFaceIndirectlyPowered(i - 1, j, k, 4) ? true : isBlockFaceIndirectlyPowered(i, j, k + 1, 3) ? true : isBlockFaceIndirectlyPowered(i, j, k - 1, 2) ? true : isBlockFaceIndirectlyPowered(i, j + 1, k, 1) ? true : isBlockFaceIndirectlyPowered(i, j - 1, k, 0) ? true : isBlockFaceIndirectlyPowered(i + 1, j, k, 5);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityHuman findNearbyPlayer(Entity entity, double d0) {
/* 2399 */		 return findNearbyPlayer(entity.locX, entity.locY, entity.locZ, d0);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityHuman findNearbyPlayer(double d0, double d1, double d2, double d3) {
/* 2403 */		 double d4 = -1.0D;
/* 2404 */		 EntityHuman entityhuman = null;
/*			*/ 
/* 2406 */		 for (int i = 0; i < this.players.size(); i++) {
/* 2407 */			 EntityHuman entityhuman1 = (EntityHuman)this.players.get(i);
/*			*/ 
/* 2409 */			 if ((entityhuman1 == null) || (entityhuman1.dead))
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/* 2413 */			 double d5 = entityhuman1.e(d0, d1, d2);
/*			*/ 
/* 2415 */			 if (((d3 < 0.0D) || (d5 < d3 * d3)) && ((d4 == -1.0D) || (d5 < d4))) {
/* 2416 */				 d4 = d5;
/* 2417 */				 entityhuman = entityhuman1;
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2421 */		 return entityhuman;
/*			*/	 }
/*			*/ 
/*			*/	 public EntityHuman findNearbyVulnerablePlayer(Entity entity, double d0) {
/* 2425 */		 return findNearbyVulnerablePlayer(entity.locX, entity.locY, entity.locZ, d0);
/*			*/	 }
/*			*/ 
/*			*/	 public EntityHuman findNearbyVulnerablePlayer(double d0, double d1, double d2, double d3) {
/* 2429 */		 double d4 = -1.0D;
/* 2430 */		 EntityHuman entityhuman = null;
/*			*/ 
/* 2432 */		 for (int i = 0; i < this.players.size(); i++) {
/* 2433 */			 EntityHuman entityhuman1 = (EntityHuman)this.players.get(i);
/*			*/ 
/* 2435 */			 if ((entityhuman1 == null) || (entityhuman1.dead))
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/*			*/ 
/* 2440 */			 if (!entityhuman1.abilities.isInvulnerable) {
/* 2441 */				 double d5 = entityhuman1.e(d0, d1, d2);
/*			*/ 
/* 2443 */				 if (((d3 < 0.0D) || (d5 < d3 * d3)) && ((d4 == -1.0D) || (d5 < d4))) {
/* 2444 */					 d4 = d5;
/* 2445 */					 entityhuman = entityhuman1;
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2450 */		 return entityhuman;
/*			*/	 }
/*			*/ 
/*			*/	 public EntityHuman a(String s) {
/* 2454 */		 for (int i = 0; i < this.players.size(); i++) {
/* 2455 */			 if (s.equals(((EntityHuman)this.players.get(i)).name)) {
/* 2456 */				 return (EntityHuman)this.players.get(i);
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 2460 */		 return null;
/*			*/	 }
/*			*/ 
/*			*/	 public void B() throws ExceptionWorldConflict {
/* 2464 */		 this.dataManager.checkSession();
/*			*/	 }
/*			*/ 
/*			*/	 public void setTime(long i) {
/* 2468 */		 this.worldData.b(i);
/*			*/	 }
/*			*/ 
/*			*/	 public long getSeed() {
/* 2472 */		 return this.worldData.getSeed();
/*			*/	 }
/*			*/ 
/*			*/	 public long getTime() {
/* 2476 */		 return this.worldData.getTime();
/*			*/	 }
/*			*/ 
/*			*/	 public ChunkCoordinates getSpawn() {
/* 2480 */		 return new ChunkCoordinates(this.worldData.c(), this.worldData.d(), this.worldData.e());
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a(EntityHuman entityhuman, int i, int j, int k) {
/* 2484 */		 return true;
/*			*/	 }
/*			*/	 public void broadcastEntityEffect(Entity entity, byte b0) {
/*			*/	 }
/*			*/ 
/*			*/	 public IChunkProvider F() {
/* 2490 */		 return this.chunkProvider;
/*			*/	 }
/*			*/ 
/*			*/	 public void playNote(int i, int j, int k, int l, int i1, int j1) {
/* 2494 */		 if (l > 0)
/* 2495 */			 Block.byId[l].b(this, i, j, k, i1, j1);
/*			*/	 }
/*			*/ 
/*			*/	 public IDataManager getDataManager()
/*			*/	 {
/* 2500 */		 return this.dataManager;
/*			*/	 }
/*			*/ 
/*			*/	 public WorldData getWorldData() {
/* 2504 */		 return this.worldData;
/*			*/	 }
/*			*/ 
/*			*/	 public void everyoneSleeping()
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public void checkSleepStatus()
/*			*/	 {
/* 2513 */		 if (!this.isStatic)
/* 2514 */			 everyoneSleeping();
/*			*/	 }
/*			*/ 
/*			*/	 public float i(float f)
/*			*/	 {
/* 2520 */		 return (this.p + (this.q - this.p) * f) * j(f);
/*			*/	 }
/*			*/ 
/*			*/	 public float j(float f) {
/* 2524 */		 return this.n + (this.o - this.n) * f;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean I() {
/* 2528 */		 return i(1.0F) > 0.9D;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean J() {
/* 2532 */		 return j(1.0F) > 0.2D;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean B(int i, int j, int k) {
/* 2536 */		 if (!J())
/* 2537 */			 return false;
/* 2538 */		 if (!j(i, j, k))
/* 2539 */			 return false;
/* 2540 */		 if (g(i, k) > j) {
/* 2541 */			 return false;
/*			*/		 }
/* 2543 */		 BiomeBase biomebase = getBiome(i, k);
/*			*/ 
/* 2545 */		 return biomebase.c() ? false : biomebase.d();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean C(int i, int j, int k)
/*			*/	 {
/* 2550 */		 BiomeBase biomebase = getBiome(i, k);
/*			*/ 
/* 2552 */		 return biomebase.e();
/*			*/	 }
/*			*/ 
/*			*/	 public void a(String s, WorldMapBase worldmapbase) {
/* 2556 */		 this.worldMaps.a(s, worldmapbase);
/*			*/	 }
/*			*/ 
/*			*/	 public WorldMapBase a(Class oclass, String s) {
/* 2560 */		 return this.worldMaps.get(oclass, s);
/*			*/	 }
/*			*/ 
/*			*/	 public int b(String s) {
/* 2564 */		 return this.worldMaps.a(s);
/*			*/	 }
/*			*/ 
/*			*/	 public void triggerEffect(int i, int j, int k, int l, int i1) {
/* 2568 */		 a((EntityHuman)null, i, j, k, l, i1);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1) {
/* 2572 */		 for (int j1 = 0; j1 < this.x.size(); j1++)
/* 2573 */			 ((IWorldAccess)this.x.get(j1)).a(entityhuman, i, j, k, l, i1);
/*			*/	 }
/*			*/ 
/*			*/	 public int getHeight()
/*			*/	 {
/* 2578 */		 return 256;
/*			*/	 }
/*			*/ 
/*			*/	 public int L() {
/* 2582 */		 return this.worldProvider.e ? 128 : 256;
/*			*/	 }
/*			*/ 
/*			*/	 public Random D(int i, int j, int k) {
/* 2586 */		 long l = i * 341873128712L + j * 132897987541L + getWorldData().getSeed() + k;
/*			*/ 
/* 2588 */		 this.random.setSeed(l);
/* 2589 */		 return this.random;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean updateLights() {
/* 2593 */		 return false;
/*			*/	 }
/*			*/ 
/*			*/	 public ChunkPosition b(String s, int i, int j, int k) {
/* 2597 */		 return F().findNearestMapFeature(this, s, i, j, k);
/*			*/	 }
/*			*/ 
/*			*/	 public CrashReport a(CrashReport crashreport) {
/* 2601 */		 crashreport.a("World " + this.worldData.getName() + " Entities", new CrashReportEntities(this));
/* 2602 */		 crashreport.a("World " + this.worldData.getName() + " Players", new CrashReportPlayers(this));
/* 2603 */		 crashreport.a("World " + this.worldData.getName() + " Chunk Stats", new CrashReportChunkStats(this));
/* 2604 */		 return crashreport;
/*			*/	 }
/*			*/ 
/*			*/	 public void f(int i, int j, int k, int l, int i1) {
/* 2608 */		 Iterator iterator = this.x.iterator();
/*			*/ 
/* 2610 */		 while (iterator.hasNext()) {
/* 2611 */			 IWorldAccess iworldaccess = (IWorldAccess)iterator.next();
/*			*/ 
/* 2613 */			 iworldaccess.a(i, j, k, l, i1);
/*			*/		 }
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.World
 * JD-Core Version:		0.6.0
 */