package net.minecraft.server;

public class RecipeIngots
{
	private Object[][] a = { { Block.GOLD_BLOCK, new ItemStack(Item.GOLD_INGOT, 9) }, { Block.IRON_BLOCK, new ItemStack(Item.IRON_INGOT, 9) }, { Block.DIAMOND_BLOCK, new ItemStack(Item.DIAMOND, 9) }, { Block.EMERALD_BLOCK, new ItemStack(Item.EMERALD, 9) }, { Block.LAPIS_BLOCK, new ItemStack(Item.INK_SACK, 9, 4) } };

	public void a(CraftingManager paramCraftingManager)
	{
		for (Object[] arrayOfObject1 : this.a) {
			Block localBlock = (Block)arrayOfObject1[0];
			ItemStack localItemStack = (ItemStack)arrayOfObject1[1];
			paramCraftingManager.registerShapedRecipe(new ItemStack(localBlock), new Object[] { "###", "###", "###", Character.valueOf('#'), localItemStack });

			paramCraftingManager.registerShapedRecipe(localItemStack, new Object[] { "#", Character.valueOf('#'), localBlock });
		}

		paramCraftingManager.registerShapedRecipe(new ItemStack(Item.GOLD_INGOT), new Object[] { "###", "###", "###", Character.valueOf('#'), Item.GOLD_NUGGET });

		paramCraftingManager.registerShapedRecipe(new ItemStack(Item.GOLD_NUGGET, 9), new Object[] { "#", Character.valueOf('#'), Item.GOLD_INGOT });
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.RecipeIngots
 * JD-Core Version:		0.6.0
 */