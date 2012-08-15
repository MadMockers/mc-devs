package net.minecraft.server;

public class PathfinderGoalMoveTowardsTarget extends PathfinderGoal
{
	private EntityCreature a;
	private EntityLiving b;
	private double c;
	private double d;
	private double e;
	private float f;
	private float g;

	public PathfinderGoalMoveTowardsTarget(EntityCreature paramEntityCreature, float paramFloat1, float paramFloat2)
	{
		this.a = paramEntityCreature;
		this.f = paramFloat1;
		this.g = paramFloat2;
		a(1);
	}

	public boolean a()
	{
		this.b = this.a.az();
		if (this.b == null) return false;
		if (this.b.e(this.a) > this.g * this.g) return false;
		Vec3D localVec3D = RandomPositionGenerator.a(this.a, 16, 7, Vec3D.a().create(this.b.locX, this.b.locY, this.b.locZ));
		if (localVec3D == null) return false;
		this.c = localVec3D.a;
		this.d = localVec3D.b;
		this.e = localVec3D.c;
		return true;
	}

	public boolean b()
	{
		return (!this.a.getNavigation().f()) && (this.b.isAlive()) && (this.b.e(this.a) < this.g * this.g);
	}

	public void c()
	{
		this.b = null;
	}

	public void e()
	{
		this.a.getNavigation().a(this.c, this.d, this.e, this.f);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalMoveTowardsTarget
 * JD-Core Version:		0.6.0
 */