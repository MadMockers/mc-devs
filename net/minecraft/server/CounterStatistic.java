package net.minecraft.server;

import java.util.List;

public class CounterStatistic extends Statistic
{
	public CounterStatistic(int paramInt, String paramString, Counter paramCounter)
	{
		super(paramInt, paramString, paramCounter);
	}

	public CounterStatistic(int paramInt, String paramString) {
		super(paramInt, paramString);
	}

	public Statistic g()
	{
		super.g();

		StatisticList.c.add(this);

		return this;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.CounterStatistic
 * JD-Core Version:		0.6.0
 */