package net.minecraft.server;

public class MerchantRecipe
{
	private ItemStack buyingItem1;
	private ItemStack buyingItem2;
	private ItemStack sellingItem;
	private int uses;

	public MerchantRecipe(NBTTagCompound paramNBTTagCompound)
	{
		a(paramNBTTagCompound);
	}

	public MerchantRecipe(ItemStack paramItemStack1, ItemStack paramItemStack2, ItemStack paramItemStack3) {
		this.buyingItem1 = paramItemStack1;
		this.buyingItem2 = paramItemStack2;
		this.sellingItem = paramItemStack3;
	}

	public MerchantRecipe(ItemStack paramItemStack1, ItemStack paramItemStack2) {
		this(paramItemStack1, null, paramItemStack2);
	}

	public MerchantRecipe(ItemStack paramItemStack, Item paramItem) {
		this(paramItemStack, new ItemStack(paramItem));
	}

	public ItemStack getBuyItem1()
	{
		return this.buyingItem1;
	}

	public ItemStack getBuyItem2() {
		return this.buyingItem2;
	}

	public boolean hasSecondItem() {
		return this.buyingItem2 != null;
	}

	public ItemStack getBuyItem3() {
		return this.sellingItem;
	}

	public boolean a(MerchantRecipe paramMerchantRecipe) {
		if ((this.buyingItem1.id != paramMerchantRecipe.buyingItem1.id) || (this.sellingItem.id != paramMerchantRecipe.sellingItem.id)) {
			return false;
		}
		return ((this.buyingItem2 == null) && (paramMerchantRecipe.buyingItem2 == null)) || ((this.buyingItem2 != null) && (paramMerchantRecipe.buyingItem2 != null) && (this.buyingItem2.id == paramMerchantRecipe.buyingItem2.id));
	}

	public boolean b(MerchantRecipe paramMerchantRecipe)
	{
		return (a(paramMerchantRecipe)) && ((this.buyingItem1.count < paramMerchantRecipe.buyingItem1.count) || ((this.buyingItem2 != null) && (this.buyingItem2.count < paramMerchantRecipe.buyingItem2.count)));
	}

	public int getUses() {
		return this.uses;
	}

	public void f() {
		this.uses += 1;
	}

	public void a(NBTTagCompound paramNBTTagCompound) {
		NBTTagCompound localNBTTagCompound1 = paramNBTTagCompound.getCompound("buy");
		this.buyingItem1 = ItemStack.a(localNBTTagCompound1);
		NBTTagCompound localNBTTagCompound2 = paramNBTTagCompound.getCompound("sell");
		this.sellingItem = ItemStack.a(localNBTTagCompound2);
		if (paramNBTTagCompound.hasKey("buyB")) {
			this.buyingItem2 = ItemStack.a(paramNBTTagCompound.getCompound("buyB"));
		}
		if (paramNBTTagCompound.hasKey("uses"))
			this.uses = paramNBTTagCompound.getInt("uses");
	}

	public NBTTagCompound g()
	{
		NBTTagCompound localNBTTagCompound = new NBTTagCompound();
		localNBTTagCompound.setCompound("buy", this.buyingItem1.save(new NBTTagCompound("buy")));
		localNBTTagCompound.setCompound("sell", this.sellingItem.save(new NBTTagCompound("sell")));
		if (this.buyingItem2 != null) {
			localNBTTagCompound.setCompound("buyB", this.buyingItem2.save(new NBTTagCompound("buyB")));
		}
		localNBTTagCompound.setInt("uses", this.uses);
		return localNBTTagCompound;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.MerchantRecipe
 * JD-Core Version:		0.6.0
 */