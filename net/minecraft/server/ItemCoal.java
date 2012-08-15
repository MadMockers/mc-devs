package net.minecraft.server;

public class ItemCoal extends Item
{
	public ItemCoal(int paramInt)
	{
		super(paramInt);

		a(true);
		setMaxDurability(0);
		a(CreativeModeTab.l);
	}

	public String c(ItemStack paramItemStack)
	{
		if (paramItemStack.getData() == 1) {
			return "item.charcoal";
		}
		return "item.coal";
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemCoal
 * JD-Core Version:		0.6.0
 */