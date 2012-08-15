package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.TrigMath;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.plugin.PluginManager;

public abstract class EntityCreature extends EntityLiving
{
	public PathEntity pathEntity;
	public Entity target;
	protected boolean b = false;
	protected int c = 0;

	public EntityCreature(World world) {
		super(world);
	}

	protected boolean i() {
		return false;
	}

	protected void be()
	{
		if (this.c > 0) {
			this.c -= 1;
		}

		this.b = i();
		float f = 16.0F;

		if (this.target == null)
		{
			Entity target = findTarget();
			if (target != null) {
				EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), target.getBukkitEntity(), EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
				this.world.getServer().getPluginManager().callEvent(event);

				if (!event.isCancelled()) {
					if (event.getTarget() == null)
						this.target = null;
					else {
						this.target = ((CraftEntity)event.getTarget()).getHandle();
					}
				}

			}

			if (this.target != null)
				this.pathEntity = this.world.findPath(this, this.target, f, true, false, false, true);
		}
		else if (this.target.isAlive()) {
			float f1 = this.target.d(this);

			if (l(this.target))
				a(this.target, f1);
		}
		else
		{
			EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, EntityTargetEvent.TargetReason.TARGET_DIED);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {
				if (event.getTarget() == null)
					this.target = null;
				else {
					this.target = ((CraftEntity)event.getTarget()).getHandle();
				}

			}

		}

		if ((!this.b) && (this.target != null) && ((this.pathEntity == null) || (this.random.nextInt(20) == 0)))
			this.pathEntity = this.world.findPath(this, this.target, f, true, false, false, true);
		else if ((!this.b) && (((this.pathEntity == null) && (this.random.nextInt(180) == 0)) || (((this.random.nextInt(120) == 0) || (this.c > 0)) && (this.bq < 100)))) {
			j();
		}

		int i = MathHelper.floor(this.boundingBox.b + 0.5D);
		boolean flag = H();
		boolean flag1 = J();

		this.pitch = 0.0F;
		if ((this.pathEntity != null) && (this.random.nextInt(100) != 0))
		{
			Vec3D vec3d = this.pathEntity.a(this);
			double d0 = this.width * 2.0F;

			while ((vec3d != null) && (vec3d.d(this.locX, vec3d.b, this.locZ) < d0 * d0)) {
				this.pathEntity.a();
				if (this.pathEntity.b()) {
					vec3d = null;
					this.pathEntity = null; continue;
				}
				vec3d = this.pathEntity.a(this);
			}

			this.bu = false;
			if (vec3d != null) {
				double d1 = vec3d.a - this.locX;
				double d2 = vec3d.c - this.locZ;
				double d3 = vec3d.b - i;

				float f2 = (float)(TrigMath.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F;
				float f3 = MathHelper.g(f2 - this.yaw);

				this.bs = this.bw;
				if (f3 > 30.0F) {
					f3 = 30.0F;
				}

				if (f3 < -30.0F) {
					f3 = -30.0F;
				}

				this.yaw += f3;
				if ((this.b) && (this.target != null)) {
					double d4 = this.target.locX - this.locX;
					double d5 = this.target.locZ - this.locZ;
					float f4 = this.yaw;

					this.yaw = ((float)(Math.atan2(d5, d4) * 180.0D / 3.141592741012573D) - 90.0F);
					f3 = (f4 - this.yaw + 90.0F) * 3.141593F / 180.0F;
					this.br = (-MathHelper.sin(f3) * this.bs * 1.0F);
					this.bs = (MathHelper.cos(f3) * this.bs * 1.0F);
				}

				if (d3 > 0.0D) {
					this.bu = true;
				}
			}

			if (this.target != null) {
				a(this.target, 30.0F, 30.0F);
			}

			if ((this.positionChanged) && (!l())) {
				this.bu = true;
			}

			if ((this.random.nextFloat() < 0.8F) && ((flag) || (flag1))) {
				this.bu = true;
			}
		}
		else
		{
			super.be();
			this.pathEntity = null;
		}
	}

	protected void j()
	{
		boolean flag = false;
		int i = -1;
		int j = -1;
		int k = -1;
		float f = -99999.0F;

		for (int l = 0; l < 10; l++) {
			int i1 = MathHelper.floor(this.locX + this.random.nextInt(13) - 6.0D);
			int j1 = MathHelper.floor(this.locY + this.random.nextInt(7) - 3.0D);
			int k1 = MathHelper.floor(this.locZ + this.random.nextInt(13) - 6.0D);
			float f1 = a(i1, j1, k1);

			if (f1 > f) {
				f = f1;
				i = i1;
				j = j1;
				k = k1;
				flag = true;
			}
		}

		if (flag)
			this.pathEntity = this.world.a(this, i, j, k, 10.0F, true, false, false, true);
	}

	protected void a(Entity entity, float f)
	{
	}

	public float a(int i, int j, int k)
	{
		return 0.0F;
	}

	protected Entity findTarget() {
		return null;
	}

	public boolean canSpawn() {
		int i = MathHelper.floor(this.locX);
		int j = MathHelper.floor(this.boundingBox.b);
		int k = MathHelper.floor(this.locZ);

		return (super.canSpawn()) && (a(i, j, k) >= 0.0F);
	}

	public boolean l() {
		return this.pathEntity != null;
	}

	public void setPathEntity(PathEntity pathentity) {
		this.pathEntity = pathentity;
	}

	public Entity m() {
		return this.target;
	}

	public void setTarget(Entity entity) {
		this.target = entity;
	}

	protected float bs() {
		if (aV()) {
			return 1.0F;
		}
		float f = super.bs();

		if (this.c > 0) {
			f *= 2.0F;
		}

		return f;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityCreature
 * JD-Core Version:		0.6.0
 */