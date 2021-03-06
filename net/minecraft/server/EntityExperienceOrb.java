package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class EntityExperienceOrb extends Entity
{
	public int a;
	public int b = 0;
	public int c;
	private int d = 5;
	public int value;

	public EntityExperienceOrb(World world, double d0, double d1, double d2, int i)
	{
		super(world);
		a(0.5F, 0.5F);
		this.height = (this.length / 2.0F);
		setPosition(d0, d1, d2);
		this.yaw = (float)(Math.random() * 360.0D);
		this.motX = ((float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D) * 2.0F);
		this.motY = ((float)(Math.random() * 0.2D) * 2.0F);
		this.motZ = ((float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D) * 2.0F);
		this.value = i;
	}

	protected boolean e_() {
		return false;
	}

	public EntityExperienceOrb(World world) {
		super(world);
		a(0.25F, 0.25F);
		this.height = (this.length / 2.0F);
	}
	protected void a() {
	}

	public void h_() {
		super.h_();
		if (this.c > 0) {
			this.c -= 1;
		}

		this.lastX = this.locX;
		this.lastY = this.locY;
		this.lastZ = this.locZ;
		this.motY -= 0.02999999932944775D;
		if (this.world.getMaterial(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) == Material.LAVA) {
			this.motY = 0.2000000029802322D;
			this.motX = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
			this.motZ = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
			this.world.makeSound(this, "random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
		}

		i(this.locX, (this.boundingBox.b + this.boundingBox.e) / 2.0D, this.locZ);
		double d0 = 8.0D;
		EntityHuman entityhuman = this.world.findNearbyPlayer(this, d0);

		if (entityhuman != null)
		{
			EntityTargetEvent event = CraftEventFactory.callEntityTargetEvent(this, entityhuman, EntityTargetEvent.TargetReason.CLOSEST_PLAYER);
			Entity target = event.getTarget() == null ? null : ((CraftEntity)event.getTarget()).getHandle();

			if ((!event.isCancelled()) && (target != null)) {
				double d1 = (target.locX - this.locX) / d0;
				double d2 = (target.locY + target.getHeadHeight() - this.locY) / d0;
				double d3 = (target.locZ - this.locZ) / d0;
				double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
				double d5 = 1.0D - d4;
				if (d5 > 0.0D) {
					d5 *= d5;
					this.motX += d1 / d4 * d5 * 0.1D;
					this.motY += d2 / d4 * d5 * 0.1D;
					this.motZ += d3 / d4 * d5 * 0.1D;
				}
			}

		}

		move(this.motX, this.motY, this.motZ);
		float f = 0.98F;

		if (this.onGround) {
			f = 0.5880001F;
			int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));

			if (i > 0) {
				f = Block.byId[i].frictionFactor * 0.98F;
			}
		}

		this.motX *= f;
		this.motY *= 0.9800000190734863D;
		this.motZ *= f;
		if (this.onGround) {
			this.motY *= -0.8999999761581421D;
		}

		this.a += 1;
		this.b += 1;
		if (this.b >= 6000)
			die();
	}

	public boolean I()
	{
		return this.world.a(this.boundingBox, Material.WATER, this);
	}

	protected void burn(int i) {
		damageEntity(DamageSource.FIRE, i);
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		K();
		this.d -= i;
		if (this.d <= 0) {
			die();
		}

		return false;
	}

	public void b(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("Health", (short)(byte)this.d);
		nbttagcompound.setShort("Age", (short)this.b);
		nbttagcompound.setShort("Value", (short)this.value);
	}

	public void a(NBTTagCompound nbttagcompound) {
		this.d = (nbttagcompound.getShort("Health") & 0xFF);
		this.b = nbttagcompound.getShort("Age");
		this.value = nbttagcompound.getShort("Value");
	}

	public void b_(EntityHuman entityhuman) {
		if ((!this.world.isStatic) && 
			(this.c == 0) && (entityhuman.bL == 0)) {
			entityhuman.bL = 2;
			this.world.makeSound(this, "random.orb", 0.1F, 0.5F * ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.8F));
			entityhuman.receive(this, 1);
			entityhuman.giveExp(CraftEventFactory.callPlayerExpChangeEvent(entityhuman, this.value).getAmount());
			die();
		}
	}

	public int d()
	{
		return this.value;
	}

	public static int getOrbValue(int i)
	{
		if (i > 162670129) return i - 100000;
		if (i > 81335063) return 81335063;
		if (i > 40667527) return 40667527;
		if (i > 20333759) return 20333759;
		if (i > 10166857) return 10166857;
		if (i > 5083423) return 5083423;
		if (i > 2541701) return 2541701;
		if (i > 1270849) return 1270849;
		if (i > 635413) return 635413;
		if (i > 317701) return 317701;
		if (i > 158849) return 158849;
		if (i > 79423) return 79423;
		if (i > 39709) return 39709;
		if (i > 19853) return 19853;
		if (i > 9923) return 9923;
		if (i > 4957) return 4957;

		return i >= 3 ? 3 : i >= 7 ? 7 : i >= 17 ? 17 : i >= 37 ? 37 : i >= 73 ? 73 : i >= 149 ? 149 : i >= 307 ? 307 : i >= 617 ? 617 : i >= 1237 ? 1237 : i >= 2477 ? 2477 : 1;
	}

	public boolean an() {
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityExperienceOrb
 * JD-Core Version:		0.6.0
 */