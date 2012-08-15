package net.minecraft.server;

public class ItemWrittenBook extends Item
{
	public ItemWrittenBook(int paramInt)
	{
		super(paramInt);
		d(1);
	}

	public static boolean a(NBTTagCompound paramNBTTagCompound)
	{
		if (!ItemBookAndQuill.a(paramNBTTagCompound)) {
			return false;
		}

		if (!paramNBTTagCompound.hasKey("title")) {
			return false;
		}
		String str = paramNBTTagCompound.getString("title");
		if ((str == null) || (str.length() > 16)) {
			return false;
		}

		return paramNBTTagCompound.hasKey("author");
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		paramEntityHuman.c(paramItemStack);
		return paramItemStack;
	}

	public boolean p()
	{
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemWrittenBook
 * JD-Core Version:		0.6.0
 */