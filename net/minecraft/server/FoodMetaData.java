package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.PluginManager;

public class FoodMetaData
{
	public int foodLevel = 20;
	public float saturationLevel = 5.0F;
	public float exhaustionLevel;
	public int foodTickTimer = 0;

	private int e = 20;

	public void eat(int i, float f)
	{
		this.foodLevel = Math.min(i + this.foodLevel, 20);
		this.saturationLevel = Math.min(this.saturationLevel + i * f * 2.0F, this.foodLevel);
	}

	public void a(ItemFood itemfood) {
		eat(itemfood.getNutrition(), itemfood.getSaturationModifier());
	}

	public void a(EntityHuman entityhuman) {
		int i = entityhuman.world.difficulty;

		this.e = this.foodLevel;
		if (this.exhaustionLevel > 4.0F) {
			this.exhaustionLevel -= 4.0F;
			if (this.saturationLevel > 0.0F) {
				this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
			} else if (i > 0)
			{
				FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, Math.max(this.foodLevel - 1, 0));

				if (!event.isCancelled()) {
					this.foodLevel = event.getFoodLevel();
				}
			}

		}

		if ((this.foodLevel >= 18) && (entityhuman.bM())) {
			this.foodTickTimer += 1;
			if (this.foodTickTimer >= 80)
			{
				entityhuman.heal(1, EntityRegainHealthEvent.RegainReason.SATIATED);
				this.foodTickTimer = 0;
			}
		} else if (this.foodLevel <= 0) {
			this.foodTickTimer += 1;
			if (this.foodTickTimer >= 80) {
				if ((entityhuman.getHealth() > 10) || (i >= 3) || ((entityhuman.getHealth() > 1) && (i >= 2)))
				{
					EntityDamageEvent event = new EntityDamageEvent(entityhuman.getBukkitEntity(), EntityDamageEvent.DamageCause.STARVATION, 1);
					entityhuman.world.getServer().getPluginManager().callEvent(event);

					if (!event.isCancelled()) {
						event.getEntity().setLastDamageCause(event);
						entityhuman.damageEntity(DamageSource.STARVE, event.getDamage());
					}

				}

				this.foodTickTimer = 0;
			}
		} else {
			this.foodTickTimer = 0;
		}
	}

	public void a(NBTTagCompound nbttagcompound) {
		if (nbttagcompound.hasKey("foodLevel")) {
			this.foodLevel = nbttagcompound.getInt("foodLevel");
			this.foodTickTimer = nbttagcompound.getInt("foodTickTimer");
			this.saturationLevel = nbttagcompound.getFloat("foodSaturationLevel");
			this.exhaustionLevel = nbttagcompound.getFloat("foodExhaustionLevel");
		}
	}

	public void b(NBTTagCompound nbttagcompound) {
		nbttagcompound.setInt("foodLevel", this.foodLevel);
		nbttagcompound.setInt("foodTickTimer", this.foodTickTimer);
		nbttagcompound.setFloat("foodSaturationLevel", this.saturationLevel);
		nbttagcompound.setFloat("foodExhaustionLevel", this.exhaustionLevel);
	}

	public int a() {
		return this.foodLevel;
	}

	public boolean c() {
		return this.foodLevel < 20;
	}

	public void a(float f) {
		this.exhaustionLevel = Math.min(this.exhaustionLevel + f, 40.0F);
	}

	public float e() {
		return this.saturationLevel;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.FoodMetaData
 * JD-Core Version:		0.6.0
 */