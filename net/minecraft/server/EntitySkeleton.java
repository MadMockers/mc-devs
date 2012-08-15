package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.plugin.PluginManager;

public class EntitySkeleton extends EntityMonster
{
	private static final ItemStack d = new ItemStack(Item.BOW, 1);

	public EntitySkeleton(World world) {
		super(world);
		this.texture = "/mob/skeleton.png";
		this.bw = 0.25F;
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new PathfinderGoalRestrictSun(this));
		this.goalSelector.a(3, new PathfinderGoalFleeSun(this, this.bw));
		this.goalSelector.a(4, new PathfinderGoalArrowAttack(this, this.bw, 1, 60));
		this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, this.bw));
		this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 16.0F, 0, true));
	}

	public boolean aV() {
		return true;
	}

	public int getMaxHealth() {
		return 20;
	}

	protected String aQ() {
		return "mob.skeleton";
	}

	protected String aR() {
		return "mob.skeletonhurt";
	}

	protected String aS() {
		return "mob.skeletonhurt";
	}

	public EnumMonsterType getMonsterType() {
		return EnumMonsterType.UNDEAD;
	}

	public void d() {
		if ((this.world.r()) && (!this.world.isStatic)) {
			float f = c(1.0F);

			if ((f > 0.5F) && (this.world.j(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ))) && (this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F))
			{
				EntityCombustEvent event = new EntityCombustEvent(getBukkitEntity(), 8);
				this.world.getServer().getPluginManager().callEvent(event);

				if (!event.isCancelled()) {
					setOnFire(event.getDuration());
				}
			}

		}

		super.d();
	}

	public void die(DamageSource damagesource) {
		super.die(damagesource);
		if (((damagesource.f() instanceof EntityArrow)) && ((damagesource.getEntity() instanceof EntityHuman))) {
			EntityHuman entityhuman = (EntityHuman)damagesource.getEntity();
			double d0 = entityhuman.locX - this.locX;
			double d1 = entityhuman.locZ - this.locZ;

			if (d0 * d0 + d1 * d1 >= 2500.0D)
				entityhuman.a(AchievementList.v);
		}
	}

	protected int getLootId()
	{
		return Item.ARROW.id;
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();
		int j = this.random.nextInt(3 + i);

		int count = this.random.nextInt(3 + i);
		if (count > 0) {
			loot.add(new org.bukkit.inventory.ItemStack(Material.ARROW, count));
		}

		count = this.random.nextInt(3 + i);
		if (count > 0) {
			loot.add(new org.bukkit.inventory.ItemStack(Material.BONE, count));
		}

		if (this.lastDamageByPlayerTime > 0) {
			int k = this.random.nextInt(200) - i;

			if (k < 5) {
				ItemStack itemstack = l(k <= 0 ? 1 : 0);
				if (itemstack != null) {
					loot.add(new CraftItemStack(itemstack));
				}
			}
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}

	protected ItemStack l(int i)
	{
		if (i > 0) {
			ItemStack itemstack = new ItemStack(Item.BOW);

			EnchantmentManager.a(this.random, itemstack, 5);
			return itemstack;
		}
		return new ItemStack(Item.BOW.id, 1, 0);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntitySkeleton
 * JD-Core Version:		0.6.0
 */