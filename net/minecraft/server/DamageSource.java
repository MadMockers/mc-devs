package net.minecraft.server;

public class DamageSource
{
	public static DamageSource FIRE = new DamageSource("inFire").j();
	public static DamageSource BURN = new DamageSource("onFire").h().j();
	public static DamageSource LAVA = new DamageSource("lava").j();
	public static DamageSource STUCK = new DamageSource("inWall").h();
	public static DamageSource DROWN = new DamageSource("drown").h();
	public static DamageSource STARVE = new DamageSource("starve").h();
	public static DamageSource CACTUS = new DamageSource("cactus");
	public static DamageSource FALL = new DamageSource("fall").h();
	public static DamageSource OUT_OF_WORLD = new DamageSource("outOfWorld").h().i();
	public static DamageSource GENERIC = new DamageSource("generic").h();
	public static DamageSource EXPLOSION = new DamageSource("explosion").m();
	public static DamageSource EXPLOSION2 = new DamageSource("explosion");
	public static DamageSource MAGIC = new DamageSource("magic").h();

	private boolean o = false;
	private boolean p = false;

	private float q = 0.3F;
	private boolean r;
	private boolean s;
	private boolean t;
	public String translationIndex;

	public static DamageSource mobAttack(EntityLiving paramEntityLiving)
	{
		return new EntityDamageSource("mob", paramEntityLiving);
	}

	public static DamageSource playerAttack(EntityHuman paramEntityHuman) {
		return new EntityDamageSource("player", paramEntityHuman);
	}

	public static DamageSource arrow(EntityArrow paramEntityArrow, Entity paramEntity) {
		return new EntityDamageSourceIndirect("arrow", paramEntityArrow, paramEntity).b();
	}

	public static DamageSource fireball(EntityFireball paramEntityFireball, Entity paramEntity) {
		if (paramEntity == null) {
			return new EntityDamageSourceIndirect("onFire", paramEntityFireball, paramEntityFireball).j().b();
		}
		return new EntityDamageSourceIndirect("fireball", paramEntityFireball, paramEntity).j().b();
	}

	public static DamageSource projectile(Entity paramEntity1, Entity paramEntity2) {
		return new EntityDamageSourceIndirect("thrown", paramEntity1, paramEntity2).b();
	}

	public static DamageSource b(Entity paramEntity1, Entity paramEntity2) {
		return new EntityDamageSourceIndirect("indirectMagic", paramEntity1, paramEntity2).h();
	}

	public boolean a()
	{
		return this.s;
	}

	public DamageSource b() {
		this.s = true;
		return this;
	}

	public boolean ignoresArmor() {
		return this.o;
	}

	public float d() {
		return this.q;
	}

	public boolean ignoresInvulnerability() {
		return this.p;
	}

	protected DamageSource(String paramString)
	{
		this.translationIndex = paramString;
	}

	public Entity f() {
		return getEntity();
	}

	public Entity getEntity() {
		return null;
	}

	protected DamageSource h() {
		this.o = true;

		this.q = 0.0F;
		return this;
	}

	protected DamageSource i() {
		this.p = true;
		return this;
	}

	protected DamageSource j() {
		this.r = true;
		return this;
	}

	public String getLocalizedDeathMessage(EntityHuman paramEntityHuman) {
		return LocaleI18n.get("death." + this.translationIndex, new Object[] { paramEntityHuman.name });
	}

	public boolean k() {
		return this.r;
	}

	public String l() {
		return this.translationIndex;
	}

	public DamageSource m() {
		this.t = true;
		return this;
	}

	public boolean n() {
		return this.t;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.DamageSource
 * JD-Core Version:		0.6.0
 */