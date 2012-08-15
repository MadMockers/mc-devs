package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PathfinderGoalPlay extends PathfinderGoal
{
	private EntityVillager a;
	private EntityLiving b;
	private float c;
	private int d;

	public PathfinderGoalPlay(EntityVillager paramEntityVillager, float paramFloat)
	{
		this.a = paramEntityVillager;
		this.c = paramFloat;
		a(1);
	}

	public boolean a()
	{
		if (this.a.getAge() >= 0) return false;
		if (this.a.au().nextInt(400) != 0) return false;

		List localList = this.a.world.a(EntityVillager.class, this.a.boundingBox.grow(6.0D, 3.0D, 6.0D));
		double d1 = 1.7976931348623157E+308D;
		for (Object localObject = localList.iterator(); ((Iterator)localObject).hasNext(); ) { EntityVillager localEntityVillager = (EntityVillager)((Iterator)localObject).next();
			if ((localEntityVillager == this.a) || 
				(localEntityVillager.p()) || 
				(localEntityVillager.getAge() >= 0)) continue;
			double d2 = localEntityVillager.e(this.a);
			if (d2 <= d1) {
				d1 = d2;
				this.b = localEntityVillager;
			}
		}
		if (this.b == null) {
			localObject = RandomPositionGenerator.a(this.a, 16, 3);
			if (localObject == null) return false;
		}
		return true;
	}

	public boolean b()
	{
		return this.d > 0;
	}

	public void e()
	{
		if (this.b != null) this.a.f(true);
		this.d = 1000;
	}

	public void c()
	{
		this.a.f(false);
		this.b = null;
	}

	public void d()
	{
		this.d -= 1;
		if (this.b != null) {
			if (this.a.e(this.b) > 4.0D) this.a.getNavigation().a(this.b, this.c);
		}
		else if (this.a.getNavigation().f()) {
			Vec3D localVec3D = RandomPositionGenerator.a(this.a, 16, 3);
			if (localVec3D == null) return;
			this.a.getNavigation().a(localVec3D.a, localVec3D.b, localVec3D.c, this.c);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalPlay
 * JD-Core Version:		0.6.0
 */