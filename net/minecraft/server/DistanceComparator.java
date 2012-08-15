package net.minecraft.server;

import java.util.Comparator;

public class DistanceComparator
	implements Comparator
{
	private Entity b;

	public DistanceComparator(PathfinderGoalNearestAttackableTarget paramPathfinderGoalNearestAttackableTarget, Entity paramEntity)
	{
		this.b = paramEntity;
	}

	public int a(Entity paramEntity1, Entity paramEntity2) {
		double d1 = this.b.e(paramEntity1);
		double d2 = this.b.e(paramEntity2);
		if (d1 < d2) return -1;
		if (d1 > d2) return 1;
		return 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.DistanceComparator
 * JD-Core Version:		0.6.0
 */