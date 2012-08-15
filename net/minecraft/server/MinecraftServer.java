/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.awt.GraphicsEnvironment;
/*			*/ import java.io.File;
/*			*/ import java.io.IOException;
/*			*/ import java.net.UnknownHostException;
/*			*/ import java.security.KeyPair;
/*			*/ import java.text.SimpleDateFormat;
/*			*/ import java.util.ArrayList;
/*			*/ import java.util.Date;
/*			*/ import java.util.Iterator;
/*			*/ import java.util.List;
/*			*/ import java.util.Queue;
/*			*/ import java.util.concurrent.ConcurrentLinkedQueue;
/*			*/ import java.util.logging.Level;
/*			*/ import java.util.logging.Logger;
/*			*/ import jline.Terminal;
/*			*/ import jline.console.ConsoleReader;
/*			*/ import joptsimple.OptionSet;
/*			*/ import org.bukkit.Bukkit;
/*			*/ import org.bukkit.World.Environment;
/*			*/ import org.bukkit.command.ConsoleCommandSender;
/*			*/ import org.bukkit.command.RemoteConsoleCommandSender;
/*			*/ import org.bukkit.craftbukkit.CraftServer;
/*			*/ import org.bukkit.craftbukkit.CraftWorld;
/*			*/ import org.bukkit.craftbukkit.scheduler.CraftScheduler;
/*			*/ import org.bukkit.craftbukkit.util.LazyPlayerSet;
/*			*/ import org.bukkit.craftbukkit.util.ServerShutdownThread;
/*			*/ import org.bukkit.entity.Player;
/*			*/ import org.bukkit.event.player.PlayerChatEvent;
/*			*/ import org.bukkit.event.server.RemoteServerCommandEvent;
/*			*/ import org.bukkit.event.world.WorldInitEvent;
/*			*/ import org.bukkit.event.world.WorldLoadEvent;
/*			*/ import org.bukkit.event.world.WorldSaveEvent;
/*			*/ import org.bukkit.generator.ChunkGenerator;
/*			*/ import org.bukkit.plugin.Plugin;
/*			*/ import org.bukkit.plugin.PluginDescriptionFile;
/*			*/ import org.bukkit.plugin.PluginLoadOrder;
/*			*/ import org.bukkit.plugin.PluginManager;
/*			*/ 
/*			*/ public abstract class MinecraftServer
/*			*/	 implements Runnable, IMojangStatistics, ICommandListener
/*			*/ {
/*	 27 */	 public static Logger log = Logger.getLogger("Minecraft");
/*	 28 */	 private static MinecraftServer l = null;
/*			*/	 public Convertable convertable;
/*	 30 */	 private final MojangStatisticsGenerator n = new MojangStatisticsGenerator("server", this);
/*			*/	 public File universe;
/*	 32 */	 private final List p = new ArrayList();
/*			*/	 private final ICommandHandler q;
/*	 34 */	 public final MethodProfiler methodProfiler = new MethodProfiler();
/*			*/	 private String serverIp;
/*	 36 */	 private int s = -1;
/*			*/	 private ServerConfigurationManagerAbstract t;
/*	 39 */	 private boolean isRunning = true;
/*	 40 */	 private boolean isStopped = false;
/*	 41 */	 private int ticks = 0;
/*			*/	 public String d;
/*			*/	 public int e;
/*			*/	 private boolean onlineMode;
/*			*/	 private boolean spawnAnimals;
/*			*/	 private boolean spawnNPCs;
/*			*/	 private boolean pvpMode;
/*			*/	 private boolean allowFlight;
/*			*/	 private String motd;
/*			*/	 private int D;
/*			*/	 private long E;
/*			*/	 private long F;
/*			*/	 private long G;
/*			*/	 private long H;
/*	 55 */	 public final long[] f = new long[100];
/*	 56 */	 public final long[] g = new long[100];
/*	 57 */	 public final long[] h = new long[100];
/*	 58 */	 public final long[] i = new long[100];
/*	 59 */	 public final long[] j = new long[100];
/*			*/	 public long[][] k;
/*			*/	 private KeyPair I;
/*			*/	 private String J;
/*			*/	 private String K;
/*			*/	 private boolean demoMode;
/*			*/	 private boolean N;
/*			*/	 private boolean O;
/*	 67 */	 private String P = "";
/*	 68 */	 private boolean Q = false;
/*			*/	 private long R;
/*			*/	 private String S;
/*			*/	 private boolean T;
/*	 74 */	 public List<WorldServer> worlds = new ArrayList();
/*			*/	 public CraftServer server;
/*			*/	 public OptionSet options;
/*			*/	 public ConsoleCommandSender console;
/*			*/	 public RemoteConsoleCommandSender remoteConsole;
/*			*/	 public ConsoleReader reader;
/*			*/	 public static int currentTick;
/*			*/	 public final Thread primaryThread;
/*	 82 */	 public Queue<PlayerChatEvent> chatQueue = new ConcurrentLinkedQueue();
/*			*/ 
/*			*/	 public MinecraftServer(OptionSet options)
/*			*/	 {
/*	 86 */		 l = this;
/*			*/ 
/*	 88 */		 this.q = new CommandDispatcher();
/*			*/ 
/*	 92 */		 this.options = options;
/*			*/		 try {
/*	 94 */			 this.reader = new ConsoleReader(System.in, System.out);
/*	 95 */			 this.reader.setExpandEvents(false);
/*			*/		 }
/*			*/		 catch (Exception e) {
/*			*/			 try {
/*	 99 */				 System.setProperty("jline.terminal", "jline.UnsupportedTerminal");
/*	100 */				 System.setProperty("user.language", "en");
/*	101 */				 org.bukkit.craftbukkit.Main.useJline = false;
/*	102 */				 this.reader = new ConsoleReader(System.in, System.out);
/*	103 */				 this.reader.setExpandEvents(false);
/*			*/			 } catch (IOException ex) {
/*	105 */				 Logger.getLogger(MinecraftServer.class.getName()).log(Level.SEVERE, null, ex);
/*			*/			 }
/*			*/		 }
/*	108 */		 Runtime.getRuntime().addShutdownHook(new ServerShutdownThread(this));
/*			*/ 
/*	110 */		 this.primaryThread = new ThreadServerApplication(this, "Server thread");
/*			*/	 }
/*			*/	 public abstract PropertyManager getPropertyManager();
/*			*/ 
/*			*/	 protected abstract boolean init() throws UnknownHostException;
/*			*/ 
/*			*/	 protected void c(String s) {
/*	119 */		 if (getConvertable().isConvertable(s)) {
/*	120 */			 log.info("Converting map!");
/*	121 */			 d("menu.convertingLevel");
/*	122 */			 getConvertable().convert(s, new ConvertProgressUpdater(this));
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected synchronized void d(String s) {
/*	127 */		 this.S = s;
/*			*/	 }
/*			*/ 
/*			*/	 protected void a(String s, String s1, long i, WorldType worldtype) {
/*	131 */		 c(s);
/*	132 */		 d("menu.loadingLevel");
/*			*/ 
/*	134 */		 IDataManager idatamanager = this.convertable.a(s, true);
/*	135 */		 WorldData worlddata = idatamanager.getWorldData();
/*			*/ 
/*	137 */		 int worldCount = 3;
/*			*/ 
/*	139 */		 for (int j = 0; j < worldCount; j++)
/*			*/		 {
/*	141 */			 int dimension = 0;
/*			*/ 
/*	143 */			 if (j == 1) {
/*	144 */				 if (getAllowNether()) {
/*	145 */					 dimension = -1;
/*			*/				 }
/*			*/ 
/*			*/			 }
/*	151 */			 else if (j == 2)
/*			*/			 {
/*	153 */				 if (this.server.getAllowEnd()) {
/*	154 */					 dimension = 1;
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/			 else
/*			*/			 {
/*	160 */				 String worldType = World.Environment.getEnvironment(dimension).toString().toLowerCase();
/*	161 */				 String name = s + "_" + worldType;
/*			*/ 
/*	163 */				 ChunkGenerator gen = this.server.getGenerator(name);
/*	164 */				 WorldSettings worldsettings = new WorldSettings(i, getGamemode(), getGenerateStructures(), false, worldtype);
/*			*/				 WorldServer world;
/*			*/				 WorldServer world;
/*	166 */				 if (j == 0)
/*			*/				 {
/*			*/					 WorldServer world;
/*	167 */					 if (L())
/*			*/					 {
/*	169 */						 world = new DemoWorldServer(this, new ServerNBTManager(this.server.getWorldContainer(), s1, true), s1, dimension, this.methodProfiler);
/*			*/					 }
/*			*/					 else
/*	172 */						 world = new WorldServer(this, new ServerNBTManager(this.server.getWorldContainer(), s1, true), s1, dimension, worldsettings, this.methodProfiler, World.Environment.getEnvironment(dimension), gen);
/*			*/				 }
/*			*/				 else {
/*	175 */					 String dim = "DIM" + dimension;
/*			*/ 
/*	177 */					 File newWorld = new File(new File(name), dim);
/*	178 */					 File oldWorld = new File(new File(s), dim);
/*			*/ 
/*	180 */					 if ((!newWorld.isDirectory()) && (oldWorld.isDirectory())) {
/*	181 */						 log.info("---- Migration of old " + worldType + " folder required ----");
/*	182 */						 log.info("Unfortunately due to the way that Minecraft implemented multiworld support in 1.6, Bukkit requires that you move your " + worldType + " folder to a new location in order to operate correctly.");
/*	183 */						 log.info("We will move this folder for you, but it will mean that you need to move it back should you wish to stop using Bukkit in the future.");
/*	184 */						 log.info("Attempting to move " + oldWorld + " to " + newWorld + "...");
/*			*/ 
/*	186 */						 if (newWorld.exists()) {
/*	187 */							 log.severe("A file or folder already exists at " + newWorld + "!");
/*	188 */							 log.info("---- Migration of old " + worldType + " folder failed ----");
/*	189 */						 } else if (newWorld.getParentFile().mkdirs()) {
/*	190 */							 if (oldWorld.renameTo(newWorld)) {
/*	191 */								 log.info("Success! To restore " + worldType + " in the future, simply move " + newWorld + " to " + oldWorld);
/*	192 */								 log.info("---- Migration of old " + worldType + " folder complete ----");
/*			*/							 } else {
/*	194 */								 log.severe("Could not move folder " + oldWorld + " to " + newWorld + "!");
/*	195 */								 log.info("---- Migration of old " + worldType + " folder failed ----");
/*			*/							 }
/*			*/						 } else {
/*	198 */							 log.severe("Could not create path for " + newWorld + "!");
/*	199 */							 log.info("---- Migration of old " + worldType + " folder failed ----");
/*			*/						 }
/*			*/					 }
/*			*/ 
/*	203 */					 c(name);
/*			*/ 
/*	206 */					 world = new SecondaryWorldServer(this, new ServerNBTManager(this.server.getWorldContainer(), name, true), name, dimension, worldsettings, (WorldServer)this.worlds.get(0), this.methodProfiler, World.Environment.getEnvironment(dimension), gen);
/*			*/				 }
/*			*/ 
/*	209 */				 if (gen != null) {
/*	210 */					 world.getWorld().getPopulators().addAll(gen.getDefaultPopulators(world.getWorld()));
/*			*/				 }
/*			*/ 
/*	213 */				 this.server.getPluginManager().callEvent(new WorldInitEvent(world.getWorld()));
/*			*/ 
/*	215 */				 world.addIWorldAccess(new WorldManager(this, world));
/*	216 */				 if (!H()) {
/*	217 */					 world.getWorldData().setGameType(getGamemode());
/*			*/				 }
/*	219 */				 this.worlds.add(world);
/*	220 */				 this.t.setPlayerFileData((WorldServer[])this.worlds.toArray(new WorldServer[this.worlds.size()]));
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	224 */		 c(getDifficulty());
/*	225 */		 d();
/*			*/	 }
/*			*/ 
/*			*/	 protected void d() {
/*	229 */		 short short1 = 196;
/*	230 */		 long i = System.currentTimeMillis();
/*			*/ 
/*	232 */		 d("menu.generatingTerrain");
/*			*/ 
/*	235 */		 for (int j = 0; j < this.worlds.size(); j++) {
/*	236 */			 WorldServer worldserver = (WorldServer)this.worlds.get(j);
/*	237 */			 log.info("Preparing start region for level " + j + " (Seed: " + worldserver.getSeed() + ")");
/*	238 */			 if (!worldserver.getWorld().getKeepSpawnInMemory())
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/*	242 */			 ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
/*			*/ 
/*	244 */			 for (int k = -short1; (k <= short1) && (isRunning()); k += 16) {
/*	245 */				 for (int l = -short1; (l <= short1) && (isRunning()); l += 16) {
/*	246 */					 long i1 = System.currentTimeMillis();
/*			*/ 
/*	248 */					 if (i1 < i) {
/*	249 */						 i = i1;
/*			*/					 }
/*			*/ 
/*	252 */					 if (i1 > i + 1000L) {
/*	253 */						 int j1 = (short1 * 2 + 1) * (short1 * 2 + 1);
/*	254 */						 int k1 = (k + short1) * (short1 * 2 + 1) + l + 1;
/*			*/ 
/*	256 */						 a_("Preparing spawn area", k1 * 100 / j1);
/*	257 */						 i = i1;
/*			*/					 }
/*			*/ 
/*	260 */					 worldserver.chunkProviderServer.getChunkAt(chunkcoordinates.x + k >> 4, chunkcoordinates.z + l >> 4);
/*			*/ 
/*	262 */					 while ((worldserver.updateLights()) && (isRunning()));
/*			*/				 }
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	270 */		 for (World world : this.worlds) {
/*	271 */			 this.server.getPluginManager().callEvent(new WorldLoadEvent(world.getWorld()));
/*			*/		 }
/*			*/ 
/*	275 */		 i(); } 
/*			*/	 public abstract boolean getGenerateStructures();
/*			*/ 
/*			*/	 public abstract EnumGamemode getGamemode();
/*			*/ 
/*			*/	 public abstract int getDifficulty();
/*			*/ 
/*			*/	 public abstract boolean isHardcore();
/*			*/ 
/*	287 */	 protected void a_(String s, int i) { this.d = s;
/*	288 */		 this.e = i;
/*	289 */		 log.info(s + ": " + i + "%"); }
/*			*/ 
/*			*/	 protected void i()
/*			*/	 {
/*	293 */		 this.d = null;
/*	294 */		 this.e = 0;
/*			*/ 
/*	296 */		 this.server.enablePlugins(PluginLoadOrder.POSTWORLD);
/*			*/	 }
/*			*/ 
/*			*/	 protected void saveChunks(boolean flag) throws ExceptionWorldConflict {
/*	300 */		 if (!this.O)
/*			*/		 {
/*	302 */			 for (int j = 0; j < this.worlds.size(); j++) {
/*	303 */				 WorldServer worldserver = (WorldServer)this.worlds.get(j);
/*			*/ 
/*	305 */				 if (worldserver != null) {
/*	306 */					 if (!flag) {
/*	307 */						 log.info("Saving chunks for level '" + worldserver.getWorldData().getName() + "'/" + worldserver.worldProvider);
/*	308 */						 worldserver.save(true, (IProgressUpdate)null);
/*			*/					 } else {
/*	310 */						 worldserver.save(false, (IProgressUpdate)null);
/*			*/					 }
/*			*/ 
/*	313 */					 worldserver.saveLevel();
/*			*/ 
/*	315 */					 WorldSaveEvent event = new WorldSaveEvent(worldserver.getWorld());
/*	316 */					 this.server.getPluginManager().callEvent(event);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void stop() throws ExceptionWorldConflict
/*			*/	 {
/*	324 */		 if (!this.O) {
/*	325 */			 log.info("Stopping server");
/*			*/ 
/*	327 */			 if (this.server != null) {
/*	328 */				 this.server.disablePlugins();
/*			*/			 }
/*			*/ 
/*	332 */			 if (ac() != null) {
/*	333 */				 ac().a();
/*			*/			 }
/*			*/ 
/*	336 */			 if (this.t != null) {
/*	337 */				 log.info("Saving players");
/*	338 */				 this.t.savePlayers();
/*	339 */				 this.t.r();
/*			*/			 }
/*			*/ 
/*	342 */			 log.info("Saving worlds");
/*	343 */			 saveChunks(false);
/*			*/ 
/*	354 */			 if ((this.n != null) && (this.n.d()))
/*	355 */				 this.n.e();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public String getServerIp()
/*			*/	 {
/*	361 */		 return this.serverIp;
/*			*/	 }
/*			*/ 
/*			*/	 public void e(String s) {
/*	365 */		 this.serverIp = s;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isRunning() {
/*	369 */		 return this.isRunning;
/*			*/	 }
/*			*/ 
/*			*/	 public void safeShutdown() {
/*	373 */		 this.isRunning = false;
/*			*/	 }
/*			*/ 
/*			*/	 public void run() {
/*			*/		 try {
/*	378 */			 if (init()) {
/*	379 */				 long i = System.currentTimeMillis();
/*			*/ 
/*	381 */				 for (long j = 0L; this.isRunning; this.Q = true) {
/*	382 */					 long k = System.currentTimeMillis();
/*	383 */					 long l = k - i;
/*			*/ 
/*	385 */					 if ((l > 2000L) && (i - this.R >= 15000L)) {
/*	386 */						 if (this.server.getWarnOnOverload())
/*	387 */							 log.warning("Can't keep up! Did the system time change, or is the server overloaded?");
/*	388 */						 l = 2000L;
/*	389 */						 this.R = i;
/*			*/					 }
/*			*/ 
/*	392 */					 if (l < 0L) {
/*	393 */						 log.warning("Time ran backwards! Did the system time change?");
/*	394 */						 l = 0L;
/*			*/					 }
/*			*/ 
/*	397 */					 j += l;
/*	398 */					 i = k;
/*	399 */					 if (((WorldServer)this.worlds.get(0)).everyoneDeeplySleeping()) {
/*	400 */						 p();
/*	401 */						 j = 0L;
/*			*/					 } else {
/*	403 */						 while (j > 50L) {
/*	404 */							 currentTick = (int)(System.currentTimeMillis() / 50L);
/*	405 */							 j -= 50L;
/*	406 */							 p();
/*			*/						 }
/*			*/					 }
/*			*/ 
/*	410 */					 Thread.sleep(1L);
/*			*/				 }
/*			*/			 } else {
/*	413 */				 a((CrashReport)null);
/*			*/			 }
/*			*/		 } catch (Throwable e) {
/*	416 */			 throwable.printStackTrace();
/*	417 */			 log.log(Level.SEVERE, "Encountered an unexpected exception " + throwable.getClass().getSimpleName(), throwable);
/*	418 */			 CrashReport crashreport = null;
/*			*/ 
/*	420 */			 if ((throwable instanceof ReportedException))
/*	421 */				 crashreport = b(((ReportedException)throwable).a());
/*			*/			 else {
/*	423 */				 crashreport = b(new CrashReport("Exception in server tick loop", throwable));
/*			*/			 }
/*			*/ 
/*	426 */			 File file1 = new File(new File(n(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt");
/*			*/ 
/*	428 */			 if (crashreport.a(file1))
/*	429 */				 log.severe("This crash report has been saved to: " + file1.getAbsolutePath());
/*			*/			 else {
/*	431 */				 log.severe("We were unable to save this crash report to disk.");
/*			*/			 }
/*			*/ 
/*	434 */			 a(crashreport);
/*			*/		 } finally {
/*			*/			 try {
/*	437 */				 stop();
/*	438 */				 this.isStopped = true;
/*			*/			 } catch (Throwable e) {
/*	440 */				 throwable1.printStackTrace();
/*			*/			 }
/*			*/			 finally {
/*			*/				 try {
/*	444 */					 this.reader.getTerminal().restore();
/*			*/				 }
/*			*/				 catch (Exception e) {
/*			*/				 }
/*	448 */				 o();
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected File n() {
/*	454 */		 return new File(".");
/*			*/	 }
/*			*/	 protected void a(CrashReport crashreport) {
/*			*/	 }
/*			*/	 protected void o() {
/*			*/	 }
/*			*/ 
/*			*/	 protected void p() throws ExceptionWorldConflict {
/*	462 */		 long i = System.nanoTime();
/*			*/ 
/*	464 */		 AxisAlignedBB.a().a();
/*	465 */		 Vec3D.a().a();
/*	466 */		 this.ticks += 1;
/*	467 */		 if (this.T) {
/*	468 */			 this.T = false;
/*			*/		 }
/*			*/ 
/*	474 */		 q();
/*	475 */		 if (this.ticks % 900 == 0)
/*			*/		 {
/*	477 */			 this.t.savePlayers();
/*	478 */			 saveChunks(true);
/*			*/		 }
/*			*/ 
/*	483 */		 this.j[(this.ticks % 100)] = (System.nanoTime() - i);
/*	484 */		 this.f[(this.ticks % 100)] = (Packet.p - this.E);
/*	485 */		 this.E = Packet.p;
/*	486 */		 this.g[(this.ticks % 100)] = (Packet.q - this.F);
/*	487 */		 this.F = Packet.q;
/*	488 */		 this.h[(this.ticks % 100)] = (Packet.n - this.G);
/*	489 */		 this.G = Packet.n;
/*	490 */		 this.i[(this.ticks % 100)] = (Packet.o - this.H);
/*	491 */		 this.H = Packet.o;
/*			*/ 
/*	494 */		 if ((!this.n.d()) && (this.ticks > 100)) {
/*	495 */			 this.n.a();
/*			*/		 }
/*			*/ 
/*	498 */		 if (this.ticks % 6000 == 0)
/*	499 */			 this.n.b();
/*			*/	 }
/*			*/ 
/*			*/	 public void q()
/*			*/	 {
/*	510 */		 this.server.getScheduler().mainThreadHeartbeat(this.ticks);
/*			*/		 String message;
/*	513 */		 while (!this.chatQueue.isEmpty()) {
/*	514 */			 PlayerChatEvent event = (PlayerChatEvent)this.chatQueue.remove();
/*	515 */			 Bukkit.getPluginManager().callEvent(event);
/*			*/ 
/*	517 */			 if (event.isCancelled())
/*			*/			 {
/*			*/				 continue;
/*			*/			 }
/*	521 */			 message = String.format(event.getFormat(), new Object[] { event.getPlayer().getDisplayName(), event.getMessage() });
/*	522 */			 this.console.sendMessage(message);
/*			*/			 Iterator i$;
/*	523 */			 if (((LazyPlayerSet)event.getRecipients()).isLazy())
/*	524 */				 for (i$ = getServerConfigurationManager().players.iterator(); i$.hasNext(); ) { Object player = i$.next();
/*	525 */					 ((EntityPlayer)player).sendMessage(message);
/*			*/				 }
/*			*/			 else {
/*	528 */				 for (Player player : event.getRecipients()) {
/*	529 */					 player.sendMessage(message);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	535 */		 if (this.ticks % 20 == 0) {
/*	536 */			 for (int i = 0; i < getServerConfigurationManager().players.size(); i++) {
/*	537 */				 EntityPlayer entityplayer = (EntityPlayer)getServerConfigurationManager().players.get(i);
/*	538 */				 entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(entityplayer.getPlayerTime()));
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	542 */		 for (int i = 0; i < this.worlds.size(); i++) {
/*	543 */			 long j = System.nanoTime();
/*			*/ 
/*	546 */			 WorldServer worldserver = (WorldServer)this.worlds.get(i);
/*			*/ 
/*	558 */			 worldserver.doTick();
/*			*/ 
/*	562 */			 while (worldserver.updateLights());
/*	565 */			 worldserver.tickEntities();
/*			*/ 
/*	569 */			 worldserver.getTracker().updatePlayers();
/*			*/		 }
/*			*/ 
/*	581 */		 ac().b();
/*			*/ 
/*	583 */		 this.t.tick();
/*			*/ 
/*	585 */		 Iterator iterator = this.p.iterator();
/*			*/ 
/*	587 */		 while (iterator.hasNext()) {
/*	588 */			 IUpdatePlayerListBox iupdateplayerlistbox = (IUpdatePlayerListBox)iterator.next();
/*			*/ 
/*	590 */			 iupdateplayerlistbox.a();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean getAllowNether()
/*			*/	 {
/*	597 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(IUpdatePlayerListBox iupdateplayerlistbox) {
/*	601 */		 this.p.add(iupdateplayerlistbox);
/*			*/	 }
/*			*/ 
/*			*/	 public static void main(OptionSet options) {
/*	605 */		 StatisticList.a();
/*			*/		 try
/*			*/		 {
/*	655 */			 DedicatedServer dedicatedserver = new DedicatedServer(options);
/*			*/ 
/*	657 */			 if (options.has("port")) {
/*	658 */				 int port = ((Integer)options.valueOf("port")).intValue();
/*	659 */				 if (port > 0) {
/*	660 */					 dedicatedserver.setPort(port);
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	664 */			 if (options.has("universe")) {
/*	665 */				 dedicatedserver.universe = ((File)options.valueOf("universe"));
/*			*/			 }
/*			*/ 
/*	668 */			 if (options.has("world")) {
/*	669 */				 dedicatedserver.m((String)options.valueOf("world"));
/*			*/			 }
/*			*/ 
/*	698 */			 dedicatedserver.primaryThread.start();
/*			*/		 }
/*			*/		 catch (Exception exception)
/*			*/		 {
/*	702 */			 log.log(Level.SEVERE, "Failed to start the minecraft server", exception);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void s()
/*			*/	 {
/*			*/	 }
/*			*/ 
/*			*/	 public File f(String s) {
/*	711 */		 return new File(n(), s);
/*			*/	 }
/*			*/ 
/*			*/	 public void info(String s) {
/*	715 */		 log.info(s);
/*			*/	 }
/*			*/ 
/*			*/	 public void warning(String s) {
/*	719 */		 log.warning(s);
/*			*/	 }
/*			*/ 
/*			*/	 public WorldServer getWorldServer(int i)
/*			*/	 {
/*	724 */		 for (WorldServer world : this.worlds) {
/*	725 */			 if (world.dimension == i) {
/*	726 */				 return world;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	730 */		 return (WorldServer)this.worlds.get(0);
/*			*/	 }
/*			*/ 
/*			*/	 public String t()
/*			*/	 {
/*	735 */		 return this.serverIp;
/*			*/	 }
/*			*/ 
/*			*/	 public int u() {
/*	739 */		 return this.s;
/*			*/	 }
/*			*/ 
/*			*/	 public String v() {
/*	743 */		 return this.motd;
/*			*/	 }
/*			*/ 
/*			*/	 public String getVersion() {
/*	747 */		 return "1.3.1";
/*			*/	 }
/*			*/ 
/*			*/	 public int x() {
/*	751 */		 return this.t.getPlayerCount();
/*			*/	 }
/*			*/ 
/*			*/	 public int y() {
/*	755 */		 return this.t.getMaxPlayers();
/*			*/	 }
/*			*/ 
/*			*/	 public String[] getPlayers() {
/*	759 */		 return this.t.d();
/*			*/	 }
/*			*/ 
/*			*/	 public String getPlugins()
/*			*/	 {
/*	764 */		 StringBuilder result = new StringBuilder();
/*	765 */		 Plugin[] plugins = this.server.getPluginManager().getPlugins();
/*			*/ 
/*	767 */		 result.append(this.server.getName());
/*	768 */		 result.append(" on Bukkit ");
/*	769 */		 result.append(this.server.getBukkitVersion());
/*			*/ 
/*	771 */		 if ((plugins.length > 0) && (this.server.getQueryPlugins())) {
/*	772 */			 result.append(": ");
/*			*/ 
/*	774 */			 for (int i = 0; i < plugins.length; i++) {
/*	775 */				 if (i > 0) {
/*	776 */					 result.append("; ");
/*			*/				 }
/*			*/ 
/*	779 */				 result.append(plugins[i].getDescription().getName());
/*	780 */				 result.append(" ");
/*	781 */				 result.append(plugins[i].getDescription().getVersion().replaceAll(";", ","));
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	785 */		 return result.toString();
/*			*/	 }
/*			*/ 
/*			*/	 public String i(String s)
/*			*/	 {
/*	790 */		 RemoteControlCommandListener.instance.b();
/*			*/ 
/*	792 */		 RemoteServerCommandEvent event = new RemoteServerCommandEvent(this.remoteConsole, s);
/*	793 */		 this.server.getPluginManager().callEvent(event);
/*	794 */		 ServerCommand servercommand = new ServerCommand(event.getCommand(), RemoteControlCommandListener.instance);
/*			*/ 
/*	796 */		 this.server.dispatchServerCommand(this.remoteConsole, servercommand);
/*			*/ 
/*	798 */		 return RemoteControlCommandListener.instance.c();
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isDebugging() {
/*	802 */		 return getPropertyManager().getBoolean("debug", false);
/*			*/	 }
/*			*/ 
/*			*/	 public void j(String s) {
/*	806 */		 log.log(Level.SEVERE, s);
/*			*/	 }
/*			*/ 
/*			*/	 public void k(String s) {
/*	810 */		 if (isDebugging())
/*	811 */			 log.log(Level.INFO, s);
/*			*/	 }
/*			*/ 
/*			*/	 public String getServerModName()
/*			*/	 {
/*	816 */		 return "craftbukkit";
/*			*/	 }
/*			*/ 
/*			*/	 public CrashReport b(CrashReport crashreport) {
/*	820 */		 crashreport.a("Is Modded", new CrashReportModded(this));
/*	821 */		 crashreport.a("Profiler Position", new CrashReportProfilerPosition(this));
/*	822 */		 if (this.t != null) {
/*	823 */			 crashreport.a("Player Count", new CrashReportPlayerCount(this));
/*			*/		 }
/*			*/ 
/*	827 */		 if (this.worlds != null) {
/*	828 */			 for (int j = 0; j < this.worlds.size(); j++) {
/*	829 */				 WorldServer worldserver = (WorldServer)this.worlds.get(j);
/*			*/ 
/*	832 */				 if (worldserver != null) {
/*	833 */					 worldserver.a(crashreport);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	838 */		 return crashreport;
/*			*/	 }
/*			*/ 
/*			*/	 public List a(ICommandListener icommandlistener, String s) {
/*	842 */		 ArrayList arraylist = new ArrayList();
/*			*/ 
/*	844 */		 if (s.startsWith("/")) {
/*	845 */			 s = s.substring(1);
/*	846 */			 boolean flag = !s.contains(" ");
/*	847 */			 List list = this.q.b(icommandlistener, s);
/*			*/ 
/*	849 */			 if (list != null) {
/*	850 */				 Iterator iterator = list.iterator();
/*			*/ 
/*	852 */				 while (iterator.hasNext()) {
/*	853 */					 String s1 = (String)iterator.next();
/*			*/ 
/*	855 */					 if (flag)
/*	856 */						 arraylist.add("/" + s1);
/*			*/					 else {
/*	858 */						 arraylist.add(s1);
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	863 */			 return arraylist;
/*			*/		 }
/*	865 */		 String[] astring = s.split(" ", -1);
/*	866 */		 String s2 = astring[(astring.length - 1)];
/*	867 */		 String[] astring1 = this.t.d();
/*	868 */		 int i = astring1.length;
/*			*/ 
/*	870 */		 for (int j = 0; j < i; j++) {
/*	871 */			 String s3 = astring1[j];
/*			*/ 
/*	873 */			 if (CommandAbstract.a(s2, s3)) {
/*	874 */				 arraylist.add(s3);
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	878 */		 return arraylist;
/*			*/	 }
/*			*/ 
/*			*/	 public static MinecraftServer getServer()
/*			*/	 {
/*	883 */		 return l;
/*			*/	 }
/*			*/ 
/*			*/	 public String getName() {
/*	887 */		 return "Server";
/*			*/	 }
/*			*/ 
/*			*/	 public void sendMessage(String s) {
/*	891 */		 log.info(StripColor.a(s));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean b(String s) {
/*	895 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public String a(String s, Object[] aobject) {
/*	899 */		 return LocaleLanguage.a().a(s, aobject);
/*			*/	 }
/*			*/ 
/*			*/	 public ICommandHandler getCommandHandler() {
/*	903 */		 return this.q;
/*			*/	 }
/*			*/ 
/*			*/	 public KeyPair E() {
/*	907 */		 return this.I;
/*			*/	 }
/*			*/ 
/*			*/	 public int F() {
/*	911 */		 return this.s;
/*			*/	 }
/*			*/ 
/*			*/	 public void setPort(int i) {
/*	915 */		 this.s = i;
/*			*/	 }
/*			*/ 
/*			*/	 public String G() {
/*	919 */		 return this.J;
/*			*/	 }
/*			*/ 
/*			*/	 public void l(String s) {
/*	923 */		 this.J = s;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean H() {
/*	927 */		 return this.J != null;
/*			*/	 }
/*			*/ 
/*			*/	 public String I() {
/*	931 */		 return this.K;
/*			*/	 }
/*			*/ 
/*			*/	 public void m(String s) {
/*	935 */		 this.K = s;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(KeyPair keypair) {
/*	939 */		 this.I = keypair;
/*			*/	 }
/*			*/ 
/*			*/	 public void c(int i)
/*			*/	 {
/*	944 */		 for (int j = 0; j < this.worlds.size(); j++) {
/*	945 */			 WorldServer worldserver = (WorldServer)this.worlds.get(j);
/*			*/ 
/*	948 */			 if (worldserver != null)
/*	949 */				 if (worldserver.getWorldData().isHardcore()) {
/*	950 */					 worldserver.difficulty = 3;
/*	951 */					 worldserver.setSpawnFlags(true, true);
/*	952 */				 } else if (H()) {
/*	953 */					 worldserver.difficulty = i;
/*	954 */					 worldserver.setSpawnFlags(worldserver.difficulty > 0, true);
/*			*/				 } else {
/*	956 */					 worldserver.difficulty = i;
/*	957 */					 worldserver.setSpawnFlags(getSpawnMonsters(), this.spawnAnimals);
/*			*/				 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 protected boolean getSpawnMonsters()
/*			*/	 {
/*	964 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean L() {
/*	968 */		 return this.demoMode;
/*			*/	 }
/*			*/ 
/*			*/	 public void b(boolean flag) {
/*	972 */		 this.demoMode = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public void c(boolean flag) {
/*	976 */		 this.N = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public Convertable getConvertable() {
/*	980 */		 return this.convertable;
/*			*/	 }
/*			*/ 
/*			*/	 public void O() {
/*	984 */		 this.O = true;
/*	985 */		 getConvertable().d();
/*			*/ 
/*	988 */		 for (int i = 0; i < this.worlds.size(); i++) {
/*	989 */			 WorldServer worldserver = (WorldServer)this.worlds.get(i);
/*			*/ 
/*	992 */			 if (worldserver != null) {
/*	993 */				 worldserver.saveLevel();
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	997 */		 getConvertable().e(((WorldServer)this.worlds.get(0)).getDataManager().g());
/*	998 */		 safeShutdown();
/*			*/	 }
/*			*/ 
/*			*/	 public String getTexturePack() {
/* 1002 */		 return this.P;
/*			*/	 }
/*			*/ 
/*			*/	 public void setTexturePack(String s) {
/* 1006 */		 this.P = s;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 1010 */		 mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
/* 1011 */		 mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
/* 1012 */		 mojangstatisticsgenerator.a("players_current", Integer.valueOf(x()));
/* 1013 */		 mojangstatisticsgenerator.a("players_max", Integer.valueOf(y()));
/* 1014 */		 mojangstatisticsgenerator.a("players_seen", Integer.valueOf(this.t.getSeenPlayers().length));
/* 1015 */		 mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
/* 1016 */		 mojangstatisticsgenerator.a("gui_state", ae() ? "enabled" : "disabled");
/* 1017 */		 mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int)(MathHelper.a(this.j) * 1.0E-006D)));
/* 1018 */		 mojangstatisticsgenerator.a("avg_sent_packet_count", Integer.valueOf((int)MathHelper.a(this.f)));
/* 1019 */		 mojangstatisticsgenerator.a("avg_sent_packet_size", Integer.valueOf((int)MathHelper.a(this.g)));
/* 1020 */		 mojangstatisticsgenerator.a("avg_rec_packet_count", Integer.valueOf((int)MathHelper.a(this.h)));
/* 1021 */		 mojangstatisticsgenerator.a("avg_rec_packet_size", Integer.valueOf((int)MathHelper.a(this.i)));
/* 1022 */		 int i = 0;
/*			*/ 
/* 1025 */		 for (int j = 0; j < this.worlds.size(); j++)
/*			*/		 {
/* 1027 */			 WorldServer worldserver = (WorldServer)this.worlds.get(j);
/*			*/ 
/* 1029 */			 WorldData worlddata = worldserver.getWorldData();
/*			*/ 
/* 1031 */			 mojangstatisticsgenerator.a("world[" + i + "][dimension]", Integer.valueOf(worldserver.worldProvider.dimension));
/* 1032 */			 mojangstatisticsgenerator.a("world[" + i + "][mode]", worlddata.getGameType());
/* 1033 */			 mojangstatisticsgenerator.a("world[" + i + "][difficulty]", Integer.valueOf(worldserver.difficulty));
/* 1034 */			 mojangstatisticsgenerator.a("world[" + i + "][hardcore]", Boolean.valueOf(worlddata.isHardcore()));
/* 1035 */			 mojangstatisticsgenerator.a("world[" + i + "][generator_name]", worlddata.getType().name());
/* 1036 */			 mojangstatisticsgenerator.a("world[" + i + "][generator_version]", Integer.valueOf(worlddata.getType().getVersion()));
/* 1037 */			 mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.D));
/* 1038 */			 mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.F().getLoadedChunks()));
/* 1039 */			 i++;
/*			*/		 }
/*			*/ 
/* 1043 */		 mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
/*			*/	 }
/*			*/ 
/*			*/	 public void b(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 1047 */		 mojangstatisticsgenerator.a("singleplayer", Boolean.valueOf(H()));
/* 1048 */		 mojangstatisticsgenerator.a("server_brand", getServerModName());
/* 1049 */		 mojangstatisticsgenerator.a("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
/* 1050 */		 mojangstatisticsgenerator.a("dedicated", Boolean.valueOf(S()));
/*			*/	 }
/*			*/ 
/*			*/	 public boolean getSnooperEnabled() {
/* 1054 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public int R() {
/* 1058 */		 return 16;
/*			*/	 }
/*			*/	 public abstract boolean S();
/*			*/ 
/*			*/	 public boolean getOnlineMode() {
/* 1064 */		 return this.onlineMode;
/*			*/	 }
/*			*/ 
/*			*/	 public void setOnlineMode(boolean flag) {
/* 1068 */		 this.onlineMode = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean getSpawnAnimals() {
/* 1072 */		 return this.spawnAnimals;
/*			*/	 }
/*			*/ 
/*			*/	 public void setSpawnAnimals(boolean flag) {
/* 1076 */		 this.spawnAnimals = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean getSpawnNPCs() {
/* 1080 */		 return this.spawnNPCs;
/*			*/	 }
/*			*/ 
/*			*/	 public void setSpawnNPCs(boolean flag) {
/* 1084 */		 this.spawnNPCs = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean getPvP() {
/* 1088 */		 return this.pvpMode;
/*			*/	 }
/*			*/ 
/*			*/	 public void setPvP(boolean flag) {
/* 1092 */		 this.pvpMode = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean getAllowFlight() {
/* 1096 */		 return this.allowFlight;
/*			*/	 }
/*			*/ 
/*			*/	 public void setAllowFlight(boolean flag) {
/* 1100 */		 this.allowFlight = flag;
/*			*/	 }
/*			*/ 
/*			*/	 public String getMotd() {
/* 1104 */		 return this.motd;
/*			*/	 }
/*			*/ 
/*			*/	 public void setMotd(String s) {
/* 1108 */		 this.motd = s;
/*			*/	 }
/*			*/ 
/*			*/	 public int getMaxBuildHeight() {
/* 1112 */		 return this.D;
/*			*/	 }
/*			*/ 
/*			*/	 public void d(int i) {
/* 1116 */		 this.D = i;
/*			*/	 }
/*			*/ 
/*			*/	 public boolean isStopped() {
/* 1120 */		 return this.isStopped;
/*			*/	 }
/*			*/ 
/*			*/	 public ServerConfigurationManagerAbstract getServerConfigurationManager() {
/* 1124 */		 return this.t;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(ServerConfigurationManagerAbstract serverconfigurationmanagerabstract) {
/* 1128 */		 this.t = serverconfigurationmanagerabstract;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(EnumGamemode enumgamemode)
/*			*/	 {
/* 1133 */		 for (int i = 0; i < this.worlds.size(); i++)
/* 1134 */			 ((WorldServer)getServer().worlds.get(i)).getWorldData().setGameType(enumgamemode);
/*			*/	 }
/*			*/ 
/*			*/	 public abstract ServerConnection ac();
/*			*/ 
/*			*/	 public boolean ae()
/*			*/	 {
/* 1142 */		 return false;
/*			*/	 }
/*			*/	 public abstract String a(EnumGamemode paramEnumGamemode, boolean paramBoolean);
/*			*/ 
/*			*/	 public int af() {
/* 1148 */		 return this.ticks;
/*			*/	 }
/*			*/ 
/*			*/	 public void ag() {
/* 1152 */		 this.T = true;
/*			*/	 }
/*			*/ 
/*			*/	 public static ServerConfigurationManagerAbstract a(MinecraftServer minecraftserver) {
/* 1156 */		 return minecraftserver.t;
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MinecraftServer
 * JD-Core Version:		0.6.0
 */