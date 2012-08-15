package net.minecraft.server;

public class ItemWithAuxData extends ItemBlock
{
	private final Block a;
	private String[] b;

	public ItemWithAuxData(int paramInt, boolean paramBoolean)
	{
		super(paramInt);
		this.a = Block.byId[f()];

		if (paramBoolean) {
			setMaxDurability(0);
			a(true);
		}
	}

	public int filterData(int paramInt)
	{
		return paramInt;
	}

	public ItemWithAuxData a(String[] paramArrayOfString) {
		this.b = paramArrayOfString;
		return this;
	}

	public String c(ItemStack paramItemStack)
	{
		if (this.b == null) {
			return super.c(paramItemStack);
		}
		int i = paramItemStack.getData();
		if ((i >= 0) && (i < this.b.length)) {
			return super.c(paramItemStack) + "." + this.b[i];
		}
		return super.c(paramItemStack);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemWithAuxData
 * JD-Core Version:		0.6.0
 */