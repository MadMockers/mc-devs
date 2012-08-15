package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.util.LongHash;
import org.bukkit.craftbukkit.util.LongHashset;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;

public abstract class World
	implements IBlockAccess
{
	public boolean e = false;
	public List entityList = new ArrayList();
	protected List g = new ArrayList();
	public List tileEntityList = new ArrayList();
	private List a = new ArrayList();
	private List b = new ArrayList();
	public List players = new ArrayList();
	public List j = new ArrayList();
	private long c = 16777215L;
	public int k = 0;
	protected int l = new Random().nextInt();
	protected final int m = 1013904223;
	protected float n;
	protected float o;
	protected float p;
	protected float q;
	protected int r = 0;
	public int s = 0;
	public boolean suppressPhysics = false;
	public int difficulty;
	public Random random = new Random();
	public WorldProvider worldProvider;
	protected List x = new ArrayList();
	public IChunkProvider chunkProvider;
	protected final IDataManager dataManager;
	public WorldData worldData;
	public boolean isLoading;
	public WorldMapCollection worldMaps;
	public final VillageCollection villages = new VillageCollection(this);
	protected final VillageSiege siegeManager = new VillageSiege(this);
	public final MethodProfiler methodProfiler;
	private ArrayList d = new ArrayList();
	private boolean L;
	public boolean allowMonsters = true;
	public boolean allowAnimals = true;
	protected LongHashset chunkTickList = new LongHashset();
	public long ticksPerAnimalSpawns;
	public long ticksPerMonsterSpawns;
	private int M;
	int[] J;
	private List N;
	public boolean isStatic;
	private final CraftWorld world;
	public boolean pvpMode;
	public boolean keepSpawnInMemory = true;
	public ChunkGenerator generator;
	Chunk lastChunkAccessed;
	int lastXAccessed = -2147483648;
	int lastZAccessed = -2147483648;
	final Object chunkLock = new Object();

	public BiomeBase getBiome(int i, int j)
	{
		if (isLoaded(i, 0, j)) {
			Chunk chunk = getChunkAtWorldCoords(i, j);

			if (chunk != null) {
				return chunk.a(i & 0xF, j & 0xF, this.worldProvider.c);
			}
		}

		return this.worldProvider.c.getBiome(i, j);
	}

	public WorldChunkManager getWorldChunkManager() {
		return this.worldProvider.c;
	}

	public CraftWorld getWorld()
	{
		return this.world;
	}

	public CraftServer getServer() {
		return (CraftServer)Bukkit.getServer();
	}

	public World(IDataManager idatamanager, String s, WorldSettings worldsettings, WorldProvider worldprovider, MethodProfiler methodprofiler, ChunkGenerator gen, World.Environment env)
	{
		this.generator = gen;
		this.world = new CraftWorld((WorldServer)this, gen, env);
		this.ticksPerAnimalSpawns = getServer().getTicksPerAnimalSpawns();
		this.ticksPerMonsterSpawns = getServer().getTicksPerMonsterSpawns();

		this.M = this.random.nextInt(12000);
		this.J = new int[32768];
		this.N = new ArrayList();
		this.isStatic = false;
		this.dataManager = idatamanager;
		this.methodProfiler = methodprofiler;
		this.worldMaps = new WorldMapCollection(idatamanager);
		this.worldData = idatamanager.getWorldData();
		if (worldprovider != null)
			this.worldProvider = worldprovider;
		else if ((this.worldData != null) && (this.worldData.i() != 0))
			this.worldProvider = WorldProvider.byDimension(this.worldData.i());
		else {
			this.worldProvider = WorldProvider.byDimension(0);
		}

		if (this.worldData == null)
			this.worldData = new WorldData(worldsettings, s);
		else {
			this.worldData.setName(s);
		}

		this.worldProvider.a(this);
		this.chunkProvider = h();
		if (!this.worldData.isInitialized()) {
			a(worldsettings);
			this.worldData.d(true);
		}

		v();
		a();

		getServer().addWorld(this.world);
	}
	protected abstract IChunkProvider h();

	protected void a(WorldSettings worldsettings) {
		this.worldData.d(true);
	}

	public int b(int i, int j)
	{
		for (int k = 63; !isEmpty(i, k + 1, j); k++);
		return getTypeId(i, k, j);
	}

	public int getTypeId(int i, int j, int k) {
		return (i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000) ? getChunkAt(i >> 4, k >> 4).getTypeId(i & 0xF, j, k & 0xF) : j >= 256 ? 0 : j < 0 ? 0 : 0;
	}

	public int b(int i, int j, int k) {
		return (i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000) ? getChunkAt(i >> 4, k >> 4).b(i & 0xF, j, k & 0xF) : j >= 256 ? 0 : j < 0 ? 0 : 0;
	}

	public boolean isEmpty(int i, int j, int k) {
		return getTypeId(i, j, k) == 0;
	}

	public boolean isTileEntity(int i, int j, int k) {
		int l = getTypeId(i, j, k);

		return (Block.byId[l] != null) && (Block.byId[l].s());
	}

	public boolean isLoaded(int i, int j, int k) {
		return (j >= 0) && (j < 256) ? isChunkLoaded(i >> 4, k >> 4) : false;
	}

	public boolean areChunksLoaded(int i, int j, int k, int l) {
		return c(i - l, j - l, k - l, i + l, j + l, k + l);
	}

	public boolean c(int i, int j, int k, int l, int i1, int j1) {
		if ((i1 >= 0) && (j < 256)) {
			i >>= 4;
			k >>= 4;
			l >>= 4;
			j1 >>= 4;

			for (int k1 = i; k1 <= l; k1++) {
				for (int l1 = k; l1 <= j1; l1++) {
					if (!isChunkLoaded(k1, l1)) {
						return false;
					}
				}
			}

			return true;
		}
		return false;
	}

	protected boolean isChunkLoaded(int i, int j)
	{
		return this.chunkProvider.isChunkLoaded(i, j);
	}

	public Chunk getChunkAtWorldCoords(int i, int j) {
		return getChunkAt(i >> 4, j >> 4);
	}

	public Chunk getChunkAt(int i, int j)
	{
		Chunk result = null;
		synchronized (this.chunkLock) {
			if ((this.lastChunkAccessed == null) || (this.lastXAccessed != i) || (this.lastZAccessed != j)) {
				this.lastXAccessed = i;
				this.lastZAccessed = j;
				this.lastChunkAccessed = this.chunkProvider.getOrCreateChunk(i, j);
			}
			result = this.lastChunkAccessed;
		}
		return result;
	}

	public boolean setRawTypeIdAndData(int i, int j, int k, int l, int i1)
	{
		return setRawTypeIdAndData(i, j, k, l, i1, true);
	}

	public boolean setRawTypeIdAndData(int i, int j, int k, int l, int i1, boolean flag) {
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			if (j < 0)
				return false;
			if (j >= 256) {
				return false;
			}
			Chunk chunk = getChunkAt(i >> 4, k >> 4);
			boolean flag1 = chunk.a(i & 0xF, j, k & 0xF, l, i1);

			x(i, j, k);

			if ((flag) && (flag1) && ((this.isStatic) || (chunk.seenByPlayer))) {
				notify(i, j, k);
			}

			return flag1;
		}

		return false;
	}

	public boolean setRawTypeId(int i, int j, int k, int l)
	{
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			if (j < 0)
				return false;
			if (j >= 256) {
				return false;
			}
			Chunk chunk = getChunkAt(i >> 4, k >> 4);
			boolean flag = chunk.a(i & 0xF, j, k & 0xF, l);

			x(i, j, k);

			if ((flag) && ((this.isStatic) || (chunk.seenByPlayer))) {
				notify(i, j, k);
			}

			return flag;
		}

		return false;
	}

	public Material getMaterial(int i, int j, int k)
	{
		int l = getTypeId(i, j, k);

		return l == 0 ? Material.AIR : Block.byId[l].material;
	}

	public int getData(int i, int j, int k) {
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			if (j < 0)
				return 0;
			if (j >= 256) {
				return 0;
			}
			Chunk chunk = getChunkAt(i >> 4, k >> 4);

			i &= 15;
			k &= 15;
			return chunk.getData(i, j, k);
		}

		return 0;
	}

	public void setData(int i, int j, int k, int l)
	{
		if (setRawData(i, j, k, l))
			update(i, j, k, getTypeId(i, j, k));
	}

	public boolean setRawData(int i, int j, int k, int l)
	{
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			if (j < 0)
				return false;
			if (j >= 256) {
				return false;
			}
			Chunk chunk = getChunkAt(i >> 4, k >> 4);
			int i1 = i & 0xF;
			int j1 = k & 0xF;
			boolean flag = chunk.b(i1, j, j1, l);

			if ((flag) && ((this.isStatic) || ((chunk.seenByPlayer) && (Block.r[(chunk.getTypeId(i1, j, j1) & 0xFFF)] != 0)))) {
				notify(i, j, k);
			}

			return flag;
		}

		return false;
	}

	public boolean setTypeId(int i, int j, int k, int l)
	{
		int old = getTypeId(i, j, k);
		if (setRawTypeId(i, j, k, l)) {
			update(i, j, k, l == 0 ? old : l);

			return true;
		}
		return false;
	}

	public boolean setTypeIdAndData(int i, int j, int k, int l, int i1)
	{
		if (setRawTypeIdAndData(i, j, k, l, i1)) {
			update(i, j, k, l);
			return true;
		}
		return false;
	}

	public void notify(int i, int j, int k)
	{
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(i, j, k);
		}
	}

	public void update(int i, int j, int k, int l) {
		applyPhysics(i, j, k, l);
	}

	public void g(int i, int j, int k, int l)
	{
		if (k > l) {
			int i1 = l;
			l = k;
			k = i1;
		}

		if (!this.worldProvider.e) {
			for (int i1 = k; i1 <= l; i1++) {
				c(EnumSkyBlock.SKY, i, i1, j);
			}
		}

		d(i, k, j, i, l, j);
	}

	public void i(int i, int j, int k) {
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(i, j, k, i, j, k);
		}
	}

	public void d(int i, int j, int k, int l, int i1, int j1) {
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(i, j, k, l, i1, j1);
		}
	}

	public void applyPhysics(int i, int j, int k, int l) {
		m(i - 1, j, k, l);
		m(i + 1, j, k, l);
		m(i, j - 1, k, l);
		m(i, j + 1, k, l);
		m(i, j, k - 1, l);
		m(i, j, k + 1, l);
	}

	private void m(int i, int j, int k, int l) {
		if ((!this.suppressPhysics) && (!this.isStatic)) {
			Block block = Block.byId[getTypeId(i, j, k)];

			if (block != null)
			{
				CraftWorld world = ((WorldServer)this).getWorld();
				if (world != null) {
					BlockPhysicsEvent event = new BlockPhysicsEvent(world.getBlockAt(i, j, k), l);
					getServer().getPluginManager().callEvent(event);

					if (event.isCancelled()) {
						return;
					}

				}

				block.doPhysics(this, i, j, k, l);
			}
		}
	}

	public boolean j(int i, int j, int k) {
		return getChunkAt(i >> 4, k >> 4).d(i & 0xF, j, k & 0xF);
	}

	public int k(int i, int j, int k) {
		if (j < 0) {
			return 0;
		}
		if (j >= 256) {
			j = 255;
		}

		return getChunkAt(i >> 4, k >> 4).c(i & 0xF, j, k & 0xF, 0);
	}

	public int getLightLevel(int i, int j, int k)
	{
		return a(i, j, k, true);
	}

	public int a(int i, int j, int k, boolean flag) {
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			if (flag) {
				int l = getTypeId(i, j, k);

				if ((l == Block.STEP.id) || (l == Block.WOOD_STEP.id) || (l == Block.SOIL.id) || (l == Block.COBBLESTONE_STAIRS.id) || (l == Block.WOOD_STAIRS.id)) {
					int i1 = a(i, j + 1, k, false);
					int j1 = a(i + 1, j, k, false);
					int k1 = a(i - 1, j, k, false);
					int l1 = a(i, j, k + 1, false);
					int i2 = a(i, j, k - 1, false);

					if (j1 > i1) {
						i1 = j1;
					}

					if (k1 > i1) {
						i1 = k1;
					}

					if (l1 > i1) {
						i1 = l1;
					}

					if (i2 > i1) {
						i1 = i2;
					}

					return i1;
				}
			}

			if (j < 0) {
				return 0;
			}
			if (j >= 256) {
				j = 255;
			}

			Chunk chunk = getChunkAt(i >> 4, k >> 4);

			i &= 15;
			k &= 15;
			return chunk.c(i, j, k, this.k);
		}

		return 15;
	}

	public int getHighestBlockYAt(int i, int j)
	{
		if ((i >= -30000000) && (j >= -30000000) && (i < 30000000) && (j < 30000000)) {
			if (!isChunkLoaded(i >> 4, j >> 4)) {
				return 0;
			}
			Chunk chunk = getChunkAt(i >> 4, j >> 4);

			return chunk.b(i & 0xF, j & 0xF);
		}

		return 0;
	}

	public int b(EnumSkyBlock enumskyblock, int i, int j, int k)
	{
		if (j < 0) {
			j = 0;
		}

		if (j >= 256) {
			j = 255;
		}

		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			int l = i >> 4;
			int i1 = k >> 4;

			if (!isChunkLoaded(l, i1)) {
				return enumskyblock.c;
			}
			Chunk chunk = getChunkAt(l, i1);

			return chunk.getBrightness(enumskyblock, i & 0xF, j, k & 0xF);
		}

		return enumskyblock.c;
	}

	public void b(EnumSkyBlock enumskyblock, int i, int j, int k, int l)
	{
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000) && 
			(j >= 0) && 
			(j < 256) && 
			(isChunkLoaded(i >> 4, k >> 4))) {
			Chunk chunk = getChunkAt(i >> 4, k >> 4);

			chunk.a(enumskyblock, i & 0xF, j, k & 0xF, l);
			Iterator iterator = this.x.iterator();

			while (iterator.hasNext()) {
				IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

				iworldaccess.b(i, j, k);
			}
		}
	}

	public void n(int i, int j, int k)
	{
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.b(i, j, k);
		}
	}

	public float o(int i, int j, int k) {
		return this.worldProvider.f[getLightLevel(i, j, k)];
	}

	public boolean r() {
		return this.k < 4;
	}

	public MovingObjectPosition a(Vec3D vec3d, Vec3D vec3d1) {
		return rayTrace(vec3d, vec3d1, false, false);
	}

	public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag) {
		return rayTrace(vec3d, vec3d1, flag, false);
	}

	public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag, boolean flag1) {
		if ((!Double.isNaN(vec3d.a)) && (!Double.isNaN(vec3d.b)) && (!Double.isNaN(vec3d.c)))
			if ((!Double.isNaN(vec3d1.a)) && (!Double.isNaN(vec3d1.b)) && (!Double.isNaN(vec3d1.c))) {
				int i = MathHelper.floor(vec3d1.a);
				int j = MathHelper.floor(vec3d1.b);
				int k = MathHelper.floor(vec3d1.c);
				int l = MathHelper.floor(vec3d.a);
				int i1 = MathHelper.floor(vec3d.b);
				int j1 = MathHelper.floor(vec3d.c);
				int k1 = getTypeId(l, i1, j1);
				int l1 = getData(l, i1, j1);
				Block block = Block.byId[k1];

				if (((!flag1) || (block == null) || (block.e(this, l, i1, j1) != null)) && (k1 > 0) && (block.a(l1, flag))) {
					MovingObjectPosition movingobjectposition = block.a(this, l, i1, j1, vec3d, vec3d1);

					if (movingobjectposition != null) {
						return movingobjectposition;
					}
				}

				k1 = 200;

				if (k1-- >= 0) {
					if ((Double.isNaN(vec3d.a)) || (Double.isNaN(vec3d.b)) || (Double.isNaN(vec3d.c))) {
						return null;
					}

					if ((l == i) && (i1 == j) && (j1 == k)) {
						return null;
					}

					boolean flag2 = true;
					boolean flag3 = true;
					boolean flag4 = true;
					double d0 = 999.0D;
					double d1 = 999.0D;
					double d2 = 999.0D;

					if (i > l)
						d0 = l + 1.0D;
					else if (i < l)
						d0 = l + 0.0D;
					else {
						flag2 = false;
					}

					if (j > i1)
						d1 = i1 + 1.0D;
					else if (j < i1)
						d1 = i1 + 0.0D;
					else {
						flag3 = false;
					}

					if (k > j1)
						d2 = j1 + 1.0D;
					else if (k < j1)
						d2 = j1 + 0.0D;
					else {
						flag4 = false;
					}

					double d3 = 999.0D;
					double d4 = 999.0D;
					double d5 = 999.0D;
					double d6 = vec3d1.a - vec3d.a;
					double d7 = vec3d1.b - vec3d.b;
					double d8 = vec3d1.c - vec3d.c;

					if (flag2) {
						d3 = (d0 - vec3d.a) / d6;
					}

					if (flag3) {
						d4 = (d1 - vec3d.b) / d7;
					}

					if (flag4) {
						d5 = (d2 - vec3d.c) / d8;
					}

					boolean flag5 = false;

					if ((d3 < d4) && (d3 < d5))
					{
						byte b0;
						byte b0;
						if (i > l)
							b0 = 4;
						else {
							b0 = 5;
						}

						vec3d.a = d0;
						vec3d.b += d7 * d3;
						vec3d.c += d8 * d3;
					} else if (d4 < d5)
					{
						byte b0;
						byte b0;
						if (j > i1)
							b0 = 0;
						else {
							b0 = 1;
						}

						vec3d.a += d6 * d4;
						vec3d.b = d1;
						vec3d.c += d8 * d4;
					}
					else
					{
						byte b0;
						byte b0;
						if (k > j1)
							b0 = 2;
						else {
							b0 = 3;
						}

						vec3d.a += d6 * d5;
						vec3d.b += d7 * d5;
						vec3d.c = d2;
					}

					vec3d2 = Vec3D.a().create(vec3d.a, vec3d.b, vec3d.c);
				}
			}
	}

	public void makeSound(Entity entity, String s, float f, float f1)
	{
		if ((entity != null) && (s != null)) {
			Iterator iterator = this.x.iterator();

			while (iterator.hasNext()) {
				IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

				iworldaccess.a(s, entity.locX, entity.locY - entity.height, entity.locZ, f, f1);
			}
		}
	}

	public void makeSound(double d0, double d1, double d2, String s, float f, float f1) {
		if (s != null) {
			Iterator iterator = this.x.iterator();

			while (iterator.hasNext()) {
				IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

				iworldaccess.a(s, d0, d1, d2, f, f1);
			}
		}
	}

	public void a(String s, int i, int j, int k) {
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(s, i, j, k);
		}
	}

	public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(s, d0, d1, d2, d3, d4, d5);
		}
	}

	public boolean strikeLightning(Entity entity) {
		this.j.add(entity);
		return true;
	}

	public boolean addEntity(Entity entity)
	{
		return addEntity(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
	}

	public boolean addEntity(Entity entity, CreatureSpawnEvent.SpawnReason spawnReason) {
		if (entity == null) return false;

		int i = MathHelper.floor(entity.locX / 16.0D);
		int j = MathHelper.floor(entity.locZ / 16.0D);
		boolean flag = false;

		if ((entity instanceof EntityHuman)) {
			flag = true;
		}

		Cancellable event = null;
		if (((entity instanceof EntityLiving)) && (!(entity instanceof EntityPlayer))) {
			boolean isAnimal = ((entity instanceof EntityAnimal)) || ((entity instanceof EntityWaterAnimal)) || ((entity instanceof EntityGolem));
			boolean isMonster = ((entity instanceof EntityMonster)) || ((entity instanceof EntityGhast)) || ((entity instanceof EntitySlime));

			if ((spawnReason != CreatureSpawnEvent.SpawnReason.CUSTOM) && (
				((isAnimal) && (!this.allowAnimals)) || ((isMonster) && (!this.allowMonsters)))) {
				entity.dead = true;
				return false;
			}

			event = CraftEventFactory.callCreatureSpawnEvent((EntityLiving)entity, spawnReason);
		} else if ((entity instanceof EntityItem)) {
			event = CraftEventFactory.callItemSpawnEvent((EntityItem)entity);
		} else if ((entity.getBukkitEntity() instanceof Projectile))
		{
			event = CraftEventFactory.callProjectileLaunchEvent(entity);
		}

		if ((event != null) && ((event.isCancelled()) || (entity.dead))) {
			entity.dead = true;
			return false;
		}

		if ((!flag) && (!isChunkLoaded(i, j))) {
			entity.dead = true;
			return false;
		}
		if ((entity instanceof EntityHuman)) {
			EntityHuman entityhuman = (EntityHuman)entity;

			this.players.add(entityhuman);
			everyoneSleeping();
		}

		getChunkAt(i, j).a(entity);
		this.entityList.add(entity);
		a(entity);
		return true;
	}

	protected void a(Entity entity)
	{
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(entity);
		}
	}

	protected void b(Entity entity) {
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.b(entity);
		}

		entity.valid = false;
	}

	public void kill(Entity entity) {
		if (entity.passenger != null) {
			entity.passenger.mount((Entity)null);
		}

		if (entity.vehicle != null) {
			entity.mount((Entity)null);
		}

		entity.die();
		if ((entity instanceof EntityHuman)) {
			this.players.remove(entity);
			everyoneSleeping();
		}
	}

	public void removeEntity(Entity entity) {
		entity.die();
		if ((entity instanceof EntityHuman)) {
			this.players.remove(entity);
			everyoneSleeping();
		}

		int i = entity.ah;
		int j = entity.aj;

		if ((entity.ag) && (isChunkLoaded(i, j))) {
			getChunkAt(i, j).b(entity);
		}

		this.entityList.remove(entity);
		b(entity);
	}

	public void addIWorldAccess(IWorldAccess iworldaccess) {
		this.x.add(iworldaccess);
	}

	public List getCubes(Entity entity, AxisAlignedBB axisalignedbb) {
		this.d.clear();
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = i1; l1 < j1; l1++) {
				if (isLoaded(k1, 64, l1)) {
					for (int i2 = k - 1; i2 < l; i2++) {
						Block block = Block.byId[getTypeId(k1, i2, l1)];

						if (block != null) {
							block.a(this, k1, i2, l1, axisalignedbb, this.d, entity);
						}
					}
				}
			}
		}

		double d0 = 0.25D;
		List list = getEntities(entity, axisalignedbb.grow(d0, d0, d0));
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Entity entity1 = (Entity)iterator.next();
			AxisAlignedBB axisalignedbb1 = entity1.E();

			if ((axisalignedbb1 != null) && (axisalignedbb1.a(axisalignedbb))) {
				this.d.add(axisalignedbb1);
			}

			axisalignedbb1 = entity.g(entity1);
			if ((axisalignedbb1 != null) && (axisalignedbb1.a(axisalignedbb))) {
				this.d.add(axisalignedbb1);
			}
		}

		return this.d;
	}

	public List a(AxisAlignedBB axisalignedbb) {
		this.d.clear();
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = i1; l1 < j1; l1++) {
				if (isLoaded(k1, 64, l1)) {
					for (int i2 = k - 1; i2 < l; i2++) {
						Block block = Block.byId[getTypeId(k1, i2, l1)];

						if (block != null) {
							block.a(this, k1, i2, l1, axisalignedbb, this.d, (Entity)null);
						}
					}
				}
			}
		}

		return this.d;
	}

	public int a(float f) {
		float f1 = c(f);
		float f2 = 1.0F - (MathHelper.cos(f1 * 3.141593F * 2.0F) * 2.0F + 0.5F);

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		f2 = 1.0F - f2;
		f2 = (float)(f2 * (1.0D - j(f) * 5.0F / 16.0D));
		f2 = (float)(f2 * (1.0D - i(f) * 5.0F / 16.0D));
		f2 = 1.0F - f2;
		return (int)(f2 * 11.0F);
	}

	public float c(float f) {
		return this.worldProvider.a(this.worldData.getTime(), f);
	}

	public int g(int i, int j) {
		return getChunkAtWorldCoords(i, j).d(i & 0xF, j & 0xF);
	}

	public int h(int i, int j) {
		Chunk chunk = getChunkAtWorldCoords(i, j);
		int k = chunk.h() + 15;

		i &= 15;

		for (j &= 15; k > 0; k--) {
			int l = chunk.getTypeId(i, k, j);

			if ((l != 0) && (Block.byId[l].material.isSolid()) && (Block.byId[l].material != Material.LEAVES)) {
				return k + 1;
			}
		}

		return -1;
	}

	public void a(int i, int j, int k, int l, int i1)
	{
	}

	public void b(int i, int j, int k, int l, int i1)
	{
	}

	public void tickEntities()
	{
		for (int i = 0; i < this.j.size(); i++) {
			Entity entity = (Entity)this.j.get(i);

			ChunkProviderServer chunkProviderServer = ((WorldServer)entity.world).chunkProviderServer;
			if (chunkProviderServer.unloadQueue.containsKey(MathHelper.floor(entity.locX) >> 4, MathHelper.floor(entity.locZ) >> 4))
			{
				continue;
			}
			if (entity == null)
			{
				continue;
			}
			entity.h_();
			if (entity.dead) {
				this.j.remove(i--);
			}

		}

		this.entityList.removeAll(this.g);
		Iterator iterator = this.g.iterator();

		while (iterator.hasNext()) {
			Entity entity = (Entity)iterator.next();
			int j = entity.ah;
			int k = entity.aj;
			if ((entity.ag) && (isChunkLoaded(j, k))) {
				getChunkAt(j, k).b(entity);
			}
		}

		iterator = this.g.iterator();

		while (iterator.hasNext()) {
			Entity entity = (Entity)iterator.next();
			b(entity);
		}

		this.g.clear();

		for (i = 0; i < this.entityList.size(); i++) {
			Entity entity = (Entity)this.entityList.get(i);

			ChunkProviderServer chunkProviderServer = ((WorldServer)entity.world).chunkProviderServer;
			if (chunkProviderServer.unloadQueue.containsKey(MathHelper.floor(entity.locX) >> 4, MathHelper.floor(entity.locZ) >> 4))
			{
				continue;
			}

			if (entity.vehicle != null) {
				if ((!entity.vehicle.dead) && (entity.vehicle.passenger == entity))
				{
					continue;
				}
				entity.vehicle.passenger = null;
				entity.vehicle = null;
			}

			if (!entity.dead) {
				playerJoinedWorld(entity);
			}

			if (entity.dead) {
				int j = entity.ah;
				int k = entity.aj;
				if ((entity.ag) && (isChunkLoaded(j, k))) {
					getChunkAt(j, k).b(entity);
				}

				this.entityList.remove(i--);
				b(entity);
			}

		}

		this.L = true;
		iterator = this.tileEntityList.iterator();

		while (iterator.hasNext()) {
			TileEntity tileentity = (TileEntity)iterator.next();

			ChunkProviderServer chunkProviderServer = ((WorldServer)tileentity.world).chunkProviderServer;
			if (chunkProviderServer.unloadQueue.containsKey(tileentity.x >> 4, tileentity.z >> 4))
			{
				continue;
			}

			if ((!tileentity.p()) && (tileentity.m()) && (isLoaded(tileentity.x, tileentity.y, tileentity.z))) {
				tileentity.g();
			}

			if (tileentity.p()) {
				iterator.remove();
				if (isChunkLoaded(tileentity.x >> 4, tileentity.z >> 4)) {
					Chunk chunk = getChunkAt(tileentity.x >> 4, tileentity.z >> 4);

					if (chunk != null) {
						chunk.f(tileentity.x & 0xF, tileentity.y, tileentity.z & 0xF);
					}
				}
			}
		}

		this.L = false;
		if (!this.b.isEmpty()) {
			this.tileEntityList.removeAll(this.b);
			this.b.clear();
		}

		if (!this.a.isEmpty()) {
			Iterator iterator1 = this.a.iterator();

			while (iterator1.hasNext()) {
				TileEntity tileentity1 = (TileEntity)iterator1.next();

				if (!tileentity1.p())
				{
					if (isChunkLoaded(tileentity1.x >> 4, tileentity1.z >> 4)) {
						Chunk chunk1 = getChunkAt(tileentity1.x >> 4, tileentity1.z >> 4);

						if (chunk1 != null) {
							chunk1.a(tileentity1.x & 0xF, tileentity1.y, tileentity1.z & 0xF, tileentity1);

							if (!this.tileEntityList.contains(tileentity1)) {
								this.tileEntityList.add(tileentity1);
							}
						}

					}

					notify(tileentity1.x, tileentity1.y, tileentity1.z);
				}
			}

			this.a.clear();
		}
	}

	public void a(Collection collection)
	{
		if (this.L)
			this.a.addAll(collection);
		else
			this.tileEntityList.addAll(collection);
	}

	public void playerJoinedWorld(Entity entity)
	{
		entityJoinedWorld(entity, true);
	}

	public void entityJoinedWorld(Entity entity, boolean flag) {
		int i = MathHelper.floor(entity.locX);
		int j = MathHelper.floor(entity.locZ);
		byte b0 = 32;

		if ((!flag) || (c(i - b0, 0, j - b0, i + b0, 0, j + b0))) {
			entity.S = entity.locX;
			entity.T = entity.locY;
			entity.U = entity.locZ;
			entity.lastYaw = entity.yaw;
			entity.lastPitch = entity.pitch;
			if ((flag) && (entity.ag)) {
				if (entity.vehicle != null)
					entity.U();
				else {
					entity.h_();
				}

			}

			if ((Double.isNaN(entity.locX)) || (Double.isInfinite(entity.locX))) {
				entity.locX = entity.S;
			}

			if ((Double.isNaN(entity.locY)) || (Double.isInfinite(entity.locY))) {
				entity.locY = entity.T;
			}

			if ((Double.isNaN(entity.locZ)) || (Double.isInfinite(entity.locZ))) {
				entity.locZ = entity.U;
			}

			if ((Double.isNaN(entity.pitch)) || (Double.isInfinite(entity.pitch))) {
				entity.pitch = entity.lastPitch;
			}

			if ((Double.isNaN(entity.yaw)) || (Double.isInfinite(entity.yaw))) {
				entity.yaw = entity.lastYaw;
			}

			int k = MathHelper.floor(entity.locX / 16.0D);
			int l = MathHelper.floor(entity.locY / 16.0D);
			int i1 = MathHelper.floor(entity.locZ / 16.0D);

			if ((!entity.ag) || (entity.ah != k) || (entity.ai != l) || (entity.aj != i1)) {
				if ((entity.ag) && (isChunkLoaded(entity.ah, entity.aj))) {
					getChunkAt(entity.ah, entity.aj).a(entity, entity.ai);
				}

				if (isChunkLoaded(k, i1)) {
					entity.ag = true;
					getChunkAt(k, i1).a(entity);
				} else {
					entity.ag = false;
				}

			}

			if ((flag) && (entity.ag) && (entity.passenger != null))
				if ((!entity.passenger.dead) && (entity.passenger.vehicle == entity)) {
					playerJoinedWorld(entity.passenger);
				} else {
					entity.passenger.vehicle = null;
					entity.passenger = null;
				}
		}
	}

	public boolean b(AxisAlignedBB axisalignedbb)
	{
		return a(axisalignedbb, (Entity)null);
	}

	public boolean a(AxisAlignedBB axisalignedbb, Entity entity) { List list = getEntities((Entity)null, axisalignedbb);
		Iterator iterator = list.iterator();
		Entity entity1;
		do {
			if (!iterator.hasNext()) {
				return true;
			}

			entity1 = (Entity)iterator.next();
		}while ((entity1.dead) || (!entity1.m) || (entity1 == entity));

		return false; }

	public boolean c(AxisAlignedBB axisalignedbb)
	{
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		if (axisalignedbb.a < 0.0D) {
			i--;
		}

		if (axisalignedbb.b < 0.0D) {
			k--;
		}

		if (axisalignedbb.c < 0.0D) {
			i1--;
		}

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = k; l1 < l; l1++) {
				for (int i2 = i1; i2 < j1; i2++) {
					Block block = Block.byId[getTypeId(k1, l1, i2)];

					if (block != null) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean containsLiquid(AxisAlignedBB axisalignedbb) {
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		if (axisalignedbb.a < 0.0D) {
			i--;
		}

		if (axisalignedbb.b < 0.0D) {
			k--;
		}

		if (axisalignedbb.c < 0.0D) {
			i1--;
		}

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = k; l1 < l; l1++) {
				for (int i2 = i1; i2 < j1; i2++) {
					Block block = Block.byId[getTypeId(k1, l1, i2)];

					if ((block != null) && (block.material.isLiquid())) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean e(AxisAlignedBB axisalignedbb) {
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		if (c(i, k, i1, j, l, j1)) {
			for (int k1 = i; k1 < j; k1++) {
				for (int l1 = k; l1 < l; l1++) {
					for (int i2 = i1; i2 < j1; i2++) {
						int j2 = getTypeId(k1, l1, i2);

						if ((j2 == Block.FIRE.id) || (j2 == Block.LAVA.id) || (j2 == Block.STATIONARY_LAVA.id)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public boolean a(AxisAlignedBB axisalignedbb, Material material, Entity entity) {
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		if (!c(i, k, i1, j, l, j1)) {
			return false;
		}
		boolean flag = false;
		Vec3D vec3d = Vec3D.a().create(0.0D, 0.0D, 0.0D);

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = k; l1 < l; l1++) {
				for (int i2 = i1; i2 < j1; i2++) {
					Block block = Block.byId[getTypeId(k1, l1, i2)];

					if ((block != null) && (block.material == material)) {
						double d0 = l1 + 1 - BlockFluids.d(getData(k1, l1, i2));

						if (l >= d0) {
							flag = true;
							block.a(this, k1, l1, i2, entity, vec3d);
						}
					}
				}
			}
		}

		if (vec3d.c() > 0.0D) {
			vec3d = vec3d.b();
			double d1 = 0.014D;

			entity.motX += vec3d.a * d1;
			entity.motY += vec3d.b * d1;
			entity.motZ += vec3d.c * d1;
		}

		return flag;
	}

	public boolean a(AxisAlignedBB axisalignedbb, Material material)
	{
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = k; l1 < l; l1++) {
				for (int i2 = i1; i2 < j1; i2++) {
					Block block = Block.byId[getTypeId(k1, l1, i2)];

					if ((block != null) && (block.material == material)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean b(AxisAlignedBB axisalignedbb, Material material) {
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.d + 1.0D);
		int k = MathHelper.floor(axisalignedbb.b);
		int l = MathHelper.floor(axisalignedbb.e + 1.0D);
		int i1 = MathHelper.floor(axisalignedbb.c);
		int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

		for (int k1 = i; k1 < j; k1++) {
			for (int l1 = k; l1 < l; l1++) {
				for (int i2 = i1; i2 < j1; i2++) {
					Block block = Block.byId[getTypeId(k1, l1, i2)];

					if ((block != null) && (block.material == material)) {
						int j2 = getData(k1, l1, i2);
						double d0 = l1 + 1;

						if (j2 < 8) {
							d0 = l1 + 1 - j2 / 8.0D;
						}

						if (d0 >= axisalignedbb.b) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public Explosion explode(Entity entity, double d0, double d1, double d2, float f) {
		return createExplosion(entity, d0, d1, d2, f, false);
	}

	public Explosion createExplosion(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
		Explosion explosion = new Explosion(this, entity, d0, d1, d2, f);

		explosion.a = flag;
		explosion.a();
		explosion.a(true);
		return explosion;
	}

	public float a(Vec3D vec3d, AxisAlignedBB axisalignedbb) {
		double d0 = 1.0D / ((axisalignedbb.d - axisalignedbb.a) * 2.0D + 1.0D);
		double d1 = 1.0D / ((axisalignedbb.e - axisalignedbb.b) * 2.0D + 1.0D);
		double d2 = 1.0D / ((axisalignedbb.f - axisalignedbb.c) * 2.0D + 1.0D);
		int i = 0;
		int j = 0;

		for (float f = 0.0F; f <= 1.0F; f = (float)(f + d0)) {
			for (float f1 = 0.0F; f1 <= 1.0F; f1 = (float)(f1 + d1)) {
				for (float f2 = 0.0F; f2 <= 1.0F; f2 = (float)(f2 + d2)) {
					double d3 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * f;
					double d4 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * f1;
					double d5 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * f2;

					if (a(Vec3D.a().create(d3, d4, d5), vec3d) == null) {
						i++;
					}

					j++;
				}
			}
		}

		return i / j;
	}

	public boolean douseFire(EntityHuman entityhuman, int i, int j, int k, int l) {
		if (l == 0) {
			j--;
		}

		if (l == 1) {
			j++;
		}

		if (l == 2) {
			k--;
		}

		if (l == 3) {
			k++;
		}

		if (l == 4) {
			i--;
		}

		if (l == 5) {
			i++;
		}

		if (getTypeId(i, j, k) == Block.FIRE.id) {
			a(entityhuman, 1004, i, j, k, 0);
			setTypeId(i, j, k, 0);
			return true;
		}
		return false;
	}

	public TileEntity getTileEntity(int i, int j, int k)
	{
		if (j >= 256) {
			return null;
		}
		Chunk chunk = getChunkAt(i >> 4, k >> 4);

		if (chunk == null) {
			return null;
		}
		TileEntity tileentity = chunk.e(i & 0xF, j, k & 0xF);

		if (tileentity == null) {
			Iterator iterator = this.a.iterator();

			while (iterator.hasNext()) {
				TileEntity tileentity1 = (TileEntity)iterator.next();

				if ((!tileentity1.p()) && (tileentity1.x == i) && (tileentity1.y == j) && (tileentity1.z == k)) {
					tileentity = tileentity1;
					break;
				}
			}
		}

		return tileentity;
	}

	public void setTileEntity(int i, int j, int k, TileEntity tileentity)
	{
		if ((tileentity != null) && (!tileentity.p()))
			if (this.L) {
				tileentity.x = i;
				tileentity.y = j;
				tileentity.z = k;
				this.a.add(tileentity);
			} else {
				this.tileEntityList.add(tileentity);
				Chunk chunk = getChunkAt(i >> 4, k >> 4);

				if (chunk != null)
					chunk.a(i & 0xF, j, k & 0xF, tileentity);
			}
	}

	public void q(int i, int j, int k)
	{
		TileEntity tileentity = getTileEntity(i, j, k);

		if ((tileentity != null) && (this.L)) {
			tileentity.j();
			this.a.remove(tileentity);
		} else {
			if (tileentity != null) {
				this.a.remove(tileentity);
				this.tileEntityList.remove(tileentity);
			}

			Chunk chunk = getChunkAt(i >> 4, k >> 4);

			if (chunk != null)
				chunk.f(i & 0xF, j, k & 0xF);
		}
	}

	public void a(TileEntity tileentity)
	{
		this.b.add(tileentity);
	}

	public boolean r(int i, int j, int k) {
		Block block = Block.byId[getTypeId(i, j, k)];

		return block == null ? false : block.d();
	}

	public boolean s(int i, int j, int k) {
		return Block.i(getTypeId(i, j, k));
	}

	public boolean t(int i, int j, int k) {
		Block block = Block.byId[getTypeId(i, j, k)];

		return block != null;
	}

	public boolean b(int i, int j, int k, boolean flag) {
		if ((i >= -30000000) && (k >= -30000000) && (i < 30000000) && (k < 30000000)) {
			Chunk chunk = this.chunkProvider.getOrCreateChunk(i >> 4, k >> 4);

			if ((chunk != null) && (!chunk.isEmpty())) {
				Block block = Block.byId[getTypeId(i, j, k)];

				return block != null;
			}
			return flag;
		}

		return flag;
	}

	public void v()
	{
		int i = a(1.0F);

		if (i != this.k)
			this.k = i;
	}

	public void setSpawnFlags(boolean flag, boolean flag1)
	{
		this.allowMonsters = flag;
		this.allowAnimals = flag1;
	}

	public void doTick() {
		l();
	}

	private void a() {
		if (this.worldData.hasStorm()) {
			this.o = 1.0F;
			if (this.worldData.isThundering())
				this.q = 1.0F;
		}
	}

	protected void l()
	{
		if (!this.worldProvider.e) {
			if (this.r > 0) {
				this.r -= 1;
			}

			int i = this.worldData.getThunderDuration();

			if (i <= 0) {
				if (this.worldData.isThundering())
					this.worldData.setThunderDuration(this.random.nextInt(12000) + 3600);
				else
					this.worldData.setThunderDuration(this.random.nextInt(168000) + 12000);
			}
			else {
				i--;
				this.worldData.setThunderDuration(i);
				if (i <= 0)
				{
					ThunderChangeEvent thunder = new ThunderChangeEvent(getWorld(), !this.worldData.isThundering());
					getServer().getPluginManager().callEvent(thunder);
					if (!thunder.isCancelled()) {
						this.worldData.setThundering(!this.worldData.isThundering());
					}
				}

			}

			int j = this.worldData.getWeatherDuration();

			if (j <= 0) {
				if (this.worldData.hasStorm())
					this.worldData.setWeatherDuration(this.random.nextInt(12000) + 12000);
				else
					this.worldData.setWeatherDuration(this.random.nextInt(168000) + 12000);
			}
			else {
				j--;
				this.worldData.setWeatherDuration(j);
				if (j <= 0)
				{
					WeatherChangeEvent weather = new WeatherChangeEvent(getWorld(), !this.worldData.hasStorm());
					getServer().getPluginManager().callEvent(weather);

					if (!weather.isCancelled()) {
						this.worldData.setStorm(!this.worldData.hasStorm());
					}
				}

			}

			this.n = this.o;
			if (this.worldData.hasStorm())
				this.o = (float)(this.o + 0.01D);
			else {
				this.o = (float)(this.o - 0.01D);
			}

			if (this.o < 0.0F) {
				this.o = 0.0F;
			}

			if (this.o > 1.0F) {
				this.o = 1.0F;
			}

			this.p = this.q;
			if (this.worldData.isThundering())
				this.q = (float)(this.q + 0.01D);
			else {
				this.q = (float)(this.q - 0.01D);
			}

			if (this.q < 0.0F) {
				this.q = 0.0F;
			}

			if (this.q > 1.0F)
				this.q = 1.0F;
		}
	}

	public void w()
	{
		this.worldData.setWeatherDuration(1);
	}

	protected void x()
	{
		for (int i = 0; i < this.players.size(); i++) {
			EntityHuman entityhuman = (EntityHuman)this.players.get(i);
			int j = MathHelper.floor(entityhuman.locX / 16.0D);
			int k = MathHelper.floor(entityhuman.locZ / 16.0D);
			byte b0 = 7;

			for (int l = -b0; l <= b0; l++) {
				for (int i1 = -b0; i1 <= b0; i1++)
				{
					ChunkProviderServer chunkProviderServer = ((WorldServer)entityhuman.world).chunkProviderServer;
					if (chunkProviderServer.unloadQueue.containsKey(l + j, i1 + k))
					{
						continue;
					}

					this.chunkTickList.add(LongHash.toLong(l + j, i1 + k));
				}
			}

		}

		if (this.M > 0) {
			this.M -= 1;
		}

		if (!this.players.isEmpty()) {
			i = this.random.nextInt(this.players.size());
			EntityHuman entityhuman = (EntityHuman)this.players.get(i);
			int j = MathHelper.floor(entityhuman.locX) + this.random.nextInt(11) - 5;
			int k = MathHelper.floor(entityhuman.locY) + this.random.nextInt(11) - 5;
			int j1 = MathHelper.floor(entityhuman.locZ) + this.random.nextInt(11) - 5;

			x(j, k, j1);
		}
	}

	protected void a(int i, int j, Chunk chunk)
	{
		if (this.M == 0) {
			this.l = (this.l * 3 + 1013904223);
			int k = this.l >> 2;
			int l = k & 0xF;
			int i1 = k >> 8 & 0xF;
			int j1 = k >> 16 & 0xFF;
			int k1 = chunk.getTypeId(l, j1, i1);

			l += i;
			i1 += j;
			if ((k1 == 0) && (k(l, j1, i1) <= this.random.nextInt(8)) && (b(EnumSkyBlock.SKY, l, j1, i1) <= 0)) {
				EntityHuman entityhuman = findNearbyPlayer(l + 0.5D, j1 + 0.5D, i1 + 0.5D, 8.0D);

				if ((entityhuman != null) && (entityhuman.e(l + 0.5D, j1 + 0.5D, i1 + 0.5D) > 4.0D)) {
					makeSound(l + 0.5D, j1 + 0.5D, i1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.random.nextFloat() * 0.2F);
					this.M = (this.random.nextInt(12000) + 6000);
				}
			}

		}

		chunk.o();
	}

	protected void g() {
		x();
	}

	public boolean u(int i, int j, int k) {
		return c(i, j, k, false);
	}

	public boolean v(int i, int j, int k) {
		return c(i, j, k, true);
	}

	public boolean c(int i, int j, int k, boolean flag) {
		BiomeBase biomebase = getBiome(i, k);
		float f = biomebase.j();

		if (f > 0.15F) {
			return false;
		}
		if ((j >= 0) && (j < 256) && (b(EnumSkyBlock.BLOCK, i, j, k) < 10)) {
			int l = getTypeId(i, j, k);

			if (((l == Block.STATIONARY_WATER.id) || (l == Block.WATER.id)) && (getData(i, j, k) == 0)) {
				if (!flag) {
					return true;
				}

				boolean flag1 = true;

				if ((flag1) && (getMaterial(i - 1, j, k) != Material.WATER)) {
					flag1 = false;
				}

				if ((flag1) && (getMaterial(i + 1, j, k) != Material.WATER)) {
					flag1 = false;
				}

				if ((flag1) && (getMaterial(i, j, k - 1) != Material.WATER)) {
					flag1 = false;
				}

				if ((flag1) && (getMaterial(i, j, k + 1) != Material.WATER)) {
					flag1 = false;
				}

				if (!flag1) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean w(int i, int j, int k)
	{
		BiomeBase biomebase = getBiome(i, k);
		float f = biomebase.j();

		if (f > 0.15F) {
			return false;
		}
		if ((j >= 0) && (j < 256) && (b(EnumSkyBlock.BLOCK, i, j, k) < 10)) {
			int l = getTypeId(i, j - 1, k);
			int i1 = getTypeId(i, j, k);

			if ((i1 == 0) && (Block.SNOW.canPlace(this, i, j, k)) && (l != 0) && (l != Block.ICE.id) && (Block.byId[l].material.isSolid())) {
				return true;
			}
		}

		return false;
	}

	public void x(int i, int j, int k)
	{
		if (!this.worldProvider.e) {
			c(EnumSkyBlock.SKY, i, j, k);
		}

		c(EnumSkyBlock.BLOCK, i, j, k);
	}

	private int a(int i, int j, int k, int l, int i1, int j1) {
		int k1 = 0;

		if (j(j, k, l)) {
			k1 = 15;
		} else {
			if (j1 == 0) {
				j1 = 1;
			}

			int l1 = b(EnumSkyBlock.SKY, j - 1, k, l) - j1;
			int i2 = b(EnumSkyBlock.SKY, j + 1, k, l) - j1;
			int j2 = b(EnumSkyBlock.SKY, j, k - 1, l) - j1;
			int k2 = b(EnumSkyBlock.SKY, j, k + 1, l) - j1;
			int l2 = b(EnumSkyBlock.SKY, j, k, l - 1) - j1;
			int i3 = b(EnumSkyBlock.SKY, j, k, l + 1) - j1;

			if (l1 > k1) {
				k1 = l1;
			}

			if (i2 > k1) {
				k1 = i2;
			}

			if (j2 > k1) {
				k1 = j2;
			}

			if (k2 > k1) {
				k1 = k2;
			}

			if (l2 > k1) {
				k1 = l2;
			}

			if (i3 > k1) {
				k1 = i3;
			}
		}

		return k1;
	}

	private int f(int i, int j, int k, int l, int i1, int j1) {
		int k1 = Block.lightEmission[i1];
		int l1 = b(EnumSkyBlock.BLOCK, j - 1, k, l) - j1;
		int i2 = b(EnumSkyBlock.BLOCK, j + 1, k, l) - j1;
		int j2 = b(EnumSkyBlock.BLOCK, j, k - 1, l) - j1;
		int k2 = b(EnumSkyBlock.BLOCK, j, k + 1, l) - j1;
		int l2 = b(EnumSkyBlock.BLOCK, j, k, l - 1) - j1;
		int i3 = b(EnumSkyBlock.BLOCK, j, k, l + 1) - j1;

		if (l1 > k1) {
			k1 = l1;
		}

		if (i2 > k1) {
			k1 = i2;
		}

		if (j2 > k1) {
			k1 = j2;
		}

		if (k2 > k1) {
			k1 = k2;
		}

		if (l2 > k1) {
			k1 = l2;
		}

		if (i3 > k1) {
			k1 = i3;
		}

		return k1;
	}

	public void c(EnumSkyBlock enumskyblock, int i, int j, int k) {
		if (areChunksLoaded(i, j, k, 17)) {
			int l = 0;
			int i1 = 0;

			int j1 = b(enumskyblock, i, j, k);
			boolean flag = false;
			int k1 = getTypeId(i, j, k);
			int l1 = b(i, j, k);

			if (l1 == 0) {
				l1 = 1;
			}

			boolean flag1 = false;
			int i2;
			int i2;
			if (enumskyblock == EnumSkyBlock.SKY)
				i2 = a(j1, i, j, k, k1, l1);
			else {
				i2 = f(j1, i, j, k, k1, l1);
			}

			if (i2 > j1) {
				this.J[(i1++)] = 133152;
			} else if (i2 < j1) {
				if (enumskyblock != EnumSkyBlock.BLOCK);
				this.J[(i1++)] = (133152 + (j1 << 18));

				while (l < i1) {
					k1 = this.J[(l++)];
					l1 = (k1 & 0x3F) - 32 + i;
					i2 = (k1 >> 6 & 0x3F) - 32 + j;
					int j2 = (k1 >> 12 & 0x3F) - 32 + k;
					int k2 = k1 >> 18 & 0xF;
					int l2 = b(enumskyblock, l1, i2, j2);
					if (l2 == k2) {
						b(enumskyblock, l1, i2, j2, 0);
						if (k2 > 0) {
							int i3 = l1 - i;
							int k3 = i2 - j;
							int j3 = j2 - k;
							if (i3 < 0) {
								i3 = -i3;
							}

							if (k3 < 0) {
								k3 = -k3;
							}

							if (j3 < 0) {
								j3 = -j3;
							}

							if (i3 + k3 + j3 < 17) {
								for (int i4 = 0; i4 < 6; i4++) {
									int l3 = i4 % 2 * 2 - 1;
									int j4 = l1 + i4 / 2 % 3 / 2 * l3;
									int k4 = i2 + (i4 / 2 + 1) % 3 / 2 * l3;
									int l4 = j2 + (i4 / 2 + 2) % 3 / 2 * l3;

									l2 = b(enumskyblock, j4, k4, l4);
									int i5 = Block.lightBlock[getTypeId(j4, k4, l4)];

									if (i5 == 0) {
										i5 = 1;
									}

									if ((l2 == k2 - i5) && (i1 < this.J.length)) {
										this.J[(i1++)] = (j4 - i + 32 + (k4 - j + 32 << 6) + (l4 - k + 32 << 12) + (k2 - i5 << 18));
									}
								}
							}
						}
					}
				}

				l = 0;
			}

			while (l < i1) {
				k1 = this.J[(l++)];
				l1 = (k1 & 0x3F) - 32 + i;
				i2 = (k1 >> 6 & 0x3F) - 32 + j;
				int j2 = (k1 >> 12 & 0x3F) - 32 + k;
				int k2 = b(enumskyblock, l1, i2, j2);
				int l2 = getTypeId(l1, i2, j2);
				int i3 = Block.lightBlock[l2];
				if (i3 == 0) {
					i3 = 1;
				}

				boolean flag2 = false;
				int k3;
				int k3;
				if (enumskyblock == EnumSkyBlock.SKY)
					k3 = a(k2, l1, i2, j2, l2, i3);
				else {
					k3 = f(k2, l1, i2, j2, l2, i3);
				}

				if (k3 != k2) {
					b(enumskyblock, l1, i2, j2, k3);
					if (k3 > k2) {
						int j3 = l1 - i;
						int i4 = i2 - j;
						int l3 = j2 - k;
						if (j3 < 0) {
							j3 = -j3;
						}

						if (i4 < 0) {
							i4 = -i4;
						}

						if (l3 < 0) {
							l3 = -l3;
						}

						if ((j3 + i4 + l3 < 17) && (i1 < this.J.length - 6)) {
							if (b(enumskyblock, l1 - 1, i2, j2) < k3) {
								this.J[(i1++)] = (l1 - 1 - i + 32 + (i2 - j + 32 << 6) + (j2 - k + 32 << 12));
							}

							if (b(enumskyblock, l1 + 1, i2, j2) < k3) {
								this.J[(i1++)] = (l1 + 1 - i + 32 + (i2 - j + 32 << 6) + (j2 - k + 32 << 12));
							}

							if (b(enumskyblock, l1, i2 - 1, j2) < k3) {
								this.J[(i1++)] = (l1 - i + 32 + (i2 - 1 - j + 32 << 6) + (j2 - k + 32 << 12));
							}

							if (b(enumskyblock, l1, i2 + 1, j2) < k3) {
								this.J[(i1++)] = (l1 - i + 32 + (i2 + 1 - j + 32 << 6) + (j2 - k + 32 << 12));
							}

							if (b(enumskyblock, l1, i2, j2 - 1) < k3) {
								this.J[(i1++)] = (l1 - i + 32 + (i2 - j + 32 << 6) + (j2 - 1 - k + 32 << 12));
							}

							if (b(enumskyblock, l1, i2, j2 + 1) < k3)
								this.J[(i1++)] = (l1 - i + 32 + (i2 - j + 32 << 6) + (j2 + 1 - k + 32 << 12));
						}
					}
				}
			}
		}
	}

	public boolean a(boolean flag)
	{
		return false;
	}

	public List a(Chunk chunk, boolean flag) {
		return null;
	}

	public List getEntities(Entity entity, AxisAlignedBB axisalignedbb) {
		this.N.clear();
		int i = MathHelper.floor((axisalignedbb.a - 2.0D) / 16.0D);
		int j = MathHelper.floor((axisalignedbb.d + 2.0D) / 16.0D);
		int k = MathHelper.floor((axisalignedbb.c - 2.0D) / 16.0D);
		int l = MathHelper.floor((axisalignedbb.f + 2.0D) / 16.0D);

		for (int i1 = i; i1 <= j; i1++) {
			for (int j1 = k; j1 <= l; j1++) {
				if (isChunkLoaded(i1, j1)) {
					getChunkAt(i1, j1).a(entity, axisalignedbb, this.N);
				}
			}
		}

		return this.N;
	}

	public List a(Class oclass, AxisAlignedBB axisalignedbb) {
		int i = MathHelper.floor((axisalignedbb.a - 2.0D) / 16.0D);
		int j = MathHelper.floor((axisalignedbb.d + 2.0D) / 16.0D);
		int k = MathHelper.floor((axisalignedbb.c - 2.0D) / 16.0D);
		int l = MathHelper.floor((axisalignedbb.f + 2.0D) / 16.0D);
		ArrayList arraylist = new ArrayList();

		for (int i1 = i; i1 <= j; i1++) {
			for (int j1 = k; j1 <= l; j1++) {
				if (isChunkLoaded(i1, j1)) {
					getChunkAt(i1, j1).a(oclass, axisalignedbb, arraylist);
				}
			}
		}

		return arraylist;
	}

	public Entity a(Class oclass, AxisAlignedBB axisalignedbb, Entity entity) {
		List list = a(oclass, axisalignedbb);
		Entity entity1 = null;
		double d0 = 1.7976931348623157E+308D;
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Entity entity2 = (Entity)iterator.next();

			if (entity2 != entity) {
				double d1 = entity.e(entity2);

				if (d1 <= d0) {
					entity1 = entity2;
					d0 = d1;
				}
			}
		}

		return entity1;
	}

	public void b(int i, int j, int k, TileEntity tileentity) {
		if (isLoaded(i, j, k))
			getChunkAtWorldCoords(i, k).e();
	}

	public int a(Class oclass)
	{
		int i = 0;

		for (int j = 0; j < this.entityList.size(); j++) {
			Entity entity = (Entity)this.entityList.get(j);

			if (oclass.isAssignableFrom(entity.getClass())) {
				i++;
			}
		}

		return i;
	}

	public void a(List list)
	{
		Entity entity = null;
		for (int i = 0; i < list.size(); i++) {
			entity = (Entity)list.get(i);
			if (entity == null) {
				continue;
			}
			this.entityList.add(entity);

			a((Entity)list.get(i));
		}
	}

	public void b(List list) {
		this.g.addAll(list);
	}

	public boolean mayPlace(int i, int j, int k, int l, boolean flag, int i1, Entity entity) {
		int j1 = getTypeId(j, k, l);
		Block block = Block.byId[j1];
		Block block1 = Block.byId[i];
		AxisAlignedBB axisalignedbb = block1.e(this, j, k, l);

		if (flag)
			axisalignedbb = null;
		boolean defaultReturn;
		boolean defaultReturn;
		if ((axisalignedbb != null) && (!a(axisalignedbb, entity))) {
			defaultReturn = false;
		} else {
			if ((block != null) && ((block == Block.WATER) || (block == Block.STATIONARY_WATER) || (block == Block.LAVA) || (block == Block.STATIONARY_LAVA) || (block == Block.FIRE) || (block.material.isReplaceable()))) {
				block = null;
			}

			defaultReturn = (i > 0) && (block == null) && (block1.canPlace(this, j, k, l, i1));
		}

		BlockCanBuildEvent event = new BlockCanBuildEvent(getWorld().getBlockAt(j, k, l), i, defaultReturn);
		getServer().getPluginManager().callEvent(event);

		return event.isBuildable();
	}

	public PathEntity findPath(Entity entity, Entity entity1, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3)
	{
		int i = MathHelper.floor(entity.locX);
		int j = MathHelper.floor(entity.locY + 1.0D);
		int k = MathHelper.floor(entity.locZ);
		int l = (int)(f + 16.0F);
		int i1 = i - l;
		int j1 = j - l;
		int k1 = k - l;
		int l1 = i + l;
		int i2 = j + l;
		int j2 = k + l;
		ChunkCache chunkcache = new ChunkCache(this, i1, j1, k1, l1, i2, j2);
		PathEntity pathentity = new Pathfinder(chunkcache, flag, flag1, flag2, flag3).a(entity, entity1, f);

		return pathentity;
	}

	public PathEntity a(Entity entity, int i, int j, int k, float f, boolean flag, boolean flag1, boolean flag2, boolean flag3)
	{
		int l = MathHelper.floor(entity.locX);
		int i1 = MathHelper.floor(entity.locY);
		int j1 = MathHelper.floor(entity.locZ);
		int k1 = (int)(f + 8.0F);
		int l1 = l - k1;
		int i2 = i1 - k1;
		int j2 = j1 - k1;
		int k2 = l + k1;
		int l2 = i1 + k1;
		int i3 = j1 + k1;
		ChunkCache chunkcache = new ChunkCache(this, l1, i2, j2, k2, l2, i3);
		PathEntity pathentity = new Pathfinder(chunkcache, flag, flag1, flag2, flag3).a(entity, i, j, k, f);

		return pathentity;
	}

	public boolean isBlockFacePowered(int i, int j, int k, int l) {
		int i1 = getTypeId(i, j, k);

		return i1 == 0 ? false : Block.byId[i1].c(this, i, j, k, l);
	}

	public boolean isBlockPowered(int i, int j, int k) {
		return isBlockFacePowered(i - 1, j, k, 4) ? true : isBlockFacePowered(i, j, k + 1, 3) ? true : isBlockFacePowered(i, j, k - 1, 2) ? true : isBlockFacePowered(i, j + 1, k, 1) ? true : isBlockFacePowered(i, j - 1, k, 0) ? true : isBlockFacePowered(i + 1, j, k, 5);
	}

	public boolean isBlockFaceIndirectlyPowered(int i, int j, int k, int l) {
		if (s(i, j, k)) {
			return isBlockPowered(i, j, k);
		}
		int i1 = getTypeId(i, j, k);

		return i1 == 0 ? false : Block.byId[i1].a(this, i, j, k, l);
	}

	public boolean isBlockIndirectlyPowered(int i, int j, int k)
	{
		return isBlockFaceIndirectlyPowered(i - 1, j, k, 4) ? true : isBlockFaceIndirectlyPowered(i, j, k + 1, 3) ? true : isBlockFaceIndirectlyPowered(i, j, k - 1, 2) ? true : isBlockFaceIndirectlyPowered(i, j + 1, k, 1) ? true : isBlockFaceIndirectlyPowered(i, j - 1, k, 0) ? true : isBlockFaceIndirectlyPowered(i + 1, j, k, 5);
	}

	public EntityHuman findNearbyPlayer(Entity entity, double d0) {
		return findNearbyPlayer(entity.locX, entity.locY, entity.locZ, d0);
	}

	public EntityHuman findNearbyPlayer(double d0, double d1, double d2, double d3) {
		double d4 = -1.0D;
		EntityHuman entityhuman = null;

		for (int i = 0; i < this.players.size(); i++) {
			EntityHuman entityhuman1 = (EntityHuman)this.players.get(i);

			if ((entityhuman1 == null) || (entityhuman1.dead))
			{
				continue;
			}
			double d5 = entityhuman1.e(d0, d1, d2);

			if (((d3 < 0.0D) || (d5 < d3 * d3)) && ((d4 == -1.0D) || (d5 < d4))) {
				d4 = d5;
				entityhuman = entityhuman1;
			}
		}

		return entityhuman;
	}

	public EntityHuman findNearbyVulnerablePlayer(Entity entity, double d0) {
		return findNearbyVulnerablePlayer(entity.locX, entity.locY, entity.locZ, d0);
	}

	public EntityHuman findNearbyVulnerablePlayer(double d0, double d1, double d2, double d3) {
		double d4 = -1.0D;
		EntityHuman entityhuman = null;

		for (int i = 0; i < this.players.size(); i++) {
			EntityHuman entityhuman1 = (EntityHuman)this.players.get(i);

			if ((entityhuman1 == null) || (entityhuman1.dead))
			{
				continue;
			}

			if (!entityhuman1.abilities.isInvulnerable) {
				double d5 = entityhuman1.e(d0, d1, d2);

				if (((d3 < 0.0D) || (d5 < d3 * d3)) && ((d4 == -1.0D) || (d5 < d4))) {
					d4 = d5;
					entityhuman = entityhuman1;
				}
			}
		}

		return entityhuman;
	}

	public EntityHuman a(String s) {
		for (int i = 0; i < this.players.size(); i++) {
			if (s.equals(((EntityHuman)this.players.get(i)).name)) {
				return (EntityHuman)this.players.get(i);
			}
		}

		return null;
	}

	public void B() throws ExceptionWorldConflict {
		this.dataManager.checkSession();
	}

	public void setTime(long i) {
		this.worldData.b(i);
	}

	public long getSeed() {
		return this.worldData.getSeed();
	}

	public long getTime() {
		return this.worldData.getTime();
	}

	public ChunkCoordinates getSpawn() {
		return new ChunkCoordinates(this.worldData.c(), this.worldData.d(), this.worldData.e());
	}

	public boolean a(EntityHuman entityhuman, int i, int j, int k) {
		return true;
	}
	public void broadcastEntityEffect(Entity entity, byte b0) {
	}

	public IChunkProvider F() {
		return this.chunkProvider;
	}

	public void playNote(int i, int j, int k, int l, int i1, int j1) {
		if (l > 0)
			Block.byId[l].b(this, i, j, k, i1, j1);
	}

	public IDataManager getDataManager()
	{
		return this.dataManager;
	}

	public WorldData getWorldData() {
		return this.worldData;
	}

	public void everyoneSleeping()
	{
	}

	public void checkSleepStatus()
	{
		if (!this.isStatic)
			everyoneSleeping();
	}

	public float i(float f)
	{
		return (this.p + (this.q - this.p) * f) * j(f);
	}

	public float j(float f) {
		return this.n + (this.o - this.n) * f;
	}

	public boolean I() {
		return i(1.0F) > 0.9D;
	}

	public boolean J() {
		return j(1.0F) > 0.2D;
	}

	public boolean B(int i, int j, int k) {
		if (!J())
			return false;
		if (!j(i, j, k))
			return false;
		if (g(i, k) > j) {
			return false;
		}
		BiomeBase biomebase = getBiome(i, k);

		return biomebase.c() ? false : biomebase.d();
	}

	public boolean C(int i, int j, int k)
	{
		BiomeBase biomebase = getBiome(i, k);

		return biomebase.e();
	}

	public void a(String s, WorldMapBase worldmapbase) {
		this.worldMaps.a(s, worldmapbase);
	}

	public WorldMapBase a(Class oclass, String s) {
		return this.worldMaps.get(oclass, s);
	}

	public int b(String s) {
		return this.worldMaps.a(s);
	}

	public void triggerEffect(int i, int j, int k, int l, int i1) {
		a((EntityHuman)null, i, j, k, l, i1);
	}

	public void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1) {
		for (int j1 = 0; j1 < this.x.size(); j1++)
			((IWorldAccess)this.x.get(j1)).a(entityhuman, i, j, k, l, i1);
	}

	public int getHeight()
	{
		return 256;
	}

	public int L() {
		return this.worldProvider.e ? 128 : 256;
	}

	public Random D(int i, int j, int k) {
		long l = i * 341873128712L + j * 132897987541L + getWorldData().getSeed() + k;

		this.random.setSeed(l);
		return this.random;
	}

	public boolean updateLights() {
		return false;
	}

	public ChunkPosition b(String s, int i, int j, int k) {
		return F().findNearestMapFeature(this, s, i, j, k);
	}

	public CrashReport a(CrashReport crashreport) {
		crashreport.a("World " + this.worldData.getName() + " Entities", new CrashReportEntities(this));
		crashreport.a("World " + this.worldData.getName() + " Players", new CrashReportPlayers(this));
		crashreport.a("World " + this.worldData.getName() + " Chunk Stats", new CrashReportChunkStats(this));
		return crashreport;
	}

	public void f(int i, int j, int k, int l, int i1) {
		Iterator iterator = this.x.iterator();

		while (iterator.hasNext()) {
			IWorldAccess iworldaccess = (IWorldAccess)iterator.next();

			iworldaccess.a(i, j, k, l, i1);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.World
 * JD-Core Version:		0.6.0
 */