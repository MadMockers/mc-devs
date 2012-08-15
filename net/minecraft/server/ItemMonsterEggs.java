package net.minecraft.server;

public class ItemMonsterEggs extends ItemBlock
{
	public ItemMonsterEggs(int paramInt)
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
		if ((i < 0) || (i >= BlockMonsterEggs.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockMonsterEggs.a[i];
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemMonsterEggs
 * JD-Core Version:		0.6.0
 */