package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalLeapAtTarget extends PathfinderGoal
{
	EntityLiving a;
	EntityLiving b;
	float c;

	public PathfinderGoalLeapAtTarget(EntityLiving paramEntityLiving, float paramFloat)
	{
		this.a = paramEntityLiving;
		this.c = paramFloat;
		a(5);
	}

	public boolean a()
	{
		this.b = this.a.az();
		if (this.b == null) return false;
		double d = this.a.e(this.b);
		if ((d < 4.0D) || (d > 16.0D)) return false;
		if (!this.a.onGround) return false;
		return this.a.au().nextInt(5) == 0;
	}

	public boolean b()
	{
		return !this.a.onGround;
	}

	public void e()
	{
		double d1 = this.b.locX - this.a.locX;
		double d2 = this.b.locZ - this.a.locZ;
		float f = MathHelper.sqrt(d1 * d1 + d2 * d2);
		this.a.motX += d1 / f * 0.5D * 0.800000011920929D + this.a.motX * 0.2000000029802322D;
		this.a.motZ += d2 / f * 0.5D * 0.800000011920929D + this.a.motZ * 0.2000000029802322D;
		this.a.motY = this.c;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalLeapAtTarget
 * JD-Core Version:		0.6.0
 */