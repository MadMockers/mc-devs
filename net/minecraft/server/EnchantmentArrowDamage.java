package net.minecraft.server;

public class EnchantmentArrowDamage extends Enchantment
{
	public EnchantmentArrowDamage(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.BOW);
		b("arrowDamage");
	}

	public int a(int paramInt)
	{
		return 1 + (paramInt - 1) * 10;
	}

	public int b(int paramInt)
	{
		return a(paramInt) + 15;
	}

	public int getMaxLevel()
	{
		return 5;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentArrowDamage
 * JD-Core Version:		0.6.0
 */