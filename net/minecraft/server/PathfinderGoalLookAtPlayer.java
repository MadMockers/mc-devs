package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalLookAtPlayer extends PathfinderGoal
{
	private EntityLiving b;
	protected Entity a;
	private float c;
	private int d;
	private float e;
	private Class f;

	public PathfinderGoalLookAtPlayer(EntityLiving paramEntityLiving, Class paramClass, float paramFloat)
	{
		this.b = paramEntityLiving;
		this.f = paramClass;
		this.c = paramFloat;
		this.e = 0.02F;
		a(2);
	}

	public PathfinderGoalLookAtPlayer(EntityLiving paramEntityLiving, Class paramClass, float paramFloat1, float paramFloat2) {
		this.b = paramEntityLiving;
		this.f = paramClass;
		this.c = paramFloat1;
		this.e = paramFloat2;
		a(2);
	}

	public boolean a()
	{
		if (this.b.au().nextFloat() >= this.e) return false;
		if (this.f == EntityHuman.class) this.a = this.b.world.findNearbyPlayer(this.b, this.c); else
			this.a = this.b.world.a(this.f, this.b.boundingBox.grow(this.c, 3.0D, this.c), this.b);
		return this.a != null;
	}

	public boolean b()
	{
		if (!this.a.isAlive()) return false;
		if (this.b.e(this.a) > this.c * this.c) return false;
		return this.d > 0;
	}

	public void e()
	{
		this.d = (40 + this.b.au().nextInt(40));
	}

	public void c()
	{
		this.a = null;
	}

	public void d()
	{
		this.b.getControllerLook().a(this.a.locX, this.a.locY + this.a.getHeadHeight(), this.a.locZ, 10.0F, this.b.bf());
		this.d -= 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalLookAtPlayer
 * JD-Core Version:		0.6.0
 */