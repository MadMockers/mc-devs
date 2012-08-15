package net.minecraft.server;

import java.io.File;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.TravelAgent;
import org.bukkit.World.Environment;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.PortalTravelAgent;
import org.bukkit.craftbukkit.command.ColouredConsoleSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.PluginManager;

public abstract class ServerConfigurationManagerAbstract
{
	private static final SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	public static final Logger a = Logger.getLogger("Minecraft");
	private final MinecraftServer server;
	public final List players = new CopyOnWriteArrayList();
	private final BanList banByName = new BanList(new File("banned-players.txt"));
	private final BanList banByIP = new BanList(new File("banned-ips.txt"));
	private Set operators = new HashSet();
	private Set whitelist = new LinkedHashSet();
	public PlayerFileData playerFileData;
	public boolean hasWhitelist;
	protected int maxPlayers;
	protected int d;
	private EnumGamemode m;
	private boolean n;
	private int o = 0;
	private CraftServer cserver;

	public ServerConfigurationManagerAbstract(MinecraftServer minecraftserver)
	{
		minecraftserver.server = new CraftServer(minecraftserver, this);
		minecraftserver.console = ColouredConsoleSender.getInstance();
		this.cserver = minecraftserver.server;

		this.server = minecraftserver;
		this.banByName.setEnabled(false);
		this.banByIP.setEnabled(false);
		this.maxPlayers = 8;
	}

	public void a(INetworkManager inetworkmanager, EntityPlayer entityplayer) {
		a(entityplayer);
		entityplayer.spawnIn(this.server.getWorldServer(entityplayer.dimension));
		entityplayer.itemInWorldManager.a((WorldServer)entityplayer.world);
		String s = "local";

		if (inetworkmanager.getSocketAddress() != null) {
			s = inetworkmanager.getSocketAddress().toString();
		}

		a.info(entityplayer.name + "[" + s + "] logged in with entity id " + entityplayer.id + " at ([" + entityplayer.world.worldData.getName() + "] " + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ")");
		WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);
		ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

		a(entityplayer, (EntityPlayer)null, worldserver);
		NetServerHandler netserverhandler = new NetServerHandler(this.server, inetworkmanager, entityplayer);

		int maxPlayers = getMaxPlayers();
		if (maxPlayers > 60) {
			maxPlayers = 60;
		}
		netserverhandler.sendPacket(new Packet1Login(entityplayer.id, worldserver.getWorldData().getType(), entityplayer.itemInWorldManager.getGameMode(), worldserver.getWorldData().isHardcore(), worldserver.worldProvider.dimension, worldserver.difficulty, worldserver.getHeight(), maxPlayers));
		entityplayer.getBukkitEntity().sendSupportedChannels();

		netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z));
		netserverhandler.sendPacket(new Packet202Abilities(entityplayer.abilities));
		b(entityplayer, worldserver);

		c(entityplayer);
		netserverhandler.a(entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
		this.server.ac().a(netserverhandler);
		netserverhandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
		if (this.server.getTexturePack().length() > 0) {
			entityplayer.a(this.server.getTexturePack(), this.server.R());
		}

		Iterator iterator = entityplayer.getEffects().iterator();

		while (iterator.hasNext()) {
			MobEffect mobeffect = (MobEffect)iterator.next();

			netserverhandler.sendPacket(new Packet41MobEffect(entityplayer.id, mobeffect));
		}

		entityplayer.syncInventory();
	}

	public void setPlayerFileData(WorldServer[] aworldserver) {
		if (this.playerFileData != null) return;
		this.playerFileData = aworldserver[0].getDataManager().getPlayerFileData();
	}

	public void a(EntityPlayer entityplayer, WorldServer worldserver) {
		WorldServer worldserver1 = entityplayer.q();

		if (worldserver != null) {
			worldserver.getPlayerManager().removePlayer(entityplayer);
		}

		worldserver1.getPlayerManager().addPlayer(entityplayer);
		worldserver1.chunkProviderServer.getChunkAt((int)entityplayer.locX >> 4, (int)entityplayer.locZ >> 4);
	}

	public int a() {
		return PlayerManager.getFurthestViewableBlock(o());
	}

	public void a(EntityPlayer entityplayer) {
		NBTTagCompound nbttagcompound = ((WorldServer)this.server.worlds.get(0)).getWorldData().h();

		if ((entityplayer.getName().equals(this.server.G())) && (nbttagcompound != null))
			entityplayer.e(nbttagcompound);
		else
			this.playerFileData.load(entityplayer);
	}

	protected void b(EntityPlayer entityplayer)
	{
		this.playerFileData.save(entityplayer);
	}

	public void c(EntityPlayer entityplayer) {
		this.cserver.detectListNameConflict(entityplayer);

		this.players.add(entityplayer);
		WorldServer worldserver = this.server.getWorldServer(entityplayer.dimension);

		if (!this.cserver.useExactLoginLocation()) {
			while (!worldserver.getCubes(entityplayer, entityplayer.boundingBox).isEmpty()) {
				entityplayer.setPosition(entityplayer.locX, entityplayer.locY + 1.0D, entityplayer.locZ);
			}
		}
		entityplayer.setPosition(entityplayer.locX, entityplayer.locY + entityplayer.getBukkitEntity().getEyeHeight(), entityplayer.locZ);

		PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(this.cserver.getPlayer(entityplayer), "§e" + entityplayer.name + " joined the game.");
		this.cserver.getPluginManager().callEvent(playerJoinEvent);

		String joinMessage = playerJoinEvent.getJoinMessage();

		if ((joinMessage != null) && (joinMessage.length() > 0)) {
			this.server.getServerConfigurationManager().sendAll(new Packet3Chat(joinMessage));
		}
		this.cserver.onPlayerJoin(playerJoinEvent.getPlayer());

		worldserver.addEntity(entityplayer);
		a(entityplayer, (WorldServer)null);
		Iterator iterator = this.players.iterator();

		Packet201PlayerInfo packet = new Packet201PlayerInfo(entityplayer.listName, true, 1000);
		for (int i = 0; i < this.players.size(); i++) {
			EntityPlayer entityplayer1 = (EntityPlayer)this.players.get(i);

			if (entityplayer1.getBukkitEntity().canSee(entityplayer.getBukkitEntity())) {
				entityplayer1.netServerHandler.sendPacket(packet);
			}

		}

		while (iterator.hasNext()) {
			EntityPlayer entityplayer1 = (EntityPlayer)iterator.next();

			if (entityplayer.getBukkitEntity().canSee(entityplayer1.getBukkitEntity()))
				entityplayer.netServerHandler.sendPacket(new Packet201PlayerInfo(entityplayer1.listName, true, entityplayer1.ping));
		}
	}

	public void d(EntityPlayer entityplayer)
	{
		entityplayer.q().getPlayerManager().movePlayer(entityplayer);
	}

	public String disconnect(EntityPlayer entityplayer) {
		if (entityplayer.netServerHandler.disconnected) return null;

		WorldServer worldserver = entityplayer.q();

		worldserver.kill(entityplayer);
		worldserver.getPlayerManager().removePlayer(entityplayer);
		this.players.remove(entityplayer);

		PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(this.cserver.getPlayer(entityplayer), "§e" + entityplayer.name + " left the game.");
		this.cserver.getPluginManager().callEvent(playerQuitEvent);

		Packet201PlayerInfo packet = new Packet201PlayerInfo(entityplayer.listName, false, 9999);
		for (int i = 0; i < this.players.size(); i++) {
			EntityPlayer entityplayer1 = (EntityPlayer)this.players.get(i);

			if (entityplayer1.getBukkitEntity().canSee(entityplayer.getBukkitEntity())) {
				entityplayer1.netServerHandler.sendPacket(packet);
			}
		}

		b(entityplayer);

		return playerQuitEvent.getQuitMessage();
	}

	public EntityPlayer attemptLogin(NetLoginHandler netloginhandler, String s, String hostname)
	{
		EntityPlayer entity = new EntityPlayer(this.server, this.server.getWorldServer(0), s, this.server.L() ? new DemoItemInWorldManager(this.server.getWorldServer(0)) : new ItemInWorldManager(this.server.getWorldServer(0)));
		Player player = entity.getBukkitEntity();
		PlayerLoginEvent event = new PlayerLoginEvent(player, hostname, netloginhandler.getSocket().getInetAddress());

		SocketAddress socketaddress = netloginhandler.networkManager.getSocketAddress();

		if (this.banByName.isBanned(s)) {
			BanEntry banentry = (BanEntry)this.banByName.getEntries().get(s);
			String s1 = "You are banned from this server!\nReason: " + banentry.getReason();

			if (banentry.getExpires() != null) {
				s1 = s1 + "\nYour ban will be removed on " + e.format(banentry.getExpires());
			}

			event.disallow(PlayerLoginEvent.Result.KICK_BANNED, s1);
		} else if (!isWhitelisted(s)) {
			event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "You are not white-listed on this server!");
		} else {
			String s2 = socketaddress.toString();

			s2 = s2.substring(s2.indexOf("/") + 1);
			s2 = s2.substring(0, s2.indexOf(":"));
			if (this.banByIP.isBanned(s2)) {
				BanEntry banentry1 = (BanEntry)this.banByIP.getEntries().get(s2);
				String s3 = "Your IP address is banned from this server!\nReason: " + banentry1.getReason();

				if (banentry1.getExpires() != null) {
					s3 = s3 + "\nYour ban will be removed on " + e.format(banentry1.getExpires());
				}

				event.disallow(PlayerLoginEvent.Result.KICK_BANNED, s3);
			} else if (this.players.size() >= this.maxPlayers) {
				event.disallow(PlayerLoginEvent.Result.KICK_FULL, "The server is full!");
			} else {
				event.disallow(PlayerLoginEvent.Result.ALLOWED, s2);
			}
		}

		this.cserver.getPluginManager().callEvent(event);
		if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
			netloginhandler.disconnect(event.getKickMessage());
			return null;
		}

		return entity;
	}

	public EntityPlayer processLogin(EntityPlayer player)
	{
		String s = player.name;
		ArrayList arraylist = new ArrayList();
		Iterator iterator = this.players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();
			if (entityplayer.name.equalsIgnoreCase(s)) {
				arraylist.add(entityplayer);
			}
		}

		iterator = arraylist.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();
			entityplayer.netServerHandler.disconnect("You logged in from another location");
		}

		return player;
	}

	public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, boolean flag)
	{
		return moveToWorld(entityplayer, i, flag, null);
	}

	public EntityPlayer moveToWorld(EntityPlayer entityplayer, int i, boolean flag, Location location)
	{
		entityplayer.q().getTracker().untrackPlayer(entityplayer);

		entityplayer.q().getPlayerManager().removePlayer(entityplayer);
		this.players.remove(entityplayer);
		this.server.getWorldServer(entityplayer.dimension).removeEntity(entityplayer);
		ChunkCoordinates chunkcoordinates = entityplayer.getBed();

		EntityPlayer entityplayer1 = entityplayer;
		org.bukkit.World fromWorld = entityplayer1.getBukkitEntity().getWorld();
		entityplayer1.viewingCredits = false;
		entityplayer1.copyTo(entityplayer, flag);

		if (location == null) {
			boolean isBedSpawn = false;
			CraftWorld cworld = (CraftWorld)this.server.server.getWorld(entityplayer.spawnWorld);
			if ((cworld != null) && (chunkcoordinates != null)) {
				ChunkCoordinates chunkcoordinates1 = EntityHuman.getBed(cworld.getHandle(), chunkcoordinates);
				if (chunkcoordinates1 != null) {
					isBedSpawn = true;
					location = new Location(cworld, chunkcoordinates1.x + 0.5D, chunkcoordinates1.y, chunkcoordinates1.z + 0.5D);
				} else {
					entityplayer1.setRespawnPosition(null);
					entityplayer1.netServerHandler.sendPacket(new Packet70Bed(0, 0));
				}
			}

			if (location == null) {
				cworld = (CraftWorld)this.server.server.getWorlds().get(0);
				chunkcoordinates = cworld.getHandle().getSpawn();
				location = new Location(cworld, chunkcoordinates.x + 0.5D, chunkcoordinates.y, chunkcoordinates.z + 0.5D);
			}

			Player respawnPlayer = this.cserver.getPlayer(entityplayer1);
			PlayerRespawnEvent respawnEvent = new PlayerRespawnEvent(respawnPlayer, location, isBedSpawn);
			this.cserver.getPluginManager().callEvent(respawnEvent);

			location = respawnEvent.getRespawnLocation();
			entityplayer.reset();
		} else {
			location.setWorld(this.server.getWorldServer(i).getWorld());
		}
		WorldServer worldserver = ((CraftWorld)location.getWorld()).getHandle();
		entityplayer1.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

		worldserver.chunkProviderServer.getChunkAt((int)entityplayer1.locX >> 4, (int)entityplayer1.locZ >> 4);

		while (!worldserver.getCubes(entityplayer1, entityplayer1.boundingBox).isEmpty()) {
			entityplayer1.setPosition(entityplayer1.locX, entityplayer1.locY + 1.0D, entityplayer1.locZ);
		}

		byte actualDimension = (byte)worldserver.getWorld().getEnvironment().getId();

		entityplayer1.netServerHandler.sendPacket(new Packet9Respawn((byte)(actualDimension >= 0 ? -1 : 0), (byte)worldserver.difficulty, worldserver.getWorldData().getType(), worldserver.getHeight(), entityplayer.itemInWorldManager.getGameMode()));
		entityplayer1.netServerHandler.sendPacket(new Packet9Respawn(actualDimension, (byte)worldserver.difficulty, worldserver.getWorldData().getType(), worldserver.getHeight(), entityplayer.itemInWorldManager.getGameMode()));
		entityplayer1.spawnIn(worldserver);
		entityplayer1.dead = false;
		entityplayer1.netServerHandler.teleport(new Location(worldserver.getWorld(), entityplayer1.locX, entityplayer1.locY, entityplayer1.locZ, entityplayer1.yaw, entityplayer1.pitch));

		ChunkCoordinates chunkcoordinates1 = worldserver.getSpawn();

		entityplayer1.netServerHandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates1.x, chunkcoordinates1.y, chunkcoordinates1.z));
		b(entityplayer1, worldserver);
		worldserver.getPlayerManager().addPlayer(entityplayer1);
		worldserver.addEntity(entityplayer1);
		this.players.add(entityplayer1);

		updateClient(entityplayer1);
		Iterator iterator = entityplayer1.getEffects().iterator();

		while (iterator.hasNext()) {
			MobEffect mobeffect = (MobEffect)iterator.next();

			entityplayer1.netServerHandler.sendPacket(new Packet41MobEffect(entityplayer1.id, mobeffect));
		}

		if (fromWorld != location.getWorld()) {
			PlayerChangedWorldEvent event = new PlayerChangedWorldEvent(entityplayer1.getBukkitEntity(), fromWorld);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}

		return entityplayer1;
	}

	public void changeDimension(EntityPlayer entityplayer, int i)
	{
		int dimension = i;
		WorldServer fromWorld = this.server.getWorldServer(entityplayer.dimension);
		WorldServer toWorld = null;
		if (entityplayer.dimension < 10) {
			for (WorldServer world : this.server.worlds) {
				if (world.dimension == dimension) {
					toWorld = world;
				}
			}
		}

		Location fromLocation = new Location(fromWorld.getWorld(), entityplayer.locX, entityplayer.locY, entityplayer.locZ, entityplayer.yaw, entityplayer.pitch);
		Location toLocation = null;

		if (toWorld != null) {
			if (((dimension == -1) || (dimension == 0)) && ((entityplayer.dimension == -1) || (entityplayer.dimension == 0))) {
				double blockRatio = dimension == 0 ? 8.0D : 0.125D;

				toLocation = toWorld == null ? null : new Location(toWorld.getWorld(), entityplayer.locX * blockRatio, entityplayer.locY, entityplayer.locZ * blockRatio, entityplayer.yaw, entityplayer.pitch);
			} else {
				ChunkCoordinates coords = toWorld.getDimensionSpawn();
				if (coords != null) {
					toLocation = new Location(toWorld.getWorld(), coords.x, coords.y, coords.z, 90.0F, 0.0F);
				}
			}
		}

		PlayerTeleportEvent.TeleportCause cause = PlayerTeleportEvent.TeleportCause.UNKNOWN;
		int playerEnvironmentId = entityplayer.getBukkitEntity().getWorld().getEnvironment().getId();
		switch (dimension) {
		case -1:
			cause = PlayerTeleportEvent.TeleportCause.NETHER_PORTAL;
			break;
		case 0:
			if (playerEnvironmentId == -1) {
				cause = PlayerTeleportEvent.TeleportCause.NETHER_PORTAL; } else {
				if (playerEnvironmentId != 1) break;
				cause = PlayerTeleportEvent.TeleportCause.END_PORTAL; } break;
		case 1:
			cause = PlayerTeleportEvent.TeleportCause.END_PORTAL;
		}

		PortalTravelAgent pta = new PortalTravelAgent();
		PlayerPortalEvent event = new PlayerPortalEvent(entityplayer.getBukkitEntity(), fromLocation, toLocation, pta, cause);

		if (entityplayer.dimension == 1) {
			event.useTravelAgent(false);
		}

		Bukkit.getServer().getPluginManager().callEvent(event);
		if ((event.isCancelled()) || (event.getTo() == null)) {
			return;
		}

		Location finalLocation = event.getTo();
		if (event.useTravelAgent()) {
			finalLocation = event.getPortalTravelAgent().findOrCreate(finalLocation);
		}
		toWorld = ((CraftWorld)finalLocation.getWorld()).getHandle();
		moveToWorld(entityplayer, toWorld.dimension, true, finalLocation);
	}

	public void tick()
	{
		if (++this.o > 600)
			this.o = 0;
	}

	public void sendAll(Packet packet)
	{
		Iterator iterator = this.players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			entityplayer.netServerHandler.sendPacket(packet);
		}
	}

	public void a(Packet packet, int i) {
		Iterator iterator = this.players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			if (entityplayer.dimension == i)
				entityplayer.netServerHandler.sendPacket(packet);
		}
	}

	public String c()
	{
		String s = "";

		for (int i = 0; i < this.players.size(); i++) {
			if (i > 0) {
				s = s + ", ";
			}

			s = s + ((EntityPlayer)this.players.get(i)).name;
		}

		return s;
	}

	public String[] d() {
		String[] astring = new String[this.players.size()];

		for (int i = 0; i < this.players.size(); i++) {
			astring[i] = ((EntityPlayer)this.players.get(i)).name;
		}

		return astring;
	}

	public BanList getNameBans() {
		return this.banByName;
	}

	public BanList getIPBans() {
		return this.banByIP;
	}

	public void addOp(String s) {
		this.operators.add(s.toLowerCase());

		Player player = this.server.server.getPlayer(s);
		if (player != null)
			player.recalculatePermissions();
	}

	public void removeOp(String s)
	{
		this.operators.remove(s.toLowerCase());

		Player player = this.server.server.getPlayer(s);
		if (player != null)
			player.recalculatePermissions();
	}

	public boolean isWhitelisted(String s)
	{
		s = s.trim().toLowerCase();
		return (!this.hasWhitelist) || (this.operators.contains(s)) || (this.whitelist.contains(s));
	}

	public boolean isOp(String s)
	{
		return (this.operators.contains(s.trim().toLowerCase())) || ((this.server.H()) && (((WorldServer)this.server.worlds.get(0)).getWorldData().allowCommands()) && (this.server.G().equalsIgnoreCase(s))) || (this.n);
	}

	public EntityPlayer f(String s) {
		Iterator iterator = this.players.iterator();
		EntityPlayer entityplayer;
		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entityplayer = (EntityPlayer)iterator.next();
		}while (!entityplayer.name.equalsIgnoreCase(s));

		return entityplayer;
	}

	public void sendPacketNearby(double d0, double d1, double d2, double d3, int i, Packet packet) {
		sendPacketNearby((EntityHuman)null, d0, d1, d2, d3, i, packet);
	}

	public void sendPacketNearby(EntityHuman entityhuman, double d0, double d1, double d2, double d3, int i, Packet packet) {
		Iterator iterator = this.players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			if ((entityplayer != entityhuman) && (entityplayer.dimension == i)) {
				double d4 = d0 - entityplayer.locX;
				double d5 = d1 - entityplayer.locY;
				double d6 = d2 - entityplayer.locZ;

				if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3)
					entityplayer.netServerHandler.sendPacket(packet);
			}
		}
	}

	public void savePlayers()
	{
		Iterator iterator = this.players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			b(entityplayer);
		}
	}

	public void addWhitelist(String s) {
		this.whitelist.add(s);
	}

	public void removeWhitelist(String s) {
		this.whitelist.remove(s);
	}

	public Set getWhitelisted() {
		return this.whitelist;
	}

	public Set getOPs() {
		return this.operators;
	}
	public void reloadWhitelist() {
	}

	public void b(EntityPlayer entityplayer, WorldServer worldserver) {
		entityplayer.netServerHandler.sendPacket(new Packet4UpdateTime(worldserver.getTime()));
		if (worldserver.J())
			entityplayer.netServerHandler.sendPacket(new Packet70Bed(1, 0));
	}

	public void updateClient(EntityPlayer entityplayer)
	{
		entityplayer.updateInventory(entityplayer.defaultContainer);
		entityplayer.n();
	}

	public int getPlayerCount() {
		return this.players.size();
	}

	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	public String[] getSeenPlayers() {
		return ((WorldServer)this.server.worlds.get(0)).getDataManager().getPlayerFileData().getSeenPlayers();
	}

	public boolean getHasWhitelist() {
		return this.hasWhitelist;
	}

	public void setHasWhitelist(boolean flag) {
		this.hasWhitelist = flag;
	}

	public List j(String s) {
		ArrayList arraylist = new ArrayList();
		Iterator iterator = this.players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			if (entityplayer.r().equals(s)) {
				arraylist.add(entityplayer);
			}
		}

		return arraylist;
	}

	public int o() {
		return this.d;
	}

	public MinecraftServer getServer() {
		return this.server;
	}

	public NBTTagCompound q() {
		return null;
	}

	private void a(EntityPlayer entityplayer, EntityPlayer entityplayer1, World world) {
		if (entityplayer1 != null)
			entityplayer.itemInWorldManager.setGameMode(entityplayer1.itemInWorldManager.getGameMode());
		else if (this.m != null) {
			entityplayer.itemInWorldManager.setGameMode(this.m);
		}

		entityplayer.itemInWorldManager.b(world.getWorldData().getGameType());
	}

	public void r() {
		while (!this.players.isEmpty())
			((EntityPlayer)this.players.get(0)).netServerHandler.disconnect("Server closed");
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ServerConfigurationManagerAbstract
 * JD-Core Version:		0.6.0
 */