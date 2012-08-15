package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.TrigMath;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.plugin.PluginManager;

public abstract class EntityLiving extends Entity
{
	public int maxNoDamageTicks = 20;
	public float ao;
	public float ap;
	public float aq = 0.0F;
	public float ar = 0.0F;
	public float as = 0.0F;
	public float at = 0.0F;
	protected float au;
	protected float av;
	protected float aw;
	protected float ax;
	protected boolean ay = true;
	protected String texture = "/mob/char.png";
	protected boolean aA = true;
	protected float aB = 0.0F;
	protected String aC = null;
	protected float aD = 1.0F;
	protected int aE = 0;
	protected float aF = 0.0F;
	public float aG = 0.1F;
	public float aH = 0.02F;
	public float aI;
	public float aJ;
	protected int health = getMaxHealth();
	public int aL;
	protected int aM;
	private int a;
	public int hurtTicks;
	public int aO;
	public float aP = 0.0F;
	public int deathTicks = 0;
	public int attackTicks = 0;
	public float aS;
	public float aT;
	protected boolean aU = false;
	protected int aV;
	public int aW = -1;
	public float aX = (float)(Math.random() * 0.8999999761581421D + 0.1000000014901161D);
	public float aY;
	public float aZ;
	public float ba;
	public EntityHuman killer = null;
	protected int lastDamageByPlayerTime = 0;
	public EntityLiving lastDamager = null;
	private int c = 0;
	private EntityLiving d = null;
	public int bd = 0;
	public int be = 0;
	public HashMap effects = new HashMap();
	public boolean updateEffects = true;
	private int f;
	private ControllerLook lookController;
	private ControllerMove moveController;
	private ControllerJump jumpController;
	private EntityAIBodyControl senses;
	private Navigation navigation;
	protected final PathfinderGoalSelector goalSelector;
	protected final PathfinderGoalSelector targetSelector;
	private EntityLiving bz;
	private EntitySenses bA;
	private float bB;
	private ChunkCoordinates bC = new ChunkCoordinates(0, 0, 0);
	private float bD = -1.0F;
	protected int bi;
	protected double bj;
	protected double bk;
	protected double bl;
	protected double bm;
	protected double bn;
	float bo = 0.0F;
	public int lastDamage = 0;
	protected int bq = 0;
	protected float br;
	protected float bs;
	protected float bt;
	protected boolean bu = false;
	protected float bv = 0.0F;
	protected float bw = 0.7F;
	private int bE = 0;
	private Entity bF;
	protected int bx = 0;
	public int expToDrop = 0;
	public int maxAirTicks = 300;

	public EntityLiving(World world) {
		super(world);
		this.m = true;
		this.goalSelector = new PathfinderGoalSelector((world != null) && (world.methodProfiler != null) ? world.methodProfiler : null);
		this.targetSelector = new PathfinderGoalSelector((world != null) && (world.methodProfiler != null) ? world.methodProfiler : null);
		this.lookController = new ControllerLook(this);
		this.moveController = new ControllerMove(this);
		this.jumpController = new ControllerJump(this);
		this.senses = new EntityAIBodyControl(this);
		this.navigation = new Navigation(this, world, 16.0F);
		this.bA = new EntitySenses(this);
		this.ap = ((float)(Math.random() + 1.0D) * 0.01F);
		setPosition(this.locX, this.locY, this.locZ);
		this.ao = ((float)Math.random() * 12398.0F);
		this.yaw = (float)(Math.random() * 3.141592741012573D * 2.0D);
		this.as = this.yaw;
		this.W = 0.5F;
	}

	public ControllerLook getControllerLook() {
		return this.lookController;
	}

	public ControllerMove getControllerMove() {
		return this.moveController;
	}

	public ControllerJump getControllerJump() {
		return this.jumpController;
	}

	public Navigation getNavigation() {
		return this.navigation;
	}

	public EntitySenses at() {
		return this.bA;
	}

	public Random au() {
		return this.random;
	}

	public EntityLiving av() {
		return this.lastDamager;
	}

	public EntityLiving aw() {
		return this.d;
	}

	public void j(Entity entity) {
		if ((entity instanceof EntityLiving))
			this.d = ((EntityLiving)entity);
	}

	public int ax()
	{
		return this.bq;
	}

	public float am() {
		return this.as;
	}

	public float ay() {
		return this.bB;
	}

	public void e(float f) {
		this.bB = f;
		f(f);
	}

	public boolean k(Entity entity) {
		j(entity);
		return false;
	}

	public EntityLiving az() {
		return this.bz;
	}

	public void b(EntityLiving entityliving) {
		this.bz = entityliving;
	}

	public boolean a(Class oclass) {
		return (EntityCreeper.class != oclass) && (EntityGhast.class != oclass);
	}
	public void aA() {
	}

	public boolean aB() {
		return d(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
	}

	public boolean d(int i, int j, int k) {
		return this.bD == -1.0F;
	}

	public void b(int i, int j, int k, int l) {
		this.bC.b(i, j, k);
		this.bD = l;
	}

	public ChunkCoordinates aC() {
		return this.bC;
	}

	public float aD() {
		return this.bD;
	}

	public void aE() {
		this.bD = -1.0F;
	}

	public boolean aF() {
		return this.bD != -1.0F;
	}

	public void c(EntityLiving entityliving) {
		this.lastDamager = entityliving;
		this.c = (this.lastDamager != null ? 60 : 0);
	}

	protected void a() {
		this.datawatcher.a(8, Integer.valueOf(this.f));
	}

	public boolean l(Entity entity) {
		return this.world.a(Vec3D.a().create(this.locX, this.locY + getHeadHeight(), this.locZ), Vec3D.a().create(entity.locX, entity.locY + entity.getHeadHeight(), entity.locZ)) == null;
	}

	public boolean L() {
		return !this.dead;
	}

	public boolean M() {
		return !this.dead;
	}

	public float getHeadHeight() {
		return this.length * 0.85F;
	}

	public int aG() {
		return 80;
	}

	public void aH() {
		String s = aQ();

		if (s != null)
			this.world.makeSound(this, s, aP(), i());
	}

	public void z()
	{
		this.aI = this.aJ;
		super.z();

		if ((isAlive()) && (this.random.nextInt(1000) < this.a++)) {
			this.a = (-aG());
			aH();
		}

		if ((isAlive()) && (inBlock()) && (!(this instanceof EntityEnderDragon))) {
			EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.SUFFOCATION, 1);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {
				event.getEntity().setLastDamageCause(event);
				damageEntity(DamageSource.STUCK, event.getDamage());
			}

		}

		if ((isFireproof()) || (this.world.isStatic)) {
			extinguish();
		}

		if ((isAlive()) && (a(Material.WATER)) && (!aU()) && (!this.effects.containsKey(Integer.valueOf(MobEffectList.WATER_BREATHING.id)))) {
			setAirTicks(h(getAirTicks()));
			if (getAirTicks() == -20) {
				setAirTicks(0);

				for (int i = 0; i < 8; i++) {
					float f = this.random.nextFloat() - this.random.nextFloat();
					float f1 = this.random.nextFloat() - this.random.nextFloat();
					float f2 = this.random.nextFloat() - this.random.nextFloat();

					this.world.a("bubble", this.locX + f, this.locY + f1, this.locZ + f2, this.motX, this.motY, this.motZ);
				}

				EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.DROWNING, 2);
				this.world.getServer().getPluginManager().callEvent(event);

				if ((!event.isCancelled()) && (event.getDamage() != 0)) {
					event.getEntity().setLastDamageCause(event);
					damageEntity(DamageSource.DROWN, event.getDamage());
				}

			}

			extinguish();
		}
		else if (getAirTicks() != 300) {
			setAirTicks(this.maxAirTicks);
		}

		this.aS = this.aT;
		if (this.attackTicks > 0) {
			this.attackTicks -= 1;
		}

		if (this.hurtTicks > 0) {
			this.hurtTicks -= 1;
		}

		if (this.noDamageTicks > 0) {
			this.noDamageTicks -= 1;
		}

		if (this.health <= 0) {
			aI();
		}

		if (this.lastDamageByPlayerTime > 0)
			this.lastDamageByPlayerTime -= 1;
		else {
			this.killer = null;
		}

		if ((this.d != null) && (!this.d.isAlive())) {
			this.d = null;
		}

		if (this.lastDamager != null) {
			if (!this.lastDamager.isAlive())
				c((EntityLiving)null);
			else if (this.c > 0)
				this.c -= 1;
			else {
				c((EntityLiving)null);
			}
		}

		bo();
		this.ax = this.aw;
		this.ar = this.aq;
		this.at = this.as;
		this.lastYaw = this.yaw;
		this.lastPitch = this.pitch;
	}

	public int getExpReward()
	{
		int exp = getExpValue(this.killer);

		if ((!this.world.isStatic) && ((this.lastDamageByPlayerTime > 0) || (alwaysGivesExp())) && (!isBaby())) {
			return exp;
		}
		return 0;
	}

	protected void aI()
	{
		this.deathTicks += 1;
		if ((this.deathTicks >= 20) && (!this.dead))
		{
			int i = this.expToDrop;
			while (i > 0) {
				int j = EntityExperienceOrb.getOrbValue(i);

				i -= j;
				this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
			}

			die();

			for (i = 0; i < 20; i++) {
				double d0 = this.random.nextGaussian() * 0.02D;
				double d1 = this.random.nextGaussian() * 0.02D;
				double d2 = this.random.nextGaussian() * 0.02D;

				this.world.a("explode", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width, this.locY + this.random.nextFloat() * this.length, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width, d0, d1, d2);
			}
		}
	}

	protected int h(int i) {
		return i - 1;
	}

	protected int getExpValue(EntityHuman entityhuman) {
		return this.aV;
	}

	protected boolean alwaysGivesExp() {
		return false;
	}

	public void aK() {
		for (int i = 0; i < 20; i++) {
			double d0 = this.random.nextGaussian() * 0.02D;
			double d1 = this.random.nextGaussian() * 0.02D;
			double d2 = this.random.nextGaussian() * 0.02D;
			double d3 = 10.0D;

			this.world.a("explode", this.locX + this.random.nextFloat() * this.width * 2.0F - this.width - d0 * d3, this.locY + this.random.nextFloat() * this.length - d1 * d3, this.locZ + this.random.nextFloat() * this.width * 2.0F - this.width - d2 * d3, d0, d1, d2);
		}
	}

	public void U() {
		super.U();
		this.au = this.av;
		this.av = 0.0F;
		this.fallDistance = 0.0F;
	}

	public void h_() {
		super.h_();
		if (this.bd > 0) {
			if (this.be <= 0) {
				this.be = 60;
			}

			this.be -= 1;
			if (this.be <= 0) {
				this.bd -= 1;
			}
		}

		d();
		double d0 = this.locX - this.lastX;
		double d1 = this.locZ - this.lastZ;
		float f = (float)(d0 * d0 + d1 * d1);
		float f1 = this.aq;
		float f2 = 0.0F;

		this.au = this.av;
		float f3 = 0.0F;

		if (f > 0.0025F) {
			f3 = 1.0F;
			f2 = (float)Math.sqrt(f) * 3.0F;

			f1 = (float)TrigMath.atan2(d1, d0) * 180.0F / 3.141593F - 90.0F;
		}

		if (this.aJ > 0.0F) {
			f1 = this.yaw;
		}

		if (!this.onGround) {
			f3 = 0.0F;
		}

		this.av += (f3 - this.av) * 0.3F;
		this.world.methodProfiler.a("headTurn");
		if (aV()) {
			this.senses.a();
		} else {
			float f4 = MathHelper.g(f1 - this.aq);

			this.aq += f4 * 0.3F;
			float f5 = MathHelper.g(this.yaw - this.aq);
			boolean flag = (f5 < -90.0F) || (f5 >= 90.0F);

			if (f5 < -75.0F) {
				f5 = -75.0F;
			}

			if (f5 >= 75.0F) {
				f5 = 75.0F;
			}

			this.aq = (this.yaw - f5);
			if (f5 * f5 > 2500.0F) {
				this.aq += f5 * 0.2F;
			}

			if (flag) {
				f2 *= -1.0F;
			}
		}

		this.world.methodProfiler.b();
		this.world.methodProfiler.a("rangeChecks");

		while (this.yaw - this.lastYaw < -180.0F) {
			this.lastYaw -= 360.0F;
		}

		while (this.yaw - this.lastYaw >= 180.0F) {
			this.lastYaw += 360.0F;
		}

		while (this.aq - this.ar < -180.0F) {
			this.ar -= 360.0F;
		}

		while (this.aq - this.ar >= 180.0F) {
			this.ar += 360.0F;
		}

		while (this.pitch - this.lastPitch < -180.0F) {
			this.lastPitch -= 360.0F;
		}

		while (this.pitch - this.lastPitch >= 180.0F) {
			this.lastPitch += 360.0F;
		}

		while (this.as - this.at < -180.0F) {
			this.at -= 360.0F;
		}

		while (this.as - this.at >= 180.0F) {
			this.at += 360.0F;
		}

		this.world.methodProfiler.b();
		this.aw += f2;
	}

	public void heal(int i)
	{
		heal(i, EntityRegainHealthEvent.RegainReason.CUSTOM);
	}

	public void heal(int i, EntityRegainHealthEvent.RegainReason regainReason) {
		if (this.health > 0) {
			EntityRegainHealthEvent event = new EntityRegainHealthEvent(getBukkitEntity(), i, regainReason);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {
				this.health += event.getAmount();
			}

			if (this.health > getMaxHealth()) {
				this.health = getMaxHealth();
			}

			this.noDamageTicks = (this.maxNoDamageTicks / 2);
		}
	}

	public abstract int getMaxHealth();

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int i) {
		this.health = i;
		if (i > getMaxHealth())
			i = getMaxHealth();
	}

	public boolean damageEntity(DamageSource damagesource, int i)
	{
		if (this.world.isStatic) {
			return false;
		}
		this.bq = 0;
		if (this.health <= 0)
			return false;
		if ((damagesource.k()) && (hasEffect(MobEffectList.FIRE_RESISTANCE))) {
			return false;
		}
		this.aZ = 1.5F;
		boolean flag = true;

		if ((damagesource instanceof EntityDamageSource)) {
			EntityDamageEvent event = CraftEventFactory.handleEntityDamageEvent(this, damagesource, i);
			if (event.isCancelled()) {
				return false;
			}
			i = event.getDamage();
		}

		if (this.noDamageTicks > this.maxNoDamageTicks / 2.0F) {
			if (i <= this.lastDamage) {
				return false;
			}

			d(damagesource, i - this.lastDamage);
			this.lastDamage = i;
			flag = false;
		} else {
			this.lastDamage = i;
			this.aL = this.health;
			this.noDamageTicks = this.maxNoDamageTicks;
			d(damagesource, i);
			this.hurtTicks = (this.aO = 10);
		}

		this.aP = 0.0F;
		Entity entity = damagesource.getEntity();

		if (entity != null) {
			if ((entity instanceof EntityLiving)) {
				c((EntityLiving)entity);
			}

			if ((entity instanceof EntityHuman)) {
				this.lastDamageByPlayerTime = 60;
				this.killer = ((EntityHuman)entity);
			} else if ((entity instanceof EntityWolf)) {
				EntityWolf entitywolf = (EntityWolf)entity;

				if (entitywolf.isTamed()) {
					this.lastDamageByPlayerTime = 60;
					this.killer = null;
				}
			}
		}

		if (flag) {
			this.world.broadcastEntityEffect(this, 2);
			if ((damagesource != DamageSource.DROWN) && (damagesource != DamageSource.EXPLOSION2)) {
				K();
			}

			if (entity != null) {
				double d0 = entity.locX - this.locX;

				for (double d1 = entity.locZ - this.locZ; d0 * d0 + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D) {
					d0 = (Math.random() - Math.random()) * 0.01D;
				}

				this.aP = ((float)(Math.atan2(d1, d0) * 180.0D / 3.141592741012573D) - this.yaw);
				a(entity, i, d0, d1);
			} else {
				this.aP = ((int)(Math.random() * 2.0D) * 180);
			}
		}

		if (this.health <= 0) {
			if (flag) {
				this.world.makeSound(this, aS(), aP(), i());
			}

			die(damagesource);
		} else if (flag) {
			this.world.makeSound(this, aR(), aP(), i());
		}

		return true;
	}

	private float i()
	{
		return isBaby() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
	}

	public int aO() {
		return 0;
	}
	protected void k(int i) {
	}

	protected int b(DamageSource damagesource, int i) {
		if (!damagesource.ignoresArmor()) {
			int j = 25 - aO();
			int k = i * j + this.aM;

			k(i);
			i = k / 25;
			this.aM = (k % 25);
		}

		return i;
	}

	protected int c(DamageSource damagesource, int i) {
		if (hasEffect(MobEffectList.RESISTANCE)) {
			int j = (getEffect(MobEffectList.RESISTANCE).getAmplifier() + 1) * 5;
			int k = 25 - j;
			int l = i * k + this.aM;

			i = l / 25;
			this.aM = (l % 25);
		}

		return i;
	}

	protected void d(DamageSource damagesource, int i) {
		i = b(damagesource, i);
		i = c(damagesource, i);
		this.health -= i;
	}

	protected float aP() {
		return 1.0F;
	}

	protected String aQ() {
		return null;
	}

	protected String aR() {
		return "damage.hurtflesh";
	}

	protected String aS() {
		return "damage.hurtflesh";
	}

	public void a(Entity entity, int i, double d0, double d1) {
		this.al = true;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1);
		float f1 = 0.4F;

		this.motX /= 2.0D;
		this.motY /= 2.0D;
		this.motZ /= 2.0D;
		this.motX -= d0 / f * f1;
		this.motY += f1;
		this.motZ -= d1 / f * f1;
		if (this.motY > 0.4000000059604645D)
			this.motY = 0.4000000059604645D;
	}

	public void die(DamageSource damagesource)
	{
		Entity entity = damagesource.getEntity();

		if ((this.aE >= 0) && (entity != null)) {
			entity.c(this, this.aE);
		}

		if (entity != null) {
			entity.a(this);
		}

		this.aU = true;
		if (!this.world.isStatic) {
			int i = 0;

			if ((entity instanceof EntityHuman)) {
				i = EnchantmentManager.getBonusMonsterLootEnchantmentLevel(((EntityHuman)entity).inventory);
			}

			if (!isBaby()) {
				dropDeathLoot(this.lastDamageByPlayerTime > 0, i);
			}
			else
			{
				CraftEventFactory.callEntityDeathEvent(this);
			}
		}

		this.world.broadcastEntityEffect(this, 3);
	}

	protected ItemStack l(int i)
	{
		return null;
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();
		int j = getLootId();

		if (j > 0) {
			int k = this.random.nextInt(3);

			if (i > 0) {
				k += this.random.nextInt(i + 1);
			}

			if (k > 0) {
				loot.add(new org.bukkit.inventory.ItemStack(j, k));
			}

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

	protected int getLootId()
	{
		return 0;
	}

	protected void a(float f) {
		super.a(f);
		int i = MathHelper.f(f - 3.0F);

		if (i > 0)
		{
			EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.FALL, i);
			this.world.getServer().getPluginManager().callEvent(event);

			if ((!event.isCancelled()) && (event.getDamage() != 0)) {
				i = event.getDamage();

				if (i > 4)
					this.world.makeSound(this, "damage.fallbig", 1.0F, 1.0F);
				else {
					this.world.makeSound(this, "damage.fallsmall", 1.0F, 1.0F);
				}

				getBukkitEntity().setLastDamageCause(event);
				damageEntity(DamageSource.FALL, i);
			}

			int j = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.locY - 0.2000000029802322D - this.height), MathHelper.floor(this.locZ));

			if (j > 0) {
				StepSound stepsound = Block.byId[j].stepSound;

				this.world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.5F, stepsound.getVolume2() * 0.75F);
			}
		}
	}

	public void e(float f, float f1)
	{
		if ((H()) && ((!(this instanceof EntityHuman)) || (!((EntityHuman)this).abilities.isFlying))) {
			double d0 = this.locY;
			a(f, f1, aV() ? 0.04F : 0.02F);
			move(this.motX, this.motY, this.motZ);
			this.motX *= 0.800000011920929D;
			this.motY *= 0.800000011920929D;
			this.motZ *= 0.800000011920929D;
			this.motY -= 0.02D;
			if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d0, this.motZ)))
				this.motY = 0.300000011920929D;
		}
		else if ((J()) && ((!(this instanceof EntityHuman)) || (!((EntityHuman)this).abilities.isFlying))) {
			double d0 = this.locY;
			a(f, f1, 0.02F);
			move(this.motX, this.motY, this.motZ);
			this.motX *= 0.5D;
			this.motY *= 0.5D;
			this.motZ *= 0.5D;
			this.motY -= 0.02D;
			if ((this.positionChanged) && (c(this.motX, this.motY + 0.6000000238418579D - this.locY + d0, this.motZ)))
				this.motY = 0.300000011920929D;
		}
		else {
			float f2 = 0.91F;

			if (this.onGround) {
				f2 = 0.5460001F;
				int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));

				if (i > 0) {
					f2 = Block.byId[i].frictionFactor * 0.91F;
				}
			}

			float f3 = 0.1627714F / (f2 * f2 * f2);
			float f4;
			if (this.onGround)
			{
				float f4;
				float f4;
				if (aV())
					f4 = ay();
				else {
					f4 = this.aG;
				}

				f4 *= f3;
			} else {
				f4 = this.aH;
			}

			a(f, f1, f4);
			f2 = 0.91F;
			if (this.onGround) {
				f2 = 0.5460001F;
				int j = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));

				if (j > 0) {
					f2 = Block.byId[j].frictionFactor * 0.91F;
				}
			}

			if (f_()) {
				float f5 = 0.15F;

				if (this.motX < -f5) {
					this.motX = (-f5);
				}

				if (this.motX > f5) {
					this.motX = f5;
				}

				if (this.motZ < -f5) {
					this.motZ = (-f5);
				}

				if (this.motZ > f5) {
					this.motZ = f5;
				}

				this.fallDistance = 0.0F;
				if (this.motY < -0.15D) {
					this.motY = -0.15D;
				}

				boolean flag = (isSneaking()) && ((this instanceof EntityHuman));

				if ((flag) && (this.motY < 0.0D)) {
					this.motY = 0.0D;
				}
			}

			move(this.motX, this.motY, this.motZ);
			if ((this.positionChanged) && (f_())) {
				this.motY = 0.2D;
			}

			this.motY -= 0.08D;
			this.motY *= 0.9800000190734863D;
			this.motX *= f2;
			this.motZ *= f2;
		}

		this.aY = this.aZ;
		double d0 = this.locX - this.lastX;
		double d1 = this.locZ - this.lastZ;
		float f6 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		this.aZ += (f6 - this.aZ) * 0.4F;
		this.ba += this.aZ;
	}

	public boolean f_() {
		int i = MathHelper.floor(this.locX);
		int j = MathHelper.floor(this.boundingBox.b);
		int k = MathHelper.floor(this.locZ);
		int l = this.world.getTypeId(i, j, k);

		return (l == Block.LADDER.id) || (l == Block.VINE.id);
	}

	public void b(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("Health", (short)this.health);
		nbttagcompound.setShort("HurtTime", (short)this.hurtTicks);
		nbttagcompound.setShort("DeathTime", (short)this.deathTicks);
		nbttagcompound.setShort("AttackTime", (short)this.attackTicks);
		if (!this.effects.isEmpty()) {
			NBTTagList nbttaglist = new NBTTagList();
			Iterator iterator = this.effects.values().iterator();

			while (iterator.hasNext()) {
				MobEffect mobeffect = (MobEffect)iterator.next();
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();

				nbttagcompound1.setByte("Id", (byte)mobeffect.getEffectId());
				nbttagcompound1.setByte("Amplifier", (byte)mobeffect.getAmplifier());
				nbttagcompound1.setInt("Duration", mobeffect.getDuration());
				nbttaglist.add(nbttagcompound1);
			}

			nbttagcompound.set("ActiveEffects", nbttaglist);
		}
	}

	public void a(NBTTagCompound nbttagcompound) {
		if (this.health < -32768) {
			this.health = -32768;
		}

		this.health = nbttagcompound.getShort("Health");
		if (!nbttagcompound.hasKey("Health")) {
			this.health = getMaxHealth();
		}

		this.hurtTicks = nbttagcompound.getShort("HurtTime");
		this.deathTicks = nbttagcompound.getShort("DeathTime");
		this.attackTicks = nbttagcompound.getShort("AttackTime");
		if (nbttagcompound.hasKey("ActiveEffects")) {
			NBTTagList nbttaglist = nbttagcompound.getList("ActiveEffects");

			for (int i = 0; i < nbttaglist.size(); i++) {
				NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
				byte b0 = nbttagcompound1.getByte("Id");
				byte b1 = nbttagcompound1.getByte("Amplifier");
				int j = nbttagcompound1.getInt("Duration");

				this.effects.put(Integer.valueOf(b0), new MobEffect(b0, j, b1));
			}
		}
	}

	public boolean isAlive() {
		return (!this.dead) && (this.health > 0);
	}

	public boolean aU() {
		return false;
	}

	public void f(float f) {
		this.bs = f;
	}

	public void d(boolean flag) {
		this.bu = flag;
	}

	public void d() {
		if (this.bE > 0) {
			this.bE -= 1;
		}

		if (this.bi > 0) {
			double d0 = this.locX + (this.bj - this.locX) / this.bi;
			double d1 = this.locY + (this.bk - this.locY) / this.bi;
			double d2 = this.locZ + (this.bl - this.locZ) / this.bi;
			double d3 = MathHelper.g(this.bm - this.yaw);

			this.yaw = (float)(this.yaw + d3 / this.bi);
			this.pitch = (float)(this.pitch + (this.bn - this.pitch) / this.bi);
			this.bi -= 1;
			setPosition(d0, d1, d2);
			b(this.yaw, this.pitch);
		}

		if (Math.abs(this.motX) < 0.005D) {
			this.motX = 0.0D;
		}

		if (Math.abs(this.motY) < 0.005D) {
			this.motY = 0.0D;
		}

		if (Math.abs(this.motZ) < 0.005D) {
			this.motZ = 0.0D;
		}

		if (aX()) {
			this.bu = false;
			this.br = 0.0F;
			this.bs = 0.0F;
			this.bt = 0.0F;
		} else if (aW()) {
			if (aV())
			{
				bc();
			}
			else
			{
				be();

				this.as = this.yaw;
			}

		}

		if (this.bu) {
			if ((!H()) && (!J())) {
				if ((this.onGround) && (this.bE == 0)) {
					aZ();
					this.bE = 10;
				}
			}
			else this.motY += 0.03999999910593033D;
		}
		else {
			this.bE = 0;
		}

		this.br *= 0.98F;
		this.bs *= 0.98F;
		this.bt *= 0.9F;
		float f = this.aG;

		this.aG *= bs();
		e(this.br, this.bs);
		this.aG = f;

		if (!this.world.isStatic) {
			List list = this.world.getEntities(this, this.boundingBox.grow(0.2000000029802322D, 0.0D, 0.2000000029802322D));

			if ((list != null) && (!list.isEmpty())) {
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					Entity entity = (Entity)iterator.next();

					if (entity.M())
						entity.collide(this);
				}
			}
		}
	}

	protected boolean aV()
	{
		return false;
	}

	protected boolean aW() {
		return !this.world.isStatic;
	}

	protected boolean aX() {
		return this.health <= 0;
	}

	public boolean aY() {
		return false;
	}

	protected void aZ() {
		this.motY = 0.4199999868869782D;
		if (hasEffect(MobEffectList.JUMP)) {
			this.motY += (getEffect(MobEffectList.JUMP).getAmplifier() + 1) * 0.1F;
		}

		if (isSprinting()) {
			float f = this.yaw * 0.01745329F;

			this.motX -= MathHelper.sin(f) * 0.2F;
			this.motZ += MathHelper.cos(f) * 0.2F;
		}

		this.al = true;
	}

	protected boolean ba() {
		return true;
	}

	protected void bb() {
		EntityHuman entityhuman = this.world.findNearbyPlayer(this, -1.0D);

		if (entityhuman != null) {
			double d0 = entityhuman.locX - this.locX;
			double d1 = entityhuman.locY - this.locY;
			double d2 = entityhuman.locZ - this.locZ;
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;

			if ((ba()) && (d3 > 16384.0D)) {
				die();
			}

			if ((this.bq > 600) && (this.random.nextInt(800) == 0) && (d3 > 1024.0D) && (ba()))
				die();
			else if (d3 < 1024.0D)
				this.bq = 0;
		}
	}

	protected void bc()
	{
		this.bq += 1;

		bb();

		this.bA.a();

		this.targetSelector.a();

		this.goalSelector.a();

		this.navigation.e();

		bd();

		this.moveController.c();

		this.lookController.a();

		this.jumpController.b();
	}

	protected void bd()
	{
	}

	protected void be() {
		this.bq += 1;
		bb();
		this.br = 0.0F;
		this.bs = 0.0F;
		float f = 8.0F;

		if (this.random.nextFloat() < 0.02F) {
			EntityHuman entityhuman = this.world.findNearbyPlayer(this, f);

			if (entityhuman != null) {
				this.bF = entityhuman;
				this.bx = (10 + this.random.nextInt(20));
			} else {
				this.bt = ((this.random.nextFloat() - 0.5F) * 20.0F);
			}
		}

		if (this.bF != null) {
			a(this.bF, 10.0F, bf());
			if ((this.bx-- <= 0) || (this.bF.dead) || (this.bF.e(this) > f * f))
				this.bF = null;
		}
		else {
			if (this.random.nextFloat() < 0.05F) {
				this.bt = ((this.random.nextFloat() - 0.5F) * 20.0F);
			}

			this.yaw += this.bt;
			this.pitch = this.bv;
		}

		boolean flag = H();
		boolean flag1 = J();

		if ((flag) || (flag1))
			this.bu = (this.random.nextFloat() < 0.8F);
	}

	public int bf()
	{
		return 40;
	}

	public void a(Entity entity, float f, float f1) {
		double d0 = entity.locX - this.locX;
		double d1 = entity.locZ - this.locZ;
		double d2;
		double d2;
		if ((entity instanceof EntityLiving)) {
			EntityLiving entityliving = (EntityLiving)entity;

			d2 = this.locY + getHeadHeight() - (entityliving.locY + entityliving.getHeadHeight());
		} else {
			d2 = (entity.boundingBox.b + entity.boundingBox.e) / 2.0D - (this.locY + getHeadHeight());
		}

		double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1);
		float f2 = (float)(Math.atan2(d1, d0) * 180.0D / 3.141592741012573D) - 90.0F;
		float f3 = (float)(-(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D));

		this.pitch = (-b(this.pitch, f3, f1));
		this.yaw = b(this.yaw, f2, f);
	}

	private float b(float f, float f1, float f2) {
		float f3 = MathHelper.g(f1 - f);

		if (f3 > f2) {
			f3 = f2;
		}

		if (f3 < -f2) {
			f3 = -f2;
		}

		return f + f3;
	}

	public boolean canSpawn() {
		return (this.world.b(this.boundingBox)) && (this.world.getCubes(this, this.boundingBox).isEmpty()) && (!this.world.containsLiquid(this.boundingBox));
	}

	protected void C()
	{
		EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(null, getBukkitEntity(), EntityDamageEvent.DamageCause.VOID, 4);
		this.world.getServer().getPluginManager().callEvent(event);

		if ((event.isCancelled()) || (event.getDamage() == 0)) {
			return;
		}

		event.getEntity().setLastDamageCause(event);
		damageEntity(DamageSource.OUT_OF_WORLD, event.getDamage());
	}

	public Vec3D Z()
	{
		return i(1.0F);
	}

	public Vec3D i(float f)
	{
		if (f == 1.0F) {
			float f1 = MathHelper.cos(-this.yaw * 0.01745329F - 3.141593F);
			float f2 = MathHelper.sin(-this.yaw * 0.01745329F - 3.141593F);
			float f3 = -MathHelper.cos(-this.pitch * 0.01745329F);
			float f4 = MathHelper.sin(-this.pitch * 0.01745329F);
			return Vec3D.a().create(f2 * f3, f4, f1 * f3);
		}
		float f1 = this.lastPitch + (this.pitch - this.lastPitch) * f;
		float f2 = this.lastYaw + (this.yaw - this.lastYaw) * f;
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);

		return Vec3D.a().create(f4 * f5, f6, f3 * f5);
	}

	public int bl()
	{
		return 4;
	}

	public boolean isSleeping() {
		return false;
	}

	protected void bo() {
		Iterator iterator = this.effects.keySet().iterator();

		while (iterator.hasNext()) {
			Integer integer = (Integer)iterator.next();
			MobEffect mobeffect = (MobEffect)this.effects.get(integer);

			if ((!mobeffect.tick(this)) && (!this.world.isStatic)) {
				iterator.remove();
				c(mobeffect);
			}

		}

		if (this.updateEffects) {
			if (!this.world.isStatic) {
				if (this.effects.isEmpty()) {
					this.datawatcher.watch(8, Integer.valueOf(0));
				} else {
					int i = PotionBrewer.a(this.effects.values());
					this.datawatcher.watch(8, Integer.valueOf(i));
				}
			}

			this.updateEffects = false;
		}

		if (this.random.nextBoolean()) {
			int i = this.datawatcher.getInt(8);
			if (i > 0) {
				double d0 = (i >> 16 & 0xFF) / 255.0D;
				double d1 = (i >> 8 & 0xFF) / 255.0D;
				double d2 = (i >> 0 & 0xFF) / 255.0D;

				this.world.a("mobSpell", this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length - this.height, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, d0, d1, d2);
			}
		}
	}

	public void bp() {
		Iterator iterator = this.effects.keySet().iterator();

		while (iterator.hasNext()) {
			Integer integer = (Integer)iterator.next();
			MobEffect mobeffect = (MobEffect)this.effects.get(integer);

			if (!this.world.isStatic) {
				iterator.remove();
				c(mobeffect);
			}
		}
	}

	public Collection getEffects() {
		return this.effects.values();
	}

	public boolean hasEffect(MobEffectList mobeffectlist) {
		return this.effects.containsKey(Integer.valueOf(mobeffectlist.id));
	}

	public MobEffect getEffect(MobEffectList mobeffectlist) {
		return (MobEffect)this.effects.get(Integer.valueOf(mobeffectlist.id));
	}

	public void addEffect(MobEffect mobeffect) {
		if (e(mobeffect))
			if (this.effects.containsKey(Integer.valueOf(mobeffect.getEffectId()))) {
				((MobEffect)this.effects.get(Integer.valueOf(mobeffect.getEffectId()))).a(mobeffect);
				b((MobEffect)this.effects.get(Integer.valueOf(mobeffect.getEffectId())));
			} else {
				this.effects.put(Integer.valueOf(mobeffect.getEffectId()), mobeffect);
				a(mobeffect);
			}
	}

	public boolean e(MobEffect mobeffect)
	{
		if (getMonsterType() == EnumMonsterType.UNDEAD) {
			int i = mobeffect.getEffectId();

			if ((i == MobEffectList.REGENERATION.id) || (i == MobEffectList.POISON.id)) {
				return false;
			}
		}

		return true;
	}

	public boolean br() {
		return getMonsterType() == EnumMonsterType.UNDEAD;
	}

	protected void a(MobEffect mobeffect) {
		this.updateEffects = true;
	}

	protected void b(MobEffect mobeffect) {
		this.updateEffects = true;
	}

	protected void c(MobEffect mobeffect) {
		this.updateEffects = true;
	}

	protected float bs() {
		float f = 1.0F;

		if (hasEffect(MobEffectList.FASTER_MOVEMENT)) {
			f *= (1.0F + 0.2F * (getEffect(MobEffectList.FASTER_MOVEMENT).getAmplifier() + 1));
		}

		if (hasEffect(MobEffectList.SLOWER_MOVEMENT)) {
			f *= (1.0F - 0.15F * (getEffect(MobEffectList.SLOWER_MOVEMENT).getAmplifier() + 1));
		}

		return f;
	}

	public void enderTeleportTo(double d0, double d1, double d2) {
		setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
	}

	public boolean isBaby() {
		return false;
	}

	public EnumMonsterType getMonsterType() {
		return EnumMonsterType.UNDEFINED;
	}

	public void a(ItemStack itemstack) {
		this.world.makeSound(this, "random.break", 0.8F, 0.8F + this.world.random.nextFloat() * 0.4F);

		for (int i = 0; i < 5; i++) {
			Vec3D vec3d = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

			vec3d.a(-this.pitch * 3.141593F / 180.0F);
			vec3d.b(-this.yaw * 3.141593F / 180.0F);
			Vec3D vec3d1 = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.3D, -this.random.nextFloat() * 0.6D - 0.3D, 0.6D);

			vec3d1.a(-this.pitch * 3.141593F / 180.0F);
			vec3d1.b(-this.yaw * 3.141593F / 180.0F);
			vec3d1 = vec3d1.add(this.locX, this.locY + getHeadHeight(), this.locZ);
			this.world.a("iconcrack_" + itemstack.getItem().id, vec3d1.a, vec3d1.b, vec3d1.c, vec3d.a, vec3d.b + 0.05D, vec3d.c);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityLiving
 * JD-Core Version:		0.6.0
 */