package net.minecraft.server;

public class EnchantmentLootBonus extends Enchantment
{
	protected EnchantmentLootBonus(int paramInt1, int paramInt2, EnchantmentSlotType paramEnchantmentSlotType)
	{
		super(paramInt1, paramInt2, paramEnchantmentSlotType);

		b("lootBonus");
		if (paramEnchantmentSlotType == EnchantmentSlotType.DIGGER)
			b("lootBonusDigger");
	}

	public int a(int paramInt)
	{
		return 15 + (paramInt - 1) * 9;
	}

	public int b(int paramInt)
	{
		return super.a(paramInt) + 50;
	}

	public int getMaxLevel()
	{
		return 3;
	}

	public boolean a(Enchantment paramEnchantment)
	{
		return (super.a(paramEnchantment)) && (paramEnchantment.id != SILK_TOUCH.id);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentLootBonus
 * JD-Core Version:		0.6.0
 */