package net.minecraft.server;

import java.util.List;

public class PathfinderGoalFollowParent extends PathfinderGoal
{
	EntityAnimal a;
	EntityAnimal b;
	float c;
	private int d;

	public PathfinderGoalFollowParent(EntityAnimal paramEntityAnimal, float paramFloat)
	{
		this.a = paramEntityAnimal;
		this.c = paramFloat;
	}

	public boolean a()
	{
		if (this.a.getAge() >= 0) return false;

		List localList = this.a.world.a(this.a.getClass(), this.a.boundingBox.grow(8.0D, 4.0D, 8.0D));

		Object localObject = null;
		double d1 = 1.7976931348623157E+308D;
		for (EntityAnimal localEntityAnimal : localList)
			if (localEntityAnimal.getAge() >= 0) {
				double d2 = this.a.e(localEntityAnimal);
				if (d2 <= d1) {
					d1 = d2;
					localObject = localEntityAnimal;
				}
			}
		if (localObject == null) return false;
		if (d1 < 9.0D) return false;
		this.b = localObject;
		return true;
	}

	public boolean b()
	{
		if (!this.b.isAlive()) return false;
		double d1 = this.a.e(this.b);
		return (d1 >= 9.0D) && (d1 <= 256.0D);
	}

	public void e()
	{
		this.d = 0;
	}

	public void c()
	{
		this.b = null;
	}

	public void d()
	{
		if (--this.d > 0) return;
		this.d = 10;
		this.a.getNavigation().a(this.b, this.c);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalFollowParent
 * JD-Core Version:		0.6.0
 */