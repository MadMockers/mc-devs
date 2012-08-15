/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class IntHashMap
/*		 */ {
/*		 */	 private transient IntHashMapEntry[] a;
/*		 */	 private transient int b;
/*		 */	 private int c;
/*		 */	 private final float d;
/*		 */	 private volatile transient int e;
/*	17 */	 private Set f = new HashSet();
/*		 */ 
/*		 */	 public IntHashMap()
/*		 */	 {
/*	21 */		 this.d = 0.75F;
/*	22 */		 this.c = 12;
/*	23 */		 this.a = new IntHashMapEntry[16];
/*		 */	 }
/*		 */ 
/*		 */	 private static int g(int paramInt) {
/*	27 */		 paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
/*	28 */		 return paramInt ^ paramInt >>> 7 ^ paramInt >>> 4;
/*		 */	 }
/*		 */ 
/*		 */	 private static int a(int paramInt1, int paramInt2) {
/*	32 */		 return paramInt1 & paramInt2 - 1;
/*		 */	 }
/*		 */ 
/*		 */	 public Object get(int paramInt)
/*		 */	 {
/*	44 */		 int i = g(paramInt);
/*	45 */		 for (IntHashMapEntry localIntHashMapEntry = this.a[a(i, this.a.length)]; localIntHashMapEntry != null; localIntHashMapEntry = localIntHashMapEntry.c) {
/*	46 */			 if (localIntHashMapEntry.a == paramInt) return localIntHashMapEntry.b;
/*		 */		 }
/*	48 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean b(int paramInt) {
/*	52 */		 return c(paramInt) != null;
/*		 */	 }
/*		 */ 
/*		 */	 final IntHashMapEntry c(int paramInt) {
/*	56 */		 int i = g(paramInt);
/*	57 */		 for (IntHashMapEntry localIntHashMapEntry = this.a[a(i, this.a.length)]; localIntHashMapEntry != null; localIntHashMapEntry = localIntHashMapEntry.c) {
/*	58 */			 if (localIntHashMapEntry.a == paramInt) return localIntHashMapEntry;
/*		 */		 }
/*	60 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(int paramInt, Object paramObject) {
/*	64 */		 this.f.add(Integer.valueOf(paramInt));
/*	65 */		 int i = g(paramInt);
/*	66 */		 int j = a(i, this.a.length);
/*	67 */		 for (IntHashMapEntry localIntHashMapEntry = this.a[j]; localIntHashMapEntry != null; localIntHashMapEntry = localIntHashMapEntry.c) {
/*	68 */			 if (localIntHashMapEntry.a == paramInt) {
/*	69 */				 localIntHashMapEntry.b = paramObject;
/*	70 */				 return;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	74 */		 this.e += 1;
/*	75 */		 a(i, paramInt, paramObject, j);
/*		 */	 }
/*		 */ 
/*		 */	 private void h(int paramInt)
/*		 */	 {
/*	80 */		 IntHashMapEntry[] arrayOfIntHashMapEntry1 = this.a;
/*	81 */		 int i = arrayOfIntHashMapEntry1.length;
/*	82 */		 if (i == 1073741824) {
/*	83 */			 this.c = 2147483647;
/*	84 */			 return;
/*		 */		 }
/*		 */ 
/*	87 */		 IntHashMapEntry[] arrayOfIntHashMapEntry2 = new IntHashMapEntry[paramInt];
/*	88 */		 a(arrayOfIntHashMapEntry2);
/*	89 */		 this.a = arrayOfIntHashMapEntry2;
/*	90 */		 this.c = (int)(paramInt * this.d);
/*		 */	 }
/*		 */ 
/*		 */	 private void a(IntHashMapEntry[] paramArrayOfIntHashMapEntry) {
/*	94 */		 IntHashMapEntry[] arrayOfIntHashMapEntry = this.a;
/*	95 */		 int i = paramArrayOfIntHashMapEntry.length;
/*	96 */		 for (int j = 0; j < arrayOfIntHashMapEntry.length; j++) {
/*	97 */			 Object localObject = arrayOfIntHashMapEntry[j];
/*	98 */			 if (localObject != null) {
/*	99 */				 arrayOfIntHashMapEntry[j] = null;
/*		 */				 do {
/* 101 */					 IntHashMapEntry localIntHashMapEntry = ((IntHashMapEntry)localObject).c;
/* 102 */					 int k = a(((IntHashMapEntry)localObject).d, i);
/* 103 */					 ((IntHashMapEntry)localObject).c = paramArrayOfIntHashMapEntry[k];
/* 104 */					 paramArrayOfIntHashMapEntry[k] = localObject;
/* 105 */					 localObject = localIntHashMapEntry;
/* 106 */				 }while (localObject != null);
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public Object d(int paramInt) {
/* 112 */		 this.f.remove(Integer.valueOf(paramInt));
/* 113 */		 IntHashMapEntry localIntHashMapEntry = e(paramInt);
/* 114 */		 return localIntHashMapEntry == null ? null : localIntHashMapEntry.b;
/*		 */	 }
/*		 */ 
/*		 */	 final IntHashMapEntry e(int paramInt) {
/* 118 */		 int i = g(paramInt);
/* 119 */		 int j = a(i, this.a.length);
/* 120 */		 Object localObject1 = this.a[j];
/* 121 */		 Object localObject2 = localObject1;
/*		 */ 
/* 123 */		 while (localObject2 != null) {
/* 124 */			 IntHashMapEntry localIntHashMapEntry = localObject2.c;
/* 125 */			 if (localObject2.a == paramInt) {
/* 126 */				 this.e += 1;
/* 127 */				 this.b -= 1;
/* 128 */				 if (localObject1 == localObject2) this.a[j] = localIntHashMapEntry; else
/* 129 */					 ((IntHashMapEntry)localObject1).c = localIntHashMapEntry;
/* 130 */				 return localObject2;
/*		 */			 }
/* 132 */			 localObject1 = localObject2;
/* 133 */			 localObject2 = localIntHashMapEntry;
/*		 */		 }
/*		 */ 
/* 136 */		 return (IntHashMapEntry)localObject2;
/*		 */	 }
/*		 */ 
/*		 */	 public void c() {
/* 140 */		 this.e += 1;
/* 141 */		 IntHashMapEntry[] arrayOfIntHashMapEntry = this.a;
/* 142 */		 for (int i = 0; i < arrayOfIntHashMapEntry.length; i++)
/* 143 */			 arrayOfIntHashMapEntry[i] = null;
/* 144 */		 this.b = 0;
/*		 */	 }
/*		 */ 
/*		 */	 private void a(int paramInt1, int paramInt2, Object paramObject, int paramInt3)
/*		 */	 {
/* 221 */		 IntHashMapEntry localIntHashMapEntry = this.a[paramInt3];
/* 222 */		 this.a[paramInt3] = new IntHashMapEntry(paramInt1, paramInt2, paramObject, localIntHashMapEntry);
/* 223 */		 if (this.b++ >= this.c) h(2 * this.a.length);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.IntHashMap
 * JD-Core Version:		0.6.0
 */