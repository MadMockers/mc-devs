/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.text.DecimalFormat;
/*		 */ import java.text.NumberFormat;
/*		 */ import java.util.List;
/*		 */ import java.util.Locale;
/*		 */ import java.util.Map;
/*		 */ 
/*		 */ public class Statistic
/*		 */ {
/*		 */	 public final int e;
/*		 */	 private final String a;
/*	11 */	 public boolean f = false;
/*		 */	 public String g;
/*		 */	 private final Counter b;
/*	50 */	 private static NumberFormat c = NumberFormat.getIntegerInstance(Locale.US);
/*	51 */	 public static Counter h = new UnknownCounter();
/*		 */ 
/*	57 */	 private static DecimalFormat d = new DecimalFormat("########0.00");
/*	58 */	 public static Counter i = new TimeCounter();
/*		 */ 
/*	80 */	 public static Counter j = new DistancesCounter();
/*		 */ 
/*		 */	 public Statistic(int paramInt, String paramString, Counter paramCounter)
/*		 */	 {
/*	16 */		 this.e = paramInt;
/*	17 */		 this.a = paramString;
/*	18 */		 this.b = paramCounter;
/*		 */	 }
/*		 */ 
/*		 */	 public Statistic(int paramInt, String paramString) {
/*	22 */		 this(paramInt, paramString, h);
/*		 */	 }
/*		 */ 
/*		 */	 public Statistic h() {
/*	26 */		 this.f = true;
/*	27 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public Statistic g() {
/*	31 */		 if (StatisticList.a.containsKey(Integer.valueOf(this.e))) {
/*	32 */			 throw new RuntimeException("Duplicate stat id: \"" + ((Statistic)StatisticList.a.get(Integer.valueOf(this.e))).a + "\" and \"" + this.a + "\" at id " + this.e);
/*		 */		 }
/*	34 */		 StatisticList.b.add(this);
/*	35 */		 StatisticList.a.put(Integer.valueOf(this.e), this);
/*		 */ 
/*	37 */		 this.g = AchievementMap.a(this.e);
/*		 */ 
/*	39 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String toString()
/*		 */	 {
/* 100 */		 return LocaleI18n.get(this.a);
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Statistic
 * JD-Core Version:		0.6.0
 */