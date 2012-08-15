package net.minecraft.server;

public class ItemBookAndQuill extends Item
{
	public ItemBookAndQuill(int paramInt)
	{
		super(paramInt);
		d(1);
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

	public static boolean a(NBTTagCompound paramNBTTagCompound)
	{
		if (paramNBTTagCompound == null) {
			return false;
		}
		if (!paramNBTTagCompound.hasKey("pages")) {
			return false;
		}

		NBTTagList localNBTTagList = (NBTTagList)paramNBTTagCompound.get("pages");
		for (int i = 0; i < localNBTTagList.size(); i++) {
			NBTTagString localNBTTagString = (NBTTagString)localNBTTagList.get(i);

			if (localNBTTagString.data == null) {
				return false;
			}
			if (localNBTTagString.data.length() > 256) {
				return false;
			}
		}

		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemBookAndQuill
 * JD-Core Version:		0.6.0
 */