package net.minecraft.server;

public class ItemSandStone extends ItemBlock
{
	private Block a;

	public ItemSandStone(int paramInt, Block paramBlock)
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
		if ((i < 0) || (i >= BlockSandStone.a.length)) {
			i = 0;
		}
		return super.getName() + "." + BlockSandStone.a[i];
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemSandStone
 * JD-Core Version:		0.6.0
 */