package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.inventory.ItemStack;

public class EntityPig extends EntityAnimal
{
	public EntityPig(World world)
	{
		super(world);
		this.texture = "/mob/pig.png";
		a(0.9F, 0.9F);
		getNavigation().a(true);
		float f = 0.25F;

		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
		this.goalSelector.a(2, new PathfinderGoalBreed(this, f));
		this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
		this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.28F));
		this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, f));
		this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
		this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
	}

	public boolean aV() {
		return true;
	}

	public int getMaxHealth() {
		return 10;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, Byte.valueOf(0));
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.setBoolean("Saddle", hasSaddle());
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		setSaddle(nbttagcompound.getBoolean("Saddle"));
	}

	protected String aQ() {
		return "mob.pig";
	}

	protected String aR() {
		return "mob.pig";
	}

	protected String aS() {
		return "mob.pigdeath";
	}

	public boolean c(EntityHuman entityhuman) {
		if (super.c(entityhuman))
			return true;
		if ((hasSaddle()) && (!this.world.isStatic) && ((this.passenger == null) || (this.passenger == entityhuman))) {
			entityhuman.mount(this);
			return true;
		}
		return false;
	}

	protected int getLootId()
	{
		return isBurning() ? Item.GRILLED_PORK.id : Item.PORK.id;
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();
		int j = this.random.nextInt(3) + 1 + this.random.nextInt(1 + i);

		if (j > 0) {
			if (isBurning())
				loot.add(new ItemStack(Item.GRILLED_PORK.id, j));
			else {
				loot.add(new ItemStack(Item.PORK.id, j));
			}
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}

	public boolean hasSaddle()
	{
		return (this.datawatcher.getByte(16) & 0x1) != 0;
	}

	public void setSaddle(boolean flag) {
		if (flag)
			this.datawatcher.watch(16, Byte.valueOf(1));
		else
			this.datawatcher.watch(16, Byte.valueOf(0));
	}

	public void a(EntityLightning entitylightning)
	{
		if (!this.world.isStatic) {
			EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);

			if (CraftEventFactory.callPigZapEvent(this, entitylightning, entitypigzombie).isCancelled()) {
				return;
			}

			entitypigzombie.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);

			this.world.addEntity(entitypigzombie, CreatureSpawnEvent.SpawnReason.LIGHTNING);
			die();
		}
	}

	protected void a(float f) {
		super.a(f);
		if ((f > 5.0F) && ((this.passenger instanceof EntityHuman)))
			((EntityHuman)this.passenger).a(AchievementList.u);
	}

	public EntityAnimal createChild(EntityAnimal entityanimal)
	{
		return new EntityPig(this.world);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityPig
 * JD-Core Version:		0.6.0
 */