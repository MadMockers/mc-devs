package net.minecraft.server;

public class WorldData
{
	private long seed;
	private WorldType type = WorldType.NORMAL;
	private int spawnX;
	private int spawnY;
	private int spawnZ;
	private long time;
	private long lastPlayed;
	private long sizeOnDisk;
	private NBTTagCompound playerData;
	private int dimension;
	private String name;
	private int version;
	private boolean isRaining;
	private int rainTicks;
	private boolean isThundering;
	private int thunderTicks;
	private EnumGamemode gameType;
	private boolean useMapFeatures;
	private boolean hardcore;
	private boolean allowCommands;
	private boolean initialized;

	protected WorldData()
	{
	}

	public WorldData(NBTTagCompound paramNBTTagCompound)
	{
		this.seed = paramNBTTagCompound.getLong("RandomSeed");
		if (paramNBTTagCompound.hasKey("generatorName")) {
			String str = paramNBTTagCompound.getString("generatorName");
			this.type = WorldType.getType(str);
			if (this.type == null) {
				this.type = WorldType.NORMAL;
			} else if (this.type.e()) {
				int i = 0;
				if (paramNBTTagCompound.hasKey("generatorVersion")) {
					i = paramNBTTagCompound.getInt("generatorVersion");
				}
				this.type = this.type.a(i);
			}
		}
		this.gameType = EnumGamemode.a(paramNBTTagCompound.getInt("GameType"));
		if (paramNBTTagCompound.hasKey("MapFeatures"))
			this.useMapFeatures = paramNBTTagCompound.getBoolean("MapFeatures");
		else {
			this.useMapFeatures = true;
		}
		this.spawnX = paramNBTTagCompound.getInt("SpawnX");
		this.spawnY = paramNBTTagCompound.getInt("SpawnY");
		this.spawnZ = paramNBTTagCompound.getInt("SpawnZ");
		this.time = paramNBTTagCompound.getLong("Time");
		this.lastPlayed = paramNBTTagCompound.getLong("LastPlayed");
		this.sizeOnDisk = paramNBTTagCompound.getLong("SizeOnDisk");
		this.name = paramNBTTagCompound.getString("LevelName");
		this.version = paramNBTTagCompound.getInt("version");
		this.rainTicks = paramNBTTagCompound.getInt("rainTime");
		this.isRaining = paramNBTTagCompound.getBoolean("raining");
		this.thunderTicks = paramNBTTagCompound.getInt("thunderTime");
		this.isThundering = paramNBTTagCompound.getBoolean("thundering");
		this.hardcore = paramNBTTagCompound.getBoolean("hardcore");

		if (paramNBTTagCompound.hasKey("initialized"))
			this.initialized = paramNBTTagCompound.getBoolean("initialized");
		else {
			this.initialized = true;
		}

		if (paramNBTTagCompound.hasKey("allowCommands"))
			this.allowCommands = paramNBTTagCompound.getBoolean("allowCommands");
		else {
			this.allowCommands = (this.gameType == EnumGamemode.CREATIVE);
		}

		if (paramNBTTagCompound.hasKey("Player")) {
			this.playerData = paramNBTTagCompound.getCompound("Player");
			this.dimension = this.playerData.getInt("Dimension");
		}
	}

	public WorldData(WorldSettings paramWorldSettings, String paramString) {
		this.seed = paramWorldSettings.d();
		this.gameType = paramWorldSettings.e();
		this.useMapFeatures = paramWorldSettings.g();
		this.name = paramString;
		this.hardcore = paramWorldSettings.f();
		this.type = paramWorldSettings.h();
		this.allowCommands = paramWorldSettings.i();
		this.initialized = false;
	}

	public WorldData(WorldData paramWorldData) {
		this.seed = paramWorldData.seed;
		this.type = paramWorldData.type;
		this.gameType = paramWorldData.gameType;
		this.useMapFeatures = paramWorldData.useMapFeatures;
		this.spawnX = paramWorldData.spawnX;
		this.spawnY = paramWorldData.spawnY;
		this.spawnZ = paramWorldData.spawnZ;
		this.time = paramWorldData.time;
		this.lastPlayed = paramWorldData.lastPlayed;
		this.sizeOnDisk = paramWorldData.sizeOnDisk;
		this.playerData = paramWorldData.playerData;
		this.dimension = paramWorldData.dimension;
		this.name = paramWorldData.name;
		this.version = paramWorldData.version;
		this.rainTicks = paramWorldData.rainTicks;
		this.isRaining = paramWorldData.isRaining;
		this.thunderTicks = paramWorldData.thunderTicks;
		this.isThundering = paramWorldData.isThundering;
		this.hardcore = paramWorldData.hardcore;
		this.allowCommands = paramWorldData.allowCommands;
		this.initialized = paramWorldData.initialized;
	}

	public NBTTagCompound a() {
		NBTTagCompound localNBTTagCompound = new NBTTagCompound();

		a(localNBTTagCompound, this.playerData);

		return localNBTTagCompound;
	}

	public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
		NBTTagCompound localNBTTagCompound = new NBTTagCompound();
		a(localNBTTagCompound, paramNBTTagCompound);

		return localNBTTagCompound;
	}

	private void a(NBTTagCompound paramNBTTagCompound1, NBTTagCompound paramNBTTagCompound2) {
		paramNBTTagCompound1.setLong("RandomSeed", this.seed);
		paramNBTTagCompound1.setString("generatorName", this.type.name());
		paramNBTTagCompound1.setInt("generatorVersion", this.type.getVersion());
		paramNBTTagCompound1.setInt("GameType", this.gameType.a());
		paramNBTTagCompound1.setBoolean("MapFeatures", this.useMapFeatures);
		paramNBTTagCompound1.setInt("SpawnX", this.spawnX);
		paramNBTTagCompound1.setInt("SpawnY", this.spawnY);
		paramNBTTagCompound1.setInt("SpawnZ", this.spawnZ);
		paramNBTTagCompound1.setLong("Time", this.time);
		paramNBTTagCompound1.setLong("SizeOnDisk", this.sizeOnDisk);
		paramNBTTagCompound1.setLong("LastPlayed", System.currentTimeMillis());
		paramNBTTagCompound1.setString("LevelName", this.name);
		paramNBTTagCompound1.setInt("version", this.version);
		paramNBTTagCompound1.setInt("rainTime", this.rainTicks);
		paramNBTTagCompound1.setBoolean("raining", this.isRaining);
		paramNBTTagCompound1.setInt("thunderTime", this.thunderTicks);
		paramNBTTagCompound1.setBoolean("thundering", this.isThundering);
		paramNBTTagCompound1.setBoolean("hardcore", this.hardcore);
		paramNBTTagCompound1.setBoolean("allowCommands", this.allowCommands);
		paramNBTTagCompound1.setBoolean("initialized", this.initialized);

		if (paramNBTTagCompound2 != null)
			paramNBTTagCompound1.setCompound("Player", paramNBTTagCompound2);
	}

	public long getSeed()
	{
		return this.seed;
	}

	public int c() {
		return this.spawnX;
	}

	public int d() {
		return this.spawnY;
	}

	public int e() {
		return this.spawnZ;
	}

	public long getTime() {
		return this.time;
	}

	public NBTTagCompound h()
	{
		return this.playerData;
	}

	public int i() {
		return this.dimension;
	}

	public void b(long paramLong)
	{
		this.time = paramLong;
	}

	public void setSpawn(int paramInt1, int paramInt2, int paramInt3)
	{
		this.spawnX = paramInt1;
		this.spawnY = paramInt2;
		this.spawnZ = paramInt3;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public int k() {
		return this.version;
	}

	public void e(int paramInt) {
		this.version = paramInt;
	}

	public boolean isThundering()
	{
		return this.isThundering;
	}

	public void setThundering(boolean paramBoolean) {
		this.isThundering = paramBoolean;
	}

	public int getThunderDuration() {
		return this.thunderTicks;
	}

	public void setThunderDuration(int paramInt) {
		this.thunderTicks = paramInt;
	}

	public boolean hasStorm() {
		return this.isRaining;
	}

	public void setStorm(boolean paramBoolean) {
		this.isRaining = paramBoolean;
	}

	public int getWeatherDuration() {
		return this.rainTicks;
	}

	public void setWeatherDuration(int paramInt) {
		this.rainTicks = paramInt;
	}

	public EnumGamemode getGameType() {
		return this.gameType;
	}

	public boolean shouldGenerateMapFeatures() {
		return this.useMapFeatures;
	}

	public void setGameType(EnumGamemode paramEnumGamemode) {
		this.gameType = paramEnumGamemode;
	}

	public boolean isHardcore() {
		return this.hardcore;
	}

	public WorldType getType() {
		return this.type;
	}

	public void setType(WorldType paramWorldType) {
		this.type = paramWorldType;
	}

	public boolean allowCommands() {
		return this.allowCommands;
	}

	public boolean isInitialized()
	{
		return this.initialized;
	}

	public void d(boolean paramBoolean) {
		this.initialized = paramBoolean;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldData
 * JD-Core Version:		0.6.0
 */