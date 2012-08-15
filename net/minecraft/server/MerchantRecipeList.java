package net.minecraft.server;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class MerchantRecipeList extends ArrayList
{
	public MerchantRecipeList()
	{
	}

	public MerchantRecipeList(NBTTagCompound paramNBTTagCompound)
	{
		a(paramNBTTagCompound);
	}

	public MerchantRecipe a(ItemStack paramItemStack1, ItemStack paramItemStack2, int paramInt) {
		if ((paramInt > 0) && (paramInt < size()))
		{
			MerchantRecipe localMerchantRecipe1 = (MerchantRecipe)get(paramInt);
			if ((paramItemStack1.id == localMerchantRecipe1.getBuyItem1().id) && (((paramItemStack2 == null) && (!localMerchantRecipe1.hasSecondItem())) || ((localMerchantRecipe1.hasSecondItem()) && (paramItemStack2 != null) && (localMerchantRecipe1.getBuyItem2().id == paramItemStack2.id)))) {
				if ((paramItemStack1.count >= localMerchantRecipe1.getBuyItem1().count) && ((!localMerchantRecipe1.hasSecondItem()) || (paramItemStack2.count >= localMerchantRecipe1.getBuyItem2().count))) {
					return localMerchantRecipe1;
				}
				return null;
			}
		}

		for (int i = 0; i < size(); i++) {
			MerchantRecipe localMerchantRecipe2 = (MerchantRecipe)get(i);
			if ((paramItemStack1.id == localMerchantRecipe2.getBuyItem1().id) && (paramItemStack1.count >= localMerchantRecipe2.getBuyItem1().count) && (((!localMerchantRecipe2.hasSecondItem()) && (paramItemStack2 == null)) || ((localMerchantRecipe2.hasSecondItem()) && (paramItemStack2 != null) && (localMerchantRecipe2.getBuyItem2().id == paramItemStack2.id) && (paramItemStack2.count >= localMerchantRecipe2.getBuyItem2().count))))
			{
				return localMerchantRecipe2;
			}
		}
		return null;
	}

	public void a(MerchantRecipe paramMerchantRecipe) {
		for (int i = 0; i < size(); i++) {
			MerchantRecipe localMerchantRecipe = (MerchantRecipe)get(i);
			if (paramMerchantRecipe.a(localMerchantRecipe)) {
				if (paramMerchantRecipe.b(localMerchantRecipe)) {
					set(i, paramMerchantRecipe);
				}
				return;
			}
		}
		add(paramMerchantRecipe);
	}

	public void a(DataOutputStream paramDataOutputStream)
	{
		paramDataOutputStream.writeByte((byte)(size() & 0xFF));
		for (int i = 0; i < size(); i++) {
			MerchantRecipe localMerchantRecipe = (MerchantRecipe)get(i);
			Packet.a(localMerchantRecipe.getBuyItem1(), paramDataOutputStream);
			Packet.a(localMerchantRecipe.getBuyItem3(), paramDataOutputStream);

			ItemStack localItemStack = localMerchantRecipe.getBuyItem2();
			paramDataOutputStream.writeBoolean(localItemStack != null);
			if (localItemStack != null)
				Packet.a(localItemStack, paramDataOutputStream);
		}
	}

	public void a(NBTTagCompound paramNBTTagCompound)
	{
		NBTTagList localNBTTagList = paramNBTTagCompound.getList("Recipes");

		for (int i = 0; i < localNBTTagList.size(); i++) {
			NBTTagCompound localNBTTagCompound = (NBTTagCompound)localNBTTagList.get(i);
			add(new MerchantRecipe(localNBTTagCompound));
		}
	}

	public NBTTagCompound a() {
		NBTTagCompound localNBTTagCompound = new NBTTagCompound();

		NBTTagList localNBTTagList = new NBTTagList("Recipes");
		for (int i = 0; i < size(); i++) {
			MerchantRecipe localMerchantRecipe = (MerchantRecipe)get(i);
			localNBTTagList.add(localMerchantRecipe.g());
		}
		localNBTTagCompound.set("Recipes", localNBTTagList);

		return localNBTTagCompound;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.MerchantRecipeList
 * JD-Core Version:		0.6.0
 */