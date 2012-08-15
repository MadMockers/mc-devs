package net.minecraft.server;

public class GenLayerRiverInit extends GenLayer
{
	public GenLayerRiverInit(long paramLong, GenLayer paramGenLayer)
	{
		super(paramLong);
		this.a = paramGenLayer;
	}

	public int[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int[] arrayOfInt1 = this.a.a(paramInt1, paramInt2, paramInt3, paramInt4);

		int[] arrayOfInt2 = IntCache.a(paramInt3 * paramInt4);
		for (int i = 0; i < paramInt4; i++) {
			for (int j = 0; j < paramInt3; j++) {
				a(j + paramInt1, i + paramInt2);
				arrayOfInt2[(j + i * paramInt3)] = (arrayOfInt1[(j + i * paramInt3)] > 0 ? a(2) + 2 : 0);
			}
		}

		return arrayOfInt2;
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.GenLayerRiverInit
 * JD-Core Version:		0.6.0
 */