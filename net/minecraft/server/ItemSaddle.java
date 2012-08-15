package net.minecraft.server;

public class ItemSaddle extends Item
{
	public ItemSaddle(int paramInt)
	{
		super(paramInt);
		this.maxStackSize = 1;
		a(CreativeModeTab.e);
	}

	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving)
	{
		if ((paramEntityLiving instanceof EntityPig)) {
			EntityPig localEntityPig = (EntityPig)paramEntityLiving;
			if ((!localEntityPig.hasSaddle()) && (!localEntityPig.isBaby())) {
				localEntityPig.setSaddle(true);
				paramItemStack.count -= 1;
			}

			return true;
		}
		return false;
	}

	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
	{
		a(paramItemStack, paramEntityLiving1);
		return true;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemSaddle
 * JD-Core Version:		0.6.0
 */