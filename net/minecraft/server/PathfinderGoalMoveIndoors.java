package net.minecraft.server;

import java.util.Random;

public class PathfinderGoalMoveIndoors extends PathfinderGoal
{
	private EntityCreature a;
	private VillageDoor b;
	private int c = -1; private int d = -1;

	public PathfinderGoalMoveIndoors(EntityCreature paramEntityCreature) {
		this.a = paramEntityCreature;
		a(1);
	}

	public boolean a()
	{
		if (((this.a.world.r()) && (!this.a.world.J())) || (this.a.world.worldProvider.e)) return false;
		if (this.a.au().nextInt(50) != 0) return false;
		if ((this.c != -1) && (this.a.e(this.c, this.a.locY, this.d) < 4.0D)) return false;
		Village localVillage = this.a.world.villages.getClosestVillage(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ), 14);
		if (localVillage == null) return false;
		this.b = localVillage.c(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ));
		return this.b != null;
	}

	public boolean b()
	{
		return !this.a.getNavigation().f();
	}

	public void e()
	{
		this.c = -1;
		if (this.a.e(this.b.getIndoorsX(), this.b.locY, this.b.getIndoorsZ()) > 256.0D) {
			Vec3D localVec3D = RandomPositionGenerator.a(this.a, 14, 3, Vec3D.a().create(this.b.getIndoorsX() + 0.5D, this.b.getIndoorsY(), this.b.getIndoorsZ() + 0.5D));
			if (localVec3D != null) this.a.getNavigation().a(localVec3D.a, localVec3D.b, localVec3D.c, 0.3F); 
		}
		else {
			this.a.getNavigation().a(this.b.getIndoorsX() + 0.5D, this.b.getIndoorsY(), this.b.getIndoorsZ() + 0.5D, 0.3F);
		}
	}

	public void c() {
		this.c = this.b.getIndoorsX();
		this.d = this.b.getIndoorsZ();
		this.b = null;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalMoveIndoors
 * JD-Core Version:		0.6.0
 */