package net.minecraft.server;

import java.util.Random;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityShootBowEvent;

public class ItemBow extends Item
{
	public ItemBow(int i)
	{
		super(i);
		this.maxStackSize = 1;
		setMaxDurability(384);
		a(CreativeModeTab.j);
	}

	public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {
		boolean flag = (entityhuman.abilities.canInstantlyBuild) || (EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_INFINITE.id, itemstack) > 0);

		if ((flag) || (entityhuman.inventory.e(Item.ARROW.id))) {
			int j = a(itemstack) - i;
			float f = j / 20.0F;

			f = (f * f + f * 2.0F) / 3.0F;
			if (f < 0.1D) {
				return;
			}

			if (f > 1.0F) {
				f = 1.0F;
			}

			EntityArrow entityarrow = new EntityArrow(world, entityhuman, f * 2.0F);

			if (f == 1.0F) {
				entityarrow.d(true);
			}

			int k = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_DAMAGE.id, itemstack);

			if (k > 0) {
				entityarrow.b(entityarrow.d() + k * 0.5D + 0.5D);
			}

			int l = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK.id, itemstack);

			if (l > 0) {
				entityarrow.a(l);
			}

			if (EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_FIRE.id, itemstack) > 0) {
				entityarrow.setOnFire(100);
			}

			EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(entityhuman, itemstack, entityarrow, f);
			if (event.isCancelled()) {
				event.getProjectile().remove();
				return;
			}

			if (event.getProjectile() == entityarrow.getBukkitEntity()) {
				world.addEntity(entityarrow);
			}

			itemstack.damage(1, entityhuman);
			world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (d.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (flag)
				entityarrow.fromPlayer = 2;
			else
				entityhuman.inventory.d(Item.ARROW.id);
		}
	}

	public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman)
	{
		return itemstack;
	}

	public int a(ItemStack itemstack) {
		return 72000;
	}

	public EnumAnimation b(ItemStack itemstack) {
		return EnumAnimation.e;
	}

	public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
		if ((entityhuman.abilities.canInstantlyBuild) || (entityhuman.inventory.e(Item.ARROW.id))) {
			entityhuman.a(itemstack, a(itemstack));
		}

		return itemstack;
	}

	public int b() {
		return 1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemBow
 * JD-Core Version:		0.6.0
 */