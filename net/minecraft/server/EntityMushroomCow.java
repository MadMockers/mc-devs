package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.plugin.PluginManager;

public class EntityMushroomCow extends EntityCow
{
	public EntityMushroomCow(World world)
	{
		super(world);
		this.texture = "/mob/redcow.png";
		a(0.9F, 1.3F);
	}

	public boolean c(EntityHuman entityhuman) {
		ItemStack itemstack = entityhuman.inventory.getItemInHand();

		if ((itemstack != null) && (itemstack.id == Item.BOWL.id) && (getAge() >= 0)) {
			if (itemstack.count == 1) {
				entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, new ItemStack(Item.MUSHROOM_SOUP));
				return true;
			}

			if ((entityhuman.inventory.pickup(new ItemStack(Item.MUSHROOM_SOUP))) && (!entityhuman.abilities.canInstantlyBuild)) {
				entityhuman.inventory.splitStack(entityhuman.inventory.itemInHandIndex, 1);
				return true;
			}
		}

		if ((itemstack != null) && (itemstack.id == Item.SHEARS.id) && (getAge() >= 0))
		{
			PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)entityhuman.getBukkitEntity(), getBukkitEntity());
			this.world.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled()) {
				return false;
			}

			die();
			this.world.a("largeexplode", this.locX, this.locY + this.length / 2.0F, this.locZ, 0.0D, 0.0D, 0.0D);
			if (!this.world.isStatic) {
				EntityCow entitycow = new EntityCow(this.world);

				entitycow.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
				entitycow.setHealth(getHealth());
				entitycow.aq = this.aq;
				this.world.addEntity(entitycow);

				for (int i = 0; i < 5; i++) {
					this.world.addEntity(new EntityItem(this.world, this.locX, this.locY + this.length, this.locZ, new ItemStack(Block.RED_MUSHROOM)));
				}
			}

			return true;
		}
		return super.c(entityhuman);
	}

	public EntityAnimal createChild(EntityAnimal entityanimal)
	{
		return new EntityMushroomCow(this.world);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityMushroomCow
 * JD-Core Version:		0.6.0
 */