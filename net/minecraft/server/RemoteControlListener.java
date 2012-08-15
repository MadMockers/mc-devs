/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.IOException;
/*		 */ import java.net.InetAddress;
/*		 */ import java.net.ServerSocket;
/*		 */ import java.net.Socket;
/*		 */ import java.net.SocketTimeoutException;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.Map;
/*		 */ import java.util.Map.Entry;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class RemoteControlListener extends RemoteConnectionThread
/*		 */ {
/*		 */	 private int g;
/*		 */	 private int h;
/*		 */	 private String i;
/*	19 */	 private ServerSocket j = null;
/*		 */	 private String k;
/*		 */	 private Map l;
/*		 */ 
/*		 */	 public RemoteControlListener(IMinecraftServer paramIMinecraftServer)
/*		 */	 {
/*	24 */		 super(paramIMinecraftServer);
/*	25 */		 this.g = paramIMinecraftServer.a("rcon.port", 0);
/*	26 */		 this.k = paramIMinecraftServer.a("rcon.password", "");
/*	27 */		 this.i = paramIMinecraftServer.t();
/*	28 */		 this.h = paramIMinecraftServer.u();
/*	29 */		 if (0 == this.g)
/*		 */		 {
/*	31 */			 this.g = (this.h + 10);
/*	32 */			 info("Setting default rcon port to " + this.g);
/*	33 */			 paramIMinecraftServer.a("rcon.port", Integer.valueOf(this.g));
/*	34 */			 if (0 == this.k.length()) {
/*	35 */				 paramIMinecraftServer.a("rcon.password", "");
/*		 */			 }
/*	37 */			 paramIMinecraftServer.a();
/*		 */		 }
/*		 */ 
/*	40 */		 if (0 == this.i.length()) {
/*	41 */			 this.i = "0.0.0.0";
/*		 */		 }
/*		 */ 
/*	44 */		 f();
/*	45 */		 this.j = null;
/*		 */	 }
/*		 */ 
/*		 */	 private void f() {
/*	49 */		 this.l = new HashMap();
/*		 */	 }
/*		 */ 
/*		 */	 private void g() {
/*	53 */		 Iterator localIterator = this.l.entrySet().iterator();
/*	54 */		 while (localIterator.hasNext()) {
/*	55 */			 Map.Entry localEntry = (Map.Entry)localIterator.next();
/*	56 */			 if (!((RemoteControlSession)localEntry.getValue()).c())
/*	57 */				 localIterator.remove();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void run()
/*		 */	 {
/*	64 */		 info("RCON running on " + this.i + ":" + this.g);
/*		 */		 try {
/*	66 */			 while (this.running)
/*		 */				 try
/*		 */				 {
/*	69 */					 Socket localSocket = this.j.accept();
/*	70 */					 localSocket.setSoTimeout(500);
/*	71 */					 RemoteControlSession localRemoteControlSession = new RemoteControlSession(this.server, localSocket);
/*	72 */					 localRemoteControlSession.a();
/*	73 */					 this.l.put(localSocket.getRemoteSocketAddress(), localRemoteControlSession);
/*		 */ 
/*	76 */					 g();
/*		 */				 }
/*		 */				 catch (SocketTimeoutException localSocketTimeoutException) {
/*	79 */					 g();
/*		 */				 } catch (IOException localIOException) {
/*	81 */					 if (this.running)
/*	82 */						 info("IO: " + localIOException.getMessage());
/*		 */				 }
/*		 */		 }
/*		 */		 finally
/*		 */		 {
/*	87 */			 b(this.j);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/*	93 */		 if (0 == this.k.length()) {
/*	94 */			 warning("No rcon password set in '" + this.server.c() + "', rcon disabled!");
/*	95 */			 return;
/*		 */		 }
/*		 */ 
/*	98 */		 if ((0 >= this.g) || (65535 < this.g)) {
/*	99 */			 warning("Invalid rcon port " + this.g + " found in '" + this.server.c() + "', rcon disabled!");
/* 100 */			 return;
/*		 */		 }
/*		 */ 
/* 103 */		 if (this.running) {
/* 104 */			 return;
/*		 */		 }
/*		 */		 try
/*		 */		 {
/* 108 */			 this.j = new ServerSocket(this.g, 0, InetAddress.getByName(this.i));
/* 109 */			 this.j.setSoTimeout(500);
/* 110 */			 super.a();
/*		 */		 } catch (IOException localIOException) {
/* 112 */			 warning("Unable to initialise rcon on " + this.i + ":" + this.g + " : " + localIOException.getMessage());
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RemoteControlListener
 * JD-Core Version:		0.6.0
 */