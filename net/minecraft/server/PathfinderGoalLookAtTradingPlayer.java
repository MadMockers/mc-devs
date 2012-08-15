package net.minecraft.server;

public class PathfinderGoalLookAtTradingPlayer extends PathfinderGoalLookAtPlayer
{
	private final EntityVillager b;

	public PathfinderGoalLookAtTradingPlayer(EntityVillager paramEntityVillager)
	{
		super(paramEntityVillager, EntityHuman.class, 8.0F);
		this.b = paramEntityVillager;
	}

	public boolean a()
	{
		if (this.b.q()) {
			this.a = this.b.l_();
			return true;
		}
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.PathfinderGoalLookAtTradingPlayer
 * JD-Core Version:		0.6.0
 */