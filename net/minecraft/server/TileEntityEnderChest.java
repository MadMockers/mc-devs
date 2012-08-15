package net.minecraft.server;

import java.util.Random;

public class TileEntityEnderChest extends TileEntity
{
	public float a;
	public float b;
	public int c;
	private int d;

	public void g()
	{
		super.g();

		if (++this.d % 20 * 4 == 0) {
			this.world.playNote(this.x, this.y, this.z, Block.ENDER_CHEST.id, 1, this.c);
		}

		this.b = this.a;

		float f1 = 0.1F;
		double d2;
		if ((this.c > 0) && (this.a == 0.0F)) {
			double d1 = this.x + 0.5D;
			d2 = this.z + 0.5D;

			this.world.makeSound(d1, this.y + 0.5D, d2, "random.chestopen", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
		}
		if (((this.c == 0) && (this.a > 0.0F)) || ((this.c > 0) && (this.a < 1.0F))) {
			float f2 = this.a;
			if (this.c > 0) this.a += f1; else
				this.a -= f1;
			if (this.a > 1.0F) {
				this.a = 1.0F;
			}
			float f3 = 0.5F;
			if ((this.a < f3) && (f2 >= f3)) {
				d2 = this.x + 0.5D;
				double d3 = this.z + 0.5D;

				this.world.makeSound(d2, this.y + 0.5D, d3, "random.chestclosed", 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
			}
			if (this.a < 0.0F)
				this.a = 0.0F;
		}
	}

	public void b(int paramInt1, int paramInt2)
	{
		if (paramInt1 == 1)
			this.c = paramInt2;
	}

	public void j()
	{
		h();
		super.j();
	}

	public void a() {
		this.c += 1;
		this.world.playNote(this.x, this.y, this.z, Block.ENDER_CHEST.id, 1, this.c);
	}

	public void b() {
		this.c -= 1;
		this.world.playNote(this.x, this.y, this.z, Block.ENDER_CHEST.id, 1, this.c);
	}

	public boolean a(EntityHuman paramEntityHuman) {
		if (this.world.getTileEntity(this.x, this.y, this.z) != this) return false;
		return paramEntityHuman.e(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) <= 64.0D;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.TileEntityEnderChest
 * JD-Core Version:		0.6.0
 */