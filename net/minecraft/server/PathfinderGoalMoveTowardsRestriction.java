package net.minecraft.server;

public class PathfinderGoalMoveTowardsRestriction extends PathfinderGoal
{
	private EntityCreature a;
	private double b;
	private double c;
	private double d;
	private float e;

	public PathfinderGoalMoveTowardsRestriction(EntityCreature paramEntityCreature, float paramFloat)
	{
		this.a = paramEntityCreature;
		this.e = paramFloat;
		a(1);
	}

	public boolean a()
	{
		if (this.a.aB()) return false;
		ChunkCoordinates localChunkCoordinates = this.a.aC();
		Vec3D localVec3D = RandomPositionGenerator.a(this.a, 16, 7, Vec3D.a().create(localChunkCoordinates.x, localChunkCoordinates.y, localChunkCoordinates.z));
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
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalMoveTowardsRestriction
 * JD-Core Version:		0.6.0
 */