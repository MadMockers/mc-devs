/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataInputStream;
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.concurrent.locks.Lock;
/*		 */ import java.util.concurrent.locks.ReadWriteLock;
/*		 */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*		 */ 
/*		 */ public class DataWatcher
/*		 */ {
/*	34 */	 private static final HashMap a = new HashMap();
/*		 */	 private final Map b;
/*		 */	 private boolean c;
/*		 */	 private ReadWriteLock d;
/*		 */ 
/*		 */	 public DataWatcher()
/*		 */	 {
/*	53 */		 this.b = new HashMap();
/*		 */ 
/*	55 */		 this.d = new ReentrantReadWriteLock();
/*		 */	 }
/*		 */	 public void a(int paramInt, Object paramObject) {
/*	58 */		 Integer localInteger = (Integer)a.get(paramObject.getClass());
/*	59 */		 if (localInteger == null) {
/*	60 */			 throw new IllegalArgumentException("Unknown data type: " + paramObject.getClass());
/*		 */		 }
/*	62 */		 if (paramInt > 31) {
/*	63 */			 throw new IllegalArgumentException("Data value id is too big with " + paramInt + "! (Max is " + 31 + ")");
/*		 */		 }
/*	65 */		 if (this.b.containsKey(Integer.valueOf(paramInt))) {
/*	66 */			 throw new IllegalArgumentException("Duplicate id value for " + paramInt + "!");
/*		 */		 }
/*		 */ 
/*	69 */		 WatchableObject localWatchableObject = new WatchableObject(localInteger.intValue(), paramInt, paramObject);
/*	70 */		 this.d.writeLock().lock();
/*	71 */		 this.b.put(Integer.valueOf(paramInt), localWatchableObject);
/*	72 */		 this.d.writeLock().unlock();
/*		 */	 }
/*		 */ 
/*		 */	 public byte getByte(int paramInt) {
/*	76 */		 return ((Byte)i(paramInt).b()).byteValue();
/*		 */	 }
/*		 */ 
/*		 */	 public short getShort(int paramInt) {
/*	80 */		 return ((Short)i(paramInt).b()).shortValue();
/*		 */	 }
/*		 */ 
/*		 */	 public int getInt(int paramInt) {
/*	84 */		 return ((Integer)i(paramInt).b()).intValue();
/*		 */	 }
/*		 */ 
/*		 */	 public String getString(int paramInt)
/*		 */	 {
/*	92 */		 return (String)i(paramInt).b();
/*		 */	 }
/*		 */	 private WatchableObject i(int paramInt) {
/* 101 */		 this.d.readLock().lock();
/*		 */		 WatchableObject localWatchableObject;
/*		 */		 try {
/* 104 */			 localWatchableObject = (WatchableObject)this.b.get(Integer.valueOf(paramInt));
/*		 */		 } catch (Throwable localThrowable) {
/* 106 */			 CrashReport localCrashReport = new CrashReport("getting synched entity data", localThrowable);
/*		 */ 
/* 108 */			 localCrashReport.a("EntityData ID", Integer.valueOf(paramInt));
/* 109 */			 throw new ReportedException(localCrashReport);
/*		 */		 }
/*		 */ 
/* 112 */		 this.d.readLock().unlock();
/* 113 */		 return localWatchableObject;
/*		 */	 }
/*		 */ 
/*		 */	 public void watch(int paramInt, Object paramObject)
/*		 */	 {
/* 122 */		 WatchableObject localWatchableObject = i(paramInt);
/*		 */ 
/* 125 */		 if (!paramObject.equals(localWatchableObject.b())) {
/* 126 */			 localWatchableObject.a(paramObject);
/* 127 */			 localWatchableObject.a(true);
/* 128 */			 this.c = true;
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a()
/*		 */	 {
/* 138 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public static void a(List paramList, DataOutputStream paramDataOutputStream) {
/* 142 */		 if (paramList != null) {
/* 143 */			 for (WatchableObject localWatchableObject : paramList) {
/* 144 */				 a(paramDataOutputStream, localWatchableObject);
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 149 */		 paramDataOutputStream.writeByte(127);
/*		 */	 }
/*		 */ 
/*		 */	 public List b() {
/* 153 */		 ArrayList localArrayList = null;
/*		 */ 
/* 155 */		 if (this.c) {
/* 156 */			 this.d.readLock().lock();
/* 157 */			 for (WatchableObject localWatchableObject : this.b.values()) {
/* 158 */				 if (localWatchableObject.d()) {
/* 159 */					 localWatchableObject.a(false);
/*		 */ 
/* 161 */					 if (localArrayList == null) {
/* 162 */						 localArrayList = new ArrayList();
/*		 */					 }
/* 164 */					 localArrayList.add(localWatchableObject);
/*		 */				 }
/*		 */			 }
/* 167 */			 this.d.readLock().unlock();
/*		 */		 }
/* 169 */		 this.c = false;
/*		 */ 
/* 171 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream) {
/* 175 */		 this.d.readLock().lock();
/* 176 */		 for (WatchableObject localWatchableObject : this.b.values()) {
/* 177 */			 a(paramDataOutputStream, localWatchableObject);
/*		 */		 }
/* 179 */		 this.d.readLock().unlock();
/*		 */ 
/* 182 */		 paramDataOutputStream.writeByte(127);
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(DataOutputStream paramDataOutputStream, WatchableObject paramWatchableObject)
/*		 */	 {
/* 202 */		 int i = (paramWatchableObject.c() << 5 | paramWatchableObject.a() & 0x1F) & 0xFF;
/* 203 */		 paramDataOutputStream.writeByte(i);
/*		 */		 Object localObject;
/* 206 */		 switch (paramWatchableObject.c()) {
/*		 */		 case 0:
/* 208 */			 paramDataOutputStream.writeByte(((Byte)paramWatchableObject.b()).byteValue());
/* 209 */			 break;
/*		 */		 case 1:
/* 211 */			 paramDataOutputStream.writeShort(((Short)paramWatchableObject.b()).shortValue());
/* 212 */			 break;
/*		 */		 case 2:
/* 214 */			 paramDataOutputStream.writeInt(((Integer)paramWatchableObject.b()).intValue());
/* 215 */			 break;
/*		 */		 case 3:
/* 217 */			 paramDataOutputStream.writeFloat(((Float)paramWatchableObject.b()).floatValue());
/* 218 */			 break;
/*		 */		 case 4:
/* 220 */			 Packet.a((String)paramWatchableObject.b(), paramDataOutputStream);
/* 221 */			 break;
/*		 */		 case 5:
/* 223 */			 localObject = (ItemStack)paramWatchableObject.b();
/* 224 */			 paramDataOutputStream.writeShort(((ItemStack)localObject).getItem().id);
/* 225 */			 paramDataOutputStream.writeByte(((ItemStack)localObject).count);
/* 226 */			 paramDataOutputStream.writeShort(((ItemStack)localObject).getData());
/*		 */ 
/* 228 */			 break;
/*		 */		 case 6:
/* 230 */			 localObject = (ChunkCoordinates)paramWatchableObject.b();
/* 231 */			 paramDataOutputStream.writeInt(((ChunkCoordinates)localObject).x);
/* 232 */			 paramDataOutputStream.writeInt(((ChunkCoordinates)localObject).y);
/* 233 */			 paramDataOutputStream.writeInt(((ChunkCoordinates)localObject).z);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public static List a(DataInputStream paramDataInputStream)
/*		 */	 {
/* 240 */		 ArrayList localArrayList = null;
/*		 */ 
/* 242 */		 int i = paramDataInputStream.readByte();
/*		 */ 
/* 244 */		 while (i != 127)
/*		 */		 {
/* 246 */			 if (localArrayList == null) {
/* 247 */				 localArrayList = new ArrayList();
/*		 */			 }
/*		 */ 
/* 251 */			 int j = (i & 0xE0) >> 5;
/* 252 */			 int k = i & 0x1F;
/*		 */ 
/* 254 */			 WatchableObject localWatchableObject = null;
/* 255 */			 switch (j) {
/*		 */			 case 0:
/* 257 */				 localWatchableObject = new WatchableObject(j, k, Byte.valueOf(paramDataInputStream.readByte()));
/* 258 */				 break;
/*		 */			 case 1:
/* 260 */				 localWatchableObject = new WatchableObject(j, k, Short.valueOf(paramDataInputStream.readShort()));
/* 261 */				 break;
/*		 */			 case 2:
/* 263 */				 localWatchableObject = new WatchableObject(j, k, Integer.valueOf(paramDataInputStream.readInt()));
/* 264 */				 break;
/*		 */			 case 3:
/* 266 */				 localWatchableObject = new WatchableObject(j, k, Float.valueOf(paramDataInputStream.readFloat()));
/* 267 */				 break;
/*		 */			 case 4:
/* 269 */				 localWatchableObject = new WatchableObject(j, k, Packet.a(paramDataInputStream, 64));
/* 270 */				 break;
/*		 */			 case 5:
/* 272 */				 int m = paramDataInputStream.readShort();
/* 273 */				 int n = paramDataInputStream.readByte();
/* 274 */				 int i1 = paramDataInputStream.readShort();
/* 275 */				 localWatchableObject = new WatchableObject(j, k, new ItemStack(m, n, i1));
/* 276 */				 break;
/*		 */			 case 6:
/* 278 */				 int i2 = paramDataInputStream.readInt();
/* 279 */				 int i3 = paramDataInputStream.readInt();
/* 280 */				 int i4 = paramDataInputStream.readInt();
/* 281 */				 localWatchableObject = new WatchableObject(j, k, new ChunkCoordinates(i2, i3, i4));
/*		 */			 }
/*		 */ 
/* 284 */			 localArrayList.add(localWatchableObject);
/*		 */ 
/* 286 */			 i = paramDataInputStream.readByte();
/*		 */		 }
/*		 */ 
/* 289 */		 return localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/*	37 */		 a.put(Byte.class, Integer.valueOf(0));
/*	38 */		 a.put(Short.class, Integer.valueOf(1));
/*	39 */		 a.put(Integer.class, Integer.valueOf(2));
/*	40 */		 a.put(Float.class, Integer.valueOf(3));
/*	41 */		 a.put(String.class, Integer.valueOf(4));
/*	42 */		 a.put(ItemStack.class, Integer.valueOf(5));
/*	43 */		 a.put(ChunkCoordinates.class, Integer.valueOf(6));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.DataWatcher
 * JD-Core Version:		0.6.0
 */