/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.IOException;
/*		 */ import java.net.InetAddress;
/*		 */ import java.net.Socket;
/*		 */ import java.security.KeyPair;
/*		 */ import java.security.PrivateKey;
/*		 */ import java.security.PublicKey;
/*		 */ import java.util.Arrays;
/*		 */ import java.util.Random;
/*		 */ import java.util.logging.Logger;
/*		 */ import javax.crypto.SecretKey;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.event.server.ServerListPingEvent;
/*		 */ 
/*		 */ public class NetLoginHandler extends NetHandler
/*		 */ {
/*		 */	 private byte[] d;
/*	15 */	 public static Logger logger = Logger.getLogger("Minecraft");
/*	16 */	 private static Random random = new Random();
/*		 */	 public NetworkManager networkManager;
/*	18 */	 public boolean c = false;
/*		 */	 private MinecraftServer server;
/*	20 */	 private int g = 0;
/*	21 */	 private String h = null;
/*	22 */	 private volatile boolean i = false;
/*	23 */	 private String loginKey = Long.toString(random.nextLong(), 16);
/*	24 */	 private SecretKey k = null;
/*	25 */	 public String hostname = "";
/*		 */ 
/*		 */	 public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s) throws IOException {
/*	28 */		 this.server = minecraftserver;
/*	29 */		 this.networkManager = new NetworkManager(socket, s, this, minecraftserver.E().getPrivate());
/*	30 */		 this.networkManager.e = 0;
/*		 */	 }
/*		 */ 
/*		 */	 public Socket getSocket()
/*		 */	 {
/*	35 */		 return this.networkManager.getSocket();
/*		 */	 }
/*		 */ 
/*		 */	 public void c()
/*		 */	 {
/*	40 */		 if (this.i) {
/*	41 */			 d();
/*		 */		 }
/*		 */ 
/*	44 */		 if (this.g++ == 600)
/*	45 */			 disconnect("Took too long to log in");
/*		 */		 else
/*	47 */			 this.networkManager.b();
/*		 */	 }
/*		 */ 
/*		 */	 public void disconnect(String s)
/*		 */	 {
/*		 */		 try {
/*	53 */			 logger.info("Disconnecting " + getName() + ": " + s);
/*	54 */			 this.networkManager.queue(new Packet255KickDisconnect(s));
/*	55 */			 this.networkManager.d();
/*	56 */			 this.c = true;
/*		 */		 } catch (Exception exception) {
/*	58 */			 exception.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet2Handshake packet2handshake)
/*		 */	 {
/*	64 */		 this.hostname = (packet2handshake.c + ':' + packet2handshake.d);
/*		 */ 
/*	66 */		 this.h = packet2handshake.f();
/*	67 */		 if (!this.h.equals(StripColor.a(this.h))) {
/*	68 */			 disconnect("Invalid username!");
/*		 */		 } else {
/*	70 */			 PublicKey publickey = this.server.E().getPublic();
/*		 */ 
/*	72 */			 if (packet2handshake.d() != 39) {
/*	73 */				 if (packet2handshake.d() > 39)
/*	74 */					 disconnect("Outdated server!");
/*		 */				 else
/*	76 */					 disconnect("Outdated client!");
/*		 */			 }
/*		 */			 else {
/*	79 */				 this.loginKey = (this.server.getOnlineMode() ? Long.toString(random.nextLong(), 16) : "-");
/*	80 */				 this.d = new byte[4];
/*	81 */				 random.nextBytes(this.d);
/*	82 */				 this.networkManager.queue(new Packet253KeyRequest(this.loginKey, publickey, this.d));
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet252KeyResponse packet252keyresponse) {
/*	88 */		 PrivateKey privatekey = this.server.E().getPrivate();
/*		 */ 
/*	90 */		 this.k = packet252keyresponse.a(privatekey);
/*	91 */		 if (!Arrays.equals(this.d, packet252keyresponse.b(privatekey))) {
/*	92 */			 disconnect("Invalid client reply");
/*		 */		 }
/*		 */ 
/*	95 */		 this.networkManager.queue(new Packet252KeyResponse());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet205ClientCommand packet205clientcommand) {
/*	99 */		 if (packet205clientcommand.a == 0)
/* 100 */			 if (this.server.getOnlineMode())
/* 101 */				 new ThreadLoginVerifier(this, this.server.server).start();
/*		 */			 else
/* 103 */				 this.i = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet1Login packet1login)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void d()
/*		 */	 {
/* 112 */		 EntityPlayer s = this.server.getServerConfigurationManager().attemptLogin(this, this.h, this.hostname);
/*		 */ 
/* 114 */		 if (s == null) {
/* 115 */			 return;
/*		 */		 }
/*		 */ 
/* 118 */		 EntityPlayer entityplayer = this.server.getServerConfigurationManager().processLogin(s);
/*		 */ 
/* 120 */		 if (entityplayer != null) {
/* 121 */			 this.server.getServerConfigurationManager().a(this.networkManager, entityplayer);
/*		 */		 }
/*		 */ 
/* 125 */		 this.c = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String s, Object[] aobject) {
/* 129 */		 logger.info(getName() + " lost connection");
/* 130 */		 this.c = true;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Packet254GetInfo packet254getinfo) {
/* 134 */		 if (this.networkManager.getSocket() == null) return;
/*		 */		 try
/*		 */		 {
/* 137 */			 ServerListPingEvent pingEvent = CraftEventFactory.callServerListPingEvent(this.server.server, getSocket().getInetAddress(), this.server.getMotd(), this.server.getServerConfigurationManager().getPlayerCount(), this.server.getServerConfigurationManager().getMaxPlayers());
/* 138 */			 String s = pingEvent.getMotd() + "§" + this.server.getServerConfigurationManager().getPlayerCount() + "§" + pingEvent.getMaxPlayers();
/*		 */ 
/* 141 */			 InetAddress inetaddress = null;
/*		 */ 
/* 143 */			 if (this.networkManager.getSocket() != null) {
/* 144 */				 inetaddress = this.networkManager.getSocket().getInetAddress();
/*		 */			 }
/*		 */ 
/* 147 */			 this.networkManager.queue(new Packet255KickDisconnect(s));
/* 148 */			 this.networkManager.d();
/* 149 */			 if ((inetaddress != null) && ((this.server.ac() instanceof DedicatedServerConnection))) {
/* 150 */				 ((DedicatedServerConnection)this.server.ac()).a(inetaddress);
/*		 */			 }
/*		 */ 
/* 153 */			 this.c = true;
/*		 */		 } catch (Exception exception) {
/* 155 */			 exception.printStackTrace();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void onUnhandledPacket(Packet packet) {
/* 160 */		 disconnect("Protocol error");
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 164 */		 return this.h != null ? this.h + " [" + this.networkManager.getSocketAddress().toString() + "]" : this.networkManager.getSocketAddress().toString();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a() {
/* 168 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 static String a(NetLoginHandler netloginhandler) {
/* 172 */		 return netloginhandler.loginKey;
/*		 */	 }
/*		 */ 
/*		 */	 static MinecraftServer b(NetLoginHandler netloginhandler) {
/* 176 */		 return netloginhandler.server;
/*		 */	 }
/*		 */ 
/*		 */	 static SecretKey c(NetLoginHandler netloginhandler) {
/* 180 */		 return netloginhandler.k;
/*		 */	 }
/*		 */ 
/*		 */	 static String d(NetLoginHandler netloginhandler) {
/* 184 */		 return netloginhandler.h;
/*		 */	 }
/*		 */ 
/*		 */	 static boolean a(NetLoginHandler netloginhandler, boolean flag) {
/* 188 */		 return netloginhandler.i = flag;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetLoginHandler
 * JD-Core Version:		0.6.0
 */