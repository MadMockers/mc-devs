/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.io.PrintStream;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class Item
/*		 */ {
/*	25 */	 private CreativeModeTab a = null;
/*		 */ 
/*	73 */	 protected static Random d = new Random();
/*		 */ 
/*	75 */	 public static Item[] byId = new Item[32000];
/*		 */ 
/*	77 */	 public static Item IRON_SPADE = new ItemSpade(0, EnumToolMaterial.IRON).b(2, 5).b("shovelIron");
/*	78 */	 public static Item IRON_PICKAXE = new ItemPickaxe(1, EnumToolMaterial.IRON).b(2, 6).b("pickaxeIron");
/*	79 */	 public static Item IRON_AXE = new ItemAxe(2, EnumToolMaterial.IRON).b(2, 7).b("hatchetIron");
/*	80 */	 public static Item FLINT_AND_STEEL = new ItemFlintAndSteel(3).b(5, 0).b("flintAndSteel");
/*	81 */	 public static Item APPLE = new ItemFood(4, 4, 0.3F, false).b(10, 0).b("apple");
/*	82 */	 public static Item BOW = new ItemBow(5).b(5, 1).b("bow");
/*	83 */	 public static Item ARROW = new Item(6).b(5, 2).b("arrow").a(CreativeModeTab.j);
/*	84 */	 public static Item COAL = new ItemCoal(7).b(7, 0).b("coal");
/*	85 */	 public static Item DIAMOND = new Item(8).b(7, 3).b("diamond").a(CreativeModeTab.l);
/*	86 */	 public static Item IRON_INGOT = new Item(9).b(7, 1).b("ingotIron").a(CreativeModeTab.l);
/*	87 */	 public static Item GOLD_INGOT = new Item(10).b(7, 2).b("ingotGold").a(CreativeModeTab.l);
/*	88 */	 public static Item IRON_SWORD = new ItemSword(11, EnumToolMaterial.IRON).b(2, 4).b("swordIron");
/*		 */ 
/*	90 */	 public static Item WOOD_SWORD = new ItemSword(12, EnumToolMaterial.WOOD).b(0, 4).b("swordWood");
/*	91 */	 public static Item WOOD_SPADE = new ItemSpade(13, EnumToolMaterial.WOOD).b(0, 5).b("shovelWood");
/*	92 */	 public static Item WOOD_PICKAXE = new ItemPickaxe(14, EnumToolMaterial.WOOD).b(0, 6).b("pickaxeWood");
/*	93 */	 public static Item WOOD_AXE = new ItemAxe(15, EnumToolMaterial.WOOD).b(0, 7).b("hatchetWood");
/*		 */ 
/*	95 */	 public static Item STONE_SWORD = new ItemSword(16, EnumToolMaterial.STONE).b(1, 4).b("swordStone");
/*	96 */	 public static Item STONE_SPADE = new ItemSpade(17, EnumToolMaterial.STONE).b(1, 5).b("shovelStone");
/*	97 */	 public static Item STONE_PICKAXE = new ItemPickaxe(18, EnumToolMaterial.STONE).b(1, 6).b("pickaxeStone");
/*	98 */	 public static Item STONE_AXE = new ItemAxe(19, EnumToolMaterial.STONE).b(1, 7).b("hatchetStone");
/*		 */ 
/* 100 */	 public static Item DIAMOND_SWORD = new ItemSword(20, EnumToolMaterial.DIAMOND).b(3, 4).b("swordDiamond");
/* 101 */	 public static Item DIAMOND_SPADE = new ItemSpade(21, EnumToolMaterial.DIAMOND).b(3, 5).b("shovelDiamond");
/* 102 */	 public static Item DIAMOND_PICKAXE = new ItemPickaxe(22, EnumToolMaterial.DIAMOND).b(3, 6).b("pickaxeDiamond");
/* 103 */	 public static Item DIAMOND_AXE = new ItemAxe(23, EnumToolMaterial.DIAMOND).b(3, 7).b("hatchetDiamond");
/*		 */ 
/* 105 */	 public static Item STICK = new Item(24).b(5, 3).n().b("stick").a(CreativeModeTab.l);
/* 106 */	 public static Item BOWL = new Item(25).b(7, 4).b("bowl").a(CreativeModeTab.l);
/* 107 */	 public static Item MUSHROOM_SOUP = new ItemSoup(26, 8).b(8, 4).b("mushroomStew");
/*		 */ 
/* 109 */	 public static Item GOLD_SWORD = new ItemSword(27, EnumToolMaterial.GOLD).b(4, 4).b("swordGold");
/* 110 */	 public static Item GOLD_SPADE = new ItemSpade(28, EnumToolMaterial.GOLD).b(4, 5).b("shovelGold");
/* 111 */	 public static Item GOLD_PICKAXE = new ItemPickaxe(29, EnumToolMaterial.GOLD).b(4, 6).b("pickaxeGold");
/* 112 */	 public static Item GOLD_AXE = new ItemAxe(30, EnumToolMaterial.GOLD).b(4, 7).b("hatchetGold");
/*		 */ 
/* 114 */	 public static Item STRING = new ItemReed(31, Block.TRIPWIRE).b(8, 0).b("string").a(CreativeModeTab.l);
/* 115 */	 public static Item FEATHER = new Item(32).b(8, 1).b("feather").a(CreativeModeTab.l);
/* 116 */	 public static Item SULPHUR = new Item(33).b(8, 2).b("sulphur").c(PotionBrewer.k).a(CreativeModeTab.l);
/*		 */ 
/* 118 */	 public static Item WOOD_HOE = new ItemHoe(34, EnumToolMaterial.WOOD).b(0, 8).b("hoeWood");
/* 119 */	 public static Item STONE_HOE = new ItemHoe(35, EnumToolMaterial.STONE).b(1, 8).b("hoeStone");
/* 120 */	 public static Item IRON_HOE = new ItemHoe(36, EnumToolMaterial.IRON).b(2, 8).b("hoeIron");
/* 121 */	 public static Item DIAMOND_HOE = new ItemHoe(37, EnumToolMaterial.DIAMOND).b(3, 8).b("hoeDiamond");
/* 122 */	 public static Item GOLD_HOE = new ItemHoe(38, EnumToolMaterial.GOLD).b(4, 8).b("hoeGold");
/*		 */ 
/* 124 */	 public static Item SEEDS = new ItemSeeds(39, Block.CROPS.id, Block.SOIL.id).b(9, 0).b("seeds");
/* 125 */	 public static Item WHEAT = new Item(40).b(9, 1).b("wheat").a(CreativeModeTab.l);
/* 126 */	 public static Item BREAD = new ItemFood(41, 5, 0.6F, false).b(9, 2).b("bread");
/*		 */ 
/* 128 */	 public static Item LEATHER_HELMET = new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0).b(0, 0).b("helmetCloth");
/* 129 */	 public static Item LEATHER_CHESTPLATE = new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1).b(0, 1).b("chestplateCloth");
/* 130 */	 public static Item LEATHER_LEGGINGS = new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2).b(0, 2).b("leggingsCloth");
/* 131 */	 public static Item LEATHER_BOOTS = new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3).b(0, 3).b("bootsCloth");
/*		 */ 
/* 133 */	 public static Item CHAINMAIL_HELMET = new ItemArmor(46, EnumArmorMaterial.CHAIN, 1, 0).b(1, 0).b("helmetChain");
/* 134 */	 public static Item CHAINMAIL_CHESTPLATE = new ItemArmor(47, EnumArmorMaterial.CHAIN, 1, 1).b(1, 1).b("chestplateChain");
/* 135 */	 public static Item CHAINMAIL_LEGGINGS = new ItemArmor(48, EnumArmorMaterial.CHAIN, 1, 2).b(1, 2).b("leggingsChain");
/* 136 */	 public static Item CHAINMAIL_BOOTS = new ItemArmor(49, EnumArmorMaterial.CHAIN, 1, 3).b(1, 3).b("bootsChain");
/*		 */ 
/* 138 */	 public static Item IRON_HELMET = new ItemArmor(50, EnumArmorMaterial.IRON, 2, 0).b(2, 0).b("helmetIron");
/* 139 */	 public static Item IRON_CHESTPLATE = new ItemArmor(51, EnumArmorMaterial.IRON, 2, 1).b(2, 1).b("chestplateIron");
/* 140 */	 public static Item IRON_LEGGINGS = new ItemArmor(52, EnumArmorMaterial.IRON, 2, 2).b(2, 2).b("leggingsIron");
/* 141 */	 public static Item IRON_BOOTS = new ItemArmor(53, EnumArmorMaterial.IRON, 2, 3).b(2, 3).b("bootsIron");
/*		 */ 
/* 143 */	 public static Item DIAMOND_HELMET = new ItemArmor(54, EnumArmorMaterial.DIAMOND, 3, 0).b(3, 0).b("helmetDiamond");
/* 144 */	 public static Item DIAMOND_CHESTPLATE = new ItemArmor(55, EnumArmorMaterial.DIAMOND, 3, 1).b(3, 1).b("chestplateDiamond");
/* 145 */	 public static Item DIAMOND_LEGGINGS = new ItemArmor(56, EnumArmorMaterial.DIAMOND, 3, 2).b(3, 2).b("leggingsDiamond");
/* 146 */	 public static Item DIAMOND_BOOTS = new ItemArmor(57, EnumArmorMaterial.DIAMOND, 3, 3).b(3, 3).b("bootsDiamond");
/*		 */ 
/* 148 */	 public static Item GOLD_HELMET = new ItemArmor(58, EnumArmorMaterial.GOLD, 4, 0).b(4, 0).b("helmetGold");
/* 149 */	 public static Item GOLD_CHESTPLATE = new ItemArmor(59, EnumArmorMaterial.GOLD, 4, 1).b(4, 1).b("chestplateGold");
/* 150 */	 public static Item GOLD_LEGGINGS = new ItemArmor(60, EnumArmorMaterial.GOLD, 4, 2).b(4, 2).b("leggingsGold");
/* 151 */	 public static Item GOLD_BOOTS = new ItemArmor(61, EnumArmorMaterial.GOLD, 4, 3).b(4, 3).b("bootsGold");
/*		 */ 
/* 153 */	 public static Item FLINT = new Item(62).b(6, 0).b("flint").a(CreativeModeTab.l);
/* 154 */	 public static Item PORK = new ItemFood(63, 3, 0.3F, true).b(7, 5).b("porkchopRaw");
/* 155 */	 public static Item GRILLED_PORK = new ItemFood(64, 8, 0.8F, true).b(8, 5).b("porkchopCooked");
/* 156 */	 public static Item PAINTING = new ItemPainting(65).b(10, 1).b("painting");
/*		 */ 
/* 158 */	 public static Item GOLDEN_APPLE = new ItemGoldenApple(66, 4, 1.2F, false).i().a(MobEffectList.REGENERATION.id, 5, 0, 1.0F).b(11, 0).b("appleGold");
/*		 */ 
/* 161 */	 public static Item SIGN = new ItemSign(67).b(10, 2).b("sign");
/* 162 */	 public static Item WOOD_DOOR = new ItemDoor(68, Material.WOOD).b(11, 2).b("doorWood");
/*		 */ 
/* 164 */	 public static Item BUCKET = new ItemBucket(69, 0).b(10, 4).b("bucket").d(16);
/* 165 */	 public static Item WATER_BUCKET = new ItemBucket(70, Block.WATER.id).b(11, 4).b("bucketWater").a(BUCKET);
/* 166 */	 public static Item LAVA_BUCKET = new ItemBucket(71, Block.LAVA.id).b(12, 4).b("bucketLava").a(BUCKET);
/*		 */ 
/* 168 */	 public static Item MINECART = new ItemMinecart(72, 0).b(7, 8).b("minecart");
/* 169 */	 public static Item SADDLE = new ItemSaddle(73).b(8, 6).b("saddle");
/* 170 */	 public static Item IRON_DOOR = new ItemDoor(74, Material.ORE).b(12, 2).b("doorIron");
/* 171 */	 public static Item REDSTONE = new ItemRedstone(75).b(8, 3).b("redstone").c(PotionBrewer.i);
/* 172 */	 public static Item SNOW_BALL = new ItemSnowball(76).b(14, 0).b("snowball");
/*		 */ 
/* 174 */	 public static Item BOAT = new ItemBoat(77).b(8, 8).b("boat");
/*		 */ 
/* 176 */	 public static Item LEATHER = new Item(78).b(7, 6).b("leather").a(CreativeModeTab.l);
/* 177 */	 public static Item MILK_BUCKET = new ItemMilkBucket(79).b(13, 4).b("milk").a(BUCKET);
/* 178 */	 public static Item CLAY_BRICK = new Item(80).b(6, 1).b("brick").a(CreativeModeTab.l);
/* 179 */	 public static Item CLAY_BALL = new Item(81).b(9, 3).b("clay").a(CreativeModeTab.l);
/* 180 */	 public static Item SUGAR_CANE = new ItemReed(82, Block.SUGAR_CANE_BLOCK).b(11, 1).b("reeds").a(CreativeModeTab.l);
/* 181 */	 public static Item PAPER = new Item(83).b(10, 3).b("paper").a(CreativeModeTab.f);
/* 182 */	 public static Item BOOK = new Item(84).b(11, 3).b("book").a(CreativeModeTab.f);
/* 183 */	 public static Item SLIME_BALL = new Item(85).b(14, 1).b("slimeball").a(CreativeModeTab.f);
/* 184 */	 public static Item STORAGE_MINECART = new ItemMinecart(86, 1).b(7, 9).b("minecartChest");
/* 185 */	 public static Item POWERED_MINECART = new ItemMinecart(87, 2).b(7, 10).b("minecartFurnace");
/* 186 */	 public static Item EGG = new ItemEgg(88).b(12, 0).b("egg");
/* 187 */	 public static Item COMPASS = new Item(89).b(6, 3).b("compass").a(CreativeModeTab.i);
/* 188 */	 public static Item FISHING_ROD = new ItemFishingRod(90).b(5, 4).b("fishingRod");
/* 189 */	 public static Item WATCH = new Item(91).b(6, 4).b("clock").a(CreativeModeTab.i);
/* 190 */	 public static Item GLOWSTONE_DUST = new Item(92).b(9, 4).b("yellowDust").c(PotionBrewer.j).a(CreativeModeTab.l);
/* 191 */	 public static Item RAW_FISH = new ItemFood(93, 2, 0.3F, false).b(9, 5).b("fishRaw");
/* 192 */	 public static Item COOKED_FISH = new ItemFood(94, 5, 0.6F, false).b(10, 5).b("fishCooked");
/*		 */ 
/* 194 */	 public static Item INK_SACK = new ItemDye(95).b(14, 4).b("dyePowder");
/* 195 */	 public static Item BONE = new Item(96).b(12, 1).b("bone").n().a(CreativeModeTab.f);
/* 196 */	 public static Item SUGAR = new Item(97).b(13, 0).b("sugar").c(PotionBrewer.b).a(CreativeModeTab.l);
/* 197 */	 public static Item CAKE = new ItemReed(98, Block.CAKE_BLOCK).d(1).b(13, 1).b("cake").a(CreativeModeTab.h);
/*		 */ 
/* 199 */	 public static Item BED = new ItemBed(99).d(1).b(13, 2).b("bed");
/*		 */ 
/* 201 */	 public static Item DIODE = new ItemReed(100, Block.DIODE_OFF).b(6, 5).b("diode").a(CreativeModeTab.d);
/* 202 */	 public static Item COOKIE = new ItemFood(101, 2, 0.1F, false).b(12, 5).b("cookie");
/*		 */ 
/* 204 */	 public static ItemWorldMap MAP = (ItemWorldMap)new ItemWorldMap(102).b(12, 3).b("map");
/*		 */ 
/* 206 */	 public static ItemShears SHEARS = (ItemShears)new ItemShears(103).b(13, 5).b("shears");
/* 207 */	 public static Item MELON = new ItemFood(104, 2, 0.3F, false).b(13, 6).b("melon");
/*		 */ 
/* 209 */	 public static Item PUMPKIN_SEEDS = new ItemSeeds(105, Block.PUMPKIN_STEM.id, Block.SOIL.id).b(13, 3).b("seeds_pumpkin");
/* 210 */	 public static Item MELON_SEEDS = new ItemSeeds(106, Block.MELON_STEM.id, Block.SOIL.id).b(14, 3).b("seeds_melon");
/*		 */ 
/* 212 */	 public static Item RAW_BEEF = new ItemFood(107, 3, 0.3F, true).b(9, 6).b("beefRaw");
/* 213 */	 public static Item COOKED_BEEF = new ItemFood(108, 8, 0.8F, true).b(10, 6).b("beefCooked");
/* 214 */	 public static Item RAW_CHICKEN = new ItemFood(109, 2, 0.3F, true).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).b(9, 7).b("chickenRaw");
/* 215 */	 public static Item COOKED_CHICKEN = new ItemFood(110, 6, 0.6F, true).b(10, 7).b("chickenCooked");
/* 216 */	 public static Item ROTTEN_FLESH = new ItemFood(111, 4, 0.1F, true).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).b(11, 5).b("rottenFlesh");
/*		 */ 
/* 218 */	 public static Item ENDER_PEARL = new ItemEnderPearl(112).b(11, 6).b("enderPearl");
/* 219 */	 public static Item BLAZE_ROD = new Item(113).b(12, 6).b("blazeRod").a(CreativeModeTab.l);
/* 220 */	 public static Item GHAST_TEAR = new Item(114).b(11, 7).b("ghastTear").c(PotionBrewer.c).a(CreativeModeTab.k);
/* 221 */	 public static Item GOLD_NUGGET = new Item(115).b(12, 7).b("goldNugget").a(CreativeModeTab.l);
/*		 */ 
/* 223 */	 public static Item NETHER_STALK = new ItemSeeds(116, Block.NETHER_WART.id, Block.SOUL_SAND.id).b(13, 7).b("netherStalkSeeds").c("+4");
/*		 */ 
/* 226 */	 public static ItemPotion POTION = (ItemPotion)new ItemPotion(117).b(13, 8).b("potion");
/* 227 */	 public static Item GLASS_BOTTLE = new ItemGlassBottle(118).b(12, 8).b("glassBottle");
/*		 */ 
/* 229 */	 public static Item SPIDER_EYE = new ItemFood(119, 2, 0.8F, false).a(MobEffectList.POISON.id, 5, 0, 1.0F).b(11, 8).b("spiderEye").c(PotionBrewer.d);
/*		 */ 
/* 231 */	 public static Item FERMENTED_SPIDER_EYE = new Item(120).b(10, 8).b("fermentedSpiderEye").c(PotionBrewer.e).a(CreativeModeTab.k);
/*		 */ 
/* 233 */	 public static Item BLAZE_POWDER = new Item(121).b(13, 9).b("blazePowder").c(PotionBrewer.g).a(CreativeModeTab.k);
/* 234 */	 public static Item MAGMA_CREAM = new Item(122).b(13, 10).b("magmaCream").c(PotionBrewer.h).a(CreativeModeTab.k);
/*		 */ 
/* 236 */	 public static Item BREWING_STAND = new ItemReed(123, Block.BREWING_STAND).b(12, 10).b("brewingStand").a(CreativeModeTab.k);
/* 237 */	 public static Item CAULDRON = new ItemReed(124, Block.CAULDRON).b(12, 9).b("cauldron").a(CreativeModeTab.k);
/* 238 */	 public static Item EYE_OF_ENDER = new ItemEnderEye(125).b(11, 9).b("eyeOfEnder");
/* 239 */	 public static Item SPECKLED_MELON = new Item(126).b(9, 8).b("speckledMelon").c(PotionBrewer.f).a(CreativeModeTab.k);
/*		 */ 
/* 241 */	 public static Item MONSTER_EGG = new ItemMonsterEgg(127).b(9, 9).b("monsterPlacer");
/* 242 */	 public static Item EXP_BOTTLE = new ItemExpBottle(128).b(11, 10).b("expBottle");
/* 243 */	 public static Item FIREBALL = new ItemFireball(129).b(14, 2).b("fireball");
/*		 */ 
/* 245 */	 public static Item BOOK_AND_QUILL = new ItemBookAndQuill(130).b(11, 11).b("writingBook").a(CreativeModeTab.f);
/* 246 */	 public static Item WRITTEN_BOOK = new ItemWrittenBook(131).b(12, 11).b("writtenBook");
/*		 */ 
/* 248 */	 public static Item EMERALD = new Item(132).b(10, 11).b("emerald").a(CreativeModeTab.l);
/*		 */ 
/* 250 */	 public static Item RECORD_1 = new ItemRecord(2000, "13").b(0, 15).b("record");
/* 251 */	 public static Item RECORD_2 = new ItemRecord(2001, "cat").b(1, 15).b("record");
/* 252 */	 public static Item RECORD_3 = new ItemRecord(2002, "blocks").b(2, 15).b("record");
/* 253 */	 public static Item RECORD_4 = new ItemRecord(2003, "chirp").b(3, 15).b("record");
/* 254 */	 public static Item RECORD_5 = new ItemRecord(2004, "far").b(4, 15).b("record");
/* 255 */	 public static Item RECORD_6 = new ItemRecord(2005, "mall").b(5, 15).b("record");
/* 256 */	 public static Item RECORD_7 = new ItemRecord(2006, "mellohi").b(6, 15).b("record");
/* 257 */	 public static Item RECORD_8 = new ItemRecord(2007, "stal").b(7, 15).b("record");
/* 258 */	 public static Item RECORD_9 = new ItemRecord(2008, "strad").b(8, 15).b("record");
/* 259 */	 public static Item RECORD_10 = new ItemRecord(2009, "ward").b(9, 15).b("record");
/* 260 */	 public static Item RECORD_11 = new ItemRecord(2010, "11").b(10, 15).b("record");
/*		 */	 public final int id;
/* 267 */	 protected int maxStackSize = 64;
/* 268 */	 private int durability = 0;
/*		 */	 protected int textureId;
/* 271 */	 protected boolean bW = false;
/* 272 */	 protected boolean bX = false;
/*		 */ 
/* 274 */	 private Item craftingResult = null;
/* 275 */	 private String bY = null;
/*		 */	 private String name;
/*		 */ 
/*		 */	 protected Item(int paramInt)
/*		 */	 {
/* 280 */		 this.id = (256 + paramInt);
/* 281 */		 if (byId[(256 + paramInt)] != null) {
/* 282 */			 System.out.println("CONFLICT @ " + paramInt);
/*		 */		 }
/*		 */ 
/* 285 */		 byId[(256 + paramInt)] = this;
/*		 */	 }
/*		 */ 
/*		 */	 public Item c(int paramInt) {
/* 289 */		 this.textureId = paramInt;
/* 290 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public Item d(int paramInt) {
/* 294 */		 this.maxStackSize = paramInt;
/* 295 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public Item b(int paramInt1, int paramInt2) {
/* 299 */		 this.textureId = (paramInt1 + paramInt2 * 16);
/* 300 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
/*		 */	 {
/* 316 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock) {
/* 320 */		 return 1.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
/* 324 */		 return paramItemStack;
/*		 */	 }
/*		 */ 
/*		 */	 public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
/* 328 */		 return paramItemStack;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxStackSize() {
/* 332 */		 return this.maxStackSize;
/*		 */	 }
/*		 */ 
/*		 */	 public int filterData(int paramInt) {
/* 336 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k() {
/* 340 */		 return this.bX;
/*		 */	 }
/*		 */ 
/*		 */	 protected Item a(boolean paramBoolean) {
/* 344 */		 this.bX = paramBoolean;
/* 345 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxDurability() {
/* 349 */		 return this.durability;
/*		 */	 }
/*		 */ 
/*		 */	 protected Item setMaxDurability(int paramInt) {
/* 353 */		 this.durability = paramInt;
/* 354 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean m() {
/* 358 */		 return (this.durability > 0) && (!this.bX);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
/*		 */	 {
/* 370 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityLiving paramEntityLiving)
/*		 */	 {
/* 385 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Entity paramEntity) {
/* 389 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canDestroySpecialBlock(Block paramBlock) {
/* 393 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving) {
/* 397 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public Item n() {
/* 401 */		 this.bW = true;
/* 402 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public Item b(String paramString)
/*		 */	 {
/* 414 */		 this.name = ("item." + paramString);
/* 415 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName()
/*		 */	 {
/* 429 */		 return this.name;
/*		 */	 }
/*		 */ 
/*		 */	 public String c(ItemStack paramItemStack) {
/* 433 */		 return this.name;
/*		 */	 }
/*		 */ 
/*		 */	 public Item a(Item paramItem) {
/* 437 */		 this.craftingResult = paramItem;
/* 438 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean h(ItemStack paramItemStack)
/*		 */	 {
/* 443 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean p() {
/* 447 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public Item q() {
/* 451 */		 return this.craftingResult;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean r() {
/* 455 */		 return this.craftingResult != null;
/*		 */	 }
/*		 */ 
/*		 */	 public String s() {
/* 459 */		 return LocaleI18n.get(getName() + ".name");
/*		 */	 }
/*		 */ 
/*		 */	 public String i(ItemStack paramItemStack) {
/* 463 */		 return LocaleI18n.get(c(paramItemStack) + ".name");
/*		 */	 }
/*		 */ 
/*		 */	 public void a(ItemStack paramItemStack, World paramWorld, Entity paramEntity, int paramInt, boolean paramBoolean)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void d(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean m_()
/*		 */	 {
/* 477 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public EnumAnimation b(ItemStack paramItemStack) {
/* 481 */		 return EnumAnimation.a;
/*		 */	 }
/*		 */ 
/*		 */	 public int a(ItemStack paramItemStack) {
/* 485 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman, int paramInt) {
/*		 */	 }
/*		 */ 
/*		 */	 protected Item c(String paramString) {
/* 492 */		 this.bY = paramString;
/* 493 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String t() {
/* 497 */		 return this.bY;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean u() {
/* 501 */		 return this.bY != null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k(ItemStack paramItemStack)
/*		 */	 {
/* 522 */		 return (getMaxStackSize() == 1) && (m());
/*		 */	 }
/*		 */ 
/*		 */	 protected MovingObjectPosition a(World paramWorld, EntityHuman paramEntityHuman, boolean paramBoolean) {
/* 526 */		 float f1 = 1.0F;
/*		 */ 
/* 528 */		 float f2 = paramEntityHuman.lastPitch + (paramEntityHuman.pitch - paramEntityHuman.lastPitch) * f1;
/* 529 */		 float f3 = paramEntityHuman.lastYaw + (paramEntityHuman.yaw - paramEntityHuman.lastYaw) * f1;
/*		 */ 
/* 531 */		 double d1 = paramEntityHuman.lastX + (paramEntityHuman.locX - paramEntityHuman.lastX) * f1;
/* 532 */		 double d2 = paramEntityHuman.lastY + (paramEntityHuman.locY - paramEntityHuman.lastY) * f1 + 1.62D - paramEntityHuman.height;
/* 533 */		 double d3 = paramEntityHuman.lastZ + (paramEntityHuman.locZ - paramEntityHuman.lastZ) * f1;
/*		 */ 
/* 535 */		 Vec3D localVec3D1 = Vec3D.a().create(d1, d2, d3);
/*		 */ 
/* 537 */		 float f4 = MathHelper.cos(-f3 * 0.01745329F - 3.141593F);
/* 538 */		 float f5 = MathHelper.sin(-f3 * 0.01745329F - 3.141593F);
/* 539 */		 float f6 = -MathHelper.cos(-f2 * 0.01745329F);
/* 540 */		 float f7 = MathHelper.sin(-f2 * 0.01745329F);
/*		 */ 
/* 542 */		 float f8 = f5 * f6;
/* 543 */		 float f9 = f7;
/* 544 */		 float f10 = f4 * f6;
/*		 */ 
/* 546 */		 double d4 = 5.0D;
/* 547 */		 Vec3D localVec3D2 = localVec3D1.add(f8 * d4, f9 * d4, f10 * d4);
/*		 */ 
/* 549 */		 return paramWorld.rayTrace(localVec3D1, localVec3D2, paramBoolean, !paramBoolean);
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/* 553 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public Item a(CreativeModeTab paramCreativeModeTab)
/*		 */	 {
/* 573 */		 this.a = paramCreativeModeTab;
/* 574 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/* 263 */		 StatisticList.c();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Item
 * JD-Core Version:		0.6.0
 */