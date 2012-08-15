package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalMakeLove extends PathfinderGoal
{
	private EntityVillager b;
	private EntityVillager c;
	private World d;
	private int e = 0;
	Village a;

	public PathfinderGoalMakeLove(EntityVillager paramEntityVillager)
	{
		this.b = paramEntityVillager;
		this.d = paramEntityVillager.world;
		a(3);
	}

	public boolean a()
	{
		if (this.b.getAge() != 0) return false;
		if (this.b.au().nextInt(500) != 0) return false;

		this.a = this.d.villages.getClosestVillage(MathHelper.floor(this.b.locX), MathHelper.floor(this.b.locY), MathHelper.floor(this.b.locZ), 0);
		if (this.a == null) return false;
		if (!f()) return false;

		Entity localEntity = this.d.a(EntityVillager.class, this.b.boundingBox.grow(8.0D, 3.0D, 8.0D), this.b);
		if (localEntity == null) return false;

		this.c = ((EntityVillager)localEntity);
		return this.c.getAge() == 0;
	}

	public void e()
	{
		this.e = 300;
		this.b.e(true);
	}

	public void c()
	{
		this.a = null;
		this.c = null;
		this.b.e(false);
	}

	public boolean b()
	{
		return (this.e >= 0) && (f()) && (this.b.getAge() == 0);
	}

	public void d()
	{
		this.e -= 1;
		this.b.getControllerLook().a(this.c, 10.0F, 30.0F);

		if (this.b.e(this.c) > 2.25D) {
			this.b.getNavigation().a(this.c, 0.25F);
		}
		else if ((this.e == 0) && (this.c.o())) i();

		if (this.b.au().nextInt(35) == 0) this.d.broadcastEntityEffect(this.b, 12);
	}

	private boolean f()
	{
		int i = (int)(this.a.getDoorCount() * 0.35D);
		return this.a.getPopulationCount() < i;
	}

	private void i() {
		EntityVillager localEntityVillager = new EntityVillager(this.d);
		this.c.setAge(6000);
		this.b.setAge(6000);
		localEntityVillager.setAge(-24000);
		localEntityVillager.setProfession(this.b.au().nextInt(5));
		localEntityVillager.setPositionRotation(this.b.locX, this.b.locY, this.b.locZ, 0.0F, 0.0F);
		this.d.addEntity(localEntityVillager);
		this.d.broadcastEntityEffect(localEntityVillager, 12);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalMakeLove
 * JD-Core Version:		0.6.0
 */