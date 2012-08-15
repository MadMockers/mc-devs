package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatisticList
{
	protected static Map a = new HashMap();

	public static List b = new ArrayList();
	public static List c = new ArrayList();
	public static List d = new ArrayList();
	public static List e = new ArrayList();

	public static Statistic f = new CounterStatistic(1000, "stat.startGame").h().g();
	public static Statistic g = new CounterStatistic(1001, "stat.createWorld").h().g();
	public static Statistic h = new CounterStatistic(1002, "stat.loadWorld").h().g();
	public static Statistic i = new CounterStatistic(1003, "stat.joinMultiplayer").h().g();
	public static Statistic j = new CounterStatistic(1004, "stat.leaveGame").h().g();

	public static Statistic k = new CounterStatistic(1100, "stat.playOneMinute", Statistic.i).h().g();

	public static Statistic l = new CounterStatistic(2000, "stat.walkOneCm", Statistic.j).h().g();
	public static Statistic m = new CounterStatistic(2001, "stat.swimOneCm", Statistic.j).h().g();
	public static Statistic n = new CounterStatistic(2002, "stat.fallOneCm", Statistic.j).h().g();
	public static Statistic o = new CounterStatistic(2003, "stat.climbOneCm", Statistic.j).h().g();
	public static Statistic p = new CounterStatistic(2004, "stat.flyOneCm", Statistic.j).h().g();
	public static Statistic q = new CounterStatistic(2005, "stat.diveOneCm", Statistic.j).h().g();
	public static Statistic r = new CounterStatistic(2006, "stat.minecartOneCm", Statistic.j).h().g();
	public static Statistic s = new CounterStatistic(2007, "stat.boatOneCm", Statistic.j).h().g();
	public static Statistic t = new CounterStatistic(2008, "stat.pigOneCm", Statistic.j).h().g();

	public static Statistic u = new CounterStatistic(2010, "stat.jump").h().g();
	public static Statistic v = new CounterStatistic(2011, "stat.drop").h().g();

	public static Statistic w = new CounterStatistic(2020, "stat.damageDealt").g();
	public static Statistic x = new CounterStatistic(2021, "stat.damageTaken").g();
	public static Statistic y = new CounterStatistic(2022, "stat.deaths").g();
	public static Statistic z = new CounterStatistic(2023, "stat.mobKills").g();
	public static Statistic A = new CounterStatistic(2024, "stat.playerKills").g();
	public static Statistic B = new CounterStatistic(2025, "stat.fishCaught").g();

	public static Statistic[] C = a("stat.mineBlock", 16777216);
	public static Statistic[] D;
	public static Statistic[] E;
	public static Statistic[] F;
	private static boolean G;
	private static boolean H;

	public static void a()
	{
	}

	public static void b()
	{
		E = a(E, "stat.useItem", 16908288, 0, 256);
		F = b(F, "stat.breakItem", 16973824, 0, 256);

		G = true;
		d();
	}

	public static void c()
	{
		E = a(E, "stat.useItem", 16908288, 256, 32000);
		F = b(F, "stat.breakItem", 16973824, 256, 32000);

		H = true;
		d();
	}

	public static void d() {
		if ((!G) || (!H))
		{
			return;
		}

		HashSet localHashSet = new HashSet();

		for (Iterator localIterator = CraftingManager.getInstance().getRecipes().iterator(); localIterator.hasNext(); ) { localObject = (IRecipe)localIterator.next();
			localHashSet.add(Integer.valueOf(((IRecipe)localObject).b().id));
		}
		Object localObject;
		for (localIterator = RecipesFurnace.getInstance().getRecipes().values().iterator(); localIterator.hasNext(); ) { localObject = (ItemStack)localIterator.next();
			localHashSet.add(Integer.valueOf(((ItemStack)localObject).id));
		}

		D = new Statistic[32000];
		for (localIterator = localHashSet.iterator(); localIterator.hasNext(); ) { localObject = (Integer)localIterator.next();
			if (Item.byId[localObject.intValue()] != null) {
				String str = LocaleI18n.get("stat.craftItem", new Object[] { Item.byId[localObject.intValue()].s() });
				D[localObject.intValue()] = new CraftingStatistic(16842752 + ((Integer)localObject).intValue(), str, ((Integer)localObject).intValue()).g();
			}
		}

		a(D);
	}

	private static Statistic[] a(String paramString, int paramInt) {
		Statistic[] arrayOfStatistic = new Statistic[256];
		for (int i1 = 0; i1 < 256; i1++) {
			if ((Block.byId[i1] != null) && (Block.byId[i1].u())) {
				String str = LocaleI18n.get(paramString, new Object[] { Block.byId[i1].getName() });
				arrayOfStatistic[i1] = new CraftingStatistic(paramInt + i1, str, i1).g();
				e.add((CraftingStatistic)arrayOfStatistic[i1]);
			}
		}

		a(arrayOfStatistic);
		return arrayOfStatistic;
	}

	private static Statistic[] a(Statistic[] paramArrayOfStatistic, String paramString, int paramInt1, int paramInt2, int paramInt3) {
		if (paramArrayOfStatistic == null) {
			paramArrayOfStatistic = new Statistic[32000];
		}
		for (int i1 = paramInt2; i1 < paramInt3; i1++) {
			if (Item.byId[i1] != null) {
				String str = LocaleI18n.get(paramString, new Object[] { Item.byId[i1].s() });
				paramArrayOfStatistic[i1] = new CraftingStatistic(paramInt1 + i1, str, i1).g();

				if (i1 >= 256) {
					d.add((CraftingStatistic)paramArrayOfStatistic[i1]);
				}
			}
		}

		a(paramArrayOfStatistic);
		return paramArrayOfStatistic;
	}

	private static Statistic[] b(Statistic[] paramArrayOfStatistic, String paramString, int paramInt1, int paramInt2, int paramInt3) {
		if (paramArrayOfStatistic == null) {
			paramArrayOfStatistic = new Statistic[32000];
		}
		for (int i1 = paramInt2; i1 < paramInt3; i1++) {
			if ((Item.byId[i1] != null) && (Item.byId[i1].m())) {
				String str = LocaleI18n.get(paramString, new Object[] { Item.byId[i1].s() });
				paramArrayOfStatistic[i1] = new CraftingStatistic(paramInt1 + i1, str, i1).g();
			}
		}

		a(paramArrayOfStatistic);
		return paramArrayOfStatistic;
	}

	private static void a(Statistic[] paramArrayOfStatistic) {
		a(paramArrayOfStatistic, Block.STATIONARY_WATER.id, Block.WATER.id);
		a(paramArrayOfStatistic, Block.STATIONARY_LAVA.id, Block.STATIONARY_LAVA.id);

		a(paramArrayOfStatistic, Block.JACK_O_LANTERN.id, Block.PUMPKIN.id);
		a(paramArrayOfStatistic, Block.BURNING_FURNACE.id, Block.FURNACE.id);
		a(paramArrayOfStatistic, Block.GLOWING_REDSTONE_ORE.id, Block.REDSTONE_ORE.id);

		a(paramArrayOfStatistic, Block.DIODE_ON.id, Block.DIODE_OFF.id);
		a(paramArrayOfStatistic, Block.REDSTONE_TORCH_ON.id, Block.REDSTONE_TORCH_OFF.id);

		a(paramArrayOfStatistic, Block.RED_MUSHROOM.id, Block.BROWN_MUSHROOM.id);
		a(paramArrayOfStatistic, Block.DOUBLE_STEP.id, Block.STEP.id);
		a(paramArrayOfStatistic, Block.WOOD_DOUBLE_STEP.id, Block.WOOD_STEP.id);

		a(paramArrayOfStatistic, Block.GRASS.id, Block.DIRT.id);
		a(paramArrayOfStatistic, Block.SOIL.id, Block.DIRT.id);
	}

	private static void a(Statistic[] paramArrayOfStatistic, int paramInt1, int paramInt2)
	{
		if ((paramArrayOfStatistic[paramInt1] != null) && (paramArrayOfStatistic[paramInt2] == null))
		{
			paramArrayOfStatistic[paramInt2] = paramArrayOfStatistic[paramInt1];
			return;
		}

		b.remove(paramArrayOfStatistic[paramInt1]);
		e.remove(paramArrayOfStatistic[paramInt1]);
		c.remove(paramArrayOfStatistic[paramInt1]);
		paramArrayOfStatistic[paramInt1] = paramArrayOfStatistic[paramInt2];
	}

	static
	{
		AchievementList.a();

		G = false;

		H = false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.StatisticList
 * JD-Core Version:		0.6.0
 */