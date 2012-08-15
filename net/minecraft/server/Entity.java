package net.minecraft.server;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.painting.PaintingBreakByEntityEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.PluginManager;

public abstract class Entity
{
	private static int entityCount = 0;
	public int id;
	public double l;
	public boolean m;
	public Entity passenger;
	public Entity vehicle;
	public World world;
	public double lastX;
	public double lastY;
	public double lastZ;
	public double locX;
	public double locY;
	public double locZ;
	public double motX;
	public double motY;
	public double motZ;
	public float yaw;
	public float pitch;
	public float lastYaw;
	public float lastPitch;
	public final AxisAlignedBB boundingBox;
	public boolean onGround;
	public boolean positionChanged;
	public boolean G;
	public boolean H;
	public boolean velocityChanged;
	protected boolean J;
	public boolean K;
	public boolean dead;
	public float height;
	public float width;
	public float length;
	public float P;
	public float Q;
	public float fallDistance;
	private int b;
	public double S;
	public double T;
	public double U;
	public float V;
	public float W;
	public boolean X;
	public float Y;
	protected Random random;
	public int ticksLived;
	public int maxFireTicks;
	public int fireTicks;
	protected boolean ac;
	public int noDamageTicks;
	private boolean justCreated;
	protected boolean fireProof;
	protected DataWatcher datawatcher;
	private double e;
	private double f;
	public boolean ag;
	public int ah;
	public int ai;
	public int aj;
	public boolean ak;
	public boolean al;
	public EnumEntitySize am;
	public UUID uniqueId = UUID.randomUUID();
	public boolean valid = true;
	protected org.bukkit.entity.Entity bukkitEntity;

	public Entity(World world)
	{
		this.id = (entityCount++);
		this.l = 1.0D;
		this.m = false;
		this.boundingBox = AxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
		this.onGround = false;
		this.H = false;
		this.velocityChanged = false;
		this.K = true;
		this.dead = false;
		this.height = 0.0F;
		this.width = 0.6F;
		this.length = 1.8F;
		this.P = 0.0F;
		this.Q = 0.0F;
		this.fallDistance = 0.0F;
		this.b = 1;
		this.V = 0.0F;
		this.W = 0.0F;
		this.X = false;
		this.Y = 0.0F;
		this.random = new Random();
		this.ticksLived = 0;
		this.maxFireTicks = 1;
		this.fireTicks = 0;
		this.ac = false;
		this.noDamageTicks = 0;
		this.justCreated = true;
		this.fireProof = false;
		this.datawatcher = new DataWatcher();
		this.ag = false;
		this.am = EnumEntitySize.SIZE_2;
		this.world = world;
		setPosition(0.0D, 0.0D, 0.0D);
		this.datawatcher.a(0, Byte.valueOf(0));
		this.datawatcher.a(1, Short.valueOf(300));
		a();
	}
	protected abstract void a();

	public DataWatcher getDataWatcher() {
		return this.datawatcher;
	}

	public boolean equals(Object object) {
		return ((Entity)object).id == this.id;
	}

	public int hashCode() {
		return this.id;
	}

	public void die() {
		this.dead = true;
	}

	protected void a(float f, float f1) {
		this.width = f;
		this.length = f1;
		float f2 = f % 2.0F;

		if (f2 < 0.375D)
			this.am = EnumEntitySize.SIZE_1;
		else if (f2 < 0.75D)
			this.am = EnumEntitySize.SIZE_2;
		else if (f2 < 1.0D)
			this.am = EnumEntitySize.SIZE_3;
		else if (f2 < 1.375D)
			this.am = EnumEntitySize.SIZE_4;
		else if (f2 < 1.75D)
			this.am = EnumEntitySize.SIZE_5;
		else
			this.am = EnumEntitySize.SIZE_6;
	}

	protected void b(float f, float f1)
	{
		if (Float.isNaN(f)) {
			f = 0.0F;
		}

		if ((f == (1.0F / 1.0F)) || (f == (1.0F / -1.0F))) {
			if ((this instanceof EntityPlayer)) {
				System.err.println(((CraftPlayer)getBukkitEntity()).getName() + " was caught trying to crash the server with an invalid yaw");
				((CraftPlayer)getBukkitEntity()).kickPlayer("Nope");
			}
			f = 0.0F;
		}

		if (Float.isNaN(f1)) {
			f1 = 0.0F;
		}

		if ((f1 == (1.0F / 1.0F)) || (f1 == (1.0F / -1.0F))) {
			if ((this instanceof EntityPlayer)) {
				System.err.println(((CraftPlayer)getBukkitEntity()).getName() + " was caught trying to crash the server with an invalid pitch");
				((CraftPlayer)getBukkitEntity()).kickPlayer("Nope");
			}
			f1 = 0.0F;
		}

		this.yaw = (f % 360.0F);
		this.pitch = (f1 % 360.0F);
	}

	public void setPosition(double d0, double d1, double d2) {
		this.locX = d0;
		this.locY = d1;
		this.locZ = d2;
		float f = this.width / 2.0F;
		float f1 = this.length;

		this.boundingBox.b(d0 - f, d1 - this.height + this.V, d2 - f, d0 + f, d1 - this.height + this.V + f1, d2 + f);
	}

	public void h_() {
		z();
	}

	public void z()
	{
		if ((this.vehicle != null) && (this.vehicle.dead)) {
			this.vehicle = null;
		}

		this.ticksLived += 1;
		this.P = this.Q;
		this.lastX = this.locX;
		this.lastY = this.locY;
		this.lastZ = this.locZ;
		this.lastPitch = this.pitch;
		this.lastYaw = this.yaw;

		if ((isSprinting()) && (!H())) {
			int j = MathHelper.floor(this.locX);
			int k = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);

			int i = MathHelper.floor(this.locZ);
			int l = this.world.getTypeId(j, k, i);

			if (l > 0) {
				this.world.a("tilecrack_" + l, this.locX + (this.random.nextFloat() - 0.5D) * this.width, this.boundingBox.b + 0.1D, this.locZ + (this.random.nextFloat() - 0.5D) * this.width, -this.motX * 4.0D, 1.5D, -this.motZ * 4.0D);
			}
		}

		if (I()) {
			if ((!this.ac) && (!this.justCreated)) {
				float f = MathHelper.sqrt(this.motX * this.motX * 0.2000000029802322D + this.motY * this.motY + this.motZ * this.motZ * 0.2000000029802322D) * 0.2F;

				if (f > 1.0F) {
					f = 1.0F;
				}

				this.world.makeSound(this, "random.splash", f, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
				float f1 = MathHelper.floor(this.boundingBox.b);

				for (int i = 0; i < 1.0F + this.width * 20.0F; i++) {
					float f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
					float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
					this.world.a("bubble", this.locX + f3, f1 + 1.0F, this.locZ + f2, this.motX, this.motY - this.random.nextFloat() * 0.2F, this.motZ);
				}

				for (i = 0; i < 1.0F + this.width * 20.0F; i++) {
					float f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
					float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
					this.world.a("splash", this.locX + f3, f1 + 1.0F, this.locZ + f2, this.motX, this.motY, this.motZ);
				}
			}

			this.fallDistance = 0.0F;
			this.ac = true;
			this.fireTicks = 0;
		} else {
			this.ac = false;
		}

		if (this.world.isStatic)
			this.fireTicks = 0;
		else if (this.fireTicks > 0) {
			if (this.fireProof) {
				this.fireTicks -= 4;
				if (this.fireTicks < 0)
					this.fireTicks = 0;
			}
			else {
				if (this.fireTicks % 20 == 0)
				{
					if ((this instanceof EntityLiving)) {
						EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.FIRE_TICK, 1);
						this.world.getServer().getPluginManager().callEvent(event);

						if (!event.isCancelled()) {
							event.getEntity().setLastDamageCause(event);
							damageEntity(DamageSource.BURN, event.getDamage());
						}
					} else {
						damageEntity(DamageSource.BURN, 1);
					}

				}

				this.fireTicks -= 1;
			}
		}

		if (J()) {
			A();
			this.fallDistance *= 0.5F;
		}

		if (this.locY < -64.0D) {
			C();
		}

		if (!this.world.isStatic) {
			a(0, this.fireTicks > 0);
			a(2, this.vehicle != null);
		}

		this.justCreated = false;
	}

	protected void A()
	{
		if (!this.fireProof)
		{
			if ((this instanceof EntityLiving)) {
				Server server = this.world.getServer();

				org.bukkit.block.Block damager = null;
				org.bukkit.entity.Entity damagee = getBukkitEntity();

				EntityDamageByBlockEvent event = new EntityDamageByBlockEvent(damager, damagee, EntityDamageEvent.DamageCause.LAVA, 4);
				server.getPluginManager().callEvent(event);

				if (!event.isCancelled()) {
					damagee.setLastDamageCause(event);
					damageEntity(DamageSource.LAVA, event.getDamage());
				}

				if (this.fireTicks <= 0)
				{
					EntityCombustEvent combustEvent = new EntityCombustByBlockEvent(damager, damagee, 15);
					server.getPluginManager().callEvent(combustEvent);

					if (!combustEvent.isCancelled())
						setOnFire(combustEvent.getDuration());
				}
				else
				{
					setOnFire(15);
				}
				return;
			}

			damageEntity(DamageSource.LAVA, 4);
			setOnFire(15);
		}
	}

	public void setOnFire(int i) {
		int j = i * 20;

		if (this.fireTicks < j)
			this.fireTicks = j;
	}

	public void extinguish()
	{
		this.fireTicks = 0;
	}

	protected void C() {
		die();
	}

	public boolean c(double d0, double d1, double d2) {
		AxisAlignedBB axisalignedbb = this.boundingBox.c(d0, d1, d2);
		List list = this.world.getCubes(this, axisalignedbb);

		return list.isEmpty();
	}

	public void move(double d0, double d1, double d2) {
		if (this.X) {
			this.boundingBox.d(d0, d1, d2);
			this.locX = ((this.boundingBox.a + this.boundingBox.d) / 2.0D);
			this.locY = (this.boundingBox.b + this.height - this.V);
			this.locZ = ((this.boundingBox.c + this.boundingBox.f) / 2.0D);
		}
		else {
			this.V *= 0.4F;
			double d3 = this.locX;
			double d4 = this.locZ;

			if (this.J) {
				this.J = false;
				d0 *= 0.25D;
				d1 *= 0.0500000007450581D;
				d2 *= 0.25D;
				this.motX = 0.0D;
				this.motY = 0.0D;
				this.motZ = 0.0D;
			}

			double d5 = d0;
			double d6 = d1;
			double d7 = d2;
			AxisAlignedBB axisalignedbb = this.boundingBox.clone();
			boolean flag = (this.onGround) && (isSneaking()) && ((this instanceof EntityHuman));

			if (flag)
			{
				for (double d8 = 0.05D; (d0 != 0.0D) && (this.world.getCubes(this, this.boundingBox.c(d0, -1.0D, 0.0D)).isEmpty()); d5 = d0) {
					if ((d0 < d8) && (d0 >= -d8))
						d0 = 0.0D;
					else if (d0 > 0.0D)
						d0 -= d8;
					else {
						d0 += d8;
					}
				}

				for (; (d2 != 0.0D) && (this.world.getCubes(this, this.boundingBox.c(0.0D, -1.0D, d2)).isEmpty()); d7 = d2) {
					if ((d2 < d8) && (d2 >= -d8))
						d2 = 0.0D;
					else if (d2 > 0.0D)
						d2 -= d8;
					else {
						d2 += d8;
					}
				}

				while ((d0 != 0.0D) && (d2 != 0.0D) && (this.world.getCubes(this, this.boundingBox.c(d0, -1.0D, d2)).isEmpty())) {
					if ((d0 < d8) && (d0 >= -d8))
						d0 = 0.0D;
					else if (d0 > 0.0D)
						d0 -= d8;
					else {
						d0 += d8;
					}

					if ((d2 < d8) && (d2 >= -d8))
						d2 = 0.0D;
					else if (d2 > 0.0D)
						d2 -= d8;
					else {
						d2 += d8;
					}

					d5 = d0;
					d7 = d2;
				}
			}

			List list = this.world.getCubes(this, this.boundingBox.a(d0, d1, d2));
			AxisAlignedBB axisalignedbb1;
			for (Iterator iterator = list.iterator(); iterator.hasNext(); d1 = axisalignedbb1.b(this.boundingBox, d1)) {
				axisalignedbb1 = (AxisAlignedBB)iterator.next();
			}

			this.boundingBox.d(0.0D, d1, 0.0D);
			if ((!this.K) && (d6 != d1)) {
				d2 = 0.0D;
				d1 = 0.0D;
				d0 = 0.0D;
			}

			boolean flag1 = (this.onGround) || ((d6 != d1) && (d6 < 0.0D));
			AxisAlignedBB axisalignedbb2;
			for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); d0 = axisalignedbb2.a(this.boundingBox, d0)) {
				axisalignedbb2 = (AxisAlignedBB)iterator1.next();
			}

			this.boundingBox.d(d0, 0.0D, 0.0D);
			if ((!this.K) && (d5 != d0)) {
				d2 = 0.0D;
				d1 = 0.0D;
				d0 = 0.0D;
			}
			AxisAlignedBB axisalignedbb2;
			for (iterator1 = list.iterator(); iterator1.hasNext(); d2 = axisalignedbb2.c(this.boundingBox, d2)) {
				axisalignedbb2 = (AxisAlignedBB)iterator1.next();
			}

			this.boundingBox.d(0.0D, 0.0D, d2);
			if ((!this.K) && (d7 != d2)) {
				d2 = 0.0D;
				d1 = 0.0D;
				d0 = 0.0D;
			}

			if ((this.W > 0.0F) && (flag1) && ((flag) || (this.V < 0.05F)) && ((d5 != d0) || (d7 != d2))) {
				double d9 = d0;
				double d10 = d1;
				double d11 = d2;

				d0 = d5;
				d1 = this.W;
				d2 = d7;
				AxisAlignedBB axisalignedbb3 = this.boundingBox.clone();

				this.boundingBox.c(axisalignedbb);
				list = this.world.getCubes(this, this.boundingBox.a(d5, d1, d7));
				AxisAlignedBB axisalignedbb4;
				for (Iterator iterator2 = list.iterator(); iterator2.hasNext(); d1 = axisalignedbb4.b(this.boundingBox, d1)) {
					axisalignedbb4 = (AxisAlignedBB)iterator2.next();
				}

				this.boundingBox.d(0.0D, d1, 0.0D);
				if ((!this.K) && (d6 != d1)) {
					d2 = 0.0D;
					d1 = 0.0D;
					d0 = 0.0D;
				}
				AxisAlignedBB axisalignedbb4;
				for (iterator2 = list.iterator(); iterator2.hasNext(); d0 = axisalignedbb4.a(this.boundingBox, d0)) {
					axisalignedbb4 = (AxisAlignedBB)iterator2.next();
				}

				this.boundingBox.d(d0, 0.0D, 0.0D);
				if ((!this.K) && (d5 != d0)) {
					d2 = 0.0D;
					d1 = 0.0D;
					d0 = 0.0D;
				}
				AxisAlignedBB axisalignedbb4;
				for (iterator2 = list.iterator(); iterator2.hasNext(); d2 = axisalignedbb4.c(this.boundingBox, d2)) {
					axisalignedbb4 = (AxisAlignedBB)iterator2.next();
				}

				this.boundingBox.d(0.0D, 0.0D, d2);
				if ((!this.K) && (d7 != d2)) {
					d2 = 0.0D;
					d1 = 0.0D;
					d0 = 0.0D;
				}

				if ((!this.K) && (d6 != d1)) {
					d2 = 0.0D;
					d1 = 0.0D;
					d0 = 0.0D;
				} else {
					d1 = -this.W;
					AxisAlignedBB axisalignedbb4;
					for (iterator2 = list.iterator(); iterator2.hasNext(); d1 = axisalignedbb4.b(this.boundingBox, d1)) {
						axisalignedbb4 = (AxisAlignedBB)iterator2.next();
					}

					this.boundingBox.d(0.0D, d1, 0.0D);
				}

				if (d9 * d9 + d11 * d11 >= d0 * d0 + d2 * d2) {
					d0 = d9;
					d1 = d10;
					d2 = d11;
					this.boundingBox.c(axisalignedbb3);
				} else {
					double d12 = this.boundingBox.b - (int)this.boundingBox.b;

					if (d12 > 0.0D) {
						this.V = (float)(this.V + d12 + 0.01D);
					}

				}

			}

			this.locX = ((this.boundingBox.a + this.boundingBox.d) / 2.0D);
			this.locY = (this.boundingBox.b + this.height - this.V);
			this.locZ = ((this.boundingBox.c + this.boundingBox.f) / 2.0D);
			this.positionChanged = ((d5 != d0) || (d7 != d2));
			this.G = (d6 != d1);
			this.onGround = ((d6 != d1) && (d6 < 0.0D));
			this.H = ((this.positionChanged) || (this.G));
			a(d1, this.onGround);
			if (d5 != d0) {
				this.motX = 0.0D;
			}

			if (d6 != d1) {
				this.motY = 0.0D;
			}

			if (d7 != d2) {
				this.motZ = 0.0D;
			}

			double d9 = this.locX - d3;
			double d10 = this.locZ - d4;

			if ((this.positionChanged) && ((getBukkitEntity() instanceof Vehicle))) {
				Vehicle vehicle = (Vehicle)getBukkitEntity();
				org.bukkit.block.Block block = this.world.getWorld().getBlockAt(MathHelper.floor(this.locX), MathHelper.floor(this.locY - this.height), MathHelper.floor(this.locZ));

				if (d5 > d0)
					block = block.getRelative(BlockFace.SOUTH);
				else if (d5 < d0)
					block = block.getRelative(BlockFace.NORTH);
				else if (d7 > d2)
					block = block.getRelative(BlockFace.WEST);
				else if (d7 < d2) {
					block = block.getRelative(BlockFace.EAST);
				}

				VehicleBlockCollisionEvent event = new VehicleBlockCollisionEvent(vehicle, block);
				this.world.getServer().getPluginManager().callEvent(event);
			}

			if ((e_()) && (!flag) && (this.vehicle == null)) {
				this.Q = (float)(this.Q + MathHelper.sqrt(d9 * d9 + d10 * d10) * 0.6D);
				int i = MathHelper.floor(this.locX);
				int j = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
				int k = MathHelper.floor(this.locZ);
				int l = this.world.getTypeId(i, j, k);

				if ((l == 0) && (this.world.getTypeId(i, j - 1, k) == Block.FENCE.id)) {
					l = this.world.getTypeId(i, j - 1, k);
				}

				if ((this.Q > this.b) && (l > 0)) {
					this.b = ((int)this.Q + 1);
					a(i, j, k, l);
					Block.byId[l].b(this.world, i, j, k, this);
				}
			}

			D();
			boolean flag2 = G();

			if (this.world.e(this.boundingBox.shrink(0.001D, 0.001D, 0.001D))) {
				burn(1);
				if (!flag2) {
					this.fireTicks += 1;

					if (this.fireTicks <= 0) {
						EntityCombustEvent event = new EntityCombustEvent(getBukkitEntity(), 8);
						this.world.getServer().getPluginManager().callEvent(event);

						if (!event.isCancelled())
							setOnFire(event.getDuration());
					}
					else
					{
						setOnFire(8);
					}
				}
			} else if (this.fireTicks <= 0) {
				this.fireTicks = (-this.maxFireTicks);
			}

			if ((flag2) && (this.fireTicks > 0)) {
				this.world.makeSound(this, "random.fizz", 0.7F, 1.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
				this.fireTicks = (-this.maxFireTicks);
			}
		}
	}

	protected void D()
	{
		int i = MathHelper.floor(this.boundingBox.a + 0.001D);
		int j = MathHelper.floor(this.boundingBox.b + 0.001D);
		int k = MathHelper.floor(this.boundingBox.c + 0.001D);
		int l = MathHelper.floor(this.boundingBox.d - 0.001D);
		int i1 = MathHelper.floor(this.boundingBox.e - 0.001D);
		int j1 = MathHelper.floor(this.boundingBox.f - 0.001D);

		if (this.world.c(i, j, k, l, i1, j1))
			for (int k1 = i; k1 <= l; k1++)
				for (int l1 = j; l1 <= i1; l1++)
					for (int i2 = k; i2 <= j1; i2++) {
						int j2 = this.world.getTypeId(k1, l1, i2);

						if (j2 > 0)
							Block.byId[j2].a(this.world, k1, l1, i2, this);
					}
	}

	protected void a(int i, int j, int k, int l)
	{
		StepSound stepsound = Block.byId[l].stepSound;

		if (this.world.getTypeId(i, j + 1, k) == Block.SNOW.id) {
			stepsound = Block.SNOW.stepSound;
			this.world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.15F, stepsound.getVolume2());
		} else if (!Block.byId[l].material.isLiquid()) {
			this.world.makeSound(this, stepsound.getName(), stepsound.getVolume1() * 0.15F, stepsound.getVolume2());
		}
	}

	protected boolean e_() {
		return true;
	}

	protected void a(double d0, boolean flag) {
		if (flag) {
			if (this.fallDistance > 0.0F) {
				if ((this instanceof EntityLiving)) {
					int i = MathHelper.floor(this.locX);
					int j = MathHelper.floor(this.locY - 0.2000000029802322D - this.height);
					int k = MathHelper.floor(this.locZ);
					int l = this.world.getTypeId(i, j, k);

					if ((l == 0) && (this.world.getTypeId(i, j - 1, k) == Block.FENCE.id)) {
						l = this.world.getTypeId(i, j - 1, k);
					}

					if (l > 0) {
						Block.byId[l].a(this.world, i, j, k, this, this.fallDistance);
					}
				}

				a(this.fallDistance);
				this.fallDistance = 0.0F;
			}
		} else if (d0 < 0.0D)
			this.fallDistance = (float)(this.fallDistance - d0);
	}

	public AxisAlignedBB E()
	{
		return null;
	}

	protected void burn(int i) {
		if (!this.fireProof)
		{
			if ((this instanceof EntityLiving)) {
				EntityDamageEvent event = new EntityDamageEvent(getBukkitEntity(), EntityDamageEvent.DamageCause.FIRE, i);
				this.world.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) {
					return;
				}

				i = event.getDamage();
				event.getEntity().setLastDamageCause(event);
			}

			damageEntity(DamageSource.FIRE, i);
		}
	}

	public final boolean isFireproof() {
		return this.fireProof;
	}

	protected void a(float f) {
		if (this.passenger != null)
			this.passenger.a(f);
	}

	public boolean G()
	{
		return (this.ac) || (this.world.B(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)));
	}

	public boolean H() {
		return this.ac;
	}

	public boolean I() {
		return this.world.a(this.boundingBox.grow(0.0D, -0.4000000059604645D, 0.0D).shrink(0.001D, 0.001D, 0.001D), Material.WATER, this);
	}

	public boolean a(Material material) {
		double d0 = this.locY + getHeadHeight();
		int i = MathHelper.floor(this.locX);
		int j = MathHelper.d(MathHelper.floor(d0));
		int k = MathHelper.floor(this.locZ);
		int l = this.world.getTypeId(i, j, k);

		if ((l != 0) && (Block.byId[l].material == material)) {
			float f = BlockFluids.d(this.world.getData(i, j, k)) - 0.1111111F;
			float f1 = j + 1 - f;

			return d0 < f1;
		}
		return false;
	}

	public float getHeadHeight()
	{
		return 0.0F;
	}

	public boolean J() {
		return this.world.a(this.boundingBox.grow(-0.1000000014901161D, -0.4000000059604645D, -0.1000000014901161D), Material.LAVA);
	}

	public void a(float f, float f1, float f2) {
		float f3 = f * f + f1 * f1;

		if (f3 >= 1.0E-004F) {
			f3 = MathHelper.c(f3);
			if (f3 < 1.0F) {
				f3 = 1.0F;
			}

			f3 = f2 / f3;
			f *= f3;
			f1 *= f3;
			float f4 = MathHelper.sin(this.yaw * 3.141593F / 180.0F);
			float f5 = MathHelper.cos(this.yaw * 3.141593F / 180.0F);

			this.motX += f * f5 - f1 * f4;
			this.motZ += f1 * f5 + f * f4;
		}
	}

	public float c(float f) {
		int i = MathHelper.floor(this.locX);
		int j = MathHelper.floor(this.locZ);

		if (this.world.isLoaded(i, 0, j)) {
			double d0 = (this.boundingBox.e - this.boundingBox.b) * 0.66D;
			int k = MathHelper.floor(this.locY - this.height + d0);

			return this.world.o(i, k, j);
		}
		return 0.0F;
	}

	public void spawnIn(World world)
	{
		if (world == null) {
			die();
			this.world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
			return;
		}

		this.world = world;
	}

	public void setLocation(double d0, double d1, double d2, float f, float f1) {
		this.lastX = (this.locX = d0);
		this.lastY = (this.locY = d1);
		this.lastZ = (this.locZ = d2);
		this.lastYaw = (this.yaw = f);
		this.lastPitch = (this.pitch = f1);
		this.V = 0.0F;
		double d3 = this.lastYaw - f;

		if (d3 < -180.0D) {
			this.lastYaw += 360.0F;
		}

		if (d3 >= 180.0D) {
			this.lastYaw -= 360.0F;
		}

		setPosition(this.locX, this.locY, this.locZ);
		b(f, f1);
	}

	public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
		this.S = (this.lastX = this.locX = d0);
		this.T = (this.lastY = this.locY = d1 + this.height);
		this.U = (this.lastZ = this.locZ = d2);
		this.yaw = f;
		this.pitch = f1;
		setPosition(this.locX, this.locY, this.locZ);
	}

	public float d(Entity entity) {
		float f = (float)(this.locX - entity.locX);
		float f1 = (float)(this.locY - entity.locY);
		float f2 = (float)(this.locZ - entity.locZ);

		return MathHelper.c(f * f + f1 * f1 + f2 * f2);
	}

	public double e(double d0, double d1, double d2) {
		double d3 = this.locX - d0;
		double d4 = this.locY - d1;
		double d5 = this.locZ - d2;

		return d3 * d3 + d4 * d4 + d5 * d5;
	}

	public double f(double d0, double d1, double d2) {
		double d3 = this.locX - d0;
		double d4 = this.locY - d1;
		double d5 = this.locZ - d2;

		return MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
	}

	public double e(Entity entity) {
		double d0 = this.locX - entity.locX;
		double d1 = this.locY - entity.locY;
		double d2 = this.locZ - entity.locZ;

		return d0 * d0 + d1 * d1 + d2 * d2;
	}
	public void b_(EntityHuman entityhuman) {
	}

	public void collide(Entity entity) {
		if ((entity.passenger != this) && (entity.vehicle != this)) {
			double d0 = entity.locX - this.locX;
			double d1 = entity.locZ - this.locZ;
			double d2 = MathHelper.a(d0, d1);

			if (d2 >= 0.009999999776482582D) {
				d2 = MathHelper.sqrt(d2);
				d0 /= d2;
				d1 /= d2;
				double d3 = 1.0D / d2;

				if (d3 > 1.0D) {
					d3 = 1.0D;
				}

				d0 *= d3;
				d1 *= d3;
				d0 *= 0.0500000007450581D;
				d1 *= 0.0500000007450581D;
				d0 *= (1.0F - this.Y);
				d1 *= (1.0F - this.Y);
				g(-d0, 0.0D, -d1);
				entity.g(d0, 0.0D, d1);
			}
		}
	}

	public void g(double d0, double d1, double d2) {
		this.motX += d0;
		this.motY += d1;
		this.motZ += d2;
		this.al = true;
	}

	protected void K() {
		this.velocityChanged = true;
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		K();
		return false;
	}

	public boolean L() {
		return false;
	}

	public boolean M() {
		return false;
	}
	public void c(Entity entity, int i) {
	}

	public boolean c(NBTTagCompound nbttagcompound) {
		String s = Q();

		if ((!this.dead) && (s != null)) {
			nbttagcompound.setString("id", s);
			d(nbttagcompound);
			return true;
		}
		return false;
	}

	public void d(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.set("Pos", a(new double[] { this.locX, this.locY + this.V, this.locZ }));
		nbttagcompound.set("Motion", a(new double[] { this.motX, this.motY, this.motZ }));

		if (Float.isNaN(this.yaw)) {
			this.yaw = 0.0F;
		}

		if (Float.isNaN(this.pitch)) {
			this.pitch = 0.0F;
		}

		nbttagcompound.set("Rotation", a(new float[] { this.yaw, this.pitch }));
		nbttagcompound.setFloat("FallDistance", this.fallDistance);
		nbttagcompound.setShort("Fire", (short)this.fireTicks);
		nbttagcompound.setShort("Air", (short)getAirTicks());
		nbttagcompound.setBoolean("OnGround", this.onGround);

		nbttagcompound.setLong("WorldUUIDLeast", this.world.getDataManager().getUUID().getLeastSignificantBits());
		nbttagcompound.setLong("WorldUUIDMost", this.world.getDataManager().getUUID().getMostSignificantBits());
		nbttagcompound.setLong("UUIDLeast", this.uniqueId.getLeastSignificantBits());
		nbttagcompound.setLong("UUIDMost", this.uniqueId.getMostSignificantBits());

		b(nbttagcompound);
	}

	public void e(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist = nbttagcompound.getList("Pos");
		NBTTagList nbttaglist1 = nbttagcompound.getList("Motion");
		NBTTagList nbttaglist2 = nbttagcompound.getList("Rotation");

		this.motX = ((NBTTagDouble)nbttaglist1.get(0)).data;
		this.motY = ((NBTTagDouble)nbttaglist1.get(1)).data;
		this.motZ = ((NBTTagDouble)nbttaglist1.get(2)).data;

		this.lastX = (this.S = this.locX = ((NBTTagDouble)nbttaglist.get(0)).data);
		this.lastY = (this.T = this.locY = ((NBTTagDouble)nbttaglist.get(1)).data);
		this.lastZ = (this.U = this.locZ = ((NBTTagDouble)nbttaglist.get(2)).data);
		this.lastYaw = (this.yaw = ((NBTTagFloat)nbttaglist2.get(0)).data);
		this.lastPitch = (this.pitch = ((NBTTagFloat)nbttaglist2.get(1)).data);
		this.fallDistance = nbttagcompound.getFloat("FallDistance");
		this.fireTicks = nbttagcompound.getShort("Fire");
		setAirTicks(nbttagcompound.getShort("Air"));
		this.onGround = nbttagcompound.getBoolean("OnGround");
		setPosition(this.locX, this.locY, this.locZ);

		long least = nbttagcompound.getLong("UUIDLeast");
		long most = nbttagcompound.getLong("UUIDMost");

		if ((least != 0L) && (most != 0L)) {
			this.uniqueId = new UUID(most, least);
		}

		b(this.yaw, this.pitch);
		a(nbttagcompound);

		if (!(getBukkitEntity() instanceof Vehicle)) {
			if (Math.abs(this.motX) > 10.0D) {
				this.motX = 0.0D;
			}

			if (Math.abs(this.motY) > 10.0D) {
				this.motY = 0.0D;
			}

			if (Math.abs(this.motZ) > 10.0D) {
				this.motZ = 0.0D;
			}

		}

		if ((this instanceof EntityPlayer)) {
			Server server = Bukkit.getServer();
			org.bukkit.World bworld = null;

			String worldName = nbttagcompound.getString("World");

			if ((nbttagcompound.hasKey("WorldUUIDMost")) && (nbttagcompound.hasKey("WorldUUIDLeast"))) {
				UUID uid = new UUID(nbttagcompound.getLong("WorldUUIDMost"), nbttagcompound.getLong("WorldUUIDLeast"));
				bworld = server.getWorld(uid);
			} else {
				bworld = server.getWorld(worldName);
			}

			if (bworld == null) {
				EntityPlayer entityPlayer = (EntityPlayer)this;
				bworld = ((CraftServer)server).getServer().getWorldServer(entityPlayer.dimension).getWorld();
			}

			spawnIn(bworld == null ? null : ((CraftWorld)bworld).getHandle());
		}
	}

	protected final String Q()
	{
		return EntityTypes.b(this);
	}
	protected abstract void a(NBTTagCompound paramNBTTagCompound);

	protected abstract void b(NBTTagCompound paramNBTTagCompound);

	protected NBTTagList a(double[] adouble) { NBTTagList nbttaglist = new NBTTagList();
		double[] adouble1 = adouble;
		int i = adouble.length;

		for (int j = 0; j < i; j++) {
			double d0 = adouble1[j];

			nbttaglist.add(new NBTTagDouble((String)null, d0));
		}

		return nbttaglist; }

	protected NBTTagList a(float[] afloat)
	{
		NBTTagList nbttaglist = new NBTTagList();
		float[] afloat1 = afloat;
		int i = afloat.length;

		for (int j = 0; j < i; j++) {
			float f = afloat1[j];

			nbttaglist.add(new NBTTagFloat((String)null, f));
		}

		return nbttaglist;
	}

	public EntityItem b(int i, int j) {
		return a(i, j, 0.0F);
	}

	public EntityItem a(int i, int j, float f) {
		return a(new ItemStack(i, j, 0), f);
	}

	public EntityItem a(ItemStack itemstack, float f) {
		EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY + f, this.locZ, itemstack);

		entityitem.pickupDelay = 10;
		this.world.addEntity(entityitem);
		return entityitem;
	}

	public boolean isAlive() {
		return !this.dead;
	}

	public boolean inBlock() {
		for (int i = 0; i < 8; i++) {
			float f = ((i >> 0) % 2 - 0.5F) * this.width * 0.8F;
			float f1 = ((i >> 1) % 2 - 0.5F) * 0.1F;
			float f2 = ((i >> 2) % 2 - 0.5F) * this.width * 0.8F;
			int j = MathHelper.floor(this.locX + f);
			int k = MathHelper.floor(this.locY + getHeadHeight() + f1);
			int l = MathHelper.floor(this.locZ + f2);

			if (this.world.s(j, k, l)) {
				return true;
			}
		}

		return false;
	}

	public boolean c(EntityHuman entityhuman) {
		return false;
	}

	public AxisAlignedBB g(Entity entity) {
		return null;
	}

	public void U() {
		if (this.vehicle.dead) {
			this.vehicle = null;
		} else {
			this.motX = 0.0D;
			this.motY = 0.0D;
			this.motZ = 0.0D;
			h_();
			if (this.vehicle != null) {
				this.vehicle.V();
				this.f += this.vehicle.yaw - this.vehicle.lastYaw;

				for (this.e += this.vehicle.pitch - this.vehicle.lastPitch; this.f >= 180.0D; this.f -= 360.0D);
				while (this.f < -180.0D) {
					this.f += 360.0D;
				}

				while (this.e >= 180.0D) {
					this.e -= 360.0D;
				}

				while (this.e < -180.0D) {
					this.e += 360.0D;
				}

				double d0 = this.f * 0.5D;
				double d1 = this.e * 0.5D;
				float f = 10.0F;

				if (d0 > f) {
					d0 = f;
				}

				if (d0 < -f) {
					d0 = -f;
				}

				if (d1 > f) {
					d1 = f;
				}

				if (d1 < -f) {
					d1 = -f;
				}

				this.f -= d0;
				this.e -= d1;
				this.yaw = (float)(this.yaw + d0);
				this.pitch = (float)(this.pitch + d1);
			}
		}
	}

	public void V() {
		if ((!(this.passenger instanceof EntityHuman)) || (!((EntityHuman)this.passenger).bF())) {
			this.passenger.S = this.passenger.locX;
			this.passenger.T = this.passenger.locY;
			this.passenger.U = this.passenger.locZ;
		}

		this.passenger.setPosition(this.locX, this.locY + X() + this.passenger.W(), this.locZ);
	}

	public double W() {
		return this.height;
	}

	public double X() {
		return this.length * 0.75D;
	}

	public void mount(Entity entity)
	{
		setPassengerOf(entity);
	}

	public org.bukkit.entity.Entity getBukkitEntity()
	{
		if (this.bukkitEntity == null) {
			this.bukkitEntity = CraftEntity.getEntity(this.world.getServer(), this);
		}
		return this.bukkitEntity;
	}

	public void setPassengerOf(Entity entity)
	{
		PluginManager pluginManager = Bukkit.getPluginManager();
		getBukkitEntity();

		this.e = 0.0D;
		this.f = 0.0D;
		if (entity == null) {
			if (this.vehicle != null)
			{
				if (((this.bukkitEntity instanceof LivingEntity)) && ((this.vehicle.getBukkitEntity() instanceof Vehicle))) {
					VehicleExitEvent event = new VehicleExitEvent((Vehicle)this.vehicle.getBukkitEntity(), (LivingEntity)this.bukkitEntity);
					pluginManager.callEvent(event);
				}

				setPositionRotation(this.vehicle.locX, this.vehicle.boundingBox.b + this.vehicle.length, this.vehicle.locZ, this.yaw, this.pitch);
				this.vehicle.passenger = null;
			}

			this.vehicle = null;
		} else if (this.vehicle == entity)
		{
			if (((this.bukkitEntity instanceof LivingEntity)) && ((this.vehicle.getBukkitEntity() instanceof Vehicle))) {
				VehicleExitEvent event = new VehicleExitEvent((Vehicle)this.vehicle.getBukkitEntity(), (LivingEntity)this.bukkitEntity);
				pluginManager.callEvent(event);
			}

			h(entity);
			this.vehicle.passenger = null;
			this.vehicle = null;
		}
		else {
			if (((this.bukkitEntity instanceof LivingEntity)) && ((entity.getBukkitEntity() instanceof Vehicle))) {
				VehicleEnterEvent event = new VehicleEnterEvent((Vehicle)entity.getBukkitEntity(), this.bukkitEntity);
				pluginManager.callEvent(event);

				if (event.isCancelled()) {
					return;
				}

			}

			if (this.vehicle != null) {
				this.vehicle.passenger = null;
			}

			if (entity.passenger != null) {
				entity.passenger.vehicle = null;
			}

			this.vehicle = entity;
			entity.passenger = this;
		}
	}

	public void h(Entity entity) {
		double d0 = entity.locX;
		double d1 = entity.boundingBox.b + entity.length;
		double d2 = entity.locZ;

		for (double d3 = -1.5D; d3 < 2.0D; d3 += 1.0D) {
			for (double d4 = -1.5D; d4 < 2.0D; d4 += 1.0D) {
				if ((d3 != 0.0D) || (d4 != 0.0D)) {
					int i = (int)(this.locX + d3);
					int j = (int)(this.locZ + d4);
					AxisAlignedBB axisalignedbb = this.boundingBox.c(d3, 1.0D, d4);

					if (this.world.a(axisalignedbb).isEmpty()) {
						if (this.world.t(i, (int)this.locY, j)) {
							setPositionRotation(this.locX + d3, this.locY + 1.0D, this.locZ + d4, this.yaw, this.pitch);
							return;
						}

						if ((this.world.t(i, (int)this.locY - 1, j)) || (this.world.getMaterial(i, (int)this.locY - 1, j) == Material.WATER)) {
							d0 = this.locX + d3;
							d1 = this.locY + 1.0D;
							d2 = this.locZ + d4;
						}
					}
				}
			}
		}

		setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
	}

	public float Y() {
		return 0.1F;
	}

	public Vec3D Z() {
		return null;
	}
	public void aa() {
	}

	public ItemStack[] getEquipment() {
		return null;
	}

	public boolean isBurning() {
		return (this.fireTicks > 0) || (f(0));
	}

	public boolean isSneaking() {
		return f(1);
	}

	public void setSneaking(boolean flag) {
		a(1, flag);
	}

	public boolean isSprinting() {
		return f(3);
	}

	public void setSprinting(boolean flag) {
		a(3, flag);
	}

	public void c(boolean flag) {
		a(4, flag);
	}

	protected boolean f(int i) {
		return (this.datawatcher.getByte(0) & 1 << i) != 0;
	}

	protected void a(int i, boolean flag) {
		byte b0 = this.datawatcher.getByte(0);

		if (flag)
			this.datawatcher.watch(0, Byte.valueOf((byte)(b0 | 1 << i)));
		else
			this.datawatcher.watch(0, Byte.valueOf((byte)(b0 & (1 << i ^ 0xFFFFFFFF))));
	}

	public int getAirTicks()
	{
		return this.datawatcher.getShort(1);
	}

	public void setAirTicks(int i) {
		this.datawatcher.watch(1, Short.valueOf((short)i));
	}

	public void a(EntityLightning entitylightning)
	{
		org.bukkit.entity.Entity thisBukkitEntity = getBukkitEntity();
		org.bukkit.entity.Entity stormBukkitEntity = entitylightning.getBukkitEntity();
		PluginManager pluginManager = Bukkit.getPluginManager();

		if ((thisBukkitEntity instanceof Painting)) {
			PaintingBreakByEntityEvent event = new PaintingBreakByEntityEvent((Painting)thisBukkitEntity, stormBukkitEntity);
			pluginManager.callEvent(event);

			if (event.isCancelled()) {
				return;
			}
		}

		EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(stormBukkitEntity, thisBukkitEntity, EntityDamageEvent.DamageCause.LIGHTNING, 5);
		pluginManager.callEvent(event);

		if (event.isCancelled()) {
			return;
		}

		thisBukkitEntity.setLastDamageCause(event);
		burn(event.getDamage());

		this.fireTicks += 1;
		if (this.fireTicks == 0)
		{
			EntityCombustByEntityEvent entityCombustEvent = new EntityCombustByEntityEvent(stormBukkitEntity, thisBukkitEntity, 8);
			pluginManager.callEvent(entityCombustEvent);
			if (!entityCombustEvent.isCancelled())
				setOnFire(entityCombustEvent.getDuration());
		}
	}

	public void a(EntityLiving entityliving)
	{
	}

	protected boolean i(double d0, double d1, double d2) {
		int i = MathHelper.floor(d0);
		int j = MathHelper.floor(d1);
		int k = MathHelper.floor(d2);
		double d3 = d0 - i;
		double d4 = d1 - j;
		double d5 = d2 - k;

		if (this.world.s(i, j, k)) {
			boolean flag = !this.world.s(i - 1, j, k);
			boolean flag1 = !this.world.s(i + 1, j, k);
			boolean flag2 = !this.world.s(i, j - 1, k);
			boolean flag3 = !this.world.s(i, j + 1, k);
			boolean flag4 = !this.world.s(i, j, k - 1);
			boolean flag5 = !this.world.s(i, j, k + 1);
			byte b0 = -1;
			double d6 = 9999.0D;

			if ((flag) && (d3 < d6)) {
				d6 = d3;
				b0 = 0;
			}

			if ((flag1) && (1.0D - d3 < d6)) {
				d6 = 1.0D - d3;
				b0 = 1;
			}

			if ((flag2) && (d4 < d6)) {
				d6 = d4;
				b0 = 2;
			}

			if ((flag3) && (1.0D - d4 < d6)) {
				d6 = 1.0D - d4;
				b0 = 3;
			}

			if ((flag4) && (d5 < d6)) {
				d6 = d5;
				b0 = 4;
			}

			if ((flag5) && (1.0D - d5 < d6)) {
				d6 = 1.0D - d5;
				b0 = 5;
			}

			float f = this.random.nextFloat() * 0.2F + 0.1F;

			if (b0 == 0) {
				this.motX = (-f);
			}

			if (b0 == 1) {
				this.motX = f;
			}

			if (b0 == 2) {
				this.motY = (-f);
			}

			if (b0 == 3) {
				this.motY = f;
			}

			if (b0 == 4) {
				this.motZ = (-f);
			}

			if (b0 == 5) {
				this.motZ = f;
			}

			return true;
		}
		return false;
	}

	public void aj()
	{
		this.J = true;
		this.fallDistance = 0.0F;
	}

	public String getLocalizedName() {
		String s = EntityTypes.b(this);

		if (s == null) {
			s = "generic";
		}

		return LocaleI18n.get("entity." + s + ".name");
	}

	public Entity[] al() {
		return null;
	}

	public boolean i(Entity entity) {
		return this == entity;
	}

	public float am() {
		return 0.0F;
	}

	public boolean an() {
		return true;
	}

	public String toString() {
		return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", new Object[] { getClass().getSimpleName(), getLocalizedName(), Integer.valueOf(this.id), this.world == null ? "~NULL~" : this.world.getWorldData().getName(), Double.valueOf(this.locX), Double.valueOf(this.locY), Double.valueOf(this.locZ) });
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Entity
 * JD-Core Version:		0.6.0
 */