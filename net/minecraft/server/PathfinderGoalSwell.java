package net.minecraft.server;

public class PathfinderGoalSwell extends PathfinderGoal
{
	EntityCreeper a;
	EntityLiving b;

	public PathfinderGoalSwell(EntityCreeper paramEntityCreeper)
	{
		this.a = paramEntityCreeper;
		a(1);
	}

	public boolean a()
	{
		EntityLiving localEntityLiving = this.a.az();
		return (this.a.p() > 0) || ((localEntityLiving != null) && (this.a.e(localEntityLiving) < 9.0D));
	}

	public void e()
	{
		this.a.getNavigation().g();
		this.b = this.a.az();
	}

	public void c()
	{
		this.b = null;
	}

	public void d()
	{
		if (this.b == null) {
			this.a.a(-1);
			return;
		}

		if (this.a.e(this.b) > 49.0D) {
			this.a.a(-1);
			return;
		}

		if (!this.a.at().canSee(this.b)) {
			this.a.a(-1);
			return;
		}

		this.a.a(1);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalSwell
 * JD-Core Version:		0.6.0
 */