package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EnchantmentManager
{
	private static final Random random = new Random();

	private static final EnchantmentModifierProtection b = new EnchantmentModifierProtection(null);

	private static final EnchantmentModifierDamage c = new EnchantmentModifierDamage(null);

	public static int getEnchantmentLevel(int paramInt, ItemStack paramItemStack)
	{
		if (paramItemStack == null) {
			return 0;
		}
		NBTTagList localNBTTagList = paramItemStack.getEnchantments();
		if (localNBTTagList == null) {
			return 0;
		}
		for (int i = 0; i < localNBTTagList.size(); i++) {
			int j = ((NBTTagCompound)localNBTTagList.get(i)).getShort("id");
			int k = ((NBTTagCompound)localNBTTagList.get(i)).getShort("lvl");

			if (j == paramInt) {
				return k;
			}
		}
		return 0;
	}

	private static int getEnchantmentLevel(int paramInt, ItemStack[] paramArrayOfItemStack) {
		int i = 0;
		for (ItemStack localItemStack : paramArrayOfItemStack) {
			int m = getEnchantmentLevel(paramInt, localItemStack);
			if (m > i) {
				i = m;
			}
		}
		return i;
	}

	private static void a(EnchantmentModifier paramEnchantmentModifier, ItemStack paramItemStack)
	{
		if (paramItemStack == null) {
			return;
		}
		NBTTagList localNBTTagList = paramItemStack.getEnchantments();
		if (localNBTTagList == null) {
			return;
		}
		for (int i = 0; i < localNBTTagList.size(); i++) {
			int j = ((NBTTagCompound)localNBTTagList.get(i)).getShort("id");
			int k = ((NBTTagCompound)localNBTTagList.get(i)).getShort("lvl");

			if (Enchantment.byId[j] != null)
				paramEnchantmentModifier.a(Enchantment.byId[j], k);
		}
	}

	private static void a(EnchantmentModifier paramEnchantmentModifier, ItemStack[] paramArrayOfItemStack)
	{
		for (ItemStack localItemStack : paramArrayOfItemStack)
			a(paramEnchantmentModifier, localItemStack);
	}

	public static int a(PlayerInventory paramPlayerInventory, DamageSource paramDamageSource)
	{
		b.a = 0;
		b.b = paramDamageSource;

		a(b, paramPlayerInventory.armor);

		if (b.a > 25) {
			b.a = 25;
		}

		return (b.a + 1 >> 1) + random.nextInt((b.a >> 1) + 1);
	}

	public static int a(PlayerInventory paramPlayerInventory, EntityLiving paramEntityLiving)
	{
		c.a = 0;
		c.b = paramEntityLiving;

		a(c, paramPlayerInventory.getItemInHand());

		if (c.a > 0) {
			return 1 + random.nextInt(c.a);
		}
		return 0;
	}

	public static int getKnockbackEnchantmentLevel(PlayerInventory paramPlayerInventory, EntityLiving paramEntityLiving) {
		return getEnchantmentLevel(Enchantment.KNOCKBACK.id, paramPlayerInventory.getItemInHand());
	}

	public static int getFireAspectEnchantmentLevel(PlayerInventory paramPlayerInventory, EntityLiving paramEntityLiving) {
		return getEnchantmentLevel(Enchantment.FIRE_ASPECT.id, paramPlayerInventory.getItemInHand());
	}

	public static int getOxygenEnchantmentLevel(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.OXYGEN.id, paramPlayerInventory.armor);
	}

	public static int getDigSpeedEnchantmentLevel(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.DIG_SPEED.id, paramPlayerInventory.getItemInHand());
	}

	public static int getDurabilityEnchantmentLevel(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.DURABILITY.id, paramPlayerInventory.getItemInHand());
	}

	public static boolean hasSilkTouchEnchantment(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.SILK_TOUCH.id, paramPlayerInventory.getItemInHand()) > 0;
	}

	public static int getBonusBlockLootEnchantmentLevel(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS.id, paramPlayerInventory.getItemInHand());
	}

	public static int getBonusMonsterLootEnchantmentLevel(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS.id, paramPlayerInventory.getItemInHand());
	}

	public static boolean hasWaterWorkerEnchantment(PlayerInventory paramPlayerInventory) {
		return getEnchantmentLevel(Enchantment.WATER_WORKER.id, paramPlayerInventory.armor) > 0;
	}

	public static int a(Random paramRandom, int paramInt1, int paramInt2, ItemStack paramItemStack)
	{
		Item localItem = paramItemStack.getItem();
		int i = localItem.b();

		if (i <= 0)
		{
			return 0;
		}

		if (paramInt2 > 15) {
			paramInt2 = 15;
		}
		int j = paramRandom.nextInt(8) + 1 + (paramInt2 >> 1) + paramRandom.nextInt(paramInt2 + 1);
		if (paramInt1 == 0) {
			return Math.max(j / 3, 1);
		}
		if (paramInt1 == 1) {
			return j * 2 / 3 + 1;
		}
		return Math.max(j, paramInt2 * 2);
	}

	public static ItemStack a(Random paramRandom, ItemStack paramItemStack, int paramInt) {
		List localList = b(paramRandom, paramItemStack, paramInt);
		if (localList != null) {
			for (EnchantmentInstance localEnchantmentInstance : localList) {
				paramItemStack.addEnchantment(localEnchantmentInstance.enchantment, localEnchantmentInstance.level);
			}
		}
		return paramItemStack;
	}

	public static List b(Random paramRandom, ItemStack paramItemStack, int paramInt)
	{
		Item localItem = paramItemStack.getItem();
		int i = localItem.b();

		if (i <= 0) {
			return null;
		}
		i /= 2;
		i = 1 + paramRandom.nextInt((i >> 1) + 1) + paramRandom.nextInt((i >> 1) + 1);

		int j = i + paramInt;

		float f = (paramRandom.nextFloat() + paramRandom.nextFloat() - 1.0F) * 0.15F;
		int k = (int)(j * (1.0F + f) + 0.5F);
		if (k < 1) {
			k = 1;
		}

		ArrayList localArrayList = null;

		Map localMap = b(k, paramItemStack);
		if ((localMap != null) && (!localMap.isEmpty()))
		{
			EnchantmentInstance localEnchantmentInstance1 = (EnchantmentInstance)WeightedRandom.a(paramRandom, localMap.values());

			if (localEnchantmentInstance1 != null) {
				localArrayList = new ArrayList();
				localArrayList.add(localEnchantmentInstance1);

				int m = k;
				while (paramRandom.nextInt(50) <= m)
				{
					Iterator localIterator1 = localMap.keySet().iterator();
					Object localObject;
					while (localIterator1.hasNext()) {
						localObject = (Integer)localIterator1.next();
						int n = 1;
						for (EnchantmentInstance localEnchantmentInstance2 : localArrayList) {
							if (!localEnchantmentInstance2.enchantment.a(Enchantment.byId[localObject.intValue()])) {
								n = 0;
								break;
							}
						}
						if (n == 0) {
							localIterator1.remove();
						}
					}

					if (!localMap.isEmpty()) {
						localObject = (EnchantmentInstance)WeightedRandom.a(paramRandom, localMap.values());
						localArrayList.add(localObject);
					}

					m >>= 1;
				}
			}
		}

		return (List)localArrayList;
	}

	public static Map b(int paramInt, ItemStack paramItemStack)
	{
		Item localItem = paramItemStack.getItem();
		HashMap localHashMap = null;

		for (Enchantment localEnchantment : Enchantment.byId)
		{
			if (localEnchantment == null)
			{
				continue;
			}
			if (!localEnchantment.slot.canEnchant(localItem))
			{
				continue;
			}
			for (int k = localEnchantment.getStartLevel(); k <= localEnchantment.getMaxLevel(); k++) {
				if ((paramInt < localEnchantment.a(k)) || (paramInt > localEnchantment.b(k)))
					continue;
				if (localHashMap == null) {
					localHashMap = new HashMap();
				}
				localHashMap.put(Integer.valueOf(localEnchantment.id), new EnchantmentInstance(localEnchantment, k));
			}

		}

		return localHashMap;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EnchantmentManager
 * JD-Core Version:		0.6.0
 */