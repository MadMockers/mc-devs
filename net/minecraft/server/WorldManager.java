package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class WorldManager
	implements IWorldAccess
{
	private MinecraftServer server;
	public WorldServer world;

	public WorldManager(MinecraftServer minecraftserver, WorldServer worldserver)
	{
		this.server = minecraftserver;
		this.world = worldserver;
	}
	public void a(String s, double d0, double d1, double d2, double d3, double d4, double d5) {
	}

	public void a(Entity entity) {
		this.world.getTracker().track(entity);
	}

	public void b(Entity entity) {
		this.world.getTracker().untrackEntity(entity);
	}

	public void a(String s, double d0, double d1, double d2, float f, float f1)
	{
		this.server.getServerConfigurationManager().sendPacketNearby(d0, d1, d2, f > 1.0F ? 16.0F * f : 16.0D, this.world.dimension, new Packet62NamedSoundEffect(s, d0, d1, d2, f, f1));
	}
	public void a(int i, int j, int k, int l, int i1, int j1) {
	}

	public void a(int i, int j, int k) {
		this.world.getPlayerManager().flagDirty(i, j, k);
	}
	public void b(int i, int j, int k) {
	}

	public void a(String s, int i, int j, int k) {
	}

	public void a(EntityHuman entityhuman, int i, int j, int k, int l, int i1) {
		this.server.getServerConfigurationManager().sendPacketNearby(entityhuman, j, k, l, 64.0D, this.world.dimension, new Packet61WorldEvent(i, j, k, l, i1));
	}

	public void a(int i, int j, int k, int l, int i1) {
		Iterator iterator = this.server.getServerConfigurationManager().players.iterator();

		while (iterator.hasNext()) {
			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			if ((entityplayer != null) && (entityplayer.world == this.world) && (entityplayer.id != i)) {
				double d0 = j - entityplayer.locX;
				double d1 = k - entityplayer.locY;
				double d2 = l - entityplayer.locZ;

				if (d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D)
					entityplayer.netServerHandler.sendPacket(new Packet55BlockBreakAnimation(i, j, k, l, i1));
			}
		}
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldManager
 * JD-Core Version:		0.6.0
 */