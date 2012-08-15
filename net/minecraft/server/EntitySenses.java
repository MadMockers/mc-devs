package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class EntitySenses
{
	EntityLiving entity;
	List seenEntities = new ArrayList();
	List unseenEntities = new ArrayList();

	public EntitySenses(EntityLiving paramEntityLiving) {
		this.entity = paramEntityLiving;
	}

	public void a() {
		this.seenEntities.clear();
		this.unseenEntities.clear();
	}

	public boolean canSee(Entity paramEntity) {
		if (this.seenEntities.contains(paramEntity)) return true;
		if (this.unseenEntities.contains(paramEntity)) return false;

		this.entity.world.methodProfiler.a("canSee");
		boolean bool = this.entity.l(paramEntity);
		this.entity.world.methodProfiler.b();
		if (bool) this.seenEntities.add(paramEntity); else
			this.unseenEntities.add(paramEntity);
		return bool;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntitySenses
 * JD-Core Version:		0.6.0
 */