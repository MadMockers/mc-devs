/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import org.bukkit.Material;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftItemStack;
/*		 */ import org.bukkit.craftbukkit.inventory.CraftShapedRecipe;
/*		 */ import org.bukkit.inventory.ShapedRecipe;
/*		 */ 
/*		 */ public class ShapedRecipes
/*		 */	 implements IRecipe
/*		 */ {
/*		 */	 private int width;
/*		 */	 private int height;
/*		 */	 private ItemStack[] items;
/*		 */	 private ItemStack result;
/*		 */	 public final int a;
/*		 */ 
/*		 */	 public ShapedRecipes(int i, int j, ItemStack[] aitemstack, ItemStack itemstack)
/*		 */	 {
/*	17 */		 this.a = itemstack.id;
/*	18 */		 this.width = i;
/*	19 */		 this.height = j;
/*	20 */		 this.items = aitemstack;
/*	21 */		 this.result = itemstack;
/*		 */	 }
/*		 */ 
/*		 */	 public ShapedRecipe toBukkitRecipe()
/*		 */	 {
/*	26 */		 CraftItemStack result = new CraftItemStack(this.result);
/*	27 */		 CraftShapedRecipe recipe = new CraftShapedRecipe(result, this);
/*	28 */		 switch (this.height) {
/*		 */		 case 1:
/*	30 */			 switch (this.width) {
/*		 */			 case 1:
/*	32 */				 recipe.shape(new String[] { "a" });
/*	33 */				 break;
/*		 */			 case 2:
/*	35 */				 recipe.shape(new String[] { "ab" });
/*	36 */				 break;
/*		 */			 case 3:
/*	38 */				 recipe.shape(new String[] { "abc" });
/*		 */			 }
/*		 */ 
/*	41 */			 break;
/*		 */		 case 2:
/*	43 */			 switch (this.width) {
/*		 */			 case 1:
/*	45 */				 recipe.shape(new String[] { "a", "b" });
/*	46 */				 break;
/*		 */			 case 2:
/*	48 */				 recipe.shape(new String[] { "ab", "cd" });
/*	49 */				 break;
/*		 */			 case 3:
/*	51 */				 recipe.shape(new String[] { "abc", "def" });
/*		 */			 }
/*		 */ 
/*	54 */			 break;
/*		 */		 case 3:
/*	56 */			 switch (this.width) {
/*		 */			 case 1:
/*	58 */				 recipe.shape(new String[] { "a", "b", "c" });
/*	59 */				 break;
/*		 */			 case 2:
/*	61 */				 recipe.shape(new String[] { "ab", "cd", "ef" });
/*	62 */				 break;
/*		 */			 case 3:
/*	64 */				 recipe.shape(new String[] { "abc", "def", "ghi" });
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/*	69 */		 char c = 'a';
/*	70 */		 for (ItemStack stack : this.items) {
/*	71 */			 if (stack != null) {
/*	72 */				 recipe.setIngredient(c, Material.getMaterial(stack.id), stack.getData());
/*		 */			 }
/*	74 */			 c = (char)(c + '\001');
/*		 */		 }
/*	76 */		 return recipe;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b()
/*		 */	 {
/*	81 */		 return this.result;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(InventoryCrafting inventorycrafting) {
/*	85 */		 for (int i = 0; i <= 3 - this.width; i++) {
/*	86 */			 for (int j = 0; j <= 3 - this.height; j++) {
/*	87 */				 if (a(inventorycrafting, i, j, true)) {
/*	88 */					 return true;
/*		 */				 }
/*		 */ 
/*	91 */				 if (a(inventorycrafting, i, j, false)) {
/*	92 */					 return true;
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/*	97 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(InventoryCrafting inventorycrafting, int i, int j, boolean flag) {
/* 101 */		 for (int k = 0; k < 3; k++) {
/* 102 */			 for (int l = 0; l < 3; l++) {
/* 103 */				 int i1 = k - i;
/* 104 */				 int j1 = l - j;
/* 105 */				 ItemStack itemstack = null;
/*		 */ 
/* 107 */				 if ((i1 >= 0) && (j1 >= 0) && (i1 < this.width) && (j1 < this.height)) {
/* 108 */					 if (flag)
/* 109 */						 itemstack = this.items[(this.width - i1 - 1 + j1 * this.width)];
/*		 */					 else {
/* 111 */						 itemstack = this.items[(i1 + j1 * this.width)];
/*		 */					 }
/*		 */				 }
/*		 */ 
/* 115 */				 ItemStack itemstack1 = inventorycrafting.b(k, l);
/*		 */ 
/* 117 */				 if ((itemstack1 != null) || (itemstack != null)) {
/* 118 */					 if (((itemstack1 == null) && (itemstack != null)) || ((itemstack1 != null) && (itemstack == null))) {
/* 119 */						 return false;
/*		 */					 }
/*		 */ 
/* 122 */					 if (itemstack.id != itemstack1.id) {
/* 123 */						 return false;
/*		 */					 }
/*		 */ 
/* 126 */					 if ((itemstack.getData() != -1) && (itemstack.getData() != itemstack1.getData())) {
/* 127 */						 return false;
/*		 */					 }
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 133 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(InventoryCrafting inventorycrafting) {
/* 137 */		 return new ItemStack(this.result.id, this.result.count, this.result.getData(), this.result.getEnchantments());
/*		 */	 }
/*		 */ 
/*		 */	 public int a() {
/* 141 */		 return this.width * this.height;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.ShapedRecipes
 * JD-Core Version:		0.6.0
 */