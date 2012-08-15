package net.minecraft.server;

public class SlotResult extends Slot
{
	private final IInventory a;
	private EntityHuman b;
	private int g;

	public SlotResult(EntityHuman paramEntityHuman, IInventory paramIInventory1, IInventory paramIInventory2, int paramInt1, int paramInt2, int paramInt3)
	{
		super(paramIInventory2, paramInt1, paramInt2, paramInt3);
		this.b = paramEntityHuman;
		this.a = paramIInventory1;
	}

	public boolean isAllowed(ItemStack paramItemStack)
	{
		return false;
	}

	public ItemStack a(int paramInt)
	{
		if (d()) {
			this.g += Math.min(paramInt, getItem().count);
		}
		return super.a(paramInt);
	}

	protected void a(ItemStack paramItemStack, int paramInt)
	{
		this.g += paramInt;
		c(paramItemStack);
	}

	protected void c(ItemStack paramItemStack)
	{
		paramItemStack.a(this.b.world, this.b, this.g);
		this.g = 0;

		if (paramItemStack.id == Block.WORKBENCH.id) this.b.a(AchievementList.h, 1);
		else if (paramItemStack.id == Item.WOOD_PICKAXE.id) this.b.a(AchievementList.i, 1);
		else if (paramItemStack.id == Block.FURNACE.id) this.b.a(AchievementList.j, 1);
		else if (paramItemStack.id == Item.WOOD_HOE.id) this.b.a(AchievementList.l, 1);
		else if (paramItemStack.id == Item.BREAD.id) this.b.a(AchievementList.m, 1);
		else if (paramItemStack.id == Item.CAKE.id) this.b.a(AchievementList.n, 1);
		else if (paramItemStack.id == Item.STONE_PICKAXE.id) this.b.a(AchievementList.o, 1);
		else if (paramItemStack.id == Item.WOOD_SWORD.id) this.b.a(AchievementList.r, 1);
		else if (paramItemStack.id == Block.ENCHANTMENT_TABLE.id) this.b.a(AchievementList.D, 1);
		else if (paramItemStack.id == Block.BOOKSHELF.id) this.b.a(AchievementList.F, 1);
	}

	public void b(ItemStack paramItemStack)
	{
		c(paramItemStack);

		for (int i = 0; i < this.a.getSize(); i++) {
			ItemStack localItemStack1 = this.a.getItem(i);
			if (localItemStack1 != null) {
				this.a.splitStack(i, 1);

				if (!localItemStack1.getItem().r())
					continue;
				ItemStack localItemStack2 = new ItemStack(localItemStack1.getItem().q());

				if ((localItemStack1.getItem().h(localItemStack1)) && (this.b.inventory.pickup(localItemStack2)))
				{
					continue;
				}

				if (this.a.getItem(i) == null) {
					this.a.setItem(i, localItemStack2);
				}
				else
					this.b.drop(localItemStack2);
			}
		}
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.SlotResult
 * JD-Core Version:		0.6.0
 */