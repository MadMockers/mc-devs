package net.minecraft.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Statistic
{
	public final int e;
	private final String a;
	public boolean f = false;
	public String g;
	private final Counter b;
	private static NumberFormat c = NumberFormat.getIntegerInstance(Locale.US);
	public static Counter h = new UnknownCounter();

	private static DecimalFormat d = new DecimalFormat("########0.00");
	public static Counter i = new TimeCounter();

	public static Counter j = new DistancesCounter();

	public Statistic(int paramInt, String paramString, Counter paramCounter)
	{
		this.e = paramInt;
		this.a = paramString;
		this.b = paramCounter;
	}

	public Statistic(int paramInt, String paramString) {
		this(paramInt, paramString, h);
	}

	public Statistic h() {
		this.f = true;
		return this;
	}

	public Statistic g() {
		if (StatisticList.a.containsKey(Integer.valueOf(this.e))) {
			throw new RuntimeException("Duplicate stat id: \"" + ((Statistic)StatisticList.a.get(Integer.valueOf(this.e))).a + "\" and \"" + this.a + "\" at id " + this.e);
		}
		StatisticList.b.add(this);
		StatisticList.a.put(Integer.valueOf(this.e), this);

		this.g = AchievementMap.a(this.e);

		return this;
	}

	public String toString()
	{
		return LocaleI18n.get(this.a);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Statistic
 * JD-Core Version:		0.6.0
 */