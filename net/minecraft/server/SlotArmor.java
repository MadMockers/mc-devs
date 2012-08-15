package net.minecraft.server;

class SlotArmor extends Slot
{
	SlotArmor(ContainerPlayer paramContainerPlayer, IInventory paramIInventory, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		super(paramIInventory, paramInt1, paramInt2, paramInt3);
	}
	public int a() {
		return 1;
	}

	public boolean isAllowed(ItemStack paramItemStack)
	{
		if ((paramItemStack.getItem() instanceof ItemArmor)) {
			return ((ItemArmor)paramItemStack.getItem()).a == this.a;
		}
		if (paramItemStack.getItem().id == Block.PUMPKIN.id) {
			return this.a == 0;
		}
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.SlotArmor
 * JD-Core Version:		0.6.0
 */