package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

public class EntitySpider extends EntityMonster
{
	public EntitySpider(World world)
	{
		super(world);
		this.texture = "/mob/spider.png";
		a(1.4F, 0.9F);
		this.bw = 0.8F;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, new Byte(0));
	}

	public void h_() {
		super.h_();
		if (!this.world.isStatic)
			e(this.positionChanged);
	}

	public int getMaxHealth()
	{
		return 16;
	}

	public double X() {
		return this.length * 0.75D - 0.5D;
	}

	protected boolean e_() {
		return false;
	}

	protected Entity findTarget() {
		float f = c(1.0F);

		if (f < 0.5F) {
			double d0 = 16.0D;

			return this.world.findNearbyVulnerablePlayer(this, d0);
		}
		return null;
	}

	protected String aQ()
	{
		return "mob.spider";
	}

	protected String aR() {
		return "mob.spider";
	}

	protected String aS() {
		return "mob.spiderdeath";
	}

	protected void a(Entity entity, float f) {
		float f1 = c(1.0F);

		if ((f1 > 0.5F) && (this.random.nextInt(100) == 0))
		{
			EntityTargetEvent event = new EntityTargetEvent(getBukkitEntity(), null, EntityTargetEvent.TargetReason.FORGOT_TARGET);
			this.world.getServer().getPluginManager().callEvent(event);

			if (!event.isCancelled()) {
				if (event.getTarget() == null)
					this.target = null;
				else {
					this.target = ((CraftEntity)event.getTarget()).getHandle();
				}
				return;
			}

		}
		else if ((f > 2.0F) && (f < 6.0F) && (this.random.nextInt(10) == 0)) {
			if (this.onGround) {
				double d0 = entity.locX - this.locX;
				double d1 = entity.locZ - this.locZ;
				float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1);

				this.motX = (d0 / f2 * 0.5D * 0.800000011920929D + this.motX * 0.2000000029802322D);
				this.motZ = (d1 / f2 * 0.5D * 0.800000011920929D + this.motZ * 0.2000000029802322D);
				this.motY = 0.4000000059604645D;
			}
		} else {
			super.a(entity, f);
		}
	}

	protected int getLootId()
	{
		return Item.STRING.id;
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();

		int k = this.random.nextInt(3);

		if (i > 0) {
			k += this.random.nextInt(i + 1);
		}

		if (k > 0) {
			loot.add(new ItemStack(Item.STRING.id, k));
		}

		if ((flag) && ((this.random.nextInt(3) == 0) || (this.random.nextInt(1 + i) > 0))) {
			loot.add(new ItemStack(Item.SPIDER_EYE.id, 1));
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}

	public boolean f_()
	{
		return p();
	}
	public void aj() {
	}

	public EnumMonsterType getMonsterType() {
		return EnumMonsterType.ARTHROPOD;
	}

	public boolean e(MobEffect mobeffect) {
		return mobeffect.getEffectId() == MobEffectList.POISON.id ? false : super.e(mobeffect);
	}

	public boolean p() {
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
}

/* 
 * Qualified Name:		 net.minecraft.server.EntitySpider
 * JD-Core Version:		0.6.0
 */