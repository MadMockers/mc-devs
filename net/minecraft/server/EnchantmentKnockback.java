package net.minecraft.server;

public class EnchantmentKnockback extends Enchantment
{
	protected EnchantmentKnockback(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.WEAPON);

		b("knockback");
	}

	public int a(int paramInt)
	{
		return 5 + 20 * (paramInt - 1);
	}

	public int b(int paramInt)
	{
		return super.a(paramInt) + 50;
	}

	public int getMaxLevel()
	{
		return 2;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentKnockback
 * JD-Core Version:		0.6.0
 */