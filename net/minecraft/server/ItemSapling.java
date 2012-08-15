package net.minecraft.server;

public class ItemSapling extends ItemBlock
{
	public ItemSapling(int paramInt)
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
		int i = paramItemStack.getData();
		if ((i < 0) || (i >= BlockSapling.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockSapling.a[i];
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemSapling
 * JD-Core Version:		0.6.0
 */