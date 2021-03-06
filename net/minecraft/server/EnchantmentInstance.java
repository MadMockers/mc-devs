package net.minecraft.server;

public class EnchantmentInstance extends WeightedRandomChoice
{
	public final Enchantment enchantment;
	public final int level;

	public EnchantmentInstance(Enchantment paramEnchantment, int paramInt)
	{
		super(paramEnchantment.getRandomWeight());
		this.enchantment = paramEnchantment;
		this.level = paramInt;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentInstance
 * JD-Core Version:		0.6.0
 */