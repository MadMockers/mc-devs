package net.minecraft.server;

public class SlotFurnaceResult extends Slot
{
	private EntityHuman a;
	private int b;

	public SlotFurnaceResult(EntityHuman paramEntityHuman, IInventory paramIInventory, int paramInt1, int paramInt2, int paramInt3)
	{
		super(paramIInventory, paramInt1, paramInt2, paramInt3);

		this.a = paramEntityHuman;
	}

	public boolean isAllowed(ItemStack paramItemStack)
	{
		return false;
	}

	public ItemStack a(int paramInt)
	{
		if (d()) {
			this.b += Math.min(paramInt, getItem().count);
		}
		return super.a(paramInt);
	}

	public void b(ItemStack paramItemStack)
	{
		c(paramItemStack);
		super.b(paramItemStack);
	}

	protected void a(ItemStack paramItemStack, int paramInt)
	{
		this.b += paramInt;
		c(paramItemStack);
	}

	protected void c(ItemStack paramItemStack)
	{
		paramItemStack.a(this.a.world, this.a, this.b);

		if (!this.a.world.isStatic) {
			int i = this.b;
			float f = RecipesFurnace.getInstance().c(paramItemStack.id);
			int j;
			if (f == 0.0F) {
				i = 0;
			} else if (f < 1.0F) {
				j = MathHelper.d(i * f);
				if ((j < MathHelper.f(i * f)) && ((float)Math.random() < i * f - j)) {
					j++;
				}
				i = j;
			}

			while (i > 0) {
				j = EntityExperienceOrb.getOrbValue(i);
				i -= j;
				this.a.world.addEntity(new EntityExperienceOrb(this.a.world, this.a.locX, this.a.locY + 0.5D, this.a.locZ + 0.5D, j));
			}
		}
		this.b = 0;

		if (paramItemStack.id == Item.IRON_INGOT.id) this.a.a(AchievementList.k, 1);
		if (paramItemStack.id == Item.COOKED_FISH.id) this.a.a(AchievementList.p, 1);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.SlotFurnaceResult
 * JD-Core Version:		0.6.0
 */