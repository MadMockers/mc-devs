package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalRandomStroll extends PathfinderGoal
{
	private EntityCreature a;
	private double b;
	private double c;
	private double d;
	private float e;

	public PathfinderGoalRandomStroll(EntityCreature paramEntityCreature, float paramFloat)
	{
		this.a = paramEntityCreature;
		this.e = paramFloat;
		a(1);
	}

	public boolean a()
	{
		if (this.a.ax() >= 100) return false;
		if (this.a.au().nextInt(120) != 0) return false;

		Vec3D localVec3D = RandomPositionGenerator.a(this.a, 10, 7);
		if (localVec3D == null) return false;
		this.b = localVec3D.a;
		this.c = localVec3D.b;
		this.d = localVec3D.c;
		return true;
	}

	public boolean b()
	{
		return !this.a.getNavigation().f();
	}

	public void e()
	{
		this.a.getNavigation().a(this.b, this.c, this.d, this.e);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalRandomStroll
 * JD-Core Version:		0.6.0
 */