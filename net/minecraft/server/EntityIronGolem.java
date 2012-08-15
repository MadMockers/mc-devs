package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

public class EntityIronGolem extends EntityGolem
{
	private int e = 0;
	Village d = null;
	private int f;
	private int g;

	public EntityIronGolem(World world)
	{
		super(world);
		this.texture = "/mob/villager_golem.png";
		a(1.4F, 2.9F);
		getNavigation().a(true);
		this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 0.25F, true));
		this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.22F, 32.0F));
		this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.16F, true));
		this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 0.16F));
		this.goalSelector.a(5, new PathfinderGoalOfferFlower(this));
		this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.16F));
		this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
		this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityMonster.class, 16.0F, 0, false, true));
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, Byte.valueOf(0));
	}

	public boolean aV() {
		return true;
	}

	protected void bd() {
		if (--this.e <= 0) {
			this.e = (70 + this.random.nextInt(50));
			this.d = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
			if (this.d == null) {
				aE();
			} else {
				ChunkCoordinates chunkcoordinates = this.d.getCenter();

				b(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, this.d.getSize());
			}
		}

		super.bd();
	}

	public int getMaxHealth() {
		return 100;
	}

	protected int h(int i) {
		return i;
	}

	public void d() {
		super.d();
		if (this.f > 0) {
			this.f -= 1;
		}

		if (this.g > 0) {
			this.g -= 1;
		}

		if ((this.motX * this.motX + this.motZ * this.motZ > 2.500000277905201E-007D) && (this.random.nextInt(5) == 0)) {
			int i = MathHelper.floor(this.locX);
			int j = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
			int k = MathHelper.floor(this.locZ);
			int l = this.world.getTypeId(i, j, k);

			if (l > 0)
				this.world.a("tilecrack_" + l, this.locX + (this.random.nextFloat() - 0.5D) * this.width, this.boundingBox.b + 0.1D, this.locZ + (this.random.nextFloat() - 0.5D) * this.width, 4.0D * (this.random.nextFloat() - 0.5D), 0.5D, (this.random.nextFloat() - 0.5D) * 4.0D);
		}
	}

	public boolean a(Class oclass)
	{
		return (q()) && (EntityHuman.class.isAssignableFrom(oclass)) ? false : super.a(oclass);
	}

	public boolean k(Entity entity) {
		this.f = 10;
		this.world.broadcastEntityEffect(this, 4);
		boolean flag = entity.damageEntity(DamageSource.mobAttack(this), 7 + this.random.nextInt(15));

		if (flag) {
			entity.motY += 0.4000000059604645D;
		}

		this.world.makeSound(this, "mob.irongolem.throw", 1.0F, 1.0F);
		return flag;
	}

	public Village n() {
		return this.d;
	}

	public void e(boolean flag) {
		this.g = (flag ? 400 : 0);
		this.world.broadcastEntityEffect(this, 11);
	}

	protected String aQ() {
		return "none";
	}

	protected String aR() {
		return "mob.irongolem.hit";
	}

	protected String aS() {
		return "mob.irongolem.death";
	}

	protected void a(int i, int j, int k, int l) {
		this.world.makeSound(this, "mob.irongolem.walk", 1.0F, 1.0F);
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();
		int j = this.random.nextInt(3);

		if (j > 0) {
			loot.add(new CraftItemStack(Block.RED_ROSE.id, j));
		}

		int k = 3 + this.random.nextInt(3);

		if (k > 0) {
			loot.add(new CraftItemStack(Item.IRON_INGOT.id, k));
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}

	public int p()
	{
		return this.g;
	}

	public boolean q() {
		return (this.datawatcher.getByte(16) & 0x1) != 0;
	}

	public void f(boolean flag) {
		byte b0 = this.datawatcher.getByte(16);

		if (flag)
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x1)));
		else
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityIronGolem
 * JD-Core Version:		0.6.0
 */