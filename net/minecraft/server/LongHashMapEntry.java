/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ class LongHashMapEntry
/*		 */ {
/*		 */	 final long a;
/*		 */	 Object b;
/*		 */	 LongHashMapEntry c;
/*		 */	 final int d;
/*		 */ 
/*		 */	 LongHashMapEntry(int paramInt, long paramLong, Object paramObject, LongHashMapEntry paramLongHashMapEntry)
/*		 */	 {
/* 180 */		 this.b = paramObject;
/* 181 */		 this.c = paramLongHashMapEntry;
/* 182 */		 this.a = paramLong;
/* 183 */		 this.d = paramInt;
/*		 */	 }
/*		 */ 
/*		 */	 public final long a() {
/* 187 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public final Object b() {
/* 191 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public final boolean equals(Object paramObject)
/*		 */	 {
/* 196 */		 if (!(paramObject instanceof LongHashMapEntry)) return false;
/* 197 */		 LongHashMapEntry localLongHashMapEntry = (LongHashMapEntry)paramObject;
/* 198 */		 Long localLong1 = Long.valueOf(a());
/* 199 */		 Long localLong2 = Long.valueOf(localLongHashMapEntry.a());
/* 200 */		 if ((localLong1 == localLong2) || ((localLong1 != null) && (localLong1.equals(localLong2)))) {
/* 201 */			 Object localObject1 = b();
/* 202 */			 Object localObject2 = localLongHashMapEntry.b();
/* 203 */			 if ((localObject1 == localObject2) || ((localObject1 != null) && (localObject1.equals(localObject2)))) return true;
/*		 */		 }
/* 205 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public final int hashCode() {
/* 209 */		 return LongHashMap.f(this.a);
/*		 */	 }
/*		 */ 
/*		 */	 public final String toString() {
/* 213 */		 return a() + "=" + b();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.LongHashMapEntry
 * JD-Core Version:		0.6.0
 */