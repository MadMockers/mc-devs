package net.minecraft.server;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Explosive;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.PluginManager;

public class EntityTNTPrimed extends Entity
{
	public int fuseTicks;
	public float yield = 4.0F;
	public boolean isIncendiary = false;

	public EntityTNTPrimed(World world) {
		super(world);
		this.fuseTicks = 0;
		this.m = true;
		a(0.98F, 0.98F);
		this.height = (this.length / 2.0F);
	}

	public EntityTNTPrimed(World world, double d0, double d1, double d2) {
		this(world);
		setPosition(d0, d1, d2);
		float f = (float)(Math.random() * 3.141592741012573D * 2.0D);

		this.motX = (-(float)Math.sin(f) * 0.02F);
		this.motY = 0.2000000029802322D;
		this.motZ = (-(float)Math.cos(f) * 0.02F);
		this.fuseTicks = 80;
		this.lastX = d0;
		this.lastY = d1;
		this.lastZ = d2;
	}
	protected void a() {
	}

	protected boolean e_() {
		return false;
	}

	public boolean L() {
		return !this.dead;
	}

	public void h_() {
		this.lastX = this.locX;
		this.lastY = this.locY;
		this.lastZ = this.locZ;
		this.motY -= 0.03999999910593033D;
		move(this.motX, this.motY, this.motZ);
		this.motX *= 0.9800000190734863D;
		this.motY *= 0.9800000190734863D;
		this.motZ *= 0.9800000190734863D;
		if (this.onGround) {
			this.motX *= 0.699999988079071D;
			this.motZ *= 0.699999988079071D;
			this.motY *= -0.5D;
		}

		if (this.fuseTicks-- <= 0)
		{
			if (!this.world.isStatic) {
				explode();
			}
			die();
		}
		else {
			this.world.a("smoke", this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode()
	{
		CraftServer server = this.world.getServer();

		ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)CraftEntity.getEntity(server, this));
		server.getPluginManager().callEvent(event);

		if (!event.isCancelled())
		{
			this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire());
		}
	}

	protected void b(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setByte("Fuse", (byte)this.fuseTicks);
	}

	protected void a(NBTTagCompound nbttagcompound) {
		this.fuseTicks = nbttagcompound.getByte("Fuse");
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityTNTPrimed
 * JD-Core Version:		0.6.0
 */