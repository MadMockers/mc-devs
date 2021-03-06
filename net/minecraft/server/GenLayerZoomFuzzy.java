package net.minecraft.server;

public class GenLayerZoomFuzzy extends GenLayer
{
	public GenLayerZoomFuzzy(long paramLong, GenLayer paramGenLayer)
	{
		super(paramLong);
		this.a = paramGenLayer;
	}

	public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = paramInt1 >> 1;
		int j = paramInt2 >> 1;
		int k = (paramInt3 >> 1) + 3;
		int m = (paramInt4 >> 1) + 3;
		int[] arrayOfInt1 = this.a.a(i, j, k, m);

		int[] arrayOfInt2 = IntCache.a(k * 2 * (m * 2));
		int n = k << 1;
		for (int i1 = 0; i1 < m - 1; i1++) {
			i2 = i1 << 1;
			int i3 = i2 * n;
			int i4 = arrayOfInt1[(0 + (i1 + 0) * k)];
			int i5 = arrayOfInt1[(0 + (i1 + 1) * k)];
			for (int i6 = 0; i6 < k - 1; i6++) {
				a(i6 + i << 1, i1 + j << 1);
				int i7 = arrayOfInt1[(i6 + 1 + (i1 + 0) * k)];
				int i8 = arrayOfInt1[(i6 + 1 + (i1 + 1) * k)];

				arrayOfInt2[i3] = i4;
				arrayOfInt2[(i3++ + n)] = a(i4, i5);
				arrayOfInt2[i3] = a(i4, i7);
				arrayOfInt2[(i3++ + n)] = b(i4, i7, i5, i8);

				i4 = i7;
				i5 = i8;
			}
		}
		int[] arrayOfInt3 = IntCache.a(paramInt3 * paramInt4);
		for (int i2 = 0; i2 < paramInt4; i2++) {
			System.arraycopy(arrayOfInt2, (i2 + (paramInt2 & 0x1)) * (k << 1) + (paramInt1 & 0x1), arrayOfInt3, i2 * paramInt3, paramInt3);
		}
		return arrayOfInt3;
	}

	protected int a(int paramInt1, int paramInt2) {
		return a(2) == 0 ? paramInt1 : paramInt2;
	}

	protected int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		int i = a(4);
		if (i == 0) return paramInt1;
		if (i == 1) return paramInt2;
		if (i == 2) return paramInt3;
		return paramInt4;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.GenLayerZoomFuzzy
 * JD-Core Version:		0.6.0
 */