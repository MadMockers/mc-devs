package net.minecraft.server;

import java.util.Random;

public class ItemEgg extends Item
{
	public ItemEgg(int paramInt)
	{
		super(paramInt);
		this.maxStackSize = 16;
		a(CreativeModeTab.l);
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		if (!paramEntityHuman.abilities.canInstantlyBuild) {
			paramItemStack.count -= 1;
		}
		paramWorld.makeSound(paramEntityHuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
		if (!paramWorld.isStatic) paramWorld.addEntity(new EntityEgg(paramWorld, paramEntityHuman));
		return paramItemStack;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemEgg
 * JD-Core Version:		0.6.0
 */