package net.minecraft.server;

public class ItemLeaves extends ItemBlock
{
	public ItemLeaves(int paramInt)
	{
		super(paramInt);

		setMaxDurability(0);
		a(true);
	}

	public int filterData(int paramInt)
	{
		return paramInt | 0x4;
	}

	public String c(ItemStack paramItemStack)
	{
		int i = paramItemStack.getData();
		if ((i < 0) || (i >= BlockLeaves.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockLeaves.a[i];
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemLeaves
 * JD-Core Version:		0.6.0
 */