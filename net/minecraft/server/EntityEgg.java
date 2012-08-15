package net.minecraft.server;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.plugin.PluginManager;

public class EntityEgg extends EntityProjectile
{
	public EntityEgg(World world)
	{
		super(world);
	}

	public EntityEgg(World world, EntityLiving entityliving) {
		super(world, entityliving);
	}

	public EntityEgg(World world, double d0, double d1, double d2) {
		super(world, d0, d1, d2);
	}

	protected void a(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entity != null) {
			movingobjectposition.entity.damageEntity(DamageSource.projectile(this, this.shooter), 0);
		}

		boolean hatching = (!this.world.isStatic) && (this.random.nextInt(8) == 0);
		int numHatching = this.random.nextInt(32) == 0 ? 4 : 1;
		if (!hatching) {
			numHatching = 0;
		}

		EntityType hatchingType = EntityType.CHICKEN;

		if ((this.shooter instanceof EntityPlayer)) {
			Player player = this.shooter == null ? null : (Player)this.shooter.getBukkitEntity();

			PlayerEggThrowEvent event = new PlayerEggThrowEvent(player, (Egg)getBukkitEntity(), hatching, (byte)numHatching, hatchingType);
			this.world.getServer().getPluginManager().callEvent(event);

			hatching = event.isHatching();
			numHatching = event.getNumHatches();
			hatchingType = event.getHatchingType();
		}

		if (hatching) {
			for (int k = 0; k < numHatching; k++) {
				org.bukkit.entity.Entity entity = this.world.getWorld().spawn(new Location(this.world.getWorld(), this.locX, this.locY, this.locZ, this.yaw, 0.0F), hatchingType.getEntityClass(), CreatureSpawnEvent.SpawnReason.EGG);
				if ((entity instanceof Ageable)) {
					((Ageable)entity).setBaby();
				}
			}

		}

		for (int j = 0; j < 8; j++) {
			this.world.a("snowballpoof", this.locX, this.locY, this.locZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.world.isStatic)
			die();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityEgg
 * JD-Core Version:		0.6.0
 */