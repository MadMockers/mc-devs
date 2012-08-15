package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.TrigMath;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.PluginManager;

public abstract class EntityHuman extends EntityLiving
	implements ICommandListener
{
	public PlayerInventory inventory = new PlayerInventory(this);
	private InventoryEnderChest enderChest = new InventoryEnderChest();
	public Container defaultContainer;
	public Container activeContainer;
	protected FoodMetaData foodData = new FoodMetaData();
	protected int bC = 0;
	public byte bD = 0;
	public int bE = 0;
	public float bF;
	public float bG;
	public boolean bH = false;
	public int bI = 0;
	public String name;
	public int dimension;
	public int bL = 0;
	public double bM;
	public double bN;
	public double bO;
	public double bP;
	public double bQ;
	public double bR;
	public boolean sleeping;
	public boolean fauxSleeping;
	public String spawnWorld = "";
	public ChunkCoordinates bT;
	public int sleepTicks;
	public float bU;
	public float bV;
	private ChunkCoordinates c;
	private ChunkCoordinates d;
	public int bW = 20;
	protected boolean bX = false;
	public float bY;
	public PlayerAbilities abilities = new PlayerAbilities();
	public int oldLevel = -1;
	public int expLevel;
	public int expTotal;
	public float exp;
	private ItemStack e;
	private int f;
	protected float cd = 0.1F;
	protected float ce = 0.02F;
	public EntityFishingHook hookedFish = null;

	public HumanEntity getBukkitEntity()
	{
		return (HumanEntity)super.getBukkitEntity();
	}

	public EntityHuman(World world)
	{
		super(world);
		this.defaultContainer = new ContainerPlayer(this.inventory, !world.isStatic);
		this.activeContainer = this.defaultContainer;
		this.height = 1.62F;
		ChunkCoordinates chunkcoordinates = world.getSpawn();

		setPositionRotation(chunkcoordinates.x + 0.5D, chunkcoordinates.y + 1, chunkcoordinates.z + 0.5D, 0.0F, 0.0F);
		this.aC = "humanoid";
		this.aB = 180.0F;
		this.maxFireTicks = 20;
		this.texture = "/mob/char.png";
	}

	public int getMaxHealth() {
		return 20;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, Byte.valueOf(0));
		this.datawatcher.a(17, Byte.valueOf(0));
	}

	public boolean bw() {
		return this.e != null;
	}

	public void by() {
		if (this.e != null) {
			this.e.b(this.world, this, this.f);
		}

		bz();
	}

	public void bz() {
		this.e = null;
		this.f = 0;
		if (!this.world.isStatic)
			c(false);
	}

	public boolean aY()
	{
		return (bw()) && (Item.byId[this.e.id].b(this.e) == EnumAnimation.d);
	}

	public void h_() {
		if (this.e != null) {
			ItemStack itemstack = this.inventory.getItemInHand();

			if (itemstack == this.e) {
				if ((this.f <= 25) && (this.f % 4 == 0)) {
					c(itemstack, 5);
				}

				if ((--this.f == 0) && (!this.world.isStatic))
					o();
			}
			else {
				bz();
			}
		}

		if (this.bL > 0) {
			this.bL -= 1;
		}

		if (isSleeping()) {
			this.sleepTicks += 1;
			if (this.sleepTicks > 100) {
				this.sleepTicks = 100;
			}

			if (!this.world.isStatic) {
				if (!l())
					a(true, true, false);
				else if (this.world.r())
					a(false, true, true);
			}
		}
		else if (this.sleepTicks > 0) {
			this.sleepTicks += 1;
			if (this.sleepTicks >= 110) {
				this.sleepTicks = 0;
			}
		}

		super.h_();
		if ((!this.world.isStatic) && (this.activeContainer != null) && (!this.activeContainer.c(this))) {
			closeInventory();
			this.activeContainer = this.defaultContainer;
		}

		if ((isBurning()) && (this.abilities.isInvulnerable)) {
			extinguish();
		}

		this.bM = this.bP;
		this.bN = this.bQ;
		this.bO = this.bR;
		double d0 = this.locX - this.bP;
		double d1 = this.locY - this.bQ;
		double d2 = this.locZ - this.bR;
		double d3 = 10.0D;

		if (d0 > d3) {
			this.bM = (this.bP = this.locX);
		}

		if (d2 > d3) {
			this.bO = (this.bR = this.locZ);
		}

		if (d1 > d3) {
			this.bN = (this.bQ = this.locY);
		}

		if (d0 < -d3) {
			this.bM = (this.bP = this.locX);
		}

		if (d2 < -d3) {
			this.bO = (this.bR = this.locZ);
		}

		if (d1 < -d3) {
			this.bN = (this.bQ = this.locY);
		}

		this.bP += d0 * 0.25D;
		this.bR += d2 * 0.25D;
		this.bQ += d1 * 0.25D;
		a(StatisticList.k, 1);
		if (this.vehicle == null) {
			this.d = null;
		}

		if (!this.world.isStatic)
			this.foodData.a(this);
	}

	protected void c(ItemStack itemstack, int i)
	{
		if (itemstack.n() == EnumAnimation.c) {
			this.world.makeSound(this, "random.drink", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
		}

		if (itemstack.n() == EnumAnimation.b) {
			for (int j = 0; j < i; j++) {
				Vec3D vec3d = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

				vec3d.a(-this.pitch * 3.141593F / 180.0F);
				vec3d.b(-this.yaw * 3.141593F / 180.0F);
				Vec3D vec3d1 = Vec3D.a().create((this.random.nextFloat() - 0.5D) * 0.3D, -this.random.nextFloat() * 0.6D - 0.3D, 0.6D);

				vec3d1.a(-this.pitch * 3.141593F / 180.0F);
				vec3d1.b(-this.yaw * 3.141593F / 180.0F);
				vec3d1 = vec3d1.add(this.locX, this.locY + getHeadHeight(), this.locZ);
				this.world.a("iconcrack_" + itemstack.getItem().id, vec3d1.a, vec3d1.b, vec3d1.c, vec3d.a, vec3d.b + 0.05D, vec3d.c);
			}

			this.world.makeSound(this, "random.eat", 0.5F + 0.5F * this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		}
	}

	protected void o() {
		if (this.e != null) {
			c(this.e, 16);
			int i = this.e.count;
			ItemStack itemstack = this.e.b(this.world, this);

			if ((itemstack != this.e) || ((itemstack != null) && (itemstack.count != i))) {
				this.inventory.items[this.inventory.itemInHandIndex] = itemstack;
				if (itemstack.count == 0) {
					this.inventory.items[this.inventory.itemInHandIndex] = null;
				}
			}

			bz();
		}
	}

	protected boolean aX() {
		return (getHealth() <= 0) || (isSleeping());
	}

	public void closeInventory()
	{
		this.activeContainer = this.defaultContainer;
	}

	public void U() {
		double d0 = this.locX;
		double d1 = this.locY;
		double d2 = this.locZ;

		super.U();
		this.bF = this.bG;
		this.bG = 0.0F;
		k(this.locX - d0, this.locY - d1, this.locZ - d2);
	}

	private int k() {
		return hasEffect(MobEffectList.SLOWER_DIG) ? 6 + (1 + getEffect(MobEffectList.SLOWER_DIG).getAmplifier()) * 2 : hasEffect(MobEffectList.FASTER_DIG) ? 6 - (1 + getEffect(MobEffectList.FASTER_DIG).getAmplifier()) * 1 : 6;
	}

	protected void be() {
		int i = k();

		if (this.bH) {
			this.bI += 1;
			if (this.bI >= i) {
				this.bI = 0;
				this.bH = false;
			}
		} else {
			this.bI = 0;
		}

		this.aJ = (this.bI / i);
	}

	public void d() {
		if (this.bC > 0) {
			this.bC -= 1;
		}

		if ((this.world.difficulty == 0) && (getHealth() < getMaxHealth()) && (this.ticksLived % 20 * 12 == 0))
		{
			heal(1, EntityRegainHealthEvent.RegainReason.REGEN);
		}

		this.inventory.k();
		this.bF = this.bG;
		super.d();
		this.aG = this.abilities.b();
		this.aH = this.ce;
		if (isSprinting()) {
			this.aG = (float)(this.aG + this.abilities.b() * 0.3D);
			this.aH = (float)(this.aH + this.ce * 0.3D);
		}

		float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);

		float f1 = (float)TrigMath.atan(-this.motY * 0.2000000029802322D) * 15.0F;

		if (f > 0.1F) {
			f = 0.1F;
		}

		if ((!this.onGround) || (getHealth() <= 0)) {
			f = 0.0F;
		}

		if ((this.onGround) || (getHealth() <= 0)) {
			f1 = 0.0F;
		}

		this.bG += (f - this.bG) * 0.4F;
		this.aT += (f1 - this.aT) * 0.8F;
		if (getHealth() > 0) {
			List list = this.world.getEntities(this, this.boundingBox.grow(1.0D, 0.0D, 1.0D));

			if (list != null) {
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					Entity entity = (Entity)iterator.next();

					if (!entity.dead)
						o(entity);
				}
			}
		}
	}

	private void o(Entity entity)
	{
		entity.b_(this);
	}

	public void die(DamageSource damagesource) {
		super.die(damagesource);
		a(0.2F, 0.2F);
		setPosition(this.locX, this.locY, this.locZ);
		this.motY = 0.1000000014901161D;
		if (this.name.equals("Notch")) {
			a(new ItemStack(Item.APPLE, 1), true);
		}

		this.inventory.m();
		if (damagesource != null) {
			this.motX = (-MathHelper.cos((this.aP + this.yaw) * 3.141593F / 180.0F) * 0.1F);
			this.motZ = (-MathHelper.sin((this.aP + this.yaw) * 3.141593F / 180.0F) * 0.1F);
		} else {
			this.motX = (this.motZ = 0.0D);
		}

		this.height = 0.1F;
		a(StatisticList.y, 1);
	}

	public void c(Entity entity, int i) {
		this.bE += i;
		if ((entity instanceof EntityHuman))
			a(StatisticList.A, 1);
		else
			a(StatisticList.z, 1);
	}

	protected int h(int i)
	{
		int j = EnchantmentManager.getOxygenEnchantmentLevel(this.inventory);

		return (j > 0) && (this.random.nextInt(j + 1) > 0) ? i : super.h(i);
	}

	public EntityItem bB() {
		return a(this.inventory.splitStack(this.inventory.itemInHandIndex, 1), false);
	}

	public EntityItem drop(ItemStack itemstack) {
		return a(itemstack, false);
	}

	public EntityItem a(ItemStack itemstack, boolean flag) {
		if (itemstack == null) {
			return null;
		}
		EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY - 0.300000011920929D + getHeadHeight(), this.locZ, itemstack);

		entityitem.pickupDelay = 40;
		float f = 0.1F;

		if (flag) {
			float f1 = this.random.nextFloat() * 0.5F;
			float f2 = this.random.nextFloat() * 3.141593F * 2.0F;

			entityitem.motX = (-MathHelper.sin(f2) * f1);
			entityitem.motZ = (MathHelper.cos(f2) * f1);
			entityitem.motY = 0.2000000029802322D;
		} else {
			f = 0.3F;
			entityitem.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
			entityitem.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F) * f);
			entityitem.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F) * f + 0.1F);
			f = 0.02F;
			float f1 = this.random.nextFloat() * 3.141593F * 2.0F;
			f *= this.random.nextFloat();
			entityitem.motX += Math.cos(f1) * f;
			entityitem.motY += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
			entityitem.motZ += Math.sin(f1) * f;
		}

		Player player = (Player)getBukkitEntity();
		CraftItem drop = new CraftItem(this.world.getServer(), entityitem);

		PlayerDropItemEvent event = new PlayerDropItemEvent(player, drop);
		this.world.getServer().getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			player.getInventory().addItem(new org.bukkit.inventory.ItemStack[] { drop.getItemStack() });
			return null;
		}

		a(entityitem);
		a(StatisticList.v, 1);
		return entityitem;
	}

	protected void a(EntityItem entityitem)
	{
		this.world.addEntity(entityitem);
	}

	public float a(Block block) {
		float f = this.inventory.a(block);
		int i = EnchantmentManager.getDigSpeedEnchantmentLevel(this.inventory);

		if ((i > 0) && (this.inventory.b(block))) {
			f += i * i + 1;
		}

		if (hasEffect(MobEffectList.FASTER_DIG)) {
			f *= (1.0F + (getEffect(MobEffectList.FASTER_DIG).getAmplifier() + 1) * 0.2F);
		}

		if (hasEffect(MobEffectList.SLOWER_DIG)) {
			f *= (1.0F - (getEffect(MobEffectList.SLOWER_DIG).getAmplifier() + 1) * 0.2F);
		}

		if ((a(Material.WATER)) && (!EnchantmentManager.hasWaterWorkerEnchantment(this.inventory))) {
			f /= 5.0F;
		}

		if (!this.onGround) {
			f /= 5.0F;
		}

		return f;
	}

	public boolean b(Block block) {
		return this.inventory.b(block);
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getList("Inventory");

		this.inventory.b(nbttaglist);
		this.dimension = nbttagcompound.getInt("Dimension");
		this.sleeping = nbttagcompound.getBoolean("Sleeping");
		this.sleepTicks = nbttagcompound.getShort("SleepTimer");
		this.exp = nbttagcompound.getFloat("XpP");
		this.expLevel = nbttagcompound.getInt("XpLevel");
		this.expTotal = nbttagcompound.getInt("XpTotal");
		if (this.sleeping) {
			this.bT = new ChunkCoordinates(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
			a(true, true, false);
		}

		this.spawnWorld = nbttagcompound.getString("SpawnWorld");
		if ("".equals(this.spawnWorld)) {
			this.spawnWorld = ((org.bukkit.World)this.world.getServer().getWorlds().get(0)).getName();
		}

		if ((nbttagcompound.hasKey("SpawnX")) && (nbttagcompound.hasKey("SpawnY")) && (nbttagcompound.hasKey("SpawnZ"))) {
			this.c = new ChunkCoordinates(nbttagcompound.getInt("SpawnX"), nbttagcompound.getInt("SpawnY"), nbttagcompound.getInt("SpawnZ"));
		}

		this.foodData.a(nbttagcompound);
		this.abilities.b(nbttagcompound);
		if (nbttagcompound.hasKey("EnderItems")) {
			NBTTagList nbttaglist1 = nbttagcompound.getList("EnderItems");

			this.enderChest.a(nbttaglist1);
		}
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.set("Inventory", this.inventory.a(new NBTTagList()));
		nbttagcompound.setInt("Dimension", this.dimension);
		nbttagcompound.setBoolean("Sleeping", this.sleeping);
		nbttagcompound.setShort("SleepTimer", (short)this.sleepTicks);
		nbttagcompound.setFloat("XpP", this.exp);
		nbttagcompound.setInt("XpLevel", this.expLevel);
		nbttagcompound.setInt("XpTotal", this.expTotal);
		if (this.c != null) {
			nbttagcompound.setInt("SpawnX", this.c.x);
			nbttagcompound.setInt("SpawnY", this.c.y);
			nbttagcompound.setInt("SpawnZ", this.c.z);
			nbttagcompound.setString("SpawnWorld", this.spawnWorld);
		}

		this.foodData.b(nbttagcompound);
		this.abilities.a(nbttagcompound);
		nbttagcompound.set("EnderItems", this.enderChest.g());
	}
	public void openContainer(IInventory iinventory) {
	}
	public void startEnchanting(int i, int j, int k) {
	}
	public void startCrafting(int i, int j, int k) {
	}
	public void receive(Entity entity, int i) {
	}

	public float getHeadHeight() {
		return 0.12F;
	}

	protected void d_() {
		this.height = 1.62F;
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		if ((this.abilities.isInvulnerable) && (!damagesource.ignoresInvulnerability())) {
			return false;
		}
		this.bq = 0;
		if (getHealth() <= 0) {
			return false;
		}
		if ((isSleeping()) && (!this.world.isStatic)) {
			a(true, true, false);
		}

		Entity entity = damagesource.getEntity();

		if (damagesource.n()) {
			if (this.world.difficulty == 0) {
				return false;
			}

			if (this.world.difficulty == 1) {
				i = i / 2 + 1;
			}

			if (this.world.difficulty == 3) {
				i = i * 3 / 2;
			}

		}

		Entity entity1 = damagesource.getEntity();

		if (((entity1 instanceof EntityArrow)) && (((EntityArrow)entity1).shooter != null)) {
			entity1 = ((EntityArrow)entity1).shooter;
		}

		if ((entity1 instanceof EntityLiving)) {
			a((EntityLiving)entity1, false);
		}

		a(StatisticList.x, i);
		return super.damageEntity(damagesource, i);
	}

	protected int c(DamageSource damagesource, int i)
	{
		int j = super.c(damagesource, i);

		if (j <= 0) {
			return 0;
		}
		int k = EnchantmentManager.a(this.inventory, damagesource);

		if (k > 20) {
			k = 20;
		}

		if ((k > 0) && (k <= 20)) {
			int l = 25 - k;
			int i1 = j * l + this.aM;

			j = i1 / 25;
			this.aM = (i1 % 25);
		}

		return j;
	}

	protected boolean h()
	{
		return false;
	}

	protected void a(EntityLiving entityliving, boolean flag) {
		if ((!(entityliving instanceof EntityCreeper)) && (!(entityliving instanceof EntityGhast))) {
			if ((entityliving instanceof EntityWolf)) {
				EntityWolf entitywolf = (EntityWolf)entityliving;

				if ((entitywolf.isTamed()) && (this.name.equals(entitywolf.getOwnerName()))) {
					return;
				}
			}

			if ((!(entityliving instanceof EntityHuman)) || (h())) {
				List list = this.world.a(EntityWolf.class, AxisAlignedBB.a().a(this.locX, this.locY, this.locZ, this.locX + 1.0D, this.locY + 1.0D, this.locZ + 1.0D).grow(16.0D, 4.0D, 16.0D));
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					EntityWolf entitywolf1 = (EntityWolf)iterator.next();

					if ((entitywolf1.isTamed()) && (entitywolf1.m() == null) && (this.name.equals(entitywolf1.getOwnerName())) && ((!flag) || (!entitywolf1.isSitting()))) {
						entitywolf1.setSitting(false);
						entitywolf1.setTarget(entityliving);
					}
				}
			}
		}
	}

	protected void k(int i) {
		this.inventory.g(i);
	}

	public int aO() {
		return this.inventory.l();
	}

	protected void d(DamageSource damagesource, int i) {
		if ((!damagesource.ignoresArmor()) && (aY())) {
			i = 1 + i >> 1;
		}

		i = b(damagesource, i);
		i = c(damagesource, i);
		j(damagesource.d());
		this.health -= i;
	}
	public void openFurnace(TileEntityFurnace tileentityfurnace) {
	}
	public void openDispenser(TileEntityDispenser tileentitydispenser) {
	}
	public void a(TileEntitySign tileentitysign) {
	}
	public void openBrewingStand(TileEntityBrewingStand tileentitybrewingstand) {
	}
	public void openTrade(IMerchant imerchant) {
	}
	public void c(ItemStack itemstack) {
	}

	public boolean m(Entity entity) {
		if (entity.c(this)) {
			return true;
		}
		ItemStack itemstack = bC();

		if ((itemstack != null) && ((entity instanceof EntityLiving))) {
			if (this.abilities.canInstantlyBuild) {
				itemstack = itemstack.cloneItemStack();
			}

			if (itemstack.a((EntityLiving)entity))
			{
				if ((itemstack.count == 0) && (!this.abilities.canInstantlyBuild)) {
					bD();
				}

				return true;
			}
		}

		return false;
	}

	public ItemStack bC()
	{
		return this.inventory.getItemInHand();
	}

	public void bD() {
		this.inventory.setItem(this.inventory.itemInHandIndex, (ItemStack)null);
	}

	public double W() {
		return this.height - 0.5F;
	}

	public void i() {
		if ((!this.bH) || (this.bI >= k() / 2) || (this.bI < 0)) {
			this.bI = -1;
			this.bH = true;
		}
	}

	public void attack(Entity entity) {
		if (entity.an()) {
			int i = this.inventory.a(entity);

			if (hasEffect(MobEffectList.INCREASE_DAMAGE)) {
				i += (3 << getEffect(MobEffectList.INCREASE_DAMAGE).getAmplifier());
			}

			if (hasEffect(MobEffectList.WEAKNESS)) {
				i -= (2 << getEffect(MobEffectList.WEAKNESS).getAmplifier());
			}

			int j = 0;
			int k = 0;

			if ((entity instanceof EntityLiving)) {
				k = EnchantmentManager.a(this.inventory, (EntityLiving)entity);
				j += EnchantmentManager.getKnockbackEnchantmentLevel(this.inventory, (EntityLiving)entity);
			}

			if (isSprinting()) {
				j++;
			}

			if ((i > 0) || (k > 0)) {
				boolean flag = (this.fallDistance > 0.0F) && (!this.onGround) && (!f_()) && (!H()) && (!hasEffect(MobEffectList.BLINDNESS)) && (this.vehicle == null) && ((entity instanceof EntityLiving));

				if (flag) {
					i += this.random.nextInt(i / 2 + 2);
				}

				i += k;
				boolean flag1 = entity.damageEntity(DamageSource.playerAttack(this), i);

				if (!flag1) {
					return;
				}

				if (flag1) {
					if (j > 0) {
						entity.g(-MathHelper.sin(this.yaw * 3.141593F / 180.0F) * j * 0.5F, 0.1D, MathHelper.cos(this.yaw * 3.141593F / 180.0F) * j * 0.5F);
						this.motX *= 0.6D;
						this.motZ *= 0.6D;
						setSprinting(false);
					}

					if (flag) {
						b(entity);
					}

					if (k > 0) {
						c(entity);
					}

					if (i >= 18) {
						a(AchievementList.E);
					}

					j(entity);
				}

				ItemStack itemstack = bC();

				if ((itemstack != null) && ((entity instanceof EntityLiving))) {
					itemstack.a((EntityLiving)entity, this);

					if (itemstack.count == 0) {
						bD();
					}
				}

				if ((entity instanceof EntityLiving)) {
					if (entity.isAlive()) {
						a((EntityLiving)entity, true);
					}

					a(StatisticList.w, i);
					int l = EnchantmentManager.getFireAspectEnchantmentLevel(this.inventory, (EntityLiving)entity);

					if (l > 0)
					{
						EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(getBukkitEntity(), entity.getBukkitEntity(), l * 4);
						Bukkit.getPluginManager().callEvent(combustEvent);

						if (!combustEvent.isCancelled()) {
							entity.setOnFire(combustEvent.getDuration());
						}
					}

				}

				j(0.3F);
			}
		}
	}

	public void b(Entity entity) {
	}

	public void c(Entity entity) {
	}

	public void die() {
		super.die();
		this.defaultContainer.a(this);
		if (this.activeContainer != null)
			this.activeContainer.a(this);
	}

	public boolean inBlock()
	{
		return (!this.sleeping) && (super.inBlock());
	}

	public boolean bF() {
		return false;
	}

	public EnumBedResult a(int i, int j, int k) {
		if (!this.world.isStatic) {
			if ((isSleeping()) || (!isAlive())) {
				return EnumBedResult.OTHER_PROBLEM;
			}

			if (!this.world.worldProvider.d()) {
				return EnumBedResult.NOT_POSSIBLE_HERE;
			}

			if (this.world.r()) {
				return EnumBedResult.NOT_POSSIBLE_NOW;
			}

			if ((Math.abs(this.locX - i) > 3.0D) || (Math.abs(this.locY - j) > 2.0D) || (Math.abs(this.locZ - k) > 3.0D)) {
				return EnumBedResult.TOO_FAR_AWAY;
			}

			double d0 = 8.0D;
			double d1 = 5.0D;
			List list = this.world.a(EntityMonster.class, AxisAlignedBB.a().a(i - d0, j - d1, k - d0, i + d0, j + d1, k + d0));

			if (!list.isEmpty()) {
				return EnumBedResult.NOT_SAFE;
			}

		}

		if ((getBukkitEntity() instanceof Player)) {
			Player player = (Player)getBukkitEntity();
			org.bukkit.block.Block bed = this.world.getWorld().getBlockAt(i, j, k);

			PlayerBedEnterEvent event = new PlayerBedEnterEvent(player, bed);
			this.world.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled()) {
				return EnumBedResult.OTHER_PROBLEM;
			}

		}

		a(0.2F, 0.2F);
		this.height = 0.2F;
		if (this.world.isLoaded(i, j, k)) {
			int l = this.world.getData(i, j, k);
			int i1 = BlockBed.d(l);
			float f = 0.5F;
			float f1 = 0.5F;

			switch (i1) {
			case 0:
				f1 = 0.9F;
				break;
			case 1:
				f = 0.1F;
				break;
			case 2:
				f1 = 0.1F;
				break;
			case 3:
				f = 0.9F;
			}

			b(i1);
			setPosition(i + f, j + 0.9375F, k + f1);
		} else {
			setPosition(i + 0.5F, j + 0.9375F, k + 0.5F);
		}

		this.sleeping = true;
		this.sleepTicks = 0;
		this.bT = new ChunkCoordinates(i, j, k);
		this.motX = (this.motZ = this.motY = 0.0D);
		if (!this.world.isStatic) {
			this.world.everyoneSleeping();
		}

		return EnumBedResult.OK;
	}

	private void b(int i) {
		this.bU = 0.0F;
		this.bV = 0.0F;
		switch (i) {
		case 0:
			this.bV = -1.8F;
			break;
		case 1:
			this.bU = 1.8F;
			break;
		case 2:
			this.bV = 1.8F;
			break;
		case 3:
			this.bU = -1.8F;
		}
	}

	public void a(boolean flag, boolean flag1, boolean flag2) {
		a(0.6F, 1.8F);
		d_();
		ChunkCoordinates chunkcoordinates = this.bT;
		ChunkCoordinates chunkcoordinates1 = this.bT;

		if ((chunkcoordinates != null) && (this.world.getTypeId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) == Block.BED.id)) {
			BlockBed.a(this.world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, false);
			chunkcoordinates1 = BlockBed.b(this.world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);
			if (chunkcoordinates1 == null) {
				chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.x, chunkcoordinates.y + 1, chunkcoordinates.z);
			}

			setPosition(chunkcoordinates1.x + 0.5F, chunkcoordinates1.y + this.height + 0.1F, chunkcoordinates1.z + 0.5F);
		}

		this.sleeping = false;
		if ((!this.world.isStatic) && (flag1)) {
			this.world.everyoneSleeping();
		}

		if ((getBukkitEntity() instanceof Player)) {
			Player player = (Player)getBukkitEntity();
			org.bukkit.block.Block bed;
			org.bukkit.block.Block bed;
			if (chunkcoordinates != null)
				bed = this.world.getWorld().getBlockAt(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z);
			else {
				bed = this.world.getWorld().getBlockAt(player.getLocation());
			}

			PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed);
			this.world.getServer().getPluginManager().callEvent(event);
		}

		if (flag)
			this.sleepTicks = 0;
		else {
			this.sleepTicks = 100;
		}

		if (flag2)
			setRespawnPosition(this.bT);
	}

	private boolean l()
	{
		return this.world.getTypeId(this.bT.x, this.bT.y, this.bT.z) == Block.BED.id;
	}

	public static ChunkCoordinates getBed(World world, ChunkCoordinates chunkcoordinates) {
		IChunkProvider ichunkprovider = world.F();

		ichunkprovider.getChunkAt(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z - 3 >> 4);
		ichunkprovider.getChunkAt(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z - 3 >> 4);
		ichunkprovider.getChunkAt(chunkcoordinates.x - 3 >> 4, chunkcoordinates.z + 3 >> 4);
		ichunkprovider.getChunkAt(chunkcoordinates.x + 3 >> 4, chunkcoordinates.z + 3 >> 4);
		if (world.getTypeId(chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z) != Block.BED.id) {
			return null;
		}
		ChunkCoordinates chunkcoordinates1 = BlockBed.b(world, chunkcoordinates.x, chunkcoordinates.y, chunkcoordinates.z, 0);

		return chunkcoordinates1;
	}

	public boolean isSleeping()
	{
		return this.sleeping;
	}

	public boolean isDeeplySleeping() {
		return (this.sleeping) && (this.sleepTicks >= 100);
	}
	public void c(String s) {
	}

	public ChunkCoordinates getBed() {
		return this.c;
	}

	public void setRespawnPosition(ChunkCoordinates chunkcoordinates) {
		if (chunkcoordinates != null) {
			this.c = new ChunkCoordinates(chunkcoordinates);
			this.spawnWorld = this.world.worldData.getName();
		} else {
			this.c = null;
		}
	}

	public void a(Statistic statistic) {
		a(statistic, 1);
	}
	public void a(Statistic statistic, int i) {
	}

	protected void aZ() {
		super.aZ();
		a(StatisticList.u, 1);
		if (isSprinting())
			j(0.8F);
		else
			j(0.2F);
	}

	public void e(float f, float f1)
	{
		double d0 = this.locX;
		double d1 = this.locY;
		double d2 = this.locZ;

		if ((this.abilities.isFlying) && (this.vehicle == null)) {
			double d3 = this.motY;
			float f2 = this.aH;

			this.aH = this.abilities.a();
			super.e(f, f1);
			this.motY = (d3 * 0.6D);
			this.aH = f2;
		} else {
			super.e(f, f1);
		}

		checkMovement(this.locX - d0, this.locY - d1, this.locZ - d2);
	}

	public void checkMovement(double d0, double d1, double d2) {
		if (this.vehicle == null)
		{
			if (a(Material.WATER)) {
				int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
				if (i > 0) {
					a(StatisticList.q, i);
					j(0.015F * i * 0.01F);
				}
			} else if (H()) {
				int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
				if (i > 0) {
					a(StatisticList.m, i);
					j(0.015F * i * 0.01F);
				}
			} else if (f_()) {
				if (d1 > 0.0D)
					a(StatisticList.o, (int)Math.round(d1 * 100.0D));
			}
			else if (this.onGround) {
				int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
				if (i > 0) {
					a(StatisticList.l, i);
					if (isSprinting())
						j(0.09999999F * i * 0.01F);
					else
						j(0.01F * i * 0.01F);
				}
			}
			else {
				int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
				if (i > 25)
					a(StatisticList.p, i);
			}
		}
	}

	private void k(double d0, double d1, double d2)
	{
		if (this.vehicle != null) {
			int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);

			if (i > 0)
				if ((this.vehicle instanceof EntityMinecart)) {
					a(StatisticList.r, i);
					if (this.d == null)
						this.d = new ChunkCoordinates(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
					else if (this.d.e(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) >= 1000000.0D)
						a(AchievementList.q, 1);
				}
				else if ((this.vehicle instanceof EntityBoat)) {
					a(StatisticList.s, i);
				} else if ((this.vehicle instanceof EntityPig)) {
					a(StatisticList.t, i);
				}
		}
	}

	protected void a(float f)
	{
		if (!this.abilities.canFly) {
			if (f >= 2.0F) {
				a(StatisticList.n, (int)Math.round(f * 100.0D));
			}

			super.a(f);
		}
	}

	public void a(EntityLiving entityliving) {
		if ((entityliving instanceof EntityMonster))
			a(AchievementList.s);
	}

	public void aa()
	{
		if (this.bW > 0)
			this.bW = 10;
		else
			this.bX = true;
	}

	public void giveExp(int i)
	{
		this.bE += i;
		int j = 2147483647 - this.expTotal;

		if (i > j) {
			i = j;
		}

		this.exp += i / getExpToLevel();

		for (this.expTotal += i; this.exp >= 1.0F; this.exp /= getExpToLevel()) {
			this.exp = ((this.exp - 1.0F) * getExpToLevel());
			levelUp();
		}
	}

	public void levelDown(int i) {
		this.expLevel -= i;
		if (this.expLevel < 0)
			this.expLevel = 0;
	}

	public int getExpToLevel()
	{
		return this.expLevel >= 15 ? 17 + (this.expLevel - 15) * 3 : this.expLevel >= 30 ? 62 + (this.expLevel - 30) * 7 : 17;
	}

	private void levelUp() {
		this.expLevel += 1;
	}

	public void j(float f) {
		if ((!this.abilities.isInvulnerable) && 
			(!this.world.isStatic))
			this.foodData.a(f);
	}

	public FoodMetaData getFoodData()
	{
		return this.foodData;
	}

	public boolean e(boolean flag) {
		return ((flag) || (this.foodData.c())) && (!this.abilities.isInvulnerable);
	}

	public boolean bM() {
		return (getHealth() > 0) && (getHealth() < getMaxHealth());
	}

	public void a(ItemStack itemstack, int i) {
		if (itemstack != this.e) {
			this.e = itemstack;
			this.f = i;
			if (!this.world.isStatic)
				c(true);
		}
	}

	public boolean e(int i, int j, int k)
	{
		return this.abilities.mayBuild;
	}

	protected int getExpValue(EntityHuman entityhuman) {
		int i = this.expLevel * 7;

		return i > 100 ? 100 : i;
	}

	protected boolean alwaysGivesExp() {
		return true;
	}

	public String getLocalizedName() {
		return this.name;
	}
	public void c(int i) {
	}

	public void copyTo(EntityHuman entityhuman, boolean flag) {
		if (flag) {
			this.inventory.b(entityhuman.inventory);
			this.health = entityhuman.health;
			this.foodData = entityhuman.foodData;
			this.expLevel = entityhuman.expLevel;
			this.expTotal = entityhuman.expTotal;
			this.exp = entityhuman.exp;
			this.bE = entityhuman.bE;
		}

		this.enderChest = entityhuman.enderChest;
	}

	protected boolean e_() {
		return !this.abilities.isFlying;
	}
	public void updateAbilities() {
	}
	public void a(EnumGamemode enumgamemode) {
	}

	public String getName() {
		return this.name;
	}

	public LocaleLanguage getLocale() {
		return LocaleLanguage.a();
	}

	public String a(String s, Object[] aobject) {
		return getLocale().a(s, aobject);
	}

	public InventoryEnderChest getEnderChest() {
		return this.enderChest;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityHuman
 * JD-Core Version:		0.6.0
 */