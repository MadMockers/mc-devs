package net.minecraft.server;

public class EnchantmentSilkTouch extends Enchantment
{
	protected EnchantmentSilkTouch(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.DIGGER);

		b("untouching");
	}

	public int a(int paramInt)
	{
		return 15;
	}

	public int b(int paramInt)
	{
		return super.a(paramInt) + 50;
	}

	public int getMaxLevel()
	{
		return 1;
	}

	public boolean a(Enchantment paramEnchantment)
	{
		return (super.a(paramEnchantment)) && (paramEnchantment.id != LOOT_BONUS_BLOCKS.id);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentSilkTouch
 * JD-Core Version:		0.6.0
 */