package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.PluginManager;

public class EntityItem extends Entity
{
	public ItemStack itemStack;
	public int age = 0;
	public int pickupDelay;
	private int e = 5;
	public float d = (float)(Math.random() * 3.141592653589793D * 2.0D);
	private int lastTick = (int)(System.currentTimeMillis() / 50L);

	public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
		super(world);
		a(0.25F, 0.25F);
		this.height = (this.length / 2.0F);
		setPosition(d0, d1, d2);
		this.itemStack = itemstack;

		if (this.itemStack == null) {
			throw new IllegalArgumentException("Can't create an EntityItem for a null item");
		}
		if (this.itemStack.count <= -1) {
			this.itemStack.count = 1;
		}

		this.yaw = (float)(Math.random() * 360.0D);
		this.motX = (float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D);
		this.motY = 0.2000000029802322D;
		this.motZ = (float)(Math.random() * 0.2000000029802322D - 0.1000000014901161D);
	}

	protected boolean e_() {
		return false;
	}

	public EntityItem(World world) {
		super(world);
		a(0.25F, 0.25F);
		this.height = (this.length / 2.0F);
	}
	protected void a() {
	}

	public void h_() {
		super.h_();

		int currentTick = (int)(System.currentTimeMillis() / 50L);
		this.pickupDelay -= currentTick - this.lastTick;
		this.lastTick = currentTick;

		this.lastX = this.locX;
		this.lastY = this.locY;
		this.lastZ = this.locZ;
		this.motY -= 0.03999999910593033D;
		i(this.locX, (this.boundingBox.b + this.boundingBox.e) / 2.0D, this.locZ);
		move(this.motX, this.motY, this.motZ);
		boolean flag = ((int)this.lastX != (int)this.locX) || ((int)this.lastY != (int)this.locY) || ((int)this.lastZ != (int)this.locZ);

		if (flag) {
			if (this.world.getMaterial(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)) == Material.LAVA) {
				this.motY = 0.2000000029802322D;
				this.motX = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
				this.motZ = ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
				this.world.makeSound(this, "random.fizz", 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
			}

			if (!this.world.isStatic) {
				Iterator iterator = this.world.a(EntityItem.class, this.boundingBox.grow(0.5D, 0.0D, 0.5D)).iterator();

				while (iterator.hasNext()) {
					EntityItem entityitem = (EntityItem)iterator.next();

					a(entityitem);
				}
			}
		}

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
			this.motY *= -0.5D;
		}

		this.age += 1;
		if (this.age >= 6000)
		{
			if (CraftEventFactory.callItemDespawnEvent(this).isCancelled()) {
				this.age = 0;
				return;
			}

			die();
		}
	}

	public boolean a(EntityItem entityitem) {
		if (entityitem == this)
			return false;
		if ((entityitem.isAlive()) && (isAlive())) {
			if (entityitem.itemStack.getItem() != this.itemStack.getItem())
				return false;
			if ((entityitem.itemStack.getItem().k()) && (entityitem.itemStack.getData() != this.itemStack.getData()))
				return false;
			if (entityitem.itemStack.count < this.itemStack.count)
				return entityitem.a(this);
			if (entityitem.itemStack.count + this.itemStack.count > entityitem.itemStack.getMaxStackSize()) {
				return false;
			}
			if ((entityitem.itemStack.hasEnchantments()) || (this.itemStack.hasEnchantments())) {
				return false;
			}

			entityitem.itemStack.count += this.itemStack.count;
			entityitem.pickupDelay = Math.max(entityitem.pickupDelay, this.pickupDelay);
			entityitem.age = Math.min(entityitem.age, this.age);
			die();
			return true;
		}

		return false;
	}

	public void d()
	{
		this.age = 4800;
	}

	public boolean I() {
		return this.world.a(this.boundingBox, Material.WATER, this);
	}

	protected void burn(int i) {
		damageEntity(DamageSource.FIRE, i);
	}

	public boolean damageEntity(DamageSource damagesource, int i) {
		K();
		this.e -= i;
		if (this.e <= 0) {
			die();
		}

		return false;
	}

	public void b(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("Health", (short)(byte)this.e);
		nbttagcompound.setShort("Age", (short)this.age);
		if (this.itemStack != null)
			nbttagcompound.setCompound("Item", this.itemStack.save(new NBTTagCompound()));
	}

	public void a(NBTTagCompound nbttagcompound)
	{
		this.e = (nbttagcompound.getShort("Health") & 0xFF);
		this.age = nbttagcompound.getShort("Age");
		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");

		this.itemStack = ItemStack.a(nbttagcompound1);
		if (this.itemStack == null)
			die();
	}

	public void b_(EntityHuman entityhuman)
	{
		if ((!this.world.isStatic) && (this.itemStack != null)) {
			int i = this.itemStack.count;

			int canHold = entityhuman.inventory.canHold(this.itemStack);
			int remaining = this.itemStack.count - canHold;

			if ((this.pickupDelay <= 0) && (canHold > 0)) {
				this.itemStack.count = canHold;
				PlayerPickupItemEvent event = new PlayerPickupItemEvent((Player)entityhuman.getBukkitEntity(), (org.bukkit.entity.Item)getBukkitEntity(), remaining);
				this.world.getServer().getPluginManager().callEvent(event);
				this.itemStack.count = (canHold + remaining);

				if (event.isCancelled()) {
					return;
				}

				this.pickupDelay = 0;
			}

			if ((this.pickupDelay == 0) && (entityhuman.inventory.pickup(this.itemStack))) {
				if (this.itemStack.id == Block.LOG.id) {
					entityhuman.a(AchievementList.g);
				}

				if (this.itemStack.id == Item.LEATHER.id) {
					entityhuman.a(AchievementList.t);
				}

				if (this.itemStack.id == Item.DIAMOND.id) {
					entityhuman.a(AchievementList.w);
				}

				if (this.itemStack.id == Item.BLAZE_ROD.id) {
					entityhuman.a(AchievementList.z);
				}

				this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				entityhuman.receive(this, i);
				if (this.itemStack.count <= 0)
					die();
			}
		}
	}

	public String getLocalizedName()
	{
		if (this.itemStack == null) return LocaleI18n.get("item.unknown");
		return LocaleI18n.get("item." + this.itemStack.a());
	}

	public boolean an() {
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityItem
 * JD-Core Version:		0.6.0
 */