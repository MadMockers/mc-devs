package net.minecraft.server;

public class EntityDamageSource extends DamageSource
{
	protected Entity o;

	public EntityDamageSource(String paramString, Entity paramEntity)
	{
		super(paramString);
		this.o = paramEntity;
	}

	public Entity getEntity()
	{
		return this.o;
	}

	public String getLocalizedDeathMessage(EntityHuman paramEntityHuman)
	{
		return LocaleI18n.get("death." + this.translationIndex, new Object[] { paramEntityHuman.name, this.o.getLocalizedName() });
	}

	public boolean n()
	{
		return (this.o != null) && ((this.o instanceof EntityLiving)) && (!(this.o instanceof EntityHuman));
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityDamageSource
 * JD-Core Version:		0.6.0
 */