package net.minecraft.server;

public class PathfinderGoalOpenDoor extends PathfinderGoalDoorInteract
{
	boolean i;
	int j;

	public PathfinderGoalOpenDoor(EntityLiving paramEntityLiving, boolean paramBoolean)
	{
		super(paramEntityLiving);
		this.a = paramEntityLiving;
		this.i = paramBoolean;
	}

	public boolean b()
	{
		return (this.i) && (this.j > 0) && (super.b());
	}

	public void e()
	{
		this.j = 20;
		this.e.setDoor(this.a.world, this.b, this.c, this.d, true);
	}

	public void c()
	{
		if (this.i)
			this.e.setDoor(this.a.world, this.b, this.c, this.d, false);
	}

	public void d()
	{
		this.j -= 1;
		super.d();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.PathfinderGoalOpenDoor
 * JD-Core Version:		0.6.0
 */