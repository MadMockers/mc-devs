package net.minecraft.server;

public class InstantMobEffect extends MobEffectList
{
	public InstantMobEffect(int paramInt1, boolean paramBoolean, int paramInt2)
	{
		super(paramInt1, paramBoolean, paramInt2);
	}

	public boolean isInstant()
	{
		return true;
	}

	public boolean a(int paramInt1, int paramInt2)
	{
		return paramInt1 >= 1;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.InstantMobEffect
 * JD-Core Version:		0.6.0
 */