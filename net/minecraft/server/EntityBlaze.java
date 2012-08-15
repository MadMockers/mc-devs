package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.inventory.ItemStack;

public class EntityBlaze extends EntityMonster
{
	private float d = 0.5F;
	private int e;
	private int g;

	public EntityBlaze(World world)
	{
		super(world);
		this.texture = "/mob/fire.png";
		this.fireProof = true;
		this.damage = 6;
		this.aV = 10;
	}

	public int getMaxHealth() {
		return 20;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, new Byte(0));
	}

	protected String aQ() {
		return "mob.blaze.breathe";
	}

	protected String aR() {
		return "mob.blaze.hit";
	}

	protected String aS() {
		return "mob.blaze.death";
	}

	public float c(float f) {
		return 1.0F;
	}

	public void d() {
		if (!this.world.isStatic) {
			if (G()) {
				damageEntity(DamageSource.DROWN, 1);
			}

			this.e -= 1;
			if (this.e <= 0) {
				this.e = 100;
				this.d = (0.5F + (float)this.random.nextGaussian() * 3.0F);
			}

			if ((m() != null) && (m().locY + m().getHeadHeight() > this.locY + getHeadHeight() + this.d)) {
				this.motY += (0.300000011920929D - this.motY) * 0.300000011920929D;
			}
		}

		if (this.random.nextInt(24) == 0) {
			this.world.makeSound(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, "fire.fire", 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F);
		}

		if ((!this.onGround) && (this.motY < 0.0D)) {
			this.motY *= 0.6D;
		}

		for (int i = 0; i < 2; i++) {
			this.world.a("largesmoke", this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
		}

		super.d();
	}

	protected void a(Entity entity, float f) {
		if ((this.attackTicks <= 0) && (f < 2.0F) && (entity.boundingBox.e > this.boundingBox.b) && (entity.boundingBox.b < this.boundingBox.e)) {
			this.attackTicks = 20;
			k(entity);
		} else if (f < 30.0F) {
			double d0 = entity.locX - this.locX;
			double d1 = entity.boundingBox.b + entity.length / 2.0F - (this.locY + this.length / 2.0F);
			double d2 = entity.locZ - this.locZ;

			if (this.attackTicks == 0) {
				this.g += 1;
				if (this.g == 1) {
					this.attackTicks = 60;
					e(true);
				} else if (this.g <= 4) {
					this.attackTicks = 6;
				} else {
					this.attackTicks = 100;
					this.g = 0;
					e(false);
				}

				if (this.g > 1) {
					float f1 = MathHelper.c(f) * 0.5F;

					this.world.a((EntityHuman)null, 1009, (int)this.locX, (int)this.locY, (int)this.locZ, 0);

					for (int i = 0; i < 1; i++) {
						EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.world, this, d0 + this.random.nextGaussian() * f1, d1, d2 + this.random.nextGaussian() * f1);

						entitysmallfireball.locY = (this.locY + this.length / 2.0F + 0.5D);
						this.world.addEntity(entitysmallfireball);
					}
				}
			}

			this.yaw = ((float)(Math.atan2(d2, d0) * 180.0D / 3.141592741012573D) - 90.0F);
			this.b = true;
		}
	}

	protected void a(float f) {
	}

	protected int getLootId() {
		return Item.BLAZE_ROD.id;
	}

	public boolean isBurning() {
		return n();
	}

	protected void dropDeathLoot(boolean flag, int i) {
		if (flag)
		{
			List loot = new ArrayList();
			int j = this.random.nextInt(2 + i);

			if (j > 0) {
				loot.add(new ItemStack(Item.BLAZE_ROD.id, j));
			}

			CraftEventFactory.callEntityDeathEvent(this, loot);
		}
	}

	public boolean n()
	{
		return (this.datawatcher.getByte(16) & 0x1) != 0;
	}

	public void e(boolean flag) {
		byte b0 = this.datawatcher.getByte(16);

		if (flag)
			b0 = (byte)(b0 | 0x1);
		else {
			b0 = (byte)(b0 & 0xFFFFFFFE);
		}

		this.datawatcher.watch(16, Byte.valueOf(b0));
	}

	protected boolean o() {
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityBlaze
 * JD-Core Version:		0.6.0
 */