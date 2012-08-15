package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class AABBPool
{
	private final int a;
	private final int b;
	private final List c = new ArrayList();
	private int d = 0;
	private int e = 0;
	private int f = 0;

	public AABBPool(int i, int j) {
		this.a = i;
		this.b = j;
	}

	public AxisAlignedBB a(double d0, double d1, double d2, double d3, double d4, double d5) {
		if (this.f == 0) return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
		AxisAlignedBB axisalignedbb;
		if (this.d >= this.c.size()) {
			AxisAlignedBB axisalignedbb = new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
			this.c.add(axisalignedbb);
		} else {
			axisalignedbb = (AxisAlignedBB)this.c.get(this.d);
			axisalignedbb.b(d0, d1, d2, d3, d4, d5);
		}

		this.d += 1;
		return axisalignedbb;
	}

	public void a() {
		if (this.d > this.e) {
			this.e = this.d;
		}

		if ((this.f++ & 0xFF) == 0) {
			int newSize = this.c.size() - (this.c.size() >> 3);
			if (newSize > this.e) {
				for (int i = this.c.size() - 1; i > newSize; i--) {
					this.c.remove(i);
				}
			}

			this.e = 0;
		}

		this.d = 0;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.AABBPool
 * JD-Core Version:		0.6.0
 */