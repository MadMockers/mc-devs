package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.CreeperPowerEvent.PowerCause;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

public class EntityCreeper extends EntityMonster
{
	int fuseTicks;
	int e;
	private int record = -1;

	public EntityCreeper(World world) {
		super(world);
		this.texture = "/mob/creeper.png";
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new PathfinderGoalSwell(this));
		this.goalSelector.a(3, new PathfinderGoalAvoidPlayer(this, EntityOcelot.class, 6.0F, 0.25F, 0.3F));
		this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 0.25F, false));
		this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.2F));
		this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, true));
		this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
	}

	public boolean aV() {
		return true;
	}

	public int getMaxHealth() {
		return 20;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, Byte.valueOf(-1));
		this.datawatcher.a(17, Byte.valueOf(0));
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		if (this.datawatcher.getByte(17) == 1)
			nbttagcompound.setBoolean("powered", true);
	}

	public void a(NBTTagCompound nbttagcompound)
	{
		super.a(nbttagcompound);
		this.datawatcher.watch(17, Byte.valueOf((byte)(nbttagcompound.getBoolean("powered") ? 1 : 0)));
	}

	public void h_() {
		if (isAlive()) {
			this.e = this.fuseTicks;
			int i = p();

			if ((i > 0) && (this.fuseTicks == 0)) {
				this.world.makeSound(this, "random.fuse", 1.0F, 0.5F);
			}

			this.fuseTicks += i;
			if (this.fuseTicks < 0) {
				this.fuseTicks = 0;
			}

			if (this.fuseTicks >= 30) {
				this.fuseTicks = 30;
				if (!this.world.isStatic)
				{
					float radius = isPowered() ? 6.0F : 3.0F;

					ExplosionPrimeEvent event = new ExplosionPrimeEvent(getBukkitEntity(), radius, false);
					this.world.getServer().getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire());
						die();
					} else {
						this.fuseTicks = 0;
					}
				}
			}

		}

		super.h_();
	}

	protected String aR() {
		return "mob.creeper";
	}

	protected String aS() {
		return "mob.creeperdeath";
	}

	public void die(DamageSource damagesource)
	{
		if ((damagesource.getEntity() instanceof EntitySkeleton))
		{
			this.record = (Item.RECORD_1.id + this.random.nextInt(10));
		}

		super.die(damagesource);
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		int j = getLootId();

		List loot = new ArrayList();

		if (j > 0) {
			int k = this.random.nextInt(3);

			if (i > 0) {
				k += this.random.nextInt(i + 1);
			}

			if (k > 0) {
				loot.add(new ItemStack(j, k));
			}

		}

		if (this.record != -1) {
			loot.add(new ItemStack(this.record, 1));
			this.record = -1;
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}

	public boolean k(Entity entity)
	{
		return true;
	}

	public boolean isPowered() {
		return this.datawatcher.getByte(17) == 1;
	}

	protected int getLootId() {
		return Item.SULPHUR.id;
	}

	public int p() {
		return this.datawatcher.getByte(16);
	}

	public void a(int i) {
		this.datawatcher.watch(16, Byte.valueOf((byte)i));
	}

	public void a(EntityLightning entitylightning) {
		super.a(entitylightning);

		if (CraftEventFactory.callCreeperPowerEvent(this, entitylightning, CreeperPowerEvent.PowerCause.LIGHTNING).isCancelled()) {
			return;
		}

		setPowered(true);
	}

	public void setPowered(boolean powered) {
		if (!powered)
			this.datawatcher.watch(17, Byte.valueOf(0));
		else
			this.datawatcher.watch(17, Byte.valueOf(1));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityCreeper
 * JD-Core Version:		0.6.0
 */