package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.PluginManager;

public class EntityArrow extends Entity
{
	private int d = -1;
	private int e = -1;
	private int f = -1;
	private int g = 0;
	private int h = 0;
	private boolean inGround = false;
	public int fromPlayer = 0;
	public int shake = 0;
	public Entity shooter;
	private int j;
	private int an = 0;
	private double damage = 2.0D;
	private int ap;

	public EntityArrow(World world)
	{
		super(world);
		a(0.5F, 0.5F);
	}

	public EntityArrow(World world, double d0, double d1, double d2) {
		super(world);
		a(0.5F, 0.5F);
		setPosition(d0, d1, d2);
		this.height = 0.0F;
	}

	public EntityArrow(World world, EntityLiving entityliving, EntityLiving entityliving1, float f, float f1) {
		super(world);
		this.shooter = entityliving;
		if ((entityliving instanceof EntityHuman)) {
			this.fromPlayer = 1;
		}

		this.locY = (entityliving.locY + entityliving.getHeadHeight() - 0.1000000014901161D);
		double d0 = entityliving1.locX - entityliving.locX;
		double d1 = entityliving1.locY + entityliving1.getHeadHeight() - 0.699999988079071D - this.locY;
		double d2 = entityliving1.locZ - entityliving.locZ;
		double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-007D) {
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592741012573D) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / 3.141592741012573D));
			double d4 = d0 / d3;
			double d5 = d2 / d3;

			setPositionRotation(entityliving.locX + d4, this.locY, entityliving.locZ + d5, f2, f3);
			this.height = 0.0F;
			float f4 = (float)d3 * 0.2F;

			shoot(d0, d1 + f4, d2, f, f1);
		}
	}

	public EntityArrow(World world, EntityLiving entityliving, float f) {
		super(world);
		this.shooter = entityliving;
		if ((entityliving instanceof EntityHuman)) {
			this.fromPlayer = 1;
		}

		a(0.5F, 0.5F);
		setPositionRotation(entityliving.locX, entityliving.locY + entityliving.getHeadHeight(), entityliving.locZ, entityliving.yaw, entityliving.pitch);
		this.locX -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
		this.locY -= 0.1000000014901161D;
		this.locZ -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
		setPosition(this.locX, this.locY, this.locZ);
		this.height = 0.0F;
		this.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
		this.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
		this.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F));
		shoot(this.motX, this.motY, this.motZ, f * 1.5F, 1.0F);
	}

	protected void a() {
		this.datawatcher.a(16, Byte.valueOf(0));
	}

	public void shoot(double d0, double d1, double d2, float f, float f1) {
		float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

		d0 /= f2;
		d1 /= f2;
		d2 /= f2;
		d0 += this.random.nextGaussian() * 0.007499999832361937D * f1;
		d1 += this.random.nextGaussian() * 0.007499999832361937D * f1;
		d2 += this.random.nextGaussian() * 0.007499999832361937D * f1;
		d0 *= f;
		d1 *= f;
		d2 *= f;
		this.motX = d0;
		this.motY = d1;
		this.motZ = d2;
		float f3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

		this.lastYaw = (this.yaw = (float)(Math.atan2(d0, d2) * 180.0D / 3.141592741012573D));
		this.lastPitch = (this.pitch = (float)(Math.atan2(d1, f3) * 180.0D / 3.141592741012573D));
		this.j = 0;
	}

	public void h_() {
		super.h_();
		if ((this.lastPitch == 0.0F) && (this.lastYaw == 0.0F)) {
			float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);

			this.lastYaw = (this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D));
			this.lastPitch = (this.pitch = (float)(Math.atan2(this.motY, f) * 180.0D / 3.141592741012573D));
		}

		int i = this.world.getTypeId(this.d, this.e, this.f);

		if (i > 0) {
			Block.byId[i].updateShape(this.world, this.d, this.e, this.f);
			AxisAlignedBB axisalignedbb = Block.byId[i].e(this.world, this.d, this.e, this.f);

			if ((axisalignedbb != null) && (axisalignedbb.a(Vec3D.a().create(this.locX, this.locY, this.locZ)))) {
				this.inGround = true;
			}
		}

		if (this.shake > 0) {
			this.shake -= 1;
		}

		if (this.inGround) {
			int j = this.world.getTypeId(this.d, this.e, this.f);
			int k = this.world.getData(this.d, this.e, this.f);

			if ((j == this.g) && (k == this.h)) {
				this.j += 1;
				if (this.j == 1200)
					die();
			}
			else {
				this.inGround = false;
				this.motX *= this.random.nextFloat() * 0.2F;
				this.motY *= this.random.nextFloat() * 0.2F;
				this.motZ *= this.random.nextFloat() * 0.2F;
				this.j = 0;
				this.an = 0;
			}
		} else {
			this.an += 1;
			Vec3D vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
			Vec3D vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
			MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1, false, true);

			vec3d = Vec3D.a().create(this.locX, this.locY, this.locZ);
			vec3d1 = Vec3D.a().create(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
			if (movingobjectposition != null) {
				vec3d1 = Vec3D.a().create(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
			}

			Entity entity = null;
			List list = this.world.getEntities(this, this.boundingBox.a(this.motX, this.motY, this.motZ).grow(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				Entity entity1 = (Entity)iterator.next();

				if ((entity1.L()) && ((entity1 != this.shooter) || (this.an >= 5))) {
					float f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.grow(f1, f1, f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.a(vec3d, vec3d1);

					if (movingobjectposition1 != null) {
						double d1 = vec3d.distanceSquared(movingobjectposition1.pos);

						if ((d1 < d0) || (d0 == 0.0D)) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null)
			{
				Projectile projectile = (Projectile)getBukkitEntity();
				ProjectileHitEvent phe = new ProjectileHitEvent(projectile);
				this.world.getServer().getPluginManager().callEvent(phe);

				if (movingobjectposition.entity != null) {
					float f2 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
					int l = MathHelper.f(f2 * this.damage);

					if (g()) {
						l += this.random.nextInt(l / 2 + 2);
					}

					DamageSource damagesource = null;

					if (this.shooter == null)
						damagesource = DamageSource.arrow(this, this);
					else {
						damagesource = DamageSource.arrow(this, this.shooter);
					}

					if (movingobjectposition.entity.damageEntity(damagesource, l)) {
						if ((isBurning()) && ((!(movingobjectposition.entity instanceof EntityPlayer)) || (this.world.pvpMode))) {
							EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(getBukkitEntity(), entity.getBukkitEntity(), 5);
							Bukkit.getPluginManager().callEvent(combustEvent);

							if (!combustEvent.isCancelled()) {
								movingobjectposition.entity.setOnFire(combustEvent.getDuration());
							}

						}

						if ((movingobjectposition.entity instanceof EntityLiving)) {
							((EntityLiving)movingobjectposition.entity).bd += 1;
							if (this.ap > 0) {
								float f3 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);

								if (f3 > 0.0F) {
									movingobjectposition.entity.g(this.motX * this.ap * 0.6000000238418579D / f3, 0.1D, this.motZ * this.ap * 0.6000000238418579D / f3);
								}
							}
						}

						this.world.makeSound(this, "random.bowhit", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
						die();
					} else {
						this.motX *= -0.1000000014901161D;
						this.motY *= -0.1000000014901161D;
						this.motZ *= -0.1000000014901161D;
						this.yaw += 180.0F;
						this.lastYaw += 180.0F;
						this.an = 0;
					}
				} else {
					this.d = movingobjectposition.b;
					this.e = movingobjectposition.c;
					this.f = movingobjectposition.d;
					this.g = this.world.getTypeId(this.d, this.e, this.f);
					this.h = this.world.getData(this.d, this.e, this.f);
					this.motX = (float)(movingobjectposition.pos.a - this.locX);
					this.motY = (float)(movingobjectposition.pos.b - this.locY);
					this.motZ = (float)(movingobjectposition.pos.c - this.locZ);
					float f2 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
					this.locX -= this.motX / f2 * 0.0500000007450581D;
					this.locY -= this.motY / f2 * 0.0500000007450581D;
					this.locZ -= this.motZ / f2 * 0.0500000007450581D;
					this.world.makeSound(this, "random.bowhit", 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.shake = 7;
					d(false);
				}
			}

			if (g()) {
				for (int i1 = 0; i1 < 4; i1++) {
					this.world.a("crit", this.locX + this.motX * i1 / 4.0D, this.locY + this.motY * i1 / 4.0D, this.locZ + this.motZ * i1 / 4.0D, -this.motX, -this.motY + 0.2D, -this.motZ);
				}
			}

			this.locX += this.motX;
			this.locY += this.motY;
			this.locZ += this.motZ;
			float f2 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
			this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0D / 3.141592741012573D);

			for (this.pitch = (float)(Math.atan2(this.motY, f2) * 180.0D / 3.141592741012573D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
			while (this.pitch - this.lastPitch >= 180.0F) {
				this.lastPitch += 360.0F;
			}

			while (this.yaw - this.lastYaw < -180.0F) {
				this.lastYaw -= 360.0F;
			}

			while (this.yaw - this.lastYaw >= 180.0F) {
				this.lastYaw += 360.0F;
			}

			this.pitch = (this.lastPitch + (this.pitch - this.lastPitch) * 0.2F);
			this.yaw = (this.lastYaw + (this.yaw - this.lastYaw) * 0.2F);
			float f4 = 0.99F;

			float f1 = 0.05F;
			if (H()) {
				for (int j1 = 0; j1 < 4; j1++) {
					float f5 = 0.25F;

					this.world.a("bubble", this.locX - this.motX * f5, this.locY - this.motY * f5, this.locZ - this.motZ * f5, this.motX, this.motY, this.motZ);
				}

				f4 = 0.8F;
			}

			this.motX *= f4;
			this.motY *= f4;
			this.motZ *= f4;
			this.motY -= f1;
			setPosition(this.locX, this.locY, this.locZ);
			D();
		}
	}

	public void b(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("xTile", (short)this.d);
		nbttagcompound.setShort("yTile", (short)this.e);
		nbttagcompound.setShort("zTile", (short)this.f);
		nbttagcompound.setByte("inTile", (byte)this.g);
		nbttagcompound.setByte("inData", (byte)this.h);
		nbttagcompound.setByte("shake", (byte)this.shake);
		nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		nbttagcompound.setByte("pickup", (byte)this.fromPlayer);
		nbttagcompound.setDouble("damage", this.damage);
	}

	public void a(NBTTagCompound nbttagcompound) {
		this.d = nbttagcompound.getShort("xTile");
		this.e = nbttagcompound.getShort("yTile");
		this.f = nbttagcompound.getShort("zTile");
		this.g = (nbttagcompound.getByte("inTile") & 0xFF);
		this.h = (nbttagcompound.getByte("inData") & 0xFF);
		this.shake = (nbttagcompound.getByte("shake") & 0xFF);
		this.inGround = (nbttagcompound.getByte("inGround") == 1);
		if (nbttagcompound.hasKey("damage")) {
			this.damage = nbttagcompound.getDouble("damage");
		}

		if (nbttagcompound.hasKey("pickup"))
			this.fromPlayer = nbttagcompound.getByte("pickup");
		else if (nbttagcompound.hasKey("player"))
			this.fromPlayer = (nbttagcompound.getBoolean("player") ? 1 : 0);
	}

	public void b_(EntityHuman entityhuman)
	{
		if ((!this.world.isStatic) && (this.inGround) && (this.shake <= 0))
		{
			ItemStack itemstack = new ItemStack(Item.ARROW);
			if ((this.inGround) && (this.fromPlayer == 1) && (this.shake <= 0) && (entityhuman.inventory.canHold(itemstack) > 0)) {
				EntityItem item = new EntityItem(this.world, this.locX, this.locY, this.locZ, itemstack);

				PlayerPickupItemEvent event = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), new CraftItem(this.world.getServer(), this, item), 0);
				this.world.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) {
					return;
				}

			}

			boolean flag = (this.fromPlayer == 1) || ((this.fromPlayer == 2) && (entityhuman.abilities.canInstantlyBuild));

			if ((this.fromPlayer == 1) && (!entityhuman.inventory.pickup(new ItemStack(Item.ARROW, 1)))) {
				flag = false;
			}

			if (flag) {
				this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				entityhuman.receive(this, 1);
				die();
			}
		}
	}

	public void b(double d0) {
		this.damage = d0;
	}

	public double d() {
		return this.damage;
	}

	public void a(int i) {
		this.ap = i;
	}

	public boolean an() {
		return false;
	}

	public void d(boolean flag) {
		byte b0 = this.datawatcher.getByte(16);

		if (flag)
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x1)));
		else
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
	}

	public boolean g()
	{
		byte b0 = this.datawatcher.getByte(16);

		return (b0 & 0x1) != 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityArrow
 * JD-Core Version:		0.6.0
 */