/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ class SlotBrewing extends Slot
/*		 */ {
/*		 */	 public SlotBrewing(ContainerBrewingStand paramContainerBrewingStand, IInventory paramIInventory, int paramInt1, int paramInt2, int paramInt3)
/*		 */	 {
/* 151 */		 super(paramIInventory, paramInt1, paramInt2, paramInt3);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isAllowed(ItemStack paramItemStack)
/*		 */	 {
/* 156 */		 if (paramItemStack != null)
/*		 */		 {
/* 158 */			 return Item.byId[paramItemStack.id].u();
/*		 */		 }
/*		 */ 
/* 163 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int a()
/*		 */	 {
/* 168 */		 return 64;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.SlotBrewing
 * JD-Core Version:		0.6.0
 */