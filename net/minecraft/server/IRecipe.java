package net.minecraft.server;

import org.bukkit.inventory.Recipe;

public abstract interface IRecipe
{
	public abstract boolean a(InventoryCrafting paramInventoryCrafting);

	public abstract ItemStack b(InventoryCrafting paramInventoryCrafting);

	public abstract int a();

	public abstract ItemStack b();

	public abstract Recipe toBukkitRecipe();
}

/* 
 * Qualified Name:		 net.minecraft.server.IRecipe
 * JD-Core Version:		0.6.0
 */