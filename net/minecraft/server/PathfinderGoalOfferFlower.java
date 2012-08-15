package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalOfferFlower extends PathfinderGoal
{
	private EntityIronGolem a;
	private EntityVillager b;
	private int c;

	public PathfinderGoalOfferFlower(EntityIronGolem paramEntityIronGolem)
	{
		this.a = paramEntityIronGolem;
		a(3);
	}

	public boolean a()
	{
		if (!this.a.world.r()) return false;
		if (this.a.au().nextInt(8000) != 0) return false;
		this.b = ((EntityVillager)this.a.world.a(EntityVillager.class, this.a.boundingBox.grow(6.0D, 2.0D, 6.0D), this.a));
		return this.b != null;
	}

	public boolean b()
	{
		return this.c > 0;
	}

	public void e()
	{
		this.c = 400;
		this.a.e(true);
	}

	public void c()
	{
		this.a.e(false);
		this.b = null;
	}

	public void d()
	{
		this.a.getControllerLook().a(this.b, 30.0F, 30.0F);
		this.c -= 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalOfferFlower
 * JD-Core Version:		0.6.0
 */