/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.lang.management.ManagementFactory;
/*		 */ import java.lang.management.RuntimeMXBean;
/*		 */ import java.net.MalformedURLException;
/*		 */ import java.net.URL;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Timer;
/*		 */ import java.util.UUID;
/*		 */ 
/*		 */ public class MojangStatisticsGenerator
/*		 */ {
/*	16 */	 private Map a = new HashMap();
/*	17 */	 private final String b = UUID.randomUUID().toString();
/*		 */	 private final URL c;
/*		 */	 private final IMojangStatistics d;
/*	20 */	 private final Timer e = new Timer("Snooper Timer", true);
/*	21 */	 private final Object f = new Object();
/*	22 */	 private boolean g = false;
/*	23 */	 private int h = 0;
/*		 */ 
/*		 */	 public MojangStatisticsGenerator(String paramString, IMojangStatistics paramIMojangStatistics) {
/*		 */		 try {
/*	27 */			 this.c = new URL("http://snoop.minecraft.net/" + paramString + "?version=" + 1);
/*		 */		 } catch (MalformedURLException localMalformedURLException) {
/*	29 */			 throw new IllegalArgumentException();
/*		 */		 }
/*		 */ 
/*	32 */		 this.d = paramIMojangStatistics;
/*		 */	 }
/*		 */ 
/*		 */	 public void a() {
/*	36 */		 if (this.g) return;
/*	37 */		 this.g = true;
/*		 */ 
/*	39 */		 f();
/*		 */ 
/*	41 */		 this.e.schedule(new MojangStatisticsTask(this), 0L, 900000L);
/*		 */	 }
/*		 */ 
/*		 */	 private void f()
/*		 */	 {
/*	58 */		 g();
/*		 */ 
/*	60 */		 a("snooper_token", this.b);
/*	61 */		 a("os_name", System.getProperty("os.name"));
/*	62 */		 a("os_version", System.getProperty("os.version"));
/*	63 */		 a("os_architecture", System.getProperty("os.arch"));
/*	64 */		 a("java_version", System.getProperty("java.version"));
/*	65 */		 a("version", "1.3.1");
/*		 */ 
/*	67 */		 this.d.b(this);
/*		 */	 }
/*		 */ 
/*		 */	 private void g() {
/*	71 */		 RuntimeMXBean localRuntimeMXBean = ManagementFactory.getRuntimeMXBean();
/*	72 */		 List localList = localRuntimeMXBean.getInputArguments();
/*	73 */		 int i = 0;
/*		 */ 
/*	75 */		 for (String str : localList) {
/*	76 */			 if (str.startsWith("-X")) {
/*	77 */				 a("jvm_arg[" + i++ + "]", str);
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	81 */		 a("jvm_args", Integer.valueOf(i));
/*		 */	 }
/*		 */ 
/*		 */	 public void b() {
/*	85 */		 a("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
/*	86 */		 a("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
/*	87 */		 a("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
/*	88 */		 a("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
/*		 */ 
/*	90 */		 this.d.a(this);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(String paramString, Object paramObject) {
/*	94 */		 synchronized (this.f) {
/*	95 */			 this.a.put(paramString, paramObject);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d()
/*		 */	 {
/* 114 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public void e() {
/* 118 */		 this.e.cancel();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MojangStatisticsGenerator
 * JD-Core Version:		0.6.0
 */