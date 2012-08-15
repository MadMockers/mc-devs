/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class MethodProfiler
/*		 */ {
/*		 */	 private final List b;
/*		 */	 private final List c;
/*		 */	 public boolean a;
/*		 */	 private String d;
/*		 */	 private final Map e;
/*		 */ 
/*		 */	 public MethodProfiler()
/*		 */	 {
/*	 6 */		 this.b = new ArrayList();
/*	 7 */		 this.c = new ArrayList();
/*	 8 */		 this.a = false;
/*	 9 */		 this.d = "";
/*	10 */		 this.e = new HashMap();
/*		 */	 }
/*		 */ 
/*		 */	 public void a()
/*		 */	 {
/*	35 */		 this.e.clear();
/*	36 */		 this.d = "";
/*	37 */		 this.b.clear();
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString) {
/*	41 */		 if (!this.a) return;
/*	42 */		 if (this.d.length() > 0) this.d += ".";
/*	43 */		 this.d += paramString;
/*	44 */		 this.b.add(this.d);
/*		 */ 
/*	46 */		 this.c.add(Long.valueOf(System.nanoTime()));
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	50 */		 if (!this.a) return;
/*		 */ 
/*	52 */		 long l1 = System.nanoTime();
/*	53 */		 long l2 = ((Long)this.c.remove(this.c.size() - 1)).longValue();
/*	54 */		 this.b.remove(this.b.size() - 1);
/*	55 */		 long l3 = l1 - l2;
/*		 */ 
/*	57 */		 if (this.e.containsKey(this.d))
/*	58 */			 this.e.put(this.d, Long.valueOf(((Long)this.e.get(this.d)).longValue() + l3));
/*		 */		 else {
/*	60 */			 this.e.put(this.d, Long.valueOf(l3));
/*		 */		 }
/*		 */ 
/*	63 */		 if (l3 > 100000000L) {
/*	64 */			 System.out.println("Something's taking too long! '" + this.d + "' took aprox " + l3 / 1000000.0D + " ms");
/*		 */		 }
/*		 */ 
/*	67 */		 this.d = (!this.b.isEmpty() ? (String)this.b.get(this.b.size() - 1) : "");
/*		 */	 }
/*		 */ 
/*		 */	 public List b(String paramString) {
/*	71 */		 if (!this.a) return null;
/*		 */ 
/*	73 */		 String str1 = paramString;
/*	74 */		 long l1 = this.e.containsKey("root") ? ((Long)this.e.get("root")).longValue() : 0L;
/*	75 */		 long l2 = this.e.containsKey(paramString) ? ((Long)this.e.get(paramString)).longValue() : -1L;
/*		 */ 
/*	77 */		 ArrayList localArrayList = new ArrayList();
/*		 */ 
/*	79 */		 if (paramString.length() > 0) paramString = paramString + ".";
/*	80 */		 long l3 = 0L;
/*		 */ 
/*	82 */		 for (Iterator localIterator = this.e.keySet().iterator(); localIterator.hasNext(); ) { localObject = (String)localIterator.next();
/*	83 */			 if ((((String)localObject).length() > paramString.length()) && (((String)localObject).startsWith(paramString)) && (((String)localObject).indexOf(".", paramString.length() + 1) < 0)) {
/*	84 */				 l3 += ((Long)this.e.get(localObject)).longValue();
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	88 */		 float f = (float)l3;
/*	89 */		 if (l3 < l2) l3 = l2;
/*	90 */		 if (l1 < l3) l1 = l3;
/*		 */ 
/*	92 */		 for (Object localObject = this.e.keySet().iterator(); ((Iterator)localObject).hasNext(); ) { str2 = (String)((Iterator)localObject).next();
/*	93 */			 if ((str2.length() > paramString.length()) && (str2.startsWith(paramString)) && (str2.indexOf(".", paramString.length() + 1) < 0)) {
/*	94 */				 long l4 = ((Long)this.e.get(str2)).longValue();
/*	95 */				 double d1 = l4 * 100.0D / l3;
/*	96 */				 double d2 = l4 * 100.0D / l1;
/*	97 */				 String str3 = str2.substring(paramString.length());
/*	98 */				 localArrayList.add(new ProfilerInfo(str3, d1, d2));
/*		 */			 }
/*		 */		 }
/* 102 */		 String str2;
/* 102 */		 for (localObject = this.e.keySet().iterator(); ((Iterator)localObject).hasNext(); ) { str2 = (String)((Iterator)localObject).next();
/* 103 */			 this.e.put(str2, Long.valueOf(((Long)this.e.get(str2)).longValue() * 999L / 1000L));
/*		 */		 }
/*		 */ 
/* 106 */		 if ((float)l3 > f) {
/* 107 */			 localArrayList.add(new ProfilerInfo("unspecified", ((float)l3 - f) * 100.0D / l3, ((float)l3 - f) * 100.0D / l1));
/*		 */		 }
/* 109 */		 Collections.sort(localArrayList);
/* 110 */		 localArrayList.add(0, new ProfilerInfo(str1, 100.0D, l3 * 100.0D / l1));
/* 111 */		 return (List)localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(String paramString) {
/* 115 */		 b();
/* 116 */		 a(paramString);
/*		 */	 }
/*		 */ 
/*		 */	 public String c() {
/* 120 */		 return this.b.size() == 0 ? "[UNKNOWN]" : (String)this.b.get(this.b.size() - 1);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MethodProfiler
 * JD-Core Version:		0.6.0
 */