/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Comparator;
/*		 */ 
/*		 */ class RecipeSorter
/*		 */	 implements Comparator
/*		 */ {
/*		 */	 RecipeSorter(CraftingManager paramCraftingManager)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public int a(IRecipe paramIRecipe1, IRecipe paramIRecipe2)
/*		 */	 {
/* 547 */		 if (((paramIRecipe1 instanceof ShapelessRecipes)) && ((paramIRecipe2 instanceof ShapedRecipes))) {
/* 548 */			 return 1;
/*		 */		 }
/* 550 */		 if (((paramIRecipe2 instanceof ShapelessRecipes)) && ((paramIRecipe1 instanceof ShapedRecipes))) {
/* 551 */			 return -1;
/*		 */		 }
/*		 */ 
/* 554 */		 if (paramIRecipe2.a() < paramIRecipe1.a()) return -1;
/* 555 */		 if (paramIRecipe2.a() > paramIRecipe1.a()) return 1;
/* 556 */		 return 0;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.RecipeSorter
 * JD-Core Version:		0.6.0
 */