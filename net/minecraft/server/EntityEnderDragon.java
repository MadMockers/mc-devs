package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.PortalType;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.util.BlockStateListPopulator;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.plugin.PluginManager;

public class EntityEnderDragon extends EntityComplex
{
	public double b;
	public double c;
	public double d;
	public double[][] e = new double[64][3];
	public int f = -1;
	public EntityComplexPart[] children;
	public EntityComplexPart h;
	public EntityComplexPart i;
	public EntityComplexPart j;
	public EntityComplexPart by;
	public EntityComplexPart bz;
	public EntityComplexPart bA;
	public EntityComplexPart bB;
	public float bC = 0.0F;
	public float bD = 0.0F;
	public boolean bE = false;
	public boolean bF = false;
	private Entity bI;
	public int bG = 0;
	public EntityEnderCrystal bH = null;

	public EntityEnderDragon(World world) {
		super(world);
		EntityComplexPart[] tmp57_54 = new EntityComplexPart[7];
		 tmp74_71 = new EntityComplexPart(this, "head", 6.0F, 6.0F); this.h = tmp74_71; tmp57_54[0] = tmp74_71;
		EntityComplexPart[] tmp79_57 = tmp57_54;
		 tmp96_93 = new EntityComplexPart(this, "body", 8.0F, 8.0F); this.i = tmp96_93; tmp79_57[1] = tmp96_93;
		EntityComplexPart[] tmp101_79 = tmp79_57;
		 tmp118_115 = new EntityComplexPart(this, "tail", 4.0F, 4.0F); this.j = tmp118_115; tmp101_79[2] = tmp118_115;
		EntityComplexPart[] tmp123_101 = tmp101_79;
		 tmp140_137 = new EntityComplexPart(this, "tail", 4.0F, 4.0F); this.by = tmp140_137; tmp123_101[3] = tmp140_137;
		EntityComplexPart[] tmp145_123 = tmp123_101;
		 tmp162_159 = new EntityComplexPart(this, "tail", 4.0F, 4.0F); this.bz = tmp162_159; tmp145_123[4] = tmp162_159;
		EntityComplexPart[] tmp167_145 = tmp145_123;
		 tmp184_181 = new EntityComplexPart(this, "wing", 4.0F, 4.0F); this.bA = tmp184_181; tmp167_145[5] = tmp184_181;
		EntityComplexPart[] tmp189_167 = tmp167_145;
		 tmp207_204 = new EntityComplexPart(this, "wing", 4.0F, 4.0F); this.bB = tmp207_204; tmp189_167[6] = tmp207_204; this.children = tmp189_167;
		this.a = 200;
		setHealth(this.a);
		this.texture = "/mob/enderdragon/ender.png";
		a(16.0F, 8.0F);
		this.X = true;
		this.fireProof = true;
		this.c = 100.0D;
		this.ak = true;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, new Integer(this.a));
	}

	public double[] a(int i, float f) {
		if (this.health <= 0) {
			f = 0.0F;
		}

		f = 1.0F - f;
		int j = this.f - i * 1 & 0x3F;
		int k = this.f - i * 1 - 1 & 0x3F;
		double[] adouble = new double[3];
		double d0 = this.e[j][0];
		double d1 = MathHelper.g(this.e[k][0] - d0);

		adouble[0] = (d0 + d1 * f);
		d0 = this.e[j][1];
		d1 = this.e[k][1] - d0;
		adouble[1] = (d0 + d1 * f);
		adouble[2] = (this.e[j][2] + (this.e[k][2] - this.e[j][2]) * f);
		return adouble;
	}

	public void d() {
		this.bC = this.bD;
		if (!this.world.isStatic) {
			this.datawatcher.watch(16, Integer.valueOf(this.health));
		}

		if (this.health <= 0) {
			float f = (this.random.nextFloat() - 0.5F) * 8.0F;
			float d05 = (this.random.nextFloat() - 0.5F) * 4.0F;
			float f1 = (this.random.nextFloat() - 0.5F) * 8.0F;
			this.world.a("largeexplode", this.locX + f, this.locY + 2.0D + d05, this.locZ + f1, 0.0D, 0.0D, 0.0D);
		} else {
			j();
			float f = 0.2F / (MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 10.0F + 1.0F);
			f *= (float)Math.pow(2.0D, this.motY);
			if (this.bF)
				this.bD += f * 0.5F;
			else {
				this.bD += f;
			}

			this.yaw = MathHelper.g(this.yaw);
			if (this.f < 0) {
				for (int i = 0; i < this.e.length; i++) {
					this.e[i][0] = this.yaw;
					this.e[i][1] = this.locY;
				}
			}

			if (++this.f == this.e.length) {
				this.f = 0;
			}

			this.e[this.f][0] = this.yaw;
			this.e[this.f][1] = this.locY;

			if (this.world.isStatic) {
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
			} else {
				double d0 = this.b - this.locX;
				double d1 = this.c - this.locY;
				double d2 = this.d - this.locZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (this.bI != null) {
					this.b = this.bI.locX;
					this.d = this.bI.locZ;
					double d4 = this.b - this.locX;
					double d5 = this.d - this.locZ;
					double d6 = Math.sqrt(d4 * d4 + d5 * d5);
					double d7 = 0.4000000059604645D + d6 / 80.0D - 1.0D;

					if (d7 > 10.0D) {
						d7 = 10.0D;
					}

					this.c = (this.bI.boundingBox.b + d7);
				} else {
					this.b += this.random.nextGaussian() * 2.0D;
					this.d += this.random.nextGaussian() * 2.0D;
				}

				if ((this.bE) || (d3 < 100.0D) || (d3 > 22500.0D) || (this.positionChanged) || (this.G)) {
					k();
				}

				d1 /= MathHelper.sqrt(d0 * d0 + d2 * d2);
				float f3 = 0.6F;
				if (d1 < -f3) {
					d1 = -f3;
				}

				if (d1 > f3) {
					d1 = f3;
				}

				this.motY += d1 * 0.1000000014901161D;
				this.yaw = MathHelper.g(this.yaw);
				double d8 = 180.0D - Math.atan2(d0, d2) * 180.0D / 3.141592741012573D;
				double d9 = MathHelper.g(d8 - this.yaw);

				if (d9 > 50.0D) {
					d9 = 50.0D;
				}

				if (d9 < -50.0D) {
					d9 = -50.0D;
				}

				Vec3D vec3d = Vec3D.a().create(this.b - this.locX, this.c - this.locY, this.d - this.locZ).b();
				Vec3D vec3d1 = Vec3D.a().create(MathHelper.sin(this.yaw * 3.141593F / 180.0F), this.motY, -MathHelper.cos(this.yaw * 3.141593F / 180.0F)).b();
				float f4 = (float)(vec3d1.b(vec3d) + 0.5D) / 1.5F;

				if (f4 < 0.0F) {
					f4 = 0.0F;
				}

				this.bt *= 0.8F;
				float f5 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 1.0F + 1.0F;
				double d10 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 1.0D + 1.0D;

				if (d10 > 40.0D) {
					d10 = 40.0D;
				}

				this.bt = (float)(this.bt + d9 * (0.699999988079071D / d10 / f5));
				this.yaw += this.bt * 0.1F;
				float f6 = (float)(2.0D / (d10 + 1.0D));
				float f7 = 0.06F;

				a(0.0F, -1.0F, f7 * (f4 * f6 + (1.0F - f6)));
				if (this.bF)
					move(this.motX * 0.800000011920929D, this.motY * 0.800000011920929D, this.motZ * 0.800000011920929D);
				else {
					move(this.motX, this.motY, this.motZ);
				}

				Vec3D vec3d2 = Vec3D.a().create(this.motX, this.motY, this.motZ).b();
				float f8 = (float)(vec3d2.b(vec3d1) + 1.0D) / 2.0F;

				f8 = 0.8F + 0.15F * f8;
				this.motX *= f8;
				this.motZ *= f8;
				this.motY *= 0.910000026226044D;
			}

			this.aq = this.yaw;
			this.h.width = (this.h.length = 3.0F);
			this.j.width = (this.j.length = 2.0F);
			this.by.width = (this.by.length = 2.0F);
			this.bz.width = (this.bz.length = 2.0F);
			this.i.length = 3.0F;
			this.i.width = 5.0F;
			this.bA.length = 2.0F;
			this.bA.width = 4.0F;
			this.bB.length = 3.0F;
			this.bB.width = 4.0F;
			float d05 = (float)(a(5, 1.0F)[1] - a(10, 1.0F)[1]) * 10.0F / 180.0F * 3.141593F;
			float f1 = MathHelper.cos(d05);
			float f9 = -MathHelper.sin(d05);
			float f10 = this.yaw * 3.141593F / 180.0F;
			float f11 = MathHelper.sin(f10);
			float f12 = MathHelper.cos(f10);

			this.i.h_();
			this.i.setPositionRotation(this.locX + f11 * 0.5F, this.locY, this.locZ - f12 * 0.5F, 0.0F, 0.0F);
			this.bA.h_();
			this.bA.setPositionRotation(this.locX + f12 * 4.5F, this.locY + 2.0D, this.locZ + f11 * 4.5F, 0.0F, 0.0F);
			this.bB.h_();
			this.bB.setPositionRotation(this.locX - f12 * 4.5F, this.locY + 2.0D, this.locZ - f11 * 4.5F, 0.0F, 0.0F);
			if ((!this.world.isStatic) && (this.hurtTicks == 0)) {
				a(this.world.getEntities(this, this.bA.boundingBox.grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
				a(this.world.getEntities(this, this.bB.boundingBox.grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
				b(this.world.getEntities(this, this.h.boundingBox.grow(1.0D, 1.0D, 1.0D)));
			}

			double[] adouble = a(5, 1.0F);
			double[] adouble1 = a(0, 1.0F);

			float f3 = MathHelper.sin(this.yaw * 3.141593F / 180.0F - this.bt * 0.01F);
			float f13 = MathHelper.cos(this.yaw * 3.141593F / 180.0F - this.bt * 0.01F);

			this.h.h_();
			this.h.setPositionRotation(this.locX + f3 * 5.5F * f1, this.locY + (adouble1[1] - adouble[1]) * 1.0D + f9 * 5.5F, this.locZ - f13 * 5.5F * f1, 0.0F, 0.0F);

			for (int j = 0; j < 3; j++) {
				EntityComplexPart entitycomplexpart = null;

				if (j == 0) {
					entitycomplexpart = this.j;
				}

				if (j == 1) {
					entitycomplexpart = this.by;
				}

				if (j == 2) {
					entitycomplexpart = this.bz;
				}

				double[] adouble2 = a(12 + j * 2, 1.0F);
				float f14 = this.yaw * 3.141593F / 180.0F + b(adouble2[0] - adouble[0]) * 3.141593F / 180.0F * 1.0F;
				float f15 = MathHelper.sin(f14);
				float f16 = MathHelper.cos(f14);
				float f17 = 1.5F;
				float f18 = (j + 1) * 2.0F;

				entitycomplexpart.h_();
				entitycomplexpart.setPositionRotation(this.locX - (f11 * f17 + f15 * f18) * f1, this.locY + (adouble2[1] - adouble[1]) * 1.0D - (f18 + f17) * f9 + 1.5D, this.locZ + (f12 * f17 + f16 * f18) * f1, 0.0F, 0.0F);
			}

			if (!this.world.isStatic)
				this.bF = (a(this.h.boundingBox) | a(this.i.boundingBox));
		}
	}

	private void j()
	{
		if (this.bH != null) {
			if (this.bH.dead) {
				if (!this.world.isStatic) {
					a(this.h, DamageSource.EXPLOSION, 10);
				}

				this.bH = null;
			} else if ((this.ticksLived % 10 == 0) && (this.health < this.a))
			{
				EntityRegainHealthEvent event = new EntityRegainHealthEvent(getBukkitEntity(), 1, EntityRegainHealthEvent.RegainReason.ENDER_CRYSTAL);
				this.world.getServer().getPluginManager().callEvent(event);

				if (!event.isCancelled()) {
					this.health += event.getAmount();
				}
			}

		}

		if (this.random.nextInt(10) == 0) {
			float f = 32.0F;
			List list = this.world.a(EntityEnderCrystal.class, this.boundingBox.grow(f, f, f));
			EntityEnderCrystal entityendercrystal = null;
			double d0 = 1.7976931348623157E+308D;
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityEnderCrystal entityendercrystal1 = (EntityEnderCrystal)iterator.next();
				double d1 = entityendercrystal1.e(this);

				if (d1 < d0) {
					d0 = d1;
					entityendercrystal = entityendercrystal1;
				}
			}

			this.bH = entityendercrystal;
		}
	}

	private void a(List list) {
		double d0 = (this.i.boundingBox.a + this.i.boundingBox.d) / 2.0D;
		double d1 = (this.i.boundingBox.c + this.i.boundingBox.f) / 2.0D;
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Entity entity = (Entity)iterator.next();

			if ((entity instanceof EntityLiving)) {
				double d2 = entity.locX - d0;
				double d3 = entity.locZ - d1;
				double d4 = d2 * d2 + d3 * d3;

				entity.g(d2 / d4 * 4.0D, 0.2000000029802322D, d3 / d4 * 4.0D);
			}
		}
	}

	private void b(List list) {
		Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			Entity entity = (Entity)iterator.next();

			if ((entity instanceof EntityLiving))
			{
				if (!(entity instanceof EntityHuman)) {
					EntityDamageByEntityEvent damageEvent = new EntityDamageByEntityEvent(getBukkitEntity(), entity.getBukkitEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, 10);
					Bukkit.getPluginManager().callEvent(damageEvent);

					if (!damageEvent.isCancelled()) {
						entity.getBukkitEntity().setLastDamageCause(damageEvent);
						entity.damageEntity(DamageSource.mobAttack(this), damageEvent.getDamage());
					}
				} else {
					entity.damageEntity(DamageSource.mobAttack(this), 10);
				}
			}
		}
	}

	private void k()
	{
		this.bE = false;
		if ((this.random.nextInt(2) == 0) && (!this.world.players.isEmpty())) {
			this.bI = ((Entity)this.world.players.get(this.random.nextInt(this.world.players.size())));
		} else {
			boolean flag = false;
			do
			{
				this.b = 0.0D;
				this.c = (70.0F + this.random.nextFloat() * 50.0F);
				this.d = 0.0D;
				this.b += this.random.nextFloat() * 120.0F - 60.0F;
				this.d += this.random.nextFloat() * 120.0F - 60.0F;
				double d0 = this.locX - this.b;
				double d1 = this.locY - this.c;
				double d2 = this.locZ - this.d;

				flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
			}while (!flag);

			this.bI = null;
		}
	}

	private float b(double d0) {
		return (float)MathHelper.g(d0);
	}

	private boolean a(AxisAlignedBB axisalignedbb) {
		int i = MathHelper.floor(axisalignedbb.a);
		int j = MathHelper.floor(axisalignedbb.b);
		int k = MathHelper.floor(axisalignedbb.c);
		int l = MathHelper.floor(axisalignedbb.d);
		int i1 = MathHelper.floor(axisalignedbb.e);
		int j1 = MathHelper.floor(axisalignedbb.f);
		boolean flag = false;
		boolean flag1 = false;

		List destroyedBlocks = new ArrayList();
		CraftWorld craftWorld = this.world.getWorld();

		for (int k1 = i; k1 <= l; k1++) {
			for (int l1 = j; l1 <= i1; l1++) {
				for (int i2 = k; i2 <= j1; i2++) {
					int j2 = this.world.getTypeId(k1, l1, i2);

					if (j2 != 0) {
						if ((j2 != Block.OBSIDIAN.id) && (j2 != Block.WHITESTONE.id) && (j2 != Block.BEDROCK.id)) {
							flag1 = true;

							destroyedBlocks.add(craftWorld.getBlockAt(k1, l1, i2));
						}
						else {
							flag = true;
						}
					}
				}
			}
		}

		if (flag1)
		{
			org.bukkit.entity.Entity bukkitEntity = getBukkitEntity();
			EntityExplodeEvent event = new EntityExplodeEvent(bukkitEntity, bukkitEntity.getLocation(), destroyedBlocks, 0.0F);
			Bukkit.getPluginManager().callEvent(event);
			if (event.isCancelled())
			{
				return flag;
			}
			for (org.bukkit.block.Block block : event.blockList()) {
				craftWorld.explodeBlock(block, event.getYield());
			}

			double d0 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * this.random.nextFloat();
			double d1 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * this.random.nextFloat();
			double d2 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * this.random.nextFloat();

			this.world.a("largeexplode", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}

		return flag;
	}

	public boolean a(EntityComplexPart entitycomplexpart, DamageSource damagesource, int i) {
		if (entitycomplexpart != this.h) {
			i = i / 4 + 1;
		}

		float f = this.yaw * 3.141593F / 180.0F;
		float f1 = MathHelper.sin(f);
		float f2 = MathHelper.cos(f);

		this.b = (this.locX + f1 * 5.0F + (this.random.nextFloat() - 0.5F) * 2.0F);
		this.c = (this.locY + this.random.nextFloat() * 3.0F + 1.0D);
		this.d = (this.locZ - f2 * 5.0F + (this.random.nextFloat() - 0.5F) * 2.0F);
		this.bI = null;
		if (((damagesource.getEntity() instanceof EntityHuman)) || (damagesource == DamageSource.EXPLOSION)) {
			dealDamage(damagesource, i);
		}

		return true;
	}

	protected void aI() {
		this.bG += 1;
		if ((this.bG >= 180) && (this.bG <= 200)) {
			float f = (this.random.nextFloat() - 0.5F) * 8.0F;
			float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
			float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;

			this.world.a("hugeexplosion", this.locX + f, this.locY + 2.0D + f1, this.locZ + f2, 0.0D, 0.0D, 0.0D);
		}

		if ((!this.world.isStatic) && (this.bG > 150) && (this.bG % 5 == 0)) {
			int i = this.expToDrop / 12;

			while (i > 0) {
				int j = EntityExperienceOrb.getOrbValue(i);
				i -= j;
				this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
			}
		}

		move(0.0D, 0.1000000014901161D, 0.0D);
		this.aq = (this.yaw += 20.0F);
		if ((this.bG == 200) && (!this.world.isStatic)) {
			int i = this.expToDrop - 10 * (this.expToDrop / 12);

			while (i > 0) {
				int j = EntityExperienceOrb.getOrbValue(i);
				i -= j;
				this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
			}

			a(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));
			die();
		}
	}

	private void a(int i, int j) {
		byte b0 = 64;

		BlockEnderPortal.a = true;
		byte b1 = 4;

		BlockStateListPopulator world = new BlockStateListPopulator(this.world.getWorld());

		for (int k = b0 - 1; k <= b0 + 32; k++) {
			for (int l = i - b1; l <= i + b1; l++) {
				for (int i1 = j - b1; i1 <= j + b1; i1++) {
					double d0 = l - i;
					double d1 = i1 - j;
					double d2 = d0 * d0 + d1 * d1;

					if (d2 <= (b1 - 0.5D) * (b1 - 0.5D)) {
						if (k < b0) {
							if (d2 <= (b1 - 1 - 0.5D) * (b1 - 1 - 0.5D))
								world.setTypeId(l, k, i1, Block.BEDROCK.id);
						}
						else if (k > b0)
							world.setTypeId(l, k, i1, 0);
						else if (d2 > (b1 - 1 - 0.5D) * (b1 - 1 - 0.5D))
							world.setTypeId(l, k, i1, Block.BEDROCK.id);
						else {
							world.setTypeId(l, k, i1, Block.ENDER_PORTAL.id);
						}
					}
				}
			}
		}

		world.setTypeId(i, b0 + 0, j, Block.BEDROCK.id);
		world.setTypeId(i, b0 + 1, j, Block.BEDROCK.id);
		world.setTypeId(i, b0 + 2, j, Block.BEDROCK.id);
		world.setTypeId(i - 1, b0 + 2, j, Block.TORCH.id);
		world.setTypeId(i + 1, b0 + 2, j, Block.TORCH.id);
		world.setTypeId(i, b0 + 2, j - 1, Block.TORCH.id);
		world.setTypeId(i, b0 + 2, j + 1, Block.TORCH.id);
		world.setTypeId(i, b0 + 3, j, Block.BEDROCK.id);
		world.setTypeId(i, b0 + 4, j, Block.DRAGON_EGG.id);

		EntityCreatePortalEvent event = new EntityCreatePortalEvent((LivingEntity)getBukkitEntity(), Collections.unmodifiableList(world.getList()), PortalType.ENDER);
		this.world.getServer().getPluginManager().callEvent(event);

		if (!event.isCancelled()) {
			for (BlockState state : event.getBlocks())
				state.update(true);
		}
		else
			for (BlockState state : event.getBlocks()) {
				packet = new Packet53BlockChange(state.getX(), state.getY(), state.getZ(), this.world);
				for (it = this.world.players.iterator(); it.hasNext(); ) {
					EntityHuman entity = (EntityHuman)it.next();
					if ((entity instanceof EntityPlayer))
						((EntityPlayer)entity).netServerHandler.sendPacket(packet);
				}
			}
		Packet53BlockChange packet;
		Iterator it;
		BlockEnderPortal.a = false;
	}
	protected void bb() {
	}

	public Entity[] al() {
		return this.children;
	}

	public boolean L() {
		return false;
	}

	public int getExpReward()
	{
		return 12000;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityEnderDragon
 * JD-Core Version:		0.6.0
 */