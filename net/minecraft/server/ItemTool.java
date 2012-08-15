package net.minecraft.server;

public class ItemTool extends Item
{
	private Block[] c;
	protected float a = 4.0F;
	private int bY;
	protected EnumToolMaterial b;

	protected ItemTool(int paramInt1, int paramInt2, EnumToolMaterial paramEnumToolMaterial, Block[] paramArrayOfBlock)
	{
		super(paramInt1);
		this.b = paramEnumToolMaterial;
		this.c = paramArrayOfBlock;
		this.maxStackSize = 1;
		setMaxDurability(paramEnumToolMaterial.a());
		this.a = paramEnumToolMaterial.b();
		this.bY = (paramInt2 + paramEnumToolMaterial.c());
		a(CreativeModeTab.i);
	}

	public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock)
	{
		for (Block localBlock : this.c) if (localBlock == paramBlock) return this.a;
		return 1.0F;
	}

	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
	{
		paramItemStack.damage(2, paramEntityLiving2);
		return true;
	}

	public boolean a(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityLiving paramEntityLiving)
	{
		if (Block.byId[paramInt1].m(paramWorld, paramInt2, paramInt3, paramInt4) != 0.0D) paramItemStack.damage(1, paramEntityLiving);
		return true;
	}

	public int a(Entity paramEntity)
	{
		return this.bY;
	}

	public int b()
	{
		return this.b.e();
	}

	public String e() {
		return this.b.toString();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemTool
 * JD-Core Version:		0.6.0
 */