package net.minecraft.server;

public class EnchantmentFlameArrows extends Enchantment
{
	public EnchantmentFlameArrows(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.BOW);
		b("arrowFire");
	}

	public int a(int paramInt)
	{
		return 20;
	}

	public int b(int paramInt)
	{
		return 50;
	}

	public int getMaxLevel()
	{
		return 1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentFlameArrows
 * JD-Core Version:		0.6.0
 */