/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ import java.util.Collections;
/*		 */ import java.util.HashMap;
/*		 */ import java.util.Map;
/*		 */ import java.util.Random;
/*		 */ 
/*		 */ public class EntityVillager extends EntityAgeable
/*		 */	 implements NPC, IMerchant
/*		 */ {
/*	33 */	 private int profession = 0;
/*		 */ 
/*	35 */	 private boolean f = false;
/*	36 */	 private boolean g = false;
/*	37 */	 Village village = null;
/*		 */	 private EntityHuman h;
/*		 */	 private MerchantRecipeList i;
/*		 */	 private int j;
/*		 */	 private boolean by;
/*		 */	 private int bz;
/*		 */	 private MerchantRecipe bA;
/* 375 */	 private static final Map bB = new HashMap();
/* 376 */	 private static final Map bC = new HashMap();
/*		 */ 
/*		 */	 public EntityVillager(World paramWorld)
/*		 */	 {
/*	48 */		 this(paramWorld, 0);
/*		 */	 }
/*		 */ 
/*		 */	 public EntityVillager(World paramWorld, int paramInt) {
/*	52 */		 super(paramWorld);
/*	53 */		 setProfession(paramInt);
/*	54 */		 this.texture = "/mob/villager/villager.png";
/*	55 */		 this.bw = 0.5F;
/*		 */ 
/*	57 */		 getNavigation().b(true);
/*	58 */		 getNavigation().a(true);
/*		 */ 
/*	60 */		 this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*	61 */		 this.goalSelector.a(1, new PathfinderGoalAvoidPlayer(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
/*	62 */		 this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
/*	63 */		 this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
/*	64 */		 this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
/*	65 */		 this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
/*	66 */		 this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
/*	67 */		 this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.3F));
/*	68 */		 this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
/*	69 */		 this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
/*	70 */		 this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32F));
/*	71 */		 this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
/*	72 */		 this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
/*	73 */		 this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.3F));
/*	74 */		 this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityLiving.class, 8.0F));
/*		 */	 }
/*		 */ 
/*		 */	 public boolean aV()
/*		 */	 {
/*	79 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 protected void bd()
/*		 */	 {
/*	84 */		 if (--this.profession <= 0) {
/*	85 */			 this.world.villages.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
/*	86 */			 this.profession = (70 + this.random.nextInt(50));
/*		 */ 
/*	88 */			 this.village = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
/*	89 */			 if (this.village == null) { aE();
/*		 */			 } else {
/*	91 */				 ChunkCoordinates localChunkCoordinates = this.village.getCenter();
/*	92 */				 b(localChunkCoordinates.x, localChunkCoordinates.y, localChunkCoordinates.z, this.village.getSize());
/*		 */			 }
/*		 */		 }
/*	95 */		 if ((!q()) && (this.j > 0)) {
/*	96 */			 this.j -= 1;
/*	97 */			 if (this.j <= 0) {
/*	98 */				 if (this.by) {
/*	99 */					 c(1);
/* 100 */					 this.by = false;
/*		 */				 }
/* 102 */				 if (this.bA != null) {
/* 103 */					 this.i.remove(this.bA);
/* 104 */					 this.bA = null;
/*		 */				 }
/* 106 */				 addEffect(new MobEffect(MobEffectList.REGENERATION.id, 200, 0));
/*		 */			 }
/*		 */		 }
/*		 */ 
/* 110 */		 super.bd();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean c(EntityHuman paramEntityHuman)
/*		 */	 {
/* 115 */		 if ((isAlive()) && (!q()) && (!isBaby())) {
/* 116 */			 if (!this.world.isStatic)
/*		 */			 {
/* 118 */				 a_(paramEntityHuman);
/* 119 */				 paramEntityHuman.openTrade(this);
/*		 */			 }
/* 121 */			 return true;
/*		 */		 }
/* 123 */		 return super.c(paramEntityHuman);
/*		 */	 }
/*		 */ 
/*		 */	 protected void a()
/*		 */	 {
/* 128 */		 super.a();
/* 129 */		 this.datawatcher.a(16, Integer.valueOf(0));
/*		 */	 }
/*		 */ 
/*		 */	 public int getMaxHealth()
/*		 */	 {
/* 134 */		 return 20;
/*		 */	 }
/*		 */ 
/*		 */	 public void b(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 139 */		 super.b(paramNBTTagCompound);
/* 140 */		 paramNBTTagCompound.setInt("Profession", getProfession());
/* 141 */		 paramNBTTagCompound.setInt("Riches", this.bz);
/* 142 */		 if (this.i != null)
/* 143 */			 paramNBTTagCompound.setCompound("Offers", this.i.a());
/*		 */	 }
/*		 */ 
/*		 */	 public void a(NBTTagCompound paramNBTTagCompound)
/*		 */	 {
/* 149 */		 super.a(paramNBTTagCompound);
/* 150 */		 setProfession(paramNBTTagCompound.getInt("Profession"));
/* 151 */		 this.bz = paramNBTTagCompound.getInt("Riches");
/* 152 */		 if (paramNBTTagCompound.hasKey("Offers")) {
/* 153 */			 NBTTagCompound localNBTTagCompound = paramNBTTagCompound.getCompound("Offers");
/* 154 */			 this.i = new MerchantRecipeList(localNBTTagCompound);
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 protected boolean ba()
/*		 */	 {
/* 178 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 protected String aQ()
/*		 */	 {
/* 183 */		 return "mob.villager.default";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aR()
/*		 */	 {
/* 188 */		 return "mob.villager.defaulthurt";
/*		 */	 }
/*		 */ 
/*		 */	 protected String aS()
/*		 */	 {
/* 193 */		 return "mob.villager.defaultdeath";
/*		 */	 }
/*		 */ 
/*		 */	 public void setProfession(int paramInt) {
/* 197 */		 this.datawatcher.watch(16, Integer.valueOf(paramInt));
/*		 */	 }
/*		 */ 
/*		 */	 public int getProfession() {
/* 201 */		 return this.datawatcher.getInt(16);
/*		 */	 }
/*		 */ 
/*		 */	 public boolean o() {
/* 205 */		 return this.f;
/*		 */	 }
/*		 */ 
/*		 */	 public void e(boolean paramBoolean) {
/* 209 */		 this.f = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public void f(boolean paramBoolean) {
/* 213 */		 this.g = paramBoolean;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean p() {
/* 217 */		 return this.g;
/*		 */	 }
/*		 */ 
/*		 */	 public void c(EntityLiving paramEntityLiving)
/*		 */	 {
/* 222 */		 super.c(paramEntityLiving);
/* 223 */		 if ((this.village != null) && (paramEntityLiving != null)) this.village.a(paramEntityLiving); 
/*		 */	 }
/*		 */ 
/*		 */	 public void a_(EntityHuman paramEntityHuman)
/*		 */	 {
/* 227 */		 this.h = paramEntityHuman;
/*		 */	 }
/*		 */ 
/*		 */	 public EntityHuman l_() {
/* 231 */		 return this.h;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean q() {
/* 235 */		 return this.h != null;
/*		 */	 }
/*		 */ 
/*		 */	 public void a(MerchantRecipe paramMerchantRecipe) {
/* 239 */		 paramMerchantRecipe.f();
/*		 */ 
/* 243 */		 if (paramMerchantRecipe.a((MerchantRecipe)this.i.get(this.i.size() - 1))) {
/* 244 */			 this.j = 60;
/* 245 */			 this.by = true;
/* 246 */		 } else if (this.i.size() > 1)
/*		 */		 {
/* 249 */			 int k = this.random.nextInt(6) + this.random.nextInt(6) + 3;
/* 250 */			 if (k <= paramMerchantRecipe.getUses()) {
/* 251 */				 this.j = 20;
/* 252 */				 this.bA = paramMerchantRecipe;
/*		 */			 }
/*		 */		 }
/* 255 */		 if (paramMerchantRecipe.getBuyItem1().id == Item.EMERALD.id)
/* 256 */			 this.bz += paramMerchantRecipe.getBuyItem1().count;
/*		 */	 }
/*		 */ 
/*		 */	 public MerchantRecipeList getOffers(EntityHuman paramEntityHuman)
/*		 */	 {
/* 261 */		 if (this.i == null) {
/* 262 */			 c(1);
/*		 */		 }
/* 264 */		 return this.i;
/*		 */	 }
/*		 */ 
/*		 */	 private void c(int paramInt) {
/* 268 */		 MerchantRecipeList localMerchantRecipeList = new MerchantRecipeList();
/* 269 */		 switch (getProfession()) {
/*		 */		 case 0:
/* 271 */			 a(localMerchantRecipeList, Item.WHEAT.id, this.random, 0.9F);
/* 272 */			 a(localMerchantRecipeList, Block.WOOL.id, this.random, 0.5F);
/* 273 */			 a(localMerchantRecipeList, Item.RAW_CHICKEN.id, this.random, 0.5F);
/* 274 */			 a(localMerchantRecipeList, Item.COOKED_FISH.id, this.random, 0.4F);
/* 275 */			 b(localMerchantRecipeList, Item.BREAD.id, this.random, 0.9F);
/* 276 */			 b(localMerchantRecipeList, Item.MELON.id, this.random, 0.3F);
/* 277 */			 b(localMerchantRecipeList, Item.APPLE.id, this.random, 0.3F);
/* 278 */			 b(localMerchantRecipeList, Item.COOKIE.id, this.random, 0.3F);
/* 279 */			 b(localMerchantRecipeList, Item.SHEARS.id, this.random, 0.3F);
/* 280 */			 b(localMerchantRecipeList, Item.FLINT_AND_STEEL.id, this.random, 0.3F);
/* 281 */			 b(localMerchantRecipeList, Item.COOKED_CHICKEN.id, this.random, 0.3F);
/* 282 */			 b(localMerchantRecipeList, Item.ARROW.id, this.random, 0.5F);
/* 283 */			 if (this.random.nextFloat() >= 0.5F) break;
/* 284 */			 localMerchantRecipeList.add(new MerchantRecipe(new ItemStack(Block.GRAVEL, 10), new ItemStack(Item.EMERALD), new ItemStack(Item.FLINT.id, 2 + this.random.nextInt(2), 0))); break;
/*		 */		 case 4:
/* 288 */			 a(localMerchantRecipeList, Item.COAL.id, this.random, 0.7F);
/* 289 */			 a(localMerchantRecipeList, Item.PORK.id, this.random, 0.5F);
/* 290 */			 a(localMerchantRecipeList, Item.RAW_BEEF.id, this.random, 0.5F);
/* 291 */			 b(localMerchantRecipeList, Item.SADDLE.id, this.random, 0.1F);
/* 292 */			 b(localMerchantRecipeList, Item.LEATHER_CHESTPLATE.id, this.random, 0.3F);
/* 293 */			 b(localMerchantRecipeList, Item.LEATHER_BOOTS.id, this.random, 0.3F);
/* 294 */			 b(localMerchantRecipeList, Item.LEATHER_HELMET.id, this.random, 0.3F);
/* 295 */			 b(localMerchantRecipeList, Item.LEATHER_LEGGINGS.id, this.random, 0.3F);
/* 296 */			 b(localMerchantRecipeList, Item.GRILLED_PORK.id, this.random, 0.3F);
/* 297 */			 b(localMerchantRecipeList, Item.COOKED_BEEF.id, this.random, 0.3F);
/* 298 */			 break;
/*		 */		 case 3:
/* 300 */			 a(localMerchantRecipeList, Item.COAL.id, this.random, 0.7F);
/* 301 */			 a(localMerchantRecipeList, Item.IRON_INGOT.id, this.random, 0.5F);
/* 302 */			 a(localMerchantRecipeList, Item.GOLD_INGOT.id, this.random, 0.5F);
/* 303 */			 a(localMerchantRecipeList, Item.DIAMOND.id, this.random, 0.5F);
/*		 */ 
/* 305 */			 b(localMerchantRecipeList, Item.IRON_SWORD.id, this.random, 0.5F);
/* 306 */			 b(localMerchantRecipeList, Item.DIAMOND_SWORD.id, this.random, 0.5F);
/* 307 */			 b(localMerchantRecipeList, Item.IRON_AXE.id, this.random, 0.3F);
/* 308 */			 b(localMerchantRecipeList, Item.DIAMOND_AXE.id, this.random, 0.3F);
/* 309 */			 b(localMerchantRecipeList, Item.IRON_PICKAXE.id, this.random, 0.5F);
/* 310 */			 b(localMerchantRecipeList, Item.DIAMOND_PICKAXE.id, this.random, 0.5F);
/* 311 */			 b(localMerchantRecipeList, Item.IRON_SPADE.id, this.random, 0.2F);
/* 312 */			 b(localMerchantRecipeList, Item.DIAMOND_SPADE.id, this.random, 0.2F);
/* 313 */			 b(localMerchantRecipeList, Item.IRON_HOE.id, this.random, 0.2F);
/* 314 */			 b(localMerchantRecipeList, Item.DIAMOND_HOE.id, this.random, 0.2F);
/* 315 */			 b(localMerchantRecipeList, Item.IRON_BOOTS.id, this.random, 0.2F);
/* 316 */			 b(localMerchantRecipeList, Item.DIAMOND_BOOTS.id, this.random, 0.2F);
/* 317 */			 b(localMerchantRecipeList, Item.IRON_HELMET.id, this.random, 0.2F);
/* 318 */			 b(localMerchantRecipeList, Item.DIAMOND_HELMET.id, this.random, 0.2F);
/* 319 */			 b(localMerchantRecipeList, Item.IRON_CHESTPLATE.id, this.random, 0.2F);
/* 320 */			 b(localMerchantRecipeList, Item.DIAMOND_CHESTPLATE.id, this.random, 0.2F);
/* 321 */			 b(localMerchantRecipeList, Item.IRON_LEGGINGS.id, this.random, 0.2F);
/* 322 */			 b(localMerchantRecipeList, Item.DIAMOND_LEGGINGS.id, this.random, 0.2F);
/* 323 */			 b(localMerchantRecipeList, Item.CHAINMAIL_BOOTS.id, this.random, 0.1F);
/* 324 */			 b(localMerchantRecipeList, Item.CHAINMAIL_HELMET.id, this.random, 0.1F);
/* 325 */			 b(localMerchantRecipeList, Item.CHAINMAIL_CHESTPLATE.id, this.random, 0.1F);
/* 326 */			 b(localMerchantRecipeList, Item.CHAINMAIL_LEGGINGS.id, this.random, 0.1F);
/* 327 */			 break;
/*		 */		 case 1:
/* 329 */			 a(localMerchantRecipeList, Item.PAPER.id, this.random, 0.8F);
/* 330 */			 a(localMerchantRecipeList, Item.BOOK.id, this.random, 0.8F);
/* 331 */			 a(localMerchantRecipeList, Item.WRITTEN_BOOK.id, this.random, 0.3F);
/* 332 */			 b(localMerchantRecipeList, Block.BOOKSHELF.id, this.random, 0.8F);
/* 333 */			 b(localMerchantRecipeList, Block.GLASS.id, this.random, 0.2F);
/* 334 */			 b(localMerchantRecipeList, Item.COMPASS.id, this.random, 0.2F);
/* 335 */			 b(localMerchantRecipeList, Item.WATCH.id, this.random, 0.2F);
/* 336 */			 break;
/*		 */		 case 2:
/* 338 */			 b(localMerchantRecipeList, Item.EYE_OF_ENDER.id, this.random, 0.3F);
/* 339 */			 b(localMerchantRecipeList, Item.EXP_BOTTLE.id, this.random, 0.2F);
/* 340 */			 b(localMerchantRecipeList, Item.REDSTONE.id, this.random, 0.4F);
/* 341 */			 b(localMerchantRecipeList, Block.GLOWSTONE.id, this.random, 0.3F);
/*		 */ 
/* 343 */			 int[] arrayOfInt1 = { Item.IRON_SWORD.id, Item.DIAMOND_SWORD.id, Item.IRON_CHESTPLATE.id, Item.DIAMOND_CHESTPLATE.id, Item.IRON_AXE.id, Item.DIAMOND_AXE.id, Item.IRON_PICKAXE.id, Item.DIAMOND_PICKAXE.id };
/*		 */ 
/* 347 */			 for (int i1 : arrayOfInt1) {
/* 348 */				 if (this.random.nextFloat() < 0.1F) {
/* 349 */					 localMerchantRecipeList.add(new MerchantRecipe(new ItemStack(i1, 1, 0), new ItemStack(Item.EMERALD, 2 + this.random.nextInt(3), 0), EnchantmentManager.a(this.random, new ItemStack(i1, 1, 0), 5 + this.random.nextInt(15))));
/*		 */				 }
/*		 */ 
/*		 */			 }
/*		 */ 
/*		 */		 }
/*		 */ 
/* 357 */		 if (localMerchantRecipeList.isEmpty()) {
/* 358 */			 a(localMerchantRecipeList, Item.GOLD_INGOT.id, this.random, 1.0F);
/*		 */		 }
/*		 */ 
/* 362 */		 Collections.shuffle(localMerchantRecipeList);
/*		 */ 
/* 364 */		 if (this.i == null) {
/* 365 */			 this.i = new MerchantRecipeList();
/*		 */		 }
/* 367 */		 for (int k = 0; (k < paramInt) && (k < localMerchantRecipeList.size()); k++)
/* 368 */			 this.i.a((MerchantRecipe)localMerchantRecipeList.get(k));
/*		 */	 }
/*		 */ 
/*		 */	 private static void a(MerchantRecipeList paramMerchantRecipeList, int paramInt, Random paramRandom, float paramFloat)
/*		 */	 {
/* 454 */		 if (paramRandom.nextFloat() < paramFloat)
/* 455 */			 paramMerchantRecipeList.add(new MerchantRecipe(a(paramInt, paramRandom), Item.EMERALD));
/*		 */	 }
/*		 */ 
/*		 */	 private static ItemStack a(int paramInt, Random paramRandom)
/*		 */	 {
/* 460 */		 return new ItemStack(paramInt, b(paramInt, paramRandom), 0);
/*		 */	 }
/*		 */ 
/*		 */	 private static int b(int paramInt, Random paramRandom) {
/* 464 */		 Tuple localTuple = (Tuple)bB.get(Integer.valueOf(paramInt));
/* 465 */		 if (localTuple == null) {
/* 466 */			 return 1;
/*		 */		 }
/* 468 */		 if (((Integer)localTuple.a()).intValue() >= ((Integer)localTuple.b()).intValue()) {
/* 469 */			 return ((Integer)localTuple.a()).intValue();
/*		 */		 }
/* 471 */		 return ((Integer)localTuple.a()).intValue() + paramRandom.nextInt(((Integer)localTuple.b()).intValue() - ((Integer)localTuple.a()).intValue());
/*		 */	 }
/*		 */ 
/*		 */	 private static void b(MerchantRecipeList paramMerchantRecipeList, int paramInt, Random paramRandom, float paramFloat)
/*		 */	 {
/* 484 */		 if (paramRandom.nextFloat() < paramFloat) {
/* 485 */			 int k = c(paramInt, paramRandom);
/*		 */			 ItemStack localItemStack1;
/*		 */			 ItemStack localItemStack2;
/* 488 */			 if (k < 0) {
/* 489 */				 localItemStack1 = new ItemStack(Item.EMERALD.id, 1, 0);
/* 490 */				 localItemStack2 = new ItemStack(paramInt, -k, 0);
/*		 */			 } else {
/* 492 */				 localItemStack1 = new ItemStack(Item.EMERALD.id, k, 0);
/* 493 */				 localItemStack2 = new ItemStack(paramInt, 1, 0);
/*		 */			 }
/* 495 */			 paramMerchantRecipeList.add(new MerchantRecipe(localItemStack1, localItemStack2));
/*		 */		 }
/*		 */	 }
/*		 */ 
/*		 */	 private static int c(int paramInt, Random paramRandom) {
/* 500 */		 Tuple localTuple = (Tuple)bC.get(Integer.valueOf(paramInt));
/* 501 */		 if (localTuple == null) {
/* 502 */			 return 1;
/*		 */		 }
/* 504 */		 if (((Integer)localTuple.a()).intValue() >= ((Integer)localTuple.b()).intValue()) {
/* 505 */			 return ((Integer)localTuple.a()).intValue();
/*		 */		 }
/* 507 */		 return ((Integer)localTuple.a()).intValue() + paramRandom.nextInt(((Integer)localTuple.b()).intValue() - ((Integer)localTuple.a()).intValue());
/*		 */	 }
/*		 */ 
/*		 */	 static
/*		 */	 {
/* 378 */		 bB.put(Integer.valueOf(Item.COAL.id), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
/* 379 */		 bB.put(Integer.valueOf(Item.IRON_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 380 */		 bB.put(Integer.valueOf(Item.GOLD_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 381 */		 bB.put(Integer.valueOf(Item.DIAMOND.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 382 */		 bB.put(Integer.valueOf(Item.PAPER.id), new Tuple(Integer.valueOf(19), Integer.valueOf(30)));
/* 383 */		 bB.put(Integer.valueOf(Item.BOOK.id), new Tuple(Integer.valueOf(12), Integer.valueOf(15)));
/* 384 */		 bB.put(Integer.valueOf(Item.WRITTEN_BOOK.id), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
/* 385 */		 bB.put(Integer.valueOf(Item.ENDER_PEARL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 386 */		 bB.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
/* 387 */		 bB.put(Integer.valueOf(Item.PORK.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
/* 388 */		 bB.put(Integer.valueOf(Item.RAW_BEEF.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
/* 389 */		 bB.put(Integer.valueOf(Item.RAW_CHICKEN.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
/* 390 */		 bB.put(Integer.valueOf(Item.COOKED_FISH.id), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
/* 391 */		 bB.put(Integer.valueOf(Item.SEEDS.id), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
/* 392 */		 bB.put(Integer.valueOf(Item.MELON_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
/* 393 */		 bB.put(Integer.valueOf(Item.PUMPKIN_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
/* 394 */		 bB.put(Integer.valueOf(Item.WHEAT.id), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
/* 395 */		 bB.put(Integer.valueOf(Block.WOOL.id), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
/* 396 */		 bB.put(Integer.valueOf(Item.ROTTEN_FLESH.id), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));
/*		 */ 
/* 398 */		 bC.put(Integer.valueOf(Item.FLINT_AND_STEEL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 399 */		 bC.put(Integer.valueOf(Item.SHEARS.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 400 */		 bC.put(Integer.valueOf(Item.IRON_SWORD.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/* 401 */		 bC.put(Integer.valueOf(Item.DIAMOND_SWORD.id), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
/* 402 */		 bC.put(Integer.valueOf(Item.IRON_AXE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/* 403 */		 bC.put(Integer.valueOf(Item.DIAMOND_AXE.id), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
/* 404 */		 bC.put(Integer.valueOf(Item.IRON_PICKAXE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
/* 405 */		 bC.put(Integer.valueOf(Item.DIAMOND_PICKAXE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 406 */		 bC.put(Integer.valueOf(Item.IRON_SPADE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 407 */		 bC.put(Integer.valueOf(Item.DIAMOND_SPADE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 408 */		 bC.put(Integer.valueOf(Item.IRON_HOE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 409 */		 bC.put(Integer.valueOf(Item.DIAMOND_HOE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 410 */		 bC.put(Integer.valueOf(Item.IRON_BOOTS.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 411 */		 bC.put(Integer.valueOf(Item.DIAMOND_BOOTS.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 412 */		 bC.put(Integer.valueOf(Item.IRON_HELMET.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
/* 413 */		 bC.put(Integer.valueOf(Item.DIAMOND_HELMET.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
/* 414 */		 bC.put(Integer.valueOf(Item.IRON_CHESTPLATE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
/* 415 */		 bC.put(Integer.valueOf(Item.DIAMOND_CHESTPLATE.id), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
/* 416 */		 bC.put(Integer.valueOf(Item.IRON_LEGGINGS.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
/* 417 */		 bC.put(Integer.valueOf(Item.DIAMOND_LEGGINGS.id), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
/* 418 */		 bC.put(Integer.valueOf(Item.CHAINMAIL_BOOTS.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/* 419 */		 bC.put(Integer.valueOf(Item.CHAINMAIL_HELMET.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
/* 420 */		 bC.put(Integer.valueOf(Item.CHAINMAIL_CHESTPLATE.id), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
/* 421 */		 bC.put(Integer.valueOf(Item.CHAINMAIL_LEGGINGS.id), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
/* 422 */		 bC.put(Integer.valueOf(Item.BREAD.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
/* 423 */		 bC.put(Integer.valueOf(Item.MELON.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
/* 424 */		 bC.put(Integer.valueOf(Item.APPLE.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
/* 425 */		 bC.put(Integer.valueOf(Item.COOKIE.id), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
/* 426 */		 bC.put(Integer.valueOf(Block.GLASS.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
/* 427 */		 bC.put(Integer.valueOf(Block.BOOKSHELF.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
/* 428 */		 bC.put(Integer.valueOf(Item.LEATHER_CHESTPLATE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
/* 429 */		 bC.put(Integer.valueOf(Item.LEATHER_BOOTS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 430 */		 bC.put(Integer.valueOf(Item.LEATHER_HELMET.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 431 */		 bC.put(Integer.valueOf(Item.LEATHER_LEGGINGS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
/* 432 */		 bC.put(Integer.valueOf(Item.SADDLE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
/* 433 */		 bC.put(Integer.valueOf(Item.EXP_BOTTLE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
/* 434 */		 bC.put(Integer.valueOf(Item.REDSTONE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
/* 435 */		 bC.put(Integer.valueOf(Item.COMPASS.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 436 */		 bC.put(Integer.valueOf(Item.WATCH.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
/* 437 */		 bC.put(Integer.valueOf(Block.GLOWSTONE.id), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
/* 438 */		 bC.put(Integer.valueOf(Item.GRILLED_PORK.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/* 439 */		 bC.put(Integer.valueOf(Item.COOKED_BEEF.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
/* 440 */		 bC.put(Integer.valueOf(Item.COOKED_CHICKEN.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
/* 441 */		 bC.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
/* 442 */		 bC.put(Integer.valueOf(Item.ARROW.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-19)));
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityVillager
 * JD-Core Version:		0.6.0
 */