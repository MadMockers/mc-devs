/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.File;
/*		 */ import java.net.Socket;
/*		 */ import java.net.SocketAddress;
/*		 */ import java.text.SimpleDateFormat;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.LinkedHashSet;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ import java.util.concurrent.CopyOnWriteArrayList;
/*		 */ import java.util.logging.Logger;
/*		 */ import org.bukkit.Bukkit;
/*		 */ import org.bukkit.Location;
/*		 */ import org.bukkit.Server;
/*		 */ import org.bukkit.TravelAgent;
/*		 */ import org.bukkit.World.Environment;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ import org.bukkit.craftbukkit.CraftWorld;
/*		 */ import org.bukkit.craftbukkit.PortalTravelAgent;
/*		 */ import org.bukkit.craftbukkit.command.ColouredConsoleSender;
/*		 */ import org.bukkit.craftbukkit.entity.CraftPlayer;
/*		 */ import org.bukkit.entity.Player;
/*		 */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*		 */ import org.bukkit.event.player.PlayerJoinEvent;
/*		 */ import org.bukkit.event.player.PlayerLoginEvent;
/*		 */ import org.bukkit.event.player.PlayerLoginEvent.Result;
/*		 */ import org.bukkit.event.player.PlayerPortalEvent;
/*		 */ import org.bukkit.event.player.PlayerQuitEvent;
/*		 */ import org.bukkit.event.player.PlayerRespawnEvent;
/*		 */ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/*		 */ import org.bukkit.plugin.PluginManager;
/*		 */ 
/*		 */ public abstract class ServerConfigurationManagerAbstract
/*		 */ {
/*	30 */	 private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
/*	31 */	 public static final Logger a = Logger.getLogger("Minecraft");
/*		 */	 private final MinecraftServer server;
/*	33 */	 public final List players = new CopyOnWriteArrayList();
/*	34 */	 private final BanList banByName = new BanList(new File("banned-players.txt"));
/*	35 */	 private final BanList banByIP = new BanList(new File("banned-ips.txt"));
/*	36 */	 private Set operators = new HashSet();
/*	37 */	 private Set whitelist = new LinkedHashSet();
/*		 */	 public PlayerFileData playerFileData;
/*		 */	 public boolean hasWhitelist;
/*		 */	 protected int maxPlayers;
/*		 */	 protected int d;
/*		 */	 private EnumGamemode m;
/*		 */	 private boolean n;
/*	44 */	 private int o = 0;
/*		 */	 private CraftServer cserver;
/*		 */ 
/*		 */	 public ServerConfigurationManagerAbstract(MinecraftServer minecraftserver)
/*		 */	 {
/*	50 */		 minecraftserver.server = new CraftServer(minecraftserver, this);
/*	51 */		 minecraftserver.console = ColouredConsoleSender.getInstance();
/*	52 */		 this.cserver = minecraftserver.server;
/*		 */ 
/*	55 */		 this.server = minecraftserver;
/*	56 */		 this.banByName.setEnabled(false);
/*	57 */		 this.banByIP.setEnabled(false);
/*	58 */		 this.maxPlayers = 8;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(INetworkManager inetworkmanager, EntityPlayer entityplayer) {
/*	62 */		 a(entityplayer);
/*	63 */		 entityplayer.spawnIn(this.server.getWorldServer(entityplayer.dimension));
/*	64 */		 entityplayer.itemInWorldManager.a((WorldServer)entityplayer.world);
/*	65 */		 String s = "local";
/*		 */ 
/*	67 */		 if (inetworkmanager.getSocketAddress() != null) {
/*	68 */			 s = inetworkmanager.getSocketAddress().toString();
/*		 */		 }
/*		 */ 
/*	72 */		 a.info(entityplayer.name + "[" + s + "] logged in with entity id " + entityplayer.id + " at ([" + entityplayer.world.worldData.getName() + "] " + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ")");
/*	73 */		 WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);
/*	74 */		 ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
/*		 */ 
/*	76 */		 a(entityplayer, (EntityPlayer)null, worldserver);
/*	77 */		 NetServerHandler netserverhandler = new NetServerHandler(this.server, inetworkmanager, entityplayer);
/*		 */ 
/*	80 */		 int maxPlayers = getMaxPlayers();
/*	81 */		 if (maxPlayers > 60) {
/*	82 */			 maxPlayers = 60;
/*		 */		 }
/*	84 */		 netserverhandler.sendPacket(new Packet1Login(entityplayer.id, worldserver.getWorldData().getType(), entityplayer.itemInWorldManager.getGameMode(), worldserver.getWorldData().isHardcore(), worldserver.worldProvider.dimension, worldserver.difficulty, worldserver.getHeight(), maxPlayers));
/*	85 */		 entityplayer.getBukkitEntity().sendSupportedChannels();
/*		 */ 
/*	88 */		 netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z));
/*	89 */		 netserverhandler.sendPacket(new Packet202Abilities(entityplayer.abilities));
/*	90 */		 b(entityplayer, worldserver);
/*		 */ 
/*	92 */		 c(entityplayer);
/*	93 */		 netserverhandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
/*	94 */		 this.server.ac().a(netserverhandler);
/*	95 */		 netserverhandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
/*	96 */		 if (this.server.getTexturePack().length() > 0) {
/*	97 */			 entityplayer.a(this.server.getTexturePack(), this.server.R());
/*		 */		 }
/*		 */ 
/* 100 */		 Iterator iterator = entityplayer.getEffects().iterator();
/*		 */ 
/* 102 */		 while (iterator.hasNext()) {
/* 103 */			 MobEffect mobeffect = (MobEffect)iterator.next();
/*		 */ 
/* 105 */			 netserverhandler.sendPacket(new Packet41MobEffect(entityplayer.id, mobeffect));
/*		 */		 }
/*		 */ 
/* 108 */		 entityplayer.syncInventory();
/*		 */	 }
/*		 */ 
/*		 */	 public void setPlayerFileData(WorldServer[] aworldserver) {
/* 112 */		 if (this.playerFileData != null) return;
/* 113 */		 this.playerFileData = aworldserver[0].getDataManager().getPlayerFileData();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityPlayer entityplayer, WorldServer worldserver) {
/* 117 */		 WorldServer worldserver1 = entityplayer.q();
/*		 */ 
/* 119 */		 if (worldserver != null) {
/* 120 */			 worldserver.getPlayerManager().removePlayer(entityplayer);
/*		 */		 }
/*		 */ 
/* 123 */		 worldserver1.getPlayerManager().addPlayer(entityplayer);
/* 124 */		 worldserver1.chunkProviderServer.getChunkAt((int)entityplayer.locX >> 4, (int)entityplayer.locZ >> 4);
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/* 128 */		 return PlayerManager.getFurthestViewableBlock(o());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(EntityPlayer entityplayer) {
/* 132 */		 NBTTagCompound nbttagcompound = ((WorldServer)this.server.worlds.get(0)).getWorldData().h();
/*		 */ 
/* 134 */		 if ((entityplayer.getName().equals(this.server.G())) && (nbttagcompound != null))
/* 135 */			 entityplayer.e(nbttagcompound);
/*		 */		 else
/* 137 */			 this.playerFileData.load(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 protected void b(EntityPlayer entityplayer)
/*		 */	 {
/* 142 */		 this.playerFileData.save(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 public void c(EntityPlayer entityplayer) {
/* 146 */		 this.cserver.detectListNameConflict(entityplayer);
/*		 */ 
/* 148 */		 this.players.add(entityplayer);
/* 149 */		 WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);
/*		 */ 
/* 152 */		 if (!this.cserver.useExactLoginLocation()) {
/* 153 */			 while (!worldserver.getCubes(entityplayer, entityplayer.boundingBox).isEmpty()) {
/* 154 */				 entityplayer.setPosition(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ);
/*		 */			 }
/*		 */		 }
/* 157 */		 entityplayer.setPosition(entityplayer.locX, entityplayer.locY + entityplayer.getBukkitEntity().getEyeHeight(), entityplayer.locZ);
/*		 */ 
/* 160 */		 PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(this.cserver.getPlayer(entityplayer), "§e" + entityplayer.name + " joined the game.");
/* 161 */		 this.cserver.getPluginManager().callEvent(playerJoinEvent);
/*		 */ 
/* 163 */		 String joinMessage = playerJoinEvent.getJoinMessage();
/*		 */ 
/* 165 */		 if ((joinMessage != null) && (joinMessage.length() > 0)) {
/* 166 */			 this.server.getServerConfigurationManager().sendAll(new Packet3Chat(joinMessage));
/*		 */		 }
/* 168 */		 this.cserver.onPlayerJoin(playerJoinEvent.getPlayer());
/*		 */ 
/* 171 */		 worldserver.addEntity(entityplayer);
/* 172 */		 a(entityplayer, (WorldServer)null);
/* 173 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 176 */		 Packet201PlayerInfo packet = new Packet201PlayerInfo(entityplayer.listName, true, 1000);
/* 177 */		 for (int i = 0; i < this.players.size(); i++) {
/* 178 */			 EntityPlayer entityplayer1 = (EntityPlayer)this.players.get(i);
/*		 */ 
/* 180 */			 if (entityplayer1.getBukkitEntity().canSee(entityplayer.getBukkitEntity())) {
/* 181 */				 entityplayer1.netServerHandler.sendPacket(packet);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 186 */		 while (iterator.hasNext()) {
/* 187 */			 EntityPlayer entityplayer1 = (EntityPlayer)iterator.next();
/*		 */ 
/* 190 */			 if (entityplayer.getBukkitEntity().canSee(entityplayer1.getBukkitEntity()))
/* 191 */				 entityplayer.netServerHandler.sendPacket(new Packet201PlayerInfo(entityplayer1.listName, true, entityplayer1.ping));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void d(EntityPlayer entityplayer)
/*		 */	 {
/* 198 */		 entityplayer.q().getPlayerManager().movePlayer(entityplayer);
/*		 */	 }
/*		 */ 
/*		 */	 public String disconnect(EntityPlayer entityplayer) {
/* 202 */		 if (entityplayer.netServerHandler.disconnected) return null;
/*		 */ 
/* 205 */		 WorldServer worldserver = entityplayer.q();
/*		 */ 
/* 207 */		 worldserver.kill(entityplayer);
/* 208 */		 worldserver.getPlayerManager().removePlayer(entityplayer);
/* 209 */		 this.players.remove(entityplayer);
/*		 */ 
/* 212 */		 PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(this.cserver.getPlayer(entityplayer), "§e" + entityplayer.name + " left the game.");
/* 213 */		 this.cserver.getPluginManager().callEvent(playerQuitEvent);
/*		 */ 
/* 216 */		 Packet201PlayerInfo packet = new Packet201PlayerInfo(entityplayer.listName, false, 9999);
/* 217 */		 for (int i = 0; i < this.players.size(); i++) {
/* 218 */			 EntityPlayer entityplayer1 = (EntityPlayer)this.players.get(i);
/*		 */ 
/* 220 */			 if (entityplayer1.getBukkitEntity().canSee(entityplayer.getBukkitEntity())) {
/* 221 */				 entityplayer1.netServerHandler.sendPacket(packet);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 225 */		 b(entityplayer);
/*		 */ 
/* 227 */		 return playerQuitEvent.getQuitMessage();
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPlayer attemptLogin(NetLoginHandler netloginhandler, String s, String hostname)
/*		 */	 {
/* 236 */		 EntityPlayer entity = new EntityPlayer(this.server, this.server.getWorldServer(0), s, this.server.L() ? new DemoItemInWorldManager(this.server.getWorldServer(0)) : new ItemInWorldManager(this.server.getWorldServer(0)));
/* 237 */		 Player player = entity.getBukkitEntity();
/* 238 */		 PlayerLoginEvent event = new PlayerLoginEvent(player, hostname, netloginhandler.getSocket().getInetAddress());
/*		 */ 
/* 240 */		 SocketAddress socketaddress = netloginhandler.networkManager.getSocketAddress();
/*		 */ 
/* 242 */		 if (this.banByName.isBanned(s)) {
/* 243 */			 BanEntry banentry = (BanEntry)this.banByName.getEntries().get(s);
/* 244 */			 String s1 = "You are banned from this server!\nReason: " + banentry.getReason();
/*		 */ 
/* 246 */			 if (banentry.getExpires() != null) {
/* 247 */				 s1 = s1 + "\nYour ban will be removed on " + e.format(banentry.getExpires());
/*		 */			 }
/*		 */ 
/* 250 */			 event.disallow(PlayerLoginEvent.Result.KICK_BANNED, s1);
/* 251 */		 } else if (!isWhitelisted(s)) {
/* 252 */			 event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "You are not white-listed on this server!");
/*		 */		 } else {
/* 254 */			 String s2 = socketaddress.toString();
/*		 */ 
/* 256 */			 s2 = s2.substring(s2.indexOf("/") + 1);
/* 257 */			 s2 = s2.substring(0, s2.indexOf(":"));
/* 258 */			 if (this.banByIP.isBanned(s2)) {
/* 259 */				 BanEntry banentry1 = (BanEntry)this.banByIP.getEntries().get(s2);
/* 260 */				 String s3 = "Your IP address is banned from this server!\nReason: " + banentry1.getReason();
/*		 */ 
/* 262 */				 if (banentry1.getExpires() != null) {
/* 263 */					 s3 = s3 + "\nYour ban will be removed on " + e.format(banentry1.getExpires());
/*		 */				 }
/*		 */ 
/* 266 */				 event.disallow(PlayerLoginEvent.Result.KICK_BANNED, s3);
/* 267 */			 } else if (this.players.size() >= this.maxPlayers) {
/* 268 */				 event.disallow(PlayerLoginEvent.Result.KICK_FULL, "The server is full!");
/*		 */			 } else {
/* 270 */				 event.disallow(PlayerLoginEvent.Result.ALLOWED, s2);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 274 */		 this.cserver.getPluginManager().callEvent(event);
/* 275 */		 if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
/* 276 */			 netloginhandler.disconnect(event.getKickMessage());
/* 277 */			 return null;
/*		 */		 }
/*		 */ 
/* 280 */		 return entity;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPlayer processLogin(EntityPlayer player)
/*		 */	 {
/* 285 */		 String s = player.name;
/* 286 */		 ArrayList arraylist = new ArrayList();
/* 287 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 291 */		 while (iterator.hasNext()) {
/* 292 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/* 293 */			 if (entityplayer.name.equalsIgnoreCase(s)) {
/* 294 */				 arraylist.add(entityplayer);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 298 */		 iterator = arraylist.iterator();
/*		 */ 
/* 300 */		 while (iterator.hasNext()) {
/* 301 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/* 302 */			 entityplayer.netServerHandler.disconnect("You logged in from another location");
/*		 */		 }
/*		 */ 
/* 316 */		 return player;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, boolean flag)
/*		 */	 {
/* 322 */		 return moveToWorld(entityplayer, i, flag, null);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, boolean flag, Location location)
/*		 */	 {
/* 327 */		 entityplayer.q().getTracker().untrackPlayer(entityplayer);
/*		 */ 
/* 329 */		 entityplayer.q().getPlayerManager().removePlayer(entityplayer);
/* 330 */		 this.players.remove(entityplayer);
/* 331 */		 this.server.getWorldServer(entityplayer.dimension).removeEntity(entityplayer);
/* 332 */		 ChunkCoordinates chunkcoordinates = entityplayer.getBed();
/*		 */ 
/* 335 */		 EntityPlayer entityplayer1 = entityplayer;
/* 336 */		 org.bukkit.World fromWorld = entityplayer1.getBukkitEntity().getWorld();
/* 337 */		 entityplayer1.viewingCredits = false;
/* 338 */		 entityplayer1.copyTo(entityplayer, flag);
/*		 */ 
/* 342 */		 if (location == null) {
/* 343 */			 boolean isBedSpawn = false;
/* 344 */			 CraftWorld cworld = (CraftWorld)this.server.server.getWorld(entityplayer.spawnWorld);
/* 345 */			 if ((cworld != null) && (chunkcoordinates != null)) {
/* 346 */				 ChunkCoordinates chunkcoordinates1 = EntityHuman.getBed(cworld.getHandle(), chunkcoordinates);
/* 347 */				 if (chunkcoordinates1 != null) {
/* 348 */					 isBedSpawn = true;
/* 349 */					 location = new Location(cworld, chunkcoordinates1.x + 0.5D, chunkcoordinates1.y, chunkcoordinates1.z + 0.5D);
/*		 */				 } else {
/* 351 */					 entityplayer1.setRespawnPosition(null);
/* 352 */					 entityplayer1.netServerHandler.sendPacket(new Packet70Bed(0, 0));
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 356 */			 if (location == null) {
/* 357 */				 cworld = (CraftWorld)this.server.server.getWorlds().get(0);
/* 358 */				 chunkcoordinates = cworld.getHandle().getSpawn();
/* 359 */				 location = new Location(cworld, chunkcoordinates.x + 0.5D, chunkcoordinates.y, chunkcoordinates.z + 0.5D);
/*		 */			 }
/*		 */ 
/* 362 */			 Player respawnPlayer = this.cserver.getPlayer(entityplayer1);
/* 363 */			 PlayerRespawnEvent respawnEvent = new PlayerRespawnEvent(respawnPlayer, location, isBedSpawn);
/* 364 */			 this.cserver.getPluginManager().callEvent(respawnEvent);
/*		 */ 
/* 366 */			 location = respawnEvent.getRespawnLocation();
/* 367 */			 entityplayer.reset();
/*		 */		 } else {
/* 369 */			 location.setWorld(this.server.getWorldServer(i).getWorld());
/*		 */		 }
/* 371 */		 WorldServer worldserver = ((CraftWorld)location.getWorld()).getHandle();
/* 372 */		 entityplayer1.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/*		 */ 
/* 375 */		 worldserver.chunkProviderServer.getChunkAt((int)entityplayer1.locX >> 4, (int)entityplayer1.locZ >> 4);
/*		 */ 
/* 377 */		 while (!worldserver.getCubes(entityplayer1, entityplayer1.boundingBox).isEmpty()) {
/* 378 */			 entityplayer1.setPosition(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
/*		 */		 }
/*		 */ 
/* 382 */		 byte actualDimension = (byte)worldserver.getWorld().getEnvironment().getId();
/*		 */ 
/* 384 */		 entityplayer1.netServerHandler.sendPacket(new Packet9Respawn((byte)(actualDimension >= 0 ? -1 : 0), (byte)worldserver.difficulty, worldserver.getWorldData().getType(), worldserver.getHeight(), entityplayer.itemInWorldManager.getGameMode()));
/* 385 */		 entityplayer1.netServerHandler.sendPacket(new Packet9Respawn(actualDimension, (byte)worldserver.difficulty, worldserver.getWorldData().getType(), worldserver.getHeight(), entityplayer.itemInWorldManager.getGameMode()));
/* 386 */		 entityplayer1.spawnIn(worldserver);
/* 387 */		 entityplayer1.dead = false;
/* 388 */		 entityplayer1.netServerHandler.teleport(new Location(worldserver.getWorld(), entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch));
/*		 */ 
/* 390 */		 ChunkCoordinates chunkcoordinates1 = worldserver.getSpawn();
/*		 */ 
/* 392 */		 entityplayer1.netServerHandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates1.x, chunkcoordinates1.y, chunkcoordinates1.z));
/* 393 */		 b(entityplayer1, worldserver);
/* 394 */		 worldserver.getPlayerManager().addPlayer(entityplayer1);
/* 395 */		 worldserver.addEntity(entityplayer1);
/* 396 */		 this.players.add(entityplayer1);
/*		 */ 
/* 398 */		 updateClient(entityplayer1);
/* 399 */		 Iterator iterator = entityplayer1.getEffects().iterator();
/*		 */ 
/* 401 */		 while (iterator.hasNext()) {
/* 402 */			 MobEffect mobeffect = (MobEffect)iterator.next();
/*		 */ 
/* 404 */			 entityplayer1.netServerHandler.sendPacket(new Packet41MobEffect(entityplayer1.id, mobeffect));
/*		 */		 }
/*		 */ 
/* 410 */		 if (fromWorld != location.getWorld()) {
/* 411 */			 PlayerChangedWorldEvent event = new PlayerChangedWorldEvent(entityplayer1.getBukkitEntity(), fromWorld);
/* 412 */			 Bukkit.getServer().getPluginManager().callEvent(event);
/*		 */		 }
/*		 */ 
/* 416 */		 return entityplayer1;
/*		 */	 }
/*		 */ 
/*		 */	 public void changeDimension(EntityPlayer entityplayer, int i)
/*		 */	 {
/* 421 */		 int dimension = i;
/* 422 */		 WorldServer fromWorld = this.server.getWorldServer(entityplayer.dimension);
/* 423 */		 WorldServer toWorld = null;
/* 424 */		 if (entityplayer.dimension < 10) {
/* 425 */			 for (WorldServer world : this.server.worlds) {
/* 426 */				 if (world.dimension == dimension) {
/* 427 */					 toWorld = world;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 432 */		 Location fromLocation = new Location(fromWorld.getWorld(), entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
/* 433 */		 Location toLocation = null;
/*		 */ 
/* 435 */		 if (toWorld != null) {
/* 436 */			 if (((dimension == -1) || (dimension == 0)) && ((entityplayer.dimension == -1) || (entityplayer.dimension == 0))) {
/* 437 */				 double blockRatio = dimension == 0 ? 8.0D : 0.125D;
/*		 */ 
/* 439 */				 toLocation = toWorld == null ? null : new Location(toWorld.getWorld(), entityplayer.locX * blockRatio, entityplayer.locY, entityplayer.locZ * blockRatio, entityplayer.yaw, entityplayer.pitch);
/*		 */			 } else {
/* 441 */				 ChunkCoordinates coords = toWorld.getDimensionSpawn();
/* 442 */				 if (coords != null) {
/* 443 */					 toLocation = new Location(toWorld.getWorld(), coords.x, coords.y, coords.z, 90.0F, 0.0F);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 448 */		 PlayerTeleportEvent.TeleportCause cause = PlayerTeleportEvent.TeleportCause.UNKNOWN;
/* 449 */		 int playerEnvironmentId = entityplayer.getBukkitEntity().getWorld().getEnvironment().getId();
/* 450 */		 switch (dimension) {
/*		 */		 case -1:
/* 452 */			 cause = PlayerTeleportEvent.TeleportCause.NETHER_PORTAL;
/* 453 */			 break;
/*		 */		 case 0:
/* 455 */			 if (playerEnvironmentId == -1) {
/* 456 */				 cause = PlayerTeleportEvent.TeleportCause.NETHER_PORTAL; } else {
/* 457 */				 if (playerEnvironmentId != 1) break;
/* 458 */				 cause = PlayerTeleportEvent.TeleportCause.END_PORTAL; } break;
/*		 */		 case 1:
/* 463 */			 cause = PlayerTeleportEvent.TeleportCause.END_PORTAL;
/*		 */		 }
/*		 */ 
/* 467 */		 PortalTravelAgent pta = new PortalTravelAgent();
/* 468 */		 PlayerPortalEvent event = new PlayerPortalEvent(entityplayer.getBukkitEntity(), fromLocation, toLocation, pta, cause);
/*		 */ 
/* 470 */		 if (entityplayer.dimension == 1) {
/* 471 */			 event.useTravelAgent(false);
/*		 */		 }
/*		 */ 
/* 474 */		 Bukkit.getServer().getPluginManager().callEvent(event);
/* 475 */		 if ((event.isCancelled()) || (event.getTo() == null)) {
/* 476 */			 return;
/*		 */		 }
/*		 */ 
/* 479 */		 Location finalLocation = event.getTo();
/* 480 */		 if (event.useTravelAgent()) {
/* 481 */			 finalLocation = event.getPortalTravelAgent().findOrCreate(finalLocation);
/*		 */		 }
/* 483 */		 toWorld = ((CraftWorld)finalLocation.getWorld()).getHandle();
/* 484 */		 moveToWorld(entityplayer, toWorld.dimension, true, finalLocation);
/*		 */	 }
/*		 */ 
/*		 */	 public void tick()
/*		 */	 {
/* 489 */		 if (++this.o > 600)
/* 490 */			 this.o = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void sendAll(Packet packet)
/*		 */	 {
/* 503 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 505 */		 while (iterator.hasNext()) {
/* 506 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 508 */			 entityplayer.netServerHandler.sendPacket(packet);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet packet, int i) {
/* 513 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 515 */		 while (iterator.hasNext()) {
/* 516 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 518 */			 if (entityplayer.dimension == i)
/* 519 */				 entityplayer.netServerHandler.sendPacket(packet);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public String c()
/*		 */	 {
/* 525 */		 String s = "";
/*		 */ 
/* 527 */		 for (int i = 0; i < this.players.size(); i++) {
/* 528 */			 if (i > 0) {
/* 529 */				 s = s + ", ";
/*		 */			 }
/*		 */ 
/* 532 */			 s = s + ((EntityPlayer)this.players.get(i)).name;
/*		 */		 }
/*		 */ 
/* 535 */		 return s;
/*		 */	 }
/*		 */ 
/*		 */	 public String[] d() {
/* 539 */		 String[] astring = new String[this.players.size()];
/*		 */ 
/* 541 */		 for (int i = 0; i < this.players.size(); i++) {
/* 542 */			 astring[i] = ((EntityPlayer)this.players.get(i)).name;
/*		 */		 }
/*		 */ 
/* 545 */		 return astring;
/*		 */	 }
/*		 */ 
/*		 */	 public BanList getNameBans() {
/* 549 */		 return this.banByName;
/*		 */	 }
/*		 */ 
/*		 */	 public BanList getIPBans() {
/* 553 */		 return this.banByIP;
/*		 */	 }
/*		 */ 
/*		 */	 public void addOp(String s) {
/* 557 */		 this.operators.add(s.toLowerCase());
/*		 */ 
/* 560 */		 Player player = this.server.server.getPlayer(s);
/* 561 */		 if (player != null)
/* 562 */			 player.recalculatePermissions();
/*		 */	 }
/*		 */ 
/*		 */	 public void removeOp(String s)
/*		 */	 {
/* 568 */		 this.operators.remove(s.toLowerCase());
/*		 */ 
/* 571 */		 Player player = this.server.server.getPlayer(s);
/* 572 */		 if (player != null)
/* 573 */			 player.recalculatePermissions();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isWhitelisted(String s)
/*		 */	 {
/* 579 */		 s = s.trim().toLowerCase();
/* 580 */		 return (!this.hasWhitelist) || (this.operators.contains(s)) || (this.whitelist.contains(s));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isOp(String s)
/*		 */	 {
/* 585 */		 return (this.operators.contains(s.trim().toLowerCase())) || ((this.server.H()) && (((WorldServer)this.server.worlds.get(0)).getWorldData().allowCommands()) && (this.server.G().equalsIgnoreCase(s))) || (this.n);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityPlayer f(String s) {
/* 589 */		 Iterator iterator = this.players.iterator();
/*		 */		 EntityPlayer entityplayer;
/*		 */		 do {
/* 594 */			 if (!iterator.hasNext()) {
/* 595 */				 return null;
/*		 */			 }
/*		 */ 
/* 598 */			 entityplayer = (EntityPlayer)iterator.next();
/* 599 */		 }while (!entityplayer.name.equalsIgnoreCase(s));
/*		 */ 
/* 601 */		 return entityplayer;
/*		 */	 }
/*		 */ 
/*		 */	 public void sendPacketNearby(double d0, double d1, double d2, double d3, int i, Packet packet) {
/* 605 */		 sendPacketNearby((EntityHuman)null, d0, d1, d2, d3, i, packet);
/*		 */	 }
/*		 */ 
/*		 */	 public void sendPacketNearby(EntityHuman entityhuman, double d0, double d1, double d2, double d3, int i, Packet packet) {
/* 609 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 611 */		 while (iterator.hasNext()) {
/* 612 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 614 */			 if ((entityplayer != entityhuman) && (entityplayer.dimension == i)) {
/* 615 */				 double d4 = d0 - entityplayer.locX;
/* 616 */				 double d5 = d1 - entityplayer.locY;
/* 617 */				 double d6 = d2 - entityplayer.locZ;
/*		 */ 
/* 619 */				 if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3)
/* 620 */					 entityplayer.netServerHandler.sendPacket(packet);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void savePlayers()
/*		 */	 {
/* 627 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 629 */		 while (iterator.hasNext()) {
/* 630 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 632 */			 b(entityplayer);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void addWhitelist(String s) {
/* 637 */		 this.whitelist.add(s);
/*		 */	 }
/*		 */ 
/*		 */	 public void removeWhitelist(String s) {
/* 641 */		 this.whitelist.remove(s);
/*		 */	 }
/*		 */ 
/*		 */	 public Set getWhitelisted() {
/* 645 */		 return this.whitelist;
/*		 */	 }
/*		 */ 
/*		 */	 public Set getOPs() {
/* 649 */		 return this.operators;
/*		 */	 }
/*		 */	 public void reloadWhitelist() {
/*		 */	 }
/*		 */ 
/*		 */	 public void b(EntityPlayer entityplayer, WorldServer worldserver) {
/* 655 */		 entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
/* 656 */		 if (worldserver.J())
/* 657 */			 entityplayer.netServerHandler.sendPacket(new Packet70Bed(1, 0));
/*		 */	 }
/*		 */ 
/*		 */	 public void updateClient(EntityPlayer entityplayer)
/*		 */	 {
/* 662 */		 entityplayer.updateInventory(entityplayer.defaultContainer);
/* 663 */		 entityplayer.n();
/*		 */	 }
/*		 */ 
/*		 */	 public int getPlayerCount() {
/* 667 */		 return this.players.size();
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxPlayers() {
/* 671 */		 return this.maxPlayers;
/*		 */	 }
/*		 */ 
/*		 */	 public String[] getSeenPlayers() {
/* 675 */		 return ((WorldServer)this.server.worlds.get(0)).getDataManager().getPlayerFileData().getSeenPlayers();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean getHasWhitelist() {
/* 679 */		 return this.hasWhitelist;
/*		 */	 }
/*		 */ 
/*		 */	 public void setHasWhitelist(boolean flag) {
/* 683 */		 this.hasWhitelist = flag;
/*		 */	 }
/*		 */ 
/*		 */	 public List j(String s) {
/* 687 */		 ArrayList arraylist = new ArrayList();
/* 688 */		 Iterator iterator = this.players.iterator();
/*		 */ 
/* 690 */		 while (iterator.hasNext()) {
/* 691 */			 EntityPlayer entityplayer = (EntityPlayer)iterator.next();
/*		 */ 
/* 693 */			 if (entityplayer.r().equals(s)) {
/* 694 */				 arraylist.add(entityplayer);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 698 */		 return arraylist;
/*		 */	 }
/*		 */ 
/*		 */	 public int o() {
/* 702 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public MinecraftServer getServer() {
/* 706 */		 return this.server;
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound q() {
/* 710 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(EntityPlayer entityplayer, EntityPlayer entityplayer1, World world) {
/* 714 */		 if (entityplayer1 != null)
/* 715 */			 entityplayer.itemInWorldManager.setGameMode(entityplayer1.itemInWorldManager.getGameMode());
/* 716 */		 else if (this.m != null) {
/* 717 */			 entityplayer.itemInWorldManager.setGameMode(this.m);
/*		 */		 }
/*		 */ 
/* 720 */		 entityplayer.itemInWorldManager.b(world.getWorldData().getGameType());
/*		 */	 }
/*		 */ 
/*		 */	 public void r() {
/* 724 */		 while (!this.players.isEmpty())
/* 725 */			 ((EntityPlayer)this.players.get(0)).netServerHandler.disconnect("Server closed");
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ServerConfigurationManagerAbstract
 * JD-Core Version:		0.6.0
 */