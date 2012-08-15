package net.minecraft.server;

public class ItemCloth extends ItemBlock
{
	public ItemCloth(int paramInt)
	{
		super(paramInt);

		setMaxDurability(0);
		a(true);
	}

	public int filterData(int paramInt)
	{
		return paramInt;
	}

	public String c(ItemStack paramItemStack)
	{
		return super.getName() + "." + ItemDye.a[BlockCloth.e_(paramItemStack.getData())];
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemCloth
 * JD-Core Version:		0.6.0
 */