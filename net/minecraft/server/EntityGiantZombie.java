package net.minecraft.server;

public class EntityGiantZombie extends EntityMonster
{
	public EntityGiantZombie(World paramWorld)
	{
		super(paramWorld);
		this.texture = "/mob/zombie.png";
		this.bw = 0.5F;
		this.damage = 50;
		this.height *= 6.0F;
		a(this.width * 6.0F, this.length * 6.0F);
	}

	public int getMaxHealth()
	{
		return 100;
	}

	public float a(int paramInt1, int paramInt2, int paramInt3)
	{
		return this.world.o(paramInt1, paramInt2, paramInt3) - 0.5F;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityGiantZombie
 * JD-Core Version:		0.6.0
 */