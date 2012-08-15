package net.minecraft.server;

public class ItemLog extends ItemBlock
{
	private Block a;

	public ItemLog(int paramInt, Block paramBlock)
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
		if ((i < 0) || (i >= BlockLog.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockLog.a[i];
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemLog
 * JD-Core Version:		0.6.0
 */