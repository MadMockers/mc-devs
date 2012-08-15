package net.minecraft.server;

final class EnchantmentModifierProtection
	implements EnchantmentModifier
{
	public int a;
	public DamageSource b;

	public void a(Enchantment paramEnchantment, int paramInt)
	{
		this.a += paramEnchantment.a(paramInt, this.b);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentModifierProtection
 * JD-Core Version:		0.6.0
 */