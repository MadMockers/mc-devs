package net.minecraft.server;

import java.util.Random;

public class TileEntityEnchantTable extends TileEntity
{
	public int a;
	public float b;
	public float c;
	public float d;
	public float e;
	public float f;
	public float g;
	public float h;
	public float i;
	public float j;
	private static Random r = new Random();

	public void g()
	{
		super.g();
		this.g = this.f;
		this.i = this.h;

		EntityHuman localEntityHuman = this.world.findNearbyPlayer(this.x + 0.5F, this.y + 0.5F, this.z + 0.5F, 3.0D);
		if (localEntityHuman != null) {
			double d1 = localEntityHuman.locX - (this.x + 0.5F);
			double d2 = localEntityHuman.locZ - (this.z + 0.5F);

			this.j = (float)Math.atan2(d2, d1);

			this.f += 0.1F;

			if ((this.f < 0.5F) || (r.nextInt(40) == 0)) {
				float f1 = this.d;
				do
					this.d += r.nextInt(4) - r.nextInt(4);
				while (f1 == this.d);
			}
		}
		else {
			this.j += 0.02F;
			this.f -= 0.1F;
		}

		while (this.h >= 3.141593F)
			this.h -= 6.283186F;
		while (this.h < -3.141593F)
			this.h += 6.283186F;
		while (this.j >= 3.141593F)
			this.j -= 6.283186F;
		while (this.j < -3.141593F)
			this.j += 6.283186F;
		float f2 = this.j - this.h;
		while (f2 >= 3.141593F)
			f2 -= 6.283186F;
		while (f2 < -3.141593F) {
			f2 += 6.283186F;
		}
		this.h += f2 * 0.4F;

		if (this.f < 0.0F) this.f = 0.0F;
		if (this.f > 1.0F) this.f = 1.0F;

		this.a += 1;
		this.c = this.b;

		float f3 = (this.d - this.b) * 0.4F;
		float f4 = 0.2F;
		if (f3 < -f4) f3 = -f4;
		if (f3 > f4) f3 = f4;
		this.e += (f3 - this.e) * 0.9F;

		this.b += this.e;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.TileEntityEnchantTable
 * JD-Core Version:		0.6.0
 */