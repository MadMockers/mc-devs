package net.minecraft.server;

public abstract interface IMerchant
{
	public abstract void a_(EntityHuman paramEntityHuman);

	public abstract EntityHuman l_();

	public abstract MerchantRecipeList getOffers(EntityHuman paramEntityHuman);

	public abstract void a(MerchantRecipe paramMerchantRecipe);
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.IMerchant
 * JD-Core Version:		0.6.0
 */