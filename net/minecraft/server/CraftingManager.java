/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.ArrayList;
/*		 */ import java.util.Collections;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Iterator;
/*		 */ import java.util.List;
/*		 */ import org.bukkit.craftbukkit.event.CraftEventFactory;
/*		 */ import org.bukkit.inventory.InventoryView;
/*		 */ 
/*		 */ public class CraftingManager
/*		 */ {
/*	13 */	 private static final CraftingManager a = new CraftingManager();
/*	14 */	 public List recipes = new ArrayList();
/*		 */	 public IRecipe lastRecipe;
/*		 */	 public InventoryView lastCraftView;
/*		 */ 
/*		 */	 public static final CraftingManager getInstance()
/*		 */	 {
/*	21 */		 return a;
/*		 */	 }
/*		 */ 
/*		 */	 public CraftingManager() {
/*	25 */		 new RecipesTools().a(this);
/*	26 */		 new RecipesWeapons().a(this);
/*	27 */		 new RecipeIngots().a(this);
/*	28 */		 new RecipesFood().a(this);
/*	29 */		 new RecipesCrafting().a(this);
/*	30 */		 new RecipesArmor().a(this);
/*	31 */		 new RecipesDyes().a(this);
/*	32 */		 registerShapedRecipe(new ItemStack(Item.PAPER, 3), new Object[] { "###", Character.valueOf('#'), Item.SUGAR_CANE });
/*	33 */		 registerShapelessRecipe(new ItemStack(Item.BOOK, 1), new Object[] { Item.PAPER, Item.PAPER, Item.PAPER, Item.LEATHER });
/*	34 */		 registerShapelessRecipe(new ItemStack(Item.BOOK_AND_QUILL, 1), new Object[] { Item.BOOK, new ItemStack(Item.INK_SACK, 1, 0), Item.FEATHER });
/*	35 */		 registerShapedRecipe(new ItemStack(Block.FENCE, 2), new Object[] { "###", "###", Character.valueOf('#'), Item.STICK });
/*	36 */		 registerShapedRecipe(new ItemStack(Block.NETHER_FENCE, 6), new Object[] { "###", "###", Character.valueOf('#'), Block.NETHER_BRICK });
/*	37 */		 registerShapedRecipe(new ItemStack(Block.FENCE_GATE, 1), new Object[] { "#W#", "#W#", Character.valueOf('#'), Item.STICK, Character.valueOf('W'), Block.WOOD });
/*	38 */		 registerShapedRecipe(new ItemStack(Block.JUKEBOX, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.DIAMOND });
/*	39 */		 registerShapedRecipe(new ItemStack(Block.NOTE_BLOCK, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.REDSTONE });
/*	40 */		 registerShapedRecipe(new ItemStack(Block.BOOKSHELF, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.BOOK });
/*	41 */		 registerShapedRecipe(new ItemStack(Block.SNOW_BLOCK, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.SNOW_BALL });
/*	42 */		 registerShapedRecipe(new ItemStack(Block.CLAY, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.CLAY_BALL });
/*	43 */		 registerShapedRecipe(new ItemStack(Block.BRICK, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.CLAY_BRICK });
/*	44 */		 registerShapedRecipe(new ItemStack(Block.GLOWSTONE, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.GLOWSTONE_DUST });
/*	45 */		 registerShapedRecipe(new ItemStack(Block.WOOL, 1), new Object[] { "##", "##", Character.valueOf('#'), Item.STRING });
/*	46 */		 registerShapedRecipe(new ItemStack(Block.TNT, 1), new Object[] { "X#X", "#X#", "X#X", Character.valueOf('X'), Item.SULPHUR, Character.valueOf('#'), Block.SAND });
/*	47 */		 registerShapedRecipe(new ItemStack(Block.STEP, 6, 3), new Object[] { "###", Character.valueOf('#'), Block.COBBLESTONE });
/*	48 */		 registerShapedRecipe(new ItemStack(Block.STEP, 6, 0), new Object[] { "###", Character.valueOf('#'), Block.STONE });
/*	49 */		 registerShapedRecipe(new ItemStack(Block.STEP, 6, 1), new Object[] { "###", Character.valueOf('#'), Block.SANDSTONE });
/*	50 */		 registerShapedRecipe(new ItemStack(Block.STEP, 6, 4), new Object[] { "###", Character.valueOf('#'), Block.BRICK });
/*	51 */		 registerShapedRecipe(new ItemStack(Block.STEP, 6, 5), new Object[] { "###", Character.valueOf('#'), Block.SMOOTH_BRICK });
/*	52 */		 registerShapedRecipe(new ItemStack(Block.WOOD_STEP, 6, 0), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 0) });
/*	53 */		 registerShapedRecipe(new ItemStack(Block.WOOD_STEP, 6, 2), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 2) });
/*	54 */		 registerShapedRecipe(new ItemStack(Block.WOOD_STEP, 6, 1), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 1) });
/*	55 */		 registerShapedRecipe(new ItemStack(Block.WOOD_STEP, 6, 3), new Object[] { "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 3) });
/*	56 */		 registerShapedRecipe(new ItemStack(Block.LADDER, 3), new Object[] { "# #", "###", "# #", Character.valueOf('#'), Item.STICK });
/*	57 */		 registerShapedRecipe(new ItemStack(Item.WOOD_DOOR, 1), new Object[] { "##", "##", "##", Character.valueOf('#'), Block.WOOD });
/*	58 */		 registerShapedRecipe(new ItemStack(Block.TRAP_DOOR, 2), new Object[] { "###", "###", Character.valueOf('#'), Block.WOOD });
/*	59 */		 registerShapedRecipe(new ItemStack(Item.IRON_DOOR, 1), new Object[] { "##", "##", "##", Character.valueOf('#'), Item.IRON_INGOT });
/*	60 */		 registerShapedRecipe(new ItemStack(Item.SIGN, 3), new Object[] { "###", "###", " X ", Character.valueOf('#'), Block.WOOD, Character.valueOf('X'), Item.STICK });
/*	61 */		 registerShapedRecipe(new ItemStack(Item.CAKE, 1), new Object[] { "AAA", "BEB", "CCC", Character.valueOf('A'), Item.MILK_BUCKET, Character.valueOf('B'), Item.SUGAR, Character.valueOf('C'), Item.WHEAT, Character.valueOf('E'), Item.EGG });
/*	62 */		 registerShapedRecipe(new ItemStack(Item.SUGAR, 1), new Object[] { "#", Character.valueOf('#'), Item.SUGAR_CANE });
/*	63 */		 registerShapedRecipe(new ItemStack(Block.WOOD, 4, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.LOG, 1, 0) });
/*	64 */		 registerShapedRecipe(new ItemStack(Block.WOOD, 4, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.LOG, 1, 1) });
/*	65 */		 registerShapedRecipe(new ItemStack(Block.WOOD, 4, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.LOG, 1, 2) });
/*	66 */		 registerShapedRecipe(new ItemStack(Block.WOOD, 4, 3), new Object[] { "#", Character.valueOf('#'), new ItemStack(Block.LOG, 1, 3) });
/*	67 */		 registerShapedRecipe(new ItemStack(Item.STICK, 4), new Object[] { "#", "#", Character.valueOf('#'), Block.WOOD });
/*	68 */		 registerShapedRecipe(new ItemStack(Block.TORCH, 4), new Object[] { "X", "#", Character.valueOf('X'), Item.COAL, Character.valueOf('#'), Item.STICK });
/*	69 */		 registerShapedRecipe(new ItemStack(Block.TORCH, 4), new Object[] { "X", "#", Character.valueOf('X'), new ItemStack(Item.COAL, 1, 1), Character.valueOf('#'), Item.STICK });
/*	70 */		 registerShapedRecipe(new ItemStack(Item.BOWL, 4), new Object[] { "# #", " # ", Character.valueOf('#'), Block.WOOD });
/*	71 */		 registerShapedRecipe(new ItemStack(Item.GLASS_BOTTLE, 3), new Object[] { "# #", " # ", Character.valueOf('#'), Block.GLASS });
/*	72 */		 registerShapedRecipe(new ItemStack(Block.RAILS, 16), new Object[] { "X X", "X#X", "X X", Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('#'), Item.STICK });
/*	73 */		 registerShapedRecipe(new ItemStack(Block.GOLDEN_RAIL, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Item.GOLD_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('#'), Item.STICK });
/*	74 */		 registerShapedRecipe(new ItemStack(Block.DETECTOR_RAIL, 6), new Object[] { "X X", "X#X", "XRX", Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('#'), Block.STONE_PLATE });
/*	75 */		 registerShapedRecipe(new ItemStack(Item.MINECART, 1), new Object[] { "# #", "###", Character.valueOf('#'), Item.IRON_INGOT });
/*	76 */		 registerShapedRecipe(new ItemStack(Item.CAULDRON, 1), new Object[] { "# #", "# #", "###", Character.valueOf('#'), Item.IRON_INGOT });
/*	77 */		 registerShapedRecipe(new ItemStack(Item.BREWING_STAND, 1), new Object[] { " B ", "###", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('B'), Item.BLAZE_ROD });
/*	78 */		 registerShapedRecipe(new ItemStack(Block.JACK_O_LANTERN, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.PUMPKIN, Character.valueOf('B'), Block.TORCH });
/*	79 */		 registerShapedRecipe(new ItemStack(Item.STORAGE_MINECART, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.CHEST, Character.valueOf('B'), Item.MINECART });
/*	80 */		 registerShapedRecipe(new ItemStack(Item.POWERED_MINECART, 1), new Object[] { "A", "B", Character.valueOf('A'), Block.FURNACE, Character.valueOf('B'), Item.MINECART });
/*	81 */		 registerShapedRecipe(new ItemStack(Item.BOAT, 1), new Object[] { "# #", "###", Character.valueOf('#'), Block.WOOD });
/*	82 */		 registerShapedRecipe(new ItemStack(Item.BUCKET, 1), new Object[] { "# #", " # ", Character.valueOf('#'), Item.IRON_INGOT });
/*	83 */		 registerShapedRecipe(new ItemStack(Item.FLINT_AND_STEEL, 1), new Object[] { "A ", " B", Character.valueOf('A'), Item.IRON_INGOT, Character.valueOf('B'), Item.FLINT });
/*	84 */		 registerShapedRecipe(new ItemStack(Item.BREAD, 1), new Object[] { "###", Character.valueOf('#'), Item.WHEAT });
/*	85 */		 registerShapedRecipe(new ItemStack(Block.WOOD_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 0) });
/*	86 */		 registerShapedRecipe(new ItemStack(Block.BIRCH_WOOD_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 2) });
/*	87 */		 registerShapedRecipe(new ItemStack(Block.SPRUCE_WOOD_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 1) });
/*	88 */		 registerShapedRecipe(new ItemStack(Block.JUNGLE_WOOD_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), new ItemStack(Block.WOOD, 1, 3) });
/*	89 */		 registerShapedRecipe(new ItemStack(Item.FISHING_ROD, 1), new Object[] { "	#", " #X", "# X", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Item.STRING });
/*	90 */		 registerShapedRecipe(new ItemStack(Block.COBBLESTONE_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), Block.COBBLESTONE });
/*	91 */		 registerShapedRecipe(new ItemStack(Block.BRICK_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), Block.BRICK });
/*	92 */		 registerShapedRecipe(new ItemStack(Block.STONE_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), Block.SMOOTH_BRICK });
/*	93 */		 registerShapedRecipe(new ItemStack(Block.NETHER_BRICK_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), Block.NETHER_BRICK });
/*	94 */		 registerShapedRecipe(new ItemStack(Block.SANDSTONE_STAIRS, 4), new Object[] { "#	", "## ", "###", Character.valueOf('#'), Block.SANDSTONE });
/*	95 */		 registerShapedRecipe(new ItemStack(Item.PAINTING, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Block.WOOL });
/*	96 */		 registerShapedRecipe(new ItemStack(Item.GOLDEN_APPLE, 1, 0), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.GOLD_NUGGET, Character.valueOf('X'), Item.APPLE });
/*	97 */		 registerShapedRecipe(new ItemStack(Item.GOLDEN_APPLE, 1, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Block.GOLD_BLOCK, Character.valueOf('X'), Item.APPLE });
/*	98 */		 registerShapedRecipe(new ItemStack(Block.LEVER, 1), new Object[] { "X", "#", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('X'), Item.STICK });
/*	99 */		 registerShapedRecipe(new ItemStack(Block.TRIPWIRE_SOURCE, 2), new Object[] { "I", "S", "#", Character.valueOf('#'), Block.WOOD, Character.valueOf('S'), Item.STICK, Character.valueOf('I'), Item.IRON_INGOT });
/* 100 */		 registerShapedRecipe(new ItemStack(Block.REDSTONE_TORCH_ON, 1), new Object[] { "X", "#", Character.valueOf('#'), Item.STICK, Character.valueOf('X'), Item.REDSTONE });
/* 101 */		 registerShapedRecipe(new ItemStack(Item.DIODE, 1), new Object[] { "#X#", "III", Character.valueOf('#'), Block.REDSTONE_TORCH_ON, Character.valueOf('X'), Item.REDSTONE, Character.valueOf('I'), Block.STONE });
/* 102 */		 registerShapedRecipe(new ItemStack(Item.WATCH, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Item.GOLD_INGOT, Character.valueOf('X'), Item.REDSTONE });
/* 103 */		 registerShapedRecipe(new ItemStack(Item.COMPASS, 1), new Object[] { " # ", "#X#", " # ", Character.valueOf('#'), Item.IRON_INGOT, Character.valueOf('X'), Item.REDSTONE });
/* 104 */		 registerShapedRecipe(new ItemStack(Item.MAP, 1), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Item.PAPER, Character.valueOf('X'), Item.COMPASS });
/* 105 */		 registerShapedRecipe(new ItemStack(Block.STONE_BUTTON, 1), new Object[] { "#", "#", Character.valueOf('#'), Block.STONE });
/* 106 */		 registerShapedRecipe(new ItemStack(Block.STONE_PLATE, 1), new Object[] { "##", Character.valueOf('#'), Block.STONE });
/* 107 */		 registerShapedRecipe(new ItemStack(Block.WOOD_PLATE, 1), new Object[] { "##", Character.valueOf('#'), Block.WOOD });
/* 108 */		 registerShapedRecipe(new ItemStack(Block.DISPENSER, 1), new Object[] { "###", "#X#", "#R#", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('X'), Item.BOW, Character.valueOf('R'), Item.REDSTONE });
/* 109 */		 registerShapedRecipe(new ItemStack(Block.PISTON, 1), new Object[] { "TTT", "#X#", "#R#", Character.valueOf('#'), Block.COBBLESTONE, Character.valueOf('X'), Item.IRON_INGOT, Character.valueOf('R'), Item.REDSTONE, Character.valueOf('T'), Block.WOOD });
/* 110 */		 registerShapedRecipe(new ItemStack(Block.PISTON_STICKY, 1), new Object[] { "S", "P", Character.valueOf('S'), Item.SLIME_BALL, Character.valueOf('P'), Block.PISTON });
/* 111 */		 registerShapedRecipe(new ItemStack(Item.BED, 1), new Object[] { "###", "XXX", Character.valueOf('#'), Block.WOOL, Character.valueOf('X'), Block.WOOD });
/* 112 */		 registerShapedRecipe(new ItemStack(Block.ENCHANTMENT_TABLE, 1), new Object[] { " B ", "D#D", "###", Character.valueOf('#'), Block.OBSIDIAN, Character.valueOf('B'), Item.BOOK, Character.valueOf('D'), Item.DIAMOND });
/* 113 */		 registerShapelessRecipe(new ItemStack(Item.EYE_OF_ENDER, 1), new Object[] { Item.ENDER_PEARL, Item.BLAZE_POWDER });
/* 114 */		 registerShapelessRecipe(new ItemStack(Item.FIREBALL, 3), new Object[] { Item.SULPHUR, Item.BLAZE_POWDER, Item.COAL });
/* 115 */		 registerShapelessRecipe(new ItemStack(Item.FIREBALL, 3), new Object[] { Item.SULPHUR, Item.BLAZE_POWDER, new ItemStack(Item.COAL, 1, 1) });
/*		 */ 
/* 117 */		 sort();
/* 118 */		 System.out.println(this.recipes.size() + " recipes");
/*		 */	 }
/*		 */ 
/*		 */	 public void sort()
/*		 */	 {
/* 123 */		 Collections.sort(this.recipes, new RecipeSorter(this));
/*		 */	 }
/*		 */ 
/*		 */	 public void registerShapedRecipe(ItemStack itemstack, Object[] aobject)
/*		 */	 {
/* 128 */		 String s = "";
/* 129 */		 int i = 0;
/* 130 */		 int j = 0;
/* 131 */		 int k = 0;
/*		 */ 
/* 134 */		 if ((aobject[i] instanceof String[])) {
/* 135 */			 String[] astring = (String[])(String[])(String[])aobject[(i++)];
/* 136 */			 String[] astring1 = astring;
/*		 */ 
/* 138 */			 int l = astring.length;
/*		 */ 
/* 140 */			 for (int i1 = 0; i1 < l; i1++) {
/* 141 */				 String s1 = astring1[i1];
/*		 */ 
/* 143 */				 k++;
/* 144 */				 j = s1.length();
/* 145 */				 s = s + s1;
/*		 */			 }
/*		 */		 } else {
/* 148 */			 while ((aobject[i] instanceof String)) {
/* 149 */				 String s2 = (String)aobject[(i++)];
/*		 */ 
/* 151 */				 k++;
/* 152 */				 j = s2.length();
/* 153 */				 s = s + s2;
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 159 */		 for (HashMap hashmap = new HashMap(); i < aobject.length; i += 2) {
/* 160 */			 Character character = (Character)aobject[i];
/* 161 */			 ItemStack itemstack1 = null;
/*		 */ 
/* 163 */			 if ((aobject[(i + 1)] instanceof Item))
/* 164 */				 itemstack1 = new ItemStack((Item)aobject[(i + 1)]);
/* 165 */			 else if ((aobject[(i + 1)] instanceof Block))
/* 166 */				 itemstack1 = new ItemStack((Block)aobject[(i + 1)], 1, -1);
/* 167 */			 else if ((aobject[(i + 1)] instanceof ItemStack)) {
/* 168 */				 itemstack1 = (ItemStack)aobject[(i + 1)];
/*		 */			 }
/*		 */ 
/* 171 */			 hashmap.put(character, itemstack1);
/*		 */		 }
/*		 */ 
/* 174 */		 ItemStack[] aitemstack = new ItemStack[j * k];
/*		 */ 
/* 176 */		 for (int l = 0; l < j * k; l++) {
/* 177 */			 char c0 = s.charAt(l);
/*		 */ 
/* 179 */			 if (hashmap.containsKey(Character.valueOf(c0)))
/* 180 */				 aitemstack[l] = ((ItemStack)hashmap.get(Character.valueOf(c0))).cloneItemStack();
/*		 */			 else {
/* 182 */				 aitemstack[l] = null;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 186 */		 this.recipes.add(new ShapedRecipes(j, k, aitemstack, itemstack));
/*		 */	 }
/*		 */ 
/*		 */	 public void registerShapelessRecipe(ItemStack itemstack, Object[] aobject) {
/* 190 */		 ArrayList arraylist = new ArrayList();
/* 191 */		 Object[] aobject1 = aobject;
/* 192 */		 int i = aobject.length;
/*		 */ 
/* 194 */		 for (int j = 0; j < i; j++) {
/* 195 */			 Object object = aobject1[j];
/*		 */ 
/* 197 */			 if ((object instanceof ItemStack)) {
/* 198 */				 arraylist.add(((ItemStack)object).cloneItemStack());
/* 199 */			 } else if ((object instanceof Item)) {
/* 200 */				 arraylist.add(new ItemStack((Item)object));
/*		 */			 } else {
/* 202 */				 if (!(object instanceof Block)) {
/* 203 */					 throw new RuntimeException("Invalid shapeless recipy!");
/*		 */				 }
/*		 */ 
/* 206 */				 arraylist.add(new ItemStack((Block)object));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 210 */		 this.recipes.add(new ShapelessRecipes(itemstack, arraylist));
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack craft(InventoryCrafting inventorycrafting) {
/* 214 */		 int i = 0;
/* 215 */		 ItemStack itemstack = null;
/* 216 */		 ItemStack itemstack1 = null;
/*		 */ 
/* 218 */		 for (int j = 0; j < inventorycrafting.getSize(); j++) {
/* 219 */			 ItemStack itemstack2 = inventorycrafting.getItem(j);
/*		 */ 
/* 221 */			 if (itemstack2 != null) {
/* 222 */				 if (i == 0) {
/* 223 */					 itemstack = itemstack2;
/*		 */				 }
/*		 */ 
/* 226 */				 if (i == 1) {
/* 227 */					 itemstack1 = itemstack2;
/*		 */				 }
/*		 */ 
/* 230 */				 i++;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 234 */		 if ((i == 2) && (itemstack.id == itemstack1.id) && (itemstack.count == 1) && (itemstack1.count == 1) && (Item.byId[itemstack.id].m())) {
/* 235 */			 Item item = Item.byId[itemstack.id];
/* 236 */			 int k = item.getMaxDurability() - itemstack.i();
/* 237 */			 int l = item.getMaxDurability() - itemstack1.i();
/* 238 */			 int i1 = k + l + item.getMaxDurability() * 10 / 100;
/* 239 */			 int j1 = item.getMaxDurability() - i1;
/*		 */ 
/* 241 */			 if (j1 < 0) {
/* 242 */				 j1 = 0;
/*		 */			 }
/*		 */ 
/* 246 */			 ItemStack result = new ItemStack(itemstack.id, 1, j1);
/* 247 */			 List ingredients = new ArrayList();
/* 248 */			 ingredients.add(itemstack.cloneItemStack());
/* 249 */			 ingredients.add(itemstack1.cloneItemStack());
/* 250 */			 ShapelessRecipes recipe = new ShapelessRecipes(result.cloneItemStack(), ingredients);
/* 251 */			 inventorycrafting.currentRecipe = recipe;
/* 252 */			 result = CraftEventFactory.callPreCraftEvent(inventorycrafting, result, this.lastCraftView, true);
/* 253 */			 return result;
/*		 */		 }
/*		 */ 
/* 256 */		 Iterator iterator = this.recipes.iterator();
/*		 */		 IRecipe irecipe;
/*		 */		 do {
/* 261 */			 if (!iterator.hasNext()) {
/* 262 */				 return null;
/*		 */			 }
/*		 */ 
/* 265 */			 irecipe = (IRecipe)iterator.next();
/* 266 */		 }while (!irecipe.a(inventorycrafting));
/*		 */ 
/* 269 */		 inventorycrafting.currentRecipe = irecipe;
/* 270 */		 ItemStack result = irecipe.b(inventorycrafting);
/* 271 */		 return CraftEventFactory.callPreCraftEvent(inventorycrafting, result, this.lastCraftView, false);
/*		 */	 }
/*		 */ 
/*		 */	 public List getRecipes()
/*		 */	 {
/* 277 */		 return this.recipes;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.CraftingManager
 * JD-Core Version:		0.6.0
 */