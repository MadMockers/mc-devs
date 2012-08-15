package net.minecraft.server;

public class ControllerLook
{
	private EntityLiving a;
	private float b;
	private float c;
	private boolean d = false;
	private double e;
	private double f;
	private double g;

	public ControllerLook(EntityLiving paramEntityLiving)
	{
		this.a = paramEntityLiving;
	}

	public void a(Entity paramEntity, float paramFloat1, float paramFloat2) {
		this.e = paramEntity.locX;
		if ((paramEntity instanceof EntityLiving)) this.f = (paramEntity.locY + paramEntity.getHeadHeight()); else
			this.f = ((paramEntity.boundingBox.b + paramEntity.boundingBox.e) / 2.0D);
		this.g = paramEntity.locZ;
		this.b = paramFloat1;
		this.c = paramFloat2;
		this.d = true;
	}

	public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
		this.e = paramDouble1;
		this.f = paramDouble2;
		this.g = paramDouble3;
		this.b = paramFloat1;
		this.c = paramFloat2;
		this.d = true;
	}

	public void a() {
		this.a.pitch = 0.0F;

		if (this.d) {
			this.d = false;

			double d1 = this.e - this.a.locX;
			double d2 = this.f - (this.a.locY + this.a.getHeadHeight());
			double d3 = this.g - this.a.locZ;
			double d4 = MathHelper.sqrt(d1 * d1 + d3 * d3);

			float f1 = (float)(Math.atan2(d3, d1) * 180.0D / 3.141592741012573D) - 90.0F;
			float f2 = (float)(-(Math.atan2(d2, d4) * 180.0D / 3.141592741012573D));
			this.a.pitch = a(this.a.pitch, f2, this.c);
			this.a.as = a(this.a.as, f1, this.b);
		} else {
			this.a.as = a(this.a.as, this.a.aq, 10.0F);
		}

		float f3 = MathHelper.g(this.a.as - this.a.aq);

		if (!this.a.getNavigation().f())
		{
			if (f3 < -75.0F) this.a.as = (this.a.aq - 75.0F);
			if (f3 > 75.0F) this.a.as = (this.a.aq + 75.0F); 
		}
	}

	private float a(float paramFloat1, float paramFloat2, float paramFloat3)
	{
		float f1 = MathHelper.g(paramFloat2 - paramFloat1);
		if (f1 > paramFloat3) {
			f1 = paramFloat3;
		}
		if (f1 < -paramFloat3) {
			f1 = -paramFloat3;
		}
		return paramFloat1 + f1;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.ControllerLook
 * JD-Core Version:		0.6.0
 */