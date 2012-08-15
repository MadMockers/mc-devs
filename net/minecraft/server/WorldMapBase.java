package net.minecraft.server;

public abstract class WorldMapBase
{
	public final String id;
	private boolean a;

	public WorldMapBase(String paramString)
	{
		this.id = paramString;
	}
	public abstract void a(NBTTagCompound paramNBTTagCompound);

	public abstract void b(NBTTagCompound paramNBTTagCompound);

	public void a() { a(true); }

	public void a(boolean paramBoolean)
	{
		this.a = paramBoolean;
	}

	public boolean b() {
		return this.a;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldMapBase
 * JD-Core Version:		0.6.0
 */