/*		 */ package net.minecraft.server;
/*		 */ 
/*		 */ public class Material
/*		 */ {
/*	 4 */	 public static final Material AIR = new MaterialGas(MaterialMapColor.b);
/*	 5 */	 public static final Material GRASS = new Material(MaterialMapColor.c);
/*	 6 */	 public static final Material EARTH = new Material(MaterialMapColor.l);
/*	 7 */	 public static final Material WOOD = new Material(MaterialMapColor.o).g();
/*	 8 */	 public static final Material STONE = new Material(MaterialMapColor.m).f();
/*	 9 */	 public static final Material ORE = new Material(MaterialMapColor.h).f();
/*	10 */	 public static final Material WATER = new MaterialLiquid(MaterialMapColor.n).n();
/*	11 */	 public static final Material LAVA = new MaterialLiquid(MaterialMapColor.f).n();
/*	12 */	 public static final Material LEAVES = new Material(MaterialMapColor.i).g().p().n();
/*	13 */	 public static final Material PLANT = new MaterialDecoration(MaterialMapColor.i).n();
/*	14 */	 public static final Material REPLACEABLE_PLANT = new MaterialDecoration(MaterialMapColor.i).g().n().i();
/*	15 */	 public static final Material SPONGE = new Material(MaterialMapColor.e);
/*	16 */	 public static final Material CLOTH = new Material(MaterialMapColor.e).g();
/*	17 */	 public static final Material FIRE = new MaterialGas(MaterialMapColor.b).n();
/*	18 */	 public static final Material SAND = new Material(MaterialMapColor.d);
/*	19 */	 public static final Material ORIENTABLE = new MaterialDecoration(MaterialMapColor.b).n();
/*	20 */	 public static final Material SHATTERABLE = new Material(MaterialMapColor.b).p();
/*	21 */	 public static final Material BUILDABLE_GLASS = new Material(MaterialMapColor.b);
/*	22 */	 public static final Material TNT = new Material(MaterialMapColor.f).g().p();
/*	23 */	 public static final Material CORAL = new Material(MaterialMapColor.i).n();
/*	24 */	 public static final Material ICE = new Material(MaterialMapColor.g).p();
/*	25 */	 public static final Material SNOW_LAYER = new MaterialDecoration(MaterialMapColor.j).i().p().f().n();
/*	26 */	 public static final Material SNOW_BLOCK = new Material(MaterialMapColor.j).f();
/*	27 */	 public static final Material CACTUS = new Material(MaterialMapColor.i).p().n();
/*	28 */	 public static final Material CLAY = new Material(MaterialMapColor.k);
/*	29 */	 public static final Material PUMPKIN = new Material(MaterialMapColor.i).n();
/*	30 */	 public static final Material DRAGON_EGG = new Material(MaterialMapColor.i).n();
/*	31 */	 public static final Material PORTAL = new MaterialPortal(MaterialMapColor.b).o();
/*	32 */	 public static final Material CAKE = new Material(MaterialMapColor.b).n();
/*	33 */	 public static final Material WEB = new MaterialWeb(MaterialMapColor.e).f().n();
/*		 */ 
/*	39 */	 public static final Material PISTON = new Material(MaterialMapColor.m).o();
/*		 */	 private boolean canBurn;
/*		 */	 private boolean H;
/*		 */	 private boolean I;
/*		 */	 public final MaterialMapColor F;
/*	48 */	 private boolean J = true;
/*		 */	 private int K;
/*		 */ 
/*		 */	 public Material(MaterialMapColor paramMaterialMapColor)
/*		 */	 {
/*	52 */		 this.F = paramMaterialMapColor;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isLiquid() {
/*	56 */		 return false;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isBuildable()
/*		 */	 {
/*	64 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean blocksLight() {
/*	68 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isSolid() {
/*	72 */		 return true;
/*		 */	 }
/*		 */ 
/*		 */	 private Material p() {
/*	76 */		 this.I = true;
/*	77 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Material f() {
/*	81 */		 this.J = false;
/*	82 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Material g() {
/*	86 */		 this.canBurn = true;
/*	87 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isBurnable() {
/*	91 */		 return this.canBurn;
/*		 */	 }
/*		 */ 
/*		 */	 public Material i() {
/*	95 */		 this.H = true;
/*	96 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isReplaceable() {
/* 100 */		 return this.H;
/*		 */	 }
/*		 */ 
/*		 */	 public boolean k() {
/* 104 */		 if (this.I) return false;
/* 105 */		 return isSolid();
/*		 */	 }
/*		 */ 
/*		 */	 public boolean isAlwaysDestroyable()
/*		 */	 {
/* 111 */		 return this.J;
/*		 */	 }
/*		 */ 
/*		 */	 public int getPushReaction() {
/* 115 */		 return this.K;
/*		 */	 }
/*		 */ 
/*		 */	 protected Material n() {
/* 119 */		 this.K = 1;
/* 120 */		 return this;
/*		 */	 }
/*		 */ 
/*		 */	 protected Material o() {
/* 124 */		 this.K = 2;
/* 125 */		 return this;
/*		 */	 }
/*		 */ }

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.Material
 * JD-Core Version:		0.6.0
 */