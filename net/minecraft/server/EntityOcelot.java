package net.minecraft.server;

import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTameEvent;

public class EntityOcelot extends EntityTameableAnimal
{
	private PathfinderGoalTempt e;

	public EntityOcelot(World world)
	{
		super(world);
		this.texture = "/mob/ozelot.png";
		a(0.6F, 0.8F);
		getNavigation().a(true);
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, this.d);
		this.goalSelector.a(3, this.e = new PathfinderGoalTempt(this, 0.18F, Item.RAW_FISH.id, true));
		this.goalSelector.a(4, new PathfinderGoalAvoidPlayer(this, EntityHuman.class, 16.0F, 0.23F, 0.4F));
		this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, 0.3F, 10.0F, 5.0F));
		this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 0.4F));
		this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3F));
		this.goalSelector.a(8, new PathfinderGoalOzelotAttack(this));
		this.goalSelector.a(9, new PathfinderGoalBreed(this, 0.23F));
		this.goalSelector.a(10, new PathfinderGoalRandomStroll(this, 0.23F));
		this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
		this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed(this, EntityChicken.class, 14.0F, 750, false));
	}

	protected void a() {
		super.a();
		this.datawatcher.a(18, Byte.valueOf(0));
	}

	public void bd() {
		if (getControllerMove().a()) {
			float f = getControllerMove().b();

			if (f == 0.18F) {
				setSneaking(true);
				setSprinting(false);
			} else if (f == 0.4F) {
				setSneaking(false);
				setSprinting(true);
			} else {
				setSneaking(false);
				setSprinting(false);
			}
		} else {
			setSneaking(false);
			setSprinting(false);
		}
	}

	protected boolean ba() {
		return !isTamed();
	}

	public boolean aV() {
		return true;
	}

	public int getMaxHealth() {
		return 10;
	}
	protected void a(float f) {
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.setInt("CatType", getCatType());
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		setCatType(nbttagcompound.getInt("CatType"));
	}

	protected String aQ() {
		return isTamed() ? "mob.cat.meow" : this.random.nextInt(4) == 0 ? "mob.cat.purreow" : s() ? "mob.cat.purr" : "";
	}

	protected String aR() {
		return "mob.cat.hitt";
	}

	protected String aS() {
		return "mob.cat.hitt";
	}

	protected float aP() {
		return 0.4F;
	}

	protected int getLootId() {
		return Item.LEATHER.id;
	}

	public boolean k(Entity entity) {
		return entity.damageEntity(DamageSource.mobAttack(this), 3);
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		this.d.a(false);
		return super.damageEntity(damagesource, i);
	}

	protected void dropDeathLoot(boolean flag, int i) {
		CraftEventFactory.callEntityDeathEvent(this);
	}

	public boolean c(EntityHuman entityhuman) {
		ItemStack itemstack = entityhuman.inventory.getItemInHand();

		if (isTamed()) {
			if ((entityhuman.name.equalsIgnoreCase(getOwnerName())) && (!this.world.isStatic) && (!b(itemstack)))
				this.d.a(!isSitting());
		}
		else if ((this.e.f()) && (itemstack != null) && (itemstack.id == Item.RAW_FISH.id) && (entityhuman.e(this) < 9.0D)) {
			if (!entityhuman.abilities.canInstantlyBuild) {
				itemstack.count -= 1;
			}

			if (itemstack.count <= 0) {
				entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
			}

			if (!this.world.isStatic)
			{
				if ((this.random.nextInt(3) == 0) && (!CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled())) {
					setTamed(true);
					setCatType(1 + this.world.random.nextInt(3));
					setOwnerName(entityhuman.name);
					e(true);
					this.d.a(true);
					this.world.broadcastEntityEffect(this, 7);
				} else {
					e(false);
					this.world.broadcastEntityEffect(this, 6);
				}
			}

			return true;
		}

		return super.c(entityhuman);
	}

	public EntityAnimal createChild(EntityAnimal entityanimal) {
		EntityOcelot entityocelot = new EntityOcelot(this.world);

		if (isTamed()) {
			entityocelot.setOwnerName(getOwnerName());
			entityocelot.setTamed(true);
			entityocelot.setCatType(getCatType());
		}

		return entityocelot;
	}

	public boolean b(ItemStack itemstack) {
		return (itemstack != null) && (itemstack.id == Item.RAW_FISH.id);
	}

	public boolean mate(EntityAnimal entityanimal) {
		if (entityanimal == this)
			return false;
		if (!isTamed())
			return false;
		if (!(entityanimal instanceof EntityOcelot)) {
			return false;
		}
		EntityOcelot entityocelot = (EntityOcelot)entityanimal;

		return entityocelot.isTamed();
	}

	public int getCatType()
	{
		return this.datawatcher.getByte(18);
	}

	public void setCatType(int i) {
		this.datawatcher.watch(18, Byte.valueOf((byte)i));
	}

	public boolean canSpawn() {
		if (this.world.random.nextInt(3) == 0) {
			return false;
		}
		if ((this.world.b(this.boundingBox)) && (this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox))) {
			int i = MathHelper.floor(this.locX);
			int j = MathHelper.floor(this.boundingBox.b);
			int k = MathHelper.floor(this.locZ);

			if (j < 63) {
				return false;
			}

			int l = this.world.getTypeId(i, j - 1, k);

			if ((l == Block.GRASS.id) || (l == Block.LEAVES.id)) {
				return true;
			}
		}

		return false;
	}

	public String getLocalizedName()
	{
		return isTamed() ? "entity.Cat.name" : super.getLocalizedName();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityOcelot
 * JD-Core Version:		0.6.0
 */