package net.minecraft.server;

public class DemoWorldServer extends WorldServer
{
	private static final long L = "North Carolina".hashCode();

	public static final WorldSettings a = new WorldSettings(L, EnumGamemode.SURVIVAL, true, false, WorldType.NORMAL).a();

	public DemoWorldServer(MinecraftServer paramMinecraftServer, IDataManager paramIDataManager, String paramString, int paramInt, MethodProfiler paramMethodProfiler) {
		super(paramMinecraftServer, paramIDataManager, paramString, paramInt, a, paramMethodProfiler);
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.DemoWorldServer
 * JD-Core Version:		0.6.0
 */