package net.minecraft.server;

public class PathfinderGoalRestrictSun extends PathfinderGoal
{
	private EntityCreature a;

	public PathfinderGoalRestrictSun(EntityCreature paramEntityCreature)
	{
		this.a = paramEntityCreature;
	}

	public boolean a()
	{
		return this.a.world.r();
	}

	public void e()
	{
		this.a.getNavigation().d(true);
	}

	public void c()
	{
		this.a.getNavigation().d(false);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalRestrictSun
 * JD-Core Version:		0.6.0
 */