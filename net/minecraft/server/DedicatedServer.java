package net.minecraft.server;

import java.io.File;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import joptsimple.OptionSet;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.LoggerOutputStream;
import org.bukkit.craftbukkit.command.CraftRemoteConsoleCommandSender;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.PluginManager;

public class DedicatedServer extends MinecraftServer
	implements IMinecraftServer
{
	private final List l = Collections.synchronizedList(new ArrayList());
	private RemoteStatusListener m;
	private RemoteControlListener n;
	public PropertyManager propertyManager;
	private boolean generateStructures;
	private EnumGamemode q;
	private ServerConnection r;
	private boolean s = false;

	public DedicatedServer(OptionSet options)
	{
		super(options);

		new ThreadSleepForever(this);
	}

	protected boolean init() throws UnknownHostException {
		ThreadCommandReader threadcommandreader = new ThreadCommandReader(this);

		threadcommandreader.setDaemon(true);
		threadcommandreader.start();
		ConsoleLogManager.init(this);

		System.setOut(new PrintStream(new LoggerOutputStream(log, Level.INFO), true));
		System.setErr(new PrintStream(new LoggerOutputStream(log, Level.SEVERE), true));

		log.info("Starting minecraft server version 1.3.1");
		if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
			log.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
		}

		log.info("Loading properties");
		this.propertyManager = new PropertyManager(this.options);
		if (H()) {
			e("127.0.0.1");
		} else {
			setOnlineMode(this.propertyManager.getBoolean("online-mode", true));
			e(this.propertyManager.getString("server-ip", ""));
		}

		setSpawnAnimals(this.propertyManager.getBoolean("spawn-animals", true));
		setSpawnNPCs(this.propertyManager.getBoolean("spawn-npcs", true));
		setPvP(this.propertyManager.getBoolean("pvp", true));
		setAllowFlight(this.propertyManager.getBoolean("allow-flight", false));
		setTexturePack(this.propertyManager.getString("texture-pack", ""));
		setMotd(this.propertyManager.getString("motd", "A Minecraft Server"));
		this.generateStructures = this.propertyManager.getBoolean("generate-structures", true);
		int i = this.propertyManager.getInt("gamemode", EnumGamemode.SURVIVAL.a());

		this.q = WorldSettings.a(i);
		log.info("Default game type: " + this.q);
		InetAddress inetaddress = null;

		if (getServerIp().length() > 0) {
			inetaddress = InetAddress.getByName(getServerIp());
		}

		if (F() < 0) {
			setPort(this.propertyManager.getInt("server-port", 25565));
		}

		log.info("Generating keypair");
		a(MinecraftEncryption.b());
		log.info("Starting Minecraft server on " + (getServerIp().length() == 0 ? "*" : getServerIp()) + ":" + F());
		try
		{
			this.r = new DedicatedServerConnection(this, inetaddress, F());
		} catch (Throwable ioexception) {
			log.warning("**** FAILED TO BIND TO PORT!");
			log.log(Level.WARNING, "The exception was: " + ioexception.toString());
			log.warning("Perhaps a server is already running on that port?");
			return false;
		}

		if (!getOnlineMode()) {
			log.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
			log.warning("The server will make no attempt to authenticate usernames. Beware.");
			log.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
			log.warning("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
		}

		a(new ServerConfigurationManager(this));
		this.convertable = new WorldLoaderServer(this.server.getWorldContainer());
		long j = System.nanoTime();

		if (I() == null) {
			m(this.propertyManager.getString("level-name", "world"));
		}

		String s = this.propertyManager.getString("level-seed", "");
		String s1 = this.propertyManager.getString("level-type", "DEFAULT");
		long k = new Random().nextLong();

		if (s.length() > 0) {
			try {
				long l = Long.parseLong(s);

				if (l != 0L)
					k = l;
			}
			catch (NumberFormatException numberformatexception) {
				k = s.hashCode();
			}
		}

		WorldType worldtype = WorldType.getType(s1);

		if (worldtype == null) {
			worldtype = WorldType.NORMAL;
		}

		d(this.propertyManager.getInt("max-build-height", 256));
		d((getMaxBuildHeight() + 8) / 16 * 16);
		d(MathHelper.a(getMaxBuildHeight(), 64, 256));
		this.propertyManager.a("max-build-height", Integer.valueOf(getMaxBuildHeight()));
		log.info("Preparing level \"" + I() + "\"");
		a(I(), I(), k, worldtype);
		long i1 = System.nanoTime() - j;
		String s2 = String.format("%.3fs", new Object[] { Double.valueOf(i1 / 1000000000.0D) });

		log.info("Done (" + s2 + ")! For help, type \"help\" or \"?\"");
		if (this.propertyManager.getBoolean("enable-query", false)) {
			log.info("Starting GS4 status listener");
			this.m = new RemoteStatusListener(this);
			this.m.a();
		}

		if (this.propertyManager.getBoolean("enable-rcon", false)) {
			log.info("Starting remote control listener");
			this.n = new RemoteControlListener(this);
			this.n.a();
			this.remoteConsole = new CraftRemoteConsoleCommandSender();
		}

		if (this.propertyManager.properties.containsKey("spawn-protection")) {
			log.info("'spawn-protection' in server.properties has been moved to 'settings.spawn-radius' in bukkit.yml. I will move your config for you.");
			this.server.setSpawnRadius(this.propertyManager.getInt("spawn-protection", 16));
			this.propertyManager.properties.remove("spawn-protection");
			this.propertyManager.savePropertiesFile();
		}

		return true;
	}

	public PropertyManager getPropertyManager() {
		return this.propertyManager;
	}

	public boolean getGenerateStructures()
	{
		return this.generateStructures;
	}

	public EnumGamemode getGamemode() {
		return this.q;
	}

	public int getDifficulty() {
		return this.propertyManager.getInt("difficulty", 1);
	}

	public boolean isHardcore() {
		return this.propertyManager.getBoolean("hardcore", false);
	}

	protected void a(CrashReport crashreport) {
		while (isRunning()) {
			ah();
			try
			{
				Thread.sleep(10L);
			} catch (InterruptedException interruptedexception) {
				interruptedexception.printStackTrace();
			}
		}
	}

	public CrashReport b(CrashReport crashreport) {
		crashreport.a("Type", new CrashReportType(this));
		return super.b(crashreport);
	}

	protected void o() {
		System.exit(0);
	}

	public void q() {
		super.q();
		ah();
	}

	public boolean getAllowNether() {
		return this.propertyManager.getBoolean("allow-nether", true);
	}

	public boolean getSpawnMonsters() {
		return this.propertyManager.getBoolean("spawn-monsters", true);
	}

	public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
		mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(ai().getHasWhitelist()));
		mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(ai().getWhitelisted().size()));
		super.a(mojangstatisticsgenerator);
	}

	public boolean getSnooperEnabled() {
		return this.propertyManager.getBoolean("snooper-enabled", true);
	}

	public void issueCommand(String s, ICommandListener icommandlistener) {
		this.l.add(new ServerCommand(s, icommandlistener));
	}

	public void ah() {
		while (!this.l.isEmpty()) {
			ServerCommand servercommand = (ServerCommand)this.l.remove(0);

			ServerCommandEvent event = new ServerCommandEvent(this.console, servercommand.command);
			this.server.getPluginManager().callEvent(event);
			servercommand = new ServerCommand(event.getCommand(), servercommand.source);

			this.server.dispatchServerCommand(this.console, servercommand);
		}
	}

	public boolean S()
	{
		return true;
	}

	public ServerConfigurationManager ai() {
		return (ServerConfigurationManager)super.getServerConfigurationManager();
	}

	public ServerConnection ac() {
		return this.r;
	}

	public int a(String s, int i) {
		return this.propertyManager.getInt(s, i);
	}

	public String a(String s, String s1) {
		return this.propertyManager.getString(s, s1);
	}

	public boolean a(String s, boolean flag) {
		return this.propertyManager.getBoolean(s, flag);
	}

	public void a(String s, Object object) {
		this.propertyManager.a(s, object);
	}

	public void a() {
		this.propertyManager.savePropertiesFile();
	}

	public String c() {
		File file1 = this.propertyManager.c();

		return file1 != null ? file1.getAbsolutePath() : "No settings file";
	}

	public void aj() {
		ServerGUI.a(this);
		this.s = true;
	}

	public boolean ae() {
		return this.s;
	}

	public String a(EnumGamemode enumgamemode, boolean flag) {
		return "";
	}

	public ServerConfigurationManagerAbstract getServerConfigurationManager() {
		return ai();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.DedicatedServer
 * JD-Core Version:		0.6.0
 */