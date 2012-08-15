package net.minecraft.server;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.Terminal;
import jline.console.ConsoleReader;
import joptsimple.OptionSet;
import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.scheduler.CraftScheduler;
import org.bukkit.craftbukkit.util.LazyPlayerSet;
import org.bukkit.craftbukkit.util.ServerShutdownThread;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;

public abstract class MinecraftServer
	implements Runnable, IMojangStatistics, ICommandListener
{
	public static Logger log = Logger.getLogger("Minecraft");
	private static MinecraftServer l = null;
	public Convertable convertable;
	private final MojangStatisticsGenerator n = new MojangStatisticsGenerator("server", this);
	public File universe;
	private final List p = new ArrayList();
	private final ICommandHandler q;
	public final MethodProfiler methodProfiler = new MethodProfiler();
	private String serverIp;
	private int s = -1;
	private ServerConfigurationManagerAbstract t;
	private boolean isRunning = true;
	private boolean isStopped = false;
	private int ticks = 0;
	public String d;
	public int e;
	private boolean onlineMode;
	private boolean spawnAnimals;
	private boolean spawnNPCs;
	private boolean pvpMode;
	private boolean allowFlight;
	private String motd;
	private int D;
	private long E;
	private long F;
	private long G;
	private long H;
	public final long[] f = new long[100];
	public final long[] g = new long[100];
	public final long[] h = new long[100];
	public final long[] i = new long[100];
	public final long[] j = new long[100];
	public long[][] k;
	private KeyPair I;
	private String J;
	private String K;
	private boolean demoMode;
	private boolean N;
	private boolean O;
	private String P = "";
	private boolean Q = false;
	private long R;
	private String S;
	private boolean T;
	public List<WorldServer> worlds = new ArrayList();
	public CraftServer server;
	public OptionSet options;
	public ConsoleCommandSender console;
	public RemoteConsoleCommandSender remoteConsole;
	public ConsoleReader reader;
	public static int currentTick;
	public final Thread primaryThread;
	public Queue<PlayerChatEvent> chatQueue = new ConcurrentLinkedQueue();

	public MinecraftServer(OptionSet options)
	{
		l = this;

		this.q = new CommandDispatcher();

		this.options = options;
		try {
			this.reader = new ConsoleReader(System.in, System.out);
			this.reader.setExpandEvents(false);
		}
		catch (Exception e) {
			try {
				System.setProperty("jline.terminal", "jline.UnsupportedTerminal");
				System.setProperty("user.language", "en");
				org.bukkit.craftbukkit.Main.useJline = false;
				this.reader = new ConsoleReader(System.in, System.out);
				this.reader.setExpandEvents(false);
			} catch (IOException ex) {
				Logger.getLogger(MinecraftServer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Runtime.getRuntime().addShutdownHook(new ServerShutdownThread(this));

		this.primaryThread = new ThreadServerApplication(this, "Server thread");
	}
	public abstract PropertyManager getPropertyManager();

	protected abstract boolean init() throws UnknownHostException;

	protected void c(String s) {
		if (getConvertable().isConvertable(s)) {
			log.info("Converting map!");
			d("menu.convertingLevel");
			getConvertable().convert(s, new ConvertProgressUpdater(this));
		}
	}

	protected synchronized void d(String s) {
		this.S = s;
	}

	protected void a(String s, String s1, long i, WorldType worldtype) {
		c(s);
		d("menu.loadingLevel");

		IDataManager idatamanager = this.convertable.a(s, true);
		WorldData worlddata = idatamanager.getWorldData();

		int worldCount = 3;

		for (int j = 0; j < worldCount; j++)
		{
			int dimension = 0;

			if (j == 1) {
				if (getAllowNether()) {
					dimension = -1;
				}

			}
			else if (j == 2)
			{
				if (this.server.getAllowEnd()) {
					dimension = 1;
				}

			}
			else
			{
				String worldType = World.Environment.getEnvironment(dimension).toString().toLowerCase();
				String name = s + "_" + worldType;

				ChunkGenerator gen = this.server.getGenerator(name);
				WorldSettings worldsettings = new WorldSettings(i, getGamemode(), getGenerateStructures(), false, worldtype);
				WorldServer world;
				WorldServer world;
				if (j == 0)
				{
					WorldServer world;
					if (L())
					{
						world = new DemoWorldServer(this, new ServerNBTManager(this.server.getWorldContainer(), s1, true), s1, dimension, this.methodProfiler);
					}
					else
						world = new WorldServer(this, new ServerNBTManager(this.server.getWorldContainer(), s1, true), s1, dimension, worldsettings, this.methodProfiler, World.Environment.getEnvironment(dimension), gen);
				}
				else {
					String dim = "DIM" + dimension;

					File newWorld = new File(new File(name), dim);
					File oldWorld = new File(new File(s), dim);

					if ((!newWorld.isDirectory()) && (oldWorld.isDirectory())) {
						log.info("---- Migration of old " + worldType + " folder required ----");
						log.info("Unfortunately due to the way that Minecraft implemented multiworld support in 1.6, Bukkit requires that you move your " + worldType + " folder to a new location in order to operate correctly.");
						log.info("We will move this folder for you, but it will mean that you need to move it back should you wish to stop using Bukkit in the future.");
						log.info("Attempting to move " + oldWorld + " to " + newWorld + "...");

						if (newWorld.exists()) {
							log.severe("A file or folder already exists at " + newWorld + "!");
							log.info("---- Migration of old " + worldType + " folder failed ----");
						} else if (newWorld.getParentFile().mkdirs()) {
							if (oldWorld.renameTo(newWorld)) {
								log.info("Success! To restore " + worldType + " in the future, simply move " + newWorld + " to " + oldWorld);
								log.info("---- Migration of old " + worldType + " folder complete ----");
							} else {
								log.severe("Could not move folder " + oldWorld + " to " + newWorld + "!");
								log.info("---- Migration of old " + worldType + " folder failed ----");
							}
						} else {
							log.severe("Could not create path for " + newWorld + "!");
							log.info("---- Migration of old " + worldType + " folder failed ----");
						}
					}

					c(name);

					world = new SecondaryWorldServer(this, new ServerNBTManager(this.server.getWorldContainer(), name, true), name, dimension, worldsettings, (WorldServer)this.worlds.get(0), this.methodProfiler, World.Environment.getEnvironment(dimension), gen);
				}

				if (gen != null) {
					world.getWorld().getPopulators().addAll(gen.getDefaultPopulators(world.getWorld()));
				}

				this.server.getPluginManager().callEvent(new WorldInitEvent(world.getWorld()));

				world.addIWorldAccess(new WorldManager(this, world));
				if (!H()) {
					world.getWorldData().setGameType(getGamemode());
				}
				this.worlds.add(world);
				this.t.setPlayerFileData((WorldServer[])this.worlds.toArray(new WorldServer[this.worlds.size()]));
			}
		}

		c(getDifficulty());
		d();
	}

	protected void d() {
		short short1 = 196;
		long i = System.currentTimeMillis();

		d("menu.generatingTerrain");

		for (int j = 0; j < this.worlds.size(); j++) {
			WorldServer worldserver = (WorldServer)this.worlds.get(j);
			log.info("Preparing start region for level " + j + " (Seed: " + worldserver.getSeed() + ")");
			if (!worldserver.getWorld().getKeepSpawnInMemory())
			{
				continue;
			}
			ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

			for (int k = -short1; (k <= short1) && (isRunning()); k += 16) {
				for (int l = -short1; (l <= short1) && (isRunning()); l += 16) {
					long i1 = System.currentTimeMillis();

					if (i1 < i) {
						i = i1;
					}

					if (i1 > i + 1000L) {
						int j1 = (short1 * 2 + 1) * (short1 * 2 + 1);
						int k1 = (k + short1) * (short1 * 2 + 1) + l + 1;

						a_("Preparing spawn area", k1 * 100 / j1);
						i = i1;
					}

					worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + k >> 4, chunkcoordinates.z + l >> 4);

					while ((worldserver.updateLights()) && (isRunning()));
				}
			}

		}

		for (World world : this.worlds) {
			this.server.getPluginManager().callEvent(new WorldLoadEvent(world.getWorld()));
		}

		i(); } 
	public abstract boolean getGenerateStructures();

	public abstract EnumGamemode getGamemode();

	public abstract int getDifficulty();

	public abstract boolean isHardcore();

	protected void a_(String s, int i) { this.d = s;
		this.e = i;
		log.info(s + ": " + i + "%"); }

	protected void i()
	{
		this.d = null;
		this.e = 0;

		this.server.enablePlugins(PluginLoadOrder.POSTWORLD);
	}

	protected void saveChunks(boolean flag) throws ExceptionWorldConflict {
		if (!this.O)
		{
			for (int j = 0; j < this.worlds.size(); j++) {
				WorldServer worldserver = (WorldServer)this.worlds.get(j);

				if (worldserver != null) {
					if (!flag) {
						log.info("Saving chunks for level '" + worldserver.getWorldData().getName() + "'/" + worldserver.worldProvider);
						worldserver.save(true, (IProgressUpdate)null);
					} else {
						worldserver.save(false, (IProgressUpdate)null);
					}

					worldserver.saveLevel();

					WorldSaveEvent event = new WorldSaveEvent(worldserver.getWorld());
					this.server.getPluginManager().callEvent(event);
				}
			}
		}
	}

	public void stop() throws ExceptionWorldConflict
	{
		if (!this.O) {
			log.info("Stopping server");

			if (this.server != null) {
				this.server.disablePlugins();
			}

			if (ac() != null) {
				ac().a();
			}

			if (this.t != null) {
				log.info("Saving players");
				this.t.savePlayers();
				this.t.r();
			}

			log.info("Saving worlds");
			saveChunks(false);

			if ((this.n != null) && (this.n.d()))
				this.n.e();
		}
	}

	public String getServerIp()
	{
		return this.serverIp;
	}

	public void e(String s) {
		this.serverIp = s;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public void safeShutdown() {
		this.isRunning = false;
	}

	public void run() {
		try {
			if (init()) {
				long i = System.currentTimeMillis();

				for (long j = 0L; this.isRunning; this.Q = true) {
					long k = System.currentTimeMillis();
					long l = k - i;

					if ((l > 2000L) && (i - this.R >= 15000L)) {
						if (this.server.getWarnOnOverload())
							log.warning("Can't keep up! Did the system time change, or is the server overloaded?");
						l = 2000L;
						this.R = i;
					}

					if (l < 0L) {
						log.warning("Time ran backwards! Did the system time change?");
						l = 0L;
					}

					j += l;
					i = k;
					if (((WorldServer)this.worlds.get(0)).everyoneDeeplySleeping()) {
						p();
						j = 0L;
					} else {
						while (j > 50L) {
							currentTick = (int)(System.currentTimeMillis() / 50L);
							j -= 50L;
							p();
						}
					}

					Thread.sleep(1L);
				}
			} else {
				a((CrashReport)null);
			}
		} catch (Throwable e) {
			throwable.printStackTrace();
			log.log(Level.SEVERE, "Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
			CrashReport crashreport = null;

			if ((throwable instanceof ReportedException))
				crashreport = b(((ReportedException)throwable).a());
			else {
				crashreport = b(new CrashReport("Exception in server tick loop", throwable));
			}

			File file1 = new File(new File(n(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt");

			if (crashreport.a(file1))
				log.severe("This crash report has been saved to: " + file1.getAbsolutePath());
			else {
				log.severe("We were unable to save this crash report to disk.");
			}

			a(crashreport);
		} finally {
			try {
				stop();
				this.isStopped = true;
			} catch (Throwable e) {
				throwable1.printStackTrace();
			}
			finally {
				try {
					this.reader.getTerminal().restore();
				}
				catch (Exception e) {
				}
				o();
			}
		}
	}

	protected File n() {
		return new File(".");
	}
	protected void a(CrashReport crashreport) {
	}
	protected void o() {
	}

	protected void p() throws ExceptionWorldConflict {
		long i = System.nanoTime();

		AxisAlignedBB.a().a();
		Vec3D.a().a();
		this.ticks += 1;
		if (this.T) {
			this.T = false;
		}

		q();
		if (this.ticks % 900 == 0)
		{
			this.t.savePlayers();
			saveChunks(true);
		}

		this.j[(this.ticks % 100)] = (System.nanoTime() - i);
		this.f[(this.ticks % 100)] = (Packet.p - this.E);
		this.E = Packet.p;
		this.g[(this.ticks % 100)] = (Packet.q - this.F);
		this.F = Packet.q;
		this.h[(this.ticks % 100)] = (Packet.n - this.G);
		this.G = Packet.n;
		this.i[(this.ticks % 100)] = (Packet.o - this.H);
		this.H = Packet.o;

		if ((!this.n.d()) && (this.ticks > 100)) {
			this.n.a();
		}

		if (this.ticks % 6000 == 0)
			this.n.b();
	}

	public void q()
	{
		this.server.getScheduler().mainThreadHeartbeat(this.ticks);
		String message;
		while (!this.chatQueue.isEmpty()) {
			PlayerChatEvent event = (PlayerChatEvent)this.chatQueue.remove();
			Bukkit.getPluginManager().callEvent(event);

			if (event.isCancelled())
			{
				continue;
			}
			message = String.format(event.getFormat(), new Object[] { event.getPlayer().getDisplayName(), event.getMessage() });
			this.console.sendMessage(message);
			Iterator i$;
			if (((LazyPlayerSet)event.getRecipients()).isLazy())
				for (i$ = getServerConfigurationManager().players.iterator(); i$.hasNext(); ) { Object player = i$.next();
					((EntityPlayer)player).sendMessage(message);
				}
			else {
				for (Player player : event.getRecipients()) {
					player.sendMessage(message);
				}
			}

		}

		if (this.ticks % 20 == 0) {
			for (int i = 0; i < getServerConfigurationManager().players.size(); i++) {
				EntityPlayer entityplayer = (EntityPlayer)getServerConfigurationManager().players.get(i);
				entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(entityplayer.getPlayerTime()));
			}
		}

		for (int i = 0; i < this.worlds.size(); i++) {
			long j = System.nanoTime();

			WorldServer worldserver = (WorldServer)this.worlds.get(i);

			worldserver.doTick();

			while (worldserver.updateLights());
			worldserver.tickEntities();

			worldserver.getTracker().updatePlayers();
		}

		ac().b();

		this.t.tick();

		Iterator iterator = this.p.iterator();

		while (iterator.hasNext()) {
			IUpdatePlayerListBox iupdateplayerlistbox = (IUpdatePlayerListBox)iterator.next();

			iupdateplayerlistbox.a();
		}
	}

	public boolean getAllowNether()
	{
		return true;
	}

	public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
		this.p.add(iupdateplayerlistbox);
	}

	public static void main(OptionSet options) {
		StatisticList.a();
		try
		{
			DedicatedServer dedicatedserver = new DedicatedServer(options);

			if (options.has("port")) {
				int port = ((Integer)options.valueOf("port")).intValue();
				if (port > 0) {
					dedicatedserver.setPort(port);
				}
			}

			if (options.has("universe")) {
				dedicatedserver.universe = ((File)options.valueOf("universe"));
			}

			if (options.has("world")) {
				dedicatedserver.m((String)options.valueOf("world"));
			}

			dedicatedserver.primaryThread.start();
		}
		catch (Exception exception)
		{
			log.log(Level.SEVERE, "Failed to start the minecraft server", exception);
		}
	}

	public void s()
	{
	}

	public File f(String s) {
		return new File(n(), s);
	}

	public void info(String s) {
		log.info(s);
	}

	public void warning(String s) {
		log.warning(s);
	}

	public WorldServer getWorldServer(int i)
	{
		for (WorldServer world : this.worlds) {
			if (world.dimension == i) {
				return world;
			}
		}

		return (WorldServer)this.worlds.get(0);
	}

	public String t()
	{
		return this.serverIp;
	}

	public int u() {
		return this.s;
	}

	public String v() {
		return this.motd;
	}

	public String getVersion() {
		return "1.3.1";
	}

	public int x() {
		return this.t.getPlayerCount();
	}

	public int y() {
		return this.t.getMaxPlayers();
	}

	public String[] getPlayers() {
		return this.t.d();
	}

	public String getPlugins()
	{
		StringBuilder result = new StringBuilder();
		Plugin[] plugins = this.server.getPluginManager().getPlugins();

		result.append(this.server.getName());
		result.append(" on Bukkit ");
		result.append(this.server.getBukkitVersion());

		if ((plugins.length > 0) && (this.server.getQueryPlugins())) {
			result.append(": ");

			for (int i = 0; i < plugins.length; i++) {
				if (i > 0) {
					result.append("; ");
				}

				result.append(plugins[i].getDescription().getName());
				result.append(" ");
				result.append(plugins[i].getDescription().getVersion().replaceAll(";", ","));
			}
		}

		return result.toString();
	}

	public String i(String s)
	{
		RemoteControlCommandListener.instance.b();

		RemoteServerCommandEvent event = new RemoteServerCommandEvent(this.remoteConsole, s);
		this.server.getPluginManager().callEvent(event);
		ServerCommand servercommand = new ServerCommand(event.getCommand(), RemoteControlCommandListener.instance);

		this.server.dispatchServerCommand(this.remoteConsole, servercommand);

		return RemoteControlCommandListener.instance.c();
	}

	public boolean isDebugging() {
		return getPropertyManager().getBoolean("debug", false);
	}

	public void j(String s) {
		log.log(Level.SEVERE, s);
	}

	public void k(String s) {
		if (isDebugging())
			log.log(Level.INFO, s);
	}

	public String getServerModName()
	{
		return "craftbukkit";
	}

	public CrashReport b(CrashReport crashreport) {
		crashreport.a("Is Modded", new CrashReportModded(this));
		crashreport.a("Profiler Position", new CrashReportProfilerPosition(this));
		if (this.t != null) {
			crashreport.a("Player Count", new CrashReportPlayerCount(this));
		}

		if (this.worlds != null) {
			for (int j = 0; j < this.worlds.size(); j++) {
				WorldServer worldserver = (WorldServer)this.worlds.get(j);

				if (worldserver != null) {
					worldserver.a(crashreport);
				}
			}
		}

		return crashreport;
	}

	public List a(ICommandListener icommandlistener, String s) {
		ArrayList arraylist = new ArrayList();

		if (s.startsWith("/")) {
			s = s.substring(1);
			boolean flag = !s.contains(" ");
			List list = this.q.b(icommandlistener, s);

			if (list != null) {
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					String s1 = (String)iterator.next();

					if (flag)
						arraylist.add("/" + s1);
					else {
						arraylist.add(s1);
					}
				}
			}

			return arraylist;
		}
		String[] astring = s.split(" ", -1);
		String s2 = astring[(astring.length - 1)];
		String[] astring1 = this.t.d();
		int i = astring1.length;

		for (int j = 0; j < i; j++) {
			String s3 = astring1[j];

			if (CommandAbstract.a(s2, s3)) {
				arraylist.add(s3);
			}
		}

		return arraylist;
	}

	public static MinecraftServer getServer()
	{
		return l;
	}

	public String getName() {
		return "Server";
	}

	public void sendMessage(String s) {
		log.info(StripColor.a(s));
	}

	public boolean b(String s) {
		return true;
	}

	public String a(String s, Object[] aobject) {
		return LocaleLanguage.a().a(s, aobject);
	}

	public ICommandHandler getCommandHandler() {
		return this.q;
	}

	public KeyPair E() {
		return this.I;
	}

	public int F() {
		return this.s;
	}

	public void setPort(int i) {
		this.s = i;
	}

	public String G() {
		return this.J;
	}

	public void l(String s) {
		this.J = s;
	}

	public boolean H() {
		return this.J != null;
	}

	public String I() {
		return this.K;
	}

	public void m(String s) {
		this.K = s;
	}

	public void a(KeyPair keypair) {
		this.I = keypair;
	}

	public void c(int i)
	{
		for (int j = 0; j < this.worlds.size(); j++) {
			WorldServer worldserver = (WorldServer)this.worlds.get(j);

			if (worldserver != null)
				if (worldserver.getWorldData().isHardcore()) {
					worldserver.difficulty = 3;
					worldserver.setSpawnFlags(true, true);
				} else if (H()) {
					worldserver.difficulty = i;
					worldserver.setSpawnFlags(worldserver.difficulty > 0, true);
				} else {
					worldserver.difficulty = i;
					worldserver.setSpawnFlags(getSpawnMonsters(), this.spawnAnimals);
				}
		}
	}

	protected boolean getSpawnMonsters()
	{
		return true;
	}

	public boolean L() {
		return this.demoMode;
	}

	public void b(boolean flag) {
		this.demoMode = flag;
	}

	public void c(boolean flag) {
		this.N = flag;
	}

	public Convertable getConvertable() {
		return this.convertable;
	}

	public void O() {
		this.O = true;
		getConvertable().d();

		for (int i = 0; i < this.worlds.size(); i++) {
			WorldServer worldserver = (WorldServer)this.worlds.get(i);

			if (worldserver != null) {
				worldserver.saveLevel();
			}
		}

		getConvertable().e(((WorldServer)this.worlds.get(0)).getDataManager().g());
		safeShutdown();
	}

	public String getTexturePack() {
		return this.P;
	}

	public void setTexturePack(String s) {
		this.P = s;
	}

	public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
		mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
		mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
		mojangstatisticsgenerator.a("players_current", Integer.valueOf(x()));
		mojangstatisticsgenerator.a("players_max", Integer.valueOf(y()));
		mojangstatisticsgenerator.a("players_seen", Integer.valueOf(this.t.getSeenPlayers().length));
		mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
		mojangstatisticsgenerator.a("gui_state", ae() ? "enabled" : "disabled");
		mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int)(MathHelper.a(this.j) * 1.0E-006D)));
		mojangstatisticsgenerator.a("avg_sent_packet_count", Integer.valueOf((int)MathHelper.a(this.f)));
		mojangstatisticsgenerator.a("avg_sent_packet_size", Integer.valueOf((int)MathHelper.a(this.g)));
		mojangstatisticsgenerator.a("avg_rec_packet_count", Integer.valueOf((int)MathHelper.a(this.h)));
		mojangstatisticsgenerator.a("avg_rec_packet_size", Integer.valueOf((int)MathHelper.a(this.i)));
		int i = 0;

		for (int j = 0; j < this.worlds.size(); j++)
		{
			WorldServer worldserver = (WorldServer)this.worlds.get(j);

			WorldData worlddata = worldserver.getWorldData();

			mojangstatisticsgenerator.a("world[" + i + "][dimension]", Integer.valueOf(worldserver.worldProvider.dimension));
			mojangstatisticsgenerator.a("world[" + i + "][mode]", worlddata.getGameType());
			mojangstatisticsgenerator.a("world[" + i + "][difficulty]", Integer.valueOf(worldserver.difficulty));
			mojangstatisticsgenerator.a("world[" + i + "][hardcore]", Boolean.valueOf(worlddata.isHardcore()));
			mojangstatisticsgenerator.a("world[" + i + "][generator_name]", worlddata.getType().name());
			mojangstatisticsgenerator.a("world[" + i + "][generator_version]", Integer.valueOf(worlddata.getType().getVersion()));
			mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.D));
			mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.F().getLoadedChunks()));
			i++;
		}

		mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
	}

	public void b(MojangStatisticsGenerator mojangstatisticsgenerator) {
		mojangstatisticsgenerator.a("singleplayer", Boolean.valueOf(H()));
		mojangstatisticsgenerator.a("server_brand", getServerModName());
		mojangstatisticsgenerator.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
		mojangstatisticsgenerator.a("dedicated", Boolean.valueOf(S()));
	}

	public boolean getSnooperEnabled() {
		return true;
	}

	public int R() {
		return 16;
	}
	public abstract boolean S();

	public boolean getOnlineMode() {
		return this.onlineMode;
	}

	public void setOnlineMode(boolean flag) {
		this.onlineMode = flag;
	}

	public boolean getSpawnAnimals() {
		return this.spawnAnimals;
	}

	public void setSpawnAnimals(boolean flag) {
		this.spawnAnimals = flag;
	}

	public boolean getSpawnNPCs() {
		return this.spawnNPCs;
	}

	public void setSpawnNPCs(boolean flag) {
		this.spawnNPCs = flag;
	}

	public boolean getPvP() {
		return this.pvpMode;
	}

	public void setPvP(boolean flag) {
		this.pvpMode = flag;
	}

	public boolean getAllowFlight() {
		return this.allowFlight;
	}

	public void setAllowFlight(boolean flag) {
		this.allowFlight = flag;
	}

	public String getMotd() {
		return this.motd;
	}

	public void setMotd(String s) {
		this.motd = s;
	}

	public int getMaxBuildHeight() {
		return this.D;
	}

	public void d(int i) {
		this.D = i;
	}

	public boolean isStopped() {
		return this.isStopped;
	}

	public ServerConfigurationManagerAbstract getServerConfigurationManager() {
		return this.t;
	}

	public void a(ServerConfigurationManagerAbstract serverconfigurationmanagerabstract) {
		this.t = serverconfigurationmanagerabstract;
	}

	public void a(EnumGamemode enumgamemode)
	{
		for (int i = 0; i < this.worlds.size(); i++)
			((WorldServer)getServer().worlds.get(i)).getWorldData().setGameType(enumgamemode);
	}

	public abstract ServerConnection ac();

	public boolean ae()
	{
		return false;
	}
	public abstract String a(EnumGamemode paramEnumGamemode, boolean paramBoolean);

	public int af() {
		return this.ticks;
	}

	public void ag() {
		this.T = true;
	}

	public static ServerConfigurationManagerAbstract a(MinecraftServer minecraftserver) {
		return minecraftserver.t;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MinecraftServer
 * JD-Core Version:		0.6.0
 */