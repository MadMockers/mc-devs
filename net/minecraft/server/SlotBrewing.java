package net.minecraft.server;

class SlotBrewing extends Slot
{
	public SlotBrewing(ContainerBrewingStand paramContainerBrewingStand, IInventory paramIInventory, int paramInt1, int paramInt2, int paramInt3)
	{
		super(paramIInventory, paramInt1, paramInt2, paramInt3);
	}

	public boolean isAllowed(ItemStack paramItemStack)
	{
		if (paramItemStack != null)
		{
			return Item.byId[paramItemStack.id].u();
		}

		return false;
	}

	public int a()
	{
		return 64;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.SlotBrewing
 * JD-Core Version:		0.6.0
 */