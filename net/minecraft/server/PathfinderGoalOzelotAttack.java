package net.minecraft.server;

public class PathfinderGoalOzelotAttack extends PathfinderGoal
{
	World a;
	EntityLiving b;
	EntityLiving c;
	int d = 0;

	public PathfinderGoalOzelotAttack(EntityLiving paramEntityLiving)
	{
		this.b = paramEntityLiving;
		this.a = paramEntityLiving.world;
		a(3);
	}

	public boolean a()
	{
		EntityLiving localEntityLiving = this.b.az();
		if (localEntityLiving == null) return false;
		this.c = localEntityLiving;
		return true;
	}

	public boolean b()
	{
		if (!this.c.isAlive()) return false;
		if (this.b.e(this.c) > 225.0D) return false;
		return (!this.b.getNavigation().f()) || (a());
	}

	public void c()
	{
		this.c = null;
		this.b.getNavigation().g();
	}

	public void d()
	{
		this.b.getControllerLook().a(this.c, 30.0F, 30.0F);

		double d1 = this.b.width * 2.0F * (this.b.width * 2.0F);
		double d2 = this.b.e(this.c.locX, this.c.boundingBox.b, this.c.locZ);

		float f = 0.23F;
		if ((d2 > d1) && (d2 < 16.0D)) f = 0.4F;
		else if (d2 < 225.0D) f = 0.18F;

		this.b.getNavigation().a(this.c, f);

		this.d = Math.max(this.d - 1, 0);

		if (d2 > d1) return;
		if (this.d > 0) return;
		this.d = 20;
		this.b.k(this.c);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalOzelotAttack
 * JD-Core Version:		0.6.0
 */