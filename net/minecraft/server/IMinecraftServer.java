package net.minecraft.server;

public abstract interface IMinecraftServer
{
	public abstract int a(String paramString, int paramInt);

	public abstract String a(String paramString1, String paramString2);

	public abstract void a(String paramString, Object paramObject);

	public abstract void a();

	public abstract String c();

	public abstract String t();

	public abstract int u();

	public abstract String v();

	public abstract String getVersion();

	public abstract int x();

	public abstract int y();

	public abstract String[] getPlayers();

	public abstract String I();

	public abstract String getPlugins();

	public abstract String i(String paramString);

	public abstract boolean isDebugging();

	public abstract void info(String paramString);

	public abstract void warning(String paramString);

	public abstract void j(String paramString);

	public abstract void k(String paramString);
}

/* 
 * Qualified Name:		 net.minecraft.server.IMinecraftServer
 * JD-Core Version:		0.6.0
 */