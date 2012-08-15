package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

public class EntitySnowman extends EntityGolem
{
	public EntitySnowman(World world)
	{
		super(world);
		this.texture = "/mob/snowman.png";
		a(0.4F, 1.8F);
		getNavigation().a(true);
		this.goalSelector.a(1, new PathfinderGoalArrowAttack(this, 0.25F, 2, 20));
		this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, 0.2F));
		this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
		this.goalSelector.a(4, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityMonster.class, 16.0F, 0, true));
	}

	public boolean aV() {
		return true;
	}

	public int getMaxHealth() {
		return 4;
	}

	public void d() {
		super.d();
		if (G())
		{
			EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.DROWNING, 1);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {
				event.getEntity().setLastDamageCause(event);
				damageEntity(DamageSource.DROWN, event.getDamage());
			}

		}

		int i = MathHelper.floor(this.locX);
		int j = MathHelper.floor(this.locZ);

		if (this.world.getBiome(i, j).j() > 1.0F)
		{
			EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.MELTING, 1);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {
				event.getEntity().setLastDamageCause(event);
				damageEntity(DamageSource.BURN, event.getDamage());
			}

		}

		for (i = 0; i < 4; i++) {
			j = MathHelper.floor(this.locX + (i % 2 * 2 - 1) * 0.25F);
			int k = MathHelper.floor(this.locY);
			int l = MathHelper.floor(this.locZ + (i / 2 % 2 * 2 - 1) * 0.25F);

			if ((this.world.getTypeId(j, k, l) != 0) || (this.world.getBiome(j, l).j() >= 0.8F) || (!Block.SNOW.canPlace(this.world, j, k, l)))
				continue;
			BlockState blockState = this.world.getWorld().getBlockAt(j, k, l).getState();
			blockState.setTypeId(Block.SNOW.id);

			EntityBlockFormEvent event = new EntityBlockFormEvent(getBukkitEntity(), blockState.getBlock(), blockState);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled())
				blockState.update(true);
		}
	}

	protected int getLootId()
	{
		return Item.SNOW_BALL.id;
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();
		int j = this.random.nextInt(16);

		if (j > 0) {
			loot.add(new ItemStack(Item.SNOW_BALL.id, j));
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntitySnowman
 * JD-Core Version:		0.6.0
 */