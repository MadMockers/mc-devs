package net.minecraft.server;

public class EntityDamageSourceIndirect extends EntityDamageSource
{
	private Entity owner;

	public EntityDamageSourceIndirect(String s, Entity entity, Entity entity1)
	{
		super(s, entity);
		this.owner = entity1;
	}

	public Entity f() {
		return this.o;
	}

	public Entity getEntity() {
		return this.owner;
	}

	public String getLocalizedDeathMessage(EntityHuman entityhuman)
	{
		String source = this.owner == null ? "Herobrine" : this.owner.getLocalizedName();
		return LocaleI18n.get("death." + this.translationIndex, new Object[] { entityhuman.name, source });
	}

	public Entity getProximateDamageSource() {
		return super.getEntity();
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityDamageSourceIndirect
 * JD-Core Version:		0.6.0
 */