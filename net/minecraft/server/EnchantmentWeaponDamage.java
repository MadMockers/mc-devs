package net.minecraft.server;

public class EnchantmentWeaponDamage extends Enchantment
{
	private static final String[] A = { "all", "undead", "arthropods" };

	private static final int[] B = { 1, 5, 5 };

	private static final int[] C = { 11, 8, 8 };

	private static final int[] D = { 20, 20, 20 };
	public final int a;

	public EnchantmentWeaponDamage(int paramInt1, int paramInt2, int paramInt3)
	{
		super(paramInt1, paramInt2, EnchantmentSlotType.WEAPON);
		this.a = paramInt3;
	}

	public int a(int paramInt)
	{
		return B[this.a] + (paramInt - 1) * C[this.a];
	}

	public int b(int paramInt)
	{
		return a(paramInt) + D[this.a];
	}

	public int getMaxLevel()
	{
		return 5;
	}

	public int a(int paramInt, EntityLiving paramEntityLiving)
	{
		if (this.a == 0) {
			return paramInt * 3;
		}
		if ((this.a == 1) && (paramEntityLiving.getMonsterType() == EnumMonsterType.UNDEAD)) {
			return paramInt * 4;
		}
		if ((this.a == 2) && (paramEntityLiving.getMonsterType() == EnumMonsterType.ARTHROPOD)) {
			return paramInt * 4;
		}
		return 0;
	}

	public boolean a(Enchantment paramEnchantment)
	{
		return !(paramEnchantment instanceof EnchantmentWeaponDamage);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentWeaponDamage
 * JD-Core Version:		0.6.0
 */