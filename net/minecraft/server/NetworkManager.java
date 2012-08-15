/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.BufferedOutputStream;
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintStream;
/*		 */ import java.net.Socket;
/*		 */ import java.net.SocketAddress;
/*		 */ import java.net.SocketException;
/*		 */ import java.security.PrivateKey;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Queue;
/*		 */ import java.util.concurrent.ConcurrentLinkedQueue;
/*		 */ import java.util.concurrent.atomic.AtomicInteger;
/*		 */ import javax.crypto.SecretKey;
/*		 */ 
/*		 */ public class NetworkManager
/*		 */	 implements INetworkManager
/*		 */ {
/*	21 */	 public static AtomicInteger a = new AtomicInteger();
/*	22 */	 public static AtomicInteger b = new AtomicInteger();
/*	23 */	 private Object h = new Object();
/*		 */	 public Socket socket;
/*		 */	 private final SocketAddress j;
/*		 */	 private volatile DataInputStream input;
/*		 */	 private volatile DataOutputStream output;
/*	28 */	 private volatile boolean m = true;
/*	29 */	 private volatile boolean n = false;
/*	30 */	 private Queue inboundQueue = new ConcurrentLinkedQueue();
/*	31 */	 private List highPriorityQueue = Collections.synchronizedList(new ArrayList());
/*	32 */	 private List lowPriorityQueue = Collections.synchronizedList(new ArrayList());
/*		 */	 private NetHandler packetListener;
/*	34 */	 private boolean s = false;
/*		 */	 private Thread t;
/*		 */	 private Thread u;
/*	37 */	 private String v = "";
/*		 */	 private Object[] w;
/*	39 */	 private int x = 0;
/*	40 */	 private int y = 0;
/*	41 */	 public static int[] c = new int[256];
/*	42 */	 public static int[] d = new int[256];
/*	43 */	 public int e = 0;
/*	44 */	 boolean f = false;
/*	45 */	 boolean g = false;
/*	46 */	 private SecretKey z = null;
/*	47 */	 private PrivateKey A = null;
/*	48 */	 private int lowPriorityQueueDelay = 50;
/*		 */ 
/*		 */	 public NetworkManager(Socket socket, String s, NetHandler nethandler, PrivateKey privatekey) throws IOException {
/*	51 */		 this.A = privatekey;
/*	52 */		 this.socket = socket;
/*	53 */		 this.j = socket.getRemoteSocketAddress();
/*	54 */		 this.packetListener = nethandler;
/*		 */		 try
/*		 */		 {
/*	57 */			 socket.setSoTimeout(30000);
/*	58 */			 socket.setTrafficClass(24);
/*		 */		 } catch (SocketException socketexception) {
/*	60 */			 System.err.println(socketexception.getMessage());
/*		 */		 }
/*		 */ 
/*	63 */		 this.input = new DataInputStream(socket.getInputStream());
/*	64 */		 this.output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 5120));
/*	65 */		 this.u = new NetworkReaderThread(this, s + " read thread");
/*	66 */		 this.t = new NetworkWriterThread(this, s + " write thread");
/*	67 */		 this.u.start();
/*	68 */		 this.t.start();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NetHandler nethandler) {
/*	72 */		 this.packetListener = nethandler;
/*		 */	 }
/*		 */ 
/*		 */	 public void queue(Packet packet) {
/*	76 */		 if (!this.s) {
/*	77 */			 Object object = this.h;
/*		 */ 
/*	79 */			 synchronized (this.h) {
/*	80 */				 this.y += packet.a() + 1;
/*	81 */				 if (packet.lowPriority)
/*	82 */					 this.lowPriorityQueue.add(packet);
/*		 */				 else
/*	84 */					 this.highPriorityQueue.add(packet);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean h()
/*		 */	 {
/*	91 */		 boolean flag = false;
/*		 */		 try
/*		 */		 {
/*	98 */			 if ((this.e == 0) || (System.currentTimeMillis() - ((Packet)this.highPriorityQueue.get(0)).timestamp >= this.e)) {
/*	99 */				 Packet packet = a(false);
/* 100 */				 if (packet != null) {
/* 101 */					 Packet.a(packet, this.output);
/* 102 */					 if (((packet instanceof Packet252KeyResponse)) && (!this.g)) {
/* 103 */						 if (!this.packetListener.a()) {
/* 104 */							 this.z = ((Packet252KeyResponse)packet).d();
/*		 */						 }
/*		 */ 
/* 107 */						 k();
/*		 */					 }
/*		 */ 
/* 110 */					 int[] aint = d;
/* 111 */					 int i = packet.k();
/* 112 */					 aint[i] += packet.a() + 1;
/* 113 */					 flag = true;
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/* 118 */			 if (((flag) || (this.lowPriorityQueueDelay-- <= 0)) && (!this.lowPriorityQueue.isEmpty()) && ((this.highPriorityQueue.isEmpty()) || (((Packet)this.highPriorityQueue.get(0)).timestamp > ((Packet)this.lowPriorityQueue.get(0)).timestamp))) {
/* 119 */				 Packet packet = a(true);
/* 120 */				 if (packet != null) {
/* 121 */					 Packet.a(packet, this.output);
/* 122 */					 int[] aint = d;
/* 123 */					 int i = packet.k();
/* 124 */					 aint[i] += packet.a() + 1;
/* 125 */					 this.lowPriorityQueueDelay = 0;
/* 126 */					 flag = true;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 130 */			 return flag;
/*		 */		 } catch (Exception exception) {
/* 132 */			 if (!this.n) {
/* 133 */				 a(exception);
/*		 */			 }
/*		 */		 }
/* 136 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private Packet a(boolean flag)
/*		 */	 {
/* 141 */		 Packet packet = null;
/* 142 */		 List list = flag ? this.lowPriorityQueue : this.highPriorityQueue;
/* 143 */		 Object object = this.h;
/*		 */ 
/* 145 */		 synchronized (this.h) {
/* 146 */			 while ((!list.isEmpty()) && (packet == null)) {
/* 147 */				 packet = (Packet)list.remove(0);
/* 148 */				 this.y -= packet.a() + 1;
/* 149 */				 if (a(packet, flag)) {
/* 150 */					 packet = null;
/*		 */				 }
/*		 */			 }
/*		 */ 
/* 154 */			 return packet;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(Packet packet, boolean flag) {
/* 159 */		 if (!packet.e()) {
/* 160 */			 return false;
/* 162 */		 }List list = flag ? this.lowPriorityQueue : this.highPriorityQueue;
/* 163 */		 Iterator iterator = list.iterator();
/*		 */		 Packet packet1;
/*		 */		 do {
/* 168 */			 if (!iterator.hasNext()) {
/* 169 */				 return false;
/*		 */			 }
/*		 */ 
/* 172 */			 packet1 = (Packet)iterator.next();
/* 173 */		 }while (packet1.k() != packet.k());
/*		 */ 
/* 175 */		 return packet.a(packet1);
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/* 180 */		 if (this.u != null) {
/* 181 */			 this.u.interrupt();
/*		 */		 }
/*		 */ 
/* 184 */		 if (this.t != null)
/* 185 */			 this.t.interrupt();
/*		 */	 }
/*		 */ 
/*		 */	 private boolean i()
/*		 */	 {
/* 190 */		 boolean flag = false;
/*		 */		 try
/*		 */		 {
/* 193 */			 Packet packet = Packet.a(this.input, this.packetListener.a());
/*		 */ 
/* 195 */			 if (packet != null) {
/* 196 */				 if (((packet instanceof Packet252KeyResponse)) && (!this.f)) {
/* 197 */					 if (this.packetListener.a()) {
/* 198 */						 this.z = ((Packet252KeyResponse)packet).a(this.A);
/*		 */					 }
/*		 */ 
/* 201 */					 j();
/*		 */				 }
/*		 */ 
/* 204 */				 int[] aint = c;
/* 205 */				 int i = packet.k();
/*		 */ 
/* 207 */				 aint[i] += packet.a() + 1;
/* 208 */				 if (!this.s) {
/* 209 */					 if ((packet.a_()) && (this.packetListener.b())) {
/* 210 */						 this.x = 0;
/* 211 */						 packet.handle(this.packetListener);
/*		 */					 } else {
/* 213 */						 this.inboundQueue.add(packet);
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 217 */				 flag = true;
/*		 */			 } else {
/* 219 */				 a("disconnect.endOfStream", new Object[0]);
/*		 */			 }
/*		 */ 
/* 222 */			 return flag;
/*		 */		 } catch (Exception exception) {
/* 224 */			 if (!this.n) {
/* 225 */				 a(exception);
/*		 */			 }
/*		 */		 }
/* 228 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(Exception exception)
/*		 */	 {
/* 234 */		 a("disconnect.genericReason", new Object[] { "Internal exception: " + exception.toString() });
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String s, Object[] aobject) {
/* 238 */		 if (this.m) {
/* 239 */			 this.n = true;
/* 240 */			 this.v = s;
/* 241 */			 this.w = aobject;
/* 242 */			 this.m = false;
/* 243 */			 new NetworkMasterThread(this).start();
/*		 */			 try
/*		 */			 {
/* 246 */				 this.input.close();
/* 247 */				 this.input = null;
/* 248 */				 this.output.close();
/* 249 */				 this.output = null;
/* 250 */				 this.socket.close();
/* 251 */				 this.socket = null;
/*		 */			 }
/*		 */			 catch (Throwable throwable) {
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/* 259 */		 if (this.y > 2097152) {
/* 260 */			 a("disconnect.overflow", new Object[0]);
/*		 */		 }
/*		 */ 
/* 263 */		 if (this.inboundQueue.isEmpty()) {
/* 264 */			 if (this.x++ == 1200)
/* 265 */				 a("disconnect.timeout", new Object[0]);
/*		 */		 }
/*		 */		 else {
/* 268 */			 this.x = 0;
/*		 */		 }
/*		 */ 
/* 271 */		 int i = 1000;
/*		 */ 
/* 273 */		 while ((!this.inboundQueue.isEmpty()) && (i-- >= 0)) {
/* 274 */			 Packet packet = (Packet)this.inboundQueue.poll();
/*		 */ 
/* 276 */			 packet.handle(this.packetListener);
/*		 */		 }
/*		 */ 
/* 279 */		 a();
/* 280 */		 if ((this.n) && (this.inboundQueue.isEmpty()))
/* 281 */			 this.packetListener.a(this.v, this.w);
/*		 */	 }
/*		 */ 
/*		 */	 public SocketAddress getSocketAddress()
/*		 */	 {
/* 286 */		 return this.j;
/*		 */	 }
/*		 */ 
/*		 */	 public void d() {
/* 290 */		 if (!this.s) {
/* 291 */			 a();
/* 292 */			 this.s = true;
/* 293 */			 this.u.interrupt();
/* 294 */			 new NetworkMonitorThread(this).start();
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private void j() throws IOException {
/* 299 */		 this.f = true;
/* 300 */		 this.input = new DataInputStream(MinecraftEncryption.a(this.z, this.socket.getInputStream()));
/*		 */	 }
/*		 */ 
/*		 */	 private void k() throws IOException {
/* 304 */		 this.output.flush();
/* 305 */		 this.g = true;
/* 306 */		 this.output = new DataOutputStream(new BufferedOutputStream(MinecraftEncryption.a(this.z, this.socket.getOutputStream()), 5120));
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 310 */		 return this.lowPriorityQueue.size();
/*		 */	 }
/*		 */ 
/*		 */	 public Socket getSocket() {
/* 314 */		 return this.socket;
/*		 */	 }
/*		 */ 
/*		 */	 static boolean a(NetworkManager networkmanager) {
/* 318 */		 return networkmanager.m;
/*		 */	 }
/*		 */ 
/*		 */	 static boolean b(NetworkManager networkmanager) {
/* 322 */		 return networkmanager.s;
/*		 */	 }
/*		 */ 
/*		 */	 static boolean c(NetworkManager networkmanager) {
/* 326 */		 return networkmanager.i();
/*		 */	 }
/*		 */ 
/*		 */	 static boolean d(NetworkManager networkmanager) {
/* 330 */		 return networkmanager.h();
/*		 */	 }
/*		 */ 
/*		 */	 static DataOutputStream e(NetworkManager networkmanager) {
/* 334 */		 return networkmanager.output;
/*		 */	 }
/*		 */ 
/*		 */	 static boolean f(NetworkManager networkmanager) {
/* 338 */		 return networkmanager.n;
/*		 */	 }
/*		 */ 
/*		 */	 static void a(NetworkManager networkmanager, Exception exception) {
/* 342 */		 networkmanager.a(exception);
/*		 */	 }
/*		 */ 
/*		 */	 static Thread g(NetworkManager networkmanager) {
/* 346 */		 return networkmanager.u;
/*		 */	 }
/*		 */ 
/*		 */	 static Thread h(NetworkManager networkmanager) {
/* 350 */		 return networkmanager.t;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.NetworkManager
 * JD-Core Version:		0.6.0
 */