package net.minecraft.server;

public final class WorldSettings
{
	private final long a;
	private final EnumGamemode b;
	private final boolean c;
	private final boolean d;
	private final WorldType e;
	private boolean f;
	private boolean g;

	public WorldSettings(long paramLong, EnumGamemode paramEnumGamemode, boolean paramBoolean1, boolean paramBoolean2, WorldType paramWorldType)
	{
		this.a = paramLong;
		this.b = paramEnumGamemode;
		this.c = paramBoolean1;
		this.d = paramBoolean2;
		this.e = paramWorldType;
	}

	public WorldSettings(WorldData paramWorldData) {
		this(paramWorldData.getSeed(), paramWorldData.getGameType(), paramWorldData.shouldGenerateMapFeatures(), paramWorldData.isHardcore(), paramWorldData.getType());
	}

	public WorldSettings a() {
		this.g = true;
		return this;
	}

	public boolean c()
	{
		return this.g;
	}

	public long d() {
		return this.a;
	}

	public EnumGamemode e() {
		return this.b;
	}

	public boolean f() {
		return this.d;
	}

	public boolean g() {
		return this.c;
	}

	public WorldType h() {
		return this.e;
	}

	public boolean i() {
		return this.f;
	}

	public static EnumGamemode a(int paramInt) {
		return EnumGamemode.a(paramInt);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.WorldSettings
 * JD-Core Version:		0.6.0
 */