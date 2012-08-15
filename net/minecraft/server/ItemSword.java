package net.minecraft.server;

public class ItemSword extends Item
{
	private int damage;
	private final EnumToolMaterial b;

	public ItemSword(int paramInt, EnumToolMaterial paramEnumToolMaterial)
	{
		super(paramInt);
		this.b = paramEnumToolMaterial;
		this.maxStackSize = 1;
		setMaxDurability(paramEnumToolMaterial.a());
		a(CreativeModeTab.j);

		this.damage = (4 + paramEnumToolMaterial.c());
	}

	public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock)
	{
		if (paramBlock.id == Block.WEB.id)
		{
			return 15.0F;
		}
		return 1.5F;
	}

	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
	{
		paramItemStack.damage(1, paramEntityLiving2);
		return true;
	}

	public boolean a(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityLiving paramEntityLiving)
	{
		if (Block.byId[paramInt1].m(paramWorld, paramInt2, paramInt3, paramInt4) != 0.0D) paramItemStack.damage(2, paramEntityLiving);
		return true;
	}

	public int a(Entity paramEntity)
	{
		return this.damage;
	}

	public EnumAnimation b(ItemStack paramItemStack)
	{
		return EnumAnimation.d;
	}

	public int a(ItemStack paramItemStack)
	{
		return 72000;
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		paramEntityHuman.a(paramItemStack, a(paramItemStack));
		return paramItemStack;
	}

	public boolean canDestroySpecialBlock(Block paramBlock)
	{
		return paramBlock.id == Block.WEB.id;
	}

	public int b()
	{
		return this.b.e();
	}

	public String f() {
		return this.b.toString();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemSword
 * JD-Core Version:		0.6.0
 */