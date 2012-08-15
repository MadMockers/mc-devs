package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.plugin.PluginManager;

public class EntitySlime extends EntityLiving
	implements IMonster
{
	public float a;
	public float b;
	public float c;
	private int jumpDelay = 0;

	public EntitySlime(World world) {
		super(world);
		this.texture = "/mob/slime.png";
		int i = 1 << this.random.nextInt(3);

		this.height = 0.0F;
		this.jumpDelay = (this.random.nextInt(20) + 10);
		setSize(i);
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, new Byte(1));
	}

	public void setSize(int i) {
		this.datawatcher.watch(16, new Byte((byte)i));
		a(0.6F * i, 0.6F * i);
		setPosition(this.locX, this.locY, this.locZ);
		setHealth(getMaxHealth());
		this.aV = i;
	}

	public int getMaxHealth() {
		int i = getSize();

		return i * i;
	}

	public int getSize() {
		return this.datawatcher.getByte(16);
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.setInt("Size", getSize() - 1);
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		setSize(nbttagcompound.getInt("Size") + 1);
	}

	protected String i() {
		return "slime";
	}

	protected String o() {
		return "mob.slime";
	}

	public void h_() {
		if ((!this.world.isStatic) && (this.world.difficulty == 0) && (getSize() > 0)) {
			this.dead = true;
		}

		this.b += (this.a - this.b) * 0.5F;
		this.c = this.b;
		boolean flag = this.onGround;

		super.h_();
		if ((this.onGround) && (!flag)) {
			int i = getSize();

			for (int j = 0; j < i * 8; j++) {
				float f = this.random.nextFloat() * 3.141593F * 2.0F;
				float f1 = this.random.nextFloat() * 0.5F + 0.5F;
				float f2 = MathHelper.sin(f) * i * 0.5F * f1;
				float f3 = MathHelper.cos(f) * i * 0.5F * f1;

				this.world.a(i(), this.locX + f2, this.boundingBox.b, this.locZ + f3, 0.0D, 0.0D, 0.0D);
			}

			if (p()) {
				this.world.makeSound(this, o(), aP(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			}

			this.a = -0.5F;
		} else if ((!this.onGround) && (flag)) {
			this.a = 1.0F;
		}

		l();
	}

	protected void be() {
		bb();
		EntityHuman entityhuman = this.world.findNearbyVulnerablePlayer(this, 16.0D);

		if (entityhuman != null) {
			a(entityhuman, 10.0F, 20.0F);
		}

		if ((this.onGround) && (this.jumpDelay-- <= 0)) {
			this.jumpDelay = k();
			if (entityhuman != null) {
				this.jumpDelay /= 3;
			}

			this.bu = true;
			if (r()) {
				this.world.makeSound(this, o(), aP(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
			}

			this.br = (1.0F - this.random.nextFloat() * 2.0F);
			this.bs = (1 * getSize());
		} else {
			this.bu = false;
			if (this.onGround)
				this.br = (this.bs = 0.0F);
		}
	}

	protected void l()
	{
		this.a *= 0.6F;
	}

	protected int k() {
		return this.random.nextInt(20) + 10;
	}

	protected EntitySlime j() {
		return new EntitySlime(this.world);
	}

	public void die() {
		int i = getSize();

		if ((!this.world.isStatic) && (i > 1) && (getHealth() <= 0)) {
			int j = 2 + this.random.nextInt(3);

			SlimeSplitEvent event = new SlimeSplitEvent((Slime)getBukkitEntity(), j);
			this.world.getServer().getPluginManager().callEvent(event);

			if ((!event.isCancelled()) && (event.getCount() > 0)) {
				j = event.getCount();
			} else {
				super.die();
				return;
			}

			for (int k = 0; k < j; k++) {
				float f = (k % 2 - 0.5F) * i / 4.0F;
				float f1 = (k / 2 - 0.5F) * i / 4.0F;
				EntitySlime entityslime = j();

				entityslime.setSize(i / 2);
				entityslime.setPositionRotation(this.locX + f, this.locY + 0.5D, this.locZ + f1, this.random.nextFloat() * 360.0F, 0.0F);
				this.world.addEntity(entityslime, CreatureSpawnEvent.SpawnReason.SLIME_SPLIT);
			}
		}

		super.die();
	}

	public void b_(EntityHuman entityhuman) {
		if (m()) {
			int i = getSize();

			if ((l(entityhuman)) && (e(entityhuman) < 0.6D * i * 0.6D * i) && (entityhuman.damageEntity(DamageSource.mobAttack(this), n())))
				this.world.makeSound(this, "mob.slimeattack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		}
	}

	protected boolean m()
	{
		return getSize() > 1;
	}

	protected int n() {
		return getSize();
	}

	protected String aR() {
		return "mob.slime";
	}

	protected String aS() {
		return "mob.slime";
	}

	protected int getLootId() {
		return getSize() == 1 ? Item.SLIME_BALL.id : 0;
	}

	public boolean canSpawn() {
		Chunk chunk = this.world.getChunkAtWorldCoords(MathHelper.floor(this.locX), MathHelper.floor(this.locZ));

		return (this.world.getWorldData().getType() != WorldType.FLAT) || (this.random.nextInt(4) == 1);
	}

	protected float aP() {
		return 0.4F * getSize();
	}

	public int bf() {
		return 0;
	}

	protected boolean r() {
		return getSize() > 1;
	}

	protected boolean p() {
		return getSize() > 2;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntitySlime
 * JD-Core Version:		0.6.0
 */