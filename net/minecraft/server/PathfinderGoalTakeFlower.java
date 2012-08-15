package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class PathfinderGoalTakeFlower extends PathfinderGoal
{
	private EntityVillager a;
	private EntityIronGolem b;
	private int c;
	private boolean d = false;

	public PathfinderGoalTakeFlower(EntityVillager paramEntityVillager) {
		this.a = paramEntityVillager;
		a(3);
	}

	public boolean a()
	{
		if (this.a.getAge() >= 0) return false;
		if (!this.a.world.r()) return false;

		List localList = this.a.world.a(EntityIronGolem.class, this.a.boundingBox.grow(6.0D, 2.0D, 6.0D));
		if (localList.isEmpty()) return false;

		for (EntityIronGolem localEntityIronGolem : localList) {
			if (localEntityIronGolem.p() > 0) {
				this.b = localEntityIronGolem;
				break;
			}
		}
		return this.b != null;
	}

	public boolean b()
	{
		return this.b.p() > 0;
	}

	public void e()
	{
		this.c = this.a.au().nextInt(320);
		this.d = false;
		this.b.getNavigation().g();
	}

	public void c()
	{
		this.b = null;
		this.a.getNavigation().g();
	}

	public void d()
	{
		this.a.getControllerLook().a(this.b, 30.0F, 30.0F);
		if (this.b.p() == this.c) {
			this.a.getNavigation().a(this.b, 0.15F);
			this.d = true;
		}

		if ((this.d) && 
			(this.a.e(this.b) < 4.0D)) {
			this.b.e(false);
			this.a.getNavigation().g();
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalTakeFlower
 * JD-Core Version:		0.6.0
 */