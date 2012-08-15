package net.minecraft.server;

public class ItemWood extends ItemBlock
{
	private Block a;

	public ItemWood(int paramInt, Block paramBlock)
	{
		super(paramInt);

		this.a = paramBlock;

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
		if ((i < 0) || (i >= BlockWood.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockWood.a[i];
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemWood
 * JD-Core Version:		0.6.0
 */