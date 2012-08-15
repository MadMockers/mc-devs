package net.minecraft.server;

public class EnchantmentWaterWorker extends Enchantment
{
	public EnchantmentWaterWorker(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.ARMOR_HEAD);
		b("waterWorker");
	}

	public int a(int paramInt)
	{
		return 1;
	}

	public int b(int paramInt)
	{
		return a(paramInt) + 40;
	}

	public int getMaxLevel()
	{
		return 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentWaterWorker
 * JD-Core Version:		0.6.0
 */