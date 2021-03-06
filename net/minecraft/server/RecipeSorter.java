package net.minecraft.server;

import java.util.Comparator;

class RecipeSorter
	implements Comparator
{
	RecipeSorter(CraftingManager paramCraftingManager)
	{
	}

	public int a(IRecipe paramIRecipe1, IRecipe paramIRecipe2)
	{
		if (((paramIRecipe1 instanceof ShapelessRecipes)) && ((paramIRecipe2 instanceof ShapedRecipes))) {
			return 1;
		}
		if (((paramIRecipe2 instanceof ShapelessRecipes)) && ((paramIRecipe1 instanceof ShapedRecipes))) {
			return -1;
		}

		if (paramIRecipe2.a() < paramIRecipe1.a()) return -1;
		if (paramIRecipe2.a() > paramIRecipe1.a()) return 1;
		return 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.RecipeSorter
 * JD-Core Version:		0.6.0
 */