package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class PathfinderGoalMoveThroughVillage extends PathfinderGoal
{
	private EntityCreature a;
	private float b;
	private PathEntity c;
	private VillageDoor d;
	private boolean e;
	private List f = new ArrayList();

	public PathfinderGoalMoveThroughVillage(EntityCreature paramEntityCreature, float paramFloat, boolean paramBoolean) {
		this.a = paramEntityCreature;
		this.b = paramFloat;
		this.e = paramBoolean;
		a(1);
	}

	public boolean a()
	{
		f();

		if ((this.e) && (this.a.world.r())) return false;

		Village localVillage = this.a.world.villages.getClosestVillage(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ), 0);
		if (localVillage == null) return false;

		this.d = a(localVillage);
		if (this.d == null) return false;

		boolean bool = this.a.getNavigation().c();
		this.a.getNavigation().b(false);
		this.c = this.a.getNavigation().a(this.d.locX, this.d.locY, this.d.locZ);
		this.a.getNavigation().b(bool);
		if (this.c != null) return true;

		Vec3D localVec3D = RandomPositionGenerator.a(this.a, 10, 7, Vec3D.a().create(this.d.locX, this.d.locY, this.d.locZ));
		if (localVec3D == null) return false;
		this.a.getNavigation().b(false);
		this.c = this.a.getNavigation().a(localVec3D.a, localVec3D.b, localVec3D.c);
		this.a.getNavigation().b(bool);
		return this.c != null;
	}

	public boolean b()
	{
		if (this.a.getNavigation().f()) return false;
		float f1 = this.a.width + 4.0F;
		return this.a.e(this.d.locX, this.d.locY, this.d.locZ) > f1 * f1;
	}

	public void e()
	{
		this.a.getNavigation().a(this.c, this.b);
	}

	public void c()
	{
		if ((this.a.getNavigation().f()) || (this.a.e(this.d.locX, this.d.locY, this.d.locZ) < 16.0D))
			this.f.add(this.d);
	}

	private VillageDoor a(Village paramVillage) {
		Object localObject = null;
		int i = 2147483647;
		List localList = paramVillage.getDoors();
		for (VillageDoor localVillageDoor : localList) {
			int j = localVillageDoor.b(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ));
			if (j < i)
				if (!a(localVillageDoor)) {
					localObject = localVillageDoor;
					i = j;
				}
		}
		return localObject;
	}

	private boolean a(VillageDoor paramVillageDoor) {
		for (VillageDoor localVillageDoor : this.f)
			if ((paramVillageDoor.locX == localVillageDoor.locX) && (paramVillageDoor.locY == localVillageDoor.locY) && (paramVillageDoor.locZ == localVillageDoor.locZ)) return true;
		return false;
	}

	private void f() {
		if (this.f.size() > 15) this.f.remove(0);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalMoveThroughVillage
 * JD-Core Version:		0.6.0
 */