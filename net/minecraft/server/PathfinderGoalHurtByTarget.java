package net.minecraft.server;

import java.util.List;

public class PathfinderGoalHurtByTarget extends PathfinderGoalTarget
{
	boolean a;
	EntityLiving b;

	public PathfinderGoalHurtByTarget(EntityLiving paramEntityLiving, boolean paramBoolean)
	{
		super(paramEntityLiving, 16.0F, false);
		this.a = paramBoolean;
		a(1);
	}

	public boolean a()
	{
		return a(this.d.av(), true);
	}

	public boolean b()
	{
		return (this.d.av() != null) && (this.d.av() != this.b);
	}

	public void e()
	{
		this.d.b(this.d.av());
		this.b = this.d.av();

		if (this.a) {
			List localList = this.d.world.a(this.d.getClass(), AxisAlignedBB.a().a(this.d.locX, this.d.locY, this.d.locZ, this.d.locX + 1.0D, this.d.locY + 1.0D, this.d.locZ + 1.0D).grow(this.e, 4.0D, this.e));
			for (EntityLiving localEntityLiving : localList) {
				if ((this.d == localEntityLiving) || 
					(localEntityLiving.az() != null)) continue;
				localEntityLiving.b(this.d.av());
			}
		}

		super.e();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalHurtByTarget
 * JD-Core Version:		0.6.0
 */