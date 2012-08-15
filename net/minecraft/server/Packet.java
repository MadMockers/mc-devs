/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.io.EOFException;
/*		 */ import java.io.IOException;
/*		 */ import java.io.PrintStream;
/*		 */ import java.net.SocketException;
/*		 */ import java.net.SocketTimeoutException;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public abstract class Packet
/*		 */ {
/*	14 */	 public static IntHashMap l = new IntHashMap();
/*	15 */	 private static Map a = new HashMap();
/*	16 */	 private static Set b = new HashSet();
/*	17 */	 private static Set c = new HashSet();
/*	18 */	 public final long timestamp = System.currentTimeMillis();
/*		 */	 public static long n;
/*		 */	 public static long o;
/*		 */	 public static long p;
/*		 */	 public static long q;
/*	23 */	 public boolean lowPriority = false;
/*		 */ 
/*		 */	 static void a(int i, boolean flag, boolean flag1, Class oclass)
/*		 */	 {
/*	28 */		 if (l.b(i))
/*	29 */			 throw new IllegalArgumentException("Duplicate packet id:" + i);
/*	30 */		 if (a.containsKey(oclass)) {
/*	31 */			 throw new IllegalArgumentException("Duplicate packet class:" + oclass);
/*		 */		 }
/*	33 */		 l.a(i, oclass);
/*	34 */		 a.put(oclass, Integer.valueOf(i));
/*	35 */		 if (flag) {
/*	36 */			 b.add(Integer.valueOf(i));
/*		 */		 }
/*		 */ 
/*	39 */		 if (flag1)
/*	40 */			 c.add(Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public static Packet d(int i)
/*		 */	 {
/*		 */		 try
/*		 */		 {
/*	47 */			 Class oclass = (Class)l.get(i);
/*		 */ 
/*	49 */			 return oclass == null ? null : (Packet)oclass.newInstance();
/*		 */		 } catch (Exception exception) {
/*	51 */			 exception.printStackTrace();
/*	52 */			 System.out.println("Skipping packet with id " + i);
/*	53 */		 }return null;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(DataOutputStream dataoutputstream, byte[] abyte) throws IOException
/*		 */	 {
/*	58 */		 dataoutputstream.writeShort(abyte.length);
/*	59 */		 dataoutputstream.write(abyte);
/*		 */	 }
/*		 */ 
/*		 */	 public static byte[] b(DataInputStream datainputstream) throws IOException {
/*	63 */		 short short1 = datainputstream.readShort();
/*		 */ 
/*	65 */		 if (short1 < 0) {
/*	66 */			 throw new IOException("Key was smaller than nothing!	Weird key!");
/*		 */		 }
/*	68 */		 byte[] abyte = new byte[short1];
/*		 */ 
/*	70 */		 datainputstream.read(abyte);
/*	71 */		 return abyte;
/*		 */	 }
/*		 */ 
/*		 */	 public final int k()
/*		 */	 {
/*	76 */		 return ((Integer)a.get(getClass())).intValue();
/*		 */	 }
/*		 */ 
/*	80 */	 public static Packet a(DataInputStream datainputstream, boolean flag) throws IOException { boolean flag1 = false;
/*	81 */		 Packet packet = null;
/*		 */		 int i;
/*		 */		 try {
/*	86 */			 i = datainputstream.read();
/*	87 */			 if (i == -1) {
/*	88 */				 return null;
/*		 */			 }
/*		 */ 
/*	91 */			 if (((flag) && (!c.contains(Integer.valueOf(i)))) || ((!flag) && (!b.contains(Integer.valueOf(i))))) {
/*	92 */				 throw new IOException("Bad packet id " + i);
/*		 */			 }
/*		 */ 
/*	95 */			 packet = d(i);
/*	96 */			 if (packet == null) {
/*	97 */				 throw new IOException("Bad packet id " + i);
/*		 */			 }
/*		 */ 
/* 100 */			 packet.a(datainputstream);
/* 101 */			 n += 1L;
/* 102 */			 o += packet.a();
/*		 */		 } catch (EOFException eofexception) {
/* 104 */			 System.out.println("Reached end of stream");
/* 105 */			 return null;
/*		 */		 }
/*		 */		 catch (SocketTimeoutException exception)
/*		 */		 {
/* 110 */			 System.out.println("Read timed out");
/* 111 */			 return null;
/*		 */		 } catch (SocketException exception) {
/* 113 */			 System.out.println("Connection reset");
/* 114 */			 return null;
/*		 */		 }
/*		 */ 
/* 119 */		 PacketCounter.a(i, packet.a());
/* 120 */		 n += 1L;
/* 121 */		 o += packet.a();
/* 122 */		 return packet; }
/*		 */ 
/*		 */	 public static void a(Packet packet, DataOutputStream dataoutputstream) throws IOException
/*		 */	 {
/* 126 */		 dataoutputstream.write(packet.k());
/* 127 */		 packet.a(dataoutputstream);
/* 128 */		 p += 1L;
/* 129 */		 q += packet.a();
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(String s, DataOutputStream dataoutputstream) throws IOException {
/* 133 */		 if (s.length() > 32767) {
/* 134 */			 throw new IOException("String too big");
/*		 */		 }
/* 136 */		 dataoutputstream.writeShort(s.length());
/* 137 */		 dataoutputstream.writeChars(s);
/*		 */	 }
/*		 */ 
/*		 */	 public static String a(DataInputStream datainputstream, int i) throws IOException
/*		 */	 {
/* 142 */		 short short1 = datainputstream.readShort();
/*		 */ 
/* 144 */		 if (short1 > i)
/* 145 */			 throw new IOException("Received string length longer than maximum allowed (" + short1 + " > " + i + ")");
/* 146 */		 if (short1 < 0) {
/* 147 */			 throw new IOException("Received string length is less than zero! Weird string!");
/*		 */		 }
/* 149 */		 StringBuilder stringbuilder = new StringBuilder();
/*		 */ 
/* 151 */		 for (int j = 0; j < short1; j++) {
/* 152 */			 stringbuilder.append(datainputstream.readChar());
/*		 */		 }
/*		 */ 
/* 155 */		 return stringbuilder.toString(); } 
/*		 */	 public abstract void a(DataInputStream paramDataInputStream) throws IOException;
/*		 */ 
/*		 */	 public abstract void a(DataOutputStream paramDataOutputStream) throws IOException;
/*		 */ 
/*		 */	 public abstract void handle(NetHandler paramNetHandler);
/*		 */ 
/*		 */	 public abstract int a();
/*		 */ 
/* 168 */	 public boolean e() { return false; }
/*		 */ 
/*		 */	 public boolean a(Packet packet)
/*		 */	 {
/* 172 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a_() {
/* 176 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString() {
/* 180 */		 String s = getClass().getSimpleName();
/*		 */ 
/* 182 */		 return s;
/*		 */	 }
/*		 */ 
/*		 */	 public static ItemStack c(DataInputStream datainputstream) throws IOException {
/* 186 */		 ItemStack itemstack = null;
/* 187 */		 short short1 = datainputstream.readShort();
/*		 */ 
/* 189 */		 if (short1 >= 0) {
/* 190 */			 byte b0 = datainputstream.readByte();
/* 191 */			 short short2 = datainputstream.readShort();
/*		 */ 
/* 193 */			 itemstack = new ItemStack(short1, b0, short2);
/* 194 */			 itemstack.tag = d(datainputstream);
/*		 */		 }
/*		 */ 
/* 197 */		 return itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(ItemStack itemstack, DataOutputStream dataoutputstream) throws IOException {
/* 201 */		 if (itemstack == null) {
/* 202 */			 dataoutputstream.writeShort(-1);
/*		 */		 } else {
/* 204 */			 dataoutputstream.writeShort(itemstack.id);
/* 205 */			 dataoutputstream.writeByte(itemstack.count);
/* 206 */			 dataoutputstream.writeShort(itemstack.getData());
/* 207 */			 NBTTagCompound nbttagcompound = null;
/*		 */ 
/* 209 */			 if ((itemstack.getItem().m()) || (itemstack.getItem().p())) {
/* 210 */				 nbttagcompound = itemstack.tag;
/*		 */			 }
/*		 */ 
/* 213 */			 a(nbttagcompound, dataoutputstream);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static NBTTagCompound d(DataInputStream datainputstream) throws IOException {
/* 218 */		 short short1 = datainputstream.readShort();
/*		 */ 
/* 220 */		 if (short1 < 0) {
/* 221 */			 return null;
/*		 */		 }
/* 223 */		 byte[] abyte = new byte[short1];
/*		 */ 
/* 225 */		 datainputstream.readFully(abyte);
/* 226 */		 return NBTCompressedStreamTools.a(abyte);
/*		 */	 }
/*		 */ 
/*		 */	 protected static void a(NBTTagCompound nbttagcompound, DataOutputStream dataoutputstream) throws IOException
/*		 */	 {
/* 231 */		 if (nbttagcompound == null) {
/* 232 */			 dataoutputstream.writeShort(-1);
/*		 */		 } else {
/* 234 */			 byte[] abyte = NBTCompressedStreamTools.a(nbttagcompound);
/*		 */ 
/* 236 */			 dataoutputstream.writeShort((short)abyte.length);
/* 237 */			 dataoutputstream.write(abyte);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 static {
/* 242 */		 a(0, true, true, Packet0KeepAlive.class);
/* 243 */		 a(1, true, true, Packet1Login.class);
/* 244 */		 a(2, false, true, Packet2Handshake.class);
/* 245 */		 a(3, true, true, Packet3Chat.class);
/* 246 */		 a(4, true, false, Packet4UpdateTime.class);
/* 247 */		 a(5, true, false, Packet5EntityEquipment.class);
/* 248 */		 a(6, true, false, Packet6SpawnPosition.class);
/* 249 */		 a(7, false, true, Packet7UseEntity.class);
/* 250 */		 a(8, true, false, Packet8UpdateHealth.class);
/* 251 */		 a(9, true, true, Packet9Respawn.class);
/* 252 */		 a(10, true, true, Packet10Flying.class);
/* 253 */		 a(11, true, true, Packet11PlayerPosition.class);
/* 254 */		 a(12, true, true, Packet12PlayerLook.class);
/* 255 */		 a(13, true, true, Packet13PlayerLookMove.class);
/* 256 */		 a(14, false, true, Packet14BlockDig.class);
/* 257 */		 a(15, false, true, Packet15Place.class);
/* 258 */		 a(16, false, true, Packet16BlockItemSwitch.class);
/* 259 */		 a(17, true, false, Packet17EntityLocationAction.class);
/* 260 */		 a(18, true, true, Packet18ArmAnimation.class);
/* 261 */		 a(19, false, true, Packet19EntityAction.class);
/* 262 */		 a(20, true, false, Packet20NamedEntitySpawn.class);
/* 263 */		 a(21, true, false, Packet21PickupSpawn.class);
/* 264 */		 a(22, true, false, Packet22Collect.class);
/* 265 */		 a(23, true, false, Packet23VehicleSpawn.class);
/* 266 */		 a(24, true, false, Packet24MobSpawn.class);
/* 267 */		 a(25, true, false, Packet25EntityPainting.class);
/* 268 */		 a(26, true, false, Packet26AddExpOrb.class);
/* 269 */		 a(28, true, false, Packet28EntityVelocity.class);
/* 270 */		 a(29, true, false, Packet29DestroyEntity.class);
/* 271 */		 a(30, true, false, Packet30Entity.class);
/* 272 */		 a(31, true, false, Packet31RelEntityMove.class);
/* 273 */		 a(32, true, false, Packet32EntityLook.class);
/* 274 */		 a(33, true, false, Packet33RelEntityMoveLook.class);
/* 275 */		 a(34, true, false, Packet34EntityTeleport.class);
/* 276 */		 a(35, true, false, Packet35EntityHeadRotation.class);
/* 277 */		 a(38, true, false, Packet38EntityStatus.class);
/* 278 */		 a(39, true, false, Packet39AttachEntity.class);
/* 279 */		 a(40, true, false, Packet40EntityMetadata.class);
/* 280 */		 a(41, true, false, Packet41MobEffect.class);
/* 281 */		 a(42, true, false, Packet42RemoveMobEffect.class);
/* 282 */		 a(43, true, false, Packet43SetExperience.class);
/* 283 */		 a(51, true, false, Packet51MapChunk.class);
/* 284 */		 a(52, true, false, Packet52MultiBlockChange.class);
/* 285 */		 a(53, true, false, Packet53BlockChange.class);
/* 286 */		 a(54, true, false, Packet54PlayNoteBlock.class);
/* 287 */		 a(55, true, false, Packet55BlockBreakAnimation.class);
/* 288 */		 a(56, true, false, Packet56MapChunkBulk.class);
/* 289 */		 a(60, true, false, Packet60Explosion.class);
/* 290 */		 a(61, true, false, Packet61WorldEvent.class);
/* 291 */		 a(62, true, false, Packet62NamedSoundEffect.class);
/* 292 */		 a(70, true, false, Packet70Bed.class);
/* 293 */		 a(71, true, false, Packet71Weather.class);
/* 294 */		 a(100, true, false, Packet100OpenWindow.class);
/* 295 */		 a(101, true, true, Packet101CloseWindow.class);
/* 296 */		 a(102, false, true, Packet102WindowClick.class);
/* 297 */		 a(103, true, false, Packet103SetSlot.class);
/* 298 */		 a(104, true, false, Packet104WindowItems.class);
/* 299 */		 a(105, true, false, Packet105CraftProgressBar.class);
/* 300 */		 a(106, true, true, Packet106Transaction.class);
/* 301 */		 a(107, true, true, Packet107SetCreativeSlot.class);
/* 302 */		 a(108, false, true, Packet108ButtonClick.class);
/* 303 */		 a(130, true, true, Packet130UpdateSign.class);
/* 304 */		 a(131, true, false, Packet131ItemData.class);
/* 305 */		 a(132, true, false, Packet132TileEntityData.class);
/* 306 */		 a(200, true, false, Packet200Statistic.class);
/* 307 */		 a(201, true, false, Packet201PlayerInfo.class);
/* 308 */		 a(202, true, true, Packet202Abilities.class);
/* 309 */		 a(203, true, true, Packet203TabComplete.class);
/* 310 */		 a(204, false, true, Packet204LocaleAndViewDistance.class);
/* 311 */		 a(205, false, true, Packet205ClientCommand.class);
/* 312 */		 a(250, true, true, Packet250CustomPayload.class);
/* 313 */		 a(252, true, true, Packet252KeyResponse.class);
/* 314 */		 a(253, true, false, Packet253KeyRequest.class);
/* 315 */		 a(254, false, true, Packet254GetInfo.class);
/* 316 */		 a(255, true, true, Packet255KickDisconnect.class);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Packet
 * JD-Core Version:		0.6.0
 */