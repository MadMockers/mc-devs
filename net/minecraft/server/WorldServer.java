/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ import java.util.Set;
/*		 */ import java.util.TreeSet;
/*		 */ import java.util.logging.Logger;
/*		 */ import org.bukkit.BlockChangeDelegate;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.Material;
/*		 */ import org.bukkit.World.Environment;
/*		 */ import org.bukkit.block.BlockState;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.generator.CustomChunkGenerator;
/*		 */ import org.bukkit.craftbukkit.generator.InternalChunkGenerator;
/*		 */ import org.bukkit.craftbukkit.generator.NetherChunkGenerator;
/*		 */ import org.bukkit.craftbukkit.generator.NormalChunkGenerator;
/*		 */ import org.bukkit.craftbukkit.generator.SkyLandsChunkGenerator;
/*		 */ import org.bukkit.craftbukkit.util.LongHash;
/*		 */ import org.bukkit.craftbukkit.util.LongHashset;
/*		 */ import org.bukkit.entity.LightningStrike;
/*		 */ import org.bukkit.event.block.BlockFormEvent;
/*		 */ import org.bukkit.event.weather.LightningStrikeEvent;
/*		 */ import org.bukkit.event.weather.ThunderChangeEvent;
/*		 */ import org.bukkit.event.weather.WeatherChangeEvent;
/*		 */ import org.bukkit.generator.ChunkGenerator;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class WorldServer extends World
/*		 */	 implements BlockChangeDelegate
/*		 */ {
/*		 */	 private final MinecraftServer server;
/*		 */	 public EntityTracker tracker;
/*		 */	 private final PlayerManager manager;
/*		 */	 private Set N;
/*		 */	 private TreeSet O;
/*		 */	 public ChunkProviderServer chunkProviderServer;
/*	30 */	 public boolean weirdIsOpCache = false;
/*		 */	 public boolean savingDisabled;
/*		 */	 private boolean P;
/*	33 */	 private NoteDataList[] Q = { new NoteDataList((EmptyClass2)null), new NoteDataList((EmptyClass2)null) };
/*	34 */	 private int R = 0;
/*	35 */	 private static final StructurePieceTreasure[] S = { new StructurePieceTreasure(Item.STICK.id, 0, 1, 3, 10), new StructurePieceTreasure(Block.WOOD.id, 0, 1, 3, 10), new StructurePieceTreasure(Block.LOG.id, 0, 1, 3, 10), new StructurePieceTreasure(Item.STONE_AXE.id, 0, 1, 1, 3), new StructurePieceTreasure(Item.WOOD_AXE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.STONE_PICKAXE.id, 0, 1, 1, 3), new StructurePieceTreasure(Item.WOOD_PICKAXE.id, 0, 1, 1, 5), new StructurePieceTreasure(Item.APPLE.id, 0, 2, 3, 5), new StructurePieceTreasure(Item.BREAD.id, 0, 2, 3, 3) };
/*		 */	 private IntHashMap entitiesById;
/*		 */	 public final int dimension;
/*		 */ 
/*		 */	 public WorldServer(MinecraftServer minecraftserver, IDataManager idatamanager, String s, int i, WorldSettings worldsettings, MethodProfiler methodprofiler, World.Environment env, ChunkGenerator gen)
/*		 */	 {
/*	42 */		 super(idatamanager, s, worldsettings, WorldProvider.byDimension(env.getId()), methodprofiler, gen, env);
/*	43 */		 this.dimension = i;
/*	44 */		 this.pvpMode = minecraftserver.getPvP();
/*		 */ 
/*	46 */		 this.server = minecraftserver;
/*	47 */		 this.tracker = new EntityTracker(this);
/*	48 */		 this.manager = new PlayerManager(this, minecraftserver.getServerConfigurationManager().o());
/*	49 */		 if (this.entitiesById == null) {
/*	50 */			 this.entitiesById = new IntHashMap();
/*		 */		 }
/*		 */ 
/*	53 */		 if (this.N == null) {
/*	54 */			 this.N = new HashSet();
/*		 */		 }
/*		 */ 
/*	57 */		 if (this.O == null)
/*	58 */			 this.O = new TreeSet();
/*		 */	 }
/*		 */ 
/*		 */	 public TileEntity getTileEntity(int i, int j, int k)
/*		 */	 {
/*	65 */		 TileEntity result = super.getTileEntity(i, j, k);
/*	66 */		 int type = getTypeId(i, j, k);
/*		 */ 
/*	68 */		 if (type == Block.CHEST.id) {
/*	69 */			 if (!(result instanceof TileEntityChest))
/*	70 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	72 */		 else if (type == Block.FURNACE.id) {
/*	73 */			 if (!(result instanceof TileEntityFurnace))
/*	74 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	76 */		 else if (type == Block.DISPENSER.id) {
/*	77 */			 if (!(result instanceof TileEntityDispenser))
/*	78 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	80 */		 else if (type == Block.JUKEBOX.id) {
/*	81 */			 if (!(result instanceof TileEntityRecordPlayer))
/*	82 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	84 */		 else if (type == Block.NOTE_BLOCK.id) {
/*	85 */			 if (!(result instanceof TileEntityNote))
/*	86 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	88 */		 else if (type == Block.MOB_SPAWNER.id) {
/*	89 */			 if (!(result instanceof TileEntityMobSpawner))
/*	90 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	92 */		 else if ((type == Block.SIGN_POST.id) || (type == Block.WALL_SIGN.id)) {
/*	93 */			 if (!(result instanceof TileEntitySign))
/*	94 */				 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*	96 */		 else if ((type == Block.ENDER_CHEST.id) && 
/*	97 */			 (!(result instanceof TileEntityEnderChest))) {
/*	98 */			 result = fixTileEntity(i, j, k, type, result);
/*		 */		 }
/*		 */ 
/* 102 */		 return result;
/*		 */	 }
/*		 */ 
/*		 */	 private TileEntity fixTileEntity(int x, int y, int z, int type, TileEntity found) {
/* 106 */		 getServer().getLogger().severe("Block at " + x + "," + y + "," + z + " is " + Material.getMaterial(type).toString() + " but has " + found + ". " + "Bukkit will attempt to fix this, but there may be additional damage that we cannot recover.");
/*		 */ 
/* 109 */		 if ((Block.byId[type] instanceof BlockContainer)) {
/* 110 */			 TileEntity replacement = ((BlockContainer)Block.byId[type]).a(this);
/* 111 */			 setTileEntity(x, y, z, replacement);
/* 112 */			 return replacement;
/*		 */		 }
/* 114 */		 getServer().getLogger().severe("Don't know how to fix for this type... Can't do anything! :(");
/* 115 */		 return found;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean canSpawn(int x, int z)
/*		 */	 {
/* 120 */		 if (this.generator != null) {
/* 121 */			 return this.generator.canSpawn(getWorld(), x, z);
/*		 */		 }
/* 123 */		 return this.worldProvider.canSpawn(x, z);
/*		 */	 }
/*		 */ 
/*		 */	 public void doTick()
/*		 */	 {
/* 129 */		 super.doTick();
/* 130 */		 if ((getWorldData().isHardcore()) && (this.difficulty < 3)) {
/* 131 */			 this.difficulty = 3;
/*		 */		 }
/*		 */ 
/* 134 */		 this.worldProvider.c.b();
/* 135 */		 if (everyoneDeeplySleeping()) {
/* 136 */			 boolean flag = false;
/*		 */ 
/* 138 */			 if (((!this.allowMonsters) || (this.difficulty < 1)) || 
/* 142 */				 (!flag)) {
/* 143 */				 long i = this.worldData.getTime() + 24000L;
/*		 */ 
/* 145 */				 this.worldData.b(i - i % 24000L);
/* 146 */				 d();
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 152 */		 long time = this.worldData.getTime();
/* 153 */		 if (((this.allowMonsters) || (this.allowAnimals)) && ((this instanceof WorldServer)) && (getServer().getHandle().players.size() > 0)) {
/* 154 */			 SpawnerCreature.spawnEntities(this, (this.allowMonsters) && (this.ticksPerMonsterSpawns != 0L) && (time % this.ticksPerMonsterSpawns == 0L), (this.allowAnimals) && (this.ticksPerAnimalSpawns != 0L) && (time % this.ticksPerAnimalSpawns == 0L));
/*		 */		 }
/*		 */ 
/* 158 */		 this.chunkProvider.unloadChunks();
/* 159 */		 int j = a(1.0F);
/*		 */ 
/* 161 */		 if (j != this.k) {
/* 162 */			 this.k = j;
/*		 */		 }
/*		 */ 
/* 165 */		 Q();
/* 166 */		 this.worldData.b(this.worldData.getTime() + 1L);
/*		 */ 
/* 168 */		 a(false);
/*		 */ 
/* 170 */		 g();
/*		 */ 
/* 172 */		 this.manager.flush();
/*		 */ 
/* 174 */		 this.villages.tick();
/* 175 */		 this.siegeManager.a();
/*		 */ 
/* 177 */		 Q();
/*		 */	 }
/*		 */ 
/*		 */	 public BiomeMeta a(EnumCreatureType enumcreaturetype, int i, int j, int k) {
/* 181 */		 List list = F().getMobsFor(enumcreaturetype, i, j, k);
/*		 */ 
/* 183 */		 return (list != null) && (!list.isEmpty()) ? (BiomeMeta)WeightedRandom.a(this.random, list) : null;
/*		 */	 }
/*		 */ 
/*		 */	 public void everyoneSleeping() {
/* 187 */		 this.P = (!this.players.isEmpty());
/* 188 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 190 */		 while (iterator.hasNext()) {
/* 191 */			 EntityHuman entityhuman = (EntityHuman)iterator.next();
/*		 */ 
/* 193 */			 if ((!entityhuman.isSleeping()) && (!entityhuman.fauxSleeping)) {
/* 194 */				 this.P = false;
/* 195 */				 break;
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void d() {
/* 201 */		 this.P = false;
/* 202 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 204 */		 while (iterator.hasNext()) {
/* 205 */			 EntityHuman entityhuman = (EntityHuman)iterator.next();
/*		 */ 
/* 207 */			 if (entityhuman.isSleeping()) {
/* 208 */				 entityhuman.a(false, false, true);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 212 */		 P();
/*		 */	 }
/*		 */ 
/*		 */	 private void P()
/*		 */	 {
/* 217 */		 WeatherChangeEvent weather = new WeatherChangeEvent(getWorld(), false);
/* 218 */		 getServer().getPluginManager().callEvent(weather);
/*		 */ 
/* 220 */		 ThunderChangeEvent thunder = new ThunderChangeEvent(getWorld(), false);
/* 221 */		 getServer().getPluginManager().callEvent(thunder);
/* 222 */		 if (!weather.isCancelled()) {
/* 223 */			 this.worldData.setWeatherDuration(0);
/* 224 */			 this.worldData.setStorm(false);
/*		 */		 }
/* 226 */		 if (!thunder.isCancelled()) {
/* 227 */			 this.worldData.setThunderDuration(0);
/* 228 */			 this.worldData.setThundering(false);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean everyoneDeeplySleeping()
/*		 */	 {
/* 234 */		 if ((this.P) && (!this.isStatic)) {
/* 235 */			 Iterator iterator = this.players.iterator();
/*		 */ 
/* 238 */			 boolean foundActualSleepers = false;
/*		 */			 EntityHuman entityhuman;
/*		 */			 do {
/* 243 */				 if (!iterator.hasNext()) {
/* 244 */					 return foundActualSleepers;
/*		 */				 }
/*		 */ 
/* 247 */				 entityhuman = (EntityHuman)iterator.next();
/*		 */ 
/* 249 */				 if (entityhuman.isDeeplySleeping())
/* 250 */					 foundActualSleepers = true;
/*		 */			 }
/* 252 */			 while ((entityhuman.isDeeplySleeping()) || (entityhuman.fauxSleeping));
/*		 */ 
/* 255 */			 return false;
/*		 */		 }
/* 257 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected void g()
/*		 */	 {
/* 262 */		 super.g();
/* 263 */		 int i = 0;
/* 264 */		 int j = 0;
/*		 */ 
/* 268 */		 for (long chunkCoord : this.chunkTickList.popAll()) {
/* 269 */			 int chunkX = LongHash.msw(chunkCoord);
/* 270 */			 int chunkZ = LongHash.lsw(chunkCoord);
/*		 */ 
/* 272 */			 int k = chunkX * 16;
/* 273 */			 int l = chunkZ * 16;
/*		 */ 
/* 276 */			 Chunk chunk = getChunkAt(chunkX, chunkZ);
/*		 */ 
/* 279 */			 a(k, l, chunk);
/*		 */ 
/* 281 */			 chunk.k();
/*		 */ 
/* 288 */			 if ((this.random.nextInt(100000) == 0) && (J()) && (I())) {
/* 289 */				 this.l = (this.l * 3 + 1013904223);
/* 290 */				 int i1 = this.l >> 2;
/* 291 */				 int j1 = k + (i1 & 0xF);
/* 292 */				 int k1 = l + (i1 >> 8 & 0xF);
/* 293 */				 int l1 = g(j1, k1);
/* 294 */				 if (B(j1, l1, k1)) {
/* 295 */					 strikeLightning(new EntityLightning(this, j1, l1, k1));
/* 296 */					 this.r = 2;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 303 */			 if (this.random.nextInt(16) == 0) {
/* 304 */				 this.l = (this.l * 3 + 1013904223);
/* 305 */				 int i1 = this.l >> 2;
/* 306 */				 int j1 = i1 & 0xF;
/* 307 */				 int k1 = i1 >> 8 & 0xF;
/* 308 */				 int l1 = g(j1 + k, k1 + l);
/* 309 */				 if (v(j1 + k, l1 - 1, k1 + l))
/*		 */				 {
/* 311 */					 BlockState blockState = getWorld().getBlockAt(j1 + k, l1 - 1, k1 + l).getState();
/* 312 */					 blockState.setTypeId(Block.ICE.id);
/*		 */ 
/* 314 */					 BlockFormEvent iceBlockForm = new BlockFormEvent(blockState.getBlock(), blockState);
/* 315 */					 getServer().getPluginManager().callEvent(iceBlockForm);
/* 316 */					 if (!iceBlockForm.isCancelled()) {
/* 317 */						 blockState.update(true);
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/* 322 */				 if ((J()) && (w(j1 + k, l1, k1 + l)))
/*		 */				 {
/* 324 */					 BlockState blockState = getWorld().getBlockAt(j1 + k, l1, k1 + l).getState();
/* 325 */					 blockState.setTypeId(Block.SNOW.id);
/*		 */ 
/* 327 */					 BlockFormEvent snow = new BlockFormEvent(blockState.getBlock(), blockState);
/* 328 */					 getServer().getPluginManager().callEvent(snow);
/* 329 */					 if (!snow.isCancelled()) {
/* 330 */						 blockState.update(true);
/*		 */					 }
/*		 */ 
/*		 */				 }
/*		 */ 
/* 335 */				 if (J()) {
/* 336 */					 BiomeBase biomebase = getBiome(j1 + k, k1 + l);
/*		 */ 
/* 338 */					 if (biomebase.d()) {
/* 339 */						 int i2 = getTypeId(j1 + k, l1 - 1, k1 + l);
/* 340 */						 if (i2 != 0) {
/* 341 */							 Block.byId[i2].f(this, j1 + k, l1 - 1, k1 + l);
/*		 */						 }
/*		 */					 }
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 348 */			 ChunkSection[] achunksection = chunk.i();
/*		 */ 
/* 350 */			 int j1 = achunksection.length;
/*		 */ 
/* 352 */			 for (int k1 = 0; k1 < j1; k1++) {
/* 353 */				 ChunkSection chunksection = achunksection[k1];
/*		 */ 
/* 355 */				 if ((chunksection != null) && (chunksection.b()))
/* 356 */					 for (int j2 = 0; j2 < 3; j2++) {
/* 357 */						 this.l = (this.l * 3 + 1013904223);
/* 358 */						 int i2 = this.l >> 2;
/* 359 */						 int k2 = i2 & 0xF;
/* 360 */						 int l2 = i2 >> 8 & 0xF;
/* 361 */						 int i3 = i2 >> 16 & 0xF;
/* 362 */						 int j3 = chunksection.a(k2, i3, l2);
/*		 */ 
/* 364 */						 j++;
/* 365 */						 Block block = Block.byId[j3];
/*		 */ 
/* 367 */						 if ((block != null) && (block.r())) {
/* 368 */							 i++;
/* 369 */							 block.b(this, k2 + k, i3 + chunksection.d(), l2 + l, this.random);
/*		 */						 }
/*		 */					 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int i, int j, int k, int l, int i1)
/*		 */	 {
/* 380 */		 NextTickListEntry nextticklistentry = new NextTickListEntry(i, j, k, l);
/* 381 */		 byte b0 = 8;
/*		 */ 
/* 383 */		 if (this.e) {
/* 384 */			 if (c(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0)) {
/* 385 */				 int j1 = getTypeId(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);
/*		 */ 
/* 387 */				 if ((j1 == nextticklistentry.d) && (j1 > 0)) {
/* 388 */					 Block.byId[j1].b(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, this.random);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/* 392 */		 else if (c(i - b0, j - b0, k - b0, i + b0, j + b0, k + b0)) {
/* 393 */			 if (l > 0) {
/* 394 */				 nextticklistentry.a(i1 + this.worldData.getTime());
/*		 */			 }
/*		 */ 
/* 397 */			 if (!this.N.contains(nextticklistentry)) {
/* 398 */				 this.N.add(nextticklistentry);
/* 399 */				 this.O.add(nextticklistentry);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b(int i, int j, int k, int l, int i1)
/*		 */	 {
/* 406 */		 NextTickListEntry nextticklistentry = new NextTickListEntry(i, j, k, l);
/*		 */ 
/* 408 */		 if (l > 0) {
/* 409 */			 nextticklistentry.a(i1 + this.worldData.getTime());
/*		 */		 }
/*		 */ 
/* 412 */		 if (!this.N.contains(nextticklistentry)) {
/* 413 */			 this.N.add(nextticklistentry);
/* 414 */			 this.O.add(nextticklistentry);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(boolean flag) {
/* 419 */		 int i = this.O.size();
/*		 */ 
/* 421 */		 if (i != this.N.size()) {
/* 422 */			 throw new IllegalStateException("TickNextTick list out of synch");
/*		 */		 }
/* 424 */		 if (i > 1000)
/*		 */		 {
/* 426 */			 if (i > 20000)
/* 427 */				 i /= 20;
/*		 */			 else {
/* 429 */				 i = 1000;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 434 */		 for (int j = 0; j < i; j++) {
/* 435 */			 NextTickListEntry nextticklistentry = (NextTickListEntry)this.O.first();
/*		 */ 
/* 437 */			 if ((!flag) && (nextticklistentry.e > this.worldData.getTime()))
/*		 */			 {
/*		 */				 break;
/*		 */			 }
/* 441 */			 this.O.remove(nextticklistentry);
/* 442 */			 this.N.remove(nextticklistentry);
/* 443 */			 byte b0 = 8;
/*		 */ 
/* 445 */			 if (c(nextticklistentry.a - b0, nextticklistentry.b - b0, nextticklistentry.c - b0, nextticklistentry.a + b0, nextticklistentry.b + b0, nextticklistentry.c + b0)) {
/* 446 */				 int k = getTypeId(nextticklistentry.a, nextticklistentry.b, nextticklistentry.c);
/*		 */ 
/* 448 */				 if ((k == nextticklistentry.d) && (k > 0)) {
/* 449 */					 Block.byId[k].b(this, nextticklistentry.a, nextticklistentry.b, nextticklistentry.c, this.random);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 454 */		 return !this.O.isEmpty();
/*		 */	 }
/*		 */ 
/*		 */	 public List a(Chunk chunk, boolean flag)
/*		 */	 {
/* 459 */		 ArrayList arraylist = null;
/* 460 */		 ChunkCoordIntPair chunkcoordintpair = chunk.l();
/* 461 */		 int i = chunkcoordintpair.x << 4;
/* 462 */		 int j = i + 16;
/* 463 */		 int k = chunkcoordintpair.z << 4;
/* 464 */		 int l = k + 16;
/* 465 */		 Iterator iterator = this.O.iterator();
/*		 */ 
/* 467 */		 while (iterator.hasNext()) {
/* 468 */			 NextTickListEntry nextticklistentry = (NextTickListEntry)iterator.next();
/*		 */ 
/* 470 */			 if ((nextticklistentry.a >= i) && (nextticklistentry.a < j) && (nextticklistentry.c >= k) && (nextticklistentry.c < l)) {
/* 471 */				 if (flag) {
/* 472 */					 this.N.remove(nextticklistentry);
/* 473 */					 iterator.remove();
/*		 */				 }
/*		 */ 
/* 476 */				 if (arraylist == null) {
/* 477 */					 arraylist = new ArrayList();
/*		 */				 }
/*		 */ 
/* 480 */				 arraylist.add(nextticklistentry);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 484 */		 return arraylist;
/*		 */	 }
/*		 */ 
/*		 */	 public void entityJoinedWorld(Entity entity, boolean flag)
/*		 */	 {
/* 493 */		 if ((!this.server.getSpawnNPCs()) && ((entity instanceof NPC))) {
/* 494 */			 entity.die();
/*		 */		 }
/*		 */ 
/* 497 */		 if (!(entity.passenger instanceof EntityHuman))
/* 498 */			 super.entityJoinedWorld(entity, flag);
/*		 */	 }
/*		 */ 
/*		 */	 public void vehicleEnteredWorld(Entity entity, boolean flag)
/*		 */	 {
/* 503 */		 super.entityJoinedWorld(entity, flag);
/*		 */	 }
/*		 */ 
/*		 */	 protected IChunkProvider h() {
/* 507 */		 IChunkLoader ichunkloader = this.dataManager.createChunkLoader(this.worldProvider);
/*		 */		 InternalChunkGenerator gen;
/*		 */		 InternalChunkGenerator gen;
/* 512 */		 if (this.generator != null) {
/* 513 */			 gen = new CustomChunkGenerator(this, getSeed(), this.generator);
/*		 */		 }
/*		 */		 else
/*		 */		 {
/*		 */			 InternalChunkGenerator gen;
/* 514 */			 if ((this.worldProvider instanceof WorldProviderHell)) {
/* 515 */				 gen = new NetherChunkGenerator(this, getSeed());
/*		 */			 }
/*		 */			 else
/*		 */			 {
/*		 */				 InternalChunkGenerator gen;
/* 516 */				 if ((this.worldProvider instanceof WorldProviderTheEnd))
/* 517 */					 gen = new SkyLandsChunkGenerator(this, getSeed());
/*		 */				 else
/* 519 */					 gen = new NormalChunkGenerator(this, getSeed());
/*		 */			 }
/*		 */		 }
/* 522 */		 this.chunkProviderServer = new ChunkProviderServer(this, ichunkloader, gen);
/*		 */ 
/* 525 */		 return this.chunkProviderServer;
/*		 */	 }
/*		 */ 
/*		 */	 public List getTileEntities(int i, int j, int k, int l, int i1, int j1) {
/* 529 */		 ArrayList arraylist = new ArrayList();
/* 530 */		 Iterator iterator = this.tileEntityList.iterator();
/*		 */ 
/* 532 */		 while (iterator.hasNext()) {
/* 533 */			 TileEntity tileentity = (TileEntity)iterator.next();
/*		 */ 
/* 535 */			 if ((tileentity.x >= i) && (tileentity.y >= j) && (tileentity.z >= k) && (tileentity.x < l) && (tileentity.y < i1) && (tileentity.z < j1)) {
/* 536 */				 arraylist.add(tileentity);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 540 */		 return arraylist;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(EntityHuman entityhuman, int i, int j, int k) {
/* 544 */		 int l = MathHelper.a(i - this.worldData.c());
/* 545 */		 int i1 = MathHelper.a(k - this.worldData.e());
/*		 */ 
/* 547 */		 if (l > i1) {
/* 548 */			 i1 = l;
/*		 */		 }
/*		 */ 
/* 552 */		 return (i1 > getServer().getSpawnRadius()) || (this.server.getServerConfigurationManager().isOp(entityhuman.name)) || (this.server.H());
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(WorldSettings worldsettings) {
/* 556 */		 if (this.entitiesById == null) {
/* 557 */			 this.entitiesById = new IntHashMap();
/*		 */		 }
/*		 */ 
/* 560 */		 if (this.N == null) {
/* 561 */			 this.N = new HashSet();
/*		 */		 }
/*		 */ 
/* 564 */		 if (this.O == null) {
/* 565 */			 this.O = new TreeSet();
/*		 */		 }
/*		 */ 
/* 568 */		 b(worldsettings);
/* 569 */		 super.a(worldsettings);
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(WorldSettings worldsettings) {
/* 573 */		 if (!this.worldProvider.e()) {
/* 574 */			 this.worldData.setSpawn(0, this.worldProvider.getSeaLevel(), 0);
/*		 */		 } else {
/* 576 */			 this.isLoading = true;
/* 577 */			 WorldChunkManager worldchunkmanager = this.worldProvider.c;
/* 578 */			 List list = worldchunkmanager.a();
/* 579 */			 Random random = new Random(getSeed());
/* 580 */			 ChunkPosition chunkposition = worldchunkmanager.a(0, 0, 256, list, random);
/* 581 */			 int i = 0;
/* 582 */			 int j = this.worldProvider.getSeaLevel();
/* 583 */			 int k = 0;
/*		 */ 
/* 586 */			 if (this.generator != null) {
/* 587 */				 Random rand = new Random(getSeed());
/* 588 */				 Location spawn = this.generator.getFixedSpawnLocation(getWorld(), rand);
/*		 */ 
/* 590 */				 if (spawn != null) {
/* 591 */					 if (spawn.getWorld() != getWorld()) {
/* 592 */						 throw new IllegalStateException("Cannot set spawn point for " + this.worldData.getName() + " to be in another world (" + spawn.getWorld().getName() + ")");
/*		 */					 }
/* 594 */					 this.worldData.setSpawn(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
/* 595 */					 this.isLoading = false;
/* 596 */					 return;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 602 */			 if (chunkposition != null) {
/* 603 */				 i = chunkposition.x;
/* 604 */				 k = chunkposition.z;
/*		 */			 } else {
/* 606 */				 System.out.println("Unable to find spawn biome");
/*		 */			 }
/*		 */ 
/* 609 */			 int l = 0;
/*		 */ 
/* 612 */			 while (!canSpawn(i, k)) {
/* 613 */				 i += random.nextInt(64) - random.nextInt(64);
/* 614 */				 k += random.nextInt(64) - random.nextInt(64);
/* 615 */				 l++;
/* 616 */				 if (l == 1000) {
/* 617 */					 break;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 621 */			 this.worldData.setSpawn(i, j, k);
/* 622 */			 this.isLoading = false;
/* 623 */			 if (worldsettings.c())
/* 624 */				 i();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void i()
/*		 */	 {
/* 630 */		 WorldGenBonusChest worldgenbonuschest = new WorldGenBonusChest(S, 10);
/*		 */ 
/* 632 */		 for (int i = 0; i < 10; i++) {
/* 633 */			 int j = this.worldData.c() + this.random.nextInt(6) - this.random.nextInt(6);
/* 634 */			 int k = this.worldData.e() + this.random.nextInt(6) - this.random.nextInt(6);
/* 635 */			 int l = h(j, k) + 1;
/*		 */ 
/* 637 */			 if (worldgenbonuschest.a(this, this.random, j, l, k))
/*		 */				 break;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public ChunkCoordinates getDimensionSpawn()
/*		 */	 {
/* 644 */		 return this.worldProvider.h();
/*		 */	 }
/*		 */ 
/*		 */	 public void save(boolean flag, IProgressUpdate iprogressupdate) throws ExceptionWorldConflict {
/* 648 */		 if (this.chunkProvider.canSave()) {
/* 649 */			 if (iprogressupdate != null) {
/* 650 */				 iprogressupdate.a("Saving level");
/*		 */			 }
/*		 */ 
/* 653 */			 a();
/* 654 */			 if (iprogressupdate != null) {
/* 655 */				 iprogressupdate.c("Saving chunks");
/*		 */			 }
/*		 */ 
/* 658 */			 this.chunkProvider.saveChunks(flag, iprogressupdate);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a() throws ExceptionWorldConflict {
/* 663 */		 B();
/* 664 */		 this.dataManager.saveWorldData(this.worldData, this.server.getServerConfigurationManager().q());
/* 665 */		 this.worldMaps.a();
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(Entity entity) {
/* 669 */		 super.a(entity);
/* 670 */		 this.entitiesById.a(entity.id, entity);
/* 671 */		 Entity[] aentity = entity.al();
/*		 */ 
/* 673 */		 if (aentity != null) {
/* 674 */			 Entity[] aentity1 = aentity;
/* 675 */			 int i = aentity.length;
/*		 */ 
/* 677 */			 for (int j = 0; j < i; j++) {
/* 678 */				 Entity entity1 = aentity1[j];
/*		 */ 
/* 680 */				 this.entitiesById.a(entity1.id, entity1);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(Entity entity) {
/* 686 */		 super.b(entity);
/* 687 */		 this.entitiesById.d(entity.id);
/* 688 */		 Entity[] aentity = entity.al();
/*		 */ 
/* 690 */		 if (aentity != null) {
/* 691 */			 Entity[] aentity1 = aentity;
/* 692 */			 int i = aentity.length;
/*		 */ 
/* 694 */			 for (int j = 0; j < i; j++) {
/* 695 */				 Entity entity1 = aentity1[j];
/*		 */ 
/* 697 */				 this.entitiesById.d(entity1.id);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Entity getEntity(int i) {
/* 703 */		 return (Entity)this.entitiesById.get(i);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean strikeLightning(Entity entity)
/*		 */	 {
/* 708 */		 LightningStrikeEvent lightning = new LightningStrikeEvent(getWorld(), (LightningStrike)entity.getBukkitEntity());
/* 709 */		 getServer().getPluginManager().callEvent(lightning);
/*		 */ 
/* 711 */		 if (lightning.isCancelled()) {
/* 712 */			 return false;
/*		 */		 }
/*		 */ 
/* 715 */		 if (super.strikeLightning(entity)) {
/* 716 */			 this.server.getServerConfigurationManager().sendPacketNearby(entity.locX, entity.locY, entity.locZ, 512.0D, this.dimension, new Packet71Weather(entity));
/*		 */ 
/* 718 */			 return true;
/*		 */		 }
/* 720 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void broadcastEntityEffect(Entity entity, byte b0)
/*		 */	 {
/* 725 */		 Packet38EntityStatus packet38entitystatus = new Packet38EntityStatus(entity.id, b0);
/*		 */ 
/* 727 */		 getTracker().sendPacketToEntity(entity, packet38entitystatus);
/*		 */	 }
/*		 */ 
/*		 */	 public Explosion createExplosion(Entity entity, double d0, double d1, double d2, float f, boolean flag)
/*		 */	 {
/* 732 */		 Explosion explosion = super.createExplosion(entity, d0, d1, d2, f, flag);
/*		 */ 
/* 734 */		 if (explosion.wasCanceled) {
/* 735 */			 return explosion;
/*		 */		 }
/*		 */ 
/* 744 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 746 */		 while (iterator.hasNext()) {
/* 747 */			 EntityHuman entityhuman = (EntityHuman)iterator.next();
/*		 */ 
/* 749 */			 if (entityhuman.e(d0, d1, d2) < 4096.0D) {
/* 750 */				 ((EntityPlayer)entityhuman).netServerHandler.sendPacket(new Packet60Explosion(d0, d1, d2, f, explosion.blocks, (Vec3D)explosion.b().get(entityhuman)));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 754 */		 return explosion;
/*		 */	 }
/*		 */ 
/* 758 */	 public void playNote(int i, int j, int k, int l, int i1, int j1) { NoteBlockData noteblockdata = new NoteBlockData(i, j, k, l, i1, j1);
/* 759 */		 Iterator iterator = this.Q[this.R].iterator();
/*		 */		 NoteBlockData noteblockdata1;
/*		 */		 do {
/* 764 */			 if (!iterator.hasNext()) {
/* 765 */				 this.Q[this.R].add(noteblockdata);
/* 766 */				 return;
/*		 */			 }
/*		 */ 
/* 769 */			 noteblockdata1 = (NoteBlockData)iterator.next();
/* 770 */		 }while (!noteblockdata1.equals(noteblockdata));
/*		 */	 }
/*		 */ 
/*		 */	 private void Q()
/*		 */	 {
/* 775 */		 while (!this.Q[this.R].isEmpty()) {
/* 776 */			 int i = this.R;
/*		 */ 
/* 778 */			 this.R ^= 1;
/* 779 */			 Iterator iterator = this.Q[i].iterator();
/*		 */ 
/* 781 */			 while (iterator.hasNext()) {
/* 782 */				 NoteBlockData noteblockdata = (NoteBlockData)iterator.next();
/*		 */ 
/* 784 */				 if (a(noteblockdata))
/*		 */				 {
/* 786 */					 this.server.getServerConfigurationManager().sendPacketNearby(noteblockdata.a(), noteblockdata.b(), noteblockdata.c(), 64.0D, this.dimension, new Packet54PlayNoteBlock(noteblockdata.a(), noteblockdata.b(), noteblockdata.c(), noteblockdata.f(), noteblockdata.d(), noteblockdata.e()));
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 790 */			 this.Q[i].clear();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(NoteBlockData noteblockdata) {
/* 795 */		 int i = getTypeId(noteblockdata.a(), noteblockdata.b(), noteblockdata.c());
/*		 */ 
/* 797 */		 if (i == noteblockdata.f()) {
/* 798 */			 Block.byId[i].b(this, noteblockdata.a(), noteblockdata.b(), noteblockdata.c(), noteblockdata.d(), noteblockdata.e());
/* 799 */			 return true;
/*		 */		 }
/* 801 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public void saveLevel()
/*		 */	 {
/* 806 */		 this.dataManager.a();
/*		 */	 }
/*		 */ 
/*		 */	 protected void l() {
/* 810 */		 boolean flag = J();
/*		 */ 
/* 812 */		 super.l();
/* 813 */		 if (flag != J())
/*		 */		 {
/* 815 */			 for (int i = 0; i < this.players.size(); i++)
/* 816 */				 if (((EntityPlayer)this.players.get(i)).world == this)
/* 817 */					 ((EntityPlayer)this.players.get(i)).netServerHandler.sendPacket(new Packet70Bed(flag ? 2 : 1, 0));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public MinecraftServer getMinecraftServer()
/*		 */	 {
/* 825 */		 return this.server;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityTracker getTracker() {
/* 829 */		 return this.tracker;
/*		 */	 }
/*		 */ 
/*		 */	 public void setTimeAndFixTicklists(long i) {
/* 833 */		 long j = i - this.worldData.getTime();
/*		 */		 NextTickListEntry nextticklistentry;
/* 837 */		 for (Iterator iterator = this.N.iterator(); iterator.hasNext(); nextticklistentry.e += j) {
/* 838 */			 nextticklistentry = (NextTickListEntry)iterator.next();
/*		 */		 }
/*		 */ 
/* 841 */		 Block[] ablock = Block.byId;
/* 842 */		 int k = ablock.length;
/*		 */ 
/* 844 */		 for (int l = 0; l < k; l++) {
/* 845 */			 Block block = ablock[l];
/*		 */ 
/* 847 */			 if (block != null) {
/* 848 */				 block.a(this, j, i);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 852 */		 setTime(i);
/*		 */	 }
/*		 */ 
/*		 */	 public PlayerManager getPlayerManager() {
/* 856 */		 return this.manager;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldServer
 * JD-Core Version:		0.6.0
 */