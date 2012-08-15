package net.minecraft.server;

public class ItemSmoothStone extends ItemBlock
{
	private Block a;

	public ItemSmoothStone(int paramInt, Block paramBlock)
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
		if ((i < 0) || (i >= BlockSmoothBrick.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockSmoothBrick.a[i];
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemSmoothStone
 * JD-Core Version:		0.6.0
 */