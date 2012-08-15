/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.io.PrintStream;
/*		 */ import java.net.InetAddress;
/*		 */ import java.net.UnknownHostException;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.List;
/*		 */ import java.util.Properties;
/*		 */ import java.util.Random;
/*		 */ import java.util.Set;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ import joptsimple.OptionSet;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.LoggerOutputStream;
/*		 */ import org.bukkit.craftbukkit.command.CraftRemoteConsoleCommandSender;
/*		 */ import org.bukkit.event.server.ServerCommandEvent;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public class DedicatedServer extends MinecraftServer
/*		 */	 implements IMinecraftServer
/*		 */ {
/*	22 */	 private final List l = Collections.synchronizedList(new ArrayList());
/*		 */	 private RemoteStatusListener m;
/*		 */	 private RemoteControlListener n;
/*		 */	 public PropertyManager propertyManager;
/*		 */	 private boolean generateStructures;
/*		 */	 private EnumGamemode q;
/*		 */	 private ServerConnection r;
/*	29 */	 private boolean s = false;
/*		 */ 
/*		 */	 public DedicatedServer(OptionSet options)
/*		 */	 {
/*	33 */		 super(options);
/*		 */ 
/*	35 */		 new ThreadSleepForever(this);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean init() throws UnknownHostException {
/*	39 */		 ThreadCommandReader threadcommandreader = new ThreadCommandReader(this);
/*		 */ 
/*	41 */		 threadcommandreader.setDaemon(true);
/*	42 */		 threadcommandreader.start();
/*	43 */		 ConsoleLogManager.init(this);
/*		 */ 
/*	46 */		 System.setOut(new PrintStream(new LoggerOutputStream(log, Level.INFO), true));
/*	47 */		 System.setErr(new PrintStream(new LoggerOutputStream(log, Level.SEVERE), true));
/*		 */ 
/*	50 */		 log.info("Starting minecraft server version 1.3.1");
/*	51 */		 if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
/*	52 */			 log.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
/*		 */		 }
/*		 */ 
/*	55 */		 log.info("Loading properties");
/*	56 */		 this.propertyManager = new PropertyManager(this.options);
/*	57 */		 if (H()) {
/*	58 */			 e("127.0.0.1");
/*		 */		 } else {
/*	60 */			 setOnlineMode(this.propertyManager.getBoolean("online-mode", true));
/*	61 */			 e(this.propertyManager.getString("server-ip", ""));
/*		 */		 }
/*		 */ 
/*	64 */		 setSpawnAnimals(this.propertyManager.getBoolean("spawn-animals", true));
/*	65 */		 setSpawnNPCs(this.propertyManager.getBoolean("spawn-npcs", true));
/*	66 */		 setPvP(this.propertyManager.getBoolean("pvp", true));
/*	67 */		 setAllowFlight(this.propertyManager.getBoolean("allow-flight", false));
/*	68 */		 setTexturePack(this.propertyManager.getString("texture-pack", ""));
/*	69 */		 setMotd(this.propertyManager.getString("motd", "A Minecraft Server"));
/*	70 */		 this.generateStructures = this.propertyManager.getBoolean("generate-structures", true);
/*	71 */		 int i = this.propertyManager.getInt("gamemode", EnumGamemode.SURVIVAL.a());
/*		 */ 
/*	73 */		 this.q = WorldSettings.a(i);
/*	74 */		 log.info("Default game type: " + this.q);
/*	75 */		 InetAddress inetaddress = null;
/*		 */ 
/*	77 */		 if (getServerIp().length() > 0) {
/*	78 */			 inetaddress = InetAddress.getByName(getServerIp());
/*		 */		 }
/*		 */ 
/*	81 */		 if (F() < 0) {
/*	82 */			 setPort(this.propertyManager.getInt("server-port", 25565));
/*		 */		 }
/*		 */ 
/*	85 */		 log.info("Generating keypair");
/*	86 */		 a(MinecraftEncryption.b());
/*	87 */		 log.info("Starting Minecraft server on " + (getServerIp().length() == 0 ? "*" : getServerIp()) + ":" + F());
/*		 */		 try
/*		 */		 {
/*	90 */			 this.r = new DedicatedServerConnection(this, inetaddress, F());
/*		 */		 } catch (Throwable ioexception) {
/*	92 */			 log.warning("**** FAILED TO BIND TO PORT!");
/*	93 */			 log.log(Level.WARNING, "The exception was: " + ioexception.toString());
/*	94 */			 log.warning("Perhaps a server is already running on that port?");
/*	95 */			 return false;
/*		 */		 }
/*		 */ 
/*	98 */		 if (!getOnlineMode()) {
/*	99 */			 log.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
/* 100 */			 log.warning("The server will make no attempt to authenticate usernames. Beware.");
/* 101 */			 log.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
/* 102 */			 log.warning("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
/*		 */		 }
/*		 */ 
/* 105 */		 a(new ServerConfigurationManager(this));
/* 106 */		 this.convertable = new WorldLoaderServer(this.server.getWorldContainer());
/* 107 */		 long j = System.nanoTime();
/*		 */ 
/* 109 */		 if (I() == null) {
/* 110 */			 m(this.propertyManager.getString("level-name", "world"));
/*		 */		 }
/*		 */ 
/* 113 */		 String s = this.propertyManager.getString("level-seed", "");
/* 114 */		 String s1 = this.propertyManager.getString("level-type", "DEFAULT");
/* 115 */		 long k = new Random().nextLong();
/*		 */ 
/* 117 */		 if (s.length() > 0) {
/*		 */			 try {
/* 119 */				 long l = Long.parseLong(s);
/*		 */ 
/* 121 */				 if (l != 0L)
/* 122 */					 k = l;
/*		 */			 }
/*		 */			 catch (NumberFormatException numberformatexception) {
/* 125 */				 k = s.hashCode();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 129 */		 WorldType worldtype = WorldType.getType(s1);
/*		 */ 
/* 131 */		 if (worldtype == null) {
/* 132 */			 worldtype = WorldType.NORMAL;
/*		 */		 }
/*		 */ 
/* 135 */		 d(this.propertyManager.getInt("max-build-height", 256));
/* 136 */		 d((getMaxBuildHeight() + 8) / 16 * 16);
/* 137 */		 d(MathHelper.a(getMaxBuildHeight(), 64, 256));
/* 138 */		 this.propertyManager.a("max-build-height", Integer.valueOf(getMaxBuildHeight()));
/* 139 */		 log.info("Preparing level \"" + I() + "\"");
/* 140 */		 a(I(), I(), k, worldtype);
/* 141 */		 long i1 = System.nanoTime() - j;
/* 142 */		 String s2 = String.format("%.3fs", new Object[] { Double.valueOf(i1 / 1000000000.0D) });
/*		 */ 
/* 144 */		 log.info("Done (" + s2 + ")! For help, type \"help\" or \"?\"");
/* 145 */		 if (this.propertyManager.getBoolean("enable-query", false)) {
/* 146 */			 log.info("Starting GS4 status listener");
/* 147 */			 this.m = new RemoteStatusListener(this);
/* 148 */			 this.m.a();
/*		 */		 }
/*		 */ 
/* 151 */		 if (this.propertyManager.getBoolean("enable-rcon", false)) {
/* 152 */			 log.info("Starting remote control listener");
/* 153 */			 this.n = new RemoteControlListener(this);
/* 154 */			 this.n.a();
/* 155 */			 this.remoteConsole = new CraftRemoteConsoleCommandSender();
/*		 */		 }
/*		 */ 
/* 159 */		 if (this.propertyManager.properties.containsKey("spawn-protection")) {
/* 160 */			 log.info("'spawn-protection' in server.properties has been moved to 'settings.spawn-radius' in bukkit.yml. I will move your config for you.");
/* 161 */			 this.server.setSpawnRadius(this.propertyManager.getInt("spawn-protection", 16));
/* 162 */			 this.propertyManager.properties.remove("spawn-protection");
/* 163 */			 this.propertyManager.savePropertiesFile();
/*		 */		 }
/*		 */ 
/* 166 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public PropertyManager getPropertyManager() {
/* 170 */		 return this.propertyManager;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getGenerateStructures()
/*		 */	 {
/* 175 */		 return this.generateStructures;
/*		 */	 }
/*		 */ 
/*		 */	 public EnumGamemode getGamemode() {
/* 179 */		 return this.q;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDifficulty() {
/* 183 */		 return this.propertyManager.getInt("difficulty", 1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isHardcore() {
/* 187 */		 return this.propertyManager.getBoolean("hardcore", false);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(CrashReport crashreport) {
/* 191 */		 while (isRunning()) {
/* 192 */			 ah();
/*		 */			 try
/*		 */			 {
/* 195 */				 Thread.sleep(10L);
/*		 */			 } catch (InterruptedException interruptedexception) {
/* 197 */				 interruptedexception.printStackTrace();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public CrashReport b(CrashReport crashreport) {
/* 203 */		 crashreport.a("Type", new CrashReportType(this));
/* 204 */		 return super.b(crashreport);
/*		 */	 }
/*		 */ 
/*		 */	 protected void o() {
/* 208 */		 System.exit(0);
/*		 */	 }
/*		 */ 
/*		 */	 public void q() {
/* 212 */		 super.q();
/* 213 */		 ah();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getAllowNether() {
/* 217 */		 return this.propertyManager.getBoolean("allow-nether", true);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getSpawnMonsters() {
/* 221 */		 return this.propertyManager.getBoolean("spawn-monsters", true);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 225 */		 mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(ai().getHasWhitelist()));
/* 226 */		 mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(ai().getWhitelisted().size()));
/* 227 */		 super.a(mojangstatisticsgenerator);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getSnooperEnabled() {
/* 231 */		 return this.propertyManager.getBoolean("snooper-enabled", true);
/*		 */	 }
/*		 */ 
/*		 */	 public void issueCommand(String s, ICommandListener icommandlistener) {
/* 235 */		 this.l.add(new ServerCommand(s, icommandlistener));
/*		 */	 }
/*		 */ 
/*		 */	 public void ah() {
/* 239 */		 while (!this.l.isEmpty()) {
/* 240 */			 ServerCommand servercommand = (ServerCommand)this.l.remove(0);
/*		 */ 
/* 243 */			 ServerCommandEvent event = new ServerCommandEvent(this.console, servercommand.command);
/* 244 */			 this.server.getPluginManager().callEvent(event);
/* 245 */			 servercommand = new ServerCommand(event.getCommand(), servercommand.source);
/*		 */ 
/* 248 */			 this.server.dispatchServerCommand(this.console, servercommand);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean S()
/*		 */	 {
/* 254 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public ServerConfigurationManager ai() {
/* 258 */		 return (ServerConfigurationManager)super.getServerConfigurationManager();
/*		 */	 }
/*		 */ 
/*		 */	 public ServerConnection ac() {
/* 262 */		 return this.r;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(String s, int i) {
/* 266 */		 return this.propertyManager.getInt(s, i);
/*		 */	 }
/*		 */ 
/*		 */	 public String a(String s, String s1) {
/* 270 */		 return this.propertyManager.getString(s, s1);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(String s, boolean flag) {
/* 274 */		 return this.propertyManager.getBoolean(s, flag);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String s, Object object) {
/* 278 */		 this.propertyManager.a(s, object);
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/* 282 */		 this.propertyManager.savePropertiesFile();
/*		 */	 }
/*		 */ 
/*		 */	 public String c() {
/* 286 */		 File file1 = this.propertyManager.c();
/*		 */ 
/* 288 */		 return file1 != null ? file1.getAbsolutePath() : "No settings file";
/*		 */	 }
/*		 */ 
/*		 */	 public void aj() {
/* 292 */		 ServerGUI.a(this);
/* 293 */		 this.s = true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean ae() {
/* 297 */		 return this.s;
/*		 */	 }
/*		 */ 
/*		 */	 public String a(EnumGamemode enumgamemode, boolean flag) {
/* 301 */		 return "";
/*		 */	 }
/*		 */ 
/*		 */	 public ServerConfigurationManagerAbstract getServerConfigurationManager() {
/* 305 */		 return ai();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.DedicatedServer
 * JD-Core Version:		0.6.0
 */