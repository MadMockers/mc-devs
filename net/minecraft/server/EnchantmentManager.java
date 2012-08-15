/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ import java.util.Set;
/*		 */ 
/*		 */ public class EnchantmentManager
/*		 */ {
/*	14 */	 private static final Random random = new Random();
/*		 */ 
/*	83 */	 private static final EnchantmentModifierProtection b = new EnchantmentModifierProtection(null);
/*		 */ 
/* 115 */	 private static final EnchantmentModifierDamage c = new EnchantmentModifierDamage(null);
/*		 */ 
/*		 */	 public static int getEnchantmentLevel(int paramInt, ItemStack paramItemStack)
/*		 */	 {
/*	17 */		 if (paramItemStack == null) {
/*	18 */			 return 0;
/*		 */		 }
/*	20 */		 NBTTagList localNBTTagList = paramItemStack.getEnchantments();
/*	21 */		 if (localNBTTagList == null) {
/*	22 */			 return 0;
/*		 */		 }
/*	24 */		 for (int i = 0; i < localNBTTagList.size(); i++) {
/*	25 */			 int j = ((NBTTagCompound)localNBTTagList.get(i)).getShort("id");
/*	26 */			 int k = ((NBTTagCompound)localNBTTagList.get(i)).getShort("lvl");
/*		 */ 
/*	28 */			 if (j == paramInt) {
/*	29 */				 return k;
/*		 */			 }
/*		 */		 }
/*	32 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 private static int getEnchantmentLevel(int paramInt, ItemStack[] paramArrayOfItemStack) {
/*	36 */		 int i = 0;
/*	37 */		 for (ItemStack localItemStack : paramArrayOfItemStack) {
/*	38 */			 int m = getEnchantmentLevel(paramInt, localItemStack);
/*	39 */			 if (m > i) {
/*	40 */				 i = m;
/*		 */			 }
/*		 */		 }
/*	43 */		 return i;
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(EnchantmentModifier paramEnchantmentModifier, ItemStack paramItemStack)
/*		 */	 {
/*	51 */		 if (paramItemStack == null) {
/*	52 */			 return;
/*		 */		 }
/*	54 */		 NBTTagList localNBTTagList = paramItemStack.getEnchantments();
/*	55 */		 if (localNBTTagList == null) {
/*	56 */			 return;
/*		 */		 }
/*	58 */		 for (int i = 0; i < localNBTTagList.size(); i++) {
/*	59 */			 int j = ((NBTTagCompound)localNBTTagList.get(i)).getShort("id");
/*	60 */			 int k = ((NBTTagCompound)localNBTTagList.get(i)).getShort("lvl");
/*		 */ 
/*	62 */			 if (Enchantment.byId[j] != null)
/*	63 */				 paramEnchantmentModifier.a(Enchantment.byId[j], k);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(EnchantmentModifier paramEnchantmentModifier, ItemStack[] paramArrayOfItemStack)
/*		 */	 {
/*	69 */		 for (ItemStack localItemStack : paramArrayOfItemStack)
/*	70 */			 a(paramEnchantmentModifier, localItemStack);
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(PlayerInventory paramPlayerInventory, DamageSource paramDamageSource)
/*		 */	 {
/*	93 */		 b.a = 0;
/*	94 */		 b.b = paramDamageSource;
/*		 */ 
/*	96 */		 a(b, paramPlayerInventory.armor);
/*		 */ 
/*	98 */		 if (b.a > 25) {
/*	99 */			 b.a = 25;
/*		 */		 }
/*		 */ 
/* 103 */		 return (b.a + 1 >> 1) + random.nextInt((b.a >> 1) + 1);
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(PlayerInventory paramPlayerInventory, EntityLiving paramEntityLiving)
/*		 */	 {
/* 124 */		 c.a = 0;
/* 125 */		 c.b = paramEntityLiving;
/*		 */ 
/* 127 */		 a(c, paramPlayerInventory.getItemInHand());
/*		 */ 
/* 129 */		 if (c.a > 0) {
/* 130 */			 return 1 + random.nextInt(c.a);
/*		 */		 }
/* 132 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static int getKnockbackEnchantmentLevel(PlayerInventory paramPlayerInventory, EntityLiving paramEntityLiving) {
/* 136 */		 return getEnchantmentLevel(Enchantment.KNOCKBACK.id, paramPlayerInventory.getItemInHand());
/*		 */	 }
/*		 */ 
/*		 */	 public static int getFireAspectEnchantmentLevel(PlayerInventory paramPlayerInventory, EntityLiving paramEntityLiving) {
/* 140 */		 return getEnchantmentLevel(Enchantment.FIRE_ASPECT.id, paramPlayerInventory.getItemInHand());
/*		 */	 }
/*		 */ 
/*		 */	 public static int getOxygenEnchantmentLevel(PlayerInventory paramPlayerInventory) {
/* 144 */		 return getEnchantmentLevel(Enchantment.OXYGEN.id, paramPlayerInventory.armor);
/*		 */	 }
/*		 */ 
/*		 */	 public static int getDigSpeedEnchantmentLevel(PlayerInventory paramPlayerInventory) {
/* 148 */		 return getEnchantmentLevel(Enchantment.DIG_SPEED.id, paramPlayerInventory.getItemInHand());
/*		 */	 }
/*		 */ 
/*		 */	 public static int getDurabilityEnchantmentLevel(PlayerInventory paramPlayerInventory) {
/* 152 */		 return getEnchantmentLevel(Enchantment.DURABILITY.id, paramPlayerInventory.getItemInHand());
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean hasSilkTouchEnchantment(PlayerInventory paramPlayerInventory) {
/* 156 */		 return getEnchantmentLevel(Enchantment.SILK_TOUCH.id, paramPlayerInventory.getItemInHand()) > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static int getBonusBlockLootEnchantmentLevel(PlayerInventory paramPlayerInventory) {
/* 160 */		 return getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS.id, paramPlayerInventory.getItemInHand());
/*		 */	 }
/*		 */ 
/*		 */	 public static int getBonusMonsterLootEnchantmentLevel(PlayerInventory paramPlayerInventory) {
/* 164 */		 return getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS.id, paramPlayerInventory.getItemInHand());
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean hasWaterWorkerEnchantment(PlayerInventory paramPlayerInventory) {
/* 168 */		 return getEnchantmentLevel(Enchantment.WATER_WORKER.id, paramPlayerInventory.armor) > 0;
/*		 */	 }
/*		 */ 
/*		 */	 public static int a(Random paramRandom, int paramInt1, int paramInt2, ItemStack paramItemStack)
/*		 */	 {
/* 183 */		 Item localItem = paramItemStack.getItem();
/* 184 */		 int i = localItem.b();
/*		 */ 
/* 186 */		 if (i <= 0)
/*		 */		 {
/* 188 */			 return 0;
/*		 */		 }
/*		 */ 
/* 191 */		 if (paramInt2 > 15) {
/* 192 */			 paramInt2 = 15;
/*		 */		 }
/* 194 */		 int j = paramRandom.nextInt(8) + 1 + (paramInt2 >> 1) + paramRandom.nextInt(paramInt2 + 1);
/* 195 */		 if (paramInt1 == 0) {
/* 196 */			 return Math.max(j / 3, 1);
/*		 */		 }
/* 198 */		 if (paramInt1 == 1) {
/* 199 */			 return j * 2 / 3 + 1;
/*		 */		 }
/* 201 */		 return Math.max(j, paramInt2 * 2);
/*		 */	 }
/*		 */ 
/*		 */	 public static ItemStack a(Random paramRandom, ItemStack paramItemStack, int paramInt) {
/* 205 */		 List localList = b(paramRandom, paramItemStack, paramInt);
/* 206 */		 if (localList != null) {
/* 207 */			 for (EnchantmentInstance localEnchantmentInstance : localList) {
/* 208 */				 paramItemStack.addEnchantment(localEnchantmentInstance.enchantment, localEnchantmentInstance.level);
/*		 */			 }
/*		 */		 }
/* 211 */		 return paramItemStack;
/*		 */	 }
/*		 */ 
/*		 */	 public static List b(Random paramRandom, ItemStack paramItemStack, int paramInt)
/*		 */	 {
/* 223 */		 Item localItem = paramItemStack.getItem();
/* 224 */		 int i = localItem.b();
/*		 */ 
/* 226 */		 if (i <= 0) {
/* 227 */			 return null;
/*		 */		 }
/* 229 */		 i /= 2;
/* 230 */		 i = 1 + paramRandom.nextInt((i >> 1) + 1) + paramRandom.nextInt((i >> 1) + 1);
/*		 */ 
/* 232 */		 int j = i + paramInt;
/*		 */ 
/* 235 */		 float f = (paramRandom.nextFloat() + paramRandom.nextFloat() - 1.0F) * 0.15F;
/* 236 */		 int k = (int)(j * (1.0F + f) + 0.5F);
/* 237 */		 if (k < 1) {
/* 238 */			 k = 1;
/*		 */		 }
/*		 */ 
/* 241 */		 ArrayList localArrayList = null;
/*		 */ 
/* 243 */		 Map localMap = b(k, paramItemStack);
/* 244 */		 if ((localMap != null) && (!localMap.isEmpty()))
/*		 */		 {
/* 246 */			 EnchantmentInstance localEnchantmentInstance1 = (EnchantmentInstance)WeightedRandom.a(paramRandom, localMap.values());
/*		 */ 
/* 248 */			 if (localEnchantmentInstance1 != null) {
/* 249 */				 localArrayList = new ArrayList();
/* 250 */				 localArrayList.add(localEnchantmentInstance1);
/*		 */ 
/* 252 */				 int m = k;
/* 253 */				 while (paramRandom.nextInt(50) <= m)
/*		 */				 {
/* 256 */					 Iterator localIterator1 = localMap.keySet().iterator();
/*		 */					 Object localObject;
/* 257 */					 while (localIterator1.hasNext()) {
/* 258 */						 localObject = (Integer)localIterator1.next();
/* 259 */						 int n = 1;
/* 260 */						 for (EnchantmentInstance localEnchantmentInstance2 : localArrayList) {
/* 261 */							 if (!localEnchantmentInstance2.enchantment.a(Enchantment.byId[localObject.intValue()])) {
/* 262 */								 n = 0;
/* 263 */								 break;
/*		 */							 }
/*		 */						 }
/* 266 */						 if (n == 0) {
/* 267 */							 localIterator1.remove();
/*		 */						 }
/*		 */					 }
/*		 */ 
/* 271 */					 if (!localMap.isEmpty()) {
/* 272 */						 localObject = (EnchantmentInstance)WeightedRandom.a(paramRandom, localMap.values());
/* 273 */						 localArrayList.add(localObject);
/*		 */					 }
/*		 */ 
/* 276 */					 m >>= 1;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 281 */		 return (List)localArrayList;
/*		 */	 }
/*		 */ 
/*		 */	 public static Map b(int paramInt, ItemStack paramItemStack)
/*		 */	 {
/* 286 */		 Item localItem = paramItemStack.getItem();
/* 287 */		 HashMap localHashMap = null;
/*		 */ 
/* 289 */		 for (Enchantment localEnchantment : Enchantment.byId)
/*		 */		 {
/* 291 */			 if (localEnchantment == null)
/*		 */			 {
/*		 */				 continue;
/*		 */			 }
/* 295 */			 if (!localEnchantment.slot.canEnchant(localItem))
/*		 */			 {
/*		 */				 continue;
/*		 */			 }
/* 299 */			 for (int k = localEnchantment.getStartLevel(); k <= localEnchantment.getMaxLevel(); k++) {
/* 300 */				 if ((paramInt < localEnchantment.a(k)) || (paramInt > localEnchantment.b(k)))
/*		 */					 continue;
/* 302 */				 if (localHashMap == null) {
/* 303 */					 localHashMap = new HashMap();
/*		 */				 }
/* 305 */				 localHashMap.put(Integer.valueOf(localEnchantment.id), new EnchantmentInstance(localEnchantment, k));
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 310 */		 return localHashMap;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EnchantmentManager
 * JD-Core Version:		0.6.0
 */