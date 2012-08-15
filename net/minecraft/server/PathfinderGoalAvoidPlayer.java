package net.minecraft.server;

import java.util.List;

public class PathfinderGoalAvoidPlayer extends PathfinderGoal
{
	private EntityCreature a;
	private float b;
	private float c;
	private Entity d;
	private float e;
	private PathEntity f;
	private Navigation g;
	private Class h;

	public PathfinderGoalAvoidPlayer(EntityCreature paramEntityCreature, Class paramClass, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		this.a = paramEntityCreature;
		this.h = paramClass;
		this.e = paramFloat1;
		this.b = paramFloat2;
		this.c = paramFloat3;
		this.g = paramEntityCreature.getNavigation();
		a(1);
	}

	public boolean a()
	{
		if (this.h == EntityHuman.class) {
			if (((this.a instanceof EntityTameableAnimal)) && (((EntityTameableAnimal)this.a).isTamed())) return false;
			this.d = this.a.world.findNearbyPlayer(this.a, this.e);
			if (this.d == null) return false; 
		}
		else {
			localObject = this.a.world.a(this.h, this.a.boundingBox.grow(this.e, 3.0D, this.e));
			if (((List)localObject).isEmpty()) return false;
			this.d = ((Entity)((List)localObject).get(0));
		}

		if (!this.a.at().canSee(this.d)) return false;

		Object localObject = RandomPositionGenerator.b(this.a, 16, 7, Vec3D.a().create(this.d.locX, this.d.locY, this.d.locZ));
		if (localObject == null) return false;
		if (this.d.e(((Vec3D)localObject).a, ((Vec3D)localObject).b, ((Vec3D)localObject).c) < this.d.e(this.a)) return false;
		this.f = this.g.a(((Vec3D)localObject).a, ((Vec3D)localObject).b, ((Vec3D)localObject).c);
		if (this.f == null) return false;
		return this.f.b((Vec3D)localObject);
	}

	public boolean b()
	{
		return !this.g.f();
	}

	public void e()
	{
		this.g.a(this.f, this.b);
	}

	public void c()
	{
		this.d = null;
	}

	public void d()
	{
		if (this.a.e(this.d) < 49.0D) this.a.getNavigation().a(this.c); else
			this.a.getNavigation().a(this.b);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalAvoidPlayer
 * JD-Core Version:		0.6.0
 */