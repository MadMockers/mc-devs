package net.minecraft.server;

public class PathfinderGoalDefendVillage extends PathfinderGoalTarget
{
	EntityIronGolem a;
	EntityLiving b;

	public PathfinderGoalDefendVillage(EntityIronGolem paramEntityIronGolem)
	{
		super(paramEntityIronGolem, 16.0F, false, true);
		this.a = paramEntityIronGolem;
		a(1);
	}

	public boolean a()
	{
		Village localVillage = this.a.n();
		if (localVillage == null) return false;
		this.b = localVillage.b(this.a);
		return a(this.b, false);
	}

	public void e()
	{
		this.a.b(this.b);
		super.e();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalDefendVillage
 * JD-Core Version:		0.6.0
 */