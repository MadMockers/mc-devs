package net.minecraft.server;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PathfinderGoalNearestAttackableTarget extends PathfinderGoalTarget
{
	EntityLiving a;
	Class b;
	int c;
	private DistanceComparator g;

	public PathfinderGoalNearestAttackableTarget(EntityLiving paramEntityLiving, Class paramClass, float paramFloat, int paramInt, boolean paramBoolean)
	{
		this(paramEntityLiving, paramClass, paramFloat, paramInt, paramBoolean, false);
	}

	public PathfinderGoalNearestAttackableTarget(EntityLiving paramEntityLiving, Class paramClass, float paramFloat, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
		super(paramEntityLiving, paramFloat, paramBoolean1, paramBoolean2);
		this.b = paramClass;
		this.e = paramFloat;
		this.c = paramInt;
		this.g = new DistanceComparator(this, paramEntityLiving);
		a(1);
	}

	public boolean a()
	{
		if ((this.c > 0) && (this.d.au().nextInt(this.c) != 0)) return false;
		Object localObject;
		if (this.b == EntityHuman.class) {
			localObject = this.d.world.findNearbyVulnerablePlayer(this.d, this.e);
			if (a((EntityLiving)localObject, false)) {
				this.a = ((EntityLiving)localObject);
				return true;
			}
		} else {
			localObject = this.d.world.a(this.b, this.d.boundingBox.grow(this.e, 4.0D, this.e));
			Collections.sort((List)localObject, this.g);
			for (Entity localEntity : (List)localObject) {
				EntityLiving localEntityLiving = (EntityLiving)localEntity;
				if (a(localEntityLiving, false)) {
					this.a = localEntityLiving;
					return true;
				}
			}
		}
		return false;
	}

	public void e()
	{
		this.d.b(this.a);
		super.e();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalNearestAttackableTarget
 * JD-Core Version:		0.6.0
 */