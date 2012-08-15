package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalFloat extends PathfinderGoal
{
	private EntityLiving a;

	public PathfinderGoalFloat(EntityLiving paramEntityLiving)
	{
		this.a = paramEntityLiving;
		a(4);
		paramEntityLiving.getNavigation().e(true);
	}

	public boolean a()
	{
		return (this.a.H()) || (this.a.J());
	}

	public void d()
	{
		if (this.a.au().nextFloat() < 0.8F) this.a.getControllerJump().a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalFloat
 * JD-Core Version:		0.6.0
 */