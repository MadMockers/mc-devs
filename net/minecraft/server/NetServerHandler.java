/*			*/ package net.minecraft.server;
/*			*/ 
/*			*/ import java.io.ByteArrayInputStream;
/*			*/ import java.io.DataInputStream;
/*			*/ import java.io.IOException;
/*			*/ import java.io.PrintStream;
/*			*/ import java.io.UnsupportedEncodingException;
/*			*/ import java.util.ArrayList;
/*			*/ import java.util.Arrays;
/*			*/ import java.util.HashSet;
/*			*/ import java.util.Iterator;
/*			*/ import java.util.List;
/*			*/ import java.util.Queue;
/*			*/ import java.util.Random;
/*			*/ import java.util.logging.Level;
/*			*/ import java.util.logging.Logger;
/*			*/ import org.bukkit.Bukkit;
/*			*/ import org.bukkit.ChatColor;
/*			*/ import org.bukkit.Location;
/*			*/ import org.bukkit.Server;
/*			*/ import org.bukkit.command.CommandException;
/*			*/ import org.bukkit.command.ConsoleCommandSender;
/*			*/ import org.bukkit.craftbukkit.ChunkCompressionThread;
/*			*/ import org.bukkit.craftbukkit.CraftServer;
/*			*/ import org.bukkit.craftbukkit.CraftWorld;
/*			*/ import org.bukkit.craftbukkit.PortalTravelAgent;
/*			*/ import org.bukkit.craftbukkit.TextWrapper;
/*			*/ import org.bukkit.craftbukkit.block.CraftBlock;
/*			*/ import org.bukkit.craftbukkit.entity.CraftPlayer;
/*			*/ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*			*/ import org.bukkit.craftbukkit.inventory.CraftInventoryView;
/*			*/ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*			*/ import org.bukkit.craftbukkit.util.LazyPlayerSet;
/*			*/ import org.bukkit.entity.HumanEntity;
/*			*/ import org.bukkit.entity.Player;
/*			*/ import org.bukkit.event.Event.Result;
/*			*/ import org.bukkit.event.HandlerList;
/*			*/ import org.bukkit.event.block.Action;
/*			*/ import org.bukkit.event.block.SignChangeEvent;
/*			*/ import org.bukkit.event.inventory.CraftItemEvent;
/*			*/ import org.bukkit.event.inventory.InventoryClickEvent;
/*			*/ import org.bukkit.event.inventory.InventoryCloseEvent;
/*			*/ import org.bukkit.event.inventory.InventoryType.SlotType;
/*			*/ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*			*/ import org.bukkit.event.player.PlayerAnimationEvent;
/*			*/ import org.bukkit.event.player.PlayerChatEvent;
/*			*/ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*			*/ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*			*/ import org.bukkit.event.player.PlayerInteractEvent;
/*			*/ import org.bukkit.event.player.PlayerItemHeldEvent;
/*			*/ import org.bukkit.event.player.PlayerKickEvent;
/*			*/ import org.bukkit.event.player.PlayerMoveEvent;
/*			*/ import org.bukkit.event.player.PlayerPortalEvent;
/*			*/ import org.bukkit.event.player.PlayerTeleportEvent;
/*			*/ import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
/*			*/ import org.bukkit.event.player.PlayerToggleFlightEvent;
/*			*/ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*			*/ import org.bukkit.event.player.PlayerToggleSprintEvent;
/*			*/ import org.bukkit.inventory.CraftingInventory;
/*			*/ import org.bukkit.inventory.Inventory;
/*			*/ import org.bukkit.inventory.InventoryView;
/*			*/ import org.bukkit.inventory.Recipe;
/*			*/ import org.bukkit.plugin.PluginManager;
/*			*/ import org.bukkit.plugin.messaging.Messenger;
/*			*/ 
/*			*/ public class NetServerHandler extends NetHandler
/*			*/ {
/*	 48 */	 public static Logger logger = Logger.getLogger("Minecraft");
/*			*/	 public INetworkManager networkManager;
/*	 50 */	 public boolean disconnected = false;
/*			*/	 private MinecraftServer minecraftServer;
/*			*/	 public EntityPlayer player;
/*			*/	 private int f;
/*			*/	 private int g;
/*			*/	 private boolean h;
/*			*/	 private int i;
/*			*/	 private long j;
/*	 58 */	 private static Random k = new Random();
/*			*/	 private long l;
/*	 60 */	 private int m = 0;
/*	 61 */	 private int x = 0;
/*			*/	 private double y;
/*			*/	 private double z;
/*			*/	 private double q;
/*	 65 */	 public boolean checkMovement = true;
/*	 66 */	 private IntHashMap s = new IntHashMap();
/*			*/	 private final CraftServer server;
/*	 80 */	 private int lastTick = MinecraftServer.currentTick;
/*	 81 */	 private int lastDropTick = MinecraftServer.currentTick;
/*	 82 */	 private int dropCount = 0;
/*			*/	 private static final int PLACE_DISTANCE_SQUARED = 36;
/*	 86 */	 private double lastPosX = 1.7976931348623157E+308D;
/*	 87 */	 private double lastPosY = 1.7976931348623157E+308D;
/*	 88 */	 private double lastPosZ = 1.7976931348623157E+308D;
/*	 89 */	 private float lastPitch = 3.4028235E+38F;
/*	 90 */	 private float lastYaw = 3.4028235E+38F;
/*	 91 */	 private boolean justTeleported = false;
/*			*/	 Long lastPacket;
/*			*/	 private int lastMaterial;
/*	102 */	 private static final HashSet<Integer> invalidItems = new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(26), Integer.valueOf(34), Integer.valueOf(36), Integer.valueOf(51), Integer.valueOf(52), Integer.valueOf(55), Integer.valueOf(59), Integer.valueOf(60), Integer.valueOf(63), Integer.valueOf(64), Integer.valueOf(68), Integer.valueOf(71), Integer.valueOf(75), Integer.valueOf(78), Integer.valueOf(83), Integer.valueOf(90), Integer.valueOf(92), Integer.valueOf(93), Integer.valueOf(94), Integer.valueOf(95) }));
/*			*/ 
/*			*/	 public NetServerHandler(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayer entityplayer)
/*			*/	 {
/*	 69 */		 this.minecraftServer = minecraftserver;
/*	 70 */		 this.networkManager = inetworkmanager;
/*	 71 */		 inetworkmanager.a(this);
/*	 72 */		 this.player = entityplayer;
/*	 73 */		 entityplayer.netServerHandler = this;
/*			*/ 
/*	 76 */		 this.server = minecraftserver.server;
/*			*/	 }
/*			*/ 
/*			*/	 public CraftPlayer getPlayer()
/*			*/	 {
/*	100 */		 return this.player == null ? null : this.player.getBukkitEntity();
/*			*/	 }
/*			*/ 
/*			*/	 public void d()
/*			*/	 {
/*	106 */		 this.h = false;
/*	107 */		 this.f += 1;
/*			*/ 
/*	109 */		 this.networkManager.b();
/*			*/ 
/*	111 */		 if (this.f - this.l > 20L) {
/*	112 */			 this.l = this.f;
/*	113 */			 this.j = (System.nanoTime() / 1000000L);
/*	114 */			 this.i = k.nextInt();
/*	115 */			 sendPacket(new Packet0KeepAlive(this.i));
/*			*/		 }
/*			*/ 
/*	118 */		 if (this.m > 0) {
/*	119 */			 this.m -= 1;
/*			*/		 }
/*			*/ 
/*	122 */		 if (this.x > 0) {
/*	123 */			 this.x -= 1;
/*			*/		 }
/*			*/ 
/*	127 */		 if ((!this.h) && (!this.player.viewingCredits))
/*	128 */			 this.player.g();
/*			*/	 }
/*			*/ 
/*			*/	 public void disconnect(String s)
/*			*/	 {
/*	135 */		 if (!this.disconnected)
/*			*/		 {
/*	137 */			 String leaveMessage = "§e" + this.player.name + " left the game.";
/*			*/ 
/*	139 */			 PlayerKickEvent event = new PlayerKickEvent(this.server.getPlayer(this.player), s, leaveMessage);
/*	140 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	142 */			 if (event.isCancelled())
/*			*/			 {
/*	144 */				 return;
/*			*/			 }
/*			*/ 
/*	147 */			 s = event.getReason();
/*			*/ 
/*	150 */			 this.player.m();
/*	151 */			 sendPacket(new Packet255KickDisconnect(s));
/*	152 */			 this.networkManager.d();
/*			*/ 
/*	155 */			 leaveMessage = event.getLeaveMessage();
/*	156 */			 if ((leaveMessage != null) && (leaveMessage.length() > 0)) {
/*	157 */				 this.minecraftServer.getServerConfigurationManager().sendAll(new Packet3Chat(leaveMessage));
/*			*/			 }
/*	159 */			 getPlayer().disconnect(s);
/*			*/ 
/*	162 */			 this.minecraftServer.getServerConfigurationManager().disconnect(this.player);
/*	163 */			 this.disconnected = true;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet10Flying packet10flying) {
/*	168 */		 WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
/*			*/ 
/*	170 */		 this.h = true;
/*	171 */		 if (!this.player.viewingCredits)
/*			*/		 {
/*	174 */			 if (!this.checkMovement) {
/*	175 */				 double d0 = packet10flying.y - this.z;
/*	176 */				 if ((packet10flying.x == this.y) && (d0 * d0 < 0.01D) && (packet10flying.z == this.q)) {
/*	177 */					 this.checkMovement = true;
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/*	182 */			 Player player = getPlayer();
/*	183 */			 Location from = new Location(player.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch);
/*	184 */			 Location to = player.getLocation().clone();
/*			*/ 
/*	187 */			 if ((packet10flying.hasPos) && ((!packet10flying.hasPos) || (packet10flying.y != -999.0D) || (packet10flying.stance != -999.0D))) {
/*	188 */				 to.setX(packet10flying.x);
/*	189 */				 to.setY(packet10flying.y);
/*	190 */				 to.setZ(packet10flying.z);
/*			*/			 }
/*			*/ 
/*	194 */			 if (packet10flying.hasLook) {
/*	195 */				 to.setYaw(packet10flying.yaw);
/*	196 */				 to.setPitch(packet10flying.pitch);
/*			*/			 }
/*			*/ 
/*	200 */			 double delta = Math.pow(this.lastPosX - to.getX(), 2.0D) + Math.pow(this.lastPosY - to.getY(), 2.0D) + Math.pow(this.lastPosZ - to.getZ(), 2.0D);
/*	201 */			 float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch());
/*			*/ 
/*	203 */			 if (((delta > 0.00390625D) || (deltaAngle > 10.0F)) && (this.checkMovement) && (!this.player.dead)) {
/*	204 */				 this.lastPosX = to.getX();
/*	205 */				 this.lastPosY = to.getY();
/*	206 */				 this.lastPosZ = to.getZ();
/*	207 */				 this.lastYaw = to.getYaw();
/*	208 */				 this.lastPitch = to.getPitch();
/*			*/ 
/*	211 */				 if (from.getX() != 1.7976931348623157E+308D) {
/*	212 */					 PlayerMoveEvent event = new PlayerMoveEvent(player, from, to);
/*	213 */					 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	216 */					 if (event.isCancelled()) {
/*	217 */						 this.player.netServerHandler.sendPacket(new Packet13PlayerLookMove(from.getX(), from.getY() + 1.620000004768372D, from.getY(), from.getZ(), from.getYaw(), from.getPitch(), false));
/*	218 */						 return;
/*			*/					 }
/*			*/ 
/*	224 */					 if ((!to.equals(event.getTo())) && (!event.isCancelled())) {
/*	225 */						 this.player.getBukkitEntity().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
/*	226 */						 return;
/*			*/					 }
/*			*/ 
/*	231 */					 if ((!from.equals(getPlayer().getLocation())) && (this.justTeleported)) {
/*	232 */						 this.justTeleported = false;
/*	233 */						 return;
/*			*/					 }
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	238 */			 if ((Double.isNaN(packet10flying.x)) || (Double.isNaN(packet10flying.y)) || (Double.isNaN(packet10flying.z)) || (Double.isNaN(packet10flying.stance))) {
/*	239 */				 player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
/*	240 */				 System.err.println(player.getName() + " was caught trying to crash the server with an invalid position.");
/*	241 */				 player.kickPlayer("Nope!");
/*	242 */				 return;
/*			*/			 }
/*			*/ 
/*	245 */			 if ((this.checkMovement) && (!this.player.dead))
/*			*/			 {
/*	252 */				 if (this.player.vehicle != null) {
/*	253 */					 float f = this.player.yaw;
/*	254 */					 float f1 = this.player.pitch;
/*			*/ 
/*	256 */					 this.player.vehicle.V();
/*	257 */					 double d1 = this.player.locX;
/*	258 */					 double d2 = this.player.locY;
/*	259 */					 double d3 = this.player.locZ;
/*	260 */					 double d5 = 0.0D;
/*			*/ 
/*	262 */					 double d4 = 0.0D;
/*	263 */					 if (packet10flying.hasLook) {
/*	264 */						 f = packet10flying.yaw;
/*	265 */						 f1 = packet10flying.pitch;
/*			*/					 }
/*			*/ 
/*	268 */					 if ((packet10flying.hasPos) && (packet10flying.y == -999.0D) && (packet10flying.stance == -999.0D))
/*			*/					 {
/*	270 */						 if ((Math.abs(packet10flying.x) > 1.0D) || (Math.abs(packet10flying.z) > 1.0D)) {
/*	271 */							 System.err.println(player.getName() + " was caught trying to crash the server with an invalid position.");
/*	272 */							 player.kickPlayer("Nope!");
/*	273 */							 return;
/*			*/						 }
/*			*/ 
/*	277 */						 d5 = packet10flying.x;
/*	278 */						 d4 = packet10flying.z;
/*			*/					 }
/*			*/ 
/*	281 */					 this.player.onGround = packet10flying.g;
/*	282 */					 this.player.g();
/*	283 */					 this.player.move(d5, 0.0D, d4);
/*	284 */					 this.player.setLocation(d1, d2, d3, f, f1);
/*	285 */					 this.player.motX = d5;
/*	286 */					 this.player.motZ = d4;
/*	287 */					 if (this.player.vehicle != null) {
/*	288 */						 worldserver.vehicleEnteredWorld(this.player.vehicle, true);
/*			*/					 }
/*			*/ 
/*	291 */					 if (this.player.vehicle != null) {
/*	292 */						 this.player.vehicle.V();
/*			*/					 }
/*			*/ 
/*	295 */					 this.minecraftServer.getServerConfigurationManager().d(this.player);
/*	296 */					 this.y = this.player.locX;
/*	297 */					 this.z = this.player.locY;
/*	298 */					 this.q = this.player.locZ;
/*	299 */					 worldserver.playerJoinedWorld(this.player);
/*	300 */					 return;
/*			*/				 }
/*			*/ 
/*	303 */				 if (this.player.isSleeping()) {
/*	304 */					 this.player.g();
/*	305 */					 this.player.setLocation(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
/*	306 */					 worldserver.playerJoinedWorld(this.player);
/*	307 */					 return;
/*			*/				 }
/*			*/ 
/*	310 */				 double d0 = this.player.locY;
/*	311 */				 this.y = this.player.locX;
/*	312 */				 this.z = this.player.locY;
/*	313 */				 this.q = this.player.locZ;
/*	314 */				 double d1 = this.player.locX;
/*	315 */				 double d2 = this.player.locY;
/*	316 */				 double d3 = this.player.locZ;
/*	317 */				 float f2 = this.player.yaw;
/*	318 */				 float f3 = this.player.pitch;
/*			*/ 
/*	320 */				 if ((packet10flying.hasPos) && (packet10flying.y == -999.0D) && (packet10flying.stance == -999.0D)) {
/*	321 */					 packet10flying.hasPos = false;
/*			*/				 }
/*			*/ 
/*	324 */				 if (packet10flying.hasPos) {
/*	325 */					 d1 = packet10flying.x;
/*	326 */					 d2 = packet10flying.y;
/*	327 */					 d3 = packet10flying.z;
/*	328 */					 double d4 = packet10flying.stance - packet10flying.y;
/*	329 */					 if ((!this.player.isSleeping()) && ((d4 > 1.65D) || (d4 < 0.1D))) {
/*	330 */						 disconnect("Illegal stance");
/*	331 */						 logger.warning(this.player.name + " had an illegal stance: " + d4);
/*	332 */						 return;
/*			*/					 }
/*			*/ 
/*	335 */					 if ((Math.abs(packet10flying.x) > 32000000.0D) || (Math.abs(packet10flying.z) > 32000000.0D)) {
/*	336 */						 disconnect("Illegal position");
/*	337 */						 return;
/*			*/					 }
/*			*/				 }
/*			*/ 
/*	341 */				 if (packet10flying.hasLook) {
/*	342 */					 f2 = packet10flying.yaw;
/*	343 */					 f3 = packet10flying.pitch;
/*			*/				 }
/*			*/ 
/*	346 */				 this.player.g();
/*	347 */				 this.player.V = 0.0F;
/*	348 */				 this.player.setLocation(this.y, this.z, this.q, f2, f3);
/*	349 */				 if (!this.checkMovement) {
/*	350 */					 return;
/*			*/				 }
/*			*/ 
/*	353 */				 double d4 = d1 - this.player.locX;
/*	354 */				 double d6 = d2 - this.player.locY;
/*	355 */				 double d7 = d3 - this.player.locZ;
/*			*/ 
/*	357 */				 double d8 = Math.max(Math.abs(d4), Math.abs(this.player.motX));
/*	358 */				 double d9 = Math.max(Math.abs(d6), Math.abs(this.player.motY));
/*	359 */				 double d10 = Math.max(Math.abs(d7), Math.abs(this.player.motZ));
/*			*/ 
/*	361 */				 double d11 = d8 * d8 + d9 * d9 + d10 * d10;
/*			*/ 
/*	363 */				 if ((d11 > 100.0D) && (this.checkMovement) && ((!this.minecraftServer.H()) || (!this.minecraftServer.G().equals(this.player.name)))) {
/*	364 */					 logger.warning(this.player.name + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
/*	365 */					 a(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
/*	366 */					 return;
/*			*/				 }
/*			*/ 
/*	369 */				 float f4 = 0.0625F;
/*	370 */				 boolean flag = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink(f4, f4, f4)).isEmpty();
/*			*/ 
/*	372 */				 if ((this.player.onGround) && (!packet10flying.g) && (d6 > 0.0D)) {
/*	373 */					 this.player.j(0.2F);
/*			*/				 }
/*			*/ 
/*	376 */				 this.player.move(d4, d6, d7);
/*	377 */				 this.player.onGround = packet10flying.g;
/*	378 */				 this.player.checkMovement(d4, d6, d7);
/*	379 */				 double d12 = d6;
/*			*/ 
/*	381 */				 d4 = d1 - this.player.locX;
/*	382 */				 d6 = d2 - this.player.locY;
/*	383 */				 if ((d6 > -0.5D) || (d6 < 0.5D)) {
/*	384 */					 d6 = 0.0D;
/*			*/				 }
/*			*/ 
/*	387 */				 d7 = d3 - this.player.locZ;
/*	388 */				 d11 = d4 * d4 + d6 * d6 + d7 * d7;
/*	389 */				 boolean flag1 = false;
/*			*/ 
/*	391 */				 if ((d11 > 0.0625D) && (!this.player.isSleeping()) && (!this.player.itemInWorldManager.isCreative())) {
/*	392 */					 flag1 = true;
/*	393 */					 logger.warning(this.player.name + " moved wrongly!");
/*			*/				 }
/*			*/ 
/*	396 */				 this.player.setLocation(d1, d2, d3, f2, f3);
/*	397 */				 boolean flag2 = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink(f4, f4, f4)).isEmpty();
/*			*/ 
/*	399 */				 if ((flag) && ((flag1) || (!flag2)) && (!this.player.isSleeping())) {
/*	400 */					 a(this.y, this.z, this.q, f2, f3);
/*	401 */					 return;
/*			*/				 }
/*			*/ 
/*	404 */				 AxisAlignedBB axisalignedbb = this.player.boundingBox.clone().grow(f4, f4, f4).a(0.0D, -0.55D, 0.0D);
/*			*/ 
/*	406 */				 if ((!this.minecraftServer.getAllowFlight()) && (!this.player.abilities.canFly) && (!worldserver.c(axisalignedbb))) {
/*	407 */					 if (d12 >= -0.03125D) {
/*	408 */						 this.g += 1;
/*	409 */						 if (this.g > 80) {
/*	410 */							 logger.warning(this.player.name + " was kicked for floating too long!");
/*	411 */							 disconnect("Flying is not enabled on this server");
/*	412 */							 return;
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*	416 */				 else this.g = 0;
/*			*/ 
/*	419 */				 this.player.onGround = packet10flying.g;
/*	420 */				 this.minecraftServer.getServerConfigurationManager().d(this.player);
/*	421 */				 if (this.player.itemInWorldManager.isCreative()) return;
/*	422 */				 this.player.b(this.player.locY - d0, packet10flying.g);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(double d0, double d1, double d2, float f, float f1)
/*			*/	 {
/*	429 */		 Player player = getPlayer();
/*	430 */		 Location from = player.getLocation();
/*	431 */		 Location to = new Location(getPlayer().getWorld(), d0, d1, d2, f, f1);
/*	432 */		 PlayerTeleportEvent event = new PlayerTeleportEvent(player, from, to, PlayerTeleportEvent.TeleportCause.UNKNOWN);
/*	433 */		 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	435 */		 from = event.getFrom();
/*	436 */		 to = event.isCancelled() ? from : event.getTo();
/*			*/ 
/*	438 */		 teleport(to);
/*			*/	 }
/*			*/ 
/*			*/	 public void teleport(Location dest)
/*			*/	 {
/*	445 */		 double d0 = dest.getX();
/*	446 */		 double d1 = dest.getY();
/*	447 */		 double d2 = dest.getZ();
/*	448 */		 float f = dest.getYaw();
/*	449 */		 float f1 = dest.getPitch();
/*			*/ 
/*	452 */		 if (Float.isNaN(f)) {
/*	453 */			 f = 0.0F;
/*			*/		 }
/*			*/ 
/*	456 */		 if (Float.isNaN(f1)) {
/*	457 */			 f1 = 0.0F;
/*			*/		 }
/*			*/ 
/*	460 */		 this.lastPosX = d0;
/*	461 */		 this.lastPosY = d1;
/*	462 */		 this.lastPosZ = d2;
/*	463 */		 this.lastYaw = f;
/*	464 */		 this.lastPitch = f1;
/*	465 */		 this.justTeleported = true;
/*			*/ 
/*	468 */		 this.checkMovement = false;
/*	469 */		 this.y = d0;
/*	470 */		 this.z = d1;
/*	471 */		 this.q = d2;
/*	472 */		 this.player.setLocation(d0, d1, d2, f, f1);
/*	473 */		 this.player.netServerHandler.sendPacket(new Packet13PlayerLookMove(d0, d1 + 1.620000004768372D, d1, d2, f, f1, false));
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet14BlockDig packet14blockdig) {
/*	477 */		 if (this.player.dead) return;
/*			*/ 
/*	479 */		 WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
/*			*/ 
/*	481 */		 if (packet14blockdig.e == 4)
/*			*/		 {
/*	484 */			 if (this.lastDropTick != MinecraftServer.currentTick) {
/*	485 */				 this.dropCount = 0;
/*	486 */				 this.lastDropTick = MinecraftServer.currentTick;
/*			*/			 }
/*			*/			 else {
/*	489 */				 this.dropCount += 1;
/*	490 */				 if (this.dropCount >= 20) {
/*	491 */					 logger.warning(this.player.name + " dropped their items too quickly!");
/*	492 */					 disconnect("You dropped your items too quickly (Hacking?)");
/*	493 */					 return;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	497 */			 this.player.bB();
/*	498 */		 } else if (packet14blockdig.e == 5) {
/*	499 */			 this.player.by();
/*			*/		 } else {
/*	501 */			 boolean flag = worldserver.weirdIsOpCache = (worldserver.dimension != 0) || (this.minecraftServer.getServerConfigurationManager().isOp(this.player.name)) || (this.minecraftServer.H()) ? 1 : 0;
/*	502 */			 boolean flag1 = false;
/*			*/ 
/*	504 */			 if (packet14blockdig.e == 0) {
/*	505 */				 flag1 = true;
/*			*/			 }
/*			*/ 
/*	508 */			 if (packet14blockdig.e == 2) {
/*	509 */				 flag1 = true;
/*			*/			 }
/*			*/ 
/*	512 */			 int i = packet14blockdig.a;
/*	513 */			 int j = packet14blockdig.b;
/*	514 */			 int k = packet14blockdig.c;
/*			*/ 
/*	516 */			 if (flag1) {
/*	517 */				 double d0 = this.player.locX - (i + 0.5D);
/*	518 */				 double d1 = this.player.locY - (j + 0.5D) + 1.5D;
/*	519 */				 double d2 = this.player.locZ - (k + 0.5D);
/*	520 */				 double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*			*/ 
/*	522 */				 if (d3 > 36.0D) {
/*	523 */					 return;
/*			*/				 }
/*			*/ 
/*	526 */				 if (j >= this.minecraftServer.getMaxBuildHeight()) {
/*	527 */					 return;
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	531 */			 ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
/*	532 */			 int l = MathHelper.a(i - chunkcoordinates.x);
/*	533 */			 int i1 = MathHelper.a(k - chunkcoordinates.z);
/*			*/ 
/*	535 */			 if (l > i1) {
/*	536 */				 i1 = l;
/*			*/			 }
/*			*/ 
/*	539 */			 if (packet14blockdig.e == 0)
/*			*/			 {
/*	541 */				 if ((i1 < this.server.getSpawnRadius()) && (!flag)) {
/*	542 */					 CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, i, j, k, l, this.player.inventory.getItemInHand());
/*			*/ 
/*	544 */					 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
/*			*/				 } else {
/*	546 */					 this.player.itemInWorldManager.dig(i, j, k, packet14blockdig.face);
/*			*/				 }
/*	548 */			 } else if (packet14blockdig.e == 2) {
/*	549 */				 this.player.itemInWorldManager.a(i, j, k);
/*	550 */				 if (worldserver.getTypeId(i, j, k) != 0)
/*	551 */					 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
/*			*/			 }
/*	553 */			 else if (packet14blockdig.e == 1) {
/*	554 */				 this.player.itemInWorldManager.c(i, j, k);
/*	555 */				 if (worldserver.getTypeId(i, j, k) != 0)
/*	556 */					 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
/*			*/			 }
/*	558 */			 else if (packet14blockdig.e == 3) {
/*	559 */				 double d4 = this.player.locX - (i + 0.5D);
/*	560 */				 double d5 = this.player.locY - (j + 0.5D);
/*	561 */				 double d6 = this.player.locZ - (k + 0.5D);
/*	562 */				 double d7 = d4 * d4 + d5 * d5 + d6 * d6;
/*			*/ 
/*	564 */				 if (d7 < 256.0D) {
/*	565 */					 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
/*			*/				 }
/*			*/			 }
/*			*/ 
/*	569 */			 worldserver.weirdIsOpCache = false;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet15Place packet15place) {
/*	574 */		 WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
/*			*/ 
/*	577 */		 if (this.player.dead) return;
/*			*/ 
/*	587 */		 if (packet15place.getFace() == 255) {
/*	588 */			 if ((packet15place.getItemStack() != null) && (packet15place.getItemStack().id == this.lastMaterial) && (this.lastPacket != null) && (packet15place.timestamp - this.lastPacket.longValue() < 100L)) {
/*	589 */				 this.lastPacket = null;
/*	590 */				 return;
/*			*/			 }
/*			*/		 } else {
/*	593 */			 this.lastMaterial = (packet15place.getItemStack() == null ? -1 : packet15place.getItemStack().id);
/*	594 */			 this.lastPacket = Long.valueOf(packet15place.timestamp);
/*			*/		 }
/*			*/ 
/*	600 */		 boolean always = false;
/*			*/ 
/*	604 */		 ItemStack itemstack = this.player.inventory.getItemInHand();
/*	605 */		 boolean flag = false;
/*	606 */		 int i = packet15place.d();
/*	607 */		 int j = packet15place.f();
/*	608 */		 int k = packet15place.g();
/*	609 */		 int l = packet15place.getFace();
/*	610 */		 boolean flag1 = worldserver.weirdIsOpCache = (worldserver.worldProvider.dimension != 0) || (this.minecraftServer.getServerConfigurationManager().isOp(this.player.name)) || (this.minecraftServer.H()) ? 1 : 0;
/*			*/ 
/*	612 */		 if (packet15place.getFace() == 255) {
/*	613 */			 if (itemstack == null) {
/*	614 */				 return;
/*			*/			 }
/*			*/ 
/*	618 */			 int itemstackAmount = itemstack.count;
/*	619 */			 PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.RIGHT_CLICK_AIR, itemstack);
/*	620 */			 if (event.useItemInHand() != Event.Result.DENY) {
/*	621 */				 this.player.itemInWorldManager.useItem(this.player, this.player.world, itemstack);
/*			*/			 }
/*			*/ 
/*	627 */			 always = itemstack.count != itemstackAmount;
/*			*/		 }
/*	629 */		 else if ((packet15place.f() >= this.minecraftServer.getMaxBuildHeight() - 1) && ((packet15place.getFace() == 1) || (packet15place.f() >= this.minecraftServer.getMaxBuildHeight()))) {
/*	630 */			 this.player.netServerHandler.sendPacket(new Packet3Chat("§7Height limit for building is " + this.minecraftServer.getMaxBuildHeight()));
/*	631 */			 flag = true;
/*			*/		 } else {
/*	633 */			 ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
/*	634 */			 int i1 = MathHelper.a(i - chunkcoordinates.x);
/*	635 */			 int j1 = MathHelper.a(k - chunkcoordinates.z);
/*			*/ 
/*	637 */			 if (i1 > j1) {
/*	638 */				 j1 = i1;
/*			*/			 }
/*			*/ 
/*	642 */			 Location eyeLoc = getPlayer().getEyeLocation();
/*	643 */			 if (Math.pow(eyeLoc.getX() - i, 2.0D) + Math.pow(eyeLoc.getY() - j, 2.0D) + Math.pow(eyeLoc.getZ() - k, 2.0D) > 36.0D) {
/*	644 */				 return;
/*			*/			 }
/*	646 */			 flag1 = true;
/*	647 */			 if ((j1 > 16) || (flag1))
/*			*/			 {
/*	649 */				 this.player.itemInWorldManager.interact(this.player, worldserver, itemstack, i, j, k, l, packet15place.j(), packet15place.l(), packet15place.m());
/*			*/			 }
/*			*/ 
/*	652 */			 flag = true;
/*			*/		 }
/*			*/ 
/*	655 */		 if (flag) {
/*	656 */			 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
/*	657 */			 if (l == 0) {
/*	658 */				 j--;
/*			*/			 }
/*			*/ 
/*	661 */			 if (l == 1) {
/*	662 */				 j++;
/*			*/			 }
/*			*/ 
/*	665 */			 if (l == 2) {
/*	666 */				 k--;
/*			*/			 }
/*			*/ 
/*	669 */			 if (l == 3) {
/*	670 */				 k++;
/*			*/			 }
/*			*/ 
/*	673 */			 if (l == 4) {
/*	674 */				 i--;
/*			*/			 }
/*			*/ 
/*	677 */			 if (l == 5) {
/*	678 */				 i++;
/*			*/			 }
/*			*/ 
/*	681 */			 this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
/*			*/		 }
/*			*/ 
/*	684 */		 itemstack = this.player.inventory.getItemInHand();
/*	685 */		 if ((itemstack != null) && (itemstack.count == 0)) {
/*	686 */			 this.player.inventory.items[this.player.inventory.itemInHandIndex] = null;
/*	687 */			 itemstack = null;
/*			*/		 }
/*			*/ 
/*	690 */		 if ((itemstack == null) || (itemstack.m() == 0)) {
/*	691 */			 this.player.h = true;
/*	692 */			 this.player.inventory.items[this.player.inventory.itemInHandIndex] = ItemStack.b(this.player.inventory.items[this.player.inventory.itemInHandIndex]);
/*	693 */			 Slot slot = this.player.activeContainer.a(this.player.inventory, this.player.inventory.itemInHandIndex);
/*			*/ 
/*	695 */			 this.player.activeContainer.b();
/*	696 */			 this.player.h = false;
/*			*/ 
/*	698 */			 if ((!ItemStack.matches(this.player.inventory.getItemInHand(), packet15place.getItemStack())) || (always)) {
/*	699 */				 sendPacket(new Packet103SetSlot(this.player.activeContainer.windowId, slot.d, this.player.inventory.getItemInHand()));
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	703 */		 worldserver.weirdIsOpCache = false;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(String s, Object[] aobject) {
/*	707 */		 if (this.disconnected) return;
/*			*/ 
/*	709 */		 logger.info(this.player.name + " lost connection: " + s);
/*			*/ 
/*	711 */		 String quitMessage = this.minecraftServer.getServerConfigurationManager().disconnect(this.player);
/*	712 */		 if ((quitMessage != null) && (quitMessage.length() > 0)) {
/*	713 */			 this.minecraftServer.getServerConfigurationManager().sendAll(new Packet3Chat(quitMessage));
/*			*/		 }
/*			*/ 
/*	716 */		 this.disconnected = true;
/*	717 */		 if ((this.minecraftServer.H()) && (this.player.name.equals(this.minecraftServer.G()))) {
/*	718 */			 logger.info("Stopping singleplayer server as player logged out");
/*	719 */			 this.minecraftServer.safeShutdown();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void onUnhandledPacket(Packet packet) {
/*	724 */		 if (this.disconnected) return;
/*	725 */		 logger.warning(getClass() + " wasn't prepared to deal with a " + packet.getClass());
/*	726 */		 disconnect("Protocol error, unexpected packet");
/*			*/	 }
/*			*/ 
/*			*/	 public void sendPacket(Packet packet) {
/*	730 */		 if ((packet instanceof Packet3Chat)) {
/*	731 */			 Packet3Chat packet3chat = (Packet3Chat)packet;
/*	732 */			 int i = this.player.getChatFlags();
/*			*/ 
/*	734 */			 if (i == 2) {
/*	735 */				 return;
/*			*/			 }
/*			*/ 
/*	738 */			 if ((i == 1) && (!packet3chat.isServer())) {
/*	739 */				 return;
/*			*/			 }
/*			*/ 
/*	743 */			 String message = packet3chat.message;
/*	744 */			 for (String line : TextWrapper.wrapText(message)) {
/*	745 */				 this.networkManager.queue(new Packet3Chat(line));
/*			*/			 }
/*	747 */			 return;
/*			*/		 }
/*			*/ 
/*	752 */		 if (packet == null)
/*	753 */			 return;
/*	754 */		 if ((packet instanceof Packet6SpawnPosition)) {
/*	755 */			 Packet6SpawnPosition packet6 = (Packet6SpawnPosition)packet;
/*	756 */			 this.player.compassTarget = new Location(getPlayer().getWorld(), packet6.x, packet6.y, packet6.z);
/*	757 */		 } else if (packet.lowPriority == true)
/*			*/		 {
/*	759 */			 ChunkCompressionThread.sendPacket(this.player, packet);
/*	760 */			 return;
/*			*/		 }
/*			*/ 
/*	764 */		 this.networkManager.queue(packet);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet16BlockItemSwitch packet16blockitemswitch)
/*			*/	 {
/*	769 */		 if (this.player.dead) return;
/*			*/ 
/*	771 */		 if ((packet16blockitemswitch.itemInHandIndex >= 0) && (packet16blockitemswitch.itemInHandIndex < PlayerInventory.getHotbarSize())) {
/*	772 */			 PlayerItemHeldEvent event = new PlayerItemHeldEvent(getPlayer(), this.player.inventory.itemInHandIndex, packet16blockitemswitch.itemInHandIndex);
/*	773 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	776 */			 this.player.inventory.itemInHandIndex = packet16blockitemswitch.itemInHandIndex;
/*			*/		 } else {
/*	778 */			 logger.warning(this.player.name + " tried to set an invalid carried item");
/*	779 */			 disconnect("Nope!");
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet3Chat packet3chat) {
/*	784 */		 if (this.player.getChatFlags() == 2) {
/*	785 */			 sendPacket(new Packet3Chat("Cannot send chat message."));
/*			*/		 } else {
/*	787 */			 String s = packet3chat.message;
/*			*/ 
/*	789 */			 if (s.length() > 100) {
/*	790 */				 disconnect("Chat message too long");
/*			*/			 } else {
/*	792 */				 s = s.trim();
/*			*/ 
/*	794 */				 for (int i = 0; i < s.length(); i++) {
/*	795 */					 if (!SharedConstants.isAllowedChatCharacter(s.charAt(i))) {
/*	796 */						 disconnect("Illegal characters in chat");
/*	797 */						 return;
/*			*/					 }
/*			*/ 
/*			*/				 }
/*			*/ 
/*	802 */				 if (this.player.getChatFlags() == 1) {
/*	803 */					 sendPacket(new Packet3Chat("Cannot send chat message."));
/*	804 */					 return;
/*			*/				 }
/*			*/ 
/*	807 */				 chat(s, packet3chat.a_());
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void chat(String s, boolean async) {
/*	813 */		 if (!this.player.dead) {
/*	814 */			 if (s.length() == 0) {
/*	815 */				 logger.warning(this.player.name + " tried to send an empty message");
/*	816 */				 return;
/*			*/			 }
/*			*/ 
/*	819 */			 if (getPlayer().isConversing()) {
/*	820 */				 getPlayer().acceptConversationInput(s);
/*	821 */				 return;
/*			*/			 }
/*			*/ 
/*	824 */			 if (s.startsWith("/")) {
/*	825 */				 handleCommand(s);
/*	826 */				 return;
/*			*/			 }
/*	828 */			 Player player = getPlayer();
/*	829 */			 AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(async, player, s, new LazyPlayerSet());
/*	830 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	832 */			 if (PlayerChatEvent.getHandlerList().getRegisteredListeners().length != 0)
/*			*/			 {
/*	834 */				 PlayerChatEvent queueEvent = new PlayerChatEvent(player, event.getMessage(), event.getFormat(), event.getRecipients());
/*	835 */				 queueEvent.setCancelled(event.isCancelled());
/*	836 */				 this.minecraftServer.chatQueue.add(queueEvent);
/*			*/			 } else {
/*	838 */				 if (event.isCancelled()) {
/*	839 */					 return;
/*			*/				 }
/*			*/ 
/*	842 */				 s = String.format(event.getFormat(), new Object[] { event.getPlayer().getDisplayName(), event.getMessage() });
/*	843 */				 this.minecraftServer.console.sendMessage(s);
/*			*/				 Iterator i$;
/*	844 */				 if (((LazyPlayerSet)event.getRecipients()).isLazy())
/*	845 */					 for (i$ = this.minecraftServer.getServerConfigurationManager().players.iterator(); i$.hasNext(); ) { Object recipient = i$.next();
/*	846 */						 ((EntityPlayer)recipient).sendMessage(s);
/*			*/					 }
/*			*/				 else {
/*	849 */					 for (Player recipient : event.getRecipients()) {
/*	850 */						 recipient.sendMessage(s);
/*			*/					 }
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/*	856 */			 this.m += 20;
/*	857 */			 if ((this.m > 200) && (!this.minecraftServer.getServerConfigurationManager().isOp(this.player.name)))
/*	858 */				 disconnect("disconnect.spam");
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 private void handleCommand(String s)
/*			*/	 {
/*	868 */		 CraftPlayer player = getPlayer();
/*			*/ 
/*	870 */		 PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, s, new LazyPlayerSet());
/*	871 */		 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	873 */		 if (event.isCancelled()) {
/*	874 */			 return;
/*			*/		 }
/*			*/		 try
/*			*/		 {
/*	878 */			 if (this.server.dispatchCommand(event.getPlayer(), event.getMessage().substring(1)))
/*	879 */				 return;
/*			*/		 }
/*			*/		 catch (CommandException ex) {
/*	882 */			 player.sendMessage(ChatColor.RED + "An internal error occurred while attempting to perform this command");
/*	883 */			 Logger.getLogger(NetServerHandler.class.getName()).log(Level.SEVERE, null, ex);
/*	884 */			 return;
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet18ArmAnimation packet18armanimation)
/*			*/	 {
/*	897 */		 if (this.player.dead) return;
/*			*/ 
/*	899 */		 if (packet18armanimation.b == 1)
/*			*/		 {
/*	901 */			 float f = 1.0F;
/*	902 */			 float f1 = this.player.lastPitch + (this.player.pitch - this.player.lastPitch) * f;
/*	903 */			 float f2 = this.player.lastYaw + (this.player.yaw - this.player.lastYaw) * f;
/*	904 */			 double d0 = this.player.lastX + (this.player.locX - this.player.lastX) * f;
/*	905 */			 double d1 = this.player.lastY + (this.player.locY - this.player.lastY) * f + 1.62D - this.player.height;
/*	906 */			 double d2 = this.player.lastZ + (this.player.locZ - this.player.lastZ) * f;
/*	907 */			 Vec3D vec3d = Vec3D.a().create(d0, d1, d2);
/*			*/ 
/*	909 */			 float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
/*	910 */			 float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
/*	911 */			 float f5 = -MathHelper.cos(-f1 * 0.01745329F);
/*	912 */			 float f6 = MathHelper.sin(-f1 * 0.01745329F);
/*	913 */			 float f7 = f4 * f5;
/*	914 */			 float f8 = f3 * f5;
/*	915 */			 double d3 = 5.0D;
/*	916 */			 Vec3D vec3d1 = vec3d.add(f7 * d3, f6 * d3, f8 * d3);
/*	917 */			 MovingObjectPosition movingobjectposition = this.player.world.rayTrace(vec3d, vec3d1, true);
/*			*/ 
/*	919 */			 if ((movingobjectposition == null) || (movingobjectposition.type != EnumMovingObjectType.TILE)) {
/*	920 */				 CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_AIR, this.player.inventory.getItemInHand());
/*			*/			 }
/*			*/ 
/*	924 */			 PlayerAnimationEvent event = new PlayerAnimationEvent(getPlayer());
/*	925 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	927 */			 if (event.isCancelled()) return;
/*			*/ 
/*	930 */			 this.player.i();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet19EntityAction packet19entityaction)
/*			*/	 {
/*	936 */		 if (this.player.dead) return;
/*			*/ 
/*	938 */		 if ((packet19entityaction.animation == 1) || (packet19entityaction.animation == 2)) {
/*	939 */			 PlayerToggleSneakEvent event = new PlayerToggleSneakEvent(getPlayer(), packet19entityaction.animation == 1);
/*	940 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	942 */			 if (event.isCancelled()) {
/*	943 */				 return;
/*			*/			 }
/*			*/		 }
/*			*/ 
/*	947 */		 if ((packet19entityaction.animation == 4) || (packet19entityaction.animation == 5)) {
/*	948 */			 PlayerToggleSprintEvent event = new PlayerToggleSprintEvent(getPlayer(), packet19entityaction.animation == 4);
/*	949 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/*	951 */			 if (event.isCancelled()) {
/*	952 */				 return;
/*			*/			 }
/*			*/ 
/*			*/		 }
/*			*/ 
/*	957 */		 if (packet19entityaction.animation == 1)
/*	958 */			 this.player.setSneaking(true);
/*	959 */		 else if (packet19entityaction.animation == 2)
/*	960 */			 this.player.setSneaking(false);
/*	961 */		 else if (packet19entityaction.animation == 4)
/*	962 */			 this.player.setSprinting(true);
/*	963 */		 else if (packet19entityaction.animation == 5)
/*	964 */			 this.player.setSprinting(false);
/*	965 */		 else if (packet19entityaction.animation == 3)
/*	966 */			 this.player.a(false, true, true);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet255KickDisconnect packet255kickdisconnect)
/*			*/	 {
/*	973 */		 getPlayer().disconnect("disconnect.quitting");
/*			*/ 
/*	975 */		 this.networkManager.a("disconnect.quitting", new Object[0]);
/*			*/	 }
/*			*/ 
/*			*/	 public int lowPriorityCount() {
/*	979 */		 return this.networkManager.e();
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet7UseEntity packet7useentity) {
/*	983 */		 if (this.player.dead) return;
/*			*/ 
/*	985 */		 WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
/*	986 */		 Entity entity = worldserver.getEntity(packet7useentity.target);
/*			*/ 
/*	988 */		 if (entity != null) {
/*	989 */			 boolean flag = this.player.l(entity);
/*	990 */			 double d0 = 36.0D;
/*			*/ 
/*	992 */			 if (!flag) {
/*	993 */				 d0 = 9.0D;
/*			*/			 }
/*			*/ 
/*	996 */			 if (this.player.e(entity) < d0) {
/*	997 */				 ItemStack itemInHand = this.player.inventory.getItemInHand();
/*	998 */				 if (packet7useentity.action == 0)
/*			*/				 {
/* 1000 */					 PlayerInteractEntityEvent event = new PlayerInteractEntityEvent(getPlayer(), entity.getBukkitEntity());
/* 1001 */					 this.server.getPluginManager().callEvent(event);
/*			*/ 
/* 1003 */					 if (event.isCancelled()) {
/* 1004 */						 return;
/*			*/					 }
/*			*/ 
/* 1007 */					 this.player.m(entity);
/*			*/ 
/* 1009 */					 if ((itemInHand != null) && (itemInHand.count <= -1))
/* 1010 */						 this.player.updateInventory(this.player.activeContainer);
/*			*/				 }
/* 1012 */				 else if (packet7useentity.action == 1) {
/* 1013 */					 if (((entity instanceof EntityItem)) || ((entity instanceof EntityExperienceOrb)) || ((entity instanceof EntityArrow))) {
/* 1014 */						 String type = entity.getClass().getSimpleName();
/* 1015 */						 disconnect("Attacking an " + type + " is not permitted");
/* 1016 */						 System.out.println("Player " + this.player.name + " tried to attack an " + type + ", so I have disconnected them for exploiting.");
/* 1017 */						 return;
/*			*/					 }
/*			*/ 
/* 1020 */					 this.player.attack(entity);
/*			*/ 
/* 1022 */					 if ((itemInHand != null) && (itemInHand.count <= -1))
/* 1023 */						 this.player.updateInventory(this.player.activeContainer);
/*			*/				 }
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet205ClientCommand packet205clientcommand)
/*			*/	 {
/* 1032 */		 if (packet205clientcommand.a == 1)
/* 1033 */			 if (this.player.viewingCredits)
/*			*/			 {
/* 1035 */				 PortalTravelAgent pta = new PortalTravelAgent();
/*			*/				 Location toLocation;
/* 1038 */				 if (this.player.getBukkitEntity().getBedSpawnLocation() == null) {
/* 1039 */					 CraftWorld cworld = (CraftWorld)this.server.getWorlds().get(0);
/* 1040 */					 ChunkCoordinates chunkcoordinates = cworld.getHandle().getSpawn();
/* 1041 */					 Location toLocation = new Location(cworld, chunkcoordinates.x + 0.5D, chunkcoordinates.y, chunkcoordinates.z + 0.5D);
/* 1042 */					 this.player.netServerHandler.sendPacket(new Packet70Bed(0, 0));
/*			*/				 } else {
/* 1044 */					 toLocation = this.player.getBukkitEntity().getBedSpawnLocation();
/* 1045 */					 toLocation = new Location(toLocation.getWorld(), toLocation.getX() + 0.5D, toLocation.getY(), toLocation.getZ() + 0.5D);
/*			*/				 }
/*			*/ 
/* 1048 */				 PlayerPortalEvent event = new PlayerPortalEvent(this.player.getBukkitEntity(), this.player.getBukkitEntity().getLocation(), toLocation, pta, PlayerTeleportEvent.TeleportCause.END_PORTAL);
/* 1049 */				 event.useTravelAgent(false);
/*			*/ 
/* 1051 */				 Bukkit.getServer().getPluginManager().callEvent(event);
/* 1052 */				 this.player = this.minecraftServer.getServerConfigurationManager().moveToWorld(this.player, 0, true, event.getTo());
/*			*/			 }
/* 1054 */			 else if (this.player.q().getWorldData().isHardcore()) {
/* 1055 */				 if ((this.minecraftServer.H()) && (this.player.name.equals(this.minecraftServer.G()))) {
/* 1056 */					 this.player.netServerHandler.disconnect("You have died. Game over, man, it's game over!");
/* 1057 */					 this.minecraftServer.O();
/*			*/				 } else {
/* 1059 */					 BanEntry banentry = new BanEntry(this.player.name);
/*			*/ 
/* 1061 */					 banentry.setReason("Death in Hardcore");
/* 1062 */					 this.minecraftServer.getServerConfigurationManager().getNameBans().add(banentry);
/* 1063 */					 this.player.netServerHandler.disconnect("You have died. Game over, man, it's game over!");
/*			*/				 }
/*			*/			 } else {
/* 1066 */				 if (this.player.getHealth() > 0) {
/* 1067 */					 return;
/*			*/				 }
/*			*/ 
/* 1070 */				 this.player = this.minecraftServer.getServerConfigurationManager().moveToWorld(this.player, 0, false);
/*			*/			 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean b()
/*			*/	 {
/* 1076 */		 return true;
/*			*/	 }
/*			*/	 public void a(Packet9Respawn packet9respawn) {
/*			*/	 }
/*			*/ 
/*			*/	 public void handleContainerClose(Packet101CloseWindow packet101closewindow) {
/* 1082 */		 if (this.player.dead) return;
/*			*/ 
/* 1085 */		 InventoryCloseEvent event = new InventoryCloseEvent(this.player.activeContainer.getBukkitView());
/* 1086 */		 this.server.getPluginManager().callEvent(event);
/* 1087 */		 this.player.activeContainer.transferTo(this.player.defaultContainer, getPlayer());
/*			*/ 
/* 1090 */		 this.player.l();
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet102WindowClick packet102windowclick) {
/* 1094 */		 if (this.player.dead) return;
/*			*/ 
/* 1096 */		 if ((this.player.activeContainer.windowId == packet102windowclick.a) && (this.player.activeContainer.b(this.player)))
/*			*/		 {
/* 1098 */			 InventoryView inventory = this.player.activeContainer.getBukkitView();
/* 1099 */			 InventoryType.SlotType type = CraftInventoryView.getSlotType(inventory, packet102windowclick.slot);
/*			*/ 
/* 1101 */			 InventoryClickEvent event = new InventoryClickEvent(inventory, type, packet102windowclick.slot, packet102windowclick.button != 0, packet102windowclick.shift);
/* 1102 */			 Inventory top = inventory.getTopInventory();
/* 1103 */			 if ((packet102windowclick.slot == 0) && ((top instanceof CraftingInventory))) {
/* 1104 */				 Recipe recipe = ((CraftingInventory)top).getRecipe();
/* 1105 */				 if (recipe != null) {
/* 1106 */					 event = new CraftItemEvent(recipe, inventory, type, packet102windowclick.slot, packet102windowclick.button != 0, packet102windowclick.shift);
/*			*/				 }
/*			*/			 }
/* 1109 */			 this.server.getPluginManager().callEvent(event);
/*			*/ 
/* 1111 */			 ItemStack itemstack = null;
/* 1112 */			 boolean defaultBehaviour = false;
/*			*/ 
/* 1114 */			 switch (1.$SwitchMap$org$bukkit$event$Event$Result[event.getResult().ordinal()]) {
/*			*/			 case 1:
/* 1116 */				 itemstack = this.player.activeContainer.clickItem(packet102windowclick.slot, packet102windowclick.button, packet102windowclick.shift, this.player);
/* 1117 */				 defaultBehaviour = true;
/* 1118 */				 break;
/*			*/			 case 2:
/* 1120 */				 break;
/*			*/			 case 3:
/* 1122 */				 org.bukkit.inventory.ItemStack cursor = event.getCursor();
/* 1123 */				 if (cursor == null)
/* 1124 */					 this.player.inventory.setCarried((ItemStack)null);
/*			*/				 else {
/* 1126 */					 this.player.inventory.setCarried(CraftItemStack.createNMSItemStack(cursor));
/*			*/				 }
/* 1128 */				 org.bukkit.inventory.ItemStack item = event.getCurrentItem();
/* 1129 */				 if (item != null) {
/* 1130 */					 itemstack = CraftItemStack.createNMSItemStack(item);
/* 1131 */					 if (packet102windowclick.slot == -999)
/* 1132 */						 this.player.drop(itemstack);
/*			*/					 else
/* 1134 */						 this.player.activeContainer.getSlot(packet102windowclick.slot).set(itemstack);
/*			*/				 } else {
/* 1136 */					 if (packet102windowclick.slot == -999) break;
/* 1137 */					 this.player.activeContainer.getSlot(packet102windowclick.slot).set((ItemStack)null);
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1143 */			 if (ItemStack.matches(packet102windowclick.item, itemstack)) {
/* 1144 */				 this.player.netServerHandler.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, true));
/* 1145 */				 this.player.h = true;
/* 1146 */				 this.player.activeContainer.b();
/* 1147 */				 this.player.broadcastCarriedItem();
/* 1148 */				 this.player.h = false;
/*			*/			 } else {
/* 1150 */				 this.s.a(this.player.activeContainer.windowId, Short.valueOf(packet102windowclick.d));
/* 1151 */				 this.player.netServerHandler.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, false));
/* 1152 */				 this.player.activeContainer.a(this.player, false);
/* 1153 */				 ArrayList arraylist = new ArrayList();
/*			*/ 
/* 1155 */				 for (int i = 0; i < this.player.activeContainer.b.size(); i++) {
/* 1156 */					 arraylist.add(((Slot)this.player.activeContainer.b.get(i)).getItem());
/*			*/				 }
/*			*/ 
/* 1159 */				 this.player.a(this.player.activeContainer, arraylist);
/*			*/ 
/* 1162 */				 if ((type == InventoryType.SlotType.RESULT) && (itemstack != null))
/* 1163 */					 this.player.netServerHandler.sendPacket(new Packet103SetSlot(this.player.activeContainer.windowId, 0, itemstack));
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet108ButtonClick packet108buttonclick)
/*			*/	 {
/* 1170 */		 if ((this.player.activeContainer.windowId == packet108buttonclick.a) && (this.player.activeContainer.b(this.player))) {
/* 1171 */			 this.player.activeContainer.a(this.player, packet108buttonclick.b);
/* 1172 */			 this.player.activeContainer.b();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet107SetCreativeSlot packet107setcreativeslot) {
/* 1177 */		 if (this.player.itemInWorldManager.isCreative()) {
/* 1178 */			 boolean flag = packet107setcreativeslot.slot < 0;
/* 1179 */			 ItemStack itemstack = packet107setcreativeslot.b;
/* 1180 */			 boolean flag1 = (packet107setcreativeslot.slot >= 1) && (packet107setcreativeslot.slot < 36 + PlayerInventory.getHotbarSize());
/*			*/ 
/* 1182 */			 boolean flag2 = (itemstack == null) || ((itemstack.id < Item.byId.length) && (itemstack.id >= 0) && (Item.byId[itemstack.id] != null) && (!invalidItems.contains(Integer.valueOf(itemstack.id))));
/* 1183 */			 boolean flag3 = (itemstack == null) || ((itemstack.getData() >= 0) && (itemstack.getData() >= 0) && (itemstack.count <= 64) && (itemstack.count > 0));
/*			*/ 
/* 1186 */			 HumanEntity player = this.player.getBukkitEntity();
/* 1187 */			 InventoryView inventory = new CraftInventoryView(player, player.getInventory(), this.player.defaultContainer);
/* 1188 */			 InventoryType.SlotType slot = InventoryType.SlotType.QUICKBAR;
/* 1189 */			 if (packet107setcreativeslot.slot == -1) {
/* 1190 */				 slot = InventoryType.SlotType.OUTSIDE;
/*			*/			 }
/*			*/ 
/* 1193 */			 InventoryClickEvent event = new InventoryClickEvent(inventory, slot, slot == InventoryType.SlotType.OUTSIDE ? -999 : packet107setcreativeslot.slot, false, false);
/* 1194 */			 this.server.getPluginManager().callEvent(event);
/* 1195 */			 org.bukkit.inventory.ItemStack item = event.getCurrentItem();
/*			*/ 
/* 1197 */			 switch (1.$SwitchMap$org$bukkit$event$Event$Result[event.getResult().ordinal()]) {
/*			*/			 case 3:
/* 1199 */				 if (slot == InventoryType.SlotType.QUICKBAR) {
/* 1200 */					 if (item == null)
/* 1201 */						 this.player.defaultContainer.setItem(packet107setcreativeslot.slot, (ItemStack)null);
/*			*/					 else
/* 1203 */						 this.player.defaultContainer.setItem(packet107setcreativeslot.slot, CraftItemStack.createNMSItemStack(item));
/*			*/				 }
/* 1205 */				 else if (item != null) {
/* 1206 */					 this.player.drop(CraftItemStack.createNMSItemStack(item));
/*			*/				 }
/* 1208 */				 return;
/*			*/			 case 2:
/* 1211 */				 if (packet107setcreativeslot.slot > -1) {
/* 1212 */					 this.player.netServerHandler.sendPacket(new Packet103SetSlot(this.player.defaultContainer.windowId, packet107setcreativeslot.slot, CraftItemStack.createNMSItemStack(item)));
/*			*/				 }
/* 1214 */				 return;
/*			*/			 case 1:
/* 1217 */				 break;
/*			*/			 default:
/* 1219 */				 return;
/*			*/			 }
/*			*/ 
/* 1223 */			 if ((flag1) && (flag2) && (flag3)) {
/* 1224 */				 if (itemstack == null)
/* 1225 */					 this.player.defaultContainer.setItem(packet107setcreativeslot.slot, (ItemStack)null);
/*			*/				 else {
/* 1227 */					 this.player.defaultContainer.setItem(packet107setcreativeslot.slot, itemstack);
/*			*/				 }
/*			*/ 
/* 1230 */				 this.player.defaultContainer.a(this.player, true);
/* 1231 */			 } else if ((flag) && (flag2) && (flag3) && (this.x < 200)) {
/* 1232 */				 this.x += 20;
/* 1233 */				 EntityItem entityitem = this.player.drop(itemstack);
/*			*/ 
/* 1235 */				 if (entityitem != null)
/* 1236 */					 entityitem.d();
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet106Transaction packet106transaction)
/*			*/	 {
/* 1243 */		 if (this.player.dead) return;
/* 1244 */		 Short oshort = (Short)this.s.get(this.player.activeContainer.windowId);
/*			*/ 
/* 1246 */		 if ((oshort != null) && (packet106transaction.b == oshort.shortValue()) && (this.player.activeContainer.windowId == packet106transaction.a) && (!this.player.activeContainer.b(this.player)))
/* 1247 */			 this.player.activeContainer.a(this.player, true);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet130UpdateSign packet130updatesign)
/*			*/	 {
/* 1252 */		 if (this.player.dead) return;
/*			*/ 
/* 1254 */		 WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
/*			*/ 
/* 1256 */		 if (worldserver.isLoaded(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z)) {
/* 1257 */			 TileEntity tileentity = worldserver.getTileEntity(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z);
/*			*/ 
/* 1259 */			 if ((tileentity instanceof TileEntitySign)) {
/* 1260 */				 TileEntitySign tileentitysign = (TileEntitySign)tileentity;
/*			*/ 
/* 1262 */				 if (!tileentitysign.a()) {
/* 1263 */					 this.minecraftServer.warning("Player " + this.player.name + " just tried to change non-editable sign");
/* 1264 */					 sendPacket(new Packet130UpdateSign(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z, tileentitysign.lines));
/* 1265 */					 return;
/*			*/				 }
/*			*/ 
/*			*/			 }
/*			*/ 
/* 1272 */			 for (int j = 0; j < 4; j++) {
/* 1273 */				 boolean flag = true;
/*			*/ 
/* 1275 */				 if (packet130updatesign.lines[j].length() > 15)
/* 1276 */					 flag = false;
/*			*/				 else {
/* 1278 */					 for (int i = 0; i < packet130updatesign.lines[j].length(); i++) {
/* 1279 */						 if (SharedConstants.allowedCharacters.indexOf(packet130updatesign.lines[j].charAt(i)) < 0) {
/* 1280 */							 flag = false;
/*			*/						 }
/*			*/					 }
/*			*/				 }
/*			*/ 
/* 1285 */				 if (!flag) {
/* 1286 */					 packet130updatesign.lines[j] = "!?";
/*			*/				 }
/*			*/			 }
/*			*/ 
/* 1290 */			 if ((tileentity instanceof TileEntitySign)) {
/* 1291 */				 j = packet130updatesign.x;
/* 1292 */				 int k = packet130updatesign.y;
/*			*/ 
/* 1294 */				 int i = packet130updatesign.z;
/* 1295 */				 TileEntitySign tileentitysign1 = (TileEntitySign)tileentity;
/*			*/ 
/* 1298 */				 Player player = this.server.getPlayer(this.player);
/* 1299 */				 SignChangeEvent event = new SignChangeEvent((CraftBlock)player.getWorld().getBlockAt(j, k, i), this.server.getPlayer(this.player), packet130updatesign.lines);
/* 1300 */				 this.server.getPluginManager().callEvent(event);
/*			*/ 
/* 1302 */				 if (!event.isCancelled()) {
/* 1303 */					 for (int l = 0; l < 4; l++) {
/* 1304 */						 tileentitysign1.lines[l] = event.getLine(l);
/*			*/					 }
/* 1306 */					 tileentitysign1.isEditable = false;
/*			*/				 }
/*			*/ 
/* 1310 */				 tileentitysign1.update();
/* 1311 */				 worldserver.notify(j, k, i);
/*			*/			 }
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet0KeepAlive packet0keepalive) {
/* 1317 */		 if (packet0keepalive.a == this.i) {
/* 1318 */			 int i = (int)(System.nanoTime() / 1000000L - this.j);
/*			*/ 
/* 1320 */			 this.player.ping = ((this.player.ping * 3 + i) / 4);
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public boolean a() {
/* 1325 */		 return true;
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet202Abilities packet202abilities)
/*			*/	 {
/* 1330 */		 if ((this.player.abilities.canFly) && (this.player.abilities.isFlying != packet202abilities.f())) {
/* 1331 */			 PlayerToggleFlightEvent event = new PlayerToggleFlightEvent(this.server.getPlayer(this.player), packet202abilities.f());
/* 1332 */			 this.server.getPluginManager().callEvent(event);
/* 1333 */			 if (!event.isCancelled()) {
/* 1334 */				 this.player.abilities.isFlying = packet202abilities.f();
/*			*/			 }
/*			*/			 else
/* 1337 */				 this.player.updateAbilities();
/*			*/		 }
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet203TabComplete packet203tabcomplete)
/*			*/	 {
/* 1344 */		 StringBuilder stringbuilder = new StringBuilder();
/*			*/		 String s;
/* 1348 */		 for (Iterator iterator = this.minecraftServer.a(this.player, packet203tabcomplete.d()).iterator(); iterator.hasNext(); stringbuilder.append(s)) {
/* 1349 */			 s = (String)iterator.next();
/* 1350 */			 if (stringbuilder.length() > 0) {
/* 1351 */				 stringbuilder.append('\000');
/*			*/			 }
/*			*/		 }
/*			*/ 
/* 1355 */		 this.player.netServerHandler.sendPacket(new Packet203TabComplete(stringbuilder.toString()));
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet204LocaleAndViewDistance packet204localeandviewdistance) {
/* 1359 */		 this.player.a(packet204localeandviewdistance);
/*			*/	 }
/*			*/ 
/*			*/	 public void a(Packet250CustomPayload packet250custompayload)
/*			*/	 {
/*			*/		 DataInputStream datainputstream;
/*			*/		 ItemStack itemstack;
/*			*/		 ItemStack itemstack1;
/* 1367 */		 if ("MC|BEdit".equals(packet250custompayload.tag))
/*			*/			 try {
/* 1369 */				 datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
/* 1370 */				 itemstack = Packet.c(datainputstream);
/* 1371 */				 if (!ItemBookAndQuill.a(itemstack.getTag())) {
/* 1372 */					 throw new IOException("Invalid book tag!");
/*			*/				 }
/*			*/ 
/* 1375 */				 itemstack1 = this.player.inventory.getItemInHand();
/* 1376 */				 if ((itemstack != null) && (itemstack.id == Item.BOOK_AND_QUILL.id) && (itemstack.id == itemstack1.id))
/* 1377 */					 itemstack1.setTag(itemstack.getTag());
/*			*/			 }
/*			*/			 catch (Exception exception) {
/* 1380 */				 exception.printStackTrace();
/*			*/			 }
/* 1382 */		 else if ("MC|BSign".equals(packet250custompayload.tag)) {
/*			*/			 try {
/* 1384 */				 datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
/* 1385 */				 itemstack = Packet.c(datainputstream);
/* 1386 */				 if (!ItemWrittenBook.a(itemstack.getTag())) {
/* 1387 */					 throw new IOException("Invalid book tag!");
/*			*/				 }
/*			*/ 
/* 1390 */				 itemstack1 = this.player.inventory.getItemInHand();
/* 1391 */				 if ((itemstack != null) && (itemstack.id == Item.WRITTEN_BOOK.id) && (itemstack1.id == Item.BOOK_AND_QUILL.id)) {
/* 1392 */					 itemstack1.setTag(itemstack.getTag());
/* 1393 */					 itemstack1.id = Item.WRITTEN_BOOK.id;
/*			*/				 }
/*			*/			 }
/*			*/			 catch (Exception exception1)
/*			*/			 {
/*			*/			 }
/*			*/		 }
/* 1400 */		 else if ("MC|TrSel".equals(packet250custompayload.tag)) {
/*			*/			 try {
/* 1402 */				 datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
/* 1403 */				 int i = datainputstream.readInt();
/* 1404 */				 Container container = this.player.activeContainer;
/*			*/ 
/* 1406 */				 if ((container instanceof ContainerMerchant))
/* 1407 */					 ((ContainerMerchant)container).c(i);
/*			*/			 }
/*			*/			 catch (Exception exception2) {
/* 1410 */				 exception2.printStackTrace();
/*			*/			 }
/*			*/ 
/*			*/		 }
/* 1415 */		 else if (packet250custompayload.tag.equals("REGISTER"))
/*			*/			 try {
/* 1417 */				 String channels = new String(packet250custompayload.data, "UTF8");
/* 1418 */				 for (String channel : channels.split(""))
/* 1419 */					 getPlayer().addChannel(channel);
/*			*/			 }
/*			*/			 catch (UnsupportedEncodingException ex) {
/* 1422 */				 Logger.getLogger(NetServerHandler.class.getName()).log(Level.SEVERE, "Could not parse REGISTER payload in plugin message packet", ex);
/*			*/			 }
/* 1424 */		 else if (packet250custompayload.tag.equals("UNREGISTER"))
/*			*/			 try {
/* 1426 */				 String channels = new String(packet250custompayload.data, "UTF8");
/* 1427 */				 for (String channel : channels.split(""))
/* 1428 */					 getPlayer().removeChannel(channel);
/*			*/			 }
/*			*/			 catch (UnsupportedEncodingException ex) {
/* 1431 */				 Logger.getLogger(NetServerHandler.class.getName()).log(Level.SEVERE, "Could not parse UNREGISTER payload in plugin message packet", ex);
/*			*/			 }
/*			*/		 else
/* 1434 */			 this.server.getMessenger().dispatchIncomingMessage(this.player.getBukkitEntity(), packet250custompayload.tag, packet250custompayload.data);
/*			*/	 }
/*			*/ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetServerHandler
 * JD-Core Version:		0.6.0
 */