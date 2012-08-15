package net.minecraft.server;

public class ItemArmor extends Item
{
	private static final int[] bY = { 11, 16, 15, 13 };
	public final int a;
	public final int b;
	public final int c;
	private final EnumArmorMaterial bZ;

	public ItemArmor(int paramInt1, EnumArmorMaterial paramEnumArmorMaterial, int paramInt2, int paramInt3)
	{
		super(paramInt1);
		this.bZ = paramEnumArmorMaterial;
		this.a = paramInt3;
		this.c = paramInt2;
		this.b = paramEnumArmorMaterial.b(paramInt3);
		setMaxDurability(paramEnumArmorMaterial.a(paramInt3));
		this.maxStackSize = 1;
		a(CreativeModeTab.j);
	}

	public int b()
	{
		return this.bZ.a();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemArmor
 * JD-Core Version:		0.6.0
 */