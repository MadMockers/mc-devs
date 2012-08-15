package net.minecraft.server;

public class PathfinderGoalSit extends PathfinderGoal
{
	private EntityTameableAnimal a;
	private boolean b = false;

	public PathfinderGoalSit(EntityTameableAnimal paramEntityTameableAnimal) {
		this.a = paramEntityTameableAnimal;
		a(5);
	}

	public boolean a()
	{
		if (!this.a.isTamed()) return false;
		if (this.a.H()) return false;
		if (!this.a.onGround) return false;

		EntityLiving localEntityLiving = this.a.getOwner();
		if (localEntityLiving == null) return true;

		if ((this.a.e(localEntityLiving) < 144.0D) && (localEntityLiving.av() != null)) {
			return false;
		}
		return this.b;
	}

	public void e()
	{
		this.a.getNavigation().g();
		this.a.setSitting(true);
	}

	public void c()
	{
		this.a.setSitting(false);
	}

	public void a(boolean paramBoolean) {
		this.b = paramBoolean;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalSit
 * JD-Core Version:		0.6.0
 */