/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ class IntHashMapEntry
/*		 */ {
/*		 */	 final int a;
/*		 */	 Object b;
/*		 */	 IntHashMapEntry c;
/*		 */	 final int d;
/*		 */ 
/*		 */	 IntHashMapEntry(int paramInt1, int paramInt2, Object paramObject, IntHashMapEntry paramIntHashMapEntry)
/*		 */	 {
/* 183 */		 this.b = paramObject;
/* 184 */		 this.c = paramIntHashMapEntry;
/* 185 */		 this.a = paramInt2;
/* 186 */		 this.d = paramInt1;
/*		 */	 }
/*		 */ 
/*		 */	 public final int a() {
/* 190 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public final Object b() {
/* 194 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public final boolean equals(Object paramObject)
/*		 */	 {
/* 199 */		 if (!(paramObject instanceof IntHashMapEntry)) return false;
/* 200 */		 IntHashMapEntry localIntHashMapEntry = (IntHashMapEntry)paramObject;
/* 201 */		 Integer localInteger1 = Integer.valueOf(a());
/* 202 */		 Integer localInteger2 = Integer.valueOf(localIntHashMapEntry.a());
/* 203 */		 if ((localInteger1 == localInteger2) || ((localInteger1 != null) && (localInteger1.equals(localInteger2)))) {
/* 204 */			 Object localObject1 = b();
/* 205 */			 Object localObject2 = localIntHashMapEntry.b();
/* 206 */			 if ((localObject1 == localObject2) || ((localObject1 != null) && (localObject1.equals(localObject2)))) return true;
/*		 */		 }
/* 208 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public final int hashCode() {
/* 212 */		 return IntHashMap.f(this.a);
/*		 */	 }
/*		 */ 
/*		 */	 public final String toString() {
/* 216 */		 return a() + "=" + b();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.IntHashMapEntry
 * JD-Core Version:		0.6.0
 */