package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.server.ServerListPingEvent;

public class NetLoginHandler extends NetHandler
{
	private byte[] d;
	public static Logger logger = Logger.getLogger("Minecraft");
	private static Random random = new Random();
	public NetworkManager networkManager;
	public boolean c = false;
	private MinecraftServer server;
	private int g = 0;
	private String h = null;
	private volatile boolean i = false;
	private String loginKey = Long.toString(random.nextLong(), 16);
	private SecretKey k = null;
	public String hostname = "";

	public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s) throws IOException {
		this.server = minecraftserver;
		this.networkManager = new NetworkManager(socket, s, this, minecraftserver.E().getPrivate());
		this.networkManager.e = 0;
	}

	public Socket getSocket()
	{
		return this.networkManager.getSocket();
	}

	public void c()
	{
		if (this.i) {
			d();
		}

		if (this.g++ == 600)
			disconnect("Took too long to log in");
		else
			this.networkManager.b();
	}

	public void disconnect(String s)
	{
		try {
			logger.info("Disconnecting " + getName() + ": " + s);
			this.networkManager.queue(new Packet255KickDisconnect(s));
			this.networkManager.d();
			this.c = true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void a(Packet2Handshake packet2handshake)
	{
		this.hostname = (packet2handshake.c + ':' + packet2handshake.d);

		this.h = packet2handshake.f();
		if (!this.h.equals(StripColor.a(this.h))) {
			disconnect("Invalid username!");
		} else {
			PublicKey publickey = this.server.E().getPublic();

			if (packet2handshake.d() != 39) {
				if (packet2handshake.d() > 39)
					disconnect("Outdated server!");
				else
					disconnect("Outdated client!");
			}
			else {
				this.loginKey = (this.server.getOnlineMode() ? Long.toString(random.nextLong(), 16) : "-");
				this.d = new byte[4];
				random.nextBytes(this.d);
				this.networkManager.queue(new Packet253KeyRequest(this.loginKey, publickey, this.d));
			}
		}
	}

	public void a(Packet252KeyResponse packet252keyresponse) {
		PrivateKey privatekey = this.server.E().getPrivate();

		this.k = packet252keyresponse.a(privatekey);
		if (!Arrays.equals(this.d, packet252keyresponse.b(privatekey))) {
			disconnect("Invalid client reply");
		}

		this.networkManager.queue(new Packet252KeyResponse());
	}

	public void a(Packet205ClientCommand packet205clientcommand) {
		if (packet205clientcommand.a == 0)
			if (this.server.getOnlineMode())
				new ThreadLoginVerifier(this, this.server.server).start();
			else
				this.i = true;
	}

	public void a(Packet1Login packet1login)
	{
	}

	public void d()
	{
		EntityPlayer s = this.server.getServerConfigurationManager().attemptLogin(this, this.h, this.hostname);

		if (s == null) {
			return;
		}

		EntityPlayer entityplayer = this.server.getServerConfigurationManager().processLogin(s);

		if (entityplayer != null) {
			this.server.getServerConfigurationManager().a(this.networkManager, entityplayer);
		}

		this.c = true;
	}

	public void a(String s, Object[] aobject) {
		logger.info(getName() + " lost connection");
		this.c = true;
	}

	public void a(Packet254GetInfo packet254getinfo) {
		if (this.networkManager.getSocket() == null) return;
		try
		{
			ServerListPingEvent pingEvent = CraftEventFactory.callServerListPingEvent(this.server.server, getSocket().getInetAddress(), this.server.getMotd(), this.server.getServerConfigurationManager().getPlayerCount(), this.server.getServerConfigurationManager().getMaxPlayers());
			String s = pingEvent.getMotd() + "§" + this.server.getServerConfigurationManager().getPlayerCount() + "§" + pingEvent.getMaxPlayers();

			InetAddress inetaddress = null;

			if (this.networkManager.getSocket() != null) {
				inetaddress = this.networkManager.getSocket().getInetAddress();
			}

			this.networkManager.queue(new Packet255KickDisconnect(s));
			this.networkManager.d();
			if ((inetaddress != null) && ((this.server.ac() instanceof DedicatedServerConnection))) {
				((DedicatedServerConnection)this.server.ac()).a(inetaddress);
			}

			this.c = true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void onUnhandledPacket(Packet packet) {
		disconnect("Protocol error");
	}

	public String getName() {
		return this.h != null ? this.h + " [" + this.networkManager.getSocketAddress().toString() + "]" : this.networkManager.getSocketAddress().toString();
	}

	public boolean a() {
		return true;
	}

	static String a(NetLoginHandler netloginhandler) {
		return netloginhandler.loginKey;
	}

	static MinecraftServer b(NetLoginHandler netloginhandler) {
		return netloginhandler.server;
	}

	static SecretKey c(NetLoginHandler netloginhandler) {
		return netloginhandler.k;
	}

	static String d(NetLoginHandler netloginhandler) {
		return netloginhandler.h;
	}

	static boolean a(NetLoginHandler netloginhandler, boolean flag) {
		return netloginhandler.i = flag;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.NetLoginHandler
 * JD-Core Version:		0.6.0
 */