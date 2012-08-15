/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ class SlotPotionBottle extends Slot
/*		 */ {
/*		 */	 private EntityHuman a;
/*		 */ 
/*		 */	 public SlotPotionBottle(EntityHuman paramEntityHuman, IInventory paramIInventory, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 124 */		 super(paramIInventory, paramInt1, paramInt2, paramInt3);
/* 125 */		 this.a = paramEntityHuman;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isAllowed(ItemStack paramItemStack)
/*		 */	 {
/* 130 */		 return a_(paramItemStack);
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 135 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(ItemStack paramItemStack)
/*		 */	 {
/* 140 */		 if ((paramItemStack.id == Item.POTION.id) && (paramItemStack.getData() > 0)) this.a.a(AchievementList.A, 1);
/* 141 */		 super.b(paramItemStack);
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean a_(ItemStack paramItemStack) {
/* 145 */		 return (paramItemStack != null) && ((paramItemStack.id == Item.POTION.id) || (paramItemStack.id == Item.GLASS_BOTTLE.id));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.SlotPotionBottle
 * JD-Core Version:		0.6.0
 */