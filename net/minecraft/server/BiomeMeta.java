package net.minecraft.server;

public class BiomeMeta extends WeightedRandomChoice
{
	public Class b;
	public int c;
	public int d;

	public BiomeMeta(Class paramClass, int paramInt1, int paramInt2, int paramInt3)
	{
		super(paramInt1);
		this.b = paramClass;
		this.c = paramInt2;
		this.d = paramInt3;
	}
}

/* 
 * Qualified Name:		 net.minecraft.server.BiomeMeta
 * JD-Core Version:		0.6.0
 */