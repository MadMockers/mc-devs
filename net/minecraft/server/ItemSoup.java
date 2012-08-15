package net.minecraft.server;

public class ItemSoup extends ItemFood
{
	public ItemSoup(int paramInt1, int paramInt2)
	{
		super(paramInt1, paramInt2, false);

		d(1);
	}

	public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		super.b(paramItemStack, paramWorld, paramEntityHuman);

		return new ItemStack(Item.BOWL);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemSoup
 * JD-Core Version:		0.6.0
 */