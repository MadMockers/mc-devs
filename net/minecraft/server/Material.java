package net.minecraft.server;

public class Material
{
	public static final Material AIR = new MaterialGas(MaterialMapColor.b);
	public static final Material GRASS = new Material(MaterialMapColor.c);
	public static final Material EARTH = new Material(MaterialMapColor.l);
	public static final Material WOOD = new Material(MaterialMapColor.o).g();
	public static final Material STONE = new Material(MaterialMapColor.m).f();
	public static final Material ORE = new Material(MaterialMapColor.h).f();
	public static final Material WATER = new MaterialLiquid(MaterialMapColor.n).n();
	public static final Material LAVA = new MaterialLiquid(MaterialMapColor.f).n();
	public static final Material LEAVES = new Material(MaterialMapColor.i).g().p().n();
	public static final Material PLANT = new MaterialDecoration(MaterialMapColor.i).n();
	public static final Material REPLACEABLE_PLANT = new MaterialDecoration(MaterialMapColor.i).g().n().i();
	public static final Material SPONGE = new Material(MaterialMapColor.e);
	public static final Material CLOTH = new Material(MaterialMapColor.e).g();
	public static final Material FIRE = new MaterialGas(MaterialMapColor.b).n();
	public static final Material SAND = new Material(MaterialMapColor.d);
	public static final Material ORIENTABLE = new MaterialDecoration(MaterialMapColor.b).n();
	public static final Material SHATTERABLE = new Material(MaterialMapColor.b).p();
	public static final Material BUILDABLE_GLASS = new Material(MaterialMapColor.b);
	public static final Material TNT = new Material(MaterialMapColor.f).g().p();
	public static final Material CORAL = new Material(MaterialMapColor.i).n();
	public static final Material ICE = new Material(MaterialMapColor.g).p();
	public static final Material SNOW_LAYER = new MaterialDecoration(MaterialMapColor.j).i().p().f().n();
	public static final Material SNOW_BLOCK = new Material(MaterialMapColor.j).f();
	public static final Material CACTUS = new Material(MaterialMapColor.i).p().n();
	public static final Material CLAY = new Material(MaterialMapColor.k);
	public static final Material PUMPKIN = new Material(MaterialMapColor.i).n();
	public static final Material DRAGON_EGG = new Material(MaterialMapColor.i).n();
	public static final Material PORTAL = new MaterialPortal(MaterialMapColor.b).o();
	public static final Material CAKE = new Material(MaterialMapColor.b).n();
	public static final Material WEB = new MaterialWeb(MaterialMapColor.e).f().n();

	public static final Material PISTON = new Material(MaterialMapColor.m).o();
	private boolean canBurn;
	private boolean H;
	private boolean I;
	public final MaterialMapColor F;
	private boolean J = true;
	private int K;

	public Material(MaterialMapColor paramMaterialMapColor)
	{
		this.F = paramMaterialMapColor;
	}

	public boolean isLiquid() {
		return false;
	}

	public boolean isBuildable()
	{
		return true;
	}

	public boolean blocksLight() {
		return true;
	}

	public boolean isSolid() {
		return true;
	}

	private Material p() {
		this.I = true;
		return this;
	}

	protected Material f() {
		this.J = false;
		return this;
	}

	protected Material g() {
		this.canBurn = true;
		return this;
	}

	public boolean isBurnable() {
		return this.canBurn;
	}

	public Material i() {
		this.H = true;
		return this;
	}

	public boolean isReplaceable() {
		return this.H;
	}

	public boolean k() {
		if (this.I) return false;
		return isSolid();
	}

	public boolean isAlwaysDestroyable()
	{
		return this.J;
	}

	public int getPushReaction() {
		return this.K;
	}

	protected Material n() {
		this.K = 1;
		return this;
	}

	protected Material o() {
		this.K = 2;
		return this;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.Material
 * JD-Core Version:		0.6.0
 */