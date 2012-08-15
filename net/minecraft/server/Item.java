package net.minecraft.server;

import java.io.PrintStream;
import java.util.Random;

public class Item
{
	private CreativeModeTab a = null;

	protected static Random d = new Random();

	public static Item[] byId = new Item[32000];

	public static Item IRON_SPADE = new ItemSpade(0, EnumToolMaterial.IRON).b(2, 5).b("shovelIron");
	public static Item IRON_PICKAXE = new ItemPickaxe(1, EnumToolMaterial.IRON).b(2, 6).b("pickaxeIron");
	public static Item IRON_AXE = new ItemAxe(2, EnumToolMaterial.IRON).b(2, 7).b("hatchetIron");
	public static Item FLINT_AND_STEEL = new ItemFlintAndSteel(3).b(5, 0).b("flintAndSteel");
	public static Item APPLE = new ItemFood(4, 4, 0.3F, false).b(10, 0).b("apple");
	public static Item BOW = new ItemBow(5).b(5, 1).b("bow");
	public static Item ARROW = new Item(6).b(5, 2).b("arrow").a(CreativeModeTab.j);
	public static Item COAL = new ItemCoal(7).b(7, 0).b("coal");
	public static Item DIAMOND = new Item(8).b(7, 3).b("diamond").a(CreativeModeTab.l);
	public static Item IRON_INGOT = new Item(9).b(7, 1).b("ingotIron").a(CreativeModeTab.l);
	public static Item GOLD_INGOT = new Item(10).b(7, 2).b("ingotGold").a(CreativeModeTab.l);
	public static Item IRON_SWORD = new ItemSword(11, EnumToolMaterial.IRON).b(2, 4).b("swordIron");

	public static Item WOOD_SWORD = new ItemSword(12, EnumToolMaterial.WOOD).b(0, 4).b("swordWood");
	public static Item WOOD_SPADE = new ItemSpade(13, EnumToolMaterial.WOOD).b(0, 5).b("shovelWood");
	public static Item WOOD_PICKAXE = new ItemPickaxe(14, EnumToolMaterial.WOOD).b(0, 6).b("pickaxeWood");
	public static Item WOOD_AXE = new ItemAxe(15, EnumToolMaterial.WOOD).b(0, 7).b("hatchetWood");

	public static Item STONE_SWORD = new ItemSword(16, EnumToolMaterial.STONE).b(1, 4).b("swordStone");
	public static Item STONE_SPADE = new ItemSpade(17, EnumToolMaterial.STONE).b(1, 5).b("shovelStone");
	public static Item STONE_PICKAXE = new ItemPickaxe(18, EnumToolMaterial.STONE).b(1, 6).b("pickaxeStone");
	public static Item STONE_AXE = new ItemAxe(19, EnumToolMaterial.STONE).b(1, 7).b("hatchetStone");

	public static Item DIAMOND_SWORD = new ItemSword(20, EnumToolMaterial.DIAMOND).b(3, 4).b("swordDiamond");
	public static Item DIAMOND_SPADE = new ItemSpade(21, EnumToolMaterial.DIAMOND).b(3, 5).b("shovelDiamond");
	public static Item DIAMOND_PICKAXE = new ItemPickaxe(22, EnumToolMaterial.DIAMOND).b(3, 6).b("pickaxeDiamond");
	public static Item DIAMOND_AXE = new ItemAxe(23, EnumToolMaterial.DIAMOND).b(3, 7).b("hatchetDiamond");

	public static Item STICK = new Item(24).b(5, 3).n().b("stick").a(CreativeModeTab.l);
	public static Item BOWL = new Item(25).b(7, 4).b("bowl").a(CreativeModeTab.l);
	public static Item MUSHROOM_SOUP = new ItemSoup(26, 8).b(8, 4).b("mushroomStew");

	public static Item GOLD_SWORD = new ItemSword(27, EnumToolMaterial.GOLD).b(4, 4).b("swordGold");
	public static Item GOLD_SPADE = new ItemSpade(28, EnumToolMaterial.GOLD).b(4, 5).b("shovelGold");
	public static Item GOLD_PICKAXE = new ItemPickaxe(29, EnumToolMaterial.GOLD).b(4, 6).b("pickaxeGold");
	public static Item GOLD_AXE = new ItemAxe(30, EnumToolMaterial.GOLD).b(4, 7).b("hatchetGold");

	public static Item STRING = new ItemReed(31, Block.TRIPWIRE).b(8, 0).b("string").a(CreativeModeTab.l);
	public static Item FEATHER = new Item(32).b(8, 1).b("feather").a(CreativeModeTab.l);
	public static Item SULPHUR = new Item(33).b(8, 2).b("sulphur").c(PotionBrewer.k).a(CreativeModeTab.l);

	public static Item WOOD_HOE = new ItemHoe(34, EnumToolMaterial.WOOD).b(0, 8).b("hoeWood");
	public static Item STONE_HOE = new ItemHoe(35, EnumToolMaterial.STONE).b(1, 8).b("hoeStone");
	public static Item IRON_HOE = new ItemHoe(36, EnumToolMaterial.IRON).b(2, 8).b("hoeIron");
	public static Item DIAMOND_HOE = new ItemHoe(37, EnumToolMaterial.DIAMOND).b(3, 8).b("hoeDiamond");
	public static Item GOLD_HOE = new ItemHoe(38, EnumToolMaterial.GOLD).b(4, 8).b("hoeGold");

	public static Item SEEDS = new ItemSeeds(39, Block.CROPS.id, Block.SOIL.id).b(9, 0).b("seeds");
	public static Item WHEAT = new Item(40).b(9, 1).b("wheat").a(CreativeModeTab.l);
	public static Item BREAD = new ItemFood(41, 5, 0.6F, false).b(9, 2).b("bread");

	public static Item LEATHER_HELMET = new ItemArmor(42, EnumArmorMaterial.CLOTH, 0, 0).b(0, 0).b("helmetCloth");
	public static Item LEATHER_CHESTPLATE = new ItemArmor(43, EnumArmorMaterial.CLOTH, 0, 1).b(0, 1).b("chestplateCloth");
	public static Item LEATHER_LEGGINGS = new ItemArmor(44, EnumArmorMaterial.CLOTH, 0, 2).b(0, 2).b("leggingsCloth");
	public static Item LEATHER_BOOTS = new ItemArmor(45, EnumArmorMaterial.CLOTH, 0, 3).b(0, 3).b("bootsCloth");

	public static Item CHAINMAIL_HELMET = new ItemArmor(46, EnumArmorMaterial.CHAIN, 1, 0).b(1, 0).b("helmetChain");
	public static Item CHAINMAIL_CHESTPLATE = new ItemArmor(47, EnumArmorMaterial.CHAIN, 1, 1).b(1, 1).b("chestplateChain");
	public static Item CHAINMAIL_LEGGINGS = new ItemArmor(48, EnumArmorMaterial.CHAIN, 1, 2).b(1, 2).b("leggingsChain");
	public static Item CHAINMAIL_BOOTS = new ItemArmor(49, EnumArmorMaterial.CHAIN, 1, 3).b(1, 3).b("bootsChain");

	public static Item IRON_HELMET = new ItemArmor(50, EnumArmorMaterial.IRON, 2, 0).b(2, 0).b("helmetIron");
	public static Item IRON_CHESTPLATE = new ItemArmor(51, EnumArmorMaterial.IRON, 2, 1).b(2, 1).b("chestplateIron");
	public static Item IRON_LEGGINGS = new ItemArmor(52, EnumArmorMaterial.IRON, 2, 2).b(2, 2).b("leggingsIron");
	public static Item IRON_BOOTS = new ItemArmor(53, EnumArmorMaterial.IRON, 2, 3).b(2, 3).b("bootsIron");

	public static Item DIAMOND_HELMET = new ItemArmor(54, EnumArmorMaterial.DIAMOND, 3, 0).b(3, 0).b("helmetDiamond");
	public static Item DIAMOND_CHESTPLATE = new ItemArmor(55, EnumArmorMaterial.DIAMOND, 3, 1).b(3, 1).b("chestplateDiamond");
	public static Item DIAMOND_LEGGINGS = new ItemArmor(56, EnumArmorMaterial.DIAMOND, 3, 2).b(3, 2).b("leggingsDiamond");
	public static Item DIAMOND_BOOTS = new ItemArmor(57, EnumArmorMaterial.DIAMOND, 3, 3).b(3, 3).b("bootsDiamond");

	public static Item GOLD_HELMET = new ItemArmor(58, EnumArmorMaterial.GOLD, 4, 0).b(4, 0).b("helmetGold");
	public static Item GOLD_CHESTPLATE = new ItemArmor(59, EnumArmorMaterial.GOLD, 4, 1).b(4, 1).b("chestplateGold");
	public static Item GOLD_LEGGINGS = new ItemArmor(60, EnumArmorMaterial.GOLD, 4, 2).b(4, 2).b("leggingsGold");
	public static Item GOLD_BOOTS = new ItemArmor(61, EnumArmorMaterial.GOLD, 4, 3).b(4, 3).b("bootsGold");

	public static Item FLINT = new Item(62).b(6, 0).b("flint").a(CreativeModeTab.l);
	public static Item PORK = new ItemFood(63, 3, 0.3F, true).b(7, 5).b("porkchopRaw");
	public static Item GRILLED_PORK = new ItemFood(64, 8, 0.8F, true).b(8, 5).b("porkchopCooked");
	public static Item PAINTING = new ItemPainting(65).b(10, 1).b("painting");

	public static Item GOLDEN_APPLE = new ItemGoldenApple(66, 4, 1.2F, false).i().a(MobEffectList.REGENERATION.id, 5, 0, 1.0F).b(11, 0).b("appleGold");

	public static Item SIGN = new ItemSign(67).b(10, 2).b("sign");
	public static Item WOOD_DOOR = new ItemDoor(68, Material.WOOD).b(11, 2).b("doorWood");

	public static Item BUCKET = new ItemBucket(69, 0).b(10, 4).b("bucket").d(16);
	public static Item WATER_BUCKET = new ItemBucket(70, Block.WATER.id).b(11, 4).b("bucketWater").a(BUCKET);
	public static Item LAVA_BUCKET = new ItemBucket(71, Block.LAVA.id).b(12, 4).b("bucketLava").a(BUCKET);

	public static Item MINECART = new ItemMinecart(72, 0).b(7, 8).b("minecart");
	public static Item SADDLE = new ItemSaddle(73).b(8, 6).b("saddle");
	public static Item IRON_DOOR = new ItemDoor(74, Material.ORE).b(12, 2).b("doorIron");
	public static Item REDSTONE = new ItemRedstone(75).b(8, 3).b("redstone").c(PotionBrewer.i);
	public static Item SNOW_BALL = new ItemSnowball(76).b(14, 0).b("snowball");

	public static Item BOAT = new ItemBoat(77).b(8, 8).b("boat");

	public static Item LEATHER = new Item(78).b(7, 6).b("leather").a(CreativeModeTab.l);
	public static Item MILK_BUCKET = new ItemMilkBucket(79).b(13, 4).b("milk").a(BUCKET);
	public static Item CLAY_BRICK = new Item(80).b(6, 1).b("brick").a(CreativeModeTab.l);
	public static Item CLAY_BALL = new Item(81).b(9, 3).b("clay").a(CreativeModeTab.l);
	public static Item SUGAR_CANE = new ItemReed(82, Block.SUGAR_CANE_BLOCK).b(11, 1).b("reeds").a(CreativeModeTab.l);
	public static Item PAPER = new Item(83).b(10, 3).b("paper").a(CreativeModeTab.f);
	public static Item BOOK = new Item(84).b(11, 3).b("book").a(CreativeModeTab.f);
	public static Item SLIME_BALL = new Item(85).b(14, 1).b("slimeball").a(CreativeModeTab.f);
	public static Item STORAGE_MINECART = new ItemMinecart(86, 1).b(7, 9).b("minecartChest");
	public static Item POWERED_MINECART = new ItemMinecart(87, 2).b(7, 10).b("minecartFurnace");
	public static Item EGG = new ItemEgg(88).b(12, 0).b("egg");
	public static Item COMPASS = new Item(89).b(6, 3).b("compass").a(CreativeModeTab.i);
	public static Item FISHING_ROD = new ItemFishingRod(90).b(5, 4).b("fishingRod");
	public static Item WATCH = new Item(91).b(6, 4).b("clock").a(CreativeModeTab.i);
	public static Item GLOWSTONE_DUST = new Item(92).b(9, 4).b("yellowDust").c(PotionBrewer.j).a(CreativeModeTab.l);
	public static Item RAW_FISH = new ItemFood(93, 2, 0.3F, false).b(9, 5).b("fishRaw");
	public static Item COOKED_FISH = new ItemFood(94, 5, 0.6F, false).b(10, 5).b("fishCooked");

	public static Item INK_SACK = new ItemDye(95).b(14, 4).b("dyePowder");
	public static Item BONE = new Item(96).b(12, 1).b("bone").n().a(CreativeModeTab.f);
	public static Item SUGAR = new Item(97).b(13, 0).b("sugar").c(PotionBrewer.b).a(CreativeModeTab.l);
	public static Item CAKE = new ItemReed(98, Block.CAKE_BLOCK).d(1).b(13, 1).b("cake").a(CreativeModeTab.h);

	public static Item BED = new ItemBed(99).d(1).b(13, 2).b("bed");

	public static Item DIODE = new ItemReed(100, Block.DIODE_OFF).b(6, 5).b("diode").a(CreativeModeTab.d);
	public static Item COOKIE = new ItemFood(101, 2, 0.1F, false).b(12, 5).b("cookie");

	public static ItemWorldMap MAP = (ItemWorldMap)new ItemWorldMap(102).b(12, 3).b("map");

	public static ItemShears SHEARS = (ItemShears)new ItemShears(103).b(13, 5).b("shears");
	public static Item MELON = new ItemFood(104, 2, 0.3F, false).b(13, 6).b("melon");

	public static Item PUMPKIN_SEEDS = new ItemSeeds(105, Block.PUMPKIN_STEM.id, Block.SOIL.id).b(13, 3).b("seeds_pumpkin");
	public static Item MELON_SEEDS = new ItemSeeds(106, Block.MELON_STEM.id, Block.SOIL.id).b(14, 3).b("seeds_melon");

	public static Item RAW_BEEF = new ItemFood(107, 3, 0.3F, true).b(9, 6).b("beefRaw");
	public static Item COOKED_BEEF = new ItemFood(108, 8, 0.8F, true).b(10, 6).b("beefCooked");
	public static Item RAW_CHICKEN = new ItemFood(109, 2, 0.3F, true).a(MobEffectList.HUNGER.id, 30, 0, 0.3F).b(9, 7).b("chickenRaw");
	public static Item COOKED_CHICKEN = new ItemFood(110, 6, 0.6F, true).b(10, 7).b("chickenCooked");
	public static Item ROTTEN_FLESH = new ItemFood(111, 4, 0.1F, true).a(MobEffectList.HUNGER.id, 30, 0, 0.8F).b(11, 5).b("rottenFlesh");

	public static Item ENDER_PEARL = new ItemEnderPearl(112).b(11, 6).b("enderPearl");
	public static Item BLAZE_ROD = new Item(113).b(12, 6).b("blazeRod").a(CreativeModeTab.l);
	public static Item GHAST_TEAR = new Item(114).b(11, 7).b("ghastTear").c(PotionBrewer.c).a(CreativeModeTab.k);
	public static Item GOLD_NUGGET = new Item(115).b(12, 7).b("goldNugget").a(CreativeModeTab.l);

	public static Item NETHER_STALK = new ItemSeeds(116, Block.NETHER_WART.id, Block.SOUL_SAND.id).b(13, 7).b("netherStalkSeeds").c("+4");

	public static ItemPotion POTION = (ItemPotion)new ItemPotion(117).b(13, 8).b("potion");
	public static Item GLASS_BOTTLE = new ItemGlassBottle(118).b(12, 8).b("glassBottle");

	public static Item SPIDER_EYE = new ItemFood(119, 2, 0.8F, false).a(MobEffectList.POISON.id, 5, 0, 1.0F).b(11, 8).b("spiderEye").c(PotionBrewer.d);

	public static Item FERMENTED_SPIDER_EYE = new Item(120).b(10, 8).b("fermentedSpiderEye").c(PotionBrewer.e).a(CreativeModeTab.k);

	public static Item BLAZE_POWDER = new Item(121).b(13, 9).b("blazePowder").c(PotionBrewer.g).a(CreativeModeTab.k);
	public static Item MAGMA_CREAM = new Item(122).b(13, 10).b("magmaCream").c(PotionBrewer.h).a(CreativeModeTab.k);

	public static Item BREWING_STAND = new ItemReed(123, Block.BREWING_STAND).b(12, 10).b("brewingStand").a(CreativeModeTab.k);
	public static Item CAULDRON = new ItemReed(124, Block.CAULDRON).b(12, 9).b("cauldron").a(CreativeModeTab.k);
	public static Item EYE_OF_ENDER = new ItemEnderEye(125).b(11, 9).b("eyeOfEnder");
	public static Item SPECKLED_MELON = new Item(126).b(9, 8).b("speckledMelon").c(PotionBrewer.f).a(CreativeModeTab.k);

	public static Item MONSTER_EGG = new ItemMonsterEgg(127).b(9, 9).b("monsterPlacer");
	public static Item EXP_BOTTLE = new ItemExpBottle(128).b(11, 10).b("expBottle");
	public static Item FIREBALL = new ItemFireball(129).b(14, 2).b("fireball");

	public static Item BOOK_AND_QUILL = new ItemBookAndQuill(130).b(11, 11).b("writingBook").a(CreativeModeTab.f);
	public static Item WRITTEN_BOOK = new ItemWrittenBook(131).b(12, 11).b("writtenBook");

	public static Item EMERALD = new Item(132).b(10, 11).b("emerald").a(CreativeModeTab.l);

	public static Item RECORD_1 = new ItemRecord(2000, "13").b(0, 15).b("record");
	public static Item RECORD_2 = new ItemRecord(2001, "cat").b(1, 15).b("record");
	public static Item RECORD_3 = new ItemRecord(2002, "blocks").b(2, 15).b("record");
	public static Item RECORD_4 = new ItemRecord(2003, "chirp").b(3, 15).b("record");
	public static Item RECORD_5 = new ItemRecord(2004, "far").b(4, 15).b("record");
	public static Item RECORD_6 = new ItemRecord(2005, "mall").b(5, 15).b("record");
	public static Item RECORD_7 = new ItemRecord(2006, "mellohi").b(6, 15).b("record");
	public static Item RECORD_8 = new ItemRecord(2007, "stal").b(7, 15).b("record");
	public static Item RECORD_9 = new ItemRecord(2008, "strad").b(8, 15).b("record");
	public static Item RECORD_10 = new ItemRecord(2009, "ward").b(9, 15).b("record");
	public static Item RECORD_11 = new ItemRecord(2010, "11").b(10, 15).b("record");
	public final int id;
	protected int maxStackSize = 64;
	private int durability = 0;
	protected int textureId;
	protected boolean bW = false;
	protected boolean bX = false;

	private Item craftingResult = null;
	private String bY = null;
	private String name;

	protected Item(int paramInt)
	{
		this.id = (256 + paramInt);
		if (byId[(256 + paramInt)] != null) {
			System.out.println("CONFLICT @ " + paramInt);
		}

		byId[(256 + paramInt)] = this;
	}

	public Item c(int paramInt) {
		this.textureId = paramInt;
		return this;
	}

	public Item d(int paramInt) {
		this.maxStackSize = paramInt;
		return this;
	}

	public Item b(int paramInt1, int paramInt2) {
		this.textureId = (paramInt1 + paramInt2 * 16);
		return this;
	}

	public boolean interactWith(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3)
	{
		return false;
	}

	public float getDestroySpeed(ItemStack paramItemStack, Block paramBlock) {
		return 1.0F;
	}

	public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
		return paramItemStack;
	}

	public ItemStack b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {
		return paramItemStack;
	}

	public int getMaxStackSize() {
		return this.maxStackSize;
	}

	public int filterData(int paramInt) {
		return 0;
	}

	public boolean k() {
		return this.bX;
	}

	protected Item a(boolean paramBoolean) {
		this.bX = paramBoolean;
		return this;
	}

	public int getMaxDurability() {
		return this.durability;
	}

	protected Item setMaxDurability(int paramInt) {
		this.durability = paramInt;
		return this;
	}

	public boolean m() {
		return (this.durability > 0) && (!this.bX);
	}

	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
	{
		return false;
	}

	public boolean a(ItemStack paramItemStack, World paramWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, EntityLiving paramEntityLiving)
	{
		return false;
	}

	public int a(Entity paramEntity) {
		return 1;
	}

	public boolean canDestroySpecialBlock(Block paramBlock) {
		return false;
	}

	public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving) {
		return false;
	}

	public Item n() {
		this.bW = true;
		return this;
	}

	public Item b(String paramString)
	{
		this.name = ("item." + paramString);
		return this;
	}

	public String getName()
	{
		return this.name;
	}

	public String c(ItemStack paramItemStack) {
		return this.name;
	}

	public Item a(Item paramItem) {
		this.craftingResult = paramItem;
		return this;
	}

	public boolean h(ItemStack paramItemStack)
	{
		return true;
	}

	public boolean p() {
		return false;
	}

	public Item q() {
		return this.craftingResult;
	}

	public boolean r() {
		return this.craftingResult != null;
	}

	public String s() {
		return LocaleI18n.get(getName() + ".name");
	}

	public String i(ItemStack paramItemStack) {
		return LocaleI18n.get(c(paramItemStack) + ".name");
	}

	public void a(ItemStack paramItemStack, World paramWorld, Entity paramEntity, int paramInt, boolean paramBoolean)
	{
	}

	public void d(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman)
	{
	}

	public boolean m_()
	{
		return false;
	}

	public EnumAnimation b(ItemStack paramItemStack) {
		return EnumAnimation.a;
	}

	public int a(ItemStack paramItemStack) {
		return 0;
	}

	public void a(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman, int paramInt) {
	}

	protected Item c(String paramString) {
		this.bY = paramString;
		return this;
	}

	public String t() {
		return this.bY;
	}

	public boolean u() {
		return this.bY != null;
	}

	public boolean k(ItemStack paramItemStack)
	{
		return (getMaxStackSize() == 1) && (m());
	}

	protected MovingObjectPosition a(World paramWorld, EntityHuman paramEntityHuman, boolean paramBoolean) {
		float f1 = 1.0F;

		float f2 = paramEntityHuman.lastPitch + (paramEntityHuman.pitch - paramEntityHuman.lastPitch) * f1;
		float f3 = paramEntityHuman.lastYaw + (paramEntityHuman.yaw - paramEntityHuman.lastYaw) * f1;

		double d1 = paramEntityHuman.lastX + (paramEntityHuman.locX - paramEntityHuman.lastX) * f1;
		double d2 = paramEntityHuman.lastY + (paramEntityHuman.locY - paramEntityHuman.lastY) * f1 + 1.62D - paramEntityHuman.height;
		double d3 = paramEntityHuman.lastZ + (paramEntityHuman.locZ - paramEntityHuman.lastZ) * f1;

		Vec3D localVec3D1 = Vec3D.a().create(d1, d2, d3);

		float f4 = MathHelper.cos(-f3 * 0.01745329F - 3.141593F);
		float f5 = MathHelper.sin(-f3 * 0.01745329F - 3.141593F);
		float f6 = -MathHelper.cos(-f2 * 0.01745329F);
		float f7 = MathHelper.sin(-f2 * 0.01745329F);

		float f8 = f5 * f6;
		float f9 = f7;
		float f10 = f4 * f6;

		double d4 = 5.0D;
		Vec3D localVec3D2 = localVec3D1.add(f8 * d4, f9 * d4, f10 * d4);

		return paramWorld.rayTrace(localVec3D1, localVec3D2, paramBoolean, !paramBoolean);
	}

	public int b() {
		return 0;
	}

	public Item a(CreativeModeTab paramCreativeModeTab)
	{
		this.a = paramCreativeModeTab;
		return this;
	}

	static
	{
		StatisticList.c();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Item
 * JD-Core Version:		0.6.0
 */