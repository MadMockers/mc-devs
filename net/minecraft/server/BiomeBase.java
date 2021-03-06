package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BiomeBase
{
	public static final BiomeBase[] biomes = new BiomeBase[256];

	public static final BiomeBase OCEAN = new BiomeOcean(0).b(112).a("Ocean").b(-1.0F, 0.4F);
	public static final BiomeBase PLAINS = new BiomePlains(1).b(9286496).a("Plains").a(0.8F, 0.4F);
	public static final BiomeBase DESERT = new BiomeDesert(2).b(16421912).a("Desert").m().a(2.0F, 0.0F).b(0.1F, 0.2F);

	public static final BiomeBase EXTREME_HILLS = new BiomeBigHills(3).b(6316128).a("Extreme Hills").b(0.3F, 1.5F).a(0.2F, 0.3F);
	public static final BiomeBase FOREST = new BiomeForest(4).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
	public static final BiomeBase TAIGA = new BiomeTaiga(5).b(747097).a("Taiga").a(5159473).b().a(0.05F, 0.8F).b(0.1F, 0.4F);

	public static final BiomeBase SWAMPLAND = new BiomeSwamp(6).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
	public static final BiomeBase RIVER = new BiomeRiver(7).b(255).a("River").b(-0.5F, 0.0F);

	public static final BiomeBase HELL = new BiomeHell(8).b(16711680).a("Hell").m().a(2.0F, 0.0F);
	public static final BiomeBase SKY = new BiomeTheEnd(9).b(8421631).a("Sky").m();

	public static final BiomeBase FROZEN_OCEAN = new BiomeOcean(10).b(9474208).a("FrozenOcean").b().b(-1.0F, 0.5F).a(0.0F, 0.5F);
	public static final BiomeBase FROZEN_RIVER = new BiomeRiver(11).b(10526975).a("FrozenRiver").b().b(-0.5F, 0.0F).a(0.0F, 0.5F);
	public static final BiomeBase ICE_PLAINS = new BiomeIcePlains(12).b(16777215).a("Ice Plains").b().a(0.0F, 0.5F);
	public static final BiomeBase ICE_MOUNTAINS = new BiomeIcePlains(13).b(10526880).a("Ice Mountains").b().b(0.3F, 1.3F).a(0.0F, 0.5F);

	public static final BiomeBase MUSHROOM_ISLAND = new BiomeMushrooms(14).b(16711935).a("MushroomIsland").a(0.9F, 1.0F).b(0.2F, 1.0F);
	public static final BiomeBase MUSHROOM_SHORE = new BiomeMushrooms(15).b(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).b(-1.0F, 0.1F);

	public static final BiomeBase BEACH = new BiomeBeach(16).b(16440917).a("Beach").a(0.8F, 0.4F).b(0.0F, 0.1F);
	public static final BiomeBase DESERT_HILLS = new BiomeDesert(17).b(13786898).a("DesertHills").m().a(2.0F, 0.0F).b(0.3F, 0.8F);
	public static final BiomeBase FOREST_HILLS = new BiomeForest(18).b(2250012).a("ForestHills").a(5159473).a(0.7F, 0.8F).b(0.3F, 0.7F);
	public static final BiomeBase TAIGA_HILLS = new BiomeTaiga(19).b(1456435).a("TaigaHills").b().a(5159473).a(0.05F, 0.8F).b(0.3F, 0.8F);

	public static final BiomeBase SMALL_MOUNTAINS = new BiomeBigHills(20).b(7501978).a("Extreme Hills Edge").b(0.2F, 0.8F).a(0.2F, 0.3F);

	public static final BiomeBase JUNGLE = new BiomeJungle(21).b(5470985).a("Jungle").a(5470985).a(1.2F, 0.9F).b(0.2F, 0.4F);
	public static final BiomeBase JUNGLE_HILLS = new BiomeJungle(22).b(2900485).a("JungleHills").a(5470985).a(1.2F, 0.9F).b(1.8F, 0.5F);
	public String y;
	public int z;
	public byte A = (byte)Block.GRASS.id;
	public byte B = (byte)Block.DIRT.id;
	public int C = 5169201;
	public float D = 0.1F;
	public float E = 0.3F;
	public float temperature = 0.5F;
	public float humidity = 0.5F;
	public int H = 16777215;
	public BiomeDecorator I;
	protected List J = new ArrayList();
	protected List K = new ArrayList();
	protected List L = new ArrayList();
	private boolean R;
	private boolean S = true;
	public final int id;
	protected WorldGenTrees N = new WorldGenTrees(false);
	protected WorldGenBigTree O = new WorldGenBigTree(false);
	protected WorldGenForest P = new WorldGenForest(false);
	protected WorldGenSwampTree Q = new WorldGenSwampTree();

	protected BiomeBase(int paramInt)
	{
		this.id = paramInt;
		biomes[paramInt] = this;
		this.I = a();

		this.K.add(new BiomeMeta(EntitySheep.class, 12, 4, 4));
		this.K.add(new BiomeMeta(EntityPig.class, 10, 4, 4));
		this.K.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
		this.K.add(new BiomeMeta(EntityCow.class, 8, 4, 4));

		this.J.add(new BiomeMeta(EntitySpider.class, 10, 4, 4));
		this.J.add(new BiomeMeta(EntityZombie.class, 10, 4, 4));
		this.J.add(new BiomeMeta(EntitySkeleton.class, 10, 4, 4));
		this.J.add(new BiomeMeta(EntityCreeper.class, 10, 4, 4));
		this.J.add(new BiomeMeta(EntitySlime.class, 10, 4, 4));
		this.J.add(new BiomeMeta(EntityEnderman.class, 1, 1, 4));

		this.L.add(new BiomeMeta(EntitySquid.class, 10, 4, 4));
	}

	protected BiomeDecorator a() {
		return new BiomeDecorator(this);
	}

	private BiomeBase a(float paramFloat1, float paramFloat2) {
		if ((paramFloat1 > 0.1F) && (paramFloat1 < 0.2F)) throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");

		this.temperature = paramFloat1;
		this.humidity = paramFloat2;
		return this;
	}

	private BiomeBase b(float paramFloat1, float paramFloat2) {
		this.D = paramFloat1;
		this.E = paramFloat2;
		return this;
	}

	private BiomeBase m() {
		this.S = false;
		return this;
	}

	public WorldGenerator a(Random paramRandom)
	{
		if (paramRandom.nextInt(10) == 0) {
			return this.O;
		}
		return this.N;
	}

	public WorldGenerator b(Random paramRandom) {
		return new WorldGenGrass(Block.LONG_GRASS.id, 1);
	}

	protected BiomeBase b() {
		this.R = true;
		return this;
	}

	protected BiomeBase a(String paramString) {
		this.y = paramString;
		return this;
	}

	protected BiomeBase a(int paramInt) {
		this.C = paramInt;
		return this;
	}

	protected BiomeBase b(int paramInt) {
		this.z = paramInt;
		return this;
	}

	public List getMobs(EnumCreatureType paramEnumCreatureType)
	{
		if (paramEnumCreatureType == EnumCreatureType.MONSTER) return this.J;
		if (paramEnumCreatureType == EnumCreatureType.CREATURE) return this.K;
		if (paramEnumCreatureType == EnumCreatureType.WATER_CREATURE) return this.L;
		return null;
	}

	public boolean c()
	{
		return this.R;
	}

	public boolean d() {
		if (this.R) return false;
		return this.S;
	}

	public boolean e() {
		return this.humidity > 0.85F;
	}

	public float f() {
		return 0.1F;
	}

	public final int g() {
		return (int)(this.humidity * 65536.0F);
	}

	public final int h() {
		return (int)(this.temperature * 65536.0F);
	}

	public final float j()
	{
		return this.temperature;
	}

	public void a(World paramWorld, Random paramRandom, int paramInt1, int paramInt2) {
		this.I.a(paramWorld, paramRandom, paramInt1, paramInt2);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BiomeBase
 * JD-Core Version:		0.6.0
 */