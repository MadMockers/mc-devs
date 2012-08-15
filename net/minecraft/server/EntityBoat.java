package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.plugin.PluginManager;

public class EntityBoat extends Entity
{
	private boolean a;
	private double b;
	private int c;
	private double d;
	private double e;
	private double f;
	private double g;
	private double h;
	public double maxSpeed = 0.4D;
	public double occupiedDeceleration = 0.2D;
	public double unoccupiedDeceleration = -1.0D;
	public boolean landBoats = false;

	public void collide(Entity entity)
	{
		org.bukkit.entity.Entity hitEntity = entity == null ? null : entity.getBukkitEntity();

		VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), hitEntity);
		this.world.getServer().getPluginManager().callEvent(event);

		if (event.isCancelled()) {
			return;
		}

		super.collide(entity);
	}

	public EntityBoat(World world)
	{
		super(world);
		this.a = true;
		this.b = 0.07000000000000001D;
		this.m = true;
		a(1.5F, 0.6F);
		this.height = (this.length / 2.0F);
	}

	protected boolean e_() {
		return false;
	}

	protected void a() {
		this.datawatcher.a(17, new Integer(0));
		this.datawatcher.a(18, new Integer(1));
		this.datawatcher.a(19, new Integer(0));
	}

	public AxisAlignedBB g(Entity entity) {
		return entity.boundingBox;
	}

	public AxisAlignedBB E() {
		return this.boundingBox;
	}

	public boolean M() {
		return true;
	}

	public EntityBoat(World world, double d0, double d1, double d2) {
		this(world);
		setPosition(d0, d1 + this.height, d2);
		this.motX = 0.0D;
		this.motY = 0.0D;
		this.motZ = 0.0D;
		this.lastX = d0;
		this.lastY = d1;
		this.lastZ = d2;

		this.world.getServer().getPluginManager().callEvent(new VehicleCreateEvent((Vehicle)getBukkitEntity()));
	}

	public double X() {
		return this.length * 0.0D - 0.300000011920929D;
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		if ((!this.world.isStatic) && (!this.dead))
		{
			Vehicle vehicle = (Vehicle)getBukkitEntity();
			org.bukkit.entity.Entity attacker = damagesource.getEntity() == null ? null : damagesource.getEntity().getBukkitEntity();

			VehicleDamageEvent event = new VehicleDamageEvent(vehicle, attacker, i);
			this.world.getServer().getPluginManager().callEvent(event);

			if (event.isCancelled()) {
				return true;
			}

			c(-i());
			b(10);
			setDamage(getDamage() + i * 10);
			K();
			if (((damagesource.getEntity() instanceof EntityHuman)) && (((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild)) {
				setDamage(100);
			}

			if (getDamage() > 40)
			{
				VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, attacker);
				this.world.getServer().getPluginManager().callEvent(destroyEvent);

				if (destroyEvent.isCancelled()) {
					setDamage(40);
					return true;
				}

				if (this.passenger != null) {
					this.passenger.mount(this);
				}

				a(Item.BOAT.id, 1, 0.0F);
				die();
			}

			return true;
		}
		return true;
	}

	public boolean L()
	{
		return !this.dead;
	}

	public void h_()
	{
		double prevX = this.locX;
		double prevY = this.locY;
		double prevZ = this.locZ;
		float prevYaw = this.yaw;
		float prevPitch = this.pitch;

		super.h_();
		if (h() > 0) {
			b(h() - 1);
		}

		if (getDamage() > 0) {
			setDamage(getDamage() - 1);
		}

		this.lastX = this.locX;
		this.lastY = this.locY;
		this.lastZ = this.locZ;
		byte b0 = 5;
		double d0 = 0.0D;

		for (int i = 0; i < b0; i++) {
			double d1 = this.boundingBox.b + (this.boundingBox.e - this.boundingBox.b) * (i + 0) / b0 - 0.125D;
			double d2 = this.boundingBox.b + (this.boundingBox.e - this.boundingBox.b) * (i + 1) / b0 - 0.125D;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.a().a(this.boundingBox.a, d1, this.boundingBox.c, this.boundingBox.d, d2, this.boundingBox.f);

			if (this.world.b(axisalignedbb, Material.WATER)) {
				d0 += 1.0D / b0;
			}
		}

		double d3 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);

		if (d3 > 0.2625D) {
			double d4 = Math.cos(this.yaw * 3.141592653589793D / 180.0D);
			double d5 = Math.sin(this.yaw * 3.141592653589793D / 180.0D);

			for (int j = 0; j < 1.0D + d3 * 60.0D; j++) {
				double d6 = this.random.nextFloat() * 2.0F - 1.0F;
				double d7 = (this.random.nextInt(2) * 2 - 1) * 0.7D;

				if (this.random.nextBoolean()) {
					double d8 = this.locX - d4 * d6 * 0.8D + d5 * d7;
					double d9 = this.locZ - d5 * d6 * 0.8D - d4 * d7;
					this.world.a("splash", d8, this.locY - 0.125D, d9, this.motX, this.motY, this.motZ);
				} else {
					double d8 = this.locX + d4 + d5 * d6 * 0.7D;
					double d9 = this.locZ + d5 - d4 * d6 * 0.7D;
					this.world.a("splash", d8, this.locY - 0.125D, d9, this.motX, this.motY, this.motZ);
				}

			}

		}

		if ((this.world.isStatic) && (this.a)) {
			if (this.c > 0) {
				double d4 = this.locX + (this.d - this.locX) / this.c;
				double d5 = this.locY + (this.e - this.locY) / this.c;
				double d10 = this.locZ + (this.f - this.locZ) / this.c;
				double d11 = MathHelper.g(this.g - this.yaw);
				this.yaw = (float)(this.yaw + d11 / this.c);
				this.pitch = (float)(this.pitch + (this.h - this.pitch) / this.c);
				this.c -= 1;
				setPosition(d4, d5, d10);
				b(this.yaw, this.pitch);
			} else {
				double d4 = this.locX + this.motX;
				double d5 = this.locY + this.motY;
				double d10 = this.locZ + this.motZ;
				setPosition(d4, d5, d10);
				if (this.onGround) {
					this.motX *= 0.5D;
					this.motY *= 0.5D;
					this.motZ *= 0.5D;
				}

				this.motX *= 0.9900000095367432D;
				this.motY *= 0.949999988079071D;
				this.motZ *= 0.9900000095367432D;
			}
		} else {
			if (d0 < 1.0D) {
				double d4 = d0 * 2.0D - 1.0D;
				this.motY += 0.03999999910593033D * d4;
			} else {
				if (this.motY < 0.0D) {
					this.motY /= 2.0D;
				}

				this.motY += 0.007000000216066837D;
			}

			if (this.passenger != null) {
				this.motX += this.passenger.motX * this.b;
				this.motZ += this.passenger.motZ * this.b;
			}
			else if (this.unoccupiedDeceleration >= 0.0D) {
				this.motX *= this.unoccupiedDeceleration;
				this.motZ *= this.unoccupiedDeceleration;

				if (this.motX <= 1.E-005D) {
					this.motX = 0.0D;
				}
				if (this.motZ <= 1.E-005D) {
					this.motZ = 0.0D;
				}

			}

			double d4 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
			if (d4 > 0.35D) {
				double d5 = 0.35D / d4;
				this.motX *= d5;
				this.motZ *= d5;
				d4 = 0.35D;
			}

			if ((d4 > d3) && (this.b < 0.35D)) {
				this.b += (0.35D - this.b) / 35.0D;
				if (this.b > 0.35D)
					this.b = 0.35D;
			}
			else {
				this.b -= (this.b - 0.07000000000000001D) / 35.0D;
				if (this.b < 0.07000000000000001D) {
					this.b = 0.07000000000000001D;
				}
			}

			if ((this.onGround) && (!this.landBoats)) {
				this.motX *= 0.5D;
				this.motY *= 0.5D;
				this.motZ *= 0.5D;
			}

			move(this.motX, this.motY, this.motZ);
			if ((this.positionChanged) && (d3 > 0.2D)) {
				if (!this.world.isStatic)
				{
					Vehicle vehicle = (Vehicle)getBukkitEntity();
					VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, null);
					this.world.getServer().getPluginManager().callEvent(destroyEvent);
					if (!destroyEvent.isCancelled()) {
						die();

						for (int k = 0; k < 3; k++) {
							a(Block.WOOD.id, 1, 0.0F);
						}

						for (k = 0; k < 2; k++)
							a(Item.STICK.id, 1, 0.0F);
					}
				}
			}
			else
			{
				this.motX *= 0.9900000095367432D;
				this.motY *= 0.949999988079071D;
				this.motZ *= 0.9900000095367432D;
			}

			this.pitch = 0.0F;
			double d5 = this.yaw;
			double d10 = this.lastX - this.locX;
			double d11 = this.lastZ - this.locZ;
			if (d10 * d10 + d11 * d11 > 0.001D) {
				d5 = (float)(Math.atan2(d11, d10) * 180.0D / 3.141592653589793D);
			}

			double d12 = MathHelper.g(d5 - this.yaw);

			if (d12 > 20.0D) {
				d12 = 20.0D;
			}

			if (d12 < -20.0D) {
				d12 = -20.0D;
			}

			this.yaw = (float)(this.yaw + d12);
			b(this.yaw, this.pitch);

			Server server = this.world.getServer();
			org.bukkit.World bworld = this.world.getWorld();

			Location from = new Location(bworld, prevX, prevY, prevZ, prevYaw, prevPitch);
			Location to = new Location(bworld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
			Vehicle vehicle = (Vehicle)getBukkitEntity();

			server.getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));

			if (!from.equals(to)) {
				VehicleMoveEvent event = new VehicleMoveEvent(vehicle, from, to);
				server.getPluginManager().callEvent(event);
			}

			if (!this.world.isStatic) {
				List list = this.world.getEntities(this, this.boundingBox.grow(0.2000000029802322D, 0.0D, 0.2000000029802322D));

				if ((list != null) && (!list.isEmpty())) {
					Iterator iterator = list.iterator();

					while (iterator.hasNext()) {
						Entity entity = (Entity)iterator.next();

						if ((entity != this.passenger) && (entity.M()) && ((entity instanceof EntityBoat))) {
							entity.collide(this);
						}
					}
				}

				for (int l = 0; l < 4; l++) {
					int i1 = MathHelper.floor(this.locX + (l % 2 - 0.5D) * 0.8D);
					int j1 = MathHelper.floor(this.locZ + (l / 2 - 0.5D) * 0.8D);

					for (int k1 = 0; k1 < 2; k1++) {
						int l1 = MathHelper.floor(this.locY) + k1;
						int i2 = this.world.getTypeId(i1, l1, j1);
						int j2 = this.world.getData(i1, l1, j1);

						if (i2 == Block.SNOW.id) {
							this.world.setTypeId(i1, l1, j1, 0);
						} else if (i2 == Block.WATER_LILY.id) {
							Block.WATER_LILY.dropNaturally(this.world, i1, l1, j1, j2, 0.3F, 0);
							this.world.setTypeId(i1, l1, j1, 0);
						}
					}
				}

				if ((this.passenger != null) && (this.passenger.dead)) {
					this.passenger.vehicle = null;
					this.passenger = null;
				}
			}
		}
	}

	public void V() {
		if (this.passenger != null) {
			double d0 = Math.cos(this.yaw * 3.141592653589793D / 180.0D) * 0.4D;
			double d1 = Math.sin(this.yaw * 3.141592653589793D / 180.0D) * 0.4D;

			this.passenger.setPosition(this.locX + d0, this.locY + X() + this.passenger.W(), this.locZ + d1);
		}
	}
	protected void b(NBTTagCompound nbttagcompound) {
	}

	protected void a(NBTTagCompound nbttagcompound) {
	}

	public boolean c(EntityHuman entityhuman) {
		if ((this.passenger != null) && ((this.passenger instanceof EntityHuman)) && (this.passenger != entityhuman)) {
			return true;
		}
		if (!this.world.isStatic) {
			entityhuman.mount(this);
		}

		return true;
	}

	public void setDamage(int i)
	{
		this.datawatcher.watch(19, Integer.valueOf(i));
	}

	public int getDamage() {
		return this.datawatcher.getInt(19);
	}

	public void b(int i) {
		this.datawatcher.watch(17, Integer.valueOf(i));
	}

	public int h() {
		return this.datawatcher.getInt(17);
	}

	public void c(int i) {
		this.datawatcher.watch(18, Integer.valueOf(i));
	}

	public int i() {
		return this.datawatcher.getInt(18);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityBoat
 * JD-Core Version:		0.6.0
 */