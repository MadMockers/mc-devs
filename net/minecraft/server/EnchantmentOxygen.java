package net.minecraft.server;

public class EnchantmentOxygen extends Enchantment
{
	public EnchantmentOxygen(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.ARMOR_HEAD);
		b("oxygen");
	}

	public int a(int paramInt)
	{
		return 10 * paramInt;
	}

	public int b(int paramInt)
	{
		return a(paramInt) + 30;
	}

	public int getMaxLevel()
	{
		return 3;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentOxygen
 * JD-Core Version:		0.6.0
 */