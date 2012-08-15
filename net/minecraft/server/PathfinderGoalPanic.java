package net.minecraft.server;

public class PathfinderGoalPanic extends PathfinderGoal
{
	private EntityCreature a;
	private float b;
	private double c;
	private double d;
	private double e;

	public PathfinderGoalPanic(EntityCreature paramEntityCreature, float paramFloat)
	{
		this.a = paramEntityCreature;
		this.b = paramFloat;
		a(1);
	}

	public boolean a()
	{
		if (this.a.av() == null) return false;
		Vec3D localVec3D = RandomPositionGenerator.a(this.a, 5, 4);
		if (localVec3D == null) return false;
		this.c = localVec3D.a;
		this.d = localVec3D.b;
		this.e = localVec3D.c;
		return true;
	}

	public void e()
	{
		this.a.getNavigation().a(this.c, this.d, this.e, this.b);
	}

	public boolean b()
	{
		return !this.a.getNavigation().f();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalPanic
 * JD-Core Version:		0.6.0
 */