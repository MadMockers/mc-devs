/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class LongHashMap
/*		 */ {
/*		 */	 private transient LongHashMapEntry[] entries;
/*		 */	 private transient int count;
/*		 */	 private int c;
/*		 */	 private final float d;
/*		 */	 private volatile transient int e;
/*		 */ 
/*		 */	 public LongHashMap()
/*		 */	 {
/*	18 */		 this.d = 0.75F;
/*	19 */		 this.c = 12;
/*	20 */		 this.entries = new LongHashMapEntry[16];
/*		 */	 }
/*		 */ 
/*		 */	 private static int g(long paramLong) {
/*	24 */		 return a((int)(paramLong ^ paramLong >>> 32));
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(int paramInt) {
/*	28 */		 paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
/*	29 */		 return paramInt ^ paramInt >>> 7 ^ paramInt >>> 4;
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(int paramInt1, int paramInt2) {
/*	33 */		 return paramInt1 & paramInt2 - 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int count() {
/*	37 */		 return this.count;
/*		 */	 }
/*		 */ 
/*		 */	 public Object getEntry(long paramLong)
/*		 */	 {
/*	45 */		 int i = g(paramLong);
/*	46 */		 for (LongHashMapEntry localLongHashMapEntry = this.entries[a(i, this.entries.length)]; localLongHashMapEntry != null; localLongHashMapEntry = localLongHashMapEntry.c) {
/*	47 */			 if (localLongHashMapEntry.a == paramLong) return localLongHashMapEntry.b;
/*		 */		 }
/*	49 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean contains(long paramLong) {
/*	53 */		 return c(paramLong) != null;
/*		 */	 }
/*		 */ 
/*		 */	 final LongHashMapEntry c(long paramLong) {
/*	57 */		 int i = g(paramLong);
/*	58 */		 for (LongHashMapEntry localLongHashMapEntry = this.entries[a(i, this.entries.length)]; localLongHashMapEntry != null; localLongHashMapEntry = localLongHashMapEntry.c) {
/*	59 */			 if (localLongHashMapEntry.a == paramLong) return localLongHashMapEntry;
/*		 */		 }
/*	61 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void put(long paramLong, Object paramObject) {
/*	65 */		 int i = g(paramLong);
/*	66 */		 int j = a(i, this.entries.length);
/*	67 */		 for (LongHashMapEntry localLongHashMapEntry = this.entries[j]; localLongHashMapEntry != null; localLongHashMapEntry = localLongHashMapEntry.c) {
/*	68 */			 if (localLongHashMapEntry.a == paramLong) {
/*	69 */				 localLongHashMapEntry.b = paramObject;
/*	70 */				 return;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	74 */		 this.e += 1;
/*	75 */		 a(i, paramLong, paramObject, j);
/*		 */	 }
/*		 */ 
/*		 */	 private void b(int paramInt)
/*		 */	 {
/*	80 */		 LongHashMapEntry[] arrayOfLongHashMapEntry1 = this.entries;
/*	81 */		 int i = arrayOfLongHashMapEntry1.length;
/*	82 */		 if (i == 1073741824) {
/*	83 */			 this.c = 2147483647;
/*	84 */			 return;
/*		 */		 }
/*		 */ 
/*	87 */		 LongHashMapEntry[] arrayOfLongHashMapEntry2 = new LongHashMapEntry[paramInt];
/*	88 */		 a(arrayOfLongHashMapEntry2);
/*	89 */		 this.entries = arrayOfLongHashMapEntry2;
/*	90 */		 this.c = (int)(paramInt * this.d);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(LongHashMapEntry[] paramArrayOfLongHashMapEntry) {
/*	94 */		 LongHashMapEntry[] arrayOfLongHashMapEntry = this.entries;
/*	95 */		 int i = paramArrayOfLongHashMapEntry.length;
/*	96 */		 for (int j = 0; j < arrayOfLongHashMapEntry.length; j++) {
/*	97 */			 Object localObject = arrayOfLongHashMapEntry[j];
/*	98 */			 if (localObject != null) {
/*	99 */				 arrayOfLongHashMapEntry[j] = null;
/*		 */				 do {
/* 101 */					 LongHashMapEntry localLongHashMapEntry = ((LongHashMapEntry)localObject).c;
/* 102 */					 int k = a(((LongHashMapEntry)localObject).d, i);
/* 103 */					 ((LongHashMapEntry)localObject).c = paramArrayOfLongHashMapEntry[k];
/* 104 */					 paramArrayOfLongHashMapEntry[k] = localObject;
/* 105 */					 localObject = localLongHashMapEntry;
/* 106 */				 }while (localObject != null);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Object remove(long paramLong) {
/* 112 */		 LongHashMapEntry localLongHashMapEntry = e(paramLong);
/* 113 */		 return localLongHashMapEntry == null ? null : localLongHashMapEntry.b;
/*		 */	 }
/*		 */ 
/*		 */	 final LongHashMapEntry e(long paramLong) {
/* 117 */		 int i = g(paramLong);
/* 118 */		 int j = a(i, this.entries.length);
/* 119 */		 Object localObject1 = this.entries[j];
/* 120 */		 Object localObject2 = localObject1;
/*		 */ 
/* 122 */		 while (localObject2 != null) {
/* 123 */			 LongHashMapEntry localLongHashMapEntry = localObject2.c;
/* 124 */			 if (localObject2.a == paramLong) {
/* 125 */				 this.e += 1;
/* 126 */				 this.count -= 1;
/* 127 */				 if (localObject1 == localObject2) this.entries[j] = localLongHashMapEntry; else
/* 128 */					 ((LongHashMapEntry)localObject1).c = localLongHashMapEntry;
/* 129 */				 return localObject2;
/*		 */			 }
/* 131 */			 localObject1 = localObject2;
/* 132 */			 localObject2 = localLongHashMapEntry;
/*		 */		 }
/*		 */ 
/* 135 */		 return (LongHashMapEntry)localObject2;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt1, long paramLong, Object paramObject, int paramInt2)
/*		 */	 {
/* 218 */		 LongHashMapEntry localLongHashMapEntry = this.entries[paramInt2];
/* 219 */		 this.entries[paramInt2] = new LongHashMapEntry(paramInt1, paramLong, paramObject, localLongHashMapEntry);
/* 220 */		 if (this.count++ >= this.c) b(2 * this.entries.length);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.LongHashMap
 * JD-Core Version:		0.6.0
 */