package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

public abstract class EntityMonster extends EntityCreature
	implements IMonster
{
	protected int damage = 2;

	public EntityMonster(World world) {
		super(world);
		this.aV = 5;
	}

	public void d() {
		float f = c(1.0F);

		if (f > 0.5F) {
			this.bq += 2;
		}

		super.d();
	}

	public void h_() {
		super.h_();
		if ((!this.world.isStatic) && (this.world.difficulty == 0))
			die();
	}

	protected Entity findTarget()
	{
		EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, 16.0D);

		return (entityhuman != null) && (l(entityhuman)) ? entityhuman : null;
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		if (super.damageEntity(damagesource, i)) {
			Entity entity = damagesource.getEntity();

			if ((this.passenger != entity) && (this.vehicle != entity)) {
				if (entity != this)
				{
					if ((entity != this.target) && (((this instanceof EntityBlaze)) || ((this instanceof EntityEnderman)) || ((this instanceof EntitySpider)) || ((this instanceof EntityGiantZombie)) || ((this instanceof EntitySilverfish)))) {
						EntityTargetEvent event = CraftEventFactory.callEntityTargetEvent(this, entity, EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY);

						if (!event.isCancelled())
							if (event.getTarget() == null)
								this.target = null;
							else
								this.target = ((CraftEntity)event.getTarget()).getHandle();
					}
					else
					{
						this.target = entity;
					}

				}

				return true;
			}
			return true;
		}

		return false;
	}

	public boolean k(Entity entity)
	{
		int i = this.damage;

		if (hasEffect(MobEffectList.INCREASE_DAMAGE)) {
			i += (3 << getEffect(MobEffectList.INCREASE_DAMAGE).getAmplifier());
		}

		if (hasEffect(MobEffectList.WEAKNESS)) {
			i -= (2 << getEffect(MobEffectList.WEAKNESS).getAmplifier());
		}

		return entity.damageEntity(DamageSource.mobAttack(this), i);
	}

	protected void a(Entity entity, float f) {
		if ((this.attackTicks <= 0) && (f < 2.0F) && (entity.boundingBox.e > this.boundingBox.b) && (entity.boundingBox.b < this.boundingBox.e)) {
			this.attackTicks = 20;
			k(entity);
		}
	}

	public float a(int i, int j, int k) {
		return 0.5F - this.world.o(i, j, k);
	}

	protected boolean o() {
		int i = MathHelper.floor(this.locX);
		int j = MathHelper.floor(this.boundingBox.b);
		int k = MathHelper.floor(this.locZ);

		if (this.world.b(EnumSkyBlock.SKY, i, j, k) > this.random.nextInt(32)) {
			return false;
		}
		int l = this.world.getLightLevel(i, j, k);

		if (this.world.I()) {
			int i1 = this.world.k;

			this.world.k = 10;
			l = this.world.getLightLevel(i, j, k);
			this.world.k = i1;
		}

		return l <= this.random.nextInt(8);
	}

	public boolean canSpawn()
	{
		return (o()) && (super.canSpawn());
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityMonster
 * JD-Core Version:		0.6.0
 */