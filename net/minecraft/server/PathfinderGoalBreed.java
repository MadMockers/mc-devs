package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class PathfinderGoalBreed extends PathfinderGoal
{
	private EntityAnimal d;
	World a;
	private EntityAnimal e;
	int b = 0;
	float c;

	public PathfinderGoalBreed(EntityAnimal entityanimal, float f)
	{
		this.d = entityanimal;
		this.a = entityanimal.world;
		this.c = f;
		a(3);
	}

	public boolean a() {
		if (!this.d.s()) {
			return false;
		}
		this.e = f();
		return this.e != null;
	}

	public boolean b()
	{
		return (this.e.isAlive()) && (this.e.s()) && (this.b < 60);
	}

	public void c() {
		this.e = null;
		this.b = 0;
	}

	public void d() {
		this.d.getControllerLook().a(this.e, 10.0F, this.d.bf());
		this.d.getNavigation().a(this.e, this.c);
		this.b += 1;
		if (this.b == 60)
			i(); 
	}

	private EntityAnimal f() {
		float f = 8.0F;
		List list = this.a.a(this.d.getClass(), this.d.boundingBox.grow(f, f, f));
		Iterator iterator = list.iterator();
		EntityAnimal entityanimal;
		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entityanimal = (EntityAnimal)iterator.next();
		}while (!this.d.mate(entityanimal));

		return entityanimal;
	}

	private void i() {
		EntityAnimal entityanimal = this.d.createChild(this.e);

		if (entityanimal != null) {
			this.d.setAge(6000);
			this.e.setAge(6000);
			this.d.t();
			this.e.t();
			entityanimal.setAge(-24000);
			entityanimal.setPositionRotation(this.d.locX, this.d.locY, this.d.locZ, 0.0F, 0.0F);
			this.a.addEntity(entityanimal, CreatureSpawnEvent.SpawnReason.BREEDING);
			Random random = this.d.au();

			for (int i = 0; i < 7; i++) {
				double d0 = random.nextGaussian() * 0.02D;
				double d1 = random.nextGaussian() * 0.02D;
				double d2 = random.nextGaussian() * 0.02D;

				this.a.a("heart", this.d.locX + random.nextFloat() * this.d.width * 2.0F - this.d.width, this.d.locY + 0.5D + random.nextFloat() * this.d.length, this.d.locZ + random.nextFloat() * this.d.width * 2.0F - this.d.width, d0, d1, d2);
			}
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalBreed
 * JD-Core Version:		0.6.0
 */