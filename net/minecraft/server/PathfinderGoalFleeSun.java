package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalFleeSun extends PathfinderGoal
{
	private EntityCreature a;
	private double b;
	private double c;
	private double d;
	private float e;
	private World f;

	public PathfinderGoalFleeSun(EntityCreature paramEntityCreature, float paramFloat)
	{
		this.a = paramEntityCreature;
		this.e = paramFloat;
		this.f = paramEntityCreature.world;
		a(1);
	}

	public boolean a()
	{
		if (!this.f.r()) return false;
		if (!this.a.isBurning()) return false;
		if (!this.f.j(MathHelper.floor(this.a.locX), (int)this.a.boundingBox.b, MathHelper.floor(this.a.locZ))) return false;

		Vec3D localVec3D = f();
		if (localVec3D == null) return false;
		this.b = localVec3D.a;
		this.c = localVec3D.b;
		this.d = localVec3D.c;
		return true;
	}

	public boolean b()
	{
		return !this.a.getNavigation().f();
	}

	public void e()
	{
		this.a.getNavigation().a(this.b, this.c, this.d, this.e);
	}

	private Vec3D f() {
		Random localRandom = this.a.au();
		for (int i = 0; i < 10; i++) {
			int j = MathHelper.floor(this.a.locX + localRandom.nextInt(20) - 10.0D);
			int k = MathHelper.floor(this.a.boundingBox.b + localRandom.nextInt(6) - 3.0D);
			int m = MathHelper.floor(this.a.locZ + localRandom.nextInt(20) - 10.0D);
			if ((!this.f.j(j, k, m)) && (this.a.a(j, k, m) < 0.0F)) return Vec3D.a().create(j, k, m);
		}
		return null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalFleeSun
 * JD-Core Version:		0.6.0
 */