/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.HashMap;
/*		 */ import java.util.LinkedHashMap;
/*		 */ import java.util.List;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class ItemPotion extends Item
/*		 */ {
/*	21 */	 private HashMap a = new HashMap();
/*	22 */	 private static final Map b = new LinkedHashMap();
/*		 */ 
/*		 */	 public ItemPotion(int paramInt) {
/*	25 */		 super(paramInt);
/*		 */ 
/*	27 */		 d(1);
/*	28 */		 a(true);
/*	29 */		 setMaxDurability(0);
/*	30 */		 a(CreativeModeTab.k);
/*		 */	 }
/*		 */ 
/*		 */	 public List l(ItemStack paramItemStack) {
/*	34 */		 return f(paramItemStack.getData());
/*		 */	 }
/*		 */ 
/*		 */	 public List f(int paramInt)
/*		 */	 {
/*	39 */		 List localList = (List)this.a.get(Integer.valueOf(paramInt));
/*	40 */		 if (localList == null) {
/*	41 */			 localList = PotionBrewer.getEffects(paramInt, false);
/*	42 */			 this.a.put(Integer.valueOf(paramInt), localList);
/*		 */		 }
/*	44 */		 return localList;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
/*		 */	 {
/*	49 */		 if (!paramEntityHuman.abilities.canInstantlyBuild) paramItemStack.count -= 1;
/*		 */ 
/*	51 */		 if (!paramWorld.isStatic) {
/*	52 */			 List localList = l(paramItemStack);
/*	53 */			 if (localList != null) {
/*	54 */				 for (MobEffect localMobEffect : localList) {
/*	55 */					 paramEntityHuman.addEffect(new MobEffect(localMobEffect));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*	59 */		 if (!paramEntityHuman.abilities.canInstantlyBuild) {
/*	60 */			 if (paramItemStack.count <= 0) {
/*	61 */				 return new ItemStack(Item.GLASS_BOTTLE);
/*		 */			 }
/*	63 */			 paramEntityHuman.inventory.pickup(new ItemStack(Item.GLASS_BOTTLE));
/*		 */		 }
/*		 */ 
/*	67 */		 return paramItemStack;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(ItemStack paramItemStack)
/*		 */	 {
/*	72 */		 return 32;
/*		 */	 }
/*		 */ 
/*		 */	 public EnumAnimation b(ItemStack paramItemStack)
/*		 */	 {
/*	77 */		 return EnumAnimation.c;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
/*		 */	 {
/*	82 */		 if (g(paramItemStack.getData())) {
/*	83 */			 if (!paramEntityHuman.abilities.canInstantlyBuild) paramItemStack.count -= 1;
/*	84 */			 paramWorld.makeSound(paramEntityHuman, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
/*	85 */			 if (!paramWorld.isStatic) paramWorld.addEntity(new EntityPotion(paramWorld, paramEntityHuman, paramItemStack.getData()));
/*	86 */			 return paramItemStack;
/*		 */		 }
/*	88 */		 paramEntityHuman.a(paramItemStack, a(paramItemStack));
/*	89 */		 return paramItemStack;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/*	94 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean g(int paramInt)
/*		 */	 {
/* 114 */		 return (paramInt & 0x4000) != 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ItemPotion
 * JD-Core Version:		0.6.0
 */