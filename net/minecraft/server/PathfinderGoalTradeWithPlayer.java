package net.minecraft.server;

public class PathfinderGoalTradeWithPlayer extends PathfinderGoal
{
	private EntityVillager a;

	public PathfinderGoalTradeWithPlayer(EntityVillager paramEntityVillager)
	{
		this.a = paramEntityVillager;
		a(5);
	}

	public boolean a()
	{
		if (!this.a.isAlive()) return false;
		if (this.a.H()) return false;
		if (!this.a.onGround) return false;
		if (this.a.velocityChanged) return false;

		EntityHuman localEntityHuman = this.a.l_();
		if (localEntityHuman == null)
		{
			return false;
		}

		if (this.a.e(localEntityHuman) > 16.0D)
		{
			return false;
		}

		return (localEntityHuman.activeContainer instanceof Container);
	}

	public void e()
	{
		this.a.getNavigation().g();
	}

	public void c()
	{
		this.a.a_(null);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalTradeWithPlayer
 * JD-Core Version:		0.6.0
 */