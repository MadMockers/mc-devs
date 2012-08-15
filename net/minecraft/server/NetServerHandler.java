package net.minecraft.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.CommandException;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.ChunkCompressionThread;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.PortalTravelAgent;
import org.bukkit.craftbukkit.TextWrapper;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.util.LazyPlayerSet;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.messaging.Messenger;

public class NetServerHandler extends NetHandler
{
	public static Logger logger = Logger.getLogger("Minecraft");
	public INetworkManager networkManager;
	public boolean disconnected = false;
	private MinecraftServer minecraftServer;
	public EntityPlayer player;
	private int f;
	private int g;
	private boolean h;
	private int i;
	private long j;
	private static Random k = new Random();
	private long l;
	private int m = 0;
	private int x = 0;
	private double y;
	private double z;
	private double q;
	public boolean checkMovement = true;
	private IntHashMap s = new IntHashMap();
	private final CraftServer server;
	private int lastTick = MinecraftServer.currentTick;
	private int lastDropTick = MinecraftServer.currentTick;
	private int dropCount = 0;
	private static final int PLACE_DISTANCE_SQUARED = 36;
	private double lastPosX = 1.7976931348623157E+308D;
	private double lastPosY = 1.7976931348623157E+308D;
	private double lastPosZ = 1.7976931348623157E+308D;
	private float lastPitch = 3.4028235E+38F;
	private float lastYaw = 3.4028235E+38F;
	private boolean justTeleported = false;
	Long lastPacket;
	private int lastMaterial;
	private static final HashSet<Integer> invalidItems = new HashSet(Arrays.asList(new Integer[] { Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(26), Integer.valueOf(34), Integer.valueOf(36), Integer.valueOf(51), Integer.valueOf(52), Integer.valueOf(55), Integer.valueOf(59), Integer.valueOf(60), Integer.valueOf(63), Integer.valueOf(64), Integer.valueOf(68), Integer.valueOf(71), Integer.valueOf(75), Integer.valueOf(78), Integer.valueOf(83), Integer.valueOf(90), Integer.valueOf(92), Integer.valueOf(93), Integer.valueOf(94), Integer.valueOf(95) }));

	public NetServerHandler(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayer entityplayer)
	{
		this.minecraftServer = minecraftserver;
		this.networkManager = inetworkmanager;
		inetworkmanager.a(this);
		this.player = entityplayer;
		entityplayer.netServerHandler = this;

		this.server = minecraftserver.server;
	}

	public CraftPlayer getPlayer()
	{
		return this.player == null ? null : this.player.getBukkitEntity();
	}

	public void d()
	{
		this.h = false;
		this.f += 1;

		this.networkManager.b();

		if (this.f - this.l > 20L) {
			this.l = this.f;
			this.j = (System.nanoTime() / 1000000L);
			this.i = k.nextInt();
			sendPacket(new Packet0KeepAlive(this.i));
		}

		if (this.m > 0) {
			this.m -= 1;
		}

		if (this.x > 0) {
			this.x -= 1;
		}

		if ((!this.h) && (!this.player.viewingCredits))
			this.player.g();
	}

	public void disconnect(String s)
	{
		if (!this.disconnected)
		{
			String leaveMessage = "§e" + this.player.name + " left the game.";

			PlayerKickEvent event = new PlayerKickEvent(this.server.getPlayer(this.player), s, leaveMessage);
			this.server.getPluginManager().callEvent(event);

			if (event.isCancelled())
			{
				return;
			}

			s = event.getReason();

			this.player.m();
			sendPacket(new Packet255KickDisconnect(s));
			this.networkManager.d();

			leaveMessage = event.getLeaveMessage();
			if ((leaveMessage != null) && (leaveMessage.length() > 0)) {
				this.minecraftServer.getServerConfigurationManager().sendAll(new Packet3Chat(leaveMessage));
			}
			getPlayer().disconnect(s);

			this.minecraftServer.getServerConfigurationManager().disconnect(this.player);
			this.disconnected = true;
		}
	}

	public void a(Packet10Flying packet10flying) {
		WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

		this.h = true;
		if (!this.player.viewingCredits)
		{
			if (!this.checkMovement) {
				double d0 = packet10flying.y - this.z;
				if ((packet10flying.x == this.y) && (d0 * d0 < 0.01D) && (packet10flying.z == this.q)) {
					this.checkMovement = true;
				}

			}

			Player player = getPlayer();
			Location from = new Location(player.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch);
			Location to = player.getLocation().clone();

			if ((packet10flying.hasPos) && ((!packet10flying.hasPos) || (packet10flying.y != -999.0D) || (packet10flying.stance != -999.0D))) {
				to.setX(packet10flying.x);
				to.setY(packet10flying.y);
				to.setZ(packet10flying.z);
			}

			if (packet10flying.hasLook) {
				to.setYaw(packet10flying.yaw);
				to.setPitch(packet10flying.pitch);
			}

			double delta = Math.pow(this.lastPosX - to.getX(), 2.0D) + Math.pow(this.lastPosY - to.getY(), 2.0D) + Math.pow(this.lastPosZ - to.getZ(), 2.0D);
			float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch());

			if (((delta > 0.00390625D) || (deltaAngle > 10.0F)) && (this.checkMovement) && (!this.player.dead)) {
				this.lastPosX = to.getX();
				this.lastPosY = to.getY();
				this.lastPosZ = to.getZ();
				this.lastYaw = to.getYaw();
				this.lastPitch = to.getPitch();

				if (from.getX() != 1.7976931348623157E+308D) {
					PlayerMoveEvent event = new PlayerMoveEvent(player, from, to);
					this.server.getPluginManager().callEvent(event);

					if (event.isCancelled()) {
						this.player.netServerHandler.sendPacket(new Packet13PlayerLookMove(from.getX(), from.getY() + 1.620000004768372D, from.getY(), from.getZ(), from.getYaw(), from.getPitch(), false));
						return;
					}

					if ((!to.equals(event.getTo())) && (!event.isCancelled())) {
						this.player.getBukkitEntity().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
						return;
					}

					if ((!from.equals(getPlayer().getLocation())) && (this.justTeleported)) {
						this.justTeleported = false;
						return;
					}
				}
			}

			if ((Double.isNaN(packet10flying.x)) || (Double.isNaN(packet10flying.y)) || (Double.isNaN(packet10flying.z)) || (Double.isNaN(packet10flying.stance))) {
				player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
				System.err.println(player.getName() + " was caught trying to crash the server with an invalid position.");
				player.kickPlayer("Nope!");
				return;
			}

			if ((this.checkMovement) && (!this.player.dead))
			{
				if (this.player.vehicle != null) {
					float f = this.player.yaw;
					float f1 = this.player.pitch;

					this.player.vehicle.V();
					double d1 = this.player.locX;
					double d2 = this.player.locY;
					double d3 = this.player.locZ;
					double d5 = 0.0D;

					double d4 = 0.0D;
					if (packet10flying.hasLook) {
						f = packet10flying.yaw;
						f1 = packet10flying.pitch;
					}

					if ((packet10flying.hasPos) && (packet10flying.y == -999.0D) && (packet10flying.stance == -999.0D))
					{
						if ((Math.abs(packet10flying.x) > 1.0D) || (Math.abs(packet10flying.z) > 1.0D)) {
							System.err.println(player.getName() + " was caught trying to crash the server with an invalid position.");
							player.kickPlayer("Nope!");
							return;
						}

						d5 = packet10flying.x;
						d4 = packet10flying.z;
					}

					this.player.onGround = packet10flying.g;
					this.player.g();
					this.player.move(d5, 0.0D, d4);
					this.player.setLocation(d1, d2, d3, f, f1);
					this.player.motX = d5;
					this.player.motZ = d4;
					if (this.player.vehicle != null) {
						worldserver.vehicleEnteredWorld(this.player.vehicle, true);
					}

					if (this.player.vehicle != null) {
						this.player.vehicle.V();
					}

					this.minecraftServer.getServerConfigurationManager().d(this.player);
					this.y = this.player.locX;
					this.z = this.player.locY;
					this.q = this.player.locZ;
					worldserver.playerJoinedWorld(this.player);
					return;
				}

				if (this.player.isSleeping()) {
					this.player.g();
					this.player.setLocation(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
					worldserver.playerJoinedWorld(this.player);
					return;
				}

				double d0 = this.player.locY;
				this.y = this.player.locX;
				this.z = this.player.locY;
				this.q = this.player.locZ;
				double d1 = this.player.locX;
				double d2 = this.player.locY;
				double d3 = this.player.locZ;
				float f2 = this.player.yaw;
				float f3 = this.player.pitch;

				if ((packet10flying.hasPos) && (packet10flying.y == -999.0D) && (packet10flying.stance == -999.0D)) {
					packet10flying.hasPos = false;
				}

				if (packet10flying.hasPos) {
					d1 = packet10flying.x;
					d2 = packet10flying.y;
					d3 = packet10flying.z;
					double d4 = packet10flying.stance - packet10flying.y;
					if ((!this.player.isSleeping()) && ((d4 > 1.65D) || (d4 < 0.1D))) {
						disconnect("Illegal stance");
						logger.warning(this.player.name + " had an illegal stance: " + d4);
						return;
					}

					if ((Math.abs(packet10flying.x) > 32000000.0D) || (Math.abs(packet10flying.z) > 32000000.0D)) {
						disconnect("Illegal position");
						return;
					}
				}

				if (packet10flying.hasLook) {
					f2 = packet10flying.yaw;
					f3 = packet10flying.pitch;
				}

				this.player.g();
				this.player.V = 0.0F;
				this.player.setLocation(this.y, this.z, this.q, f2, f3);
				if (!this.checkMovement) {
					return;
				}

				double d4 = d1 - this.player.locX;
				double d6 = d2 - this.player.locY;
				double d7 = d3 - this.player.locZ;

				double d8 = Math.max(Math.abs(d4), Math.abs(this.player.motX));
				double d9 = Math.max(Math.abs(d6), Math.abs(this.player.motY));
				double d10 = Math.max(Math.abs(d7), Math.abs(this.player.motZ));

				double d11 = d8 * d8 + d9 * d9 + d10 * d10;

				if ((d11 > 100.0D) && (this.checkMovement) && ((!this.minecraftServer.H()) || (!this.minecraftServer.G().equals(this.player.name)))) {
					logger.warning(this.player.name + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
					a(this.y, this.z, this.q, this.player.yaw, this.player.pitch);
					return;
				}

				float f4 = 0.0625F;
				boolean flag = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink(f4, f4, f4)).isEmpty();

				if ((this.player.onGround) && (!packet10flying.g) && (d6 > 0.0D)) {
					this.player.j(0.2F);
				}

				this.player.move(d4, d6, d7);
				this.player.onGround = packet10flying.g;
				this.player.checkMovement(d4, d6, d7);
				double d12 = d6;

				d4 = d1 - this.player.locX;
				d6 = d2 - this.player.locY;
				if ((d6 > -0.5D) || (d6 < 0.5D)) {
					d6 = 0.0D;
				}

				d7 = d3 - this.player.locZ;
				d11 = d4 * d4 + d6 * d6 + d7 * d7;
				boolean flag1 = false;

				if ((d11 > 0.0625D) && (!this.player.isSleeping()) && (!this.player.itemInWorldManager.isCreative())) {
					flag1 = true;
					logger.warning(this.player.name + " moved wrongly!");
				}

				this.player.setLocation(d1, d2, d3, f2, f3);
				boolean flag2 = worldserver.getCubes(this.player, this.player.boundingBox.clone().shrink(f4, f4, f4)).isEmpty();

				if ((flag) && ((flag1) || (!flag2)) && (!this.player.isSleeping())) {
					a(this.y, this.z, this.q, f2, f3);
					return;
				}

				AxisAlignedBB axisalignedbb = this.player.boundingBox.clone().grow(f4, f4, f4).a(0.0D, -0.55D, 0.0D);

				if ((!this.minecraftServer.getAllowFlight()) && (!this.player.abilities.canFly) && (!worldserver.c(axisalignedbb))) {
					if (d12 >= -0.03125D) {
						this.g += 1;
						if (this.g > 80) {
							logger.warning(this.player.name + " was kicked for floating too long!");
							disconnect("Flying is not enabled on this server");
							return;
						}
					}
				}
				else this.g = 0;

				this.player.onGround = packet10flying.g;
				this.minecraftServer.getServerConfigurationManager().d(this.player);
				if (this.player.itemInWorldManager.isCreative()) return;
				this.player.b(this.player.locY - d0, packet10flying.g);
			}
		}
	}

	public void a(double d0, double d1, double d2, float f, float f1)
	{
		Player player = getPlayer();
		Location from = player.getLocation();
		Location to = new Location(getPlayer().getWorld(), d0, d1, d2, f, f1);
		PlayerTeleportEvent event = new PlayerTeleportEvent(player, from, to, PlayerTeleportEvent.TeleportCause.UNKNOWN);
		this.server.getPluginManager().callEvent(event);

		from = event.getFrom();
		to = event.isCancelled() ? from : event.getTo();

		teleport(to);
	}

	public void teleport(Location dest)
	{
		double d0 = dest.getX();
		double d1 = dest.getY();
		double d2 = dest.getZ();
		float f = dest.getYaw();
		float f1 = dest.getPitch();

		if (Float.isNaN(f)) {
			f = 0.0F;
		}

		if (Float.isNaN(f1)) {
			f1 = 0.0F;
		}

		this.lastPosX = d0;
		this.lastPosY = d1;
		this.lastPosZ = d2;
		this.lastYaw = f;
		this.lastPitch = f1;
		this.justTeleported = true;

		this.checkMovement = false;
		this.y = d0;
		this.z = d1;
		this.q = d2;
		this.player.setLocation(d0, d1, d2, f, f1);
		this.player.netServerHandler.sendPacket(new Packet13PlayerLookMove(d0, d1 + 1.620000004768372D, d1, d2, f, f1, false));
	}

	public void a(Packet14BlockDig packet14blockdig) {
		if (this.player.dead) return;

		WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

		if (packet14blockdig.e == 4)
		{
			if (this.lastDropTick != MinecraftServer.currentTick) {
				this.dropCount = 0;
				this.lastDropTick = MinecraftServer.currentTick;
			}
			else {
				this.dropCount += 1;
				if (this.dropCount >= 20) {
					logger.warning(this.player.name + " dropped their items too quickly!");
					disconnect("You dropped your items too quickly (Hacking?)");
					return;
				}
			}

			this.player.bB();
		} else if (packet14blockdig.e == 5) {
			this.player.by();
		} else {
			boolean flag = worldserver.weirdIsOpCache = (worldserver.dimension != 0) || (this.minecraftServer.getServerConfigurationManager().isOp(this.player.name)) || (this.minecraftServer.H()) ? 1 : 0;
			boolean flag1 = false;

			if (packet14blockdig.e == 0) {
				flag1 = true;
			}

			if (packet14blockdig.e == 2) {
				flag1 = true;
			}

			int i = packet14blockdig.a;
			int j = packet14blockdig.b;
			int k = packet14blockdig.c;

			if (flag1) {
				double d0 = this.player.locX - (i + 0.5D);
				double d1 = this.player.locY - (j + 0.5D) + 1.5D;
				double d2 = this.player.locZ - (k + 0.5D);
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;

				if (d3 > 36.0D) {
					return;
				}

				if (j >= this.minecraftServer.getMaxBuildHeight()) {
					return;
				}
			}

			ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
			int l = MathHelper.a(i - chunkcoordinates.x);
			int i1 = MathHelper.a(k - chunkcoordinates.z);

			if (l > i1) {
				i1 = l;
			}

			if (packet14blockdig.e == 0)
			{
				if ((i1 < this.server.getSpawnRadius()) && (!flag)) {
					CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_BLOCK, i, j, k, l, this.player.inventory.getItemInHand());

					this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
				} else {
					this.player.itemInWorldManager.dig(i, j, k, packet14blockdig.face);
				}
			} else if (packet14blockdig.e == 2) {
				this.player.itemInWorldManager.a(i, j, k);
				if (worldserver.getTypeId(i, j, k) != 0)
					this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
			}
			else if (packet14blockdig.e == 1) {
				this.player.itemInWorldManager.c(i, j, k);
				if (worldserver.getTypeId(i, j, k) != 0)
					this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
			}
			else if (packet14blockdig.e == 3) {
				double d4 = this.player.locX - (i + 0.5D);
				double d5 = this.player.locY - (j + 0.5D);
				double d6 = this.player.locZ - (k + 0.5D);
				double d7 = d4 * d4 + d5 * d5 + d6 * d6;

				if (d7 < 256.0D) {
					this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
				}
			}

			worldserver.weirdIsOpCache = false;
		}
	}

	public void a(Packet15Place packet15place) {
		WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

		if (this.player.dead) return;

		if (packet15place.getFace() == 255) {
			if ((packet15place.getItemStack() != null) && (packet15place.getItemStack().id == this.lastMaterial) && (this.lastPacket != null) && (packet15place.timestamp - this.lastPacket.longValue() < 100L)) {
				this.lastPacket = null;
				return;
			}
		} else {
			this.lastMaterial = (packet15place.getItemStack() == null ? -1 : packet15place.getItemStack().id);
			this.lastPacket = Long.valueOf(packet15place.timestamp);
		}

		boolean always = false;

		ItemStack itemstack = this.player.inventory.getItemInHand();
		boolean flag = false;
		int i = packet15place.d();
		int j = packet15place.f();
		int k = packet15place.g();
		int l = packet15place.getFace();
		boolean flag1 = worldserver.weirdIsOpCache = (worldserver.worldProvider.dimension != 0) || (this.minecraftServer.getServerConfigurationManager().isOp(this.player.name)) || (this.minecraftServer.H()) ? 1 : 0;

		if (packet15place.getFace() == 255) {
			if (itemstack == null) {
				return;
			}

			int itemstackAmount = itemstack.count;
			PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.RIGHT_CLICK_AIR, itemstack);
			if (event.useItemInHand() != Event.Result.DENY) {
				this.player.itemInWorldManager.useItem(this.player, this.player.world, itemstack);
			}

			always = itemstack.count != itemstackAmount;
		}
		else if ((packet15place.f() >= this.minecraftServer.getMaxBuildHeight() - 1) && ((packet15place.getFace() == 1) || (packet15place.f() >= this.minecraftServer.getMaxBuildHeight()))) {
			this.player.netServerHandler.sendPacket(new Packet3Chat("§7Height limit for building is " + this.minecraftServer.getMaxBuildHeight()));
			flag = true;
		} else {
			ChunkCoordinates chunkcoordinates = worldserver.getSpawn();
			int i1 = MathHelper.a(i - chunkcoordinates.x);
			int j1 = MathHelper.a(k - chunkcoordinates.z);

			if (i1 > j1) {
				j1 = i1;
			}

			Location eyeLoc = getPlayer().getEyeLocation();
			if (Math.pow(eyeLoc.getX() - i, 2.0D) + Math.pow(eyeLoc.getY() - j, 2.0D) + Math.pow(eyeLoc.getZ() - k, 2.0D) > 36.0D) {
				return;
			}
			flag1 = true;
			if ((j1 > 16) || (flag1))
			{
				this.player.itemInWorldManager.interact(this.player, worldserver, itemstack, i, j, k, l, packet15place.j(), packet15place.l(), packet15place.m());
			}

			flag = true;
		}

		if (flag) {
			this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
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

			this.player.netServerHandler.sendPacket(new Packet53BlockChange(i, j, k, worldserver));
		}

		itemstack = this.player.inventory.getItemInHand();
		if ((itemstack != null) && (itemstack.count == 0)) {
			this.player.inventory.items[this.player.inventory.itemInHandIndex] = null;
			itemstack = null;
		}

		if ((itemstack == null) || (itemstack.m() == 0)) {
			this.player.h = true;
			this.player.inventory.items[this.player.inventory.itemInHandIndex] = ItemStack.b(this.player.inventory.items[this.player.inventory.itemInHandIndex]);
			Slot slot = this.player.activeContainer.a(this.player.inventory, this.player.inventory.itemInHandIndex);

			this.player.activeContainer.b();
			this.player.h = false;

			if ((!ItemStack.matches(this.player.inventory.getItemInHand(), packet15place.getItemStack())) || (always)) {
				sendPacket(new Packet103SetSlot(this.player.activeContainer.windowId, slot.d, this.player.inventory.getItemInHand()));
			}
		}

		worldserver.weirdIsOpCache = false;
	}

	public void a(String s, Object[] aobject) {
		if (this.disconnected) return;

		logger.info(this.player.name + " lost connection: " + s);

		String quitMessage = this.minecraftServer.getServerConfigurationManager().disconnect(this.player);
		if ((quitMessage != null) && (quitMessage.length() > 0)) {
			this.minecraftServer.getServerConfigurationManager().sendAll(new Packet3Chat(quitMessage));
		}

		this.disconnected = true;
		if ((this.minecraftServer.H()) && (this.player.name.equals(this.minecraftServer.G()))) {
			logger.info("Stopping singleplayer server as player logged out");
			this.minecraftServer.safeShutdown();
		}
	}

	public void onUnhandledPacket(Packet packet) {
		if (this.disconnected) return;
		logger.warning(getClass() + " wasn't prepared to deal with a " + packet.getClass());
		disconnect("Protocol error, unexpected packet");
	}

	public void sendPacket(Packet packet) {
		if ((packet instanceof Packet3Chat)) {
			Packet3Chat packet3chat = (Packet3Chat)packet;
			int i = this.player.getChatFlags();

			if (i == 2) {
				return;
			}

			if ((i == 1) && (!packet3chat.isServer())) {
				return;
			}

			String message = packet3chat.message;
			for (String line : TextWrapper.wrapText(message)) {
				this.networkManager.queue(new Packet3Chat(line));
			}
			return;
		}

		if (packet == null)
			return;
		if ((packet instanceof Packet6SpawnPosition)) {
			Packet6SpawnPosition packet6 = (Packet6SpawnPosition)packet;
			this.player.compassTarget = new Location(getPlayer().getWorld(), packet6.x, packet6.y, packet6.z);
		} else if (packet.lowPriority == true)
		{
			ChunkCompressionThread.sendPacket(this.player, packet);
			return;
		}

		this.networkManager.queue(packet);
	}

	public void a(Packet16BlockItemSwitch packet16blockitemswitch)
	{
		if (this.player.dead) return;

		if ((packet16blockitemswitch.itemInHandIndex >= 0) && (packet16blockitemswitch.itemInHandIndex < PlayerInventory.getHotbarSize())) {
			PlayerItemHeldEvent event = new PlayerItemHeldEvent(getPlayer(), this.player.inventory.itemInHandIndex, packet16blockitemswitch.itemInHandIndex);
			this.server.getPluginManager().callEvent(event);

			this.player.inventory.itemInHandIndex = packet16blockitemswitch.itemInHandIndex;
		} else {
			logger.warning(this.player.name + " tried to set an invalid carried item");
			disconnect("Nope!");
		}
	}

	public void a(Packet3Chat packet3chat) {
		if (this.player.getChatFlags() == 2) {
			sendPacket(new Packet3Chat("Cannot send chat message."));
		} else {
			String s = packet3chat.message;

			if (s.length() > 100) {
				disconnect("Chat message too long");
			} else {
				s = s.trim();

				for (int i = 0; i < s.length(); i++) {
					if (!SharedConstants.isAllowedChatCharacter(s.charAt(i))) {
						disconnect("Illegal characters in chat");
						return;
					}

				}

				if (this.player.getChatFlags() == 1) {
					sendPacket(new Packet3Chat("Cannot send chat message."));
					return;
				}

				chat(s, packet3chat.a_());
			}
		}
	}

	public void chat(String s, boolean async) {
		if (!this.player.dead) {
			if (s.length() == 0) {
				logger.warning(this.player.name + " tried to send an empty message");
				return;
			}

			if (getPlayer().isConversing()) {
				getPlayer().acceptConversationInput(s);
				return;
			}

			if (s.startsWith("/")) {
				handleCommand(s);
				return;
			}
			Player player = getPlayer();
			AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(async, player, s, new LazyPlayerSet());
			this.server.getPluginManager().callEvent(event);

			if (PlayerChatEvent.getHandlerList().getRegisteredListeners().length != 0)
			{
				PlayerChatEvent queueEvent = new PlayerChatEvent(player, event.getMessage(), event.getFormat(), event.getRecipients());
				queueEvent.setCancelled(event.isCancelled());
				this.minecraftServer.chatQueue.add(queueEvent);
			} else {
				if (event.isCancelled()) {
					return;
				}

				s = String.format(event.getFormat(), new Object[] { event.getPlayer().getDisplayName(), event.getMessage() });
				this.minecraftServer.console.sendMessage(s);
				Iterator i$;
				if (((LazyPlayerSet)event.getRecipients()).isLazy())
					for (i$ = this.minecraftServer.getServerConfigurationManager().players.iterator(); i$.hasNext(); ) { Object recipient = i$.next();
						((EntityPlayer)recipient).sendMessage(s);
					}
				else {
					for (Player recipient : event.getRecipients()) {
						recipient.sendMessage(s);
					}
				}

			}

			this.m += 20;
			if ((this.m > 200) && (!this.minecraftServer.getServerConfigurationManager().isOp(this.player.name)))
				disconnect("disconnect.spam");
		}
	}

	private void handleCommand(String s)
	{
		CraftPlayer player = getPlayer();

		PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, s, new LazyPlayerSet());
		this.server.getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}
		try
		{
			if (this.server.dispatchCommand(event.getPlayer(), event.getMessage().substring(1)))
				return;
		}
		catch (CommandException ex) {
			player.sendMessage(ChatColor.RED + "An internal error occurred while attempting to perform this command");
			Logger.getLogger(NetServerHandler.class.getName()).log(Level.SEVERE, null, ex);
			return;
		}
	}

	public void a(Packet18ArmAnimation packet18armanimation)
	{
		if (this.player.dead) return;

		if (packet18armanimation.b == 1)
		{
			float f = 1.0F;
			float f1 = this.player.lastPitch + (this.player.pitch - this.player.lastPitch) * f;
			float f2 = this.player.lastYaw + (this.player.yaw - this.player.lastYaw) * f;
			double d0 = this.player.lastX + (this.player.locX - this.player.lastX) * f;
			double d1 = this.player.lastY + (this.player.locY - this.player.lastY) * f + 1.62D - this.player.height;
			double d2 = this.player.lastZ + (this.player.locZ - this.player.lastZ) * f;
			Vec3D vec3d = Vec3D.a().create(d0, d1, d2);

			float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
			float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
			float f5 = -MathHelper.cos(-f1 * 0.01745329F);
			float f6 = MathHelper.sin(-f1 * 0.01745329F);
			float f7 = f4 * f5;
			float f8 = f3 * f5;
			double d3 = 5.0D;
			Vec3D vec3d1 = vec3d.add(f7 * d3, f6 * d3, f8 * d3);
			MovingObjectPosition movingobjectposition = this.player.world.rayTrace(vec3d, vec3d1, true);

			if ((movingobjectposition == null) || (movingobjectposition.type != EnumMovingObjectType.TILE)) {
				CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_AIR, this.player.inventory.getItemInHand());
			}

			PlayerAnimationEvent event = new PlayerAnimationEvent(getPlayer());
			this.server.getPluginManager().callEvent(event);

			if (event.isCancelled()) return;

			this.player.i();
		}
	}

	public void a(Packet19EntityAction packet19entityaction)
	{
		if (this.player.dead) return;

		if ((packet19entityaction.animation == 1) || (packet19entityaction.animation == 2)) {
			PlayerToggleSneakEvent event = new PlayerToggleSneakEvent(getPlayer(), packet19entityaction.animation == 1);
			this.server.getPluginManager().callEvent(event);

			if (event.isCancelled()) {
				return;
			}
		}

		if ((packet19entityaction.animation == 4) || (packet19entityaction.animation == 5)) {
			PlayerToggleSprintEvent event = new PlayerToggleSprintEvent(getPlayer(), packet19entityaction.animation == 4);
			this.server.getPluginManager().callEvent(event);

			if (event.isCancelled()) {
				return;
			}

		}

		if (packet19entityaction.animation == 1)
			this.player.setSneaking(true);
		else if (packet19entityaction.animation == 2)
			this.player.setSneaking(false);
		else if (packet19entityaction.animation == 4)
			this.player.setSprinting(true);
		else if (packet19entityaction.animation == 5)
			this.player.setSprinting(false);
		else if (packet19entityaction.animation == 3)
			this.player.a(false, true, true);
	}

	public void a(Packet255KickDisconnect packet255kickdisconnect)
	{
		getPlayer().disconnect("disconnect.quitting");

		this.networkManager.a("disconnect.quitting", new Object[0]);
	}

	public int lowPriorityCount() {
		return this.networkManager.e();
	}

	public void a(Packet7UseEntity packet7useentity) {
		if (this.player.dead) return;

		WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);
		Entity entity = worldserver.getEntity(packet7useentity.target);

		if (entity != null) {
			boolean flag = this.player.l(entity);
			double d0 = 36.0D;

			if (!flag) {
				d0 = 9.0D;
			}

			if (this.player.e(entity) < d0) {
				ItemStack itemInHand = this.player.inventory.getItemInHand();
				if (packet7useentity.action == 0)
				{
					PlayerInteractEntityEvent event = new PlayerInteractEntityEvent(getPlayer(), entity.getBukkitEntity());
					this.server.getPluginManager().callEvent(event);

					if (event.isCancelled()) {
						return;
					}

					this.player.m(entity);

					if ((itemInHand != null) && (itemInHand.count <= -1))
						this.player.updateInventory(this.player.activeContainer);
				}
				else if (packet7useentity.action == 1) {
					if (((entity instanceof EntityItem)) || ((entity instanceof EntityExperienceOrb)) || ((entity instanceof EntityArrow))) {
						String type = entity.getClass().getSimpleName();
						disconnect("Attacking an " + type + " is not permitted");
						System.out.println("Player " + this.player.name + " tried to attack an " + type + ", so I have disconnected them for exploiting.");
						return;
					}

					this.player.attack(entity);

					if ((itemInHand != null) && (itemInHand.count <= -1))
						this.player.updateInventory(this.player.activeContainer);
				}
			}
		}
	}

	public void a(Packet205ClientCommand packet205clientcommand)
	{
		if (packet205clientcommand.a == 1)
			if (this.player.viewingCredits)
			{
				PortalTravelAgent pta = new PortalTravelAgent();
				Location toLocation;
				if (this.player.getBukkitEntity().getBedSpawnLocation() == null) {
					CraftWorld cworld = (CraftWorld)this.server.getWorlds().get(0);
					ChunkCoordinates chunkcoordinates = cworld.getHandle().getSpawn();
					Location toLocation = new Location(cworld, chunkcoordinates.x + 0.5D, chunkcoordinates.y, chunkcoordinates.z + 0.5D);
					this.player.netServerHandler.sendPacket(new Packet70Bed(0, 0));
				} else {
					toLocation = this.player.getBukkitEntity().getBedSpawnLocation();
					toLocation = new Location(toLocation.getWorld(), toLocation.getX() + 0.5D, toLocation.getY(), toLocation.getZ() + 0.5D);
				}

				PlayerPortalEvent event = new PlayerPortalEvent(this.player.getBukkitEntity(), this.player.getBukkitEntity().getLocation(), toLocation, pta, PlayerTeleportEvent.TeleportCause.END_PORTAL);
				event.useTravelAgent(false);

				Bukkit.getServer().getPluginManager().callEvent(event);
				this.player = this.minecraftServer.getServerConfigurationManager().moveToWorld(this.player, 0, true, event.getTo());
			}
			else if (this.player.q().getWorldData().isHardcore()) {
				if ((this.minecraftServer.H()) && (this.player.name.equals(this.minecraftServer.G()))) {
					this.player.netServerHandler.disconnect("You have died. Game over, man, it's game over!");
					this.minecraftServer.O();
				} else {
					BanEntry banentry = new BanEntry(this.player.name);

					banentry.setReason("Death in Hardcore");
					this.minecraftServer.getServerConfigurationManager().getNameBans().add(banentry);
					this.player.netServerHandler.disconnect("You have died. Game over, man, it's game over!");
				}
			} else {
				if (this.player.getHealth() > 0) {
					return;
				}

				this.player = this.minecraftServer.getServerConfigurationManager().moveToWorld(this.player, 0, false);
			}
	}

	public boolean b()
	{
		return true;
	}
	public void a(Packet9Respawn packet9respawn) {
	}

	public void handleContainerClose(Packet101CloseWindow packet101closewindow) {
		if (this.player.dead) return;

		InventoryCloseEvent event = new InventoryCloseEvent(this.player.activeContainer.getBukkitView());
		this.server.getPluginManager().callEvent(event);
		this.player.activeContainer.transferTo(this.player.defaultContainer, getPlayer());

		this.player.l();
	}

	public void a(Packet102WindowClick packet102windowclick) {
		if (this.player.dead) return;

		if ((this.player.activeContainer.windowId == packet102windowclick.a) && (this.player.activeContainer.b(this.player)))
		{
			InventoryView inventory = this.player.activeContainer.getBukkitView();
			InventoryType.SlotType type = CraftInventoryView.getSlotType(inventory, packet102windowclick.slot);

			InventoryClickEvent event = new InventoryClickEvent(inventory, type, packet102windowclick.slot, packet102windowclick.button != 0, packet102windowclick.shift);
			Inventory top = inventory.getTopInventory();
			if ((packet102windowclick.slot == 0) && ((top instanceof CraftingInventory))) {
				Recipe recipe = ((CraftingInventory)top).getRecipe();
				if (recipe != null) {
					event = new CraftItemEvent(recipe, inventory, type, packet102windowclick.slot, packet102windowclick.button != 0, packet102windowclick.shift);
				}
			}
			this.server.getPluginManager().callEvent(event);

			ItemStack itemstack = null;
			boolean defaultBehaviour = false;

			switch (1.$SwitchMap$org$bukkit$event$Event$Result[event.getResult().ordinal()]) {
			case 1:
				itemstack = this.player.activeContainer.clickItem(packet102windowclick.slot, packet102windowclick.button, packet102windowclick.shift, this.player);
				defaultBehaviour = true;
				break;
			case 2:
				break;
			case 3:
				org.bukkit.inventory.ItemStack cursor = event.getCursor();
				if (cursor == null)
					this.player.inventory.setCarried((ItemStack)null);
				else {
					this.player.inventory.setCarried(CraftItemStack.createNMSItemStack(cursor));
				}
				org.bukkit.inventory.ItemStack item = event.getCurrentItem();
				if (item != null) {
					itemstack = CraftItemStack.createNMSItemStack(item);
					if (packet102windowclick.slot == -999)
						this.player.drop(itemstack);
					else
						this.player.activeContainer.getSlot(packet102windowclick.slot).set(itemstack);
				} else {
					if (packet102windowclick.slot == -999) break;
					this.player.activeContainer.getSlot(packet102windowclick.slot).set((ItemStack)null);
				}

			}

			if (ItemStack.matches(packet102windowclick.item, itemstack)) {
				this.player.netServerHandler.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, true));
				this.player.h = true;
				this.player.activeContainer.b();
				this.player.broadcastCarriedItem();
				this.player.h = false;
			} else {
				this.s.a(this.player.activeContainer.windowId, Short.valueOf(packet102windowclick.d));
				this.player.netServerHandler.sendPacket(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, false));
				this.player.activeContainer.a(this.player, false);
				ArrayList arraylist = new ArrayList();

				for (int i = 0; i < this.player.activeContainer.b.size(); i++) {
					arraylist.add(((Slot)this.player.activeContainer.b.get(i)).getItem());
				}

				this.player.a(this.player.activeContainer, arraylist);

				if ((type == InventoryType.SlotType.RESULT) && (itemstack != null))
					this.player.netServerHandler.sendPacket(new Packet103SetSlot(this.player.activeContainer.windowId, 0, itemstack));
			}
		}
	}

	public void a(Packet108ButtonClick packet108buttonclick)
	{
		if ((this.player.activeContainer.windowId == packet108buttonclick.a) && (this.player.activeContainer.b(this.player))) {
			this.player.activeContainer.a(this.player, packet108buttonclick.b);
			this.player.activeContainer.b();
		}
	}

	public void a(Packet107SetCreativeSlot packet107setcreativeslot) {
		if (this.player.itemInWorldManager.isCreative()) {
			boolean flag = packet107setcreativeslot.slot < 0;
			ItemStack itemstack = packet107setcreativeslot.b;
			boolean flag1 = (packet107setcreativeslot.slot >= 1) && (packet107setcreativeslot.slot < 36 + PlayerInventory.getHotbarSize());

			boolean flag2 = (itemstack == null) || ((itemstack.id < Item.byId.length) && (itemstack.id >= 0) && (Item.byId[itemstack.id] != null) && (!invalidItems.contains(Integer.valueOf(itemstack.id))));
			boolean flag3 = (itemstack == null) || ((itemstack.getData() >= 0) && (itemstack.getData() >= 0) && (itemstack.count <= 64) && (itemstack.count > 0));

			HumanEntity player = this.player.getBukkitEntity();
			InventoryView inventory = new CraftInventoryView(player, player.getInventory(), this.player.defaultContainer);
			InventoryType.SlotType slot = InventoryType.SlotType.QUICKBAR;
			if (packet107setcreativeslot.slot == -1) {
				slot = InventoryType.SlotType.OUTSIDE;
			}

			InventoryClickEvent event = new InventoryClickEvent(inventory, slot, slot == InventoryType.SlotType.OUTSIDE ? -999 : packet107setcreativeslot.slot, false, false);
			this.server.getPluginManager().callEvent(event);
			org.bukkit.inventory.ItemStack item = event.getCurrentItem();

			switch (1.$SwitchMap$org$bukkit$event$Event$Result[event.getResult().ordinal()]) {
			case 3:
				if (slot == InventoryType.SlotType.QUICKBAR) {
					if (item == null)
						this.player.defaultContainer.setItem(packet107setcreativeslot.slot, (ItemStack)null);
					else
						this.player.defaultContainer.setItem(packet107setcreativeslot.slot, CraftItemStack.createNMSItemStack(item));
				}
				else if (item != null) {
					this.player.drop(CraftItemStack.createNMSItemStack(item));
				}
				return;
			case 2:
				if (packet107setcreativeslot.slot > -1) {
					this.player.netServerHandler.sendPacket(new Packet103SetSlot(this.player.defaultContainer.windowId, packet107setcreativeslot.slot, CraftItemStack.createNMSItemStack(item)));
				}
				return;
			case 1:
				break;
			default:
				return;
			}

			if ((flag1) && (flag2) && (flag3)) {
				if (itemstack == null)
					this.player.defaultContainer.setItem(packet107setcreativeslot.slot, (ItemStack)null);
				else {
					this.player.defaultContainer.setItem(packet107setcreativeslot.slot, itemstack);
				}

				this.player.defaultContainer.a(this.player, true);
			} else if ((flag) && (flag2) && (flag3) && (this.x < 200)) {
				this.x += 20;
				EntityItem entityitem = this.player.drop(itemstack);

				if (entityitem != null)
					entityitem.d();
			}
		}
	}

	public void a(Packet106Transaction packet106transaction)
	{
		if (this.player.dead) return;
		Short oshort = (Short)this.s.get(this.player.activeContainer.windowId);

		if ((oshort != null) && (packet106transaction.b == oshort.shortValue()) && (this.player.activeContainer.windowId == packet106transaction.a) && (!this.player.activeContainer.b(this.player)))
			this.player.activeContainer.a(this.player, true);
	}

	public void a(Packet130UpdateSign packet130updatesign)
	{
		if (this.player.dead) return;

		WorldServer worldserver = this.minecraftServer.getWorldServer(this.player.dimension);

		if (worldserver.isLoaded(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z)) {
			TileEntity tileentity = worldserver.getTileEntity(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z);

			if ((tileentity instanceof TileEntitySign)) {
				TileEntitySign tileentitysign = (TileEntitySign)tileentity;

				if (!tileentitysign.a()) {
					this.minecraftServer.warning("Player " + this.player.name + " just tried to change non-editable sign");
					sendPacket(new Packet130UpdateSign(packet130updatesign.x, packet130updatesign.y, packet130updatesign.z, tileentitysign.lines));
					return;
				}

			}

			for (int j = 0; j < 4; j++) {
				boolean flag = true;

				if (packet130updatesign.lines[j].length() > 15)
					flag = false;
				else {
					for (int i = 0; i < packet130updatesign.lines[j].length(); i++) {
						if (SharedConstants.allowedCharacters.indexOf(packet130updatesign.lines[j].charAt(i)) < 0) {
							flag = false;
						}
					}
				}

				if (!flag) {
					packet130updatesign.lines[j] = "!?";
				}
			}

			if ((tileentity instanceof TileEntitySign)) {
				j = packet130updatesign.x;
				int k = packet130updatesign.y;

				int i = packet130updatesign.z;
				TileEntitySign tileentitysign1 = (TileEntitySign)tileentity;

				Player player = this.server.getPlayer(this.player);
				SignChangeEvent event = new SignChangeEvent((CraftBlock)player.getWorld().getBlockAt(j, k, i), this.server.getPlayer(this.player), packet130updatesign.lines);
				this.server.getPluginManager().callEvent(event);

				if (!event.isCancelled()) {
					for (int l = 0; l < 4; l++) {
						tileentitysign1.lines[l] = event.getLine(l);
					}
					tileentitysign1.isEditable = false;
				}

				tileentitysign1.update();
				worldserver.notify(j, k, i);
			}
		}
	}

	public void a(Packet0KeepAlive packet0keepalive) {
		if (packet0keepalive.a == this.i) {
			int i = (int)(System.nanoTime() / 1000000L - this.j);

			this.player.ping = ((this.player.ping * 3 + i) / 4);
		}
	}

	public boolean a() {
		return true;
	}

	public void a(Packet202Abilities packet202abilities)
	{
		if ((this.player.abilities.canFly) && (this.player.abilities.isFlying != packet202abilities.f())) {
			PlayerToggleFlightEvent event = new PlayerToggleFlightEvent(this.server.getPlayer(this.player), packet202abilities.f());
			this.server.getPluginManager().callEvent(event);
			if (!event.isCancelled()) {
				this.player.abilities.isFlying = packet202abilities.f();
			}
			else
				this.player.updateAbilities();
		}
	}

	public void a(Packet203TabComplete packet203tabcomplete)
	{
		StringBuilder stringbuilder = new StringBuilder();
		String s;
		for (Iterator iterator = this.minecraftServer.a(this.player, packet203tabcomplete.d()).iterator(); iterator.hasNext(); stringbuilder.append(s)) {
			s = (String)iterator.next();
			if (stringbuilder.length() > 0) {
				stringbuilder.append('\000');
			}
		}

		this.player.netServerHandler.sendPacket(new Packet203TabComplete(stringbuilder.toString()));
	}

	public void a(Packet204LocaleAndViewDistance packet204localeandviewdistance) {
		this.player.a(packet204localeandviewdistance);
	}

	public void a(Packet250CustomPayload packet250custompayload)
	{
		DataInputStream datainputstream;
		ItemStack itemstack;
		ItemStack itemstack1;
		if ("MC|BEdit".equals(packet250custompayload.tag))
			try {
				datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
				itemstack = Packet.c(datainputstream);
				if (!ItemBookAndQuill.a(itemstack.getTag())) {
					throw new IOException("Invalid book tag!");
				}

				itemstack1 = this.player.inventory.getItemInHand();
				if ((itemstack != null) && (itemstack.id == Item.BOOK_AND_QUILL.id) && (itemstack.id == itemstack1.id))
					itemstack1.setTag(itemstack.getTag());
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		else if ("MC|BSign".equals(packet250custompayload.tag)) {
			try {
				datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
				itemstack = Packet.c(datainputstream);
				if (!ItemWrittenBook.a(itemstack.getTag())) {
					throw new IOException("Invalid book tag!");
				}

				itemstack1 = this.player.inventory.getItemInHand();
				if ((itemstack != null) && (itemstack.id == Item.WRITTEN_BOOK.id) && (itemstack1.id == Item.BOOK_AND_QUILL.id)) {
					itemstack1.setTag(itemstack.getTag());
					itemstack1.id = Item.WRITTEN_BOOK.id;
				}
			}
			catch (Exception exception1)
			{
			}
		}
		else if ("MC|TrSel".equals(packet250custompayload.tag)) {
			try {
				datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.data));
				int i = datainputstream.readInt();
				Container container = this.player.activeContainer;

				if ((container instanceof ContainerMerchant))
					((ContainerMerchant)container).c(i);
			}
			catch (Exception exception2) {
				exception2.printStackTrace();
			}

		}
		else if (packet250custompayload.tag.equals("REGISTER"))
			try {
				String channels = new String(packet250custompayload.data, "UTF8");
				for (String channel : channels.split(""))
					getPlayer().addChannel(channel);
			}
			catch (UnsupportedEncodingException ex) {
				Logger.getLogger(NetServerHandler.class.getName()).log(Level.SEVERE, "Could not parse REGISTER payload in plugin message packet", ex);
			}
		else if (packet250custompayload.tag.equals("UNREGISTER"))
			try {
				String channels = new String(packet250custompayload.data, "UTF8");
				for (String channel : channels.split(""))
					getPlayer().removeChannel(channel);
			}
			catch (UnsupportedEncodingException ex) {
				Logger.getLogger(NetServerHandler.class.getName()).log(Level.SEVERE, "Could not parse UNREGISTER payload in plugin message packet", ex);
			}
		else
			this.server.getMessenger().dispatchIncomingMessage(this.player.getBukkitEntity(), packet250custompayload.tag, packet250custompayload.data);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NetServerHandler
 * JD-Core Version:		0.6.0
 */