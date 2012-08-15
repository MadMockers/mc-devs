package net.minecraft.server;

public class EntityComplexPart extends Entity
{
	public final EntityComplex owner;
	public final String b;

	public EntityComplexPart(EntityComplex paramEntityComplex, String paramString, float paramFloat1, float paramFloat2)
	{
		super(paramEntityComplex.world);
		a(paramFloat1, paramFloat2);
		this.owner = paramEntityComplex;
		this.b = paramString;
	}

	protected void a()
	{
	}

	protected void a(NBTTagCompound paramNBTTagCompound)
	{
	}

	protected void b(NBTTagCompound paramNBTTagCompound)
	{
	}

	public boolean L()
	{
		return true;
	}

	public boolean damageEntity(DamageSource paramDamageSource, int paramInt)
	{
		return this.owner.a(this, paramDamageSource, paramInt);
	}

	public boolean i(Entity paramEntity)
	{
		return (this == paramEntity) || (this.owner == paramEntity);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityComplexPart
 * JD-Core Version:		0.6.0
 */