package net.minecraft.server;

public class PathfinderGoalRestrictOpenDoor extends PathfinderGoal
{
	private EntityCreature a;
	private VillageDoor b;

	public PathfinderGoalRestrictOpenDoor(EntityCreature paramEntityCreature)
	{
		this.a = paramEntityCreature;
	}

	public boolean a()
	{
		if (this.a.world.r()) return false;
		Village localVillage = this.a.world.villages.getClosestVillage(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ), 16);
		if (localVillage == null) return false;
		this.b = localVillage.b(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ));
		if (this.b == null) return false;
		return this.b.c(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ)) < 2.25D;
	}

	public boolean b()
	{
		if (this.a.world.r()) return false;
		return (!this.b.g) && (this.b.a(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locZ)));
	}

	public void e()
	{
		this.a.getNavigation().b(false);
		this.a.getNavigation().c(false);
	}

	public void c()
	{
		this.a.getNavigation().b(true);
		this.a.getNavigation().c(true);
		this.b = null;
	}

	public void d()
	{
		this.b.e();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalRestrictOpenDoor
 * JD-Core Version:		0.6.0
 */