/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class WorldData
/*		 */ {
/*		 */	 private long seed;
/*	11 */	 private WorldType type = WorldType.NORMAL;
/*		 */	 private int spawnX;
/*		 */	 private int spawnY;
/*		 */	 private int spawnZ;
/*		 */	 private long time;
/*		 */	 private long lastPlayed;
/*		 */	 private long sizeOnDisk;
/*		 */	 private NBTTagCompound playerData;
/*		 */	 private int dimension;
/*		 */	 private String name;
/*		 */	 private int version;
/*		 */	 private boolean isRaining;
/*		 */	 private int rainTicks;
/*		 */	 private boolean isThundering;
/*		 */	 private int thunderTicks;
/*		 */	 private EnumGamemode gameType;
/*		 */	 private boolean useMapFeatures;
/*		 */	 private boolean hardcore;
/*		 */	 private boolean allowCommands;
/*		 */	 private boolean initialized;
/*		 */ 
/*		 */	 protected WorldData()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public WorldData(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*	40 */		 this.seed = paramNBTTagCompound.getLong("RandomSeed");
/*	41 */		 if (paramNBTTagCompound.hasKey("generatorName")) {
/*	42 */			 String str = paramNBTTagCompound.getString("generatorName");
/*	43 */			 this.type = WorldType.getType(str);
/*	44 */			 if (this.type == null) {
/*	45 */				 this.type = WorldType.NORMAL;
/*	46 */			 } else if (this.type.e()) {
/*	47 */				 int i = 0;
/*	48 */				 if (paramNBTTagCompound.hasKey("generatorVersion")) {
/*	49 */					 i = paramNBTTagCompound.getInt("generatorVersion");
/*		 */				 }
/*	51 */				 this.type = this.type.a(i);
/*		 */			 }
/*		 */		 }
/*	54 */		 this.gameType = EnumGamemode.a(paramNBTTagCompound.getInt("GameType"));
/*	55 */		 if (paramNBTTagCompound.hasKey("MapFeatures"))
/*	56 */			 this.useMapFeatures = paramNBTTagCompound.getBoolean("MapFeatures");
/*		 */		 else {
/*	58 */			 this.useMapFeatures = true;
/*		 */		 }
/*	60 */		 this.spawnX = paramNBTTagCompound.getInt("SpawnX");
/*	61 */		 this.spawnY = paramNBTTagCompound.getInt("SpawnY");
/*	62 */		 this.spawnZ = paramNBTTagCompound.getInt("SpawnZ");
/*	63 */		 this.time = paramNBTTagCompound.getLong("Time");
/*	64 */		 this.lastPlayed = paramNBTTagCompound.getLong("LastPlayed");
/*	65 */		 this.sizeOnDisk = paramNBTTagCompound.getLong("SizeOnDisk");
/*	66 */		 this.name = paramNBTTagCompound.getString("LevelName");
/*	67 */		 this.version = paramNBTTagCompound.getInt("version");
/*	68 */		 this.rainTicks = paramNBTTagCompound.getInt("rainTime");
/*	69 */		 this.isRaining = paramNBTTagCompound.getBoolean("raining");
/*	70 */		 this.thunderTicks = paramNBTTagCompound.getInt("thunderTime");
/*	71 */		 this.isThundering = paramNBTTagCompound.getBoolean("thundering");
/*	72 */		 this.hardcore = paramNBTTagCompound.getBoolean("hardcore");
/*		 */ 
/*	74 */		 if (paramNBTTagCompound.hasKey("initialized"))
/*	75 */			 this.initialized = paramNBTTagCompound.getBoolean("initialized");
/*		 */		 else {
/*	77 */			 this.initialized = true;
/*		 */		 }
/*		 */ 
/*	80 */		 if (paramNBTTagCompound.hasKey("allowCommands"))
/*	81 */			 this.allowCommands = paramNBTTagCompound.getBoolean("allowCommands");
/*		 */		 else {
/*	83 */			 this.allowCommands = (this.gameType == EnumGamemode.CREATIVE);
/*		 */		 }
/*		 */ 
/*	86 */		 if (paramNBTTagCompound.hasKey("Player")) {
/*	87 */			 this.playerData = paramNBTTagCompound.getCompound("Player");
/*	88 */			 this.dimension = this.playerData.getInt("Dimension");
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public WorldData(WorldSettings paramWorldSettings, String paramString) {
/*	93 */		 this.seed = paramWorldSettings.d();
/*	94 */		 this.gameType = paramWorldSettings.e();
/*	95 */		 this.useMapFeatures = paramWorldSettings.g();
/*	96 */		 this.name = paramString;
/*	97 */		 this.hardcore = paramWorldSettings.f();
/*	98 */		 this.type = paramWorldSettings.h();
/*	99 */		 this.allowCommands = paramWorldSettings.i();
/* 100 */		 this.initialized = false;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldData(WorldData paramWorldData) {
/* 104 */		 this.seed = paramWorldData.seed;
/* 105 */		 this.type = paramWorldData.type;
/* 106 */		 this.gameType = paramWorldData.gameType;
/* 107 */		 this.useMapFeatures = paramWorldData.useMapFeatures;
/* 108 */		 this.spawnX = paramWorldData.spawnX;
/* 109 */		 this.spawnY = paramWorldData.spawnY;
/* 110 */		 this.spawnZ = paramWorldData.spawnZ;
/* 111 */		 this.time = paramWorldData.time;
/* 112 */		 this.lastPlayed = paramWorldData.lastPlayed;
/* 113 */		 this.sizeOnDisk = paramWorldData.sizeOnDisk;
/* 114 */		 this.playerData = paramWorldData.playerData;
/* 115 */		 this.dimension = paramWorldData.dimension;
/* 116 */		 this.name = paramWorldData.name;
/* 117 */		 this.version = paramWorldData.version;
/* 118 */		 this.rainTicks = paramWorldData.rainTicks;
/* 119 */		 this.isRaining = paramWorldData.isRaining;
/* 120 */		 this.thunderTicks = paramWorldData.thunderTicks;
/* 121 */		 this.isThundering = paramWorldData.isThundering;
/* 122 */		 this.hardcore = paramWorldData.hardcore;
/* 123 */		 this.allowCommands = paramWorldData.allowCommands;
/* 124 */		 this.initialized = paramWorldData.initialized;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound a() {
/* 128 */		 NBTTagCompound localNBTTagCompound = new NBTTagCompound();
/*		 */ 
/* 130 */		 a(localNBTTagCompound, this.playerData);
/*		 */ 
/* 132 */		 return localNBTTagCompound;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 136 */		 NBTTagCompound localNBTTagCompound = new NBTTagCompound();
/* 137 */		 a(localNBTTagCompound, paramNBTTagCompound);
/*		 */ 
/* 139 */		 return localNBTTagCompound;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(NBTTagCompound paramNBTTagCompound1, NBTTagCompound paramNBTTagCompound2) {
/* 143 */		 paramNBTTagCompound1.setLong("RandomSeed", this.seed);
/* 144 */		 paramNBTTagCompound1.setString("generatorName", this.type.name());
/* 145 */		 paramNBTTagCompound1.setInt("generatorVersion", this.type.getVersion());
/* 146 */		 paramNBTTagCompound1.setInt("GameType", this.gameType.a());
/* 147 */		 paramNBTTagCompound1.setBoolean("MapFeatures", this.useMapFeatures);
/* 148 */		 paramNBTTagCompound1.setInt("SpawnX", this.spawnX);
/* 149 */		 paramNBTTagCompound1.setInt("SpawnY", this.spawnY);
/* 150 */		 paramNBTTagCompound1.setInt("SpawnZ", this.spawnZ);
/* 151 */		 paramNBTTagCompound1.setLong("Time", this.time);
/* 152 */		 paramNBTTagCompound1.setLong("SizeOnDisk", this.sizeOnDisk);
/* 153 */		 paramNBTTagCompound1.setLong("LastPlayed", System.currentTimeMillis());
/* 154 */		 paramNBTTagCompound1.setString("LevelName", this.name);
/* 155 */		 paramNBTTagCompound1.setInt("version", this.version);
/* 156 */		 paramNBTTagCompound1.setInt("rainTime", this.rainTicks);
/* 157 */		 paramNBTTagCompound1.setBoolean("raining", this.isRaining);
/* 158 */		 paramNBTTagCompound1.setInt("thunderTime", this.thunderTicks);
/* 159 */		 paramNBTTagCompound1.setBoolean("thundering", this.isThundering);
/* 160 */		 paramNBTTagCompound1.setBoolean("hardcore", this.hardcore);
/* 161 */		 paramNBTTagCompound1.setBoolean("allowCommands", this.allowCommands);
/* 162 */		 paramNBTTagCompound1.setBoolean("initialized", this.initialized);
/*		 */ 
/* 164 */		 if (paramNBTTagCompound2 != null)
/* 165 */			 paramNBTTagCompound1.setCompound("Player", paramNBTTagCompound2);
/*		 */	 }
/*		 */ 
/*		 */	 public long getSeed()
/*		 */	 {
/* 170 */		 return this.seed;
/*		 */	 }
/*		 */ 
/*		 */	 public int c() {
/* 174 */		 return this.spawnX;
/*		 */	 }
/*		 */ 
/*		 */	 public int d() {
/* 178 */		 return this.spawnY;
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 182 */		 return this.spawnZ;
/*		 */	 }
/*		 */ 
/*		 */	 public long getTime() {
/* 186 */		 return this.time;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound h()
/*		 */	 {
/* 194 */		 return this.playerData;
/*		 */	 }
/*		 */ 
/*		 */	 public int i() {
/* 198 */		 return this.dimension;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(long paramLong)
/*		 */	 {
/* 218 */		 this.time = paramLong;
/*		 */	 }
/*		 */ 
/*		 */	 public void setSpawn(int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 234 */		 this.spawnX = paramInt1;
/* 235 */		 this.spawnY = paramInt2;
/* 236 */		 this.spawnZ = paramInt3;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 240 */		 return this.name;
/*		 */	 }
/*		 */ 
/*		 */	 public void setName(String paramString) {
/* 244 */		 this.name = paramString;
/*		 */	 }
/*		 */ 
/*		 */	 public int k() {
/* 248 */		 return this.version;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(int paramInt) {
/* 252 */		 this.version = paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isThundering()
/*		 */	 {
/* 260 */		 return this.isThundering;
/*		 */	 }
/*		 */ 
/*		 */	 public void setThundering(boolean paramBoolean) {
/* 264 */		 this.isThundering = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public int getThunderDuration() {
/* 268 */		 return this.thunderTicks;
/*		 */	 }
/*		 */ 
/*		 */	 public void setThunderDuration(int paramInt) {
/* 272 */		 this.thunderTicks = paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean hasStorm() {
/* 276 */		 return this.isRaining;
/*		 */	 }
/*		 */ 
/*		 */	 public void setStorm(boolean paramBoolean) {
/* 280 */		 this.isRaining = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public int getWeatherDuration() {
/* 284 */		 return this.rainTicks;
/*		 */	 }
/*		 */ 
/*		 */	 public void setWeatherDuration(int paramInt) {
/* 288 */		 this.rainTicks = paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public EnumGamemode getGameType() {
/* 292 */		 return this.gameType;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean shouldGenerateMapFeatures() {
/* 296 */		 return this.useMapFeatures;
/*		 */	 }
/*		 */ 
/*		 */	 public void setGameType(EnumGamemode paramEnumGamemode) {
/* 300 */		 this.gameType = paramEnumGamemode;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isHardcore() {
/* 304 */		 return this.hardcore;
/*		 */	 }
/*		 */ 
/*		 */	 public WorldType getType() {
/* 308 */		 return this.type;
/*		 */	 }
/*		 */ 
/*		 */	 public void setType(WorldType paramWorldType) {
/* 312 */		 this.type = paramWorldType;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean allowCommands() {
/* 316 */		 return this.allowCommands;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isInitialized()
/*		 */	 {
/* 324 */		 return this.initialized;
/*		 */	 }
/*		 */ 
/*		 */	 public void d(boolean paramBoolean) {
/* 328 */		 this.initialized = paramBoolean;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldData
 * JD-Core Version:		0.6.0
 */