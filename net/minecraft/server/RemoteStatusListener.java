/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import B;
/*		 */ import java.io.IOException;
/*		 */ import java.net.DatagramPacket;
/*		 */ import java.net.DatagramSocket;
/*		 */ import java.net.InetAddress;
/*		 */ import java.net.PortUnreachableException;
/*		 */ import java.net.SocketAddress;
/*		 */ import java.net.SocketException;
/*		 */ import java.net.SocketTimeoutException;
/*		 */ import java.net.UnknownHostException;
/*		 */ import java.util.Date;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.Map;
/*		 */ import java.util.Map.Entry;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class RemoteStatusListener extends RemoteConnectionThread
/*		 */ {
/*		 */	 private long clearedTime;
/*		 */	 private int bindPort;
/*		 */	 private int serverPort;
/*		 */	 private int maxPlayers;
/*		 */	 private String localAddress;
/*		 */	 private String worldName;
/*	23 */	 private DatagramSocket socket = null;
/*	24 */	 private byte[] n = new byte[1460];
/*	25 */	 private DatagramPacket o = null;
/*		 */	 private Map p;
/*		 */	 private String hostname;
/*		 */	 private String motd;
/*		 */	 private Map challenges;
/*		 */	 private long t;
/*		 */	 private RemoteStatusReply cachedReply;
/*		 */	 private long cacheTime;
/*		 */ 
/*		 */	 public RemoteStatusListener(IMinecraftServer paramIMinecraftServer)
/*		 */	 {
/*	37 */		 super(paramIMinecraftServer);
/*		 */ 
/*	39 */		 this.bindPort = paramIMinecraftServer.a("query.port", 0);
/*	40 */		 this.motd = paramIMinecraftServer.t();
/*	41 */		 this.serverPort = paramIMinecraftServer.u();
/*	42 */		 this.localAddress = paramIMinecraftServer.v();
/*	43 */		 this.maxPlayers = paramIMinecraftServer.y();
/*	44 */		 this.worldName = paramIMinecraftServer.I();
/*		 */ 
/*	47 */		 this.cacheTime = 0L;
/*		 */ 
/*	49 */		 this.hostname = "0.0.0.0";
/*		 */ 
/*	52 */		 if ((0 == this.motd.length()) || (this.hostname.equals(this.motd)))
/*		 */		 {
/*	54 */			 this.motd = "0.0.0.0";
/*		 */			 try {
/*	56 */				 InetAddress localInetAddress = InetAddress.getLocalHost();
/*	57 */				 this.hostname = localInetAddress.getHostAddress();
/*		 */			 } catch (UnknownHostException localUnknownHostException) {
/*	59 */				 warning("Unable to determine local host IP, please set server-ip in '" + paramIMinecraftServer.c() + "' : " + localUnknownHostException.getMessage());
/*		 */			 }
/*		 */		 } else {
/*	62 */			 this.hostname = this.motd;
/*		 */		 }
/*		 */ 
/*	66 */		 if (0 == this.bindPort)
/*		 */		 {
/*	68 */			 this.bindPort = this.serverPort;
/*	69 */			 info("Setting default query port to " + this.bindPort);
/*	70 */			 paramIMinecraftServer.a("query.port", Integer.valueOf(this.bindPort));
/*	71 */			 paramIMinecraftServer.a("debug", Boolean.valueOf(false));
/*	72 */			 paramIMinecraftServer.a();
/*		 */		 }
/*		 */ 
/*	75 */		 this.p = new HashMap();
/*	76 */		 this.cachedReply = new RemoteStatusReply(1460);
/*	77 */		 this.challenges = new HashMap();
/*	78 */		 this.t = new Date().getTime();
/*		 */	 }
/*		 */ 
/*		 */	 private void send(byte[] paramArrayOfByte, DatagramPacket paramDatagramPacket) {
/*	82 */		 this.socket.send(new DatagramPacket(paramArrayOfByte, paramArrayOfByte.length, paramDatagramPacket.getSocketAddress()));
/*		 */	 }
/*		 */ 
/*		 */	 private boolean parsePacket(DatagramPacket paramDatagramPacket) {
/*	86 */		 byte[] arrayOfByte = paramDatagramPacket.getData();
/*	87 */		 int i = paramDatagramPacket.getLength();
/*		 */ 
/*	89 */		 SocketAddress localSocketAddress = paramDatagramPacket.getSocketAddress();
/*	90 */		 debug("Packet len " + i + " [" + localSocketAddress + "]");
/*	91 */		 if ((3 > i) || (-2 != arrayOfByte[0]) || (-3 != arrayOfByte[1]))
/*		 */		 {
/*	93 */			 debug("Invalid packet [" + localSocketAddress + "]");
/*	94 */			 return false;
/*		 */		 }
/*		 */ 
/*	98 */		 debug("Packet '" + StatusChallengeUtils.a(arrayOfByte[2]) + "' [" + localSocketAddress + "]");
/*	99 */		 switch (arrayOfByte[2])
/*		 */		 {
/*		 */		 case 9:
/* 102 */			 createChallenge(paramDatagramPacket);
/* 103 */			 debug("Challenge [" + localSocketAddress + "]");
/* 104 */			 return true;
/*		 */		 case 0:
/* 108 */			 if (!hasChallenged(paramDatagramPacket).booleanValue()) {
/* 109 */				 debug("Invalid challenge [" + localSocketAddress + "]");
/* 110 */				 return false;
/*		 */			 }
/*		 */ 
/* 113 */			 if (15 == i)
/*		 */			 {
/* 115 */				 send(getFullReply(paramDatagramPacket), paramDatagramPacket);
/* 116 */				 debug("Rules [" + localSocketAddress + "]");
/*		 */			 }
/*		 */			 else {
/* 119 */				 RemoteStatusReply localRemoteStatusReply = new RemoteStatusReply(1460);
/* 120 */				 localRemoteStatusReply.write(0);
/* 121 */				 localRemoteStatusReply.write(getIdentityToken(paramDatagramPacket.getSocketAddress()));
/* 122 */				 localRemoteStatusReply.write(this.localAddress);
/* 123 */				 localRemoteStatusReply.write("SMP");
/* 124 */				 localRemoteStatusReply.write(this.worldName);
/* 125 */				 localRemoteStatusReply.write(Integer.toString(d()));
/* 126 */				 localRemoteStatusReply.write(Integer.toString(this.maxPlayers));
/* 127 */				 localRemoteStatusReply.write((short)this.serverPort);
/* 128 */				 localRemoteStatusReply.write(this.hostname);
/*		 */ 
/* 130 */				 send(localRemoteStatusReply.getBytes(), paramDatagramPacket);
/* 131 */				 debug("Status [" + localSocketAddress + "]");
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 135 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private byte[] getFullReply(DatagramPacket paramDatagramPacket) {
/* 139 */		 long l = System.currentTimeMillis();
/* 140 */		 if (l < this.cacheTime + 5000L)
/*		 */		 {
/* 142 */			 localObject = this.cachedReply.getBytes();
/* 143 */			 byte[] arrayOfByte = getIdentityToken(paramDatagramPacket.getSocketAddress());
/* 144 */			 localObject[1] = arrayOfByte[0];
/* 145 */			 localObject[2] = arrayOfByte[1];
/* 146 */			 localObject[3] = arrayOfByte[2];
/* 147 */			 localObject[4] = arrayOfByte[3];
/*		 */ 
/* 149 */			 return localObject;
/*		 */		 }
/*		 */ 
/* 152 */		 this.cacheTime = l;
/*		 */ 
/* 154 */		 this.cachedReply.reset();
/* 155 */		 this.cachedReply.write(0);
/* 156 */		 this.cachedReply.write(getIdentityToken(paramDatagramPacket.getSocketAddress()));
/* 157 */		 this.cachedReply.write("splitnum");
/* 158 */		 this.cachedReply.write(128);
/* 159 */		 this.cachedReply.write(0);
/*		 */ 
/* 162 */		 this.cachedReply.write("hostname");
/* 163 */		 this.cachedReply.write(this.localAddress);
/* 164 */		 this.cachedReply.write("gametype");
/* 165 */		 this.cachedReply.write("SMP");
/* 166 */		 this.cachedReply.write("game_id");
/* 167 */		 this.cachedReply.write("MINECRAFT");
/* 168 */		 this.cachedReply.write("version");
/* 169 */		 this.cachedReply.write(this.server.getVersion());
/* 170 */		 this.cachedReply.write("plugins");
/* 171 */		 this.cachedReply.write(this.server.getPlugins());
/* 172 */		 this.cachedReply.write("map");
/* 173 */		 this.cachedReply.write(this.worldName);
/* 174 */		 this.cachedReply.write("numplayers");
/* 175 */		 this.cachedReply.write("" + d());
/* 176 */		 this.cachedReply.write("maxplayers");
/* 177 */		 this.cachedReply.write("" + this.maxPlayers);
/* 178 */		 this.cachedReply.write("hostport");
/* 179 */		 this.cachedReply.write("" + this.serverPort);
/* 180 */		 this.cachedReply.write("hostip");
/* 181 */		 this.cachedReply.write(this.hostname);
/* 182 */		 this.cachedReply.write(0);
/* 183 */		 this.cachedReply.write(1);
/*		 */ 
/* 187 */		 this.cachedReply.write("player_");
/* 188 */		 this.cachedReply.write(0);
/*		 */ 
/* 190 */		 Object localObject = this.server.getPlayers();
/* 191 */		 int i = (byte)localObject.length;
/* 192 */		 for (int j = (byte)(i - 1); j >= 0; j = (byte)(j - 1)) {
/* 193 */			 this.cachedReply.write(localObject[j]);
/*		 */		 }
/*		 */ 
/* 196 */		 this.cachedReply.write(0);
/*		 */ 
/* 198 */		 return (B)this.cachedReply.getBytes();
/*		 */	 }
/*		 */ 
/*		 */	 private byte[] getIdentityToken(SocketAddress paramSocketAddress) {
/* 202 */		 return ((RemoteStatusChallenge)this.challenges.get(paramSocketAddress)).getIdentityToken();
/*		 */	 }
/*		 */ 
/*		 */	 private Boolean hasChallenged(DatagramPacket paramDatagramPacket) {
/* 206 */		 SocketAddress localSocketAddress = paramDatagramPacket.getSocketAddress();
/* 207 */		 if (!this.challenges.containsKey(localSocketAddress))
/*		 */		 {
/* 209 */			 return Boolean.valueOf(false);
/*		 */		 }
/*		 */ 
/* 212 */		 byte[] arrayOfByte = paramDatagramPacket.getData();
/* 213 */		 if (((RemoteStatusChallenge)this.challenges.get(localSocketAddress)).getToken() != StatusChallengeUtils.c(arrayOfByte, 7, paramDatagramPacket.getLength()))
/*		 */		 {
/* 215 */			 return Boolean.valueOf(false);
/*		 */		 }
/*		 */ 
/* 219 */		 return Boolean.valueOf(true);
/*		 */	 }
/*		 */ 
/*		 */	 private void createChallenge(DatagramPacket paramDatagramPacket) {
/* 223 */		 RemoteStatusChallenge localRemoteStatusChallenge = new RemoteStatusChallenge(this, paramDatagramPacket);
/* 224 */		 this.challenges.put(paramDatagramPacket.getSocketAddress(), localRemoteStatusChallenge);
/*		 */ 
/* 226 */		 send(localRemoteStatusChallenge.getChallengeResponse(), paramDatagramPacket);
/*		 */	 }
/*		 */ 
/*		 */	 private void cleanChallenges()
/*		 */	 {
/* 231 */		 if (!this.running) {
/* 232 */			 return;
/*		 */		 }
/*		 */ 
/* 235 */		 long l = System.currentTimeMillis();
/* 236 */		 if (l < this.clearedTime + 30000L) {
/* 237 */			 return;
/*		 */		 }
/* 239 */		 this.clearedTime = l;
/*		 */ 
/* 241 */		 Iterator localIterator = this.challenges.entrySet().iterator();
/* 242 */		 while (localIterator.hasNext()) {
/* 243 */			 Map.Entry localEntry = (Map.Entry)localIterator.next();
/* 244 */			 if (((RemoteStatusChallenge)localEntry.getValue()).isExpired(l).booleanValue())
/* 245 */				 localIterator.remove();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void run()
/*		 */	 {
/* 252 */		 info("Query running on " + this.motd + ":" + this.bindPort);
/* 253 */		 this.clearedTime = System.currentTimeMillis();
/* 254 */		 this.o = new DatagramPacket(this.n, this.n.length);
/*		 */		 try
/*		 */		 {
/* 257 */			 while (this.running)
/*		 */				 try
/*		 */				 {
/* 260 */					 this.socket.receive(this.o);
/*		 */ 
/* 264 */					 cleanChallenges();
/*		 */ 
/* 267 */					 parsePacket(this.o);
/*		 */				 }
/*		 */				 catch (SocketTimeoutException localSocketTimeoutException)
/*		 */				 {
/* 271 */					 cleanChallenges();
/*		 */				 }
/*		 */				 catch (PortUnreachableException localPortUnreachableException)
/*		 */				 {
/*		 */				 }
/*		 */				 catch (IOException localIOException) {
/* 277 */					 a(localIOException);
/*		 */				 }
/*		 */		 }
/*		 */		 finally {
/* 281 */			 e();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/* 287 */		 if (this.running) {
/* 288 */			 return;
/*		 */		 }
/*		 */ 
/* 291 */		 if ((0 >= this.bindPort) || (65535 < this.bindPort)) {
/* 292 */			 warning("Invalid query port " + this.bindPort + " found in '" + this.server.c() + "' (queries disabled)");
/* 293 */			 return;
/*		 */		 }
/*		 */ 
/* 296 */		 if (g())
/* 297 */			 super.a();
/*		 */	 }
/*		 */ 
/*		 */	 private void a(Exception paramException)
/*		 */	 {
/* 302 */		 if (!this.running) {
/* 303 */			 return;
/*		 */		 }
/*		 */ 
/* 307 */		 warning("Unexpected exception, buggy JRE? (" + paramException.toString() + ")");
/*		 */ 
/* 310 */		 if (!g()) {
/* 311 */			 error("Failed to recover from buggy JRE, shutting down!");
/* 312 */			 this.running = false;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean g()
/*		 */	 {
/*		 */		 try {
/* 319 */			 this.socket = new DatagramSocket(this.bindPort, InetAddress.getByName(this.motd));
/* 320 */			 a(this.socket);
/* 321 */			 this.socket.setSoTimeout(500);
/* 322 */			 return true;
/*		 */		 } catch (SocketException localSocketException) {
/* 324 */			 warning("Unable to initialise query system on " + this.motd + ":" + this.bindPort + " (Socket): " + localSocketException.getMessage());
/*		 */		 } catch (UnknownHostException localUnknownHostException) {
/* 326 */			 warning("Unable to initialise query system on " + this.motd + ":" + this.bindPort + " (Unknown Host): " + localUnknownHostException.getMessage());
/*		 */		 } catch (Exception localException) {
/* 328 */			 warning("Unable to initialise query system on " + this.motd + ":" + this.bindPort + " (E): " + localException.getMessage());
/*		 */		 }
/*		 */ 
/* 331 */		 return false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RemoteStatusListener
 * JD-Core Version:		0.6.0
 */