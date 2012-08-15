/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.DataOutputStream;
/*		 */ import java.util.ArrayList;
/*		 */ 
/*		 */ public class MerchantRecipeList extends ArrayList
/*		 */ {
/*		 */	 public MerchantRecipeList()
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public MerchantRecipeList(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/*	24 */		 a(paramNBTTagCompound);
/*		 */	 }
/*		 */ 
/*		 */	 public MerchantRecipe a(ItemStack paramItemStack1, ItemStack paramItemStack2, int paramInt) {
/*	28 */		 if ((paramInt > 0) && (paramInt < size()))
/*		 */		 {
/*	30 */			 MerchantRecipe localMerchantRecipe1 = (MerchantRecipe)get(paramInt);
/*	31 */			 if ((paramItemStack1.id == localMerchantRecipe1.getBuyItem1().id) && (((paramItemStack2 == null) && (!localMerchantRecipe1.hasSecondItem())) || ((localMerchantRecipe1.hasSecondItem()) && (paramItemStack2 != null) && (localMerchantRecipe1.getBuyItem2().id == paramItemStack2.id)))) {
/*	32 */				 if ((paramItemStack1.count >= localMerchantRecipe1.getBuyItem1().count) && ((!localMerchantRecipe1.hasSecondItem()) || (paramItemStack2.count >= localMerchantRecipe1.getBuyItem2().count))) {
/*	33 */					 return localMerchantRecipe1;
/*		 */				 }
/*	35 */				 return null;
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	39 */		 for (int i = 0; i < size(); i++) {
/*	40 */			 MerchantRecipe localMerchantRecipe2 = (MerchantRecipe)get(i);
/*	41 */			 if ((paramItemStack1.id == localMerchantRecipe2.getBuyItem1().id) && (paramItemStack1.count >= localMerchantRecipe2.getBuyItem1().count) && (((!localMerchantRecipe2.hasSecondItem()) && (paramItemStack2 == null)) || ((localMerchantRecipe2.hasSecondItem()) && (paramItemStack2 != null) && (localMerchantRecipe2.getBuyItem2().id == paramItemStack2.id) && (paramItemStack2.count >= localMerchantRecipe2.getBuyItem2().count))))
/*		 */			 {
/*	43 */				 return localMerchantRecipe2;
/*		 */			 }
/*		 */		 }
/*	46 */		 return null;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(MerchantRecipe paramMerchantRecipe) {
/*	50 */		 for (int i = 0; i < size(); i++) {
/*	51 */			 MerchantRecipe localMerchantRecipe = (MerchantRecipe)get(i);
/*	52 */			 if (paramMerchantRecipe.a(localMerchantRecipe)) {
/*	53 */				 if (paramMerchantRecipe.b(localMerchantRecipe)) {
/*	54 */					 set(i, paramMerchantRecipe);
/*		 */				 }
/*	56 */				 return;
/*		 */			 }
/*		 */		 }
/*	59 */		 add(paramMerchantRecipe);
/*		 */	 }
/*		 */ 
/*		 */	 public void a(DataOutputStream paramDataOutputStream)
/*		 */	 {
/*	75 */		 paramDataOutputStream.writeByte((byte)(size() & 0xFF));
/*	76 */		 for (int i = 0; i < size(); i++) {
/*	77 */			 MerchantRecipe localMerchantRecipe = (MerchantRecipe)get(i);
/*	78 */			 Packet.a(localMerchantRecipe.getBuyItem1(), paramDataOutputStream);
/*	79 */			 Packet.a(localMerchantRecipe.getBuyItem3(), paramDataOutputStream);
/*		 */ 
/*	81 */			 ItemStack localItemStack = localMerchantRecipe.getBuyItem2();
/*	82 */			 paramDataOutputStream.writeBoolean(localItemStack != null);
/*	83 */			 if (localItemStack != null)
/*	84 */				 Packet.a(localItemStack, paramDataOutputStream);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 109 */		 NBTTagList localNBTTagList = paramNBTTagCompound.getList("Recipes");
/*		 */ 
/* 111 */		 for (int i = 0; i < localNBTTagList.size(); i++) {
/* 112 */			 NBTTagCompound localNBTTagCompound = (NBTTagCompound)localNBTTagList.get(i);
/* 113 */			 add(new MerchantRecipe(localNBTTagCompound));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 public NBTTagCompound a() {
/* 118 */		 NBTTagCompound localNBTTagCompound = new NBTTagCompound();
/*		 */ 
/* 120 */		 NBTTagList localNBTTagList = new NBTTagList("Recipes");
/* 121 */		 for (int i = 0; i < size(); i++) {
/* 122 */			 MerchantRecipe localMerchantRecipe = (MerchantRecipe)get(i);
/* 123 */			 localNBTTagList.add(localMerchantRecipe.g());
/*		 */		 }
/* 125 */		 localNBTTagCompound.set("Recipes", localNBTTagList);
/*		 */ 
/* 127 */		 return localNBTTagCompound;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MerchantRecipeList
 * JD-Core Version:		0.6.0
 */