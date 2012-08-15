package net.minecraft.server;

public enum EnchantmentSlotType
{
	public boolean canEnchant(Item paramItem)
	{
		if (this == ALL) return true;

		if ((paramItem instanceof ItemArmor)) {
			if (this == ARMOR) return true;
			ItemArmor localItemArmor = (ItemArmor)paramItem;
			if (localItemArmor.a == 0) return this == ARMOR_HEAD;
			if (localItemArmor.a == 2) return this == ARMOR_LEGS;
			if (localItemArmor.a == 1) return this == ARMOR_TORSO;
			if (localItemArmor.a == 3) return this == ARMOR_FEET;
			return false;
		}if ((paramItem instanceof ItemSword))
			return this == WEAPON;
		if ((paramItem instanceof ItemTool))
			return this == DIGGER;
		if ((paramItem instanceof ItemBow)) {
			return this == BOW;
		}
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentSlotType
 * JD-Core Version:		0.6.0
 */