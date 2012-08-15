package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.inventory.CraftShapelessRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class ShapelessRecipes
	implements IRecipe
{
	private final ItemStack result;
	private final List ingredients;

	public ShapelessRecipes(ItemStack itemstack, List list)
	{
		this.result = itemstack;
		this.ingredients = list;
	}

	public ShapelessRecipe toBukkitRecipe()
	{
		CraftItemStack result = new CraftItemStack(this.result);
		CraftShapelessRecipe recipe = new CraftShapelessRecipe(result, this);
		for (ItemStack stack : this.ingredients) {
			if (stack != null) {
				recipe.addIngredient(Material.getMaterial(stack.id), stack.getData());
			}
		}
		return recipe;
	}

	public ItemStack b()
	{
		return this.result;
	}

	public boolean a(InventoryCrafting inventorycrafting) {
		ArrayList arraylist = new ArrayList(this.ingredients);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				ItemStack itemstack = inventorycrafting.b(j, i);

				if (itemstack != null) {
					boolean flag = false;
					Iterator iterator = arraylist.iterator();

					while (iterator.hasNext()) {
						ItemStack itemstack1 = (ItemStack)iterator.next();

						if ((itemstack.id == itemstack1.id) && ((itemstack1.getData() == -1) || (itemstack.getData() == itemstack1.getData()))) {
							flag = true;
							arraylist.remove(itemstack1);
							break;
						}
					}

					if (!flag) {
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	public ItemStack b(InventoryCrafting inventorycrafting) {
		return this.result.cloneItemStack();
	}

	public int a() {
		return this.ingredients.size();
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ShapelessRecipes
 * JD-Core Version:		0.6.0
 */