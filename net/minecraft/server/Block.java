/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.List;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class Block
/*		 */ {
/*		 */	 private CreativeModeTab creativeTab;
/*	 9 */	 public static final StepSound d = new StepSound("stone", 1.0F, 1.0F);
/*	10 */	 public static final StepSound e = new StepSound("wood", 1.0F, 1.0F);
/*	11 */	 public static final StepSound f = new StepSound("gravel", 1.0F, 1.0F);
/*	12 */	 public static final StepSound g = new StepSound("grass", 1.0F, 1.0F);
/*	13 */	 public static final StepSound h = new StepSound("stone", 1.0F, 1.0F);
/*	14 */	 public static final StepSound i = new StepSound("stone", 1.0F, 1.5F);
/*	15 */	 public static final StepSound j = new StepSoundStone("stone", 1.0F, 1.0F);
/*	16 */	 public static final StepSound k = new StepSound("cloth", 1.0F, 1.0F);
/*	17 */	 public static final StepSound l = new StepSoundSand("sand", 1.0F, 1.0F);
/*	18 */	 public static final Block[] byId = new Block[4096];
/*	19 */	 public static final boolean[] n = new boolean[4096];
/*	20 */	 public static final int[] lightBlock = new int[4096];
/*	21 */	 public static final boolean[] p = new boolean[4096];
/*	22 */	 public static final int[] lightEmission = new int[4096];
/*	23 */	 public static final boolean[] r = new boolean[4096];
/*	24 */	 public static boolean[] s = new boolean[4096];
/*	25 */	 public static final Block STONE = new BlockStone(1, 1).c(1.5F).b(10.0F).a(h).b("stone");
/*	26 */	 public static final BlockGrass GRASS = (BlockGrass)new BlockGrass(2).c(0.6F).a(g).b("grass");
/*	27 */	 public static final Block DIRT = new BlockDirt(3, 2).c(0.5F).a(f).b("dirt");
/*	28 */	 public static final Block COBBLESTONE = new Block(4, 16, Material.STONE).c(2.0F).b(10.0F).a(h).b("stonebrick").a(CreativeModeTab.b);
/*	29 */	 public static final Block WOOD = new BlockWood(5).c(2.0F).b(5.0F).a(e).b("wood").p();
/*	30 */	 public static final Block SAPLING = new BlockSapling(6, 15).c(0.0F).a(g).b("sapling").p();
/*	31 */	 public static final Block BEDROCK = new Block(7, 17, Material.STONE).q().b(6000000.0F).a(h).b("bedrock").v().a(CreativeModeTab.b);
/*	32 */	 public static final Block WATER = new BlockFlowing(8, Material.WATER).c(100.0F).h(3).b("water").v().p();
/*	33 */	 public static final Block STATIONARY_WATER = new BlockStationary(9, Material.WATER).c(100.0F).h(3).b("water").v().p();
/*	34 */	 public static final Block LAVA = new BlockFlowing(10, Material.LAVA).c(0.0F).a(1.0F).h(255).b("lava").v().p();
/*	35 */	 public static final Block STATIONARY_LAVA = new BlockStationary(11, Material.LAVA).c(100.0F).a(1.0F).h(255).b("lava").v().p();
/*	36 */	 public static final Block SAND = new BlockSand(12, 18).c(0.5F).a(l).b("sand");
/*	37 */	 public static final Block GRAVEL = new BlockGravel(13, 19).c(0.6F).a(f).b("gravel");
/*	38 */	 public static final Block GOLD_ORE = new BlockOre(14, 32).c(3.0F).b(5.0F).a(h).b("oreGold");
/*	39 */	 public static final Block IRON_ORE = new BlockOre(15, 33).c(3.0F).b(5.0F).a(h).b("oreIron");
/*	40 */	 public static final Block COAL_ORE = new BlockOre(16, 34).c(3.0F).b(5.0F).a(h).b("oreCoal");
/*	41 */	 public static final Block LOG = new BlockLog(17).c(2.0F).a(e).b("log").p();
/*	42 */	 public static final BlockLeaves LEAVES = (BlockLeaves)new BlockLeaves(18, 52).c(0.2F).h(1).a(g).b("leaves").p();
/*	43 */	 public static final Block SPONGE = new BlockSponge(19).c(0.6F).a(g).b("sponge");
/*	44 */	 public static final Block GLASS = new BlockGlass(20, 49, Material.SHATTERABLE, false).c(0.3F).a(j).b("glass");
/*	45 */	 public static final Block LAPIS_ORE = new BlockOre(21, 160).c(3.0F).b(5.0F).a(h).b("oreLapis");
/*	46 */	 public static final Block LAPIS_BLOCK = new Block(22, 144, Material.STONE).c(3.0F).b(5.0F).a(h).b("blockLapis").a(CreativeModeTab.b);
/*	47 */	 public static final Block DISPENSER = new BlockDispenser(23).c(3.5F).a(h).b("dispenser").p();
/*	48 */	 public static final Block SANDSTONE = new BlockSandStone(24).a(h).c(0.8F).b("sandStone").p();
/*	49 */	 public static final Block NOTE_BLOCK = new BlockNote(25).c(0.8F).b("musicBlock").p();
/*	50 */	 public static final Block BED = new BlockBed(26).c(0.2F).b("bed").v().p();
/*	51 */	 public static final Block GOLDEN_RAIL = new BlockMinecartTrack(27, 179, true).c(0.7F).a(i).b("goldenRail").p();
/*	52 */	 public static final Block DETECTOR_RAIL = new BlockMinecartDetector(28, 195).c(0.7F).a(i).b("detectorRail").p();
/*	53 */	 public static final Block PISTON_STICKY = new BlockPiston(29, 106, true).b("pistonStickyBase").p();
/*	54 */	 public static final Block WEB = new BlockWeb(30, 11).h(1).c(4.0F).b("web");
/*	55 */	 public static final BlockLongGrass LONG_GRASS = (BlockLongGrass)new BlockLongGrass(31, 39).c(0.0F).a(g).b("tallgrass");
/*	56 */	 public static final BlockDeadBush DEAD_BUSH = (BlockDeadBush)new BlockDeadBush(32, 55).c(0.0F).a(g).b("deadbush");
/*	57 */	 public static final Block PISTON = new BlockPiston(33, 107, false).b("pistonBase").p();
/*	58 */	 public static final BlockPistonExtension PISTON_EXTENSION = (BlockPistonExtension)new BlockPistonExtension(34, 107).p();
/*	59 */	 public static final Block WOOL = new BlockCloth().c(0.8F).a(k).b("cloth").p();
/*	60 */	 public static final BlockPistonMoving PISTON_MOVING = new BlockPistonMoving(36);
/*	61 */	 public static final BlockFlower YELLOW_FLOWER = (BlockFlower)new BlockFlower(37, 13).c(0.0F).a(g).b("flower");
/*	62 */	 public static final BlockFlower RED_ROSE = (BlockFlower)new BlockFlower(38, 12).c(0.0F).a(g).b("rose");
/*	63 */	 public static final BlockFlower BROWN_MUSHROOM = (BlockFlower)new BlockMushroom(39, 29).c(0.0F).a(g).a(0.125F).b("mushroom");
/*	64 */	 public static final BlockFlower RED_MUSHROOM = (BlockFlower)new BlockMushroom(40, 28).c(0.0F).a(g).b("mushroom");
/*	65 */	 public static final Block GOLD_BLOCK = new BlockOreBlock(41, 23).c(3.0F).b(10.0F).a(i).b("blockGold");
/*	66 */	 public static final Block IRON_BLOCK = new BlockOreBlock(42, 22).c(5.0F).b(10.0F).a(i).b("blockIron");
/*	67 */	 public static final BlockStepAbstract DOUBLE_STEP = (BlockStepAbstract)new BlockStep(43, true).c(2.0F).b(10.0F).a(h).b("stoneSlab");
/*	68 */	 public static final BlockStepAbstract STEP = (BlockStepAbstract)new BlockStep(44, false).c(2.0F).b(10.0F).a(h).b("stoneSlab");
/*	69 */	 public static final Block BRICK = new Block(45, 7, Material.STONE).c(2.0F).b(10.0F).a(h).b("brick").a(CreativeModeTab.b);
/*	70 */	 public static final Block TNT = new BlockTNT(46, 8).c(0.0F).a(g).b("tnt");
/*	71 */	 public static final Block BOOKSHELF = new BlockBookshelf(47, 35).c(1.5F).a(e).b("bookshelf");
/*	72 */	 public static final Block MOSSY_COBBLESTONE = new Block(48, 36, Material.STONE).c(2.0F).b(10.0F).a(h).b("stoneMoss").a(CreativeModeTab.b);
/*	73 */	 public static final Block OBSIDIAN = new BlockObsidian(49, 37).c(50.0F).b(2000.0F).a(h).b("obsidian");
/*	74 */	 public static final Block TORCH = new BlockTorch(50, 80).c(0.0F).a(0.9375F).a(e).b("torch").p();
/*	75 */	 public static final BlockFire FIRE = (BlockFire)new BlockFire(51, 31).c(0.0F).a(1.0F).a(e).b("fire").v();
/*	76 */	 public static final Block MOB_SPAWNER = new BlockMobSpawner(52, 65).c(5.0F).a(i).b("mobSpawner").v();
/*	77 */	 public static final Block WOOD_STAIRS = new BlockStairs(53, WOOD, 0).b("stairsWood").p();
/*	78 */	 public static final Block CHEST = new BlockChest(54).c(2.5F).a(e).b("chest").p();
/*	79 */	 public static final Block REDSTONE_WIRE = new BlockRedstoneWire(55, 164).c(0.0F).a(d).b("redstoneDust").v().p();
/*	80 */	 public static final Block DIAMOND_ORE = new BlockOre(56, 50).c(3.0F).b(5.0F).a(h).b("oreDiamond");
/*	81 */	 public static final Block DIAMOND_BLOCK = new BlockOreBlock(57, 24).c(5.0F).b(10.0F).a(i).b("blockDiamond");
/*	82 */	 public static final Block WORKBENCH = new BlockWorkbench(58).c(2.5F).a(e).b("workbench");
/*	83 */	 public static final Block CROPS = new BlockCrops(59, 88).c(0.0F).a(g).b("crops").v().p();
/*	84 */	 public static final Block SOIL = new BlockSoil(60).c(0.6F).a(f).b("farmland").p();
/*	85 */	 public static final Block FURNACE = new BlockFurnace(61, false).c(3.5F).a(h).b("furnace").p().a(CreativeModeTab.c);
/*	86 */	 public static final Block BURNING_FURNACE = new BlockFurnace(62, true).c(3.5F).a(h).a(0.875F).b("furnace").p();
/*	87 */	 public static final Block SIGN_POST = new BlockSign(63, TileEntitySign.class, true).c(1.0F).a(e).b("sign").v().p();
/*	88 */	 public static final Block WOODEN_DOOR = new BlockDoor(64, Material.WOOD).c(3.0F).a(e).b("doorWood").v().p();
/*	89 */	 public static final Block LADDER = new BlockLadder(65, 83).c(0.4F).a(e).b("ladder").p();
/*	90 */	 public static final Block RAILS = new BlockMinecartTrack(66, 128, false).c(0.7F).a(i).b("rail").p();
/*	91 */	 public static final Block COBBLESTONE_STAIRS = new BlockStairs(67, COBBLESTONE, 0).b("stairsStone").p();
/*	92 */	 public static final Block WALL_SIGN = new BlockSign(68, TileEntitySign.class, false).c(1.0F).a(e).b("sign").v().p();
/*	93 */	 public static final Block LEVER = new BlockLever(69, 96).c(0.5F).a(e).b("lever").p();
/*	94 */	 public static final Block STONE_PLATE = new BlockPressurePlate(70, STONE.textureId, EnumMobType.MOBS, Material.STONE).c(0.5F).a(h).b("pressurePlate").p();
/*	95 */	 public static final Block IRON_DOOR_BLOCK = new BlockDoor(71, Material.ORE).c(5.0F).a(i).b("doorIron").v().p();
/*	96 */	 public static final Block WOOD_PLATE = new BlockPressurePlate(72, WOOD.textureId, EnumMobType.EVERYTHING, Material.WOOD).c(0.5F).a(e).b("pressurePlate").p();
/*	97 */	 public static final Block REDSTONE_ORE = new BlockRedstoneOre(73, 51, false).c(3.0F).b(5.0F).a(h).b("oreRedstone").p().a(CreativeModeTab.b);
/*	98 */	 public static final Block GLOWING_REDSTONE_ORE = new BlockRedstoneOre(74, 51, true).a(0.625F).c(3.0F).b(5.0F).a(h).b("oreRedstone").p();
/*	99 */	 public static final Block REDSTONE_TORCH_OFF = new BlockRedstoneTorch(75, 115, false).c(0.0F).a(e).b("notGate").p();
/* 100 */	 public static final Block REDSTONE_TORCH_ON = new BlockRedstoneTorch(76, 99, true).c(0.0F).a(0.5F).a(e).b("notGate").p().a(CreativeModeTab.d);
/* 101 */	 public static final Block STONE_BUTTON = new BlockButton(77, STONE.textureId).c(0.5F).a(h).b("button").p();
/* 102 */	 public static final Block SNOW = new BlockSnow(78, 66).c(0.1F).a(k).b("snow").p().h(0);
/* 103 */	 public static final Block ICE = new BlockIce(79, 67).c(0.5F).h(3).a(j).b("ice");
/* 104 */	 public static final Block SNOW_BLOCK = new BlockSnowBlock(80, 66).c(0.2F).a(k).b("snow");
/* 105 */	 public static final Block CACTUS = new BlockCactus(81, 70).c(0.4F).a(k).b("cactus");
/* 106 */	 public static final Block CLAY = new BlockClay(82, 72).c(0.6F).a(f).b("clay");
/* 107 */	 public static final Block SUGAR_CANE_BLOCK = new BlockReed(83, 73).c(0.0F).a(g).b("reeds").v();
/* 108 */	 public static final Block JUKEBOX = new BlockJukeBox(84, 74).c(2.0F).b(10.0F).a(h).b("jukebox").p();
/* 109 */	 public static final Block FENCE = new BlockFence(85, 4).c(2.0F).b(5.0F).a(e).b("fence");
/* 110 */	 public static final Block PUMPKIN = new BlockPumpkin(86, 102, false).c(1.0F).a(e).b("pumpkin").p();
/* 111 */	 public static final Block NETHERRACK = new BlockBloodStone(87, 103).c(0.4F).a(h).b("hellrock");
/* 112 */	 public static final Block SOUL_SAND = new BlockSlowSand(88, 104).c(0.5F).a(l).b("hellsand");
/* 113 */	 public static final Block GLOWSTONE = new BlockLightStone(89, 105, Material.SHATTERABLE).c(0.3F).a(j).a(1.0F).b("lightgem");
/* 114 */	 public static final BlockPortal PORTAL = (BlockPortal)new BlockPortal(90, 14).c(-1.0F).a(j).a(0.75F).b("portal");
/* 115 */	 public static final Block JACK_O_LANTERN = new BlockPumpkin(91, 102, true).c(1.0F).a(e).a(1.0F).b("litpumpkin").p();
/* 116 */	 public static final Block CAKE_BLOCK = new BlockCake(92, 121).c(0.5F).a(k).b("cake").v().p();
/* 117 */	 public static final Block DIODE_OFF = new BlockDiode(93, false).c(0.0F).a(e).b("diode").v().p();
/* 118 */	 public static final Block DIODE_ON = new BlockDiode(94, true).c(0.0F).a(0.625F).a(e).b("diode").v().p();
/* 119 */	 public static final Block LOCKED_CHEST = new BlockLockedChest(95).c(0.0F).a(1.0F).a(e).b("lockedchest").b(true).p();
/* 120 */	 public static final Block TRAP_DOOR = new BlockTrapdoor(96, Material.WOOD).c(3.0F).a(e).b("trapdoor").v().p();
/* 121 */	 public static final Block MONSTER_EGGS = new BlockMonsterEggs(97).c(0.75F).b("monsterStoneEgg");
/* 122 */	 public static final Block SMOOTH_BRICK = new BlockSmoothBrick(98).c(1.5F).b(10.0F).a(h).b("stonebricksmooth");
/* 123 */	 public static final Block BIG_MUSHROOM_1 = new BlockHugeMushroom(99, Material.WOOD, 142, 0).c(0.2F).a(e).b("mushroom").p();
/* 124 */	 public static final Block BIG_MUSHROOM_2 = new BlockHugeMushroom(100, Material.WOOD, 142, 1).c(0.2F).a(e).b("mushroom").p();
/* 125 */	 public static final Block IRON_FENCE = new BlockThinFence(101, 85, 85, Material.ORE, true).c(5.0F).b(10.0F).a(i).b("fenceIron");
/* 126 */	 public static final Block THIN_GLASS = new BlockThinFence(102, 49, 148, Material.SHATTERABLE, false).c(0.3F).a(j).b("thinGlass");
/* 127 */	 public static final Block MELON = new BlockMelon(103).c(1.0F).a(e).b("melon");
/* 128 */	 public static final Block PUMPKIN_STEM = new BlockStem(104, PUMPKIN).c(0.0F).a(e).b("pumpkinStem").p();
/* 129 */	 public static final Block MELON_STEM = new BlockStem(105, MELON).c(0.0F).a(e).b("pumpkinStem").p();
/* 130 */	 public static final Block VINE = new BlockVine(106).c(0.2F).a(g).b("vine").p();
/* 131 */	 public static final Block FENCE_GATE = new BlockFenceGate(107, 4).c(2.0F).b(5.0F).a(e).b("fenceGate").p();
/* 132 */	 public static final Block BRICK_STAIRS = new BlockStairs(108, BRICK, 0).b("stairsBrick").p();
/* 133 */	 public static final Block STONE_STAIRS = new BlockStairs(109, SMOOTH_BRICK, 0).b("stairsStoneBrickSmooth").p();
/* 134 */	 public static final BlockMycel MYCEL = (BlockMycel)new BlockMycel(110).c(0.6F).a(g).b("mycel");
/* 135 */	 public static final Block WATER_LILY = new BlockWaterLily(111, 76).c(0.0F).a(g).b("waterlily");
/* 136 */	 public static final Block NETHER_BRICK = new Block(112, 224, Material.STONE).c(2.0F).b(10.0F).a(h).b("netherBrick").a(CreativeModeTab.b);
/* 137 */	 public static final Block NETHER_FENCE = new BlockFence(113, 224, Material.STONE).c(2.0F).b(10.0F).a(h).b("netherFence");
/* 138 */	 public static final Block NETHER_BRICK_STAIRS = new BlockStairs(114, NETHER_BRICK, 0).b("stairsNetherBrick").p();
/* 139 */	 public static final Block NETHER_WART = new BlockNetherWart(115).b("netherStalk").p();
/* 140 */	 public static final Block ENCHANTMENT_TABLE = new BlockEnchantmentTable(116).c(5.0F).b(2000.0F).b("enchantmentTable");
/* 141 */	 public static final Block BREWING_STAND = new BlockBrewingStand(117).c(0.5F).a(0.125F).b("brewingStand").p();
/* 142 */	 public static final Block CAULDRON = new BlockCauldron(118).c(2.0F).b("cauldron").p();
/* 143 */	 public static final Block ENDER_PORTAL = new BlockEnderPortal(119, Material.PORTAL).c(-1.0F).b(6000000.0F);
/* 144 */	 public static final Block ENDER_PORTAL_FRAME = new BlockEnderPortalFrame(120).a(j).a(0.125F).c(-1.0F).b("endPortalFrame").p().b(6000000.0F).a(CreativeModeTab.c);
/* 145 */	 public static final Block WHITESTONE = new Block(121, 175, Material.STONE).c(3.0F).b(15.0F).a(h).b("whiteStone").a(CreativeModeTab.b);
/* 146 */	 public static final Block DRAGON_EGG = new BlockDragonEgg(122, 167).c(3.0F).b(15.0F).a(h).a(0.125F).b("dragonEgg");
/* 147 */	 public static final Block REDSTONE_LAMP_OFF = new BlockRedstoneLamp(123, false).c(0.3F).a(j).b("redstoneLight").a(CreativeModeTab.d);
/* 148 */	 public static final Block REDSTONE_LAMP_ON = new BlockRedstoneLamp(124, true).c(0.3F).a(j).b("redstoneLight");
/* 149 */	 public static final BlockStepAbstract WOOD_DOUBLE_STEP = (BlockStepAbstract)new BlockWoodStep(125, true).c(2.0F).b(5.0F).a(e).b("woodSlab");
/* 150 */	 public static final BlockStepAbstract WOOD_STEP = (BlockStepAbstract)new BlockWoodStep(126, false).c(2.0F).b(5.0F).a(e).b("woodSlab");
/* 151 */	 public static final Block COCOA = new BlockCocoa(127).c(0.2F).b(5.0F).a(e).b("cocoa").p();
/* 152 */	 public static final Block SANDSTONE_STAIRS = new BlockStairs(128, SANDSTONE, 0).b("stairsSandStone").p();
/* 153 */	 public static final Block EMERALD_ORE = new BlockOre(129, 171).c(3.0F).b(5.0F).a(h).b("oreEmerald");
/* 154 */	 public static final Block ENDER_CHEST = new BlockEnderChest(130).c(22.5F).b(1000.0F).a(h).b("enderChest").p().a(0.5F);
/* 155 */	 public static final BlockTripwireHook TRIPWIRE_SOURCE = (BlockTripwireHook)new BlockTripwireHook(131).b("tripWireSource").p();
/* 156 */	 public static final Block TRIPWIRE = new BlockTripwire(132).b("tripWire").p();
/* 157 */	 public static final Block EMERALD_BLOCK = new BlockOreBlock(133, 25).c(5.0F).b(10.0F).a(i).b("blockEmerald");
/* 158 */	 public static final Block SPRUCE_WOOD_STAIRS = new BlockStairs(134, WOOD, 1).b("stairsWoodSpruce").p();
/* 159 */	 public static final Block BIRCH_WOOD_STAIRS = new BlockStairs(135, WOOD, 2).b("stairsWoodBirch").p();
/* 160 */	 public static final Block JUNGLE_WOOD_STAIRS = new BlockStairs(136, WOOD, 3).b("stairsWoodJungle").p();
/*		 */	 public int textureId;
/*		 */	 public final int id;
/*		 */	 protected float strength;
/*		 */	 protected float durability;
/*		 */	 protected boolean cd;
/*		 */	 protected boolean ce;
/*		 */	 protected boolean cf;
/*		 */	 protected boolean isTileEntity;
/*		 */	 public double minX;
/*		 */	 public double minY;
/*		 */	 public double minZ;
/*		 */	 public double maxX;
/*		 */	 public double maxY;
/*		 */	 public double maxZ;
/*		 */	 public StepSound stepSound;
/*		 */	 public float co;
/*		 */	 public final Material material;
/*		 */	 public float frictionFactor;
/*		 */	 private String name;
/*		 */ 
/*		 */	 protected Block(int i, Material material)
/*		 */	 {
/* 182 */		 this.cd = true;
/* 183 */		 this.ce = true;
/* 184 */		 this.stepSound = d;
/* 185 */		 this.co = 1.0F;
/* 186 */		 this.frictionFactor = 0.6F;
/* 187 */		 if (byId[i] != null) {
/* 188 */			 throw new IllegalArgumentException("Slot " + i + " is already occupied by " + byId[i] + " when adding " + this);
/*		 */		 }
/* 190 */		 this.material = material;
/* 191 */		 byId[i] = this;
/* 192 */		 this.id = i;
/* 193 */		 a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/* 194 */		 n[i] = d();
/* 195 */		 lightBlock[i] = (d() ? 'ÿ' : 0);
/* 196 */		 p[i] = (!material.blocksLight() ? 1 : false);
/*		 */	 }
/*		 */ 
/*		 */	 protected Block p()
/*		 */	 {
/* 201 */		 r[this.id] = true;
/* 202 */		 return this;
/*		 */	 }
/*		 */	 protected void r_() {
/*		 */	 }
/*		 */ 
/*		 */	 protected Block(int i, int j, Material material) {
/* 208 */		 this(i, material);
/* 209 */		 this.textureId = j;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block a(StepSound stepsound) {
/* 213 */		 this.stepSound = stepsound;
/* 214 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block h(int i) {
/* 218 */		 lightBlock[this.id] = i;
/* 219 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block a(float f) {
/* 223 */		 lightEmission[this.id] = (int)(15.0F * f);
/* 224 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block b(float f) {
/* 228 */		 this.durability = (f * 3.0F);
/* 229 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public static boolean i(int i) {
/* 233 */		 Block block = byId[i];
/*		 */ 
/* 235 */		 return block != null;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c() {
/* 239 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(IBlockAccess iblockaccess, int i, int j, int k) {
/* 243 */		 return !this.material.isSolid();
/*		 */	 }
/*		 */ 
/*		 */	 public int b() {
/* 247 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block c(float f) {
/* 251 */		 this.strength = f;
/* 252 */		 if (this.durability < f * 5.0F) {
/* 253 */			 this.durability = (f * 5.0F);
/*		 */		 }
/*		 */ 
/* 256 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block q() {
/* 260 */		 c(-1.0F);
/* 261 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public float m(World world, int i, int j, int k) {
/* 265 */		 return this.strength;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block b(boolean flag) {
/* 269 */		 this.cf = flag;
/* 270 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean r() {
/* 274 */		 return this.cf;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean s() {
/* 278 */		 return this.isTileEntity;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(float f, float f1, float f2, float f3, float f4, float f5) {
/* 282 */		 this.minX = f;
/* 283 */		 this.minY = f1;
/* 284 */		 this.minZ = f2;
/* 285 */		 this.maxX = f3;
/* 286 */		 this.maxY = f4;
/* 287 */		 this.maxZ = f5;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 291 */		 return iblockaccess.getMaterial(i, j, k).isBuildable();
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i, int j) {
/* 295 */		 return a(i);
/*		 */	 }
/*		 */ 
/*		 */	 public int a(int i) {
/* 299 */		 return this.textureId;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List list, Entity entity) {
/* 303 */		 AxisAlignedBB axisalignedbb1 = e(world, i, j, k);
/*		 */ 
/* 305 */		 if ((axisalignedbb1 != null) && (axisalignedbb.a(axisalignedbb1)))
/* 306 */			 list.add(axisalignedbb1);
/*		 */	 }
/*		 */ 
/*		 */	 public AxisAlignedBB e(World world, int i, int j, int k)
/*		 */	 {
/* 311 */		 return AxisAlignedBB.a().a(i + this.minX, j + this.minY, k + this.minZ, i + this.maxX, j + this.maxY, k + this.maxZ);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d() {
/* 315 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(int i, boolean flag) {
/* 319 */		 return l();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean l() {
/* 323 */		 return true;
/*		 */	 }
/*		 */	 public void b(World world, int i, int j, int k, Random random) {
/*		 */	 }
/*		 */	 public void postBreak(World world, int i, int j, int k, int l) {
/*		 */	 }
/*		 */	 public void doPhysics(World world, int i, int j, int k, int l) {
/*		 */	 }
/*		 */ 
/*		 */	 public int p_() {
/* 333 */		 return 10;
/*		 */	 }
/*		 */	 public void onPlace(World world, int i, int j, int k) {
/*		 */	 }
/*		 */	 public void remove(World world, int i, int j, int k, int l, int i1) {
/*		 */	 }
/*		 */ 
/*		 */	 public int a(Random random) {
/* 341 */		 return 1;
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropType(int i, Random random, int j) {
/* 345 */		 return this.id;
/*		 */	 }
/*		 */ 
/*		 */	 public float getDamage(EntityHuman entityhuman, World world, int i, int j, int k) {
/* 349 */		 float f = m(world, i, j, k);
/*		 */ 
/* 351 */		 return !entityhuman.b(this) ? 1.0F / f / 100.0F : f < 0.0F ? 0.0F : entityhuman.a(this) / f / 30.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public final void c(World world, int i, int j, int k, int l, int i1) {
/* 355 */		 dropNaturally(world, i, j, k, l, 1.0F, i1);
/*		 */	 }
/*		 */ 
/*		 */	 public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
/* 359 */		 if (!world.isStatic) {
/* 360 */			 int j1 = getDropCount(i1, world.random);
/*		 */ 
/* 362 */			 for (int k1 = 0; k1 < j1; k1++)
/*		 */			 {
/* 364 */				 if (world.random.nextFloat() < f) {
/* 365 */					 int l1 = getDropType(l, world.random, i1);
/*		 */ 
/* 367 */					 if (l1 > 0)
/* 368 */						 a(world, i, j, k, new ItemStack(l1, 1, getDropData(l)));
/*		 */				 }
/*		 */			 }
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void a(World world, int i, int j, int k, ItemStack itemstack)
/*		 */	 {
/* 376 */		 if (!world.isStatic) {
/* 377 */			 float f = 0.7F;
/* 378 */			 double d0 = world.random.nextFloat() * f + (1.0F - f) * 0.5D;
/* 379 */			 double d1 = world.random.nextFloat() * f + (1.0F - f) * 0.5D;
/* 380 */			 double d2 = world.random.nextFloat() * f + (1.0F - f) * 0.5D;
/* 381 */			 EntityItem entityitem = new EntityItem(world, i + d0, j + d1, k + d2, itemstack);
/*		 */ 
/* 383 */			 entityitem.pickupDelay = 10;
/* 384 */			 world.addEntity(entityitem);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected void g(World world, int i, int j, int k, int l) {
/* 389 */		 if (!world.isStatic)
/* 390 */			 while (l > 0) {
/* 391 */				 int i1 = EntityExperienceOrb.getOrbValue(l);
/*		 */ 
/* 393 */				 l -= i1;
/* 394 */				 world.addEntity(new EntityExperienceOrb(world, i + 0.5D, j + 0.5D, k + 0.5D, i1));
/*		 */			 }
/*		 */	 }
/*		 */ 
/*		 */	 protected int getDropData(int i)
/*		 */	 {
/* 400 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 public float a(Entity entity) {
/* 404 */		 return this.durability / 5.0F;
/*		 */	 }
/*		 */ 
/*		 */	 public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
/* 408 */		 updateShape(world, i, j, k);
/* 409 */		 vec3d = vec3d.add(-i, -j, -k);
/* 410 */		 vec3d1 = vec3d1.add(-i, -j, -k);
/* 411 */		 Vec3D vec3d2 = vec3d.b(vec3d1, this.minX);
/* 412 */		 Vec3D vec3d3 = vec3d.b(vec3d1, this.maxX);
/* 413 */		 Vec3D vec3d4 = vec3d.c(vec3d1, this.minY);
/* 414 */		 Vec3D vec3d5 = vec3d.c(vec3d1, this.maxY);
/* 415 */		 Vec3D vec3d6 = vec3d.d(vec3d1, this.minZ);
/* 416 */		 Vec3D vec3d7 = vec3d.d(vec3d1, this.maxZ);
/*		 */ 
/* 418 */		 if (!a(vec3d2)) {
/* 419 */			 vec3d2 = null;
/*		 */		 }
/*		 */ 
/* 422 */		 if (!a(vec3d3)) {
/* 423 */			 vec3d3 = null;
/*		 */		 }
/*		 */ 
/* 426 */		 if (!b(vec3d4)) {
/* 427 */			 vec3d4 = null;
/*		 */		 }
/*		 */ 
/* 430 */		 if (!b(vec3d5)) {
/* 431 */			 vec3d5 = null;
/*		 */		 }
/*		 */ 
/* 434 */		 if (!c(vec3d6)) {
/* 435 */			 vec3d6 = null;
/*		 */		 }
/*		 */ 
/* 438 */		 if (!c(vec3d7)) {
/* 439 */			 vec3d7 = null;
/*		 */		 }
/*		 */ 
/* 442 */		 Vec3D vec3d8 = null;
/*		 */ 
/* 444 */		 if ((vec3d2 != null) && ((vec3d8 == null) || (vec3d.distanceSquared(vec3d2) < vec3d.distanceSquared(vec3d8)))) {
/* 445 */			 vec3d8 = vec3d2;
/*		 */		 }
/*		 */ 
/* 448 */		 if ((vec3d3 != null) && ((vec3d8 == null) || (vec3d.distanceSquared(vec3d3) < vec3d.distanceSquared(vec3d8)))) {
/* 449 */			 vec3d8 = vec3d3;
/*		 */		 }
/*		 */ 
/* 452 */		 if ((vec3d4 != null) && ((vec3d8 == null) || (vec3d.distanceSquared(vec3d4) < vec3d.distanceSquared(vec3d8)))) {
/* 453 */			 vec3d8 = vec3d4;
/*		 */		 }
/*		 */ 
/* 456 */		 if ((vec3d5 != null) && ((vec3d8 == null) || (vec3d.distanceSquared(vec3d5) < vec3d.distanceSquared(vec3d8)))) {
/* 457 */			 vec3d8 = vec3d5;
/*		 */		 }
/*		 */ 
/* 460 */		 if ((vec3d6 != null) && ((vec3d8 == null) || (vec3d.distanceSquared(vec3d6) < vec3d.distanceSquared(vec3d8)))) {
/* 461 */			 vec3d8 = vec3d6;
/*		 */		 }
/*		 */ 
/* 464 */		 if ((vec3d7 != null) && ((vec3d8 == null) || (vec3d.distanceSquared(vec3d7) < vec3d.distanceSquared(vec3d8)))) {
/* 465 */			 vec3d8 = vec3d7;
/*		 */		 }
/*		 */ 
/* 468 */		 if (vec3d8 == null) {
/* 469 */			 return null;
/*		 */		 }
/* 471 */		 byte b0 = -1;
/*		 */ 
/* 473 */		 if (vec3d8 == vec3d2) {
/* 474 */			 b0 = 4;
/*		 */		 }
/*		 */ 
/* 477 */		 if (vec3d8 == vec3d3) {
/* 478 */			 b0 = 5;
/*		 */		 }
/*		 */ 
/* 481 */		 if (vec3d8 == vec3d4) {
/* 482 */			 b0 = 0;
/*		 */		 }
/*		 */ 
/* 485 */		 if (vec3d8 == vec3d5) {
/* 486 */			 b0 = 1;
/*		 */		 }
/*		 */ 
/* 489 */		 if (vec3d8 == vec3d6) {
/* 490 */			 b0 = 2;
/*		 */		 }
/*		 */ 
/* 493 */		 if (vec3d8 == vec3d7) {
/* 494 */			 b0 = 3;
/*		 */		 }
/*		 */ 
/* 497 */		 return new MovingObjectPosition(i, j, k, b0, vec3d8.add(i, j, k));
/*		 */	 }
/*		 */ 
/*		 */	 private boolean a(Vec3D vec3d)
/*		 */	 {
/* 502 */		 return vec3d != null;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean b(Vec3D vec3d) {
/* 506 */		 return vec3d != null;
/*		 */	 }
/*		 */ 
/*		 */	 private boolean c(Vec3D vec3d) {
/* 510 */		 return vec3d != null;
/*		 */	 }
/*		 */	 public void wasExploded(World world, int i, int j, int k) {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k, int l) {
/* 516 */		 return canPlace(world, i, j, k);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean canPlace(World world, int i, int j, int k) {
/* 520 */		 int l = world.getTypeId(i, j, k);
/*		 */ 
/* 522 */		 return (l == 0) || (byId[l].material.isReplaceable());
/*		 */	 }
/*		 */ 
/*		 */	 public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
/* 526 */		 return false;
/*		 */	 }
/*		 */	 public void b(World world, int i, int j, int k, Entity entity) {
/*		 */	 }
/*		 */	 public void postPlace(World world, int i, int j, int k, int l, float f, float f1, float f2) {
/*		 */	 }
/*		 */	 public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
/*		 */	 }
/*		 */	 public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
/*		 */	 }
/*		 */	 public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
/* 540 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isPowerSource() {
/* 544 */		 return false;
/*		 */	 }
/*		 */	 public void a(World world, int i, int j, int k, Entity entity) {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(World world, int i, int j, int k, int l) {
/* 550 */		 return false;
/*		 */	 }
/*		 */	 public void f() {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
/* 556 */		 entityhuman.a(StatisticList.C[this.id], 1);
/* 557 */		 entityhuman.j(0.025F);
/* 558 */		 if ((q_()) && (EnchantmentManager.hasSilkTouchEnchantment(entityhuman.inventory))) {
/* 559 */			 ItemStack itemstack = c_(l);
/*		 */ 
/* 561 */			 if (itemstack != null)
/* 562 */				 a(world, i, j, k, itemstack);
/*		 */		 }
/*		 */		 else {
/* 565 */			 int i1 = EnchantmentManager.getBonusBlockLootEnchantmentLevel(entityhuman.inventory);
/*		 */ 
/* 567 */			 c(world, i, j, k, l, i1);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean q_() {
/* 572 */		 return (c()) && (!this.isTileEntity);
/*		 */	 }
/*		 */ 
/*		 */	 protected ItemStack c_(int i) {
/* 576 */		 int j = 0;
/*		 */ 
/* 578 */		 if ((this.id >= 0) && (this.id < Item.byId.length) && (Item.byId[this.id].k())) {
/* 579 */			 j = i;
/*		 */		 }
/*		 */ 
/* 582 */		 return new ItemStack(this.id, 1, j);
/*		 */	 }
/*		 */ 
/*		 */	 public int getDropCount(int i, Random random) {
/* 586 */		 return a(random);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean d(World world, int i, int j, int k) {
/* 590 */		 return true;
/*		 */	 }
/*		 */	 public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
/*		 */	 }
/*		 */ 
/*		 */	 public Block b(String s) {
/* 596 */		 this.name = ("tile." + s);
/* 597 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public String getName() {
/* 601 */		 return LocaleI18n.get(a() + ".name");
/*		 */	 }
/*		 */ 
/*		 */	 public String a() {
/* 605 */		 return this.name;
/*		 */	 }
/*		 */	 public void b(World world, int i, int j, int k, int l, int i1) {
/*		 */	 }
/*		 */ 
/*		 */	 public boolean u() {
/* 611 */		 return this.ce;
/*		 */	 }
/*		 */ 
/*		 */	 protected Block v() {
/* 615 */		 this.ce = false;
/* 616 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public int e() {
/* 620 */		 return this.material.getPushReaction();
/*		 */	 }
/*		 */	 public void a(World world, int i, int j, int k, Entity entity, float f) {
/*		 */	 }
/*		 */ 
/*		 */	 public Block a(CreativeModeTab creativemodetab) {
/* 626 */		 this.creativeTab = creativemodetab;
/* 627 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, int i, int j, int k, int l, EntityHuman entityhuman)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void h(World world, int i, int j, int k, int l)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void f(World world, int i, int j, int k)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public void a(World world, long i, long j)
/*		 */	 {
/*		 */	 }
/*		 */ 
/*		 */	 public static int getDropData(Block block, int data)
/*		 */	 {
/* 699 */		 return block.getDropData(data);
/*		 */	 }
/*		 */ 
/*		 */	 public int getExpDrop(World world, int data, int enchantmentLevel) {
/* 703 */		 return 0;
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/* 639 */		 Item.byId[WOOL.id] = new ItemCloth(WOOL.id - 256).b("cloth");
/* 640 */		 Item.byId[LOG.id] = new ItemLog(LOG.id - 256, LOG).b("log");
/* 641 */		 Item.byId[WOOD.id] = new ItemWood(WOOD.id - 256, WOOD).b("wood");
/* 642 */		 Item.byId[MONSTER_EGGS.id] = new ItemMonsterEggs(MONSTER_EGGS.id - 256).b("monsterStoneEgg");
/* 643 */		 Item.byId[SMOOTH_BRICK.id] = new ItemSmoothStone(SMOOTH_BRICK.id - 256, SMOOTH_BRICK).b("stonebricksmooth");
/* 644 */		 Item.byId[SANDSTONE.id] = new ItemSandStone(SANDSTONE.id - 256, SANDSTONE).b("sandStone");
/* 645 */		 Item.byId[STEP.id] = new ItemStep(STEP.id - 256, STEP, DOUBLE_STEP, false).b("stoneSlab");
/* 646 */		 Item.byId[DOUBLE_STEP.id] = new ItemStep(DOUBLE_STEP.id - 256, STEP, DOUBLE_STEP, true).b("stoneSlab");
/* 647 */		 Item.byId[WOOD_STEP.id] = new ItemStep(WOOD_STEP.id - 256, WOOD_STEP, WOOD_DOUBLE_STEP, false).b("woodSlab");
/* 648 */		 Item.byId[WOOD_DOUBLE_STEP.id] = new ItemStep(WOOD_DOUBLE_STEP.id - 256, WOOD_STEP, WOOD_DOUBLE_STEP, true).b("woodSlab");
/* 649 */		 Item.byId[SAPLING.id] = new ItemSapling(SAPLING.id - 256).b("sapling");
/* 650 */		 Item.byId[LEAVES.id] = new ItemLeaves(LEAVES.id - 256).b("leaves");
/* 651 */		 Item.byId[VINE.id] = new ItemWithAuxData(VINE.id - 256, false);
/* 652 */		 Item.byId[LONG_GRASS.id] = new ItemWithAuxData(LONG_GRASS.id - 256, true).a(new String[] { "shrub", "grass", "fern" });
/* 653 */		 Item.byId[WATER_LILY.id] = new ItemWaterLily(WATER_LILY.id - 256);
/* 654 */		 Item.byId[PISTON.id] = new ItemPiston(PISTON.id - 256);
/* 655 */		 Item.byId[PISTON_STICKY.id] = new ItemPiston(PISTON_STICKY.id - 256);
/* 656 */		 Item.byId[BIG_MUSHROOM_1.id] = new ItemWithAuxData(BIG_MUSHROOM_1.id - 256, false);
/* 657 */		 Item.byId[BIG_MUSHROOM_2.id] = new ItemWithAuxData(BIG_MUSHROOM_2.id - 256, false);
/* 658 */		 Item.byId[MOB_SPAWNER.id] = new ItemWithAuxData(MOB_SPAWNER.id - 256, false);
/*		 */ 
/* 660 */		 for (int i = 0; i < 256; i++) {
/* 661 */			 if (byId[i] != null) {
/* 662 */				 if (Item.byId[i] == null) {
/* 663 */					 Item.byId[i] = new ItemBlock(i - 256);
/* 664 */					 byId[i].r_();
/*		 */				 }
/*		 */ 
/* 667 */				 boolean flag = false;
/*		 */ 
/* 669 */				 if ((i > 0) && (byId[i].b() == 10)) {
/* 670 */					 flag = true;
/*		 */				 }
/*		 */ 
/* 673 */				 if ((i > 0) && ((byId[i] instanceof BlockStepAbstract))) {
/* 674 */					 flag = true;
/*		 */				 }
/*		 */ 
/* 677 */				 if (i == SOIL.id) {
/* 678 */					 flag = true;
/*		 */				 }
/*		 */ 
/* 681 */				 if (p[i] != 0) {
/* 682 */					 flag = true;
/*		 */				 }
/*		 */ 
/* 685 */				 if (lightBlock[i] == 0) {
/* 686 */					 flag = true;
/*		 */				 }
/*		 */ 
/* 689 */				 s[i] = flag;
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 693 */		 p[0] = true;
/* 694 */		 StatisticList.b();
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Block
 * JD-Core Version:		0.6.0
 */