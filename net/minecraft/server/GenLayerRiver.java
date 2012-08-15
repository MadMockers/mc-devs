package net.minecraft.server;

public class GenLayerRiver extends GenLayer
{
	public GenLayerRiver(long paramLong, GenLayer paramGenLayer)
	{
		super(paramLong);
		this.a = paramGenLayer;
	}

	public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = paramInt1 - 1;
		int j = paramInt2 - 1;
		int k = paramInt3 + 2;
		int m = paramInt4 + 2;
		int[] arrayOfInt1 = this.a.a(i, j, k, m);

		int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
		for (int n = 0; n < paramInt4; n++) {
			for (int i1 = 0; i1 < paramInt3; i1++) {
				int i2 = arrayOfInt1[(i1 + 0 + (n + 1) * k)];
				int i3 = arrayOfInt1[(i1 + 2 + (n + 1) * k)];
				int i4 = arrayOfInt1[(i1 + 1 + (n + 0) * k)];
				int i5 = arrayOfInt1[(i1 + 1 + (n + 2) * k)];
				int i6 = arrayOfInt1[(i1 + 1 + (n + 1) * k)];
				if ((i6 == 0) || (i2 == 0) || (i3 == 0) || (i4 == 0) || (i5 == 0) || (i6 != i2) || (i6 != i4) || (i6 != i3) || (i6 != i5))
					arrayOfInt2[(i1 + n * paramInt3)] = BiomeBase.RIVER.id;
				else {
					arrayOfInt2[(i1 + n * paramInt3)] = -1;
				}
			}
		}

		return arrayOfInt2;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.GenLayerRiver
 * JD-Core Version:		0.6.0
 */