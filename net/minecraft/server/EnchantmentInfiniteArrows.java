package net.minecraft.server;

public class EnchantmentInfiniteArrows extends Enchantment
{
	public EnchantmentInfiniteArrows(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.BOW);
		b("arrowInfinite");
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
 * Qualified Name:		 net.minecraft.server.EnchantmentInfiniteArrows
 * JD-Core Version:		0.6.0
 */