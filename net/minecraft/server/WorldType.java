package net.minecraft.server;

public class WorldType
{
	public static final WorldType[] types = new WorldType[16];

	public static final WorldType NORMAL = new WorldType(0, "default", 1).f();
	public static final WorldType FLAT = new WorldType(1, "flat");
	public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");

	public static final WorldType NORMAL_1_1 = new WorldType(8, "default_1_1", 0).a(false);
	private final String name;
	private final int version;
	private boolean h;
	private boolean i;

	private WorldType(int paramInt, String paramString)
	{
		this(paramInt, paramString, 0);
	}

	private WorldType(int paramInt1, String paramString, int paramInt2) {
		this.name = paramString;
		this.version = paramInt2;
		this.h = true;
		types[paramInt1] = this;
	}

	public String name() {
		return this.name;
	}

	public int getVersion()
	{
		return this.version;
	}

	public WorldType a(int paramInt) {
		if ((this == NORMAL) && (paramInt == 0)) {
			return NORMAL_1_1;
		}
		return this;
	}

	private WorldType a(boolean paramBoolean) {
		this.h = paramBoolean;
		return this;
	}

	private WorldType f()
	{
		this.i = true;
		return this;
	}

	public boolean e() {
		return this.i;
	}

	public static WorldType getType(String paramString) {
		for (WorldType localWorldType : types) {
			if ((localWorldType != null) && (localWorldType.name.equalsIgnoreCase(paramString))) {
				return localWorldType;
			}
		}
		return null;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldType
 * JD-Core Version:		0.6.0
 */