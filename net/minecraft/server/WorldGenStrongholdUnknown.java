package net.minecraft.server;

final class WorldGenStrongholdUnknown extends WorldGenStrongholdPieceWeight
{
	WorldGenStrongholdUnknown(Class paramClass, int paramInt1, int paramInt2)
	{
		super(paramClass, paramInt1, paramInt2);
	}
	public boolean a(int paramInt) {
		return (super.a(paramInt)) && (paramInt > 4);
	}
}

/* Location:					 F:\Minecraft\1.3.1v\craftbukkit\
 * Qualified Name:		 net.minecraft.server.WorldGenStrongholdUnknown
 * JD-Core Version:		0.6.0
 */