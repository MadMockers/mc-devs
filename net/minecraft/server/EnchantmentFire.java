package net.minecraft.server;

public class EnchantmentFire extends Enchantment
{
	protected EnchantmentFire(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.WEAPON);

		b("fire");
	}

	public int a(int paramInt)
	{
		return 10 + 20 * (paramInt - 1);
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

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentFire
 * JD-Core Version:		0.6.0
 */