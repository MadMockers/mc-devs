package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.plugin.PluginManager;

public class EntitySheep extends EntityAnimal
{
	public static final float[][] d = { { 1.0F, 1.0F, 1.0F }, { 0.95F, 0.7F, 0.2F }, { 0.9F, 0.5F, 0.85F }, { 0.6F, 0.7F, 0.95F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.7F, 0.8F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.6F, 0.7F }, { 0.7F, 0.4F, 0.9F }, { 0.2F, 0.4F, 0.8F }, { 0.5F, 0.4F, 0.3F }, { 0.4F, 0.5F, 0.2F }, { 0.8F, 0.3F, 0.3F }, { 0.1F, 0.1F, 0.1F } };
	private int e;
	private PathfinderGoalEatTile f = new PathfinderGoalEatTile(this);

	public EntitySheep(World world) {
		super(world);
		this.texture = "/mob/sheep.png";
		a(0.9F, 1.3F);
		float f = 0.23F;

		getNavigation().a(true);
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalPanic(this, 0.38F));
		this.goalSelector.a(2, new PathfinderGoalBreed(this, f));
		this.goalSelector.a(3, new PathfinderGoalTempt(this, 0.25F, Item.WHEAT.id, false));
		this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 0.25F));
		this.goalSelector.a(5, this.f);
		this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, f));
		this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	}

	protected boolean aV() {
		return true;
	}

	protected void bc() {
		this.e = this.f.f();
		super.bc();
	}

	public void d() {
		if (this.world.isStatic) {
			this.e = Math.max(0, this.e - 1);
		}

		super.d();
	}

	public int getMaxHealth() {
		return 8;
	}

	protected void a() {
		super.a();
		this.datawatcher.a(16, new Byte(0));
	}

	protected void dropDeathLoot(boolean flag, int i)
	{
		List loot = new ArrayList();

		if (!isSheared()) {
			loot.add(new org.bukkit.inventory.ItemStack(Material.WOOL, 1, 0, Byte.valueOf((byte)getColor())));
		}

		CraftEventFactory.callEntityDeathEvent(this, loot);
	}

	protected int getLootId()
	{
		return Block.WOOL.id;
	}

	public boolean c(EntityHuman entityhuman) {
		ItemStack itemstack = entityhuman.inventory.getItemInHand();

		if ((itemstack != null) && (itemstack.id == Item.SHEARS.id) && (!isSheared()) && (!isBaby())) {
			if (!this.world.isStatic)
			{
				PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)entityhuman.getBukkitEntity(), getBukkitEntity());
				this.world.getServer().getPluginManager().callEvent(event);

				if (event.isCancelled()) {
					return false;
				}

				setSheared(true);
				int i = 1 + this.random.nextInt(3);

				for (int j = 0; j < i; j++) {
					EntityItem entityitem = a(new ItemStack(Block.WOOL.id, 1, getColor()), 1.0F);

					entityitem.motY += this.random.nextFloat() * 0.05F;
					entityitem.motX += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
					entityitem.motZ += (this.random.nextFloat() - this.random.nextFloat()) * 0.1F;
				}
			}

			itemstack.damage(1, entityhuman);
		}

		return super.c(entityhuman);
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.setBoolean("Sheared", isSheared());
		nbttagcompound.setByte("Color", (byte)getColor());
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		setSheared(nbttagcompound.getBoolean("Sheared"));
		setColor(nbttagcompound.getByte("Color"));
	}

	protected String aQ() {
		return "mob.sheep";
	}

	protected String aR() {
		return "mob.sheep";
	}

	protected String aS() {
		return "mob.sheep";
	}

	public int getColor() {
		return this.datawatcher.getByte(16) & 0xF;
	}

	public void setColor(int i) {
		byte b0 = this.datawatcher.getByte(16);

		this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xF0 | i & 0xF)));
	}

	public boolean isSheared() {
		return (this.datawatcher.getByte(16) & 0x10) != 0;
	}

	public void setSheared(boolean flag) {
		byte b0 = this.datawatcher.getByte(16);

		if (flag)
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 | 0x10)));
		else
			this.datawatcher.watch(16, Byte.valueOf((byte)(b0 & 0xFFFFFFEF)));
	}

	public static int a(Random random)
	{
		int i = random.nextInt(100);

		return random.nextInt(500) == 0 ? 6 : i < 18 ? 12 : i < 15 ? 8 : i < 10 ? 7 : i < 5 ? 15 : 0;
	}

	public EntityAnimal createChild(EntityAnimal entityanimal) {
		EntitySheep entitysheep = (EntitySheep)entityanimal;
		EntitySheep entitysheep1 = new EntitySheep(this.world);

		if (this.random.nextBoolean())
			entitysheep1.setColor(getColor());
		else {
			entitysheep1.setColor(entitysheep.getColor());
		}

		return entitysheep1;
	}

	public void aA()
	{
		SheepRegrowWoolEvent event = new SheepRegrowWoolEvent((Sheep)getBukkitEntity());
		this.world.getServer().getPluginManager().callEvent(event);

		if (!event.isCancelled()) {
			setSheared(false);
		}

		if (isBaby()) {
			int i = getAge() + 1200;

			if (i > 0) {
				i = 0;
			}

			setAge(i);
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntitySheep
 * JD-Core Version:		0.6.0
 */