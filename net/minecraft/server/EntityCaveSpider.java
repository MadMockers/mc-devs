package net.minecraft.server;

public class EntityCaveSpider extends EntitySpider
{
	public EntityCaveSpider(World paramWorld)
	{
		super(paramWorld);
		this.texture = "/mob/cavespider.png";
		a(0.7F, 0.5F);
	}

	public int getMaxHealth()
	{
		return 12;
	}

	public boolean k(Entity paramEntity)
	{
		if (super.k(paramEntity))
		{
			if ((paramEntity instanceof EntityLiving)) {
				int i = 0;
				if (this.world.difficulty > 1)
				{
					if (this.world.difficulty == 2)
						i = 7;
					else if (this.world.difficulty == 3) {
						i = 15;
					}
				}
				if (i > 0) {
					((EntityLiving)paramEntity).addEffect(new MobEffect(MobEffectList.POISON.id, i * 20, 0));
				}
			}

			return true;
		}
		return false;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityCaveSpider
 * JD-Core Version:		0.6.0
 */