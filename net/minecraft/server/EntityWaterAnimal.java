package net.minecraft.server;

import java.util.Random;

public abstract class EntityWaterAnimal extends EntityCreature
	implements IAnimal
{
	public EntityWaterAnimal(World paramWorld)
	{
		super(paramWorld);
	}

	public boolean aU()
	{
		return true;
	}

	public boolean canSpawn()
	{
		return this.world.b(this.boundingBox);
	}

	public int aG()
	{
		return 120;
	}

	protected boolean ba()
	{
		return true;
	}

	protected int getExpValue(EntityHuman paramEntityHuman)
	{
		return 1 + this.world.random.nextInt(3);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityWaterAnimal
 * JD-Core Version:		0.6.0
 */