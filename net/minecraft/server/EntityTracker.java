package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EntityTracker
{
	private final WorldServer world;
	private Set b = new HashSet();
	public IntHashMap trackedEntities = new IntHashMap();
	private int d;

	public EntityTracker(WorldServer worldserver)
	{
		this.world = worldserver;
		this.d = worldserver.getMinecraftServer().getServerConfigurationManager().a();
	}

	public synchronized void track(Entity entity)
	{
		if ((entity instanceof EntityPlayer)) {
			addEntity(entity, 512, 2);
			EntityPlayer entityplayer = (EntityPlayer)entity;
			Iterator iterator = this.b.iterator();

			while (iterator.hasNext()) {
				EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();

				if (entitytrackerentry.tracker != entityplayer)
					entitytrackerentry.updatePlayer(entityplayer);
			}
		}
		else if ((entity instanceof EntityFishingHook)) {
			addEntity(entity, 64, 5, true);
		} else if ((entity instanceof EntityArrow)) {
			addEntity(entity, 64, 20, false);
		} else if ((entity instanceof EntitySmallFireball)) {
			addEntity(entity, 64, 10, false);
		} else if ((entity instanceof EntityFireball)) {
			addEntity(entity, 64, 10, false);
		} else if ((entity instanceof EntitySnowball)) {
			addEntity(entity, 64, 10, true);
		} else if ((entity instanceof EntityEnderPearl)) {
			addEntity(entity, 64, 10, true);
		} else if ((entity instanceof EntityEnderSignal)) {
			addEntity(entity, 64, 4, true);
		} else if ((entity instanceof EntityEgg)) {
			addEntity(entity, 64, 10, true);
		} else if ((entity instanceof EntityPotion)) {
			addEntity(entity, 64, 10, true);
		} else if ((entity instanceof EntityThrownExpBottle)) {
			addEntity(entity, 64, 10, true);
		} else if ((entity instanceof EntityItem)) {
			addEntity(entity, 64, 20, true);
		} else if ((entity instanceof EntityMinecart)) {
			addEntity(entity, 80, 3, true);
		} else if ((entity instanceof EntityBoat)) {
			addEntity(entity, 80, 3, true);
		} else if ((entity instanceof EntitySquid)) {
			addEntity(entity, 64, 3, true);
		} else if ((entity instanceof IAnimal)) {
			addEntity(entity, 80, 3, true);
		} else if ((entity instanceof EntityEnderDragon)) {
			addEntity(entity, 160, 3, true);
		} else if ((entity instanceof EntityTNTPrimed)) {
			addEntity(entity, 160, 10, true);
		} else if ((entity instanceof EntityFallingBlock)) {
			addEntity(entity, 160, 20, true);
		} else if ((entity instanceof EntityPainting)) {
			addEntity(entity, 160, 2147483647, false);
		} else if ((entity instanceof EntityExperienceOrb)) {
			addEntity(entity, 160, 20, true);
		} else if ((entity instanceof EntityEnderCrystal)) {
			addEntity(entity, 256, 2147483647, false);
		}
	}

	public void addEntity(Entity entity, int i, int j) {
		addEntity(entity, i, j, false);
	}

	public synchronized void addEntity(Entity entity, int i, int j, boolean flag)
	{
		if (i > this.d) {
			i = this.d;
		}

		if (!this.trackedEntities.b(entity.id))
		{
			EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i, j, flag);

			this.b.add(entitytrackerentry);
			this.trackedEntities.a(entity.id, entitytrackerentry);
			entitytrackerentry.scanPlayers(this.world.players);
		}
	}

	public synchronized void untrackEntity(Entity entity)
	{
		if ((entity instanceof EntityPlayer)) {
			EntityPlayer entityplayer = (EntityPlayer)entity;
			Iterator iterator = this.b.iterator();

			while (iterator.hasNext()) {
				EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();

				entitytrackerentry.a(entityplayer);
			}
		}

		EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)this.trackedEntities.d(entity.id);

		if (entitytrackerentry1 != null) {
			this.b.remove(entitytrackerentry1);
			entitytrackerentry1.a();
		}
	}

	public synchronized void updatePlayers()
	{
		ArrayList arraylist = new ArrayList();
		Iterator iterator = this.b.iterator();

		while (iterator.hasNext()) {
			EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();

			entitytrackerentry.track(this.world.players);
			if ((entitytrackerentry.n) && ((entitytrackerentry.tracker instanceof EntityPlayer))) {
				arraylist.add((EntityPlayer)entitytrackerentry.tracker);
			}
		}

		iterator = arraylist.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();
			EntityPlayer entityplayer1 = entityplayer;
			Iterator iterator1 = this.b.iterator();

			while (iterator1.hasNext()) {
				EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)iterator1.next();

				if (entitytrackerentry1.tracker != entityplayer1)
					entitytrackerentry1.updatePlayer(entityplayer1);
			}
		}
	}

	public synchronized void a(Entity entity, Packet packet)
	{
		EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntities.get(entity.id);

		if (entitytrackerentry != null)
			entitytrackerentry.broadcast(packet);
	}

	public synchronized void sendPacketToEntity(Entity entity, Packet packet)
	{
		EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntities.get(entity.id);

		if (entitytrackerentry != null)
			entitytrackerentry.broadcastIncludingSelf(packet);
	}

	public synchronized void untrackPlayer(EntityPlayer entityplayer)
	{
		Iterator iterator = this.b.iterator();

		while (iterator.hasNext()) {
			EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)iterator.next();

			entitytrackerentry.clear(entityplayer);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityTracker
 * JD-Core Version:		0.6.0
 */