package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityBreakDoorEvent;

public class PathfinderGoalBreakDoor extends PathfinderGoalDoorInteract
{
	private int i;
	private int j = -1;

	public PathfinderGoalBreakDoor(EntityLiving entityliving) {
		super(entityliving);
	}

	public boolean a() {
		return super.a();
	}

	public void e() {
		super.e();
		this.i = 0;
	}

	public boolean b() {
		double d0 = this.a.e(this.b, this.c, this.d);

		return (this.i <= 240) && (!this.e.a_(this.a.world, this.b, this.c, this.d)) && (d0 < 4.0D);
	}

	public void c() {
		super.c();
		this.a.world.f(this.a.id, this.b, this.c, this.d, -1);
	}

	public void d() {
		super.d();
		if (this.a.au().nextInt(20) == 0) {
			this.a.world.triggerEffect(1010, this.b, this.c, this.d, 0);
		}

		this.i += 1;
		int i = (int)(this.i / 240.0F * 10.0F);

		if (i != this.j) {
			this.a.world.f(this.a.id, this.b, this.c, this.d, i);
			this.j = i;
		}

		if ((this.i == 240) && (this.a.world.difficulty == 3))
		{
			if (CraftEventFactory.callEntityBreakDoorEvent(this.a, this.b, this.c, this.d).isCancelled()) {
				e();
				return;
			}

			this.a.world.setTypeId(this.b, this.c, this.d, 0);
			this.a.world.triggerEffect(1012, this.b, this.c, this.d, 0);
			this.a.world.triggerEffect(2001, this.b, this.c, this.d, this.e.id);
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalBreakDoor
 * JD-Core Version:		0.6.0
 */