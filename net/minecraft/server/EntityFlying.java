package net.minecraft.server;

public abstract class EntityFlying extends EntityLiving
{
	public EntityFlying(World paramWorld)
	{
		super(paramWorld);
	}

	protected void a(float paramFloat)
	{
	}

	public void e(float paramFloat1, float paramFloat2)
	{
		if (H()) {
			a(paramFloat1, paramFloat2, 0.02F);
			move(this.motX, this.motY, this.motZ);

			this.motX *= 0.800000011920929D;
			this.motY *= 0.800000011920929D;
			this.motZ *= 0.800000011920929D;
		} else if (J()) {
			a(paramFloat1, paramFloat2, 0.02F);
			move(this.motX, this.motY, this.motZ);
			this.motX *= 0.5D;
			this.motY *= 0.5D;
			this.motZ *= 0.5D;
		} else {
			float f1 = 0.91F;
			if (this.onGround) {
				f1 = 0.5460001F;
				int i = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));
				if (i > 0) {
					f1 = Block.byId[i].frictionFactor * 0.91F;
				}
			}

			float f2 = 0.1627714F / (f1 * f1 * f1);
			a(paramFloat1, paramFloat2, this.onGround ? 0.1F * f2 : 0.02F);

			f1 = 0.91F;
			if (this.onGround) {
				f1 = 0.5460001F;
				int j = this.world.getTypeId(MathHelper.floor(this.locX), MathHelper.floor(this.boundingBox.b) - 1, MathHelper.floor(this.locZ));
				if (j > 0) {
					f1 = Block.byId[j].frictionFactor * 0.91F;
				}
			}

			move(this.motX, this.motY, this.motZ);

			this.motX *= f1;
			this.motY *= f1;
			this.motZ *= f1;
		}
		this.aY = this.aZ;
		double d1 = this.locX - this.lastX;
		double d2 = this.locZ - this.lastZ;
		float f3 = MathHelper.sqrt(d1 * d1 + d2 * d2) * 4.0F;
		if (f3 > 1.0F) f3 = 1.0F;
		this.aZ += (f3 - this.aZ) * 0.4F;
		this.ba += this.aZ;
	}

	public boolean f_()
	{
		return false;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityFlying
 * JD-Core Version:		0.6.0
 */