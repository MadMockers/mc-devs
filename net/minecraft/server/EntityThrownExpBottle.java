package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.ExpBottleEvent;

public class EntityThrownExpBottle extends EntityProjectile
{
	public EntityThrownExpBottle(World world)
	{
		super(world);
	}

	public EntityThrownExpBottle(World world, EntityLiving entityliving) {
		super(world, entityliving);
	}

	public EntityThrownExpBottle(World world, double d0, double d1, double d2) {
		super(world, d0, d1, d2);
	}

	protected float h() {
		return 0.07F;
	}

	protected float d() {
		return 0.7F;
	}

	protected float g() {
		return -20.0F;
	}

	protected void a(MovingObjectPosition movingobjectposition) {
		if (!this.world.isStatic)
		{
			int i = 3 + this.world.random.nextInt(5) + this.world.random.nextInt(5);

			ExpBottleEvent event = CraftEventFactory.callExpBottleEvent(this, i);
			i = event.getExperience();
			if (event.getShowEffect()) {
				this.world.triggerEffect(2002, (int)Math.round(this.locX), (int)Math.round(this.locY), (int)Math.round(this.locZ), 0);
			}

			while (i > 0) {
				int j = EntityExperienceOrb.getOrbValue(i);

				i -= j;
				this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
			}

			die();
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityThrownExpBottle
 * JD-Core Version:		0.6.0
 */