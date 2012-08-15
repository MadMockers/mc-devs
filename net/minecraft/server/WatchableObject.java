/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class WatchableObject
/*		 */ {
/*		 */	 private final int a;
/*		 */	 private final int b;
/*		 */	 private Object c;
/*		 */	 private boolean d;
/*		 */ 
/*		 */	 public WatchableObject(int paramInt1, int paramInt2, Object paramObject)
/*		 */	 {
/* 315 */		 this.b = paramInt2;
/* 316 */		 this.c = paramObject;
/* 317 */		 this.a = paramInt1;
/* 318 */		 this.d = true;
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/* 322 */		 return this.b;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(Object paramObject) {
/* 326 */		 this.c = paramObject;
/*		 */	 }
/*		 */ 
/*		 */	 public Object b() {
/* 330 */		 return this.c;
/*		 */	 }
/*		 */ 
/*		 */	 public int c() {
/* 334 */		 return this.a;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/* 338 */		 return this.d;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(boolean paramBoolean) {
/* 342 */		 this.d = paramBoolean;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WatchableObject
 * JD-Core Version:		0.6.0
 */