package net.minecraft.server;

public class ItemMilkBucket extends Item
{
	public ItemMilkBucket(int paramInt)
	{
		super(paramInt);

		d(1);
		a(CreativeModeTab.f);
	}

	public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		if (!paramEntityHuman.abilities.canInstantlyBuild) paramItemStack.count -= 1;

		if (!paramWorld.isStatic) {
			paramEntityHuman.bp();
		}

		if (paramItemStack.count <= 0) {
			return new ItemStack(Item.BUCKET);
		}
		return paramItemStack;
	}

	public int a(ItemStack paramItemStack)
	{
		return 32;
	}

	public EnumAnimation b(ItemStack paramItemStack)
	{
		return EnumAnimation.c;
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		paramEntityHuman.a(paramItemStack, a(paramItemStack));
		return paramItemStack;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemMilkBucket
 * JD-Core Version:		0.6.0
 */