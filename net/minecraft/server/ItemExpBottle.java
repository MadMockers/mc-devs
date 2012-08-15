package net.minecraft.server;

import java.util.Random;

public class ItemExpBottle extends Item
{
	public ItemExpBottle(int paramInt)
	{
		super(paramInt);
		a(CreativeModeTab.f);
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		if (!paramEntityHuman.abilities.canInstantlyBuild) {
			paramItemStack.count -= 1;
		}
		paramWorld.makeSound(paramEntityHuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
		if (!paramWorld.isStatic) paramWorld.addEntity(new EntityThrownExpBottle(paramWorld, paramEntityHuman));
		return paramItemStack;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemExpBottle
 * JD-Core Version:		0.6.0
 */