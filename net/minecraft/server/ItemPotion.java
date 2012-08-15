package net.minecraft.server;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemPotion extends Item
{
	private HashMap a = new HashMap();
	private static final Map b = new LinkedHashMap();

	public ItemPotion(int paramInt) {
		super(paramInt);

		d(1);
		a(true);
		setMaxDurability(0);
		a(CreativeModeTab.k);
	}

	public List l(ItemStack paramItemStack) {
		return f(paramItemStack.getData());
	}

	public List f(int paramInt)
	{
		List localList = (List)this.a.get(Integer.valueOf(paramInt));
		if (localList == null) {
			localList = PotionBrewer.getEffects(paramInt, false);
			this.a.put(Integer.valueOf(paramInt), localList);
		}
		return localList;
	}

	public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		if (!paramEntityHuman.abilities.canInstantlyBuild) paramItemStack.count -= 1;

		if (!paramWorld.isStatic) {
			List localList = l(paramItemStack);
			if (localList != null) {
				for (MobEffect localMobEffect : localList) {
					paramEntityHuman.addEffect(new MobEffect(localMobEffect));
				}
			}
		}
		if (!paramEntityHuman.abilities.canInstantlyBuild) {
			if (paramItemStack.count <= 0) {
				return new ItemStack(Item.GLASS_BOTTLE);
			}
			paramEntityHuman.inventory.pickup(new ItemStack(Item.GLASS_BOTTLE));
		}

		return paramItemStack;
	}

	public int a(ItemStack paramItemStack)
	{
		return 32;
	}

	public EnumAnimation b(ItemStack paramItemStack)
	{
		return EnumAnimation.c;
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
		if (g(paramItemStack.getData())) {
			if (!paramEntityHuman.abilities.canInstantlyBuild) paramItemStack.count -= 1;
			paramWorld.makeSound(paramEntityHuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
			if (!paramWorld.isStatic) paramWorld.addEntity(new EntityPotion(paramWorld, paramEntityHuman, paramItemStack.getData()));
			return paramItemStack;
		}
		paramEntityHuman.a(paramItemStack, a(paramItemStack));
		return paramItemStack;
	}

	public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		return false;
	}

	public static boolean g(int paramInt)
	{
		return (paramInt & 0x4000) != 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ItemPotion
 * JD-Core Version:		0.6.0
 */