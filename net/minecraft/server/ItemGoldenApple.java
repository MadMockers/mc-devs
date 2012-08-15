package net.minecraft.server;

public class ItemGoldenApple extends ItemFood
{
	public ItemGoldenApple(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean)
	{
		super(paramInt1, paramInt2, paramFloat, paramBoolean);

		a(true);
	}

	protected void c(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		if (paramItemStack.getData() > 0) {
			if (!paramWorld.isStatic) {
				paramEntityHuman.addEffect(new MobEffect(MobEffectList.REGENERATION.id, 600, 3));
				paramEntityHuman.addEffect(new MobEffect(MobEffectList.RESISTANCE.id, 6000, 0));
				paramEntityHuman.addEffect(new MobEffect(MobEffectList.FIRE_RESISTANCE.id, 6000, 0));
			}
		}
		else super.c(paramItemStack, paramWorld, paramEntityHuman);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemGoldenApple
 * JD-Core Version:		0.6.0
 */