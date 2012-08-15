package net.minecraft.server;

public class PathfinderGoalOwnerHurtByTarget extends PathfinderGoalTarget
{
	EntityTameableAnimal a;
	EntityLiving b;

	public PathfinderGoalOwnerHurtByTarget(EntityTameableAnimal paramEntityTameableAnimal)
	{
		super(paramEntityTameableAnimal, 32.0F, false);
		this.a = paramEntityTameableAnimal;
		a(1);
	}

	public boolean a()
	{
		if (!this.a.isTamed()) return false;
		EntityLiving localEntityLiving = this.a.getOwner();
		if (localEntityLiving == null) return false;
		this.b = localEntityLiving.av();
		return a(this.b, false);
	}

	public void e()
	{
		this.d.b(this.b);
		super.e();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalOwnerHurtByTarget
 * JD-Core Version:		0.6.0
 */