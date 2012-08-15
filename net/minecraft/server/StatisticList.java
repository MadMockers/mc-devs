/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collection;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.HashSet;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class StatisticList
/*		 */ {
/*	20 */	 protected static Map a = new HashMap();
/*		 */ 
/*	22 */	 public static List b = new ArrayList();
/*	23 */	 public static List c = new ArrayList();
/*	24 */	 public static List d = new ArrayList();
/*	25 */	 public static List e = new ArrayList();
/*		 */ 
/*	27 */	 public static Statistic f = new CounterStatistic(1000, "stat.startGame").h().g();
/*	28 */	 public static Statistic g = new CounterStatistic(1001, "stat.createWorld").h().g();
/*	29 */	 public static Statistic h = new CounterStatistic(1002, "stat.loadWorld").h().g();
/*	30 */	 public static Statistic i = new CounterStatistic(1003, "stat.joinMultiplayer").h().g();
/*	31 */	 public static Statistic j = new CounterStatistic(1004, "stat.leaveGame").h().g();
/*		 */ 
/*	33 */	 public static Statistic k = new CounterStatistic(1100, "stat.playOneMinute", Statistic.i).h().g();
/*		 */ 
/*	35 */	 public static Statistic l = new CounterStatistic(2000, "stat.walkOneCm", Statistic.j).h().g();
/*	36 */	 public static Statistic m = new CounterStatistic(2001, "stat.swimOneCm", Statistic.j).h().g();
/*	37 */	 public static Statistic n = new CounterStatistic(2002, "stat.fallOneCm", Statistic.j).h().g();
/*	38 */	 public static Statistic o = new CounterStatistic(2003, "stat.climbOneCm", Statistic.j).h().g();
/*	39 */	 public static Statistic p = new CounterStatistic(2004, "stat.flyOneCm", Statistic.j).h().g();
/*	40 */	 public static Statistic q = new CounterStatistic(2005, "stat.diveOneCm", Statistic.j).h().g();
/*	41 */	 public static Statistic r = new CounterStatistic(2006, "stat.minecartOneCm", Statistic.j).h().g();
/*	42 */	 public static Statistic s = new CounterStatistic(2007, "stat.boatOneCm", Statistic.j).h().g();
/*	43 */	 public static Statistic t = new CounterStatistic(2008, "stat.pigOneCm", Statistic.j).h().g();
/*		 */ 
/*	45 */	 public static Statistic u = new CounterStatistic(2010, "stat.jump").h().g();
/*	46 */	 public static Statistic v = new CounterStatistic(2011, "stat.drop").h().g();
/*		 */ 
/*	48 */	 public static Statistic w = new CounterStatistic(2020, "stat.damageDealt").g();
/*	49 */	 public static Statistic x = new CounterStatistic(2021, "stat.damageTaken").g();
/*	50 */	 public static Statistic y = new CounterStatistic(2022, "stat.deaths").g();
/*	51 */	 public static Statistic z = new CounterStatistic(2023, "stat.mobKills").g();
/*	52 */	 public static Statistic A = new CounterStatistic(2024, "stat.playerKills").g();
/*	53 */	 public static Statistic B = new CounterStatistic(2025, "stat.fishCaught").g();
/*		 */ 
/*	55 */	 public static Statistic[] C = a("stat.mineBlock", 16777216);
/*		 */	 public static Statistic[] D;
/*		 */	 public static Statistic[] E;
/*		 */	 public static Statistic[] F;
/*		 */	 private static boolean G;
/*		 */	 private static boolean H;
/*		 */ 
/*		 */	 public static void a()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public static void b()
/*		 */	 {
/*	75 */		 E = a(E, "stat.useItem", 16908288, 0, 256);
/*	76 */		 F = b(F, "stat.breakItem", 16973824, 0, 256);
/*		 */ 
/*	78 */		 G = true;
/*	79 */		 d();
/*		 */	 }
/*		 */ 
/*		 */	 public static void c()
/*		 */	 {
/*	85 */		 E = a(E, "stat.useItem", 16908288, 256, 32000);
/*	86 */		 F = b(F, "stat.breakItem", 16973824, 256, 32000);
/*		 */ 
/*	88 */		 H = true;
/*	89 */		 d();
/*		 */	 }
/*		 */ 
/*		 */	 public static void d() {
/*	93 */		 if ((!G) || (!H))
/*		 */		 {
/*	95 */			 return;
/*		 */		 }
/*		 */ 
/*	98 */		 HashSet localHashSet = new HashSet();
/*		 */ 
/* 100 */		 for (Iterator localIterator = CraftingManager.getInstance().getRecipes().iterator(); localIterator.hasNext(); ) { localObject = (IRecipe)localIterator.next();
/* 101 */			 localHashSet.add(Integer.valueOf(((IRecipe)localObject).b().id));
/*		 */		 }
/* 103 */		 Object localObject;
/* 103 */		 for (localIterator = RecipesFurnace.getInstance().getRecipes().values().iterator(); localIterator.hasNext(); ) { localObject = (ItemStack)localIterator.next();
/* 104 */			 localHashSet.add(Integer.valueOf(((ItemStack)localObject).id));
/*		 */		 }
/*		 */ 
/* 107 */		 D = new Statistic[32000];
/* 108 */		 for (localIterator = localHashSet.iterator(); localIterator.hasNext(); ) { localObject = (Integer)localIterator.next();
/* 109 */			 if (Item.byId[localObject.intValue()] != null) {
/* 110 */				 String str = LocaleI18n.get("stat.craftItem", new Object[] { Item.byId[localObject.intValue()].s() });
/* 111 */				 D[localObject.intValue()] = new CraftingStatistic(16842752 + ((Integer)localObject).intValue(), str, ((Integer)localObject).intValue()).g();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 115 */		 a(D);
/*		 */	 }
/*		 */ 
/*		 */	 private static Statistic[] a(String paramString, int paramInt) {
/* 119 */		 Statistic[] arrayOfStatistic = new Statistic[256];
/* 120 */		 for (int i1 = 0; i1 < 256; i1++) {
/* 121 */			 if ((Block.byId[i1] != null) && (Block.byId[i1].u())) {
/* 122 */				 String str = LocaleI18n.get(paramString, new Object[] { Block.byId[i1].getName() });
/* 123 */				 arrayOfStatistic[i1] = new CraftingStatistic(paramInt + i1, str, i1).g();
/* 124 */				 e.add((CraftingStatistic)arrayOfStatistic[i1]);
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 128 */		 a(arrayOfStatistic);
/* 129 */		 return arrayOfStatistic;
/*		 */	 }
/*		 */ 
/*		 */	 private static Statistic[] a(Statistic[] paramArrayOfStatistic, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/* 133 */		 if (paramArrayOfStatistic == null) {
/* 134 */			 paramArrayOfStatistic = new Statistic[32000];
/*		 */		 }
/* 136 */		 for (int i1 = paramInt2; i1 < paramInt3; i1++) {
/* 137 */			 if (Item.byId[i1] != null) {
/* 138 */				 String str = LocaleI18n.get(paramString, new Object[] { Item.byId[i1].s() });
/* 139 */				 paramArrayOfStatistic[i1] = new CraftingStatistic(paramInt1 + i1, str, i1).g();
/*		 */ 
/* 141 */				 if (i1 >= 256) {
/* 142 */					 d.add((CraftingStatistic)paramArrayOfStatistic[i1]);
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 147 */		 a(paramArrayOfStatistic);
/* 148 */		 return paramArrayOfStatistic;
/*		 */	 }
/*		 */ 
/*		 */	 private static Statistic[] b(Statistic[] paramArrayOfStatistic, String paramString, int paramInt1, int paramInt2, int paramInt3) {
/* 152 */		 if (paramArrayOfStatistic == null) {
/* 153 */			 paramArrayOfStatistic = new Statistic[32000];
/*		 */		 }
/* 155 */		 for (int i1 = paramInt2; i1 < paramInt3; i1++) {
/* 156 */			 if ((Item.byId[i1] != null) && (Item.byId[i1].m())) {
/* 157 */				 String str = LocaleI18n.get(paramString, new Object[] { Item.byId[i1].s() });
/* 158 */				 paramArrayOfStatistic[i1] = new CraftingStatistic(paramInt1 + i1, str, i1).g();
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 162 */		 a(paramArrayOfStatistic);
/* 163 */		 return paramArrayOfStatistic;
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(Statistic[] paramArrayOfStatistic) {
/* 167 */		 a(paramArrayOfStatistic, Block.STATIONARY_WATER.id, Block.WATER.id);
/* 168 */		 a(paramArrayOfStatistic, Block.STATIONARY_LAVA.id, Block.STATIONARY_LAVA.id);
/*		 */ 
/* 170 */		 a(paramArrayOfStatistic, Block.JACK_O_LANTERN.id, Block.PUMPKIN.id);
/* 171 */		 a(paramArrayOfStatistic, Block.BURNING_FURNACE.id, Block.FURNACE.id);
/* 172 */		 a(paramArrayOfStatistic, Block.GLOWING_REDSTONE_ORE.id, Block.REDSTONE_ORE.id);
/*		 */ 
/* 174 */		 a(paramArrayOfStatistic, Block.DIODE_ON.id, Block.DIODE_OFF.id);
/* 175 */		 a(paramArrayOfStatistic, Block.REDSTONE_TORCH_ON.id, Block.REDSTONE_TORCH_OFF.id);
/*		 */ 
/* 177 */		 a(paramArrayOfStatistic, Block.RED_MUSHROOM.id, Block.BROWN_MUSHROOM.id);
/* 178 */		 a(paramArrayOfStatistic, Block.DOUBLE_STEP.id, Block.STEP.id);
/* 179 */		 a(paramArrayOfStatistic, Block.WOOD_DOUBLE_STEP.id, Block.WOOD_STEP.id);
/*		 */ 
/* 181 */		 a(paramArrayOfStatistic, Block.GRASS.id, Block.DIRT.id);
/* 182 */		 a(paramArrayOfStatistic, Block.SOIL.id, Block.DIRT.id);
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(Statistic[] paramArrayOfStatistic, int paramInt1, int paramInt2)
/*		 */	 {
/* 187 */		 if ((paramArrayOfStatistic[paramInt1] != null) && (paramArrayOfStatistic[paramInt2] == null))
/*		 */		 {
/* 189 */			 paramArrayOfStatistic[paramInt2] = paramArrayOfStatistic[paramInt1];
/* 190 */			 return;
/*		 */		 }
/*		 */ 
/* 193 */		 b.remove(paramArrayOfStatistic[paramInt1]);
/* 194 */		 e.remove(paramArrayOfStatistic[paramInt1]);
/* 195 */		 c.remove(paramArrayOfStatistic[paramInt1]);
/* 196 */		 paramArrayOfStatistic[paramInt1] = paramArrayOfStatistic[paramInt2];
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/*	61 */		 AchievementList.a();
/*		 */ 
/*	71 */		 G = false;
/*		 */ 
/*	82 */		 H = false;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.StatisticList
 * JD-Core Version:		0.6.0
 */