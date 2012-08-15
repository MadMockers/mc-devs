package net.minecraft.server;

class SlotPotionBottle extends Slot
{
	private EntityHuman a;

	public SlotPotionBottle(EntityHuman paramEntityHuman, IInventory paramIInventory, int paramInt1, int paramInt2, int paramInt3)
	{
		super(paramIInventory, paramInt1, paramInt2, paramInt3);
		this.a = paramEntityHuman;
	}

	public boolean isAllowed(ItemStack paramItemStack)
	{
		return a_(paramItemStack);
	}

	public int a()
	{
		return 1;
	}

	public void b(ItemStack paramItemStack)
	{
		if ((paramItemStack.id == Item.POTION.id) && (paramItemStack.getData() > 0)) this.a.a(AchievementList.A, 1);
		super.b(paramItemStack);
	}

	public static boolean a_(ItemStack paramItemStack) {
		return (paramItemStack != null) && ((paramItemStack.id == Item.POTION.id) || (paramItemStack.id == Item.GLASS_BOTTLE.id));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.SlotPotionBottle
 * JD-Core Version:		0.6.0
 */