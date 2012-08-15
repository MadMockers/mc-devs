/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.IOException;
/*		 */ import java.net.DatagramSocket;
/*		 */ import java.net.ServerSocket;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ 
/*		 */ public abstract class RemoteConnectionThread
/*		 */	 implements Runnable
/*		 */ {
/*	10 */	 protected boolean running = false;
/*		 */	 protected IMinecraftServer server;
/*		 */	 protected Thread thread;
/*	13 */	 protected int d = 5;
/*	14 */	 protected List e = new ArrayList();
/*	15 */	 protected List f = new ArrayList();
/*		 */ 
/*		 */	 RemoteConnectionThread(IMinecraftServer paramIMinecraftServer) {
/*	18 */		 this.server = paramIMinecraftServer;
/*	19 */		 if (this.server.isDebugging())
/*	20 */			 warning("Debugging is enabled, performance maybe reduced!");
/*		 */	 }
/*		 */ 
/*		 */	 public synchronized void a()
/*		 */	 {
/*	27 */		 this.thread = new Thread(this);
/*	28 */		 this.thread.start();
/*	29 */		 this.running = true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c()
/*		 */	 {
/*	68 */		 return this.running;
/*		 */	 }
/*		 */ 
/*		 */	 protected void debug(String paramString) {
/*	72 */		 this.server.k(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 protected void info(String paramString) {
/*	76 */		 this.server.info(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 protected void warning(String paramString) {
/*	80 */		 this.server.warning(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 protected void error(String paramString) {
/*	84 */		 this.server.j(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 protected int d() {
/*	88 */		 return this.server.x();
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(DatagramSocket paramDatagramSocket) {
/*	92 */		 debug("registerSocket: " + paramDatagramSocket);
/*	93 */		 this.e.add(paramDatagramSocket);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean a(DatagramSocket paramDatagramSocket, boolean paramBoolean)
/*		 */	 {
/* 106 */		 debug("closeSocket: " + paramDatagramSocket);
/* 107 */		 if (null == paramDatagramSocket) {
/* 108 */			 return false;
/*		 */		 }
/*		 */ 
/* 111 */		 int i = 0;
/* 112 */		 if (!paramDatagramSocket.isClosed()) {
/* 113 */			 paramDatagramSocket.close();
/* 114 */			 i = 1;
/*		 */		 }
/*		 */ 
/* 117 */		 if (paramBoolean) {
/* 118 */			 this.e.remove(paramDatagramSocket);
/*		 */		 }
/*		 */ 
/* 121 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean b(ServerSocket paramServerSocket) {
/* 125 */		 return a(paramServerSocket, true);
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean a(ServerSocket paramServerSocket, boolean paramBoolean) {
/* 129 */		 debug("closeSocket: " + paramServerSocket);
/* 130 */		 if (null == paramServerSocket) {
/* 131 */			 return false;
/*		 */		 }
/*		 */ 
/* 134 */		 int i = 0;
/*		 */		 try {
/* 136 */			 if (!paramServerSocket.isClosed()) {
/* 137 */				 paramServerSocket.close();
/* 138 */				 i = 1;
/*		 */			 }
/*		 */		 } catch (IOException localIOException) {
/* 141 */			 warning("IO: " + localIOException.getMessage());
/*		 */		 }
/*		 */ 
/* 144 */		 if (paramBoolean) {
/* 145 */			 this.f.remove(paramServerSocket);
/*		 */		 }
/*		 */ 
/* 148 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 protected void e() {
/* 152 */		 a(false);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(boolean paramBoolean) {
/* 156 */		 int i = 0;
/* 157 */		 for (Iterator localIterator = this.e.iterator(); localIterator.hasNext(); ) { localObject = (DatagramSocket)localIterator.next();
/* 158 */			 if (a((DatagramSocket)localObject, false))
/* 159 */				 i++;
/*		 */		 }
/* 164 */		 Object localObject;
/* 162 */		 this.e.clear();
/*		 */ 
/* 164 */		 for (localIterator = this.f.iterator(); localIterator.hasNext(); ) { localObject = (ServerSocket)localIterator.next();
/* 165 */			 if (a((ServerSocket)localObject, false)) {
/* 166 */				 i++;
/*		 */			 }
/*		 */		 }
/* 169 */		 this.f.clear();
/*		 */ 
/* 171 */		 if ((paramBoolean) && (0 < i))
/* 172 */			 warning("Force closed " + i + " sockets");
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RemoteConnectionThread
 * JD-Core Version:		0.6.0
 */