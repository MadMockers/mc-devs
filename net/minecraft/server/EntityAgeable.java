package net.minecraft.server;

public abstract class EntityAgeable extends EntityCreature
{
	public boolean ageLocked = false;

	public EntityAgeable(World world) {
		super(world);
	}

	protected void a() {
		super.a();
		this.datawatcher.a(12, new Integer(0));
	}

	public int getAge() {
		return this.datawatcher.getInt(12);
	}

	public void setAge(int i) {
		this.datawatcher.watch(12, Integer.valueOf(i));
	}

	public void b(NBTTagCompound nbttagcompound) {
		super.b(nbttagcompound);
		nbttagcompound.setInt("Age", getAge());
		nbttagcompound.setBoolean("AgeLocked", this.ageLocked);
	}

	public void a(NBTTagCompound nbttagcompound) {
		super.a(nbttagcompound);
		setAge(nbttagcompound.getInt("Age"));
		this.ageLocked = nbttagcompound.getBoolean("AgeLocked");
	}

	public void d() {
		super.d();
		int i = getAge();

		if (this.ageLocked) return;
		if (i < 0) {
			i++;
			setAge(i);
		} else if (i > 0) {
			i--;
			setAge(i);
		}
	}

	public boolean isBaby() {
		return getAge() < 0;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.EntityAgeable
 * JD-Core Version:		0.6.0
 */