/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintStream;
/*		 */ import java.net.InetAddress;
/*		 */ import java.net.ServerSocket;
/*		 */ import java.net.Socket;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.List;
/*		 */ import java.util.logging.Level;
/*		 */ import java.util.logging.Logger;
/*		 */ import org.bukkit.craftbukkit.CraftServer;
/*		 */ 
/*		 */ public class DedicatedServerConnectionThread extends Thread
/*		 */ {
/*	16 */	 private static Logger a = Logger.getLogger("Minecraft");
/*	17 */	 private final List b = Collections.synchronizedList(new ArrayList());
/*	18 */	 private final HashMap c = new HashMap();
/*	19 */	 private int d = 0;
/*		 */	 private final ServerSocket e;
/*		 */	 private ServerConnection f;
/*		 */	 private final InetAddress g;
/*		 */	 private final int h;
/*		 */	 long connectionThrottle;
/*		 */ 
/*		 */	 public DedicatedServerConnectionThread(ServerConnection serverconnection, InetAddress inetaddress, int i)
/*		 */		 throws IOException
/*		 */	 {
/*	28 */		 super("Listen thread");
/*	29 */		 this.f = serverconnection;
/*	30 */		 this.g = inetaddress;
/*	31 */		 this.h = i;
/*	32 */		 this.e = new ServerSocket(i, 0, inetaddress);
/*	33 */		 this.e.setPerformancePreferences(0, 2, 1);
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/*	37 */		 List list = this.b;
/*		 */ 
/*	39 */		 synchronized (this.b) {
/*	40 */			 for (int i = 0; i < this.b.size(); i++) {
/*	41 */				 NetLoginHandler netloginhandler = (NetLoginHandler)this.b.get(i);
/*		 */				 try
/*		 */				 {
/*	44 */					 netloginhandler.c();
/*		 */				 } catch (Exception exception) {
/*	46 */					 netloginhandler.disconnect("Internal server error");
/*	47 */					 a.log(Level.WARNING, "Failed to handle packet: " + exception, exception);
/*		 */				 }
/*		 */ 
/*	50 */				 if (netloginhandler.c) {
/*	51 */					 this.b.remove(i--);
/*		 */				 }
/*		 */ 
/*	54 */				 netloginhandler.networkManager.a();
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void run() {
/*	60 */		 while (this.f.b) {
/*		 */			 try {
/*	62 */				 Socket socket = this.e.accept();
/*	63 */				 InetAddress inetaddress = socket.getInetAddress();
/*	64 */				 long i = System.currentTimeMillis();
/*	65 */				 HashMap hashmap = this.c;
/*		 */ 
/*	68 */				 if (this.f.d().server == null) {
/*	69 */					 socket.close();
/*	70 */					 continue;
/*		 */				 }
/*		 */ 
/*	73 */				 this.connectionThrottle = this.f.d().server.getConnectionThrottle();
/*		 */ 
/*	76 */				 synchronized (this.c) {
/*	77 */					 if ((this.c.containsKey(inetaddress)) && (!b(inetaddress)) && (i - ((Long)this.c.get(inetaddress)).longValue() < this.connectionThrottle)) {
/*	78 */						 this.c.put(inetaddress, Long.valueOf(i));
/*	79 */						 socket.close();
/*	80 */						 continue;
/*		 */					 }
/*		 */ 
/*	83 */					 this.c.put(inetaddress, Long.valueOf(i));
/*		 */				 }
/*		 */ 
/*	86 */				 NetLoginHandler netloginhandler = new NetLoginHandler(this.f.d(), socket, "Connection #" + this.d++);
/*		 */ 
/*	88 */				 a(netloginhandler);
/*		 */			 } catch (IOException ioexception) {
/*	90 */				 a.warning("DSCT: " + ioexception.getMessage());
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	94 */		 System.out.println("Closing listening thread");
/*		 */	 }
/*		 */ 
/*		 */	 private void a(NetLoginHandler netloginhandler) {
/*	98 */		 if (netloginhandler == null) {
/*	99 */			 throw new IllegalArgumentException("Got null pendingconnection!");
/*		 */		 }
/* 101 */		 List list = this.b;
/*		 */ 
/* 103 */		 synchronized (this.b) {
/* 104 */			 this.b.add(netloginhandler);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private static boolean b(InetAddress inetaddress)
/*		 */	 {
/* 110 */		 return "127.0.0.1".equals(inetaddress.getHostAddress());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(InetAddress inetaddress) {
/* 114 */		 if (inetaddress != null) {
/* 115 */			 HashMap hashmap = this.c;
/*		 */ 
/* 117 */			 synchronized (this.c) {
/* 118 */				 this.c.remove(inetaddress);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*		 */		 try {
/* 125 */			 this.e.close();
/*		 */		 }
/*		 */		 catch (Throwable throwable)
/*		 */		 {
/*		 */		 }
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.DedicatedServerConnectionThread
 * JD-Core Version:		0.6.0
 */