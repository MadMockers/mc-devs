package net.minecraft.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EntityVillager extends EntityAgeable
	implements NPC, IMerchant
{
	private int profession = 0;

	private boolean f = false;
	private boolean g = false;
	Village village = null;
	private EntityHuman h;
	private MerchantRecipeList i;
	private int j;
	private boolean by;
	private int bz;
	private MerchantRecipe bA;
	private static final Map bB = new HashMap();
	private static final Map bC = new HashMap();

	public EntityVillager(World paramWorld)
	{
		this(paramWorld, 0);
	}

	public EntityVillager(World paramWorld, int paramInt) {
		super(paramWorld);
		setProfession(paramInt);
		this.texture = "/mob/villager/villager.png";
		this.bw = 0.5F;

		getNavigation().b(true);
		getNavigation().a(true);

		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalAvoidPlayer(this, EntityZombie.class, 8.0F, 0.3F, 0.35F));
		this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
		this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
		this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
		this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
		this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
		this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.3F));
		this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
		this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
		this.goalSelector.a(8, new PathfinderGoalPlay(this, 0.32F));
		this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
		this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
		this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.3F));
		this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityLiving.class, 8.0F));
	}

	public boolean aV()
	{
		return true;
	}

	protected void bd()
	{
		if (--this.profession <= 0) {
			this.world.villages.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
			this.profession = (70 + this.random.nextInt(50));

			this.village = this.world.villages.getClosestVillage(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ), 32);
			if (this.village == null) { aE();
			} else {
				ChunkCoordinates localChunkCoordinates = this.village.getCenter();
				b(localChunkCoordinates.x, localChunkCoordinates.y, localChunkCoordinates.z, this.village.getSize());
			}
		}
		if ((!q()) && (this.j > 0)) {
			this.j -= 1;
			if (this.j <= 0) {
				if (this.by) {
					c(1);
					this.by = false;
				}
				if (this.bA != null) {
					this.i.remove(this.bA);
					this.bA = null;
				}
				addEffect(new MobEffect(MobEffectList.REGENERATION.id, 200, 0));
			}
		}

		super.bd();
	}

	public boolean c(EntityHuman paramEntityHuman)
	{
		if ((isAlive()) && (!q()) && (!isBaby())) {
			if (!this.world.isStatic)
			{
				a_(paramEntityHuman);
				paramEntityHuman.openTrade(this);
			}
			return true;
		}
		return super.c(paramEntityHuman);
	}

	protected void a()
	{
		super.a();
		this.datawatcher.a(16, Integer.valueOf(0));
	}

	public int getMaxHealth()
	{
		return 20;
	}

	public void b(NBTTagCompound paramNBTTagCompound)
	{
		super.b(paramNBTTagCompound);
		paramNBTTagCompound.setInt("Profession", getProfession());
		paramNBTTagCompound.setInt("Riches", this.bz);
		if (this.i != null)
			paramNBTTagCompound.setCompound("Offers", this.i.a());
	}

	public void a(NBTTagCompound paramNBTTagCompound)
	{
		super.a(paramNBTTagCompound);
		setProfession(paramNBTTagCompound.getInt("Profession"));
		this.bz = paramNBTTagCompound.getInt("Riches");
		if (paramNBTTagCompound.hasKey("Offers")) {
			NBTTagCompound localNBTTagCompound = paramNBTTagCompound.getCompound("Offers");
			this.i = new MerchantRecipeList(localNBTTagCompound);
		}
	}

	protected boolean ba()
	{
		return false;
	}

	protected String aQ()
	{
		return "mob.villager.default";
	}

	protected String aR()
	{
		return "mob.villager.defaulthurt";
	}

	protected String aS()
	{
		return "mob.villager.defaultdeath";
	}

	public void setProfession(int paramInt) {
		this.datawatcher.watch(16, Integer.valueOf(paramInt));
	}

	public int getProfession() {
		return this.datawatcher.getInt(16);
	}

	public boolean o() {
		return this.f;
	}

	public void e(boolean paramBoolean) {
		this.f = paramBoolean;
	}

	public void f(boolean paramBoolean) {
		this.g = paramBoolean;
	}

	public boolean p() {
		return this.g;
	}

	public void c(EntityLiving paramEntityLiving)
	{
		super.c(paramEntityLiving);
		if ((this.village != null) && (paramEntityLiving != null)) this.village.a(paramEntityLiving); 
	}

	public void a_(EntityHuman paramEntityHuman)
	{
		this.h = paramEntityHuman;
	}

	public EntityHuman l_() {
		return this.h;
	}

	public boolean q() {
		return this.h != null;
	}

	public void a(MerchantRecipe paramMerchantRecipe) {
		paramMerchantRecipe.f();

		if (paramMerchantRecipe.a((MerchantRecipe)this.i.get(this.i.size() - 1))) {
			this.j = 60;
			this.by = true;
		} else if (this.i.size() > 1)
		{
			int k = this.random.nextInt(6) + this.random.nextInt(6) + 3;
			if (k <= paramMerchantRecipe.getUses()) {
				this.j = 20;
				this.bA = paramMerchantRecipe;
			}
		}
		if (paramMerchantRecipe.getBuyItem1().id == Item.EMERALD.id)
			this.bz += paramMerchantRecipe.getBuyItem1().count;
	}

	public MerchantRecipeList getOffers(EntityHuman paramEntityHuman)
	{
		if (this.i == null) {
			c(1);
		}
		return this.i;
	}

	private void c(int paramInt) {
		MerchantRecipeList localMerchantRecipeList = new MerchantRecipeList();
		switch (getProfession()) {
		case 0:
			a(localMerchantRecipeList, Item.WHEAT.id, this.random, 0.9F);
			a(localMerchantRecipeList, Block.WOOL.id, this.random, 0.5F);
			a(localMerchantRecipeList, Item.RAW_CHICKEN.id, this.random, 0.5F);
			a(localMerchantRecipeList, Item.COOKED_FISH.id, this.random, 0.4F);
			b(localMerchantRecipeList, Item.BREAD.id, this.random, 0.9F);
			b(localMerchantRecipeList, Item.MELON.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.APPLE.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.COOKIE.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.SHEARS.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.FLINT_AND_STEEL.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.COOKED_CHICKEN.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.ARROW.id, this.random, 0.5F);
			if (this.random.nextFloat() >= 0.5F) break;
			localMerchantRecipeList.add(new MerchantRecipe(new ItemStack(Block.GRAVEL, 10), new ItemStack(Item.EMERALD), new ItemStack(Item.FLINT.id, 2 + this.random.nextInt(2), 0))); break;
		case 4:
			a(localMerchantRecipeList, Item.COAL.id, this.random, 0.7F);
			a(localMerchantRecipeList, Item.PORK.id, this.random, 0.5F);
			a(localMerchantRecipeList, Item.RAW_BEEF.id, this.random, 0.5F);
			b(localMerchantRecipeList, Item.SADDLE.id, this.random, 0.1F);
			b(localMerchantRecipeList, Item.LEATHER_CHESTPLATE.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.LEATHER_BOOTS.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.LEATHER_HELMET.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.LEATHER_LEGGINGS.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.GRILLED_PORK.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.COOKED_BEEF.id, this.random, 0.3F);
			break;
		case 3:
			a(localMerchantRecipeList, Item.COAL.id, this.random, 0.7F);
			a(localMerchantRecipeList, Item.IRON_INGOT.id, this.random, 0.5F);
			a(localMerchantRecipeList, Item.GOLD_INGOT.id, this.random, 0.5F);
			a(localMerchantRecipeList, Item.DIAMOND.id, this.random, 0.5F);

			b(localMerchantRecipeList, Item.IRON_SWORD.id, this.random, 0.5F);
			b(localMerchantRecipeList, Item.DIAMOND_SWORD.id, this.random, 0.5F);
			b(localMerchantRecipeList, Item.IRON_AXE.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.DIAMOND_AXE.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.IRON_PICKAXE.id, this.random, 0.5F);
			b(localMerchantRecipeList, Item.DIAMOND_PICKAXE.id, this.random, 0.5F);
			b(localMerchantRecipeList, Item.IRON_SPADE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.DIAMOND_SPADE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.IRON_HOE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.DIAMOND_HOE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.IRON_BOOTS.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.DIAMOND_BOOTS.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.IRON_HELMET.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.DIAMOND_HELMET.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.IRON_CHESTPLATE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.DIAMOND_CHESTPLATE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.IRON_LEGGINGS.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.DIAMOND_LEGGINGS.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.CHAINMAIL_BOOTS.id, this.random, 0.1F);
			b(localMerchantRecipeList, Item.CHAINMAIL_HELMET.id, this.random, 0.1F);
			b(localMerchantRecipeList, Item.CHAINMAIL_CHESTPLATE.id, this.random, 0.1F);
			b(localMerchantRecipeList, Item.CHAINMAIL_LEGGINGS.id, this.random, 0.1F);
			break;
		case 1:
			a(localMerchantRecipeList, Item.PAPER.id, this.random, 0.8F);
			a(localMerchantRecipeList, Item.BOOK.id, this.random, 0.8F);
			a(localMerchantRecipeList, Item.WRITTEN_BOOK.id, this.random, 0.3F);
			b(localMerchantRecipeList, Block.BOOKSHELF.id, this.random, 0.8F);
			b(localMerchantRecipeList, Block.GLASS.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.COMPASS.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.WATCH.id, this.random, 0.2F);
			break;
		case 2:
			b(localMerchantRecipeList, Item.EYE_OF_ENDER.id, this.random, 0.3F);
			b(localMerchantRecipeList, Item.EXP_BOTTLE.id, this.random, 0.2F);
			b(localMerchantRecipeList, Item.REDSTONE.id, this.random, 0.4F);
			b(localMerchantRecipeList, Block.GLOWSTONE.id, this.random, 0.3F);

			int[] arrayOfInt1 = { Item.IRON_SWORD.id, Item.DIAMOND_SWORD.id, Item.IRON_CHESTPLATE.id, Item.DIAMOND_CHESTPLATE.id, Item.IRON_AXE.id, Item.DIAMOND_AXE.id, Item.IRON_PICKAXE.id, Item.DIAMOND_PICKAXE.id };

			for (int i1 : arrayOfInt1) {
				if (this.random.nextFloat() < 0.1F) {
					localMerchantRecipeList.add(new MerchantRecipe(new ItemStack(i1, 1, 0), new ItemStack(Item.EMERALD, 2 + this.random.nextInt(3), 0), EnchantmentManager.a(this.random, new ItemStack(i1, 1, 0), 5 + this.random.nextInt(15))));
				}

			}

		}

		if (localMerchantRecipeList.isEmpty()) {
			a(localMerchantRecipeList, Item.GOLD_INGOT.id, this.random, 1.0F);
		}

		Collections.shuffle(localMerchantRecipeList);

		if (this.i == null) {
			this.i = new MerchantRecipeList();
		}
		for (int k = 0; (k < paramInt) && (k < localMerchantRecipeList.size()); k++)
			this.i.a((MerchantRecipe)localMerchantRecipeList.get(k));
	}

	private static void a(MerchantRecipeList paramMerchantRecipeList, int paramInt, Random paramRandom, float paramFloat)
	{
		if (paramRandom.nextFloat() < paramFloat)
			paramMerchantRecipeList.add(new MerchantRecipe(a(paramInt, paramRandom), Item.EMERALD));
	}

	private static ItemStack a(int paramInt, Random paramRandom)
	{
		return new ItemStack(paramInt, b(paramInt, paramRandom), 0);
	}

	private static int b(int paramInt, Random paramRandom) {
		Tuple localTuple = (Tuple)bB.get(Integer.valueOf(paramInt));
		if (localTuple == null) {
			return 1;
		}
		if (((Integer)localTuple.a()).intValue() >= ((Integer)localTuple.b()).intValue()) {
			return ((Integer)localTuple.a()).intValue();
		}
		return ((Integer)localTuple.a()).intValue() + paramRandom.nextInt(((Integer)localTuple.b()).intValue() - ((Integer)localTuple.a()).intValue());
	}

	private static void b(MerchantRecipeList paramMerchantRecipeList, int paramInt, Random paramRandom, float paramFloat)
	{
		if (paramRandom.nextFloat() < paramFloat) {
			int k = c(paramInt, paramRandom);
			ItemStack localItemStack1;
			ItemStack localItemStack2;
			if (k < 0) {
				localItemStack1 = new ItemStack(Item.EMERALD.id, 1, 0);
				localItemStack2 = new ItemStack(paramInt, -k, 0);
			} else {
				localItemStack1 = new ItemStack(Item.EMERALD.id, k, 0);
				localItemStack2 = new ItemStack(paramInt, 1, 0);
			}
			paramMerchantRecipeList.add(new MerchantRecipe(localItemStack1, localItemStack2));
		}
	}

	private static int c(int paramInt, Random paramRandom) {
		Tuple localTuple = (Tuple)bC.get(Integer.valueOf(paramInt));
		if (localTuple == null) {
			return 1;
		}
		if (((Integer)localTuple.a()).intValue() >= ((Integer)localTuple.b()).intValue()) {
			return ((Integer)localTuple.a()).intValue();
		}
		return ((Integer)localTuple.a()).intValue() + paramRandom.nextInt(((Integer)localTuple.b()).intValue() - ((Integer)localTuple.a()).intValue());
	}

	static
	{
		bB.put(Integer.valueOf(Item.COAL.id), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
		bB.put(Integer.valueOf(Item.IRON_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
		bB.put(Integer.valueOf(Item.GOLD_INGOT.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
		bB.put(Integer.valueOf(Item.DIAMOND.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		bB.put(Integer.valueOf(Item.PAPER.id), new Tuple(Integer.valueOf(19), Integer.valueOf(30)));
		bB.put(Integer.valueOf(Item.BOOK.id), new Tuple(Integer.valueOf(12), Integer.valueOf(15)));
		bB.put(Integer.valueOf(Item.WRITTEN_BOOK.id), new Tuple(Integer.valueOf(1), Integer.valueOf(1)));
		bB.put(Integer.valueOf(Item.ENDER_PEARL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		bB.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(2), Integer.valueOf(3)));
		bB.put(Integer.valueOf(Item.PORK.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
		bB.put(Integer.valueOf(Item.RAW_BEEF.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
		bB.put(Integer.valueOf(Item.RAW_CHICKEN.id), new Tuple(Integer.valueOf(14), Integer.valueOf(18)));
		bB.put(Integer.valueOf(Item.COOKED_FISH.id), new Tuple(Integer.valueOf(9), Integer.valueOf(13)));
		bB.put(Integer.valueOf(Item.SEEDS.id), new Tuple(Integer.valueOf(34), Integer.valueOf(48)));
		bB.put(Integer.valueOf(Item.MELON_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
		bB.put(Integer.valueOf(Item.PUMPKIN_SEEDS.id), new Tuple(Integer.valueOf(30), Integer.valueOf(38)));
		bB.put(Integer.valueOf(Item.WHEAT.id), new Tuple(Integer.valueOf(18), Integer.valueOf(22)));
		bB.put(Integer.valueOf(Block.WOOL.id), new Tuple(Integer.valueOf(14), Integer.valueOf(22)));
		bB.put(Integer.valueOf(Item.ROTTEN_FLESH.id), new Tuple(Integer.valueOf(36), Integer.valueOf(64)));

		bC.put(Integer.valueOf(Item.FLINT_AND_STEEL.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		bC.put(Integer.valueOf(Item.SHEARS.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		bC.put(Integer.valueOf(Item.IRON_SWORD.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
		bC.put(Integer.valueOf(Item.DIAMOND_SWORD.id), new Tuple(Integer.valueOf(12), Integer.valueOf(14)));
		bC.put(Integer.valueOf(Item.IRON_AXE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
		bC.put(Integer.valueOf(Item.DIAMOND_AXE.id), new Tuple(Integer.valueOf(9), Integer.valueOf(12)));
		bC.put(Integer.valueOf(Item.IRON_PICKAXE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(9)));
		bC.put(Integer.valueOf(Item.DIAMOND_PICKAXE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
		bC.put(Integer.valueOf(Item.IRON_SPADE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		bC.put(Integer.valueOf(Item.DIAMOND_SPADE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		bC.put(Integer.valueOf(Item.IRON_HOE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		bC.put(Integer.valueOf(Item.DIAMOND_HOE.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		bC.put(Integer.valueOf(Item.IRON_BOOTS.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		bC.put(Integer.valueOf(Item.DIAMOND_BOOTS.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		bC.put(Integer.valueOf(Item.IRON_HELMET.id), new Tuple(Integer.valueOf(4), Integer.valueOf(6)));
		bC.put(Integer.valueOf(Item.DIAMOND_HELMET.id), new Tuple(Integer.valueOf(7), Integer.valueOf(8)));
		bC.put(Integer.valueOf(Item.IRON_CHESTPLATE.id), new Tuple(Integer.valueOf(10), Integer.valueOf(14)));
		bC.put(Integer.valueOf(Item.DIAMOND_CHESTPLATE.id), new Tuple(Integer.valueOf(16), Integer.valueOf(19)));
		bC.put(Integer.valueOf(Item.IRON_LEGGINGS.id), new Tuple(Integer.valueOf(8), Integer.valueOf(10)));
		bC.put(Integer.valueOf(Item.DIAMOND_LEGGINGS.id), new Tuple(Integer.valueOf(11), Integer.valueOf(14)));
		bC.put(Integer.valueOf(Item.CHAINMAIL_BOOTS.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
		bC.put(Integer.valueOf(Item.CHAINMAIL_HELMET.id), new Tuple(Integer.valueOf(5), Integer.valueOf(7)));
		bC.put(Integer.valueOf(Item.CHAINMAIL_CHESTPLATE.id), new Tuple(Integer.valueOf(11), Integer.valueOf(15)));
		bC.put(Integer.valueOf(Item.CHAINMAIL_LEGGINGS.id), new Tuple(Integer.valueOf(9), Integer.valueOf(11)));
		bC.put(Integer.valueOf(Item.BREAD.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-2)));
		bC.put(Integer.valueOf(Item.MELON.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
		bC.put(Integer.valueOf(Item.APPLE.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-4)));
		bC.put(Integer.valueOf(Item.COOKIE.id), new Tuple(Integer.valueOf(-10), Integer.valueOf(-7)));
		bC.put(Integer.valueOf(Block.GLASS.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-3)));
		bC.put(Integer.valueOf(Block.BOOKSHELF.id), new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
		bC.put(Integer.valueOf(Item.LEATHER_CHESTPLATE.id), new Tuple(Integer.valueOf(4), Integer.valueOf(5)));
		bC.put(Integer.valueOf(Item.LEATHER_BOOTS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
		bC.put(Integer.valueOf(Item.LEATHER_HELMET.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
		bC.put(Integer.valueOf(Item.LEATHER_LEGGINGS.id), new Tuple(Integer.valueOf(2), Integer.valueOf(4)));
		bC.put(Integer.valueOf(Item.SADDLE.id), new Tuple(Integer.valueOf(6), Integer.valueOf(8)));
		bC.put(Integer.valueOf(Item.EXP_BOTTLE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
		bC.put(Integer.valueOf(Item.REDSTONE.id), new Tuple(Integer.valueOf(-4), Integer.valueOf(-1)));
		bC.put(Integer.valueOf(Item.COMPASS.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
		bC.put(Integer.valueOf(Item.WATCH.id), new Tuple(Integer.valueOf(10), Integer.valueOf(12)));
		bC.put(Integer.valueOf(Block.GLOWSTONE.id), new Tuple(Integer.valueOf(-3), Integer.valueOf(-1)));
		bC.put(Integer.valueOf(Item.GRILLED_PORK.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
		bC.put(Integer.valueOf(Item.COOKED_BEEF.id), new Tuple(Integer.valueOf(-7), Integer.valueOf(-5)));
		bC.put(Integer.valueOf(Item.COOKED_CHICKEN.id), new Tuple(Integer.valueOf(-8), Integer.valueOf(-6)));
		bC.put(Integer.valueOf(Item.EYE_OF_ENDER.id), new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
		bC.put(Integer.valueOf(Item.ARROW.id), new Tuple(Integer.valueOf(-5), Integer.valueOf(-19)));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityVillager
 * JD-Core Version:		0.6.0
 */