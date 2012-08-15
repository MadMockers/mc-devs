package net.minecraft.server;

public class EntityAIBodyControl
{
	private EntityLiving entity;
	private int b = 0;
	private float c = 0.0F;

	public EntityAIBodyControl(EntityLiving paramEntityLiving) {
		this.entity = paramEntityLiving;
	}

	public void a() {
		double d1 = this.entity.locX - this.entity.lastX;
		double d2 = this.entity.locZ - this.entity.lastZ;

		if (d1 * d1 + d2 * d2 > 2.500000277905201E-007D)
		{
			this.entity.aq = this.entity.yaw;
			this.entity.as = a(this.entity.aq, this.entity.as, 75.0F);
			this.c = this.entity.as;
			this.b = 0;
			return;
		}

		float f = 75.0F;
		if (Math.abs(this.entity.as - this.c) > 15.0F) {
			this.b = 0;
			this.c = this.entity.as;
		} else {
			this.b += 1;

			if (this.b > 10) {
				f = Math.max(1.0F - (this.b - 10) / 10.0F, 0.0F) * 75.0F;
			}
		}
		this.entity.aq = a(this.entity.as, this.entity.aq, f);
	}

	private float a(float paramFloat1, float paramFloat2, float paramFloat3) {
		float f = MathHelper.g(paramFloat1 - paramFloat2);
		if (f < -paramFloat3) f = -paramFloat3;
		if (f >= paramFloat3) f = paramFloat3;
		return paramFloat1 - f;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.EntityAIBodyControl
 * JD-Core Version:		0.6.0
 */