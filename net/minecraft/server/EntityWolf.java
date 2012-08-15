package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTameEvent;

public class EntityWolf extends EntityTameableAnimal
{
	private float e;
	private float f;
	private boolean g;
	private boolean h;
	private float i;
	private float j;

	public EntityWolf(World world)
	{
		super(world);
		this.texture = "/mob/wolf.png";
		a(0.6F, 0.8F);
		this.bw = 0.3F;
		getNavigation().a(true);
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, this.d);
		this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
		this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, this.bw, true));
		this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, this.bw, 10.0F, 2.0F));
		this.goalSelector.a(6, new PathfinderGoalBreed(this, this.bw));
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, this.bw));
		this.goalSelector.a(8, new PathfinderGoalBeg(this, 8.0F));
		this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
		this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
		this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true));
		this.targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed(this, EntitySheep.class, 16.0F, 200, false));
	}

	public boolean aV() {
		return true;
	}

	public void b(EntityLiving entityliving) {
		super.b(entityliving);
		if ((entityliving instanceof EntityHuman))
			setAngry(true);
	}

	protected void bd()
	{
		this.datawatcher.watch(18, Integer.valueOf(getHealth()));
	}

	public int getMaxHealth() {
		return isTamed() ? 20 : 8;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(18, new Integer(getHealth()));
		this.datawatcher.a(19, new Byte(0));
	}

	protected boolean e_() {
		return false;
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.setBoolean("Angry", isAngry());
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		setAngry(nbttagcompound.getBoolean("Angry"));
	}

	protected boolean ba() {
		return isAngry();
	}

	protected String aQ() {
		return this.random.nextInt(3) == 0 ? "mob.wolf.panting" : (isTamed()) && (this.datawatcher.getInt(18) < 10) ? "mob.wolf.whine" : isAngry() ? "mob.wolf.growl" : "mob.wolf.bark";
	}

	protected String aR() {
		return "mob.wolf.hurt";
	}

	protected String aS() {
		return "mob.wolf.death";
	}

	protected float aP() {
		return 0.4F;
	}

	protected int getLootId() {
		return -1;
	}

	public void d() {
		super.d();
		if ((!this.world.isStatic) && (this.g) && (!this.h) && (!l()) && (this.onGround)) {
			this.h = true;
			this.i = 0.0F;
			this.j = 0.0F;
			this.world.broadcastEntityEffect(this, 8);
		}
	}

	public void h_() {
		super.h_();
		this.f = this.e;
		if (bv())
			this.e += (1.0F - this.e) * 0.4F;
		else {
			this.e += (0.0F - this.e) * 0.4F;
		}

		if (bv()) {
			this.bx = 10;
		}

		if (G()) {
			this.g = true;
			this.h = false;
			this.i = 0.0F;
			this.j = 0.0F;
		} else if (((this.g) || (this.h)) && (this.h)) {
			if (this.i == 0.0F) {
				this.world.makeSound(this, "mob.wolf.shake", aP(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			}

			this.j = this.i;
			this.i += 0.05F;
			if (this.j >= 2.0F) {
				this.g = false;
				this.h = false;
				this.j = 0.0F;
				this.i = 0.0F;
			}

			if (this.i > 0.4F) {
				float f = (float)this.boundingBox.b;
				int i = (int)(MathHelper.sin((this.i - 0.4F) * 3.141593F) * 7.0F);

				for (int j = 0; j < i; j++) {
					float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
					float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;

					this.world.a("splash", this.locX + f1, f + 0.8F, this.locZ + f2, this.motX, this.motY, this.motZ);
				}
			}
		}
	}

	public float getHeadHeight() {
		return this.length * 0.8F;
	}

	public int bf() {
		return isSitting() ? 20 : super.bf();
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		Entity entity = damagesource.getEntity();

		this.d.a(false);
		if ((entity != null) && (!(entity instanceof EntityHuman)) && (!(entity instanceof EntityArrow))) {
			i = (i + 1) / 2;
		}

		return super.damageEntity(damagesource, i);
	}

	public boolean k(Entity entity) {
		int i = isTamed() ? 4 : 2;

		return entity.damageEntity(DamageSource.mobAttack(this), i);
	}

	public boolean c(EntityHuman entityhuman) {
		ItemStack itemstack = entityhuman.inventory.getItemInHand();

		if (isTamed()) {
			if ((itemstack != null) && ((Item.byId[itemstack.id] instanceof ItemFood))) {
				ItemFood itemfood = (ItemFood)Item.byId[itemstack.id];

				if ((itemfood.h()) && (this.datawatcher.getInt(18) < 20)) {
					if (!entityhuman.abilities.canInstantlyBuild) {
						itemstack.count -= 1;
					}

					heal(itemfood.getNutrition());
					if (itemstack.count <= 0) {
						entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack)null);
					}

					return true;
				}
			}

			if ((entityhuman.name.equalsIgnoreCase(getOwnerName())) && (!this.world.isStatic) && (!b(itemstack))) {
				this.d.a(!isSitting());
				this.bu = false;
				setPathEntity((PathEntity)null);
			}
		} else if ((itemstack != null) && (itemstack.id == Item.BONE.id) && (!isAngry())) {
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
					setPathEntity((PathEntity)null);
					b((EntityLiving)null);
					this.d.a(true);
					setHealth(20);
					setOwnerName(entityhuman.name);
					e(true);
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

	public boolean b(ItemStack itemstack) {
		return !(Item.byId[itemstack.id] instanceof ItemFood) ? false : itemstack == null ? false : ((ItemFood)Item.byId[itemstack.id]).h();
	}

	public int bl() {
		return 8;
	}

	public boolean isAngry() {
		return (this.datawatcher.getByte(16) & 0x2) != 0;
	}

	public void setAngry(boolean flag) {
		byte b0 = this.datawatcher.getByte(16);

		if (flag)
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x2)));
		else
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFD)));
	}

	public EntityAnimal createChild(EntityAnimal entityanimal)
	{
		EntityWolf entitywolf = new EntityWolf(this.world);

		entitywolf.setOwnerName(getOwnerName());
		entitywolf.setTamed(true);
		return entitywolf;
	}

	public void i(boolean flag) {
		byte b0 = this.datawatcher.getByte(19);

		if (flag)
			this.datawatcher.watch(19, Byte.valueOf(1));
		else
			this.datawatcher.watch(19, Byte.valueOf(0));
	}

	public boolean mate(EntityAnimal entityanimal)
	{
		if (entityanimal == this)
			return false;
		if (!isTamed())
			return false;
		if (!(entityanimal instanceof EntityWolf)) {
			return false;
		}
		EntityWolf entitywolf = (EntityWolf)entityanimal;

		return entitywolf.isTamed();
	}

	public boolean bv()
	{
		return this.datawatcher.getByte(19) == 1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityWolf
 * JD-Core Version:		0.6.0
 */